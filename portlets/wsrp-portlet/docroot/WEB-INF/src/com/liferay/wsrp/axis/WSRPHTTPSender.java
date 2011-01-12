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

package com.liferay.util.axis;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.util.SystemProperties;

import java.io.InputStream;
import java.io.OutputStream;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.util.regex.Pattern;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.axis.transport.http.HTTPSender;

/**
 * @author Brian Wing Shun Chan
 */
public class SimpleHTTPSender extends HTTPSender {

	public static String getCurrentCookie() {
		return _currentCookie.get();
	}

	public void invoke(MessageContext ctx) throws AxisFault {
		String url = ctx.getStrProp(MessageContext.TRANS_URL);

		if (_pattern.matcher(url).matches()) {
			if (_log.isDebugEnabled()) {
				_log.debug("A match was found for " + url);
			}

			_invoke(ctx, url);
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("No match was found for " + url);
			}

			super.invoke(ctx);

			_registerCurrentCookie(ctx);
		}
	}

	private void _invoke(MessageContext ctx, String url) throws AxisFault {
		try {
			String userName = ctx.getUsername();
			String password = ctx.getPassword();

			if ((userName != null) && (password != null)) {
				Authenticator.setDefault(
					new SimpleAuthenticator(userName, password));
			}

			URL urlObj = new URL(url);

			URLConnection urlc = urlObj.openConnection();

			_writeToConnection(urlc, ctx);
			_readFromConnection(urlc, ctx);
		}
		catch (Exception e) {
			throw AxisFault.makeFault(e);
		}
		finally {
			Authenticator.setDefault(null);
		}
	}

	private void _readFromConnection(URLConnection urlc, MessageContext ctx)
		throws Exception {

		String contentType = urlc.getContentType();
		String contentLocation = urlc.getHeaderField("Content-Location");

		InputStream is = ((HttpURLConnection)urlc).getErrorStream();

		if (is == null) {
			is = urlc.getInputStream();
		}

		is = new UnsyncBufferedInputStream(is, 8192);

		Message response = new Message(is, false, contentType, contentLocation);

		response.setMessageType(Message.RESPONSE);

		ctx.setResponseMessage(response);
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

	private void _writeToConnection(URLConnection urlc, MessageContext ctx)
		throws Exception {

		urlc.setDoOutput(true);

		Message request = ctx.getRequestMessage();

		String contentType = request.getContentType(ctx.getSOAPConstants());

		urlc.setRequestProperty("Content-Type", contentType);

		if (ctx.useSOAPAction()) {
			urlc.setRequestProperty("SOAPAction", ctx.getSOAPActionURI());
		}

		OutputStream os = new UnsyncBufferedOutputStream(
			urlc.getOutputStream(), 8192);

		request.writeTo(os);

		os.flush();
	}

	private static Log _log = LogFactoryUtil.getLog(SimpleHTTPSender.class);

	private static ThreadLocal<String> _currentCookie =
		new InitialThreadLocal<String>(
			SimpleHTTPSender.class + "._currentCookie", StringPool.BLANK);
	private static Pattern _pattern = Pattern.compile(
		SystemProperties.get(
			SimpleHTTPSender.class.getName() + ".regexp.pattern"));

}