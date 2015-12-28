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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MicroblogsEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryService
 * @generated
 */
@ProviderType
public class MicroblogsEntryServiceWrapper implements MicroblogsEntryService,
	ServiceWrapper<MicroblogsEntryService> {
	public MicroblogsEntryServiceWrapper(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type,
		long parentMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.addMicroblogsEntry(userId, content,
			type, parentMicroblogsEntryId, socialRelationType, serviceContext);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.deleteMicroblogsEntry(microblogsEntryId);
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

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _microblogsEntryService.getOSGiServiceIdentifier();
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

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
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