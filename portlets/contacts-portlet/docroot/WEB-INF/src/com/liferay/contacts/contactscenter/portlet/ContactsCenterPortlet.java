/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.contacts.contactscenter.portlet;

import com.liferay.contacts.DuplicateEntryEmailAddressException;
import com.liferay.contacts.EntryEmailAddressException;
import com.liferay.contacts.model.Entry;
import com.liferay.contacts.service.EntryLocalServiceUtil;
import com.liferay.contacts.util.ContactsConstants;
import com.liferay.contacts.util.ContactsUtil;
import com.liferay.contacts.util.PortletKeys;
import com.liferay.contacts.util.SocialRelationConstants;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.ContactFirstNameException;
import com.liferay.portal.ContactFullNameException;
import com.liferay.portal.ContactLastNameException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.EmailAddressException;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.ReservedUserEmailAddressException;
import com.liferay.portal.ReservedUserScreenNameException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.UserScreenNameException;
import com.liferay.portal.UserSmsException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.bean.BeanParamUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.EmailAddressServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.comparator.UserLastNameComparator;
import com.liferay.portlet.announcements.model.AnnouncementsDelivery;
import com.liferay.portlet.announcements.service.AnnouncementsDeliveryLocalServiceUtil;
import com.liferay.portlet.social.NoSuchRelationException;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestConstants;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.service.SocialRelationLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestInterpreterLocalServiceUtil;
import com.liferay.portlet.social.service.SocialRequestLocalServiceUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ryan Park
 * @author Jonathan Lee
 * @author Eudaldo Alonso
 */
public class ContactsCenterPortlet extends MVCPortlet {

	public void addSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			boolean blocked = SocialRelationLocalServiceUtil.hasRelation(
				userId, themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY);

