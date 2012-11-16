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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTimer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTimer
 * @generated
 */
public class KaleoTimerWrapper implements KaleoTimer, ModelWrapper<KaleoTimer> {
	public KaleoTimerWrapper(KaleoTimer kaleoTimer) {
		_kaleoTimer = kaleoTimer;
	}

	public Class<?> getModelClass() {
		return KaleoTimer.class;
	}

	public String getModelClassName() {
		return KaleoTimer.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTimerId", getKaleoTimerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("name", getName());
		attributes.put("blocking", getBlocking());
		attributes.put("description", getDescription());
		attributes.put("duration", getDuration());
		attributes.put("scale", getScale());
		attributes.put("recurrenceDuration", getRecurrenceDuration());
		attributes.put("recurrenceScale", getRecurrenceScale());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTimerId = (Long)attributes.get("kaleoTimerId");

		if (kaleoTimerId != null) {
			setKaleoTimerId(kaleoTimerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean blocking = (Boolean)attributes.get("blocking");

		if (blocking != null) {
			setBlocking(blocking);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Double duration = (Double)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		String scale = (String)attributes.get("scale");

		if (scale != null) {
			setScale(scale);
		}

		Double recurrenceDuration = (Double)attributes.get("recurrenceDuration");

		if (recurrenceDuration != null) {
			setRecurrenceDuration(recurrenceDuration);
		}

		String recurrenceScale = (String)attributes.get("recurrenceScale");

		if (recurrenceScale != null) {
			setRecurrenceScale(recurrenceScale);
		}
	}

	/**
	* Returns the primary key of this kaleo timer.
	*
	* @return the primary key of this kaleo timer
	*/
	public long getPrimaryKey() {
		return _kaleoTimer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo timer.
	*
	* @param primaryKey the primary key of this kaleo timer
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTimer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo timer ID of this kaleo timer.
	*
	* @return the kaleo timer ID of this kaleo timer
	*/
	public long getKaleoTimerId() {
		return _kaleoTimer.getKaleoTimerId();
	}

	/**
	* Sets the kaleo timer ID of this kaleo timer.
	*
	* @param kaleoTimerId the kaleo timer ID of this kaleo timer
	*/
	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimer.setKaleoTimerId(kaleoTimerId);
	}

	/**
	* Returns the group ID of this kaleo timer.
	*
	* @return the group ID of this kaleo timer
	*/
	public long getGroupId() {
		return _kaleoTimer.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo timer.
	*
	* @param groupId the group ID of this kaleo timer
	*/
	public void setGroupId(long groupId) {
		_kaleoTimer.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo timer.
	*
	* @return the company ID of this kaleo timer
	*/
	public long getCompanyId() {
		return _kaleoTimer.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo timer.
	*
	* @param companyId the company ID of this kaleo timer
	*/
	public void setCompanyId(long companyId) {
		_kaleoTimer.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo timer.
	*
	* @return the user ID of this kaleo timer
	*/
	public long getUserId() {
		return _kaleoTimer.getUserId();
	}

	/**
	* Sets the user ID of this kaleo timer.
	*
	* @param userId the user ID of this kaleo timer
	*/
	public void setUserId(long userId) {
		_kaleoTimer.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo timer.
	*
	* @return the user uuid of this kaleo timer
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimer.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo timer.
	*
	* @param userUuid the user uuid of this kaleo timer
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTimer.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo timer.
	*
	* @return the user name of this kaleo timer
	*/
	public java.lang.String getUserName() {
		return _kaleoTimer.getUserName();
	}

	/**
	* Sets the user name of this kaleo timer.
	*
	* @param userName the user name of this kaleo timer
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTimer.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo timer.
	*
	* @return the create date of this kaleo timer
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTimer.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo timer.
	*
	* @param createDate the create date of this kaleo timer
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTimer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo timer.
	*
	* @return the modified date of this kaleo timer
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTimer.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo timer.
	*
	* @param modifiedDate the modified date of this kaleo timer
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTimer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo timer.
	*
	* @return the kaleo class name of this kaleo timer
	*/
	public java.lang.String getKaleoClassName() {
		return _kaleoTimer.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo timer.
	*
	* @param kaleoClassName the kaleo class name of this kaleo timer
	*/
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTimer.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo timer.
	*
	* @return the kaleo class p k of this kaleo timer
	*/
	public long getKaleoClassPK() {
		return _kaleoTimer.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo timer.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo timer
	*/
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTimer.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo timer.
	*
	* @return the kaleo definition ID of this kaleo timer
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTimer.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo timer.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo timer
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTimer.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the name of this kaleo timer.
	*
	* @return the name of this kaleo timer
	*/
	public java.lang.String getName() {
		return _kaleoTimer.getName();
	}

	/**
	* Sets the name of this kaleo timer.
	*
	* @param name the name of this kaleo timer
	*/
	public void setName(java.lang.String name) {
		_kaleoTimer.setName(name);
	}

	/**
	* Returns the blocking of this kaleo timer.
	*
	* @return the blocking of this kaleo timer
	*/
	public boolean getBlocking() {
		return _kaleoTimer.getBlocking();
	}

	/**
	* Returns <code>true</code> if this kaleo timer is blocking.
	*
	* @return <code>true</code> if this kaleo timer is blocking; <code>false</code> otherwise
	*/
	public boolean isBlocking() {
		return _kaleoTimer.isBlocking();
	}

	/**
	* Sets whether this kaleo timer is blocking.
	*
	* @param blocking the blocking of this kaleo timer
	*/
	public void setBlocking(boolean blocking) {
		_kaleoTimer.setBlocking(blocking);
	}

	/**
	* Returns the description of this kaleo timer.
	*
	* @return the description of this kaleo timer
	*/
	public java.lang.String getDescription() {
		return _kaleoTimer.getDescription();
	}

	/**
	* Sets the description of this kaleo timer.
	*
	* @param description the description of this kaleo timer
	*/
	public void setDescription(java.lang.String description) {
		_kaleoTimer.setDescription(description);
	}

	/**
	* Returns the duration of this kaleo timer.
	*
	* @return the duration of this kaleo timer
	*/
	public double getDuration() {
		return _kaleoTimer.getDuration();
	}

	/**
	* Sets the duration of this kaleo timer.
	*
	* @param duration the duration of this kaleo timer
	*/
	public void setDuration(double duration) {
		_kaleoTimer.setDuration(duration);
	}

	/**
	* Returns the scale of this kaleo timer.
	*
	* @return the scale of this kaleo timer
	*/
	public java.lang.String getScale() {
		return _kaleoTimer.getScale();
	}

	/**
	* Sets the scale of this kaleo timer.
	*
	* @param scale the scale of this kaleo timer
	*/
	public void setScale(java.lang.String scale) {
		_kaleoTimer.setScale(scale);
	}

	/**
	* Returns the recurrence duration of this kaleo timer.
	*
	* @return the recurrence duration of this kaleo timer
	*/
	public double getRecurrenceDuration() {
		return _kaleoTimer.getRecurrenceDuration();
	}

	/**
	* Sets the recurrence duration of this kaleo timer.
	*
	* @param recurrenceDuration the recurrence duration of this kaleo timer
	*/
	public void setRecurrenceDuration(double recurrenceDuration) {
		_kaleoTimer.setRecurrenceDuration(recurrenceDuration);
	}

	/**
	* Returns the recurrence scale of this kaleo timer.
	*
	* @return the recurrence scale of this kaleo timer
	*/
	public java.lang.String getRecurrenceScale() {
		return _kaleoTimer.getRecurrenceScale();
	}

	/**
	* Sets the recurrence scale of this kaleo timer.
	*
	* @param recurrenceScale the recurrence scale of this kaleo timer
	*/
	public void setRecurrenceScale(java.lang.String recurrenceScale) {
		_kaleoTimer.setRecurrenceScale(recurrenceScale);
	}

	public boolean isNew() {
		return _kaleoTimer.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTimer.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTimer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTimer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTimer.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTimer.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTimer.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTimer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTimer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTimerWrapper((KaleoTimer)_kaleoTimer.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		return _kaleoTimer.compareTo(kaleoTimer);
	}

	@Override
	public int hashCode() {
		return _kaleoTimer.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTimer> toCacheModel() {
		return _kaleoTimer.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer toEscapedModel() {
		return new KaleoTimerWrapper(_kaleoTimer.toEscapedModel());
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer toUnescapedModel() {
		return new KaleoTimerWrapper(_kaleoTimer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTimer.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTimer.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTimer.persist();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskReassignments()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTimer.getKaleoTaskReassignments();
	}

	public boolean isRecurring() {
		return _kaleoTimer.isRecurring();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoTimer getWrappedKaleoTimer() {
		return _kaleoTimer;
	}

	public KaleoTimer getWrappedModel() {
		return _kaleoTimer;
	}

	public void resetOriginalValues() {
		_kaleoTimer.resetOriginalValues();
	}

	private KaleoTimer _kaleoTimer;
}