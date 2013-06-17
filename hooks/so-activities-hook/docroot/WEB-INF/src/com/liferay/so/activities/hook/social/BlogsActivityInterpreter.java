/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.so.activities.hook.social;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityConstants;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class BlogsActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			SocialActivitySet activitySet = null;

			if ((activity.getType() == _ACTIVITY_KEY_ADD_COMMENT) ||
				(activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) ||
				(activity.getType() ==
					SocialActivityConstants.TYPE_ADD_COMMENT)) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getUserActivitySet(
						activity.getGroupId(), activity.getUserId(),
						activity.getClassNameId(), activity.getType());
			}
			else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet)) {
				return activitySet.getActivitySetId();
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	@Override
	protected String getBody(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getBody(
			activity.getClassName(), activity.getClassPK(), serviceContext);
	}

	@Override
	protected String getBody(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		if (activitySet.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return getBody(
				activitySet.getClassName(), activitySet.getClassPK(),
				serviceContext);
		}

		return super.getBody(activitySet, serviceContext);
	}

	protected String getBody(
			String className, long classPK, ServiceContext serviceContext)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"blogs-page-content\">");

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(classPK);

		String content = HtmlUtil.extractText(entry.getContent());

		sb.append(StringUtil.shorten(content, 200));

		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getTitlePattern(
		String groupName,
		com.liferay.portlet.social.model.SocialActivity activity) {

		if (activity.getType() == _ACTIVITY_KEY_ADD_COMMENT) {
			return "commented-on-a-blog-entry";
		}
		else if (activity.getType() == _ACTIVITY_KEY_ADD_ENTRY) {
			return "wrote-a-new-blog-entry";
		}
		else if (activity.getType() == _ACTIVITY_KEY_UPDATE_ENTRY) {
			return "updated-a-blog-entry";
		}

		return StringPool.BLANK;
	}

	/**
	 * {@link com.liferay.portlet.blogs.social.BlogsActivityKeys#ADD_COMMENT}
	 */
	private static final int _ACTIVITY_KEY_ADD_COMMENT = 1;

	/**
	 * {@link com.liferay.portlet.blogs.social.BlogsActivityKeys#ADD_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_ADD_ENTRY = 2;

	/**
	 * {@link com.liferay.portlet.blogs.social.BlogsActivityKeys#UPDATE_ENTRY}
	 */
	private static final int _ACTIVITY_KEY_UPDATE_ENTRY = 3;

	private static final String[] _CLASS_NAMES = {BlogsEntry.class.getName()};

}