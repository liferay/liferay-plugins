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

package com.liferay.shortlink.hook.filter;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.shortlink.util.PortletPropsValues;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miroslav Ligas
 */
public abstract class BaseShortLinkFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		String serverName = request.getServerName();

		return PortletPropsValues.SHORT_URL_HOSTNAME.equals(serverName);
	}

	protected abstract String getOriginalURL(String shortUrl)
		throws PortalException, SystemException;

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String servletPath = request.getServletPath();

		String shortUrl = servletPath.substring(1);

		String originalURL = null;

		try {
			originalURL = getOriginalURL(shortUrl);
		}
		catch (PortalException pe) {
			Log log = getLog();

			if (log.isInfoEnabled()) {
				log.info("Unable to find short link entry for URL " + shortUrl);
			}
		}
		catch (SystemException se) {
			Log log = getLog();

			if (log.isWarnEnabled()) {
				log.warn(
					"Unable to get short link entry for URL " + shortUrl, se);
			}
		}

		if (Validator.isNull(originalURL) &&
			Validator.isNotNull(
				PortletPropsValues.SHORT_URL_NOT_FOUND_REDIRECT)) {

			originalURL = PortletPropsValues.SHORT_URL_NOT_FOUND_REDIRECT;
		}

		if (Validator.isNotNull(originalURL)) {
			try {
				response.sendRedirect(originalURL);

				return;
			}
			catch (IOException ioe) {
				Log log = getLog();

				if (log.isWarnEnabled()) {
					log.warn("Unable to redirect to long URL", ioe);
				}
			}
		}

		processFilter(getClass(), request, response, filterChain);
	}

}