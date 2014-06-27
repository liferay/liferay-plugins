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

package com.liferay.bbb.service.impl;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;
import com.liferay.bbb.service.base.BBBServerLocalServiceBaseImpl;
import com.liferay.bbb.util.BBBAPIUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Shinn Lok
 */
public class BBBServerLocalServiceImpl extends BBBServerLocalServiceBaseImpl {

	@Override
	public BBBServer addBBBServer(
			long userId, String name, String url, String secret,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long bbbServerId = counterLocalService.increment();

		BBBServer bbbServer = bbbServerPersistence.create(bbbServerId);

		bbbServer.setCompanyId(user.getCompanyId());
		bbbServer.setUserId(user.getUserId());
		bbbServer.setUserName(user.getFullName());
		bbbServer.setCreateDate(serviceContext.getCreateDate(now));
		bbbServer.setModifiedDate(serviceContext.getModifiedDate(now));
		bbbServer.setName(name);
		bbbServer.setUrl(formatURL(url));
		bbbServer.setSecret(secret);
		bbbServer.setActive(BBBAPIUtil.isServerActive(bbbServer));

		bbbServerPersistence.update(bbbServer);

		return bbbServer;
	}

	@Override
	public void checkBBBServers() throws PortalException {
		List<BBBServer> bbbServers = bbbServerPersistence.findAll();

		for (BBBServer bbbServer : bbbServers) {
			bbbServer.setActive(BBBAPIUtil.isServerActive(bbbServer));

			bbbServerPersistence.update(bbbServer);
		}

		List<BBBMeeting> bbbMeetings =
			BBBMeetingLocalServiceUtil.getBBBMeetings(
				BBBMeetingConstants.STATUS_IN_PROGRESS);

		for (BBBMeeting bbbMeeting : bbbMeetings) {
			if (!BBBAPIUtil.isMeetingRunning(bbbMeeting.getBbbMeetingId())) {
				bbbMeeting.setStatus(BBBMeetingConstants.STATUS_COMPLETED);

				bbbMeetingPersistence.update(bbbMeeting);
			}
		}
	}

	@Override
	public BBBServer deleteBBBServer(BBBServer bbbServer) {
		bbbServerPersistence.remove(bbbServer);

		return bbbServer;
	}

	@Override
	public BBBServer deleteBBBServer(long bbbServerId) throws PortalException {
		BBBServer bbbServer = bbbServerPersistence.findByPrimaryKey(
			bbbServerId);

		return deleteBBBServer(bbbServer);
	}

	@Override
	public List<BBBServer> getBBBServers(boolean active) {
		return bbbServerPersistence.findByActive(active);
	}

	@Override
	public List<BBBServer> getBBBServers(
		int start, int end, OrderByComparator obc) {

		return bbbServerPersistence.findAll(start, end, obc);
	}

	@Override
	public int getBBBServersCount() {
		return bbbServerPersistence.countAll();
	}

	@Override
	public BBBServer updateBBBServer(
			long bbbServerId, String name, String url, String secret,
			ServiceContext serviceContext)
		throws PortalException {

		BBBServer bbbServer = bbbServerPersistence.findByPrimaryKey(
			bbbServerId);

		bbbServer.setModifiedDate(serviceContext.getModifiedDate(null));
		bbbServer.setName(name);
		bbbServer.setUrl(formatURL(url));
		bbbServer.setSecret(secret);
		bbbServer.setActive(BBBAPIUtil.isServerActive(bbbServer));

		return bbbServerPersistence.update(bbbServer);
	}

	protected String formatURL(String url) {
		if (!url.endsWith(StringPool.SLASH)) {
			url += StringPool.SLASH;
		}

		return url;
	}

}