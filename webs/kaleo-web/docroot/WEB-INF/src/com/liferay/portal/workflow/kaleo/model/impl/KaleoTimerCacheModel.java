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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

import java.util.Date;

/**
 * The cache model class for representing KaleoTimer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimer
 * @generated
 */
public class KaleoTimerCacheModel implements CacheModel<KaleoTimer> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{kaleoTimerId=");
		sb.append(kaleoTimerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", kaleoNodeId=");
		sb.append(kaleoNodeId);
		sb.append(", parentKaleoNodeId=");
		sb.append(parentKaleoNodeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", defaultTimer=");
		sb.append(defaultTimer);
		sb.append(", description=");
		sb.append(description);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", scale=");
		sb.append(scale);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTimer toEntityModel() {
		KaleoTimerImpl kaleoTimerImpl = new KaleoTimerImpl();

		kaleoTimerImpl.setKaleoTimerId(kaleoTimerId);
		kaleoTimerImpl.setGroupId(groupId);
		kaleoTimerImpl.setCompanyId(companyId);
		kaleoTimerImpl.setUserId(userId);

		if (userName == null) {
			kaleoTimerImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setUserName(userName);
		}

		if (createDate > 0) {
			kaleoTimerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate > 0) {
			kaleoTimerImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTimerImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTimerImpl.setKaleoNodeId(kaleoNodeId);
		kaleoTimerImpl.setParentKaleoNodeId(parentKaleoNodeId);

		if (name == null) {
			kaleoTimerImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setName(name);
		}

		kaleoTimerImpl.setDefaultTimer(defaultTimer);

		if (description == null) {
			kaleoTimerImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setDescription(description);
		}

		kaleoTimerImpl.setDuration(duration);

		if (scale == null) {
			kaleoTimerImpl.setScale(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setScale(scale);
		}

		kaleoTimerImpl.resetOriginalValues();

		return kaleoTimerImpl;
	}

	public long kaleoTimerId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoNodeId;
	public long parentKaleoNodeId;
	public String name;
	public boolean defaultTimer;
	public String description;
	public double duration;
	public String scale;
}