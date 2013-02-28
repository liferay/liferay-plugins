/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SocialActivitySet}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialActivitySet
 * @generated
 */
public class SocialActivitySetWrapper implements SocialActivitySet,
	ModelWrapper<SocialActivitySet> {
	public SocialActivitySetWrapper(SocialActivitySet socialActivitySet) {
		_socialActivitySet = socialActivitySet;
	}

	public Class<?> getModelClass() {
		return SocialActivitySet.class;
	}

	public String getModelClassName() {
		return SocialActivitySet.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activitySetId", getActivitySetId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("activityCount", getActivityCount());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long activitySetId = (Long)attributes.get("activitySetId");

		if (activitySetId != null) {
			setActivitySetId(activitySetId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer activityCount = (Integer)attributes.get("activityCount");

		if (activityCount != null) {
			setActivityCount(activityCount);
		}
	}

	/**
	* Returns the primary key of this social activity set.
	*
	* @return the primary key of this social activity set
	*/
	public long getPrimaryKey() {
		return _socialActivitySet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this social activity set.
	*
	* @param primaryKey the primary key of this social activity set
	*/
	public void setPrimaryKey(long primaryKey) {
		_socialActivitySet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the activity set ID of this social activity set.
	*
	* @return the activity set ID of this social activity set
	*/
	public long getActivitySetId() {
		return _socialActivitySet.getActivitySetId();
	}

	/**
	* Sets the activity set ID of this social activity set.
	*
	* @param activitySetId the activity set ID of this social activity set
	*/
	public void setActivitySetId(long activitySetId) {
		_socialActivitySet.setActivitySetId(activitySetId);
	}

	/**
	* Returns the group ID of this social activity set.
	*
	* @return the group ID of this social activity set
	*/
	public long getGroupId() {
		return _socialActivitySet.getGroupId();
	}

	/**
	* Sets the group ID of this social activity set.
	*
	* @param groupId the group ID of this social activity set
	*/
	public void setGroupId(long groupId) {
		_socialActivitySet.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this social activity set.
	*
	* @return the company ID of this social activity set
	*/
	public long getCompanyId() {
		return _socialActivitySet.getCompanyId();
	}

	/**
	* Sets the company ID of this social activity set.
	*
	* @param companyId the company ID of this social activity set
	*/
	public void setCompanyId(long companyId) {
		_socialActivitySet.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this social activity set.
	*
	* @return the user ID of this social activity set
	*/
	public long getUserId() {
		return _socialActivitySet.getUserId();
	}

	/**
	* Sets the user ID of this social activity set.
	*
	* @param userId the user ID of this social activity set
	*/
	public void setUserId(long userId) {
		_socialActivitySet.setUserId(userId);
	}

	/**
	* Returns the user uuid of this social activity set.
	*
	* @return the user uuid of this social activity set
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySet.getUserUuid();
	}

	/**
	* Sets the user uuid of this social activity set.
	*
	* @param userUuid the user uuid of this social activity set
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_socialActivitySet.setUserUuid(userUuid);
	}

	/**
	* Returns the create date of this social activity set.
	*
	* @return the create date of this social activity set
	*/
	public long getCreateDate() {
		return _socialActivitySet.getCreateDate();
	}

	/**
	* Sets the create date of this social activity set.
	*
	* @param createDate the create date of this social activity set
	*/
	public void setCreateDate(long createDate) {
		_socialActivitySet.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this social activity set.
	*
	* @return the modified date of this social activity set
	*/
	public long getModifiedDate() {
		return _socialActivitySet.getModifiedDate();
	}

	/**
	* Sets the modified date of this social activity set.
	*
	* @param modifiedDate the modified date of this social activity set
	*/
	public void setModifiedDate(long modifiedDate) {
		_socialActivitySet.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this social activity set.
	*
	* @return the fully qualified class name of this social activity set
	*/
	public java.lang.String getClassName() {
		return _socialActivitySet.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_socialActivitySet.setClassName(className);
	}

	/**
	* Returns the class name ID of this social activity set.
	*
	* @return the class name ID of this social activity set
	*/
	public long getClassNameId() {
		return _socialActivitySet.getClassNameId();
	}

	/**
	* Sets the class name ID of this social activity set.
	*
	* @param classNameId the class name ID of this social activity set
	*/
	public void setClassNameId(long classNameId) {
		_socialActivitySet.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this social activity set.
	*
	* @return the class p k of this social activity set
	*/
	public long getClassPK() {
		return _socialActivitySet.getClassPK();
	}

	/**
	* Sets the class p k of this social activity set.
	*
	* @param classPK the class p k of this social activity set
	*/
	public void setClassPK(long classPK) {
		_socialActivitySet.setClassPK(classPK);
	}

	/**
	* Returns the type of this social activity set.
	*
	* @return the type of this social activity set
	*/
	public int getType() {
		return _socialActivitySet.getType();
	}

	/**
	* Sets the type of this social activity set.
	*
	* @param type the type of this social activity set
	*/
	public void setType(int type) {
		_socialActivitySet.setType(type);
	}

	/**
	* Returns the activity count of this social activity set.
	*
	* @return the activity count of this social activity set
	*/
	public int getActivityCount() {
		return _socialActivitySet.getActivityCount();
	}

	/**
	* Sets the activity count of this social activity set.
	*
	* @param activityCount the activity count of this social activity set
	*/
	public void setActivityCount(int activityCount) {
		_socialActivitySet.setActivityCount(activityCount);
	}

	public boolean isNew() {
		return _socialActivitySet.isNew();
	}

	public void setNew(boolean n) {
		_socialActivitySet.setNew(n);
	}

	public boolean isCachedModel() {
		return _socialActivitySet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_socialActivitySet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _socialActivitySet.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _socialActivitySet.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_socialActivitySet.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _socialActivitySet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_socialActivitySet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SocialActivitySetWrapper((SocialActivitySet)_socialActivitySet.clone());
	}

	public int compareTo(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet) {
		return _socialActivitySet.compareTo(socialActivitySet);
	}

	@Override
	public int hashCode() {
		return _socialActivitySet.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.so.activities.model.SocialActivitySet> toCacheModel() {
		return _socialActivitySet.toCacheModel();
	}

	public com.liferay.so.activities.model.SocialActivitySet toEscapedModel() {
		return new SocialActivitySetWrapper(_socialActivitySet.toEscapedModel());
	}

	public com.liferay.so.activities.model.SocialActivitySet toUnescapedModel() {
		return new SocialActivitySetWrapper(_socialActivitySet.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _socialActivitySet.toString();
	}

	public java.lang.String toXmlString() {
		return _socialActivitySet.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_socialActivitySet.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SocialActivitySet getWrappedSocialActivitySet() {
		return _socialActivitySet;
	}

	public SocialActivitySet getWrappedModel() {
		return _socialActivitySet;
	}

	public void resetOriginalValues() {
		_socialActivitySet.resetOriginalValues();
	}

	private SocialActivitySet _socialActivitySet;
}