AUI().add(
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

				instance.loadFolders(instance.accountId);
			},

			addAccount: function () {
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

			loadAccounts: function(accountId) {
				var instance = this;

				instance.accountsContainer.io.set('data', {accountId: accountId});

				instance.accountsContainer.io.start();
			},

			loadAccount: function(accountId, inboxFolderId) {
				var instance = this;

				instance.manageFoldersContainer.hide();
				instance.messagesContainer.show();
				instance.messageContainer.hide();
				instance.composeContainer.hide();

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
								}
								else {
									instance.promptForPassword(accountId, inboxFolderId);
								}
							}
						}
					}
				);
			},

			loadFolders: function(accountId) {
				var instance = this;

				instance.foldersContainer.io.set('data', {accountId: accountId});

				instance.foldersContainer.io.start();
			},

			setStatus: function(type, message) {
				var messageType = 'portlet-msg-error';

				if (type == 'success') {
					messageType = 'portlet-msg-success';
				}
				else if (type == 'info') {
					messageType = 'portlet-msg-info';
				}

				A.all('.mail-status').html('<table style="margin: 0 auto;"><tr><td>&nbsp;</td><td><span class="message ' + messageType + '">' + message + '</span></td><td>&nbsp;</td></tr></table>');
			},

			_assignEvents: function() {
				var instance = this;

				instance.accountsContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_accounts',
						autoLoad: true,
						data: {accountId: instance.accountId},
						showLoading: false,
						method: 'POST'
					}
				);

				instance.foldersContainer.plug(
					A.Plugin.IO,
					{
						uri: themeDisplay.getLayoutURL() + '/-/mail/view_folders',
						autoLoad: false,
						method: 'POST'
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
			},

			accountId: null,
			folderId: null,
			keywords: '',
			orderByField: 'sentDate',
			orderByType: 'desc'
		};
	},
	'',
	{
		requires: ['aui-base', 'aui-dialog', 'aui-io']
	}
);