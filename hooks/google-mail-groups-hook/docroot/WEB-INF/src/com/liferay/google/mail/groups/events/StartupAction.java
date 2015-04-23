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

package com.liferay.google.mail.groups.events;

import com.liferay.google.mail.groups.util.GoogleMailGroupsUtil;
import com.liferay.google.mail.groups.util.PortletPropsValues;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Matthew Kong
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			if ((PortletPropsValues.EMAIL_LARGE_GROUP_SIZE >= 0) &&
				Validator.isNotNull(
					PortletPropsValues.EMAIL_LARGE_GROUP_ROLE)) {

				for (String id : ids) {
					long companyId = Long.valueOf(id);

					setUpExpando(companyId);
					setUpRole(companyId);
				}
			}

			if (!PortletPropsValues.SYNC_ON_STARTUP) {
				return;
			}

			GoogleMailGroupsUtil.syncGroups();
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void setUpExpando(long companyId) throws Exception {
		ExpandoTable expandoTable = ExpandoTableLocalServiceUtil.fetchTable(
			companyId, PortalUtil.getClassNameId(Group.class.getName()),
			ExpandoTableConstants.DEFAULT_TABLE_NAME);

		if (expandoTable == null) {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), "googleMailGroupsLargeGroup",
				ExpandoColumnConstants.BOOLEAN);
		}
		catch (Exception e) {
		}
	}

	protected void setUpRole(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.fetchRole(
			companyId, PortletPropsValues.EMAIL_LARGE_GROUP_ROLE);

		if (role != null) {
			return;
		}

		User user = UserLocalServiceUtil.getDefaultUser(companyId);

		RoleLocalServiceUtil.addRole(
			user.getUserId(), null, 0,
			PortletPropsValues.EMAIL_LARGE_GROUP_ROLE, null, null,
			RoleConstants.TYPE_REGULAR, null, new ServiceContext());
	}

}