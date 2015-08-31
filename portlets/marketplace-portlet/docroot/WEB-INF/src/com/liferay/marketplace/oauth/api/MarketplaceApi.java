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

package com.liferay.marketplace.oauth.api;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.marketplace.configuration.PortletPropsValues;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;

/**
 * @author Ryan Park
 */
public class MarketplaceApi extends DefaultApi10a {

	@Override
	public String getAccessTokenEndpoint() {
		return _ACCESS_TOKEN_ENDPOINT_URL;
	}

	@Override
	public String getAuthorizationUrl(Token token) {
		return HttpUtil.addParameter(
			_AUTHORIZATION_URL, OAuthConstants.TOKEN, token.getToken());
	}

	@Override
	public String getRequestTokenEndpoint() {
		return _REQUEST_TOKEN_ENDPOINT_URL;
	}

	private static final String _ACCESS_TOKEN_ENDPOINT_URL =
		PortletPropsValues.MARKETPLACE_URL + "/c/portal/oauth/access_token";

	private static final String _AUTHORIZATION_URL =
		PortletPropsValues.MARKETPLACE_URL + "/c/portal/oauth/authorize";

	private static final String _REQUEST_TOKEN_ENDPOINT_URL =
		PortletPropsValues.MARKETPLACE_URL + "/c/portal/oauth/request_token";

}