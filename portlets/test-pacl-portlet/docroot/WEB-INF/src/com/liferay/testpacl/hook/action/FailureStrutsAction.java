/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.testpacl.hook.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class FailureStrutsAction extends BaseStrutsAction {

	public static boolean isInstantiated() {
		return _instantiated;
	}

	public FailureStrutsAction() {
		_instantiated = true;
	}

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		return null;
	}

	private static boolean _instantiated;

}