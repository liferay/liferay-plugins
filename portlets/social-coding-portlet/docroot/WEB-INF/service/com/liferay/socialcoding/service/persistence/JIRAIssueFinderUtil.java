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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueFinderUtil {
	public static int countByCD_P(java.util.Date createDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByCD_P(
		java.util.Date createDate, long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.socialcoding.model.JIRAIssue> findByCD_P(
		java.util.Date createDate, long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findByCD_P(createDate, projectId, start, end);
	}

	public static JIRAIssueFinder getFinder() {
		if (_finder == null) {
			_finder = (JIRAIssueFinder)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAIssueFinder.class.getName());

			ReferenceRegistry.registerReference(JIRAIssueFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(JIRAIssueFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(JIRAIssueFinderUtil.class, "_finder");
	}

	private static JIRAIssueFinder _finder;
}