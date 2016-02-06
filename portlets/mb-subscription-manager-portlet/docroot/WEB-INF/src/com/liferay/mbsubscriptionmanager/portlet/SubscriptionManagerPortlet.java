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

package com.liferay.mbsubscriptionmanager.portlet;

import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class SubscriptionManagerPortlet extends MVCPortlet {

	public void subscribeUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long mbCategoryId = ParamUtil.getLong(actionRequest, "mbCategoryId");

		MBCategory mbCategory = MBCategoryLocalServiceUtil.getMBCategory(
			mbCategoryId);

		long[] userIds = ParamUtil.getLongValues(actionRequest, "userIds");

		for (long userId : userIds) {
			SubscriptionLocalServiceUtil.addSubscription(
				userId, mbCategory.getGroupId(), MBCategory.class.getName(),
				mbCategory.getCategoryId());
		}
	}

	public void unsubscribeUsers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long mbCategoryId = ParamUtil.getLong(actionRequest, "mbCategoryId");

		MBCategory mbCategory = MBCategoryLocalServiceUtil.getMBCategory(
			mbCategoryId);

		long[] userIds = ParamUtil.getLongValues(actionRequest, "userIds");

		for (long userId : userIds) {
			if (!SubscriptionLocalServiceUtil.isSubscribed(
					mbCategory.getCompanyId(), userId,
					MBCategory.class.getName(), mbCategoryId)) {

				continue;
			}

			SubscriptionLocalServiceUtil.deleteSubscription(
				userId, MBCategory.class.getName(), mbCategoryId);
		}
	}

}