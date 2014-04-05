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

package com.liferay.marketplace.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;

/**
 * @author Peter Shin
 */
public class MarketplaceUtil {

	public static String decodeClientId(String encodedClientId, String token) {
		if (Validator.isNull(encodedClientId) || Validator.isNull(token)) {
			return StringPool.BLANK;
		}

		byte[] encodedClientIdBytes = UnicodeFormatter.hexToBytes(
			encodedClientId);

		if (encodedClientIdBytes.length == 0) {
			return StringPool.BLANK;
		}

		return new String(xor(encodedClientIdBytes, token.getBytes()));
	}

	public static String encodeClientId(
			long companyId, long userId, String token)
		throws PortalException, SystemException {

		if (Validator.isNull(token)) {
			return StringPool.BLANK;
		}

		String clientId = ExpandoValueLocalServiceUtil.getData(
			companyId, User.class.getName(), "MP", "clientId", userId,
			"default-client-id");

		byte[] encodedClientIdBytes = xor(
			clientId.getBytes(), token.getBytes());

		return UnicodeFormatter.bytesToHex(encodedClientIdBytes);
	}

	public static boolean hasMarketplaceAdmin(long companyId)
		throws SystemException {

		int count = ExpandoValueLocalServiceUtil.getColumnValuesCount(
			companyId, User.class.getName(), "MP", "clientId");

		if (count <= 0) {
			return false;
		}

		return true;
	}

	public static boolean isMarketplaceAdmin(User user)
		throws PortalException, SystemException {

		String clientId = ExpandoValueLocalServiceUtil.getData(
			user.getCompanyId(), User.class.getName(), "MP", "clientId",
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