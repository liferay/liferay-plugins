var LiferayNoir = function () {
	var $ = jQuery;
	return {
		init: function() {
			var instance = this;
			
			instance.handleLastChild();
			instance.handleSearchForm();
			instance.fixAddPage();
		},
		
		fixAddPage: function() {
			var instance = this;
			if (jQuery.browser.msie) {
				jQuery('#add-page').css('position', 'absolute');
			}
		},

		handleLastChild: function () {
			var instance = this;
			if (!$.browser.msie) {
				return;
			}
			$('#footer ul li:last').addClass('last-child');
		},
		
		handleSearchForm: function() {
			var searchForm = $('#banner .search');	
			
			var searchInput = searchForm.find('input[type=image]');
			var searchLink = $('<a class="search-input-link" href="javascript:;"></a>');
			
			searchLink.click(
				function() {
					$(this).parents('form')[0].submit();
				}
			);
			
			searchInput.hide();
			searchInput.before(searchLink);
		}
	};
}();

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		LiferayNoir.init();
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