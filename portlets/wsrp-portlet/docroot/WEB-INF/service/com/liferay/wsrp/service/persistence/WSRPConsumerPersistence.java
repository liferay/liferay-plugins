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

import com.liferay.wsrp.model.WSRPConsumer;

/**
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerPersistenceImpl
 * @see       WSRPConsumerUtil
 * @generated
 */
public interface WSRPConsumerPersistence extends BasePersistence<WSRPConsumer> {
	public void cacheResult(com.liferay.wsrp.model.WSRPConsumer wsrpConsumer);

	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumer> wsrpConsumers);

	public com.liferay.wsrp.model.WSRPConsumer create(long wsrpConsumerId);

	public com.liferay.wsrp.model.WSRPConsumer remove(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public com.liferay.wsrp.model.WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.wsrp.model.WSRPConsumer findByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public com.liferay.wsrp.model.WSRPConsumer fetchByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.wsrp.model.WSRPConsumer findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public com.liferay.wsrp.model.WSRPConsumer findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public com.liferay.wsrp.model.WSRPConsumer[] findByCompanyId_PrevAndNext(
		long wsrpConsumerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.wsrp.NoSuchConsumerException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}