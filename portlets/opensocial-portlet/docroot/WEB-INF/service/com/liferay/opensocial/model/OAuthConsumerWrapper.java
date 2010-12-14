/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link OAuthConsumer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthConsumer
 * @generated
 */
public class OAuthConsumerWrapper implements OAuthConsumer {
	public OAuthConsumerWrapper(OAuthConsumer oAuthConsumer) {
		_oAuthConsumer = oAuthConsumer;
	}

	/**
	* Gets the primary key of this o auth consumer.
	*
	* @return the primary key of this o auth consumer
	*/
	public long getPrimaryKey() {
		return _oAuthConsumer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth consumer
	*
	* @param pk the primary key of this o auth consumer
	*/
	public void setPrimaryKey(long pk) {
		_oAuthConsumer.setPrimaryKey(pk);
	}

	/**
	* Gets the oauth consumer id of this o auth consumer.
	*
	* @return the oauth consumer id of this o auth consumer
	*/
	public long getOauthConsumerId() {
		return _oAuthConsumer.getOauthConsumerId();
	}

	/**
	* Sets the oauth consumer id of this o auth consumer.
	*
	* @param oauthConsumerId the oauth consumer id of this o auth consumer
	*/
	public void setOauthConsumerId(long oauthConsumerId) {
		_oAuthConsumer.setOauthConsumerId(oauthConsumerId);
	}

	/**
	* Gets the company id of this o auth consumer.
	*
	* @return the company id of this o auth consumer
	*/
	public long getCompanyId() {
		return _oAuthConsumer.getCompanyId();
	}

	/**
	* Sets the company id of this o auth consumer.
	*
	* @param companyId the company id of this o auth consumer
	*/
	public void setCompanyId(long companyId) {
		_oAuthConsumer.setCompanyId(companyId);
	}

	/**
	* Gets the create date of this o auth consumer.
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
	* Gets the modified date of this o auth consumer.
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
	* Gets the gadget id of this o auth consumer.
	*
	* @return the gadget id of this o auth consumer
	*/
	public long getGadgetId() {
		return _oAuthConsumer.getGadgetId();
	}

	/**
	* Sets the gadget id of this o auth consumer.
	*
	* @param gadgetId the gadget id of this o auth consumer
	*/
	public void setGadgetId(long gadgetId) {
		_oAuthConsumer.setGadgetId(gadgetId);
	}

	/**
	* Gets the service name of this o auth consumer.
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
	* Gets the consumer key of this o auth consumer.
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
	* Gets the consumer secret of this o auth consumer.
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
	* Gets the key type of this o auth consumer.
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

	/**
	* Gets the callback url of this o auth consumer.
	*
	* @return the callback url of this o auth consumer
	*/
	public java.lang.String getCallbackUrl() {
		return _oAuthConsumer.getCallbackUrl();
	}

	/**
	* Sets the callback url of this o auth consumer.
	*
	* @param callbackUrl the callback url of this o auth consumer
	*/
	public void setCallbackUrl(java.lang.String callbackUrl) {
		_oAuthConsumer.setCallbackUrl(callbackUrl);
	}

	/**
	* Gets the key name of this o auth consumer.
	*
	* @return the key name of this o auth consumer
	*/
	public java.lang.String getKeyName() {
		return _oAuthConsumer.getKeyName();
	}

	/**
	* Sets the key name of this o auth consumer.
	*
	* @param keyName the key name of this o auth consumer
	*/
	public void setKeyName(java.lang.String keyName) {
		_oAuthConsumer.setKeyName(keyName);
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

	public void setEscapedModel(boolean escapedModel) {
		_oAuthConsumer.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthConsumer.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthConsumer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new OAuthConsumerWrapper((OAuthConsumer)_oAuthConsumer.clone());
	}

	public int compareTo(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer) {
		return _oAuthConsumer.compareTo(oAuthConsumer);
	}

	public int hashCode() {
		return _oAuthConsumer.hashCode();
	}

	public com.liferay.opensocial.model.OAuthConsumer toEscapedModel() {
		return new OAuthConsumerWrapper(_oAuthConsumer.toEscapedModel());
	}

	public java.lang.String toString() {
		return _oAuthConsumer.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthConsumer.toXmlString();
	}

	public OAuthConsumer getWrappedOAuthConsumer() {
		return _oAuthConsumer;
	}

	private OAuthConsumer _oAuthConsumer;
}