/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MicroblogsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MicroblogsEntryService
 * @generated
 */
public class MicroblogsEntryServiceWrapper implements MicroblogsEntryService,
	ServiceWrapper<MicroblogsEntryService> {
	public MicroblogsEntryServiceWrapper(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _microblogsEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_microblogsEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _microblogsEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.addMicroblogsEntry(userId, content,
			type, receiverUserId, receiverMicroblogsEntryId,
			socialRelationType, serviceContext);
	}

	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.deleteMicroblogsEntry(microblogsEntryId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntries(start, end);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		java.lang.String assetTagName, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntries(assetTagName,
			start, end);
	}

	public int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCount();
	}

	public int getMicroblogsEntriesCount(java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCount(assetTagName);
	}

	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntry(microblogsEntryId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getUserMicroblogsEntries(microblogsEntryUserId,
			start, end);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getUserMicroblogsEntries(microblogsEntryUserId,
			type, start, end);
	}

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getUserMicroblogsEntriesCount(microblogsEntryUserId);
	}

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getUserMicroblogsEntriesCount(microblogsEntryUserId,
			type);
	}

	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public MicroblogsEntryService getWrappedMicroblogsEntryService() {
		return _microblogsEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedMicroblogsEntryService(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	public MicroblogsEntryService getWrappedService() {
		return _microblogsEntryService;
	}

	public void setWrappedService(MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	private MicroblogsEntryService _microblogsEntryService;
}