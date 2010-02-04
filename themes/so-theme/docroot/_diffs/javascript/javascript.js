var LiferayInc = function() {
	var $ = jQuery;
	
	return {
		init: function() {
			var instance = this;

			instance.handleMySitesDropDown();
		},

		handleMySitesDropDown: function() {
			$('#navigation-top .my-sites').hoverIntent(
				{
					interval: 0,
					timeout: 500,
					over: function() {
						$(this).addClass('open');
						$('.child-menu', $(this)).show();
					},
					out: function() {
						$(this).removeClass('open');
						$('.child-menu', $(this)).hide();
					}
				}
			);
		}
	};
}();

jQuery(document).ready(
	function() {
		LiferayInc.init();
	}
);