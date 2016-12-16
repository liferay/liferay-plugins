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

package com.liferay.sync.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

import java.util.Date;

/**
 * @author Shinn Lok
 */
public class ResourcePermissionModelListener
	extends SyncBaseModelListener<ResourcePermission> {

	@Override
	public void onBeforeCreate(ResourcePermission resourcePermission)
		throws ModelListenerException {

		try {
			SyncDLObject syncDLObject = fetchSyncDLObject(resourcePermission);

			if (syncDLObject == null) {
				return;
			}

			if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
				updateSyncDLObject(syncDLObject);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeRemove(ResourcePermission resourcePermission)
		throws ModelListenerException {

		try {
			SyncDLObject syncDLObject = fetchSyncDLObject(resourcePermission);

			if (syncDLObject == null) {
				return;
			}

			if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
				Date date = new Date();

				syncDLObject.setModifiedTime(date.getTime());
				syncDLObject.setLastPermissionChangeDate(date);

				SyncDLObjectLocalServiceUtil.updateSyncDLObject(syncDLObject);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(ResourcePermission resourcePermission)
		throws ModelListenerException {

		try {
			SyncDLObject syncDLObject = fetchSyncDLObject(resourcePermission);

			if (syncDLObject == null) {
				return;
			}

			ResourcePermission originalResourcePermission =
				ResourcePermissionLocalServiceUtil.fetchResourcePermission(
					resourcePermission.getResourcePermissionId());

			if (originalResourcePermission.hasActionId(ActionKeys.VIEW) &&
				!resourcePermission.hasActionId(ActionKeys.VIEW)) {

				Date date = new Date();

				syncDLObject.setModifiedTime(date.getTime());
				syncDLObject.setLastPermissionChangeDate(date);

				SyncDLObjectLocalServiceUtil.updateSyncDLObject(syncDLObject);
			}
			else if (!originalResourcePermission.hasActionId(ActionKeys.VIEW) &&
					 resourcePermission.hasActionId(ActionKeys.VIEW)) {

				updateSyncDLObject(syncDLObject);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}