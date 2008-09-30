var Desktop = function () {
	var $ = jQuery;
	var isFreeformLayout = themeDisplay.isFreeformLayout();

	return {
		initHtml: function() {
			var instance = this;

			if (isFreeformLayout) {
				instance._handleBodyClicks();
			}
			else {
				instance._handleAddScrollbar();
			}
		},

		initPage: function() {
			var instance = this;

			instance._handleAddSidebar();
			instance._handlePortletIcons();

			if (isFreeformLayout) {
				instance._handleTaskbarInit();
				instance._handlePortletClicks();
			}
		},

		taskbarAddPortlet: function(portletId) {
			var instance = this;

			if ($('#tb_' + portletId).size() == 0) {
				var portlet = $('#p_p_id_' + portletId + '_');
				var iconClose = portlet.find('span.icon-close');

				var href = '';

				if (portlet.hasClass('portlet-minimized')) {
					portlet.css({display:'none'}).removeClass('portlet-minimized');
					href = "javascript: Desktop.portletRestore('" + portletId + "');";
				}
				else {
					href = "javascript: Desktop.portletMinimize('" + portletId + "');";
				}

				if (iconClose.size() == 0) {
					href= "#";
				}

				var title = $.trim(portlet.find('span.portlet-title').text());

				if (title != '') {
					var htmlStr = '';

					htmlStr += '<li id="tb_' + portletId + '" class="taskbar-link">';
					htmlStr += '	<a href="' + href +'">';
					htmlStr += '		<span class="taskbar-link-title">';
					htmlStr += 			title;
					htmlStr += '		</span>';
					htmlStr += '	</a>';
					htmlStr += '</li>';

					$('#taskbar-portlets').append(htmlStr);

					var tbLinks = $('#taskbar-portlets .taskbar-link');
					var count = tbLinks.size();

					tbLinks.css({width:100/count + '%'});
				}

				if (isFreeformLayout) {
					instance.portletRestore(portletId);
				}
			}
		},

		taskbarSelectedPortlet: function(portletId) {
			var instance = this;

			var tbLink =  $('#tb_' + portletId);

			if (tbLink.size() > 0) {
				$('.taskbar-link.selected').removeClass('selected');
				tbLink.addClass('selected');
			}
		},

		portletMaximize: function(portletId) {
			var instance = this;

			var windowHeight = 0;

			$(window).load(
				function() {
					if ($('html').is('.ie6')) {
						windowHeight = document.documentElement.offsetHeight-4;
					}
					else if ($('html').is('.ie')) {
						windowHeight = document.body.offsetHeight;
					}
					else {
						windowHeight = window.innerHeight;
					}

					$('#portlet-wrapper-' + portletId + ' .middle-center').css({height:windowHeight-68 + "px"});
				}
			);

			// Improve performance in IE

			if ($('html').is('.ie')) {
				var resizeTimer = null;

				$(window).resize(
					function() {
						if (resizeTimer) clearTimeout(resizeTimer);

						resizeTimer = setTimeout(
							function() {
								if ($('html').is('.ie6')) {
									windowHeight = document.documentElement.offsetHeight-4;
								}
								else {
									windowHeight = document.body.offsetHeight;
								}

								$('#portlet-wrapper-' + portletId + ' .middle-center').css({height:windowHeight-68 + "px"});
							},
							100
						);
					}
				);
			}
			else {
				$(window).resize(
					function() {
						$('#portlet-wrapper-' + portletId + ' .middle-center').css({height:window.innerHeight-68 + 'px'});
					}
				);
			}
		},

		portletMinimize: function(portletId) {
			var instance = this;

			var htmlStr = $('#tb_' + portletId + ' a').attr('href');

			htmlStr = htmlStr.replace('portletMinimize','portletRestore');

			$('#tb_' + portletId + ' a').attr('href',htmlStr);

			$('#p_p_id_' + portletId + '_').css({display:'none'});

			$.ajax(
				{
					url: themeDisplay.getPathMain() + '/portal/update_layout',
					data: {
						p_l_id: themeDisplay.getPlid(),
						p_p_id: portletId,
						p_p_restore: false,
						doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
						cmd: 'minimize'
					}
				}
			);
		},

		portletRemove: function(portletId,columnPos) {
			var instance = this;

			$('#tb_' + portletId).remove();

			var count = $('#taskbar-portlets .taskbar-link').size()-1;

			if (count < 1) {
				count = 1;
			}

			$('#taskbar-portlets .taskbar-link').css({width:100/count + '%'});

			var portlet = $('#p_p_id_' + portletId + '_');

			portlet.portletProcessed = true;
			portlet.portletId = portletId;
			portlet.columnPos = columnPos;

			Liferay.Portlet.close(portlet, true);
		},

		portletRestore: function(portletId) {
			var instance = this;

			var htmlStr = $('#tb_' + portletId + ' a').attr("href");

			htmlStr = htmlStr.replace('portletRestore','portletMinimize');

			$('#tb_' + portletId + ' a').attr("href",htmlStr);

			var portlet = $('#p_p_id_' + portletId + '_');
			var columnId = portlet.parent().attr('id');

			if (isFreeformLayout) {
				portlet.appendTo('#' + columnId);
				portlet.css({display:''});
			}
			else {
				portlet.css({display:''});
			}

			portlet.find('span.portlet-icons a').removeClass('selected');

			var currId = 'tb_' + portletId;
			var taskbarId = $('.taskbar-link.selected').attr('id');

			if ((currId != taskbarId) && isFreeformLayout) {
				instance.taskbarSelectedPortlet(portletId);
			}

			$.ajax(
				{
					url: themeDisplay.getPathMain() + '/portal/update_layout',
					data: {
						p_l_id: themeDisplay.getPlid(),
						p_p_id: portletId,
						p_p_restore: true,
						doAsUserId: themeDisplay.getDoAsUserIdEncoded(),
						cmd: 'minimize'
					}
				}
			);
		},

		_handleAddScrollbar: function() {
			var instance = this;

			$('html').css({overflow:'auto'});
			$('#wrapper').css({'min-width':'964px'});
		},

		_handleAddSidebar: function() {
			var instance = this;

			var sbContainer = $('#sidebar-container');
			var sbLink = $('#sidebar-link');

			if ((sbContainer.size() > 0) && (sbLink.size() == 0)) {
				var htmlStr = '';

				htmlStr += '<div id="sidebar-link">';
				htmlStr += '	<a href="javascript: Sidebar.animate();"></a>';
				htmlStr += '</div>'

				sbContainer.prepend(htmlStr).css({display:''});

				Sidebar.toggle('87',false);
			}
		},

		_handleBodyClicks: function() {
			var instance = this;

			$('body').click(
				function(event) {
					var $target = $(event.target);

					if ($target.is('#wrapper')) {
						$('.taskbar-link.selected').removeClass('selected');
					}
				}
			);
		},

		_handlePortletClicks: function() {
			var instance = this;

			var portlets = $('div.portlet-boundary');

			portlets.click(
				function(event) {
					var index = portlets.index(this);
					var portletId = '';

					portletId = portlets.eq(index).attr('id');
					portletId = portletId.substring(0,portletId.length-1);
					portletId = portletId.replace('p_p_id_','');

					instance.taskbarSelectedPortlet(portletId);
				}
			);
		},

		_handlePortletIcons: function() {
			var instance = this;

			$('div.portlet-boundary').find('span.portlet-icons a').hoverIntent(
				{
					interval: 0,
					timeout: 0,
					over: function() {
						$(this).addClass('selected');
					},
					out: function() {
						$(this).removeClass('selected');
					}
				}
			);
		},

		_handleTaskbarInit: function() {
			var instance = this;

			var portlet = $('#content-wrapper').find("div.portlet-boundary:last");
			var portletId = '';

			if (portlet.size() > 0) {
				portletId = portlet.attr('id');
				portletId = portletId.substring(0,portletId.length-1);
				portletId = portletId.replace('p_p_id_','');

				instance.taskbarSelectedPortlet(portletId);
			}
		}

	};
}();