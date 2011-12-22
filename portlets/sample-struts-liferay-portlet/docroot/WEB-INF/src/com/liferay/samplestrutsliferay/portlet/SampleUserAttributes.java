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

package com.liferay.samplestrutsliferay.portlet;

import com.liferay.portlet.CustomUserAttributes;
import com.liferay.portlet.UserAttributes;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SampleUserAttributes implements CustomUserAttributes {

	public String getValue(String name, Map userInfo) {
		if (name == null) {
			return null;
		}

		String companyId = (String)userInfo.get(
			UserAttributes.LIFERAY_COMPANY_ID);
		String userId = (String)userInfo.get(UserAttributes.LIFERAY_USER_ID);

		if (name.equals("user.name.test")) {
			return "Test Name";
		}
		else {
			return null;
		}
	}

	@Override
	public Object clone() {
		return new SampleUserAttributes();
	}

}