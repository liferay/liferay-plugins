/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.google.mail.groups.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;
import com.google.api.services.groupssettings.Groupssettings;
import com.google.api.services.groupssettings.model.Groups;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupActionableDynamicQuery;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GoogleMailGroupsUtil {

	public static void addGGroup(String name, String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Groups gGroups = directory.groups();

			com.google.api.services.admin.directory.model.Group gGroup =
				new com.google.api.services.admin.directory.model.Group();

			gGroup.setEmail(groupEmailAddress);
			gGroup.setName(name);

			Directory.Groups.Insert insert = gGroups.insert(gGroup);

			insert.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void addGGroupManagers(List<User> users)
		throws PortalException {

		for (User user : users) {
			List<Group> groups = GroupLocalServiceUtil.getUserGroups(
				user.getUserId(), true);

			for (Group group : groups) {
				if (!isSync(group)) {
					continue;
				}

				String groupEmailAddress = getGroupEmailAddress(group);

				String userEmailAddress = getUserEmailAddress(user);

				Member member = getGGroupMember(
					groupEmailAddress, userEmailAddress);

				if (member == null) {
					continue;
				}

				String gRole = member.getRole();

				if (gRole.equals("MANAGER") || gRole.equals("OWNER")) {
					continue;
				}

				member.setRole("MANAGER");

				updateGGroupMember(groupEmailAddress, userEmailAddress, member);
			}
		}
	}

	public static void addGGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Member member = new Member();

			member.setEmail(emailAddress);

			Directory.Members.Insert insert = members.insert(
				groupEmailAddress, member);

			insert.execute();
		}
		catch (GoogleJsonResponseException gjre) {
			if (gjre.getStatusCode() == _ERROR_CONFLICT) {
				return;
			}

			throw new PortalException(gjre);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void checkLargeGroup(Group group) throws PortalException {
		if ((PortletPropsValues.EMAIL_LARGE_GROUP_SIZE == 0) ||
			Validator.isNull(PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {

			return;
		}

		String whoCanPostMessage = null;

		boolean largeGroup = isLargeGroup(group);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("inherit", Boolean.TRUE);
		params.put("usersGroups", group.getGroupId());

		int count = UserLocalServiceUtil.searchCount(
			group.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
			params);

		if (!largeGroup &&
			(count >= PortletPropsValues.EMAIL_LARGE_GROUP_SIZE)) {

			ExpandoValueLocalServiceUtil.addValue(
				group.getCompanyId(), Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, "largeGroup",
				group.getGroupId(), true);

			whoCanPostMessage = "ALL_MANAGERS_CAN_POST";

			updateGGroupManagers(group);
		}
		else if (largeGroup &&
				 (count < PortletPropsValues.EMAIL_LARGE_GROUP_SIZE)) {

			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(
				group.getCompanyId(), Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, "largeGroup",
				group.getGroupId());

			expandoValue.setBoolean(false);

			ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);

			whoCanPostMessage = "ANYONE_CAN_POST";
		}
		else {
			return;
		}

		String groupEmailAddress = getGroupEmailAddress(group);

		Groups groups = getGroupssettingsGroup(groupEmailAddress);

		if (groups == null) {
			return;
		}

		groups.setWhoCanPostMessage(whoCanPostMessage);

		updateGroupssettingsGroup(groupEmailAddress, groups);
	}

	public static void deleteGGroup(String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Groups gGroups = directory.groups();

			Directory.Groups.Delete delete = gGroups.delete(groupEmailAddress);

			delete.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGGroupMember(
			String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Delete delete = members.delete(
				groupEmailAddress, emailAddress);

			delete.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static Directory getDirectory() throws Exception {
		if (_directory != null) {
			return _directory;
		}

		GoogleCredential googleCredential = getGoogleCredential();

		Directory.Builder builder = new Directory.Builder(
			googleCredential.getTransport(), googleCredential.getJsonFactory(),
			googleCredential);

		_directory = builder.build();

		return _directory;
	}

	public static com.google.api.services.admin.directory.model.Group getGGroup(
		String groupEmailAddress) {

		try {
			Directory directory = getDirectory();

			Directory.Groups gGroups = directory.groups();

			Directory.Groups.Get get = gGroups.get(groupEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Member getGGroupMember(
		String groupEmailAddress, String userEmailAddress) {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Get get = members.get(
				groupEmailAddress, userEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Members getGGroupMembers(String groupEmailAddress)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.List list = members.list(groupEmailAddress);

			return list.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static String getGroupEmailAddress(Group group)
		throws PortalException {

		StringBundler sb = new StringBundler(4);

		sb.append(PortletPropsValues.EMAIL_PREFIX);

		String friendlyURL = group.getFriendlyURL();

		sb.append(friendlyURL.substring(1));

		sb.append(StringPool.AT);

		Company company = CompanyLocalServiceUtil.getCompany(
			group.getCompanyId());

		sb.append(company.getMx());

		return sb.toString();
	}

	public static Groupssettings getGroupssettings() throws Exception {
		if (_groupssettings != null) {
			return _groupssettings;
		}

		GoogleCredential googleCredential = getGoogleCredential();

		Groupssettings.Builder builder = new Groupssettings.Builder(
			googleCredential.getTransport(), googleCredential.getJsonFactory(),
			googleCredential);

		_groupssettings = builder.build();

		return _groupssettings;
	}

	public static Groups getGroupssettingsGroup(String groupEmailAddress) {
		try {
			Groupssettings groupssettings = getGroupssettings();

			Groupssettings.Groups groups = groupssettings.groups();

			Groupssettings.Groups.Get get = groups.get(groupEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static String getUserEmailAddress(User user) throws PortalException {
		return user.getUserId() + StringPool.AT + user.getCompanyMx();
	}

	public static boolean isLargeGroup(Group group) {
		ExpandoBridge expandoBridge = group.getExpandoBridge();

		return GetterUtil.getBoolean(
			expandoBridge.getAttribute("largeGroup", false));
	}

	public static boolean isSync(Group group) {
		if ((group == null) || group.isCompany() || group.isControlPanel() ||
			group.isGuest() || (!group.isOrganization() && !group.isSite())) {

			return false;
		}

		return true;
	}

	public static void removeGGroupManagers(List<User> users)
		throws PortalException {

		for (User user : users) {
			if (RoleLocalServiceUtil.hasUserRole(
					user.getUserId(), user.getCompanyId(),
				PortletPropsValues.EMAIL_LARGE_GROUP_ROLE, true)) {

				continue;
			}

			List<Group> groups = GroupLocalServiceUtil.getUserGroups(
				user.getUserId(), true);

			for (Group group : groups) {
				if (!isSync(group)) {
					continue;
				}

				String groupEmailAddress = getGroupEmailAddress(group);

				String userEmailAddress = getUserEmailAddress(user);

				Member member = getGGroupMember(
					groupEmailAddress, userEmailAddress);

				if (member == null) {
					continue;
				}

				String gRole = member.getRole();

				if (gRole.equals("MEMBER") || gRole.equals("OWNER")) {
					continue;
				}

				member.setRole("MEMBER");

				updateGGroupMember(groupEmailAddress, userEmailAddress, member);
			}
		}
	}

	public static void syncGroups() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new GroupActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) throws PortalException {
				Group group = (Group)object;

				if (!isSync(group)) {
					return;
				}

				List<String> gGroupMemberEmailAddresses =
					new ArrayList<String>();

				String groupEmailAddress = getGroupEmailAddress(group);

				com.google.api.services.admin.directory.model.Group gGroup =
					getGGroup(groupEmailAddress);

				if (gGroup == null) {
					addGGroup(group.getDescriptiveName(), groupEmailAddress);
				}

				Members members = getGGroupMembers(groupEmailAddress);

				if ((members != null) && (members.getMembers() != null)) {
					for (Member member : members.getMembers()) {
						gGroupMemberEmailAddresses.add(member.getEmail());
					}
				}

				List<String> emailAddresses = new ArrayList<String>();

				LinkedHashMap<String, Object> userParams =
					new LinkedHashMap<String, Object>();

				userParams.put("inherit", Boolean.TRUE);
				userParams.put("usersGroups", new Long(group.getGroupId()));

				List<User> users = UserLocalServiceUtil.search(
					group.getCompanyId(), null,
					WorkflowConstants.STATUS_APPROVED, userParams,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					(OrderByComparator)null);

				for (User user : users) {
					emailAddresses.add(getUserEmailAddress(user));
				}

				for (String gGroupMemberEmailAddress :
						gGroupMemberEmailAddresses) {

					if (emailAddresses.contains(gGroupMemberEmailAddress)) {
						continue;
					}

					deleteGGroupMember(
						groupEmailAddress, gGroupMemberEmailAddress);
				}

				for (String emailAddress : emailAddresses) {
					if (gGroupMemberEmailAddresses.contains(emailAddress)) {
						continue;
					}

					addGGroupMember(groupEmailAddress, emailAddress);
				}

				checkLargeGroup(group);
			}
		};

		actionableDynamicQuery.performActions();
	}

	public static void updateGGroupMember(
			String groupEmailAddress, String userEmailAddress, Member member)
		throws PortalException {

		try {
			Directory directory = getDirectory();

			Directory.Members members = directory.members();

			Directory.Members.Update update = members.update(
				groupEmailAddress, userEmailAddress, member);

			update.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void updateGroupssettingsGroup(
			String groupEmailAddress, Groups groups)
		throws PortalException {

		try {
			Groupssettings groupssettings = getGroupssettings();

			Groupssettings.Groups groupssettingsGroups =
				groupssettings.groups();

			Groupssettings.Groups.Update update = groupssettingsGroups.update(
				groupEmailAddress, groups);

			update.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	protected static GoogleCredential getGoogleCredential() throws Exception {
		if (_googleCredential != null) {
			return _googleCredential;
		}

		GoogleCredential.Builder builder = new GoogleCredential.Builder();

		builder.setJsonFactory(new JacksonFactory());
		builder.setServiceAccountId(
			PortletPropsValues.GOOGLE_API_SERVICE_ACCOUNT_ID);

		File file = new File(
			PropsUtil.get(PropsKeys.LIFERAY_HOME) +
				PortletPropsValues.
					GOOGLE_API_SERVICE_ACCOUNT_PRIVATE_KEY_P12_FILE);

		builder.setServiceAccountPrivateKeyFromP12File(file);

		builder.setServiceAccountScopes(_SCOPES);
		builder.setServiceAccountUser(
			PortletPropsValues.GOOGLE_API_SERVICE_ACCOUNT_USER);
		builder.setTransport(new NetHttpTransport());

		_googleCredential = builder.build();

		return _googleCredential;
	}

	protected static void updateGGroupManagers(Group group) {
		Role role = RoleLocalServiceUtil.fetchRole(
			group.getCompanyId(), PortletPropsValues.EMAIL_LARGE_GROUP_ROLE);

		if (role == null) {
			return;
		}

		List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

		for (User user : users) {
			try {
				String groupEmailAddress = getGroupEmailAddress(group);
				String userEmailAddress = getUserEmailAddress(user);

				Member member = getGGroupMember(
					groupEmailAddress, userEmailAddress);

				member.setRole("MANAGER");

				updateGGroupMember(groupEmailAddress, userEmailAddress, member);
			}
			catch (Exception e) {
			}
		}
	}

	private static final int _ERROR_CONFLICT = 409;

	private static final List<String> _SCOPES = Arrays.asList(
		"https://www.googleapis.com/auth/admin.directory.group",
		"https://www.googleapis.com/auth/admin.directory.user",
		"https://www.googleapis.com/auth/apps.groups.settings");

	private static Directory _directory;
	private static GoogleCredential _googleCredential;
	private static Groupssettings _groupssettings;

}