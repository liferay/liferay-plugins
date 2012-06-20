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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;

import java.io.IOException;
import java.io.OutputStream;

import javax.portlet.PortletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 */
public interface OAuthProviderManager {

	public String addParameters(String url, String... parameters)
		throws OAuthException;

	public void formEncode(
			String oauthToken, String tokenSecret, OutputStream out)
		throws OAuthException;

	public void generateAccessToken(OAuthAccessor accessor, long userId)
		throws SystemException;

	public void generateRequestToken( OAuthAccessor accessor);

	public OAuthAccessor getAccessor(OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException;

	public OAuthConsumer getConsumer( OAuthMessage requestMessage)
		throws OAuthException, OAuthProblemException;

	public OAuthMessage getMessage(HttpServletRequest request, String url);

	public OAuthMessage getMessage(PortletRequest request, String url);

	public void handleException(
		HttpServletRequest request, HttpServletResponse response, Exception e,
		boolean sendBody)
		throws IOException, ServletException;

	public void markAsAuthorized(OAuthAccessor accessor, long userId)
		throws SystemException;

	void validateMessage(OAuthMessage message, OAuthAccessor accessor)
		throws OAuthException;

}