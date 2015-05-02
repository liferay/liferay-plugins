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

package com.liferay.alloy.mvc;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public interface AlloyController {

	public void afterPropertiesSet();

	public void execute() throws Exception;

	public Portlet getPortlet();

	public HttpServletRequest getRequest();

	public String getResponseContent();

	public ThemeDisplay getThemeDisplay();

	public long increment() throws Exception;

	public void indexModel(BaseModel<?> baseModel) throws Exception;

	public void persistModel(BaseModel<?> baseModel) throws Exception;

	public void setModel(BaseModel<?> baseModel, Object... properties)
		throws Exception;

	public void setPageContext(PageContext pageContext);

	public void setUser(User user);

	public String translate(String pattern, Object... arguments);

	public void updateModel(BaseModel<?> baseModel, Object... properties)
		throws Exception;

	public void updateModelIgnoreRequest(
			BaseModel<?> baseModel, Object... properties)
		throws Exception;

}