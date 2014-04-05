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

package com.liferay.chat.jabber;

import com.liferay.chat.util.PortletPropsValues;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class JabberUtil {

	public static void disconnect(long userId) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().disconnect(userId);
	}

	public static String getResource(String jabberId) {
		return getJabber().getResource(jabberId);
	}

	public static String getScreenName(String jabberId) {
		return getJabber().getScreenName(jabberId);
	}

	public static List<Object[]> getStatuses(
		long companyId, long userId, List<Object[]> buddies) {

		if (!PortletPropsValues.JABBER_ENABLED) {
			return buddies;
		}

		return getJabber().getStatuses(companyId, userId, buddies);
	}

	public static void login(long userId, String password) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().login(userId, password);
	}

	public static void sendMessage(
		long fromUserId, long toUserId, String content) {

		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().sendMessage(fromUserId, toUserId, content);
	}

	public static void updatePassword(long userId, String password) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().updatePassword(userId, password);
	}

	public static void updateStatus(long userId, int online) {
		if (!PortletPropsValues.JABBER_ENABLED) {
			return;
		}

		getJabber().updateStatus(userId, online);
	}

	public void setJabber(Jabber jabber) {
		_jabber = jabber;
	}

	protected static Jabber getJabber() {
		return _jabber;
	}

	private static Jabber _jabber;

}