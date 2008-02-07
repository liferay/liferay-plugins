jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		jQuery("#navigation ul li").sifr({
			path: themeDisplay.getPathThemeRoot()+'fonts/',
			color:'02245c',
			link:'02245c',
			hover:'fff'
		});
		jQuery(".portlet-title").sifr({
			path: themeDisplay.getPathThemeRoot()+'fonts/',
			color: '02245c'
		});
		jQuery(".lfr-dock.interactive-mode h2 span").sifr({
			path: themeDisplay.getPathThemeRoot()+'fonts/',
			font: 'Keyboard Plaque',
			color:'ffffff'
		});
		jQuery("#navigation ul li").hover(
		function(){
			jQuery(this).addClass('hover');
		},
		function(){
			jQuery(this).removeClass('hover');
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