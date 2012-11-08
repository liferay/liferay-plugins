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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.Asset;
import com.liferay.ams.service.AssetLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class AssetActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public AssetActionableDynamicQuery() throws SystemException {
		setBaseLocalService(AssetLocalServiceUtil.getService());
		setClass(Asset.class);

		setClassLoader(com.liferay.ams.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("assetId");
	}
}