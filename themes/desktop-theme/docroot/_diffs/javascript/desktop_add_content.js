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

			jQuery('#layout_configuration_content').trigger('focus').addClass('focus');
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

		var plid = themeDisplay.getPlid();
		var doAsUserId = themeDisplay.getDoAsUserIdEncoded();

		if (!instance.menu) {
			var url =
				themeDisplay.getPathMain() +
				'/portal/render_portlet' +
				'?p_l_id=' + plid +
				'&p_p_id=' + ppid +
				'&p_p_state=exclusive' +
				'&doAsUserId=' + doAsUserId;

			var popupWidth = 250;

			if (instance.offsetMenu) {
				var body = jQuery('body');

				body.addClass('lfr-has-sidebar');
			}

			jQuery.ajax(
				{
					url: url,
					success: function(message) {
						jQuery("#sidebar-content").html(message);
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

			var placeHolder = jQuery('<div class="loading-animation"></div>');
			var onComplete = LiferayDesktop.addPortlet(portletId);
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

			var portletPosition = addPortlet(portletOptions);

			instance._loadPortletFiles(portletMetaData);
		}
	},

	_configureGrid: function() {
		var instance = this;

		if (!Liferay.Layout.isFreeForm) {
			instance._sortColumns = Liferay.Layout.Columns.sortColumns;
			instance._sortableInstance = instance._sortColumns.data('sortable');
			instance._onDrag = instance._onColumnDrag;
			instance._onDragStart = instance._onColumnDragStart;
			instance._onDragStop = instance._onColumnDragStop;
		}
		else {
			instance._onDrag = instance._onFreeFormDrag;
			instance._onDragStart = instance._onFreeFormDragStart;
			instance._onDragStop = instance._onFreeFormDragStop;
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
			var headerPortalCssPaths = portlet.attr('headerPortalCssPaths');
            var headerPortletCssPaths = portlet.attr('headerPortletCssPaths');
			var footerPortalCssPaths = portlet.attr('footerPortalCssPaths');
			var footerPortletCssPaths = portlet.attr('footerPortletCssPaths');

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

		Liferay.Publisher.subscribe('closePortlet', instance._onPortletClose, instance);

		instance._portletItems = jQuery('div.lfr-portlet-item', jQuery("#sidebar-content"));
		var portlets = instance._portletItems;

		portlets.find('a').click(
			function(event) {
				var link = jQuery(this);
				var portlet = link.parents('.lfr-portlet-item:first');

				instance._addPortlet(portlet);
			}
		);

		var zIndex = jQuery("#sidebar-content").parents('.ui-dialog').css('z-index');

		instance._helper = jQuery('<div class="ui-drag-helper not-intersecting"><div class="forbidden-action"></div></div>').css('z-index', zIndex + 10);

		instance._configureGrid();

		instance._dragOptions = {
			appendTo: 'body',
			connectToSortable: '.lfr-portlet-column',
			distance: 2,
			helper: function() {
				return instance._helper.clone()[0];
			},
			start: function(event, ui) {
				instance._onDragStart(event, ui, this);
			},
			drag: function(event, ui) {
				instance._onDrag(event, ui, this);
			},
			stop: function(event, ui) {
				instance._onDragStop(event, ui, this);
			}
		};

		if (Liferay.Layout.isFreeForm) {
			instance._dragOptions.appendTo = '#column-1';
		}
		else {

			// Let's make sure we have all the columns ready

			var sortColumns = instance._sortColumns;
			var sortableInstance = instance._sortableInstance;

			sortableInstance.refresh();

			sortColumns.bind('sortreceive.sortable',
				function(event, ui) {
					if (ui.item.is('.lfr-portlet-item') && !sortableInstance.dragging) {
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
						ui.item.css(
							{
								height: 200,
								width: 300
							}
						);
					}
				}
			);
		}

		portlets.draggable(instance._dragOptions);

		portlets.filter('.lfr-portlet-used').draggable('disable');

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

	_loadPortletFiles: function(portletMetaData) {
		var instance = this;

		var headerPortalCssPaths = portletMetaData.portalPaths.header;
		var footerPortalCssPaths = portletMetaData.portalPaths.footer;
		var headerPortletCssPaths = portletMetaData.portletPaths.header;
		var footerPortletCssPaths = portletMetaData.portletPaths.footer;

		var head = jQuery('head');
		var docBody = jQuery(document.body);

		if (headerPortalCssPaths) {
			headerPortalCssPaths = headerPortalCssPaths.split(',');

			jQuery.each(
				headerPortalCssPaths,
				function(i, n) {
					head.prepend('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);
		}

		if (headerPortletCssPaths) {
			headerPortletCssPaths = headerPortletCssPaths.split(',');

			jQuery.each(
				headerPortletCssPaths,
				function(i, n) {
					head.prepend('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);
		}

		if (Liferay.Browser.is_ie) {
			jQuery('link.lfr-css-file').each(
				function(i) {
					document.createStyleSheet(this.href);
				}
			);
		}

		if (footerPortalCssPaths) {
			footerPortalCssPaths = footerPortalCssPaths.split(',');

			jQuery.each(
				footerPortalCssPaths,
				function(i, n) {
					docBody.append('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);
		}

		if (footerPortletCssPaths) {
			footerPortletCssPaths = footerPortletCssPaths.split(',');

			jQuery.each(
				footerPortletCssPaths,
				function(i, n) {
					docBody.append('<link href="' + this + '" rel="stylesheet" type="text/css" />');
				}
			);
		}
	},

	_onColumnDrag: function(event, ui, obj) {
	},

	_onColumnDragStart: function(event, ui, obj) {
	},

	_onColumnDragStop: function(event, ui, obj) {
		var instance = this;

		Liferay.Layout.Columns.stopDragging();
	},

	_onDrag: function(event, ui, obj) {
	},

	_onDragStart: function(event, ui, obj) {
	},

	_onDragStop: function(event, ui, obj) {
	},

	_onFreeFormDrag: function(event, ui, obj) {
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

	_onPortletClose: function(portletData) {
		var instance = this;

		var popup = jQuery('#portal_add_content');
		var item = popup.find('.lfr-portlet-item[@plid=' + portletData.plid + '][@portletId=' + portletData.portletId + '][@instanceable=false]');

		if (item.is('.lfr-portlet-used')) {
			item.removeClass('lfr-portlet-used');
			item.draggable('enable');
		}
	}
};