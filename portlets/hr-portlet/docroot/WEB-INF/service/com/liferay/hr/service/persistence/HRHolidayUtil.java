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

package com.liferay.hr.service.persistence;

import com.liferay.hr.model.HRHoliday;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r holiday service. This utility wraps {@link HRHolidayPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRHolidayPersistence
 * @see HRHolidayPersistenceImpl
 * @generated
 */
public class HRHolidayUtil {
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
	public static void clearCache(HRHoliday hrHoliday) {
		getPersistence().clearCache(hrHoliday);
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
	public static List<HRHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HRHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HRHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HRHoliday remove(HRHoliday hrHoliday)
		throws SystemException {
		return getPersistence().remove(hrHoliday);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HRHoliday update(HRHoliday hrHoliday, boolean merge)
		throws SystemException {
		return getPersistence().update(hrHoliday, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HRHoliday update(HRHoliday hrHoliday, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrHoliday, merge, serviceContext);
	}

	/**
	* Caches the h r holiday in the entity cache if it is enabled.
	*
	* @param hrHoliday the h r holiday to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HRHoliday hrHoliday) {
		getPersistence().cacheResult(hrHoliday);
	}

	/**
	* Caches the h r holidaies in the entity cache if it is enabled.
	*
	* @param hrHolidaies the h r holidaies to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies) {
		getPersistence().cacheResult(hrHolidaies);
	}

	/**
	* Creates a new h r holiday with the primary key. Does not add the h r holiday to the database.
	*
	* @param hrHolidayId the primary key for the new h r holiday
	* @return the new h r holiday
	*/
	public static com.liferay.hr.model.HRHoliday create(long hrHolidayId) {
		return getPersistence().create(hrHolidayId);
	}

	/**
	* Removes the h r holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrHolidayId the primary key of the h r holiday to remove
	* @return the h r holiday that was removed
	* @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRHoliday remove(long hrHolidayId)
		throws com.liferay.hr.NoSuchHolidayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrHolidayId);
	}

	public static com.liferay.hr.model.HRHoliday updateImpl(
		com.liferay.hr.model.HRHoliday hrHoliday, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrHoliday, merge);
	}

	/**
	* Finds the h r holiday with the primary key or throws a {@link com.liferay.hr.NoSuchHolidayException} if it could not be found.
	*
	* @param hrHolidayId the primary key of the h r holiday to find
	* @return the h r holiday
	* @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRHoliday findByPrimaryKey(
		long hrHolidayId)
		throws com.liferay.hr.NoSuchHolidayException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrHolidayId);
	}

	/**
	* Finds the h r holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrHolidayId the primary key of the h r holiday to find
	* @return the h r holiday, or <code>null</code> if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HRHoliday fetchByPrimaryKey(
		long hrHolidayId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrHolidayId);
	}

	/**
	* Finds all the h r holidaies.
	*
	* @return the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r holidaies to return
	* @param end the upper bound of the range of h r holidaies to return (not inclusive)
	* @return the range of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r holidaies to return
	* @param end the upper bound of the range of h r holidaies to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r holidaies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r holidaies.
	*
	* @return the number of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Gets all the h r offices associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday to get the associated h r offices for
	* @return the h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> getHROffices(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHROffices(pk);
	}

	/**
	* Gets a range of all the h r offices associated with the h r holiday.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r holiday to get the associated h r offices for
	* @param start the lower bound of the range of h r holidaies to return
	* @param end the upper bound of the range of h r holidaies to return (not inclusive)
	* @return the range of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> getHROffices(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHROffices(pk, start, end);
	}

	/**
	* Gets an ordered range of all the h r offices associated with the h r holiday.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r holiday to get the associated h r offices for
	* @param start the lower bound of the range of h r holidaies to return
	* @param end the upper bound of the range of h r holidaies to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> getHROffices(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHROffices(pk, start, end, orderByComparator);
	}

	/**
	* Gets the number of h r offices associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday to get the number of associated h r offices for
	* @return the number of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static int getHROfficesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHROfficesSize(pk);
	}

	/**
	* Determines if the h r office is associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @return <code>true</code> if the h r office is associated with the h r holiday; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHROffice(pk, hrOfficePK);
	}

	/**
	* Determines if the h r holiday has any h r offices associated with it.
	*
	* @param pk the primary key of the h r holiday to check for associations with h r offices
	* @return <code>true</code> if the h r holiday has any h r offices associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHROffices(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHROffices(pk);
	}

	/**
	* Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void addHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHROffice(pk, hrOfficePK);
	}

	/**
	* Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffice the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void addHROffice(long pk,
		com.liferay.hr.model.HROffice hrOffice)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHROffice(pk, hrOffice);
	}

	/**
	* Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePKs the primary keys of the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static void addHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHROffices(pk, hrOfficePKs);
	}

	/**
	* Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffices the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static void addHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHROffices(pk, hrOffices);
	}

	/**
	* Clears all associations between the h r holiday and its h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday to clear the associated h r offices from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearHROffices(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearHROffices(pk);
	}

	/**
	* Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHROffice(pk, hrOfficePK);
	}

	/**
	* Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffice the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHROffice(long pk,
		com.liferay.hr.model.HROffice hrOffice)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHROffice(pk, hrOffice);
	}

	/**
	* Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePKs the primary keys of the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHROffices(pk, hrOfficePKs);
	}

	/**
	* Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffices the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHROffices(pk, hrOffices);
	}

	/**
	* Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday to set the associations for
	* @param hrOfficePKs the primary keys of the h r offices to be associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void setHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHROffices(pk, hrOfficePKs);
	}

	/**
	* Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday to set the associations for
	* @param hrOffices the h r offices to be associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void setHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHROffices(pk, hrOffices);
	}

	public static HRHolidayPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HRHolidayPersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HRHolidayPersistence.class.getName());

			ReferenceRegistry.registerReference(HRHolidayUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HRHolidayPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HRHolidayUtil.class, "_persistence");
	}

	private static HRHolidayPersistence _persistence;
}