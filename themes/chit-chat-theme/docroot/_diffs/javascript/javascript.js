AUI().ready(
	'aui-base',
	function(A) {
		var hideTask = new A.DelayedTask(
			function(event) {
				var childMenu = this.one('.child-menu');

				if (childMenu) {
					childMenu.setStyle('display', 'none');
				}
			}
		);

		A.all('#navigation li').on(
			{
				mouseenter: function(event) {
					var childMenu = this.one('.child-menu');

					if (childMenu) {
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