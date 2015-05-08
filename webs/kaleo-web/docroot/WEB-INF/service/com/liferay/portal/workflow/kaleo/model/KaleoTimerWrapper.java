/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTimer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTimer
 * @generated
 */
@ProviderType
public class KaleoTimerWrapper implements KaleoTimer, ModelWrapper<KaleoTimer> {
	public KaleoTimerWrapper(KaleoTimer kaleoTimer) {
		_kaleoTimer = kaleoTimer;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTimer.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTimer.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public java.lang.Object clone() {
		return new KaleoTimerWrapper((KaleoTimer)_kaleoTimer.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTimer kaleoTimer) {
		return _kaleoTimer.compareTo(kaleoTimer);
	}

	/**
	* Returns the blocking of this kaleo timer.
	*
	* @return the blocking of this kaleo timer
	*/
	@Override
	public boolean getBlocking() {
		return _kaleoTimer.getBlocking();
	}

	/**
	* Returns the company ID of this kaleo timer.
	*
	* @return the company ID of this kaleo timer
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTimer.getCompanyId();
	}

	/**
	* Returns the create date of this kaleo timer.
	*
	* @return the create date of this kaleo timer
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoTimer.getCreateDate();
	}

	/**
	* Returns the description of this kaleo timer.
	*
	* @return the description of this kaleo timer
	*/
	@Override
	public java.lang.String getDescription() {
		return _kaleoTimer.getDescription();
	}

	/**
	* Returns the duration of this kaleo timer.
	*
	* @return the duration of this kaleo timer
	*/
	@Override
	public double getDuration() {
		return _kaleoTimer.getDuration();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTimer.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo timer.
	*
	* @return the group ID of this kaleo timer
	*/
	@Override
	public long getGroupId() {
		return _kaleoTimer.getGroupId();
	}

	/**
	* Returns the kaleo class name of this kaleo timer.
	*
	* @return the kaleo class name of this kaleo timer
	*/
	@Override
	public java.lang.String getKaleoClassName() {
		return _kaleoTimer.getKaleoClassName();
	}

	/**
	* Returns the kaleo class p k of this kaleo timer.
	*
	* @return the kaleo class p k of this kaleo timer
	*/
	@Override
	public long getKaleoClassPK() {
		return _kaleoTimer.getKaleoClassPK();
	}

	/**
	* Returns the kaleo definition ID of this kaleo timer.
	*
	* @return the kaleo definition ID of this kaleo timer
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTimer.getKaleoDefinitionId();
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> getKaleoTaskReassignments() {
		return _kaleoTimer.getKaleoTaskReassignments();
	}

	/**
	* Returns the kaleo timer ID of this kaleo timer.
	*
	* @return the kaleo timer ID of this kaleo timer
	*/
	@Override
	public long getKaleoTimerId() {
		return _kaleoTimer.getKaleoTimerId();
	}

	/**
	* Returns the modified date of this kaleo timer.
	*
	* @return the modified date of this kaleo timer
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoTimer.getModifiedDate();
	}

	/**
	* Returns the name of this kaleo timer.
	*
	* @return the name of this kaleo timer
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoTimer.getName();
	}

	/**
	* Returns the primary key of this kaleo timer.
	*
	* @return the primary key of this kaleo timer
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTimer.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTimer.getPrimaryKeyObj();
	}

	/**
	* Returns the recurrence duration of this kaleo timer.
	*
	* @return the recurrence duration of this kaleo timer
	*/
	@Override
	public double getRecurrenceDuration() {
		return _kaleoTimer.getRecurrenceDuration();
	}

	/**
	* Returns the recurrence scale of this kaleo timer.
	*
	* @return the recurrence scale of this kaleo timer
	*/
	@Override
	public java.lang.String getRecurrenceScale() {
		return _kaleoTimer.getRecurrenceScale();
	}

	/**
	* Returns the scale of this kaleo timer.
	*
	* @return the scale of this kaleo timer
	*/
	@Override
	public java.lang.String getScale() {
		return _kaleoTimer.getScale();
	}

	/**
	* Returns the user ID of this kaleo timer.
	*
	* @return the user ID of this kaleo timer
	*/
	@Override
	public long getUserId() {
		return _kaleoTimer.getUserId();
	}

	/**
	* Returns the user name of this kaleo timer.
	*
	* @return the user name of this kaleo timer
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTimer.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo timer.
	*
	* @return the user uuid of this kaleo timer
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoTimer.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _kaleoTimer.hashCode();
	}

	/**
	* Returns <code>true</code> if this kaleo timer is blocking.
	*
	* @return <code>true</code> if this kaleo timer is blocking; <code>false</code> otherwise
	*/
	@Override
	public boolean isBlocking() {
		return _kaleoTimer.isBlocking();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTimer.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTimer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoTimer.isNew();
	}

	@Override
	public boolean isRecurring() {
		return _kaleoTimer.isRecurring();
	}

	@Override
	public void persist() {
		_kaleoTimer.persist();
	}

	/**
	* Sets whether this kaleo timer is blocking.
	*
	* @param blocking the blocking of this kaleo timer
	*/
	@Override
	public void setBlocking(boolean blocking) {
		_kaleoTimer.setBlocking(blocking);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTimer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kaleo timer.
	*
	* @param companyId the company ID of this kaleo timer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTimer.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this kaleo timer.
	*
	* @param createDate the create date of this kaleo timer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoTimer.setCreateDate(createDate);
	}

	/**
	* Sets the description of this kaleo timer.
	*
	* @param description the description of this kaleo timer
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kaleoTimer.setDescription(description);
	}

	/**
	* Sets the duration of this kaleo timer.
	*
	* @param duration the duration of this kaleo timer
	*/
	@Override
	public void setDuration(double duration) {
		_kaleoTimer.setDuration(duration);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTimer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTimer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTimer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo timer.
	*
	* @param groupId the group ID of this kaleo timer
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTimer.setGroupId(groupId);
	}

	/**
	* Sets the kaleo class name of this kaleo timer.
	*
	* @param kaleoClassName the kaleo class name of this kaleo timer
	*/
	@Override
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTimer.setKaleoClassName(kaleoClassName);
	}

	/**
	* Sets the kaleo class p k of this kaleo timer.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo timer
	*/
	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTimer.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Sets the kaleo definition ID of this kaleo timer.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo timer
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTimer.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo timer ID of this kaleo timer.
	*
	* @param kaleoTimerId the kaleo timer ID of this kaleo timer
	*/
	@Override
	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimer.setKaleoTimerId(kaleoTimerId);
	}

	/**
	* Sets the modified date of this kaleo timer.
	*
	* @param modifiedDate the modified date of this kaleo timer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoTimer.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this kaleo timer.
	*
	* @param name the name of this kaleo timer
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoTimer.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTimer.setNew(n);
	}

	/**
	* Sets the primary key of this kaleo timer.
	*
	* @param primaryKey the primary key of this kaleo timer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTimer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTimer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recurrence duration of this kaleo timer.
	*
	* @param recurrenceDuration the recurrence duration of this kaleo timer
	*/
	@Override
	public void setRecurrenceDuration(double recurrenceDuration) {
		_kaleoTimer.setRecurrenceDuration(recurrenceDuration);
	}

	/**
	* Sets the recurrence scale of this kaleo timer.
	*
	* @param recurrenceScale the recurrence scale of this kaleo timer
	*/
	@Override
	public void setRecurrenceScale(java.lang.String recurrenceScale) {
		_kaleoTimer.setRecurrenceScale(recurrenceScale);
	}

	/**
	* Sets the scale of this kaleo timer.
	*
	* @param scale the scale of this kaleo timer
	*/
	@Override
	public void setScale(java.lang.String scale) {
		_kaleoTimer.setScale(scale);
	}

	/**
	* Sets the user ID of this kaleo timer.
	*
	* @param userId the user ID of this kaleo timer
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTimer.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo timer.
	*
	* @param userName the user name of this kaleo timer
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTimer.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo timer.
	*
	* @param userUuid the user uuid of this kaleo timer
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTimer.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTimer> toCacheModel() {
		return _kaleoTimer.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer toEscapedModel() {
		return new KaleoTimerWrapper(_kaleoTimer.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTimer.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTimer toUnescapedModel() {
		return new KaleoTimerWrapper(_kaleoTimer.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTimer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTimerWrapper)) {
			return false;
		}

		KaleoTimerWrapper kaleoTimerWrapper = (KaleoTimerWrapper)obj;

		if (Validator.equals(_kaleoTimer, kaleoTimerWrapper._kaleoTimer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoTimer getWrappedKaleoTimer() {
		return _kaleoTimer;
	}

	@Override
	public KaleoTimer getWrappedModel() {
		return _kaleoTimer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoTimer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoTimer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTimer.resetOriginalValues();
	}

	private final KaleoTimer _kaleoTimer;
}