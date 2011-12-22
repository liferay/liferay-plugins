/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.hook.events;

import com.liferay.marketplace.util.MarketplaceConstants;
import com.liferay.marketplace.util.MarketplaceUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Peter Shin
 */
public class LogoutPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		User user = PortalUtil.getUser(request);

		if (user == null) {
			return;
		}

		if (user.isDefaultUser()) {
			return;
		}

		if (!MarketplaceUtil.isMarketplaceAdmin(user)) {
			return;
		}

		String redirect = MarketplaceConstants.MARKETPLACE_URL_LOGOUT;

		redirect = HttpUtil.setParameter(
			redirect, "referer", PortalUtil.getCurrentCompleteURL(request));

		response.sendRedirect(redirect);
	}

}