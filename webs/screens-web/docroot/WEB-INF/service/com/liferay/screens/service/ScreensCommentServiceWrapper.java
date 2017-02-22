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

package com.liferay.screens.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScreensCommentService}.
 *
 * @author José Manuel Navarro
 * @see ScreensCommentService
 * @generated
 */
public class ScreensCommentServiceWrapper implements ScreensCommentService,
	ServiceWrapper<ScreensCommentService> {
	public ScreensCommentServiceWrapper(
		ScreensCommentService screensCommentService) {
		_screensCommentService = screensCommentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _screensCommentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_screensCommentService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _screensCommentService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject addComment(
		java.lang.String className, long classPK, java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensCommentService.addComment(className, classPK, body);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensCommentService.getComment(commentId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getComments(
		java.lang.String className, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensCommentService.getComments(className, classPK, start, end);
	}

	@Override
	public int getCommentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensCommentService.getCommentsCount(className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateComment(
		long commentId, java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _screensCommentService.updateComment(commentId, body);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public ScreensCommentService getWrappedScreensCommentService() {
		return _screensCommentService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedScreensCommentService(
		ScreensCommentService screensCommentService) {
		_screensCommentService = screensCommentService;
	}

	@Override
	public ScreensCommentService getWrappedService() {
		return _screensCommentService;
	}

	@Override
	public void setWrappedService(ScreensCommentService screensCommentService) {
		_screensCommentService = screensCommentService;
	}

	private ScreensCommentService _screensCommentService;
}