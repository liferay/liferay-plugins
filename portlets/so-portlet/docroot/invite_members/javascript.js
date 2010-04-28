AUI().use(
	'aui-base', 'aui-live-search',
	function(A) {
		Liferay.namespace('SO');

		Liferay.SO.InviteMembers = {
			init: function(params) {
				var instance = this;

				if (!instance._initialized) {
					instance._assignEvents();
					instance._setupEventHandler();

					instance._initialized = true;
				}
			},

			addEmailInvite: function() {
				var instance = this;

				var address = instance._emailInput;

				var emailAddress = A.Lang.trim(address.val());

				if (emailAddress) {
					var emailHTML = '<div class="user" data-emailAddress="' + emailAddress + '"><span class="email">' + emailAddress + '</span></div>';

					A.one('.so-portlet-invite-members .email-invited .list').append(emailHTML);

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
				var userIdsToInvite = A.all('.so-portlet-invite-members .invited .user');
				var emailAddressesToInvite = A.all('.so-portlet-invite-members .email-invited .user');

				var receiverUserIds = [];
				var receiverEmailAddresses = [];

				userIdsToInvite.each(
					function(user, index, users) {
						receiverUserIds.push(user.attr('data-userId'));
					}
				);

				emailAddressesToInvite.each(
					function(emailAddress, index, emailAddresses) {
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

				A.one('.so-portlet-invite-members #add-email-address').on(
					'click',
					function() {
						var emailAddress = A.one('.so-portlet-invite-members #new-member-email-address');

						instance.addEmailInvite(emailAddress);
					}
				);
			},

			_assignEvents: function() {
				A.one('.so-portlet-members .invite-members a').on(
					'click',
					function(event) {
						A.one('#so-profile-wrapper').hide();
						A.one('.so-portlet-invite-members .invite-members-wrapper').show();
					}
				);
			},

			_setupEventHandler: function() {
				var instance = this;

				// Live Search

				new A.LiveSearch(
					{
						input: '#invite-user-search',
						nodes: '.so-portlet-invite-members .uninvited .user'
					}
				);

				// Email Input

				instance._emailInput = A.one('#new-member-email-address');

				instance._emailInput.on(
					'keypress',
					function(event){
						if(event.which == 13){
							instance.addEmailInvite();
						}
					}
				);

				// User List

				A.one('.so-portlet-invite-members').on(
					'click',
					function (event) {
						var target = event.target;
						var userTarget = null;

						if (target.hasClass('user') || (userTarget = target.ancestor('.user'))) {
							target = target.hasClass('user') ? target : userTarget;

							var userEmail = target.attr('data-emailAddress');
							var userId = target.attr('data-userId');

							if(userEmail){
								instance.removeEmailInvite(target);
							}
							else {
								if (target.ancestor('.uninvited')) {
									if (target.hasClass('invited-user')) {
										instance.removeMemberInvite(target);
									}
									else {
										instance.addMemberInvite(target);
									}
								}
								else if (target.ancestor('.invited')) {
									instance.removeMemberInvite(target);
								}
							}
						}
						else if(target.attr('id') == 'add-email-address') {
							instance._emailInput = A.one('#new-member-email-address');
							instance.addEmailInvite();
						}
					}
				);
			}
		};
	}
);