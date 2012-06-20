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

package com.liferay.portlet.oauth;

public class OAuthConstants {

public static final String AUTHORIZED = "authorized";
public static final String OAUTH_CALLBACK = "oauth_callback";
public static final String ALREADY_AUTHORIZED = "already-authorized";
public static final String NONE = "none";
public static final String OAUTH_TOKEN = "oauth_token";
public static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
public static final String OAUTH_VERIFIER = "oauth_verifier";
public static final String USER = "user";

public static final String WEB_APP_BEAN = "oAuthApplication";
public static final String WEB_APP_ID = "applicationId";
public static final String WEB_APP_NAME = "name";
public static final String WEB_APP_NAME_ID = "nameId";
public static final String WEB_APP_DESCRIPTION = "description";
public static final String WEB_APP_WEBSITE = "website";
public static final String WEB_APP_WEBSITE_ID = "websiteId";
public static final String WEB_APP_CALLBACKURL = "callBackURL";
public static final String WEB_APP_CALLBACKURL_ID = "callBackURLId";
public static final String WEB_APP_ACCESS_TYPE = "accessLevel";
public static final String WEB_APP_KEY = "consumerKey";
public static final String WEB_APP_REQ_PROCESSED = "request_processed";
public static final String WEB_APP_SECRET = "consumerSecret";

public static final String WEB_APP_LANG_KEY_ACCESS_TYPE_OPTION = "access-level-option-";
public static final String WEB_APP_LANG_KEY_ACCESS_TYPE_SHORT = "access-level-option-{0}-short";

public static final String EMPTY_RESULTS_MESSAGE = "no-apps-were-found";
public static final int ACCESS_TYPE_READ = 0;
public static final int ACCESS_TYPE_WRITE = 1;

public static final String PORTLET_KEY_OAUTH_ADMIN = "OA01";
public static final String PORTLET_KEY_OAUTH_USERS = "OA02";
}