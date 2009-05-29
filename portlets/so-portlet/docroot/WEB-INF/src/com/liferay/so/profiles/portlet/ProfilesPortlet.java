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

package com.liferay.so.profiles.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.announcements.model.AnnouncementsDelivery;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * <a href="ProfilesPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Ryan Park
 *
 */
public class ProfilesPortlet extends MVCPortlet {

	public void updateUserProfile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = ParamUtil.getLong(actionRequest, "userId");

		User user = UserLocalServiceUtil.getUser(userId);
		Contact contact = user.getContact();

		String oldPassword = getUpdateUserPassword(
			actionRequest, user.getUserId());
		String newPassword1 = StringPool.BLANK;
		String newPassword2 = StringPool.BLANK;
		boolean passwordReset = false;
		String reminderQueryQuestion = user.getReminderQueryQuestion();
		String reminderQueryAnswer = user.getReminderQueryAnswer();
		String screenName = user.getScreenName();
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String openId = user.getOpenId();
		String languageId = user.getLanguageId();
		String timeZoneId = user.getTimeZoneId();
		String greeting = user.getGreeting();
		String comments = ParamUtil.getString(actionRequest, "comments");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String middleName = ParamUtil.getString(actionRequest, "middleName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		int prefixId = contact.getPrefixId();
		int suffixId = contact.getSuffixId();
		boolean male = user.isMale();
		int birthdayMonth = 1;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String smsSn = contact.getSmsSn();
		String aimSn = ParamUtil.getString(actionRequest, "aimSn");
		String facebookSn = ParamUtil.getString(actionRequest, "facebookSn");
		String icqSn = ParamUtil.getString(actionRequest, "icqSn");
		String jabberSn = ParamUtil.getString(actionRequest, "jabberSn");
		String msnSn = ParamUtil.getString(actionRequest, "msnSn");
		String mySpaceSn = ParamUtil.getString(actionRequest, "mySpaceSn");
		String skypeSn = ParamUtil.getString(actionRequest, "skypeSn");
		String twitterSn = ParamUtil.getString(actionRequest, "twitterSn");
		String ymSn = ParamUtil.getString(actionRequest, "ymSn");
		String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		List<UserGroupRole> userGroupRoles = null;
		long[] userGroupIds = null;
		List<Address> addresses = (List<Address>)PortalClassInvoker.invoke(
			_CLASS_NAME_ENTERPRISE_ADMIN_UTIL, "getAddresses", actionRequest);
		List<EmailAddress> emailAddresses =
			(List<EmailAddress>)PortalClassInvoker.invoke(
				_CLASS_NAME_ENTERPRISE_ADMIN_UTIL, "getEmailAddresses",
					actionRequest);
		List<Phone> phones = (List<Phone>)PortalClassInvoker.invoke(
			_CLASS_NAME_ENTERPRISE_ADMIN_UTIL, "getPhones", actionRequest);
		List<Website> websites = (List<Website>)PortalClassInvoker.invoke(
			_CLASS_NAME_ENTERPRISE_ADMIN_UTIL, "getWebsites", actionRequest);
		List<AnnouncementsDelivery> announcementsDeliveries =
			new ArrayList<AnnouncementsDelivery>();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		UserServiceUtil.updateUser(
			userId, oldPassword, newPassword1, newPassword2, passwordReset,
			reminderQueryQuestion, reminderQueryAnswer, screenName,
			emailAddress, openId, languageId, timeZoneId, greeting, comments,
			firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, smsSn, aimSn, facebookSn,
			icqSn, jabberSn, msnSn, mySpaceSn, skypeSn, twitterSn, ymSn,
			jobTitle, groupIds, organizationIds, roleIds, userGroupRoles,
			userGroupIds, addresses, emailAddresses, phones, websites,
			announcementsDeliveries, serviceContext);
	}

	public void updateUserProjects(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");

		updateProjectsEntries(userId, actionRequest);
	}

	public void updateUserSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");

		User user = UserLocalServiceUtil.getUser(userId);
		Contact contact = user.getContact();

