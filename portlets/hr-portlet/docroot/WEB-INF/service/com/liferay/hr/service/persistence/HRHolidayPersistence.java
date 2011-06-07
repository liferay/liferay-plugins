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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r holiday service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HRHolidayPersistenceImpl
 * @see HRHolidayUtil
 * @generated
 */
public interface HRHolidayPersistence extends BasePersistence<HRHoliday> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HRHolidayUtil} to access the h r holiday persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r holiday in the entity cache if it is enabled.
	*
	* @param hrHoliday the h r holiday
	*/
	public void cacheResult(com.liferay.hr.model.HRHoliday hrHoliday);

	/**
	* Caches the h r holidaies in the entity cache if it is enabled.
	*
	* @param hrHolidaies the h r holidaies
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies);

	/**
	* Creates a new h r holiday with the primary key. Does not add the h r holiday to the database.
	*
	* @param hrHolidayId the primary key for the new h r holiday
	* @return the new h r holiday
	*/
	public com.liferay.hr.model.HRHoliday create(long hrHolidayId);

	/**
	* Removes the h r holiday with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrHolidayId the primary key of the h r holiday
	* @return the h r holiday that was removed
	* @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRHoliday remove(long hrHolidayId)
		throws com.liferay.hr.NoSuchHolidayException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HRHoliday updateImpl(
		com.liferay.hr.model.HRHoliday hrHoliday, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r holiday with the primary key or throws a {@link com.liferay.hr.NoSuchHolidayException} if it could not be found.
	*
	* @param hrHolidayId the primary key of the h r holiday
	* @return the h r holiday
	* @throws com.liferay.hr.NoSuchHolidayException if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRHoliday findByPrimaryKey(long hrHolidayId)
		throws com.liferay.hr.NoSuchHolidayException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r holiday with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrHolidayId the primary key of the h r holiday
	* @return the h r holiday, or <code>null</code> if a h r holiday with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRHoliday fetchByPrimaryKey(long hrHolidayId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r holidaies.
	*
	* @return the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r holidaies
	* @param end the upper bound of the range of h r holidaies (not inclusive)
	* @return the range of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r holidaies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r holidaies
	* @param end the upper bound of the range of h r holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r holidaies from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r holidaies.
	*
	* @return the number of h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r offices associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday
	* @return the h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> getHROffices(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r offices associated with the h r holiday.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r holiday
	* @param start the lower bound of the range of h r holidaies
	* @param end the upper bound of the range of h r holidaies (not inclusive)
	* @return the range of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> getHROffices(long pk,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r offices associated with the h r holiday.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r holiday
	* @param start the lower bound of the range of h r holidaies
	* @param end the upper bound of the range of h r holidaies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> getHROffices(long pk,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r offices associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday
	* @return the number of h r offices associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public int getHROfficesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r office is associated with the h r holiday.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @return <code>true</code> if the h r office is associated with the h r holiday; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r holiday has any h r offices associated with it.
	*
	* @param pk the primary key of the h r holiday to check for associations with h r offices
	* @return <code>true</code> if the h r holiday has any h r offices associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHROffices(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void addHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffice the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void addHROffice(long pk, com.liferay.hr.model.HROffice hrOffice)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePKs the primary keys of the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public void addHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffices the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public void addHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the h r holiday and its h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday to clear the associated h r offices from
	* @throws SystemException if a system exception occurred
	*/
	public void clearHROffices(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePK the primary key of the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void removeHROffice(long pk, long hrOfficePK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r holiday and the h r office. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffice the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void removeHROffice(long pk, com.liferay.hr.model.HROffice hrOffice)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePKs the primary keys of the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public void removeHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r holiday and the h r offices. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffices the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public void removeHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOfficePKs the primary keys of the h r offices to be associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void setHROffices(long pk, long[] hrOfficePKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r offices associated with the h r holiday, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r holiday
	* @param hrOffices the h r offices to be associated with the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void setHROffices(long pk,
		java.util.List<com.liferay.hr.model.HROffice> hrOffices)
		throws com.liferay.portal.kernel.exception.SystemException;

	public HRHoliday remove(HRHoliday hrHoliday) throws SystemException;
}