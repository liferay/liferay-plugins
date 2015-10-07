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

package com.liferay.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SyncDLObjectFinderUtil {
	public static java.util.List<java.lang.Long> filterFindByR_U_T(
		long groupId, long userId, long[] typePKs) {
		return getFinder().filterFindByR_U_T(groupId, userId, typePKs);
	}

	public static java.util.List<com.liferay.sync.model.SyncDLObject> findByModifiedTime(
		long modifiedTime, long repositoryId, long parentFolderId,
		java.lang.String type, int start, int end) {
		return getFinder()
				   .findByModifiedTime(modifiedTime, repositoryId,
			parentFolderId, type, start, end);
	}

	public static SyncDLObjectFinder getFinder() {
		if (_finder == null) {
			_finder = (SyncDLObjectFinder)PortletBeanLocatorUtil.locate(com.liferay.sync.service.ClpSerializer.getServletContextName(),
					SyncDLObjectFinder.class.getName());

			ReferenceRegistry.registerReference(SyncDLObjectFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(SyncDLObjectFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(SyncDLObjectFinderUtil.class,
			"_finder");
	}

	private static SyncDLObjectFinder _finder;
}