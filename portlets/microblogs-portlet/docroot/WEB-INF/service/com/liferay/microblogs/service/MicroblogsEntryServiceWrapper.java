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

package com.liferay.microblogs.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MicroblogsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryService
 * @generated
 */
public class MicroblogsEntryServiceWrapper implements MicroblogsEntryService,
	ServiceWrapper<MicroblogsEntryService> {
	public MicroblogsEntryServiceWrapper(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.addMicroblogsEntry(userId, content,
			type, receiverUserId, receiverMicroblogsEntryId,
			socialRelationType, serviceContext);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.deleteMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _microblogsEntryService.getBeanIdentifier();
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		java.lang.String assetTagName, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getMicroblogsEntries(assetTagName,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getMicroblogsEntries(start, end);
	}

	@Override
	public int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getMicroblogsEntriesCount();
	}

	@Override
	public int getMicroblogsEntriesCount(java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getMicroblogsEntriesCount(assetTagName);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getMicroblogsEntry(microblogsEntryId);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getUserMicroblogsEntries(microblogsEntryUserId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getUserMicroblogsEntries(microblogsEntryUserId,
			type, start, end);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getUserMicroblogsEntriesCount(microblogsEntryUserId);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId,
		int type) throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.getUserMicroblogsEntriesCount(microblogsEntryUserId,
			type);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _microblogsEntryService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_microblogsEntryService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public MicroblogsEntryService getWrappedMicroblogsEntryService() {
		return _microblogsEntryService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedMicroblogsEntryService(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	@Override
	public MicroblogsEntryService getWrappedService() {
		return _microblogsEntryService;
	}

	@Override
	public void setWrappedService(MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	private MicroblogsEntryService _microblogsEntryService;
}