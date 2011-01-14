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
 * This class is a wrapper for {@link SPSession}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SPSession
 * @generated
 */
public class SPSessionWrapper implements SPSession {
	public SPSessionWrapper(SPSession spSession) {
		_spSession = spSession;
	}

	/**
	* Gets the primary key of this s p session.
	*
	* @return the primary key of this s p session
	*/
	public long getPrimaryKey() {
		return _spSession.getPrimaryKey();
	}

	/**
	* Sets the primary key of this s p session
	*
	* @param pk the primary key of this s p session
	*/
	public void setPrimaryKey(long pk) {
		_spSession.setPrimaryKey(pk);
	}

	/**
	* Gets the sp session ID of this s p session.
	*
	* @return the sp session ID of this s p session
	*/
	public long getSpSessionId() {
		return _spSession.getSpSessionId();
	}

	/**
	* Sets the sp session ID of this s p session.
	*
	* @param spSessionId the sp session ID of this s p session
	*/
	public void setSpSessionId(long spSessionId) {
		_spSession.setSpSessionId(spSessionId);
	}

	/**
	* Gets the company ID of this s p session.
	*
	* @return the company ID of this s p session
	*/
	public long getCompanyId() {
		return _spSession.getCompanyId();
	}

	/**
	* Sets the company ID of this s p session.
	*
	* @param companyId the company ID of this s p session
	*/
	public void setCompanyId(long companyId) {
		_spSession.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this s p session.
	*
	* @return the user ID of this s p session
	*/
	public long getUserId() {
		return _spSession.getUserId();
	}

	/**
	* Sets the user ID of this s p session.
	*
	* @param userId the user ID of this s p session
	*/
	public void setUserId(long userId) {
		_spSession.setUserId(userId);
	}

	/**
	* Gets the user uuid of this s p session.
	*
	* @return the user uuid of this s p session
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _spSession.getUserUuid();
	}

	/**
	* Sets the user uuid of this s p session.
	*
	* @param userUuid the user uuid of this s p session
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_spSession.setUserUuid(userUuid);
	}

	/**
	* Gets the create date of this s p session.
	*
	* @return the create date of this s p session
	*/
	public java.util.Date getCreateDate() {
		return _spSession.getCreateDate();
	}

	/**
	* Sets the create date of this s p session.
	*
	* @param createDate the create date of this s p session
	*/
	public void setCreateDate(java.util.Date createDate) {
		_spSession.setCreateDate(createDate);
	}

	/**
	* Gets the sso session ID of this s p session.
	*
	* @return the sso session ID of this s p session
	*/
	public long getSsoSessionId() {
		return _spSession.getSsoSessionId();
	}

	/**
	* Sets the sso session ID of this s p session.
	*
	* @param ssoSessionId the sso session ID of this s p session
	*/
	public void setSsoSessionId(long ssoSessionId) {
		_spSession.setSsoSessionId(ssoSessionId);
	}

	/**
	* Gets the issuer of this s p session.
	*
	* @return the issuer of this s p session
	*/
	public java.lang.String getIssuer() {
		return _spSession.getIssuer();
	}

	/**
	* Sets the issuer of this s p session.
	*
	* @param issuer the issuer of this s p session
	*/
	public void setIssuer(java.lang.String issuer) {
		_spSession.setIssuer(issuer);
	}

	public boolean isNew() {
		return _spSession.isNew();
	}

	public void setNew(boolean n) {
		_spSession.setNew(n);
	}

	public boolean isCachedModel() {
		return _spSession.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_spSession.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _spSession.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_spSession.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _spSession.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _spSession.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_spSession.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new SPSessionWrapper((SPSession)_spSession.clone());
	}

	public int compareTo(com.liferay.portal.saml.model.SPSession spSession) {
		return _spSession.compareTo(spSession);
	}

	public int hashCode() {
		return _spSession.hashCode();
	}

	public com.liferay.portal.saml.model.SPSession toEscapedModel() {
		return new SPSessionWrapper(_spSession.toEscapedModel());
	}

	public java.lang.String toString() {
		return _spSession.toString();
	}

	public java.lang.String toXmlString() {
		return _spSession.toXmlString();
	}

	public SPSession getWrappedSPSession() {
		return _spSession;
	}

	private SPSession _spSession;
}