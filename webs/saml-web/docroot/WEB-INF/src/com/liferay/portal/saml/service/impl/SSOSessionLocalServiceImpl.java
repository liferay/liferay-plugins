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

package com.liferay.portal.saml.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.saml.service.base.SSOSessionLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the s s o session local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.saml.service.SSOSessionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Mika Koivisto
 * @see com.liferay.portal.saml.service.base.SSOSessionLocalServiceBaseImpl
 * @see com.liferay.portal.saml.service.SSOSessionLocalServiceUtil
 */
public class SSOSessionLocalServiceImpl extends SSOSessionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.saml.service.SSOSessionLocalServiceUtil} to access the s s o session local service.
	 */

	public SSOSession addSSOSession(
		long companyId, long userId, String sessionKey)
		throws SystemException {

		long ssoSessionId =
			counterLocalService.increment(SSOSession.class.getName());

		SSOSession ssoSession = ssoSessionPersistence.create(ssoSessionId);

		Date now = new Date();

		ssoSession.setCompanyId(companyId);
		ssoSession.setCreateDate(now);
		ssoSession.setLastActiveDate(now);
		ssoSession.setKey(sessionKey);
		ssoSession.setUserId(userId);

		ssoSessionPersistence.update(ssoSession, false);

		return ssoSession;
	}

	public SSOSession getSSOSession(String key)
		throws PortalException, SystemException {

		return ssoSessionPersistence.findByKey(key);
	}
}