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

package com.liferay.microblogs.service.impl;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.service.base.MicroblogsEntryLocalServiceBaseImpl;
import com.liferay.microblogs.social.MicroblogsActivityKeys;
import com.liferay.microblogs.util.MicroblogsEntryConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryLocalServiceImpl
	extends MicroblogsEntryLocalServiceBaseImpl {

	 public MicroblogsEntry addMicroblogsEntry(
			long userId, String content, int type, long receiverUserId,
			long receiverEntryId, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long microblogsEntryId = counterLocalService.increment();

		MicroblogsEntry microblogsEntry = microblogsEntryPersistence.create(
			microblogsEntryId);

		microblogsEntry.setCompanyId(user.getCompanyId());
		microblogsEntry.setUserId(user.getUserId());
		microblogsEntry.setUserName(user.getFullName());
		microblogsEntry.setCreateDate(now);
		microblogsEntry.setModifiedDate(now);
		microblogsEntry.setContent(content);
		microblogsEntry.setType(type);

		if (receiverUserId == 0) {
			receiverUserId = userId;
		}

		microblogsEntry.setReceiverUserId(receiverUserId);
		microblogsEntry.setReceiverEntryId(receiverEntryId);
		microblogsEntry.setSocialRelationType(socialRelationType);

		microblogsEntryPersistence.update(microblogsEntry, false);

		//Resources

		addMicroblogsEntryResources(microblogsEntry);

		// Social

		int actitivtyKey = MicroblogsActivityKeys.ADD_ENTRY;

		if (type == MicroblogsEntryConstants.REPLY) {
			actitivtyKey = MicroblogsActivityKeys.REPLY_ENTRY;
		}
		else if (type == MicroblogsEntryConstants.REPOST) {
			actitivtyKey =  MicroblogsActivityKeys.REPOST_ENTRY;
		}

		SocialActivityLocalServiceUtil.addActivity(
			userId, 0, MicroblogsEntry.class.getName(), microblogsEntryId,
			actitivtyKey, StringPool.BLANK, receiverUserId);

		// Tags

		AssetEntryLocalServiceUtil.updateEntry(
			userId, 0, MicroblogsEntry.class.getName(),
			microblogsEntryId, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return microblogsEntry;
	}

	public void addMicroblogsEntryResources(MicroblogsEntry microblogsEntry)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			microblogsEntry.getCompanyId(), 0, microblogsEntry.getUserId(),
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId(), false, true, true);
	}

	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);

		deleteMicroblogsEntry(microblogsEntry);
	}

	public void deleteMicroblogsEntry(MicroblogsEntry microblogsEntry)
		throws PortalException, SystemException {

		// Entry

		microblogsEntryPersistence.remove(microblogsEntry);

		// Tags

		AssetEntryLocalServiceUtil.deleteEntry(
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId());

		// Social

		SocialActivityLocalServiceUtil.deleteActivities(
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId());
	}

	public void deleteUserMicroblogsEntries(long userId)
		throws PortalException, SystemException {

		microblogsEntryPersistence.removeByUserId(userId);
	}

	public List<MicroblogsEntry> getCompanyMicroblogsEntries(
			long companyId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getCompanyMicroblogsEntriesCount(long companyId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.countByCompanyId(companyId);
	}

	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);
	}

	public List<MicroblogsEntry> getEntryMicroblogsEntriesByType(
			int type, long receiverEntryId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByT_RE(
			type, receiverEntryId, start, end);
	}

	public int getEntryMicroblogsEntriesCountByType(
			int type, long receiverEntryId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.countByT_RE(type, receiverEntryId);
	}

	public List<MicroblogsEntry> getReceiverMicroblogsEntriesByType(
			int type, long receiverUserId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByT_RU(
			type, receiverUserId, start, end);
	}

	public int getRecieverMicroblogsEntriesCountByType(
			int type, long receiverUserId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.countByT_RU(type, receiverUserId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long userId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByUserId(userId, start, end);
	}

	public int getUserMicroblogsEntriesCount(long userId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.countByUserId(userId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntriesByType(
			long userId, int type, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByU_T(userId, type, start, end);
	}

	public int getUserMicroblogsEntriesCountByType(
			long userId, int type)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.countByU_T(userId, type);
	}

	public List<MicroblogsEntry> getUsersMicroblogsEntries(
			long[] userIds, long viewerUserId, int start, int end)
		throws PortalException, SystemException {

		return microblogsEntryFinder.findByC_U_T_RU_RE_S_V(
			0, userIds, 0, 0, 0, 0, viewerUserId, start, end);
	}

	public int getUsersMicroblogsEntriesCount(long[] userIds, long viewerUserId)
		throws PortalException, SystemException {

		return microblogsEntryFinder.countByC_U_T_RU_RE_S_V(
			0, userIds, 0, 0, 0, 0, viewerUserId);
	}

	public MicroblogsEntry updateMicroblogsEntry(
			long microblogsEntryId, String content, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);

		microblogsEntry.setModifiedDate(new Date());
		microblogsEntry.setContent(content);
		microblogsEntry.setSocialRelationType(socialRelationType);

		microblogsEntryPersistence.update(microblogsEntry, false);

		// Tags

		AssetEntryLocalServiceUtil.updateEntry(
			microblogsEntry.getUserId(), 0, MicroblogsEntry.class.getName(),
			microblogsEntryId, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return microblogsEntry;
	}

}