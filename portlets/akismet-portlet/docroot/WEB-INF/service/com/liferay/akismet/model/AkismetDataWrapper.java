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

package com.liferay.akismet.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AkismetData}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AkismetData
 * @generated
 */
public class AkismetDataWrapper implements AkismetData,
	ModelWrapper<AkismetData> {
	public AkismetDataWrapper(AkismetData akismetData) {
		_akismetData = akismetData;
	}

	public Class<?> getModelClass() {
		return AkismetData.class;
	}

	public String getModelClassName() {
		return AkismetData.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetDataId", getAkismetDataId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mbMessageId", getMbMessageId());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetDataId = (Long)attributes.get("akismetDataId");

		if (akismetDataId != null) {
			setAkismetDataId(akismetDataId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long mbMessageId = (Long)attributes.get("mbMessageId");

		if (mbMessageId != null) {
			setMbMessageId(mbMessageId);
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
	public long getPrimaryKey() {
		return _akismetData.getPrimaryKey();
	}

	/**
	* Sets the primary key of this akismet data.
	*
	* @param primaryKey the primary key of this akismet data
	*/
	public void setPrimaryKey(long primaryKey) {
		_akismetData.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the akismet data ID of this akismet data.
	*
	* @return the akismet data ID of this akismet data
	*/
	public long getAkismetDataId() {
		return _akismetData.getAkismetDataId();
	}

	/**
	* Sets the akismet data ID of this akismet data.
	*
	* @param akismetDataId the akismet data ID of this akismet data
	*/
	public void setAkismetDataId(long akismetDataId) {
		_akismetData.setAkismetDataId(akismetDataId);
	}

	/**
	* Returns the modified date of this akismet data.
	*
	* @return the modified date of this akismet data
	*/
	public java.util.Date getModifiedDate() {
		return _akismetData.getModifiedDate();
	}

	/**
	* Sets the modified date of this akismet data.
	*
	* @param modifiedDate the modified date of this akismet data
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_akismetData.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the mb message ID of this akismet data.
	*
	* @return the mb message ID of this akismet data
	*/
	public long getMbMessageId() {
		return _akismetData.getMbMessageId();
	}

	/**
	* Sets the mb message ID of this akismet data.
	*
	* @param mbMessageId the mb message ID of this akismet data
	*/
	public void setMbMessageId(long mbMessageId) {
		_akismetData.setMbMessageId(mbMessageId);
	}

	/**
	* Returns the type of this akismet data.
	*
	* @return the type of this akismet data
	*/
	public java.lang.String getType() {
		return _akismetData.getType();
	}

	/**
	* Sets the type of this akismet data.
	*
	* @param type the type of this akismet data
	*/
	public void setType(java.lang.String type) {
		_akismetData.setType(type);
	}

	/**
	* Returns the permalink of this akismet data.
	*
	* @return the permalink of this akismet data
	*/
	public java.lang.String getPermalink() {
		return _akismetData.getPermalink();
	}

	/**
	* Sets the permalink of this akismet data.
	*
	* @param permalink the permalink of this akismet data
	*/
	public void setPermalink(java.lang.String permalink) {
		_akismetData.setPermalink(permalink);
	}

	/**
	* Returns the referrer of this akismet data.
	*
	* @return the referrer of this akismet data
	*/
	public java.lang.String getReferrer() {
		return _akismetData.getReferrer();
	}

	/**
	* Sets the referrer of this akismet data.
	*
	* @param referrer the referrer of this akismet data
	*/
	public void setReferrer(java.lang.String referrer) {
		_akismetData.setReferrer(referrer);
	}

	/**
	* Returns the user agent of this akismet data.
	*
	* @return the user agent of this akismet data
	*/
	public java.lang.String getUserAgent() {
		return _akismetData.getUserAgent();
	}

	/**
	* Sets the user agent of this akismet data.
	*
	* @param userAgent the user agent of this akismet data
	*/
	public void setUserAgent(java.lang.String userAgent) {
		_akismetData.setUserAgent(userAgent);
	}

	/**
	* Returns the user i p of this akismet data.
	*
	* @return the user i p of this akismet data
	*/
	public java.lang.String getUserIP() {
		return _akismetData.getUserIP();
	}

	/**
	* Sets the user i p of this akismet data.
	*
	* @param userIP the user i p of this akismet data
	*/
	public void setUserIP(java.lang.String userIP) {
		_akismetData.setUserIP(userIP);
	}

	/**
	* Returns the user u r l of this akismet data.
	*
	* @return the user u r l of this akismet data
	*/
	public java.lang.String getUserURL() {
		return _akismetData.getUserURL();
	}

	/**
	* Sets the user u r l of this akismet data.
	*
	* @param userURL the user u r l of this akismet data
	*/
	public void setUserURL(java.lang.String userURL) {
		_akismetData.setUserURL(userURL);
	}

	public boolean isNew() {
		return _akismetData.isNew();
	}

	public void setNew(boolean n) {
		_akismetData.setNew(n);
	}

	public boolean isCachedModel() {
		return _akismetData.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_akismetData.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _akismetData.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _akismetData.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_akismetData.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _akismetData.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_akismetData.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AkismetDataWrapper((AkismetData)_akismetData.clone());
	}

	public int compareTo(com.liferay.akismet.model.AkismetData akismetData) {
		return _akismetData.compareTo(akismetData);
	}

	@Override
	public int hashCode() {
		return _akismetData.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.akismet.model.AkismetData> toCacheModel() {
		return _akismetData.toCacheModel();
	}

	public com.liferay.akismet.model.AkismetData toEscapedModel() {
		return new AkismetDataWrapper(_akismetData.toEscapedModel());
	}

	public com.liferay.akismet.model.AkismetData toUnescapedModel() {
		return new AkismetDataWrapper(_akismetData.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _akismetData.toString();
	}

	public java.lang.String toXmlString() {
		return _akismetData.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_akismetData.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AkismetData getWrappedAkismetData() {
		return _akismetData;
	}

	public AkismetData getWrappedModel() {
		return _akismetData;
	}

	public void resetOriginalValues() {
		_akismetData.resetOriginalValues();
	}

	private AkismetData _akismetData;
}