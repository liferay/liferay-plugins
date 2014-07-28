AUI.add(
	'liferay-plugin-dockbar-notifications',
	function(A) {
		var DockbarNotifications = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'dockbarnotifications',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._actionableNotificationsList = config.actionableNotificationsList;
						instance._baseActionURL = config.baseActionURL;
						instance._nonActionableNotificationsList = config.nonActionableNotificationsList;
						instance._portletKey = config.portletKey;

						var userNotifications =  A.one('.dockbar-user-notifications');

						userNotifications.on(
							'click',
							function(event) {
								var target = event.target;

								if (target.ancestor('.dockbar-user-notifications-container')) {
									return;
								}

								instance._setDelivered();

								var currentTarget = event.currentTarget;

								var container = currentTarget.one('.dockbar-user-notifications-container');

								container.toggleClass('open');

								var menuOpen = container.hasClass('open');

								if (menuOpen) {
									currentTarget.on(
										'clickoutside',
										function(event) {
											container.removeClass('open');
										}
									);

									instance._nonActionableNotificationsList.render();
									instance._actionableNotificationsList.render();
								}
							}
						);

						A.on(
							'domready',
							function() {
								Liferay.Poller.addListener(instance._portletKey, instance._onPollerUpdate, instance);
							}
						);
					},

					_onPollerUpdate: function(response) {
						var instance = this;

						instance._updateDockbarNotificationsCount(response.newUserNotificationsCount, response.timestamp, response.unreadNonActionableUserNotificationsCount, response.unreadUserNotificationsCount);
					},

					_setDelivered: function() {
						var instance = this;

						var portletURL = new Liferay.PortletURL.createURL(instance._baseActionURL);

						portletURL.setParameter('javax.portlet.action', 'setDelivered');

						portletURL.setWindowState('normal');

						A.io.request(portletURL.toString());
					},

					_updateDockbarNotificationsCount: function(newUserNotificationsCount, timestamp, unreadNonActionableUserNotificationsCount, unreadUserNotificationsCount) {
						var instance = this;

						if (!instance._previousTimestamp || (instance._previousTimestamp < timestamp)) {
							instance._previousTimestamp = timestamp;

							var dockbarUserNotificationsCount = A.one('.dockbar-user-notifications .user-notifications-count');

							if (dockbarUserNotificationsCount) {
								dockbarUserNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

								dockbarUserNotificationsCount.setHTML(unreadUserNotificationsCount);

								instance._totalMarkAsReadableCount = unreadNonActionableUserNotificationsCount;
							}
						}
					}
				}
			}
		)

		Liferay.DockbarNotifications = DockbarNotifications;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-deprecated', 'liferay-poller', 'liferay-portlet-base', 'liferay-portlet-url']
	}

);

