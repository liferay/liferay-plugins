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

package com.liferay.socialcoding.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class JIRAChangeGroupFinderUtil {
	public static int countByCD_P(java.util.Date createDate, long projectId) {
		return getFinder().countByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByCD_P(
		java.util.Date createDate, long projectId) {
		return getFinder().findByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAChangeGroup> findByCD_P(
		java.util.Date createDate, long projectId, int start, int end) {
		return getFinder().findByCD_P(createDate, projectId, start, end);
	}

	public static JIRAChangeGroupFinder getFinder() {
		if (_finder == null) {
			_finder = (JIRAChangeGroupFinder)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAChangeGroupFinder.class.getName());

			ReferenceRegistry.registerReference(JIRAChangeGroupFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(JIRAChangeGroupFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(JIRAChangeGroupFinderUtil.class,
			"_finder");
	}

	private static JIRAChangeGroupFinder _finder;
}