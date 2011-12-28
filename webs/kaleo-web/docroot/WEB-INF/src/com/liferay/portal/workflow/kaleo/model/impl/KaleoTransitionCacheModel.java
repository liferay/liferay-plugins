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
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTransition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTransition
 * @generated
 */
public class KaleoTransitionCacheModel implements CacheModel<KaleoTransition>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoTransitionId=");
		sb.append(kaleoTransitionId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", sourceKaleoNodeId=");
		sb.append(sourceKaleoNodeId);
		sb.append(", sourceKaleoNodeName=");
		sb.append(sourceKaleoNodeName);
		sb.append(", targetKaleoNodeId=");
		sb.append(targetKaleoNodeId);
		sb.append(", targetKaleoNodeName=");
		sb.append(targetKaleoNodeName);
		sb.append(", defaultTransition=");
		sb.append(defaultTransition);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTransition toEntityModel() {
		KaleoTransitionImpl kaleoTransitionImpl = new KaleoTransitionImpl();

		kaleoTransitionImpl.setKaleoTransitionId(kaleoTransitionId);
		kaleoTransitionImpl.setGroupId(groupId);
		kaleoTransitionImpl.setCompanyId(companyId);
		kaleoTransitionImpl.setUserId(userId);

		if (userName == null) {
			kaleoTransitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTransitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTransitionImpl.setCreateDate(null);
		}
		else {
			kaleoTransitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTransitionImpl.setModifiedDate(null);
		}
		else {
			kaleoTransitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTransitionImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTransitionImpl.setKaleoNodeId(kaleoNodeId);

		if (name == null) {
			kaleoTransitionImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoTransitionImpl.setName(name);
		}

		if (description == null) {
			kaleoTransitionImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoTransitionImpl.setDescription(description);
		}

		kaleoTransitionImpl.setSourceKaleoNodeId(sourceKaleoNodeId);

		if (sourceKaleoNodeName == null) {
			kaleoTransitionImpl.setSourceKaleoNodeName(StringPool.BLANK);
		}
		else {
			kaleoTransitionImpl.setSourceKaleoNodeName(sourceKaleoNodeName);
		}

		kaleoTransitionImpl.setTargetKaleoNodeId(targetKaleoNodeId);

		if (targetKaleoNodeName == null) {
			kaleoTransitionImpl.setTargetKaleoNodeName(StringPool.BLANK);
		}
		else {
			kaleoTransitionImpl.setTargetKaleoNodeName(targetKaleoNodeName);
		}

		kaleoTransitionImpl.setDefaultTransition(defaultTransition);

		kaleoTransitionImpl.resetOriginalValues();

		return kaleoTransitionImpl;
	}

	public long kaleoTransitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoNodeId;
	public String name;
	public String description;
	public long sourceKaleoNodeId;
	public String sourceKaleoNodeName;
	public long targetKaleoNodeId;
	public String targetKaleoNodeName;
	public boolean defaultTransition;
}