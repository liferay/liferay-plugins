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
import java.util.Set;

/**
 * WebRTC client (not thread safe)
 *
 * @author Philippe Proulx
 */
public class WebRTCClient {
	public WebRTCClient(long userId) {
		this.userId = userId;
		this.updatePresence();
	}

	public void addConnection(WebRTCClient dst, WebRTCConnection conn) {
		if (this.connectionExists(dst)) {

			// TODO: error: already exists

			return;
		}

		this.connections.put(dst, conn);
	}

	public boolean connectionExists(WebRTCClient dst) {
		return this.connections.containsKey(dst);
	}

	public Set<WebRTCClient> getConnectedClients() {
		Set<WebRTCClient> connectedClients = this.connections.keySet();

		return connectedClients;
	}

	public WebRTCConnection getConnection(WebRTCClient dst) {
		if (!this.connectionExists(dst)) {

			// TODO: error

			return null;
		}

		return this.connections.get(dst);
	}

	public WebRTCClient.Mailbox getOugoingMailbox() {
		return this.outgoingMailbox;
	}

	public long getTs() {
		return this.ts;
	}

	public long getUserId() {
		return this.userId;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

	public void isAvailable(boolean val) {
		this.isAvailable = val;
	}

	public void removeAllConnections() {
		for (WebRTCClient dst : this.connections.keySet()) {
			dst.removeSimpleConnection(this);
		}

		this.connections.clear();
	}

	public void removeBilateralConnection(WebRTCClient dst) {
		if (dst.connectionExists(this)) {
			dst.removeSimpleConnection(this);
		}

		this.removeSimpleConnection(dst);
	}

	public synchronized void updatePresence() {
		this.ts = System.currentTimeMillis();
	}

	public static class Mailbox {
		private final ArrayList<Mailbox.Mail> messages = new ArrayList<Mailbox.Mail>();

		public void push(Mailbox.Mail mail) {
			synchronized (this.messages) {
				this.messages.add(mail);
			}
		}

		public ArrayList<Mailbox.Mail> popAll() {
			ArrayList<Mailbox.Mail> ret;
			synchronized (this.messages) {
				ret = new ArrayList<Mailbox.Mail>(this.messages);
				this.messages.clear();
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
			private final long fromUserId;
			private final String jsonMessage;

			public Mail(Mailbox.Mail mail) {
				this.fromUserId = mail.fromUserId;
				this.jsonMessage = mail.jsonMessage;
			}

			public Mail(long fromUserId, String message) {
				this.fromUserId = fromUserId;
				this.jsonMessage = message;
			}

			public final long getFromUserId() {
				return this.fromUserId;
			}

			public String getJsonMessage() {
				return jsonMessage;
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

			public String getMsgType() {
				return "conn";
			}
		}
	} public synchronized void reset() {
		this.isAvailable(false);
		this.removeAllConnections();
	}

	private void removeSimpleConnection(WebRTCClient dst) {
		if (this.connectionExists(dst)) {
			this.connections.remove(dst);
		}
	}

	private final HashMap<WebRTCClient, WebRTCConnection> connections = new HashMap<WebRTCClient, WebRTCConnection>();
	private boolean isAvailable = false;
	private final WebRTCClient.Mailbox outgoingMailbox = new WebRTCClient.Mailbox();
	private long ts;
	private final long userId;

}