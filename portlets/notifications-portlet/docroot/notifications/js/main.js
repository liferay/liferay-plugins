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
				instance._dockbarNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', 'dockbar', null, null, 'false', 'true');
				instance._dockbarViewDelta = config.dockbarViewDelta;
				instance._fullViewNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, config.start.toString(), config.end.toString(), 'true');
				instance._lastPage = config.userNotificationEventsCount <= config.end;
				instance._namespace = config.namespace;
				instance._nextPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, (config.start + config.delta).toString(), (config.end + config.delta).toString(), 'true');

				if ((config.end - config.delta) <= 0) {
					instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, '0', config.delta.toString(), 'true');
				}
				else {
					instance._previousPageNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', config.filter, (config.start - config.delta).toString(), (config.end - config.delta).toString(), 'true');
				}

				instance._totalMarkAsReadableCount = config.unreadNonActionableUserNotificationsCount;

				instance._createMarkAllAsReadNode(config);

				instance._bindUI();

				instance._updateFullViewNotificationsCount(config.unreadActionableUserNotificationsCount, config.unreadNonActionableUserNotificationsCount);
			},

			initDockbarNotifications: function(config) {
				var instance = this;

				instance._baseActionURL = config.baseActionURL;
				instance._baseRenderURL = config.baseRenderURL;
				instance._portletKey = config.portletKey;

				instance._dockbarNotificationsURL = instance._getRenderURL('/notifications/view_entries.jsp', 'dockbar', null, null, 'false', 'true');

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

			_bindNavMenu: function(menu, uri, allNotifications, unreadActionable, unreadNonActionable) {
				var instance = this;

				if (menu) {
					menu.on(
						'click',
						function() {
							instance._allNotifications = allNotifications;
							instance._unreadActionable = unreadActionable;
							instance._unreadNonActionable = unreadNonActionable;

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
						A.Lang.sub(Liferay.Language.get('mark-all-as-read-x-of-x'), [config.currentPageNotificationEventsCount, instance._totalMarkAsReadableCount]) + '</a><hr class="separator" />';

					var dockbarMarkAllAsRead = A.one('.dockbarMarkAllAsRead');

					if (dockbarMarkAllAsRead) {
						dockbarMarkAllAsRead.get('parentNode').replaceChild(A.Node.create(nodeHTML), dockbarMarkAllAsRead);

						if (config.userNotificationEventIds == '') {
							var dockbarMarkAsReadNode = A.one('.dockbar-user-notifications .mark-all-as-read');

							if (dockbarMarkAsReadNode) {
								dockbarMarkAsReadNode.addClass('hide');
							}
						}
					}

					var fullViewMarkAllAsRead = A.one('.fullViewMarkAllAsRead');

					if (fullViewMarkAllAsRead) {
						fullViewMarkAllAsRead.get('parentNode').replaceChild(A.Node.create(nodeHTML), fullViewMarkAllAsRead);
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
									instance._setDelivered();

									instance.renderNotificationsList(instance._getDockbarNotificationsList(), instance._dockbarNotificationsURL);

									if (instance._allNotifications || ((typeof(instance._allNotifications) == 'undefined') && (typeof(instance._unreadActionable) == 'undefined') && (typeof(instance._unreadNonActionable) == 'undefined'))) {
										instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._getRenderURL('/notifications/view_entries.jsp'));
									}
									else if (instance._unreadActionable) {
										instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._getRenderURL('/notifications/view_entries.jsp', 'unread-actionable'));
									}
									else if (instance._unreadNonActionable) {
										instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._getRenderURL('/notifications/view_entries.jsp', 'unread-nonactionable'));
									}

									var dockbarUserNotificationsCount = A.one('.dockbar-user-notifications .user-notifications-count');

									if (dockbarUserNotificationsCount) {
										dockbarUserNotificationsCount.toggleClass('alert', false);
									}
								}
							}
						},
						content: A.one('.dockbar-user-notifications'),
						toggleTouch: true,
						trigger: '.dockbar-user-notifications .dropdown-toggle'
					}
				);
			},

			_deleteNotification: function(target) {
				var instance = this;

				var deleteNode = target.ancestor('.user-notification-delete');

				if (deleteNode) {

					if (instance._hasRequestSent(deleteNode, deleteNode.getAttribute('data-deleteURL'))) {
						return;
					}

					A.io.request(deleteNode.getAttribute('data-deleteURL'));
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

				instance._dockbarNotificationsList = A.one('.dockbar-user-notifications .user-notifications-list');

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

			_getRenderURL: function(mvcPath, filter, start, end, fullView) {
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

				portletURL.setWindowState('exclusive');

				return portletURL.toString();
			},

			_getResourceURL: function(resourceId, dockbarViewDelta) {
				var instance = this;

				var portletURL = new Liferay.PortletURL.createURL(instance._baseResourceURL);

				if (dockbarViewDelta) {
					portletURL.setParameter('dockbarViewDelta', dockbarViewDelta);
				}

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

				if (instance._hasRequestSent(currentTarget, currentTarget.attr('href'))) {
					return;
				}

				var dockbarNotificationsList;

				var fullViewNotificationsList;

				var loadingRow = A.Node.create('<div class="loading-animation"></div>');

				if (!markAllAsRead) {
					currentRow = currentTarget.ancestor('.user-notification');
					currentRow.hide().placeAfter(loadingRow);
				}
				else {
					dockbarNotificationsList = A.one('.dockbar-user-notifications .nonactionable-user-notifications-list');

					if (dockbarNotificationsList) {
						dockbarNotificationsList.hide().placeAfter(loadingRow);
					}

					fullViewNotificationsList = A.one('.user-notifications-list-container .nonactionable-user-notifications-list');

					if (fullViewNotificationsList) {
						fullViewNotificationsList.hide().placeAfter(loadingRow);
					}
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
									else {
										if (dockbarNotificationsList) {
											dockbarNotificationsList.remove();
										}
										if (fullViewNotificationsList) {
											fullViewNotificationsList.remove();
										}

										loadingRow.remove();
									}

									if (response.success) {
										instance._deleteNotification(currentTarget);

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

				instance._updateDockbarNotificationsCount(response.newUserNotificationsCount, response.timestamp, response.unreadNonActionableUserNotificationsCount, response.unreadUserNotificationsCount);
			},

			_openWindow: function(uri) {
				if (uri.match('p_p_state=maximized') || uri.match('p_p_state=pop_up') || uri.match('p_p_state=exclusive')) {
					return true;
				}

				return false;
			},

			_redirect: function(uri, openDialog) {
				if (uri) {
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
				}
			},

			_setDelivered: function() {
				var instance = this;

				A.io.request(instance._getActionURL('setDelivered'));
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
			},

			_updateDockbarNotificationsList: function() {
				var instance = this;

				var userNotifications =  A.one('.dockbar-user-notifications');

				if (!userNotifications.hasClass('open')) {
					userNotifications.addClass('open');
				}

				A.io.request(
					instance._getResourceURL('userNotificationEvents', instance._dockbarViewDelta),
					{
						dataType: 'JSON',
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response) {
									var dockbarNotificationsList = A.one('.dockbar-user-notifications .dockbar-user-notifications-list');

									if (dockbarNotificationsList) {
										var dockbarMarkAllAsRead = A.one('.dockbar-user-notifications .mark-all-as-read');

										if (response.noResult) {
											dockbarNotificationsList.empty();

											var message = A.one('.dockbar-user-notifications .message');

											if (message.hasClass('hide')) {
												message.removeClass('hide');
											}

											if (dockbarMarkAllAsRead) {
												if (!dockbarMarkAllAsRead.hasClass('hide')) {
													dockbarMarkAllAsRead.addClass('hide');
												}
											}

											var separator = dockbarMarkAllAsRead.next('.separator');

											if (separator) {
												separator.addClass('hide');
											}
										}
										else {
											if (response['markAsReadCount'] && response['markAsReadCount'] > 0) {
												if (dockbarMarkAllAsRead.hasClass('hide')) {
													dockbarMarkAllAsRead.removeClass('hide');
												}

												dockbarMarkAllAsRead.setAttribute('href', instance._getActionURL('markAllAsRead', response['userNotificationEventIds']));

												var html = A.Lang.sub(Liferay.Language.get('mark-all-as-read-x-of-x'), [response['markAsReadCount'].toString(), instance._totalMarkAsReadableCount]);

												dockbarMarkAllAsRead.setHTML(html);
											}
											else {
												if (!dockbarMarkAllAsRead.hasClass('hide')) {
													dockbarMarkAllAsRead.addClass('hide');
												}
											}

											var entries = [];

											var jsonArray = response['entries'];

											if (jsonArray) {
												for (var i = 0; i < jsonArray.length; i++) {
													entries.push(jsonArray[i]);
												}
											}

											entries = entries.join('');

											dockbarNotificationsList.empty();

											dockbarNotificationsList.append(entries);
										}
									}
								}
							}
						}
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

			_updateNotifications: function(fullView, markAllAsRead) {
				var instance = this;

				A.io.request(
					instance._getResourceURL('notificationsCount'),
					{
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response.success) {
									if (!fullView) {
										instance._updateDockbarNotificationsList();
									}

									if (instance._unreadActionable || instance._unreadNonActionable || ((typeof(instance._allNotifications) == 'undefined') && (typeof(instance._unreadActionable) == 'undefined') && (typeof(instance._unreadNonActionable) == 'undefined'))) {
										if (instance._lastPage && (markAllAsRead || (instance._currentPageNotificationEventsCount == 1))) {
											instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._previousPageNotificationsURL);
										}
										else {
											instance.renderNotificationsList(instance._getFullViewNotificationsList(), instance._fullViewNotificationsURL);
										}
									}

									instance._updateDockbarNotificationsCount(response['newUserNotificationsCount'], response['timestamp'], response['unreadNonActionableUserNotificationsCount'], response['unreadUserNotificationsCount']);
									instance._updateFullViewNotificationsCount(response['unreadActionableUserNotificationsCount'], response['unreadNonActionableUserNotificationsCount']);
								}
							}
						},
						dataType: 'JSON'
					}
				);
			},

			_viewNotification: function(event) {
				var instance = this;

				var currentTarget = event.currentTarget;

				var openDialog = currentTarget.attr('data-openDialog');

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
		};
	}
);