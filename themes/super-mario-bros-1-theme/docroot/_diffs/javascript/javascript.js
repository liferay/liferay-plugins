jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		var dock = jQuery('#banner .lfr-dock').addClass('collapsed').unbind();
		var dockButton = dock.find('.user-greeting span');
		var dockPopupContainer = dock.find('.lfr-dock-list-container');
		var dockPopup = dock.find('.lfr-dock-list');
		var transistion = false;

		jQuery('#navigation a').each(function () {
			var instance = jQuery(this);
			instance.width(Math.ceil(instance.width() / 32) * 32);
		});

		var contentWrapper = jQuery('#content-wrapper');
		var contentOffset = (jQuery('#layout-grid').length > 0) ? 24 : 28;
		contentWrapper.css('height', 'auto').css('height', (Math.ceil(contentWrapper.height() / 32) * 32) + contentOffset);
		if (Liferay.Layout) {
			Liferay.Layout.Columns.sortColumns.bind('sortupdate.sortable', function () {
				contentWrapper.css('height', 'auto').css('height', (Math.ceil(contentWrapper.height() / 32) * 32) + 24);
			});
		}

		dockButton.click(function () {
			if (dock.hasClass('collapsed') && !transistion) {
				transistion = true;
				dockPopupContainer.css('overflow', 'hidden');
				dockPopup.css({
					display: 'block',
					position: 'relative',
					top: 32
				}).animate({
					top: 0
				}, 400, function () {
					dock.removeClass('collapsed').addClass('expanded');
					dockPopupContainer.css({overflow: 'visible', zoom: 1});
					transistion = false;
				});
			}
			else if (dock.hasClass('expanded') && !transistion) {
				transistion = true;
				dockPopupContainer.css('overflow', 'hidden');
				dockPopup.css({
					position: 'relative',
					top: 0
				}).animate({
					top: 32
				}, 400, function () {
					dock.removeClass('expanded').addClass('collapsed');
					transistion = false;
				});
			}
		});
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	jQueryObj: the jQuery wrapped object of the current portlet
	*/

	function(portletId, jQueryObj) {
	}
);

jQuery(document).last(

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);