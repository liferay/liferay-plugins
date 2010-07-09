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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialnetworking.model.MeetupsRegistration;

import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsRegistrationPersistence
 * @see       MeetupsRegistrationPersistenceImpl
 * @generated
 */
public class MeetupsRegistrationUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(MeetupsRegistration meetupsRegistration) {
		getPersistence().clearCache(meetupsRegistration);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetupsRegistration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static MeetupsRegistration remove(
		MeetupsRegistration meetupsRegistration) throws SystemException {
		return getPersistence().remove(meetupsRegistration);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration, boolean merge)
		throws SystemException {
		return getPersistence().update(meetupsRegistration, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static MeetupsRegistration update(
		MeetupsRegistration meetupsRegistration, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(meetupsRegistration, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration) {
		getPersistence().cacheResult(meetupsRegistration);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> meetupsRegistrations) {
		getPersistence().cacheResult(meetupsRegistrations);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration create(
		long meetupsRegistrationId) {
		return getPersistence().create(meetupsRegistrationId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration remove(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().remove(meetupsRegistrationId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration updateImpl(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(meetupsRegistration, merge);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().findByPrimaryKey(meetupsRegistrationId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupsRegistrationId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId, start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetupsEntryId(meetupsEntryId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_First(meetupsEntryId, orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_Last(meetupsEntryId, orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence().findByU_ME(userId, meetupsEntryId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_ME(userId, meetupsEntryId);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_ME(userId, meetupsEntryId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status, start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByME_S(meetupsEntryId, status, start, end,
			orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_First(meetupsEntryId, status, orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration findByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_Last(meetupsEntryId, status, orderByComparator);
	}

	public static com.liferay.socialnetworking.model.MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, status, orderByComparator);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMeetupsEntryId(meetupsEntryId);
	}

	public static void removeByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsRegistrationException {
		getPersistence().removeByU_ME(userId, meetupsEntryId);
	}

	public static void removeByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByME_S(meetupsEntryId, status);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMeetupsEntryId(meetupsEntryId);
	}

	public static int countByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_ME(userId, meetupsEntryId);
	}

	public static int countByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByME_S(meetupsEntryId, status);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupsRegistrationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MeetupsRegistrationPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					MeetupsRegistrationPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(MeetupsRegistrationPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupsRegistrationPersistence _persistence;
}