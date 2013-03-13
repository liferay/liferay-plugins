/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.so.activities.model.SocialActivitySet;

import java.util.List;

/**
 * The persistence utility for the social activity set service. This utility wraps {@link SocialActivitySetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetPersistence
 * @see SocialActivitySetPersistenceImpl
 * @generated
 */
public class SocialActivitySetUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(SocialActivitySet socialActivitySet) {
		getPersistence().clearCache(socialActivitySet);
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
	public static List<SocialActivitySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SocialActivitySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SocialActivitySet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SocialActivitySet update(
		SocialActivitySet socialActivitySet, boolean merge)
		throws SystemException {
		return getPersistence().update(socialActivitySet, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SocialActivitySet update(
		SocialActivitySet socialActivitySet, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(socialActivitySet, merge, serviceContext);
	}

	/**
	* Caches the social activity set in the entity cache if it is enabled.
	*
	* @param socialActivitySet the social activity set
	*/
	public static void cacheResult(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet) {
		getPersistence().cacheResult(socialActivitySet);
	}

	/**
	* Caches the social activity sets in the entity cache if it is enabled.
	*
	* @param socialActivitySets the social activity sets
	*/
	public static void cacheResult(
		java.util.List<com.liferay.so.activities.model.SocialActivitySet> socialActivitySets) {
		getPersistence().cacheResult(socialActivitySets);
	}

	/**
	* Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	*
	* @param activitySetId the primary key for the new social activity set
	* @return the new social activity set
	*/
	public static com.liferay.so.activities.model.SocialActivitySet create(
		long activitySetId) {
		return getPersistence().create(activitySetId);
	}

	/**
	* Removes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set that was removed
	* @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet remove(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivitySetException {
		return getPersistence().remove(activitySetId);
	}

	public static com.liferay.so.activities.model.SocialActivitySet updateImpl(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(socialActivitySet, merge);
	}

	/**
	* Returns the social activity set with the primary key or throws a {@link com.liferay.so.activities.NoSuchActivitySetException} if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set
	* @throws com.liferay.so.activities.NoSuchActivitySetException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet findByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.activities.NoSuchActivitySetException {
		return getPersistence().findByPrimaryKey(activitySetId);
	}

	/**
	* Returns the social activity set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set, or <code>null</code> if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet fetchByPrimaryKey(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(activitySetId);
	}

	/**
	* Returns all the social activity sets.
	*
	* @return the social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the social activity sets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of social activity sets.
	*
	* @return the number of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SocialActivitySetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SocialActivitySetPersistence)PortletBeanLocatorUtil.locate(com.liferay.so.activities.service.ClpSerializer.getServletContextName(),
					SocialActivitySetPersistence.class.getName());

			ReferenceRegistry.registerReference(SocialActivitySetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SocialActivitySetPersistence persistence) {
	}

	private static SocialActivitySetPersistence _persistence;
}