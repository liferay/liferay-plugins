AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/
	'aui-base',
	'aui-io-request',
	'event',

	function(A) {
		var body = A.getBody();
		var messageBoard = A.one('.portlet-message-boards');

		if (messageBoard) {
			var toggleEditControls = function (node) {
				node.each(
					function(n) {
						n.on(
							['mouseenter', 'mouseleave']
							, function(event) {
									var t = 10000;

								if (event.type == 'mouseenter') {
									n.addClass('controls-visible');

									setTimeout(
										function() {
											if (n.hasClass('controls-visible')) {
												n.removeClass('controls-visible');
											}
									}, t);
								} else if (event.type == 'mouseleave') {
									n.removeClass('controls-visible');
								}
							}
						);
					}
				);
			};

			var threads = messageBoard.all('.message-container');

			toggleEditControls(threads);
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