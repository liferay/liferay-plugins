/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.hook.indexer;

import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalServiceUtil;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ryan Park
 */
public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			Object projectTitles = params.get("projectTitles");

			if (Validator.isNotNull(projectTitles)) {
				contextQuery.addRequiredTerm(
					"projectTitles", String.valueOf(projectTitles), true);
			}
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNotNull(keywords)) {
			searchQuery.addTerm("projectTitles", keywords, true);
		}
	}

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {

		User user = (User)obj;

		List<ProjectsEntry> projectsEntries =
			ProjectsEntryLocalServiceUtil.getUserProjectsEntries(
				user.getUserId());

		String[] projectTitles = new String[projectsEntries.size()];

		for (int i = 0; i < projectTitles.length; i++) {
			ProjectsEntry projectEntry = projectsEntries.get(i);

			projectTitles[i] = projectEntry.getTitle().toLowerCase();
		}

		document.addKeyword("projectTitles", projectTitles);
	}

}