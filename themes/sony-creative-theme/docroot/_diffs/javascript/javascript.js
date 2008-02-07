jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		jQuery(".parent-nav-item").hover(
			function () {
					var instance = jQuery(this);
					var child = jQuery('.child-menu', this);
					child.show(0);
				},
				function () {
					var instance = jQuery(this);
					var child = jQuery('.child-menu', this);
					child.hide(0);
				}
		);
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