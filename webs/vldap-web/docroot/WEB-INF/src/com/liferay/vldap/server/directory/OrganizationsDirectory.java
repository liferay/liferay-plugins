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

import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class OrganizationsDirectory extends BaseDirectory {

	public OrganizationsDirectory(Directory parentDirectory)
		throws Exception {

		super("ou=Organizations", parentDirectory);

		initAttributes();
	}

	protected void initAttributes() {
		addAttribute("objectclass", "organizationalUnit");
		addAttribute("objectclass", "top");
		addAttribute("ou", "Organizations");
	}

	protected List<Directory> initDirectories() throws Exception {
		List<Organization> organizations = Collections.emptyList();

		if (_user != null) {
			organizations = OrganizationLocalServiceUtil.getUserOrganizations(
				_user.getUserId());
		}
		else if (_company != null) {
			organizations = OrganizationLocalServiceUtil.search(
				_company.getCompanyId(),
				OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, null, null,
				null, null, null, 0, PortletPropsValues.SEARCH_MAX_SIZE, null);
		}

		for (Organization organization : organizations) {
			Directory organizationDirectory = new OrganizationDirectory(
				organization, this);

			_directories.add(organizationDirectory);
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