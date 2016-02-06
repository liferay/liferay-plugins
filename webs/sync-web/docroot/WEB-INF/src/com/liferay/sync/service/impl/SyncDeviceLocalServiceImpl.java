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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.base.SyncDeviceLocalServiceBaseImpl;
import com.liferay.sync.shared.util.SyncDeviceConstants;

import java.util.Date;
import java.util.List;

/**
 * @author Shinn Lok
 */
@ProviderType
public class SyncDeviceLocalServiceImpl extends SyncDeviceLocalServiceBaseImpl {

	@Override
	public SyncDevice addSyncDevice(
			long userId, String type, int buildNumber, int featureSet)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long syncDeviceId = counterLocalService.increment();

		SyncDevice syncDevice = syncDevicePersistence.create(syncDeviceId);

		syncDevice.setCompanyId(user.getCompanyId());
		syncDevice.setUserId(user.getUserId());
		syncDevice.setUserName(user.getFullName());
		syncDevice.setCreateDate(now);
		syncDevice.setModifiedDate(now);
		syncDevice.setType(type);
		syncDevice.setBuildNumber(buildNumber);
		syncDevice.setFeatureSet(featureSet);
		syncDevice.setStatus(SyncDeviceConstants.STATUS_ACTIVE);

		syncDevicePersistence.update(syncDevice);

		return syncDevice;
	}

	@Override
	public List<SyncDevice> search(
		long companyId, String keywords, int start, int end,
		OrderByComparator<SyncDevice> orderByComparator) {

		return syncDevicePersistence.findByC_U(
			companyId, StringUtil.quote(keywords, StringPool.PERCENT), start,
			end, orderByComparator);
	}

	@Override
	public void updateStatus(long syncDeviceId, int status)
		throws PortalException {

		SyncDevice syncDevice = syncDevicePersistence.findByPrimaryKey(
			syncDeviceId);

		syncDevice.setStatus(status);

		syncDevicePersistence.update(syncDevice);
	}

	@Override
	public SyncDevice updateSyncDevice(
			long syncDeviceId, String type, int buildNumber, int featureSet,
			int status)
		throws PortalException {

		SyncDevice syncDevice = syncDevicePersistence.findByPrimaryKey(
			syncDeviceId);

		syncDevice.setModifiedDate(new Date());
		syncDevice.setType(type);
		syncDevice.setBuildNumber(buildNumber);
		syncDevice.setFeatureSet(featureSet);
		syncDevice.setStatus(status);

		syncDevicePersistence.update(syncDevice);

		return syncDevice;
	}

}