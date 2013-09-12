AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	function(A) {
		Liferay.namespace('Notifications');

		Liferay.Notifications = {
			viewNotification: function(event) {
				var instance = this;

				var currentTarget = event.currentTarget;

				var markAsReadURL = currentTarget.attr('data-markAsReadURL');

				if (markAsReadURL) {
					A.io.request(
						markAsReadURL,
						{
							after: {
								success: function(event, id, obj) {
									var responseData = this.get('responseData');

									if (responseData.success) {
										var userNotification = currentTarget.ancestor('.user-notification');

										if (userNotification) {
											userNotification.removeClass('unread');

											var read = userNotification.one('.content .read');

											if (read) {
												read.setHTML(Liferay.Language.get('read'));
											}
										}
									}
								}
							},
							dataType: 'json'
						}
					);
				}

				var uri = currentTarget.attr('href');

				if (uri) {
					Liferay.Util.openWindow(
						{
							id: 'notificationsWindow',
							uri: uri
						}
					);
				}
			}
		};
	}
);