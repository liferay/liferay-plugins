package com.liferay.portlet.oauth.simulator;

import org.scribe.builder.api.DefaultApi10a;

/**
 * @author Ivica Cardic
 */
public class LiferayApi extends DefaultApi10a{

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