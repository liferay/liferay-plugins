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
 * This class is a wrapper for {@link HRProjectRole}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRProjectRole
 * @generated
 */
public class HRProjectRoleWrapper implements HRProjectRole {
	public HRProjectRoleWrapper(HRProjectRole hrProjectRole) {
		_hrProjectRole = hrProjectRole;
	}

	public Class<?> getModelClass() {
		return HRProjectRole.class;
	}

	public String getModelClassName() {
		return HRProjectRole.class.getName();
	}

	/**
	* Returns the primary key of this h r project role.
	*
	* @return the primary key of this h r project role
	*/
	public long getPrimaryKey() {
		return _hrProjectRole.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r project role.
	*
	* @param primaryKey the primary key of this h r project role
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrProjectRole.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr project role ID of this h r project role.
	*
	* @return the hr project role ID of this h r project role
	*/
	public long getHrProjectRoleId() {
		return _hrProjectRole.getHrProjectRoleId();
	}

	/**
	* Sets the hr project role ID of this h r project role.
	*
	* @param hrProjectRoleId the hr project role ID of this h r project role
	*/
	public void setHrProjectRoleId(long hrProjectRoleId) {
		_hrProjectRole.setHrProjectRoleId(hrProjectRoleId);
	}

	/**
	* Returns the group ID of this h r project role.
	*
	* @return the group ID of this h r project role
	*/
	public long getGroupId() {
		return _hrProjectRole.getGroupId();
	}

	/**
	* Sets the group ID of this h r project role.
	*
	* @param groupId the group ID of this h r project role
	*/
	public void setGroupId(long groupId) {
		_hrProjectRole.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r project role.
	*
	* @return the company ID of this h r project role
	*/
	public long getCompanyId() {
		return _hrProjectRole.getCompanyId();
	}

	/**
	* Sets the company ID of this h r project role.
	*
	* @param companyId the company ID of this h r project role
	*/
	public void setCompanyId(long companyId) {
		_hrProjectRole.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r project role.
	*
	* @return the user ID of this h r project role
	*/
	public long getUserId() {
		return _hrProjectRole.getUserId();
	}

	/**
	* Sets the user ID of this h r project role.
	*
	* @param userId the user ID of this h r project role
	*/
	public void setUserId(long userId) {
		_hrProjectRole.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r project role.
	*
	* @return the user uuid of this h r project role
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrProjectRole.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r project role.
	*
	* @param userUuid the user uuid of this h r project role
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrProjectRole.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r project role.
	*
	* @return the user name of this h r project role
	*/
	public java.lang.String getUserName() {
		return _hrProjectRole.getUserName();
	}

	/**
	* Sets the user name of this h r project role.
	*
	* @param userName the user name of this h r project role
	*/
	public void setUserName(java.lang.String userName) {
		_hrProjectRole.setUserName(userName);
	}

	/**
	* Returns the create date of this h r project role.
	*
	* @return the create date of this h r project role
	*/
	public java.util.Date getCreateDate() {
		return _hrProjectRole.getCreateDate();
	}

	/**
	* Sets the create date of this h r project role.
	*
	* @param createDate the create date of this h r project role
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrProjectRole.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r project role.
	*
	* @return the modified date of this h r project role
	*/
	public java.util.Date getModifiedDate() {
		return _hrProjectRole.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r project role.
	*
	* @param modifiedDate the modified date of this h r project role
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrProjectRole.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this h r project role.
	*
	* @return the name of this h r project role
	*/
	public java.lang.String getName() {
		return _hrProjectRole.getName();
	}

	/**
	* Sets the name of this h r project role.
	*
	* @param name the name of this h r project role
	*/
	public void setName(java.lang.String name) {
		_hrProjectRole.setName(name);
	}

	/**
	* Returns the description of this h r project role.
	*
	* @return the description of this h r project role
	*/
	public java.lang.String getDescription() {
		return _hrProjectRole.getDescription();
	}

	/**
	* Sets the description of this h r project role.
	*
	* @param description the description of this h r project role
	*/
	public void setDescription(java.lang.String description) {
		_hrProjectRole.setDescription(description);
	}

	public boolean isNew() {
		return _hrProjectRole.isNew();
	}

	public void setNew(boolean n) {
		_hrProjectRole.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrProjectRole.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrProjectRole.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrProjectRole.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrProjectRole.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrProjectRole.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrProjectRole.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrProjectRole.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrProjectRole.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRProjectRoleWrapper((HRProjectRole)_hrProjectRole.clone());
	}

	public int compareTo(com.liferay.hr.model.HRProjectRole hrProjectRole) {
		return _hrProjectRole.compareTo(hrProjectRole);
	}

	@Override
	public int hashCode() {
		return _hrProjectRole.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRProjectRole> toCacheModel() {
		return _hrProjectRole.toCacheModel();
	}

	public com.liferay.hr.model.HRProjectRole toEscapedModel() {
		return new HRProjectRoleWrapper(_hrProjectRole.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrProjectRole.toString();
	}

	public java.lang.String toXmlString() {
		return _hrProjectRole.toXmlString();
	}

	public HRProjectRole getWrappedHRProjectRole() {
		return _hrProjectRole;
	}

	public void resetOriginalValues() {
		_hrProjectRole.resetOriginalValues();
	}

	private HRProjectRole _hrProjectRole;
}