		String oldPassword = getUpdateUserPassword(
			actionRequest, user.getUserId());
		String newPassword1 = ParamUtil.getString(actionRequest, "password1");
		String newPassword2 = ParamUtil.getString(actionRequest, "password2");
		boolean passwordReset = false;
		String reminderQueryQuestion = user.getReminderQueryQuestion();
		String reminderQueryAnswer = user.getReminderQueryAnswer();
		String screenName = user.getScreenName();
		String emailAddress = user.getEmailAddress();
		String openId = user.getOpenId();
		String languageId = ParamUtil.getString(actionRequest, "languageId");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");
		String greeting = user.getGreeting();
		String comments = user.getComments();
		String firstName = contact.getFirstName();
		String middleName = contact.getMiddleName();
		String lastName = contact.getLastName();
		int prefixId = contact.getPrefixId();
		int suffixId = contact.getSuffixId();
		boolean male = user.isMale();
		int birthdayMonth = 1;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String smsSn = contact.getSmsSn();
		String aimSn = contact.getAimSn();
		String facebookSn = contact.getFacebookSn();
		String icqSn = contact.getIcqSn();
		String jabberSn = contact.getJabberSn();
		String msnSn = contact.getMsnSn();
		String mySpaceSn = contact.getMySpaceSn();
		String skypeSn = contact.getSkypeSn();
		String twitterSn = contact.getTwitterSn();
		String ymSn = contact.getYmSn();
		String jobTitle = contact.getJobTitle();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		UserServiceUtil.updateUser(
			userId, oldPassword, newPassword1, newPassword2, passwordReset,
			reminderQueryQuestion, reminderQueryAnswer, screenName,
			emailAddress, openId, languageId, timeZoneId, greeting, comments,
			firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, smsSn, aimSn, facebookSn,
			icqSn, jabberSn, msnSn, mySpaceSn, skypeSn, twitterSn, ymSn,
			jobTitle, null, null, null, null, null, serviceContext);
	}

	protected String getUpdateUserPassword(
		ActionRequest actionRequest, long userId) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			actionRequest);

		String password = PortalUtil.getUserPassword(request);

		if (userId != PortalUtil.getUserId(request)) {
			password = StringPool.BLANK;
		}

		return password;
	}

	protected void updateProjectsEntries(
			long userId, ActionRequest actionRequest)
		throws Exception {

		Set<Long> projectsEntryIds = new HashSet<Long>();

		int[] projectsEntriesIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "projectsEntriesIndexes"), 0);

		for (int projectsEntriesIndex : projectsEntriesIndexes) {
			long projectsEntryId = ParamUtil.getLong(
				actionRequest, "projectsEntryId" + projectsEntriesIndex);
			String title = ParamUtil.getString(
				actionRequest, "projectsEntryTitle" + projectsEntriesIndex);
			String description = ParamUtil.getString(
				actionRequest, "projectsEntryDescription" +
					projectsEntriesIndex);

			if (Validator.isNull(title)) {
				continue;
			}

			int startDateMonth = ParamUtil.getInteger(
				actionRequest, "projectsEntryStartDateMonth" +
					projectsEntriesIndex);
			int startDateDay = 1;
			int startDateYear = ParamUtil.getInteger(
				actionRequest, "projectsEntryStartDateYear" +
					projectsEntriesIndex);
			int endDateMonth = ParamUtil.getInteger(
				actionRequest, "projectsEntryEndDateMonth" +
					projectsEntriesIndex);
			int endDateDay = 1;
			int endDateYear = ParamUtil.getInteger(
				actionRequest, "projectsEntryEndDateYear" +
					projectsEntriesIndex);

			boolean current = ParamUtil.getBoolean(
				actionRequest, "projectsEntryCurrent" + projectsEntriesIndex);
			String otherMembers = ParamUtil.getString(
				actionRequest, "projectsEntryOtherMembers" +
					projectsEntriesIndex);

			if (projectsEntryId <= 0) {
				ProjectsEntry projectsEntry =
					ProjectsEntryLocalServiceUtil.addProjectsEntry(
						userId, title, description, startDateMonth,
						startDateDay, startDateYear, endDateMonth, endDateDay,
						endDateYear, current, otherMembers);

				projectsEntryId = projectsEntry.getProjectsEntryId();
			}
			else {
				ProjectsEntryLocalServiceUtil.updateProjectsEntry(
					projectsEntryId, title, description, startDateMonth,
					startDateDay, startDateYear, endDateMonth, endDateDay,
					endDateYear, current, otherMembers);
			}

			projectsEntryIds.add(projectsEntryId);
		}

		List<ProjectsEntry> projectsEntries =
			ProjectsEntryLocalServiceUtil.getUserProjectsEntries(userId);

		for (ProjectsEntry projectsEntry : projectsEntries) {
			if (!projectsEntryIds.contains(
				projectsEntry.getProjectsEntryId())) {

				ProjectsEntryLocalServiceUtil.deleteProjectsEntry(
					projectsEntry.getProjectsEntryId());
			}
		}
	}

	private static final String _CLASS_NAME_ENTERPRISE_ADMIN_UTIL =
		"com.liferay.portlet.enterpriseadmin.util.EnterpriseAdminUtil";

}