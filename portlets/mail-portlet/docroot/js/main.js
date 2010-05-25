AUI().add(
	'liferay-plugin-mail',
	function(A) {
		Liferay.Mail = {
			init: function(params) {
				var instance = this;

				instance.namespace = params.namespace;				
				instance.setAccountId(params.initialAccountId);
				instance.setFolderId(params.initialFolderId);

				// Commonly used expressions

				instance.accountsContainer = A.one('#accountsContainer');
				instance.composeContainer = A.one('#composeContainer');
				instance.controlContainer = A.one('#controlContainer');
				instance.contentContainer = A.one('#contentContainer');
				instance.foldersContainer = A.one('#foldersContainer');
				instance.mailContainer = A.one('#mailContainer');
				instance.manageFoldersContainer = A.one('#manageFoldersContainer');
				instance.messageContainer = A.one('#messageContainer');
				instance.messagesContainer = A.one('#messagesContainer');

				// init methods

				instance._assignEvents();
				
				if (instance.getAccountId() != 0) {
					instance.loadFolders(instance.getAccountId());
					instance.loadMessages(instance.getFolderId(), 1, instance.getOrderByField(), instance.getOrderByType(), instance.getKeywords());
				}

				//instance.autoSaveDraft();
			},

			_assignEvents: function() {
				var instance = this;

				instance.composeContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/compose',
						autoLoad: false,
						data: {
							accountId: 0,
							messageId: 0,
							messageType: 0,
							replyMessageId: 0
						},
						method: 'POST'
					}
				);

				instance.accountsContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_accounts',
						autoLoad: true,
						data: {
							accountId: instance.getAccountId()
						},
						showLoading: false,
						method: 'POST'
					}
				);

				instance.foldersContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_folders',
						autoLoad: false,
						data: {
							accountId: 0
						},
						method: 'POST'
					}
				);

				instance.manageFoldersContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/manage_folders',
						autoLoad: false,
						data: {
							accountId: 0
						},
						method: 'POST'
					}
				);

				instance.messagesContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_messages',
						autoLoad: false,
						data: {
							folderId: 0,
							pageNumber: 1,
							orderByField: 'sentDate',
							orderByType: 'desc',
							keywords: 0
						},
						method: 'POST'
					}
				);

				instance.messageContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_message',
						autoLoad: false,
						data: {
							folderId: 0,
							messageNumber: 0,
							keywords: ''
						},
						dataType: 'json',
						method: 'POST'
					}
				);

				// Event Handlers

				instance.messageContainer.delegate('click', function(event) {
					var link = event.currentTarget;

					var folderId = link.getAttribute('folderId');
					var pageNumber = link.getAttribute('pageNumber');
					var orderByField = link.getAttribute('orderByField');
					var orderByType = link.getAttribute('orderByType');
					var keywords = link.getAttribute('keywords');

					instance.manageFoldersContainer.setStyle('display', 'none');
					instance.messagesContainer.setStyle('display', null);
					instance.messageContainer.setStyle('display', 'none');
					instance.composeContainer.setStyle('display', 'none');

					instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
				}, '.back-to-messages');

				instance.mailContainer.delegate('click', function(event) {
					var link = event.currentTarget;

					var messageId = link.getAttribute('data-messageId');
					var messageType = link.getAttribute('data-messageType');
					var replyMessageId = link.getAttribute('data-replyMessageId');

					if (replyMessageId == 0) {
						instance.manageFoldersContainer.setStyle('display', 'none');
						instance.messagesContainer.setStyle('display', 'none');
						instance.messageContainer.setStyle('display', 'none');
					}

					instance.composeContainer.setStyle('display', null);

					instance.composeMessage(instance.getAccountId(), messageId, messageType, replyMessageId);
				}, '.compose-message');

				instance.messagesContainer.delegate('click', function(event) {
					var messageIds = instance.getSelectedMessageIds();

					instance.deleteMessages(messageIds);
				}, '.delete-messages');

				instance.composeContainer.delegate('click', function(event) {
					var messageIds = '';

					alert('discarding-draft');

					//instance.discardDraft(messageId);
				}, '.discard-draft');

				instance.foldersContainer.delegate('click', function() {
					instance.editAccount(instance.getAccountId());
				}, '.edit-account');

				instance.messagesContainer.delegate('change', function(event) {
					var messageIds = instance.getSelectedMessageIds();
					var value = this.one('select').get('value').split(',');

					instance.flagMessages(messageIds, value[0], value[1]);
				}, '.flag-messages');

				instance.accountsContainer.delegate('click', function(event) {
					var link = event.currentTarget;

					var accountId = link.getAttribute('data-accountId');
					var inboxFolderId = link.getAttribute('data-inboxFolderId');

					instance.loadAccounts(accountId);
					instance.loadAccount(accountId, inboxFolderId);
				}, '.folders-link');

				instance.foldersContainer.delegate('click', function() {
					instance.loadManageFolders(instance.getAccountId());
				}, '.manage-folders');

				instance.contentContainer.delegate('click', function(event) {
					var link = event.currentTarget;

					var folderId = link.getAttribute('folderId');
					var messageNumber = link.getAttribute('messageNumber');
					var orderByField = link.getAttribute('orderByField');
					var orderByType = link.getAttribute('orderByType');
					var keywords = link.getAttribute('keywords');

					instance.manageFoldersContainer.setStyle('display', 'none');
					instance.messagesContainer.setStyle('display', 'none');
					instance.messageContainer.setStyle('display', null);
					instance.composeContainer.setStyle('display', 'none');

					instance.loadMessage(folderId, messageNumber, orderByField, orderByType, keywords);
				}, '.message-link');

				instance.mailContainer.delegate('click', function(event) {
					var link = event.currentTarget;

					var folderId = link.getAttribute('folderId');
					var pageNumber = link.getAttribute('pageNumber');
					var orderByField = link.getAttribute('orderByField');
					var orderByType = link.getAttribute('orderByType');
					var keywords = link.getAttribute('keywords');

					instance.manageFoldersContainer.setStyle('display', 'none');
					instance.messagesContainer.setStyle('display', null);
					instance.messageContainer.setStyle('display', 'none');
					instance.composeContainer.setStyle('display', 'none');

					instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
				}, '.messages-link');

				instance.messagesContainer.delegate('change', function(event) {
					var folderId = this.one('select').get('value');
					var messageIds = instance.getSelectedMessageIds();

					instance.moveMessages(folderId, messageIds);
				}, '.move-messages');

				instance.composeContainer.delegate('click', function(event) {
					var accountId = '';
					var messageId = '';
					var to = '';
					var cc = '';
					var bcc = '';
					var subject = '';
					var body = '';
					
					instance.saveDraft(accountId, messageId, to, cc, bcc, subject, body);
				}, 'save-draft');

				instance.messagesContainer.delegate('click', function(event) {
					instance.messagesContainer.all('input[type=checkbox]').each(
						function(item, index, collection) {
							item.set('checked', true);
						}
					);
				}, '.select-all');

				instance.messagesContainer.delegate('click', function(event) {
					instance.messagesContainer.all('input[type=checkbox]').each(
						function(item, index, collection) {
							item.set('checked', false);
						}
					);
				}, '.select-none');
				
			},
			
			addAccount: function() {
				var instance = this;

				var dialog = new A.Dialog(
					{
						centered: true,
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('add-account'),
						width: 600
					}
				)
				.render();

				dialog.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/add_account'
					}
				);
			},

			autoSaveDraft: function() {
				var instance = this;

				if (instance.composeContainer.getStyle('display') == 'null') {
					if (instance.isMessageModifiedSinceLastSave()) {
						if (!instance._lockSaveAndSend) {
							instance.saveMessage();
						}
					}
				}

				if (themeDisplay.isSignedIn()) {
					setTimeout('Liferay.Mail.autoSaveDraft()', 30000);
				}
			},

			composeMessage: function(accountId, messageId, messageType, replyMessageId) {
				var instance = this;

				instance.composeContainer.io.set('data', { accountId: accountId, messageId: messageId, messageType: messageType, replyMessageId: replyMessageId });

				instance.composeContainer.io.start();
			},

			deleteAccount: function(accountId) {
				var instance = this;

				var answer = confirm(Liferay.Language.get('are-you-sure-you-want-to-delete-this-account'));

				if (!answer) {
					return;
				}

				instance.setStatus('info', 'deleting-account');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_account',
					{
						data: {
							accountId: accountId
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								instance.setStatus(results.status, results.message);

								if (results.status == 'success') {
									// reload accounts
								}
							}
						}
					}
				);
			},

			deleteMessages: function(messageIds) {
				var instance = this;

				instance.setStatus('info', 'deleting-messages');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_messages',
					{
						data: {
							messageIds: messageIds
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								instance.setStatus(results.status, results.message);

								if (results.status == 'success') {
									//instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
								}
							}
						}
					}
				);
			},

			discardDraft: function(messageId) {
				var instance = this;

				instance.setStatus('info', 'discarding-draft');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/delete_messages',
					{
						data: {
							messageIds: messageId
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								instance.setStatus(results.status, results.message);

								if (results.status == 'success') {
									// hide compose window
									//instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
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
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('update-account'),
						width: 600
					}
				)
				.render();

				dialog.plug(
					A.Plugin.IO,
					{
						data: {
							accountId: accountId
						},
						after: {
							success: function(event) {

								dialog.get('contentBox').one('.close-edit-account').on(
									'click',
									dialog.close, dialog
								);
							
							}
						},
						uri: themeDisplay.getLayoutURL() + '/-/mail/edit_account'
					}
				);
			},

			flagMessages: function(messageIds, flag, value) {
				var instance = this;

				instance.setStatus('info', 'flagging-messages');

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/flag_messages',
					{
						data: {
							messageIds: messageIds,
							flag: flag,
							value: value
						},
						dataType: 'json',
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								instance.setStatus(results.status, results.message);

								if (results.status == 'success') {
									//instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
								}
							}
						}
					}
				);
			},

			getSelectedMessageIds: function() {
				var instance = this;

				var selectedMessageIds = [];

				instance.messagesContainer.all('input[type=checkbox]').each(
					function(item, index, collection) {
						var val = item.getAttribute('messageId');

						if (val && item.get('checked')) {
							selectedMessageIds.push(val);
						}
					}
				);

				return selectedMessageIds;
			},

			loadAccount: function(accountId, inboxFolderId) {
				var instance = this;

				instance.manageFoldersContainer.setStyle('display', 'none');
				instance.messagesContainer.setStyle('display', null);
				instance.messageContainer.setStyle('display', 'none');
				instance.composeContainer.setStyle('display', 'none');

				// Check if password has been saved in session

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/password_saved',
					{
						data: {
							accountId: accountId,
							inboxFolderId: inboxFolderId
						},
						method: 'POST',
						on: {
							failure: function (event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function (event, id, obj) {
								var results = this.get('responseData');

								if (results == 'true') {
									instance.loadFolders(accountId);
									instance.loadMessages(inboxFolderId, 1, 'sentDate', 'desc', '');
								}
								else {
									instance.promptForPassword(accountId, inboxFolderId);
								}
							}
						}
					}
				);
			},

			loadAccounts: function(accountId) {
				var instance = this;

				instance.accountsContainer.io.set('data', { accountId: accountId });

				instance.accountsContainer.io.start();
			},

			loadFolders: function(accountId) {
				var instance = this;

				instance.foldersContainer.io.set('data', { accountId: accountId });

				instance.foldersContainer.io.start();
			},

			loadManageFolders: function(accountId) {
				var instance = this;

				instance.manageFoldersContainer.setStyle('display', null);
				instance.messagesContainer.setStyle('display', 'none');
				instance.messageContainer.setStyle('display', 'none');
				instance.composeContainer.setStyle('display', 'none');

				instance.manageFoldersContainer.io.set('data', { accountId: accountId });

				instance.manageFoldersContainer.io.start();
			},

			loadMessage: function(folderId, messageNumber, orderByField, orderByType, keywords) {
				var instance = this;

				instance.messageContainer.io.set('data', { folderId: folderId, messageNumber: messageNumber, orderByField: orderByField, orderByType: orderByType, keywords: keywords });

				instance.messageContainer.io.start();
			},

			loadMessages: function(folderId, pageNumber, orderByField, orderByType, keywords) {
				var instance = this;

				instance.messagesContainer.io.set('data', { folderId: folderId, pageNumber: pageNumber, orderByField: orderByField, orderByType: orderByType, keywords: keywords });

				instance.messagesContainer.io.start();
			},

			moveMessages: function(folderId, messageIds) {
				var instance = this;

				instance.setStatus('info', 'moving-messages');

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
								var results = this.get('responseData');

								console.log(results);

								instance.setStatus(results.status, results.message);

								if (results.status == 'success') {
									//instance.loadFolders(accountId);
									//instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
								}
							}
						}
					}
				);
			},

			promptForPassword: function(accountId, inboxFolderId) {
		 		var instance = this;

				var dialog = new A.Dialog(
					{
						centered: true,
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('password'),
						width: 600
					}
				)
				.render();

				dialog.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/password_prompt',
						data: {
							accountId: accountId,
							inboxFolderId: inboxFolderId
						}
					}
				);
			},

			saveDraft: function(accountId, messageId, to, cc, bcc, subject, body) {
				var instance = this;

				alert('save draft');
			},

			setStatus: function(type, message) {
				var instance = this;

				var messageType = 'portlet-msg-error';

				if (type == 'success') {
					messageType = 'portlet-msg-success';
				}
				else if (type == 'info') {
					messageType = 'portlet-msg-info';
				}

				A.one('.mail-status').html('<table style="margin: 0 auto;"><tr><td>&nbsp;</td><td><span class="message ' + messageType + '">' + message + '</span></td><td>&nbsp;</td></tr></table>');
			},

			getAccountId: function() {
				var instance = this;

				return instance._accountId;
			},
			
			getFolderId: function() {
				var instance = this;

				return instance._folderId;
			},
			
			getKeywords: function() {
				var instance = this;

				return instance._keywords;
			},
			
			getOrderByField: function() {
				var instance = this;

				return instance._orderByField;
			},
			
			getOrderByType: function() {
				var instance = this;

				return instance._orderType;
			},
			
			setAccountId: function(accountId) {
				var instance = this;

				instance._accountId = accountId;
			},
			
			setFolderId: function(folderId) {
				var instance = this;

				instance._folderId = folderId;
			},
			
			setKeywords: function(keywords) {
				var instance = this;

				instance._keywords = keywords;
			},
			
			setOrderByField: function(orderByField) {
				var instance = this;

				instance._orderByField = orderByField;
			},
			
			setOrderByType: function(orderByType) {
				var instance = this;

				instance._orderByType = orderByType;
			},
			
			_accountId: null,
			_folderId: null,
			_keywords: '',
			_orderByField: 'sentDate',
			_orderByType: 'desc'
		}
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'aui-dialog']
	}
);