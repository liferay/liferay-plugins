AUI.add(
	'liferay-plugin-privatemessaging',
	function(A) {
		Liferay.PrivateMessaging = {
			init: function(params) {
				var instance = this;

				var namespace = params.namespace;

				instance.namespace = namespace;

				instance.newMessageURL = params.newMessageURL;
				instance.deleteMessagesURL = params.deleteMessagesURL;
				instance.markMessagesAsReadURL = params.markMessagesAsReadURL;
				instance.markMessagesAsUnreadURL = params.markMessagesAsUnreadURL;

				instance.checkAll = A.one('#' + namespace + 'checkAll');
				instance.userThreadsSearchContainer = A.one('#' + namespace + 'userThreadsSearchContainer');

				instance.privateMessagingContainer = A.one('#p_p_id' + namespace + ' .private-messaging-container');

				if (instance.privateMessagingContainer) {
					instance._assignEvents();
				}
			},

			deleteMessages: function(mbThreadIds) {
				var instance = this;

				A.io.request(
					instance.deleteMessagesURL,
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
					instance.markMessagesAsReadURL,
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
					instance.markMessagesAsUnreadURL,
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

				new A.Dialog(
					{
						align: Liferay.Util.Window.ALIGN_CENTER,
						cssClass: 'private-messaging-portlet',
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('new-message'),
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						data: {
							mbThreadId: mbThreadId
						},
						uri: instance.newMessageURL
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
						var checkBox = event.target;

						var privateMessages = instance.privateMessagingContainer.all('input[type=checkbox]');

						privateMessages.attr('checked', checkBox.get('checked'));
					},
					'.check-all'
				);

				instance.privateMessagingContainer.delegate(
					'click',
					function(event) {
						var checkBox = event.target;

						Liferay.Util.updateCheckboxValue(checkBox);

						Liferay.Util.checkAllBox(
							instance.userThreadsSearchContainer,
							instance.namespace + 'mbThreadCheckbox',
							instance.checkAll
						);
					},
					'.results-row input[type=checkbox]'
				);
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