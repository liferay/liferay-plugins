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
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoCondition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoCondition
 * @generated
 */
public class KaleoConditionCacheModel implements CacheModel<KaleoCondition>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{kaleoConditionId=");
		sb.append(kaleoConditionId);
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
		sb.append(", script=");
		sb.append(script);
		sb.append(", scriptLanguage=");
		sb.append(scriptLanguage);
		sb.append("}");

		return sb.toString();
	}

	public KaleoCondition toEntityModel() {
		KaleoConditionImpl kaleoConditionImpl = new KaleoConditionImpl();

		kaleoConditionImpl.setKaleoConditionId(kaleoConditionId);
		kaleoConditionImpl.setGroupId(groupId);
		kaleoConditionImpl.setCompanyId(companyId);
		kaleoConditionImpl.setUserId(userId);

		if (userName == null) {
			kaleoConditionImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoConditionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoConditionImpl.setCreateDate(null);
		}
		else {
			kaleoConditionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoConditionImpl.setModifiedDate(null);
		}
		else {
			kaleoConditionImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoConditionImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoConditionImpl.setKaleoNodeId(kaleoNodeId);

		if (script == null) {
			kaleoConditionImpl.setScript(StringPool.BLANK);
		}
		else {
			kaleoConditionImpl.setScript(script);
		}

		if (scriptLanguage == null) {
			kaleoConditionImpl.setScriptLanguage(StringPool.BLANK);
		}
		else {
			kaleoConditionImpl.setScriptLanguage(scriptLanguage);
		}

		kaleoConditionImpl.resetOriginalValues();

		return kaleoConditionImpl;
	}

	public long kaleoConditionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoNodeId;
	public String script;
	public String scriptLanguage;
}