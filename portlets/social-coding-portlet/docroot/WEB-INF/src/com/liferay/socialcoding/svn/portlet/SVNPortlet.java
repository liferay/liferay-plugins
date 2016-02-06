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

package com.liferay.socialcoding.svn.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Eduardo Garcia
 */
public class SVNPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		String resourceID = resourceRequest.getResourceID();

		if (resourceID.equals("rss")) {
			try {
				serveRSS(resourceRequest, resourceResponse);
			}
			catch (Exception e) {
				throw new PortletException(e);
			}
		}
	}

	protected void serveRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		if (!PortalUtil.isRSSFeedsEnabled()) {
			PortalUtil.sendRSSFeedsDisabledError(
				resourceRequest, resourceResponse);

			return;
		}

		resourceResponse.setContentType(ContentTypes.TEXT_XML_UTF8);

		include(
			templatePath + "view_feeds.jsp", resourceRequest, resourceResponse);
	}

}