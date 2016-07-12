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
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;
import com.liferay.sync.shared.util.SyncDeviceConstants;

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