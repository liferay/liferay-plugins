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

package com.liferay.hr.model;

/**
 * <p>
 * This class is a wrapper for {@link HRClient}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRClient
 * @generated
 */
public class HRClientWrapper implements HRClient {
	public HRClientWrapper(HRClient hrClient) {
		_hrClient = hrClient;
	}

	public Class<?> getModelClass() {
		return HRClient.class;
	}

	public String getModelClassName() {
		return HRClient.class.getName();
	}

	/**
	* Gets the primary key of this h r client.
	*
	* @return the primary key of this h r client
	*/
	public long getPrimaryKey() {
		return _hrClient.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r client
	*
	* @param primaryKey the primary key of this h r client
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrClient.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the hr client ID of this h r client.
	*
	* @return the hr client ID of this h r client
	*/
	public long getHrClientId() {
		return _hrClient.getHrClientId();
	}

	/**
	* Sets the hr client ID of this h r client.
	*
	* @param hrClientId the hr client ID of this h r client
	*/
	public void setHrClientId(long hrClientId) {
		_hrClient.setHrClientId(hrClientId);
	}

	/**
	* Gets the group ID of this h r client.
	*
	* @return the group ID of this h r client
	*/
	public long getGroupId() {
		return _hrClient.getGroupId();
	}

	/**
	* Sets the group ID of this h r client.
	*
	* @param groupId the group ID of this h r client
	*/
	public void setGroupId(long groupId) {
		_hrClient.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r client.
	*
	* @return the company ID of this h r client
	*/
	public long getCompanyId() {
		return _hrClient.getCompanyId();
	}

	/**
	* Sets the company ID of this h r client.
	*
	* @param companyId the company ID of this h r client
	*/
	public void setCompanyId(long companyId) {
		_hrClient.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r client.
	*
	* @return the user ID of this h r client
	*/
	public long getUserId() {
		return _hrClient.getUserId();
	}

	/**
	* Sets the user ID of this h r client.
	*
	* @param userId the user ID of this h r client
	*/
	public void setUserId(long userId) {
		_hrClient.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r client.
	*
	* @return the user uuid of this h r client
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrClient.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r client.
	*
	* @param userUuid the user uuid of this h r client
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrClient.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r client.
	*
	* @return the user name of this h r client
	*/
	public java.lang.String getUserName() {
		return _hrClient.getUserName();
	}

	/**
	* Sets the user name of this h r client.
	*
	* @param userName the user name of this h r client
	*/
	public void setUserName(java.lang.String userName) {
		_hrClient.setUserName(userName);
	}

	/**
	* Gets the create date of this h r client.
	*
	* @return the create date of this h r client
	*/
	public java.util.Date getCreateDate() {
		return _hrClient.getCreateDate();
	}

	/**
	* Sets the create date of this h r client.
	*
	* @param createDate the create date of this h r client
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrClient.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r client.
	*
	* @return the modified date of this h r client
	*/
	public java.util.Date getModifiedDate() {
		return _hrClient.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r client.
	*
	* @param modifiedDate the modified date of this h r client
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrClient.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this h r client.
	*
	* @return the name of this h r client
	*/
	public java.lang.String getName() {
		return _hrClient.getName();
	}

	/**
	* Sets the name of this h r client.
	*
	* @param name the name of this h r client
	*/
	public void setName(java.lang.String name) {
		_hrClient.setName(name);
	}

	/**
	* Gets the description of this h r client.
	*
	* @return the description of this h r client
	*/
	public java.lang.String getDescription() {
		return _hrClient.getDescription();
	}

	/**
	* Sets the description of this h r client.
	*
	* @param description the description of this h r client
	*/
	public void setDescription(java.lang.String description) {
		_hrClient.setDescription(description);
	}

	public boolean isNew() {
		return _hrClient.isNew();
	}

	public void setNew(boolean n) {
		_hrClient.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrClient.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrClient.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrClient.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrClient.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrClient.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrClient.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrClient.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrClient.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRClientWrapper((HRClient)_hrClient.clone());
	}

	public int compareTo(com.liferay.hr.model.HRClient hrClient) {
		return _hrClient.compareTo(hrClient);
	}

	public int hashCode() {
		return _hrClient.hashCode();
	}

	public com.liferay.hr.model.HRClient toEscapedModel() {
		return new HRClientWrapper(_hrClient.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrClient.toString();
	}

	public java.lang.String toXmlString() {
		return _hrClient.toXmlString();
	}

	public HRClient getWrappedHRClient() {
		return _hrClient;
	}

	public void resetOriginalValues() {
		_hrClient.resetOriginalValues();
	}

	private HRClient _hrClient;
}