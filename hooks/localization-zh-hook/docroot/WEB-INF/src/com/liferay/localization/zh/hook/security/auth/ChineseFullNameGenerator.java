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

package com.liferay.localization.zh.hook.security.auth;

import com.liferay.localization.zh.util.LocalizationZHUtil;
import com.liferay.portal.kernel.security.auth.FamilyNameFirstFullNameGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Andrew Yang
 * @author Samuel Kong
 */
public class ChineseFullNameGenerator extends FamilyNameFirstFullNameGenerator {

	@Override
	public String[] splitFullName(String fullName) {
		if (!LocalizationZHUtil.isCJKUnifiedIdeographString(fullName)) {
			return super.splitFullName(fullName);
		}

		String firstName = StringPool.BLANK;
		String middleName = StringPool.BLANK;
		String lastName = StringPool.BLANK;

		if (Validator.isNotNull(fullName)) {
			if (fullName.length() == 1) {
				firstName = fullName;
			}

			firstName = fullName.substring(1);
			lastName = fullName.substring(0, 1);
		}

		return new String[] {firstName, middleName, lastName};
	}

	@Override
	protected String buildFullName(
		String firstName, String middleName, String lastName,
		boolean useInitials) {

		if (!LocalizationZHUtil.isCJKUnifiedIdeographString(firstName) &&
			!LocalizationZHUtil.isCJKUnifiedIdeographString(middleName) &&
			!LocalizationZHUtil.isCJKUnifiedIdeographString(lastName)) {

			return super.buildFullName(
				firstName, middleName, lastName, useInitials);
		}

		if (Validator.isNull(lastName)) {
			return firstName;
		}

		return lastName + firstName;
	}

}