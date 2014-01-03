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

package com.liferay.wsrp.model;

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WSRPConsumer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumer
 * @generated
 */
public class WSRPConsumerWrapper implements WSRPConsumer,
	ModelWrapper<WSRPConsumer> {
	public WSRPConsumerWrapper(WSRPConsumer wsrpConsumer) {
		_wsrpConsumer = wsrpConsumer;
	}

	@Override
	public Class<?> getModelClass() {
		return WSRPConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return WSRPConsumer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("wsrpConsumerId", getWsrpConsumerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("wsdl", getWsdl());
		attributes.put("registrationContextString",
			getRegistrationContextString());
		attributes.put("registrationPropertiesString",
			getRegistrationPropertiesString());
		attributes.put("forwardCookies", getForwardCookies());
		attributes.put("forwardHeaders", getForwardHeaders());
		attributes.put("markupCharacterSets", getMarkupCharacterSets());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long wsrpConsumerId = (Long)attributes.get("wsrpConsumerId");

		if (wsrpConsumerId != null) {
			setWsrpConsumerId(wsrpConsumerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String wsdl = (String)attributes.get("wsdl");

		if (wsdl != null) {
			setWsdl(wsdl);
		}

		String registrationContextString = (String)attributes.get(
				"registrationContextString");

		if (registrationContextString != null) {
			setRegistrationContextString(registrationContextString);
		}

		String registrationPropertiesString = (String)attributes.get(
				"registrationPropertiesString");

		if (registrationPropertiesString != null) {
			setRegistrationPropertiesString(registrationPropertiesString);
		}

		String forwardCookies = (String)attributes.get("forwardCookies");

		if (forwardCookies != null) {
			setForwardCookies(forwardCookies);
		}

		String forwardHeaders = (String)attributes.get("forwardHeaders");

		if (forwardHeaders != null) {
			setForwardHeaders(forwardHeaders);
		}

		String markupCharacterSets = (String)attributes.get(
				"markupCharacterSets");

		if (markupCharacterSets != null) {
			setMarkupCharacterSets(markupCharacterSets);
		}
	}

	/**
	* Returns the primary key of this w s r p consumer.
	*
	* @return the primary key of this w s r p consumer
	*/
	@Override
	public long getPrimaryKey() {
		return _wsrpConsumer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this w s r p consumer.
	*
	* @param primaryKey the primary key of this w s r p consumer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wsrpConsumer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this w s r p consumer.
	*
	* @return the uuid of this w s r p consumer
	*/
	@Override
	public java.lang.String getUuid() {
		return _wsrpConsumer.getUuid();
	}

	/**
	* Sets the uuid of this w s r p consumer.
	*
	* @param uuid the uuid of this w s r p consumer
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_wsrpConsumer.setUuid(uuid);
	}

	/**
	* Returns the wsrp consumer ID of this w s r p consumer.
	*
	* @return the wsrp consumer ID of this w s r p consumer
	*/
	@Override
	public long getWsrpConsumerId() {
		return _wsrpConsumer.getWsrpConsumerId();
	}

	/**
	* Sets the wsrp consumer ID of this w s r p consumer.
	*
	* @param wsrpConsumerId the wsrp consumer ID of this w s r p consumer
	*/
	@Override
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumer.setWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Returns the company ID of this w s r p consumer.
	*
	* @return the company ID of this w s r p consumer
	*/
	@Override
	public long getCompanyId() {
		return _wsrpConsumer.getCompanyId();
	}

	/**
	* Sets the company ID of this w s r p consumer.
	*
	* @param companyId the company ID of this w s r p consumer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wsrpConsumer.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this w s r p consumer.
	*
	* @return the create date of this w s r p consumer
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _wsrpConsumer.getCreateDate();
	}

	/**
	* Sets the create date of this w s r p consumer.
	*
	* @param createDate the create date of this w s r p consumer
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this w s r p consumer.
	*
	* @return the modified date of this w s r p consumer
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _wsrpConsumer.getModifiedDate();
	}

	/**
	* Sets the modified date of this w s r p consumer.
	*
	* @param modifiedDate the modified date of this w s r p consumer
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this w s r p consumer.
	*
	* @return the name of this w s r p consumer
	*/
	@Override
	public java.lang.String getName() {
		return _wsrpConsumer.getName();
	}

	/**
	* Sets the name of this w s r p consumer.
	*
	* @param name the name of this w s r p consumer
	*/
	@Override
	public void setName(java.lang.String name) {
		_wsrpConsumer.setName(name);
	}

	/**
	* Returns the url of this w s r p consumer.
	*
	* @return the url of this w s r p consumer
	*/
	@Override
	public java.lang.String getUrl() {
		return _wsrpConsumer.getUrl();
	}

	/**
	* Sets the url of this w s r p consumer.
	*
	* @param url the url of this w s r p consumer
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_wsrpConsumer.setUrl(url);
	}

	/**
	* Returns the wsdl of this w s r p consumer.
	*
	* @return the wsdl of this w s r p consumer
	*/
	@Override
	public java.lang.String getWsdl() {
		return _wsrpConsumer.getWsdl();
	}

	/**
	* Sets the wsdl of this w s r p consumer.
	*
	* @param wsdl the wsdl of this w s r p consumer
	*/
	@Override
	public void setWsdl(java.lang.String wsdl) {
		_wsrpConsumer.setWsdl(wsdl);
	}

	/**
	* Returns the registration context string of this w s r p consumer.
	*
	* @return the registration context string of this w s r p consumer
	*/
	@Override
	public java.lang.String getRegistrationContextString() {
		return _wsrpConsumer.getRegistrationContextString();
	}

	/**
	* Sets the registration context string of this w s r p consumer.
	*
	* @param registrationContextString the registration context string of this w s r p consumer
	*/
	@Override
	public void setRegistrationContextString(
		java.lang.String registrationContextString) {
		_wsrpConsumer.setRegistrationContextString(registrationContextString);
	}

	/**
	* Returns the registration properties string of this w s r p consumer.
	*
	* @return the registration properties string of this w s r p consumer
	*/
	@Override
	public java.lang.String getRegistrationPropertiesString() {
		return _wsrpConsumer.getRegistrationPropertiesString();
	}

	/**
	* Sets the registration properties string of this w s r p consumer.
	*
	* @param registrationPropertiesString the registration properties string of this w s r p consumer
	*/
	@Override
	public void setRegistrationPropertiesString(
		java.lang.String registrationPropertiesString) {
		_wsrpConsumer.setRegistrationPropertiesString(registrationPropertiesString);
	}

	/**
	* Returns the forward cookies of this w s r p consumer.
	*
	* @return the forward cookies of this w s r p consumer
	*/
	@Override
	public java.lang.String getForwardCookies() {
		return _wsrpConsumer.getForwardCookies();
	}

	/**
	* Sets the forward cookies of this w s r p consumer.
	*
	* @param forwardCookies the forward cookies of this w s r p consumer
	*/
	@Override
	public void setForwardCookies(java.lang.String forwardCookies) {
		_wsrpConsumer.setForwardCookies(forwardCookies);
	}

	/**
	* Returns the forward headers of this w s r p consumer.
	*
	* @return the forward headers of this w s r p consumer
	*/
	@Override
	public java.lang.String getForwardHeaders() {
		return _wsrpConsumer.getForwardHeaders();
	}

	/**
	* Sets the forward headers of this w s r p consumer.
	*
	* @param forwardHeaders the forward headers of this w s r p consumer
	*/
	@Override
	public void setForwardHeaders(java.lang.String forwardHeaders) {
		_wsrpConsumer.setForwardHeaders(forwardHeaders);
	}

	/**
	* Returns the markup character sets of this w s r p consumer.
	*
	* @return the markup character sets of this w s r p consumer
	*/
	@Override
	public java.lang.String getMarkupCharacterSets() {
		return _wsrpConsumer.getMarkupCharacterSets();
	}

	/**
	* Sets the markup character sets of this w s r p consumer.
	*
	* @param markupCharacterSets the markup character sets of this w s r p consumer
	*/
	@Override
	public void setMarkupCharacterSets(java.lang.String markupCharacterSets) {
		_wsrpConsumer.setMarkupCharacterSets(markupCharacterSets);
	}

	@Override
	public boolean isNew() {
		return _wsrpConsumer.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_wsrpConsumer.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _wsrpConsumer.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumer.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _wsrpConsumer.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpConsumer.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wsrpConsumer.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumer.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_wsrpConsumer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_wsrpConsumer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPConsumerWrapper((WSRPConsumer)_wsrpConsumer.clone());
	}

	@Override
	public int compareTo(com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return _wsrpConsumer.compareTo(wsrpConsumer);
	}

	@Override
	public int hashCode() {
		return _wsrpConsumer.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.wsrp.model.WSRPConsumer> toCacheModel() {
		return _wsrpConsumer.toCacheModel();
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer toEscapedModel() {
		return new WSRPConsumerWrapper(_wsrpConsumer.toEscapedModel());
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer toUnescapedModel() {
		return new WSRPConsumerWrapper(_wsrpConsumer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wsrpConsumer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _wsrpConsumer.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumer.persist();
	}

	@Override
	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext() {
		return _wsrpConsumer.getRegistrationContext();
	}

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties() {
		return _wsrpConsumer.getRegistrationProperties();
	}

	@Override
	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext) {
		_wsrpConsumer.setRegistrationContext(registrationContext);
	}

	@Override
	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties) {
		_wsrpConsumer.setRegistrationProperties(registrationProperties);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPConsumerWrapper)) {
			return false;
		}

		WSRPConsumerWrapper wsrpConsumerWrapper = (WSRPConsumerWrapper)obj;

		if (Validator.equals(_wsrpConsumer, wsrpConsumerWrapper._wsrpConsumer)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _wsrpConsumer.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public WSRPConsumer getWrappedWSRPConsumer() {
		return _wsrpConsumer;
	}

	@Override
	public WSRPConsumer getWrappedModel() {
		return _wsrpConsumer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wsrpConsumer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wsrpConsumer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wsrpConsumer.resetOriginalValues();
	}

	private WSRPConsumer _wsrpConsumer;
}