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

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author William Newbury
 */
public class MockAlloyControllerImpl
	extends BaseAlloyControllerImpl implements AlloyController {

	public MockAlloyControllerImpl(
		BaseAlloyControllerImpl baseAlloyControllerImpl) {

		company = baseAlloyControllerImpl.company;

		try {
			user = UserLocalServiceUtil.getDefaultUser(company.getCompanyId());

			themeDisplay = baseAlloyControllerImpl.themeDisplay;

			themeDisplay = (ThemeDisplay)themeDisplay.clone();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void afterPropertiesSet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute() throws Exception {
		throw new UnsupportedOperationException();
	}

	@Override
	public Portlet getPortlet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public HttpServletRequest getRequest() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getResponseContent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String translate(String pattern, Object... arguments) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateModel(BaseModel<?> baseModel, Object... properties)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Override
	protected void setAttachedModel(BaseModel<?> baseModel) throws Exception {
	}

}