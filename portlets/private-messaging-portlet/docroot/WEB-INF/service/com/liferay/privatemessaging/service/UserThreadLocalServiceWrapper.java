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

package com.liferay.privatemessaging.service;

/**
 * <p>
 * This class is a wrapper for {@link UserThreadLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       UserThreadLocalService
 * @generated
 */
public class UserThreadLocalServiceWrapper implements UserThreadLocalService {
	public UserThreadLocalServiceWrapper(
		UserThreadLocalService userThreadLocalService) {
		_userThreadLocalService = userThreadLocalService;
	}

	/**
	* Adds the user thread to the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread to add
	* @return the user thread that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread addUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.addUserThread(userThread);
	}

	/**
	* Creates a new user thread with the primary key. Does not add the user thread to the database.
	*
	* @param userThreadId the primary key for the new user thread
	* @return the new user thread
	*/
	public com.liferay.privatemessaging.model.UserThread createUserThread(
		long userThreadId) {
		return _userThreadLocalService.createUserThread(userThreadId);
	}

	/**
	* Deletes the user thread with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userThreadId the primary key of the user thread to delete
	* @throws PortalException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserThread(long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_userThreadLocalService.deleteUserThread(userThreadId);
	}

	/**
	* Deletes the user thread from the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		_userThreadLocalService.deleteUserThread(userThread);
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery);
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _userThreadLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _userThreadLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the user thread with the primary key.
	*
	* @param userThreadId the primary key of the user thread to get
	* @return the user thread
	* @throws PortalException if a user thread with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread getUserThread(
		long userThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.getUserThread(userThreadId);
	}

	/**
	* Gets a range of all the user threads.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user threads to return
	* @param end the upper bound of the range of user threads to return (not inclusive)
	* @return the range of user threads
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserThreads(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.getUserThreads(start, end);
	}

	/**
	* Gets the number of user threads.
	*
	* @return the number of user threads
	* @throws SystemException if a system exception occurred
	*/
	public int getUserThreadsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.getUserThreadsCount();
	}

	/**
	* Updates the user thread in the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread to update
	* @return the user thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread updateUserThread(
		com.liferay.privatemessaging.model.UserThread userThread)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.updateUserThread(userThread);
	}

	/**
	* Updates the user thread in the database. Also notifies the appropriate model listeners.
	*
	* @param userThread the user thread to update
	* @param merge whether to merge the user thread with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the user thread that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.privatemessaging.model.UserThread updateUserThread(
		com.liferay.privatemessaging.model.UserThread userThread, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _userThreadLocalService.updateUserThread(userThread, merge);
	}

	public UserThreadLocalService getWrappedUserThreadLocalService() {
		return _userThreadLocalService;
	}

	private UserThreadLocalService _userThreadLocalService;
}