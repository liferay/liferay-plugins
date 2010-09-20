AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/
		'aui-base', 'anim-node-plugin', function(A) {
		var nav = A.all('#navigation ul');
		var navLight = A.all('#navigation #navigation-light');
		
		navLight.plug(A.Plugin.NodeFX, {
			from: {
				opacity: 0
			},
			to: {
				opacity: 1
			},
			duration: 0.3
		});

		nav.hover(function(event) {
			A.one('#navigation-light').fx.set('reverse', false).run();
		},
		function(event) {
			A.one('#navigation-light').fx.set('reverse', true).run();
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