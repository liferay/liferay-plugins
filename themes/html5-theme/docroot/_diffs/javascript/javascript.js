/* HTML5 Support for IE */

(
	function() {
		var element = 'abbr article aside audio bb canvas datagrid datalist details dialog figure footer header mark menu meter nav output progress section time video'.split(' '),
		elementLength = element.length;
		
		while (elementLength--) {
			document.createElement(element[elementLength]);
		}
	}
)();

/* jQuery */

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
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