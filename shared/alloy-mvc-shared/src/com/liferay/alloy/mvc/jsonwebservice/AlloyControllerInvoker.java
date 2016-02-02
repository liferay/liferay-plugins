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

package com.liferay.alloy.mvc.jsonwebservice;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyPortlet;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.model.Portlet;

/**
 * @author Ethan Bustad
 */
public interface AlloyControllerInvoker {

	public JSONSerializable invokeAlloyController(
			String lifecycle, Object... parameters)
		throws Exception;

	public void setProperties(
		ThemeDisplay themeDisplay, AlloyPortlet alloyPortlet, Portlet portlet,
		String controller, Class<? extends AlloyController> controllerClass);

}