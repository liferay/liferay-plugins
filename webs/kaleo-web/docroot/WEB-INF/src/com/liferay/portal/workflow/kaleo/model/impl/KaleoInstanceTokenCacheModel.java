/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoInstanceToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceToken
 * @generated
 */
public class KaleoInstanceTokenCacheModel implements CacheModel<KaleoInstanceToken>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoInstanceTokenId=");
		sb.append(kaleoInstanceTokenId);
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
		sb.append(", kaleoInstanceId=");
		sb.append(kaleoInstanceId);
		sb.append(", parentKaleoInstanceTokenId=");
		sb.append(parentKaleoInstanceTokenId);
		sb.append(", currentKaleoNodeId=");
		sb.append(currentKaleoNodeId);
		sb.append(", currentKaleoNodeName=");
		sb.append(currentKaleoNodeName);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append("}");

		return sb.toString();
	}

	public KaleoInstanceToken toEntityModel() {
		KaleoInstanceTokenImpl kaleoInstanceTokenImpl = new KaleoInstanceTokenImpl();

		kaleoInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoInstanceTokenImpl.setGroupId(groupId);
		kaleoInstanceTokenImpl.setCompanyId(companyId);
		kaleoInstanceTokenImpl.setUserId(userId);

		if (userName == null) {
			kaleoInstanceTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoInstanceTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setCreateDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setModifiedDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoInstanceTokenImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoInstanceTokenImpl.setParentKaleoInstanceTokenId(parentKaleoInstanceTokenId);
		kaleoInstanceTokenImpl.setCurrentKaleoNodeId(currentKaleoNodeId);

		if (currentKaleoNodeName == null) {
			kaleoInstanceTokenImpl.setCurrentKaleoNodeName(StringPool.BLANK);
		}
		else {
			kaleoInstanceTokenImpl.setCurrentKaleoNodeName(currentKaleoNodeName);
		}

		if (className == null) {
			kaleoInstanceTokenImpl.setClassName(StringPool.BLANK);
		}
		else {
			kaleoInstanceTokenImpl.setClassName(className);
		}

		kaleoInstanceTokenImpl.setClassPK(classPK);
		kaleoInstanceTokenImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			kaleoInstanceTokenImpl.setCompletionDate(null);
		}
		else {
			kaleoInstanceTokenImpl.setCompletionDate(new Date(completionDate));
		}

		kaleoInstanceTokenImpl.resetOriginalValues();

		return kaleoInstanceTokenImpl;
	}

	public long kaleoInstanceTokenId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoInstanceId;
	public long parentKaleoInstanceTokenId;
	public long currentKaleoNodeId;
	public String currentKaleoNodeName;
	public String className;
	public long classPK;
	public boolean completed;
	public long completionDate;
}