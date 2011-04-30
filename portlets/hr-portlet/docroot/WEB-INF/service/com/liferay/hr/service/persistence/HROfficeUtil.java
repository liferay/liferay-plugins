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

import com.liferay.hr.model.HROffice;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the h r office service. This utility wraps {@link HROfficePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HROfficePersistence
 * @see HROfficePersistenceImpl
 * @generated
 */
public class HROfficeUtil {
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
	public static void clearCache(HROffice hrOffice) {
		getPersistence().clearCache(hrOffice);
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
	public static List<HROffice> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HROffice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HROffice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static HROffice remove(HROffice hrOffice) throws SystemException {
		return getPersistence().remove(hrOffice);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static HROffice update(HROffice hrOffice, boolean merge)
		throws SystemException {
		return getPersistence().update(hrOffice, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static HROffice update(HROffice hrOffice, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(hrOffice, merge, serviceContext);
	}

	/**
	* Caches the h r office in the entity cache if it is enabled.
	*
	* @param hrOffice the h r office to cache
	*/
	public static void cacheResult(com.liferay.hr.model.HROffice hrOffice) {
		getPersistence().cacheResult(hrOffice);
	}

	/**
	* Caches the h r offices in the entity cache if it is enabled.
	*
	* @param hrOffices the h r offices to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hr.model.HROffice> hrOffices) {
		getPersistence().cacheResult(hrOffices);
	}

	/**
	* Creates a new h r office with the primary key. Does not add the h r office to the database.
	*
	* @param hrOfficeId the primary key for the new h r office
	* @return the new h r office
	*/
	public static com.liferay.hr.model.HROffice create(long hrOfficeId) {
		return getPersistence().create(hrOfficeId);
	}

	/**
	* Removes the h r office with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrOfficeId the primary key of the h r office to remove
	* @return the h r office that was removed
	* @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HROffice remove(long hrOfficeId)
		throws com.liferay.hr.NoSuchOfficeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(hrOfficeId);
	}

	public static com.liferay.hr.model.HROffice updateImpl(
		com.liferay.hr.model.HROffice hrOffice, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(hrOffice, merge);
	}

	/**
	* Finds the h r office with the primary key or throws a {@link com.liferay.hr.NoSuchOfficeException} if it could not be found.
	*
	* @param hrOfficeId the primary key of the h r office to find
	* @return the h r office
	* @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HROffice findByPrimaryKey(
		long hrOfficeId)
		throws com.liferay.hr.NoSuchOfficeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(hrOfficeId);
	}

	/**
	* Finds the h r office with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrOfficeId the primary key of the h r office to find
	* @return the h r office, or <code>null</code> if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hr.model.HROffice fetchByPrimaryKey(
		long hrOfficeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(hrOfficeId);
	}

	/**
	* Finds all the h r offices.
	*
	* @return the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the h r offices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r offices to return
	* @param end the upper bound of the range of h r offices to return (not inclusive)
	* @return the range of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the h r offices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r offices to return
	* @param end the upper bound of the range of h r offices to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HROffice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the h r offices from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the h r offices.
	*
	* @return the number of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Gets all the h r holidaies associated with the h r office.
	*
	* @param pk the primary key of the h r office to get the associated h r holidaies for
	* @return the h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRHolidaies(pk);
	}

	/**
	* Gets a range of all the h r holidaies associated with the h r office.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r office to get the associated h r holidaies for
	* @param start the lower bound of the range of h r offices to return
	* @param end the upper bound of the range of h r offices to return (not inclusive)
	* @return the range of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRHolidaies(pk, start, end);
	}

	/**
	* Gets an ordered range of all the h r holidaies associated with the h r office.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r office to get the associated h r holidaies for
	* @param start the lower bound of the range of h r offices to return
	* @param end the upper bound of the range of h r offices to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRHolidaies(pk, start, end, orderByComparator);
	}

	/**
	* Gets the number of h r holidaies associated with the h r office.
	*
	* @param pk the primary key of the h r office to get the number of associated h r holidaies for
	* @return the number of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static int getHRHolidaiesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getHRHolidaiesSize(pk);
	}

	/**
	* Determines if the h r holiday is associated with the h r office.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @return <code>true</code> if the h r holiday is associated with the h r office; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRHoliday(pk, hrHolidayPK);
	}

	/**
	* Determines if the h r office has any h r holidaies associated with it.
	*
	* @param pk the primary key of the h r office to check for associations with h r holidaies
	* @return <code>true</code> if the h r office has any h r holidaies associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsHRHolidaies(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsHRHolidaies(pk);
	}

	/**
	* Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRHoliday(pk, hrHolidayPK);
	}

	/**
	* Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHoliday the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRHoliday(long pk,
		com.liferay.hr.model.HRHoliday hrHoliday)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRHoliday(pk, hrHoliday);
	}

	/**
	* Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPKs the primary keys of the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRHolidaies(pk, hrHolidayPKs);
	}

	/**
	* Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidaies the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static void addHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addHRHolidaies(pk, hrHolidaies);
	}

	/**
	* Clears all associations between the h r office and its h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office to clear the associated h r holidaies from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearHRHolidaies(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearHRHolidaies(pk);
	}

	/**
	* Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRHoliday(pk, hrHolidayPK);
	}

	/**
	* Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHoliday the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRHoliday(long pk,
		com.liferay.hr.model.HRHoliday hrHoliday)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRHoliday(pk, hrHoliday);
	}

	/**
	* Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPKs the primary keys of the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRHolidaies(pk, hrHolidayPKs);
	}

	/**
	* Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidaies the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public static void removeHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeHRHolidaies(pk, hrHolidaies);
	}

	/**
	* Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office to set the associations for
	* @param hrHolidayPKs the primary keys of the h r holidaies to be associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRHolidaies(pk, hrHolidayPKs);
	}

	/**
	* Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office to set the associations for
	* @param hrHolidaies the h r holidaies to be associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public static void setHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setHRHolidaies(pk, hrHolidaies);
	}

	public static HROfficePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (HROfficePersistence)PortletBeanLocatorUtil.locate(com.liferay.hr.service.ClpSerializer.getServletContextName(),
					HROfficePersistence.class.getName());

			ReferenceRegistry.registerReference(HROfficeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(HROfficePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(HROfficeUtil.class, "_persistence");
	}

	private static HROfficePersistence _persistence;
}