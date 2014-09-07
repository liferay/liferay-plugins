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

import com.liferay.google.apps.connector.GGroup;
import com.liferay.google.apps.connector.GGroupManager;
import com.liferay.google.apps.connector.GGroupMember;
import com.liferay.google.apps.connector.GoogleAppsConnectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupActionableDynamicQuery;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GoogleMailGroupsUtil {

	public static String getGroupEmailAddress(Group group)
		throws PortalException {

		StringBundler sb = new StringBundler(5);

		sb.append(PortletPropsValues.EMAIL_PREFIX);
		sb.append(StringPool.DASH);

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

	public static boolean isSync(Group group) {
		if ((group == null) || group.isCompany() || group.isControlPanel() ||
			group.isGuest() || (!group.isOrganization() && !group.isSite())) {

			return false;
		}

		return true;
	}

	public static void syncGroups(long companyId) throws Exception {
		final GGroupManager gGroupManager =
			GoogleAppsConnectionFactoryUtil.getGGroupManager(companyId);

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

				GGroup gGroup = gGroupManager.getGGroup(groupEmailAddress);

				if ((gGroup == null) ||
					Validator.isNull(gGroup.getEmailAddress())) {

					gGroupManager.addGGroup(
						groupEmailAddress, group.getDescriptiveName(),
						StringPool.BLANK, PortletPropsValues.EMAIL_PERMISSION);
				}

				List<GGroupMember> gGroupMembers =
					gGroupManager.getGGroupMembers(groupEmailAddress);

				for (GGroupMember gGroupMember : gGroupMembers) {
					gGroupMemberEmailAddresses.add(
						gGroupMember.getEmailAddress());
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

					gGroupManager.deleteGGroupMember(
						groupEmailAddress, gGroupMemberEmailAddress);
				}

				for (String emailAddress : emailAddresses) {
					if (gGroupMemberEmailAddresses.contains(emailAddress)) {
						continue;
					}

					gGroupManager.addGGroupMember(
						groupEmailAddress, emailAddress);
				}
			}
		};

		actionableDynamicQuery.performActions();
	}

}