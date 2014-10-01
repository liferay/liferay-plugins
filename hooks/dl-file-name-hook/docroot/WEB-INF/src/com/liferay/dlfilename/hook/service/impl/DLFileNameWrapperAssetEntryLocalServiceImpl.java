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

package com.liferay.dlfilename.hook.service.impl;

import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalService;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceWrapper;

/**
 * @author Preston Crary
 */
public class DLFileNameWrapperAssetEntryLocalServiceImpl
	extends AssetEntryLocalServiceWrapper {

	public DLFileNameWrapperAssetEntryLocalServiceImpl(
		AssetEntryLocalService assetEntryLocalService) {

		super(assetEntryLocalService);

		ClassLoader classLoader = getClass().getClassLoader();

		assetEntryLocalService =
			(AssetEntryLocalService)ProxyUtil.newProxyInstance(
				classLoader, new Class<?>[]{AssetEntryLocalService.class},
				new DLFileNameWrapperInvocationHandler(assetEntryLocalService));

		this.setWrappedService(assetEntryLocalService);
	}

}