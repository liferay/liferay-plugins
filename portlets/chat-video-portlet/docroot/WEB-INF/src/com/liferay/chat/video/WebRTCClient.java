/**
 * Copyright (c) 2013 Savoir-faire Linux Inc.
 *     Philippe Proulx <philippe.proulx@savoirfairelinux.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms are permitted
 * provided that the above copyright notice and this paragraph are
 * duplicated in all such forms and that any documentation,
 * advertising materials, and other materials related to such
 * distribution and use acknowledge that the software was developed
 * by Savoir-faire Linux.  The name of Savoir-faire Linux
 * may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package com.liferay.chat.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * WebRTC client (not thread safe)
 *
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class WebRTCClient {
	private final HashMap<WebRTCClient, WebRTCConnection> connections = new HashMap<WebRTCClient, WebRTCConnection>();
	private final WebRTCClient.Mailbox outgoingMailbox = new WebRTCClient.Mailbox();
	private boolean isAvailable = false;
	private final long userId;
	private long ts;

	public WebRTCClient(long userId) {
		this.userId = userId;
		this.updatePresence();
	}

	public void isAvailable(boolean val) {
		this.isAvailable = val;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

	public long getTs() {
		return this.ts;
	}

	public synchronized void updatePresence() {
		this.ts = System.currentTimeMillis();
	}

	public long getUserId() {
		return this.userId;
	}

	public synchronized void reset() {
		this.isAvailable(false);
		this.removeAllConnections();
	}

	public WebRTCClient.Mailbox getOugoingMailbox() {
		return this.outgoingMailbox;
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

	public WebRTCConnection getConnection(WebRTCClient dst) {
		if (!this.connectionExists(dst)) {
			// TODO: error
			return null;
		}

		return this.connections.get(dst);
	}

	public void removeAllConnections() {
		for (WebRTCClient dst : this.connections.keySet()) {
			dst.removeSimpleConnection(this);
		}
		this.connections.clear();
	}

	private void removeSimpleConnection(WebRTCClient dst) {
		if (this.connectionExists(dst)) {
			this.connections.remove(dst);
		}
	}

	public void removeBilateralConnection(WebRTCClient dst) {
		if (dst.connectionExists(this)) {
			dst.removeSimpleConnection(this);
		}
		this.removeSimpleConnection(dst);
	}

	public Set<WebRTCClient> getConnectedClients() {
		Set<WebRTCClient> connectedClients = this.connections.keySet();

		return connectedClients;
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
	}
}
