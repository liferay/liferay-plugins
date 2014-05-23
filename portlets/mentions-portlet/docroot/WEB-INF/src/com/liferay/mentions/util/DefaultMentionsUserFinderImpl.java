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

package com.liferay.mentions.util;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.WildcardMode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.portlet.social.util.SocialInteractionsConfiguration;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Sergio González
 */
public class DefaultMentionsUserFinderImpl implements MentionsUserFinder {

	@Override
	public List<User> getUsers(
			long companyId, long userId, String query,
			SocialInteractionsConfiguration configuration)
		throws PortalException, SystemException {

		if (!configuration.isSocialInteractionsEnabled()) {
			return Collections.emptyList();
		}

		if (configuration.isSocialInteractionsAnyUserEnabled()) {
			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put("wildcardMode", WildcardMode.TRAILING);

			return UserLocalServiceUtil.search(
				companyId, query, WorkflowConstants.STATUS_APPROVED, params, 0,
				_MAX_USERS, new UserScreenNameComparator());
		}

		int[] types = getRelationTypes(configuration);

		long[] groupIds = getGroupIds(userId, configuration);

		boolean searchBySocialRelation =
			configuration.isSocialInteractionsSocialRelationTypesEnabled() &&
			types.length > 0;

		boolean searchByGroup =
			configuration.isSocialInteractionsSitesEnabled() &&
				groupIds.length > 0;

		if (searchBySocialRelation && searchByGroup) {
			return UserLocalServiceUtil.searchSocial(
				groupIds, userId, types, query, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}

		if (searchByGroup) {
			return UserLocalServiceUtil.searchSocial(
				companyId, groupIds, query, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}

		if (searchBySocialRelation) {
			return UserLocalServiceUtil.searchSocial(
				userId, types, query, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return Collections.emptyList();
	}

	protected int[] getRelationTypes(
		SocialInteractionsConfiguration configuration) {

		if (!configuration.isSocialInteractionsSocialRelationTypesEnabled()) {
			return _EMPTY_TYPES;
		}

		return configuration.getSocialInteractionsSocialRelationTypesArray();
	}

	private long[] getGroupIds(
			long userId, SocialInteractionsConfiguration configuration)
		throws PortalException, SystemException {

		if (!configuration.isSocialInteractionsSitesEnabled()) {
			return _EMPTY_GROUPS;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		return user.getGroupIds();
	}

	private static long[] _EMPTY_GROUPS = {};

	private static int[] _EMPTY_TYPES = {};

	private static int _MAX_USERS = 100;

}