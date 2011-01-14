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

package com.liferay.portal.saml;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.opensaml.common.IdentifierGenerator;

/**
 * @author Mika Koivisto
 */
public class SAMLIdentifierGenerator implements IdentifierGenerator {

	public String generateIdentifier() {
		return generateIdentifier(16);
	}

	public String generateIdentifier(int size) {
		byte[] bytes = new byte[size];

		_secureRandom.nextBytes(bytes);

		return "_" + new String(Hex.encodeHex(bytes));
	}

	private SecureRandom _secureRandom = new SecureRandom();
}
