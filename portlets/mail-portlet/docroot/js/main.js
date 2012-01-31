AUI.add(
	'liferay-plugin-mail',
	function(A) {
		Liferay.Mail = {
			init: function(params) {
				var instance = this;

				instance.namespace = params.namespace;

				instance.accountId = params.initialAccountId;
				instance.folderId = params.initialFolderId;

				instance.accountsContainer = A.one('#accountsContainer');
				instance.composeContainer = A.one('#composeContainer');
				instance.controlContainer = A.one('#controlContainer');
				instance.contentContainer = A.one('#contentContainer');
				instance.foldersContainer = A.one('#foldersContainer');
				instance.mailContainer = A.one('#mailContainer');
				instance.manageFoldersContainer = A.one('#manageFoldersContainer');
				instance.messageContainer = A.one('#messageContainer');
				instance.messagesContainer = A.one('#messagesContainer');

				instance._assignEvents();

				if (instance.accountId == 0) {
					return;
				}

				instance.loadAccount(instance.accountId, instance.folderId);

				Liferay.on('closePortlet', instance._pollStopMessages);
			},

			addAccount: function() {
				var instance = this;

				new A.Dialog(
					{
						centered: true,
						cssClass: 'mail-dialog',
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('add-account'),
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/add_account'
					}
				).render();
			},

			checkMessages: function(folderId) {
				var instance = this;

				if (instance.accountId <= 0) {
					return;
				}

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/check_messages',
					{
						data: {
							accountId: instance.accountId,
							folderId: folderId
						},
						dataType: 'json',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if ((responseData.status != 'success') || (responseData.value == 'false')) {
									return;
								}

								instance.loadFolders(instance.accountId);

								if (instance.folderId == folderId) {
									instance.loadMessages(folderId, instance.pageNumber, instance.orderByField, instance.orderByType, instance.keywords, true);
								}
							}
						}
					}
				);
			},

			deleteMessages: function(messageIds) {
				var instance = this;

				instance.setStatus('info', Liferay.Language.get('deleting-messages'));

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_messages',
					{
						data: {messageIds: messageIds},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function(event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								instance.setStatus(responseData.status, responseData.message);

								if (responseData.status == 'success') {
									instance.loadMessages(instance.folderId, instance.pageNumber, instance.orderByField, instance.orderByType, instance.keywords);
								}
							}
						}
					}
				);
			},

			editAccount: function(accountId) {
				var instance = this;

				var dialog = new A.Dialog(
					{
						centered: true,
						cssClass: 'mail-dialog',
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('edit-account'),
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						data: {accountId: accountId},
						uri: themeDisplay.getLayoutURL() + '/-/mail/edit_account'
					}
				).render();
			},

			flagMessages: function(flag, value, messageIds) {
				var instance = this;

				instance.setStatus('info', Liferay.Language.get('flagging-messages'));

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/flag_messages',
					{
						data: {
							flag: flag,
							messageIds: messageIds,
							value: value
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var responseData = this.get('responseData');

								instance.setStatus(responseData.status, responseData.message);

								instance.loadMessages(instance.folderId, instance.pageNumber, instance.orderByField, instance.orderByType, instance.keywords);
							}
						}
					}
				);
			},

			loadAccounts: function(accountId) {
				var instance = this;

				instance.accountsContainer.io.set('data', {accountId: accountId});

				instance.accountsContainer.io.start();
			},

			loadAccount: function(accountId, inboxFolderId) {
				var instance = this;

				instance._displayContainer(instance.messagesContainer);

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/password_saved',
					{
						data: {
							accountId: accountId,
							inboxFolderId: inboxFolderId
						},
						method: 'POST',
						on: {
							failure: function(event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								if (A.DataType.Boolean.parse(responseData)) {
									instance.accountId = accountId;
									instance.inboxFolderId = inboxFolderId;

									instance.loadFolders(accountId);
									instance.loadMessages(inboxFolderId, 1, 'sentDate', 'desc', '');
								}
								else {
									instance.passwordPrompt(accountId, inboxFolderId);
								}
							}
						}
					}
				);
			},

			loadCompose: function(accountId, messageId, messageType, replyMessageId) {
				var instance = this;

				instance._displayContainer(instance.composeContainer);

				instance.composeContainer.io.set(
					'data',
					{
						accountId: accountId,
						messageId: messageId,
						messageType: messageType,
						replyMessageId: replyMessageId
					}
				);

				instance.composeContainer.io.start();
			},

			loadFolders: function(accountId) {
				var instance = this;

				if (accountId > 0) {
					instance.controlContainer.show();

					instance.foldersContainer.io.set('data', {accountId: accountId});

					instance.foldersContainer.io.start();
				}
			},

			loadManageFolders: function(accountId) {
				var instance = this;

				instance._displayContainer(instance.manageFoldersContainer);

				instance.manageFoldersContainer.io.set('data', {accountId: accountId});

				instance.manageFoldersContainer.io.start();
			},

			loadMessage: function(folderId, messageNumber, orderByField, orderByType, keywords) {
				var instance = this;

				instance.folderId = folderId;
				instance.orderByField = orderByField;
				instance.orderByType = orderByType;
				instance.keywords = keywords;

				instance._displayContainer(instance.messageContainer);

				instance.messageContainer.io.set(
					'data',
					{
						folderId: folderId,
						messageNumber: messageNumber,
						orderByField: orderByField,
						orderByType: orderByType,
						keywords: keywords
					}
				);

				instance.messageContainer.io.start();
			},

			loadMessages: function(folderId, pageNumber, orderByField, orderByType, keywords, autoLoad) {
				var instance = this;

				instance.folderId = folderId;
				instance.pageNumber = pageNumber;
				instance.orderByField = orderByField;
				instance.orderByType = orderByType;
				instance.keywords = keywords;

				if (autoLoad) {
					instance.messagesContainer.io.set('showLoading', false);
				}
				else {
					instance._displayContainer(instance.messagesContainer);

					if (pageNumber == 1) {
						instance.checkMessages(folderId);
					}
				}

				instance.messagesContainer.io.set(
					'data',
					{
						folderId: folderId,
						pageNumber: pageNumber,
						orderByField: orderByField,
						orderByType: orderByType,
						keywords: keywords
					}
				);

				instance.messagesContainer.io.start();

				instance.messagesContainer.io.set('showLoading', true);
			},

			moveMessages: function(folderId, messageIds) {
				var instance = this;

				instance.setStatus('info', Liferay.Language.get('moving-messages'));

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/move_messages',
					{
						data: {
							folderId: folderId,
							messageIds: messageIds
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var responseData = this.get('responseData');

								instance.setStatus(responseData.status, responseData.message);

								instance.loadFolders(instance.accountId);
								instance.loadMessages(instance.folderId, instance.pageNumber, instance.orderByField, instance.orderByType, instance.keywords);
							}
						}
					}
				);
			},

			passwordPrompt: function(accountId, inboxFolderId) {
				var instance = this;

				new A.Dialog(
					{
						centered: true,
						cssClass: 'mail-dialog',
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('password'),
						width: 600
					}
				).plug(
					A.Plugin.IO,
					{
						data: {
							accountId: accountId,
							inboxFolderId: inboxFolderId
						},
						uri: themeDisplay.getLayoutURL() + '/-/mail/password_prompt'
					}
				).render();
			},

			reset: function() {
				var instance = this;

				instance.accountId = null;
				instance.folderId = null;
				instance.inboxFolderId = null;
				instance.keywords = '';
				instance.orderByField = 'sentDate';
				instance.orderByType = 'desc';
				instance.pageNumber = 1;

				instance.composeContainer.html('');
				instance.foldersContainer.html('');
				instance.manageFoldersContainer.html('');
				instance.messageContainer.html('');
				instance.messagesContainer.html('');

				instance.controlContainer.hide();

				instance.loadAccounts();

				instance._pollStopMessages();
			},

			setStatus: function(type, message, indefinite) {
				var instance = this;

				var messageType = 'portlet-msg-error';

				if (type == 'success') {
					messageType = 'portlet-msg-success';
				}
				else if (type == 'info') {
					messageType = 'portlet-msg-info';
				}

				var statusContainers = A.all('.mail-status').html('<table style="margin: 0 auto;"><tr><td>&nbsp;</td><td><span class="message ' + messageType + '">' + message + '</span></td><td>&nbsp;</td></tr></table>');

				var status = statusContainers.all('table');

				if (!indefinite) {
					setTimeout(
						function() {
							status.remove();
						},
						5000
					);
				}
			},

			_assignEvents: function() {
				var instance = this;

				instance.accountsContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: true,
						data: {accountId: instance.accountId},
						method: 'POST',
						showLoading: false,
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_accounts'
					}
				);

				instance.composeContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST',
						on: {
							success: function(event) {
								try {
									window[instance.namespace + 'editor'] = null;

									delete window[instance.namespace + 'editor'];
								}
								catch (e) {
								}
							}
						},
						uri: themeDisplay.getLayoutURL() + '/-/mail/compose'
					}
				);

				instance.foldersContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST',
						showLoading: false,
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_folders'
					}
				);

				instance.messageContainer.plug(
					A.Plugin.IO,
					{
						after: {
							success: function() {
								instance.loadFolders(instance.accountId);
							}
						},
						autoLoad: false,
						method: 'POST',
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_message'
					}
				);

				instance.messagesContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST',
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_messages',
						after: {
							success: function() {
								instance.messagesContainer.all('.flag-messages select').on(
									'change',
									function(event) {
										var messageIds = instance._getSelectedMessageIds();
										var values = event.currentTarget.get('value').split(',');

										instance.flagMessages(values[0], values[1], messageIds);
									}
								);

								instance.messagesContainer.all('.move-messages select').on(
									'change',
									function(event) {
										var folderId = event.currentTarget.get('value');
										var messageIds = instance._getSelectedMessageIds();

										instance.moveMessages(folderId, messageIds);
									}
								);
							}
						}
					}
				);

				instance.accountsContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var accountId = link.getAttribute('data-accountId');
						var inboxFolderId = link.getAttribute('data-inboxFolderId');

						instance.loadAccounts(accountId);
						instance.loadAccount(accountId, inboxFolderId);
					},
					'.folders-link'
				);

				instance.composeContainer.delegate(
					'click',
					function(event) {
						var button = event.currentTarget.one('input[type="button"]');

						var messageId = button.getAttribute('data-messageId');

						instance.deleteMessages([messageId]);
					},
					'.discard-draft'
				);

				instance.contentContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var folderId = link.getAttribute('data-folderId');
						var messageNumber = link.getAttribute('data-messageNumber');
						var orderByField = link.getAttribute('data-orderByField');
						var orderByType = link.getAttribute('data-orderByType');
						var keywords = link.getAttribute('data-keywords');

						instance.loadMessage(folderId, messageNumber, orderByField, orderByType, keywords);
					},
					'.message-link'
				);

				instance.contentContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var messageId = link.getAttribute('data-messageId');

						instance.loadCompose(instance.accountId, messageId, "edit", 0);
					},
					'.draft-link'
				);

				instance.foldersContainer.delegate(
					'click',
					function(event) {
						instance.editAccount(instance.accountId);
					},
					'.edit-account'
				);

				instance.foldersContainer.delegate(
					'click',
					function(event) {
						instance.loadManageFolders(instance.accountId);
					},
					'.manage-folders'
				);

				instance.mailContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var folderId = link.getAttribute('data-folderId');
						var pageNumber = link.getAttribute('data-pageNumber');
						var orderByField = link.getAttribute('data-orderByField');
						var orderByType = link.getAttribute('data-orderByType');
						var keywords = link.getAttribute('data-keywords');

						instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
					},
					'.messages-link'
				);

				instance.mailContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						if (!link.hasAttribute('data-messageType')) {
							link = link.one('input[type="button"]');
						}

						var messageId = link.getAttribute('data-messageId');
						var messageType = link.getAttribute('data-messageType');
						var replyMessageId = link.getAttribute('data-replyMessageId');

						instance.loadCompose(instance.accountId, messageId, messageType, replyMessageId);
					},
					'.compose-message'
				);

				instance.manageFoldersContainer.plug(
					A.Plugin.IO,
					{
						autoLoad: false,
						method: 'POST',
						uri: themeDisplay.getLayoutURL() + '/-/mail/manage_folders'
					}
				);

				instance.messageContainer.delegate(
					'click',
					function(event) {
						var button = event.currentTarget.one('input[type="button"]');

						var messageId = button.getAttribute('data-messageId');

						instance.deleteMessages([messageId]);
					},
					'.delete-message'
				);

				instance.messagesContainer.delegate(
					'click',
					function(event) {
						var messageIds = instance._getSelectedMessageIds();

						instance.deleteMessages(messageIds);
					},
					'.delete-messages'
				);

				instance.messagesContainer.delegate(
					'click',
					function(event) {
						var keywords = instance.messagesContainer.one('.search input').val();

						instance.loadMessages(instance.folderId, 1, instance.orderByField, instance.orderByType, keywords);
					},
					'.search-messages'
				);

				instance.messagesContainer.delegate(
					'keyup',
					function(event) {
						if (event.keyCode != 13) {
							return;
						}

						var keywords = instance.messagesContainer.one('.search input').val();

						instance.loadMessages(instance.folderId, 1, instance.orderByField, instance.orderByType, keywords);
					},
					'.search'
				);

				instance.messagesContainer.delegate(
					'click',
					function(event) {
						instance.messagesContainer.all('input[type=checkbox]').each(
							function(item, index, collection) {
								item.set('checked', true);
							}
						);
					},
					'.select-all'
				);

				instance.messagesContainer.delegate(
					'click',
					function(event) {
						instance.messagesContainer.all('input[type=checkbox]').each(
							function(item, index, collection) {
								item.set('checked', false);
							}
						);
					},
					'.select-none'
				);

				instance.timeoutMessages = setTimeout('Liferay.Mail._pollCheckMessages()', instance._pollInterval);
			},

			_displayContainer: function(container) {
				var instance = this;

				instance.composeContainer.hide();
				instance.manageFoldersContainer.hide();
				instance.messagesContainer.hide();
				instance.messageContainer.hide();

				container.show();
			},

			_getSelectedMessageIds: function() {
				var instance = this;

				var messageIds = [];

				instance.messagesContainer.all('input[type=checkbox]').each(
					function(item, index, collection) {
						var messageId = item.getAttribute('messageId');

						if (messageId && item.get('checked')) {
							messageIds.push(messageId);
						}
					}
				);

				return messageIds;
			},

			_pollCheckMessages: function() {
				var instance = this;

				instance.checkMessages(instance.inboxFolderId);

				instance.timeoutMessages = setTimeout('Liferay.Mail._pollCheckMessages()', instance._pollInterval);
			},

			_pollStopMessages: function() {
				var instance = this;

				clearTimeout(instance.timeoutMessages);
			},

			accountId: null,
			folderId: null,
			inboxFolderId: null,
			keywords: '',
			orderByField: 'sentDate',
			orderByType: 'desc',
			pageNumber: 1,
			_pollInterval: 60000
		};
	},
	'',
	{
		requires: ['aui-base', 'aui-datatype', 'aui-dialog', 'aui-io']
	}
);