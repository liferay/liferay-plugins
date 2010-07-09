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
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link DefinitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DefinitionLocalService
 * @generated
 */
public class DefinitionLocalServiceWrapper implements DefinitionLocalService {
	public DefinitionLocalServiceWrapper(
		DefinitionLocalService definitionLocalService) {
		_definitionLocalService = definitionLocalService;
	}

	public com.liferay.ams.model.Definition addDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.addDefinition(definition);
	}

	public com.liferay.ams.model.Definition createDefinition(long definitionId) {
		return _definitionLocalService.createDefinition(definitionId);
	}

	public void deleteDefinition(long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_definitionLocalService.deleteDefinition(definitionId);
	}

	public void deleteDefinition(com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_definitionLocalService.deleteDefinition(definition);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.Definition getDefinition(long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.getDefinition(definitionId);
	}

	public java.util.List<com.liferay.ams.model.Definition> getDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.getDefinitions(start, end);
	}

	public int getDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.getDefinitionsCount();
	}

	public com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.updateDefinition(definition);
	}

	public com.liferay.ams.model.Definition updateDefinition(
		com.liferay.ams.model.Definition definition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionLocalService.updateDefinition(definition, merge);
	}

	public DefinitionLocalService getWrappedDefinitionLocalService() {
		return _definitionLocalService;
	}

	private DefinitionLocalService _definitionLocalService;
}