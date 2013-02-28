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
 * This class is a wrapper for {@link SocialActivity}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialActivity
 * @generated
 */
public class SocialActivityWrapper implements SocialActivity,
	ModelWrapper<SocialActivity> {
	public SocialActivityWrapper(SocialActivity socialActivity) {
		_socialActivity = socialActivity;
	}

	public Class<?> getModelClass() {
		return SocialActivity.class;
	}

	public String getModelClassName() {
		return SocialActivity.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("activityId", getActivityId());
		attributes.put("activitySetId", getActivitySetId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long activityId = (Long)attributes.get("activityId");

		if (activityId != null) {
			setActivityId(activityId);
		}

		Long activitySetId = (Long)attributes.get("activitySetId");

		if (activitySetId != null) {
			setActivitySetId(activitySetId);
		}
	}

	/**
	* Returns the primary key of this social activity.
	*
	* @return the primary key of this social activity
	*/
	public long getPrimaryKey() {
		return _socialActivity.getPrimaryKey();
	}

	/**
	* Sets the primary key of this social activity.
	*
	* @param primaryKey the primary key of this social activity
	*/
	public void setPrimaryKey(long primaryKey) {
		_socialActivity.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the activity ID of this social activity.
	*
	* @return the activity ID of this social activity
	*/
	public long getActivityId() {
		return _socialActivity.getActivityId();
	}

	/**
	* Sets the activity ID of this social activity.
	*
	* @param activityId the activity ID of this social activity
	*/
	public void setActivityId(long activityId) {
		_socialActivity.setActivityId(activityId);
	}

	/**
	* Returns the activity set ID of this social activity.
	*
	* @return the activity set ID of this social activity
	*/
	public long getActivitySetId() {
		return _socialActivity.getActivitySetId();
	}

	/**
	* Sets the activity set ID of this social activity.
	*
	* @param activitySetId the activity set ID of this social activity
	*/
	public void setActivitySetId(long activitySetId) {
		_socialActivity.setActivitySetId(activitySetId);
	}

	public boolean isNew() {
		return _socialActivity.isNew();
	}

	public void setNew(boolean n) {
		_socialActivity.setNew(n);
	}

	public boolean isCachedModel() {
		return _socialActivity.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_socialActivity.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _socialActivity.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _socialActivity.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_socialActivity.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _socialActivity.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_socialActivity.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SocialActivityWrapper((SocialActivity)_socialActivity.clone());
	}

	public int compareTo(
		com.liferay.so.activities.model.SocialActivity socialActivity) {
		return _socialActivity.compareTo(socialActivity);
	}

	@Override
	public int hashCode() {
		return _socialActivity.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.so.activities.model.SocialActivity> toCacheModel() {
		return _socialActivity.toCacheModel();
	}

	public com.liferay.so.activities.model.SocialActivity toEscapedModel() {
		return new SocialActivityWrapper(_socialActivity.toEscapedModel());
	}

	public com.liferay.so.activities.model.SocialActivity toUnescapedModel() {
		return new SocialActivityWrapper(_socialActivity.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _socialActivity.toString();
	}

	public java.lang.String toXmlString() {
		return _socialActivity.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_socialActivity.persist();
	}

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivity.getGroupId();
	}

	public com.liferay.portlet.social.model.SocialActivity getPortalSocialActivity()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivity.getPortalSocialActivity();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SocialActivity getWrappedSocialActivity() {
		return _socialActivity;
	}

	public SocialActivity getWrappedModel() {
		return _socialActivity;
	}

	public void resetOriginalValues() {
		_socialActivity.resetOriginalValues();
	}

	private SocialActivity _socialActivity;
}