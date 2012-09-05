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
 * This class is a wrapper for {@link Application}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Application
 * @generated
 */
public class ApplicationWrapper implements Application,
	ModelWrapper<Application> {
	public ApplicationWrapper(Application application) {
		_application = application;
	}

	public Class<?> getModelClass() {
		return Application.class;
	}

	public String getModelClassName() {
		return Application.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("applicationId", getApplicationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("website", getWebsite());
		attributes.put("callBackURL", getCallBackURL());
		attributes.put("accessLevel", getAccessLevel());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());

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

		String callBackURL = (String)attributes.get("callBackURL");

		if (callBackURL != null) {
			setCallBackURL(callBackURL);
		}

		Integer accessLevel = (Integer)attributes.get("accessLevel");

		if (accessLevel != null) {
			setAccessLevel(accessLevel);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}
	}

	/**
	* Returns the primary key of this application.
	*
	* @return the primary key of this application
	*/
	public long getPrimaryKey() {
		return _application.getPrimaryKey();
	}

	/**
	* Sets the primary key of this application.
	*
	* @param primaryKey the primary key of this application
	*/
	public void setPrimaryKey(long primaryKey) {
		_application.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the application ID of this application.
	*
	* @return the application ID of this application
	*/
	public long getApplicationId() {
		return _application.getApplicationId();
	}

	/**
	* Sets the application ID of this application.
	*
	* @param applicationId the application ID of this application
	*/
	public void setApplicationId(long applicationId) {
		_application.setApplicationId(applicationId);
	}

	/**
	* Returns the company ID of this application.
	*
	* @return the company ID of this application
	*/
	public long getCompanyId() {
		return _application.getCompanyId();
	}

	/**
	* Sets the company ID of this application.
	*
	* @param companyId the company ID of this application
	*/
	public void setCompanyId(long companyId) {
		_application.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this application.
	*
	* @return the user ID of this application
	*/
	public long getUserId() {
		return _application.getUserId();
	}

	/**
	* Sets the user ID of this application.
	*
	* @param userId the user ID of this application
	*/
	public void setUserId(long userId) {
		_application.setUserId(userId);
	}

	/**
	* Returns the user uuid of this application.
	*
	* @return the user uuid of this application
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _application.getUserUuid();
	}

	/**
	* Sets the user uuid of this application.
	*
	* @param userUuid the user uuid of this application
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_application.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this application.
	*
	* @return the user name of this application
	*/
	public java.lang.String getUserName() {
		return _application.getUserName();
	}

	/**
	* Sets the user name of this application.
	*
	* @param userName the user name of this application
	*/
	public void setUserName(java.lang.String userName) {
		_application.setUserName(userName);
	}

	/**
	* Returns the create date of this application.
	*
	* @return the create date of this application
	*/
	public java.util.Date getCreateDate() {
		return _application.getCreateDate();
	}

	/**
	* Sets the create date of this application.
	*
	* @param createDate the create date of this application
	*/
	public void setCreateDate(java.util.Date createDate) {
		_application.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this application.
	*
	* @return the modified date of this application
	*/
	public java.util.Date getModifiedDate() {
		return _application.getModifiedDate();
	}

	/**
	* Sets the modified date of this application.
	*
	* @param modifiedDate the modified date of this application
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_application.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this application.
	*
	* @return the name of this application
	*/
	public java.lang.String getName() {
		return _application.getName();
	}

	/**
	* Sets the name of this application.
	*
	* @param name the name of this application
	*/
	public void setName(java.lang.String name) {
		_application.setName(name);
	}

	/**
	* Returns the description of this application.
	*
	* @return the description of this application
	*/
	public java.lang.String getDescription() {
		return _application.getDescription();
	}

	/**
	* Sets the description of this application.
	*
	* @param description the description of this application
	*/
	public void setDescription(java.lang.String description) {
		_application.setDescription(description);
	}

	/**
	* Returns the website of this application.
	*
	* @return the website of this application
	*/
	public java.lang.String getWebsite() {
		return _application.getWebsite();
	}

	/**
	* Sets the website of this application.
	*
	* @param website the website of this application
	*/
	public void setWebsite(java.lang.String website) {
		_application.setWebsite(website);
	}

	/**
	* Returns the call back u r l of this application.
	*
	* @return the call back u r l of this application
	*/
	public java.lang.String getCallBackURL() {
		return _application.getCallBackURL();
	}

	/**
	* Sets the call back u r l of this application.
	*
	* @param callBackURL the call back u r l of this application
	*/
	public void setCallBackURL(java.lang.String callBackURL) {
		_application.setCallBackURL(callBackURL);
	}

	/**
	* Returns the access level of this application.
	*
	* @return the access level of this application
	*/
	public int getAccessLevel() {
		return _application.getAccessLevel();
	}

	/**
	* Sets the access level of this application.
	*
	* @param accessLevel the access level of this application
	*/
	public void setAccessLevel(int accessLevel) {
		_application.setAccessLevel(accessLevel);
	}

	/**
	* Returns the consumer key of this application.
	*
	* @return the consumer key of this application
	*/
	public java.lang.String getConsumerKey() {
		return _application.getConsumerKey();
	}

	/**
	* Sets the consumer key of this application.
	*
	* @param consumerKey the consumer key of this application
	*/
	public void setConsumerKey(java.lang.String consumerKey) {
		_application.setConsumerKey(consumerKey);
	}

	/**
	* Returns the consumer secret of this application.
	*
	* @return the consumer secret of this application
	*/
	public java.lang.String getConsumerSecret() {
		return _application.getConsumerSecret();
	}

	/**
	* Sets the consumer secret of this application.
	*
	* @param consumerSecret the consumer secret of this application
	*/
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_application.setConsumerSecret(consumerSecret);
	}

	public boolean isNew() {
		return _application.isNew();
	}

	public void setNew(boolean n) {
		_application.setNew(n);
	}

	public boolean isCachedModel() {
		return _application.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_application.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _application.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _application.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_application.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _application.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_application.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ApplicationWrapper((Application)_application.clone());
	}

	public int compareTo(com.liferay.portal.oauth.model.Application application) {
		return _application.compareTo(application);
	}

	@Override
	public int hashCode() {
		return _application.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.oauth.model.Application> toCacheModel() {
		return _application.toCacheModel();
	}

	public com.liferay.portal.oauth.model.Application toEscapedModel() {
		return new ApplicationWrapper(_application.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _application.toString();
	}

	public java.lang.String toXmlString() {
		return _application.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_application.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Application getWrappedApplication() {
		return _application;
	}

	public Application getWrappedModel() {
		return _application;
	}

	public void resetOriginalValues() {
		_application.resetOriginalValues();
	}

	private Application _application;
}