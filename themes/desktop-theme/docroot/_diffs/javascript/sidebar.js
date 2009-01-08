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

				instance._dialog = Liferay.Popup(
					{
						width: popupWidth,
						message: '<div class="loading-animation" />',
						position: [4,33],
						resizable: false,
						title: Liferay.Language.get("add-application"),
						onClose: function() {
							instance.menu = null;
							body.removeClass('lfr-has-sidebar');
						}
					}
				);
			}
			else {
				instance._dialog = jQuery("#sidebar-content");
			}

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
						message = message.replace("lfr-auto-focus", "");

						instance._dialog.html(message);
						instance._loadContent();
					}
				}
			);
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
				if (instance._sortColumns) {
					instance._sortColumns.filter(':first').prepend(placeHolder);
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

		instance._portletItems = instance._dialog.find('div.lfr-portlet-item');
		var portlets = instance._portletItems;

		portlets.find('a').click(
			function(event) {
				var link = jQuery(this);
				var portlet = link.parents('.lfr-portlet-item:first');

				instance._addPortlet(portlet);
			}
		);

		var zIndex = instance._dialog.parents('.ui-dialog').css('z-index');

		instance._helper = jQuery(Liferay.Template.PORTLET).css('z-index', zIndex + 10);
		instance._helper.addClass('ui-proxy generic-portlet not-intersecting');

		var type = 'Column';
		var appendTo = 'body';

		if (Liferay.Layout.isFreeForm) {
			appendTo = '#column-1';
			type = 'FreeForm';
		}
		else {

			// Let's make sure we have all the columns ready

			if (!instance._sortColumns || !instance._sortableInstance) {
				instance._sortColumns = Liferay.Layout.Columns.sortColumns;
				instance._sortableInstance = instance._sortColumns.data('sortable');
			}

			var sortColumns = instance._sortColumns;
			var sortableInstance = instance._sortableInstance;

			sortableInstance.refresh();

			if (!instance._eventsBound) {
				sortColumns.bind('sortreceive.sortable',
					function(event, ui) {
						if (ui.item.is('.lfr-portlet-item') && ui.sender.is('.lfr-portlet-item') && !sortableInstance.dragging) {
							var placeholder = ui.item;
							var portlet = ui.sender;

							var options = {
								item: placeholder
							};

							instance._addPortlet(portlet, options);

							placeholder.hide();
						}
					}
				);

				sortColumns.bind('sortactivate.sortable',
					function(event) {
						Liferay.Layout.Columns.startDragging();
						sortableInstance.refreshPositions(true);
					}
				);

				sortColumns.bind(
					'sortstart.sortable',
					function(event, ui) {
						if (ui.item.is('.lfr-portlet-item')) {
							ui.placeholder.css(
								{
									height: 200,
									width: 300
								}
							);
						}
					}
				);

				instance._eventsBound = true;
			}
		}

		instance._dragOptions = {
			appendTo: appendTo,
			connectToSortable: '.lfr-portlet-column',
			distance: 2,
			helper: function(event) {
				var helper = instance._helper.clone();
				var title = this.getAttribute('title');

				helper.find('.portlet-title').text(title);

				return helper[0];
			},
			start: function(event, ui) {
				if (instance['_on'+ type +'DragStart']) {
					instance['_on'+ type +'DragStart'](event, ui, this);
				}
			},
			drag: function(event, ui) {
				if (instance['_on'+ type +'Drag']) {
					instance['_on'+ type +'Drag'](event, ui, this);
				}
			},
			stop: function(event, ui) {
				if (instance['_on'+ type +'DragStop']) {
					instance['_on'+ type +'DragStop'](event, ui, this);
				}
			}
		};

		portlets.draggable(instance._dragOptions);

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

	_onColumnDragStop: function(event, ui, obj) {
		var instance = this;

		Liferay.Layout.Columns.stopDragging();
	},

	_onFreeFormDragStart: function(event, ui, obj) {
		var instance = this;

		ui.helper.removeClass('not-intersecting');
	},

	_onFreeFormDragStop: function(event, ui, obj) {
		var instance = this;

		var portlet = jQuery(obj);
		var helper = ui.helper;
		var position = ui.position;

		var dimensions = {
			height: ui.helper.height(),
			position: 'absolute',
			width: ui.helper.width()
		};

		var options = {
			beforePortletLoaded: function(placeHolder) {
				placeHolder = jQuery(placeHolder);
				placeHolder.css(position);
				placeHolder.css(dimensions);
			},
			item: helper,
			onComplete: function(portlet, portletId) {
				jQuery(portlet).css(position);
				Liferay.Layout.FreeForm._moveToTop(portlet);
				Liferay.Layout.FreeForm._savePosition(portlet);
			}
		};

		instance._addPortlet(portlet, options);
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