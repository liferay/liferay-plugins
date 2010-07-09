/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenFinderUtil {
	public static int countKaleoTaskInstanceTokens(
		com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .countKaleoTaskInstanceTokens(kaleoTaskInstanceTokenQuery);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> findKaleoTaskInstanceTokens(
		com.liferay.portal.workflow.kaleo.service.persistence.KaleoTaskInstanceTokenQuery kaleoTaskInstanceTokenQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findKaleoTaskInstanceTokens(kaleoTaskInstanceTokenQuery);
	}

	public static KaleoTaskInstanceTokenFinder getFinder() {
		if (_finder == null) {
			_finder = (KaleoTaskInstanceTokenFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceTokenFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(KaleoTaskInstanceTokenFinder finder) {
		_finder = finder;
	}

	private static KaleoTaskInstanceTokenFinder _finder;
}