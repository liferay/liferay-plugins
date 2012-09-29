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

package com.liferay.localization.ja.hook.security.auth;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.DefaultFullNameGenerator;

/**
 * @author Andy Yang
 */
public class JapaneseFullNameGenerator extends DefaultFullNameGenerator {

	@Override
	public String[] splitFullName(String fullName) {
		String firstName = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		String lastName = StringPool.BLANK;

		if (Validator.isNotNull(fullName)) {
			String[] name = StringUtil.split(fullName, StringPool.SPACE);

			if (name.length == 1) {
				firstName = name[0];

				return new String[] {firstName, middleName, lastName};
			}

			lastName = name[0];
			firstName = name[1]; }

		return new String[] {firstName, middleName, lastName};
	}

	@Override
	protected String buildFullName(
		String firstName, String middleName, String lastName,
		boolean useInitials) {

		StringBundler sb = new StringBundler(3);

		if (Validator.isNotNull(lastName)) {
			sb.append(lastName);
			sb.append(StringPool.SPACE);
		}

		sb.append(firstName);

		return sb.toString();
	}

}