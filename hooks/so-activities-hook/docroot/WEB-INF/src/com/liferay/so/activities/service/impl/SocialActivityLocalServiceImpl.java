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

package com.liferay.so.activities.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.so.activities.model.SocialActivity;
import com.liferay.so.activities.service.base.SocialActivityLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityLocalServiceImpl
	extends SocialActivityLocalServiceBaseImpl {

	public void addActivity(long activityId)
		throws PortalException, SystemException {

		SocialActivity activity = socialActivityPersistence.create(activityId);

		socialActivityPersistence.update(activity, false);

		socialActivityInterpreterLocalService.updateActivitySet(
			activity.getActivityId());
	}

	public void deleteActivities(long[] activityIds)
		throws PortalException, SystemException {

		for (long activityId : activityIds) {
			deleteActivity(activityId);
		}
	}

	public void deleteActivity(long activityId)
		throws PortalException, SystemException {

		SocialActivity activity = socialActivityPersistence.findByPrimaryKey(
			activityId);

		socialActivitySetLocalService.decrementActivityCount(
			activity.getActivitySetId());

		socialActivityPersistence.remove(activityId);
	}

	public SocialActivity getActivity(long activityId)
		throws PortalException, SystemException {

		return socialActivityPersistence.findByPrimaryKey(activityId);
	}

	public List<SocialActivity> getActivitySetActivities(
			long activitySetId, int start, int end)
		throws SystemException {

		return socialActivityPersistence.findByActivitySetId(
			activitySetId, start, end);
	}

}