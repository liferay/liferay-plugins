AUI().ready(
	'aui-base',
	'aui-io-request',
	'event',
	function(A) {
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

		var messageBoard = A.one('.portlet-message-boards');

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

		var toggleDockbar = A.one('#toggleDockbar');

		if (toggleDockbar) {
			toggleDockbar.on(
				'click',
				function(event) {
					event.preventDefault();

					var body = A.one('body');

					body.toggleClass('show-dockbar');
				}
			);
		}

		var siteNavigationNavbar = A.one('#_145_navSiteNavigationNavbarCollapse ul');

		var navigation = A.one('#navigation ul');

		if (siteNavigationNavbar && navigation) {
			siteNavigationNavbar.setHTML(navigation.getHTML());
		}
	}
);