AUI().ready(
	'aui-base',
	function(A) {
		var hideTask = new A.DelayedTask(
			function(event) {
				var childMenu = this.one('.child-menu');

				if (childMenu) {
					childMenu.removeClass('open');
					childMenu.setStyle('display', 'none');
				}
			}
		);

		A.all('#navigation-top .my-sites').on(
			{
				mouseenter: function(event) {
					var childMenu = this.one('.child-menu');

					if (childMenu) {
						childMenu.addClass('open');
						childMenu.setStyle('display', 'block');
					}
				},
				
				mouseleave: function(event) {
					hideTask.delay(25, null, this, event);
				}
			}
		);
	}
);