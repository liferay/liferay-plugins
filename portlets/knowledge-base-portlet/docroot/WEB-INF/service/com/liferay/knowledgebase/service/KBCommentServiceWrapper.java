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

package com.liferay.knowledgebase.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBCommentService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentService
 * @generated
 */
public class KBCommentServiceWrapper implements KBCommentService,
	ServiceWrapper<KBCommentService> {
	public KBCommentServiceWrapper(KBCommentService kbCommentService) {
		_kbCommentService = kbCommentService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kbCommentService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbCommentService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbCommentService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.deleteKBComment(kbComment);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.deleteKBComment(kbCommentId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.getKBComment(kbCommentId);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.getKBComments(groupId, status, start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, java.lang.String className, long classPK, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.getKBComments(groupId, className, classPK,
			status, start, end);
	}

	@Override
	public int getKBCommentsCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.getKBCommentsCount(groupId, status);
	}

	@Override
	public int getKBCommentsCount(long groupId, java.lang.String className,
		long classPK, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.getKBCommentsCount(groupId, className,
			classPK, status);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.updateKBComment(kbCommentId, classNameId,
			classPK, content, status, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.updateKBComment(kbCommentId, classNameId,
			classPK, content, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateStatus(
		long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentService.updateStatus(kbCommentId, status,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KBCommentService getWrappedKBCommentService() {
		return _kbCommentService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKBCommentService(KBCommentService kbCommentService) {
		_kbCommentService = kbCommentService;
	}

	@Override
	public KBCommentService getWrappedService() {
		return _kbCommentService;
	}

	@Override
	public void setWrappedService(KBCommentService kbCommentService) {
		_kbCommentService = kbCommentService;
	}

	private KBCommentService _kbCommentService;
}