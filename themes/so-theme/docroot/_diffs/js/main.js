AUI().ready(
	'aui-io-request',
	function(A) {
		var body = A.getBody();

		var toggleFluid = A.one('#toggleFluid');

		if (toggleFluid) {
			toggleFluid.on(
				'click',
				function(event) {
					if (!body.hasClass('so-layout-fluid-ad')) {
						body.toggleClass('so-layout-fluid');

						A.io.request(
							themeDisplay.getPathMain() + '/portal/session_click',
							{
								data: {
									'so-layout-fluid': body.hasClass('so-layout-fluid')
								}
							}
						);
					};
				}
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