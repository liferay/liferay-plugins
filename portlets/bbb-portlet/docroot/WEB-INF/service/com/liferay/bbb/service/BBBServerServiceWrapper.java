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

package com.liferay.bbb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BBBServerService}.
 *
 * @author Shinn Lok
 * @see BBBServerService
 * @generated
 */
public class BBBServerServiceWrapper implements BBBServerService,
	ServiceWrapper<BBBServerService> {
	public BBBServerServiceWrapper(BBBServerService bbbServerService) {
		_bbbServerService = bbbServerService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbServerService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbServerService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbServerService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.bbb.model.BBBServer addBBBServer(long groupId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbServerService.addBBBServer(groupId, name, url, secret,
			serviceContext);
	}

	@Override
	public com.liferay.bbb.model.BBBServer deleteBBBServer(long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbServerService.deleteBBBServer(bbbServerId);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbServerService.getBBBServers(groupId, start, end, obc);
	}

	@Override
	public int getBBBServersCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbServerService.getBBBServersCount(groupId);
	}

	@Override
	public com.liferay.bbb.model.BBBServer updateBBBServer(long bbbServerId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbServerService.updateBBBServer(bbbServerId, name, url,
			secret, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public BBBServerService getWrappedBBBServerService() {
		return _bbbServerService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedBBBServerService(BBBServerService bbbServerService) {
		_bbbServerService = bbbServerService;
	}

	@Override
	public BBBServerService getWrappedService() {
		return _bbbServerService;
	}

	@Override
	public void setWrappedService(BBBServerService bbbServerService) {
		_bbbServerService = bbbServerService;
	}

	private BBBServerService _bbbServerService;
}