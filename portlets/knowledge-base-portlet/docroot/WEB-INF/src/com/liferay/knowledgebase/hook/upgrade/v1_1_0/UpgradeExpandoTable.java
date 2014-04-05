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

package com.liferay.knowledgebase.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Peter Shin
 */
public class UpgradeExpandoTable extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoTable(PortalUtil.getDefaultCompanyId());
	}

	protected void updateExpandoTable(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, Subscription.class.getName(), "KB");
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoTableLocalServiceUtil.deleteExpandoTable(expandoTable);
	}

}