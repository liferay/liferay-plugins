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

package com.liferay.dlfilename.hook.events;

import com.liferay.dlfilename.hook.model.impl.DLFileNameFileEntryImpl;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Preston Crary
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			long companyId = GetterUtil.getLong(ids[0]);

			doRun(companyId, DLFileEntry.class.getName());
			doRun(companyId, DLFileVersion.class.getName());
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId, String className) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (DuplicateTableNameException dtne) {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumn expandoColumn =
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(),
					DLFileNameFileEntryImpl.EXPAND_COLUMN_NAME_DISPLAY_TITLE,
					ExpandoColumnConstants.STRING);

			UnicodeProperties typeSettingsProperties = new UnicodeProperties();

			typeSettingsProperties.setProperty(
				ExpandoColumnConstants.INDEX_TYPE,
				String.valueOf(ExpandoColumnConstants.INDEX_TYPE_TEXT));
			typeSettingsProperties.setProperty(
				ExpandoColumnConstants.PROPERTY_HIDDEN,
				Boolean.TRUE.toString());

			expandoColumn.setTypeSettingsProperties(typeSettingsProperties);

			ExpandoColumnLocalServiceUtil.updateExpandoColumn(expandoColumn);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

}