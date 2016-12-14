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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectConstants;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;
import com.liferay.sync.shared.util.SyncDeviceConstants;

import java.util.Date;
import java.util.List;

/**
 * @author Jonsthan McCann
 */
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		try {
			if (!user.isActive()) {
				List<SyncDevice> syncDevices =
					SyncDeviceLocalServiceUtil.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					SyncDeviceLocalServiceUtil.deleteSyncDevice(syncDevice);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(Role.class.getName())) {
			return;
		}

		long roleId = (Long)associationClassPK;

		try {
			List<ResourcePermission> resourcePermissions =
				ResourcePermissionLocalServiceUtil
					.getRoleResourcePermissions(roleId);

			for (ResourcePermission resourcePermission : resourcePermissions) {
				if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
					SyncDLObject syncDLObject =
						fetchSyncDLObject(resourcePermission);
	
					if (syncDLObject == null) {
						return;
					}
	
					updateSyncDLObject(syncDLObject);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(Role.class.getName())) {
			return;
		}

		long roleId = (Long)associationClassPK;

		try {
			List<ResourcePermission> resourcePermissions =
				ResourcePermissionLocalServiceUtil
					.getRoleResourcePermissions(roleId);

			for (ResourcePermission resourcePermission : resourcePermissions) {
				if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
					SyncDLObject syncDLObject =
						fetchSyncDLObject(resourcePermission);
	
					if (syncDLObject == null) {
						continue;
					}
	
					Date date = new Date();

					syncDLObject.setModifiedTime(date.getTime());
					syncDLObject.setLastPermissionChangeDate(date);
	
					SyncDLObjectLocalServiceUtil
						.updateSyncDLObject(syncDLObject);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			User originalUser = UserLocalServiceUtil.getUser(user.getUserId());

			if (originalUser.isActive() && !user.isActive()) {
				List<SyncDevice> syncDevices =
					SyncDeviceLocalServiceUtil.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					SyncDeviceLocalServiceUtil.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_INACTIVE);
				}
			}
			else if (!originalUser.isActive() && user.isActive()) {
				List<SyncDevice> syncDevices =
					SyncDeviceLocalServiceUtil.getSyncDevices(
						user.getUserId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null);

				for (SyncDevice syncDevice : syncDevices) {
					SyncDeviceLocalServiceUtil.updateStatus(
						syncDevice.getSyncDeviceId(),
						SyncDeviceConstants.STATUS_ACTIVE);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected SyncDLObject fetchSyncDLObject(
			ResourcePermission resourcePermission)
		throws SystemException {

		String modelName = resourcePermission.getName();

		if (modelName.equals(DLFileEntry.class.getName())) {
			return SyncDLObjectLocalServiceUtil.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FILE,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}
		else if (modelName.equals(DLFolder.class.getName())) {
			return SyncDLObjectLocalServiceUtil.fetchSyncDLObject(
				SyncDLObjectConstants.TYPE_FOLDER,
				GetterUtil.getLong(resourcePermission.getPrimKey()));
		}

		return null;
	}

	protected void updateSyncDLObject(SyncDLObject syncDLObject)
		throws SystemException {

		syncDLObject.setModifiedTime(System.currentTimeMillis());

		SyncDLObjectLocalServiceUtil.updateSyncDLObject(syncDLObject);

		String type = syncDLObject.getType();

		if (!type.equals(SyncDLObjectConstants.TYPE_FOLDER)) {
			return;
		}

		List<SyncDLObject> childSyncDLObjects =
			SyncDLObjectLocalServiceUtil.getSyncDLObjects(
				syncDLObject.getRepositoryId(), syncDLObject.getTypePK());

		for (SyncDLObject childSyncDLObject : childSyncDLObjects) {
			updateSyncDLObject(childSyncDLObject);
		}
	}

}