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

package com.liferay.akismet.service.persistence;

import com.liferay.akismet.model.AkismetData;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the akismet data service. This utility wraps {@link AkismetDataPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataPersistence
 * @see AkismetDataPersistenceImpl
 * @generated
 */
public class AkismetDataUtil {
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
	public static void clearCache(AkismetData akismetData) {
		getPersistence().clearCache(akismetData);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AkismetData> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AkismetData> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AkismetData> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static AkismetData update(AkismetData akismetData)
		throws SystemException {
		return getPersistence().update(akismetData);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static AkismetData update(AkismetData akismetData,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(akismetData, serviceContext);
	}

	/**
	* Returns all the akismet datas where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findByLtModifiedDate(
		java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns a range of all the akismet datas where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of matching akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtModifiedDate(modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the akismet datas where modifiedDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findByLtModifiedDate(
		java.util.Date modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate(modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet data
	* @throws com.liferay.akismet.NoSuchDataException if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData findByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching akismet data, or <code>null</code> if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData fetchByLtModifiedDate_First(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_First(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet data
	* @throws com.liferay.akismet.NoSuchDataException if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData findByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching akismet data, or <code>null</code> if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData fetchByLtModifiedDate_Last(
		java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtModifiedDate_Last(modifiedDate, orderByComparator);
	}

	/**
	* Returns the akismet datas before and after the current akismet data in the ordered set where modifiedDate &lt; &#63;.
	*
	* @param akismetDataId the primary key of the current akismet data
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next akismet data
	* @throws com.liferay.akismet.NoSuchDataException if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData[] findByLtModifiedDate_PrevAndNext(
		long akismetDataId, java.util.Date modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtModifiedDate_PrevAndNext(akismetDataId,
			modifiedDate, orderByComparator);
	}

	/**
	* Removes all the akismet datas where modifiedDate &lt; &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the number of akismet datas where modifiedDate &lt; &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLtModifiedDate(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLtModifiedDate(modifiedDate);
	}

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.akismet.NoSuchDataException} if it could not be found.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching akismet data
	* @throws com.liferay.akismet.NoSuchDataException if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData findByC_C(
		long classNameId, long classPK)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData fetchByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(classNameId, classPK);
	}

	/**
	* Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData fetchByC_C(
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C(classNameId, classPK, retrieveFromCache);
	}

	/**
	* Removes the akismet data where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the akismet data that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData removeByC_C(
		long classNameId, long classPK)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of akismet datas where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Caches the akismet data in the entity cache if it is enabled.
	*
	* @param akismetData the akismet data
	*/
	public static void cacheResult(
		com.liferay.akismet.model.AkismetData akismetData) {
		getPersistence().cacheResult(akismetData);
	}

	/**
	* Caches the akismet datas in the entity cache if it is enabled.
	*
	* @param akismetDatas the akismet datas
	*/
	public static void cacheResult(
		java.util.List<com.liferay.akismet.model.AkismetData> akismetDatas) {
		getPersistence().cacheResult(akismetDatas);
	}

	/**
	* Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	*
	* @param akismetDataId the primary key for the new akismet data
	* @return the new akismet data
	*/
	public static com.liferay.akismet.model.AkismetData create(
		long akismetDataId) {
		return getPersistence().create(akismetDataId);
	}

	/**
	* Removes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data that was removed
	* @throws com.liferay.akismet.NoSuchDataException if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData remove(
		long akismetDataId)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(akismetDataId);
	}

	public static com.liferay.akismet.model.AkismetData updateImpl(
		com.liferay.akismet.model.AkismetData akismetData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(akismetData);
	}

	/**
	* Returns the akismet data with the primary key or throws a {@link com.liferay.akismet.NoSuchDataException} if it could not be found.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data
	* @throws com.liferay.akismet.NoSuchDataException if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData findByPrimaryKey(
		long akismetDataId)
		throws com.liferay.akismet.NoSuchDataException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(akismetDataId);
	}

	/**
	* Returns the akismet data with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data, or <code>null</code> if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.akismet.model.AkismetData fetchByPrimaryKey(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(akismetDataId);
	}

	/**
	* Returns all the akismet datas.
	*
	* @return the akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the akismet datas from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of akismet datas.
	*
	* @return the number of akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AkismetDataPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AkismetDataPersistence)PortletBeanLocatorUtil.locate(com.liferay.akismet.service.ClpSerializer.getServletContextName(),
					AkismetDataPersistence.class.getName());

			ReferenceRegistry.registerReference(AkismetDataUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(AkismetDataPersistence persistence) {
	}

	private static AkismetDataPersistence _persistence;
}