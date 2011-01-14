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

package com.liferay.portal.saml.model;

/**
 * <p>
 * This class is a wrapper for {@link SSOSession}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SSOSession
 * @generated
 */
public class SSOSessionWrapper implements SSOSession {
	public SSOSessionWrapper(SSOSession ssoSession) {
		_ssoSession = ssoSession;
	}

	/**
	* Gets the primary key of this s s o session.
	*
	* @return the primary key of this s s o session
	*/
	public long getPrimaryKey() {
		return _ssoSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s s o session
	*
	* @param pk the primary key of this s s o session
	*/
	public void setPrimaryKey(long pk) {
		_ssoSession.setPrimaryKey(pk);
	}

	/**
	* Gets the sso session ID of this s s o session.
	*
	* @return the sso session ID of this s s o session
	*/
	public long getSsoSessionId() {
		return _ssoSession.getSsoSessionId();
	}

	/**
	* Sets the sso session ID of this s s o session.
	*
	* @param ssoSessionId the sso session ID of this s s o session
	*/
	public void setSsoSessionId(long ssoSessionId) {
		_ssoSession.setSsoSessionId(ssoSessionId);
	}

	/**
	* Gets the company ID of this s s o session.
	*
	* @return the company ID of this s s o session
	*/
	public long getCompanyId() {
		return _ssoSession.getCompanyId();
	}

	/**
	* Sets the company ID of this s s o session.
	*
	* @param companyId the company ID of this s s o session
	*/
	public void setCompanyId(long companyId) {
		_ssoSession.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this s s o session.
	*
	* @return the user ID of this s s o session
	*/
	public long getUserId() {
		return _ssoSession.getUserId();
	}

	/**
	* Sets the user ID of this s s o session.
	*
	* @param userId the user ID of this s s o session
	*/
	public void setUserId(long userId) {
		_ssoSession.setUserId(userId);
	}

	/**
	* Gets the user uuid of this s s o session.
	*
	* @return the user uuid of this s s o session
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this s s o session.
	*
	* @param userUuid the user uuid of this s s o session
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_ssoSession.setUserUuid(userUuid);
	}

	/**
	* Gets the create date of this s s o session.
	*
	* @return the create date of this s s o session
	*/
	public java.util.Date getCreateDate() {
		return _ssoSession.getCreateDate();
	}

	/**
	* Sets the create date of this s s o session.
	*
	* @param createDate the create date of this s s o session
	*/
	public void setCreateDate(java.util.Date createDate) {
		_ssoSession.setCreateDate(createDate);
	}

	/**
	* Gets the last active date of this s s o session.
	*
	* @return the last active date of this s s o session
	*/
	public java.util.Date getLastActiveDate() {
		return _ssoSession.getLastActiveDate();
	}

	/**
	* Sets the last active date of this s s o session.
	*
	* @param lastActiveDate the last active date of this s s o session
	*/
	public void setLastActiveDate(java.util.Date lastActiveDate) {
		_ssoSession.setLastActiveDate(lastActiveDate);
	}

	/**
	* Gets the key of this s s o session.
	*
	* @return the key of this s s o session
	*/
	public java.lang.String getKey() {
		return _ssoSession.getKey();
	}

	/**
	* Sets the key of this s s o session.
	*
	* @param key the key of this s s o session
	*/
	public void setKey(java.lang.String key) {
		_ssoSession.setKey(key);
	}

	public boolean isNew() {
		return _ssoSession.isNew();
	}

	public void setNew(boolean n) {
		_ssoSession.setNew(n);
	}

	public boolean isCachedModel() {
		return _ssoSession.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_ssoSession.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _ssoSession.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_ssoSession.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _ssoSession.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _ssoSession.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_ssoSession.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new SSOSessionWrapper((SSOSession)_ssoSession.clone());
	}

	public int compareTo(com.liferay.portal.saml.model.SSOSession ssoSession) {
		return _ssoSession.compareTo(ssoSession);
	}

	public int hashCode() {
		return _ssoSession.hashCode();
	}

	public com.liferay.portal.saml.model.SSOSession toEscapedModel() {
		return new SSOSessionWrapper(_ssoSession.toEscapedModel());
	}

	public java.lang.String toString() {
		return _ssoSession.toString();
	}

	public java.lang.String toXmlString() {
		return _ssoSession.toXmlString();
	}

	public boolean isExpired() {
		return _ssoSession.isExpired();
	}

	public SSOSession getWrappedSSOSession() {
		return _ssoSession;
	}

	private SSOSession _ssoSession;
}