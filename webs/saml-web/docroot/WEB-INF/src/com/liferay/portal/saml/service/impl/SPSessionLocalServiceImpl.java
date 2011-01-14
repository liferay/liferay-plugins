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
import com.liferay.portal.saml.model.SPSession;
import com.liferay.portal.saml.service.base.SPSessionLocalServiceBaseImpl;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the s p session local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.saml.service.SPSessionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Mika Koivisto
 * @see com.liferay.portal.saml.service.base.SPSessionLocalServiceBaseImpl
 * @see com.liferay.portal.saml.service.SPSessionLocalServiceUtil
 */
public class SPSessionLocalServiceImpl extends SPSessionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.portal.saml.service.SPSessionLocalServiceUtil} to access the s p session local service.
	 */

	public SPSession addSPSession(
		long ssoSessionId, String issuer, ServiceContext serviceContext)
		throws SystemException {

		long spSessionId =
			counterLocalService.increment(SPSession.class.getName());

		SPSession spSession = spSessionPersistence.create(spSessionId);

		long companyId = serviceContext.getCompanyId();
		long userId = serviceContext.getUserId();
		Date now = new Date();

		spSession.setCompanyId(companyId);
		spSession.setCreateDate(now);
		spSession.setIssuer(issuer);
		spSession.setUserId(userId);
		spSession.setSsoSessionId(ssoSessionId);

		spSessionPersistence.update(spSession, false);

		return spSession;
	}

	public List<SPSession> findBySSOSessionId(long ssoSessionId)
		throws SystemException {

		return spSessionPersistence.findBySSOSessionId(ssoSessionId);
	}

	public SPSession findByS_I(long sessionId, String issuer)
		throws PortalException, SystemException {

		return spSessionPersistence.findByS_I(sessionId, issuer);
	}
}