AUI().use(
	'aui-base',
	'aui-io-plugin-deprecated',
	'liferay-menu-toggle',
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
				instance._delta = config.delta;
				instance._end = config.end.toString();
				instance._filter = config.filter;
				instance._start = config.start.toString();
				instance._userNotificationEventIds = config.userNotificationEventIds;
				instance._userNotificationEventsCount = config.userNotificationEventsCount;

				instance._nextStart = (config.start + config.delta).toString();
				instance._nextEnd = (config.end + config.delta).toString();
				instance._previousStart = (config.start - config.delta).toString();
				instance._previousEnd = (config.end - config.delta).toString();

				instance._bindUI();

				instance._createMarkAllAsReadNode();

				instance._updateFullviewNotificationsCount(instance._filter, instance._userNotificationEventsCount);
			},

			initDockbarNotifications: function(config) {
				var instance = this;

				instance._baseActionURL = config.baseActionURL;
				instance._baseRenderURL = config.baseRenderURL;
				instance._portletKey = config.portletKey;

				instance._createMenuToggle();

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

			_bindDismissDelegation: function(fullView, notificationsList, markAllAsRead, selector) {
				var instance = this;

				if (notificationsList) {
					notificationsList.delegate(
						'click',
						function(event) {
							instance._dismissNotifications(event, fullView, markAllAsRead);
						},
						selector
					);
				}
			},

			_bindDismissDockbarNotification: function() {
				var instance = this;

				instance._bindDismissDelegation(false, this._getDockbarNotificationsList(), false, '.user-notification .btn-action');
			},

			_bindDismissDockbarNotifications: function() {
				var instance = this;

				instance._bindDismissDelegation(false, this._getDockbarNotificationsList(), true, '.dismiss-notifications');
			},

			_bindDismissFullviewNotification: function() {
				var instance = this;

				instance._bindDismissDelegation(true, this._getFullviewNotificationsList(), false, '.user-notification .btn-action');
			},

			_bindDismissFullviewNotifications: function() {
				var instance = this;

				instance._bindDismissDelegation(true, this._getFullviewNotificationsList(), true, '.dismiss-notifications');
			},

			_bindMenu: function(menu, uri) {
				var instance = this;

				if (menu) {
					menu.on(
						'click',
						function(event) {
							var userNotificationsList = instance._getFullviewNotificationsList();

							var userNotificationsSidebar = instance._getUserNotificationsSidebar();

							if (userNotificationsList) {
								instance.renderNotificationsList(userNotificationsList, uri);

								A.io.request(instance._getActionURL('setDelivered'));

								if (userNotificationsSidebar) {
									userNotificationsSidebar.all('.nav a').removeClass('selected');
								}

								menu.addClass('selected');
							}
						}
					);
				}
			},

			_bindNavBar: function() {
				var instance = this;

				var userNotificationsSidebar = instance._getUserNotificationsSidebar();

				if (userNotificationsSidebar) {
					var unreadNav = userNotificationsSidebar.one('.unread');

					instance._bindMenu(unreadNav, instance._getRenderURL('/notifications/view_entries.jsp', 'unread'));

					var allNotificationsNav = userNotificationsSidebar.one('.all-notifications');

					instance._bindMenu(allNotificationsNav, instance._getRenderURL('/notifications/view_entries.jsp'));

					var manageNav = userNotificationsSidebar.one('.manage');

					instance._bindMenu(manageNav, instance._getRenderURL('/notifications/configuration.jsp'));
				}
			},

			_bindNextPageNotifications: function() {
				var instance = this;

				instance._bindPaginateDelegation(this._getFullviewNotificationsList(), false, '.message .next a');
			},

			_bindPaginateDelegation: function(userNotificationsList, previous, selector) {
				var instance = this;

				if (userNotificationsList) {
					userNotificationsList.delegate(
						'click',
						function(event) {
							instance._paginateNotifications(event, previous);
						},
						selector
					);
				}
			},

			_bindPreviousPageNotifications: function() {
				var instance = this;

				instance._bindPaginateDelegation(this._getFullviewNotificationsList(), true, '.message .previous a');
			},

			_bindUI: function() {
				var instance = this;

				instance._bindDismissDockbarNotification();

				instance._bindDismissDockbarNotifications();

				instance._bindDismissFullviewNotification();

				instance._bindDismissFullviewNotifications();

				instance._bindNavBar();

				instance._bindNextPageNotifications();

				instance._bindPreviousPageNotifications();

				instance._bindViewDockbarNotification();

				instance._bindViewFullviewNotification();
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

			_bindViewDockbarNotification: function() {
				var instance = this;

				instance._bindViewDelegation(this._getDockbarNotificationsList(), '.user-notification .user-notification-link');
			},

			_bindViewFullviewNotification: function() {
				var instance = this;

				instance._bindViewDelegation(this._getFullviewNotificationsList(), '.user-notification .user-notification-link');
			},

			_createMarkAllAsReadNode: function() {
				var instance = this;

				if (instance._userNotificationEventsCount > 0 && parseInt(instance._start) == 0) {
					var nodeHTML = '<a class="dismiss-notifications" href="' + instance._getDismissNotificationURL(instance._userNotificationEventIds) + '">' +
							A.Lang.sub(Liferay.Language.get('mark-all-as-read-x'), [instance._currentPageNotificationEventsCount]) + '</a>';

					var dockbarDismiss = A.one('.dropDownMarkAllAsRead');

					if (dockbarDismiss) {
						dockbarDismiss.get('parentNode').replaceChild(A.Node.create(nodeHTML), dockbarDismiss);
					}

					var fullviewDismiss = A.one('.fullViewMarkAllAsRead');

					if (fullviewDismiss) {
						fullviewDismiss.get('parentNode').replaceChild(A.Node.create(nodeHTML), fullviewDismiss);
					}
				}
			},

			_createMenuToggle: function() {
				var instance = this;

				new Liferay.MenuToggle(
					{
						after: {
							openChange: function(event) {
								if (event.newVal) {
									instance.renderNotificationsList(instance._getDockbarNotificationsList(), instance._getUnreadURL());

									A.io.request(instance._getActionURL('setDelivered'));

									var dockbarUserNotifications = instance._getDockbarUserNotifications();

									if (dockbarUserNotifications) {
										var dockbarUserNotificationsCount = dockbarUserNotifications.one('.user-notifications-count');

										if (dockbarUserNotificationsCount) {
											dockbarUserNotificationsCount.removeClass('alert');
										}
									}
								}
							}
						},
						content: instance._getDockbarUserNotifications(),
						toggleTouch: true,
						trigger: '.dockbar-user-notifications .dropdown-toggle'
					}
				);
			},

			_dismissNotifications: function(event, fullView, markAllAsRead) {
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
							success: function(event) {
								var response = this.get('responseData');

								if (response.success) {

									if (!markAllAsRead) {
										currentRow.remove();
										loadingRow.remove();
									}

									instance._getNotificationsCount(fullView);
								}
							}
						},
						dataType: 'json'
					}
				);
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

			_getDismissNotificationURL: function(userNotificationEventIds) {
				var instance = this;

				return instance._getActionURL('markAllAsRead', userNotificationEventIds);
			},

			_getDockbarNotificationsList: function() {
				var instance = this;

				if (instance._dockbarNotificationsList) {
					return instance._dockbarNotificationsList;
				}

				instance._dockbarNotificationsList = A.one('.dockbar-user-notifications .user-notifications-list');

				return instance._dockbarNotificationsList ;
			},

			_getDockbarURL: function() {
				var instance = this;

				return instance._getRenderURL('/notifications/view_entries.jsp', 'unread', null, null, 'true', 'false');
			},

			_getDockbarUserNotifications: function() {
				var instance = this;

				if (instance._dockbarUserNotifications) {
					return instance._dockbarUserNotifications;
				}

				instance._dockbarUserNotifications = A.one('.dockbar-user-notifications');

				return instance._dockbarUserNotifications;
			},

			_getFullviewNotificationsList: function() {
				var instance = this;

				if (instance._fullviewNotificationsList) {
					return instance._fullviewNotificationsList;
				}

				instance._fullviewNotificationsList = A.one('.user-notifications-list-container .user-notifications-list');

				return instance._fullviewNotificationsList;
			},

			_getFullviewURL: function() {
				var instance = this;

				return instance._getRenderURL('/notifications/view_entries.jsp', instance._filter, instance._start, instance._end, 'false', 'true');
			},

			_getNextPageURL: function() {
				var instance = this;

				return instance._getRenderURL('/notifications/view_entries.jsp', instance._filter, instance._nextStart, instance._nextEnd, null, 'true');
			},

			_getNotificationsCount: function(fullView) {
				var instance = this;

				A.io.request(
					instance._getNotificationsCountURL(),
					{
						on: {
							success: function(event) {
								var response = this.get('responseData');

								if (response.success) {
									instance._updateNotificationsCount(response["newUserNotificationsCount"], response["unreadUserNotificationsCount"]);

									var userNotificationsList = instance._getFullviewNotificationsList();

									if (fullView) {
										instance.renderNotificationsList(userNotificationsList, instance._getFullviewURL());
									}
									else {
										if (userNotificationsList) {
											instance.renderNotificationsList(userNotificationsList, instance._getFullviewURL());
										}

										instance.renderNotificationsList(instance._getDockbarNotificationsList(), instance._getDockbarURL());
									}
								}
							}
						},
						dataType: 'json'
					}
				);
			},

			_getNotificationsCountURL: function() {
				var instance = this;

				return instance._getResourceURL('notifcationsCount');
			},

			_getPreviousPageURL: function() {
				var instance = this;

				return instance._getRenderURL('/notifications/view_entries.jsp', instance._filter, instance._previousStart, instance._previousEnd, null, 'true');
			},

			_getRenderURL: function(mvcPath, filter, start, end, dockbar, fullview) {
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

				if (dockbar) {
					portletURL.setParameter('dockbar', dockbar);
				}

				if (fullview) {
					portletURL.setParameter('fullView', fullview);
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

			_getUnreadURL: function() {
				var instance = this;

				return instance._getRenderURL('/notifications/view_entries.jsp', 'unread', null, null, 'true', 'false');
			},

			_getUserNotificationsSidebar: function() {
				var instance = this;

				if (instance._userNotificationSidebar) {
					return instance._userNotificationSidebar;
				}

				instance._userNotificationSidebar = A.one('.user-notifications-sidebar');

				return instance._userNotificationSidebar;
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

			_onPollerUpdate: function(response) {
				var instance = this;

				instance._updateDockbarNotificationsCount(response.newUserNotificationsCount, response.unreadUserNotificationsCount);
			},

			_openWindow: function(uri) {
				if (uri.match('p_p_state=maximized') || uri.match('p_p_state=pop_up') || uri.match('p_p_state=exclusive')) {
					return true;
				}

				return false;
			},

			_paginateNotifications: function(event, previous) {
				event.preventDefault();

				var instance = this;

				var currentTarget = event.currentTarget;

				var userNotificationsList = currentTarget.ancestor('.user-notifications-list-container .user-notifications-list');

				if (userNotificationsList) {
					if (previous) {
						instance.renderNotificationsList(instance._getFullviewNotificationsList(), instance._getPreviousPageURL());
					}
					else {
						instance.renderNotificationsList(instance._getFullviewNotificationsList(), instance._getNextPageURL());
					}
				}
			},

			_redirect: function(uri, openDialog) {
				if (openDialog === 'false') {
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
			},

			_updateDockbarNotificationsCount: function(newUserNotificationsCount, unreadUserNotificationsCount) {
				var instance = this;

				var dockbarUserNotifications = instance._getDockbarUserNotifications();

				if (dockbarUserNotifications) {
					var dockbarUserNotificationsCount = dockbarUserNotifications.one('.user-notifications-count');

					if (dockbarUserNotificationsCount) {
						dockbarUserNotificationsCount.toggleClass('alert', (newUserNotificationsCount > 0));

						dockbarUserNotificationsCount.setHTML(unreadUserNotificationsCount);
					}
				}
			},

			_updateFullviewNotificationsCount: function(filter, unreadUserNotificationsCount) {
				var instance = this;

				if (filter == 'unread') {
					var userNotificationsSidebar = instance._getUserNotificationsSidebar();

					if (userNotificationsSidebar) {
						var unreadCount = userNotificationsSidebar.one('.unread .count');

						if (unreadCount) {
							unreadCount.setHTML(unreadUserNotificationsCount);
						}
					}
				}
			},

			_updateNotificationsCount: function(newUserNotificationsCount, unreadUserNotificationsCount) {
				var instance = this;

				instance._updateDockbarNotificationsCount(newUserNotificationsCount, unreadUserNotificationsCount);
				instance._updateFullviewNotificationsCount('unread', unreadUserNotificationsCount);
			},

			_viewNotification: function(event) {
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
								dataType: 'JSON'
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
			}
		};
	}
);