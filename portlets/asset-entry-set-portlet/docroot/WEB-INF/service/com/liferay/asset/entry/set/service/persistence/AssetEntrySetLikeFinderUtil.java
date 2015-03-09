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

package com.liferay.asset.entry.set.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntrySetLikeFinderUtil {
	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySetLike> findByAESI_NotC_C(
		long assetEntrySetId, long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByAESI_NotC_C(assetEntrySetId, classNameId, classPK,
			start, end);
	}

	public static AssetEntrySetLikeFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetEntrySetLikeFinder)PortletBeanLocatorUtil.locate(com.liferay.asset.entry.set.service.ClpSerializer.getServletContextName(),
					AssetEntrySetLikeFinder.class.getName());

			ReferenceRegistry.registerReference(AssetEntrySetLikeFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetEntrySetLikeFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetEntrySetLikeFinderUtil.class,
			"_finder");
	}

	private static AssetEntrySetLikeFinder _finder;
}