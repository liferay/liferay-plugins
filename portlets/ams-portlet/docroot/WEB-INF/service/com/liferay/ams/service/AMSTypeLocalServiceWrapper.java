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
 * <a href="AMSTypeLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSTypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSTypeLocalService
 * @generated
 */
public class AMSTypeLocalServiceWrapper implements AMSTypeLocalService {
	public AMSTypeLocalServiceWrapper(AMSTypeLocalService amsTypeLocalService) {
		_amsTypeLocalService = amsTypeLocalService;
	}

	public com.liferay.ams.model.AMSType addAMSType(
		com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.addAMSType(amsType);
	}

	public com.liferay.ams.model.AMSType createAMSType(long amsTypeId) {
		return _amsTypeLocalService.createAMSType(amsTypeId);
	}

	public void deleteAMSType(long amsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_amsTypeLocalService.deleteAMSType(amsTypeId);
	}

	public void deleteAMSType(com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		_amsTypeLocalService.deleteAMSType(amsType);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.AMSType getAMSType(long amsTypeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.getAMSType(amsTypeId);
	}

	public java.util.List<com.liferay.ams.model.AMSType> getAMSTypes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.getAMSTypes(start, end);
	}

	public int getAMSTypesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.getAMSTypesCount();
	}

	public com.liferay.ams.model.AMSType updateAMSType(
		com.liferay.ams.model.AMSType amsType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.updateAMSType(amsType);
	}

	public com.liferay.ams.model.AMSType updateAMSType(
		com.liferay.ams.model.AMSType amsType, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsTypeLocalService.updateAMSType(amsType, merge);
	}

	public AMSTypeLocalService getWrappedAMSTypeLocalService() {
		return _amsTypeLocalService;
	}

	private AMSTypeLocalService _amsTypeLocalService;
}