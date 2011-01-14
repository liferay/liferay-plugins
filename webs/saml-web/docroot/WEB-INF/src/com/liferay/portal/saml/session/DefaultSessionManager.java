/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.saml.session;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.saml.NoSuchSPSessionException;
import com.liferay.portal.saml.NoSuchSSOSessionException;
import com.liferay.portal.saml.model.SPSession;
import com.liferay.portal.saml.model.SSOSession;
import com.liferay.portal.saml.service.SPSessionLocalServiceUtil;
import com.liferay.portal.saml.service.SSOSessionLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class DefaultSessionManager implements SessionManager {

	public void destroySession(String sessionId)
		throws PortalException, SystemException {

		SSOSession ssoSession = SSOSessionLocalServiceUtil.getSSOSession(sessionId);

		List<SPSession> spSessions =
			SPSessionLocalServiceUtil.findBySSOSessionId(
				ssoSession.getSsoSessionId());

		for (SPSession spSession : spSessions) {
			SPSessionLocalServiceUtil.deleteSPSession(spSession);
		}

		SSOSessionLocalServiceUtil.deleteSSOSession(ssoSession);
	}

	public void registerSession(
		String sessionId, String issuer, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long companyId = serviceContext.getCompanyId();
		long userId = serviceContext.getUserId();

		SSOSession ssoSession = null;

		try {
			ssoSession = SSOSessionLocalServiceUtil.getSSOSession(sessionId);

			ssoSession.setLastActiveDate(new Date());

			SSOSessionLocalServiceUtil.updateSSOSession(ssoSession, false);
		}
		catch (NoSuchSSOSessionException nssse) {
			ssoSession =
				SSOSessionLocalServiceUtil.addSSOSession(
					companyId, userId, sessionId);
		}

		SPSession spSession = null;

		try {
			spSession =
				SPSessionLocalServiceUtil.findByS_I(
					ssoSession.getSsoSessionId(), issuer);
		}
		catch (NoSuchSPSessionException nssse) {
			spSession =
				SPSessionLocalServiceUtil.addSPSession(
					ssoSession.getSsoSessionId(), issuer, serviceContext);
		}
	}

	public List<String> getActiveSPs(String sessionId)
		throws PortalException, SystemException {

		SSOSession ssoSession =
			SSOSessionLocalServiceUtil.getSSOSession(sessionId);
		List<SPSession> spSessions =
			SPSessionLocalServiceUtil.findBySSOSessionId(
				ssoSession.getSsoSessionId());

		List<String> issuers = new ArrayList<String>();

		for (SPSession spSession : spSessions) {
			issuers.add(spSession.getIssuer());
		}

		return issuers;
	}

	public boolean isExpired(String sessionId)
		throws PortalException, SystemException {

		try {
			SSOSession ssoSession = 
				SSOSessionLocalServiceUtil.getSSOSession(sessionId);

			return ssoSession.isExpired();
		}
		catch (NoSuchSSOSessionException nssse) {
		}

		return true;
	}
}
