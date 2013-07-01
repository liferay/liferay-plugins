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

package com.liferay.sociallogin.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Terry Jia
 */
public class RandomStatusGenerator {

	public static String getRandomState() {
		if (Validator.isNull(_state)) {
			MessageDigest messageDigest = null;

			try {
				messageDigest = MessageDigest.getInstance(_ALGORITHM_MD5);
			}
			catch (NoSuchAlgorithmException nsae) {
			}

			StringBuffer sb = new StringBuffer();

			long now = System.currentTimeMillis();

			String localHost = StringPool.BLANK;

			try {
				localHost = InetAddress.getLocalHost().toString();
			}
			catch (UnknownHostException uhe) {
			}

			sb.append(localHost);
			sb.append(StringPool.UNDERLINE);
			sb.append(now);
			sb.append(StringPool.UNDERLINE);
			sb.append(new SecureRandom().nextLong());

			messageDigest.update(sb.toString().getBytes());

			byte[] bytes = messageDigest.digest();

			char[] encodeBuffer = new char[32];

			for (int i = 0; i < bytes.length; i++) {
				int value = bytes[i] & 0xff;

				encodeBuffer[i * 2] = _HEX_CHARACTERS[value >> 4];
				encodeBuffer[i * 2 + 1] = _HEX_CHARACTERS[value & 0xf];
			}

			_state = new String(encodeBuffer);
		}

		return _state;
	}

	private static final String _ALGORITHM_MD5 = "MD5";

	private static final char[] _HEX_CHARACTERS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	private static String _state = StringPool.BLANK;

}