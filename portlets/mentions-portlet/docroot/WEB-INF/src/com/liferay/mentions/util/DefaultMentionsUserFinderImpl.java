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
			SocialInteractionsConfiguration socialInteractionsConfiguration)
		throws PortalException {

		if (socialInteractionsConfiguration.
				isSocialInteractionsAnyUserEnabled()) {

			LinkedHashMap<String, Object> params = new LinkedHashMap<>();

			params.put("wildcardMode", WildcardMode.TRAILING);

			return UserLocalServiceUtil.search(
				companyId, query, WorkflowConstants.STATUS_APPROVED, params, 0,
				_MAX_USERS, new UserScreenNameComparator());
		}

		User user = UserLocalServiceUtil.getUser(userId);

		int[] types =
			socialInteractionsConfiguration.
				getSocialInteractionsSocialRelationTypesArray();

		if (socialInteractionsConfiguration.
				isSocialInteractionsSocialRelationTypesEnabled() &&
			socialInteractionsConfiguration.
				isSocialInteractionsSitesEnabled()) {

			return UserLocalServiceUtil.searchSocial(
				user.getGroupIds(), userId, types, query, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}

		if (socialInteractionsConfiguration.
				isSocialInteractionsSitesEnabled()) {

			return UserLocalServiceUtil.searchSocial(
				companyId, user.getGroupIds(), query, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
		}

		if (socialInteractionsConfiguration.
				isSocialInteractionsSocialRelationTypesEnabled()) {

			return UserLocalServiceUtil.searchSocial(
				userId, types, query, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		return Collections.emptyList();
	}

	private static int _MAX_USERS = 100;

}