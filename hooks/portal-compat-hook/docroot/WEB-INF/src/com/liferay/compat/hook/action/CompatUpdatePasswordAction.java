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

package com.liferay.compat.hook.action;

import com.liferay.compat.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.servlet.NoRedirectServletResponse;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jonathan Lee
 */
public class CompatUpdatePasswordAction extends BaseStrutsAction {

	@Override
	public String execute(
			StrutsAction originalStrutsAction, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		NoRedirectServletResponse noRedirectServletResponse =
			new NoRedirectServletResponse(response);

		String forward = originalStrutsAction.execute(
			request, noRedirectServletResponse);

		String location = noRedirectServletResponse.getRedirectLocation();

		if (Validator.isNotNull(location)) {
			String redirect = ParamUtil.getString(request, WebKeys.REFERER);

			if (Validator.isNull(redirect)) {
				redirect = location;
			}

			response.sendRedirect(redirect);
		}

		return forward;
	}

}