$(function() {

	$('.manualBtn').bind('click', function() {
		var btn = $(this);
		var showPanel = $('#showPanel');


		//  '<iframe name="my-iframe" id="my-iframe" src="初始显示的页面或者图片" frameborder="边框（一般为0）" width="宽" height="高" scrolling="是否滚动（一般为“no”）"></iframe>"';
		var iframe_str = '<iframe name="my-iframe" id="my-iframe" src="' + btn.attr('href') +
			'" frameborder="0" scrolling="no" style="width:100%;height:100%;"></iframe>"';
		//第二个参数是标题或者索引
		if (showPanel.tabs('exists', btn.text())) {
			showPanel.tabs('select', btn.text())
		} else {
			showPanel.tabs('add', {
				title: btn.text(),
				content: iframe_str,
				closable: true
			});
		}
		return false;
	});
})
