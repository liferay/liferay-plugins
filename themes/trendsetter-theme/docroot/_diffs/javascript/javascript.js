var Trendsetter = function() {
	var $ = jQuery;

	return {
		init: function() {
			var instance = this;

			this.handleDropDown();
		},

		handleDropDown: function() {
			$('#navigation li').hoverIntent(
				{
					interval: 25,
					timeout: 0,
					over: function() {
						var menuItem = $(this);

						menuItem.find(".child-menu").slideDown("fast");
					},
					out: function() {
						var menuItem = $(this);

						menuItem.find(".child-menu").slideUp("fast");
					}
				}
			);
		}
	};
}();

jQuery(document).ready(
	function() {
		Trendsetter.init();
	}
);