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
public class AssetEntrySetFinderUtil {
	public static int countByCCNI_ATN(long creatorClassNameId,
		java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCCNI_ATN(creatorClassNameId, assetTagName);
	}

	public static int countByCCNI_CCPK_ATN(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countByCCNI_CCPK_ATN(creatorClassNameId, creatorClassPK,
			assetTagName, andOperator);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_ATN(
		long creatorClassNameId, java.lang.String assetTagName, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCCNI_ATN(creatorClassNameId, assetTagName, start, end);
	}

	public static java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> findByCCNI_CCPK_ATN(
		long creatorClassNameId, long creatorClassPK,
		java.lang.String assetTagName, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByCCNI_CCPK_ATN(creatorClassNameId, creatorClassPK,
			assetTagName, andOperator, start, end);
	}

	public static AssetEntrySetFinder getFinder() {
		if (_finder == null) {
			_finder = (AssetEntrySetFinder)PortletBeanLocatorUtil.locate(com.liferay.asset.entry.set.service.ClpSerializer.getServletContextName(),
					AssetEntrySetFinder.class.getName());

			ReferenceRegistry.registerReference(AssetEntrySetFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(AssetEntrySetFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(AssetEntrySetFinderUtil.class,
			"_finder");
	}

	private static AssetEntrySetFinder _finder;
}