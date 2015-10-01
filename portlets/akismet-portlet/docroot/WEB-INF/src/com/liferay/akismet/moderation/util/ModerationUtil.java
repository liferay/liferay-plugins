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

package com.liferay.akismet.moderation.util;

import com.liferay.akismet.util.AkismetConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageLocalServiceUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ModerationUtil {

	public static List<MBMessage> getDiscussionMBMessages(
			long scopeGroupId, int start, int end)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, true);

		return MBMessageLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	public static int getDiscussionMBMessagesCount(long scopeGroupId)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, true);

		return (int)MBMessageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	public static List<MBMessage> getMBMessages(
			long scopeGroupId, int start, int end)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, false);

		return MBMessageLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	public static int getMBMessagesCount(long scopeGroupId)
		throws PortalException {

		DynamicQuery dynamicQuery = buildMBMessageDynamicQuery(
			scopeGroupId, false);

		return (int)MBMessageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	public static List<WikiPage> getWikiPages(
			long scopeGroupId, int start, int end)
		throws PortalException {

		DynamicQuery dynamicQuery = buildWikiPageDynamicQuery(scopeGroupId);

		return WikiPageLocalServiceUtil.dynamicQuery(dynamicQuery, start, end);
	}

	public static int getWikiPagesCount(long scopeGroupId)
		throws PortalException {

		DynamicQuery dynamicQuery = buildWikiPageDynamicQuery(scopeGroupId);

		return (int)WikiPageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	protected static DynamicQuery buildMBMessageDynamicQuery(
			long scopeGroupId, boolean discussion)
		throws PortalException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			MBMessage.class);

		Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

		if (!group.isCompany()) {
			Property groupIdProperty = PropertyFactoryUtil.forName("groupId");

			Long[] scopeGroupIds = getChildScopeGroupIds(scopeGroupId);

			dynamicQuery.add(groupIdProperty.in(scopeGroupIds));
		}

		Property categoryIdProperty = PropertyFactoryUtil.forName("categoryId");

		if (discussion) {
			dynamicQuery.add(
				categoryIdProperty.eq(
					MBCategoryConstants.DISCUSSION_CATEGORY_ID));
		}
		else {
			dynamicQuery.add(
				categoryIdProperty.ne(
					MBCategoryConstants.DISCUSSION_CATEGORY_ID));
		}

		Property statusProperty = PropertyFactoryUtil.forName("status");

		dynamicQuery.add(statusProperty.eq(WorkflowConstants.STATUS_DENIED));

		return dynamicQuery;
	}

	protected static DynamicQuery buildWikiPageDynamicQuery(long scopeGroupId)
		throws PortalException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			WikiPage.class);

		Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);

		if (!group.isCompany()) {
			Property groupIdProperty = PropertyFactoryUtil.forName("groupId");

			Long[] scopeGroupIds = getChildScopeGroupIds(scopeGroupId);

			dynamicQuery.add(groupIdProperty.in(scopeGroupIds));
		}

		Property summaryProperty = PropertyFactoryUtil.forName("summary");

		dynamicQuery.add(
			summaryProperty.eq(AkismetConstants.WIKI_PAGE_PENDING_APPROVAL));

		return dynamicQuery;
	}

	protected static Long[] getChildScopeGroupIds(long parentGroupId) {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Group.class);

		Property parentGroupIdProperty = PropertyFactoryUtil.forName(
			"parentGroupId");

		dynamicQuery.add(parentGroupIdProperty.eq(parentGroupId));

		List<Group> groups = GroupLocalServiceUtil.dynamicQuery(dynamicQuery);

		Long[] scopeGroupIds = new Long[groups.size() + 1];

		scopeGroupIds[0] = parentGroupId;

		for (int i = 0; i < groups.size(); i++) {
			Group group = groups.get(i);

			scopeGroupIds[i + 1] = group.getGroupId();
		}

		return scopeGroupIds;
	}

}