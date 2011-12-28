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

package com.liferay.opensocial.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OAuthConsumer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthConsumer
 * @generated
 */
public class OAuthConsumerWrapper implements OAuthConsumer,
	ModelWrapper<OAuthConsumer> {
	public OAuthConsumerWrapper(OAuthConsumer oAuthConsumer) {
		_oAuthConsumer = oAuthConsumer;
	}

	public Class<?> getModelClass() {
		return OAuthConsumer.class;
	}

	public String getModelClassName() {
		return OAuthConsumer.class.getName();
	}

	/**
	* Returns the primary key of this o auth consumer.
	*
	* @return the primary key of this o auth consumer
	*/
	public long getPrimaryKey() {
		return _oAuthConsumer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth consumer.
	*
	* @param primaryKey the primary key of this o auth consumer
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthConsumer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the o auth consumer ID of this o auth consumer.
	*
	* @return the o auth consumer ID of this o auth consumer
	*/
	public long getOAuthConsumerId() {
		return _oAuthConsumer.getOAuthConsumerId();
	}

	/**
	* Sets the o auth consumer ID of this o auth consumer.
	*
	* @param oAuthConsumerId the o auth consumer ID of this o auth consumer
	*/
	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumer.setOAuthConsumerId(oAuthConsumerId);
	}

	/**
	* Returns the company ID of this o auth consumer.
	*
	* @return the company ID of this o auth consumer
	*/
	public long getCompanyId() {
		return _oAuthConsumer.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth consumer.
	*
	* @param companyId the company ID of this o auth consumer
	*/
	public void setCompanyId(long companyId) {
		_oAuthConsumer.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this o auth consumer.
	*
	* @return the create date of this o auth consumer
	*/
	public java.util.Date getCreateDate() {
		return _oAuthConsumer.getCreateDate();
	}

	/**
	* Sets the create date of this o auth consumer.
	*
	* @param createDate the create date of this o auth consumer
	*/
	public void setCreateDate(java.util.Date createDate) {
		_oAuthConsumer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth consumer.
	*
	* @return the modified date of this o auth consumer
	*/
	public java.util.Date getModifiedDate() {
		return _oAuthConsumer.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth consumer.
	*
	* @param modifiedDate the modified date of this o auth consumer
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthConsumer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the gadget key of this o auth consumer.
	*
	* @return the gadget key of this o auth consumer
	*/
	public java.lang.String getGadgetKey() {
		return _oAuthConsumer.getGadgetKey();
	}

	/**
	* Sets the gadget key of this o auth consumer.
	*
	* @param gadgetKey the gadget key of this o auth consumer
	*/
	public void setGadgetKey(java.lang.String gadgetKey) {
		_oAuthConsumer.setGadgetKey(gadgetKey);
	}

	/**
	* Returns the service name of this o auth consumer.
	*
	* @return the service name of this o auth consumer
	*/
	public java.lang.String getServiceName() {
		return _oAuthConsumer.getServiceName();
	}

	/**
	* Sets the service name of this o auth consumer.
	*
	* @param serviceName the service name of this o auth consumer
	*/
	public void setServiceName(java.lang.String serviceName) {
		_oAuthConsumer.setServiceName(serviceName);
	}

	/**
	* Returns the consumer key of this o auth consumer.
	*
	* @return the consumer key of this o auth consumer
	*/
	public java.lang.String getConsumerKey() {
		return _oAuthConsumer.getConsumerKey();
	}

	/**
	* Sets the consumer key of this o auth consumer.
	*
	* @param consumerKey the consumer key of this o auth consumer
	*/
	public void setConsumerKey(java.lang.String consumerKey) {
		_oAuthConsumer.setConsumerKey(consumerKey);
	}

	/**
	* Returns the consumer secret of this o auth consumer.
	*
	* @return the consumer secret of this o auth consumer
	*/
	public java.lang.String getConsumerSecret() {
		return _oAuthConsumer.getConsumerSecret();
	}

	/**
	* Sets the consumer secret of this o auth consumer.
	*
	* @param consumerSecret the consumer secret of this o auth consumer
	*/
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_oAuthConsumer.setConsumerSecret(consumerSecret);
	}

	/**
	* Returns the key type of this o auth consumer.
	*
	* @return the key type of this o auth consumer
	*/
	public java.lang.String getKeyType() {
		return _oAuthConsumer.getKeyType();
	}

	/**
	* Sets the key type of this o auth consumer.
	*
	* @param keyType the key type of this o auth consumer
	*/
	public void setKeyType(java.lang.String keyType) {
		_oAuthConsumer.setKeyType(keyType);
	}

	public boolean isNew() {
		return _oAuthConsumer.isNew();
	}

	public void setNew(boolean n) {
		_oAuthConsumer.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthConsumer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthConsumer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthConsumer.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthConsumer.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthConsumer.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthConsumer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthConsumerWrapper((OAuthConsumer)_oAuthConsumer.clone());
	}

	public int compareTo(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer) {
		return _oAuthConsumer.compareTo(oAuthConsumer);
	}

	@Override
	public int hashCode() {
		return _oAuthConsumer.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.opensocial.model.OAuthConsumer> toCacheModel() {
		return _oAuthConsumer.toCacheModel();
	}

	public com.liferay.opensocial.model.OAuthConsumer toEscapedModel() {
		return new OAuthConsumerWrapper(_oAuthConsumer.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthConsumer.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthConsumer.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthConsumer.persist();
	}

	public java.lang.String getKeyName() {
		return _oAuthConsumer.getKeyName();
	}

	public void setKeyName(java.lang.String keyName) {
		_oAuthConsumer.setKeyName(keyName);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthConsumer getWrappedOAuthConsumer() {
		return _oAuthConsumer;
	}

	public OAuthConsumer getWrappedModel() {
		return _oAuthConsumer;
	}

	public void resetOriginalValues() {
		_oAuthConsumer.resetOriginalValues();
	}

	private OAuthConsumer _oAuthConsumer;
}