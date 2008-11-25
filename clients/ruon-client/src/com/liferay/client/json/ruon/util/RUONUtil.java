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
 */

package com.liferay.client.json.ruon.util;

import com.liferay.client.json.ruon.model.Presence;
import com.liferay.portal.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <a href="RUONUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class RUONUtil {

	public static List<Presence> getPresences(long userId, Locale locale)
		throws PortalException {

		Message message = new Message();

		message.put("command", "getPresences");
		message.put("userId", userId);
		message.put("locale", locale);
		
		long timeout=1000000;
		
		JSONObject responseJSON =
			(JSONObject)MessageBusUtil.sendSynchronousMessage(
				DestinationNames.RUON, message,timeout);

		if (responseJSON == null) {
			return null;
		}

		JSONArray presencesJSON = responseJSON.getJSONArray("presencesJSON");

		if ((presencesJSON == null) || (presencesJSON.length() == 0)) {
			return null;
		}

		List<Presence> presences = new ArrayList<Presence>();

		for (int i = 0; i < presencesJSON.length(); i++) {
			JSONObject presenceJSON = presencesJSON.getJSONObject(i);

			String output = presenceJSON.getString("output");

			Presence presence = new Presence(userId, output);

			presences.add(presence);
		}

		return presences;
	}

	public static void updateNetwork(String name, long ttl, Converter converter)
		throws PortalException {

		Message message = new Message();

		message.put("command", "updateNetwork");
		message.put("name", name);
		message.put("ttl", ttl);
		message.put("converter", converter);

		MessageBusUtil.sendMessage(DestinationNames.RUON, message);
	}

	public static void updatePresence(
			long userId, String networkName, boolean online)
		throws PortalException {

		Message message = new Message();

		message.put("command", "updatePresence");
		message.put("userId", userId);
		message.put("networkName", networkName);
		message.put("online", online);

		MessageBusUtil.sendMessage(DestinationNames.RUON, message);
	}

}