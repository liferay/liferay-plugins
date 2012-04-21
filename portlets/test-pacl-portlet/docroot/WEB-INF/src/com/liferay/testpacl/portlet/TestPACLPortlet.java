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

package com.liferay.testpacl.portlet;

import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPACLPortlet extends MVCPortlet {

	@Override
	public void destroy() {
		File file = new File("../webapps/chat-portlet/css/main.css");

		try {
			file.exists();

			throw new RuntimeException(
				"File is not protected by the portal security manager");
		}
		catch (SecurityException se) {
		}
	}

}