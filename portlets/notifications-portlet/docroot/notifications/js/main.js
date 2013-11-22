AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	function(A) {
		Liferay.namespace('Notifications');

		Liferay.Notifications = {
			viewNotification: function(event) {
				var instance = this;

				var currentTarget = event.currentTarget;

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

												if (instance._openWindow(uri)) {
													Liferay.Util.openWindow(
														{
															id: 'notificationsWindow',
															uri: uri
														}
													);
												}
												else {
													var topWindow = Liferay.Util.getTop();

													topWindow.location.href = uri;
												}
											}
										}
									}
								},
								dataType: 'json'
							}
						);
					}
				}
			},

			_openWindow: function(uri) {
				if (uri.match('p_p_state=maximized') || uri.match('p_p_state=pop_up') || uri.match('p_p_state=exclusive')) {
					return true;
				}

				return false;
			}
		};
	}
);