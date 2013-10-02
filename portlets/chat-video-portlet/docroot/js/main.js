;(function(A) {
	Liferay.on(
		'chatPortletReady',
		function(event) {
			var ChatManager = Liferay.Chat.Manager;

			A.on(
				function() {
					// Runs before the init method

					alert('init...');
				},
				ChatManager,
				'init'
			);

			A.on(
				function(panelName, panel) {
					// Prevents _addPanel from being run

					return new A.Do.Prevent();
				},
				ChatManager,
				'_addPanel'
			);
		}
	);
}(AUI()));