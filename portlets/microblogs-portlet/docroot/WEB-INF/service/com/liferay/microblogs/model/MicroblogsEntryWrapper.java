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

package com.liferay.microblogs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MicroblogsEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntry
 * @generated
 */
@ProviderType
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
		attributes.put("creatorClassNameId", getCreatorClassNameId());
		attributes.put("creatorClassPK", getCreatorClassPK());
		attributes.put("content", getContent());
		attributes.put("type", getType());
		attributes.put("parentMicroblogsEntryId", getParentMicroblogsEntryId());
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

		Long creatorClassNameId = (Long)attributes.get("creatorClassNameId");

		if (creatorClassNameId != null) {
			setCreatorClassNameId(creatorClassNameId);
		}

		Long creatorClassPK = (Long)attributes.get("creatorClassPK");

		if (creatorClassPK != null) {
			setCreatorClassPK(creatorClassPK);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long parentMicroblogsEntryId = (Long)attributes.get(
				"parentMicroblogsEntryId");

		if (parentMicroblogsEntryId != null) {
			setParentMicroblogsEntryId(parentMicroblogsEntryId);
		}

		Integer socialRelationType = (Integer)attributes.get(
				"socialRelationType");

		if (socialRelationType != null) {
			setSocialRelationType(socialRelationType);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _microblogsEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _microblogsEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _microblogsEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _microblogsEntry.getExpandoBridge();
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
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.microblogs.model.MicroblogsEntry> toCacheModel() {
		return _microblogsEntry.toCacheModel();
	}

	@Override
	public int compareTo(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return _microblogsEntry.compareTo(microblogsEntry);
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
	* Returns the type of this microblogs entry.
	*
	* @return the type of this microblogs entry
	*/
	@Override
	public int getType() {
		return _microblogsEntry.getType();
	}

	@Override
	public int hashCode() {
		return _microblogsEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _microblogsEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MicroblogsEntryWrapper((MicroblogsEntry)_microblogsEntry.clone());
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
	* Returns the user name of this microblogs entry.
	*
	* @return the user name of this microblogs entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _microblogsEntry.getUserName();
	}

	/**
	* Returns the user uuid of this microblogs entry.
	*
	* @return the user uuid of this microblogs entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _microblogsEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _microblogsEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _microblogsEntry.toXmlString();
	}

	/**
	* Returns the create date of this microblogs entry.
	*
	* @return the create date of this microblogs entry
	*/
	@Override
	public Date getCreateDate() {
		return _microblogsEntry.getCreateDate();
	}

	/**
	* Returns the modified date of this microblogs entry.
	*
	* @return the modified date of this microblogs entry
	*/
	@Override
	public Date getModifiedDate() {
		return _microblogsEntry.getModifiedDate();
	}

	@Override
	public long fetchParentMicroblogsEntryUserId() {
		return _microblogsEntry.fetchParentMicroblogsEntryUserId();
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
	* Returns the creator class name ID of this microblogs entry.
	*
	* @return the creator class name ID of this microblogs entry
	*/
	@Override
	public long getCreatorClassNameId() {
		return _microblogsEntry.getCreatorClassNameId();
	}

	/**
	* Returns the creator class p k of this microblogs entry.
	*
	* @return the creator class p k of this microblogs entry
	*/
	@Override
	public long getCreatorClassPK() {
		return _microblogsEntry.getCreatorClassPK();
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
	* Returns the parent microblogs entry ID of this microblogs entry.
	*
	* @return the parent microblogs entry ID of this microblogs entry
	*/
	@Override
	public long getParentMicroblogsEntryId() {
		return _microblogsEntry.getParentMicroblogsEntryId();
	}

	@Override
	public long getParentMicroblogsEntryUserId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntry.getParentMicroblogsEntryUserId();
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
	* Returns the user ID of this microblogs entry.
	*
	* @return the user ID of this microblogs entry
	*/
	@Override
	public long getUserId() {
		return _microblogsEntry.getUserId();
	}

	@Override
	public void persist() {
		_microblogsEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_microblogsEntry.setCachedModel(cachedModel);
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
	* Sets the content of this microblogs entry.
	*
	* @param content the content of this microblogs entry
	*/
	@Override
	public void setContent(java.lang.String content) {
		_microblogsEntry.setContent(content);
	}

	/**
	* Sets the create date of this microblogs entry.
	*
	* @param createDate the create date of this microblogs entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_microblogsEntry.setCreateDate(createDate);
	}

	/**
	* Sets the creator class name ID of this microblogs entry.
	*
	* @param creatorClassNameId the creator class name ID of this microblogs entry
	*/
	@Override
	public void setCreatorClassNameId(long creatorClassNameId) {
		_microblogsEntry.setCreatorClassNameId(creatorClassNameId);
	}

	/**
	* Sets the creator class p k of this microblogs entry.
	*
	* @param creatorClassPK the creator class p k of this microblogs entry
	*/
	@Override
	public void setCreatorClassPK(long creatorClassPK) {
		_microblogsEntry.setCreatorClassPK(creatorClassPK);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_microblogsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_microblogsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_microblogsEntry.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified date of this microblogs entry.
	*
	* @param modifiedDate the modified date of this microblogs entry
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_microblogsEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_microblogsEntry.setNew(n);
	}

	/**
	* Sets the parent microblogs entry ID of this microblogs entry.
	*
	* @param parentMicroblogsEntryId the parent microblogs entry ID of this microblogs entry
	*/
	@Override
	public void setParentMicroblogsEntryId(long parentMicroblogsEntryId) {
		_microblogsEntry.setParentMicroblogsEntryId(parentMicroblogsEntryId);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_microblogsEntry.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the user ID of this microblogs entry.
	*
	* @param userId the user ID of this microblogs entry
	*/
	@Override
	public void setUserId(long userId) {
		_microblogsEntry.setUserId(userId);
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
	* Sets the user uuid of this microblogs entry.
	*
	* @param userUuid the user uuid of this microblogs entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_microblogsEntry.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MicroblogsEntryWrapper)) {
			return false;
		}

		MicroblogsEntryWrapper microblogsEntryWrapper = (MicroblogsEntryWrapper)obj;

		if (Validator.equals(_microblogsEntry,
					microblogsEntryWrapper._microblogsEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public MicroblogsEntry getWrappedModel() {
		return _microblogsEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _microblogsEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _microblogsEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_microblogsEntry.resetOriginalValues();
	}

	private final MicroblogsEntry _microblogsEntry;
}