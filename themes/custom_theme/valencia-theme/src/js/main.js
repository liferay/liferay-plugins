AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {

		// (Optional) Active Carousel
		$('.carousel').carousel()

		// Toggle Navbar dropdowns
		$( '.select' ).dropdown({
			'dropdownClass': 'dropdown-menu',
			'optionClass': ''
		});
		// Make the Navbar opaque

		var transparent = true;

		if ($( '.navbar-color-on-scroll' ).length !== 0) {
			$(window).on( 'scroll', debounce(function () {
				if ($(document).scrollTop() > 100) {
					if (transparent) {
						transparent = false;
						$( '.navbar-color-on-scroll' ).removeClass( 'navbar-transparent' );
					}
				} else {
					if (!transparent) {
						transparent = true;
						$( '.navbar-color-on-scroll' ).addClass( 'navbar-transparent' );
					}
				}
			}, 17));
		}

		function debounce(func, wait, immediate) {
			var timeout;
			return function () {
				var context = this,
					args = arguments;
				clearTimeout(timeout);
				timeout = setTimeout(function () {
					timeout = null;
					if (!immediate) {
						func.apply(context, args);
					}
				}, wait);
				if (immediate && !timeout) {
					func.apply(context, args);
				}
			};
		}
	}

	

);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);

jQuery(document).ready(function($) {

});