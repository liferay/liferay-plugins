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

package com.liferay.sociallogin.hook.security.auth;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.BaseAutoLogin;
import com.liferay.portal.service.UserLocalServiceUtil;
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
public class QqAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(request);

		if (!QqConnectUtil.isEnabled(companyId)) {
			return null;
		}

		User user = getUser(request, companyId);

		if (user == null) {
			return null;
		}

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.FALSE.toString();

		return credentials;
	}

	protected User getUser(HttpServletRequest request, long companyId) {
		HttpSession session = request.getSession();

		String openId = (String)session.getAttribute(
			WebKeys.SOCIAL_LOGIN_QQ_OPEN_ID);

		User user = null;

		if (Validator.isNotNull(openId)) {
			try {
				List<ExpandoValue> expandoValues =
					ExpandoValueLocalServiceUtil.getColumnValues(
						companyId, User.class.getName(),
						ExpandoTableConstants.DEFAULT_TABLE_NAME,
						QqConstants.QQ_OPEN_ID, openId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

				if (expandoValues.size() > 0) {
					session.removeAttribute(WebKeys.SOCIAL_LOGIN_QQ_OPEN_ID);

					long userId = expandoValues.get(0).getClassPK();

					user = UserLocalServiceUtil.getUser(userId);
				}
			}
			catch (PortalException pe) {
			}
			catch (SystemException pe) {
			}
		}

		return user;
	}

}