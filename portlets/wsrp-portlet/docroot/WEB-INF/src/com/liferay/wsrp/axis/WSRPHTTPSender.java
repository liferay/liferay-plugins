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

package com.liferay.wsrp.axis;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.axis.SimpleHTTPSender;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.transport.http.HTTPSender;

/**
 * @author Michael Young
 */
public class WSRPHTTPSender extends HTTPSender {

	public static String getCurrentCookie() {
		return _currentCookie.get();
	}

	public static HttpServletRequest getCurrentRequest() {
		return _currentRequest.get();
	}

	public static void setCurrentRequest(HttpServletRequest request) {
		_currentRequest.set(request);
	}

	public WSRPHTTPSender(String forwardCookies, String forwardHeaders) {
		if (Validator.isNotNull(forwardCookies)) {
			_forwardCookies = StringUtil.split(
				StringUtil.toLowerCase(forwardCookies));
		}

		if (Validator.isNotNull(forwardHeaders)) {
			_forwardHeaders = StringUtil.split(forwardHeaders);
		}
	}

	@Override
	public void invoke(MessageContext messageContext) throws AxisFault {
		HttpServletRequest request = getCurrentRequest();

		if (request == null) {
			super.invoke(messageContext);

			return;
		}

		addForwardCookies(messageContext, request);

		addForwardHeaders(messageContext, request);

		super.invoke(messageContext);

		registerCurrentCookie(messageContext);
	}

	protected void addForwardCookies(
		MessageContext messageContext, HttpServletRequest request) {

		if (_forwardCookies.length == 0) {
			return;
		}

		Map<String, String> cookiesMap = new HashMap<>();

		Object cookiesObject = messageContext.getProperty(
			HTTPConstants.HEADER_COOKIE);

		String[] cookies = new String[0];

		if (cookiesObject instanceof String[]) {
			cookies = (String[])cookiesObject;
		}
		else if (cookiesObject instanceof String) {
			cookies = new String[] {(String)cookiesObject};
		}

		for (String cookie : cookies) {
			String name = cookie.substring(0, cookie.indexOf(StringPool.EQUAL));

			cookiesMap.put(StringUtil.toLowerCase(name), cookie);
		}

		for (String forwardCookie : _forwardCookies) {
			String value = CookieKeys.getCookie(request, forwardCookie);

			if (Validator.isNull(value)) {
				continue;
			}

			cookiesMap.put(
				forwardCookie,
				forwardCookie.concat(StringPool.EQUAL).concat(value));
		}

		Collection<String> cookiesCollection = cookiesMap.values();

		cookiesObject = cookiesCollection.toArray(new String[0]);

		messageContext.setProperty(HTTPConstants.HEADER_COOKIE, cookiesObject);
	}

	protected void addForwardHeaders(
		MessageContext messageContext, HttpServletRequest request) {

		if (_forwardHeaders.length == 0) {
			return;
		}

		Hashtable<String, Object> requestHeaders =
			(Hashtable<String, Object>)messageContext.getProperty(
				HTTPConstants.REQUEST_HEADERS);

		if (requestHeaders == null) {
			requestHeaders = new Hashtable<>();

			messageContext.setProperty(
				HTTPConstants.REQUEST_HEADERS, requestHeaders);
		}

		for (String headerName : _forwardHeaders) {
			String header = request.getHeader(headerName);

			if (header != null) {
				requestHeaders.put(headerName, header);
			}
		}
	}

	protected void registerCurrentCookie(MessageContext messageContext) {
		String cookie = StringPool.BLANK;

		try {
			cookie = GetterUtil.getString(
				messageContext.getStrProp(HTTPConstants.HEADER_COOKIE));
		}
		catch (Throwable t) {
			if (_log.isWarnEnabled()) {
				_log.warn(t, t);
			}
		}

		_currentCookie.set(cookie);
	}

	private static Log _log = LogFactoryUtil.getLog(WSRPHTTPSender.class);

	private static ThreadLocal<String> _currentCookie =
		new InitialThreadLocal<>(
			SimpleHTTPSender.class + "._currentCookie", StringPool.BLANK);
	private static ThreadLocal<HttpServletRequest> _currentRequest =
		new InitialThreadLocal<>(
			SimpleHTTPSender.class + "._currentRequest", null);

	private String[] _forwardCookies = new String[0];
	private String[] _forwardHeaders = new String[0];

}