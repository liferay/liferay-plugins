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

package com.liferay.socialcoding.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		if (companyId == PortalUtil.getDefaultCompanyId()) {
			setUpExpando(companyId);
		}
	}

	protected void setUpExpando(long companyId) throws Exception {
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				companyId, User.class.getName(), "SC");
		}
		catch (DuplicateTableNameException dtne) {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(), "SC");
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "jiraUserId",
				ExpandoColumnConstants.STRING);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

}