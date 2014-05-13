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

package com.liferay.knowledgebase.navigation.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.service.KBArticleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Roberto Díaz
 */
public class NavigationUtil {

	public static List<KBArticle> getChildKBArticles(
			KBArticle kbArticle, List<KBArticle> childKBArticles)
		throws Exception {

		List<KBArticle> currentChildKBArticles =
			KBArticleLocalServiceUtil.getKBArticles(
				kbArticle.getGroupId(), kbArticle.getResourcePrimKey(),
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		if (!currentChildKBArticles.isEmpty()) {
			childKBArticles.addAll(currentChildKBArticles);

			for (KBArticle currentChildKbArticle : currentChildKBArticles) {
				getChildKBArticles(currentChildKbArticle, childKBArticles);
			}
		}

		return childKBArticles;
	}

}