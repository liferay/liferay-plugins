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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class KBArticleFinderUtil {
	public static int countByUrlTitle(long groupId,
		java.lang.String kbFolderUrlTitle, java.lang.String kbArticleUrlTitle,
		int[] status) {
		return getFinder()
				   .countByUrlTitle(groupId, kbFolderUrlTitle,
			kbArticleUrlTitle, status);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUrlTitle(
		long groupId, java.lang.String kbFolderUrlTitle,
		java.lang.String kbArticleUrlTitle, int[] status, int start, int end) {
		return getFinder()
				   .findByUrlTitle(groupId, kbFolderUrlTitle,
			kbArticleUrlTitle, status, start, end);
	}

	public static KBArticleFinder getFinder() {
		if (_finder == null) {
			_finder = (KBArticleFinder)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBArticleFinder.class.getName());

			ReferenceRegistry.registerReference(KBArticleFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(KBArticleFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(KBArticleFinderUtil.class, "_finder");
	}

	private static KBArticleFinder _finder;
}