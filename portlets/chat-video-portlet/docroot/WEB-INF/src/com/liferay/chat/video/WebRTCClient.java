/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Philippe Proulx
 */
public class WebRTCClient {

	public WebRTCClient(long userId) {
		_userId = userId;
		updatePresence();
	}

	public void addConnection(WebRTCClient dst, WebRTCConnection conn) {
		if (connectionExists(dst)) {

			// TODO: error: already exists

			return;
		}

		_connections.put(dst, conn);
	}

	public boolean connectionExists(WebRTCClient dst) {
		return _connections.containsKey(dst);
	}

	public Set<WebRTCClient> getConnectedClients() {
		Set<WebRTCClient> connectedClients = _connections.keySet();

		return connectedClients;
	}

	public WebRTCConnection getConnection(WebRTCClient dst) {
		if (!connectionExists(dst)) {

			// TODO: error

			return null;
		}

		return _connections.get(dst);
	}

	public WebRTCClient.Mailbox getOugoingMailbox() {
		return _mailbox;
	}

	public long getTs() {
		return _timestamp;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isAvailable() {
		return _available;
	}

	public void isAvailable(boolean val) {
		_available = val;
	}

	public void removeAllConnections() {
		for (WebRTCClient dst : _connections.keySet()) {
			dst.removeSimpleConnection(this);
		}

		_connections.clear();
	}

	public void removeBilateralConnection(WebRTCClient dst) {
		if (dst.connectionExists(this)) {
			dst.removeSimpleConnection(this);
		}

		removeSimpleConnection(dst);
	}

	public synchronized void updatePresence() {
		_timestamp = System.currentTimeMillis();
	}

	public static class Mailbox {
		private final ArrayList<Mailbox.Mail> messages = new ArrayList<Mailbox.Mail>();

		public void push(Mailbox.Mail mail) {
			synchronized (messages) {
				messages.add(mail);
			}
		}

		public ArrayList<Mailbox.Mail> popAll() {
			ArrayList<Mailbox.Mail> ret;
			synchronized (messages) {
				ret = new ArrayList<Mailbox.Mail>(messages);
				messages.clear();
			}

			return ret;
		}

		/**
		 * Client immutable mail
		 *
		 * A mail always has a source (from whom is the mail) and a JSON (object)
		 * message content. It is to be contained into the destination user's mailbox.
		 *
		 * @author  Philippe Proulx <philippe.proulx@savoirfairelinux.com>
		 */
		public static abstract class Mail {
			private long _fromUserId;
			private String _jsonMessage;

			public Mail(Mailbox.Mail mail) {
				_fromUserId = mail._fromUserId;
				_jsonMessage = mail._jsonMessage;
			}

			public Mail(long fromUserId, String message) {
				_fromUserId = fromUserId;
				_jsonMessage = message;
			}

			public long getFromUserId() {
				return _fromUserId;
			}

			public String getJsonMessage() {
				return _jsonMessage;
			}

			// specialized messages need to implement those

			public abstract String getMsgType();
		}

		/**
		 * Ice candidate mail
		 *
		 * @author  Philippe Proulx <philippe.proulx@savoirfairelinux.com>
		 */
		public static class IceCandidateMail extends Mail {
			public IceCandidateMail(IceCandidateMail mail) {
				super(mail);
			}

			public IceCandidateMail(long fromUserId, String message) {
				super(fromUserId, message);
			}

			@Override
			public String getMsgType() {
				return "ice";
			}
		}

		/**
		 * Error mail
		 *
		 * @author  Philippe Proulx <philippe.proulx@savoirfairelinux.com>
		 */
		public static class ErrorMail extends Mail {
			public ErrorMail(ErrorMail mail) {
				super(mail);
			}

			public ErrorMail(long fromUserId, String message) {
				super(fromUserId, message);
			}

			@Override
			public String getMsgType() {
				return "err";
			}
		}

		/**
		 * SDP description mail
		 *
		 * @author  Philippe Proulx <philippe.proulx@savoirfairelinux.com>
		 */
		public static class SdpMail extends Mail {
			public SdpMail(SdpMail mail) {
				super(mail);
			}

			public SdpMail(long fromUserId, String message) {
				super(fromUserId, message);
			}

			@Override
			public String getMsgType() {
				return "sdp";
			}
		}

		/**
		 * Connection state mail
		 *
		 * @author  Philippe Proulx <philippe.proulx@savoirfairelinux.com>
		 */
		public static class ConnectionMail extends Mail {
			public ConnectionMail(ConnectionMail mail) {
				super(mail);
			}

			public ConnectionMail(long fromUserId, String message) {
				super(fromUserId, message);
			}

			@Override
			public String getMsgType() {
				return "conn";
			}
		}
	} public synchronized void reset() {
		isAvailable(false);
		removeAllConnections();
	}

	private void removeSimpleConnection(WebRTCClient dst) {
		if (connectionExists(dst)) {
			_connections.remove(dst);
		}
	}

	private Map<WebRTCClient, WebRTCConnection> _connections = new HashMap<WebRTCClient, WebRTCConnection>();
	private boolean _available;
	private WebRTCClient.Mailbox _mailbox = new WebRTCClient.Mailbox();
	private long _timestamp;
	private long _userId;

}