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

import com.liferay.chat.model.Status;
import com.liferay.chat.service.StatusLocalServiceUtil;
import com.liferay.chat.util.PortletPropsValues;
import com.liferay.chat.util.comparator.BuddyComparator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ContactConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

/**
 * @author Bruno Farache
 */
public class JabberImpl implements Jabber {

	@Override
	public void disconnect(long userId) {
		Connection connection = getConnection(userId);

		if (connection == null) {
			return;
		}

		connection.disconnect();

		_connections.remove(userId);

		_onlineUserIds.remove(userId);
	}

	@Override
	public String getResource(String jabberId) {
		String resource = StringUtil.extractLast(jabberId, StringPool.AT);

		resource = StringUtil.extractLast(resource, StringPool.SLASH);

		if (resource == null) {
			return StringPool.BLANK;
		}

		return resource;
	}

	@Override
	public String getScreenName(String jabberId) {
		return StringUtil.extractFirst(jabberId, StringPool.AT);
	}

	@Override
	public List<Object[]> getStatuses(
		long companyId, long userId, List<Object[]> buddies) {

		try {
			Connection connection = getConnection(userId);

			if (connection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("User " + userId + " is not connected to Jabber");
				}

				return buddies;
			}

			List<Object[]> jabberBuddies = new ArrayList<>();

			jabberBuddies.addAll(buddies);

			Roster roster = connection.getRoster();

			Collection<RosterEntry> rosterEntries = roster.getEntries();

			if (PortletPropsValues.JABBER_IMPORT_USER_ENABLED) {
				for (Object[] buddy : buddies) {
					String firstName = (String)buddy[1];
					String lastName = (String)buddy[3];
					String middleName = (String)buddy[5];
					String screenName = (String)buddy[7];

					String fullName = ContactConstants.getFullName(
						firstName, middleName, lastName);

					String jabberId = getFullJabberId(screenName);

					if (!roster.contains(jabberId)) {
						roster.createEntry(jabberId, fullName, null);
					}
				}
			}

			BuddyComparator buddyComparator = new BuddyComparator(true);

			for (RosterEntry rosterEntry : rosterEntries) {
				Presence presence = roster.getPresence(rosterEntry.getUser());

				if (!presence.isAvailable()) {
					continue;
				}

				User user = UserLocalServiceUtil.getUserByScreenName(
					companyId, getScreenName(rosterEntry.getUser()));

				Object[] jabberBuddy = new Object[10];

				jabberBuddy[0] = true;
				jabberBuddy[1] = user.getFirstName();
				jabberBuddy[2] = user.getGroupId();
				jabberBuddy[3] = user.getLastName();
				jabberBuddy[4] = user.isMale();
				jabberBuddy[5] = user.getMiddleName();
				jabberBuddy[6] = user.getPortraitId();
				jabberBuddy[7] = user.getScreenName();
				jabberBuddy[8] = user.getUserId();
				jabberBuddy[9] = user.getUserUuid();

				if (Collections.binarySearch(
						jabberBuddies, jabberBuddy, buddyComparator) < 0) {

					jabberBuddies.add(jabberBuddy);
				}
			}

			Collections.sort(jabberBuddies, buddyComparator);

			return jabberBuddies;
		}
		catch (Exception e) {
			_log.error("Unable to get Jabber buddies", e);

			return buddies;
		}
	}

	@Override
	public void login(long userId, String password) {
		try {
			connect(userId, password);
		}
		catch (XMPPException xmppe1) {
			String message1 = xmppe1.getMessage();

			if (Validator.isNotNull(message1) &&
				message1.contains("not-authorized")) {

				if (!PortletPropsValues.JABBER_IMPORT_USER_ENABLED) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"User " + userId + " cannot connect to Jabber");
					}

					return;
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Importing user " + userId +
							" because he cannot connect to Jabber");
				}

				try {
					importUser(userId, password);

					connect(userId, password);
				}
				catch (XMPPException xmppe2) {
					String message2 = xmppe2.getMessage();

					if (message2.contains("conflict(409)")) {
						_log.error(
							"User " + userId + " already exists but password " +
								"is not synchronized with Jabber");
					}
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void sendMessage(long fromUserId, long toUserId, String content) {
		try {
			if (Validator.isNull(content)) {
				return;
			}

			Connection connection = getConnection(fromUserId);

			if (connection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"User " + fromUserId + " is not connected to Jabber" +
							" and cannot send messages");
				}

				return;
			}

			User toUser = UserLocalServiceUtil.getUser(toUserId);

			Roster roster = connection.getRoster();

			String jabberId = getJabberId(toUser.getScreenName());

			if (!roster.contains(jabberId)) {
				return;
			}

			Iterator<Presence> presences = roster.getPresences(jabberId);

			while (presences.hasNext()) {
				Presence presence = presences.next();

				String from = presence.getFrom();

				String resource = getResource(from);

				if (StringUtil.equalsIgnoreCase(
						resource, PortletPropsValues.JABBER_RESOURCE)) {

					continue;
				}

				ChatManager chatManager = connection.getChatManager();

				MessageListener messageListener = new JabberMessageListener(
					toUser.getCompanyId(), fromUserId);

				Chat chat = chatManager.createChat(from, messageListener);

				try {
					chat.sendMessage(content);
				}
				catch (XMPPException xmppe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"User " + fromUserId + " could not send message",
							xmppe);
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void updatePassword(long userId, String password) {
		if (!PortletPropsValues.JABBER_IMPORT_USER_ENABLED ||
			(password == null)) {

			return;
		}

		Connection connection = getConnection(userId);

		if (connection == null) {
			return;
		}

		try {
			AccountManager accountManager = connection.getAccountManager();

			accountManager.changePassword(password);
		}
		catch (XMPPException xmppe) {
			_log.error("Unable to update user " + userId + " password", xmppe);
		}
	}

	@Override
	public void updateStatus(long userId, int online) {
		updateStatus(userId, online, null);
	}

	protected Connection connect() throws Exception {
		long userId = -1;
		String password = null;

		return connect(userId, password);
	}

	protected Connection connect(long userId, String password)
		throws Exception {

		Connection connection = getConnection(userId);

		if (connection != null) {
			return connection;
		}

		connection = new XMPPConnection(getConnectionConfiguration());

		connection.connect();

		if (userId < 0) {
			return connection;
		}

		User user = UserLocalServiceUtil.getUserById(userId);

		connection.login(
			user.getScreenName(), password, PortletPropsValues.JABBER_RESOURCE);

		Status status = StatusLocalServiceUtil.getUserStatus(userId);

		if (status.getOnline()) {
			updateStatus(userId, 1, connection);
		}

		ChatManager chatManager = connection.getChatManager();

		ChatManagerListener chatMessageListener = new JabberChatManagerListener(
			user.getCompanyId(), userId);

		chatManager.addChatListener(chatMessageListener);

		_connections.put(userId, connection);

		return connection;
	}

	protected Connection getConnection(long userId) {
		return _connections.get(userId);
	}

	protected ConnectionConfiguration getConnectionConfiguration()
		throws UnknownHostException {

		if (_connectionConfiguration != null) {
			return _connectionConfiguration;
		}

		String jabberHost = PortletPropsValues.JABBER_HOST;

		if (!Validator.isIPAddress(jabberHost)) {
			InetAddress inetAddress = InetAddress.getByName(jabberHost);

			jabberHost = inetAddress.getHostAddress();
		}

		_connectionConfiguration = new ConnectionConfiguration(
			jabberHost, PortletPropsValues.JABBER_PORT,
			PortletPropsValues.JABBER_SERVICE_NAME);

		_connectionConfiguration.setSendPresence(false);

		SmackConfiguration.setLocalSocks5ProxyEnabled(
			PortletPropsValues.JABBER_SOCK5_PROXY_ENABLED);
		SmackConfiguration.setLocalSocks5ProxyPort(
			PortletPropsValues.JABBER_SOCK5_PROXY_PORT);

		return _connectionConfiguration;
	}

	protected String getFullJabberId(String screenName) {
		String jabberId = getJabberId(screenName);

		return jabberId.concat(StringPool.SLASH).concat(
			PortletPropsValues.JABBER_RESOURCE);
	}

	protected String getJabberId(String screenName) {
		return screenName.concat(StringPool.AT).concat(
			PortletPropsValues.JABBER_SERVICE_NAME);
	}

	protected void importUser(long userId, String password) throws Exception {
		Connection connection = connect();

		AccountManager accountManager = connection.getAccountManager();

		if (!accountManager.supportsAccountCreation()) {
			_log.error("Jabber server does not support account creation");

			return;
		}

		User user = UserLocalServiceUtil.getUserById(userId);

		Map<String, String> attributes = new HashMap<>();

		attributes.put("email", user.getEmailAddress());
		attributes.put("first", user.getFirstName());
		attributes.put("last", user.getLastName());
		attributes.put("name", user.getFullName());

		accountManager.createAccount(
			user.getScreenName(), password, attributes);
	}

	protected void updateStatus(
		long userId, int online, Connection connection) {

		try {
			if (connection == null) {
				connection = getConnection(userId);

				if (connection == null) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"User " + userId + " is not connected to Jabber");
					}

					return;
				}
			}

			if ((online == 1) && !_onlineUserIds.contains(userId)) {
				Presence presence = new Presence(Presence.Type.available);

				connection.sendPacket(presence);

				_onlineUserIds.add(userId);
			}
			else if ((online == 0) && _onlineUserIds.contains(userId)) {
				Presence presence = new Presence(Presence.Type.unavailable);

				connection.sendPacket(presence);

				_onlineUserIds.remove(userId);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(JabberImpl.class);

	private ConnectionConfiguration _connectionConfiguration;
	private Map<Long, Connection> _connections = new HashMap<>();
	private Set<Long> _onlineUserIds = new HashSet<>();

}