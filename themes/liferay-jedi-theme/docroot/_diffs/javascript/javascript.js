var LiferayJedi = function () {
	var $ = jQuery;
	return {
		init: function() {
			var instance = this;

			instance.handleSearchForm();
			instance.dropDownMenu();
			instance.handleLastChild();
			instance.createToggleControls();
		},

		createToggleControls: function(args) {
			var instance = this;

			var dock = jQuery('.lfr-dock .lfr-dock-list');
			var toggleLnk = jQuery('<a href="javascript: ;">Toggle Edit Controls</a>');
			var li = jQuery('<li></li>');

			toggleLnk.click(
				function(event){
					jQuery('.lfr-meta-actions, .portlet-borderless-bar, .portlet-icons').toggle();
				}
			);

			li.append(toggleLnk);
			dock.find('> .my-places').before(li);
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
		},

		handleSearchForm: function() {
			var searchForm = $('#banner .search');

			if (searchForm.length) {
				var searchInput = searchForm.find('input[@type=image]');
				var searchLink = $('<a class="search-input-link" href="javascript:;"></a>');

				searchLink.click(
					function() {
						$(this).parents('form')[0].submit();
					}
				);

				searchInput.hide();
				searchInput.before(searchLink);
			}
		},

		handleLastChild: function () {
			var instance = this;

			$('#footer ul li:last').addClass('last-child');
		}
	};
}();

jQuery(document).ready(
	function() {
		LiferayJedi.init();
	}
);