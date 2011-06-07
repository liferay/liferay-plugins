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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the h r office service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Wesley Gong
 * @see HROfficePersistenceImpl
 * @see HROfficeUtil
 * @generated
 */
public interface HROfficePersistence extends BasePersistence<HROffice> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HROfficeUtil} to access the h r office persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the h r office in the entity cache if it is enabled.
	*
	* @param hrOffice the h r office
	*/
	public void cacheResult(com.liferay.hr.model.HROffice hrOffice);

	/**
	* Caches the h r offices in the entity cache if it is enabled.
	*
	* @param hrOffices the h r offices
	*/
	public void cacheResult(
		java.util.List<com.liferay.hr.model.HROffice> hrOffices);

	/**
	* Creates a new h r office with the primary key. Does not add the h r office to the database.
	*
	* @param hrOfficeId the primary key for the new h r office
	* @return the new h r office
	*/
	public com.liferay.hr.model.HROffice create(long hrOfficeId);

	/**
	* Removes the h r office with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrOfficeId the primary key of the h r office
	* @return the h r office that was removed
	* @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HROffice remove(long hrOfficeId)
		throws com.liferay.hr.NoSuchOfficeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hr.model.HROffice updateImpl(
		com.liferay.hr.model.HROffice hrOffice, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r office with the primary key or throws a {@link com.liferay.hr.NoSuchOfficeException} if it could not be found.
	*
	* @param hrOfficeId the primary key of the h r office
	* @return the h r office
	* @throws com.liferay.hr.NoSuchOfficeException if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HROffice findByPrimaryKey(long hrOfficeId)
		throws com.liferay.hr.NoSuchOfficeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the h r office with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param hrOfficeId the primary key of the h r office
	* @return the h r office, or <code>null</code> if a h r office with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HROffice fetchByPrimaryKey(long hrOfficeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r offices.
	*
	* @return the h r offices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r offices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r offices
	* @param end the upper bound of the range of h r offices (not inclusive)
	* @return the range of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r offices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r offices
	* @param end the upper bound of the range of h r offices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HROffice> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the h r offices from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r offices.
	*
	* @return the number of h r offices
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the h r holidaies associated with the h r office.
	*
	* @param pk the primary key of the h r office
	* @return the h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the h r holidaies associated with the h r office.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r office
	* @param start the lower bound of the range of h r offices
	* @param end the upper bound of the range of h r offices (not inclusive)
	* @return the range of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the h r holidaies associated with the h r office.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the h r office
	* @param start the lower bound of the range of h r offices
	* @param end the upper bound of the range of h r offices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRHoliday> getHRHolidaies(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of h r holidaies associated with the h r office.
	*
	* @param pk the primary key of the h r office
	* @return the number of h r holidaies associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public int getHRHolidaiesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r holiday is associated with the h r office.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @return <code>true</code> if the h r holiday is associated with the h r office; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Determines if the h r office has any h r holidaies associated with it.
	*
	* @param pk the primary key of the h r office to check for associations with h r holidaies
	* @return <code>true</code> if the h r office has any h r holidaies associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsHRHolidaies(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void addHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHoliday the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void addHRHoliday(long pk, com.liferay.hr.model.HRHoliday hrHoliday)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPKs the primary keys of the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public void addHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidaies the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public void addHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the h r office and its h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office to clear the associated h r holidaies from
	* @throws SystemException if a system exception occurred
	*/
	public void clearHRHolidaies(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPK the primary key of the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRHoliday(long pk, long hrHolidayPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r office and the h r holiday. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHoliday the h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRHoliday(long pk,
		com.liferay.hr.model.HRHoliday hrHoliday)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPKs the primary keys of the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the h r office and the h r holidaies. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidaies the h r holidaies
	* @throws SystemException if a system exception occurred
	*/
	public void removeHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidayPKs the primary keys of the h r holidaies to be associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void setHRHolidaies(long pk, long[] hrHolidayPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the h r holidaies associated with the h r office, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the h r office
	* @param hrHolidaies the h r holidaies to be associated with the h r office
	* @throws SystemException if a system exception occurred
	*/
	public void setHRHolidaies(long pk,
		java.util.List<com.liferay.hr.model.HRHoliday> hrHolidaies)
		throws com.liferay.portal.kernel.exception.SystemException;

	public HROffice remove(HROffice hrOffice) throws SystemException;
}