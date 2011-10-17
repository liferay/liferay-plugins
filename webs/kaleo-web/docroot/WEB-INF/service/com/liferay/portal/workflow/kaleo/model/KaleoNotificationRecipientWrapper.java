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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoNotificationRecipient}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationRecipient
 * @generated
 */
public class KaleoNotificationRecipientWrapper
	implements KaleoNotificationRecipient,
		ModelWrapper<KaleoNotificationRecipient> {
	public KaleoNotificationRecipientWrapper(
		KaleoNotificationRecipient kaleoNotificationRecipient) {
		_kaleoNotificationRecipient = kaleoNotificationRecipient;
	}

	public Class<?> getModelClass() {
		return KaleoNotificationRecipient.class;
	}

	public String getModelClassName() {
		return KaleoNotificationRecipient.class.getName();
	}

	/**
	* Returns the primary key of this kaleo notification recipient.
	*
	* @return the primary key of this kaleo notification recipient
	*/
	public long getPrimaryKey() {
		return _kaleoNotificationRecipient.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo notification recipient.
	*
	* @param primaryKey the primary key of this kaleo notification recipient
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoNotificationRecipient.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo notification recipient ID of this kaleo notification recipient.
	*
	* @return the kaleo notification recipient ID of this kaleo notification recipient
	*/
	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipient.getKaleoNotificationRecipientId();
	}

	/**
	* Sets the kaleo notification recipient ID of this kaleo notification recipient.
	*
	* @param kaleoNotificationRecipientId the kaleo notification recipient ID of this kaleo notification recipient
	*/
	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipient.setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
	}

	/**
	* Returns the group ID of this kaleo notification recipient.
	*
	* @return the group ID of this kaleo notification recipient
	*/
	public long getGroupId() {
		return _kaleoNotificationRecipient.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo notification recipient.
	*
	* @param groupId the group ID of this kaleo notification recipient
	*/
	public void setGroupId(long groupId) {
		_kaleoNotificationRecipient.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo notification recipient.
	*
	* @return the company ID of this kaleo notification recipient
	*/
	public long getCompanyId() {
		return _kaleoNotificationRecipient.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo notification recipient.
	*
	* @param companyId the company ID of this kaleo notification recipient
	*/
	public void setCompanyId(long companyId) {
		_kaleoNotificationRecipient.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo notification recipient.
	*
	* @return the user ID of this kaleo notification recipient
	*/
	public long getUserId() {
		return _kaleoNotificationRecipient.getUserId();
	}

	/**
	* Sets the user ID of this kaleo notification recipient.
	*
	* @param userId the user ID of this kaleo notification recipient
	*/
	public void setUserId(long userId) {
		_kaleoNotificationRecipient.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo notification recipient.
	*
	* @return the user uuid of this kaleo notification recipient
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationRecipient.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo notification recipient.
	*
	* @param userUuid the user uuid of this kaleo notification recipient
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNotificationRecipient.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo notification recipient.
	*
	* @return the user name of this kaleo notification recipient
	*/
	public java.lang.String getUserName() {
		return _kaleoNotificationRecipient.getUserName();
	}

	/**
	* Sets the user name of this kaleo notification recipient.
	*
	* @param userName the user name of this kaleo notification recipient
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoNotificationRecipient.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo notification recipient.
	*
	* @return the create date of this kaleo notification recipient
	*/
	public java.util.Date getCreateDate() {
		return _kaleoNotificationRecipient.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo notification recipient.
	*
	* @param createDate the create date of this kaleo notification recipient
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoNotificationRecipient.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo notification recipient.
	*
	* @return the modified date of this kaleo notification recipient
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoNotificationRecipient.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo notification recipient.
	*
	* @param modifiedDate the modified date of this kaleo notification recipient
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNotificationRecipient.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo notification recipient.
	*
	* @return the kaleo definition ID of this kaleo notification recipient
	*/
	public long getKaleoDefinitionId() {
		return _kaleoNotificationRecipient.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo notification recipient.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo notification recipient
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNotificationRecipient.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo notification ID of this kaleo notification recipient.
	*
	* @return the kaleo notification ID of this kaleo notification recipient
	*/
	public long getKaleoNotificationId() {
		return _kaleoNotificationRecipient.getKaleoNotificationId();
	}

	/**
	* Sets the kaleo notification ID of this kaleo notification recipient.
	*
	* @param kaleoNotificationId the kaleo notification ID of this kaleo notification recipient
	*/
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationRecipient.setKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Returns the recipient class name of this kaleo notification recipient.
	*
	* @return the recipient class name of this kaleo notification recipient
	*/
	public java.lang.String getRecipientClassName() {
		return _kaleoNotificationRecipient.getRecipientClassName();
	}

	/**
	* Sets the recipient class name of this kaleo notification recipient.
	*
	* @param recipientClassName the recipient class name of this kaleo notification recipient
	*/
	public void setRecipientClassName(java.lang.String recipientClassName) {
		_kaleoNotificationRecipient.setRecipientClassName(recipientClassName);
	}

	/**
	* Returns the recipient class p k of this kaleo notification recipient.
	*
	* @return the recipient class p k of this kaleo notification recipient
	*/
	public long getRecipientClassPK() {
		return _kaleoNotificationRecipient.getRecipientClassPK();
	}

	/**
	* Sets the recipient class p k of this kaleo notification recipient.
	*
	* @param recipientClassPK the recipient class p k of this kaleo notification recipient
	*/
	public void setRecipientClassPK(long recipientClassPK) {
		_kaleoNotificationRecipient.setRecipientClassPK(recipientClassPK);
	}

	/**
	* Returns the recipient role type of this kaleo notification recipient.
	*
	* @return the recipient role type of this kaleo notification recipient
	*/
	public int getRecipientRoleType() {
		return _kaleoNotificationRecipient.getRecipientRoleType();
	}

	/**
	* Sets the recipient role type of this kaleo notification recipient.
	*
	* @param recipientRoleType the recipient role type of this kaleo notification recipient
	*/
	public void setRecipientRoleType(int recipientRoleType) {
		_kaleoNotificationRecipient.setRecipientRoleType(recipientRoleType);
	}

	/**
	* Returns the address of this kaleo notification recipient.
	*
	* @return the address of this kaleo notification recipient
	*/
	public java.lang.String getAddress() {
		return _kaleoNotificationRecipient.getAddress();
	}

	/**
	* Sets the address of this kaleo notification recipient.
	*
	* @param address the address of this kaleo notification recipient
	*/
	public void setAddress(java.lang.String address) {
		_kaleoNotificationRecipient.setAddress(address);
	}

	public boolean isNew() {
		return _kaleoNotificationRecipient.isNew();
	}

	public void setNew(boolean n) {
		_kaleoNotificationRecipient.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoNotificationRecipient.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoNotificationRecipient.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoNotificationRecipient.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNotificationRecipient.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoNotificationRecipient.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNotificationRecipient.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNotificationRecipient.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoNotificationRecipientWrapper((KaleoNotificationRecipient)_kaleoNotificationRecipient.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient) {
		return _kaleoNotificationRecipient.compareTo(kaleoNotificationRecipient);
	}

	@Override
	public int hashCode() {
		return _kaleoNotificationRecipient.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> toCacheModel() {
		return _kaleoNotificationRecipient.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient toEscapedModel() {
		return new KaleoNotificationRecipientWrapper(_kaleoNotificationRecipient.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoNotificationRecipient.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoNotificationRecipient.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationRecipient.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoNotificationRecipient getWrappedKaleoNotificationRecipient() {
		return _kaleoNotificationRecipient;
	}

	public KaleoNotificationRecipient getWrappedModel() {
		return _kaleoNotificationRecipient;
	}

	public void resetOriginalValues() {
		_kaleoNotificationRecipient.resetOriginalValues();
	}

	private KaleoNotificationRecipient _kaleoNotificationRecipient;
}