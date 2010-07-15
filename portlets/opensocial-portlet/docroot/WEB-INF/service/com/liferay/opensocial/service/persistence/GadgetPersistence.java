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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.Gadget;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       GadgetPersistenceImpl
 * @see       GadgetUtil
 * @generated
 */
public interface GadgetPersistence extends BasePersistence<Gadget> {
	public void cacheResult(com.liferay.opensocial.model.Gadget gadget);

	public void cacheResult(
		java.util.List<com.liferay.opensocial.model.Gadget> gadgets);

	public com.liferay.opensocial.model.Gadget create(long gadgetId);

	public com.liferay.opensocial.model.Gadget remove(long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget updateImpl(
		com.liferay.opensocial.model.Gadget gadget, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget findByPrimaryKey(long gadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget fetchByPrimaryKey(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.Gadget[] findByCompanyId_PrevAndNext(
		long gadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.opensocial.model.Gadget> findAll(
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