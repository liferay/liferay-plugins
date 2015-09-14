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

package com.liferay.marketplace.oauth.util;

import com.liferay.marketplace.configuration.PortletPropsValues;
import com.liferay.marketplace.oauth.api.MarketplaceApi;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import org.scribe.builder.api.Api;
import org.scribe.model.OAuthConfig;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * @author Ryan Park
 */
public class OAuthUtil {

	public static void deleteAccessToken(User user)
		throws PortalException, SystemException {

		ExpandoValueLocalServiceUtil.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId());
		ExpandoValueLocalServiceUtil.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId());
	}

	public static void deleteRequestToken(User user)
		throws PortalException, SystemException {

		ExpandoValueLocalServiceUtil.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId());
		ExpandoValueLocalServiceUtil.deleteValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId());
	}

	public static Token getAccessToken(User user)
		throws PortalException, SystemException {

		ExpandoValue secretExpandoValue =
			ExpandoValueLocalServiceUtil.getValue(
				user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
				user.getUserId());
		ExpandoValue tokenExpandoValue =
			ExpandoValueLocalServiceUtil.getValue(
				user.getCompanyId(), User.class.getName(), "MP", "accessToken",
				user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public static OAuthService getOAuthService() {
		Api api = new MarketplaceApi();

		OAuthConfig oAuthConfig = new OAuthConfig(
			PortletPropsValues.MARKETPLACE_KEY,
			PortletPropsValues.MARKETPLACE_SECRET,
			PortletPropsValues.MARKETPLACE_URL, SignatureType.Header, null,
			null);

		return api.createService(oAuthConfig);
	}

	public static Token getRequestToken(User user)
		throws PortalException, SystemException {

		ExpandoValue secretExpandoValue =
			ExpandoValueLocalServiceUtil.getValue(
				user.getCompanyId(), User.class.getName(), "MP",
				"requestSecret", user.getUserId());
		ExpandoValue tokenExpandoValue =
			ExpandoValueLocalServiceUtil.getValue(
				user.getCompanyId(), User.class.getName(), "MP", "requestToken",
				user.getUserId());

		if ((secretExpandoValue == null) || (tokenExpandoValue == null)) {
			return null;
		}

		return new Token(
			tokenExpandoValue.getString(), secretExpandoValue.getString());
	}

	public static void updateAccessToken(User user, Token token)
		throws PortalException, SystemException {

		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessSecret",
			user.getUserId(), token.getSecret());
		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "accessToken",
			user.getUserId(), token.getToken());
	}

	public static void updateRequestToken(User user, Token token)
		throws PortalException, SystemException {

		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestSecret",
			user.getUserId(), token.getSecret());
		ExpandoValueLocalServiceUtil.addValue(
			user.getCompanyId(), User.class.getName(), "MP", "requestToken",
			user.getUserId(), token.getToken());
	}

}