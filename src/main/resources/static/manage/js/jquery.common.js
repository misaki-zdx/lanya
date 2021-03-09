$(function(){
	//获取当前窗口大小并且减去head和foot的高度
	var winHeight = $(window).height()-50-45-50;
	$(".span10").css("min-height",winHeight+"px");
});
//自定义jquery方法
;(function($){
	$.fn.extend({
	    serializeObject:function(){
    	   var o = {};    
    	   var a = this.serializeArray();    
    	   $.each(a, function() {    
    	       if (o[this.name]) {    
    	           if (!o[this.name].push) {    
    	               o[this.name] = [o[this.name]];    
    	           }    
    	           o[this.name].push(this.value || '');    
    	       } else {    
    	           o[this.name] = this.value || '';    
    	       }    
    	   });    
    	   return o;    
	    },	
		loading:function(options){
			options = $.extend({
				"setTimeout":800,
				"fadeOut":1000
			},options);
			
			this.append('<div class="loadingDiv" style="background:white;width:100%;height:100%;position:fixed;top:0px;left:0px;"><img style="width:30px;height:30px;position:absolute;top:50%;left:50%;margin-top:-15px;margin-left:-15px;" src="img/loading.gif"/></div>');
			window.onload = function(){
				window.setTimeout(function(){
					$(".loadingDiv").fadeOut(options.fadeOut,function(){$(".loadingDiv").remove()});
				},options.setTimeout);
			}
			return this;
		},
		//基础表格样式
		baseDatagrid:function(options){
			options = $.extend({
				fit:true,
		        fitColumns:true,
		        pagination:true,
		        pageSize:10,
		        pageList:[5,10,15,30],
		        rownumbers:true,
		        toolbar:"#toolbar"
			},options);
			
			this.datagrid(options);
			
			return this;
		},
		//详情表格样式
		detailDatagrid:function(options){
			options = $.extend({
				fit:true,
				fitColumns:true,
				pagination:true,
			    pageSize:10,
			    pageList:[5,10,15,30],
				rownumbers:true,
			    toolbar:"#toolbar",
			    view:detailview
			},options);
			
			this.datagrid(options);
			
			return this;
		},
		//基础窗口样式
		baseDialog:function(options){
			var currentWindow = this;
			
			options = $.extend({
				closed:true,
		        modal:true,
		        buttons:[{
					text:'提交',
					iconCls:'icon-add',
					handler:function(){
						currentWindow.find("form").form('submit',{
						    url:options.url,
						    onSubmit: function(){
						    	var flag = $(this).form('validate');
						        return flag;
						    },
						    success:function(data){
						    	data = $.parseJSON(data);
						        if(data.msgCode==MsgCode.REQUEST_SCCESS){
						        	try{
						        		//兼容treegrid
							        	component.dataGrid.treegrid("reload");
						        	}catch(e){
						        		component.dataGrid.datagrid("reload");
						        	}
						        	
						        	currentWindow.dialog("close");
						        	currentWindow.find("form").form('clear');
						        	currentWindow.find("input").val("");
						        }else{
						        	alert(data.msg);
						        }
						    }
						});
					}
				},{
					text:'重置',
					iconCls:'icon-cancel',
					handler:function(){
						currentWindow.find("form").form('clear');
					}
				}]
			},options);
			
			this.dialog(options);
			
			return this;
		},
		//多条件查询窗口样式
		searchDialog:function(options){
			var currentWindow = this;
			
			options = $.extend({
				closed:true,
		        modal:true,
		        buttons:[{
					text:'提交',
					iconCls:'icon-add',
					handler:function(){
						var searchData = component.searchWindowForm.serializeObject();
						component.dataGrid.datagrid('reload',searchData);
						component.searchWindow.dialog("close");
						component.searchWindow.find("form").form('clear');
					}
				},{
					text:'重置',
					iconCls:'icon-cancel',
					handler:function(){
						component.searchWindow.find("form").form('clear');
					}
				}]
			},options);
			
			this.dialog(options);
			return this;
		}
	});
})(jQuery);

