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

package com.liferay.sync.hook.upgrade.v1_0_2;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeSyncDLObject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateColumn(
			"DLFileEntry", "fileEntryId",
			new String[] {
				SyncDLObjectConstants.TYPE_FILE,
				SyncDLObjectConstants.TYPE_PRIVATE_WORKING_COPY
			});
		updateColumn(
			"DLFolder", "folderId",
			new String[] {SyncDLObjectConstants.TYPE_FOLDER});

		upgradeTrashEvents();
	}

	protected void updateColumn(
			String tableName, String primaryKeyColumnName, String[] types)
		throws Exception {

		StringBundler sb = new StringBundler(types.length * 4 + 7);

		sb.append("update SyncDLObject set treePath = (select treePath from ");
		sb.append(tableName);
		sb.append(" where (");
		sb.append(tableName);
		sb.append(StringPool.PERIOD);
		sb.append(primaryKeyColumnName);
		sb.append(" = SyncDLObject.typePK)) where (");

		for (int i = 0; i < types.length; i++) {
			sb.append("type_ = '");
			sb.append(types[i]);
			sb.append(StringPool.APOSTROPHE);

			if ((i + 1) < types.length) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		runSQL(sb.toString());
	}

	protected void upgradeTrashEvents() throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			SyncDLObjectLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					dynamicQuery.add(
						RestrictionsFactoryUtil.eq(
							"event", SyncDLObjectConstants.EVENT_TRASH));
					dynamicQuery.add(
						RestrictionsFactoryUtil.eq(
							"type", SyncDLObjectConstants.TYPE_FOLDER));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDLObject>() {

				@Override
				public void performAction(SyncDLObject syncDLObject)
					throws PortalException {

					SyncDLObjectLocalServiceUtil.trashSyncDLObjects(
						syncDLObject);
				}

			});

		actionableDynamicQuery.performActions();
	}

}