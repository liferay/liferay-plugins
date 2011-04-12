AUI().use(
	'aui-base',
	'aui-dialog',
	'aui-io-plugin',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.Sites = {
			init: function() {
				var instance = this;

				instance._assignEvents();
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

				var viewportRegion = A.getBody().get('viewportRegion');

				var popup = instance.getPopup();

				popup.show();

				popup.set('title', title);
				popup.set('xy', [viewportRegion.left + 20, viewportRegion.top + 20]);

				popup.io.set('uri', url);
				popup.io.start();
			},

			getPopup: function() {
				var instance = this;

				if (!instance._popup) {
					instance._popup = new A.Dialog(
						{
							cssClass: 'so-portlet-sites-dialog',
							resizable: false,
							width: 526
						}
					).plug(
						A.Plugin.IO,
						{autoLoad: false}
					).render();
				}

				return instance._popup;
			},

			updateSites: function() {
				var instance = this;

				if (!instance._siteList) {
					instance._siteList = A.one('.so-portlet-sites form');
				}

				if (!instance._siteList.io) {
					instance._siteList.plug(
						A.Plugin.IO,
						{
							autoLoad: false,
							cache: false,
							method: 'get',
							on: {
								success: function(event, id, xhr) {
									event.stopImmediatePropagation();

									var html = A.Node.create(xhr.responseText).one('.so-portlet-sites form').get('innerHTML');

									instance._siteList.setContent(html);
								}
							},
							uri: themeDisplay.getLayoutURL()
						}
					);
				}

				instance._siteList.io.start();
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
			}
		}
	}
);