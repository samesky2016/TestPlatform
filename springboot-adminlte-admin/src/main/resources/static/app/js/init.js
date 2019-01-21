
(function ($, window) {

"use strict";

//Ajax提交操作,带确认提示,用于AJAX删除单个记录
$("*[data-tiggle='ajax']").click(function(){
	
	var dataUrl = $(this).attr("data-submit-url");
	var dataConfirm = $(this).attr("data-confirm");
	
	$.confirm({
		type: 'red',
		closeIcon: true,
	    title: '警告',
	    content: dataConfirm ? dataConfirm : '确认操作?',
	    buttons: {
	        '确认': {
	            btnClass: 'btn-blue',
	            action: function(){
	            	$.post(dataUrl,{},function(json){
						if(json.code==200){
							window.location.reload();
						}else{
							$.alert({
							    title: '提示',
							    content: json.msg,
							    buttons:{"好的":{ btnClass: 'btn-blue'}}
							});
						}
					});
	            }
	        },
	        '取消':{}
	    }
	});
	
});

//批量删除确认框

$("*[delete-batch-url]").click(function(){
	var deleteBatchUrl = $(this).attr('delete-batch-url');
	var ids = [];
	$.each($("input:checked"),function(n,i){
		if($(this).val()!='root'){
		   ids.push($(this).val());
		}
	});
	
	if(ids.length==0){
		$.alert({
		    title: '提示',
		    backgroundDismiss:true,
		    content: "请选择要删除的记录!",
		    buttons:{"好的":{ btnClass: 'btn-blue'}}
		});
	}else{
		$.confirm({
			type: 'red',
			closeIcon: true,
		    title: '警告',
		    content: "确认删除选中的【"+ids.length+"】条记录?",
		    buttons: {
		        '确认': {
		            btnClass: 'btn-blue',
		            action: function(){
		            	$.post(deleteBatchUrl,{id:ids},function(json){
							if(json.code==200){
								window.location.reload();
							}else{
								$.alert({
								    title: '提示',
								    content: json.msg,
								    buttons:{"好的":{ btnClass: 'btn-blue'}}
								});
							}
						});
		            }
		        },
		        '取消':{}
		    }
		});		
	}
});


//ajaxmodel,异步弹出框
$("*[data-tiggle='ajaxmodel']").click(function(){
	var dataUrl = $(this).attr("data-url");
	var dataMax = eval($(this).attr("data-max"));
	var dataTitle = $(this).attr("data-title");
	$.confirm({
	    title: dataTitle ? dataTitle : "标题",
	    columnClass: dataMax ? 'col-md-9 col-md-offset-1' : 'col-md-6 col-md-offset-3',
	    content: 'url:'+dataUrl,
		closeIcon: true,
		backgroundDismiss:true,
	    buttons:{'关闭':{}}
	});
});

//icheck
var checkedBgColor = "#f5f5f5",
		unCheckedBgColor = "#fff";
	//单选
	$(".checkbox-item").on('ifChecked',function(){
		$(this).parents('tr').css('background',checkedBgColor);
	}).on('ifUnchecked',function(){
		$(this).parents("tr").css('background',unCheckedBgColor);
	});
	//全选
	$(".checkbox-toolbar").on('ifChecked',function(){
		$(".checkbox-item").iCheck('check');
		$(".checkbox-item").parents("tr").css('background',checkedBgColor);
	}).on('ifUnchecked',function(){
		$(".checkbox-item").iCheck('uncheck');
		$(".checkbox-item").parents("tr").css('background',unCheckedBgColor);
	});

	$(function(){
		//sysc,异步加载数据 
		//用法,<span sysc sysc-url="/system/role/getCount?roleId=${role.id}" format=" [ %s个用户 ]">
		var syscDoms = $("*[sysc]");
		syscDoms.html('<i class="fa fa-refresh fa-spin"></i> loading...');
		$.each(syscDoms,function(i,n){
			var syscUrl = $(n).attr("sysc-url");
			var format  = $(n).attr("format");
			if(syscUrl){
				$.post(syscUrl,{},function(data){
					$(n).text(format?format.replace("%s",data) : data);
				},"text")
			}
		});
	});
	
	/**
	 * 导出报表
	 */
    
    window.exportTo = function(fileName) {
    
    	var date = new Date();
    	
		$(".table").table2excel({
			exclude: ".noExl",
			name: "Excel Document Name",
			filename: fileName+date.getTime(),
			exclude_img: true,
			exclude_links: true,
			exclude_inputs: true
		});
	}
	
		/**
	 * 导出报表2
	 */
    
//第五种方法
        var idTmr;
        function  getExplorer() {
            var explorer = window.navigator.userAgent ;
            //ie
            if (explorer.indexOf("MSIE") >= 0) {
                return 'ie';
            }
            //firefox
            else if (explorer.indexOf("Firefox") >= 0) {
                return 'Firefox';
            }
            //Chrome
            else if(explorer.indexOf("Chrome") >= 0){
                return 'Chrome';
            }
            //Opera
            else if(explorer.indexOf("Opera") >= 0){
                return 'Opera';
            }
            //Safari
            else if(explorer.indexOf("Safari") >= 0){
                return 'Safari';
            }
        }
        window.exportToTable = function(tableid) {
            if(getExplorer()=='ie')
            {
                var curTbl = document.getElementById(tableid);
                var oXL = new ActiveXObject("Excel.Application");
                var oWB = oXL.Workbooks.Add();
                var xlsheet = oWB.Worksheets(1);
                var sel = document.body.createTextRange();
                sel.moveToElementText(curTbl);
                sel.select();
                sel.execCommand("Copy");
                xlsheet.Paste();
                oXL.Visible = true;
 
                try {
                    var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
                } catch (e) {
                    print("Nested catch caught " + e);
                } finally {
                    oWB.SaveAs(fname);
                    oWB.Close(savechanges = false);
                    oXL.Quit();
                    oXL = null;
                    idTmr = window.setInterval("Cleanup();", 1);
                }
 
            }
            else
            {
                tableToExcel(tableid)
            }
        }
        function Cleanup() {
            window.clearInterval(idTmr);
            CollectGarbage();
        }
        var tableToExcel = (function() {
            var uri = 'data:application/vnd.ms-excel;base64,',
                    template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',
                    base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
                    format = function(s, c) {
                        return s.replace(/{(\w+)}/g,
                                function(m, p) { return c[p]; }) }
            return function(table, name) {
                if (!table.nodeType) table = document.getElementById(table)
                var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
                window.location.href = uri + base64(format(template, ctx))
            }
        })()

	
})(jQuery, window);
