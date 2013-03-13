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

package com.liferay.so.activities.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.so.activities.model.SocialActivitySet;
import com.liferay.so.activities.service.base.SocialActivitySetLocalServiceBaseImpl;

/**
 * @author Jonathan Lee
 */
public class SocialActivitySetLocalServiceImpl
	extends SocialActivitySetLocalServiceBaseImpl {

	public SocialActivitySet addActivitySet(
			long userId, long activityId, String className, long classPK,
			int type)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		SocialActivity activity = SocialActivityLocalServiceUtil.getActivity(
			activityId);

		long activitySetId = counterLocalService.increment();

		SocialActivitySet activitySet = socialActivitySetPersistence.create(
			activitySetId);

		activitySet.setActivitySetId(activity.getActivityId());
		activitySet.setCompanyId(user.getCompanyId());
		activitySet.setUserId(userId);
		activitySet.setCreateDate(activity.getCreateDate());
		activitySet.setModifiedDate(activity.getCreateDate());
		activitySet.setClassName(className);
		activitySet.setClassPK(classPK);
		activitySet.setType(type);
		activitySet.setActivityCount(0);

		socialActivitySetPersistence.update(activitySet, false);

		socialActivityLocalService.addActivity(
			activitySet.getActivitySetId(), activityId);

		return activitySet;
	}

	public SocialActivitySet decrementActivityCount(long activitySetId)
		throws PortalException, SystemException {

		SocialActivitySet socialActivitySet =
			socialActivitySetPersistence.findByPrimaryKey(activitySetId);

		socialActivitySet.setActivityCount(
			socialActivitySet.getActivityCount() - 1);

		return socialActivitySetPersistence.update(socialActivitySet, false);
	}

	public SocialActivitySet deleteActivitySet(long activitySetId)
		throws PortalException, SystemException {

		socialActivityPersistence.removeByActivitySetId(activitySetId);

		return socialActivitySetPersistence.remove(activitySetId);
	}

	public SocialActivitySet deleteActivitySet(SocialActivitySet activitySet)
		throws SystemException {

		socialActivityPersistence.removeByActivitySetId(
			activitySet.getActivitySetId());

		return socialActivitySetPersistence.remove(activitySet);
	}

	public SocialActivitySet incrementActivityCount(long activitySetId)
		throws PortalException, SystemException {

		SocialActivitySet socialActivitySet =
			socialActivitySetPersistence.findByPrimaryKey(activitySetId);

		socialActivitySet.setActivityCount(
			socialActivitySet.getActivityCount() + 1);

		return socialActivitySetPersistence.update(socialActivitySet, false);
	}

}