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

package com.liferay.ams.service;


/**
 * <p>
 * This class is a wrapper for {@link TypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TypeLocalService
 * @generated
 */
public class TypeLocalServiceWrapper implements TypeLocalService {
	public TypeLocalServiceWrapper(TypeLocalService typeLocalService) {
		_typeLocalService = typeLocalService;
	}

	public com.liferay.ams.model.Type addType(com.liferay.ams.model.Type type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.addType(type);
	}

	public com.liferay.ams.model.Type createType(long typeId) {
		return _typeLocalService.createType(typeId);
	}

	public void deleteType(long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_typeLocalService.deleteType(typeId);
	}

	public void deleteType(com.liferay.ams.model.Type type)
		throws com.liferay.portal.kernel.exception.SystemException {
		_typeLocalService.deleteType(type);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.Type getType(long typeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.getType(typeId);
	}

	public java.util.List<com.liferay.ams.model.Type> getTypes(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.getTypes(start, end);
	}

	public int getTypesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.getTypesCount();
	}

	public com.liferay.ams.model.Type updateType(
		com.liferay.ams.model.Type type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.updateType(type);
	}

	public com.liferay.ams.model.Type updateType(
		com.liferay.ams.model.Type type, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _typeLocalService.updateType(type, merge);
	}

	public TypeLocalService getWrappedTypeLocalService() {
		return _typeLocalService;
	}

	private TypeLocalService _typeLocalService;
}