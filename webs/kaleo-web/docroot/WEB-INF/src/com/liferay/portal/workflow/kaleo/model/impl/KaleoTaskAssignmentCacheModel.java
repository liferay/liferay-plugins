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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing KaleoTaskAssignment in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignment
 * @generated
 */
public class KaleoTaskAssignmentCacheModel implements CacheModel<KaleoTaskAssignment>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoTaskAssignmentId=");
		sb.append(kaleoTaskAssignmentId);
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
		sb.append(", kaleoNodeId=");
		sb.append(kaleoNodeId);
		sb.append(", assigneeClassName=");
		sb.append(assigneeClassName);
		sb.append(", assigneeClassPK=");
		sb.append(assigneeClassPK);
		sb.append(", assigneeActionId=");
		sb.append(assigneeActionId);
		sb.append(", assigneeScript=");
		sb.append(assigneeScript);
		sb.append(", assigneeScriptLanguage=");
		sb.append(assigneeScriptLanguage);
		sb.append("}");

		return sb.toString();
	}

	public KaleoTaskAssignment toEntityModel() {
		KaleoTaskAssignmentImpl kaleoTaskAssignmentImpl = new KaleoTaskAssignmentImpl();

		kaleoTaskAssignmentImpl.setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
		kaleoTaskAssignmentImpl.setGroupId(groupId);
		kaleoTaskAssignmentImpl.setCompanyId(companyId);
		kaleoTaskAssignmentImpl.setUserId(userId);

		if (userName == null) {
			kaleoTaskAssignmentImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentImpl.setCreateDate(null);
		}
		else {
			kaleoTaskAssignmentImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTaskAssignmentImpl.setModifiedDate(null);
		}
		else {
			kaleoTaskAssignmentImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoTaskAssignmentImpl.setKaleoClassName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoTaskAssignmentImpl.setKaleoClassPK(kaleoClassPK);
		kaleoTaskAssignmentImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskAssignmentImpl.setKaleoNodeId(kaleoNodeId);

		if (assigneeClassName == null) {
			kaleoTaskAssignmentImpl.setAssigneeClassName(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeClassName(assigneeClassName);
		}

		kaleoTaskAssignmentImpl.setAssigneeClassPK(assigneeClassPK);

		if (assigneeActionId == null) {
			kaleoTaskAssignmentImpl.setAssigneeActionId(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeActionId(assigneeActionId);
		}

		if (assigneeScript == null) {
			kaleoTaskAssignmentImpl.setAssigneeScript(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeScript(assigneeScript);
		}

		if (assigneeScriptLanguage == null) {
			kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(StringPool.BLANK);
		}
		else {
			kaleoTaskAssignmentImpl.setAssigneeScriptLanguage(assigneeScriptLanguage);
		}

		kaleoTaskAssignmentImpl.resetOriginalValues();

		return kaleoTaskAssignmentImpl;
	}

	public long kaleoTaskAssignmentId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String kaleoClassName;
	public long kaleoClassPK;
	public long kaleoDefinitionId;
	public long kaleoNodeId;
	public String assigneeClassName;
	public long assigneeClassPK;
	public String assigneeActionId;
	public String assigneeScript;
	public String assigneeScriptLanguage;
}