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

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.exception.NoSuchGadgetException;
import com.liferay.opensocial.model.Gadget;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the gadget service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.opensocial.service.persistence.impl.GadgetPersistenceImpl
 * @see GadgetUtil
 * @generated
 */
@ProviderType
public interface GadgetPersistence extends BasePersistence<Gadget> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link GadgetUtil} to access the gadget persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the gadgets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching gadgets
	*/
	public java.util.List<Gadget> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the gadgets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the first gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the last gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the last gadget in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where uuid = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] findByUuid_PrevAndNext(long gadgetId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns all the gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the gadgets that the user has permission to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] filterFindByUuid_PrevAndNext(long gadgetId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Removes all the gadgets where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of gadgets where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching gadgets
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the number of gadgets that the user has permission to view where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching gadgets that the user has permission to view
	*/
	public int filterCountByUuid(java.lang.String uuid);

	/**
	* Returns all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching gadgets
	*/
	public java.util.List<Gadget> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns an ordered range of all the gadgets where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the first gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the last gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the last gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] findByUuid_C_PrevAndNext(long gadgetId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns all the gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] filterFindByUuid_C_PrevAndNext(long gadgetId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Removes all the gadgets where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of gadgets where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching gadgets
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of gadgets that the user has permission to view where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching gadgets that the user has permission to view
	*/
	public int filterCountByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the gadgets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching gadgets
	*/
	public java.util.List<Gadget> findByCompanyId(long companyId);

	/**
	* Returns a range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets
	*/
	public java.util.List<Gadget> findByCompanyId(long companyId, int start,
		int end);

	/**
	* Returns an ordered range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns an ordered range of all the gadgets where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching gadgets
	*/
	public java.util.List<Gadget> findByCompanyId(long companyId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the first gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the last gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns the last gadget in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set where companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] findByCompanyId_PrevAndNext(long gadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Returns all the gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByCompanyId(long companyId);

	/**
	* Returns a range of all the gadgets that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the gadgets that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching gadgets that the user has permission to view
	*/
	public java.util.List<Gadget> filterFindByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns the gadgets before and after the current gadget in the ordered set of gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param gadgetId the primary key of the current gadget
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget[] filterFindByCompanyId_PrevAndNext(long gadgetId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator)
		throws NoSuchGadgetException;

	/**
	* Removes all the gadgets where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of gadgets where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching gadgets
	*/
	public int countByCompanyId(long companyId);

	/**
	* Returns the number of gadgets that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching gadgets that the user has permission to view
	*/
	public int filterCountByCompanyId(long companyId);

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or throws a {@link NoSuchGadgetException} if it could not be found.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the matching gadget
	* @throws NoSuchGadgetException if a matching gadget could not be found
	*/
	public Gadget findByC_U(long companyId, java.lang.String url)
		throws NoSuchGadgetException;

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByC_U(long companyId, java.lang.String url);

	/**
	* Returns the gadget where companyId = &#63; and url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param url the url
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	*/
	public Gadget fetchByC_U(long companyId, java.lang.String url,
		boolean retrieveFromCache);

	/**
	* Removes the gadget where companyId = &#63; and url = &#63; from the database.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the gadget that was removed
	*/
	public Gadget removeByC_U(long companyId, java.lang.String url)
		throws NoSuchGadgetException;

	/**
	* Returns the number of gadgets where companyId = &#63; and url = &#63;.
	*
	* @param companyId the company ID
	* @param url the url
	* @return the number of matching gadgets
	*/
	public int countByC_U(long companyId, java.lang.String url);

	/**
	* Caches the gadget in the entity cache if it is enabled.
	*
	* @param gadget the gadget
	*/
	public void cacheResult(Gadget gadget);

	/**
	* Caches the gadgets in the entity cache if it is enabled.
	*
	* @param gadgets the gadgets
	*/
	public void cacheResult(java.util.List<Gadget> gadgets);

	/**
	* Creates a new gadget with the primary key. Does not add the gadget to the database.
	*
	* @param gadgetId the primary key for the new gadget
	* @return the new gadget
	*/
	public Gadget create(long gadgetId);

	/**
	* Removes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget that was removed
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget remove(long gadgetId) throws NoSuchGadgetException;

	public Gadget updateImpl(Gadget gadget);

	/**
	* Returns the gadget with the primary key or throws a {@link NoSuchGadgetException} if it could not be found.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget
	* @throws NoSuchGadgetException if a gadget with the primary key could not be found
	*/
	public Gadget findByPrimaryKey(long gadgetId) throws NoSuchGadgetException;

	/**
	* Returns the gadget with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget, or <code>null</code> if a gadget with the primary key could not be found
	*/
	public Gadget fetchByPrimaryKey(long gadgetId);

	@Override
	public java.util.Map<java.io.Serializable, Gadget> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the gadgets.
	*
	* @return the gadgets
	*/
	public java.util.List<Gadget> findAll();

	/**
	* Returns a range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of gadgets
	*/
	public java.util.List<Gadget> findAll(int start, int end);

	/**
	* Returns an ordered range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of gadgets
	*/
	public java.util.List<Gadget> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator);

	/**
	* Returns an ordered range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of gadgets
	*/
	public java.util.List<Gadget> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Gadget> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the gadgets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of gadgets.
	*
	* @return the number of gadgets
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}