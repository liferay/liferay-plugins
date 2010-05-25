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
 * <a href="AMSDefinitionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSDefinitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSDefinitionLocalService
 * @generated
 */
public class AMSDefinitionLocalServiceWrapper
	implements AMSDefinitionLocalService {
	public AMSDefinitionLocalServiceWrapper(
		AMSDefinitionLocalService amsDefinitionLocalService) {
		_amsDefinitionLocalService = amsDefinitionLocalService;
	}

	public com.liferay.ams.model.AMSDefinition addAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.addAMSDefinition(amsDefinition);
	}

	public com.liferay.ams.model.AMSDefinition createAMSDefinition(
		long assetDefinitionId) {
		return _amsDefinitionLocalService.createAMSDefinition(assetDefinitionId);
	}

	public void deleteAMSDefinition(long assetDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_amsDefinitionLocalService.deleteAMSDefinition(assetDefinitionId);
	}

	public void deleteAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		_amsDefinitionLocalService.deleteAMSDefinition(amsDefinition);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.AMSDefinition getAMSDefinition(
		long assetDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.getAMSDefinition(assetDefinitionId);
	}

	public java.util.List<com.liferay.ams.model.AMSDefinition> getAMSDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.getAMSDefinitions(start, end);
	}

	public int getAMSDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.getAMSDefinitionsCount();
	}

	public com.liferay.ams.model.AMSDefinition updateAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.updateAMSDefinition(amsDefinition);
	}

	public com.liferay.ams.model.AMSDefinition updateAMSDefinition(
		com.liferay.ams.model.AMSDefinition amsDefinition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsDefinitionLocalService.updateAMSDefinition(amsDefinition,
			merge);
	}

	public AMSDefinitionLocalService getWrappedAMSDefinitionLocalService() {
		return _amsDefinitionLocalService;
	}

	private AMSDefinitionLocalService _amsDefinitionLocalService;
}