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

package com.liferay.marketplace.hook.upgrade.v1_0_0;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import java.util.List;

/**
 * @author Peter Shin
 */
public class UpgradeExpando extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateMPExpandoColumns(companyId);
		}
	}

	protected void updateMPExpandoColumns(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(), "MP");
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn oldExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, User.class.getName(), expandoTable.getName(),
				"client-id");

		if (oldExpandoColumn == null) {
			return;
		}

		ExpandoColumn newExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, User.class.getName(), expandoTable.getName(),
				"clientId");

		if (newExpandoColumn == null) {
			newExpandoColumn = ExpandoColumnLocalServiceUtil.updateColumn(
				oldExpandoColumn.getColumnId(), "clientId",
				ExpandoColumnConstants.STRING);
		}

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				oldExpandoColumn.getColumnId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			ExpandoValueLocalServiceUtil.addValue(
				expandoValue.getCompanyId(), User.class.getName(),
				expandoTable.getName(), newExpandoColumn.getName(),
				expandoValue.getClassPK(), expandoValue.getString());
		}

		ExpandoColumnLocalServiceUtil.deleteColumn(
			oldExpandoColumn.getColumnId());
	}

}