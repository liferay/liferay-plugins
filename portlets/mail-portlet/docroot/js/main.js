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

				instance._assignEvents();
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
						data: {
							accountId: instance.accountId
						},
						showLoading: false,
						method: 'POST'
					}
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