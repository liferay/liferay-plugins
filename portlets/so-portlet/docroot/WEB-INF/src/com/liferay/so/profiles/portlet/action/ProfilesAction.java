/**
 * Copyright (c) 2008-2009 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.profiles.portlet.action;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.ContactLocalServiceUtil;
import com.liferay.portal.service.PhoneLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.WebsiteLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.tags.service.TagsAssetLocalServiceUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * <a href="ProfilesAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brett Swaim
 *
 */
public class ProfilesAction extends JSPPortlet {

	public void doView(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		super.doView(renderRequest, renderResponse);
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		String id = ParamUtil.getString(actionRequest,  "id");
		String value = ParamUtil.getString(actionRequest, "value");

		try {
			if (id.equals("addAddress")) {
				updateAddress(actionRequest, user);
			}
			else if (id.equals("addPhoneNumber")) {
				updatePhoneNumber(actionRequest, user);
			}
			else if (id.equals("addWebsite")) {
				updateWebsite(actionRequest, user);
			}
			else if (id.equals("aim")) {
				updateAim(user, value);
			}
			else if (id.equals("email")) {
				updateEmail(user, value);
			}
			else if (id.equals("facebook")) {
				updateFacebook(user, value);
			}
			else if (id.equals("firstName")) {
				updateFirstName(user, value);
			}
			else if (id.equals("icq")) {
				updateIcq(user, value);
			}
			else if (id.equals("jabber")) {
				updateJabber(user, value);
			}
			else if (id.equals("lastName")) {
				updateLastName(user, value);
			}
			else if (id.equals("msn")) {
				updateMsn(user, value);
			}
			else if (id.equals("myspace")) {
				updateMyspace(user, value);
			}
			else if (id.equals("skype")) {
				updateSkype(user, value);
			}
			else if (id.equals("title")) {
				updateTitle(user, value);
			}
			else if (id.equals("twitter")) {
				updateTwitter(user, value);
			}
			else if (id.equals("updateAddress")) {
				updateAddress(actionRequest, user);
			}
			else if (id.equals("updatePhoneNumber")) {
				updatePhoneNumber(actionRequest, user);
			}
			else if (id.equals("updateTags")) {
				updateTags(actionRequest, themeDisplay, user);
			}
			else if (id.equals("updateWebsite")) {
				updateWebsite(actionRequest, user);
			}
			else if (id.equals("ym")) {
				updateYm(user, value);
			}
		} catch (SystemException se) {
			if (_log.isErrorEnabled()) {
				_log.error(se);
			}
		}
	}

	protected void updateAddress(ActionRequest actionRequest, User user) {
		int addressTypeId = ParamUtil.getInteger(
			actionRequest, "addressTypeId");

		long addressCountryId = ParamUtil.getLong(
			actionRequest, "addressCountryId");
		long addressRegionId = ParamUtil.getLong(
			actionRequest, "addressRegionId");
		long classPk = user.getContactId();
		long userId = user.getUserId();

		String city = ParamUtil.getString(actionRequest, "city");
		String className = Contact.class.getName();
		String id = ParamUtil.getString(actionRequest, "id");
		String street1 = ParamUtil.getString(actionRequest, "street1");
		String street2 = ParamUtil.getString(actionRequest, "street2");
		String zip = ParamUtil.getString(actionRequest, "zip");

		if (id.equals("addAddress")) {
			try {
				AddressLocalServiceUtil.addAddress(
					userId, className, classPk, street1, street2,
					StringPool.BLANK, city, zip, addressRegionId,
					addressCountryId, addressTypeId, false, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
		else {
			try {
				long addressId = ParamUtil.getLong(actionRequest, "addressId");

				Address address = AddressLocalServiceUtil.getAddress(addressId);

				address.setStreet1(street1);
				address.setStreet2(street2);
				address.setCity(city);
				address.setZip(zip);
				address.setRegionId(addressRegionId);
				address.setCountryId(addressCountryId);
				address.setTypeId(addressTypeId);

				AddressLocalServiceUtil.updateAddress(address, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	protected void updateAim(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setAimSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateEmail(User user, String value) throws SystemException {
		user.setEmailAddress(value);

		UserLocalServiceUtil.updateUser(user, false);
	}

	protected void updateFacebook(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setFacebookSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateFirstName(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setFirstName(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateIcq(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setIcqSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateJabber(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setJabberSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateLastName(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setLastName(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateMsn(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setMsnSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateMyspace(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setMySpaceSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updatePhoneNumber(ActionRequest actionRequest, User user) {
		int typeId = ParamUtil.getInteger(actionRequest, "type");

		String extension = ParamUtil.getString(actionRequest, "extension");
		String id = ParamUtil.getString(actionRequest, "id");
		String number = ParamUtil.getString(actionRequest, "number");

		if (id.equals("addPhoneNumber")) {
			try {
				PhoneLocalServiceUtil.addPhone(
					user.getUserId(), Contact.class.getName(),
					user.getContactId(), number, extension, typeId, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
		else {
			try {
				long phoneId = ParamUtil.getLong(actionRequest, "phoneId");

				Phone phone = PhoneLocalServiceUtil.getPhone(phoneId);

				phone.setNumber(number);
				phone.setExtension(extension);
				phone.setTypeId(typeId);

				PhoneLocalServiceUtil.updatePhone(phone, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	protected void updateSkype(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setSkypeSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateTags(
		ActionRequest actionRequest, ThemeDisplay themeDisplay, User user) {

		String tags = ParamUtil.getString(actionRequest, "tagsEntries");

		try {
			TagsAssetLocalServiceUtil.updateAsset(
				user.getUserId(), themeDisplay.getLayout().getGroupId(),
				User.class.getName(), user.getUserId(), new String[]{""},
				tags.split(","));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e);
			}
		}
	}

	protected void updateTitle(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setJobTitle(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateTwitter(User user, String value)
		throws SystemException {

		Contact contact = user.getContact();

		contact.setTwitterSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	protected void updateWebsite(ActionRequest actionRequest, User user) {
		int type = ParamUtil.getInteger(actionRequest, "type");

		String id = ParamUtil.getString(actionRequest, "id");
		String url = ParamUtil.getString(actionRequest, "url");

		if (id.equals("addWebsite")) {
			try {
				WebsiteLocalServiceUtil.addWebsite(
					user.getUserId(), Contact.class.getName(),
					user.getContactId(), url, type, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
		else {
			try {
				long websiteId = ParamUtil.getLong(actionRequest, "websiteId");

				Website website = WebsiteLocalServiceUtil.getWebsite(websiteId);

				website.setUrl(url);
				website.setTypeId(type);

				WebsiteLocalServiceUtil.updateWebsite(website, false);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	protected void updateYm(User user, String value) throws SystemException {
		Contact contact = user.getContact();

		contact.setYmSn(value);

		ContactLocalServiceUtil.updateContact(contact, false);
	}

	private static Log _log = LogFactoryUtil.getLog(ProfilesAction.class);

}