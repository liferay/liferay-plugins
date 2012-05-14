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

package com.liferay.portal.oauth.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthApplication}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthApplication
 * @generated
 */
public class OAuthApplicationWrapper implements OAuthApplication,
	ModelWrapper<OAuthApplication> {
	public OAuthApplicationWrapper(OAuthApplication oAuthApplication) {
		_oAuthApplication = oAuthApplication;
	}

	public Class<?> getModelClass() {
		return OAuthApplication.class;
	}

	public String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationId", getApplicationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("ownerId", getOwnerId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("website", getWebsite());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("callBackURL", getCallBackURL());
		attributes.put("accessLevel", getAccessLevel());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long applicationId = (Long)attributes.get("applicationId");

		if (applicationId != null) {
			setApplicationId(applicationId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long ownerId = (Long)attributes.get("ownerId");

		if (ownerId != null) {
			setOwnerId(ownerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String callBackURL = (String)attributes.get("callBackURL");

		if (callBackURL != null) {
			setCallBackURL(callBackURL);
		}

		Integer accessLevel = (Integer)attributes.get("accessLevel");

		if (accessLevel != null) {
			setAccessLevel(accessLevel);
		}
	}

	/**
	* Returns the primary key of this o auth application.
	*
	* @return the primary key of this o auth application
	*/
	public long getPrimaryKey() {
		return _oAuthApplication.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth application.
	*
	* @param primaryKey the primary key of this o auth application
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthApplication.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the application ID of this o auth application.
	*
	* @return the application ID of this o auth application
	*/
	public long getApplicationId() {
		return _oAuthApplication.getApplicationId();
	}

	/**
	* Sets the application ID of this o auth application.
	*
	* @param applicationId the application ID of this o auth application
	*/
	public void setApplicationId(long applicationId) {
		_oAuthApplication.setApplicationId(applicationId);
	}

	/**
	* Returns the company ID of this o auth application.
	*
	* @return the company ID of this o auth application
	*/
	public long getCompanyId() {
		return _oAuthApplication.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth application.
	*
	* @param companyId the company ID of this o auth application
	*/
	public void setCompanyId(long companyId) {
		_oAuthApplication.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this o auth application.
	*
	* @return the user ID of this o auth application
	*/
	public long getUserId() {
		return _oAuthApplication.getUserId();
	}

	/**
	* Sets the user ID of this o auth application.
	*
	* @param userId the user ID of this o auth application
	*/
	public void setUserId(long userId) {
		_oAuthApplication.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth application.
	*
	* @return the user uuid of this o auth application
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplication.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth application.
	*
	* @param userUuid the user uuid of this o auth application
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthApplication.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth application.
	*
	* @return the user name of this o auth application
	*/
	public java.lang.String getUserName() {
		return _oAuthApplication.getUserName();
	}

	/**
	* Sets the user name of this o auth application.
	*
	* @param userName the user name of this o auth application
	*/
	public void setUserName(java.lang.String userName) {
		_oAuthApplication.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth application.
	*
	* @return the create date of this o auth application
	*/
	public java.util.Date getCreateDate() {
		return _oAuthApplication.getCreateDate();
	}

	/**
	* Sets the create date of this o auth application.
	*
	* @param createDate the create date of this o auth application
	*/
	public void setCreateDate(java.util.Date createDate) {
		_oAuthApplication.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth application.
	*
	* @return the modified date of this o auth application
	*/
	public java.util.Date getModifiedDate() {
		return _oAuthApplication.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth application.
	*
	* @param modifiedDate the modified date of this o auth application
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthApplication.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the owner ID of this o auth application.
	*
	* @return the owner ID of this o auth application
	*/
	public long getOwnerId() {
		return _oAuthApplication.getOwnerId();
	}

	/**
	* Sets the owner ID of this o auth application.
	*
	* @param ownerId the owner ID of this o auth application
	*/
	public void setOwnerId(long ownerId) {
		_oAuthApplication.setOwnerId(ownerId);
	}

	/**
	* Returns the name of this o auth application.
	*
	* @return the name of this o auth application
	*/
	public java.lang.String getName() {
		return _oAuthApplication.getName();
	}

	/**
	* Sets the name of this o auth application.
	*
	* @param name the name of this o auth application
	*/
	public void setName(java.lang.String name) {
		_oAuthApplication.setName(name);
	}

	/**
	* Returns the description of this o auth application.
	*
	* @return the description of this o auth application
	*/
	public java.lang.String getDescription() {
		return _oAuthApplication.getDescription();
	}

	/**
	* Sets the description of this o auth application.
	*
	* @param description the description of this o auth application
	*/
	public void setDescription(java.lang.String description) {
		_oAuthApplication.setDescription(description);
	}

	/**
	* Returns the website of this o auth application.
	*
	* @return the website of this o auth application
	*/
	public java.lang.String getWebsite() {
		return _oAuthApplication.getWebsite();
	}

	/**
	* Sets the website of this o auth application.
	*
	* @param website the website of this o auth application
	*/
	public void setWebsite(java.lang.String website) {
		_oAuthApplication.setWebsite(website);
	}

	/**
	* Returns the consumer key of this o auth application.
	*
	* @return the consumer key of this o auth application
	*/
	public java.lang.String getConsumerKey() {
		return _oAuthApplication.getConsumerKey();
	}

	/**
	* Sets the consumer key of this o auth application.
	*
	* @param consumerKey the consumer key of this o auth application
	*/
	public void setConsumerKey(java.lang.String consumerKey) {
		_oAuthApplication.setConsumerKey(consumerKey);
	}

	/**
	* Returns the consumer secret of this o auth application.
	*
	* @return the consumer secret of this o auth application
	*/
	public java.lang.String getConsumerSecret() {
		return _oAuthApplication.getConsumerSecret();
	}

	/**
	* Sets the consumer secret of this o auth application.
	*
	* @param consumerSecret the consumer secret of this o auth application
	*/
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_oAuthApplication.setConsumerSecret(consumerSecret);
	}

	/**
	* Returns the call back u r l of this o auth application.
	*
	* @return the call back u r l of this o auth application
	*/
	public java.lang.String getCallBackURL() {
		return _oAuthApplication.getCallBackURL();
	}

	/**
	* Sets the call back u r l of this o auth application.
	*
	* @param callBackURL the call back u r l of this o auth application
	*/
	public void setCallBackURL(java.lang.String callBackURL) {
		_oAuthApplication.setCallBackURL(callBackURL);
	}

	/**
	* Returns the access level of this o auth application.
	*
	* @return the access level of this o auth application
	*/
	public int getAccessLevel() {
		return _oAuthApplication.getAccessLevel();
	}

	/**
	* Sets the access level of this o auth application.
	*
	* @param accessLevel the access level of this o auth application
	*/
	public void setAccessLevel(int accessLevel) {
		_oAuthApplication.setAccessLevel(accessLevel);
	}

	public boolean isNew() {
		return _oAuthApplication.isNew();
	}

	public void setNew(boolean n) {
		_oAuthApplication.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthApplication.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthApplication.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthApplication.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthApplication.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthApplication.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthApplication.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthApplication.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthApplicationWrapper((OAuthApplication)_oAuthApplication.clone());
	}

	public int compareTo(OAuthApplication oAuthApplication) {
		return _oAuthApplication.compareTo(oAuthApplication);
	}

	@Override
	public int hashCode() {
		return _oAuthApplication.hashCode();
	}

	public com.liferay.portal.model.CacheModel<OAuthApplication> toCacheModel() {
		return _oAuthApplication.toCacheModel();
	}

	public OAuthApplication toEscapedModel() {
		return new OAuthApplicationWrapper(_oAuthApplication.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthApplication.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthApplication.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthApplication.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthApplication getWrappedOAuthApplication() {
		return _oAuthApplication;
	}

	public OAuthApplication getWrappedModel() {
		return _oAuthApplication;
	}

	public void resetOriginalValues() {
		_oAuthApplication.resetOriginalValues();
	}

	private OAuthApplication _oAuthApplication;
}