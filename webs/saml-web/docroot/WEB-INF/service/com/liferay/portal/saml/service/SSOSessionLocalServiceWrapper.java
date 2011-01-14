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

package com.liferay.portal.saml.service;

/**
 * <p>
 * This class is a wrapper for {@link SSOSessionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SSOSessionLocalService
 * @generated
 */
public class SSOSessionLocalServiceWrapper implements SSOSessionLocalService {
	public SSOSessionLocalServiceWrapper(
		SSOSessionLocalService ssoSessionLocalService) {
		_ssoSessionLocalService = ssoSessionLocalService;
	}

	/**
	* Adds the s s o session to the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to add
	* @return the s s o session that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession addSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.addSSOSession(ssoSession);
	}

	/**
	* Creates a new s s o session with the primary key. Does not add the s s o session to the database.
	*
	* @param ssoSessionId the primary key for the new s s o session
	* @return the new s s o session
	*/
	public com.liferay.portal.saml.model.SSOSession createSSOSession(
		long ssoSessionId) {
		return _ssoSessionLocalService.createSSOSession(ssoSessionId);
	}

	/**
	* Deletes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSessionId the primary key of the s s o session to delete
	* @throws PortalException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSSOSession(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ssoSessionLocalService.deleteSSOSession(ssoSessionId);
	}

	/**
	* Deletes the s s o session from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		_ssoSessionLocalService.deleteSSOSession(ssoSession);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the s s o session with the primary key.
	*
	* @param ssoSessionId the primary key of the s s o session to get
	* @return the s s o session
	* @throws PortalException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession getSSOSession(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.getSSOSession(ssoSessionId);
	}

	/**
	* Gets a range of all the s s o sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s s o sessions to return
	* @param end the upper bound of the range of s s o sessions to return (not inclusive)
	* @return the range of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.saml.model.SSOSession> getSSOSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.getSSOSessions(start, end);
	}

	/**
	* Gets the number of s s o sessions.
	*
	* @return the number of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	public int getSSOSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.getSSOSessionsCount();
	}

	/**
	* Updates the s s o session in the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to update
	* @return the s s o session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession updateSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.updateSSOSession(ssoSession);
	}

	/**
	* Updates the s s o session in the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to update
	* @param merge whether to merge the s s o session with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the s s o session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession updateSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.updateSSOSession(ssoSession, merge);
	}

	public com.liferay.portal.saml.model.SSOSession addSSOSession(
		long companyId, long userId, java.lang.String sessionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.addSSOSession(companyId, userId,
			sessionKey);
	}

	public com.liferay.portal.saml.model.SSOSession getSSOSession(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ssoSessionLocalService.getSSOSession(key);
	}

	public SSOSessionLocalService getWrappedSSOSessionLocalService() {
		return _ssoSessionLocalService;
	}

	public void setWrappedSSOSessionLocalService(
		SSOSessionLocalService ssoSessionLocalService) {
		_ssoSessionLocalService = ssoSessionLocalService;
	}

	private SSOSessionLocalService _ssoSessionLocalService;
}