/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.Gadget;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the gadget service. This utility wraps {@link GadgetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see GadgetPersistence
 * @see GadgetPersistenceImpl
 * @generated
 */
public class GadgetUtil {
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
	public static void clearCache(Gadget gadget) {
		getPersistence().clearCache(gadget);
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
	public static List<Gadget> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Gadget> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Gadget> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Gadget update(Gadget gadget) throws SystemException {
		return getPersistence().update(gadget);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Gadget update(Gadget gadget, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(gadget, serviceContext);
	}

	/**
	* Returns all the gadgets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the gadgets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where uuid = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] findByUuid_PrevAndNext(
		long gadgetId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(gadgetId, uuid, orderByComparator);
	}

	/**
	* Returns all the gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid);
	}

	/**
	* Returns a range of all the gadgets that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] filterFindByUuid_PrevAndNext(
		long gadgetId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_PrevAndNext(gadgetId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the gadgets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of gadgets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUuid(uuid);
	}

	/**
	* Returns all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] findByUuid_C_PrevAndNext(
		long gadgetId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(gadgetId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Returns all the gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] filterFindByUuid_C_PrevAndNext(
		long gadgetId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUuid_C_PrevAndNext(gadgetId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the gadgets where uuid = &#63; and companyId = &#63; from the database.
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
	* Returns the number of gadgets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the gadgets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] findByCompanyId_PrevAndNext(
		long gadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(gadgetId, companyId,
			orderByComparator);
	}

	/**
	* Returns all the gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId);
	}

	/**
	* Returns a range of all the gadgets that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId(companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget[] filterFindByCompanyId_PrevAndNext(
		long gadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByCompanyId_PrevAndNext(gadgetId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the gadgets where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of gadgets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the number of gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching gadgets that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByCompanyId(companyId);
	}

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the matching gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByC_U(
		long companyId, java.lang.String url)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_U(companyId, url);
	}

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByC_U(
		long companyId, java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_U(companyId, url);
	}

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param url the url
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByC_U(
		long companyId, java.lang.String url, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_U(companyId, url, retrieveFromCache);
	}

	/**
	* Removes the gadget where companyId = &#63; and url = &#63; from the database.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the gadget that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget removeByC_U(
		long companyId, java.lang.String url)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByC_U(companyId, url);
	}

	/**
	* Returns the number of gadgets where companyId = &#63; and url = &#63;.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the number of matching gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_U(long companyId, java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_U(companyId, url);
	}

	/**
	* Caches the gadget in the entity cache if it is enabled.
	*
	* @param gadget the gadget
	*/
	public static void cacheResult(com.liferay.opensocial.model.Gadget gadget) {
		getPersistence().cacheResult(gadget);
	}

	/**
	* Caches the gadgets in the entity cache if it is enabled.
	*
	* @param gadgets the gadgets
	*/
	public static void cacheResult(
		java.util.List<com.liferay.opensocial.model.Gadget> gadgets) {
		getPersistence().cacheResult(gadgets);
	}

	/**
	* Creates a new gadget with the primary key. Does not add the gadget to the database.
	*
	* @param gadgetId the primary key for the new gadget
	* @return the new gadget
	*/
	public static com.liferay.opensocial.model.Gadget create(long gadgetId) {
		return getPersistence().create(gadgetId);
	}

	/**
	* Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget that was removed
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget remove(long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(gadgetId);
	}

	public static com.liferay.opensocial.model.Gadget updateImpl(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(gadget);
	}

	/**
	* Returns the gadget with the primary key or throws a {@link com.liferay.opensocial.NoSuchGadgetException} if it could not be found.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget
	* @throws com.liferay.opensocial.NoSuchGadgetException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget findByPrimaryKey(
		long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(gadgetId);
	}

	/**
	* Returns the gadget with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchByPrimaryKey(
		long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(gadgetId);
	}

	/**
	* Returns all the gadgets.
	*
	* @return the gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the gadgets from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of gadgets.
	*
	* @return the number of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static GadgetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (GadgetPersistence)PortletBeanLocatorUtil.locate(com.liferay.opensocial.service.ClpSerializer.getServletContextName(),
					GadgetPersistence.class.getName());

			ReferenceRegistry.registerReference(GadgetUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(GadgetPersistence persistence) {
	}

	private static GadgetPersistence _persistence;
}