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

import com.liferay.ams.model.AMSType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="AMSTypePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSTypePersistenceImpl
 * @see       AMSTypeUtil
 * @generated
 */
public interface AMSTypePersistence extends BasePersistence<AMSType> {
	public void cacheResult(com.liferay.ams.model.AMSType amsType);

	public void cacheResult(
		java.util.List<com.liferay.ams.model.AMSType> amsTypes);

	public com.liferay.ams.model.AMSType create(long amsTypeId);

	public com.liferay.ams.model.AMSType remove(long amsTypeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSType updateImpl(
		com.liferay.ams.model.AMSType amsType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSType findByPrimaryKey(long amsTypeId)
		throws com.liferay.ams.NoSuchTypeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.ams.model.AMSType fetchByPrimaryKey(long amsTypeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSType> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSType> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.ams.model.AMSType> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}