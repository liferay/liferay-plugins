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

package com.liferay.sociallogin.hook.action;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.model.ExpandoValue;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.sociallogin.util.WebKeys;
import com.liferay.sociallogin.util.tencent.qq.QqConnectUtil;
import com.liferay.sociallogin.util.tencent.qq.QqConstants;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Terry Jia
 */
public class QqConnectAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long companyId = themeDisplay.getCompanyId();

		if (!QqConnectUtil.isEnabled(companyId)) {
			throw new PrincipalException();
		}

		HttpSession session = request.getSession();

		String returnState = ParamUtil.getString(request, "state");

		String state = (String)session.getAttribute(
			QqConnectUtil.getConnectState());

		if (Validator.isNull(state) && !state.equals(returnState)) {
			throw new PrincipalException();
		}

		session.removeAttribute(QqConnectUtil.getConnectState());

		String redirect = PortalUtil.getHomeURL(request);

		String code = ParamUtil.getString(request, "code");

		long userId = GetterUtil.getLong(
			session.getAttribute(WebKeys.SOCIAL_LOGIN_USER_ID));

		String token = QqConnectUtil.getAccessToken(companyId, code);

		String openId = QqConnectUtil.getSocialAccountId(companyId, token);

		if (Validator.isNotNull(openId)) {
			session.setAttribute(WebKeys.SOCIAL_LOGIN_QQ_OPEN_ID, openId);

			List<ExpandoValue> expandoValues =
				ExpandoValueLocalServiceUtil.getColumnValues(
					companyId, User.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME,
					QqConstants.QQ_OPEN_ID, openId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

			if (expandoValues.size() == 0) {
				if (userId > 0) {
					session.removeAttribute(WebKeys.SOCIAL_LOGIN_USER_ID);

					session.removeAttribute(WebKeys.SOCIAL_LOGIN_QQ_OPEN_ID);

					ExpandoValueLocalServiceUtil.addValue(
						companyId, User.class.getName(),
						ExpandoTableConstants.DEFAULT_TABLE_NAME,
						QqConstants.QQ_OPEN_ID, userId, openId);
				}
				else {
					redirect = PortalUtil.getCreateAccountURL(
						request, themeDisplay);
				}
			}
		}

		response.sendRedirect(redirect);

		return null;
	}

}