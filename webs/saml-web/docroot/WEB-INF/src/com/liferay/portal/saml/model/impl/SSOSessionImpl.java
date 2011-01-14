/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.model.impl;

import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.saml.util.PropsKeys;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;

/**
 * The model implementation for the SSOSession service. Represents a row in the &quot;SAML_SSOSession&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.saml.model.SSOSession} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class SSOSessionImpl extends SSOSessionModelImpl implements SSOSession {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a s s o session model instance should use the {@link SSOSession} interface instead.
	 */
	public SSOSessionImpl() {
	}

	public boolean isExpired() {
		try {
			long companyId = CompanyThreadLocal.getCompanyId().longValue();

			long maxAge = GetterUtil.getLong(
				PrefsPropsUtil.getString(
					companyId, PropsKeys.SAML_IDP_SSO_SESSION_MAXIMUM_AGE));
			long timeout = GetterUtil.getLong(
				PrefsPropsUtil.getString(
					companyId, PropsKeys.SAML_IDP_SSO_SESSION_TIMEOUT));

			if (maxAge > 0) {
				long expireTime = getCreateDate().getTime() + 1000 * maxAge;

				if (System.currentTimeMillis() > expireTime) {
					return true;
				}
			}

			long expireTime = getLastActiveDate().getTime() + 1000 * timeout;

			return (System.currentTimeMillis() > expireTime);
		}
		catch (Exception e) {
			return false;
		}
	}
}