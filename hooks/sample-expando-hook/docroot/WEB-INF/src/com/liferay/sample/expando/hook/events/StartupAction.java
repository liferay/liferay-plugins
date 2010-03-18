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

package com.liferay.sample.expando.hook.events;


import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.expando.model.ExpandoTableConstants;

/**
 * <a href="StartupAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StartupAction extends SimpleAction {

	public void run(String[] ids) throws ActionException {
		try {
			doRun(GetterUtil.getLong(ids[0]));
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(long companyId) throws Exception {
		
		String className = StringPool.BLANK;
		String newFieldName = StringPool.BLANK;
		int newFieldType = 0;

		className = CalEvent.class.getName();
		newFieldName = "URL";
		newFieldType = ExpandoColumnConstants.STRING;

		createNewField(
			className, newFieldName, newFieldType, companyId);

		System.out.println("Confirmed the creation of the field " +
			newFieldName + " for the entity "+ className);
	}

	private void createNewField(
		String entityName, String newFieldName,	int newFieldType,
		long companyId) throws SystemException{

		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				entityName, ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (Exception dtne) {
			try{

				table = ExpandoTableLocalServiceUtil.getTable(
					entityName, ExpandoTableConstants.DEFAULT_TABLE_NAME);
			} catch (Exception e){
			}
		}
		
		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(),
				newFieldName,
				newFieldType);
		}
		catch (Exception dcne) {
		}
	}
}
