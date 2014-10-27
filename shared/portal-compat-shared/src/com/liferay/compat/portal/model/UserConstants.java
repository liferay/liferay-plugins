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

package com.liferay.compat.portal.model;

import java.lang.reflect.Method;

/**
 * @author Jenny Chen
 */
public class UserConstants extends com.liferay.portal.model.UserConstants {

	public static String getPortraitURL(
		String imagePath, boolean male, long portraitId, String userUuid) {

		try {
			Class<?> clazz = UserConstants.class;

			Method method = clazz.getMethod(
				"getPortraitURL", String.class, Boolean.class, Long.class,
				String.class);

			return (String)method.invoke(
				clazz, imagePath, male, portraitId, userUuid);
		}
		catch (Exception e) {
			return UserConstants.getPortraitURL(imagePath, male, portraitId);
		}
	}

}