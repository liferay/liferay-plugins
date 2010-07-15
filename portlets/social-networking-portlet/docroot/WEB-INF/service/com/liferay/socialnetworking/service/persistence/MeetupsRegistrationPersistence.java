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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialnetworking.model.MeetupsRegistration;

/**
 * @author    Brian Wing Shun Chan
 * @see       MeetupsRegistrationPersistenceImpl
 * @see       MeetupsRegistrationUtil
 * @generated
 */
public interface MeetupsRegistrationPersistence extends BasePersistence<MeetupsRegistration> {
	public void cacheResult(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration);

	public void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> meetupsRegistrations);

	public com.liferay.socialnetworking.model.MeetupsRegistration create(
		long meetupsRegistrationId);

	public com.liferay.socialnetworking.model.MeetupsRegistration remove(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public com.liferay.socialnetworking.model.MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException;

	public void removeByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}