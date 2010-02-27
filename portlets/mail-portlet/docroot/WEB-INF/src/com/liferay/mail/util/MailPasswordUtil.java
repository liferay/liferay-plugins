/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringPool;

import java.io.UnsupportedEncodingException;

/**
 * <a href="MailPasswordUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Deepak Gothe
 */
public class MailPasswordUtil {

	public static String decrypt(String encryptedPassword) {
		String password = null;

		try {
			byte[] passwordBytes = Base64.decode(encryptedPassword);

			password = new String(passwordBytes, StringPool.UTF8);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error("Unable to decrypt the password", uee);
		}

		return password;
	}

	public static String encrypt(String password) {
		String encryptedPassword = null;

		try {
			byte[] passwordBytes = password.getBytes(StringPool.UTF8);

			encryptedPassword = Base64.encode(passwordBytes);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error("Unable to encrypt the password", uee);
		}

		return encryptedPassword;
	}

	private static Log _log = LogFactoryUtil.getLog(MailPasswordUtil.class);

}