AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	function(A) {
		Liferay.namespace('Microblogs');

		Liferay.Microblogs = {
			init: function(param) {
				var instance = this;

				instance._microblogsEntriesURL = param.microblogsEntriesURL;
			},

			closePopup: function() {
				var instance = this;

				var popup = instance.getPopup()

				if (popup) {
					popup.hide();
				}
			},

			displayPopup: function(url, title) {
				var instance = this;

				var popup = instance.getPopup();

				popup.show();

				popup.set('title', title);

				popup.io.set('uri', url);

				popup.io.start();
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							centered: true,
							constrain2view: true,
							cssClass: 'microblogs-portlet',
							modal: true,
							resizable: false,
							width: 475
						}
					).plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					).render();
				}

				return instance._popup;
			},

			updateMicroblogs: function(url) {
				var instance = this;

				instance._micrblogsEntries = A.one('.microblogs-portlet .portlet-body');

				if (!instance._micrblogsEntries.io) {
					instance._micrblogsEntries.plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					);
				}

				if (!url) {
					url = instance._microblogsEntriesURL;
				}

				instance._micrblogsEntries.io.set('uri', url);

				instance._micrblogsEntries.io.start();
			}
		}
	}
);