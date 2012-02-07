AUI().ready(
	'aui-io-request',
	function(A) {
		var body = A.getBody();
		A.one('#toggleFluid').on(
			'click',
			function (event) {
			event.preventDefault();

			var toggleFluid = 'so-layout-fluid'

			var isFluidLayout = body.hasClass('so-layout-fluid');

			body.toggleClass('so-layout-fluid');

			if (!isFluidLayout) {
				var toggleFluidClass = true;
			} else {
				var toggleFluidClass = false;
			};

			A.io.request(
				themeDisplay.getPathMain() + '/portal/session_click',
				{
					data: {
							'so-layout-fluid': toggleFluidClass
						}
				}
			);
		});
	}
);