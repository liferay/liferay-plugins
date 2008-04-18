var DesktopAddContent = {
	categories : [],
	initialized : false,
	loadingImage : null,
	menu : null,
	menuDiv : null,
	menuIframe : null,
	portlets : [],
	showTimer : 0,
	offsetMenu: false,

	init : function () {
		var instance = this;

		var menu = jQuery('#portal_add_content');

		instance.menu = menu;

		instance._isFreeform = themeDisplay.isFreeformLayout();

		if (menu.length) {
			var list = menu.childNodes;

			instance.menuDiv = menu.find('.portal-add-content');
			instance.menuIframe = menu.find('iframe');

			instance.portlets = menu.find('.lfr-portlet-item');
			instance.categories = menu.find('.lfr-content-category');
			instance.categoryContainers = menu.find('.lfr-add-content');

			instance.initialized = true;

			jQuery('#layout_configuration_content').keyup(
				function(event) {
					instance.startShowTimer(event, this);
				}
			);
		}

		if (instance._isFreeform) {
			instance._grid = jQuery('body .freeform #column-1:first');
			instance._offsetL = instance._grid[0].offsetLeft;
			instance._offsetT = instance._grid[0].offsetTop;
		}
	},

	show : function (plid, ppid, doAsUserId) {
		var instance = this;

		if (!instance.menu) {
			var url = themeDisplay.getPathMain() + "/portal/render_portlet?p_l_id=" + plid + "&p_p_id=" + ppid + "&doAsUserId=" + doAsUserId + "&p_p_state=exclusive";

			var popupWidth = 250;

			if (instance.offsetMenu) {
				var body = jQuery('body');
				var originalPadding = body.css('padding-left');

				body.css('padding-left', popupWidth + 10);
			}

			AjaxUtil.update(url, 'sidebar-content',
				{
					onComplete: function() {
						instance._loadContent();
					}
				}
			);
		}
	},

	searchField: function(event, obj) {
		var instance = this;

		var word = jQuery.trim(obj.value).toLowerCase();
		var portlets = instance.portlets;
		var categories = instance.categories;
		var categoryContainers = instance.categoryContainers;

		if (word != '*' && word.length) {
			word = word.match(/[a-zA-Z0-9]*/g).join("");
			portlets.hide();
			categories.hide();
			categoryContainers.hide();
			portlets.each(
				function(i) {
					var name = this.id.toLowerCase();
					if (name.indexOf(word) > -1) {
						var portlet = jQuery(this);
						portlet.show();
						portlet.parents('.lfr-content-category').addClass('visible').removeClass('hidden').show();
						portlet.parents('.lfr-add-content').addClass('expanded').removeClass('collapsed').show();
					}
				}
			);
		}
		else {
			if (!word.length) {
				categories.addClass('hidden').removeClass('visible').css('display', '');
				categoryContainers.addClass('collapsed').removeClass('expanded').css('display', '');
				portlets.css('display', '');
			}
			else if (word == '*') {
				categories.addClass('visible').removeClass('hidden');
				categoryContainers.addClass('expanded').removeClass('collapsed');
				portlets.show();
			}
		}
	},

	startShowTimer : function (event, obj) {
		var instance = this;

		if (instance.showTimer) {
			clearTimeout(instance.showTimer);
			instance.showTimer = 0;
		}

		instance.showTimer = setTimeout(
			function() {
				instance.searchField(event, obj);
			},
			250
		);
	},

	_loadContent: function() {
		var instance = this;

		instance.init();

		Liferay.Util.addInputType();
		Liferay.Util.addInputFocus();

		Liferay.Publisher.subscribe('closePortlet', instance._onPortletClose, instance);

		var clicked = false;

		var portlets = jQuery('.lfr-portlet-item');

		var options = {
			threshold: 10,
			onStart: function(s) {
				var event = s.browserEvent;
				var originalTarget = jQuery(event.originalTarget || event.srcElement);

				if (!originalTarget.is('a')) {
					Liferay.Columns._onStart(s);
				}
				else {
					clicked = true;

					var portlet = originalTarget.parents('.lfr-portlet-item:first');

					if (!portlet.is('.lfr-portlet-used')) {
						var plid = portlet.attr('plid');
						var portletId = portlet.attr('portletId');
						var isInstanceable = (portlet.attr('instanceable') == 'true');

						addPortlet(plid, portletId, themeDisplay.getDoAsUserIdEncoded());

						if (!isInstanceable) {
							portlet.addClass('lfr-portlet-used');
							portlet.unbind('mousedown');
						}
					}
				}

			},
			onMove: function(s) {
				if (!instance._isFreeform) {
					Liferay.Columns._onMove(s);
				}
			},
			onComplete: function(s) {
				if (!clicked) {
					var container = s.container;

					var plid = container.getAttribute('plid');
					var portletId = container.getAttribute('portletId');

					if (plid && portletId) {
						var portlet = jQuery(s.container);
						var isInstanceable = (container.getAttribute('instanceable') == 'true');
						var doAsUserId = themeDisplay.getDoAsUserIdEncoded();
						var portletBound = addPortlet(plid, portletId, doAsUserId, true);

						if (!isInstanceable) {
							if (portletBound) {
								portlet.addClass('lfr-portlet-used');
								portlet.unbind('mousedown');
							}
						}
						else {
							Liferay.Columns.add(portlet, options);
						}

						portlet.css(
							{
								top: 0,
								left: 0
							}
						);

						s.container = portletBound;

						var completed = Liferay.Columns._onComplete(s);

						if (!instance._isFreeform) {
							if (!completed) {
								if (isInstanceable) {
									portletId = portletBound.id;
									portletId = portletId.replace(/^p_p_id_(.*)_$/, '$1');
								}

								closePortlet(plid, portletId, doAsUserId, true);
							}
						}

						if (completed || instance._isFreeform) {
							portlet.Highlight(750, '#ffe98f');
						}

						if (instance._isFreeform) {
							var jPortlet = jQuery(portletBound);

							jPortlet.css(
								{
									left: (mousePos.x - instance._offsetL) + 'px',
									top: (mousePos.y - instance._offsetT) + 'px'
								}
							);
						}

						var portletContainer = jQuery('.portlet-boundary_' + portletId + '_');
						var portletLink = jQuery('.portlet-boundary_' + portletId + '_ a');
						var realPortletId = portletLink.attr('name');
						realPortletId = realPortletId.replace('p_','');
						LiferayDesktop.addTaskbarLink(realPortletId);
						LiferayDesktop.selectTaskbarLink(realPortletId);
						LiferayDesktop.addPortletGroupName(realPortletId);
						portletContainer.click(function() {LiferayDesktop.selectTaskbarLink(realPortletId);});
					}
				}

				clicked = false;
			}
		};

		instance._layoutOptions = options;

		portlets.each(
			function() {
				if (this.className.indexOf('lfr-portlet-used') == -1) {
					Liferay.Columns.add(this, options);
				}
			}
		);

		if (Liferay.Browser.is_ie) {
			portlets.hover(
				function() {
					this.className += ' over';
				},
				function() {
					this.className = this.className.replace('over', '');
				}
			);
		}

		jQuery('.lfr-add-content > h2').click(
			function() {
				var heading = jQuery(this).parent();
				var category = heading.find('> .lfr-content-category');

				category.toggleClass('hidden').toggleClass('visible');
				heading.toggleClass('collapsed').toggleClass('expanded');
			}
		);
	},

	_onPortletClose: function(portletData) {
		var instance = this;

		var popup = jQuery('#portal_add_content');
		var item = popup.find('.lfr-portlet-item[@plid=' + portletData.plid + '][@portletId=' + portletData.portletId + '][@instanceable=false]');

		if (item.is('.lfr-portlet-used')) {
			item.removeClass('lfr-portlet-used')
			Liferay.Columns.add(item[0], instance._layoutOptions);
		}
	}
};