AUI().use(
	'aui-base',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Activities = {
			toggleEntry: function(event, portletNamespace) {
				var entryId = event.currentTarget.attr('data-entryId');

				var entry = A.one('#' + portletNamespace + entryId);

				var bodyContainer = entry.one('.grouped-activity-body-container');
				var subentryHeight = entry.one('.activity-subentry').outerHeight();

				var minHeight = (subentryHeight * 3) + 'px';

				if (entry.hasClass('toggler-content-collapsed')) {
					entry.removeClass('toggler-content-collapsed');

					bodyContainer.setStyle('height', minHeight);
					bodyContainer.setStyle('max-height', 'none');
				}
				else {
					entry.addClass('toggler-content-collapsed');
				}
			}
		};
	}
);