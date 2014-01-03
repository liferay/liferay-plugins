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
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivitySet;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivitySetLocalServiceUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;

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
			SocialActivitySet activitySet = null;

			boolean comment = false;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if ((activity.getType() ==
					SocialActivityKeyConstants.BLOGS_ADD_COMMENT) ||
				(activity.getType() ==
					SocialActivityConstants.TYPE_ADD_COMMENT)) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getClassNameId(), activity.getClassPK(),
						activity.getType());

				comment = true;
			}
			else if (activity.getType() ==
						SocialActivityKeyConstants.BLOGS_UPDATE_ENTRY) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, comment)) {
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

		if ((activitySet.getType() ==
				SocialActivityKeyConstants.BLOGS_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityKeyConstants.BLOGS_UPDATE_ENTRY) ||
			(activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT)) {

			if (!hasPermissions(activitySet, serviceContext)) {
				return null;
			}

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
	protected SocialActivityFeedEntry getSubfeedEntry(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		String title = getPageTitle(
			activity.getClassName(), activity.getClassPK(), serviceContext);

		BlogsEntry entry = BlogsEntryLocalServiceUtil.getEntry(
			activity.getClassPK());

		String content = HtmlUtil.extractText(entry.getContent());

		String body = StringUtil.shorten(content, 200);

		return new SocialActivityFeedEntry(title, body);
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		if ((activity.getType() ==
				SocialActivityKeyConstants.BLOGS_ADD_COMMENT) ||
			(activity.getType() == SocialActivityConstants.TYPE_ADD_COMMENT)) {

			return "commented-on-a-blog-entry";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.BLOGS_ADD_ENTRY) {

			return "wrote-a-new-blog-entry";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.BLOGS_UPDATE_ENTRY) {

			return "updated-a-blog-entry";
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if ((activitySet.getType() ==
				SocialActivityKeyConstants.BLOGS_ADD_COMMENT) ||
			(activitySet.getType() ==
				SocialActivityConstants.TYPE_ADD_COMMENT)) {

			return "commented-on-a-blog-entry";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.BLOGS_ADD_ENTRY) {

			return "wrote-x-new-blog-entries";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.BLOGS_UPDATE_ENTRY) {

			return "made-x-updates-to-a-blog-entry";
		}

		return StringPool.BLANK;
	}

	private static final String[] _CLASS_NAMES = {BlogsEntry.class.getName()};

}