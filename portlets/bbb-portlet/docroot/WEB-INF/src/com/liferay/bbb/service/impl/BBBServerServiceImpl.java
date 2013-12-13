/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.bbb.service.impl;

import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.base.BBBServerServiceBaseImpl;
import com.liferay.bbb.service.permission.AdminPermission;
import com.liferay.bbb.service.permission.BBBServerPermission;
import com.liferay.bbb.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBServerServiceImpl extends BBBServerServiceBaseImpl {

	@Override
	public BBBServer addBBBServer(
			long groupId, String name, String url, String secret,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_SERVER);

		return bbbServerLocalService.addBBBServer(
			getUserId(), groupId, name, url, secret, serviceContext);
	}

	@Override
	public BBBServer deleteBBBServer(long bbbServerId)
		throws PortalException, SystemException {

		BBBServerPermission.check(
			getPermissionChecker(), bbbServerId, ActionKeys.DELETE);

		return bbbServerLocalService.deleteBBBServer(bbbServerId);
	}

	@Override
	public List<BBBServer> getBBBServers(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return bbbServerPersistence.filterFindByGroupId(
			groupId, start, end, obc);
	}

	@Override
	public int getBBBServersCount(long groupId) throws SystemException {
		return bbbServerPersistence.filterCountByGroupId(groupId);
	}

	@Override
	public BBBServer updateBBBServer(
			long bbbServerId, String name, String url, String secret,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		BBBServerPermission.check(
			getPermissionChecker(), bbbServerId, ActionKeys.UPDATE);

		return bbbServerLocalService.updateBBBServer(
			bbbServerId, name, url, secret, serviceContext);
	}

}