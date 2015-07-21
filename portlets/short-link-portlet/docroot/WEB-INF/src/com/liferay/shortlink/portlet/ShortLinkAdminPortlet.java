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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.shortlink.DuplicateShortLinkEntryException;
import com.liferay.shortlink.ShortLinkEntryOriginalURLException;
import com.liferay.shortlink.ShortLinkEntryShortURLException;
import com.liferay.shortlink.service.ShortLinkEntryLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Miroslav Ligas
 */
public class ShortLinkAdminPortlet extends MVCPortlet {

	public void deleteShortLinkEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		long shortLinkEntryId = ParamUtil.getLong(
			actionRequest, "shortLinkEntryId");

		ShortLinkEntryLocalServiceUtil.deleteShortLinkEntry(shortLinkEntryId);
	}

	public void updateShortLinkEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		long shortLinkEntryId = ParamUtil.getLong(
			actionRequest, "shortLinkEntryId");

		String originalURL = ParamUtil.getString(actionRequest, "originalURL");
		String shortURL = ParamUtil.getString(actionRequest, "shortURL");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (shortLinkEntryId > 0) {
			ShortLinkEntryLocalServiceUtil.updateShortLinkEntry(
				shortLinkEntryId, originalURL, shortURL, active);
		}
		else {
			ShortLinkEntryLocalServiceUtil.addShortLinkEntry(
				originalURL, shortURL, false);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateShortLinkEntryException ||
			cause instanceof ShortLinkEntryOriginalURLException ||
			cause instanceof ShortLinkEntryShortURLException) {

			return true;
		}

		return super.isSessionErrorException(cause);
	}

}