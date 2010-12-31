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

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.directory.shared.ldap.filter.BranchNode;
import org.apache.directory.shared.ldap.filter.EqualityNode;
import org.apache.directory.shared.ldap.filter.ExprNode;
import org.apache.directory.shared.ldap.message.internal.InternalRequest;
import org.apache.directory.shared.ldap.message.internal.InternalSearchRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class UsersDirectory extends BaseDirectory {

	public UsersDirectory(Directory parentDirectory)
		throws Exception {

		super("ou=Users", parentDirectory);

		initAttributes();
	}

	protected String getScreenName() {
		InternalRequest internalRequest = getInternalRequest();

		if (internalRequest instanceof InternalSearchRequest) {
			InternalSearchRequest internalSearchRequest =
				(InternalSearchRequest)internalRequest;

			ExprNode exprNode = internalSearchRequest.getFilter();

			return getScreenName(exprNode);
		}

		return null;
	}

	protected String getScreenName(ExprNode exprNode) {
		if (exprNode.isLeaf()) {
			if (exprNode instanceof EqualityNode<?>) {
				EqualityNode<?> equalityNode = (EqualityNode<?>)exprNode;

				String attributeId = equalityNode.getAttribute();
				String value = equalityNode.getValue().getString();

				if (attributeId.equals("cn")) {
					return value;
				}
			}
		}
		else {
			BranchNode branchNode = (BranchNode)exprNode;

			for (ExprNode childBranchNode : branchNode.getChildren()) {
				String screenName = getScreenName(childBranchNode);

				if (screenName != null) {
					return screenName;
				}
			}
		}

		return null;
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Users");
	}

	protected List<Directory> initDirectories() throws Exception {
		long companyId = 0;
		String firstName = null;
		String middleName = null;
		String lastName = null;
		String screenName = getScreenName();
		String emailAddress = null;
		Boolean active = Boolean.TRUE;
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		boolean andSearch = true;
		int start = 0;
		int end = PortletPropsValues.SEARCH_MAX_SIZE;
		OrderByComparator orderByComparator = new UserScreenNameComparator();

		if (_group != null) {
			companyId = _group.getCompanyId();

			params.put("usersGroups", _group.getGroupId());
		}
		else if (_organization != null) {
			companyId = _organization.getCompanyId();

			params.put("usersOrgs", _organization.getOrganizationId());
		}
		else if (_role != null) {
			companyId = _role.getCompanyId();

			params.put("usersRoles", _role.getRoleId());
		}
		else if (_company != null) {
			companyId = _company.getCompanyId();
		}

		List<User> users = UserLocalServiceUtil.search(
			companyId, firstName, middleName, lastName, screenName,
			emailAddress, active, params, andSearch, start, end,
			orderByComparator);

		for (User user : users) {
			if (user.isDefaultUser()) {
				continue;
			}

			Directory userDirectory = new UserDirectory(user, this);

			addAttribute("member", userDirectory.getName().getName());

			_directories.add(userDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	protected void setGroup(Group group) {
		_group = group;
	}

	protected void setOrganization(Organization organization) {
		_organization = organization;
	}

	protected void setRole(Role role) {
		_role = role;
	}

	private Company _company;
	private List<Directory> _directories = new ArrayList<Directory>();
	private Group _group;
	private Organization _organization;
	private Role _role;

}