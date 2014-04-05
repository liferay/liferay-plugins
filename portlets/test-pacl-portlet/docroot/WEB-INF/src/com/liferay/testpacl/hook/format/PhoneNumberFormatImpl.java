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

package com.liferay.testpacl.hook.format;

import com.liferay.portal.kernel.format.PhoneNumberFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class PhoneNumberFormatImpl implements PhoneNumberFormat {

	@Override
	public String format(String phoneNumber) {
		return "(TEST) " + phoneNumber;
	}

	@Override
	public String strip(String phoneNumber) {
		return phoneNumber;
	}

	@Override
	public boolean validate(String phoneNumber) {
		return true;
	}

}