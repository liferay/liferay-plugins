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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class GadgetActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public GadgetActionableDynamicQuery() throws SystemException {
		setBaseLocalService(GadgetLocalServiceUtil.getService());
		setClass(Gadget.class);

		setClassLoader(com.liferay.opensocial.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("gadgetId");
	}
}