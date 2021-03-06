$(function() {
	$('#loginForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			"po.email": {
				message: '邮箱校验失败',
				validators: {
					notEmpty: {
						message: '邮箱不能为空'
					},
					regexp: {
						regexp: /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,
						message: '邮箱格式错误'
					}
				}
			},
			"po.pwd": {
				message: '密码校验失败',
				validators: {
					notEmpty: {
						message: '密码不能为空'
					},
					regexp: {
						regexp: /^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\S]{8,}$/,
						message: '密码包含字母、数字及特殊字符，8位以上'
					}
				}
			}
		}
	}).on('success.form.bv', function(e) { //点击提交之后
		e.preventDefault();
		var $form = $(e.target);
		var bv = $form.data('bootstrapValidator');
		var md5Str1 = $.md5($("#loginPwd").val());
		$("#loginPwd").val(md5Str1);
		e.target.submit();
	});

	$('#registerForm').bootstrapValidator({
		message: 'This value is not valid',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
			validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			"po.email": {
				message: '邮箱校验失败',
				validators: {
					notEmpty: {
						message: '邮箱不能为空'
					},
					regexp: {
						regexp: /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/,
						message: '邮箱格式错误'
					}
				}
			},
			"po.pwd": {
				message: '密码校验失败',
				validators: {
					notEmpty: {
						message: '密码不能为空'
					},
					regexp: {
						regexp: /^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\S]{8,}$/,
						message: '密码包含字母、数字及特殊字符，8位以上'
					}
				}
			},
			"confirmPwd": {
				message: '密码校验失败',
				validators: {
					notEmpty: {
						message: '密码不能为空'
					},
					regexp: {
						regexp: /^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\S]{8,}$/,
						message: '密码包含字母、数字及特殊字符，8位以上'
					},
					identical: {
						field: 'po.pwd',
						message: '两次输入不一致'
					},
					different: {
						field: 'pw.email',
						message: '密码不能和邮箱一样'
					}
				}
			},
		}
	}).on('success.form.bv', function(e) { //点击提交之后
		e.preventDefault();
		var $form = $(e.target);
		var bv = $form.data('bootstrapValidator');
		var md5Str1 = $.md5($("#registerPwd").val());
		$("#registerPwd").val(md5Str1);
		var md5Str2 = $.md5($("#confirmPwd").val());
		$("#confirmPwd").val(md5Str2);
		e.target.submit();
	});
});
