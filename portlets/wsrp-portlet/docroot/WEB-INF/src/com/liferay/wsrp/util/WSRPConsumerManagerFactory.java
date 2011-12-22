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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.wsrp.model.WSRPConsumer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import oasis.names.tc.wsrp.v2.types.RegistrationContext;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerManagerFactory {

	public static void destroyWSRPConsumerManager(String url) {
		_wsrpConsumerManagersByUrl.remove(url);
	}

	public static WSRPConsumerManager getWSRPConsumerManager(
			WSRPConsumer wsrpConsumer, String userToken)
		throws Exception {

		return _getWSRPConsumerManager(
			wsrpConsumer.getUrl(), wsrpConsumer.getRegistrationContext(),
			wsrpConsumer.getForwardCookies(), userToken);
	}

	public static boolean testWSRPConsumerManager(
		WSRPConsumer wsrpConsumer, String userToken) {

		try {
			new WSRPConsumerManager(
				wsrpConsumer.getUrl(), wsrpConsumer.getRegistrationContext(),
				wsrpConsumer.getForwardCookies(), userToken);

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private static WSRPConsumerManager _getWSRPConsumerManager(
			String url, RegistrationContext registrationContext,
			String forwardCookies, String userToken)
		throws Exception {

		Map<String, WSRPConsumerManager> wsrpConsumerManagersByUserToken =
			_wsrpConsumerManagersByUrl.get(url);

		if (wsrpConsumerManagersByUserToken == null) {
			wsrpConsumerManagersByUserToken =
				new ConcurrentHashMap<String, WSRPConsumerManager>();

			_wsrpConsumerManagersByUrl.put(
				url, wsrpConsumerManagersByUserToken);
		}

		String userTokenKey = userToken;

		if (Validator.isNull(userTokenKey)) {
			userTokenKey = _DEFAULT_USER_TOKEN;
		}

		WSRPConsumerManager wsrpConsumerManager =
			wsrpConsumerManagersByUserToken.get(userTokenKey);

		if (wsrpConsumerManager != null) {
			return wsrpConsumerManager;
		}

		wsrpConsumerManager = new WSRPConsumerManager(
			url, registrationContext, forwardCookies, userToken);

		wsrpConsumerManagersByUserToken.put(userTokenKey, wsrpConsumerManager);

		return wsrpConsumerManager;
	}

	private static final String _DEFAULT_USER_TOKEN =
		"default.wsrp.consumer.manager.user.token";

	private static Map<String, Map<String, WSRPConsumerManager>>
		_wsrpConsumerManagersByUrl =
			new ConcurrentHashMap<String, Map<String, WSRPConsumerManager>>();

}