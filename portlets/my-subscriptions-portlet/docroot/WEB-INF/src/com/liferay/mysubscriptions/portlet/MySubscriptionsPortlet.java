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

package com.liferay.mysubscriptions.portlet;

import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Peter Shin
 * @author Jonathan Lee
 */
public class MySubscriptionsPortlet extends MVCPortlet {

	public void unsubscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long[] subscriptionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "subscriptionIds"), 0L);

		for (long subscriptionId : subscriptionIds) {
			if (subscriptionId <= 0) {
				continue;
			}

			Subscription subscription =
				SubscriptionLocalServiceUtil.getSubscription(subscriptionId);

			if (themeDisplay.getUserId() != subscription.getUserId()) {
				throw new PrincipalException();
			}

			SubscriptionLocalServiceUtil.deleteSubscription(subscription);
		}
	}

}