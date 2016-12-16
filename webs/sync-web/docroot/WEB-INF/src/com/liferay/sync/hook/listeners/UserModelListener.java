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
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;
import com.liferay.sync.shared.util.SyncDeviceConstants;

import java.util.Date;
import java.util.List;

/**
 * @author Jonsthan McCann
 */
public class UserModelListener extends SyncBaseModelListener<User> {

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

		try {
			List<ResourcePermission> resourcePermissions =
				ResourcePermissionLocalServiceUtil.getRoleResourcePermissions(
					(Long)associationClassPK);

			for (ResourcePermission resourcePermission : resourcePermissions) {
				if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
					SyncDLObject syncDLObject = fetchSyncDLObject(
						resourcePermission);

					if (syncDLObject == null) {
						continue;
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

		try {
			List<ResourcePermission> resourcePermissions =
				ResourcePermissionLocalServiceUtil.getRoleResourcePermissions(
					(Long)associationClassPK);

			for (ResourcePermission resourcePermission : resourcePermissions) {
				if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
					SyncDLObject syncDLObject = fetchSyncDLObject(
						resourcePermission);

					if (syncDLObject == null) {
						continue;
					}

					Date date = new Date();

					syncDLObject.setModifiedTime(date.getTime());
					syncDLObject.setLastPermissionChangeDate(date);

					SyncDLObjectLocalServiceUtil.updateSyncDLObject(
						syncDLObject);
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

}