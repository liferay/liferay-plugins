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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.wsrp.model.WSRPConsumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import oasis.names.tc.wsrp.v2.types.RegistrationContext;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerManagerFactory {

	public static void destroyWSRPConsumerManager(String url) {
		_wsrpConsumerManagers.remove(url);

		HttpSession session = getSession();

		if (session != null) {
			session.removeAttribute("wsrpConsumerManagers");
		}
	}

	public static HttpSession getSession() {
		return _session.get();
	}

	public static WSRPConsumerManager getWSRPConsumerManager(
			WSRPConsumer wsrpConsumer)
		throws Exception {

		return _getWSRPConsumerManager(
			wsrpConsumer.getUrl(), wsrpConsumer.getRegistrationContext(),
			wsrpConsumer.getForwardCookies());
	}

	public static void setSession(HttpSession session) {
		_session.set(session);
	}

	public static boolean testWSRPConsumerManager(WSRPConsumer wsrpConsumer) {
		try {
			String userToken = _getUserToken();

			new WSRPConsumerManager(
				wsrpConsumer.getUrl(), wsrpConsumer.getRegistrationContext(),
				wsrpConsumer.getForwardCookies(), userToken);

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private static String _getUserToken() throws Exception {
		String userToken = null;

		HttpSession session = getSession();

		if (session != null) {
			Long userId = (Long)session.getAttribute(WebKeys.USER_ID);

			User user = null;

			if (userId != null) {
				user = UserLocalServiceUtil.fetchUser(userId);
			}

			if (user != null) {
				userToken = user.getLogin();
			}
		}

		return userToken;
	}

	@SuppressWarnings("unchecked")
	private static WSRPConsumerManager _getWSRPConsumerManager(
			String url, RegistrationContext registrationContext,
			String forwardCookies)
		throws Exception {

		HttpSession session = getSession();

		Map<String, WSRPConsumerManager> wsrpConsumerManagers =
			_wsrpConsumerManagers;

		if (session != null) {
			wsrpConsumerManagers =
				(Map<String, WSRPConsumerManager>)
					session.getAttribute("wsrpConsumerManagers");

			if (wsrpConsumerManagers == null) {
				wsrpConsumerManagers =
					new ConcurrentHashMap<String, WSRPConsumerManager>();

				session.setAttribute(
					"wsrpConsumerManagers", wsrpConsumerManagers);
			}
		}

		WSRPConsumerManager wsrpConsumerManager = wsrpConsumerManagers.get(url);

		if (wsrpConsumerManager == null) {
			String userToken = _getUserToken();

			wsrpConsumerManager = new WSRPConsumerManager(
				url, registrationContext, forwardCookies, userToken);

			wsrpConsumerManagers.put(url, wsrpConsumerManager);
		}

		return wsrpConsumerManager;
	}

	private static AutoResetThreadLocal<HttpSession> _session =
		new AutoResetThreadLocal<HttpSession>(
			HttpSession.class + "._session", null);

	private static Map<String, WSRPConsumerManager>
		_wsrpConsumerManagers =
			new ConcurrentHashMap<String, WSRPConsumerManager>();

}