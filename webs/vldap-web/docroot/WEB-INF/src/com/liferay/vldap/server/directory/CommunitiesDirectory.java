/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CommunitiesDirectory extends BaseDirectory {

	public CommunitiesDirectory(Directory parentDirectory)
		throws Exception {

		super("ou=Communities", parentDirectory);

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Communities");
	}

	protected List<Directory> initDirectories() throws Exception {
		List<Group> groups = Collections.EMPTY_LIST;

		if (_user != null) {
			groups = GroupLocalServiceUtil.getUserGroups(_user.getUserId());
		}
		else if (_company != null) {
			groups = GroupLocalServiceUtil.search(
				_company.getCompanyId(), null, null, null, 0,
				PortletPropsValues.SEARCH_MAX_SIZE);
		}

		for (Group group : groups) {
			Directory communityDirectory = new CommunityDirectory(group, this);

			_directories.add(communityDirectory);
		}

		return _directories;
	}

	protected void setCompany(Company company) {
		_company = company;
	}

	protected void setUser(User user) {
		_user = user;
	}

	private Company _company;
	private List<Directory> _directories = new ArrayList<Directory>();
	private User _user;

}