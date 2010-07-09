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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.Type;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TypePersistenceImpl
 * @see       TypeUtil
 * @generated
 */
public interface TypePersistence extends BasePersistence<Type> {
	public void cacheResult(com.liferay.ams.model.Type type);

	public void cacheResult(java.util.List<com.liferay.ams.model.Type> types);

	public com.liferay.ams.model.Type create(long typeId);

	public com.liferay.ams.model.Type remove(long typeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Type updateImpl(
		com.liferay.ams.model.Type type, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Type findByPrimaryKey(long typeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.Type fetchByPrimaryKey(long typeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Type> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Type> findAll(int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.Type> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}