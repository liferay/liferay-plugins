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