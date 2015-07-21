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

package com.liferay.shortlink.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.shortlink.DuplicateShortLinkEntryException;
import com.liferay.shortlink.ShortLinkEntryOriginalURLException;
import com.liferay.shortlink.model.ShortLinkEntry;
import com.liferay.shortlink.service.ShortLinkEntryLocalServiceUtil;
import com.liferay.shortlink.util.PortletPropsValues;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Miroslav Ligas
 */
public class ShortLinkGeneratorPortlet extends MVCPortlet {

	public void addShortLinkEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String originalURL = ParamUtil.getString(actionRequest, "originalURL");

		ShortLinkEntry shortLinkEntry =
			ShortLinkEntryLocalServiceUtil.addShortLinkEntry(
				originalURL, null, true);

		actionResponse.setRenderParameter(
			"shortURL", getFullShortURL(shortLinkEntry));
	}

	protected String getFullShortURL(ShortLinkEntry shortLinkEntry) {
		return PortletPropsValues.SHORT_URL_HOSTNAME +
			StringPool.FORWARD_SLASH + shortLinkEntry.getShortURL();
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateShortLinkEntryException ||
			cause instanceof ShortLinkEntryOriginalURLException) {

			return true;
		}

		return super.isSessionErrorException(cause);
	}

}