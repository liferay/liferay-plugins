/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.text.Format;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.directory.shared.ldap.name.DN;

/**
 * @author Brian Wing Shun Chan
 */
public class UserDirectory extends BaseDirectory {

	public UserDirectory(User user, Directory parentDirectory)
		throws Exception {

		super("cn=" + user.getScreenName(), parentDirectory);

		_user = user;

		initAttributes();
	}

	@Override
	protected void initAttributes() {
		addAttribute("cn", _user.getScreenName());
		addAttribute("createTimestamp", _format.format(_user.getCreateDate()));
		addAttribute("displayName", _user.getFullName());
		addAttribute("givenName", _user.getFirstName());
		addAttribute("mail", _user.getEmailAddress());
		addAttribute(
			"modifyTimestamp", _format.format(_user.getModifiedDate()));
		addAttribute("sn", _user.getLastName());
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "inetOrgPerson");
		addAttribute("objectclass", "liferayPerson");
		addAttribute("objectclass", "top");
		addAttribute("uid", String.valueOf(_user.getUserId()));
		addAttribute("uuid", _user.getUuid());

		try {
			initMemberAttributes();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	protected List<Directory> initDirectories() throws Exception {
		return _directories;
	}

	protected void initMemberAttributes() throws Exception {
		Directory parentDirectory = getParentDirectory();

		CompanyDirectory companyDirectory =
			(CompanyDirectory)parentDirectory.getParentDirectory();

		DN companyDirectoryDN = companyDirectory.getName();

		long groupClassNameId = PortalUtil.getClassNameId(
			Group.class.getName());

		LinkedHashMap<String, Object> groupParams =
			new LinkedHashMap<String, Object>();

		groupParams.put("usersGroups", new Long(_user.getUserId()));

		List<Group> groups = GroupLocalServiceUtil.search(
			_user.getCompanyId(), new long[] {groupClassNameId}, null, null,
			groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			addAttribute(
				"member",
				"cn=" + group.getName() + ",ou=" + group.getName() +
					",ou=Communities," + companyDirectoryDN.getName());
		}

		for (Organization organization : _user.getOrganizations()) {
			addAttribute(
				"member",
				"cn=" + organization.getName() + ",ou=" +
					organization.getName() + ",ou=Organizations," +
						companyDirectoryDN.getName());
		}

		for (Role role : _user.getRoles()) {
			addAttribute(
				"member",
				"cn=" + role.getName() + ",ou=" + role.getName() +
					",ou=Roles," + companyDirectoryDN.getName());
		}

		for (UserGroup userGroup : _user.getUserGroups()) {
			addAttribute(
				"member",
				"cn=" + userGroup.getName() + ",ou=" + userGroup.getName() +
					",ou=User Groups," + companyDirectoryDN.getName());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(UserDirectory.class);

	private Format _format =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss.SZ");
	private User _user;

}