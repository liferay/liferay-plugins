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

package com.liferay.so.activities.model;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public abstract class BaseSocialActivityInterpreter
	extends com.liferay.compat.portlet.social.model.
				BaseSocialActivityInterpreter
	implements SocialActivityInterpreter {

	public long getActivitySetId(long activityId) {
		return 0;
	}

	public String getSelector() {
		return StringPool.BLANK;
	}

	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet, ServiceContext serviceContext) {

		try {
			List<SocialActivity> activities =
				SocialActivityLocalServiceUtil.getActivitySetActivities(
					activitySet.getActivitySetId(), 0, 1);

			if (!activities.isEmpty()) {
				SocialActivity activity = activities.get(0);

				return doInterpret(
					activity.getPortalSocialActivity(), serviceContext);
			}
		}
		catch (Exception e) {
			_log.error("Unable to interpret activity set", e);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialActivityInterpreter.class);

}