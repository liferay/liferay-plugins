var Desktop = function () {
	var $ = jQuery;

	return {
		initHtml: function() {
			var instance = this;

			if (!$('#content-wrapper').hasClass('freeform')) {
				jQuery("html").css({overflow:"auto"});
			}
		},

		initPage: function() {
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

			if ($('#content-wrapper').is('.freeform')) {
				$('body').click(
					function(event) {
						instance.tbDeselect(event);
					}
				);
			}
		},

		tbAddLink: function(portletId) {
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

				var tbLinkStr = '';
				tbLinkStr += '<li id="tb_' + portletId + '" class="taskbar-link">';
				tbLinkStr += '	<a href="' + href +'">';
				tbLinkStr += '		<span class="taskbar-link-title">';
				tbLinkStr += 			$.trim(portlet.find('span.portlet-title').text());
				tbLinkStr += '		</span>';
				tbLinkStr += '	</a>';
				tbLinkStr += '</li>';

				$('#taskbar-portlets').append(tbLinkStr);

				var tbLinks = $('#taskbar-portlets .taskbar-link');
				var totalWidth = $('#taskbar-portlets-wrapper').width()-52-10;
				var count = tbLinks.size();

				if (count*200 < totalWidth) {
					tbLinks.css({width:'200px'});
				}
				else {
					tbLinks.css({width:100/count + '%'});
				}

				if ($('#content-wrapper').is('.freeform')) {
					portlet.click(
						function() {
							instance.portletRestore(portletId);
						}
					);
				}
			}
		},

		tbDeselect: function(event) {
			var instance = this;

			var $target = $(event.target);

			if ($target.is('#wrapper')) {
				$('.taskbar-link.selected').removeClass('selected');
			}
		},

		tbSelectLink: function(portletId) {
			var instance = this;

			var tbCurrLink = $('.taskbar-link.selected');
			var tbLink =  $('#tb_' + portletId);

			tbCurrLink.removeClass('selected');
			tbLink.addClass('selected');
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

			// Improve browser performance for IE

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

			var tbLinkStr = $('#tb_' + portletId + ' a').attr('href');

			tbLinkStr = tbLinkStr.replace('portletMinimize','portletRestore');
			$('#tb_' + portletId + ' a').attr('href',tbLinkStr);

			$('#p_p_id_' + portletId + '_').css({display:'none'});

			jQuery.ajax(
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

			var taskbarLinks = $('#taskbar-portlets .taskbar-link');
			var totalWidth = $('#taskbar-portlets-wrapper').width()-52-10;

			if (count*200 < totalWidth) {
				taskbarLinks.css({width:'200px'});
			}
			else {
				taskbarLinks.css({width:100/count + '%'});
			}

			jQuery(
				function () {
					var portlet = jQuery('#p_p_id_' + portletId + '_');

					portlet.portletProcessed = true;
					portlet.portletId = portletId;
					portlet.columnPos = columnPos;

					Liferay.Portlet.close(portlet, true);
				}
			);
		},

		portletRestore: function(portletId) {
			var instance = this;

			var tbLinkStr = $('#tb_' + portletId + ' a').attr("href");

			tbLinkStr = tbLinkStr.replace('portletRestore','portletMinimize');
			$('#tb_' + portletId + ' a').attr("href",tbLinkStr);

			var portlet = $('#p_p_id_' + portletId + '_');
			var columnId = portlet.parent().attr('id');

			if ($('#content-wrapper').is('.freeform')) {
				portlet.appendTo('#' + columnId);
				portlet.css({display:''});
			}
			else {
				portlet.css({display:''});
			}

			var currId = 'tb_' + portletId;
			var taskbarId = $('.taskbar-link.selected').attr('id');

			if ((currId != taskbarId) && $('#content-wrapper').is('.freeform')) {
				instance.tbSelectLink(portletId);
			}

			jQuery.ajax(
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
		}

	};
}();