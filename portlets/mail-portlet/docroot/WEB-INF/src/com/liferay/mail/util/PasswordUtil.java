/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.Validator;

import java.io.UnsupportedEncodingException;

/**
 * @author Deepak Gothe
 */
public class PasswordUtil {

	public static String decrypt(String encryptedPassword) {
		String unencryptedPassword = null;

		try {
			if (Validator.isNull(encryptedPassword)) {
				return StringPool.BLANK;
			}

			byte[] bytes = Base64.decode(encryptedPassword);

			unencryptedPassword = new String(bytes, StringPool.UTF8);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error("Unable to decrypt the password", uee);
		}

		return unencryptedPassword;
	}

	public static String encrypt(String unencryptedPassword) {
		String encryptedPassword = null;

		try {
			byte[] bytes = unencryptedPassword.getBytes(StringPool.UTF8);

			encryptedPassword = Base64.encode(bytes);
		}
		catch (UnsupportedEncodingException uee) {
			_log.error("Unable to encrypt the password", uee);
		}

		return encryptedPassword;
	}

	private static Log _log = LogFactoryUtil.getLog(PasswordUtil.class);

}