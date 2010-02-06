/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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