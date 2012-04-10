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
import com.liferay.contacts.util.ContactsUtil;
import com.liferay.contacts.util.PortletKeys;
import com.liferay.contacts.util.SocialRelationConstants;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.ContactFullNameException;
import com.liferay.portal.EmailAddressException;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchListTypeException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.UserSmsException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.ChannelHubManagerUtil;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
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
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			request, response, user.getFullName() + ".vcf",
			vCard.getBytes(StringPool.UTF8), "text/x-vcard; charset=UTF-8");
	}

	public void exportVCards(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long[] userIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "userIds"), 0L);

		List<User> users = new ArrayList<User>();

		for (long userId : userIds) {
			users.add(UserServiceUtil.getUserById(userId));
		}

		String vCards = ContactsUtil.getVCards(users);

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);
		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			resourceResponse);

		ServletResponseUtil.sendFile(
			request, response, "vcards.vcf", vCards.getBytes(StringPool.UTF8),
			"text/x-vcard; charset=UTF-8");
	}

	public void getContact(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(resourceRequest, "userId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("success", true);

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		User user = UserLocalServiceUtil.getUser(userId);

		boolean viewRelationActions = true;

		if (SocialRelationLocalServiceUtil.hasRelation(
				userId, themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_UNI_ENEMY)) {

			viewRelationActions = false;
		}
		else if (SocialRelationLocalServiceUtil.hasRelation(
					themeDisplay.getUserId(), userId,
					SocialRelationConstants.TYPE_UNI_ENEMY)) {

			viewRelationActions = false;
		}

		boolean block = SocialRelationLocalServiceUtil.hasRelation(
			themeDisplay.getUserId(), userId,
			SocialRelationConstants.TYPE_UNI_ENEMY);

		userJSONObject.put("block", block);

		boolean connectionRequested =
			viewRelationActions &&
			SocialRequestLocalServiceUtil.hasRequest(
				themeDisplay.getUserId(), User.class.getName(),
				themeDisplay.getUserId(),
				SocialRelationConstants.TYPE_BI_CONNECTION, userId,
				SocialRequestConstants.STATUS_PENDING);

		userJSONObject.put("connectionRequested", connectionRequested);

		boolean connected =
			!connectionRequested &&
			viewRelationActions &&
			SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), userId,
				SocialRelationConstants.TYPE_BI_CONNECTION);

		userJSONObject.put("connected", connected);

		userJSONObject.put("emailAddress", user.getEmailAddress());
		userJSONObject.put("firstName", user.getFirstName());

		boolean following =
			viewRelationActions &&
			SocialRelationLocalServiceUtil.hasRelation(
				themeDisplay.getUserId(), userId,
				SocialRelationConstants.TYPE_UNI_FOLLOWER);

		userJSONObject.put("following", following);

		userJSONObject.put("fullName", user.getFullName());
		userJSONObject.put("jobTitle", user.getJobTitle());
		userJSONObject.put("lastName", user.getLastName());
		userJSONObject.put("portraitURL", user.getPortraitURL(themeDisplay));
		userJSONObject.put("userId", String.valueOf(user.getUserId()));

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("userId", String.valueOf(user.getUserId()));
		viewSummaryURL.setParameter("portalUser", Boolean.TRUE.toString());

		userJSONObject.put("viewSummaryURL", viewSummaryURL.toString());

		jsonObject.put("user", userJSONObject);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	public void getContacts(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(resourceRequest, "redirect");

		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		int socialRelationType = ParamUtil.getInteger(
			resourceRequest, "socialRelationType");
		int start = ParamUtil.getInteger(resourceRequest, "start");
		int end = ParamUtil.getInteger(resourceRequest, "end");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject optionsJSONObject = JSONFactoryUtil.createJSONObject();

		optionsJSONObject.put("end", end);
		optionsJSONObject.put("keywords", keywords);
		optionsJSONObject.put("socialRelationType", socialRelationType);
		optionsJSONObject.put("start", start);

		jsonObject.put("options", optionsJSONObject);

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String portletName = portletDisplay.getPortletName();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		if ((socialRelationType == 0) &&
			!portletName.equals(PortletKeys.MEMBERS)) {

			List<BaseModel<?>> contacts =
				EntryLocalServiceUtil.searchUsersAndContacts(
					themeDisplay.getCompanyId(), themeDisplay.getUserId(),
					keywords, start, end);

			for (BaseModel<?> contact : contacts) {
				JSONObject contactJSONObject = null;

				if (contact instanceof User) {
					contactJSONObject = getUserJSONObject(
						resourceResponse, (User)contact, themeDisplay);
				}
				else {
					contactJSONObject = getEntryJSONObject(
						resourceResponse, (Entry)contact, redirect,
						themeDisplay);
				}

				jsonArray.put(contactJSONObject);
			}
		}
		else if ((socialRelationType ==
					SocialRelationConstants.TYPE_MY_CONTACTS) &&
				 !portletName.equals(PortletKeys.MEMBERS)) {

			List<Entry> entries = EntryLocalServiceUtil.search(
				themeDisplay.getUserId(), keywords, start, end);

			for (Entry entry : entries) {
				JSONObject contactJSONObject = getEntryJSONObject(
					resourceResponse, entry, redirect, themeDisplay);

				jsonArray.put(contactJSONObject);
			}
		}
		else {
			Group group = themeDisplay.getScopeGroup();
			Layout layout = themeDisplay.getLayout();

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			if (group.isUser() && layout.isPublicLayout()) {
				params.put("socialRelation", new Long[] {group.getClassPK()});
			}
			else if (socialRelationType != 0) {
				params.put(
					"socialRelationType",
					new Long[] {
						themeDisplay.getUserId(), new Long(socialRelationType)
					});
			}

			if (portletName.equals(PortletKeys.MEMBERS)) {
				params.put("usersGroups", group.getGroupId());
			}

			List<User> users = UserLocalServiceUtil.search(
				themeDisplay.getCompanyId(), keywords,
				WorkflowConstants.STATUS_APPROVED, params, start, end,
				new UserLastNameComparator(true));

			int usersCount = UserLocalServiceUtil.searchCount(
				themeDisplay.getCompanyId(), keywords,
				WorkflowConstants.STATUS_APPROVED, params);

			jsonObject.put("count", usersCount);

			for (User user : users) {
				JSONObject userJSONObject = getUserJSONObject(
					resourceResponse, user, themeDisplay);

				jsonArray.put(userJSONObject);
			}
		}

		jsonObject.put("users", jsonArray);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("deleteEntry")) {
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

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String comments = ParamUtil.getString(actionRequest, "comments");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			Entry entry = null;

			String message = StringPool.BLANK;

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

			jsonObject.put("entryId", entry.getEntryId());
			jsonObject.put("success", true);

			SessionMessages.add(
				actionRequest, "request_processed",
				themeDisplay.translate(message));
		}
		catch (Exception e) {
			String message = null;

			if (e instanceof EntryEmailAddressException) {
				message = "please-enter-a-valid-email-address";
			}
			else if (e instanceof ContactFullNameException) {
				message = "full-name-cannot-be-empty";
			}
			else if (e instanceof DuplicateEntryEmailAddressException) {
				message = "there-is-already-a-contact-with-this-email-address";
			}
			else {
				message =
					"an-error-occurred-while-processing-the-requested-resource";
			}

			jsonObject.put("message", themeDisplay.translate(message));
			jsonObject.put("success", false);
		}

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
			else if (fieldGroup.equals("comments")) {
				updateComments(actionRequest);
			}
			else if (fieldGroup.equals("instantMessenger")) {
				updateInstantMessenger(actionRequest);
			}
			else if (fieldGroup.equals("sms")) {
				updateSMS(actionRequest);
			}
			else if (fieldGroup.equals("socialNetwork")) {
				updateSocialNetwork(actionRequest);
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
			else if (e instanceof UserSmsException) {
				message = "please-enter-a-sms-id-that-is-a-valid-email-address";
			}
			else if (e instanceof WebsiteURLException) {
				message = "please-enter-a-valid-url";
			}

			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			jsonObject.put("message", themeDisplay.translate(message));
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

	protected JSONObject getEntryJSONObject(
			ResourceResponse resourceResponse, Entry entry, String redirect,
			ThemeDisplay themeDisplay)
		throws Exception {

		JSONObject contactJSONObject = JSONFactoryUtil.createJSONObject();

		contactJSONObject.put("entryId", String.valueOf(entry.getEntryId()));
		contactJSONObject.put("emailAddress", entry.getEmailAddress());
		contactJSONObject.put("fullName", entry.getFullName());
		contactJSONObject.put("portalUser", false);
		contactJSONObject.put(
			"portraitURL",
			themeDisplay.getPathImage() + "/user_male_portrait?img_id=0");

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("redirect", redirect);
		viewSummaryURL.setParameter(
			"entryId", String.valueOf(entry.getEntryId()));
		viewSummaryURL.setParameter("portalUser", Boolean.FALSE.toString());

		contactJSONObject.put("viewSummaryURL", viewSummaryURL.toString());

		return contactJSONObject;
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
			ResourceResponse resourceResponse, User user,
			ThemeDisplay themeDisplay)
		throws Exception {

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		userJSONObject.put("emailAddress", user.getEmailAddress());
		userJSONObject.put("firstName", user.getFirstName());
		userJSONObject.put("fullName", user.getFullName());
		userJSONObject.put("jobTitle", user.getJobTitle());
		userJSONObject.put("lastName", user.getLastName());
		userJSONObject.put("portalUser", true);
		userJSONObject.put("portraitURL", user.getPortraitURL(themeDisplay));
		userJSONObject.put("userId", String.valueOf(user.getUserId()));

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		PortletURL viewSummaryURL = liferayPortletResponse.createRenderURL();

		viewSummaryURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		viewSummaryURL.setParameter(
			"mvcPath", "/contacts_center/view_resources.jsp");
		viewSummaryURL.setParameter("userId", String.valueOf(user.getUserId()));
		viewSummaryURL.setParameter("portalUser", Boolean.TRUE.toString());

		userJSONObject.put("viewSummaryURL", viewSummaryURL.toString());

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

	protected void updateComments(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Contact contact = themeDisplay.getContact();

		String comments = ParamUtil.getString(actionRequest, "comments");

		updateProfile(
			actionRequest, contact.getAimSn(), comments,
			contact.getFacebookSn(), contact.getIcqSn(), contact.getJabberSn(),
			contact.getMsnSn(), contact.getMySpaceSn(), contact.getSkypeSn(),
			contact.getSmsSn(), contact.getTwitterSn(), contact.getYmSn());
	}

	protected void updateInstantMessenger(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Contact contact = themeDisplay.getContact();

		String aimSn = ParamUtil.getString(actionRequest, "aimSn");
		String icqSn = ParamUtil.getString(actionRequest, "icqSn");
		String jabberSn = ParamUtil.getString(actionRequest, "jabberSn");
		String msnSn = ParamUtil.getString(actionRequest, "msnSn");
		String skypeSn = ParamUtil.getString(actionRequest, "skypeSn");
		String ymSn = ParamUtil.getString(actionRequest, "ymSn");

		updateProfile(
			actionRequest, aimSn, user.getComments(), contact.getFacebookSn(),
			icqSn, jabberSn, msnSn, contact.getMySpaceSn(), skypeSn,
			contact.getSmsSn(), contact.getTwitterSn(), ymSn);
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

	protected void updateProfile(
			ActionRequest actionRequest, String aimSn, String comments,
			String facebookSn, String icqSn, String jabberSn, String msnSn,
			String mySpaceSn, String skypeSn, String sms, String twitterSn,
			String ymSn)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Contact contact = user.getContact();

		Calendar cal = CalendarFactoryUtil.getCalendar();

		cal.setTime(user.getBirthday());

		int birthdayDay = cal.get(Calendar.DATE);
		int birthdayMonth = cal.get(Calendar.MONTH);
		int birthdayYear = cal.get(Calendar.YEAR);

		List<UserGroupRole> userGroupRoles = UsersAdminUtil.getUserGroupRoles(
			actionRequest);
		List<EmailAddress> emailAddresses = UsersAdminUtil.getEmailAddresses(
			actionRequest);
		List<AnnouncementsDelivery> announcementsDeliveries =
			AnnouncementsDeliveryLocalServiceUtil.getUserDeliveries(
				user.getUserId());

		UserServiceUtil.updateUser(
			user.getUserId(), user.getPasswordUnencrypted(),
			user.getPasswordUnencrypted(), user.getPasswordUnencrypted(),
			user.getPasswordReset(), user.getReminderQueryQuestion(),
			user.getReminderQueryAnswer(), user.getScreenName(),
			user.getEmailAddress(), user.getFacebookId(), user.getOpenId(),
			user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
			comments, user.getFirstName(), user.getMiddleName(),
			user.getLastName(), contact.getPrefixId(), contact.getSuffixId(),
			user.isMale(), birthdayMonth, birthdayDay, birthdayYear, sms, aimSn,
			facebookSn, icqSn, jabberSn, msnSn, mySpaceSn, skypeSn, twitterSn,
			ymSn, user.getJobTitle(), user.getGroupIds(),
			user.getOrganizationIds(), user.getRoleIds(), userGroupRoles,
			user.getUserGroupIds(), user.getAddresses(), emailAddresses,
			user.getPhones(), user.getWebsites(), announcementsDeliveries,
			new ServiceContext());
	}

	protected void updateSMS(ActionRequest actionRequest) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Contact contact = themeDisplay.getContact();

		String smsSn = ParamUtil.getString(actionRequest, "smsSn");

		updateProfile(
			actionRequest, contact.getAimSn(), user.getComments(),
			contact.getFacebookSn(), contact.getIcqSn(), contact.getJabberSn(),
			contact.getMsnSn(), contact.getMySpaceSn(), contact.getSkypeSn(),
			smsSn, contact.getTwitterSn(), contact.getYmSn());
	}

	protected void updateSocialNetwork(ActionRequest actionRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		Contact contact = themeDisplay.getContact();

		String facebookSn = ParamUtil.getString(actionRequest, "facebookSn");
		String mySpaceSn = ParamUtil.getString(actionRequest, "mySpaceSn");
		String twitterSn = ParamUtil.getString(actionRequest, "twitterSn");

		updateProfile(
			actionRequest, contact.getAimSn(), user.getComments(), facebookSn,
			contact.getIcqSn(), contact.getJabberSn(), contact.getMsnSn(),
			mySpaceSn, contact.getSkypeSn(), contact.getSmsSn(), twitterSn,
			contact.getYmSn());
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