AUI.add(
	'liferay-soffice-profile',
	function(A) {
		var Profiles = function() {
			var instance = this;

			Profiles.superclass.constructor.apply(instance, arguments);
		}

		Profiles.NAME = 'soprofile';

		Profiles.ATTRS = {
			userID: {
				value: 0
			}
		};

		A.extend(
			Profiles,
			A.Base,
			{
				initializer: function(){
					var instance = this;

					instance._profileWrapper = A.one('#so-profile-wrapper');

					instance._assignEvents();
				},

				_assignEvents: function() {
					var instance = this;

					instance.after('userIDChange', instance._afterUserIDChange);

					A.all('.so-portlet-members .user').on(
						'click',
						function(event) {
							instance.set('userID', event.currentTarget.getAttribute('data-userId'));
						}
					);

					instance._profileWrapper.delegate(
						'click',
						function(event) {
							instance._loadProfileAction('user_profile');
						},
						'.so-display-profile'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event){
							instance._loadProfileAction('edit_profile');
						},
						'.so-edit-profile'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event){
							instance._loadProfileAction('edit_projects');
						},
						'.so-edit-projects'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event){
							instance._loadProfileAction('edit_settings');
						},
						'.so-change-settings'
					);
				},

				_afterUserIDChange: function(event) {
					var instance = this;

					instance._loadProfileAction('user_profile');
				},

				_loadProfileAction: function(action) {
					var instance = this;

					var userId = instance.get('userID');

					instance._showProfile();

					var profile = instance._profileWrapper;

					if (!profile.io) {
						profile.plug(
							A.Plugin.IO,
							{
								autoLoad: false,
								method: 'POST'
							}
						);
					}

					profile.io.set('data', {userId: userId});
					profile.io.set('uri', themeDisplay.getLayoutURL() + '/-/profiles/' + action);

					profile.io.start();

				},

				_showProfile: function() {
					var instance = this;

					var inviteMembers = A.one('.so-portlet-invite-members .invite-members-wrapper');

					if (inviteMembers) {
						inviteMembers.hide();
					}

					instance._profileWrapper.show();
				}
			}
		);

		Liferay.namespace('SO');

		Liferay.SO.Profiles = Profiles;
	},
	'',
	{
		requires: ['aui-base']
	}
);