AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/
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

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);