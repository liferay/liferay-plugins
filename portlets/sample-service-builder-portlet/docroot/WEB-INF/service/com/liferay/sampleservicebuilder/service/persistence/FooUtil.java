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

package com.liferay.sampleservicebuilder.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.sampleservicebuilder.model.Foo;

import java.util.List;

/**
 * The persistence utility for the foo service. This utility wraps {@link FooPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooPersistence
 * @see FooPersistenceImpl
 * @generated
 */
public class FooUtil {
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
	public static void clearCache(Foo foo) {
		getPersistence().clearCache(foo);
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
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Foo> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Foo update(Foo foo, boolean merge) throws SystemException {
		return getPersistence().update(foo, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Foo update(Foo foo, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(foo, merge, serviceContext);
	}

	/**
	* Caches the foo in the entity cache if it is enabled.
	*
	* @param foo the foo
	*/
	public static void cacheResult(
		com.liferay.sampleservicebuilder.model.Foo foo) {
		getPersistence().cacheResult(foo);
	}

	/**
	* Caches the foos in the entity cache if it is enabled.
	*
	* @param foos the foos
	*/
	public static void cacheResult(
		java.util.List<com.liferay.sampleservicebuilder.model.Foo> foos) {
		getPersistence().cacheResult(foos);
	}

	/**
	* Creates a new foo with the primary key. Does not add the foo to the database.
	*
	* @param fooId the primary key for the new foo
	* @return the new foo
	*/
	public static com.liferay.sampleservicebuilder.model.Foo create(long fooId) {
		return getPersistence().create(fooId);
	}

	/**
	* Removes the foo with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param fooId the primary key of the foo
	* @return the foo that was removed
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo remove(long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().remove(fooId);
	}

	public static com.liferay.sampleservicebuilder.model.Foo updateImpl(
		com.liferay.sampleservicebuilder.model.Foo foo, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(foo, merge);
	}

	/**
	* Returns the foo with the primary key or throws a {@link com.liferay.sampleservicebuilder.NoSuchFooException} if it could not be found.
	*
	* @param fooId the primary key of the foo
	* @return the foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByPrimaryKey(
		long fooId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByPrimaryKey(fooId);
	}

	/**
	* Returns the foo with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param fooId the primary key of the foo
	* @return the foo, or <code>null</code> if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByPrimaryKey(
		long fooId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(fooId);
	}

	/**
	* Returns all the foos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the foos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the foos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the foos before and after the current foo in the ordered set where uuid = &#63;.
	*
	* @param fooId the primary key of the current foo
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo[] findByUuid_PrevAndNext(
		long fooId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByUuid_PrevAndNext(fooId, uuid, orderByComparator);
	}

	/**
	* Returns the foo where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.sampleservicebuilder.NoSuchFooException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the foo where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Returns all the foos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the foos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the foos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the foos before and after the current foo in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param fooId the primary key of the current foo
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo[] findByUuid_C_PrevAndNext(
		long fooId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(fooId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Returns all the foos where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2);
	}

	/**
	* Returns a range of all the foos where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByField2(field2, start, end);
	}

	/**
	* Returns an ordered range of all the foos where field2 = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param field2 the field2
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findByField2(
		boolean field2, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByField2(field2, start, end, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_First(field2, orderByComparator);
	}

	/**
	* Returns the first foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByField2_First(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByField2_First(field2, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo findByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().findByField2_Last(field2, orderByComparator);
	}

	/**
	* Returns the last foo in the ordered set where field2 = &#63;.
	*
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching foo, or <code>null</code> if a matching foo could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo fetchByField2_Last(
		boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByField2_Last(field2, orderByComparator);
	}

	/**
	* Returns the foos before and after the current foo in the ordered set where field2 = &#63;.
	*
	* @param fooId the primary key of the current foo
	* @param field2 the field2
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next foo
	* @throws com.liferay.sampleservicebuilder.NoSuchFooException if a foo with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo[] findByField2_PrevAndNext(
		long fooId, boolean field2,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence()
				   .findByField2_PrevAndNext(fooId, field2, orderByComparator);
	}

	/**
	* Returns all the foos.
	*
	* @return the foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @return the range of foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the foos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of foos
	* @param end the upper bound of the range of foos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of foos
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.sampleservicebuilder.model.Foo> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the foos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the foo where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the foo that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.sampleservicebuilder.model.Foo removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sampleservicebuilder.NoSuchFooException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the foos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Removes all the foos where field2 = &#63; from the database.
	*
	* @param field2 the field2
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByField2(field2);
	}

	/**
	* Removes all the foos from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of foos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of foos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of foos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of foos where field2 = &#63;.
	*
	* @param field2 the field2
	* @return the number of matching foos
	* @throws SystemException if a system exception occurred
	*/
	public static int countByField2(boolean field2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByField2(field2);
	}

	/**
	* Returns the number of foos.
	*
	* @return the number of foos
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FooPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FooPersistence)PortletBeanLocatorUtil.locate(com.liferay.sampleservicebuilder.service.ClpSerializer.getServletContextName(),
					FooPersistence.class.getName());

			ReferenceRegistry.registerReference(FooUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(FooPersistence persistence) {
	}

	private static FooPersistence _persistence;
}