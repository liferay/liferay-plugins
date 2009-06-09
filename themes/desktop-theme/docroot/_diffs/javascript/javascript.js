var Desktop = function () {
	var $ = jQuery;

	return {
		initHtml: function() {
			var instance = this;

			if (instance._isFreeformLayout && !instance._isStateMaximized && !instance._isLoginPage) {
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

			if (instance._isFreeformLayout && !instance._isStateMaximized && !instance._isLoginPage) {
				instance._handleTaskbarInit();
				instance._handlePortletClicks();
			}
		},

		taskbarAddPortlet: function(portletId, hasMinIcon) {
			var instance = this;

			if ($('#tb_' + portletId).size() > 0) {
				return;
			}

			var portlet = $('#p_p_id_' + portletId + '_');
			var iconClose = portlet.find('span.icon-close');

			var href = '';

			if (portlet.hasClass('portlet-minimized')) {
				portlet.css({display: 'none'}).removeClass('portlet-minimized');
				href = 'javascript: Desktop.portletRestore(\'' + portletId + '\');';
			}
			else {
				href = 'javascript: Desktop.portletMinimize(\'' + portletId + '\');';
			}

			if (!hasMinIcon) {
				href = 'javascript: void(0);';
			}

			var title = $.trim(portlet.find('span.portlet-title').text());

			if (title != '') {
				var buffer = [];

				buffer.push('<li id="tb_' + portletId + '" class="taskbar-link">');
				buffer.push('	<a href="' + href +'">');
				buffer.push('		<span class="taskbar-link-title">');
				buffer.push( 			title);
				buffer.push('		</span>');
				buffer.push('	</a>');
				buffer.push('</li>');

				$('#taskbar-portlets').append(buffer.join(''));

				var tbLinks = $('#taskbar-portlets .taskbar-link');
				var count = tbLinks.size();

				instance._updateTaskbarLinks(count);
			}

			if (instance._isFreeformLayout) {
				instance.portletRestore(portletId);
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

		portletMinimize: function(portletId) {
			var instance = this;

			var htmlStr = $('#tb_' + portletId + ' a').attr('href');

			htmlStr = htmlStr.replace('portletMinimize', 'portletRestore');

			$('#tb_' + portletId + ' a').attr('href', htmlStr);

			$('#p_p_id_' + portletId + '_').css({display: 'none'});

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

		portletRemove: function(portletId, columnPos) {
			var instance = this;

			$('#tb_' + portletId).remove();

			var count = $('#taskbar-portlets .taskbar-link').size() - 1;

			if (count < 1) {
				count = 1;
			}

			instance._updateTaskbarLinks(count);

			var portlet = $('#p_p_id_' + portletId + '_');

			portlet.portletProcessed = true;
			portlet.portletId = portletId;
			portlet.columnPos = columnPos;

			Liferay.Portlet.close(portlet, true);
		},

		portletRestore: function(portletId) {
			var instance = this;

			var htmlStr = $('#tb_' + portletId + ' a').attr("href");

			htmlStr = htmlStr.replace('portletRestore', 'portletMinimize');

			$('#tb_' + portletId + ' a').attr("href", htmlStr);

			var portlet = $('#p_p_id_' + portletId + '_');
			var columnId = portlet.parent().attr('id');

			if (instance._isFreeformLayout) {
				portlet.appendTo('#' + columnId);
				portlet.css({display: ''});
			}
			else {
				portlet.css({display: ''});
			}

			portlet.find('span.portlet-icons a').removeClass('selected');

			var currId = 'tb_' + portletId;
			var taskbarId = $('.taskbar-link.selected').attr('id');

			if ((currId != taskbarId) && instance._isFreeformLayout) {
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

			$('html').css({overflow: 'auto'});
			$('#wrapper').css({'min-width': instance._minWidth + 'px'});
		},

		_handleAddSidebar: function() {
			var instance = this;

			var sbContainer = $('#sidebar-container');
			var sbLink = $('#sidebar-link');

			if ((sbContainer.size() > 0) && (sbLink.size() == 0)) {
				var buffer = [];

				buffer.push('<div id="sidebar-link">');
				buffer.push('	<a href="javascript: Sidebar.animate();"></a>');
				buffer.push('</div>');

				sbContainer.prepend(buffer.join('')).css({display: ''});

				Sidebar.toggle('87', false);
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
					portletId = portletId.substring(0, portletId.length - 1);
					portletId = portletId.replace('p_p_id_', '');

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
				portletId = portletId.substring(0, portletId.length - 1);
				portletId = portletId.replace('p_p_id_', '');

				instance.taskbarSelectedPortlet(portletId);
			}
		},

		_updateTaskbarLinks: function(count) {
			var instance = this;

			var tbLinks = $('#taskbar-portlets .taskbar-link');

			if ($('body').hasClass('osx')) {
				return;
			}

			// IE6 does not support css min-width

			if ($('html').is('.ie6')) {
				var tbWidth = $('#taskbar-portlets-wrapper').width() - $('.lfr-dock.interactive-mode').width();
				var width = tbWidth / count;

				if (width > 200) {
					width = 200;
				}
				else if (width < 40) {
					width = 40;
				}

				tbLinks.css({width: width + 'px'});
			}
			else {
				tbLinks.css({width: 100 / count + '%'});
			}
		},

		_isLoginPage: ($('#desktop-login').length == 1) ? true : false,
		_isFreeformLayout: themeDisplay.isFreeformLayout(),
		_isStateMaximized: themeDisplay.isStateMaximized(),
		_minWidth: 964

	};
}();

if (themeDisplay.isSignedIn()) {
	var Sidebar = Expanse.extend({}, LayoutConfiguration);

	Sidebar.toggle = function(ppid, isPopup) {
		var instance = this;

		var plid = themeDisplay.getPlid();
		var doAsUserId = themeDisplay.getDoAsUserIdEncoded();

		if (!instance.menu) {
			var url = themeDisplay.getPathMain() + '/portal/render_portlet';

			if (isPopup) {
				var popupWidth = 250;
				var body = jQuery('body');

				body.addClass('lfr-has-sidebar');

				instance._dialog = new Expanse.Popup(
					{
						header: Liferay.Language.get('add-application'),
						onClose: function() {
							instance.menu = null;
							body.removeClass('lfr-has-sidebar');
						},
						resizable: false,
						url: url,
						urlData: {
							p_l_id: plid,
							p_p_id: ppid,
							p_p_state: 'exclusive',
							doAsUserId: doAsUserId
						},
						urlSuccess: function(message) {
							message = message.replace('lfr-auto-focus', '');

							instance._dialog.setBody(message);
							instance._loadContent();
						},
						xy: [4,33],
						width: popupWidth
					}
				);
			}
			else {
				instance._dialog = jQuery('#sidebar-content');

				jQuery.ajax(
					{
						url: url,
						data: {
							p_l_id: plid,
							p_p_id: ppid,
							p_p_state: 'exclusive',
							doAsUserId: doAsUserId
						},
						success: function(message) {
							message = message.replace('lfr-auto-focus', '');

							instance._dialog.html(message);
							instance._loadContent();
						}
					}
				);
			}

			instance._dialogBody = jQuery(instance._dialog.body);
		}
	}

	Sidebar.animate = function() {
		var instance = this;

		var sb = jQuery('#sidebar');
		var sbCalendar = jQuery('#sidebar-top .calendar');
		var sbClock = jQuery('#sidebar-top .clock');
		var sbLink = jQuery('#sidebar-link a');

		if (sbLink.is('.expanded')) {
			sbClock.css({display: ''});
			sbLink.removeClass('expanded');

			sbCalendar.animate({opacity: '0'}, 600, 'linear');
			sb.parent().animate({width: '20px'}, 600, 'linear');
			sb.animate({opacity: '0'}, 600, 'linear',
				function() {
					sb.css({display: 'none'});
				}
			);
		}
		else {
			sb.css({display: 'block'});
			sbLink.addClass('expanded');

			sbCalendar.animate({opacity: '1'}, 1000, 'linear');
			sb.animate({opacity: '1'}, 600, 'linear');
			sb.parent().animate({width: '270px'}, 600, 'linear',
				function() {
					sbClock.css({display: 'block'});
				}
			);
		}
	}
}

jQuery(document).ready(
	function() {
		Desktop.initHtml();
	}
);

jQuery(document).last(
	function() {
		Desktop.initPage();
	}
);