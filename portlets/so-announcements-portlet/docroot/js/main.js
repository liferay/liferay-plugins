AUI().use(
	'aui-base',
	'transition',
	function(A) {
		Liferay.namespace('Announcements');

		Liferay.Announcements = {
			toggleEntry: function(event, portletNamespace) {
				var node = event.currentTarget;
				var entryId = node.getAttribute('data-entryId');
				var entry = A.one('#' + portletNamespace + entryId);
				var content = entry.one('.entry-content');
				var contentContainer = entry.one('.entry-content-container');
				var control = entry.all('.toggle-entry');

				contentHeight = '75px';

				if (entry.hasClass('visible')) {
					entry.removeClass('visible');

					contentHeight = '75px';

					if (control) {
						control.html(Liferay.Language.get('view-more'));
					}
				}
				else {
					contentContainer.setStyle('height', '75px');
					contentContainer.setStyle('max-height', 'none');
					entry.addClass('visible');

					contentHeight = content.getComputedStyle('height');

					if (control) {
						control.html(Liferay.Language.get('view-less'));
					}
				}
				contentContainer.transition(
					{
						height: contentHeight,
						duration: 0.5,
						easing: 'ease-in-out'
					}
				);
			}
		};
	}
);