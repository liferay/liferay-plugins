var LiferayJedi = function () {
	var $ = jQuery;
	return {
		init: function() {
			var instance = this;
			
			instance.handleSearchForm();
			instance.dropDownMenu();
			instance.handleLastChild();
		},

		handleSearchForm: function() {
			var searchForm = $('#banner .search');	
			
			var searchInput = searchForm.find('input[@type=image]');
			var searchLink = $('<a class="search-input-link" href="javascript:;"></a>');
			
			searchLink.click(
				function() {
					$(this).parents('form')[0].submit();
				}
			);
			
			searchInput.hide();
			searchInput.before(searchLink);
		},

		handleLastChild: function () {
			var instance = this;

			$('#footer ul li:last').addClass('last-child');
		},

		dropDownMenu: function() {
			$(".parent-nav-item").hoverIntent(
				{
					interval: 25,
					timeout: 0,
					over: function () {
						var instance = $(this);
						var child = $('.child-menu', this);
						instance.addClass("init");
						child.slideDown(100);
					},
					
					out: function () {
						var instance = $(this);
						var child = $('.child-menu', this);
						child.slideUp(50);
						instance.removeClass("init");
					}
				}
			);
		}
	};
}();

jQuery(document).ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {
		LiferayJedi.init();
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