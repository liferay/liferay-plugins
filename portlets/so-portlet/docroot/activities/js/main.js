AUI().use(
	'aui-base',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Activities = {
			toggleEntry: function(event, portletNamespace) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.one('#' + portletNamespace + entryId);

				if (entry.hasClass('toggler-content-collapsed')) {
					entry.removeClass('toggler-content-collapsed');
				}
				else {
					entry.addClass('toggler-content-collapsed');
				}
			}
		};
	}
);