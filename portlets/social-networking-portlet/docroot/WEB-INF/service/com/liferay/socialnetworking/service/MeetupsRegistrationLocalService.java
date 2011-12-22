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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the meetups registration local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationLocalServiceUtil
 * @see com.liferay.socialnetworking.service.base.MeetupsRegistrationLocalServiceBaseImpl
 * @see com.liferay.socialnetworking.service.impl.MeetupsRegistrationLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface MeetupsRegistrationLocalService
	extends PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupsRegistrationLocalServiceUtil} to access the meetups registration local service. Add custom service methods to {@link com.liferay.socialnetworking.service.impl.MeetupsRegistrationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the meetups registration to the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration
	* @return the meetups registration that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration addMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new meetups registration with the primary key. Does not add the meetups registration to the database.
	*
	* @param meetupsRegistrationId the primary key for the new meetups registration
	* @return the new meetups registration
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId);

	/**
	* Deletes the meetups registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @throws PortalException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the meetups registration from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
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
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
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
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.socialnetworking.model.MeetupsRegistration fetchMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups registration with the primary key.
	*
	* @param meetupsRegistrationId the primary key of the meetups registration
	* @return the meetups registration
	* @throws PortalException if a meetups registration with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups registrations
	* @param end the upper bound of the range of meetups registrations (not inclusive)
	* @return the range of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups registrations.
	*
	* @return the number of meetups registrations
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMeetupsRegistrationsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the meetups registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration
	* @return the meetups registration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the meetups registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param meetupsRegistration the meetups registration
	* @param merge whether to merge the meetups registration with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the meetups registration that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		long userId, long meetupsEntryId, int status, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}