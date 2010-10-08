AUI.add(
	'liferay-soffice-profile',
	function(A) {
		var Profiles = function() {
			Profiles.superclass.constructor.apply(this, arguments);
		};

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

					instance.after('userIDChange', instance._afterUserIDChange);

					A.all('.so-portlet-members .user').on(
						'click',
						function(event) {
							instance._showProfile();

							instance.set('userID', event.currentTarget.getAttribute('data-userId'));
						}
					);

					instance._profileWrapper.delegate(
						'click',
						function(event) {
							instance._loadProfileAction('user_profile', false);
						},
						'.so-display-profile'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event) {
							instance._loadProfileAction('edit_profile', true);
						},
						'.so-edit-profile'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event) {
							instance._loadProfileAction('edit_projects', true);
						},
						'.so-edit-projects'
					);

					instance._profileWrapper.delegate(
						'click',
						function(event) {
							instance._loadProfileAction('edit_settings', true);
						},
						'.so-change-settings'
					);
				},

				_afterUserIDChange: function(event) {
					var instance = this;

					instance._loadProfileAction('user_profile', false);
				},

				_bindForm: function() {
					var instance = this;

					var form = instance._profileWrapper.one('form');

					form.on(
						'submit',
						function(event) {
							event.preventDefault();

							var profile = instance._getProfileIO();

							Liferay.fire('saveAutoFields');

							profile.io.set('form', {id: form.getDOM()});
							profile.io.set('uri', form.getAttribute('action'));

							profile.io.start();

							window.scrollTo(0,0);
						}
					);
				},

				_getProfileIO: function() {
					var instance = this;

					profile = instance._profileWrapper;

					if (!profile.io) {
						profile.plug(
							A.Plugin.IO,
							{
								autoLoad: false,
								method: 'POST'
							}
						);
					}

					profile.io.set('data', null);
					profile.io.set('uri', null);
					profile.io.set('form', null);

					profile.io.detach('success', instance._bindForm);

					return profile;
				},

				_loadProfileAction: function(action, hasForm) {
					var instance = this;

					var userId = instance.get('userID');

					instance._showProfile();

					var profile = instance._getProfileIO();

					if (hasForm) {
						profile.io.on('success', instance._bindForm, instance);
					}

					profile.io.set('data', {userId: userId});
					profile.io.set('uri', themeDisplay.getLayoutURL() + '/-/profiles/' + action);

					profile.io.start();

					window.scrollTo(0,0);
				},

				_showProfile: function() {
					var instance = this;

					var inviteMembers = A.one('#so-invitemembers-wrapper');

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