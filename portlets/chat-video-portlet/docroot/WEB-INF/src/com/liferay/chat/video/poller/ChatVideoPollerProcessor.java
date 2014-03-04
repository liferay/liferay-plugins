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
import com.liferay.chat.video.WebRTCMail;
import com.liferay.chat.video.WebRTCManager;
import com.liferay.chat.video.WebRTCManagerFactory;
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

	@Override
	protected void doReceive(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		receiveWebRTCData(pollerRequest, pollerResponse);
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
	}

	protected void receiveWebRTCData(
			PollerRequest pollerRequest, PollerResponse pollerResponse)
		throws Exception {

		WebRTCClient webRTCClient = _webRTCManager.getWebRTCClient(
			pollerRequest.getUserId());

		JSONArray webRTCMailsJSONArray = JSONFactoryUtil.createJSONArray();

		if (webRTCClient != null) {
			List<WebRTCMail> webRTCMails =
				webRTCClient.getOutgoingWebRTCMailbox().popWebRTCMails();

			for (WebRTCMail webRTCMail : webRTCMails) {
				String messageType = webRTCMail.getMessageType();
				String messageJSON = webRTCMail.getMessageJSON();
				long sourceUserId = webRTCMail.getSourceUserId();
				JSONObject mailJSONObject = JSONFactoryUtil.createJSONObject();

				mailJSONObject.put("type", messageType);
				mailJSONObject.put("msg", messageJSON);
				mailJSONObject.put("source_user_id", sourceUserId);

				webRTCMailsJSONArray.put(mailJSONObject);
			}
		}

		JSONArray webRTCClientsJSONArray = JSONFactoryUtil.createJSONArray();

		for (Long userId : _webRTCManager.getAvailableWebRTCClientIds()) {
			if (userId != pollerRequest.getUserId()) {
				webRTCClientsJSONArray.put(userId);
			}
		}

		JSONObject webRTCJSONObject = JSONFactoryUtil.createJSONObject();

		webRTCJSONObject.put("mails", webRTCMailsJSONArray);
		webRTCJSONObject.put("clients", webRTCClientsJSONArray);

		pollerResponse.setParameter("webrtc", webRTCJSONObject);
	}

	private WebRTCManager _webRTCManager =
		WebRTCManagerFactory.createWebRTCManager();

}