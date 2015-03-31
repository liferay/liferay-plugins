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

				Liferay.on('closePortlet', instance._pollStopMessages, instance);
			},

			addAccount: function() {
				var instance = this;

				instance.dialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							centered: true,
							cssClass: 'mail-dialog',
							destroyOnClose: true,
							modal: true,
							width: 600
						},
						title: Liferay.Language.get('add-account')
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
						data: Liferay.Util.ns(
							instance.namespace,
							{
								accountId: instance.accountId,
								folderId: folderId
							}
						),
						dataType: 'JSON',
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
						data: Liferay.Util.ns(
							instance.namespace,
							{
								messageIds: messageIds
							}
						),
						dataType: 'JSON',
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

				instance.dialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							centered: true,
							cssClass: 'mail-dialog',
							destroyOnClose: true,
							modal: true,
							width: 600
						},
						title: Liferay.Language.get('edit-account')
					}
				).plug(
					A.Plugin.IO,
					{
						data: Liferay.Util.ns(
							instance.namespace,
							{
								accountId: accountId
							}
						),
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
						data: Liferay.Util.ns(
							instance.namespace,
							{
								flag: flag,
								messageIds: messageIds,
								value: value
							}
						),
						dataType: 'JSON',
						method: 'POST',
						on: {
							failure: function(event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function(event, id, obj) {
								var responseData = this.get('responseData');

								instance.setStatus(responseData.status, responseData.message);

								instance.loadMessages(instance.folderId, instance.pageNumber, instance.orderByField, instance.orderByType, instance.keywords);
							}
						}
					}
				);
			},

			loadAccount: function(accountId, inboxFolderId) {
				var instance = this;

				instance._displayContainer(instance.messagesContainer);

				A.io.request(
					themeDisplay.getLayoutURL() + '/-/mail/password_saved',
					{
						data: Liferay.Util.ns(
							instance.namespace,
							{
								accountId: accountId,
								inboxFolderId: inboxFolderId
							}
						),
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

				instance._hideWindow();
			},

			loadAccounts: function(accountId) {
				var instance = this;

				instance.accountsContainer.io.set(
					'data',
					Liferay.Util.ns(
						instance.namespace,
						{
							accountId: accountId
						}
					)
				);

				instance.accountsContainer.io.start();
			},

			loadCompose: function(accountId, messageId, messageType, replyMessageId) {
				var instance = this;

				instance._displayContainer(instance.composeContainer);

				instance.composeContainer.io.set(
					'data',
					Liferay.Util.ns(
						instance.namespace,
						{
							accountId: accountId,
							messageId: messageId,
							messageType: messageType,
							replyMessageId: replyMessageId
						}
					)
				);

				instance.composeContainer.io.start();
			},

			loadFolders: function(accountId) {
				var instance = this;

				if (accountId > 0) {
					instance.controlContainer.show();

					instance.foldersContainer.io.set(
						'data',
						Liferay.Util.ns(
							instance.namespace,
							{
								accountId: accountId
							}
						)
					);

					instance.foldersContainer.io.start();
				}

				instance._hideWindow();
			},

			loadManageFolders: function(accountId) {
				var instance = this;

				instance._displayContainer(instance.manageFoldersContainer);

				instance.manageFoldersContainer.io.set(
					'data',
					Liferay.Util.ns(
						instance.namespace,
						{
							accountId: accountId
						}
					)
				);

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
					Liferay.Util.ns(
						instance.namespace,
						{
							folderId: folderId,
							keywords: keywords,
							messageNumber: messageNumber,
							orderByField: orderByField,
							orderByType: orderByType
						}
					)
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
					Liferay.Util.ns(
						instance.namespace,
						{
							folderId: folderId,
							keywords: keywords,
							orderByField: orderByField,
							orderByType: orderByType,
							pageNumber: pageNumber
						}
					)
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
						data: Liferay.Util.ns(
							instance.namespace,
							{
								folderId: folderId,
								messageIds: messageIds
							}
						),
						dataType: 'JSON',
						method: 'POST',
						on: {
							failure: function(event, id, obj) {
								instance.setStatus('error', Liferay.Language.get('unable-to-connect-with-mail-server'));
							},
							success: function(event, id, obj) {
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

				instance.dialog = Liferay.Util.Window.getWindow(
					{
						dialog: {
							centered: true,
							cssClass: 'mail-dialog',
							destroyOnClose: true,
							modal: true,
							width: 600
						},
						title: Liferay.Language.get('password')
					}
				).plug(
					A.Plugin.IO,
					{
						data: Liferay.Util.ns(
							instance.namespace,
							{
								accountId: accountId,
								inboxFolderId: inboxFolderId
							}
						),
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
				instance._hideWindow();
			},

			setStatus: function(type, message, indefinite) {
				var instance = this;

				var statusContainers = A.all('.mail-status').html('<table style="margin: 0 auto;"><tr><td>&nbsp;</td><td><span class="alert message alert-' + type + '">' + message + '</span></td><td>&nbsp;</td></tr></table>');

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
						data: Liferay.Util.ns(
							instance.namespace,
							{
								accountId: instance.accountId
							}
						),
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
						after: {
							success: function() {
								instance.messagesContainer.all('.flag-messages').on(
									'click',
									function(event) {
										var messageIds = instance._getSelectedMessageIds();

										var currentTarget = event.currentTarget;

										var flagType = currentTarget.getData('flagType');
										var flagToggle = currentTarget.getData('flagToggle');

										instance.flagMessages(flagType, flagToggle, messageIds);
									}
								);

								instance.messagesContainer.all('.move-messages').on(
									'click',
									function(event) {
										var folderId = A.Lang.String.trim(event.currentTarget.text());

										var messageIds = instance._getSelectedMessageIds();

										instance.moveMessages(folderId, messageIds);
									}
								);
							}
						},
						autoLoad: false,
						method: 'POST',
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_messages'
					}
				);

				instance.accountsContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var accountId = link.getData('accountId');
						var inboxFolderId = link.getData('inboxFolderId');

						instance.loadAccounts(accountId);
						instance.loadAccount(accountId, inboxFolderId);
					},
					'.folders-link'
				);

				instance.composeContainer.delegate(
					'click',
					function(event) {
						var messageId = event.currentTarget.getData('messageId');

						instance.deleteMessages([messageId]);
					},
					'.discard-draft'
				);

				instance.contentContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var li = link.ancestor('li');

						if (!li || !li.hasClass('disabled')) {
							var folderId = link.getData('folderId');
							var messageNumber = link.getData('messageNumber');
							var orderByField = link.getData('orderByField');
							var orderByType = link.getData('orderByType');
							var keywords = link.getData('keywords');

							instance.loadMessage(folderId, messageNumber, orderByField, orderByType, keywords);
						}
					},
					'.message-link'
				);

				instance.contentContainer.delegate(
					'click',
					function(event) {
						var link = event.currentTarget;

						var messageId = link.getData('messageId');

						instance.loadCompose(instance.accountId, messageId, 'edit', 0);
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

						var li = link.ancestor('li');

						if (!li || !li.hasClass('disabled')) {
							var folderId = link.getData('folderId');
							var pageNumber = link.getData('pageNumber');
							var orderByField = link.getData('orderByField');
							var orderByType = link.getData('orderByType');
							var keywords = link.getData('keywords');

							instance.loadMessages(folderId, pageNumber, orderByField, orderByType, keywords);
						}
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

						var messageId = link.getData('messageId');
						var messageType = link.getData('messageType');
						var replyMessageId = link.getData('replyMessageId');

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
						var messageId = event.currentTarget.getData('messageId');

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
						var keywords = event.currentTarget.ancestor('.search-messages').one('input').val();

						instance.loadMessages(instance.folderId, 1, instance.orderByField, instance.orderByType, keywords);
					},
					'.search-messages button'
				);

				instance.messagesContainer.delegate(
					'keyup',
					function(event) {
						if (event.keyCode != 13) {
							return;
						}

						var keywords = event.currentTarget.val();

						instance.loadMessages(instance.folderId, 1, instance.orderByField, instance.orderByType, keywords);
					},
					'.search-messages input'
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

				instance._pollCheckMessages();
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

			_hideWindow: function() {
				var instance = this;

				var dialog = instance.dialog;

				if (dialog) {
					dialog.hide();
				}

			},

			_pollCheckMessages: function() {
				var instance = this;

				if (instance.inboxFolderId) {
					instance.checkMessages(instance.inboxFolderId);
				}

				if (instance.timeoutMessages) {
					clearTimeout(instance.timeoutMessages);
				}

				instance.timeoutMessages = setTimeout(A.bind('_pollCheckMessages', instance), instance._pollInterval);
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
		requires: ['aui-base', 'aui-datatype', 'aui-io-deprecated', 'liferay-util-window']
	}
);