(function() {
	var Dom = Expanse.Dom;
	var Event = Expanse.Event;
	var DDM = Expanse.DragDrop;

	var Sidebar = {
		categories: [],
		portlets: [],
		showTimer: 0,

		init: function() {
			var instance = this;

			var menu = jQuery('#portal_add_content');

			instance.menu = menu;

			if (menu.length) {
				instance.portlets = menu.find('.lfr-portlet-item');
				instance.categories = menu.find('.lfr-content-category');
				instance.categoryContainers = menu.find('.lfr-add-content');

				var data = function() {
					var value = jQuery(this).attr('id');

					return Liferay.Util.uncamelize(value).toLowerCase();
				};

				var searchField = jQuery('#layout_configuration_content');

				searchField.liveSearch(
					{
						list: instance.portlets,
						data: data,
						show: function() {
							var portlet = jQuery(this);

							portlet.show();
							portlet.parents('.lfr-content-category').addClass('visible').removeClass('hidden').show();
							portlet.parents('.lfr-add-content').addClass('expanded').removeClass('collapsed').show();
						},
						hide: function() {
							var portlet = jQuery(this);

							portlet.hide();
						}
					}
				);

				searchField.liveSearch(
					{
						list: instance.categoryContainers,
						data: data,
						after: function() {
							if (!this.term) {
								instance.categories.addClass('hidden').removeClass('visible').css('display', '');
								instance.categoryContainers.addClass('collapsed').removeClass('expanded').css('display', '');
								instance.portlets.css('display', '');
							}

							if (this.term == "*") {
								instance.categories.addClass('visible').removeClass('hidden');
								instance.categoryContainers.addClass('expanded').removeClass('collapsed');
								instance.portlets.show();
							}
						},
						exclude: function() {
							var categoryContent = jQuery('.lfr-content-category', this);

							var totalVisibleChildren = categoryContent.find('> div:visible').length;

							return totalVisibleChildren > 0;
						}
					}
				);
			}
		},

		animate: function() {
			var instance = this;

			var sb = jQuery('#sidebar');
			var sbCalendar = jQuery('#sidebar-top .calendar');
			var sbClock = jQuery('#sidebar-top .clock');
			var sbLink = jQuery('#sidebar-link a');

			if (sbLink.is('.expanded')) {
				sbClock.css({display:''});
				sbLink.removeClass('expanded');

				sbCalendar.animate({opacity:'0'},600,'linear');
				sb.parent().animate({width:'20px'},600,'linear');
				sb.animate({opacity:'0'},600,'linear',
					function() {
						sb.css({display:'none'});
					}
				);
			}
			else {
				sb.css({display:'block'});
				sbLink.addClass('expanded');

				sbCalendar.animate({opacity:'1'},1000,'linear');
				sb.animate({opacity:'1'},600,'linear');
				sb.parent().animate({width:'270px'},600,'linear',
					function() {
						sbClock.css({display:'block'});
					}
				);
			}
		},

		toggle: function(ppid, isPopup) {
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
		},

		_addPortlet: function(portlet, options) {
			var instance = this;

			var portletMetaData = instance._getPortletMetaData(portlet);

			if (!portletMetaData.portletUsed) {
				var plid = portletMetaData.plid;
				var portletId = portletMetaData.portletId;
				var isInstanceable = portletMetaData.instanceable;

				if (!isInstanceable) {
					portlet.addClass('lfr-portlet-used');
					portlet.draggable('disable');
				}

				var placeHolder = jQuery('<div class="loading-animation" />');
				var onComplete = null;
				var beforePortletLoaded = null;

				if (options) {
					var item = options.item;

					options.placeHolder = placeHolder[0];
					onComplete = options.onComplete;
					beforePortletLoaded = options.beforePortletLoaded;

					item.after(placeHolder);
					item.remove();
				}
				else {
					if (instance._dropZones && instance._dropZones.length) {
						jQuery(instance._dropZones[0]).prepend(placeHolder);
					}
				}

				var portletOptions = {
					beforePortletLoaded: beforePortletLoaded,
					onComplete: onComplete,
					plid: plid,
					portletId: portletId,
					placeHolder: placeHolder
				}

				var portletPosition = Liferay.Portlet.add(portletOptions);

				instance._loadPortletFiles(portletMetaData);
			}
		},

		_getPortletMetaData: function(portlet) {
			var instance = this;

			var portletMetaData = portlet._LFR_portletMetaData;

			if (!portletMetaData) {
				var instanceable = (portlet.attr('instanceable') == 'true');
				var plid = portlet.attr('plid');
				var portletId = portlet.attr('portletId');
				var portletUsed = portlet.is('.lfr-portlet-used');
				var headerPortalCssPaths = (portlet.attr('headerPortalCssPaths') || '').split(',');
	            var headerPortletCssPaths = (portlet.attr('headerPortletCssPaths') || '').split(',');
				var footerPortalCssPaths = (portlet.attr('footerPortalCssPaths') || '').split(',');
				var footerPortletCssPaths = (portlet.attr('footerPortletCssPaths') || '').split(',');

				portletMetaData = {
					instanceable: instanceable,
					plid: plid,
					portletId: portletId,
					portletPaths: {
						footer: footerPortletCssPaths,
						header: headerPortletCssPaths
					},
					portalPaths: {
						footer: footerPortalCssPaths,
						header: headerPortalCssPaths
					},
					portletUsed: portletUsed
				}

				portlet._LFR_portletMetaData = portletMetaData;
			}

			return portletMetaData;
		},

		_loadContent: function() {
			var instance = this;

			instance.init();

			Liferay.Util.addInputType();

			Liferay.bind('closePortlet', instance._onPortletClose, instance);

			instance._portletItems = instance._dialogBody.find('div.lfr-portlet-item');
			var portlets = instance._portletItems;

			portlets.find('a').click(
				function(event) {
					var link = jQuery(this);
					var portlet = link.parents('.lfr-portlet-item:first');

					instance._addPortlet(portlet);
				}
			);

			var zIndex = instance._dialogBody.parents('.ui-dialog').css('z-index');

			instance._helper = jQuery(Liferay.Template.PORTLET).css('z-index', zIndex + 10);
			instance._helper.addClass('ui-proxy generic-portlet not-intersecting');

			var portletItem = columnPortletItem;

			if (Liferay.Layout.isFreeForm) {
				portletItem = freeFormPortletItem;
			}
			else {
				instance._dropZones = Liferay.Layout.Columns.dropZones;
			}

			for (var i = instance._portletItems.length - 1; i >= 0; i--){
				new portletItem(instance._portletItems[i], 'portlets');
			}

			portlets.filter('.lfr-portlet-used').draggable('disable');

			if (Liferay.Browser.isIe()) {
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

		_loadPortletFiles: function(portletMetaData) {
			var instance = this;

			var headerPortalCssPaths = portletMetaData.portalPaths.header;
			var footerPortalCssPaths = portletMetaData.portalPaths.footer;
			var headerPortletCssPaths = portletMetaData.portletPaths.header;
			var footerPortletCssPaths = portletMetaData.portletPaths.footer;

			var head = jQuery('head');
			var docBody = jQuery(document.body);

			var headerCSS = headerPortalCssPaths.concat(headerPortletCssPaths);
			var footerCSS = footerPortalCssPaths.concat(footerPortletCssPaths);

			jQuery.each(
				headerCSS,
				function(i, n) {
					head.prepend('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);

			if (Liferay.Browser.isIe()) {
				jQuery('body link').appendTo('head');

				jQuery('link.lfr-css-file').each(
					function(i) {
						document.createStyleSheet(this.href);
					}
				);
			}

			jQuery.each(
				footerCSS,
				function(i, n) {
					docBody.append('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);
		},

		_onPortletClose: function(event, portletData) {
			var instance = this;

			var popup = jQuery('#portal_add_content');
			var item = popup.find('.lfr-portlet-item[plid=' + portletData.plid + '][portletId=' + portletData.portletId + '][instanceable=false]');

			if (item.is('.lfr-portlet-used')) {
				item.removeClass('lfr-portlet-used');
				item.draggable('enable');
			}
		}
	};

	var columnPortletItem = Liferay.Layout.columnPortlet.extend({
		endDrag: function(event) {
			var instance = this;

			var portlet = instance._getPlaceholder();
			var proxy = instance.getDragEl();

			var placeholder = jQuery(instance._getPlaceholder());
			var portlet = jQuery(instance.getEl());

			var options = {
				item: placeholder
			};

			Sidebar._addPortlet(portlet, options);

			placeholder.hide();

			Liferay.Layout.Columns.stopDragging();
		},

		startDrag: function() {
			var instance = this;

			instance.parent.apply(instance, arguments);

			Dom.setStyle(instance.getEl(), 'visibility', '');
		},

		_getPlaceholder: function() {
			var instance = this;

			if (!instance._placeHolder) {
				instance._placeHolder = jQuery(instance.getEl()).clone();

				instance._placeHolder.css(
					{
						height: 200,
						visibility: 'hidden',
						width: 300
					}
				);

				instance._placeHolder.appendTo('body');

				instance._placeHolder = instance._placeHolder[0];
			}

			return instance._placeHolder;
		},

		_updateProxy: function() {
			var instance = this;

			instance.parent.apply(instance, arguments);

			var original = instance.getEl();
			var proxy = instance._getProxy();
			var obj = jQuery(original);

			var titleHtml = obj.attr('title');

			proxy.find('.portlet-title').html(titleHtml);

			proxy.css(
				{
					height: 200,
					width: 300
				}
			);
		}
	});

	var freeFormPortletItem = Liferay.Layout.freeFormPortlet.extend({
		endDrag: function(event) {
			var instance = this;

			var portlet = instance.getEl();
			var proxy = instance.getDragEl();

			var proxyStyle = proxy.style;

			portlet = jQuery(portlet);
			proxy = jQuery('.ui-proxy', proxy);

			var proxyHeight = proxy.height();
			var proxyWidth = proxy.width();

			var position = {
				left: proxyStyle.left,
				top: proxyStyle.top
			};

			var dimensions = {
				height: proxyHeight,
				position: 'absolute',
				width: proxyWidth
			};

			var options = {
				beforePortletLoaded: function(placeHolder) {
					placeHolder = jQuery(placeHolder);

					placeHolder.css(position);
					placeHolder.css(dimensions);
				},
				item: proxy.parent(),
				onComplete: function(portlet, portletId) {
					jQuery(portlet).css(position);
					Liferay.Layout.FreeForm._moveToTop(portlet);
					Liferay.Layout.FreeForm._savePosition(portlet);
				}
			};

			Sidebar._addPortlet(portlet, options);
		},

		b4StartDrag: function() {
			var instance = this;

			jQuery('#column-1').append(instance.getDragEl());

			instance.parent.apply(instance, arguments);
		},

		startDrag: function() {
			var instance = this;

			instance._updateProxy();
		},

		_getPlaceholder: function() {
			var instance = this;

			if (!instance._placeHolder) {
				instance._placeHolder = jQuery(instance.getEl()).clone();

				instance._placeHolder.css(
					{
						height: 200,
						visibility: 'hidden',
						width: 300
					}
				);

				instance._placeHolder.appendTo('#column-1');

				instance._placeHolder = instance._placeHolder[0];
			}

			return instance._placeHolder;
		},

		_updateProxy: function() {
			var instance = this;

			instance.parent.apply(instance, arguments);

			var original = instance.getEl();
			var proxy = instance._getProxy();
			var obj = jQuery(original);

			var titleHtml = obj.attr('title');

			proxy.find('.portlet-title').html(titleHtml);

			proxy.css(
				{
					height: 200,
					width: 300
				}
			);
		}
	});

	Liferay.Sidebar = Sidebar;

	window.Sidebar = Sidebar;
})();