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

package com.liferay.so.activities.hook.social;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.so.activities.model.SocialActivitySet;
import com.liferay.so.activities.service.SocialActivitySetLocalServiceUtil;
import com.liferay.so.activities.util.SocialActivityKeyConstants;

import java.text.Format;

/**
 * @author Evan Thibodeau
 * @author Matthew Kong
 */
public class CalendarActivityInterpreter extends SOSocialActivityInterpreter {

	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected long getActivitySetId(long activityId) {
		try {
			SocialActivitySet activitySet = null;

			SocialActivity activity =
				SocialActivityLocalServiceUtil.getActivity(activityId);

			if (activity.getType() ==
					SocialActivityKeyConstants.CALENDAR_UPDATE_EVENT) {

				activitySet =
					SocialActivitySetLocalServiceUtil.getClassActivitySet(
						activity.getUserId(), activity.getClassNameId(),
						activity.getClassPK(), activity.getType());
			}

			if ((activitySet != null) && !isExpired(activitySet, false)) {
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

		if (activitySet.getType() ==
				SocialActivityKeyConstants.CALENDAR_UPDATE_EVENT) {

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

		StringBundler sb = new StringBundler(15);

		sb.append("<div class=\"activity-body\"><div class=\"title\">");
		sb.append(getPageTitle(className, classPK, serviceContext));
		sb.append("</div><div class=\"date\"><strong>");
		sb.append(serviceContext.translate("date"));
		sb.append(": </strong>");

		Format dateFormatDate = getFormatDateTime(
			serviceContext.getLocale(), serviceContext.getTimeZone());

		CalEvent event = CalEventLocalServiceUtil.getEvent(classPK);

		sb.append(dateFormatDate.format((event.getStartDate())));

		sb.append("</div><div class=\"location\"><strong>");
		sb.append(serviceContext.translate("location"));
		sb.append(": </strong>");
		sb.append(event.getLocation());
		sb.append("</div><div class=\"description\"><strong>");
		sb.append(serviceContext.translate("description"));
		sb.append(": </strong>");

		AssetRenderer assetRenderer = getAssetRenderer(className, classPK);

		sb.append(
			StringUtil.shorten(
				assetRenderer.getSummary(serviceContext.getLocale()), 200));

		sb.append("</div></div>");

		return sb.toString();
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		if (activity.getType() ==
				SocialActivityKeyConstants.CALENDAR_ADD_EVENT) {

			return "added-a-new-calendar-event";
		}
		else if (activity.getType() ==
					SocialActivityKeyConstants.CALENDAR_UPDATE_EVENT) {

			return "updated-a-calendar-event";
		}

		return StringPool.BLANK;
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivitySet activitySet) {

		if (activitySet.getType() ==
				SocialActivityKeyConstants.CALENDAR_ADD_EVENT) {

			return "added-x-new-calendar-events";
		}
		else if (activitySet.getType() ==
					SocialActivityKeyConstants.CALENDAR_UPDATE_EVENT) {

			return "made-x-updates-to-a-calendar-event";
		}

		return StringPool.BLANK;
	}

	private static final String[] _CLASS_NAMES = {CalEvent.class.getName()};

}