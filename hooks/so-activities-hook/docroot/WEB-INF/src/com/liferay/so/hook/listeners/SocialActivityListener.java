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

package com.liferay.so.hook.listeners;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.so.activities.service.SocialActivityLocalServiceUtil;

/**
 * @author Matthew Kong
 */
public class SocialActivityListener extends BaseModelListener<SocialActivity> {

	@Override
	public void onAfterCreate(SocialActivity activity)
		throws ModelListenerException {

		try {
			if (activity.getMirrorActivityId() > 0) {
				return;
			}

			SocialActivityLocalServiceUtil.addActivity(
				activity.getActivityId());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(SocialActivity activity)
		throws ModelListenerException {

		try {
			if (SocialActivityLocalServiceUtil.fetchSocialActivity(
					activity.getActivityId()) != null) {

				SocialActivityLocalServiceUtil.deleteActivity(
					activity.getActivityId());
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}