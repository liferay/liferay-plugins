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

package com.liferay.opensocial.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthConsumer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumer
 * @generated
 */
@ProviderType
public class OAuthConsumerWrapper implements OAuthConsumer,
	ModelWrapper<OAuthConsumer> {
	public OAuthConsumerWrapper(OAuthConsumer oAuthConsumer) {
		_oAuthConsumer = oAuthConsumer;
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthConsumer.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthConsumer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthConsumerId", getOAuthConsumerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("gadgetKey", getGadgetKey());
		attributes.put("serviceName", getServiceName());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("keyType", getKeyType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthConsumerId = (Long)attributes.get("oAuthConsumerId");

		if (oAuthConsumerId != null) {
			setOAuthConsumerId(oAuthConsumerId);
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

		String gadgetKey = (String)attributes.get("gadgetKey");

		if (gadgetKey != null) {
			setGadgetKey(gadgetKey);
		}

		String serviceName = (String)attributes.get("serviceName");

		if (serviceName != null) {
			setServiceName(serviceName);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String keyType = (String)attributes.get("keyType");

		if (keyType != null) {
			setKeyType(keyType);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthConsumerWrapper((OAuthConsumer)_oAuthConsumer.clone());
	}

	@Override
	public int compareTo(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer) {
		return _oAuthConsumer.compareTo(oAuthConsumer);
	}

	/**
	* Returns the company ID of this o auth consumer.
	*
	* @return the company ID of this o auth consumer
	*/
	@Override
	public long getCompanyId() {
		return _oAuthConsumer.getCompanyId();
	}

	/**
	* Returns the consumer key of this o auth consumer.
	*
	* @return the consumer key of this o auth consumer
	*/
	@Override
	public java.lang.String getConsumerKey() {
		return _oAuthConsumer.getConsumerKey();
	}

	/**
	* Returns the consumer secret of this o auth consumer.
	*
	* @return the consumer secret of this o auth consumer
	*/
	@Override
	public java.lang.String getConsumerSecret() {
		return _oAuthConsumer.getConsumerSecret();
	}

	/**
	* Returns the create date of this o auth consumer.
	*
	* @return the create date of this o auth consumer
	*/
	@Override
	public Date getCreateDate() {
		return _oAuthConsumer.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthConsumer.getExpandoBridge();
	}

	/**
	* Returns the gadget key of this o auth consumer.
	*
	* @return the gadget key of this o auth consumer
	*/
	@Override
	public java.lang.String getGadgetKey() {
		return _oAuthConsumer.getGadgetKey();
	}

	@Override
	public java.lang.String getKeyName() {
		return _oAuthConsumer.getKeyName();
	}

	/**
	* Returns the key type of this o auth consumer.
	*
	* @return the key type of this o auth consumer
	*/
	@Override
	public java.lang.String getKeyType() {
		return _oAuthConsumer.getKeyType();
	}

	/**
	* Returns the modified date of this o auth consumer.
	*
	* @return the modified date of this o auth consumer
	*/
	@Override
	public Date getModifiedDate() {
		return _oAuthConsumer.getModifiedDate();
	}

	/**
	* Returns the o auth consumer ID of this o auth consumer.
	*
	* @return the o auth consumer ID of this o auth consumer
	*/
	@Override
	public long getOAuthConsumerId() {
		return _oAuthConsumer.getOAuthConsumerId();
	}

	/**
	* Returns the primary key of this o auth consumer.
	*
	* @return the primary key of this o auth consumer
	*/
	@Override
	public long getPrimaryKey() {
		return _oAuthConsumer.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthConsumer.getPrimaryKeyObj();
	}

	/**
	* Returns the service name of this o auth consumer.
	*
	* @return the service name of this o auth consumer
	*/
	@Override
	public java.lang.String getServiceName() {
		return _oAuthConsumer.getServiceName();
	}

	@Override
	public int hashCode() {
		return _oAuthConsumer.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _oAuthConsumer.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _oAuthConsumer.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _oAuthConsumer.isNew();
	}

	@Override
	public void persist() {
		_oAuthConsumer.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_oAuthConsumer.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this o auth consumer.
	*
	* @param companyId the company ID of this o auth consumer
	*/
	@Override
	public void setCompanyId(long companyId) {
		_oAuthConsumer.setCompanyId(companyId);
	}

	/**
	* Sets the consumer key of this o auth consumer.
	*
	* @param consumerKey the consumer key of this o auth consumer
	*/
	@Override
	public void setConsumerKey(java.lang.String consumerKey) {
		_oAuthConsumer.setConsumerKey(consumerKey);
	}

	/**
	* Sets the consumer secret of this o auth consumer.
	*
	* @param consumerSecret the consumer secret of this o auth consumer
	*/
	@Override
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_oAuthConsumer.setConsumerSecret(consumerSecret);
	}

	/**
	* Sets the create date of this o auth consumer.
	*
	* @param createDate the create date of this o auth consumer
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_oAuthConsumer.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_oAuthConsumer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_oAuthConsumer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the gadget key of this o auth consumer.
	*
	* @param gadgetKey the gadget key of this o auth consumer
	*/
	@Override
	public void setGadgetKey(java.lang.String gadgetKey) {
		_oAuthConsumer.setGadgetKey(gadgetKey);
	}

	@Override
	public void setKeyName(java.lang.String keyName) {
		_oAuthConsumer.setKeyName(keyName);
	}

	/**
	* Sets the key type of this o auth consumer.
	*
	* @param keyType the key type of this o auth consumer
	*/
	@Override
	public void setKeyType(java.lang.String keyType) {
		_oAuthConsumer.setKeyType(keyType);
	}

	/**
	* Sets the modified date of this o auth consumer.
	*
	* @param modifiedDate the modified date of this o auth consumer
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_oAuthConsumer.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_oAuthConsumer.setNew(n);
	}

	/**
	* Sets the o auth consumer ID of this o auth consumer.
	*
	* @param oAuthConsumerId the o auth consumer ID of this o auth consumer
	*/
	@Override
	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumer.setOAuthConsumerId(oAuthConsumerId);
	}

	/**
	* Sets the primary key of this o auth consumer.
	*
	* @param primaryKey the primary key of this o auth consumer
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_oAuthConsumer.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthConsumer.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the service name of this o auth consumer.
	*
	* @param serviceName the service name of this o auth consumer
	*/
	@Override
	public void setServiceName(java.lang.String serviceName) {
		_oAuthConsumer.setServiceName(serviceName);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.OAuthConsumer> toCacheModel() {
		return _oAuthConsumer.toCacheModel();
	}

	@Override
	public com.liferay.opensocial.model.OAuthConsumer toEscapedModel() {
		return new OAuthConsumerWrapper(_oAuthConsumer.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthConsumer.toString();
	}

	@Override
	public com.liferay.opensocial.model.OAuthConsumer toUnescapedModel() {
		return new OAuthConsumerWrapper(_oAuthConsumer.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _oAuthConsumer.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OAuthConsumerWrapper)) {
			return false;
		}

		OAuthConsumerWrapper oAuthConsumerWrapper = (OAuthConsumerWrapper)obj;

		if (Validator.equals(_oAuthConsumer, oAuthConsumerWrapper._oAuthConsumer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public OAuthConsumer getWrappedOAuthConsumer() {
		return _oAuthConsumer;
	}

	@Override
	public OAuthConsumer getWrappedModel() {
		return _oAuthConsumer;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _oAuthConsumer.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _oAuthConsumer.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_oAuthConsumer.resetOriginalValues();
	}

	private final OAuthConsumer _oAuthConsumer;
}