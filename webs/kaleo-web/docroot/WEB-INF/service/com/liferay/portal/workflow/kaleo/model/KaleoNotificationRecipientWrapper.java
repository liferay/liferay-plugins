/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoNotificationRecipient}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipient
 * @generated
 */
public class KaleoNotificationRecipientWrapper
	implements KaleoNotificationRecipient,
		ModelWrapper<KaleoNotificationRecipient> {
	public KaleoNotificationRecipientWrapper(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		_kaleoNotificationRecipient = kaleoNotificationRecipient;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoNotificationRecipient.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoNotificationRecipient.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoNotificationRecipientId",
			getKaleoNotificationRecipientId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNotificationId", getKaleoNotificationId());
		attributes.put("recipientClassName", getRecipientClassName());
		attributes.put("recipientClassPK", getRecipientClassPK());
		attributes.put("recipientRoleType", getRecipientRoleType());
		attributes.put("address", getAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoNotificationRecipientId = (Long)attributes.get(
				"kaleoNotificationRecipientId");

		if (kaleoNotificationRecipientId != null) {
			setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
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

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNotificationId = (Long)attributes.get("kaleoNotificationId");

		if (kaleoNotificationId != null) {
			setKaleoNotificationId(kaleoNotificationId);
		}

		String recipientClassName = (String)attributes.get("recipientClassName");

		if (recipientClassName != null) {
			setRecipientClassName(recipientClassName);
		}

		Long recipientClassPK = (Long)attributes.get("recipientClassPK");

		if (recipientClassPK != null) {
			setRecipientClassPK(recipientClassPK);
		}

		Integer recipientRoleType = (Integer)attributes.get("recipientRoleType");

		if (recipientRoleType != null) {
			setRecipientRoleType(recipientRoleType);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}
	}

	/**
	* Returns the primary key of this kaleo notification recipient.
	*
	* @return the primary key of this kaleo notification recipient
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoNotificationRecipient.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo notification recipient.
	*
	* @param primaryKey the primary key of this kaleo notification recipient
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoNotificationRecipient.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo notification recipient ID of this kaleo notification recipient.
	*
	* @return the kaleo notification recipient ID of this kaleo notification recipient
	*/
	@Override
	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipient.getKaleoNotificationRecipientId();
	}

	/**
	* Sets the kaleo notification recipient ID of this kaleo notification recipient.
	*
	* @param kaleoNotificationRecipientId the kaleo notification recipient ID of this kaleo notification recipient
	*/
	@Override
	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipient.setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
	}

	/**
	* Returns the group ID of this kaleo notification recipient.
	*
	* @return the group ID of this kaleo notification recipient
	*/
	@Override
	public long getGroupId() {
		return _kaleoNotificationRecipient.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo notification recipient.
	*
	* @param groupId the group ID of this kaleo notification recipient
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoNotificationRecipient.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo notification recipient.
	*
	* @return the company ID of this kaleo notification recipient
	*/
	@Override
	public long getCompanyId() {
		return _kaleoNotificationRecipient.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo notification recipient.
	*
	* @param companyId the company ID of this kaleo notification recipient
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoNotificationRecipient.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo notification recipient.
	*
	* @return the user ID of this kaleo notification recipient
	*/
	@Override
	public long getUserId() {
		return _kaleoNotificationRecipient.getUserId();
	}

	/**
	* Sets the user ID of this kaleo notification recipient.
	*
	* @param userId the user ID of this kaleo notification recipient
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoNotificationRecipient.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo notification recipient.
	*
	* @return the user uuid of this kaleo notification recipient
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipient.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo notification recipient.
	*
	* @param userUuid the user uuid of this kaleo notification recipient
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNotificationRecipient.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo notification recipient.
	*
	* @return the user name of this kaleo notification recipient
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoNotificationRecipient.getUserName();
	}

	/**
	* Sets the user name of this kaleo notification recipient.
	*
	* @param userName the user name of this kaleo notification recipient
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoNotificationRecipient.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo notification recipient.
	*
	* @return the create date of this kaleo notification recipient
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoNotificationRecipient.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo notification recipient.
	*
	* @param createDate the create date of this kaleo notification recipient
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoNotificationRecipient.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo notification recipient.
	*
	* @return the modified date of this kaleo notification recipient
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoNotificationRecipient.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo notification recipient.
	*
	* @param modifiedDate the modified date of this kaleo notification recipient
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNotificationRecipient.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo notification recipient.
	*
	* @return the kaleo definition ID of this kaleo notification recipient
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoNotificationRecipient.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo notification recipient.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo notification recipient
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNotificationRecipient.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo notification ID of this kaleo notification recipient.
	*
	* @return the kaleo notification ID of this kaleo notification recipient
	*/
	@Override
	public long getKaleoNotificationId() {
		return _kaleoNotificationRecipient.getKaleoNotificationId();
	}

	/**
	* Sets the kaleo notification ID of this kaleo notification recipient.
	*
	* @param kaleoNotificationId the kaleo notification ID of this kaleo notification recipient
	*/
	@Override
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationRecipient.setKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Returns the recipient class name of this kaleo notification recipient.
	*
	* @return the recipient class name of this kaleo notification recipient
	*/
	@Override
	public java.lang.String getRecipientClassName() {
		return _kaleoNotificationRecipient.getRecipientClassName();
	}

	/**
	* Sets the recipient class name of this kaleo notification recipient.
	*
	* @param recipientClassName the recipient class name of this kaleo notification recipient
	*/
	@Override
	public void setRecipientClassName(java.lang.String recipientClassName) {
		_kaleoNotificationRecipient.setRecipientClassName(recipientClassName);
	}

	/**
	* Returns the recipient class p k of this kaleo notification recipient.
	*
	* @return the recipient class p k of this kaleo notification recipient
	*/
	@Override
	public long getRecipientClassPK() {
		return _kaleoNotificationRecipient.getRecipientClassPK();
	}

	/**
	* Sets the recipient class p k of this kaleo notification recipient.
	*
	* @param recipientClassPK the recipient class p k of this kaleo notification recipient
	*/
	@Override
	public void setRecipientClassPK(long recipientClassPK) {
		_kaleoNotificationRecipient.setRecipientClassPK(recipientClassPK);
	}

	/**
	* Returns the recipient role type of this kaleo notification recipient.
	*
	* @return the recipient role type of this kaleo notification recipient
	*/
	@Override
	public int getRecipientRoleType() {
		return _kaleoNotificationRecipient.getRecipientRoleType();
	}

	/**
	* Sets the recipient role type of this kaleo notification recipient.
	*
	* @param recipientRoleType the recipient role type of this kaleo notification recipient
	*/
	@Override
	public void setRecipientRoleType(int recipientRoleType) {
		_kaleoNotificationRecipient.setRecipientRoleType(recipientRoleType);
	}

	/**
	* Returns the address of this kaleo notification recipient.
	*
	* @return the address of this kaleo notification recipient
	*/
	@Override
	public java.lang.String getAddress() {
		return _kaleoNotificationRecipient.getAddress();
	}

	/**
	* Sets the address of this kaleo notification recipient.
	*
	* @param address the address of this kaleo notification recipient
	*/
	@Override
	public void setAddress(java.lang.String address) {
		_kaleoNotificationRecipient.setAddress(address);
	}

	@Override
	public boolean isNew() {
		return _kaleoNotificationRecipient.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoNotificationRecipient.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoNotificationRecipient.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoNotificationRecipient.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoNotificationRecipient.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNotificationRecipient.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoNotificationRecipient.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNotificationRecipient.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoNotificationRecipient.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoNotificationRecipient.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNotificationRecipient.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoNotificationRecipientWrapper((KaleoNotificationRecipient)_kaleoNotificationRecipient.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient) {
		return _kaleoNotificationRecipient.compareTo(kaleoNotificationRecipient);
	}

	@Override
	public int hashCode() {
		return _kaleoNotificationRecipient.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> toCacheModel() {
		return _kaleoNotificationRecipient.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient toEscapedModel() {
		return new KaleoNotificationRecipientWrapper(_kaleoNotificationRecipient.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient toUnescapedModel() {
		return new KaleoNotificationRecipientWrapper(_kaleoNotificationRecipient.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoNotificationRecipient.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoNotificationRecipient.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipient.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNotificationRecipientWrapper)) {
			return false;
		}

		KaleoNotificationRecipientWrapper kaleoNotificationRecipientWrapper = (KaleoNotificationRecipientWrapper)obj;

		if (Validator.equals(_kaleoNotificationRecipient,
					kaleoNotificationRecipientWrapper._kaleoNotificationRecipient)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoNotificationRecipient getWrappedKaleoNotificationRecipient() {
		return _kaleoNotificationRecipient;
	}

	@Override
	public KaleoNotificationRecipient getWrappedModel() {
		return _kaleoNotificationRecipient;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoNotificationRecipient.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoNotificationRecipient.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoNotificationRecipient.resetOriginalValues();
	}

	private KaleoNotificationRecipient _kaleoNotificationRecipient;
}