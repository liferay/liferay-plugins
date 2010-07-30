/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.wsrp.model.WSRPProducer;

/**
 * The persistence interface for the w s r p producer service.
 *
 * <p>
 * Never modify this interface directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerPersistenceImpl
 * @see WSRPProducerUtil
 * @generated
 */
public interface WSRPProducerPersistence extends BasePersistence<WSRPProducer> {
	/**
	* Caches the w s r p producer in the entity cache if it is enabled.
	*
	* @param wsrpProducer the w s r p producer to cache
	*/
	public void cacheResult(com.liferay.wsrp.model.WSRPProducer wsrpProducer);

	/**
	* Caches the w s r p producers in the entity cache if it is enabled.
	*
	* @param wsrpProducers the w s r p producers to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPProducer> wsrpProducers);

	/**
	* Creates a new w s r p producer with the primary key.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public com.liferay.wsrp.model.WSRPProducer create(long wsrpProducerId);

	/**
	* Removes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer to remove
	* @return the w s r p producer that was removed
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer remove(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the w s r p producer with the primary key or throws a {@link com.liferay.wsrp.NoSuchProducerException} if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer to find
	* @return the w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Finds the w s r p producer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wsrpProducerId the primary key of the w s r p producer to find
	* @return the w s r p producer, or <code>null</code> if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of w s r p producers to return
	* @param end the upper bound of the range of w s r p producers to return (not inclusive)
	* @return the range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the w s r p producers where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param start the lower bound of the range of w s r p producers to return
	* @param end the upper bound of the range of w s r p producers to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first w s r p producer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Finds the last w s r p producer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a matching w s r p producer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Finds the w s r p producers before and after the current w s r p producer in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wsrpProducerId the primary key of the current w s r p producer
	* @param companyId the company id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next w s r p producer
	* @throws com.liferay.wsrp.NoSuchProducerException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	/**
	* Finds all the w s r p producers.
	*
	* @return the w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers to return
	* @param end the upper bound of the range of w s r p producers to return (not inclusive)
	* @return the range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers to return
	* @param end the upper bound of the range of w s r p producers to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p producers where companyId = &#63; from the database.
	*
	* @param companyId the company id to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the w s r p producers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the w s r p producers where companyId = &#63;.
	*
	* @param companyId the company id to search with
	* @return the number of matching w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the w s r p producers.
	*
	* @return the number of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}