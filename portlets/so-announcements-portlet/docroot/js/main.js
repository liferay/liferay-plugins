AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	'transition',
	function(A) {
		Liferay.namespace('Announcements');

		Liferay.Announcements = {
			init: function(config) {
				var instance = this;

				instance._namespace = config.namespace;
				instance._viewEntriesURL = config.viewEntriesURL;
			},

			toggleEntry: function(event) {
				var entry = event.currentTarget.ancestor('.entry');

				var entryId = entry.attr('data-entryId');

				var content = entry.one('.entry-content');
				var contentContainer = entry.one('.entry-content-container');
				var control = entry.one('.toggle-entry');

				var contentHeight = '75px';

				if (entry.hasClass('announcement-collapsed')) {
					entry.removeClass('announcement-collapsed');

					contentContainer.setStyle('height', '75px');
					contentContainer.setStyle('maxHeight', 'none');

					contentHeight = content.getComputedStyle('height');

					if (control) {
						control.html('<i class="icon-collapse-alt"></i><span> ' + Liferay.Language.get('view-less') + '</span>');
					}
				}
				else {
					entry.addClass('announcement-collapsed');

					if (control) {
						control.html('<i class="icon-expand-alt"></i><span> ' + Liferay.Language.get('view-more') + '</span>');
					}
				}

				contentContainer.transition(
					{
						duration: 0.5,
						easing: 'ease-in-out',
						height: contentHeight
					}
				);
			},

			transitionEntry: function(id) {
				var entry = A.one(id);

				entry.transition(
					{
						opacity: {
							duration: .5,
							value: 0
						}
					}
				);
			},

			updateEntries: function(readEntries, start) {
				var instance = this;

				var url = Liferay.Util.addParams(instance._namespace + 'readEntries=' + readEntries, instance._viewEntriesURL) || instance._viewEntriesURL;

				var node;

				if (readEntries) {
					var header = A.one('#' + instance._namespace + 'readEntriesContainer .entries .header');

					if (header) {
						var expanded = 'false';

						if (header.hasClass('toggler-header-expanded')) {
							expanded = 'true';
						}

						url = Liferay.Util.addParams(instance._namespace + 'expanded=' + expanded, url) || url;
					}

					node = A.one('#' + instance._namespace + 'readEntriesContainer');
				}
				else {
					node = A.one('#' + instance._namespace + 'unreadEntriesContainer');
				}

				if (node) {
					var entries = node.one('.entries');

					if (entries) {
						start = (start == null) ? entries.attr('data-start') : start;

						url = Liferay.Util.addParams(instance._namespace + 'start=' + start, url) || url;
					}

					if (!node.io) {
						node.plug(
							A.Plugin.IO,
							{
								autoLoad: false
							}
						);
					}

					node.io.set('uri', url);

					node.io.start();
				}
			}
		};
	}
);