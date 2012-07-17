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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portlet.oauth.OAuthConstants;
import com.liferay.portlet.oauth.simulator.LiferayApi;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
public class OAuthSimulatorPortlet extends MVCPortlet {

	public void addOAuthorization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(
				actionRequest, OAuthConstants.WEB_APP_ID);

		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);

				OAuthService oAuthService = new ServiceBuilder()
					.provider(LiferayApi.class)
					.apiKey(app.getConsumerKey())
					.apiSecret(app.getConsumerSecret()).build();

				Token requestToken = oAuthService.getRequestToken();

				actionRequest.setAttribute(
						"oauth-simulator-url",
						LiferayApi.AUTHORIZE_URL.replace(
							"%s", requestToken.getToken()));
				actionRequest.setAttribute(
						"oauth-simulator-token", requestToken.getToken());
				actionRequest.setAttribute(
						"oauth-simulator-secret", requestToken.getSecret());
				actionRequest.setAttribute(
						"applicationId", Long.toString(applicationId));
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(actionRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
		}
		else {
			SessionErrors.add(
					actionRequest, "cant-complete-operation-without-id");
		}
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.render(request, response);
	}

	public void verifyOAuthorization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(
				actionRequest, OAuthConstants.WEB_APP_ID);

		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);

				OAuthService oAuthService = new ServiceBuilder()
					.provider(LiferayApi.class)
					.apiKey(app.getConsumerKey())
					.apiSecret(app.getConsumerSecret()).build();

				String tokenString = ParamUtil.getString(
						actionRequest, "oauth-simulator-token");
				String secretString = ParamUtil.getString(
						actionRequest, "oauth-simulator-secret");
				String verifierString = ParamUtil.getString(
						actionRequest, "oauth-simulator-verifier");

				Verifier verifier = new Verifier(verifierString);

				Token requestToken = new Token(tokenString, secretString);

				Token accessToken = oAuthService
						.getAccessToken(requestToken, verifier);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(actionRequest, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
		}
		else {
			SessionErrors.add(
				actionRequest, "cant-complete-operation-without-id");
		}
	}

}