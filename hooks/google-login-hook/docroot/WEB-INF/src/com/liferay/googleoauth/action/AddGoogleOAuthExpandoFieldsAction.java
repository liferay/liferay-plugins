/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.googleoauth.action;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Sergio González
 */
public class AddGoogleOAuthExpandoFieldsAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addColumn(
			long tableId, String name, UnicodeProperties properties)
		throws PortalException, SystemException {

		ExpandoColumn expandoColum = ExpandoColumnLocalServiceUtil.getColumn(
			tableId, name);

		if (expandoColum != null) {
			return;
		}

		expandoColum = ExpandoColumnLocalServiceUtil.addColumn(
			tableId, name, ExpandoColumnConstants.STRING);

		ExpandoColumnLocalServiceUtil.updateTypeSettings(
			expandoColum.getColumnId(), properties.toString());
	}

	protected void doRun(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception e) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		UnicodeProperties properties = new UnicodeProperties();

		properties.setProperty("hidden", "true");
		properties.setProperty("visible-with-update-permission", "false");

		addColumn(expandoTable.getTableId(), "googleAccessToken", properties);
		addColumn(expandoTable.getTableId(), "googleRefreshToken", properties);
		addColumn(expandoTable.getTableId(), "googleUserId", properties);
	}

}