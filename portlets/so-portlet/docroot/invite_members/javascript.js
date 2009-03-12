Liferay.SO = Liferay.SO || {};

Liferay.SO.InviteMembers = {
	init: function(params) {
		var instance = this;

		instance._assignEvents();
		instance._assignAddEmailEvents();
		instance._assignAddMemberEvents();
		instance._setupLiveSearch();
	},

	addEmailInvite: function(address) {
		var instance = this;

		var emailAddress = address.attr('value');

		if ((emailAddress != "") && (jQuery("." + instance._sanitizeEmailAddress(emailAddress)).size() < 1)) {
			var emailHTML = '<div class="user" data-emailAddress="' + emailAddress + '"><span class="email">' + emailAddress + '</span></div>';

			jQuery('.so-portlet-invite-members .email-invited .list').append(emailHTML);

			var inviteMember = jQuery('.so-portlet-invite-members .email-invited .list').find('.user[data-emailAddress=' + emailAddress + ']');

			var formInput = '<input class="' + instance._sanitizeEmailAddress(emailAddress) + '" name="emailAddresses" type="hidden" value="' + emailAddress + '" />';

			jQuery('.invite-actions').append(formInput);

			jQuery('.so-portlet-invite-members #new-member-email-address').val('');

			var numOfMembers = jQuery("#invitedMembersCount").val();

			jQuery("#invitedMembersCount").val(parseInt(numOfMembers) + 1);

			inviteMember.click(
				function() {
					instance.removeEmailInvite(jQuery(this));
				}
			);
		}
		else if (jQuery("." + instance._sanitizeEmailAddress(emailAddress)).size() > 0) {
			var emailBox = jQuery('#new-member-email-address');
			var previousColor = emailBox.css('color');

			emailBox.css('color', '#CCCC00');

			setTimeout(
				function() {
					emailBox.css('color', previousColor);
				},
				100
			);
		}
	},
	
	addMemberInvite: function(member) {
		var instance = this;

		var invitedUser = member.clone().appendTo('.so-portlet-invite-members .invited .list');

		invitedUser.click(
			function() {
				instance.removeMemberInvite(jQuery(this));
			}
		);

		member.removeClass('user').addClass('invited-user');
	},

	removeEmailInvite: function(address) {
		var instance = this;

		var addressStr = address.attr('data-emailaddress');

		jQuery("." + instance._sanitizeEmailAddress(addressStr)).remove();

		address.remove();
	},

	removeMemberInvite: function(member) {
		var userId = member.attr('data-userId');

		jQuery('.so-portlet-invite-members .uninvited .list').find('.invited-user[data-userId=' + userId + ']').removeClass('invited-user').addClass('user');

		jQuery('.so-portlet-invite-members .invited .list').find('.user[data-userId=' + userId + ']').remove();

		var numOfMembers = jQuery("#invitedMembersCount").val();

		jQuery("#invitedMembersCount").val(parseInt(numOfMembers) - 1);
	},

	_assignAddEmailEvents: function() {
		var instance = this;

		jQuery('.so-portlet-invite-members #add-email-address').click(
			function() {
				var emailAddress = jQuery('.so-portlet-invite-members #new-member-email-address');

				instance.addEmailInvite(jQuery(emailAddress));
			}
		);
	},

	_assignAddMemberEvents: function() {
		var instance = this;

		jQuery('.so-portlet-invite-members .user').click(
			function() {
				var member = jQuery(this);

				if (member.hasClass('user')) {
					instance.addMemberInvite(member);
				}
				else {
					instance.removeMemberInvite(member);
				}
			}
		);
	},

	_assignEvents: function() {
		jQuery('.so-portlet-members .invite-members a').click(
			function() {
				jQuery('.so-portlet-profiles .profile-wrapper').hide();
				jQuery('.so-portlet-invite-members .invite-members-wrapper').show();
			}
		);
	},

	_sanitizeEmailAddress: function(address) {
		return address.replace(/@|\./g,"");
	},

	_setupLiveSearch: function() {
		var input = jQuery('#invite-user-search');

		var options = {
			list: jQuery('.so-portlet-invite-members .uninvited .user')
		};

		input.liveSearch(options);
	}
}