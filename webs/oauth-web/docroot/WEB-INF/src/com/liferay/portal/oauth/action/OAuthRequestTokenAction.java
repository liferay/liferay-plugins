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
import com.liferay.portal.oauth.*;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Request Token request handler action.
 *
 * @author Ivica Cardic
 */
public class OAuthRequestTokenAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			OAuthMessage requestMessage = OAuthProviderManagerUtil.getMessage(
				request, null);

			OAuthConsumer consumer = OAuthProviderManagerUtil.getConsumer(
				requestMessage);

			OAuthAccessor accessor = new OAuthAccessorImpl(consumer);

			OAuthProviderManagerUtil.validateMessage(requestMessage, accessor);

			// Support the 'Variable Accessor Secret' extension
			// described in http://oauth.pbwiki.com/AccessorSecret
			String secret = requestMessage.getParameter(
				"oauth_accessor_secret");

			if (secret != null) {
				accessor.setProperty(OAuthConsumer.ACCESSOR_SECRET, secret);
			}

			// generate request_token and secret
			OAuthProviderManagerUtil.generateRequestToken(accessor);

			response.setContentType("text/plain");

			OutputStream out = response.getOutputStream();
			OAuthProviderManagerUtil.formEncode(
				accessor.getRequestToken(), accessor.getTokenSecret(), out);

			out.close();
		} catch (Exception e) {
			OAuthProviderManagerUtil.handleException(
				request, response, e, true);
		}

		return null;
	}

}