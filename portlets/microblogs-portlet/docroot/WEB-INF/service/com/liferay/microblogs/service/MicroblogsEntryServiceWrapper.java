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

package com.liferay.microblogs.service;

/**
 * <p>
 * This class is a wrapper for {@link MicroblogsEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MicroblogsEntryService
 * @generated
 */
public class MicroblogsEntryServiceWrapper implements MicroblogsEntryService {
	public MicroblogsEntryServiceWrapper(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.addMicroblogsEntry(userId, content,
			type, receiverUserId, receiverEntryId, socialRelationType,
			serviceContext);
	}

	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryService.deleteMicroblogsEntry(microblogsEntryId);
	}

	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId, long viewerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntry(microblogsEntryId,
			viewerUserId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long companyId, long[] userIds, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType, long viewerUserId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntries(companyId, userIds,
			type, receiverUserId, receiverEntryId, socialRelationType,
			viewerUserId, start, end);
	}

	public int getMicroblogsEntriesCount(long companyId, long userId, int type,
		long receiverUserId, long receiverEntryId, int socialRelationType,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCount(companyId,
			userId, type, receiverUserId, receiverEntryId, socialRelationType,
			viewerUserId);
	}

	public int getMicroblogsEntriesCount(long companyId, long[] userIds,
		int type, long receiverUserId, long receiverEntryId,
		int socialRelationType, long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCount(companyId,
			userIds, type, receiverUserId, receiverEntryId, socialRelationType,
			viewerUserId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long companyId, long userId, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType, long viewerUserId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntries(companyId, userId,
			type, receiverUserId, receiverEntryId, socialRelationType,
			viewerUserId, start, end);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntriesByTag(
		java.lang.String tagName, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesByTag(tagName,
			viewerUserId, start, end);
	}

	public int getMicroblogsEntriesCountByTag(java.lang.String tagName,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCountByTag(tagName,
			viewerUserId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntriesByTags(
		java.lang.String[] tagNames, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesByTags(tagNames,
			viewerUserId, start, end);
	}

	public int getMicroblogsEntriesCountByTags(java.lang.String[] tagNames,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryService.getMicroblogsEntriesCountByTags(tagNames,
			viewerUserId);
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

	public MicroblogsEntryService getWrappedMicroblogsEntryService() {
		return _microblogsEntryService;
	}

	public void setWrappedMicroblogsEntryService(
		MicroblogsEntryService microblogsEntryService) {
		_microblogsEntryService = microblogsEntryService;
	}

	private MicroblogsEntryService _microblogsEntryService;
}