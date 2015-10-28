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

import com.google.api.services.admin.directory.model.Member;
import com.google.api.services.admin.directory.model.Members;
import com.google.api.services.groupssettings.model.Groups;

import com.liferay.google.apps.connector.util.GoogleDirectoryUtil;
import com.liferay.google.apps.connector.util.GoogleGroupssettingsUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
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
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GoogleMailGroupsUtil {

	public static void checkLargeGroup(Group group) throws PortalException {
		if ((PortletPropsValues.EMAIL_LARGE_GROUP_SIZE < 0) ||
			Validator.isNull(PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {

			return;
		}

		String whoCanPostMessage = null;

		boolean largeGroup = isLargeGroup(group);

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("inherit", Boolean.TRUE);
		params.put("usersGroups", group.getGroupId());

		int count = UserLocalServiceUtil.searchCount(
			group.getCompanyId(), null, WorkflowConstants.STATUS_APPROVED,
			params);

		if (!largeGroup &&
			(count >= PortletPropsValues.EMAIL_LARGE_GROUP_SIZE)) {

			ExpandoValueLocalServiceUtil.addValue(
				group.getCompanyId(), Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				"googleMailGroupsLargeGroup", group.getGroupId(), true);

			whoCanPostMessage = "ALL_MANAGERS_CAN_POST";

			updateGroupManagers(group);
		}
		else if (largeGroup &&
				 (count < PortletPropsValues.EMAIL_LARGE_GROUP_SIZE)) {

			ExpandoValue expandoValue = ExpandoValueLocalServiceUtil.getValue(
				group.getCompanyId(), Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				"googleMailGroupsLargeGroup", group.getGroupId());

			expandoValue.setBoolean(false);

			ExpandoValueLocalServiceUtil.updateExpandoValue(expandoValue);

			whoCanPostMessage = "ANYONE_CAN_POST";
		}
		else {
			return;
		}

		String groupEmailAddress = getGroupEmailAddress(group);

		Groups groups = GoogleGroupssettingsUtil.getGroups(groupEmailAddress);

		if (groups == null) {
			return;
		}

		groups.setWhoCanPostMessage(whoCanPostMessage);

		GoogleGroupssettingsUtil.updateGroups(groupEmailAddress, groups);
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

	public static String getUserEmailAddress(User user) throws PortalException {
		return user.getUserId() + StringPool.AT + user.getCompanyMx();
	}

	public static boolean isLargeGroup(Group group) {
		ExpandoBridge expandoBridge = group.getExpandoBridge();

		return GetterUtil.getBoolean(
			expandoBridge.getAttribute("googleMailGroupsLargeGroup", false));
	}

	public static boolean isSync(Group group) {
		if ((group == null) || group.isCompany() || group.isControlPanel() ||
			group.isGuest() || (!group.isOrganization() && !group.isSite())) {

			return false;
		}

		return true;
	}

	public static void syncGroups() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			GroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Group>() {

				@Override
				public void performAction(Group group) throws PortalException {
					if (!isSync(group)) {
						return;
					}

					List<String> groupMemberEmailAddresses = new ArrayList<>();
					Members members = null;

					String groupEmailAddress = getGroupEmailAddress(group);

					if (GoogleDirectoryUtil.getGroup(
							groupEmailAddress) == null) {

						try {
							GoogleDirectoryUtil.addGroup(
								group.getDescriptiveName(), groupEmailAddress);
						}
						catch (Exception e) {
							_log.error(
								"Unable to add Google group for " +
									group.getDescriptiveName(),
								e);

							return;
						}
					}
					else {
						members = GoogleDirectoryUtil.getGroupMembers(
							groupEmailAddress);
					}

					if ((members != null) && (members.getMembers() != null)) {
						for (Member member : members.getMembers()) {
							groupMemberEmailAddresses.add(member.getEmail());
						}
					}

					List<String> emailAddresses = new ArrayList<>();

					LinkedHashMap<String, Object> userParams =
						new LinkedHashMap<>();

					userParams.put("inherit", Boolean.TRUE);
					userParams.put(
						"usersGroups", Long.valueOf(group.getGroupId()));

					List<User> users = UserLocalServiceUtil.search(
						group.getCompanyId(), null,
						WorkflowConstants.STATUS_APPROVED, userParams,
						QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						(OrderByComparator)null);

					for (User user : users) {
						emailAddresses.add(getUserEmailAddress(user));
					}

					for (String groupMemberEmailAddress :
							groupMemberEmailAddresses) {

						if (emailAddresses.contains(groupMemberEmailAddress)) {
							continue;
						}

						try {
							GoogleDirectoryUtil.deleteGroupMember(
								groupEmailAddress, groupMemberEmailAddress);
						}
						catch (Exception e) {
							StringBundler sb = new StringBundler(4);

							sb.append("Unable to delete ");
							sb.append(groupMemberEmailAddress);
							sb.append(" from Google group ");
							sb.append(groupEmailAddress);

							_log.error(sb.toString(), e);
						}
					}

					for (String emailAddress : emailAddresses) {
						if (groupMemberEmailAddresses.contains(emailAddress)) {
							continue;
						}

						try {
							GoogleDirectoryUtil.addGroupMember(
								groupEmailAddress, emailAddress);
						}
						catch (Exception e) {
							StringBundler sb = new StringBundler(4);

							sb.append("Unable to add ");
							sb.append(emailAddress);
							sb.append(" to Google group ");
							sb.append(groupEmailAddress);

							_log.error(sb.toString(), e);
						}
					}

					checkLargeGroup(group);
				}

			});

		actionableDynamicQuery.performActions();
	}

	public static void updateGroupMemberRoles(
			List<User> users, String groupMemberRole)
		throws PortalException {

		for (User user : users) {
			if (groupMemberRole.equals("MEMBER") &&
				RoleLocalServiceUtil.hasUserRole(
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

				updateGroupMemberRole(group, user, groupMemberRole);
			}
		}
	}

	protected static void updateGroupManagers(Group group) {
		Role role = RoleLocalServiceUtil.fetchRole(
			group.getCompanyId(), PortletPropsValues.EMAIL_LARGE_GROUP_ROLE);

		if (role == null) {
			return;
		}

		List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

		for (User user : users) {
			try {
				updateGroupMemberRole(group, user, "MANAGER");
			}
			catch (Exception e) {
			}
		}
	}

	protected static void updateGroupMemberRole(
			Group group, User user, String groupMemberRole)
		throws PortalException {

		String groupEmailAddress = getGroupEmailAddress(group);
		String userEmailAddress = getUserEmailAddress(user);

		Member member = GoogleDirectoryUtil.getGroupMember(
			groupEmailAddress, userEmailAddress);

		String currentGroupMemberRole = member.getRole();

		if (currentGroupMemberRole.equals(groupMemberRole) ||
			currentGroupMemberRole.equals("OWNER")) {

			return;
		}

		member.setRole(groupMemberRole);

		GoogleDirectoryUtil.updateGroupMember(
			groupEmailAddress, userEmailAddress, member);
	}

	private static Log _log = LogFactoryUtil.getLog(GoogleMailGroupsUtil.class);

}