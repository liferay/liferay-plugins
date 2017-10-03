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

package com.liferay.testoauth.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.testoauth.oauth.OAuthServiceHandler;
import com.liferay.testoauth.oauth.OAuthServiceHandlerFactory;
import com.liferay.testoauth.oauth.OAuthUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;

import org.scribe.model.Token;

/**
 * @author Igor Beslic
 */
public class TestOAuthPortlet extends MVCPortlet {

	public void resetOAuth(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		portletPreferences.reset("accessSecret");
		portletPreferences.reset("accessToken");
		portletPreferences.reset("accessURI");
		portletPreferences.reset("authorizeURI");
		portletPreferences.reset("key");
		portletPreferences.reset("hostName");
		portletPreferences.reset("hostPort");
		portletPreferences.reset("requestURI");
		portletPreferences.reset("secret");
		portletPreferences.reset("useServerProvidedCallbackUrl");
		portletPreferences.reset("windowState");

		portletPreferences.store();

		OAuthServiceHandlerFactory.resetServiceHandlers();
	}

	public void setupOAuth(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletSession portletSession = actionRequest.getPortletSession();

		Token requestToken = (Token)portletSession.getAttribute(
			Token.class.getName());

		String oAuthVerifier = ParamUtil.getString(
			actionRequest, "oauth_verifier");

		if (Validator.isNull(oAuthVerifier)) {
			SessionErrors.add(actionRequest, "authenticationFailed");

			return;
		}

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		OAuthServiceHandler oAuthServiceHandler =
			OAuthUtil.getOAuthServiceHandler(portletPreferences);

		Token token = oAuthServiceHandler.extractAccessToken(
			requestToken, oAuthVerifier);

		if (token == null) {
			SessionErrors.add(actionRequest, "authenticationFailed");
		}

		portletPreferences.setValue("accessSecret", token.getSecret());
		portletPreferences.setValue("accessToken", token.getToken());

		portletPreferences.store();
	}

	public void setupOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String accessURI = ParamUtil.getString(actionRequest, "accessURI");
		String authorizeURI = ParamUtil.getString(
			actionRequest, "authorizeURI");
		String key = ParamUtil.getString(actionRequest, "key");
		String hostName = ParamUtil.getString(actionRequest, "hostName");
		String hostPort = ParamUtil.getString(actionRequest, "hostPort");
		String requestURI = ParamUtil.getString(actionRequest, "requestURI");
		String secret = ParamUtil.getString(actionRequest, "secret");
		boolean useServerProvidedCallbackUrl = ParamUtil.getBoolean(
			actionRequest, "useServerProvidedCallbackUrl");
		String windowState = ParamUtil.getString(actionRequest, "windowState");

		if (Validator.isNotNull(accessURI) &&
			Validator.isNotNull(authorizeURI) &&
			Validator.isNotNull(hostName) && Validator.isNotNull(hostPort) &&
			Validator.isNotNull(key) && Validator.isNotNull(requestURI) &&
			Validator.isNotNull(secret)) {

			PortletPreferences portletPreferences =
				actionRequest.getPreferences();

			portletPreferences.setValue("accessURI", accessURI);
			portletPreferences.setValue("authorizeURI", authorizeURI);
			portletPreferences.setValue("key", key);
			portletPreferences.setValue("hostName", hostName);
			portletPreferences.setValue("hostPort", hostPort);
			portletPreferences.setValue("requestURI", requestURI);
			portletPreferences.setValue("secret", secret);
			portletPreferences.setValue(
				"useServerProvidedCallbackUrl",
				String.valueOf(useServerProvidedCallbackUrl));
			portletPreferences.setValue("windowState", windowState);

			portletPreferences.store();
		}
	}

}