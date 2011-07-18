/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link WSRPConsumer}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumer
 * @generated
 */
public class WSRPConsumerWrapper implements WSRPConsumer {
	public WSRPConsumerWrapper(WSRPConsumer wsrpConsumer) {
		_wsrpConsumer = wsrpConsumer;
	}

	public Class<?> getModelClass() {
		return WSRPConsumer.class;
	}

	public String getModelClassName() {
		return WSRPConsumer.class.getName();
	}

	/**
	* Returns the primary key of this w s r p consumer.
	*
	* @return the primary key of this w s r p consumer
	*/
	public long getPrimaryKey() {
		return _wsrpConsumer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this w s r p consumer.
	*
	* @param primaryKey the primary key of this w s r p consumer
	*/
	public void setPrimaryKey(long primaryKey) {
		_wsrpConsumer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this w s r p consumer.
	*
	* @return the uuid of this w s r p consumer
	*/
	public java.lang.String getUuid() {
		return _wsrpConsumer.getUuid();
	}

	/**
	* Sets the uuid of this w s r p consumer.
	*
	* @param uuid the uuid of this w s r p consumer
	*/
	public void setUuid(java.lang.String uuid) {
		_wsrpConsumer.setUuid(uuid);
	}

	/**
	* Returns the wsrp consumer ID of this w s r p consumer.
	*
	* @return the wsrp consumer ID of this w s r p consumer
	*/
	public long getWsrpConsumerId() {
		return _wsrpConsumer.getWsrpConsumerId();
	}

	/**
	* Sets the wsrp consumer ID of this w s r p consumer.
	*
	* @param wsrpConsumerId the wsrp consumer ID of this w s r p consumer
	*/
	public void setWsrpConsumerId(long wsrpConsumerId) {
		_wsrpConsumer.setWsrpConsumerId(wsrpConsumerId);
	}

	/**
	* Returns the company ID of this w s r p consumer.
	*
	* @return the company ID of this w s r p consumer
	*/
	public long getCompanyId() {
		return _wsrpConsumer.getCompanyId();
	}

	/**
	* Sets the company ID of this w s r p consumer.
	*
	* @param companyId the company ID of this w s r p consumer
	*/
	public void setCompanyId(long companyId) {
		_wsrpConsumer.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this w s r p consumer.
	*
	* @return the create date of this w s r p consumer
	*/
	public java.util.Date getCreateDate() {
		return _wsrpConsumer.getCreateDate();
	}

	/**
	* Sets the create date of this w s r p consumer.
	*
	* @param createDate the create date of this w s r p consumer
	*/
	public void setCreateDate(java.util.Date createDate) {
		_wsrpConsumer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this w s r p consumer.
	*
	* @return the modified date of this w s r p consumer
	*/
	public java.util.Date getModifiedDate() {
		return _wsrpConsumer.getModifiedDate();
	}

	/**
	* Sets the modified date of this w s r p consumer.
	*
	* @param modifiedDate the modified date of this w s r p consumer
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wsrpConsumer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this w s r p consumer.
	*
	* @return the name of this w s r p consumer
	*/
	public java.lang.String getName() {
		return _wsrpConsumer.getName();
	}

	/**
	* Sets the name of this w s r p consumer.
	*
	* @param name the name of this w s r p consumer
	*/
	public void setName(java.lang.String name) {
		_wsrpConsumer.setName(name);
	}

	/**
	* Returns the url of this w s r p consumer.
	*
	* @return the url of this w s r p consumer
	*/
	public java.lang.String getUrl() {
		return _wsrpConsumer.getUrl();
	}

	/**
	* Sets the url of this w s r p consumer.
	*
	* @param url the url of this w s r p consumer
	*/
	public void setUrl(java.lang.String url) {
		_wsrpConsumer.setUrl(url);
	}

	/**
	* Returns the wsdl of this w s r p consumer.
	*
	* @return the wsdl of this w s r p consumer
	*/
	public java.lang.String getWsdl() {
		return _wsrpConsumer.getWsdl();
	}

	/**
	* Sets the wsdl of this w s r p consumer.
	*
	* @param wsdl the wsdl of this w s r p consumer
	*/
	public void setWsdl(java.lang.String wsdl) {
		_wsrpConsumer.setWsdl(wsdl);
	}

	/**
	* Returns the registration context string of this w s r p consumer.
	*
	* @return the registration context string of this w s r p consumer
	*/
	public java.lang.String getRegistrationContextString() {
		return _wsrpConsumer.getRegistrationContextString();
	}

	/**
	* Sets the registration context string of this w s r p consumer.
	*
	* @param registrationContextString the registration context string of this w s r p consumer
	*/
	public void setRegistrationContextString(
		java.lang.String registrationContextString) {
		_wsrpConsumer.setRegistrationContextString(registrationContextString);
	}

	/**
	* Returns the registration properties string of this w s r p consumer.
	*
	* @return the registration properties string of this w s r p consumer
	*/
	public java.lang.String getRegistrationPropertiesString() {
		return _wsrpConsumer.getRegistrationPropertiesString();
	}

	/**
	* Sets the registration properties string of this w s r p consumer.
	*
	* @param registrationPropertiesString the registration properties string of this w s r p consumer
	*/
	public void setRegistrationPropertiesString(
		java.lang.String registrationPropertiesString) {
		_wsrpConsumer.setRegistrationPropertiesString(registrationPropertiesString);
	}

	/**
	* Returns the forward cookies of this w s r p consumer.
	*
	* @return the forward cookies of this w s r p consumer
	*/
	public java.lang.String getForwardCookies() {
		return _wsrpConsumer.getForwardCookies();
	}

	/**
	* Sets the forward cookies of this w s r p consumer.
	*
	* @param forwardCookies the forward cookies of this w s r p consumer
	*/
	public void setForwardCookies(java.lang.String forwardCookies) {
		_wsrpConsumer.setForwardCookies(forwardCookies);
	}

	public boolean isNew() {
		return _wsrpConsumer.isNew();
	}

	public void setNew(boolean n) {
		_wsrpConsumer.setNew(n);
	}

	public boolean isCachedModel() {
		return _wsrpConsumer.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wsrpConsumer.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wsrpConsumer.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wsrpConsumer.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wsrpConsumer.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wsrpConsumer.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wsrpConsumer.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wsrpConsumer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WSRPConsumerWrapper((WSRPConsumer)_wsrpConsumer.clone());
	}

	public int compareTo(com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return _wsrpConsumer.compareTo(wsrpConsumer);
	}

	@Override
	public int hashCode() {
		return _wsrpConsumer.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.wsrp.model.WSRPConsumer> toCacheModel() {
		return _wsrpConsumer.toCacheModel();
	}

	public com.liferay.wsrp.model.WSRPConsumer toEscapedModel() {
		return new WSRPConsumerWrapper(_wsrpConsumer.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wsrpConsumer.toString();
	}

	public java.lang.String toXmlString() {
		return _wsrpConsumer.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumer.persist();
	}

	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext() {
		return _wsrpConsumer.getRegistrationContext();
	}

	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties() {
		return _wsrpConsumer.getRegistrationProperties();
	}

	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext) {
		_wsrpConsumer.setRegistrationContext(registrationContext);
	}

	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties) {
		_wsrpConsumer.setRegistrationProperties(registrationProperties);
	}

	public WSRPConsumer getWrappedWSRPConsumer() {
		return _wsrpConsumer;
	}

	public void resetOriginalValues() {
		_wsrpConsumer.resetOriginalValues();
	}

	private WSRPConsumer _wsrpConsumer;
}