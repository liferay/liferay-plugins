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

package com.liferay.portal.oauth.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.oauth.OAuthAccessor;
import com.liferay.portal.oauth.OAuthMessage;
import com.liferay.portal.oauth.OAuthProblemException;
import com.liferay.portal.oauth.OAuthProviderManagerUtil;
import com.liferay.portlet.oauth.OAuthConstants;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Access Token request handler action.
 *
 * @author Ivica Cardic
 */
public class OAuthAccessTokenAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			OAuthMessage requestMessage = OAuthProviderManagerUtil.getMessage(
				request, null);

			OAuthAccessor accessor = OAuthProviderManagerUtil.getAccessor(
				requestMessage);

			OAuthProviderManagerUtil.validateMessage(requestMessage, accessor);

			// make sure token is authorized
			if (!Boolean.TRUE.equals(accessor.getProperty(
					OAuthConstants.AUTHORIZED))) {
				throw new OAuthProblemException(
					OAuthProblemException.PERMISSION_DENIED);
			}

			long userId = (Long)accessor.getProperty(OAuthConstants.USER);

			// generate access token and secret
			OAuthProviderManagerUtil.generateAccessToken(accessor, userId);

			response.setContentType("text/plain");

			OutputStream out = response.getOutputStream();
			OAuthProviderManagerUtil
				.formEncode(
					accessor.getAccessToken(), accessor.getTokenSecret(), out);

			out.close();

		} catch (Exception e) {
			OAuthProviderManagerUtil.handleException(
				request, response, e, true);
		}

		return null;
	}

}