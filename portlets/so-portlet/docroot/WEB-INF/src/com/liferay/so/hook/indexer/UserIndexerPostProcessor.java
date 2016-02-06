/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.so.model.ProjectsEntry;
import com.liferay.so.service.ProjectsEntryLocalServiceUtil;
import com.liferay.social.kernel.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ryan Park
 */
public class UserIndexerPostProcessor extends BaseIndexerPostProcessor {

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter booleanFilter, SearchContext searchContext)
		throws Exception {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			Object projectTitles = params.get("projectTitles");

			if (Validator.isNotNull(projectTitles)) {
				booleanFilter.addRequiredTerm(
					"projectTitles", String.valueOf(projectTitles));
			}

			Object socialRelationType = params.get("socialRelationType");

			if (Validator.isNotNull(socialRelationType)) {
				Long[] socialRelationTypeValues = (Long[])socialRelationType;

				booleanFilter.addRequiredTerm(
					"socialRelationships", socialRelationTypeValues[0]);
			}
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

			projectTitles[i] = StringUtil.toLowerCase(projectEntry.getTitle());
		}

		document.addKeyword("projectTitles", projectTitles);

		int count = UserLocalServiceUtil.getSocialUsersCount(
			user.getUserId(), SocialRelationConstants.TYPE_BI_CONNECTION,
			StringPool.EQUAL);

		List<Long> socialRelationshipUserIds = new ArrayList<>();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			List<User> socialRelationshipUsers =
				UserLocalServiceUtil.getSocialUsers(
					user.getUserId(),
					SocialRelationConstants.TYPE_BI_CONNECTION,
					StringPool.EQUAL, start, end,
					new UserFirstNameComparator(true));

			for (User socialRelationshipUser : socialRelationshipUsers) {
				socialRelationshipUserIds.add(
					socialRelationshipUser.getUserId());
			}
		}

		document.addKeyword(
			"socialRelationships",
			ArrayUtil.toLongArray(socialRelationshipUserIds));
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNotNull(keywords)) {
			searchQuery.addTerm("projectTitles", keywords, true);
		}
	}

}