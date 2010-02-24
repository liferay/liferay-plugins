AUI().use(
	'aui-dialog',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Sites = {
			init: function() {
				var instance = this;

				instance._assignEvents();
			},

			closePopup: function() {
				var instance = this;

				if (instance._popup) {
					instance._popup.hide();
				}
			},

			displayPopup: function(url, title) {
				var instance = this;

				var popup = instance._getPopup();

				popup.show();

				popup.set('title', title);

				popup.io.set('uri', url);
				popup.io.start();
			},

			_assignEvents: function() {
				A.one('.so-portlet-sites').delegate(
					'click',
					function(event) {
						var downImage = 'arrow_down.png';
						var rightImage = 'arrow_right.png';

						var image = event.currentTarget;

						var description = image.get('parentNode').one('.description');
						var src = image.get('src');

						if (src.indexOf('down.png') > -1) {
							description.hide();
							src = src.replace(downImage, rightImage);
						}
						else {
							description.show();
							src = src.replace(rightImage, downImage);
						}

						image.set('src', src);
					},
					'.description-toggle'
				);
			},

			_getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							width: 600,
							xy: [15,15]
						}
					).plug(
						A.Plugin.IO,
						{
							autoLoad: false
						}
					).render();
				}

				return instance._popup;
			}
		}
	}
);