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
import java.util.List;
import java.util.Set;

/**
 * WebRTC manager (thread safe)
 *
 * A manager has to be instantiated by any communication mechanism that
 * can talk to clients. Its purpose is to connect two clients together,
 * follow that connection and manage mails for clients. Mails are added
 * automatically to users' mailboxes with the {@code processMsg*} methods.
 *
 * @author Philippe Proulx
 */
public class WebRTCManager {
	/*
	 * Clients should send an "update presence" message every 10-15 seconds,
	 * so check presences every 30 seconds.
	 */
	/**
	 * Verifies the connections states for timeouts handling.
	 *
	 * This method should be called by an external timer/scheduler.
	 */
	public static void checkAllManagersConnectionsStates() {
		synchronized (WebRTCManager.managers) {
			for (WebRTCManager manager : WebRTCManager.managers) {
				manager.checkConnectionsStates();
			}
		}
	}

	/**
	 * Verifies presences of all registered clients.
	 *
	 * This method should be called by an external timer/scheduler.
	 */
	public static void checkAllManagersPresences() {
		synchronized (WebRTCManager.managers) {
			for (WebRTCManager manager : WebRTCManager.managers) {
				manager.checkPresences();
			}
		}
	}

	public WebRTCManager() {
		synchronized (WebRTCManager.managers) {
			WebRTCManager.managers.add(this);
		}
	}

	public boolean clientIsAvailable(long userId) {
		if (!this.clients.containsKey(userId)) {
			return false;
		}

		return this.clients.get(userId).isAvailable();
	}

	public List<Long> getAvailableClientsIds() {
		ArrayList<Long> uids = new ArrayList<Long>();
		synchronized (this.clients) {
			for (Long uid : this.clients.keySet()) {
				if (this.clientIsAvailable(uid)) {
					uids.add(uid);
				}
			}
		}

		return uids;
	}

	public WebRTCClient getClient(long userId) {
		synchronized (this.clients) {
			return this.getClientUnsafe(userId);
		}
	}

	public void processMsgCall(long fromUserId, long toUserId) {
		synchronized (this.clients) {
			this.addNonExistingClient(fromUserId);
			WebRTCClient fromClient = this.getClientUnsafe(fromUserId);

			// cannot make a call if source client is not available

			if (!this.clientIsAvailable(fromUserId)) {

				// TODO: error

				return;
			}

			// check if the destination client is available

			if (!this.clientIsAvailable(toUserId)) {

				// add error to user mailbox

				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"unavailable_user\"}"));
				return;
			}

			WebRTCClient toClient = this.getClientUnsafe(toUserId);

			// check if a connection already exists

