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

package com.liferay.knowledgebase.admin.util;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.util.KnowledgeBaseUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.theme.ThemeDisplay;

import javax.portlet.PortletURL;
import javax.portlet.WindowState;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String SEARCH_PATH = "/c/knowledge_base/open_search";

	public static final String TITLE = "Liferay Knowledge Base Search: ";

	@Override
	public Indexer<KBArticle> getIndexer() {
		return IndexerRegistryUtil.getIndexer(KBArticle.class);
	}

	@Override
	public String getSearchPath() {
		return SEARCH_PATH;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

	@Override
	protected String getURL(
		ThemeDisplay themeDisplay, long groupId, Document result,
		PortletURL portletURL) {

		long resourcePrimKey = GetterUtil.getLong(
			result.get(Field.ENTRY_CLASS_PK));
		int status = WorkflowConstants.STATUS_APPROVED;

		WindowState windowState = portletURL.getWindowState();

		return KnowledgeBaseUtil.getKBArticleURL(
			themeDisplay.getPlid(), resourcePrimKey, status,
			themeDisplay.getPortalURL(),
			windowState.equals(LiferayWindowState.MAXIMIZED));
	}

}