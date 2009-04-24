Liferay.SO = Liferay.SO || {};

Liferay.SO.Profiles = {
	init: function(params) {
		var instance = this;

		instance._assignEvents();
	},

	displayUserProfile: function(userId) {
		jQuery('.so-portlet-invite-members .invite-members-wrapper').hide();
		jQuery('.so-portlet-profiles .profile-wrapper').show();

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/profiles/user_profile',
				data: {userId: userId},
				success: function(result) {
					jQuery('.profile-wrapper').html(result);
				},
				type: 'POST'
			}
		);
	},

	_assignEvents: function() {
		var instance = this;

		jQuery('.so-portlet-members .user').click(
			function() {
				var userId = jQuery(this).attr('data-userId');

				instance.displayUserProfile(userId);
			}
		);
	}
}