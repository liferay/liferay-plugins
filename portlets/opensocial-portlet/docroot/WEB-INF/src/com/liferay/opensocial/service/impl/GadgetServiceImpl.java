/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.base.GadgetServiceBaseImpl;
import com.liferay.opensocial.service.permission.GadgetPermission;
import com.liferay.opensocial.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

/**
 * @author Brian Wing Shun Chan
 * @author Dennis Ju
 */
public class GadgetServiceImpl extends GadgetServiceBaseImpl {

	public Gadget addGadget(
			long companyId, String url, String portletCategoryNames,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.PUBLISH_GADGET);

		return gadgetLocalService.addGadget(
			companyId, url, portletCategoryNames, serviceContext);
	}

	public void deleteGadget(long gadgetId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(), gadgetId,
			ActionKeys.DELETE);

		gadgetLocalService.deleteGadget(gadgetId);
	}

	public void updateGadget(
			long gadgetId, String portletCategoryNames,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		GadgetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(), gadgetId,
			ActionKeys.UPDATE);

		gadgetLocalService.updateGadget(gadgetId, portletCategoryNames);
	}

}