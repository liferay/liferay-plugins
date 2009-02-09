var Glowstick = function() {
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

						menuItem.find(".child-menu").show();
					},
					out: function() {
						var menuItem = $(this);

						menuItem.find(".child-menu").hide();
					}
				}
			);
		}
	};
}();

jQuery(document).ready(
	function() {
		Glowstick.init();
	}
);