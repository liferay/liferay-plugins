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

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * @author Peter Shin
 */
public class MarketplaceUtil {

	public static String decodeClientId(
		String encodedClientId, String mpToken) {

		if (Validator.isNull(encodedClientId) || Validator.isNull(mpToken)) {
			return StringPool.BLANK;
		}

		String base64EncodedClientId = UnicodeFormatter.parseString(
			encodedClientId);

		byte[] encodedClientIdBytes = Base64.decode(base64EncodedClientId);

		return new String(xor(encodedClientIdBytes, mpToken.getBytes()));
	}

	public static String encodeClientId(
		String decodedClientId, String mpToken) {

		if (Validator.isNull(decodedClientId) || Validator.isNull(mpToken)) {
			return StringPool.BLANK;
		}

		byte[] encodedClientIdBytes = xor(
			decodedClientId.getBytes(), mpToken.getBytes());

		String base64EncodedClientId = Base64.encode(encodedClientIdBytes);

		return UnicodeFormatter.toString(base64EncodedClientId);
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