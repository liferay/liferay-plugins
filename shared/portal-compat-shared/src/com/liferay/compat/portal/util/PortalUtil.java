/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.compat.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Michael Prigge
 */
public class PortalUtil extends com.liferay.portal.util.PortalUtil {

	public static Set<String> getComputerAddresses() {
		Set<String> _computerAddresses = new HashSet<String>();

		try {
			List<NetworkInterface> networkInterfaces = Collections.list(
				NetworkInterface.getNetworkInterfaces());

			for (NetworkInterface networkInterface : networkInterfaces) {
				List<InetAddress> inetAddresses = Collections.list(
					networkInterface.getInetAddresses());

				for (InetAddress inetAddress : inetAddresses) {
					if (inetAddress instanceof Inet4Address) {
						_computerAddresses.add(inetAddress.getHostAddress());
					}
				}
			}
		}
		catch (Exception e) {
			_log.error("Unable to determine server's IP addresses");

			_log.error(e, e);
		}

		return _computerAddresses;
	}

	private static Log _log = LogFactoryUtil.getLog(PortalUtil.class);

}