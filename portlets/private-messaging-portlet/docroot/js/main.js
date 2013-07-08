AUI.add(
	'liferay-plugin-privatemessaging',
	function(A) {
		var Lang = A.Lang;

		var APluginIO = A.Plugin.IO;

		var STR_CLICK = 'click';

		var STR_JSON = 'json';

		var STR_POST = 'POST';

		var PrivateMessaging = A.Component.create(
			{
				ATTRS: {
					baseActionURL: {
						validator: Lang.isString
					},

					portletId: {
						validator: Lang.isString
					}
				},

				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._userThreadsContainer = instance.byId('userThreadsSearchContainer');

						instance._privateMessagingContainer = instance.byId('privateMessagingContainer');

						instance._eventHandles = [];

						instance._bindUI();
					},

					destructor: function() {
						var instance = this;

						(new A.EventHandle(instance._eventHandles)).detach();
					},

					deleteMessages: function(mbThreadIds) {
						var instance = this;

						instance._sendRequest(instance._getActionURL('deleteMessages').toString(), mbThreadIds);
					},

					markMessagesAsRead: function(mbThreadIds) {
						var instance = this;

						instance._sendRequest(instance._getActionURL('markMessagesAsRead').toString(), mbThreadIds);
					},

					markMessagesAsUnread: function(mbThreadIds) {
						var instance = this;

						instance._sendRequest(instance._getActionURL('markMessagesAsUnread').toString(), mbThreadIds);
					},

					_bindCheckAllMessages: function() {
						var instance = this;

						var checkAllNode = instance._privateMessagingContainer.one('.check-all');

						if (checkAllNode) {
							instance._eventHandles.push(
								checkAllNode.on(
									STR_CLICK,
									function(event) {
										var checkBox = event.target;

										var privateMessages = instance._privateMessagingContainer.all('input[type=checkbox]');

										privateMessages.set('checked', checkBox.get('checked'));
									},
									instance
								)
							);
						}

						if (instance._userThreadsContainer) {
							instance._eventHandles.push(
								instance._userThreadsContainer.delegate(
									STR_CLICK,
									function(event) {
										var checkBox = event.target;

										Liferay.Util.updateCheckboxValue(checkBox);

										Liferay.Util.checkAllBox(
											instance._userThreadsContainer,
											instance.get('namespace') + 'mbThreadCheckbox',
											'.check-all'
										);
									},
									'input[type=checkbox]'
								)
							);
						}
					},

					_bindCreateMessage: function() {
						var instance = this;

						var newMessageNode = instance.byId('newMessage');

						if (newMessageNode) {
							instance._eventHandles.push(
								newMessageNode.on(
									STR_CLICK,
									function() {
										instance._newMessage();
									},
									instance
								)
							);
						}
					},

					_bindDeleteMessage: function() {
						var instance = this;

						var deleteMessageNode = instance.byId('deleteMessage');

						if (deleteMessageNode) {
							instance._eventHandles.push(
								deleteMessageNode.on(
									STR_CLICK,
									function(event) {
										if (!confirm(Liferay.Language.get('are-your-sure-you-want-to-delete-the-message'))) {
											event.preventDefault();
										}
									},
									instance
								)
							);
						}
					},

					_bindDeleteMessages: function() {
						var instance = this;

						var deleteMessagesNode = instance.byId('deleteMessages');

						if (deleteMessagesNode) {
							instance._eventHandles.push(
								deleteMessagesNode.on(
									STR_CLICK,
									function(event) {
										var mbThreadIds = instance._getSelectedMessageIds();

										if (mbThreadIds.length) {
											if (confirm(Liferay.Language.get('are-your-sure-you-want-to-delete-the-selected-messages'))) {
												instance.deleteMessages(mbThreadIds);
											}
										}
									},
									instance
								)
							);
						}
					},

					_bindMarkMessageRead: function() {
						var instance = this;

						var markMessageReadNode = instance.byId('markMessageAsRead');

						if (markMessageReadNode) {
							instance._eventHandles.push(
								markMessageReadNode.on(
									STR_CLICK,
									function(event) {
										if (confirm(Liferay.Language.get('are-your-sure-you-want-to-mark-the-message-as-read'))) {
											event.preventDefault();
										}
									},
									instance
								)
							);
						}
					},

					_bindMarkMessagesRead: function() {
						var instance = this;

						var markMessagesReadNode = instance.byId('markMessagesAsRead');

						if (markMessagesReadNode) {
							instance._eventHandles.push(
								markMessagesReadNode.on(
									STR_CLICK,
									function(event) {
										event.preventDefault();

										var mbThreadIds = instance._getSelectedMessageIds();

										if (mbThreadIds.length) {
											if (confirm(Liferay.Language.get('are-your-sure-you-want-to-mark-the-selected-messages-as-read'))) {
												instance.markMessagesAsRead(mbThreadIds);
											}
										}
									},
									instance
								)
							);
						}
					},

					_bindMarkMessageUnread: function() {
						var instance = this;

						var markMessageUnread = instance.byId('markMessageAsUnread');

						if (markMessageUnread) {
							instance._eventHandles.push(
								markMessageUnread.on(
									STR_CLICK,
									function(event) {
										if (!confirm(Liferay.Language.get('are-your-sure-you-want-to-mark-the-message-as-unread'))) {
											event.preventDefault();
										}
									},
									instance
								)
							);
						}
					},

					_bindMarkMessagesUnread: function() {
						var instance = this;

						var markMessagesUnread = instance.byId('markMessagesAsUnread');

						if (markMessagesUnread) {
							instance._eventHandles.push(
								markMessagesUnread.on(
									STR_CLICK,
									function(event) {
										event.preventDefault();

										var mbThreadIds = instance._getSelectedMessageIds();

										if (mbThreadIds.length) {
											if (confirm(Liferay.Language.get('are-your-sure-you-want-to-mark-the-selected-messages-as-unread'))) {
												instance.markMessagesAsUnread(mbThreadIds);
											}
										}
									},
									instance
								)
							);
						}
					},

					_bindUI: function() {
						var instance = this;

						if (instance._privateMessagingContainer) {
							instance._bindCreateMessage();

							instance._bindDeleteMessages();

							instance._bindDeleteMessage();

							instance._bindMarkMessageRead();

							instance._bindMarkMessagesRead();

							instance._bindMarkMessageUnread();

							instance._bindMarkMessagesUnread();

							instance._bindCheckAllMessages();
						}
					},

					_getActionURL: function(name) {
						var instance = this;

						var windowState = 'NORMAL';

						if (themeDisplay.isStateMaximized()) {
							windowState = 'MAXIMIZED';
						}

						var portletURL = new Liferay.PortletURL.createURL(instance.get('baseActionURL'));

						portletURL.setParameter('javax.portlet.action', name);
						portletURL.setPortletId(instance.get('portletId'));
						portletURL.setWindowState(windowState);

						return portletURL;
					},

					_getSelectedMessageIds: function() {
						var instance = this;

						var mbThreadIds = [];

						instance._userThreadsContainer.all('input[type=checkbox]:checked').each(
							function(item, index, collection) {
								var mbThreadId = item.getAttribute('data-mbThreadId');

								if (mbThreadId) {
									mbThreadIds.push(mbThreadId);
								}
							}
						);

						return mbThreadIds;
					},

					_newMessage: function(mbThreadId) {
						var instance = this;

						var redirectURL = new Liferay.PortletURL.createRenderURL();

						redirectURL.setWindowState('NORMAL');

						var portletURL = new Liferay.PortletURL.createRenderURL();

						portletURL.setPortletId(instance.get('portletId'));
						portletURL.setWindowState('EXCLUSIVE');

						portletURL.setParameter('mvcPath', '/new_message.jsp');
						portletURL.setParameter('redirect', redirectURL.toString());

						var messageDialog = new A.Modal(
							{
								centered: true,
								constrain: true,
								cssClass: 'private-messaging-portlet',
								destroyOnHide: true,
								headerContent: Liferay.Language.get('new-message'),
								height: 600,
								modal: true,
								plugins: [Liferay.WidgetZIndex],
								width: 600
							}
						).plug(
							APluginIO,
							{
								data: {
									mbThreadId: mbThreadId
								},
								uri: portletURL.toString()
							}
						).render();
					},

					_sendRequest: function(request, mbThreadIds) {
						var instance = this;

						A.io.request(
							request,
							{
								data: {
									mbThreadIds: mbThreadIds
								},
								on: {
									success: function(event, id, obj) {
										A.config.win.location = themeDisplay.getLayoutURL();
									}
								}
							}
						);
					}
				}
			}
		);

		Liferay.PrivateMessaging = PrivateMessaging;
	},
	'',
	{
		requires: ['aui-base', 'aui-io-deprecated', 'aui-modal', 'liferay-node', 'liferay-portlet-base', 'liferay-portlet-url', 'liferay-widget-zindex']
	}
);