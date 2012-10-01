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

package com.liferay.portal.oauth;

/**
 * @author Ivica Cardic
 */
public interface OAuthAccessor {

	public String getAccessToken();

	public OAuthConsumer getConsumer();

	public String getRequestToken();

	public String getTokenSecret();

	void setRequestToken(String requestToken);

	void setTokenSecret(String tokenSecret);

	void setAccessToken(String accesToken);

	void setProperty(String name, Object value);

	Object getProperty(String name);

	public Object getWrappedOAuthAccessor();

}