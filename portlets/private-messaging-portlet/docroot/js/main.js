AUI.add(
	'liferay-plugin-privatemessaging',
	function(A) {
		Liferay.PrivateMessaging = {
			init: function(params) {
				var instance = this;

				instance.namespace = params.namespace;

				instance.privateMessagingContainer = A.one('#p_p_id' + params.namespace + ' .private-messaging-container');

				if (instance.privateMessagingContainer) {
					instance._assignEvents();
				}
			},

			deleteMessages: function(mbThreadIds) {
				var instance = this;

				A.io.request(
					instance._getActionURL('deleteMessages').toString(),
					{
						data: {
							mbThreadIds: mbThreadIds
						},
						dataType: 'json',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								window.location = themeDisplay.getLayoutURL();
							}
						}
					}
				);
			},

			markMessagesAsRead: function(mbThreadIds) {
				var instance = this;

				A.io.request(
					instance._getActionURL('markMessagesAsRead').toString(),
					{
						data: {
							mbThreadIds: mbThreadIds
						},
						dataType: 'json',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								window.location = themeDisplay.getLayoutURL();
							}
						}
					}
				);
			},

			markMessagesAsUnread: function(mbThreadIds) {
				var instance = this;

				A.io.request(
					instance._getActionURL('markMessagesAsUnread').toString(),
					{
						data: {
							mbThreadIds: mbThreadIds
						},
						dataType: 'json',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								window.location = themeDisplay.getLayoutURL();
							}
						}
					}
				);
			},

			newMessage: function(mbThreadId) {
				var instance = this;

				var portletURL = new Liferay.PortletURL.createResourceURL();

				portletURL.setParameter('mvcPath', '/new_message.jsp');
				portletURL.setPortletId('1_WAR_privatemessagingportlet');
				portletURL.setWindowState('EXCLUSIVE');

				new A.Dialog(
					{
						centered: true,
						cssClass: 'private-messaging-portlet',
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('new-message'),
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						data: {mbThreadId: mbThreadId},
						uri: portletURL.toString()
					}
				).render();
			},

			_assignEvents: function() {
				var instance = this;

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						instance.newMessage();
					},
					'.new-message'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						var mbThreadIds = instance._getSelectedMessageIds();

						instance.deleteMessages(mbThreadIds);
					},
					'.delete-messages'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						var mbThreadIds = instance._getSelectedMessageIds();

						instance.markMessagesAsRead(mbThreadIds);
					},
					'.mark-messages-as-read'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						var mbThreadIds = instance._getSelectedMessageIds();

						instance.markMessagesAsUnread(mbThreadIds);
					},
					'.mark-messages-as-unread'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						instance.privateMessagingContainer.all('input[type=checkbox]').each(
							function(item, index, collection) {
								item.set('checked', true);
							}
						);
					},
					'.select-all'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						instance.privateMessagingContainer.all('input[type=checkbox]').each(
							function(item, index, collection) {
								item.set('checked', false);
							}
						);
					},
					'.select-none'
				);
			},

			_getActionURL: function(name) {
				var instance = this;

				var windowState = 'NORMAL';

				if (themeDisplay.isStateMaximized()) {
					windowState = 'MAXIMIZED';
				}

				var portletURL = new Liferay.PortletURL.createActionURL();

				portletURL.setParameter('javax.portlet.action', name);
				portletURL.setPortletId('1_WAR_privatemessagingportlet');
				portletURL.setWindowState(windowState);

				return portletURL;
			},

			_getSelectedMessageIds: function() {
				var instance = this;

				var mbThreadIds = [];

				instance.privateMessagingContainer.all('input[type=checkbox]').each(
					function(item, index, collection) {
						var mbThreadId = item.getAttribute('data-mbThreadId');

						if (mbThreadId && item.get('checked')) {
							mbThreadIds.push(mbThreadId);
						}
					}
				);

				return mbThreadIds;
			}
		};
	},
	'',
	{
		requires: ['aui-base', 'aui-datatype', 'aui-dialog', 'aui-io', 'liferay-portlet-url']
	}
);