Liferay.SO = Liferay.SO || {};

Liferay.SO.InviteMembers = {
	init: function(params) {
		var instance = this;

		if (!instance._initialized) {
			instance._assignEvents();
			instance._setupLiveSearch();
			instance._setupEventHandler();

			instance._initialized = true;
		}
	},

	addEmailInvite: function() {
		var instance = this;

		var address = instance._emailInput;

		var emailAddress = jQuery.trim(address.val());

		if (emailAddress) {
			var emailHTML = '<div class="user" data-emailAddress="' + emailAddress + '"><span class="email">' + emailAddress + '</span></div>';

			jQuery('.so-portlet-invite-members .email-invited .list').append(emailHTML);

			address.val('');
		}
	},
	
	addMemberInvite: function(member) {
		var instance = this;

		var invitedUser = member.clone().appendTo('.so-portlet-invite-members .invited .list');

		invitedUser.data('originalUser', member);
		member.data('invitedUser', invitedUser);

		member.addClass('invited-user');
	},

	prepareMemberInvite: function(data) {
		var userIdsToInvite = jQuery('.so-portlet-invite-members .invited .user');
		var emailAddressesToInvite = jQuery('.so-portlet-invite-members .email-invited .user');

		var receiverUserIds = [];
		var receiverEmailAddresses = [];

		userIdsToInvite.each(
			function(index) {
				user = jQuery(this);
			
				receiverUserIds.push(user.attr('data-userId'));
			}
		);

		emailAddressesToInvite.each(
			function(index) {
				emailAddress = jQuery(this);

				receiverEmailAddresses.push(emailAddress.attr('data-emailAddress'));
			}
		)

		receiverUserIds = receiverUserIds.join();
		receiverEmailAddresses = receiverEmailAddresses.join();

		for(var i = 0; i < data.length; i++){
			var obj = data[i];

			if(obj.name.indexOf('receiverUserIds') > -1){
				obj.value = receiverUserIds;
			}
			else if(obj.name.indexOf('receiverEmailAddresses') > -1){
				obj.value = receiverEmailAddresses;
			}
		}
	},

	removeEmailInvite: function(address) {
		address.remove();
	},

	removeMemberInvite: function(member) {
		var userId = member.attr('data-userId');

		var originalUser = member.data('originalUser');
		var invitedUser = member.data('invitedUser');

		if(originalUser){
			originalUser.removeClass('invited-user');
			member.remove();
		}
		else if(invitedUser){
			member.removeClass('invited-user');
			invitedUser.remove();
		}
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

	_assignEvents: function() {
		jQuery('.so-portlet-members .invite-members a').click(
			function() {
				jQuery('.so-portlet-profiles .profile-wrapper').hide();
				jQuery('.so-portlet-invite-members .invite-members-wrapper').show();
			}
		);
	},

	_setupEventHandler: function() {
		var instance = this;

		instance._emailInput = jQuery('#new-member-email-address');

		instance._emailInput.livequery(
			'keypress',
			function(event){
				if(event.which == 13){
					instance.addEmailInvite();
				}			
			}
		);

		jQuery('.so-portlet-invite-members').click(
			function (event) {
				var target = jQuery(event.target);
				var userTarget = jQuery([]);

				if (target.hasClass('user') || (userTarget = target.parents('.user:first')).length) {
					target = target.hasClass('user') ? target : userTarget;

					var userEmail = target.attr('data-emailAddress');
					var userId = target.attr('data-userId');

					if(userEmail){
						instance.removeEmailInvite(target);
					}
					else {
						if (target.parents('.uninvited:first').length) {
							if (target.hasClass('invited-user')) {
								instance.removeMemberInvite(target);
							}
							else {
								instance.addMemberInvite(target);
							}
						}
						else if (target.parents('.invited:first').length) {
							instance.removeMemberInvite(target);
						}
					}
				}
				else if(target.attr('id') == 'add-email-address') {
					instance._emailInput = jQuery('#new-member-email-address');
					instance.addEmailInvite();
				}
			}
		);
	},

	_setupLiveSearch: function() {
		var input = jQuery('#invite-user-search');

		var options = {
			list: jQuery('.so-portlet-invite-members .uninvited .user')
		};

		input.liveSearch(options);
	}
}