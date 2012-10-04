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

package com.liferay.portal.oauth.util;

public interface OAuthConstants {

	public static final String ACCESS_TYPE = "access-type";

	public static final String ACCESS_TYPE_OPTION = "access-type-option-";

	public static final int ACCESS_TYPE_READ = 0;

	public static final String ACCESS_TYPE_SHORT =
					"access-type-option-{0}-short";

	public static final int ACCESS_TYPE_WRITE = 1;

	/**
	 * The name of the property whose value is the <a
	 * href="http://oauth.pbwiki.com/AccessorSecret">Accessor Secret</a>.
	 */
	public static final String ACCESSOR_SECRET = "oauth_accessor_secret";
	
	public static final String ALREADY_AUTHORIZED_KEY = "already-authorized";

	public static final String APPLICATION_ID = "applicationId";

	public static final String ASC = "asc";

	public static final String AUTHORIZED = "authorized";

	public static final String BEAN_ID = "oAuthApplication";

	public static final String CALLBACK_URL = "callBackURL";

	public static final String CONSUMER_KEY = "consumerKey";

	public static final String CONSUMER_SECRET = "consumerSecret";

	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public static final String NO_APPLICATION_USERS =
					"no-authorized-applications-were-found";

	public static final String NO_APPLICATIONS = "no-applications-were-found";

	public static final String NONE = "none";

	public static final String OAUTH_ACCESSOR = "OAUTH_ACCESSOR";

	public static final String OAUTH_ADMIN = "1_WAR_oauthportlet";

	public static final String OAUTH_CALLBACK = "oauth_callback";

	public static final String OAUTH_TOKEN = "oauth_token";

	public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";

	public static final String OAUTH_USERS = "2_WAR_oauthportlet";

	public static final String OAUTH_VERIFIER = "oauth_verifier";

	public static final String PERMISSION_DENIED = "permission_denied";

	public static final String REQUEST_PROCESSED = "request_processed";

	public static final String TOKEN_EXPIRED = "token_expired";

	public static final String TOKEN_REJECTED = "token_rejected";

	public static final String TOOLBAR_ITEM = "toolbarItem";

	public static final String USER = "user";

	public static final String VERIFIER = "VERIFIER";

	public static final String WEBSITE = "website";

}