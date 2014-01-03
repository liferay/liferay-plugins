/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.service.impl;

import com.liferay.microblogs.UnsupportedMicroblogsEntryException;
import com.liferay.microblogs.microblogs.social.MicroblogsActivityKeys;
import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.base.MicroblogsEntryLocalServiceBaseImpl;
import com.liferay.microblogs.util.PortletKeys;
import com.liferay.microblogs.util.comparator.EntryCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
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
			long receiverMicroblogsEntryId, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Microblogs entry

		User user = userPersistence.findByPrimaryKey(userId);

		if (receiverUserId == 0) {
			receiverUserId = userId;
		}

		Date now = new Date();

		validate(type, receiverMicroblogsEntryId);

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
		microblogsEntry.setReceiverUserId(receiverUserId);
		microblogsEntry.setReceiverMicroblogsEntryId(receiverMicroblogsEntryId);
		microblogsEntry.setSocialRelationType(socialRelationType);

		microblogsEntryPersistence.update(microblogsEntry);

		// Resources

		resourceLocalService.addModelResources(microblogsEntry, serviceContext);

		// Asset

		updateAsset(
			microblogsEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Social

		int activityKey = MicroblogsActivityKeys.ADD_ENTRY;

		if (type == MicroblogsEntryConstants.TYPE_REPLY) {
			activityKey = MicroblogsActivityKeys.REPLY_ENTRY;
		}
		else if (type == MicroblogsEntryConstants.TYPE_REPOST) {
			activityKey = MicroblogsActivityKeys.REPOST_ENTRY;
		}

		JSONObject extraDataJSONObject = JSONFactoryUtil.createJSONObject();

		extraDataJSONObject.put("content", microblogsEntry.getContent());
		extraDataJSONObject.put(
			"receiverMicroblogsEntryId", receiverMicroblogsEntryId);

		SocialActivityLocalServiceUtil.addActivity(
			userId, 0, MicroblogsEntry.class.getName(), microblogsEntryId,
			activityKey, extraDataJSONObject.toString(), receiverUserId);

		// Notification

		if (type == MicroblogsEntryConstants.TYPE_REPLY) {
			sendNotificationEvent(microblogsEntry);
		}

		return microblogsEntry;
	}

	@Override
	public MicroblogsEntry deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);

		return deleteMicroblogsEntry(microblogsEntry);
	}

	@Override
	public MicroblogsEntry deleteMicroblogsEntry(
			MicroblogsEntry microblogsEntry)
		throws PortalException, SystemException {

		// Microblogs entry

		microblogsEntryPersistence.remove(microblogsEntry);

		// Asset

		AssetEntryLocalServiceUtil.deleteEntry(
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId());

		// Social

		SocialActivityLocalServiceUtil.deleteActivities(
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId());

		return microblogsEntry;
	}

	public void deleteUserMicroblogsEntries(long userId)
		throws PortalException, SystemException {

		List<MicroblogsEntry> microblogsEntries =
			microblogsEntryPersistence.findByUserId(userId);

		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			deleteMicroblogsEntry(microblogsEntry);
		}
	}

	public List<MicroblogsEntry> getCompanyMicroblogsEntries(
			long companyId, int start, int end)
		throws SystemException {

		return microblogsEntryPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getCompanyMicroblogsEntriesCount(long companyId)
		throws SystemException {

		return microblogsEntryPersistence.countByCompanyId(companyId);
	}

	@Override
	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException, SystemException {

		return microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);
	}

	public List<MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
			int type, long receiverMicroblogsEntryId, int start, int end)
		throws SystemException {

		return microblogsEntryPersistence.findByT_RMEI(
			type, receiverMicroblogsEntryId, start, end,
			new EntryCreateDateComparator(true));
	}

	public List<MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
			int type, long receiverMicroblogsEntryId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return microblogsEntryPersistence.findByT_RMEI(
			type, receiverMicroblogsEntryId, start, end, orderByComparator);
	}

	public int getReceiverMicroblogsEntryMicroblogsEntriesCount(
			int type, long receiverMicroblogsEntryId)
		throws SystemException {

		return microblogsEntryPersistence.countByT_RMEI(
			type, receiverMicroblogsEntryId);
	}

	public List<MicroblogsEntry> getReceiverUserMicroblogsEntries(
			int type, long receiverUserId, int start, int end)
		throws SystemException {

		return microblogsEntryPersistence.findByT_R(
			type, receiverUserId, start, end);
	}

	public int getReceiverUserMicroblogsEntriesCount(
			int type, long receiverUserId)
		throws SystemException {

		return microblogsEntryPersistence.countByT_R(type, receiverUserId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long userId, int start, int end)
		throws SystemException {

		return microblogsEntryPersistence.findByUserId(userId, start, end);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
			long userId, int type, int start, int end)
		throws SystemException {

		return microblogsEntryPersistence.findByU_T(userId, type, start, end);
	}

	public int getUserMicroblogsEntriesCount(long userId)
		throws SystemException {

		return microblogsEntryPersistence.countByUserId(userId);
	}

	public int getUserMicroblogsEntriesCount(long userId, int type)
		throws SystemException {

		return microblogsEntryPersistence.countByU_T(userId, type);
	}

	public void updateAsset(
			MicroblogsEntry microblogsEntry, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getCompanyGroup(
			microblogsEntry.getCompanyId());

		AssetEntryLocalServiceUtil.updateEntry(
			microblogsEntry.getUserId(), group.getGroupId(),
			MicroblogsEntry.class.getName(),
			microblogsEntry.getMicroblogsEntryId(), assetCategoryIds,
			assetTagNames);
	}

	public MicroblogsEntry updateMicroblogsEntry(
			long microblogsEntryId, String content, int socialRelationType,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Microblogs entry

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);

		microblogsEntry.setModifiedDate(new Date());
		microblogsEntry.setContent(content);
		microblogsEntry.setSocialRelationType(socialRelationType);

		microblogsEntryPersistence.update(microblogsEntry);

		// Asset

		updateAsset(
			microblogsEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return microblogsEntry;
	}

	protected void sendNotificationEvent(MicroblogsEntry microblogsEntry)
		throws PortalException, SystemException {

		if (UserNotificationManagerUtil.isDeliver(
				microblogsEntry.getReceiverUserId(), PortletKeys.MICROBLOGS, 0,
				MicroblogsEntryConstants.TYPE_REPLY,
				UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			notificationEventJSONObject.put(
				"classPK", microblogsEntry.getMicroblogsEntryId());
			notificationEventJSONObject.put(
				"userId", microblogsEntry.getUserId());

			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), PortletKeys.MICROBLOGS,
					notificationEventJSONObject);

			notificationEvent.setDeliveryRequired(0);

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
				microblogsEntry.getReceiverUserId(), notificationEvent);
		}
	}

	protected void validate(int type, long receiverMicroblogsEntryId)
		throws PortalException, SystemException {

		if (receiverMicroblogsEntryId == 0) {
			return;
		}

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(
				receiverMicroblogsEntryId);

		if (microblogsEntry.getSocialRelationType() ==
				MicroblogsEntryConstants.TYPE_EVERYONE) {

			return;
		}

		if (type == MicroblogsEntryConstants.TYPE_REPOST) {
			throw new UnsupportedMicroblogsEntryException();
		}
	}

}