AUI.add(
	'liferay-plugin-notifications-list',
	function(A) {
		var NotificationsList = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'notificationslist',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._actionable = config.actionable;
						instance._baseActionURL = config.baseActionURL;
						instance._baseRenderURL = config.baseRenderURL;
						instance._baseResourceURL = config.baseResourceURL;
						instance._end = config.start + config.delta;
						instance._fullView = config.fullView;
						instance._namespace = config.namespace;
						instance._markAllAsReadNode = config.markAllAsReadNode;
						instance._notificationsContainer = config.notificationsContainer;
						instance._notificationsCount = config.notificationsCount;
						instance._notificationsNode = config.notificationsNode;
						instance._portletKey = config.portletKey;
						instance._start = config.start;

						instance._nextPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.actionable, (config.start + config.delta).toString(), (config.end + config.delta).toString());

						if ((config.end - config.delta) <= 0) {
							instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.actionable, '0', config.delta.toString());
						}
						else {
							instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.actionable, (config.start - config.delta).toString(), (config.end - config.delta).toString());
						}

						instance._bindUI();
					},

					markAsAllRead: function(event, userNotificationEventIds) {
						event.preventDefault();

						var instance = this;

						var uri = instance._getActionURL('markAllAsRead', userNotificationEventIds);

						A.io.request(
							uri,
							{
								after: {
									success: function() {
										var response = this.get('responseData');

										if (response.success) {
											instance.render();
										}
									}
								},
								dataType: 'JSON'
							}
						);
					},

					render: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						notificationsNode.plug(A.LoadingMask);

						notificationsNode.loadingmask.show();

						var portletURL = new Liferay.PortletURL.createURL(instance._baseResourceURL);

						portletURL.setParameter('actionable', instance._actionable);
						portletURL.setParameter('end', instance._end);
						portletURL.setParameter('start', instance._start);

						portletURL.setResourceId("getUserNotificationEvents");

						A.io.request(
							portletURL.toString(),
							{
								dataType: 'JSON',
								on: {
									success: function() {
										var response = this.get('responseData');

										if (response) {
											var total = response['total'];

											var notificationsCount = notificationsContainer.one(instance._notificationsCount);

											if (notificationsCount) {
												notificationsCount.setHTML('(' + total + ')');
											}

											if (notificationsNode) {
												var entries = [];

												var entriesJSONArray = response['entries'];

												if (entriesJSONArray) {
													for (var i = 0; i < entriesJSONArray.length; i++) {
														entries.push(entriesJSONArray[i]);
													}

													entries = entries.join('');
												}

												var markAllAsReadNode = notificationsContainer.one(instance._markAllAsReadNode);
												var message = notificationsContainer.one('.message');

												if (entries.length == 0) {
													if (markAllAsReadNode) {
														markAllAsReadNode.hide();
													}

													if (message) {
														message.show();
													}

													notificationsNode.empty();
												}
												else {
													if (markAllAsReadNode) {
														markAllAsReadNode.show();
													}

													if (message) {
														message.hide();
													}

													notificationsNode.setHTML(entries);
												}

												instance._userNotificationEventIds = response['userNotificationEventIds'];

												notificationsNode.loadingmask.hide();
											}
										}
									}
								}
							}
						);
					},

					_bindMarkAllAsRead: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var markAllAsReadNode = notificationsContainer.one(instance._markAllAsReadNode);

						if (markAllAsReadNode) {
							markAllAsReadNode.on(
								'click',
								function(event) {
									instance.markAsAllRead(event, instance._userNotificationEventIds);
								}
							);
						}
					},

					_bindMarkAsRead: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									var currentTarget = event.currentTarget;

									var currentRow = currentTarget.ancestor('.user-notification');

									currentRow.plug(A.LoadingMask);

									currentRow.loadingmask.show();

									var userNotificationLink = currentRow.one('.user-notification-link');

									var markAsReadURL = userNotificationLink.attr('data-markAsReadURL');

									if (markAsReadURL) {
										A.io.request(
											markAsReadURL,
											{
												after: {
													success: function() {
														var responseData = this.get('responseData');

														if (responseData.success) {
															currentRow.remove();

															instance.render();
														}
													}
												},
												dataType: 'JSON'
											}
										);
									}
								},
								'.user-notification .mark-as-read'
							);
						}
					},

					_bindNavMenu: function(menu, uri, allNotifications, unreadActionable, unreadNonActionable) {
						var instance = this;

						if (menu) {
							menu.on(
								'click',
								function() {
									instance._allNotifications = allNotifications;
									instance._unreadActionable = unreadActionable;
									instance._unreadNonActionable = unreadNonActionable;

									instance._renderNotificationsList(uri);

									var userNotificationsSidebar = A.one('.user-notifications-sidebar');

									if (userNotificationsSidebar) {
										userNotificationsSidebar.all('.nav a').removeClass('selected');
									}

									menu.addClass('selected');
								}
							);
						}
					},

					_bindNextPageNotifications: function() {
						var instance = this;

						instance._bindPaginateDelegation(instance._getFullViewNotificationsList(), false, '.message .next a');
					},

					_bindNotificationsAction: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									instance._respondNotification(event);
								},
								'.user-notification .btn-action'
							);
						}
					},

					_bindPaginateDelegation: function(userNotificationsList, previous, selector) {
						var instance = this;

						if (userNotificationsList) {
							userNotificationsList.delegate(
								'click',
								function(event) {
									event.preventDefault();

									var currentTarget = event.currentTarget;

									var userNotificationsList = currentTarget.ancestor('.user-notifications-list-container .user-notifications-list');

									if (userNotificationsList) {
										if (previous) {
											instance._renderNotificationsList(instance._previousPageNotificationsURL);
										}
										else {
											instance._renderNotificationsList(instance._nextPageNotificationsURL);
										}
									}
								},
								selector
							);
						}
					},

					_bindPreviousPageNotifications: function() {
						var instance = this;

						instance._bindPaginateDelegation(instance._getFullViewNotificationsList(), true, '.message .previous a');
					},

					_bindUI: function() {
						var instance = this;

						instance._bindMarkAllAsRead();

						instance._bindMarkAsRead();

						instance._bindNotificationsAction();

						//instance._bindNextPageNotifications();

						//instance._bindPreviousPageNotifications();

						//instance._bindUserNotificationsSideBar();

						instance._bindViewNotification();
					},

					_bindUserNotificationsSideBar: function() {
						var instance = this;

						var userNotificationsSidebar = A.one('.user-notifications-sidebar');

						if (userNotificationsSidebar) {
							var allNotificationsNav = userNotificationsSidebar.one('.all-notifications');

							instance._bindNavMenu(allNotificationsNav, instance._getRenderURL('/notifications/view_entries.jsp'), true, false, false);

							var unreadActionableNav = userNotificationsSidebar.one('.unread-actionable');

							instance._bindNavMenu(unreadActionableNav, instance._getRenderURL('/notifications/view_entries.jsp', 'unread-actionable'), false, true, false);

							var unreadNonActionableNav = userNotificationsSidebar.one('.unread-nonactionable');

							instance._bindNavMenu(unreadNonActionableNav, instance._getRenderURL('/notifications/view_entries.jsp', 'unread-nonactionable'), false, false, true);

							var manageNav = userNotificationsSidebar.one('.manage');

							instance._bindNavMenu(manageNav, instance._getRenderURL('/notifications/configuration.jsp'), false, false, false);
						}
					},

					_bindViewNotification: function() {
						var instance = this;

						var notificationsContainer = A.one(instance._notificationsContainer);

						var notificationsNode = notificationsContainer.one(instance._notificationsNode);

						if (notificationsNode) {
							notificationsNode.delegate(
								'click',
								function(event) {
									instance._viewNotification(event);
								},
								'.user-notification .user-notification-link'
							);
						}
					},

					_getActionURL: function(name, userNotificationEventIds) {
						var instance = this;

						var portletURL = new Liferay.PortletURL.createURL(instance._baseActionURL);

						portletURL.setParameter('javax.portlet.action', name);

						if (userNotificationEventIds) {
							portletURL.setParameter('userNotificationEventIds', userNotificationEventIds);
						}

						portletURL.setWindowState('normal');

						return portletURL.toString();
					},

					_getRenderURL: function(mvcPath, actionable, start, end, fullView) {
						var instance = this;

						var portletURL = new Liferay.PortletURL.createURL(instance._baseRenderURL);

						portletURL.setParameter('mvcPath', mvcPath);

						if (actionable != 'undefined') {
							portletURL.setParameter('actionable', actionable);
						}

						if (start != 'undefined') {
							portletURL.setParameter('start', start);
						}

						if (end != 'undefined') {
							portletURL.setParameter('end', end);
						}

						if (fullView != 'undefined') {
							portletURL.setParameter('fullView', fullView);
						}

						portletURL.setWindowState('exclusive');

						return portletURL.toString();
					},

					_hasRequestSent: function(node, uri) {
						var instance = this;

						if ((instance._lastNode == node) && (instance._lastUri == uri)) {
							return true;
						}
						else {
							instance._lastNode = node;
							instance._lastUri = uri;

							setTimeout(
								function() {
									instance._lastNode = null;
									instance._lastUri = null;
								}, 300);

							return false;
						}
					},

					_openWindow: function(uri) {
						if (uri.match('p_p_state=maximized') || uri.match('p_p_state=pop_up') || uri.match('p_p_state=exclusive')) {
							return true;
						}

						return false;
					},

					_redirect: function(uri) {
						var instance = this;

						if (uri) {
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
					},

					_renderNotificationsList: function(uri) {
						var instance = this;

						var notificationsList = A.one(instance._notificationsContainer);

						if (notificationsList && !instance._hasRequestSent(notificationsList, uri)) {
							if (!notificationsList.io) {
								notificationsList.plug(
									A.Plugin.IO,
									{
										autoLoad: false
									}
								);
							}

							notificationsList.io.set('uri', uri);
							notificationsList.io.start();
						}
					},

					_respondNotification: function(event) {
						var instance = this;

						event.preventDefault();

						var currentTarget = event.currentTarget;

						if (instance._hasRequestSent(currentTarget, currentTarget.attr('href'))) {
							return;
						}

						var currentRow = currentTarget.ancestor('.user-notification');

						currentRow.plug(A.LoadingMask);

						currentRow.loadingmask.show();

						A.io.request(
							currentTarget.attr('href'),
							{
								after: {
									success: function() {
										var response = this.get('responseData');

										if (response.success) {
											currentRow.remove();

											var deleteNode = currentTarget.ancestor('.user-notification-delete');

											if (deleteNode) {
												A.io.request(deleteNode.getAttribute('data-deleteURL'));
											}

											instance.render();
										}
										else {
											currentRow.loadingmask.hide();
										}
									}
								},
								dataType: 'JSON'
							}
						);
					},

					_updateFullViewNotificationsCount: function(unreadActionableUserNotificationsCount, unreadNonActionableUserNotificationsCount) {
						var userNotificationsSidebar = A.one('.user-notifications-sidebar');

						if (userNotificationsSidebar) {
							var unreadCount = userNotificationsSidebar.one('.unread-actionable .count');

							if (unreadCount) {
								unreadCount.setHTML(unreadActionableUserNotificationsCount);
							}

							unreadCount = userNotificationsSidebar.one('.unread-nonactionable .count');

							if (unreadCount) {
								unreadCount.setHTML(unreadNonActionableUserNotificationsCount);
							}
						}
					},

					_viewNotification: function(event) {
						var instance = this;

						var currentTarget = event.currentTarget;

						var target = event.target;

						if (target.hasClass('.mark-as-read') || target.ancestor('.mark-as-read')) {
							return;
						}

						var uri = currentTarget.attr('data-href');

						var markAsReadURL = currentTarget.attr('data-markAsReadURL');

						if (instance._hasRequestSent(currentTarget, markAsReadURL)) {
							return;
						}

						if (markAsReadURL) {
							A.io.request(
								markAsReadURL,
								{
									after: {
										success: function() {
											var responseData = this.get('responseData');

											if (responseData.success) {
												instance._redirect(uri);
											}
										}
									},
									dataType: 'JSON'
								}
							);
						}
						else {
							instance._redirect(uri);
						}
					}
				}
			}
		);

		Liferay.NotificationsList = NotificationsList;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-deprecated', 'aui-loading-mask-deprecated', 'liferay-poller', 'liferay-portlet-base', 'liferay-portlet-url']
	}
);