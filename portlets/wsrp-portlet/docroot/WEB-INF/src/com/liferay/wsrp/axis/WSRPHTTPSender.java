/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.CookieUtil;
import com.liferay.util.axis.SimpleHTTPSender;

import java.util.HashMap;
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

	public WSRPHTTPSender(String forwardCookies) {
		_forwardCookies = new String[0];

		if (Validator.isNotNull(forwardCookies)) {
			forwardCookies = forwardCookies.toLowerCase();

			_forwardCookies = StringUtil.split(
				forwardCookies, StringPool.COMMA);
		}
	}

	public static String getCurrentCookie() {
		return _currentCookie.get();
	}

	public static HttpServletRequest getCurrentRequest() {
		return _currentRequest.get();
	}

	public static void setCurrentRequest(HttpServletRequest httpServletRequest)
	{
		_currentRequest.set(httpServletRequest);
	}

	public void invoke(MessageContext ctx) throws AxisFault {
		HttpServletRequest request = getCurrentRequest();

		if (request == null) {
			super.invoke(ctx);

			return;
		}

		Object cookiesObject = ctx.getProperty(HTTPConstants.HEADER_COOKIE);

		String[] cookiesArray = new String[0];

		if (cookiesObject instanceof String[]) {
			cookiesArray = (String[])cookiesObject;
		}
		else if (cookiesObject instanceof String) {
			cookiesArray = new String[] {(String)cookiesObject};
		}

		Map<String, String> cookiesMap = new HashMap<String, String>();

		for (String cookie : cookiesArray) {
			String name = cookie.substring(0, cookie.indexOf(StringPool.EQUAL));

			cookiesMap.put(name.toLowerCase(), cookie);
		}

		for (String forwardCookie : _forwardCookies) {
			String value = CookieUtil.get(request, forwardCookie);

			if (Validator.isNotNull(value)) {
				StringBundler sb = new StringBundler(forwardCookie);

				sb.append(StringPool.EQUAL);
				sb.append(value);

				cookiesMap.put(forwardCookie, sb.toString());
			}
		}

		cookiesObject = cookiesMap.values().toArray(new String[0]);

		ctx.setProperty(HTTPConstants.HEADER_COOKIE, cookiesObject);

		super.invoke(ctx);

		_registerCurrentCookie(ctx);
	}

	private void _registerCurrentCookie(MessageContext ctx) {
		String cookie = StringPool.BLANK;

		try {
			cookie = GetterUtil.getString(
				ctx.getStrProp(HTTPConstants.HEADER_COOKIE));
		}
		catch (Throwable t) {
			_log.warn(t);
		}

		_currentCookie.set(cookie);
	}

	private static Log _log = LogFactoryUtil.getLog(WSRPHTTPSender.class);

	private static ThreadLocal<String> _currentCookie =
		new InitialThreadLocal<String>(
			SimpleHTTPSender.class + "._currentCookie", StringPool.BLANK);
	private static ThreadLocal<HttpServletRequest> _currentRequest =
		new InitialThreadLocal<HttpServletRequest>(
			SimpleHTTPSender.class + "._currentRequest", null);

	private String[] _forwardCookies;

}