			if (fromClient.connectionExists(toClient) || toClient.connectionExists(fromClient)) {

				// add error to user mailbox

				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"existing_conn\"}"));
				return;
			}

			// initialize the connection

			WebRTCConnection conn = new WebRTCConnection(fromClient);
			conn.setState(WebRTCConnection.State.INITIATED);
			toClient.addConnection(fromClient, conn);
			fromClient.addConnection(toClient, conn);

			// add call connection to destination user mailbox

			toClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ConnectionMail(fromUserId,
					"{\"type\": \"call\"}"));
		}
	}

	public void processMsgHangUp(long fromUserId, long toUserId) {
		synchronized (this.clients) {
			WebRTCClient fromClient = this.getClientUnsafe(fromUserId);

			if (fromClient == null) {

				// TODO: error

				return;
			}

			WebRTCClient toClient = this.getClientUnsafe(toUserId);

			if (toClient == null) {

				// no destination client anyway

				return;
			}

			// remove connection if any and notify both peers they lost it

			if (fromClient.connectionExists(toClient)) {
				fromClient.removeBilateralConnection(toClient);
				WebRTCManager.notifyClientLostConnection(fromClient, toClient, "hangUp");
				WebRTCManager.notifyClientLostConnection(toClient, fromClient, "hangUp");
			}
		}
	}

	public void processMsgReset(long userId) {
		synchronized (this.clients) {
			this.resetUserUnsafe(userId);
		}
	} public void processMsgAnswer(long fromUserId, long toUserId, boolean acceptAnswer) {
		synchronized (this.clients) {
			this.addNonExistingClient(fromUserId);
			WebRTCClient fromClient = this.getClientUnsafe(fromUserId);

			// cannot make a call if source client is not available

			if (!this.clientIsAvailable(fromUserId)) {

				// TODO: error

				return;
			}

			// check if the destination client (original caller) is available

			if (!this.clientIsAvailable(toUserId)) {

				// add error to user mailbox

				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"unavailable_user\"}"));
				return;
			}

			WebRTCClient toClient = this.getClientUnsafe(toUserId);

			// verify connection state

			if (!WebRTCManager.validateConnectionState(fromClient, toClient, WebRTCConnection.State.INITIATED)) {
				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"invalid_state\"}"));
				return;
			}

			// make sure the client answering is *not* the caller

			if (fromClient.getConnection(toClient).getCaller() == fromClient) {
				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"cannot_answer\"}"));
				return;
			}

			// get/update the connection

			if (acceptAnswer) {
				WebRTCConnection conn = fromClient.getConnection(toClient);
				conn.setState(WebRTCConnection.State.CONNECTED);
			} else {
				fromClient.removeBilateralConnection(toClient);
			}

			// add answer connection to destination user mailbox

			String acceptJsonBool = acceptAnswer ? "true" : "false";
			toClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ConnectionMail(fromUserId,
					"{\"type\": \"answer\", \"accept\": " + acceptJsonBool + "}"));
		}
	}

	public void processMsgSetAvailability(long userId, boolean isAvailable) {
		synchronized (this.clients) {
			this.removeClient(userId);
			this.addNonExistingClient(userId);
			this.getClientUnsafe(userId).isAvailable(isAvailable);
		}
	}

	public void processMsgShareIceCandidate(long fromUserId, long toUserId, String jsonIceCandidate) {
		this.processMsgShareWebRTCStuff(fromUserId, toUserId, new WebRTCClient.Mailbox.IceCandidateMail(fromUserId, jsonIceCandidate));
	}

	public void processMsgShareSdp(long fromUserId, long toUserId, String jsonSdp) {
		this.processMsgShareWebRTCStuff(fromUserId, toUserId, new WebRTCClient.Mailbox.SdpMail(fromUserId, jsonSdp));
	}

	public void removeClient(long userId) {
		WebRTCClient client = this.getClientUnsafe(userId);

		if (client != null) {
			client.removeAllConnections();
			this.clients.remove(userId);
		}
	}

	public void updatePresence(long userId) {
		synchronized (this.clients) {
			WebRTCClient client = this.getClientUnsafe(userId);

			if (client == null) {
				return;
			}

			client.updatePresence();
		}
	}

	private static void notifyClientLostConnection(WebRTCClient toClient, WebRTCClient fromClient, String reason) {
		toClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ConnectionMail(fromClient.getUserId(),
				String.format("{\"type\": \"status\", \"status\": \"lost\", \"reason\": \"%s\"}", reason)));
	}

	private static boolean validateConnectionState(WebRTCClient peer1, WebRTCClient peer2, WebRTCConnection.State expectedState) {

		// connection must exist for both peers

		if (peer1.connectionExists(peer2) && peer2.connectionExists(peer1)) {

			// and must be the same

			if (peer1.getConnection(peer2) != peer2.getConnection(peer1)) {
				return false;
			}

			// and its state must match the expected state

			if (peer1.getConnection(peer2).getState() != expectedState) {
				return false;
			}
		}

		return true;
	}

	private void addNonExistingClient(long userId) {
		if (this.getClientUnsafe(userId) == null) {
			this.clients.put(userId, new WebRTCClient(userId));
		}
	}

	private void checkConnectionsStates() {
		synchronized(this.clients) {

			// verify each client

			for (WebRTCClient client : this.clients.values()) {

				// verify each connected other clients

				for (WebRTCClient otherClient : client.getConnectedClients()) {
					WebRTCConnection conn = client.getConnection(otherClient);

					// if the (client -> other client) connection is initiated

					if (conn.getState() == WebRTCConnection.State.INITIATED) {

						// timeout?

						if (conn.getInitatedTsMs() > WebRTCManager.CONNECTION_TIMEOUT_MS) {

							// disconnect both clients

							assert(otherClient.connectionExists(client));
							client.removeBilateralConnection(otherClient);
							WebRTCManager.notifyClientLostConnection(client, otherClient, "timeout");
							WebRTCManager.notifyClientLostConnection(otherClient, client, "timeout");
						}
					}
				}
			}
		}
	}

	private void checkPresences() {
		long currentTimeMs = System.currentTimeMillis();

		synchronized(this.clients) {
			Set<Long> presUserIds = this.clients.keySet();

			for (long userId : presUserIds) {
				long tsMs = this.getClientUnsafe(userId).getTs();
				long diff = currentTimeMs - tsMs;

				if (diff > WebRTCManager.PRESENCE_TIMEOUT_MS) {

					// expired: reset this client and remove it

					this.resetUserUnsafe(userId);
					this.removeClient(userId);
				}
			}
		}
	}

	private WebRTCClient getClientUnsafe(long userId) {
		if (this.clients.containsKey(userId)) {
			return this.clients.get(userId);
		} else {
			return null;
		}
	}

	private void processMsgShareWebRTCStuff(long fromUserId, long toUserId, WebRTCClient.Mailbox.Mail mail) {
		synchronized (this.clients) {
			if (this.getClientUnsafe(fromUserId) == null || this.getClientUnsafe(toUserId) == null) {

				// TODO: error

				return;
			}

			WebRTCClient fromClient = this.getClientUnsafe(fromUserId);
			WebRTCClient toClient = this.getClientUnsafe(toUserId);

			// verify connection state

			if (!WebRTCManager.validateConnectionState(fromClient, toClient, WebRTCConnection.State.CONNECTED)) {
				fromClient.getOugoingMailbox().push(new WebRTCClient.Mailbox.ErrorMail(toUserId,
						"{\"id\": \"invalid_state\"}"));

				return;
			}

			// add SDP to destination user mailbox

			toClient.getOugoingMailbox().push(mail);
		}
	}

	private void resetUserUnsafe(long userId) {
		WebRTCClient client = this.getClientUnsafe(userId);

		if (client == null) {

			// already reset

			return;
		}

		Set<WebRTCClient> connectedClients = client.getConnectedClients();

		for (WebRTCClient cc : connectedClients) {
			if (client.getConnection(cc).getState() != WebRTCConnection.State.DISCONNECTED) {
				WebRTCManager.notifyClientLostConnection(cc, client, "reset");
			}
		}

		client.reset();
		client.updatePresence();
	}

	// connection state timeout value (ms)

	private static final long CONNECTION_TIMEOUT_MS = 60000;

	private static final long PRESENCE_TIMEOUT_MS = 30000;

	// all known managers (most of the time, only one will exist)

	private static final List<WebRTCManager> managers = new ArrayList<WebRTCManager>();

	// user ID -> WebRTC client (for all known clients by this manager)

	private final HashMap<Long, WebRTCClient> clients = new HashMap<Long, WebRTCClient>();

}