			if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				SocialRelationLocalServiceUtil.deleteRelations(
					themeDisplay.getUserId(), userId);
			}
			else if (blocked) {
				continue;
			}

			SocialRelationLocalServiceUtil.addRelation(
				themeDisplay.getUserId(), userId, type);

			if (blocked) {
				SocialRelationLocalServiceUtil.addRelation(
					userId, themeDisplay.getUserId(), type);
			}
		}
	}

	public void deleteSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			try {
				SocialRelationLocalServiceUtil.deleteRelation(
					themeDisplay.getUserId(), userId, type);
			}
			catch (NoSuchRelationException nsre) {
			}
		}
	}

	public void exportVCard(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		User user = UserServiceUtil.getUserById(userId);

		String vCard = ContactsUtil.getVCard(user);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, user.getFullName() + ".vcf",
			vCard.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void exportVCards(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long[] userIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "userIds"), 0L);

		List<User> users = new ArrayList<User>(userIds.length);

		for (long userId : userIds) {
			User user = UserServiceUtil.getUserById(userId);

			users.add(user);
		}

		String vCards = ContactsUtil.getVCards(users);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "vcards.vcf",
			vCards.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void getContact(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("success", true);

		JSONObject userJSONObject = getUserJSONObject(
			resourceResponse, themeDisplay, userId);

		jsonObject.put("user", userJSONObject);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	public void getContacts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject contactListJSONObject = getContactsJSONObject(
			resourceRequest, resourceResponse);

		writeJSON(resourceRequest, resourceResponse, contactListJSONObject);
	}

	public void getSelectedContacts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "userIds"), 0L);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long userId : userIds) {
			try {
				JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

				userJSONObject.put("success", true);
				userJSONObject.put(
					"user",
					getUserJSONObject(resourceResponse, themeDisplay, userId));

				jsonArray.put(userJSONObject);
			}
			catch (NoSuchUserException nsue) {
			}
		}

		jsonObject.put("contacts", jsonArray);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			boolean jsonFormat = ParamUtil.getBoolean(
				actionRequest, "jsonFormat");

			if (jsonFormat) {
				if (actionName.equals("addSocialRelation")) {
					addSocialRelation(actionRequest, actionResponse);
					}
				else if (actionName.equals("deleteSocialRelation")) {
					deleteSocialRelation(actionRequest, actionResponse);
				}
				else if (actionName.equals("requestSocialRelation")) {
					requestSocialRelation(actionRequest, actionResponse);
				}

				JSONObject jsonObject = getContactsDisplayJSONObject(
					actionRequest, actionResponse);

				writeJSON(actionRequest, actionResponse, jsonObject);
			}
			else if (actionName.equals("deleteEntry")) {
				deleteEntry(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateEntry")) {
				updateEntry(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateFieldGroup")) {
				updateFieldGroup(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void requestSocialRelation(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = getUserIds(actionRequest);

		int type = ParamUtil.getInteger(actionRequest, "type");

		for (long userId : userIds) {
			if (userId == themeDisplay.getUserId()) {
				continue;
			}

			if (SocialRelationLocalServiceUtil.hasRelation(
					userId, themeDisplay.getUserId(),
					SocialRelationConstants.TYPE_UNI_ENEMY)) {

				continue;
			}

			SocialRequest socialRequest =
				SocialRequestLocalServiceUtil.addRequest(
					themeDisplay.getUserId(), 0, User.class.getName(),
					themeDisplay.getUserId(), type, StringPool.BLANK, userId);

			sendNotificationEvent(socialRequest, themeDisplay);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("exportVCard")) {
				exportVCard(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("exportVCards")) {
				exportVCards(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getContact")) {
				getContact(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getContacts")) {
				getContacts(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getSelectedContacts")) {
				getSelectedContacts(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String comments = ParamUtil.getString(actionRequest, "comments");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String message = null;

		try {
			Entry entry = null;

			if (entryId > 0) {
				entry = EntryLocalServiceUtil.updateEntry(
					entryId, fullName, emailAddress, comments);

				message = "you-have-successfully-updated-the-contact";
			}
			else {
				entry = EntryLocalServiceUtil.addEntry(
					themeDisplay.getUserId(), fullName, emailAddress, comments);

				message = "you-have-successfully-added-a-new-contact";
			}

			jsonObject.put(
				"contact",
				getEntryJSONObject(
					actionResponse, themeDisplay, entry, redirect));

			JSONObject contactsJSONObject = getContactsJSONObject(
				actionRequest, actionResponse);

			jsonObject.put("contactList", contactsJSONObject);

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			if (e instanceof ContactFullNameException) {
				message = "full-name-cannot-be-empty";
			}
			else if (e instanceof DuplicateEntryEmailAddressException) {
				message = "there-is-already-a-contact-with-this-email-address";
			}
			else if (e instanceof EntryEmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else {
				message =
					"an-error-occurred-while-processing-the-requested-resource";
			}

			jsonObject.put("success", false);
		}

		jsonObject.put("message", translate(actionRequest, message));

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateFieldGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			String fieldGroup = ParamUtil.getString(
				actionRequest, "fieldGroup");

			if (fieldGroup.equals("additionalEmailAddresses")) {
				updateAdditionalEmailAddresses(actionRequest);
			}
			else if (fieldGroup.equals("addresses")) {
				updateAddresses(actionRequest);
			}
			else if (fieldGroup.equals("categorization")) {
				updateAsset(actionRequest);
			}
			else if (fieldGroup.equals("comments") ||
					fieldGroup.equals("details") ||
					fieldGroup.equals("instantMessenger") ||
					fieldGroup.equals("sms") ||
					fieldGroup.equals("socialNetwork")) {

				updateProfile(actionRequest);
			}
			else if (fieldGroup.equals("phoneNumbers")) {
				updatePhoneNumbers(actionRequest);
			}
			else if (fieldGroup.equals("websites")) {
				updateWebsites(actionRequest);
			}

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			jsonObject.put("redirect", redirect);

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			String message = "your-request-failed-to-complete";

			if (e instanceof AddressCityException) {
				message = "please-enter-a-valid-city";
			}
			else if (e instanceof AddressStreetException) {
				message = "please-enter-a-valid-street";
			}
			else if (e instanceof AddressZipException) {
				message = "please-enter-a-valid-postal-code";
			}
			else if (e instanceof ContactFirstNameException) {
				message = "please-enter-a-valid-first-name";
			}
			else if (e instanceof ContactFullNameException) {
				message = "please-enter-a-valid-first-middle-and-last-name";
			}
			else if (e instanceof ContactLastNameException) {
				message = "please-enter-a-valid-last-name";
			}
			else if (e instanceof DuplicateUserEmailAddressException) {
				message = "the-email-address-you-requested-is-already-taken";
			}
			else if (e instanceof EmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else if (e instanceof NoSuchCountryException) {
				message ="please-select-a-country";
			}
			else if (e instanceof NoSuchListTypeException) {
				message = "please-select-a-type";
			}
			else if (e instanceof NoSuchRegionException) {
				message = "please-select-a-region";
			}
			else if (e instanceof PhoneNumberException) {
				message = "please-enter-a-valid-phone-number";
			}
			else if (e instanceof ReservedUserEmailAddressException) {
				message = "the-email-address-you-requested-is-reserveds";
			}
			else if (e instanceof ReservedUserScreenNameException) {
				message = "the-screen-name-you-requested-is-reserved";
			}
			else if (e instanceof UserEmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else if (e instanceof UserScreenNameException) {
				message = "please-enter-a-valid-screen-name";
			}
			else if (e instanceof UserSmsException) {
				message = "please-enter-a-sms-id-that-is-a-valid-email-address";
			}
			else if (e instanceof WebsiteURLException) {
				message = "please-enter-a-valid-url";
			}

			jsonObject.put("message", translate(actionRequest, message));

			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateSocialRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long requestId = ParamUtil.getLong(actionRequest, "requestId");

		int status = ParamUtil.getInteger(actionRequest, "status");

		SocialRequest socialRequest =
			SocialRequestLocalServiceUtil.getSocialRequest(requestId);

		if (SocialRelationLocalServiceUtil.hasRelation(
				socialRequest.getReceiverUserId(), socialRequest.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			status = SocialRequestConstants.STATUS_IGNORE;
		}

		SocialRequestLocalServiceUtil.updateRequest(
			requestId, status, themeDisplay);

		String notificationEventUuid = ParamUtil.getString(
			actionRequest, "notificationEventUuid");

		ChannelHubManagerUtil.confirmDelivery(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(),
			notificationEventUuid, false);
	}

	protected void deleteEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		if (entryId > 0) {
			EntryLocalServiceUtil.deleteEntry(entryId);
		}
	}

	protected JSONObject getContactsDisplayJSONObject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] userIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "userIds"), 0L);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject contactListJSONObject = getContactsJSONObject(
			actionRequest, actionResponse);

		jsonObject.put("contactList", contactListJSONObject);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long userId : userIds) {
			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			userJSONObject.put("success", true);
			userJSONObject.put(
				"user",
				getUserJSONObject(actionResponse, themeDisplay, userId));

			jsonArray.put(userJSONObject);
		}

		jsonObject.put("contacts", jsonArray);

		String message = getRelationMessage(actionRequest);

		jsonObject.put("message", translate(actionRequest, message));

		return jsonObject;
	}

	protected JSONObject getContactsJSONObject(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(portletRequest, "redirect");

		String filterBy = ParamUtil.getString(portletRequest, "filterBy");
		String keywords = ParamUtil.getString(portletRequest, "keywords");
		int start = ParamUtil.getInteger(portletRequest, "start");
		int end = ParamUtil.getInteger(portletRequest, "end");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject optionsJSONObject = JSONFactoryUtil.createJSONObject();

		optionsJSONObject.put("end", end);
		optionsJSONObject.put("filterBy", filterBy);
		optionsJSONObject.put("keywords", keywords);
		optionsJSONObject.put("start", start);

		jsonObject.put("options", optionsJSONObject);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletName = portletDisplay.getPortletName();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if (filterBy.equals(ContactsConstants.FILTER_BY_DEFAULT) &&
			!portletName.equals(PortletKeys.MEMBERS)) {

			List<BaseModel<?>> contacts =
				EntryLocalServiceUtil.searchUsersAndContacts(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, start, end);

			int contactsCount =
				EntryLocalServiceUtil.searchUsersAndContactsCount(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords);

			jsonObject.put("count", contactsCount);

			for (BaseModel<?> contact : contacts) {
				JSONObject contactJSONObject = null;

				if (contact instanceof User) {
					contactJSONObject = getUserJSONObject(
						portletResponse, themeDisplay, (User)contact);
				}
				else {
					contactJSONObject = getEntryJSONObject(
						portletResponse, themeDisplay, (Entry)contact,
						redirect);
				}

				jsonArray.put(contactJSONObject);
			}
		}
		else if (filterBy.equals(
					ContactsConstants.FILTER_BY_TYPE_MY_CONTACTS) &&
				 !portletName.equals(PortletKeys.MEMBERS)) {

			List<Entry> entries = EntryLocalServiceUtil.search(
				themeDisplay.getUserId(), keywords, start, end);

			int entriesCount = EntryLocalServiceUtil.searchCount(
				themeDisplay.getUserId(), keywords);

			jsonObject.put("count", entriesCount);

			for (Entry entry : entries) {
				JSONObject contactJSONObject = getEntryJSONObject(
					portletResponse, themeDisplay, entry, redirect);

				jsonArray.put(contactJSONObject);
			}
		}
		else {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			Group group = themeDisplay.getScopeGroup();
			Layout layout = themeDisplay.getLayout();

			if (group.isUser() && layout.isPublicLayout()) {
				params.put("socialRelation", new Long[] {group.getClassPK()});
			}
			else if (filterBy.startsWith(ContactsConstants.FILTER_BY_TYPE)) {
				params.put(
					"socialRelationType",
					new Long[] {
						themeDisplay.getUserId(),
						ContactsUtil.getSocialRelationType(filterBy)
					});
			}

			if (portletName.equals(PortletKeys.MEMBERS)) {
				params.put("usersGroups", group.getGroupId());
			}
			else if (filterBy.startsWith(ContactsConstants.FILTER_BY_GROUP)) {
				params.put("usersGroups", ContactsUtil.getGroupId(filterBy));
			}

			int usersCount = UserLocalServiceUtil.searchCount(
				themeDisplay.getCompanyId(), keywords,
				WorkflowConstants.STATUS_APPROVED, params);

			jsonObject.put("count", usersCount);

			List<User> users = UserLocalServiceUtil.search(
				themeDisplay.getCompanyId(), keywords,
				WorkflowConstants.STATUS_APPROVED, params, start, end,
				new UserLastNameComparator(true));

			for (User user : users) {
				JSONObject userJSONObject = getUserJSONObject(
					portletResponse, themeDisplay, user);

				jsonArray.put(userJSONObject);
			}
		}

		jsonObject.put("users", jsonArray);

		return jsonObject;
	}

	protected JSONObject getEntryJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			Entry entry, String redirect)
		throws Exception {

		entry = entry.toEscapedModel();

		JSONObject contactJSONObject = JSONFactoryUtil.createJSONObject();

		contactJSONObject.put("emailAddress", entry.getEmailAddress());
		contactJSONObject.put("entryId", String.valueOf(entry.getEntryId()));
		contactJSONObject.put("comments", entry.getComments());
		contactJSONObject.put("fullName", entry.getFullName());
		contactJSONObject.put("portalUser", false);
		contactJSONObject.put(
			"portraitURL",
			themeDisplay.getPathImage() + "/user_male_portrait?img_id=0");
		contactJSONObject.put("redirect", redirect);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)portletResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("redirect", redirect);
		viewSummaryURL.setParameter(
			"entryId", String.valueOf(entry.getEntryId()));
		viewSummaryURL.setParameter("portalUser", Boolean.FALSE.toString());
		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		contactJSONObject.put("viewSummaryURL", viewSummaryURL.toString());

		return contactJSONObject;
	}

	protected String getRelationMessage(ActionRequest actionRequest) {
		int type = ParamUtil.getInteger(actionRequest, "type");

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		String message = "your-request-completed-successfully";

		if (actionName.equals("addSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message = "you-are-now-connected-to-this-user";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
				message = "you-are-now-following-this-user";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				message = "you-have-blocked-this-user";
			}
		}
		else if (actionName.equals("deleteSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message = "you-are-not-connected-to-this-user-anymore";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_FOLLOWER) {
				message = "you-are-not-following-this-user-anymore";
			}
			else if (type == SocialRelationConstants.TYPE_UNI_ENEMY) {
				message = "you-have-unblocked-this-user";
			}
		}
		else if (actionName.equals("requestSocialRelation")) {
			if (type == SocialRelationConstants.TYPE_BI_CONNECTION) {
				message =
					"this-user-has-received-a-connection-request-from-you";
			}
		}

		return message;
	}

	protected long[] getUserIds(ActionRequest actionRequest) {
		long[] userIds;

		long userId = ParamUtil.getLong(actionRequest, "userId", 0);

		if (userId > 0) {
			userIds = new long[] {userId};
		}
		else {
			userIds = StringUtil.split(
				ParamUtil.getString(actionRequest, "userIds"), 0L);
		}

		return userIds;
	}

	protected JSONObject getUserJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			long userId)
		throws Exception {

		User user = UserLocalServiceUtil.getUser(userId);

		return getUserJSONObject(portletResponse, themeDisplay, user);
	}

	protected JSONObject getUserJSONObject(
			PortletResponse portletResponse, ThemeDisplay themeDisplay,
			User user)
		throws Exception {

		user = user.toEscapedModel();

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		boolean block = SocialRelationLocalServiceUtil.hasRelation(
			themeDisplay.getUserId(), user.getUserId(),
			SocialRelationConstants.TYPE_UNI_ENEMY);

		userJSONObject.put("block", block);

		userJSONObject.put("emailAddress", user.getEmailAddress());
		userJSONObject.put("firstName", user.getFirstName());
		userJSONObject.put("fullName", user.getFullName());
		userJSONObject.put("jobTitle", user.getJobTitle());
		userJSONObject.put("lastName", user.getLastName());
		userJSONObject.put("portalUser", true);
		userJSONObject.put("portraitURL", user.getPortraitURL(themeDisplay));
		userJSONObject.put("userId", String.valueOf(user.getUserId()));

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)portletResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("userId", String.valueOf(user.getUserId()));
		viewSummaryURL.setParameter("portalUser", Boolean.TRUE.toString());
		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		userJSONObject.put("viewSummaryURL", viewSummaryURL.toString());

		if (!SocialRelationLocalServiceUtil.hasRelation(
				user.getUserId(), themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY) &&
			!SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), user.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			boolean connectionRequested =
				SocialRequestLocalServiceUtil.hasRequest(
					themeDisplay.getUserId(), User.class.getName(),
					themeDisplay.getUserId(),
					SocialRelationConstants.TYPE_BI_CONNECTION,
					user.getUserId(), SocialRequestConstants.STATUS_PENDING);

			userJSONObject.put("connectionRequested", connectionRequested);

			boolean connected =
				!connectionRequested &&
				SocialRelationLocalServiceUtil.hasRelation(
					themeDisplay.getUserId(), user.getUserId(),
					SocialRelationConstants.TYPE_BI_CONNECTION);

			userJSONObject.put("connected", connected);

			boolean following =
				SocialRelationLocalServiceUtil.hasRelation(
					themeDisplay.getUserId(), user.getUserId(),
					SocialRelationConstants.TYPE_UNI_FOLLOWER);

			userJSONObject.put("following", following);
		}

		return userJSONObject;
	}

	protected void sendNotificationEvent(
			SocialRequest socialRequest, ThemeDisplay themeDisplay)
		throws Exception {

		JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		SocialRequestFeedEntry socialRequestFeedEntry =
			SocialRequestInterpreterLocalServiceUtil.interpret(
				socialRequest, themeDisplay);

		notificationEventJSONObject.put("portletId", "1_WAR_contactsportlet");
		notificationEventJSONObject.put(
			"requestId", socialRequest.getRequestId());
		notificationEventJSONObject.put(
			"title", socialRequestFeedEntry.getTitle());
		notificationEventJSONObject.put("userId", socialRequest.getUserId());

		NotificationEvent notificationEvent =
			NotificationEventFactoryUtil.createNotificationEvent(
				System.currentTimeMillis(), "6_WAR_soportlet",
				notificationEventJSONObject);

		notificationEvent.setDeliveryRequired(0);

		ChannelHubManagerUtil.sendNotificationEvent(
			socialRequest.getCompanyId(), socialRequest.getReceiverUserId(),
			notificationEvent);
	}

	protected String translate(PortletRequest portletRequest, String key) {
		PortletConfig portletConfig =
			(PortletConfig)portletRequest.getAttribute(
				JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return LanguageUtil.get(portletConfig, themeDisplay.getLocale(), key);
	}

	protected void updateAdditionalEmailAddresses(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<EmailAddress> emailAddresses = UsersAdminUtil.getEmailAddresses(
			actionRequest);

		UsersAdminUtil.updateEmailAddresses(
			Contact.class.getName(), user.getContactId(), emailAddresses);
	}

	protected void updateAddresses(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Address> addresses = UsersAdminUtil.getAddresses(actionRequest);

		UsersAdminUtil.updateAddresses(
			Contact.class.getName(), user.getContactId(), addresses);
	}

	protected void updateAsset(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		long[] assetCategoryIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetCategoryNames"), 0L);
		String[] assetTagNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "assetTagNames"));

		UserLocalServiceUtil.updateAsset(
			user.getUserId(), user, assetCategoryIds, assetTagNames);
	}

	protected void updatePhoneNumbers(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Phone> phones = UsersAdminUtil.getPhones(actionRequest);

		UsersAdminUtil.updatePhones(
			Contact.class.getName(), user.getContactId(), phones);
	}

	protected void updateProfile(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		if (deleteLogo) {
			UserServiceUtil.deletePortrait(user.getUserId());
		}

		String comments = BeanParamUtil.getString(
			user, actionRequest, "comments");
		String emailAddress = BeanParamUtil.getString(
			user, actionRequest, "emailAddress");
		String firstName = BeanParamUtil.getString(
			user, actionRequest, "firstName");
		String jobTitle = BeanParamUtil.getString(
			user, actionRequest, "jobTitle");
		String lastName = BeanParamUtil.getString(
			user, actionRequest, "lastName");
		String middleName = BeanParamUtil.getString(
			user, actionRequest, "middleName");
		String screenName = BeanParamUtil.getString(
			user, actionRequest, "screenName");

		Contact contact = user.getContact();

		String aimSn = BeanParamUtil.getString(contact, actionRequest, "aimSn");
		String facebookSn = BeanParamUtil.getString(
			contact, actionRequest, "facebookSn");
		String icqSn = BeanParamUtil.getString(contact, actionRequest, "icqSn");
		String jabberSn = BeanParamUtil.getString(
			contact, actionRequest, "jabberSn");
		String msnSn = BeanParamUtil.getString(contact, actionRequest, "msnSn");
		String mySpaceSn = BeanParamUtil.getString(
			contact, actionRequest, "mySpaceSn");
		String skypeSn = BeanParamUtil.getString(
			contact, actionRequest, "skypeSn");
		String smsSn = BeanParamUtil.getString(contact, actionRequest, "smsSn");
		String twitterSn = BeanParamUtil.getString(
			contact, actionRequest, "twitterSn");
		String ymSn = BeanParamUtil.getString(contact, actionRequest, "ymSn");

		Calendar cal = CalendarFactoryUtil.getCalendar();

		cal.setTime(user.getBirthday());

		int birthdayDay = cal.get(Calendar.DATE);
		int birthdayMonth = cal.get(Calendar.MONTH);
		int birthdayYear = cal.get(Calendar.YEAR);

		List<UserGroupRole> userGroupRoles = UsersAdminUtil.getUserGroupRoles(
			actionRequest);
		List<EmailAddress> emailAddresses =
			EmailAddressServiceUtil.getEmailAddresses(
				Contact.class.getName(), contact.getContactId());
		List<AnnouncementsDelivery> announcementsDeliveries =
			AnnouncementsDeliveryLocalServiceUtil.getUserDeliveries(
				user.getUserId());

		UserServiceUtil.updateUser(
			user.getUserId(), user.getPasswordUnencrypted(),
			user.getPasswordUnencrypted(), user.getPasswordUnencrypted(),
			user.getPasswordReset(), user.getReminderQueryQuestion(),
			user.getReminderQueryAnswer(), screenName, emailAddress,
			user.getFacebookId(), user.getOpenId(), user.getLanguageId(),
			user.getTimeZoneId(), user.getGreeting(), comments, firstName,
			middleName, lastName, contact.getPrefixId(), contact.getSuffixId(),
			user.isMale(), birthdayMonth, birthdayDay, birthdayYear, smsSn,
			aimSn, facebookSn, icqSn, jabberSn, msnSn, mySpaceSn, skypeSn,
			twitterSn, ymSn, jobTitle, user.getGroupIds(),
			user.getOrganizationIds(), user.getRoleIds(), userGroupRoles,
			user.getUserGroupIds(), user.getAddresses(), emailAddresses,
			user.getPhones(), user.getWebsites(), announcementsDeliveries,
			new ServiceContext());
	}

	protected void updateWebsites(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		List<Website> websites = UsersAdminUtil.getWebsites(actionRequest);

		UsersAdminUtil.updateWebsites(
			Contact.class.getName(), user.getContactId(), websites);
	}

}