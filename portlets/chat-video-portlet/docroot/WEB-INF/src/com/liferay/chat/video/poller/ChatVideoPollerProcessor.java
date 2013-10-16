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

package com.liferay.chat.video.poller;

import com.liferay.chat.video.WebRTCClient;
import com.liferay.chat.video.WebRTCClient.Mailbox;
import com.liferay.chat.video.WebRTCManager;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

import java.util.List;

/**
 * @author Philippe Proulx
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

			// safely ignore unknown messages TODO: error

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
			List<WebRTCClient.Mailbox.Mail> clientMails = client.getMailbox().popAll();

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
		this.getWebRTCData(pollerRequest, pollerResponse);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		this.processWebRTCMessage(pollerRequest);
	}
}