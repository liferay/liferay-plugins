/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoTask}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTask
 * @generated
 */
public class KaleoTaskWrapper implements KaleoTask {
	public KaleoTaskWrapper(KaleoTask kaleoTask) {
		_kaleoTask = kaleoTask;
	}

	public long getPrimaryKey() {
		return _kaleoTask.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTask.setPrimaryKey(pk);
	}

	public long getKaleoTaskId() {
		return _kaleoTask.getKaleoTaskId();
	}

	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTask.setKaleoTaskId(kaleoTaskId);
	}

	public long getGroupId() {
		return _kaleoTask.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoTask.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoTask.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTask.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTask.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTask.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTask.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTask.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTask.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTask.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTask.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTask.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTask.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoTask.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoNodeId() {
		return _kaleoTask.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTask.setKaleoNodeId(kaleoNodeId);
	}

	public java.lang.String getName() {
		return _kaleoTask.getName();
	}

	public void setName(java.lang.String name) {
		_kaleoTask.setName(name);
	}

	public java.lang.String getDescription() {
		return _kaleoTask.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_kaleoTask.setDescription(description);
	}

	public double getDueDateDuration() {
		return _kaleoTask.getDueDateDuration();
	}

	public void setDueDateDuration(double dueDateDuration) {
		_kaleoTask.setDueDateDuration(dueDateDuration);
	}

	public java.lang.String getDueDateScale() {
		return _kaleoTask.getDueDateScale();
	}

	public void setDueDateScale(java.lang.String dueDateScale) {
		_kaleoTask.setDueDateScale(dueDateScale);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTask toEscapedModel() {
		return _kaleoTask.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTask.isNew();
	}

	public boolean setNew(boolean n) {
		return _kaleoTask.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTask.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTask.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTask.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTask.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTask.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTask.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTask.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTask kaleoTask) {
		return _kaleoTask.compareTo(kaleoTask);
	}

	public int hashCode() {
		return _kaleoTask.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTask.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTask.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoNode();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskAssignments()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTask.getKaleoTaskAssignments();
	}

	public KaleoTask getWrappedKaleoTask() {
		return _kaleoTask;
	}

	private KaleoTask _kaleoTask;
}