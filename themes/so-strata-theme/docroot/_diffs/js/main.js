AUI().ready(
	'aui-base',
	'aui-io-request',
	'event',
	function(A) {
		var messageBoard = A.one('.portlet-message-boards');
		var toggleDockbar = A.one('#toggleDockbar');

		toggleDockbar.on(
			'click',
			function(event) {
				event.preventDefault();

				var body = A.one('body');

				body.toggleClass('show-dockbar');

				A.fire('close-menus', event);
			}
		);

		if (messageBoard) {
			messageBoard.delegate(
				['mouseenter', 'mouseleave'],
				function(event) {
					var target = event.currentTarget;

					target.toggleClass('controls-visible', event.type == 'mouseenter');
				},
				'.message-container'
			);
		}

		var memberButton = A.one('#memberButton');

		if (memberButton) {
			memberButton.on(
				'click',
				function(event) {
					event.preventDefault();

					A.io.request(
						event.currentTarget.attr('href'),
						{
							on: {
								success: function(event, id, obj) {
									window.location.reload();
								}
							}
						}
					);
				}
			);
		}
	}
);