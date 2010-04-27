AUI().add(
	'liferay-soffice-profiles',
	function(A) {
		var Profiles = function() {
			var instance = this;

			instance.users = 
			instance.profileWrapper = A.one('#so-profile-wrapper');

		}

		Liferay.namespace('SO');

		Liferay.SO.Profiles = Profiles;

		Liferay.SO.Profiles = {
			init: function(params) {
				var instance = this;

				instance._assignEvents();
			},

			displayUserProfile: function(userId) {
				this._loadProfileAction(userId, 'user_profile');
			},

			editUserProfile: function(userId) {
				this._loadProfileAction(userId, 'edit_profile');
			},

			editUserProjects: function(userId) {
				this._loadProfileAction(userId, 'edit_projects');
			},

			editUserSettings: function(userId) {
				this._loadProfileAction(userId, 'edit_settings');
			},

			_assignEvents: function() {
				var instance = this;

				A.all('.so-portlet-members .user').on(
					'click',
					function(event) {
						var userId = event.currentTarget.attr('data-userId');

						instance.displayUserProfile(userId);
					}
				);
			},

			_loadProfileAction: function(userId, action) {
				var instance = this;

				instance._showProfile();

				var profile = A.one('#so-profile-wrapper');

				if (!profile) {
					return;
				}

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
				var inviteMembers = A.one('.so-portlet-invite-members .invite-members-wrapper');

				if (inviteMembers) {
					inviteMembers.hide();
				}

				A.one('#so-profile-wrapper').show();
			}
		};
	},
	'',
	{
		requires: ['aui-base']
	}
);