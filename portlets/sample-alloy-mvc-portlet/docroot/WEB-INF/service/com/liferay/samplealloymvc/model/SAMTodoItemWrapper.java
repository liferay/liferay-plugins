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
 * This class is a wrapper for {@link SAMTodoItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAMTodoItem
 * @generated
 */
@ProviderType
public class SAMTodoItemWrapper implements SAMTodoItem,
	ModelWrapper<SAMTodoItem> {
	public SAMTodoItemWrapper(SAMTodoItem samTodoItem) {
		_samTodoItem = samTodoItem;
	}

	@Override
	public Class<?> getModelClass() {
		return SAMTodoItem.class;
	}

	@Override
	public String getModelClassName() {
		return SAMTodoItem.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samTodoItemId", getSamTodoItemId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samTodoListId", getSamTodoListId());
		attributes.put("description", getDescription());
		attributes.put("priority", getPriority());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samTodoItemId = (Long)attributes.get("samTodoItemId");

		if (samTodoItemId != null) {
			setSamTodoItemId(samTodoItemId);
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

		Long samTodoListId = (Long)attributes.get("samTodoListId");

		if (samTodoListId != null) {
			setSamTodoListId(samTodoListId);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public SAMTodoItem toEscapedModel() {
		return new SAMTodoItemWrapper(_samTodoItem.toEscapedModel());
	}

	@Override
	public SAMTodoItem toUnescapedModel() {
		return new SAMTodoItemWrapper(_samTodoItem.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _samTodoItem.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _samTodoItem.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _samTodoItem.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _samTodoItem.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SAMTodoItem> toCacheModel() {
		return _samTodoItem.toCacheModel();
	}

	@Override
	public int compareTo(SAMTodoItem samTodoItem) {
		return _samTodoItem.compareTo(samTodoItem);
	}

	/**
	* Returns the priority of this s a m todo item.
	*
	* @return the priority of this s a m todo item
	*/
	@Override
	public int getPriority() {
		return _samTodoItem.getPriority();
	}

	/**
	* Returns the status of this s a m todo item.
	*
	* @return the status of this s a m todo item
	*/
	@Override
	public int getStatus() {
		return _samTodoItem.getStatus();
	}

	@Override
	public int hashCode() {
		return _samTodoItem.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samTodoItem.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SAMTodoItemWrapper((SAMTodoItem)_samTodoItem.clone());
	}

	/**
	* Returns the description of this s a m todo item.
	*
	* @return the description of this s a m todo item
	*/
	@Override
	public java.lang.String getDescription() {
		return _samTodoItem.getDescription();
	}

	/**
	* Returns the user name of this s a m todo item.
	*
	* @return the user name of this s a m todo item
	*/
	@Override
	public java.lang.String getUserName() {
		return _samTodoItem.getUserName();
	}

	/**
	* Returns the user uuid of this s a m todo item.
	*
	* @return the user uuid of this s a m todo item
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _samTodoItem.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _samTodoItem.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _samTodoItem.toXmlString();
	}

	/**
	* Returns the create date of this s a m todo item.
	*
	* @return the create date of this s a m todo item
	*/
	@Override
	public Date getCreateDate() {
		return _samTodoItem.getCreateDate();
	}

	/**
	* Returns the modified date of this s a m todo item.
	*
	* @return the modified date of this s a m todo item
	*/
	@Override
	public Date getModifiedDate() {
		return _samTodoItem.getModifiedDate();
	}

	/**
	* Returns the company ID of this s a m todo item.
	*
	* @return the company ID of this s a m todo item
	*/
	@Override
	public long getCompanyId() {
		return _samTodoItem.getCompanyId();
	}

	/**
	* Returns the primary key of this s a m todo item.
	*
	* @return the primary key of this s a m todo item
	*/
	@Override
	public long getPrimaryKey() {
		return _samTodoItem.getPrimaryKey();
	}

	/**
	* Returns the sam todo item ID of this s a m todo item.
	*
	* @return the sam todo item ID of this s a m todo item
	*/
	@Override
	public long getSamTodoItemId() {
		return _samTodoItem.getSamTodoItemId();
	}

	/**
	* Returns the sam todo list ID of this s a m todo item.
	*
	* @return the sam todo list ID of this s a m todo item
	*/
	@Override
	public long getSamTodoListId() {
		return _samTodoItem.getSamTodoListId();
	}

	/**
	* Returns the user ID of this s a m todo item.
	*
	* @return the user ID of this s a m todo item
	*/
	@Override
	public long getUserId() {
		return _samTodoItem.getUserId();
	}

	@Override
	public void persist() {
		_samTodoItem.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_samTodoItem.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this s a m todo item.
	*
	* @param companyId the company ID of this s a m todo item
	*/
	@Override
	public void setCompanyId(long companyId) {
		_samTodoItem.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this s a m todo item.
	*
	* @param createDate the create date of this s a m todo item
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_samTodoItem.setCreateDate(createDate);
	}

	/**
	* Sets the description of this s a m todo item.
	*
	* @param description the description of this s a m todo item
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_samTodoItem.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_samTodoItem.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_samTodoItem.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_samTodoItem.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this s a m todo item.
	*
	* @param modifiedDate the modified date of this s a m todo item
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_samTodoItem.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_samTodoItem.setNew(n);
	}

	/**
	* Sets the primary key of this s a m todo item.
	*
	* @param primaryKey the primary key of this s a m todo item
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_samTodoItem.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_samTodoItem.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the priority of this s a m todo item.
	*
	* @param priority the priority of this s a m todo item
	*/
	@Override
	public void setPriority(int priority) {
		_samTodoItem.setPriority(priority);
	}

	/**
	* Sets the sam todo item ID of this s a m todo item.
	*
	* @param samTodoItemId the sam todo item ID of this s a m todo item
	*/
	@Override
	public void setSamTodoItemId(long samTodoItemId) {
		_samTodoItem.setSamTodoItemId(samTodoItemId);
	}

	/**
	* Sets the sam todo list ID of this s a m todo item.
	*
	* @param samTodoListId the sam todo list ID of this s a m todo item
	*/
	@Override
	public void setSamTodoListId(long samTodoListId) {
		_samTodoItem.setSamTodoListId(samTodoListId);
	}

	/**
	* Sets the status of this s a m todo item.
	*
	* @param status the status of this s a m todo item
	*/
	@Override
	public void setStatus(int status) {
		_samTodoItem.setStatus(status);
	}

	/**
	* Sets the user ID of this s a m todo item.
	*
	* @param userId the user ID of this s a m todo item
	*/
	@Override
	public void setUserId(long userId) {
		_samTodoItem.setUserId(userId);
	}

	/**
	* Sets the user name of this s a m todo item.
	*
	* @param userName the user name of this s a m todo item
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_samTodoItem.setUserName(userName);
	}

	/**
	* Sets the user uuid of this s a m todo item.
	*
	* @param userUuid the user uuid of this s a m todo item
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_samTodoItem.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SAMTodoItemWrapper)) {
			return false;
		}

		SAMTodoItemWrapper samTodoItemWrapper = (SAMTodoItemWrapper)obj;

		if (Objects.equals(_samTodoItem, samTodoItemWrapper._samTodoItem)) {
			return true;
		}

		return false;
	}

	@Override
	public SAMTodoItem getWrappedModel() {
		return _samTodoItem;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _samTodoItem.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _samTodoItem.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_samTodoItem.resetOriginalValues();
	}

	private final SAMTodoItem _samTodoItem;
}