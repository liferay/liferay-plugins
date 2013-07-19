AUI().ready(
	'aui-base',
	'aui-io-request',
	'event',
	function(A) {
		var messageBoard = A.one('.portlet-message-boards');

		if (messageBoard) {
			messageBoard.delegate(
				['mouseenter', 'mouseleave'],
				function(event) {
					var target = event.currentTarget;

					if (event.type == 'mouseenter') {
						target.addClass('controls-visible');

						setTimeout(
							function() {
								if (target.hasClass('controls-visible')) {
									target.removeClass('controls-visible');
								}
							},
							10000
						);
					}
					else if (event.type == 'mouseleave') {
						target.removeClass('controls-visible');
					}
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
						event.target.get('href'),
						{
							method: 'POST',
							on: {
								success: function(event, id, obj) {
									window.location = '';
								}
							}
						}
					);
				}
			);
		}
	}
);