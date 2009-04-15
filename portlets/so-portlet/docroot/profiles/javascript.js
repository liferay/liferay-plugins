Liferay.SO = Liferay.SO || {};

Liferay.SO.Profiles = {
	init: function(params) {
		var instance = this;

		instance._namespace = params.namespace;
		instance._postURL = params.postURL;

		instance._portletContainer = jQuery('#p_p_id' + instance._namespace);

		instance._assignGuestEvents();

		if (themeDisplay.isSignedIn()) {
			instance._assignPrivilegedEvents();
			instance._populateCountryAndRegion();
		}
	},

	_assignGuestEvents: function() {
		var instance = this;

		jQuery('.so-portlet-members .user').click(
			function() {
				var userId = jQuery(this).attr('data-userId');

				instance._displayUserProfile(userId);
			}
		);
	},

	_assignPrivilegedEvents: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.add-address-button').click(
			function() {
				instance._showEditAddress();
			}
		);

		portletContainer.find('.add-phone-number-button').click(
			function() {
				instance._showEditPhoneNumber();
			}
		);

		portletContainer.find('.add-website-button').click(
			function() {
				instance._showEditWebsite();
			}
		);

		portletContainer.find('.cancel-address-button').click(
			function() {
				instance._hideAddress();
				instance._resetAddress();
			}
		);

		portletContainer.find('.cancel-phone-number-button').click(
			function() {
				instance._hidePhoneNumber();
				instance._resetPhoneNumber();
			}
		);

		portletContainer.find('.cancel-user-password-button').click(
			function() {
				instance._hideUserPassword(jQuery(this));
			}
		);

		portletContainer.find('.cancel-website-button').click(
			function() {
				instance._hideWebsite();
				instance._resetWebsite();
			}
		);

		portletContainer.find('.change-user-password-button').click(
			function() {
				instance._showEditUserPassword(jQuery(this));
			}
		);

		portletContainer.find('.delete').click(
			function () {
				var row = jQuery(this).parents('tr:first');
				var id = jQuery(this).prev().attr('id');

				jQuery.ajax({
					url: instance._postURL,
					data: {
						id: id,
						value: ''
					},
					success: function() {
						row.remove();
					}
				});
			}
		);

		portletContainer.find('.delete-address').click(
			function() {
				instance._deleteAddress(jQuery(this));
			}
		);

		portletContainer.find('.delete-phone-number').click(
			function() {
				instance._deletePhoneNumber(jQuery(this));
			}
		);

		portletContainer.find('.delete-website').click(
			function() {
				instance._deleteWebsite(jQuery(this));
			}
		);

		portletContainer.find('.edit-address').click(
			function() {
				instance._editAddress(jQuery(this));
			}
		);

		portletContainer.find('.edit-phone-number').click(
			function() {
				instance._editPhoneNumber(jQuery(this));
			}
		);

		portletContainer.find('.edit-website').click(
			function() {
				instance._editWebsite(jQuery(this));
			}
		);

		portletContainer.find('.editable').parent().hover(
			function() {
				var field = jQuery(this);

				if (!field.find('.editable >form').length) {
					field.addClass('hover');
				}
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		jQuery('.editable input').livequery(
			function() {
				this.select();
			}
		);

		portletContainer.find('.editable').editable(
			function(value, settings) {
				var id = this.id;

				if (id) {
					var field = jQuery(this);

					jQuery.ajax({
						url: instance._postURL,
						data: {
							id: id,
							value: value
						},
						success: function() {
							field.removeClass('portlet-msg-info');
							field.parents('tr:first').next().find('.editable:first').trigger('click');
						}
					});
				}

				return value;
			},
			{
				type      : 'text',
				cancel    : 'Cancel',
				submit    : 'OK',
				style     : 'display:inline',
				data: function(value, settings) {
					jQuery(this).parent().removeClass('hover');

					return value;
				},
				tooltip   : 'Click to edit...'
			}
		);

		portletContainer.find('.phone-number').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		portletContainer.find('.phone-number-controls').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		portletContainer.find('.user-address').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		portletContainer.find('.user-address-controls').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		portletContainer.find('.website').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);

		portletContainer.find('.website-controls').hover(
			function() {
				jQuery(this).addClass('hover');
			},
			function() {
				jQuery(this).removeClass('hover');
			}
		);
	},

	_deleteAddress: function(obj) {
		var instance = this;

		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'address', '');

		Liferay.Service.Portal.Address.deleteAddress(
				{
					addressId: id
				}
			);

		obj.parent().parent().remove();
	},

	_deletePhoneNumber: function(obj) {
		var instance = this;

		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'phonenumber', '');

		Liferay.Service.Portal.Phone.deletePhone(
				{
					phoneId: id
				}
			);

		obj.parents('tr:first').remove();
	},

	_deleteWebsite: function(obj) {
		var instance = this;

		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'website', '');

		Liferay.Service.Portal.Website.deleteWebsite(
				{
					websiteId: id
				}
			);

		obj.parent().parent().remove();
	},

	_displayProfilePage: function(userId, page) {
		jQuery('.so-portlet-invite-members .invite-members-wrapper').hide();
		jQuery('.so-portlet-profiles .profile-wrapper').show();

		jQuery.ajax(
			{
				url: themeDisplay.getLayoutURL() + '/-/profiles/' + page,
				data: {userId: userId},
				success: function(result) {
					jQuery('.profile-wrapper').html(result);
				},
				type: 'POST'
			}
		);
	},

	_displayUserProfile: function(userId) {
		var instance = this;

		instance._displayProfilePage(userId, 'user_profile');
	},

	_editAddress: function(obj) {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;

		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'address', '');

		var address = Liferay.Service.Portal.Address.getAddress({
				addressId: id
			}
		);

		new Liferay.DynamicSelect(
				[
					{
						select: instance._namespace + 'addressRegionId',
						selectId: 'regionId',
						selectDesc: 'name',
						selectVal: address.regionId,
						selectData: function(callback, selectKey) {
							Liferay.Service.Portal.Region.getRegions(
								{
									countryId: address.countryId,
									active: true
								},
								callback
							);
						}
					}
				]
			);

		obj.removeClass('hover');

		portletContainer.find('#' + namespace + 'addressCmd').val('updateAddress');
		portletContainer.find('#' + namespace + 'addressId').val(id);
		portletContainer.find('#' + namespace + 'street1').val(address.street1);
		portletContainer.find('#' + namespace + 'street2').val(address.street2);
		portletContainer.find('#' + namespace + 'city').val(address.city);
		portletContainer.find('#' + namespace + 'zip').val(address.zip);

		jQuery('#' + namespace + 'addressCountryId').find('option[value='+ address.countryId + ']')[0].selected=true;
		jQuery('#' + namespace + 'addressTypeId').find('option[value='+ address.typeId + ']')[0].selected=true;

		instance._showEditAddress();
	},

	_editPhoneNumber: function(obj) {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;
		
		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'phonenumber', '');

		var phone = Liferay.Service.Portal.Phone.getPhone({
				phoneId: id
			}
		);

		portletContainer.find('#' + namespace + 'phoneCmd').val('updatePhoneNumber');
		portletContainer.find('#' + namespace + 'phoneId').val(id);
		portletContainer.find('#' + namespace + 'number').val(phone.number);

		jQuery('#' + namespace + 'extension').val(phone.extension);
		jQuery('#' + namespace + 'phoneType').find('option[value='+ phone.typeId + ']')[0].selected=true;

		instance._showEditPhoneNumber();
	},

	_editWebsite: function(obj) {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;

		var id = obj.parent().attr('id');

		id = id.replace(instance._namespace + 'website', '');

		var website = Liferay.Service.Portal.Website.getWebsite({
				websiteId: id
			}
		);

		portletContainer.find('#' + namespace + 'websiteCmd').val('updateWebsite');
		portletContainer.find('#' + namespace + 'websiteId').val(id);
		portletContainer.find('#' + namespace + 'url').val(website.url);

		jQuery('#' + namespace + 'websiteType').find('option[value='+ website.typeId + ']')[0].selected=true;

		instance._showEditWebsite();
	},

	_hideAddress: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.address-form').hide();
		portletContainer.find('.add-address').show();
	},

	_hidePhoneNumber: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.phone-number-form').hide();
		portletContainer.find('.add-phone-number').show();
	},

	_hideUserPassword: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.user-password-form').hide();
		portletContainer.find('.change-user-password-button').show();
	},

	_hideWebsite: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.website-form').hide();
		portletContainer.find('.add-website').show();
	},

	_populateCountryAndRegion: function() {
		var instance = this;

		new Liferay.DynamicSelect(
				[
					{
						select: instance._namespace + 'addressCountryId',
						selectId: 'countryId',
						selectDesc: 'name',
						selectData: function(callback) {
							Liferay.Service.Portal.Country.getCountries(
								{
									active: true
								},
								callback
							);
						}
					},
					{
						select: instance._namespace + 'addressRegionId',
						selectId: 'regionId',
						selectDesc: 'name',
						selectData: function(callback, selectKey) {
							Liferay.Service.Portal.Region.getRegions(
								{
									countryId: selectKey,
									active: true
								},
								callback
							);
						}
					}
				]
			);
	},

	_resetAddress: function() {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;

		portletContainer.find('#' + namespace + 'addressCmd').val('addAddress');
		portletContainer.find('#' + namespace + 'addressId').val('');
		portletContainer.find('#' + namespace + 'street1').val('');
		portletContainer.find('#' + namespace + 'street2').val('');
		portletContainer.find('#' + namespace + 'city').val('');
		portletContainer.find('#' + namespace + 'zip').val('');

		jQuery('#' + namespace + 'addressCountryId').find('option[value=0]')[0].selected=true;
		jQuery('#' + namespace + 'addressRegionId').find('option[value=0]')[0].selected=true;
	},

	_resetPhoneNumber: function() {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;


		portletContainer.find('#' + namespace + 'phoneCmd').val('addPhoneNumber');
		portletContainer.find('#' + namespace + 'phoneId').val('');
		portletContainer.find('#' + namespace + 'number').val('');
		portletContainer.find('#' + namespace + 'extension').val('');

		instance._showEditPhoneNumber();
	},

	_resetWebsite: function() {
		var instance = this;

		var namespace = instance._namespace;
		var portletContainer = instance._portletContainer;

		portletContainer.find('#' + namespace + 'websiteCmd').val('addWebsite');
		portletContainer.find('#' + namespace + 'websiteId').val('');
		portletContainer.find('#' + namespace + 'url').val('');

		instance._showEditWebsite();
	},

	_showEditAddress: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.address-form').show();
		portletContainer.find('.add-address').hide();
	},

	_showEditPhoneNumber: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.phone-number-form').show();
		portletContainer.find('.add-phone-number').hide();
	},

	_showEditUserPassword: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.user-password-form').show();
		portletContainer.find('.change-user-password-button').hide();
	},

	_showEditWebsite: function() {
		var instance = this;

		var portletContainer = instance._portletContainer;

		portletContainer.find('.website-form').show();
		portletContainer.find('.add-website').hide();
	}
};