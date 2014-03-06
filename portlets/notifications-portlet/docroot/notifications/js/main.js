AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	function(A) {
		Liferay.namespace('Notifications');

		Liferay.Notifications = {
			viewNotification: function(event) {
				var instance = this;

				var currentTarget = event.currentTarget;

				var openDialog = currentTarget.attr('data-openDialog');

				var uri = currentTarget.attr('data-href');

				if (uri) {
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

												instance._redirect(uri, openDialog);
											}
										}
									}
								},
								dataType: 'json'
							}
						);
					}
					else {
						var userNotification = currentTarget.ancestor('.user-notification');

						if (userNotification) {
							instance._redirect(uri, openDialog);
						}
					}
				}
			},

			_redirect: function(uri, openDialog) {
				if (openDialog === "false") {
					var topWindow = Liferay.Util.getTop();

					topWindow.location.href = uri;
				}
				else {
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