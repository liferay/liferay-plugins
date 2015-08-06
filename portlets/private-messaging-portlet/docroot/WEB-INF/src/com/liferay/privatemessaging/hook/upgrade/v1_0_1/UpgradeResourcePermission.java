/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.privatemessaging.hook.upgrade.v1_0_1;

import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ResourceLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFolder;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class UpgradeResourcePermission extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				company.getCompanyId());

			upgradeDLFolderResourcePermission(company, group);
			upgradeDLResourcePermission(company, group);
		}
	}

	private void upgradeDLFolderResourcePermission(Company company, Group group)
		throws PortalException {

		Repository repository = RepositoryLocalServiceUtil.fetchRepository(
			group.getGroupId(), MBPortletKeys.MESSAGE_BOARDS);

		if (repository == null) {
			return;
		}

		long folderId = repository.getDlFolderId();

		Role role = RoleLocalServiceUtil.getRole(
			company.getCompanyId(), RoleConstants.GUEST);

		if (ResourcePermissionLocalServiceUtil.hasResourcePermission(
				company.getCompanyId(), DLFolder.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(folderId),
				role.getRoleId(), ActionKeys.VIEW)) {

			return;
		}

		ResourceLocalServiceUtil.addResources(
			company.getCompanyId(), group.getGroupId(), 0,
			DLFolder.class.getName(), folderId, false, true, true);
	}

	private void upgradeDLResourcePermission(Company company, Group group)
		throws PortalException {

		int count =
			ResourcePermissionLocalServiceUtil.getResourcePermissionsCount(
				company.getCompanyId(), _DL_RESOURCE_NAME,
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(group.getGroupId()));

		if (count > 0) {
			return;
		}

		ResourceLocalServiceUtil.addResources(
			company.getCompanyId(), group.getGroupId(), 0, _DL_RESOURCE_NAME,
			group.getGroupId(), false, true, true);
	}

	private static final String _DL_RESOURCE_NAME =
		"com.liferay.portlet.documentlibrary";

}