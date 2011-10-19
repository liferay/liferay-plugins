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

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTimer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimer
 * @generated
 */
public class KaleoTimerCacheModel implements CacheModel<KaleoTimer>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

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
		sb.append(", kaleoClassName=");
		sb.append(kaleoClassName);
		sb.append(", kaleoClassPK=");
		sb.append(kaleoClassPK);
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", blocking=");
		sb.append(blocking);
		sb.append(", description=");
		sb.append(description);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", scale=");
		sb.append(scale);
		sb.append(", recurrenceDuration=");
		sb.append(recurrenceDuration);
		sb.append(", recurrenceScale=");
		sb.append(recurrenceScale);
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

		if (createDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setCreateDate(null);
		}
		else {
			kaleoTimerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setModifiedDate(null);
		}
		else {
			kaleoTimerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoTimerImpl.setKaleoClassName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoTimerImpl.setKaleoClassPK(kaleoClassPK);
		kaleoTimerImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (name == null) {
			kaleoTimerImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setName(name);
		}

		kaleoTimerImpl.setBlocking(blocking);

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

		kaleoTimerImpl.setRecurrenceDuration(recurrenceDuration);

		if (recurrenceScale == null) {
			kaleoTimerImpl.setRecurrenceScale(StringPool.BLANK);
		}
		else {
			kaleoTimerImpl.setRecurrenceScale(recurrenceScale);
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
	public String kaleoClassName;
	public long kaleoClassPK;
	public long kaleoDefinitionId;
	public String name;
	public boolean blocking;
	public String description;
	public double duration;
	public String scale;
	public double recurrenceDuration;
	public String recurrenceScale;
}