jQuery(document).ready(
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