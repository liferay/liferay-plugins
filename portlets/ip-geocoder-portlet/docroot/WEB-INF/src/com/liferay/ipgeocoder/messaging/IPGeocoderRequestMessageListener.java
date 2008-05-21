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

package com.liferay.ipgeocoder.messaging;

import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.service.IPInfoLocalServiceUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.JSONUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * <a href="IPGeocoderRequestMessageListener.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPGeocoderRequestMessageListener implements MessageListener {

	public void receive(String messageId, String message) {
		try {
			String ipAddress = message;

			IPInfo ipInfo = IPInfoLocalServiceUtil.getIPInfo(ipAddress);

			if (Validator.isNotNull(messageId)) {
				JSONObject jsonObject = new JSONObject();

				JSONUtil.put(jsonObject, "ipInfo", JSONUtil.serialize(ipInfo));

				MessageBusUtil.sendMessage(
					DestinationNames.IP_GEOCODER_RESPONSE,
					jsonObject.toString());
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log =
		LogFactory.getLog(IPGeocoderRequestMessageListener.class);

}