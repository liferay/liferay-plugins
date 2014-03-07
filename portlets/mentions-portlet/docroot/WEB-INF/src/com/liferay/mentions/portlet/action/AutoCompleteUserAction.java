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

package com.liferay.mentions.portlet.action;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.usersadmin.util.UsersAdminUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class AutoCompleteUserAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		try {
			JSONArray jsonArray = getJSONArray(request);

			response.setContentType(ContentTypes.APPLICATION_JSON);

			ServletResponseUtil.write(response, jsonArray.toString());
		}
		catch (Exception e) {
			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}

		return null;
	}

	protected JSONArray getJSONArray(HttpServletRequest request)
		throws PortalException, SystemException {

		String query = ParamUtil.getString(request, "query") + StringPool.STAR;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		long userId = PrincipalThreadLocal.getUserId();

		long companyId = CompanyThreadLocal.getCompanyId();

		Hits hits = UserLocalServiceUtil.search(
			companyId, query, query, query, query, StringPool.BLANK,
			WorkflowConstants.STATUS_APPROVED, null, false, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, (Sort)null);

		List<User> users = UsersAdminUtil.getUsers(hits);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		for (User user : users) {
			if (user.isDefaultUser() || (userId == user.getUserId())) {
				continue;
			}

			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			String portraitURL = user.getPortraitURL(themeDisplay);

			userJSONObject.put("portrait", portraitURL);
			userJSONObject.put("fullName", user.getFullName());
			userJSONObject.put(
				"screenName", StringPool.AT + user.getScreenName());
			userJSONObject.put("text", user.getScreenName());

			jsonArray.put(userJSONObject);
		}

		return jsonArray;
	}

}