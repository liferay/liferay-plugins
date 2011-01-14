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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the s s o session local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SSOSessionLocalServiceUtil
 * @see com.liferay.portal.saml.service.base.SSOSessionLocalServiceBaseImpl
 * @see com.liferay.portal.saml.service.impl.SSOSessionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SSOSessionLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SSOSessionLocalServiceUtil} to access the s s o session local service. Add custom service methods to {@link com.liferay.portal.saml.service.impl.SSOSessionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the s s o session to the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to add
	* @return the s s o session that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession addSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new s s o session with the primary key. Does not add the s s o session to the database.
	*
	* @param ssoSessionId the primary key for the new s s o session
	* @return the new s s o session
	*/
	public com.liferay.portal.saml.model.SSOSession createSSOSession(
		long ssoSessionId);

	/**
	* Deletes the s s o session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSessionId the primary key of the s s o session to delete
	* @throws PortalException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSSOSession(long ssoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the s s o session from the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the s s o session with the primary key.
	*
	* @param ssoSessionId the primary key of the s s o session to get
	* @return the s s o session
	* @throws PortalException if a s s o session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.saml.model.SSOSession getSSOSession(
		long ssoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.saml.model.SSOSession> getSSOSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Gets the number of s s o sessions.
	*
	* @return the number of s s o sessions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSSOSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the s s o session in the database. Also notifies the appropriate model listeners.
	*
	* @param ssoSession the s s o session to update
	* @return the s s o session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.saml.model.SSOSession updateSSOSession(
		com.liferay.portal.saml.model.SSOSession ssoSession)
		throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.saml.model.SSOSession addSSOSession(
		long companyId, long userId, java.lang.String sessionKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.saml.model.SSOSession getSSOSession(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}