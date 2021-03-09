//扩展easyui表单验证规则
$.extend($.fn.validatebox.defaults.rules, {
	maxLength:{
	    validator: function(value, param){
	        return value.length<=param[0];
	    },
	    message: '最大长度为{0}'
	},
	Length:{
	    validator: function(value, param){
	        return value.length>=param[0]&&value.length<=param[1];
	    },
	    message: '长度为{0}-{1}'
	},
	phone:{
	    validator: function(value, param){
	    	var reg = /^1[34578]\d{9}$/;
            return reg.test(value);
	    },
	    message: '请输入正确的电话号码'
	}
});
//验证input加公共属性
$(function(){
	$(".easyui-validatebox").validatebox({
	    validateOnCreate:false,
	    validateOnBlur:true,
	    tipPosition:"right",
	    missingMessage:'必填项'
	});
});


















