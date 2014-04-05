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

import com.liferay.compat.portal.service.ServiceContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.so.activities.model.SocialActivity;
import com.liferay.so.activities.model.SocialActivityInterpreter;
import com.liferay.so.activities.model.SocialActivitySet;
import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;
import com.liferay.so.activities.service.SocialActivitySetLocalServiceUtil;
import com.liferay.so.activities.service.persistence.SocialActivityUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 * @author Matthew Kong
 */
public abstract class BaseSocialActivityInterpreter
	extends com.liferay.compat.portlet.social.model.
				BaseSocialActivityInterpreter
	implements SocialActivityInterpreter {

	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet,
		com.liferay.portal.service.ServiceContext serviceContext) {

		try {
			return doInterpret(activitySet, new ServiceContext(serviceContext));
		}
		catch (Exception e) {
			_log.error("Unable to interpret activity set", e);
		}

		return null;
	}

	public void updateActivitySet(long activityId)
		throws PortalException, SystemException {

		SocialActivity activity = SocialActivityUtil.fetchByPrimaryKey(
			activityId);

		if ((activity == null) || (activity.getActivitySetId() > 0)) {
			return;
		}

		long activitySetId = getActivitySetId(activityId);

		if (activitySetId > 0) {
			SocialActivitySetLocalServiceUtil.incrementActivityCount(
				activitySetId, activityId);
		}
		else {
			SocialActivitySetLocalServiceUtil.addActivitySet(activityId);
		}
	}

	protected SocialActivityFeedEntry doInterpret(
			SocialActivitySet activitySet, ServiceContext serviceContext)
		throws Exception {

		List<SocialActivity> activities =
			SocialActivityLocalServiceUtil.getActivitySetActivities(
				activitySet.getActivitySetId(), 0, 1);

		if (!activities.isEmpty()) {
			SocialActivity activity = activities.get(0);

			return doInterpret(
				activity.getPortalSocialActivity(), serviceContext);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseSocialActivityInterpreter.class);

}