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

package com.liferay.privatemessaging.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserThreadService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadService
 * @generated
 */
public class UserThreadServiceWrapper implements UserThreadService,
	ServiceWrapper<UserThreadService> {
	public UserThreadServiceWrapper(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _userThreadService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_userThreadService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _userThreadService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.portlet.messageboards.model.MBMessage getLastThreadMessage(
		long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userThreadService.getLastThreadMessage(mbThreadId);
	}

	@Override
	public java.util.List<com.liferay.portlet.messageboards.model.MBMessage> getThreadMessages(
		long mbThreadId, int start, int end, boolean ascending)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userThreadService.getThreadMessages(mbThreadId, start, end,
			ascending);
	}

	@Override
	public int getThreadMessagesCount(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _userThreadService.getThreadMessagesCount(mbThreadId);
	}

	@Override
	public java.util.List<com.liferay.privatemessaging.model.UserThread> getUserUserThreads(
		boolean deleted)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _userThreadService.getUserUserThreads(deleted);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public UserThreadService getWrappedUserThreadService() {
		return _userThreadService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedUserThreadService(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	@Override
	public UserThreadService getWrappedService() {
		return _userThreadService;
	}

	@Override
	public void setWrappedService(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	private UserThreadService _userThreadService;
}