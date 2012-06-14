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

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.oauth.OAuthAccessor;
import com.liferay.portal.oauth.OAuthMessage;
import com.liferay.portal.oauth.OAuthProblemException;
import com.liferay.portal.oauth.OAuthProviderManagerUtil;
import com.liferay.portal.oauth.util.OAuthConstants;
import com.liferay.portal.oauth.util.OAuthWebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Authorization request handler action.
 *
 * @author Ivica Cardic
 */
public class OAuthAuthorizeAction extends BaseStrutsAction{

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String cmd = ParamUtil.getString(request, Constants.CMD);

		OAuthMessage requestMessage = OAuthProviderManagerUtil.getMessage(
			request, null);

		OAuthAccessor accessor = null;

		try {
			accessor = OAuthProviderManagerUtil.getAccessor(requestMessage);

			if (Validator.isNull(cmd)) {
				if (Boolean.TRUE.equals(accessor.getProperty(
					OAuthConstants.AUTHORIZED))) {

					return returnToConsumer(request, response, accessor);
				}else {
					String callback = request.getParameter(
						OAuthConstants.OAUTH_CALLBACK);
					if ((callback == null) || (callback.length() <= 0)) {
						callback = OAuthConstants.NONE;
					}

					request.setAttribute(OAuthWebKeys.CALLBACK, callback);
					request.setAttribute(
						OAuthWebKeys.TOKEN, accessor.getRequestToken());
				}
			}
			else if (cmd.equals(Constants.UPDATE)) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);

				OAuthProviderManagerUtil.markAsAuthorized(
					accessor, themeDisplay.getUserId());

				return returnToConsumer(request, response, accessor);
			}
		}
		catch (Exception e) {
			if (e instanceof PrincipalException) {
				SessionErrors.add(request, e.getClass().getName());
			} else if (e instanceof OAuthProblemException) {
				SessionErrors.add(
					request, ((OAuthProblemException) e).getProblem());
			}
			else {
				PortalUtil.sendError(e, request, response);

				return null;
			}
		}

		return sendToAuthorizePage(request, response, accessor);
	}

	private String returnToConsumer(
			HttpServletRequest request, HttpServletResponse response,
			OAuthAccessor accessor)
		throws Exception{

		// send the user back to site's callBackUrl
		String callback = request.getParameter(OAuthConstants.OAUTH_CALLBACK);

		if (OAuthConstants.NONE.equals(callback)
			&& (accessor.getConsumer().getCallbackURL() != null)
			&& (accessor.getConsumer().getCallbackURL().length() > 0)) {

			// first check if we have something in our properties file
			callback = accessor.getConsumer().getCallbackURL();
		}

		String token = accessor.getRequestToken();

		// for now use md5 of name + current time + token as secret
		String secretData = callback + System.nanoTime() + token;
		String verifier = DigestUtils.md5Hex(secretData);

		if (token != null) {
			if (OAuthConstants.NONE.equals(callback) ) {
				SessionMessages.add(
					request, OAuthConstants.SUCCESS_AUTHORIZATION);

				request.setAttribute(OAuthWebKeys.VERIFIER, verifier);

				return sendToAuthorizePage(request, response, accessor);
			} else {
				// if callback is not passed in, use the callback from config
				if ((callback == null) || (callback.length() <=0)) {
					callback = accessor.getConsumer().getCallbackURL();
				}

				callback = OAuthProviderManagerUtil.addParameters(
					callback, OAuthConstants.OAUTH_TOKEN, token,
					OAuthConstants.OAUTH_VERIFIER, verifier);
			}

			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", callback);
		}

		return null;
	}

	private String sendToAuthorizePage(
		HttpServletRequest request, HttpServletResponse response,
		OAuthAccessor accessor) {

		if (accessor != null) {
			request.setAttribute(
				OAuthWebKeys.NAME, accessor.getConsumer()
				.getOAuthApplication().getName());
			request.setAttribute(
				OAuthWebKeys.WEB_SITE, accessor.getConsumer()
				.getOAuthApplication().getWebsite());
			request.setAttribute(
				OAuthWebKeys.ACCESS_LEVEL, accessor.getConsumer()
				.getOAuthApplication().getAccessLevel());
			request.setAttribute(
				OAuthWebKeys.DESCRIPTION, accessor.getConsumer()
				.getOAuthApplication().getDescription());
		}

		return VIEW_URL;

	}

	private static final String VIEW_URL = "/portal/oauth/authorize.jsp";

}