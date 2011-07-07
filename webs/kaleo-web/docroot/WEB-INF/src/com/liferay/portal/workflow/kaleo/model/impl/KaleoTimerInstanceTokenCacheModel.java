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
import com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken;

import java.util.Date;

/**
 * The cache model class for representing KaleoTimerInstanceToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimerInstanceToken
 * @generated
 */
public class KaleoTimerInstanceTokenCacheModel implements CacheModel<KaleoTimerInstanceToken> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoTimerInstanceTokenId=");
		sb.append(kaleoTimerInstanceTokenId);
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
		sb.append(", kaleoInstanceTokenId=");
		sb.append(kaleoInstanceTokenId);
		sb.append(", kaleoTimerId=");
		sb.append(kaleoTimerId);
		sb.append(", kaleoTimerName=");
		sb.append(kaleoTimerName);
		sb.append(", completionUserId=");
		sb.append(completionUserId);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append(", workflowContext=");
		sb.append(workflowContext);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTimerInstanceToken toEntityModel() {
		KaleoTimerInstanceTokenImpl kaleoTimerInstanceTokenImpl = new KaleoTimerInstanceTokenImpl();

		kaleoTimerInstanceTokenImpl.setKaleoTimerInstanceTokenId(kaleoTimerInstanceTokenId);
		kaleoTimerInstanceTokenImpl.setGroupId(groupId);
		kaleoTimerInstanceTokenImpl.setCompanyId(companyId);
		kaleoTimerInstanceTokenImpl.setUserId(userId);

		if (userName == null) {
			kaleoTimerInstanceTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTimerInstanceTokenImpl.setUserName(userName);
		}

		if (createDate > 0) {
			kaleoTimerInstanceTokenImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate > 0) {
			kaleoTimerInstanceTokenImpl.setModifiedDate(new Date(modifiedDate));
		}

		kaleoTimerInstanceTokenImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTimerInstanceTokenImpl.setKaleoInstanceId(kaleoInstanceId);
		kaleoTimerInstanceTokenImpl.setKaleoInstanceTokenId(kaleoInstanceTokenId);
		kaleoTimerInstanceTokenImpl.setKaleoTimerId(kaleoTimerId);

		if (kaleoTimerName == null) {
			kaleoTimerInstanceTokenImpl.setKaleoTimerName(StringPool.BLANK);
		}
		else {
			kaleoTimerInstanceTokenImpl.setKaleoTimerName(kaleoTimerName);
		}

		kaleoTimerInstanceTokenImpl.setCompletionUserId(completionUserId);
		kaleoTimerInstanceTokenImpl.setCompleted(completed);

		if (completionDate > 0) {
			kaleoTimerInstanceTokenImpl.setCompletionDate(new Date(
					completionDate));
		}

		if (workflowContext == null) {
			kaleoTimerInstanceTokenImpl.setWorkflowContext(StringPool.BLANK);
		}
		else {
			kaleoTimerInstanceTokenImpl.setWorkflowContext(workflowContext);
		}

		kaleoTimerInstanceTokenImpl.resetOriginalValues();

		return kaleoTimerInstanceTokenImpl;
	}

	public long kaleoTimerInstanceTokenId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoInstanceId;
	public long kaleoInstanceTokenId;
	public long kaleoTimerId;
	public String kaleoTimerName;
	public long completionUserId;
	public boolean completed;
	public long completionDate;
	public String workflowContext;
}