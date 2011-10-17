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
 * This class is a wrapper for {@link KaleoTaskForm}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskForm
 * @generated
 */
public class KaleoTaskFormWrapper implements KaleoTaskForm,
	ModelWrapper<KaleoTaskForm> {
	public KaleoTaskFormWrapper(KaleoTaskForm kaleoTaskForm) {
		_kaleoTaskForm = kaleoTaskForm;
	}

	public Class<?> getModelClass() {
		return KaleoTaskForm.class;
	}

	public String getModelClassName() {
		return KaleoTaskForm.class.getName();
	}

	/**
	* Returns the primary key of this kaleo task form.
	*
	* @return the primary key of this kaleo task form
	*/
	public long getPrimaryKey() {
		return _kaleoTaskForm.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task form.
	*
	* @param primaryKey the primary key of this kaleo task form
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskForm.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task form ID of this kaleo task form.
	*
	* @return the kaleo task form ID of this kaleo task form
	*/
	public long getKaleoTaskFormId() {
		return _kaleoTaskForm.getKaleoTaskFormId();
	}

	/**
	* Sets the kaleo task form ID of this kaleo task form.
	*
	* @param kaleoTaskFormId the kaleo task form ID of this kaleo task form
	*/
	public void setKaleoTaskFormId(long kaleoTaskFormId) {
		_kaleoTaskForm.setKaleoTaskFormId(kaleoTaskFormId);
	}

	/**
	* Returns the group ID of this kaleo task form.
	*
	* @return the group ID of this kaleo task form
	*/
	public long getGroupId() {
		return _kaleoTaskForm.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task form.
	*
	* @param groupId the group ID of this kaleo task form
	*/
	public void setGroupId(long groupId) {
		_kaleoTaskForm.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task form.
	*
	* @return the company ID of this kaleo task form
	*/
	public long getCompanyId() {
		return _kaleoTaskForm.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task form.
	*
	* @param companyId the company ID of this kaleo task form
	*/
	public void setCompanyId(long companyId) {
		_kaleoTaskForm.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task form.
	*
	* @return the user ID of this kaleo task form
	*/
	public long getUserId() {
		return _kaleoTaskForm.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task form.
	*
	* @param userId the user ID of this kaleo task form
	*/
	public void setUserId(long userId) {
		_kaleoTaskForm.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task form.
	*
	* @return the user uuid of this kaleo task form
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskForm.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task form.
	*
	* @param userUuid the user uuid of this kaleo task form
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskForm.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task form.
	*
	* @return the user name of this kaleo task form
	*/
	public java.lang.String getUserName() {
		return _kaleoTaskForm.getUserName();
	}

	/**
	* Sets the user name of this kaleo task form.
	*
	* @param userName the user name of this kaleo task form
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTaskForm.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task form.
	*
	* @return the create date of this kaleo task form
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTaskForm.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task form.
	*
	* @param createDate the create date of this kaleo task form
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskForm.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task form.
	*
	* @return the modified date of this kaleo task form
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTaskForm.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task form.
	*
	* @param modifiedDate the modified date of this kaleo task form
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskForm.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task form.
	*
	* @return the kaleo definition ID of this kaleo task form
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTaskForm.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task form.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task form
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskForm.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo task ID of this kaleo task form.
	*
	* @return the kaleo task ID of this kaleo task form
	*/
	public long getKaleoTaskId() {
		return _kaleoTaskForm.getKaleoTaskId();
	}

	/**
	* Sets the kaleo task ID of this kaleo task form.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task form
	*/
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskForm.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Returns the description of this kaleo task form.
	*
	* @return the description of this kaleo task form
	*/
	public java.lang.String getDescription() {
		return _kaleoTaskForm.getDescription();
	}

	/**
	* Sets the description of this kaleo task form.
	*
	* @param description the description of this kaleo task form
	*/
	public void setDescription(java.lang.String description) {
		_kaleoTaskForm.setDescription(description);
	}

	/**
	* Returns the form template ID of this kaleo task form.
	*
	* @return the form template ID of this kaleo task form
	*/
	public long getFormTemplateId() {
		return _kaleoTaskForm.getFormTemplateId();
	}

	/**
	* Sets the form template ID of this kaleo task form.
	*
	* @param formTemplateId the form template ID of this kaleo task form
	*/
	public void setFormTemplateId(long formTemplateId) {
		_kaleoTaskForm.setFormTemplateId(formTemplateId);
	}

	public boolean isNew() {
		return _kaleoTaskForm.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTaskForm.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskForm.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskForm.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskForm.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskForm.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskForm.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskForm.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskForm.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskFormWrapper((KaleoTaskForm)_kaleoTaskForm.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskForm kaleoTaskForm) {
		return _kaleoTaskForm.compareTo(kaleoTaskForm);
	}

	@Override
	public int hashCode() {
		return _kaleoTaskForm.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskForm> toCacheModel() {
		return _kaleoTaskForm.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskForm toEscapedModel() {
		return new KaleoTaskFormWrapper(_kaleoTaskForm.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskForm.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskForm.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskForm.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoTaskForm getWrappedKaleoTaskForm() {
		return _kaleoTaskForm;
	}

	public KaleoTaskForm getWrappedModel() {
		return _kaleoTaskForm;
	}

	public void resetOriginalValues() {
		_kaleoTaskForm.resetOriginalValues();
	}

	private KaleoTaskForm _kaleoTaskForm;
}