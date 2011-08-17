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

package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.vldap.util.LdapUtil;

import java.text.Format;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UserDirectory extends Directory {

	public UserDirectory(String top, Company company, User user)
		throws Exception {

		addAttribute("cn", user.getScreenName());

		Date createDate = user.getCreateDate();

		if (createDate == null) {
			createDate = new Date();
		}

		addAttribute(
			"createTimestamp", _simpleDateFormat.format(createDate));

		addAttribute("displayName", user.getFullName());
		addAttribute("givenName", user.getFirstName());
		addAttribute("mail", user.getEmailAddress());

		Date modifyDate = user.getModifiedDate();

		if (modifyDate == null) {
			modifyDate = new Date();
		}

		addAttribute(
			"modifyTimestamp",
			_simpleDateFormat.format(modifyDate));

		addAttribute("sn", user.getLastName());
		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "inetOrgPerson");
		addAttribute("objectclass", "liferayPerson");
		addAttribute("objectclass", "top");
		addAttribute("uid", String.valueOf(user.getUserId()));
		addAttribute("uuid", user.getUuid());

		String name = LdapUtil.buildName(top, company);

		setName(top, company, "Users", "cn=" + user.getScreenName());

		long groupClassNameId = PortalUtil.getClassNameId(
			Group.class.getName());

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("usersGroups", user.getUserId());

		List<Group> groups = GroupLocalServiceUtil.search(
			user.getCompanyId(), new long[] {groupClassNameId}, null, null,
			params, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(group.getName());
			sb.append(",ou=");
			sb.append(group.getName());
			sb.append(",ou=Communities,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (Organization organization : user.getOrganizations()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(organization.getName());
			sb.append(",ou=");
			sb.append(organization.getName());
			sb.append(",ou=Organizations,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (Role role : user.getRoles()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(role.getName());
			sb.append(",ou=");
			sb.append(role.getName());
			sb.append(",ou=Roles,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (UserGroup userGroup : user.getUserGroups()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(userGroup.getName());
			sb.append(",ou=");
			sb.append(userGroup.getName());
			sb.append(",ou=User Groups,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}
	}

	private static Format _simpleDateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss.SZ");

}