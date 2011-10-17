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
 * This class is a wrapper for {@link KaleoCondition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoCondition
 * @generated
 */
public class KaleoConditionWrapper implements KaleoCondition,
	ModelWrapper<KaleoCondition> {
	public KaleoConditionWrapper(KaleoCondition kaleoCondition) {
		_kaleoCondition = kaleoCondition;
	}

	public Class<?> getModelClass() {
		return KaleoCondition.class;
	}

	public String getModelClassName() {
		return KaleoCondition.class.getName();
	}

	/**
	* Returns the primary key of this kaleo condition.
	*
	* @return the primary key of this kaleo condition
	*/
	public long getPrimaryKey() {
		return _kaleoCondition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo condition.
	*
	* @param primaryKey the primary key of this kaleo condition
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoCondition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo condition ID of this kaleo condition.
	*
	* @return the kaleo condition ID of this kaleo condition
	*/
	public long getKaleoConditionId() {
		return _kaleoCondition.getKaleoConditionId();
	}

	/**
	* Sets the kaleo condition ID of this kaleo condition.
	*
	* @param kaleoConditionId the kaleo condition ID of this kaleo condition
	*/
	public void setKaleoConditionId(long kaleoConditionId) {
		_kaleoCondition.setKaleoConditionId(kaleoConditionId);
	}

	/**
	* Returns the group ID of this kaleo condition.
	*
	* @return the group ID of this kaleo condition
	*/
	public long getGroupId() {
		return _kaleoCondition.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo condition.
	*
	* @param groupId the group ID of this kaleo condition
	*/
	public void setGroupId(long groupId) {
		_kaleoCondition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo condition.
	*
	* @return the company ID of this kaleo condition
	*/
	public long getCompanyId() {
		return _kaleoCondition.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo condition.
	*
	* @param companyId the company ID of this kaleo condition
	*/
	public void setCompanyId(long companyId) {
		_kaleoCondition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo condition.
	*
	* @return the user ID of this kaleo condition
	*/
	public long getUserId() {
		return _kaleoCondition.getUserId();
	}

	/**
	* Sets the user ID of this kaleo condition.
	*
	* @param userId the user ID of this kaleo condition
	*/
	public void setUserId(long userId) {
		_kaleoCondition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo condition.
	*
	* @return the user uuid of this kaleo condition
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoCondition.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo condition.
	*
	* @param userUuid the user uuid of this kaleo condition
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoCondition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo condition.
	*
	* @return the user name of this kaleo condition
	*/
	public java.lang.String getUserName() {
		return _kaleoCondition.getUserName();
	}

	/**
	* Sets the user name of this kaleo condition.
	*
	* @param userName the user name of this kaleo condition
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoCondition.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo condition.
	*
	* @return the create date of this kaleo condition
	*/
	public java.util.Date getCreateDate() {
		return _kaleoCondition.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo condition.
	*
	* @param createDate the create date of this kaleo condition
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoCondition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo condition.
	*
	* @return the modified date of this kaleo condition
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoCondition.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo condition.
	*
	* @param modifiedDate the modified date of this kaleo condition
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoCondition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo definition ID of this kaleo condition.
	*
	* @return the kaleo definition ID of this kaleo condition
	*/
	public long getKaleoDefinitionId() {
		return _kaleoCondition.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo condition.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo condition
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoCondition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node ID of this kaleo condition.
	*
	* @return the kaleo node ID of this kaleo condition
	*/
	public long getKaleoNodeId() {
		return _kaleoCondition.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node ID of this kaleo condition.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo condition
	*/
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoCondition.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the script of this kaleo condition.
	*
	* @return the script of this kaleo condition
	*/
	public java.lang.String getScript() {
		return _kaleoCondition.getScript();
	}

	/**
	* Sets the script of this kaleo condition.
	*
	* @param script the script of this kaleo condition
	*/
	public void setScript(java.lang.String script) {
		_kaleoCondition.setScript(script);
	}

	/**
	* Returns the script language of this kaleo condition.
	*
	* @return the script language of this kaleo condition
	*/
	public java.lang.String getScriptLanguage() {
		return _kaleoCondition.getScriptLanguage();
	}

	/**
	* Sets the script language of this kaleo condition.
	*
	* @param scriptLanguage the script language of this kaleo condition
	*/
	public void setScriptLanguage(java.lang.String scriptLanguage) {
		_kaleoCondition.setScriptLanguage(scriptLanguage);
	}

	public boolean isNew() {
		return _kaleoCondition.isNew();
	}

	public void setNew(boolean n) {
		_kaleoCondition.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoCondition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoCondition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoCondition.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoCondition.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoCondition.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoCondition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoCondition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoConditionWrapper((KaleoCondition)_kaleoCondition.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoCondition kaleoCondition) {
		return _kaleoCondition.compareTo(kaleoCondition);
	}

	@Override
	public int hashCode() {
		return _kaleoCondition.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoCondition> toCacheModel() {
		return _kaleoCondition.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoCondition toEscapedModel() {
		return new KaleoConditionWrapper(_kaleoCondition.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoCondition.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoCondition.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoCondition.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoCondition getWrappedKaleoCondition() {
		return _kaleoCondition;
	}

	public KaleoCondition getWrappedModel() {
		return _kaleoCondition;
	}

	public void resetOriginalValues() {
		_kaleoCondition.resetOriginalValues();
	}

	private KaleoCondition _kaleoCondition;
}