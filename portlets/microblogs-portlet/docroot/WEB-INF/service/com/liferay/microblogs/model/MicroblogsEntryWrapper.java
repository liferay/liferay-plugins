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

package com.liferay.microblogs.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MicroblogsEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MicroblogsEntry
 * @generated
 */
public class MicroblogsEntryWrapper implements MicroblogsEntry,
	ModelWrapper<MicroblogsEntry> {
	public MicroblogsEntryWrapper(MicroblogsEntry microblogsEntry) {
		_microblogsEntry = microblogsEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return MicroblogsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MicroblogsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("microblogsEntryId", getMicroblogsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("content", getContent());
		attributes.put("type", getType());
		attributes.put("receiverUserId", getReceiverUserId());
		attributes.put("receiverMicroblogsEntryId",
			getReceiverMicroblogsEntryId());
		attributes.put("socialRelationType", getSocialRelationType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long microblogsEntryId = (Long)attributes.get("microblogsEntryId");

		if (microblogsEntryId != null) {
			setMicroblogsEntryId(microblogsEntryId);
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

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long receiverUserId = (Long)attributes.get("receiverUserId");

		if (receiverUserId != null) {
			setReceiverUserId(receiverUserId);
		}

		Long receiverMicroblogsEntryId = (Long)attributes.get(
				"receiverMicroblogsEntryId");

		if (receiverMicroblogsEntryId != null) {
			setReceiverMicroblogsEntryId(receiverMicroblogsEntryId);
		}

		Integer socialRelationType = (Integer)attributes.get(
				"socialRelationType");

		if (socialRelationType != null) {
			setSocialRelationType(socialRelationType);
		}
	}

	/**
	* Returns the primary key of this microblogs entry.
	*
	* @return the primary key of this microblogs entry
	*/
	@Override
	public long getPrimaryKey() {
		return _microblogsEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this microblogs entry.
	*
	* @param primaryKey the primary key of this microblogs entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_microblogsEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the microblogs entry ID of this microblogs entry.
	*
	* @return the microblogs entry ID of this microblogs entry
	*/
	@Override
	public long getMicroblogsEntryId() {
		return _microblogsEntry.getMicroblogsEntryId();
	}

	/**
	* Sets the microblogs entry ID of this microblogs entry.
	*
	* @param microblogsEntryId the microblogs entry ID of this microblogs entry
	*/
	@Override
	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntry.setMicroblogsEntryId(microblogsEntryId);
	}

	/**
	* Returns the company ID of this microblogs entry.
	*
	* @return the company ID of this microblogs entry
	*/
	@Override
	public long getCompanyId() {
		return _microblogsEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this microblogs entry.
	*
	* @param companyId the company ID of this microblogs entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_microblogsEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this microblogs entry.
	*
	* @return the user ID of this microblogs entry
	*/
	@Override
	public long getUserId() {
		return _microblogsEntry.getUserId();
	}

	/**
	* Sets the user ID of this microblogs entry.
	*
	* @param userId the user ID of this microblogs entry
	*/
	@Override
	public void setUserId(long userId) {
		_microblogsEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this microblogs entry.
	*
	* @return the user uuid of this microblogs entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this microblogs entry.
	*
	* @param userUuid the user uuid of this microblogs entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_microblogsEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this microblogs entry.
	*
	* @return the user name of this microblogs entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _microblogsEntry.getUserName();
	}

	/**
	* Sets the user name of this microblogs entry.
	*
	* @param userName the user name of this microblogs entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_microblogsEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this microblogs entry.
	*
	* @return the create date of this microblogs entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _microblogsEntry.getCreateDate();
	}

	/**
	* Sets the create date of this microblogs entry.
	*
	* @param createDate the create date of this microblogs entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_microblogsEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this microblogs entry.
	*
	* @return the modified date of this microblogs entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _microblogsEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this microblogs entry.
	*
	* @param modifiedDate the modified date of this microblogs entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_microblogsEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the content of this microblogs entry.
	*
	* @return the content of this microblogs entry
	*/
	@Override
	public java.lang.String getContent() {
		return _microblogsEntry.getContent();
	}

	/**
	* Sets the content of this microblogs entry.
	*
	* @param content the content of this microblogs entry
	*/
	@Override
	public void setContent(java.lang.String content) {
		_microblogsEntry.setContent(content);
	}

	/**
	* Returns the type of this microblogs entry.
	*
	* @return the type of this microblogs entry
	*/
	@Override
	public int getType() {
		return _microblogsEntry.getType();
	}

	/**
	* Sets the type of this microblogs entry.
	*
	* @param type the type of this microblogs entry
	*/
	@Override
	public void setType(int type) {
		_microblogsEntry.setType(type);
	}

	/**
	* Returns the receiver user ID of this microblogs entry.
	*
	* @return the receiver user ID of this microblogs entry
	*/
	@Override
	public long getReceiverUserId() {
		return _microblogsEntry.getReceiverUserId();
	}

	/**
	* Sets the receiver user ID of this microblogs entry.
	*
	* @param receiverUserId the receiver user ID of this microblogs entry
	*/
	@Override
	public void setReceiverUserId(long receiverUserId) {
		_microblogsEntry.setReceiverUserId(receiverUserId);
	}

	/**
	* Returns the receiver user uuid of this microblogs entry.
	*
	* @return the receiver user uuid of this microblogs entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getReceiverUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntry.getReceiverUserUuid();
	}

	/**
	* Sets the receiver user uuid of this microblogs entry.
	*
	* @param receiverUserUuid the receiver user uuid of this microblogs entry
	*/
	@Override
	public void setReceiverUserUuid(java.lang.String receiverUserUuid) {
		_microblogsEntry.setReceiverUserUuid(receiverUserUuid);
	}

	/**
	* Returns the receiver microblogs entry ID of this microblogs entry.
	*
	* @return the receiver microblogs entry ID of this microblogs entry
	*/
	@Override
	public long getReceiverMicroblogsEntryId() {
		return _microblogsEntry.getReceiverMicroblogsEntryId();
	}

	/**
	* Sets the receiver microblogs entry ID of this microblogs entry.
	*
	* @param receiverMicroblogsEntryId the receiver microblogs entry ID of this microblogs entry
	*/
	@Override
	public void setReceiverMicroblogsEntryId(long receiverMicroblogsEntryId) {
		_microblogsEntry.setReceiverMicroblogsEntryId(receiverMicroblogsEntryId);
	}

	/**
	* Returns the social relation type of this microblogs entry.
	*
	* @return the social relation type of this microblogs entry
	*/
	@Override
	public int getSocialRelationType() {
		return _microblogsEntry.getSocialRelationType();
	}

	/**
	* Sets the social relation type of this microblogs entry.
	*
	* @param socialRelationType the social relation type of this microblogs entry
	*/
	@Override
	public void setSocialRelationType(int socialRelationType) {
		_microblogsEntry.setSocialRelationType(socialRelationType);
	}

	@Override
	public boolean isNew() {
		return _microblogsEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_microblogsEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _microblogsEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_microblogsEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _microblogsEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _microblogsEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_microblogsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _microblogsEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_microblogsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_microblogsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_microblogsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MicroblogsEntryWrapper((MicroblogsEntry)_microblogsEntry.clone());
	}

	@Override
	public int compareTo(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return _microblogsEntry.compareTo(microblogsEntry);
	}

	@Override
	public int hashCode() {
		return _microblogsEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.microblogs.model.MicroblogsEntry> toCacheModel() {
		return _microblogsEntry.toCacheModel();
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry toEscapedModel() {
		return new MicroblogsEntryWrapper(_microblogsEntry.toEscapedModel());
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry toUnescapedModel() {
		return new MicroblogsEntryWrapper(_microblogsEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _microblogsEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _microblogsEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntry.persist();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public MicroblogsEntry getWrappedMicroblogsEntry() {
		return _microblogsEntry;
	}

	@Override
	public MicroblogsEntry getWrappedModel() {
		return _microblogsEntry;
	}

	@Override
	public void resetOriginalValues() {
		_microblogsEntry.resetOriginalValues();
	}

	private MicroblogsEntry _microblogsEntry;
}