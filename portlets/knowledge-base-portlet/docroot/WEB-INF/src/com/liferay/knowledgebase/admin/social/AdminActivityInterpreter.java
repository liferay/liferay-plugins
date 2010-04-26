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

package com.liferay.knowledgebase.admin.social;

import com.liferay.knowledgebase.model.Article;
import com.liferay.knowledgebase.service.ArticleLocalServiceUtil;
import com.liferay.knowledgebase.service.permission.ArticlePermission;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * <a href="AdminActivityInterpreter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class AdminActivityInterpreter extends BaseSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!ArticlePermission.contains(
				permissionChecker, activity.getClassPK(), ActionKeys.VIEW)) {

			return null;
		}

		// Link

		String link = StringPool.BLANK;

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			activity.getGroupId(), "1_WAR_knowledgebaseportlet");

		if (Validator.isNotNull(layoutFullURL)) {
			link =
				layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR +
					"admin/article/" + activity.getClassPK();
		}

		// Title

		String title = StringPool.BLANK;

		String groupName = StringPool.BLANK;

		if (activity.getGroupId() != themeDisplay.getScopeGroupId()) {
			groupName = getGroupName(activity.getGroupId(), themeDisplay);
		}

		String pattern = StringPool.BLANK;

		if (activity.getType() == AdminActivityKeys.ADD_ARTICLE) {
			pattern = "activity-knowledge-base-admin-add-article";
		}
		else if (activity.getType() == AdminActivityKeys.UPDATE_ARTICLE) {
			pattern = "activity-knowledge-base-admin-update-article";
		}

		if (Validator.isNotNull(groupName)) {
			pattern += "-in";
		}

		Object[] arguments = new Object[0];

		Article article = ArticleLocalServiceUtil.getArticleByResourcePrimKey(
			activity.getClassPK());

		String text = HtmlUtil.escape(cleanContent(article.getTitle()));

		if (Validator.isNotNull(link)) {
			text = wrapLink(link, text);
		}

		arguments = new Object[] {
			getUserName(activity.getUserId(), themeDisplay), text, groupName
		};

		title = themeDisplay.translate(pattern, arguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		Article.class.getName()
	};

}