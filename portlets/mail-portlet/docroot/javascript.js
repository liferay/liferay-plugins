AUI().add(
	'liferay-plugin-mail',
	function(A) {
		Liferay.Mail = {
			init: function() {
				var instance = this;

				alert('init');
			}
		}
	},
	'',
	{
		requires: ['aui-base', 'aui-io', 'aui-dialog']
	}
);