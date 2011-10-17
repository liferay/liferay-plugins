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
 * This class is a wrapper for {@link KaleoNotification}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotification
 * @generated
 */
public class KaleoNotificationWrapper implements KaleoNotification,
	ModelWrapper<KaleoNotification> {
	public KaleoNotificationWrapper(KaleoNotification kaleoNotification) {
		_kaleoNotification = kaleoNotification;
	}

	public Class<?> getModelClass() {
		return KaleoNotification.class;
	}

	public String getModelClassName() {
		return KaleoNotification.class.getName();
	}

	/**
	* Returns the primary key of this kaleo notification.
	*
	* @return the primary key of this kaleo notification
	*/
	public long getPrimaryKey() {
		return _kaleoNotification.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo notification.
	*
	* @param primaryKey the primary key of this kaleo notification
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoNotification.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo notification ID of this kaleo notification.
	*
	* @return the kaleo notification ID of this kaleo notification
	*/
	public long getKaleoNotificationId() {
		return _kaleoNotification.getKaleoNotificationId();
	}

	/**
	* Sets the kaleo notification ID of this kaleo notification.
	*
	* @param kaleoNotificationId the kaleo notification ID of this kaleo notification
	*/
	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotification.setKaleoNotificationId(kaleoNotificationId);
	}

	/**
	* Returns the group ID of this kaleo notification.
	*
	* @return the group ID of this kaleo notification
	*/
	public long getGroupId() {
		return _kaleoNotification.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo notification.
	*
	* @param groupId the group ID of this kaleo notification
	*/
	public void setGroupId(long groupId) {
		_kaleoNotification.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo notification.
	*
	* @return the company ID of this kaleo notification
	*/
	public long getCompanyId() {
		return _kaleoNotification.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo notification.
	*
	* @param companyId the company ID of this kaleo notification
	*/
	public void setCompanyId(long companyId) {
		_kaleoNotification.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo notification.
	*
	* @return the user ID of this kaleo notification
	*/
	public long getUserId() {
		return _kaleoNotification.getUserId();
	}

	/**
	* Sets the user ID of this kaleo notification.
	*
	* @param userId the user ID of this kaleo notification
	*/
	public void setUserId(long userId) {
		_kaleoNotification.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo notification.
	*
	* @return the user uuid of this kaleo notification
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotification.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo notification.
	*
	* @param userUuid the user uuid of this kaleo notification
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNotification.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo notification.
	*
	* @return the user name of this kaleo notification
	*/
	public java.lang.String getUserName() {
		return _kaleoNotification.getUserName();
	}

	/**
	* Sets the user name of this kaleo notification.
	*
	* @param userName the user name of this kaleo notification
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoNotification.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo notification.
	*
	* @return the create date of this kaleo notification
	*/
	public java.util.Date getCreateDate() {
		return _kaleoNotification.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo notification.
	*
	* @param createDate the create date of this kaleo notification
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoNotification.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo notification.
	*
	* @return the modified date of this kaleo notification
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoNotification.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo notification.
	*
	* @param modifiedDate the modified date of this kaleo notification
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNotification.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo notification.
	*
	* @return the kaleo class name of this kaleo notification
	*/
	public java.lang.String getKaleoClassName() {
		return _kaleoNotification.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo notification.
	*
	* @param kaleoClassName the kaleo class name of this kaleo notification
	*/
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoNotification.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo notification.
	*
	* @return the kaleo class p k of this kaleo notification
	*/
	public long getKaleoClassPK() {
		return _kaleoNotification.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo notification.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo notification
	*/
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoNotification.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo notification.
	*
	* @return the kaleo definition ID of this kaleo notification
	*/
	public long getKaleoDefinitionId() {
		return _kaleoNotification.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo notification.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo notification
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNotification.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node name of this kaleo notification.
	*
	* @return the kaleo node name of this kaleo notification
	*/
	public java.lang.String getKaleoNodeName() {
		return _kaleoNotification.getKaleoNodeName();
	}

	/**
	* Sets the kaleo node name of this kaleo notification.
	*
	* @param kaleoNodeName the kaleo node name of this kaleo notification
	*/
	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoNotification.setKaleoNodeName(kaleoNodeName);
	}

	/**
	* Returns the name of this kaleo notification.
	*
	* @return the name of this kaleo notification
	*/
	public java.lang.String getName() {
		return _kaleoNotification.getName();
	}

	/**
	* Sets the name of this kaleo notification.
	*
	* @param name the name of this kaleo notification
	*/
	public void setName(java.lang.String name) {
		_kaleoNotification.setName(name);
	}

	/**
	* Returns the description of this kaleo notification.
	*
	* @return the description of this kaleo notification
	*/
	public java.lang.String getDescription() {
		return _kaleoNotification.getDescription();
	}

	/**
	* Sets the description of this kaleo notification.
	*
	* @param description the description of this kaleo notification
	*/
	public void setDescription(java.lang.String description) {
		_kaleoNotification.setDescription(description);
	}

	/**
	* Returns the execution type of this kaleo notification.
	*
	* @return the execution type of this kaleo notification
	*/
	public java.lang.String getExecutionType() {
		return _kaleoNotification.getExecutionType();
	}

	/**
	* Sets the execution type of this kaleo notification.
	*
	* @param executionType the execution type of this kaleo notification
	*/
	public void setExecutionType(java.lang.String executionType) {
		_kaleoNotification.setExecutionType(executionType);
	}

	/**
	* Returns the template of this kaleo notification.
	*
	* @return the template of this kaleo notification
	*/
	public java.lang.String getTemplate() {
		return _kaleoNotification.getTemplate();
	}

	/**
	* Sets the template of this kaleo notification.
	*
	* @param template the template of this kaleo notification
	*/
	public void setTemplate(java.lang.String template) {
		_kaleoNotification.setTemplate(template);
	}

	/**
	* Returns the template language of this kaleo notification.
	*
	* @return the template language of this kaleo notification
	*/
	public java.lang.String getTemplateLanguage() {
		return _kaleoNotification.getTemplateLanguage();
	}

	/**
	* Sets the template language of this kaleo notification.
	*
	* @param templateLanguage the template language of this kaleo notification
	*/
	public void setTemplateLanguage(java.lang.String templateLanguage) {
		_kaleoNotification.setTemplateLanguage(templateLanguage);
	}

	/**
	* Returns the notification types of this kaleo notification.
	*
	* @return the notification types of this kaleo notification
	*/
	public java.lang.String getNotificationTypes() {
		return _kaleoNotification.getNotificationTypes();
	}

	/**
	* Sets the notification types of this kaleo notification.
	*
	* @param notificationTypes the notification types of this kaleo notification
	*/
	public void setNotificationTypes(java.lang.String notificationTypes) {
		_kaleoNotification.setNotificationTypes(notificationTypes);
	}

	public boolean isNew() {
		return _kaleoNotification.isNew();
	}

	public void setNew(boolean n) {
		_kaleoNotification.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoNotification.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoNotification.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoNotification.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNotification.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoNotification.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNotification.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNotification.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoNotificationWrapper((KaleoNotification)_kaleoNotification.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification) {
		return _kaleoNotification.compareTo(kaleoNotification);
	}

	@Override
	public int hashCode() {
		return _kaleoNotification.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoNotification> toCacheModel() {
		return _kaleoNotification.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification toEscapedModel() {
		return new KaleoNotificationWrapper(_kaleoNotification.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoNotification.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoNotification.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotification.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoNotification getWrappedKaleoNotification() {
		return _kaleoNotification;
	}

	public KaleoNotification getWrappedModel() {
		return _kaleoNotification;
	}

	public void resetOriginalValues() {
		_kaleoNotification.resetOriginalValues();
	}

	private KaleoNotification _kaleoNotification;
}