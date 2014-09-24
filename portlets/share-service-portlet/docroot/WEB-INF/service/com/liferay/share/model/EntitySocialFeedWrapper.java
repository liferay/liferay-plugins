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

package com.liferay.share.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link EntitySocialFeed}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntitySocialFeed
 * @generated
 */
@ProviderType
public class EntitySocialFeedWrapper implements EntitySocialFeed,
	ModelWrapper<EntitySocialFeed> {
	public EntitySocialFeedWrapper(EntitySocialFeed entitySocialFeed) {
		_entitySocialFeed = entitySocialFeed;
	}

	@Override
	public Class<?> getModelClass() {
		return EntitySocialFeed.class;
	}

	@Override
	public String getModelClassName() {
		return EntitySocialFeed.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("feedClassNameId", getFeedClassNameId());
		attributes.put("feedClassPK", getFeedClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long feedClassNameId = (Long)attributes.get("feedClassNameId");

		if (feedClassNameId != null) {
			setFeedClassNameId(feedClassNameId);
		}

		Long feedClassPK = (Long)attributes.get("feedClassPK");

		if (feedClassPK != null) {
			setFeedClassPK(feedClassPK);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new EntitySocialFeedWrapper((EntitySocialFeed)_entitySocialFeed.clone());
	}

	@Override
	public int compareTo(
		com.liferay.share.model.EntitySocialFeed entitySocialFeed) {
		return _entitySocialFeed.compareTo(entitySocialFeed);
	}

	/**
	* Returns the fully qualified class name of this entity social feed.
	*
	* @return the fully qualified class name of this entity social feed
	*/
	@Override
	public java.lang.String getClassName() {
		return _entitySocialFeed.getClassName();
	}

	/**
	* Returns the class name ID of this entity social feed.
	*
	* @return the class name ID of this entity social feed
	*/
	@Override
	public long getClassNameId() {
		return _entitySocialFeed.getClassNameId();
	}

	/**
	* Returns the class p k of this entity social feed.
	*
	* @return the class p k of this entity social feed
	*/
	@Override
	public long getClassPK() {
		return _entitySocialFeed.getClassPK();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entitySocialFeed.getExpandoBridge();
	}

	/**
	* Returns the feed class name ID of this entity social feed.
	*
	* @return the feed class name ID of this entity social feed
	*/
	@Override
	public long getFeedClassNameId() {
		return _entitySocialFeed.getFeedClassNameId();
	}

	/**
	* Returns the feed class p k of this entity social feed.
	*
	* @return the feed class p k of this entity social feed
	*/
	@Override
	public long getFeedClassPK() {
		return _entitySocialFeed.getFeedClassPK();
	}

	/**
	* Returns the primary key of this entity social feed.
	*
	* @return the primary key of this entity social feed
	*/
	@Override
	public com.liferay.share.service.persistence.EntitySocialFeedPK getPrimaryKey() {
		return _entitySocialFeed.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _entitySocialFeed.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _entitySocialFeed.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _entitySocialFeed.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entitySocialFeed.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entitySocialFeed.isNew();
	}

	@Override
	public void persist() {
		_entitySocialFeed.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entitySocialFeed.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_entitySocialFeed.setClassName(className);
	}

	/**
	* Sets the class name ID of this entity social feed.
	*
	* @param classNameId the class name ID of this entity social feed
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_entitySocialFeed.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this entity social feed.
	*
	* @param classPK the class p k of this entity social feed
	*/
	@Override
	public void setClassPK(long classPK) {
		_entitySocialFeed.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_entitySocialFeed.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_entitySocialFeed.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entitySocialFeed.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the feed class name ID of this entity social feed.
	*
	* @param feedClassNameId the feed class name ID of this entity social feed
	*/
	@Override
	public void setFeedClassNameId(long feedClassNameId) {
		_entitySocialFeed.setFeedClassNameId(feedClassNameId);
	}

	/**
	* Sets the feed class p k of this entity social feed.
	*
	* @param feedClassPK the feed class p k of this entity social feed
	*/
	@Override
	public void setFeedClassPK(long feedClassPK) {
		_entitySocialFeed.setFeedClassPK(feedClassPK);
	}

	@Override
	public void setNew(boolean n) {
		_entitySocialFeed.setNew(n);
	}

	/**
	* Sets the primary key of this entity social feed.
	*
	* @param primaryKey the primary key of this entity social feed
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.share.service.persistence.EntitySocialFeedPK primaryKey) {
		_entitySocialFeed.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_entitySocialFeed.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.share.model.EntitySocialFeed> toCacheModel() {
		return _entitySocialFeed.toCacheModel();
	}

	@Override
	public com.liferay.share.model.EntitySocialFeed toEscapedModel() {
		return new EntitySocialFeedWrapper(_entitySocialFeed.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _entitySocialFeed.toString();
	}

	@Override
	public com.liferay.share.model.EntitySocialFeed toUnescapedModel() {
		return new EntitySocialFeedWrapper(_entitySocialFeed.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _entitySocialFeed.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntitySocialFeedWrapper)) {
			return false;
		}

		EntitySocialFeedWrapper entitySocialFeedWrapper = (EntitySocialFeedWrapper)obj;

		if (Validator.equals(_entitySocialFeed,
					entitySocialFeedWrapper._entitySocialFeed)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public EntitySocialFeed getWrappedEntitySocialFeed() {
		return _entitySocialFeed;
	}

	@Override
	public EntitySocialFeed getWrappedModel() {
		return _entitySocialFeed;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entitySocialFeed.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entitySocialFeed.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entitySocialFeed.resetOriginalValues();
	}

	private final EntitySocialFeed _entitySocialFeed;
}