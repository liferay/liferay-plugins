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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.oauth.model.Application;
import com.liferay.portal.oauth.service.ApplicationLocalServiceUtil;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portlet.oauth.simulator.LiferayApi;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
public class SimulatorPortlet extends MVCPortlet {

	public void addOAuthorization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(actionRequest, "applicationId");

		if (0 != applicationId) {
			try {
				Application app = ApplicationLocalServiceUtil
										.fetchApplication(applicationId);

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
			throws IOException, PortletException {
		super.render(request, response);
	}

	public void setupOAuthSimulator(
		ActionRequest actionRequest, ActionResponse actionResponse)
	throws IOException, PortletException {

		int userCount = ParamUtil.getInteger(
				actionRequest, "oauth-simulator-user-cnt", 0);
		int vendorPercentage = ParamUtil.getInteger(
			actionRequest, "oauth-simulator-vendor-percentage", 0);
		int maxApplicationsPerUser = ParamUtil.getInteger(
			actionRequest, "oauth-simulator-max-applications", 0);

		if ((0 != userCount) && (0 != maxApplicationsPerUser)) {

			vendorPercentage = 100/vendorPercentage;

			try {
				ServiceContext sc = ServiceContextFactory.getInstance(
					actionRequest);

				if (_log.isInfoEnabled()) {
					_log.info("Generating test defaults...");
				}

				Role role = null;

				try {
					RoleServiceUtil.getRole(sc.getCompanyId(), OAUTH_ROLE_NAME);
				}
				catch (PortalException e) {
					_log.warn("Role "
						.concat(OAUTH_ROLE_NAME)
						.concat(" does not exist. Creating new role."));

					Map<Locale, String> titleMap =
									new HashMap<Locale, String>();
					titleMap.put(LocaleUtil.getDefault(), OAUTH_ROLE_TITLE);

					Map<Locale, String> descMap = new HashMap<Locale, String>();
					descMap.put(
						LocaleUtil.getDefault(), OAUTH_ROLE_DESCRIPTION);

					if (_log.isInfoEnabled()) {
						_log.info("Creating role ".concat(OAUTH_ROLE_NAME));
					}

					role =
						RoleServiceUtil.addRole(
							OAUTH_ROLE_NAME, titleMap, descMap,
							RoleConstants.TYPE_REGULAR);
				}

				long addedAppsCnt = 0L;
				String base = "oauths";

				for (int i=0; i<userCount; i++) {
					User u = UserServiceUtil.addUser(
						sc.getCompanyId(), false, "test", "test", true, null,
						base.concat(
							String.valueOf(i))
							.concat(".name@liferayt.com"),
						0L, StringPool.BLANK, LocaleUtil.getDefault(),
						"OAuth".concat(String.valueOf(i)), null,
						"Userman".concat(String.valueOf(i)), 0, 0, (i%2==0),
						(i%10 + 1), (i%27 + 1), (1950+(i%62)), null, null, null,
						null, null, false, sc);

					if (i%vendorPercentage == 0) {
						RoleServiceUtil.addUserRoles(
							u.getUserId(), new long[]{role.getRoleId()});

						StringBundler titleSb = new StringBundler(5);

						for (int added = 0, randomIdx;
								added < maxApplicationsPerUser; added++) {
							randomIdx = (int)(addedAppsCnt%APP_NAMES.length);

							titleSb.append((addedAppsCnt%2==0?"":"IT"));
							titleSb.append(" ");
							titleSb.append(APP_NAMES[randomIdx]);
							titleSb.append(" ");
							titleSb.append(String.valueOf(added%3));

							ApplicationLocalServiceUtil
							.addApplication(
								u.getUserId(), titleSb.toString(),
								APP_DESCRIPTION, APP_WEB_URL, APP_CALLBACK_URL,
								added%2, sc);

							addedAppsCnt++;
						}
					}

					if (_log.isInfoEnabled()) {
						_log.info("Generation done, new user: "
							.concat(u.getFirstName()));
					}
				}
			}
			catch (Exception e) {
				_log.error(e);

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

	public void verifyOAuthorization(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(actionRequest, "applicationId");

		if (0 != applicationId) {
			try {
				Application app = ApplicationLocalServiceUtil
										.fetchApplication(applicationId);

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

	private static final Log _log = LogFactoryUtil.getLog(
		SimulatorPortlet.class);

	private static final String APP_CALLBACK_URL =
			"http://www.liferay.com/test-oauth/callback";
	private static final String APP_DESCRIPTION =
			"Automatically added for testing OAuth Administration Portlets.";
	private static final String[] APP_NAMES = new String[] {
		"Mega", "AC", "Guru", "Sync", "Kato", "Ipon", "Luna", "Lungo", "Pluto",
		"Reco", "Perpetuo", "Lambda"};
	private static final String APP_WEB_URL = "http://oauth-test.liferay.com";
	private static final String OAUTH_ROLE_DESCRIPTION =
			"Oauth developer vendor etc";
	private static final String OAUTH_ROLE_NAME = "OAUTH Developer";
	private static final String OAUTH_ROLE_TITLE =
			"OAuth Application Developer";

}