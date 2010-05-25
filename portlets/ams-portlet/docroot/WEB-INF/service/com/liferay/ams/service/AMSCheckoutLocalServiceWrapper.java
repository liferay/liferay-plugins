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
 * <a href="AMSCheckoutLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSCheckoutLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckoutLocalService
 * @generated
 */
public class AMSCheckoutLocalServiceWrapper implements AMSCheckoutLocalService {
	public AMSCheckoutLocalServiceWrapper(
		AMSCheckoutLocalService amsCheckoutLocalService) {
		_amsCheckoutLocalService = amsCheckoutLocalService;
	}

	public com.liferay.ams.model.AMSCheckout addAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.addAMSCheckout(amsCheckout);
	}

	public com.liferay.ams.model.AMSCheckout createAMSCheckout(
		long amsCheckoutId) {
		return _amsCheckoutLocalService.createAMSCheckout(amsCheckoutId);
	}

	public void deleteAMSCheckout(long amsCheckoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_amsCheckoutLocalService.deleteAMSCheckout(amsCheckoutId);
	}

	public void deleteAMSCheckout(com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		_amsCheckoutLocalService.deleteAMSCheckout(amsCheckout);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.ams.model.AMSCheckout getAMSCheckout(long amsCheckoutId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.getAMSCheckout(amsCheckoutId);
	}

	public java.util.List<com.liferay.ams.model.AMSCheckout> getAMSCheckouts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.getAMSCheckouts(start, end);
	}

	public int getAMSCheckoutsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.getAMSCheckoutsCount();
	}

	public com.liferay.ams.model.AMSCheckout updateAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.updateAMSCheckout(amsCheckout);
	}

	public com.liferay.ams.model.AMSCheckout updateAMSCheckout(
		com.liferay.ams.model.AMSCheckout amsCheckout, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckoutLocalService.updateAMSCheckout(amsCheckout, merge);
	}

	public AMSCheckoutLocalService getWrappedAMSCheckoutLocalService() {
		return _amsCheckoutLocalService;
	}

	private AMSCheckoutLocalService _amsCheckoutLocalService;
}