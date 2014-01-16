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

package com.liferay.sync.engine.util;

import java.nio.charset.Charset;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author Dennis Ju
 */
public class Encryptor {

	public static String decrypt(String value) throws Exception {
		if (value == null) {
			return "";
		}

		SecretKey secretKey = new SecretKeySpec(_PASSWORD, _ALGORITHM);

		Cipher cipher = Cipher.getInstance(_ALGORITHM);

		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		String decryptedValue = value;

		for (int i = 0; i < _ITERATIONS; i++) {
			byte[] decodedBytes = Base64.decodeBase64(decryptedValue);

			byte[] decryptedBytes = cipher.doFinal(decodedBytes);

			decryptedValue = new String(
				decryptedBytes, _UTF8_CHARSET).substring(_SALT_LENGTH);
		}

		return decryptedValue;
	}

	public static String encrypt(String value) throws Exception {
		if (value == null) {
			return "";
		}

		SecretKey secretKey = new SecretKeySpec(_PASSWORD, _ALGORITHM);

		Cipher cipher = Cipher.getInstance(_ALGORITHM);

		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		String salt = getSalt();

		String encryptedValue = value;

		for (int i = 0; i < _ITERATIONS; i++) {
			encryptedValue = salt.concat(encryptedValue);

			byte[] encryptedBytes = cipher.doFinal(
				encryptedValue.getBytes(_UTF8_CHARSET));

			encryptedValue = Base64.encodeBase64String(encryptedBytes);
		}

		return encryptedValue;
	}

	protected static String getSalt() {
		byte[] saltBytes = new byte[_SALT_LENGTH];

		_secureRandom.nextBytes(saltBytes);

		return Base64.encodeBase64String(saltBytes).substring(0, _SALT_LENGTH);
	}

	private static final String _ALGORITHM = "AES";

	private static final int _ITERATIONS = 8;

	private static final byte[] _PASSWORD = {
		(byte)0x56, (byte)0x78, (byte)0x7e, (byte)0x36, (byte)0x50, (byte)0x64,
		(byte)0x7a, (byte)0x2e, (byte)0x2b, (byte)0x68, (byte)0x25, (byte)0x58,
		(byte)0x45, (byte)0x39, (byte)0x4a, (byte)0x6f
	};

	private static final int _SALT_LENGTH = 16;

	private static final Charset _UTF8_CHARSET = Charset.forName("UTF-8");

	private static SecureRandom _secureRandom = new SecureRandom();

}