//封装全局函数
$.extend({
    beginLoading:function(){
    	$("body").append('<div class="loadingDiv" style="background:white;background:rgba(255,255,255,0.3);width:100%;height:100%;position:fixed;top:0px;left:0px;z-index:9999;"><img style="width:30px;height:30px;position:absolute;top:50%;left:50%;margin-top:-15px;margin-left:-15px;" src="img/loading.gif"/></div>');
    },	
    endLoading:function(){
    	$(".loadingDiv").fadeOut(300,function(){$(".loadingDiv").remove()});
    },	
	mainAjax:function(options){
		options = $.extend({
 			type:"post",
 			async:true,
 			dataType:"json",
 			beforeSend:function(){
 				$.beginLoading();
 			},
 			complete:function(){
 				$.endLoading();
 			}
		},options);
		
		$.ajax(options);
	},
	isEmpty:function(value){
		if(null==value||undefined==value||""==value){
			return true;
		}else{
			return false;
		}
	},
	//批量删除数据
	deleteByIds:function(ajaxOptions){
		ajaxOptions = $.extend({
 			type:"post",
 			async:true,
 			dataType:"json"
		},ajaxOptions);
		
		if(window.confirm("确定要删除吗?")){
     		$.mainAjax(ajaxOptions);
     	}
	},
	delData:function(component,options){
		try{
    		//兼容treegrid
			var rows = component.dataGrid.treegrid('getCheckedNodes');
    	}catch(e){
    		var rows = component.dataGrid.datagrid('getSelections');
    	}
		
		if($.isEmpty(rows)){
			alert("请先选择要删除的行！");
		}else{
			var idArray = [];
			for(var i=0;i<rows.length;i++){
				idArray.push(rows[i].id);
			}
			
			$.deleteByIds({
					url:options.url,
					data:{
			 			"ids":idArray.join()
			 		},
			 		success:function(data){
			 			if(data.msgCode==MsgCode.REQUEST_SCCESS){
			 				try{
				        		//兼容treegrid
					        	component.dataGrid.treegrid("reload");
				        	}catch(e){
				        		component.dataGrid.datagrid("reload");
				        	}
				        }else{
				        	alert(data.msg);
				        }
		 			}
			});
		}
	},
	//获取数据详情并回填表单
	fillForm:function(ajaxOptions){
		ajaxOptions = $.extend({
 			type:"post",
 			async:false,
 			dataType:"json",
 			success:function(data){
				var $inputs = ajaxOptions.currentForm.find("input");
				for(var i=0;i<$inputs.length;i++){
					var $input = $($inputs[i]);
					var inputName = $input.attr("name");
					if(!$.isEmpty(inputName)){
						$input.val(data["po"][inputName.split(".")[1]]);
					}
				}
			}
		},ajaxOptions);
		
		$.mainAjax(ajaxOptions);
	},
	//是否选择了要修改的行
	isAllowUpdate:function(component){
    	var rows = component.dataGrid.datagrid('getSelections');
		if($.isEmpty(rows)){
			alert("请先选择要修改的行！");
			return false;
		}
		if(rows.length>1){
			alert("只能修改一条数据！");
			return false;
		}
		
		return true;
	},
	//打开更新窗口
	openUpdateWindow:function(component,options){
    	var rows = component.dataGrid.datagrid('getSelections');
	 	if($.isAllowUpdate(component)){
	 		//获取数据详情
			var id = rows[0].id;
			$.fillForm({
				url:options.url,
				data:{
					"po.id":id
				},
				currentForm:component.updateWindoworm
			});
			
			component.updateWindow.dialog("open");
	 	}
	},
	//审核
	auditing:function(component,options){
    	var rows = component.dataGrid.datagrid('getSelections');
		if($.isEmpty(rows)){
			alert("请先选择要删除的行！");
		}else{
			var idArray = [];
			for(var i=0;i<rows.length;i++){
				idArray.push(rows[i].id);
			}
			
			$.mainAjax({
				url:options.url,
				data:{
					"ids":idArray.join(),
					"auditingState":options.auditingState
				},
				success:function(data) {
					if(data.msgCode==MsgCode.REQUEST_SCCESS){
			        	component.dataGrid.datagrid("reload");
			        }
			        alert(data.msg);
				}
			});
		}
	}
});

//加进度菊花
// $("body").loading(
//    {
// 	   "setTimeout":500,
// 	   "fadeOut":300 
//    }
// );

//判断标记
MsgCode = {
	"REQUEST_SCCESS":1,
	"REQUEST_FALSE":2,
	"SESSION_TIME_OUT":3
}

//ajax完成时回调函数
$(document).ajaxComplete(function(event, xhr, settings) {
    //从http头信息取出sessionstatus，判断是否是 timeout
    if(xhr.getResponseHeader("sessionstatus")=="timeout"){ 
        //从http头信息取出登录的url ＝ loginPath
        if(xhr.getResponseHeader("loginPath")){
            alert("会话过期，请重新登陆!");
            //打会到登录页面
            window.location.replace(xhr.getResponseHeader("loginPath"));  
        }else{  
            alert("请求超时请重新登陆 !");  
        }  
    }  
}); 














