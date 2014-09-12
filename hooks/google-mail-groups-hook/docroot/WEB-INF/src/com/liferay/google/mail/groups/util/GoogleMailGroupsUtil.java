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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.persistence.GroupActionableDynamicQuery;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Matthew Kong
 */
public class GoogleMailGroupsUtil {

	public static void addGGroup(
			Directory directory, String name, String groupEmailAddress)
		throws PortalException {

		try {
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

	public static void addGGroupMember(
			Directory directory, String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
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

	public static void deleteGGroup(
			Directory directory, String groupEmailAddress)
		throws PortalException {

		try {
			Directory.Groups gGroups = directory.groups();

			Directory.Groups.Delete delete = gGroups.delete(groupEmailAddress);

			delete.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public static void deleteGGroupMember(
			Directory directory, String groupEmailAddress, String emailAddress)
		throws PortalException {

		try {
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
		GoogleCredential googleCredential = getGoogleCredential();

		Directory.Builder builder = new Directory.Builder(
			googleCredential.getTransport(), googleCredential.getJsonFactory(),
			googleCredential);

		return builder.build();
	}

	public static com.google.api.services.admin.directory.model.Group
		getGGroup(Directory directory, String groupEmailAddress) {

		try {
			Directory.Groups gGroups = directory.groups();

			Directory.Groups.Get get = gGroups.get(groupEmailAddress);

			return get.execute();
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Members getGGroupMembers(
			Directory directory, String groupEmailAddress)
		throws PortalException {

		try {
			Directory.Members members = directory.members();

			Directory.Members.List list = members.list(groupEmailAddress);

			return list.execute();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

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
		final Directory directory = getDirectory();

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
					getGGroup(directory, groupEmailAddress);

				if (gGroup == null) {
					addGGroup(
						directory, group.getDescriptiveName(),
						groupEmailAddress);
				}

				Members members = getGGroupMembers(
					directory, groupEmailAddress);

				if (members.getMembers() != null) {
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
						directory, groupEmailAddress, gGroupMemberEmailAddress);
				}

				for (String emailAddress : emailAddresses) {
					if (gGroupMemberEmailAddresses.contains(emailAddress)) {
						continue;
					}

					addGGroupMember(directory, groupEmailAddress, emailAddress);
				}
			}
		};

		actionableDynamicQuery.performActions();
	}

	protected static GoogleCredential getGoogleCredential() throws Exception {
		GoogleCredential.Builder builder = new GoogleCredential.Builder();

		builder.setJsonFactory(new JacksonFactory());
		builder.setServiceAccountId(PortletPropsValues.SERVICE_ACCOUNT_ID);

		File file = new File(
			PropsUtil.get(PropsKeys.LIFERAY_HOME) +
				PortletPropsValues.SERVICE_ACCOUNT_PRIVATE_KEY_P12_FILE);

		builder.setServiceAccountPrivateKeyFromP12File(file);

		builder.setServiceAccountScopes(_SCOPES_DIRECTORY);
		builder.setServiceAccountUser(PortletPropsValues.SERVICE_ACCOUNT_USER);
		builder.setTransport(new NetHttpTransport());

		return builder.build();
	}

	private static final int _ERROR_CONFLICT = 409;

	private static final List<String> _SCOPES_DIRECTORY = Arrays.asList(
		"https://www.googleapis.com/auth/admin.directory.group",
		"https://www.googleapis.com/auth/admin.directory.user");

}