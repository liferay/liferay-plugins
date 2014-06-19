AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	'liferay-poller',
	'liferay-portlet-url',
	function(A) {
		Liferay.namespace('Notifications');

		Liferay.Notifications = {
			init: function(config) {
				var instance = this;

				instance._baseActionURL = config.baseActionURL;
				instance._baseRenderURL = config.baseRenderURL;
				instance._baseResourceURL = config.baseResourceURL;
				instance._currentPageNotificationEventsCount = config.currentPageNotificationEventsCount;
				instance._dockbarNotificationsURL = instance._getRenderURL('/dockbar_notifications/view.jsp', 'unread', null, null, 'false', 'true');
				instance._fullViewNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, config.start.toString(), config.end.toString(), 'true');
				instance._lastPage = config.userNotificationEventsCount <= config.end;
				instance._nextPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, (config.start + config.delta).toString(), (config.end + config.delta).toString(), 'true');

				if ((config.end - config.delta) <= 0) {
					instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, '0', config.delta.toString(), 'true');
				}
				else {
					instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, (config.start - config.delta).toString(), (config.end - config.delta).toString(), 'true');
				}

				instance._createMarkAllAsReadNode(config);

				instance._bindUI();

				instance._updateFullViewNotificationsCount(config.filter, config.userNotificationEventsCount);
			},

			initDockbarNotifications: function(config) {
				var instance = this;

				instance._baseActionURL = config.baseActionURL;
				instance._baseRenderURL = config.baseRenderURL;
				instance._namespace = config.namespace;
				instance._portletKey = config.portletKey;

				instance._dockbarNotificationsURL = instance._getRenderURL('/dockbar_notifications/view.jsp', 'unread', null, null, 'false', 'true');

				instance._createMenuToggle(config.menuOpen);

				A.on(
					'domready',
					function() {
						Liferay.Poller.addListener(instance._portletKey, instance._onPollerUpdate, instance);
					}
				);
			},

			renderNotificationsList: function(notificationsList, uri) {
				var instance = this;

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

			_bindMarkAllAsRead: function() {
				var instance = this;

				instance._bindMarkAsReadDelegation(false, instance._getDockbarNotificationsList(), true, '.mark-all-as-read');
				instance._bindMarkAsReadDelegation(true, instance._getFullViewNotificationsList(), true, '.mark-all-as-read');
			},

			_bindMarkAsRead: function() {
				var instance = this;

				instance._bindMarkAsReadDelegation(false, instance._getDockbarNotificationsList(), false, '.user-notification .btn-action');
				instance._bindMarkAsReadDelegation(true, instance._getFullViewNotificationsList(), false, '.user-notification .btn-action');
			},

			_bindMarkAsReadDelegation: function(fullView, notificationsList, markAllAsRead, selector) {
				var instance = this;

				if (notificationsList) {
					notificationsList.delegate(
						'click',
						function(event) {
							instance._markAsRead(event, fullView, markAllAsRead);
						},
						selector
					);
				}
			},

			_bindNavMenu: function(menu, uri, allNotifications, unread) {
				var instance = this;

				if (menu) {
					menu.on(
						'click',
						function() {
							instance._allNotifications = allNotifications;
							instance._unread = unread;

							instance._setDelivered();

							instance.renderNotificationsList(instance._getFullViewNotificationsList(), uri);

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
									instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._previousPageNotificationsURL);
								}
								else {
									instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._nextPageNotificationsURL);
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

				instance._bindNextPageNotifications();

				instance._bindPreviousPageNotifications();

				instance._bindUserNotificationsSideBar();

				instance._bindViewNotification();
			},

			_bindUserNotificationsSideBar: function() {
				var instance = this;

				var userNotificationsSidebar = A.one('.user-notifications-sidebar');

				if (userNotificationsSidebar) {
					var unreadNav = userNotificationsSidebar.one('.unread');

					instance._bindNavMenu(unreadNav, instance._getRenderURL('/notifications/view_entries.jsp', 'unread'), false, true);

					var allNotificationsNav = userNotificationsSidebar.one('.all-notifications');

					instance._bindNavMenu(allNotificationsNav, instance._getRenderURL('/notifications/view_entries.jsp'), true, false);

					var manageNav = userNotificationsSidebar.one('.manage');

					instance._bindNavMenu(manageNav, instance._getRenderURL('/notifications/configuration.jsp'), false, false);
				}
			},

			_bindViewDelegation: function(notificationsList, selector) {
				var instance = this;

				if (notificationsList) {
					notificationsList.delegate(
						'click',
						function(event) {
							instance._viewNotification(event);
						},
						selector
					);
				}
			},

			_bindViewNotification: function() {
				var instance = this;

				instance._bindViewDelegation(instance._getDockbarNotificationsList(), '.user-notification .user-notification-link');
				instance._bindViewDelegation(instance._getFullViewNotificationsList(), '.user-notification .user-notification-link');
			},

			_createMarkAllAsReadNode: function(config) {
				var instance = this;

				if (config.userNotificationEventsCount > 0) {
					var nodeHTML = '<a class="mark-all-as-read" href="' + instance._getActionURL('markAllAsRead', config.userNotificationEventIds) + '">' +
							A.Lang.sub(Liferay.Language.get('mark-all-as-read-x'), [config.currentPageNotificationEventsCount]) + '</a>';

					var dockbarMarkAllAsRead = A.one('.dockbarMarkAllAsRead');

					if (dockbarMarkAllAsRead) {
						dockbarMarkAllAsRead.get('parentNode').replaceChild(A.Node.create(nodeHTML), dockbarMarkAllAsRead);
					}

					var fullViewMarkAllAsRead = A.one('.fullViewMarkAllAsRead');

					if (fullViewMarkAllAsRead) {
						fullViewMarkAllAsRead.get('parentNode').replaceChild(A.Node.create(nodeHTML), fullViewMarkAllAsRead);
					}
				}
			},

			_createMenuToggle: function(menuOpen) {
				var instance = this;

				var unreadURL = instance._getRenderURL('/notifications/view_entries.jsp', 'unread', null, null, 'false');

				var userNotifications =  A.one('.dockbar-user-notifications');

				if (menuOpen) {
					if (!userNotifications.hasClass('open')) {
						userNotifications.addClass('open');
					}

					userNotifications.on(
						'clickoutside',
						function(event) {
							userNotifications.removeClass('open');
						}
					);

					instance.renderNotificationsList(instance._getDockbarNotificationsList(), unreadURL);
				}
				else {
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

								instance.renderNotificationsList(instance._getDockbarNotificationsList(), unreadURL);

								if (instance._allNotifications) {
									instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._getRenderURL('/notifications/view_entries.jsp'));
								}
								else if (instance._unread || ((typeof(instance._allNotifications) == 'undefined') && (typeof(instance._unread) == 'undefined'))) {
									instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._getRenderURL('/notifications/view_entries.jsp', 'unread'));
								}

								var dockbarUserNotificationsCount = A.one('.dockbar-user-notifications .user-notifications-count');

								if (dockbarUserNotificationsCount) {
									dockbarUserNotificationsCount.removeClass('alert');
								}
							}
						}
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

			_getDockbarNotificationsList: function() {
				var instance = this;

				if (instance._dockbarNotificationsList) {
					return instance._dockbarNotificationsList;
				}

				instance._dockbarNotificationsList = A.one('.dockbar-user-notifications .dockbar-user-notifications-container .user-notifications-list');

				return instance._dockbarNotificationsList ;
			},

			_getFullViewNotificationsList: function() {
				var instance = this;

				if (instance._fullViewNotificationsList) {
					return instance._fullViewNotificationsList;
				}

				instance._fullViewNotificationsList = A.one('.user-notifications-list-container .user-notifications-list');

				return instance._fullViewNotificationsList;
			},

			_getRenderURL: function(mvcPath, filter, start, end, fullView, menuOpen) {
				var instance = this;

				var portletURL = new Liferay.PortletURL.createURL(instance._baseRenderURL);

				portletURL.setParameter('mvcPath', mvcPath);

				if (filter) {
					portletURL.setParameter('filter', filter);
				}

				if (start) {
					portletURL.setParameter('start', start);
				}

				if (end) {
					portletURL.setParameter('end', end);
				}

				if (fullView) {
					portletURL.setParameter('fullView', fullView);
				}

				if (menuOpen) {
					portletURL.setParameter('menuOpen', menuOpen);
				}

				portletURL.setWindowState('exclusive');

				return portletURL.toString();
			},

			_getResourceURL: function(resourceId) {
				var instance = this;

				var portletURL = new Liferay.PortletURL.createURL(instance._baseResourceURL);

				portletURL.setResourceId(resourceId);

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

			_markAsRead: function(event, fullView, markAllAsRead) {
				event.preventDefault();

				var instance = this;

				var currentRow;

				var currentTarget = event.currentTarget;

				var loadingRow = A.Node.create('<div class="loading-animation"></div>');

				if (!markAllAsRead) {
					currentRow = currentTarget.ancestor('.user-notification');
					currentRow.hide().placeAfter(loadingRow);
				}

				A.io.request(
					currentTarget.attr('href'),
					{
						after: {
							success: function() {
								var response = this.get('responseData');

								if (response) {
									if (!markAllAsRead) {
										currentRow.remove();
										loadingRow.remove();
									}

									if (response.success) {
										instance._updateNotifications(fullView, markAllAsRead);
									}
								}
							}
						},
						dataType: 'JSON'
					}
				);
			},

			_onPollerUpdate: function(response) {
				var instance = this;

				instance._updateDockbarNotificationsCount(response.newUserNotificationsCount, response.timestamp, response.unreadUserNotificationsCount);
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

			_setDelivered: function() {
				var instance = this;

				A.io.request(instance._getActionURL('setDelivered'));
			},

			_updateDockbarNotificationsCount: function(newUserNotificationsCount, timestamp, unreadUserNotificationsCount) {
				var instance = this;

				if (!instance._previousTimestamp || (instance._previousTimestamp < timestamp)) {
					instance._previousTimestamp = timestamp;

					var dockbarUserNotificationsCount = A.one('.dockbar-user-notifications .user-notifications-count');

					if (dockbarUserNotificationsCount) {
						dockbarUserNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

						dockbarUserNotificationsCount.setHTML(unreadUserNotificationsCount);
					}
				}
			},

			_updateFullViewNotificationsCount: function(filter, unreadUserNotificationsCount) {
				if (filter == 'unread') {
					var userNotificationsSidebar = A.one('.user-notifications-sidebar');

					if (userNotificationsSidebar) {
						var unreadCount = userNotificationsSidebar.one('.unread .count');

						if (unreadCount) {
							unreadCount.setHTML(unreadUserNotificationsCount);
						}
					}
				}
			},

			_updateNotifications: function(fullView, markAllAsRead) {
				var instance = this;

				A.io.request(
					instance._getResourceURL('notifcationsCount'),
					{
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response.success) {
									if (!fullView) {
										instance.renderNotificationsList(instance._getDockbarNotificationsList(), instance._dockbarNotificationsURL);
									}

									if (instance._unread || ((typeof(instance._allNotifications) == 'undefined') && (typeof(instance._unread) == 'undefined'))) {
										if (instance._lastPage && (markAllAsRead || (instance._currentPageNotificationEventsCount == 1))) {
											instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._previousPageNotificationsURL);
										}
										else {
											instance.renderNotificationsList(instance._getFullViewNotificationsList(),  instance._fullViewNotificationsURL);
										}
									}

									instance._updateNotificationsCount(response['timestamp'], response['newUserNotificationsCount'], response['unreadUserNotificationsCount']);
								}
							}
						},
						dataType: 'JSON'
					}
				);
			},

			_updateNotificationsCount: function(timestamp, newUserNotificationsCount, unreadUserNotificationsCount) {
				var instance = this;

				instance._updateDockbarNotificationsCount(newUserNotificationsCount, timestamp, unreadUserNotificationsCount);
				instance._updateFullViewNotificationsCount('unread', unreadUserNotificationsCount);
			},

			_viewNotification: function(event) {
				var instance = this;

				var currentTarget = event.currentTarget;

				var uri = currentTarget.attr('data-href');

				var markAsReadURL = currentTarget.attr('data-markAsReadURL');

				if (markAsReadURL) {
					A.io.request(
						markAsReadURL,
						{
							after: {
								success: function() {
									var responseData = this.get('responseData');

									if (responseData.success) {
										var userNotification = currentTarget.ancestor('.user-notification');

										if (userNotification) {
											userNotification.removeClass('unread');

											var read = userNotification.one('.content .read');

											if (read) {
												read.setHTML(Liferay.Language.get('read'));
											}

											instance._redirect(uri);
										}
									}
								}
							},
							dataType: 'JSON'
						}
					);
				}
				else {
					var userNotification = currentTarget.ancestor('.user-notification');

					if (userNotification) {
						instance._redirect(uri);
					}
				}
			}
		};
	}
);