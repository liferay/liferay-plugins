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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Tomas Polesovsky
 */
public class WSRPURLUtil {

	public static String encodeWSRPAuth(long companyId, String wsrpAuth)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		Key key = company.getKeyObj();

		byte[] hmacSHA = getHMACSha(
			key.getEncoded(), wsrpAuth.getBytes(StringPool.UTF8));

		wsrpAuth = Base64.encode(hmacSHA);

		wsrpAuth = Base64.toURLSafe(wsrpAuth);

		return wsrpAuth;
	}

	protected static byte[] getHMACSha(byte[] key, byte[] data)
		throws Exception {

		Mac mac = Mac.getInstance(_ALGORITHM);

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, _ALGORITHM);

		mac.init(secretKeySpec);

		return mac.doFinal(data);
	}

	private static final String _ALGORITHM = "HmacSHA1";

}