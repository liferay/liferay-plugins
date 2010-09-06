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

package com.liferay.knowledgebase.hook.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Subscription;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
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
		setupExpando(companyId);
	}

	protected void setupExpando(long companyId) throws Exception {
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				companyId, Subscription.class.getName(), "KB");
		}
		catch (DuplicateTableNameException dtne) {
			table = ExpandoTableLocalServiceUtil.getTable(
				companyId, Subscription.class.getName(), "KB");
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), "portletIds",
				ExpandoColumnConstants.STRING_ARRAY);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

}