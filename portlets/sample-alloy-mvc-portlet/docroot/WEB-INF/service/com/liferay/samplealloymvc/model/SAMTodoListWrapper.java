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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SAMTodoList}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoList
 * @generated
 */
@ProviderType
public class SAMTodoListWrapper implements SAMTodoList,
	ModelWrapper<SAMTodoList> {
	public SAMTodoListWrapper(SAMTodoList samTodoList) {
		_samTodoList = samTodoList;
	}

	@Override
	public Class<?> getModelClass() {
		return SAMTodoList.class;
	}

	@Override
	public String getModelClassName() {
		return SAMTodoList.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samTodoListId", getSamTodoListId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samTodoListId = (Long)attributes.get("samTodoListId");

		if (samTodoListId != null) {
			setSamTodoListId(samTodoListId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	@Override
	public SAMTodoList toEscapedModel() {
		return new SAMTodoListWrapper(_samTodoList.toEscapedModel());
	}

	@Override
	public SAMTodoList toUnescapedModel() {
		return new SAMTodoListWrapper(_samTodoList.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _samTodoList.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _samTodoList.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _samTodoList.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _samTodoList.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SAMTodoList> toCacheModel() {
		return _samTodoList.toCacheModel();
	}

	@Override
	public int compareTo(SAMTodoList samTodoList) {
		return _samTodoList.compareTo(samTodoList);
	}

	@Override
	public int hashCode() {
		return _samTodoList.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samTodoList.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SAMTodoListWrapper((SAMTodoList)_samTodoList.clone());
	}

	/**
	* Returns the name of this s a m todo list.
	*
	* @return the name of this s a m todo list
	*/
	@Override
	public java.lang.String getName() {
		return _samTodoList.getName();
	}

	/**
	* Returns the user name of this s a m todo list.
	*
	* @return the user name of this s a m todo list
	*/
	@Override
	public java.lang.String getUserName() {
		return _samTodoList.getUserName();
	}

	/**
	* Returns the user uuid of this s a m todo list.
	*
	* @return the user uuid of this s a m todo list
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _samTodoList.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _samTodoList.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _samTodoList.toXmlString();
	}

	/**
	* Returns the create date of this s a m todo list.
	*
	* @return the create date of this s a m todo list
	*/
	@Override
	public Date getCreateDate() {
		return _samTodoList.getCreateDate();
	}

	/**
	* Returns the modified date of this s a m todo list.
	*
	* @return the modified date of this s a m todo list
	*/
	@Override
	public Date getModifiedDate() {
		return _samTodoList.getModifiedDate();
	}

	/**
	* Returns the company ID of this s a m todo list.
	*
	* @return the company ID of this s a m todo list
	*/
	@Override
	public long getCompanyId() {
		return _samTodoList.getCompanyId();
	}

	/**
	* Returns the primary key of this s a m todo list.
	*
	* @return the primary key of this s a m todo list
	*/
	@Override
	public long getPrimaryKey() {
		return _samTodoList.getPrimaryKey();
	}

	/**
	* Returns the sam todo list ID of this s a m todo list.
	*
	* @return the sam todo list ID of this s a m todo list
	*/
	@Override
	public long getSamTodoListId() {
		return _samTodoList.getSamTodoListId();
	}

	/**
	* Returns the user ID of this s a m todo list.
	*
	* @return the user ID of this s a m todo list
	*/
	@Override
	public long getUserId() {
		return _samTodoList.getUserId();
	}

	@Override
	public void persist() {
		_samTodoList.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_samTodoList.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this s a m todo list.
	*
	* @param companyId the company ID of this s a m todo list
	*/
	@Override
	public void setCompanyId(long companyId) {
		_samTodoList.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this s a m todo list.
	*
	* @param createDate the create date of this s a m todo list
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_samTodoList.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_samTodoList.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_samTodoList.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_samTodoList.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this s a m todo list.
	*
	* @param modifiedDate the modified date of this s a m todo list
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_samTodoList.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this s a m todo list.
	*
	* @param name the name of this s a m todo list
	*/
	@Override
	public void setName(java.lang.String name) {
		_samTodoList.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_samTodoList.setNew(n);
	}

	/**
	* Sets the primary key of this s a m todo list.
	*
	* @param primaryKey the primary key of this s a m todo list
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_samTodoList.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_samTodoList.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sam todo list ID of this s a m todo list.
	*
	* @param samTodoListId the sam todo list ID of this s a m todo list
	*/
	@Override
	public void setSamTodoListId(long samTodoListId) {
		_samTodoList.setSamTodoListId(samTodoListId);
	}

	/**
	* Sets the user ID of this s a m todo list.
	*
	* @param userId the user ID of this s a m todo list
	*/
	@Override
	public void setUserId(long userId) {
		_samTodoList.setUserId(userId);
	}

	/**
	* Sets the user name of this s a m todo list.
	*
	* @param userName the user name of this s a m todo list
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_samTodoList.setUserName(userName);
	}

	/**
	* Sets the user uuid of this s a m todo list.
	*
	* @param userUuid the user uuid of this s a m todo list
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_samTodoList.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SAMTodoListWrapper)) {
			return false;
		}

		SAMTodoListWrapper samTodoListWrapper = (SAMTodoListWrapper)obj;

		if (Objects.equals(_samTodoList, samTodoListWrapper._samTodoList)) {
			return true;
		}

		return false;
	}

	@Override
	public SAMTodoList getWrappedModel() {
		return _samTodoList;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _samTodoList.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _samTodoList.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_samTodoList.resetOriginalValues();
	}

	private final SAMTodoList _samTodoList;
}