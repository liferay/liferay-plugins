/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.imap;

import com.liferay.mail.MailException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;

/**
 * <a href="IMAPConnection.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class IMAPConnection {

	public IMAPConnection(
			String incomingHostName, int incomingPort, boolean incomingSecure,
			String outgoingHostName, int outgoingPort, boolean outgoingSecure,
			String login, String password) {

		_incomingHostName = incomingHostName;
		_incomingPort = incomingPort;
		_incomingSecure = incomingSecure;
		_outgoingHostName = outgoingHostName;
		_outgoingPort = outgoingPort;
		_outgoingSecure = outgoingSecure;
		_login = login;
		_password = password;
	}

	public Session getSession() {
		if (Validator.isNull(_session)) {
			boolean debug = GetterUtil.getBoolean(
				PortletProps.get("javamail.debug"));

			Properties props = new Properties();

			props.put("mail.imap.host", _incomingHostName);
			props.put("mail.imap.port", _incomingPort);

			props.put("mail.imaps.auth", "true");
			props.put("mail.imaps.host", _incomingHostName);
			props.put("mail.imaps.port", _incomingPort);
			props.put("mail.imaps.socketFactory.port", _incomingPort);
			props.put("mail.imaps.socketFactory.class", _SSL_FACTORY);
			props.put("mail.imaps.socketFactory.fallback", "false");

			props.put("mail.smtp.host", _outgoingHostName);
			props.put("mail.smtp.port", _outgoingPort);

			props.put("mail.smtps.auth", "true");
			props.put("mail.smtps.host", _outgoingHostName);
			props.put("mail.smtps.port", _outgoingPort);

			props.put("mail.smtps.socketFactory.port", _outgoingPort);
			props.put("mail.smtps.socketFactory.class", _SSL_FACTORY);
			props.put("mail.smtps.socketFactory.fallback", "false");

			props.put("mail.debug", Boolean.toString(debug));

			_session = Session.getInstance(props);

			_session.setDebug(debug);
		}

		return _session;
	}

	public Store getStore(boolean useOldStores)
		throws MessagingException {

		Store store = null;

		String storeKey = _incomingHostName + _outgoingHostName + _login;

		if (useOldStores) {
			store = _allStores.get(storeKey);

			if (Validator.isNotNull(store) && !store.isConnected()) {
				store.close();

				store = null;
			}
		}

		if (Validator.isNull(store)) {
			Session session = getSession();

			if (_incomingSecure) {
				store = session.getStore("imaps");
			}
			else {
				store = session.getStore("imap");
			}

			store.addConnectionListener(new ConnectionListener(storeKey));
			store.connect(_incomingHostName, _incomingPort, _login, _password);

			if (useOldStores) {
				_allStores.put(storeKey, store);
			}
		}

		return store;
	}

	public Transport getTransport() throws MessagingException {
		String transportKey = _login + "_TRANSPORT_" + _incomingHostName;

		Transport transport = null;

		Session session = getSession();

		if (_outgoingSecure) {
			transport = session.getTransport("smtps");
		}
		else {
			transport = session.getTransport("smtp");
		}

		transport.addConnectionListener(
				new ConnectionListener(transportKey));

		transport.connect(_outgoingHostName, _outgoingPort, _login, _password);

		return transport;
	}

	public void testConnection() throws MailException {
		try {
			testIncomingConnection();
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}

		try {
			testOutgoingConnection();
		}
		catch (MessagingException me) {
			throw new MailException(me);
		}
	}

	protected void testIncomingConnection() throws MessagingException {
		Store store = getStore(false);

		Folder folder = store.getDefaultFolder();

		if (Validator.isNotNull(folder) &&
			Validator.isNotNull(folder.list())) {
		}

		store.close();
	}

	protected void testOutgoingConnection() throws MessagingException {
		Transport transport = getTransport();

		transport.isConnected();

		transport.close();
	}

	protected IMAPConnection() {
	}

	private static final String _SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static ConcurrentHashMap<String, Store> _allStores =
		new ConcurrentHashMap<String, Store>();

	private static Log _log = LogFactoryUtil.getLog(IMAPConnection.class);

	private String _incomingHostName;
	private int _incomingPort;
	private boolean _incomingSecure;
	private String _login;
	private String _outgoingHostName;
	private int _outgoingPort;
	private boolean _outgoingSecure;
	private String _password;
	private Session _session;

}