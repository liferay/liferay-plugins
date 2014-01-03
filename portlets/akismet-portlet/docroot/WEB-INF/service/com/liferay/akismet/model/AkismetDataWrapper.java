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

package com.liferay.akismet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AkismetData}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AkismetData
 * @generated
 */
public class AkismetDataWrapper implements AkismetData,
	ModelWrapper<AkismetData> {
	public AkismetDataWrapper(AkismetData akismetData) {
		_akismetData = akismetData;
	}

	@Override
	public Class<?> getModelClass() {
		return AkismetData.class;
	}

	@Override
	public String getModelClassName() {
		return AkismetData.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetDataId", getAkismetDataId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetDataId = (Long)attributes.get("akismetDataId");

		if (akismetDataId != null) {
			setAkismetDataId(akismetDataId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

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

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String permalink = (String)attributes.get("permalink");

		if (permalink != null) {
			setPermalink(permalink);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String userIP = (String)attributes.get("userIP");

		if (userIP != null) {
			setUserIP(userIP);
		}

		String userURL = (String)attributes.get("userURL");

		if (userURL != null) {
			setUserURL(userURL);
		}
	}

	/**
	* Returns the primary key of this akismet data.
	*
	* @return the primary key of this akismet data
	*/
	@Override
	public long getPrimaryKey() {
		return _akismetData.getPrimaryKey();
	}

	/**
	* Sets the primary key of this akismet data.
	*
	* @param primaryKey the primary key of this akismet data
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_akismetData.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the akismet data ID of this akismet data.
	*
	* @return the akismet data ID of this akismet data
	*/
	@Override
	public long getAkismetDataId() {
		return _akismetData.getAkismetDataId();
	}

	/**
	* Sets the akismet data ID of this akismet data.
	*
	* @param akismetDataId the akismet data ID of this akismet data
	*/
	@Override
	public void setAkismetDataId(long akismetDataId) {
		_akismetData.setAkismetDataId(akismetDataId);
	}

	/**
	* Returns the modified date of this akismet data.
	*
	* @return the modified date of this akismet data
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _akismetData.getModifiedDate();
	}

	/**
	* Sets the modified date of this akismet data.
	*
	* @param modifiedDate the modified date of this akismet data
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_akismetData.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this akismet data.
	*
	* @return the fully qualified class name of this akismet data
	*/
	@Override
	public java.lang.String getClassName() {
		return _akismetData.getClassName();
	}

	@Override
	public void setClassName(java.lang.String className) {
		_akismetData.setClassName(className);
	}

	/**
	* Returns the class name ID of this akismet data.
	*
	* @return the class name ID of this akismet data
	*/
	@Override
	public long getClassNameId() {
		return _akismetData.getClassNameId();
	}

	/**
	* Sets the class name ID of this akismet data.
	*
	* @param classNameId the class name ID of this akismet data
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_akismetData.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this akismet data.
	*
	* @return the class p k of this akismet data
	*/
	@Override
	public long getClassPK() {
		return _akismetData.getClassPK();
	}

	/**
	* Sets the class p k of this akismet data.
	*
	* @param classPK the class p k of this akismet data
	*/
	@Override
	public void setClassPK(long classPK) {
		_akismetData.setClassPK(classPK);
	}

	/**
	* Returns the type of this akismet data.
	*
	* @return the type of this akismet data
	*/
	@Override
	public java.lang.String getType() {
		return _akismetData.getType();
	}

	/**
	* Sets the type of this akismet data.
	*
	* @param type the type of this akismet data
	*/
	@Override
	public void setType(java.lang.String type) {
		_akismetData.setType(type);
	}

	/**
	* Returns the permalink of this akismet data.
	*
	* @return the permalink of this akismet data
	*/
	@Override
	public java.lang.String getPermalink() {
		return _akismetData.getPermalink();
	}

	/**
	* Sets the permalink of this akismet data.
	*
	* @param permalink the permalink of this akismet data
	*/
	@Override
	public void setPermalink(java.lang.String permalink) {
		_akismetData.setPermalink(permalink);
	}

	/**
	* Returns the referrer of this akismet data.
	*
	* @return the referrer of this akismet data
	*/
	@Override
	public java.lang.String getReferrer() {
		return _akismetData.getReferrer();
	}

	/**
	* Sets the referrer of this akismet data.
	*
	* @param referrer the referrer of this akismet data
	*/
	@Override
	public void setReferrer(java.lang.String referrer) {
		_akismetData.setReferrer(referrer);
	}

	/**
	* Returns the user agent of this akismet data.
	*
	* @return the user agent of this akismet data
	*/
	@Override
	public java.lang.String getUserAgent() {
		return _akismetData.getUserAgent();
	}

	/**
	* Sets the user agent of this akismet data.
	*
	* @param userAgent the user agent of this akismet data
	*/
	@Override
	public void setUserAgent(java.lang.String userAgent) {
		_akismetData.setUserAgent(userAgent);
	}

	/**
	* Returns the user i p of this akismet data.
	*
	* @return the user i p of this akismet data
	*/
	@Override
	public java.lang.String getUserIP() {
		return _akismetData.getUserIP();
	}

	/**
	* Sets the user i p of this akismet data.
	*
	* @param userIP the user i p of this akismet data
	*/
	@Override
	public void setUserIP(java.lang.String userIP) {
		_akismetData.setUserIP(userIP);
	}

	/**
	* Returns the user u r l of this akismet data.
	*
	* @return the user u r l of this akismet data
	*/
	@Override
	public java.lang.String getUserURL() {
		return _akismetData.getUserURL();
	}

	/**
	* Sets the user u r l of this akismet data.
	*
	* @param userURL the user u r l of this akismet data
	*/
	@Override
	public void setUserURL(java.lang.String userURL) {
		_akismetData.setUserURL(userURL);
	}

	@Override
	public boolean isNew() {
		return _akismetData.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_akismetData.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _akismetData.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_akismetData.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _akismetData.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _akismetData.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_akismetData.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _akismetData.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_akismetData.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_akismetData.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_akismetData.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AkismetDataWrapper((AkismetData)_akismetData.clone());
	}

	@Override
	public int compareTo(com.liferay.akismet.model.AkismetData akismetData) {
		return _akismetData.compareTo(akismetData);
	}

	@Override
	public int hashCode() {
		return _akismetData.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.akismet.model.AkismetData> toCacheModel() {
		return _akismetData.toCacheModel();
	}

	@Override
	public com.liferay.akismet.model.AkismetData toEscapedModel() {
		return new AkismetDataWrapper(_akismetData.toEscapedModel());
	}

	@Override
	public com.liferay.akismet.model.AkismetData toUnescapedModel() {
		return new AkismetDataWrapper(_akismetData.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _akismetData.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _akismetData.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_akismetData.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AkismetDataWrapper)) {
			return false;
		}

		AkismetDataWrapper akismetDataWrapper = (AkismetDataWrapper)obj;

		if (Validator.equals(_akismetData, akismetDataWrapper._akismetData)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public AkismetData getWrappedAkismetData() {
		return _akismetData;
	}

	@Override
	public AkismetData getWrappedModel() {
		return _akismetData;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _akismetData.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _akismetData.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_akismetData.resetOriginalValues();
	}

	private AkismetData _akismetData;
}