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

package com.liferay.sync.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.base.SyncDeviceServiceBaseImpl;
import com.liferay.sync.shared.util.SyncDeviceConstants;

/**
 * @author Shinn Lok
 */
@ProviderType
public class SyncDeviceServiceImpl extends SyncDeviceServiceBaseImpl {

	@Override
	public SyncDevice registerSyncDevice(
			String type, int buildNumber, int featureSet, String uuid)
		throws PortalException {

		User user = getUser();

		SyncDevice syncDevice =
			syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(
				uuid, user.getCompanyId());

		if (syncDevice == null) {
			return syncDeviceLocalService.addSyncDevice(
				user.getUserId(), type, buildNumber, featureSet);
		}

		if (syncDevice.getUserId() != user.getUserId()) {
			throw new PrincipalException();
		}

		return syncDeviceLocalService.updateSyncDevice(
			syncDevice.getSyncDeviceId(), type, buildNumber, featureSet,
			syncDevice.getStatus());
	}

	@Override
	public void unregisterSyncDevice(String uuid) throws PortalException {
		User user = getUser();

		SyncDevice syncDevice =
			syncDeviceLocalService.fetchSyncDeviceByUuidAndCompanyId(
				uuid, user.getCompanyId());

		if ((syncDevice == null) ||
			(syncDevice.getUserId() != user.getUserId()) ||
			(syncDevice.getStatus() == SyncDeviceConstants.STATUS_WIPED)) {

			return;
		}

		syncDeviceLocalService.deleteSyncDevice(syncDevice);
	}

}