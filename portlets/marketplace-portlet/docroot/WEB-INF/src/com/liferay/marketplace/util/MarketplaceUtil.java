/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.marketplace.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

import org.apache.commons.codec.binary.Hex;

/**
 * @author Peter Shin
 */
public class MarketplaceUtil {

	public static String decodeClientId(String clientId, String token)
		throws Exception {

		if (Validator.isNull(clientId) || Validator.isNull(token)) {
			return StringPool.BLANK;
		}

		byte[] bytes = Hex.decodeHex(clientId.toCharArray());

		return new String(xor(bytes, token.getBytes()));
	}

	public static String encodeClientId(String clientId, String token) {
		if (Validator.isNull(clientId) || Validator.isNull(token)) {
			return StringPool.BLANK;
		}

		byte[] data = xor(clientId.getBytes(), token.getBytes());

		return new String(Hex.encodeHex(data));
	}

	public static boolean hasMarketplaceAdmin(long companyId) throws Exception {
		int count = ExpandoValueLocalServiceUtil.getColumnValuesCount(
			companyId, User.class.getName(), "MP", "client-id");

		if (count <= 0) {
			return false;
		}

		return true;
	}

	public static boolean isMarketplaceAdmin(User user) throws Exception {
		String clientId = ExpandoValueLocalServiceUtil.getData(
			user.getCompanyId(), User.class.getName(), "MP", "client-id",
			user.getUserId(), StringPool.BLANK);

		if (Validator.isNull(clientId)) {
			return false;
		}

		return true;
	}

	protected static byte[] xor(byte[] bytes1, byte[] bytes2) {
		byte[] bytes = new byte[bytes1.length];

		for (int i = 0; i < bytes1.length; i++) {
			bytes[i] = (byte)(bytes1[i] ^ bytes2[i % bytes2.length]);
		}

		return bytes;
	}

}