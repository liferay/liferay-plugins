Liferay.SO = Liferay.SO || {};

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

		jQuery('.so-portlet-members .user').click(
			function() {
				var userId = jQuery(this).attr('data-userId');

				instance.displayUserProfile(userId);
			}
		);
	},

	_loadProfileAction: function(userId, action) {
		var instance = this;

		instance._showProfile();

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/profiles/' + action,
				data: {userId: userId},
				success: function(result) {
					jQuery('.profile-wrapper').html(result);
				},
				type: 'POST'
			}
		);
	},

	_showProfile: function() {
		jQuery('.so-portlet-invite-members .invite-members-wrapper').hide();
		jQuery('.so-portlet-profiles .profile-wrapper').show();
	}
};