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

public class OAuthConstants {
	

	public static final String ASC = "asc";

	public static final String ACCESS_TYPE = "accessType";

	public static final int ACCESS_TYPE_READ = 0;

	public static final int ACCESS_TYPE_WRITE = 1;

	/**
	 * The name of the property whose value is the <a
	 * href="http://oauth.pbwiki.com/AccessorSecret">Accessor Secret</a>.
	 */
	public static final String ACCESSOR_SECRET = "oauth_accessor_secret";

	public static final String APPLICATION_ID = "applicationId";

	public static final String AUTHORIZED = "authorized";

	public static final String BEAN_ID = "oAuthApplication";

	public static final String CALLBACK_URL = "callBackURL";

	public static final String CONSUMER_KEY = "consumerKey";

	public static final String CONSUMER_SECRET = "consumerSecret";

	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public static final String NONE = "none";

	public static final String OAUTH_ACCESSOR = "OAUTH_ACCESSOR";

	public static final String OAUTH_CALLBACK = "oauth_callback";

	public static final String OAUTH_ADMIN = "1_WAR_oauthportlet";

	public static final String OAUTH_USERS = "2_WAR_oauthportlet";

	public static final String OAUTH_TOKEN = "oauth_token";

	public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";

	public static final String OAUTH_VERIFIER = "oauth_verifier";

	public static final String USER = "user";

	public static final String VERIFIER = "VERIFIER";

	public static final String WEBSITE = "website";
	
	// TODO dump rest - That is what Ray did

	public static final String ALREADY_AUTHORIZED = "already-authorized";
	
	public static final String EMPTY_RESULTS_MESSAGE = "no-apps-were-found";
	
	public static final String PORTLET_KEY_OAUTH_ADMIN = "OA01";
	public static final String PORTLET_KEY_OAUTH_USERS = "OA02";

	public static final String TOOLBAR_ITEM = "toolbarItem";
	public static final String TOOLBAR_ITEM_MY_APPS = "my-applications";
	public static final String TOOLBAR_ITEM_MY_AUTHORISATIONS =
												"my-authorizations";
	public static final String TOOLBAR_ITEM_VIEW_ALL = "view-all";

	public static final String WEB_APP_ACCESS_TYPE = "accessLevel";
	
	public static final String WEB_APP_KEY = "consumerKey";
	public static final String WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION =
			"access-level-option-";
	public static final String WEB_APP_LANG_KEY_ACCESS_TYPE_SHORT =
			"access-level-option-{0}-short";
	
	public static final String WEB_APP_NAME_ID = "nameId";
	public static final String WEB_APP_REQ_PROCESSED = "request_processed";

}