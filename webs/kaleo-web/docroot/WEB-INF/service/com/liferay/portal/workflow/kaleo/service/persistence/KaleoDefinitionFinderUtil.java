/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoDefinitionFinderUtil {
	public static int countKaleoDefinitions(
		com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionQuery kaleoDefinitionQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countKaleoDefinitions(kaleoDefinitionQuery);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findKaleoDefinitions(
		com.liferay.portal.workflow.kaleo.service.persistence.KaleoDefinitionQuery kaleoDefinitionQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().findKaleoDefinitions(kaleoDefinitionQuery);
	}

	public static KaleoDefinitionFinder getFinder() {
		if (_finder == null) {
			_finder = (KaleoDefinitionFinder)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.getServletContextName(),
					KaleoDefinitionFinder.class.getName());

			ReferenceRegistry.registerReference(KaleoDefinitionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(KaleoDefinitionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(KaleoDefinitionFinderUtil.class,
			"_finder");
	}

	private static KaleoDefinitionFinder _finder;
}