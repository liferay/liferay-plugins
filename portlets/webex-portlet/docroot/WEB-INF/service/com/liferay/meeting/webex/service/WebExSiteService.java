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

package com.liferay.meeting.webex.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.meeting.webex.model.WebExSite;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service interface for WebExSite. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Anant Singh
 * @see WebExSiteServiceUtil
 * @see com.liferay.meeting.webex.service.base.WebExSiteServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WebExSiteService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExSiteServiceUtil} to access the web ex site remote service. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public WebExSite deleteWebExSite(long webExSiteId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite fetchSiteKeyWebExSite(long siteKey)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite getWebExSite(long webExSiteId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWebExSitesCount(long groupId);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSites(long groupId, int start, int end,
		OrderByComparator obc);

	public void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		ServiceContext serviceContext) throws PortalException;

	public void updateWebExSite(long webExSiteId, java.lang.String apiURL,
		java.lang.String login, java.lang.String password,
		ServiceContext serviceContext) throws PortalException;
}