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

package com.liferay.samplewrapper.hook.model.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserWrapper;

/**
 * @author Brian Wing Shun Chan
 */
public class SampleWrapperUserImpl extends UserWrapper {

	public SampleWrapperUserImpl(User user) {
		super(user);
	}

	public String getFavoriteColor() {
		return "Green";
	}

	@Override
	public String getFirstName() {
		System.out.println("Called SampleWrapperUserImpl.getFirstName()");

		return super.getFirstName();
	}

}