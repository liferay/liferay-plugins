/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.

 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 **/

package com.liferay.ruon.messaging;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.ruon.service.CommunicationLocalServiceUtil;
import com.liferay.ruon.service.PresenceLocalServiceUtil;
import com.liferay.ruon.util.PresenceStatusConstants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="RUONMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Murali Krishna Reddy
 *
 */
public class RUONMessageListener implements MessageListener{

	public void receive(Object message) {
		throw new UnsupportedOperationException();
	}

	public void receive(String message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	protected void doReceive(String message) throws Exception {
		JSONObject jsonObj = JSONFactoryUtil.createJSONObject(message);

		String responseDestination = jsonObj.getString(
			"lfrResponseDestination");

		String responseId = jsonObj.getString("lfrResponseId");

		if (Validator.isNull(responseDestination) ||
			Validator.isNull(responseId)) {

			return;
		}

		JSONObject getPresenceStatusRequestJSON =
				jsonObj.getJSONObject("getPresenceStatusRequest");

		 if (getPresenceStatusRequestJSON != null){

			JSONObject getPresenceStatusResponseJSON =
					getPresenceStatusResponseJSON(
							getPresenceStatusRequestJSON,responseId);

			 MessageBusUtil.sendMessage(
					responseDestination,
					getPresenceStatusResponseJSON.toString());

			 return;
		}

		JSONObject communicationWaysRequestJSON =
				jsonObj.getJSONObject("communicationWaysRequest");

		if (communicationWaysRequestJSON != null){

			JSONObject communicationWaysResponseJSON =
					getCommunicationWaysResponseJSON(
							communicationWaysRequestJSON,responseId);

			 MessageBusUtil.sendMessage(
					responseDestination,
					communicationWaysResponseJSON.toString());

			 return;
		}

		JSONObject setPresenceStatusRequestJSON =
				jsonObj.getJSONObject("setPresenceStatusRequest");

		if (setPresenceStatusRequestJSON != null){
			String result =
					handleSetPresenceStatus(setPresenceStatusRequestJSON);

			JSONObject jsonObj1 = JSONFactoryUtil.createJSONObject();

			jsonObj1.put("lfrResponseId", responseId);
			jsonObj1.put("result",result);

			 MessageBusUtil.sendMessage(
					responseDestination,
					jsonObj1.toString());
		}
	}

	private JSONObject getPresenceStatusResponseJSON (
		JSONObject getPresenceStatusRequestJSON,String responseId)
			throws Exception{

			Long requestUserId =
					getPresenceStatusRequestJSON.getLong("userId");

			String presenceStatus =
				PresenceLocalServiceUtil.getPresenceStatusOfUser(
						requestUserId);

			JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

			jsonObj.put("lfrResponseId", responseId);
			jsonObj.put(
				"getPresenceStatusResponse",
					JSONFactoryUtil.createJSONObject().put(
							"presenceStatus",presenceStatus));

		return jsonObj;
	}

	private JSONObject getCommunicationWaysResponseJSON(
			JSONObject communicationWaysRequestJSON,String responseId)
			throws Exception{

		Long requestUserId =
				communicationWaysRequestJSON.getLong("userId");

		Long loggedInUserId =
				communicationWaysRequestJSON.getLong("loggedInUserId");

		String communicationWays =
				CommunicationLocalServiceUtil.getWaysToCommunicate(
						requestUserId,loggedInUserId);

		JSONObject jsonObj = JSONFactoryUtil.createJSONObject();

		jsonObj.put("lfrResponseId", responseId);
		jsonObj.put(
			"communicationWaysResponse",
				JSONFactoryUtil.createJSONObject().put(
						"communicationWays",communicationWays));

		return jsonObj;
	}

	private String handleSetPresenceStatus(
		JSONObject setPresenceStatusRequestJSON)
			throws Exception{

		Long requestUserId =
				setPresenceStatusRequestJSON.getLong("userId");

		String status =
				setPresenceStatusRequestJSON.getString("status");

		if (status.equalsIgnoreCase("online")){
			PresenceLocalServiceUtil.setPresenceStatusOfUser(
					requestUserId, PresenceStatusConstants.STATUS_ONLINE);
			return "online";
		}
		else{
			PresenceLocalServiceUtil.setPresenceStatusOfUser(
					requestUserId, PresenceStatusConstants.STATUS_OFFLINE);
			return "offline";
		}
	}

	private static Log _log =
		LogFactory.getLog(RUONMessageListener.class);

}