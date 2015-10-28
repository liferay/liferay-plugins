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

package com.liferay.so.hook.upgrade.v3_0_0;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DL;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * @author Matthew Kong
 */
public class UpgradeDocumentLibrary extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateManualCheckInRequired(companyId);
		}
	}

	protected void updateManualCheckInRequired(long companyId)
		throws Exception {

		final ExpandoColumn expandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, DLFileEntry.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME,
				DL.MANUAL_CHECK_IN_REQUIRED);

		if (expandoColumn == null) {
			return;
		}

		ActionableDynamicQuery actionableDynamicQuery =
			ExpandoValueLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property columnIdProperty = PropertyFactoryUtil.forName(
						"columnId");

					dynamicQuery.add(
						columnIdProperty.eq(expandoColumn.getColumnId()));

					Property dataProperty = PropertyFactoryUtil.forName("data");

					dynamicQuery.add(dataProperty.eq(StringPool.TRUE));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<ExpandoValue>() {

				@Override
				public void performAction(ExpandoValue expandoValue)
					throws PortalException {

					DLFileVersion dlFileVersion =
						DLFileVersionLocalServiceUtil.fetchDLFileVersion(
							expandoValue.getClassPK());

					if ((dlFileVersion == null) || dlFileVersion.isDraft()) {
						return;
					}

					DLFileEntry dlFileEntry = dlFileVersion.getFileEntry();

					dlFileEntry.setManualCheckInRequired(true);

					DLFileEntryLocalServiceUtil.updateDLFileEntry(dlFileEntry);
				}

			});

		actionableDynamicQuery.performActions();

		ExpandoColumnLocalServiceUtil.deleteColumn(expandoColumn);
	}

}