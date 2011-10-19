/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.model.impl;

import com.liferay.chat.model.Status;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing Status in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Status
 * @generated
 */
public class StatusCacheModel implements CacheModel<Status>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{statusId=");
		sb.append(statusId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", online=");
		sb.append(online);
		sb.append(", awake=");
		sb.append(awake);
		sb.append(", activePanelId=");
		sb.append(activePanelId);
		sb.append(", message=");
		sb.append(message);
		sb.append(", playSound=");
		sb.append(playSound);
		sb.append("}");

		return sb.toString();
	}

	public Status toEntityModel() {
		StatusImpl statusImpl = new StatusImpl();

		statusImpl.setStatusId(statusId);
		statusImpl.setUserId(userId);
		statusImpl.setModifiedDate(modifiedDate);
		statusImpl.setOnline(online);
		statusImpl.setAwake(awake);

		if (activePanelId == null) {
			statusImpl.setActivePanelId(StringPool.BLANK);
		}
		else {
			statusImpl.setActivePanelId(activePanelId);
		}

		if (message == null) {
			statusImpl.setMessage(StringPool.BLANK);
		}
		else {
			statusImpl.setMessage(message);
		}

		statusImpl.setPlaySound(playSound);

		statusImpl.resetOriginalValues();

		return statusImpl;
	}

	public long statusId;
	public long userId;
	public long modifiedDate;
	public boolean online;
	public boolean awake;
	public String activePanelId;
	public String message;
	public boolean playSound;
}