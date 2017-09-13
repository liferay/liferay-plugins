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
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.util.Encryptor;

/**
 * @author Tomas Polesovsky
 */
public class WSRPURLUtil {

	public static String encodeWSRPAuth(long companyId, String wsrpAuth)
		throws Exception {

		Company company = CompanyLocalServiceUtil.getCompany(companyId);

		wsrpAuth = String.valueOf(wsrpAuth.hashCode());
		wsrpAuth = Encryptor.encrypt(company.getKeyObj(), wsrpAuth);
		wsrpAuth = Base64.toURLSafe(wsrpAuth);

		return wsrpAuth;
	}

}