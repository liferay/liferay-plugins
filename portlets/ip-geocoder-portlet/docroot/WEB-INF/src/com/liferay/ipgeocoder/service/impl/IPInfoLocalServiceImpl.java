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

package com.liferay.ipgeocoder.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.model.impl.IPInfoImpl;
import com.liferay.ipgeocoder.service.base.IPInfoLocalServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import java.util.Date;

/**
 * <a href="IPInfoLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class IPInfoLocalServiceImpl extends IPInfoLocalServiceBaseImpl {

	public IPInfo getIPInfo(String ipAddress) {
		try {
			IPInfo ipInfo = ipInfoPersistence.fetchByIpAddress(ipAddress);

			if (ipInfo == null) {
				IPInfo tempIPInfo = downloadIPInfo(ipAddress);

				long ipInfoId = CounterLocalServiceUtil.increment();

				ipInfo = ipInfoPersistence.create(ipInfoId);

				ipInfo.setCreateDate(new Date());
				ipInfo.setIpAddress(ipAddress);
				ipInfo.setLatitude(tempIPInfo.getLatitude());
				ipInfo.setLongitude(tempIPInfo.getLongitude());
				ipInfo.setCity(tempIPInfo.getCity());
				ipInfo.setCountry(tempIPInfo.getCountry());

				ipInfoPersistence.update(ipInfo, false);
			}

			return ipInfo;
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return null;
		}
	}

	protected IPInfo downloadIPInfo(String ipAddress) throws IOException {
		IPInfo ipInfo = new IPInfoImpl();

		String url = _URL_PREFIX + ipAddress + _URL_SUFFIX;

		String content = HttpUtil.URLtoString(url);

		BufferedReader br = new BufferedReader(new StringReader(content));

		String line = br.readLine();

		if (line != null) {
			int pos = line.indexOf(StringPool.COLON);

			ipInfo.setCountry(line.substring(pos + 2));
		}

		if (line != null) {
			line = br.readLine();
		}

		if (line != null) {
			int pos = line.indexOf(StringPool.COLON);

			ipInfo.setCity(line.substring(pos + 2));
		}

		if (line != null) {
			line = br.readLine();
		}

		if (line != null) {
			int pos = line.indexOf(StringPool.COLON);

			ipInfo.setLatitude(GetterUtil.getDouble(line.substring(pos + 2)));
		}

		if (line != null) {
			line = br.readLine();
		}

		if (line != null) {
			int pos = line.indexOf(StringPool.COLON);

			ipInfo.setLongitude(GetterUtil.getDouble(line.substring(pos + 2)));
		}

		br.close();

		return ipInfo;
	}

	private static final String _URL_PREFIX =
		"http://api.hostip.info/get_html.php?ip=";

	private static final String _URL_SUFFIX = "&position=true";

	private static Log _log =
		LogFactoryUtil.getLog(IPInfoLocalServiceImpl.class);

}