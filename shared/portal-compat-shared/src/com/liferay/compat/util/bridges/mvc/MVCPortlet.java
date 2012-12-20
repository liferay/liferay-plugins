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

package com.liferay.compat.util.bridges.mvc;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author Shinn Lok
 */
public class MVCPortlet extends com.liferay.util.bridges.mvc.MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		super.processAction(actionRequest, actionResponse);

		if (copyRequestParameters && !SessionErrors.isEmpty(actionRequest)) {
			UploadPortletRequest uploadPortletRequest =
				(UploadPortletRequest)actionRequest.getAttribute(
					PortalUtil.class.getName() + "#uploadPortletRequest");

			PortalUtil.copyRequestParameters(
				uploadPortletRequest, actionResponse);
		}
	}

}