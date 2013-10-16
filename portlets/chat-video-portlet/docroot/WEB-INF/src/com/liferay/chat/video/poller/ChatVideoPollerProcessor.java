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
package com.liferay.chat.video.poller;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.ContactConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.chat.video.WebRTCClient;
import com.liferay.chat.video.WebRTCManager;
import com.liferay.chat.video.WebRTCClient.Mailbox;

import java.util.Collections;
import java.util.List;

/**
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class ChatVideoPollerProcessor extends BasePollerProcessor {
	private final WebRTCManager webRtcManager = new WebRTCManager();

    protected String getWebRTCMessageType(PollerRequest req) {
        return this.getString(req, "webrtcMsgType");
    }

    protected long getWebRTCDstUserId(PollerRequest req) {
        if (req.getParameterMap().containsKey("webrtcDstUserId")) {
            return this.getLong(req, "webrtcDstUserId");
        } else {
            return -1;
        }
    }

    protected void processWebRTCMessage(PollerRequest req) {
        long srcUserId = req.getUserId();
        String webRtcMsgType = this.getWebRTCMessageType(req);
        long dstUserId = this.getWebRTCDstUserId(req);

        // process message
        if (webRtcMsgType.equals("setAvailability")) {
            boolean isAvailable = getBoolean(req, "webrtcIsAvailable");
            this.webRtcManager.processMsgSetAvailability(srcUserId, isAvailable);
        } else if (webRtcMsgType.equals("call")) {
            this.webRtcManager.processMsgCall(srcUserId, dstUserId);
        } else if (webRtcMsgType.equals("answer")) {
            boolean acceptAnswer = getBoolean(req, "webrtcAccept");
            this.webRtcManager.processMsgAnswer(srcUserId, dstUserId, acceptAnswer);
        } else if (webRtcMsgType.equals("sdp")) {
            String jsonSdp = getString(req, "webrtcSdp");
            this.webRtcManager.processMsgShareSdp(srcUserId, dstUserId, jsonSdp);
        } else if (webRtcMsgType.equals("ice")) {
            String jsonIceCandidate = getString(req, "webrtcIce");
            this.webRtcManager.processMsgShareIceCandidate(srcUserId, dstUserId, jsonIceCandidate);
        } else if (webRtcMsgType.equals("hangUp")) {
            this.webRtcManager.processMsgHangUp(srcUserId, dstUserId);
        } else if (webRtcMsgType.equals("reset")) {
            this.webRtcManager.processMsgReset(srcUserId);
        } else if (webRtcMsgType.equals("updatePresence")) {
            this.webRtcManager.updatePresence(srcUserId);
        } else {
            // safely ignore unknown messages
            // TODO: error
        }
    }

    protected void getWebRTCData(PollerRequest req, PollerResponse resp) throws Exception {
        // get WebRTC client
        WebRTCClient client = this.webRtcManager.getClient(req.getUserId());

        // initialize response
        JSONObject webrtcObj = JSONFactoryUtil.createJSONObject();
        JSONArray mailsArray = JSONFactoryUtil.createJSONArray();
        JSONArray clientsArray = JSONFactoryUtil.createJSONArray();

        // get client mails
        if (client != null) {
            List<WebRTCClient.Mailbox.Mail> clientMails = client.getOugoingMailbox().popAll();
            for (Mailbox.Mail mail : clientMails) {
                String type = mail.getMsgType();
                String jsonMessage = mail.getJsonMessage();
                JSONObject mailObj = JSONFactoryUtil.createJSONObject();
                mailObj.put("type", type);
                mailObj.put("msg", jsonMessage);
                mailObj.put("fromUserId", mail.getFromUserId());
                mailsArray.put(mailObj);
            }
        }

        // copy list of connected clients (except me) to outgoing array
        for (Long uid : this.webRtcManager.getAvailableClientsIds()) {
            if (uid != req.getUserId()) {
                clientsArray.put(uid);
            }
        }

        // set parameters
        webrtcObj.put("mails", mailsArray);
        webrtcObj.put("clients", clientsArray);
        resp.setParameter("webrtc", webrtcObj);
    }

	@Override
	protected void doReceive(PollerRequest pollerRequest, PollerResponse pollerResponse) throws Exception {
        JSONObject yallObj = JSONFactoryUtil.createJSONObject();

        this.getWebRTCData(pollerRequest, pollerResponse);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
        this.processWebRTCMessage(pollerRequest);
	}
}
