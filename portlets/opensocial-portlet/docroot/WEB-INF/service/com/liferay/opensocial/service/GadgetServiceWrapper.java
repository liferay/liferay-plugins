/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link GadgetService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       GadgetService
 * @generated
 */
public class GadgetServiceWrapper implements GadgetService,
	ServiceWrapper<GadgetService> {
	public GadgetServiceWrapper(GadgetService gadgetService) {
		_gadgetService = gadgetService;
	}

	public com.liferay.opensocial.model.Gadget addGadget(long companyId,
		java.lang.String url, java.lang.String portletCategoryNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetService.addGadget(companyId, url, portletCategoryNames,
			serviceContext);
	}

	public void deleteGadget(long gadgetId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetService.deleteGadget(gadgetId, serviceContext);
	}

	public void updateGadget(long gadgetId,
		java.lang.String portletCategoryNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetService.updateGadget(gadgetId, portletCategoryNames,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public GadgetService getWrappedGadgetService() {
		return _gadgetService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedGadgetService(GadgetService gadgetService) {
		_gadgetService = gadgetService;
	}

	public GadgetService getWrappedService() {
		return _gadgetService;
	}

	public void setWrappedService(GadgetService gadgetService) {
		_gadgetService = gadgetService;
	}

	private GadgetService _gadgetService;
}