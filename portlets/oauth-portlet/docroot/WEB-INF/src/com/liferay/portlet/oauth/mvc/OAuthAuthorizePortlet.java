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

package com.liferay.portlet.oauth.mvc;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.oauth.OAuthAccessor;
import com.liferay.portal.oauth.OAuthMessage;
import com.liferay.portal.oauth.OAuthProblemException;
import com.liferay.portal.oauth.OAuthProviderManagerUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.oauth.OAuthConstants;
import com.liferay.portlet.oauth.OAuthWebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.*;

import org.apache.commons.codec.digest.DigestUtils;
public class OAuthAuthorizePortlet extends MVCPortlet {

	public void authorize(ActionRequest request, ActionResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

		OAuthMessage requestMessage = OAuthProviderManagerUtil.getMessage(
				request, null);

		try {
			OAuthAccessor accessor = OAuthProviderManagerUtil.getAccessor(
				requestMessage);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					request);

			OAuthProviderManagerUtil.markAsAuthorized(
					accessor, serviceContext.getUserId(), serviceContext);

			returnToConsumer(request, response, accessor);
		}
		catch (Exception e) {
			if (e instanceof OAuthProblemException) {
				SessionErrors.add(
						request, ((OAuthProblemException) e).getProblem());
			}
			else {
				SessionErrors.add(request, e.getClass());
			}
		}
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {

		OAuthMessage requestMessage = OAuthProviderManagerUtil.getMessage(
			request, null);

		try {
			OAuthAccessor accessor = OAuthProviderManagerUtil.getAccessor(
				requestMessage);

			request.setAttribute(OAuthWebKeys.OAUTH_ACCESSOR, accessor);

			if (Boolean.TRUE.equals(
				accessor.getProperty(OAuthConstants.AUTHORIZED))) {

				SessionErrors.add(request, OAuthConstants.ALREADY_AUTHORIZED);
			}
		}
		catch (Exception e) {
			if (e instanceof OAuthProblemException) {
				SessionErrors.add(
					request, OAuthProblemException.class,
					((OAuthProblemException) e).getProblem());
			}
			else {
				SessionErrors.add(request, e.getClass());
			}
		}

		super.render(request, response);
	}

	private void returnToConsumer(
			ActionRequest request, ActionResponse response,
			OAuthAccessor accessor)
		throws Exception {

		// send the user back to site's callBackUrl
		String callback = request.getParameter(OAuthConstants.OAUTH_CALLBACK);

		if (OAuthConstants.NONE.equals(callback) &&
			(accessor.getConsumer().getCallbackURL() != null) &&
			(accessor.getConsumer().getCallbackURL().length() > 0)) {

			// first check if we have something in our properties file
			callback = accessor.getConsumer().getCallbackURL();
		}

		String token = accessor.getRequestToken();

		// for now use md5 of name + current time + token as secret
		String secretData = callback + System.nanoTime() + token;
		String verifier = DigestUtils.md5Hex(secretData);

		if (OAuthConstants.NONE.equals(callback) ) {
			request.setAttribute(OAuthWebKeys.VERIFIER, verifier);
			request.setAttribute(OAuthWebKeys.OAUTH_ACCESSOR, accessor);
		} else {
			// if callback is not passed in, use the callback from config
			if ((callback == null) || (callback.length() <= 0)) {
				callback = accessor.getConsumer().getCallbackURL();
			}

			if (token != null) {
				callback = OAuthProviderManagerUtil.addParameters(
					callback, OAuthConstants.OAUTH_TOKEN, token,
					OAuthConstants.OAUTH_VERIFIER, verifier);
			}

			response.sendRedirect(callback);
		}
	}

}