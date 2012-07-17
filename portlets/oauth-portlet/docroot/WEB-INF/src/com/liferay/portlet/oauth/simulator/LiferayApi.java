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

package com.liferay.portlet.oauth.simulator;

import org.scribe.builder.api.DefaultApi10a;

/**
 * @author Ivica Cardic
 */
public class LiferayApi extends DefaultApi10a {

	public static final String AUTHORIZE_URL =
		"http://localhost:8080/c/portal/oauth/authorize?oauth_token=%s";

	@Override
	public String getAccessTokenEndpoint()
	{
		return "http://localhost:8080/c/portal/oauth/access_token";
	}

	@Override
	public String getRequestTokenEndpoint()
	{
		return "http://localhost:8080/c/portal/oauth/request_token";
	}

}