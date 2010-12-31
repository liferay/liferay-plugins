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

package com.liferay.knowledgebase.admin.util;

import com.liferay.portal.kernel.search.HitsOpenSearchImpl;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String SEARCH_PATH = "/c/knowledge_base/open_search";

	public static final String TITLE = "Liferay Knowledge Base Search: ";

	public String getPortletId() {
		return AdminIndexer.PORTLET_ID;
	}

	public String getSearchPath() {
		return SEARCH_PATH;
	}

	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

}