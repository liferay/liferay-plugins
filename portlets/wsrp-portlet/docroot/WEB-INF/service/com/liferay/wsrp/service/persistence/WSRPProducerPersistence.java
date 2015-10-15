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

package com.liferay.wsrp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.wsrp.model.WSRPProducer;

/**
 * The persistence interface for the w s r p producer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wsrp.service.persistence.impl.WSRPProducerPersistenceImpl
 * @see WSRPProducerUtil
 * @generated
 */
@ProviderType
public interface WSRPProducerPersistence extends BasePersistence<WSRPProducer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPProducerUtil} to access the w s r p producer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where uuid = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer[] findByUuid_PrevAndNext(long wsrpProducerId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Removes all the w s r p producers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of w s r p producers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching w s r p producers
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProducerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the w s r p producer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the w s r p producer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the w s r p producer that was removed
	*/
	public WSRPProducer removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the number of w s r p producers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching w s r p producers
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns an ordered range of all the w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer[] findByUuid_C_PrevAndNext(long wsrpProducerId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Removes all the w s r p producers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of w s r p producers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByCompanyId(long companyId);

	/**
	* Returns a range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByCompanyId(long companyId,
		int start, int end);

	/**
	* Returns an ordered range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns an ordered range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching w s r p producers
	*/
	public java.util.List<WSRPProducer> findByCompanyId(long companyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByCompanyId_First(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer
	* @throws NoSuchProducerException if a matching w s r p producer could not be found
	*/
	public WSRPProducer findByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	public WSRPProducer fetchByCompanyId_Last(long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns the w s r p producers before and after the current w s r p producer in the ordered set where companyId = &#63;.
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer[] findByCompanyId_PrevAndNext(long wsrpProducerId,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Removes all the w s r p producers where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	*/
	public void removeByCompanyId(long companyId);

	/**
	* Returns the number of w s r p producers where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching w s r p producers
	*/
	public int countByCompanyId(long companyId);

	/**
	* Caches the w s r p producer in the entity cache if it is enabled.
	*
	* @param wsrpProducer the w s r p producer
	*/
	public void cacheResult(WSRPProducer wsrpProducer);

	/**
	* Caches the w s r p producers in the entity cache if it is enabled.
	*
	* @param wsrpProducers the w s r p producers
	*/
	public void cacheResult(java.util.List<WSRPProducer> wsrpProducers);

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public WSRPProducer create(long wsrpProducerId);

	/**
	* Removes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer that was removed
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer remove(long wsrpProducerId)
		throws com.liferay.wsrp.NoSuchProducerException;

	public WSRPProducer updateImpl(WSRPProducer wsrpProducer);

	/**
	* Returns the w s r p producer with the primary key or throws a {@link NoSuchProducerException} if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws NoSuchProducerException if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer findByPrimaryKey(long wsrpProducerId)
		throws com.liferay.wsrp.NoSuchProducerException;

	/**
	* Returns the w s r p producer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer, or <code>null</code> if a w s r p producer with the primary key could not be found
	*/
	public WSRPProducer fetchByPrimaryKey(long wsrpProducerId);

	@Override
	public java.util.Map<java.io.Serializable, WSRPProducer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the w s r p producers.
	*
	* @return the w s r p producers
	*/
	public java.util.List<WSRPProducer> findAll();

	/**
	* Returns a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of w s r p producers
	*/
	public java.util.List<WSRPProducer> findAll(int start, int end);

	/**
	* Returns an ordered range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of w s r p producers
	*/
	public java.util.List<WSRPProducer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator);

	/**
	* Returns an ordered range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of w s r p producers
	*/
	public java.util.List<WSRPProducer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WSRPProducer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the w s r p producers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}