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
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class WebRtcManager {
    /*
     * Clients should send an "update presence" message every 10-15 seconds,
     * so check presences every 30 seconds.
     */
    private static final long PRESENCE_TIMEOUT_MS = 30000;

    // connection state timeout value (ms)
    private static final long CONNECTION_TIMEOUT_MS = 60000;

    // all known managers (most of the time, only one will exist)
    private static final List<WebRtcManager> managers = new ArrayList<WebRtcManager>();

    // user ID -> WebRTC client (for all known clients by this manager)
    private final HashMap<Long, WebRtcClient> clients = new HashMap<Long, WebRtcClient>();

    public WebRtcManager() {
        synchronized (WebRtcManager.managers) {
            WebRtcManager.managers.add(this);
        }
    }

    public boolean clientIsAvailable(long userId) {
        if (!this.clients.containsKey(userId)) {
            return false;
        }

        return this.clients.get(userId).isAvailable();
    }

    private WebRtcClient getClientUnsafe(long userId) {
        if (this.clients.containsKey(userId)) {
            return this.clients.get(userId);
        } else {
            return null;
        }
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

    public WebRtcClient getClient(long userId) {
        synchronized (this.clients) {
            return this.getClientUnsafe(userId);
        }
    }

    public void removeClient(long userId) {
        WebRtcClient client = this.getClientUnsafe(userId);
        if (client != null) {
            client.removeAllConnections();
            this.clients.remove(userId);
        }
    }

    private void addNonExistingClient(long userId) {
        if (this.getClientUnsafe(userId) == null) {
            this.clients.put(userId, new WebRtcClient(userId));
        }
    }

    public void updatePresence(long userId) {
        synchronized (this.clients) {
            WebRtcClient client = this.getClientUnsafe(userId);
            if (client == null) {
                return;
            }
            client.updatePresence();
        }
    }

    /**
     * Verifies presences of all registered clients.
     *
     * This method should be called by an external timer/scheduler.
     */
    public static void checkAllManagersPresences() {
        synchronized (WebRtcManager.managers) {
            for (WebRtcManager manager : WebRtcManager.managers) {
                manager.checkPresences();
            }
        }
    }

    /**
     * Verifies the connections states for timeouts handling.
     *
     * This method should be called by an external timer/scheduler.
     */
    public static void checkAllManagersConnectionsStates() {
        synchronized (WebRtcManager.managers) {
            for (WebRtcManager manager : WebRtcManager.managers) {
                manager.checkConnectionsStates();
            }
        }
    }

    private void checkConnectionsStates() {
        synchronized(this.clients) {
            // verify each client
            for (WebRtcClient client : this.clients.values()) {
                // verify each connected other clients
                for (WebRtcClient otherClient : client.getConnectedClients()) {
                    WebRtcConnection conn = client.getConnection(otherClient);

                    // if the (client -> other client) connection is initiated
                    if (conn.getState() == WebRtcConnection.State.INITIATED) {
                        // timeout?
                        if (conn.getInitatedTsMs() > WebRtcManager.CONNECTION_TIMEOUT_MS) {
                            // disconnect both clients
                            assert(otherClient.connectionExists(client));
                            client.removeBilateralConnection(otherClient);
                            WebRtcManager.notifyClientLostConnection(client, otherClient, "timeout");
                            WebRtcManager.notifyClientLostConnection(otherClient, client, "timeout");
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
                if (diff > WebRtcManager.PRESENCE_TIMEOUT_MS) {
                    // expired: reset this client and remove it
                    this.resetUserUnsafe(userId);
                    this.removeClient(userId);
                }
            }
        }
    }

    public void processMsgSetAvailability(long userId, boolean isAvailable) {
        synchronized (this.clients) {
            this.removeClient(userId);
            this.addNonExistingClient(userId);
            this.getClientUnsafe(userId).isAvailable(isAvailable);
        }
    }

    public void processMsgCall(long fromUserId, long toUserId) {
        synchronized (this.clients) {
            this.addNonExistingClient(fromUserId);
            WebRtcClient fromClient = this.getClientUnsafe(fromUserId);

            // cannot make a call if source client is not available
            if (!this.clientIsAvailable(fromUserId)) {
                // TODO: error
                return;
            }

            // check if the destination client is available
            if (!this.clientIsAvailable(toUserId)) {
                // add error to user mailbox
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"unavailable_user\"}"));
                return;
            }
            WebRtcClient toClient = this.getClientUnsafe(toUserId);

            // check if a connection already exists
            if (fromClient.connectionExists(toClient) || toClient.connectionExists(fromClient)) {
                // add error to user mailbox
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"existing_conn\"}"));
                return;
            }

            // initialize the connection
            WebRtcConnection conn = new WebRtcConnection(fromClient);
            conn.setState(WebRtcConnection.State.INITIATED);
            toClient.addConnection(fromClient, conn);
            fromClient.addConnection(toClient, conn);

            // add call connection to destination user mailbox
            toClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ConnectionMail(fromUserId,
                    "{\"type\": \"call\"}"));
        }
    }

    private static boolean validateConnectionState(WebRtcClient peer1, WebRtcClient peer2, WebRtcConnection.State expectedState) {
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

    public void processMsgAnswer(long fromUserId, long toUserId, boolean acceptAnswer) {
        synchronized (this.clients) {
            this.addNonExistingClient(fromUserId);
            WebRtcClient fromClient = this.getClientUnsafe(fromUserId);

            // cannot make a call if source client is not available
            if (!this.clientIsAvailable(fromUserId)) {
                // TODO: error
                return;
            }

            // check if the destination client (original caller) is available
            if (!this.clientIsAvailable(toUserId)) {
                // add error to user mailbox
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"unavailable_user\"}"));
                return;
            }
            WebRtcClient toClient = this.getClientUnsafe(toUserId);

            // verify connection state
            if (!WebRtcManager.validateConnectionState(fromClient, toClient, WebRtcConnection.State.INITIATED)) {
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"invalid_state\"}"));
                return;
            }

            // make sure the client answering is *not* the caller
            if (fromClient.getConnection(toClient).getCaller() == fromClient) {
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"cannot_answer\"}"));
                return;
            }

            // get/update the connection
            if (acceptAnswer) {
                WebRtcConnection conn = fromClient.getConnection(toClient);
                conn.setState(WebRtcConnection.State.CONNECTED);
            } else {
                fromClient.removeBilateralConnection(toClient);
            }

            // add answer connection to destination user mailbox
            String acceptJsonBool = acceptAnswer ? "true" : "false";
            toClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ConnectionMail(fromUserId,
                    "{\"type\": \"answer\", \"accept\": " + acceptJsonBool + "}"));
        }
    }

    private void processMsgShareWebRTCStuff(long fromUserId, long toUserId, WebRtcClient.Mailbox.Mail mail) {
        synchronized (this.clients) {
            if (this.getClientUnsafe(fromUserId) == null || this.getClientUnsafe(toUserId) == null) {
                // TODO: error
                return;
            }
            WebRtcClient fromClient = this.getClientUnsafe(fromUserId);
            WebRtcClient toClient = this.getClientUnsafe(toUserId);

            // verify connection state
            if (!WebRtcManager.validateConnectionState(fromClient, toClient, WebRtcConnection.State.CONNECTED)) {
                fromClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ErrorMail(toUserId,
                        "{\"id\": \"invalid_state\"}"));

                return;
            }

            // add SDP to destination user mailbox
            toClient.getOugoingMailbox().push(mail);
        }
    }

    public void processMsgShareSdp(long fromUserId, long toUserId, String jsonSdp) {
        this.processMsgShareWebRTCStuff(fromUserId, toUserId, new WebRtcClient.Mailbox.SdpMail(fromUserId, jsonSdp));
    }

    public void processMsgShareIceCandidate(long fromUserId, long toUserId, String jsonIceCandidate) {
        this.processMsgShareWebRTCStuff(fromUserId, toUserId, new WebRtcClient.Mailbox.IceCandidateMail(fromUserId, jsonIceCandidate));
    }

    private static void notifyClientLostConnection(WebRtcClient toClient, WebRtcClient fromClient, String reason) {
        toClient.getOugoingMailbox().push(new WebRtcClient.Mailbox.ConnectionMail(fromClient.getUserId(),
                String.format("{\"type\": \"status\", \"status\": \"lost\", \"reason\": \"%s\"}", reason)));
    }

    public void processMsgHangUp(long fromUserId, long toUserId) {
        synchronized (this.clients) {
            WebRtcClient fromClient = this.getClientUnsafe(fromUserId);
            if (fromClient == null) {
                // TODO: error
                return;
            }
            WebRtcClient toClient = this.getClientUnsafe(toUserId);
            if (toClient == null) {
                // no destination client anyway
                return;
            }

            // remove connection if any and notify both peers they lost it
            if (fromClient.connectionExists(toClient)) {
                fromClient.removeBilateralConnection(toClient);
                WebRtcManager.notifyClientLostConnection(fromClient, toClient, "hangUp");
                WebRtcManager.notifyClientLostConnection(toClient, fromClient, "hangUp");
            }
        }
    }

    private void resetUserUnsafe(long userId) {
        WebRtcClient client = this.getClientUnsafe(userId);
        if (client == null) {
            // already reset
            return;
        }
        Set<WebRtcClient> connectedClients = client.getConnectedClients();
        for (WebRtcClient cc : connectedClients) {
            if (client.getConnection(cc).getState() != WebRtcConnection.State.DISCONNECTED) {
                WebRtcManager.notifyClientLostConnection(cc, client, "reset");
            }
        }
        client.reset();
        client.updatePresence();
    }

    public void processMsgReset(long userId) {
        synchronized (this.clients) {
            this.resetUserUnsafe(userId);
        }
    }
}
