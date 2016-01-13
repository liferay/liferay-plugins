/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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
import com.liferay.microblogs.util.MicroblogsUtil;
import com.liferay.microblogs.util.PortletKeys;
import com.liferay.microblogs.util.comparator.EntryCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Jonathan Lee
 */
public class MicroblogsEntryLocalServiceImpl
	extends MicroblogsEntryLocalServiceBaseImpl {

	public MicroblogsEntry addMicroblogsEntry(
			long userId, long creatorClassNameId, long creatorClassPK,
			String content, int type, long parentMicroblogsEntryId,
			int socialRelationType, ServiceContext serviceContext)
		throws PortalException {

		// Microblogs entry

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(type, parentMicroblogsEntryId);

		long microblogsEntryId = counterLocalService.increment();

		if (parentMicroblogsEntryId == 0) {
			parentMicroblogsEntryId = microblogsEntryId;
		}

		MicroblogsEntry microblogsEntry = microblogsEntryPersistence.create(
			microblogsEntryId);

		microblogsEntry.setCompanyId(user.getCompanyId());
		microblogsEntry.setUserId(user.getUserId());
		microblogsEntry.setUserName(user.getFullName());
		microblogsEntry.setCreateDate(now);
		microblogsEntry.setModifiedDate(now);
		microblogsEntry.setCreatorClassNameId(creatorClassNameId);
		microblogsEntry.setCreatorClassPK(creatorClassPK);
		microblogsEntry.setContent(content);
		microblogsEntry.setType(type);
		microblogsEntry.setParentMicroblogsEntryId(parentMicroblogsEntryId);
		microblogsEntry.setSocialRelationType(socialRelationType);

		microblogsEntryPersistence.update(microblogsEntry);

		// Resources

		resourceLocalService.addModelResources(microblogsEntry, serviceContext);

		// Asset

		updateAsset(
			microblogsEntry, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		return microblogsEntry;
	}

	public MicroblogsEntry addMicroblogsEntry(
			long userId, String content, int type, long parentMicroblogsEntryId,
			int socialRelationType, ServiceContext serviceContext)
		throws PortalException {

		// Microblogs entry

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(type, parentMicroblogsEntryId);

		long microblogsEntryId = counterLocalService.increment();

		if (parentMicroblogsEntryId == 0) {
			parentMicroblogsEntryId = microblogsEntryId;
		}

		MicroblogsEntry microblogsEntry = microblogsEntryPersistence.create(
			microblogsEntryId);

		microblogsEntry.setCompanyId(user.getCompanyId());
		microblogsEntry.setUserId(user.getUserId());
		microblogsEntry.setUserName(user.getFullName());
		microblogsEntry.setCreateDate(now);
		microblogsEntry.setModifiedDate(now);
		microblogsEntry.setCreatorClassNameId(
			classNameLocalService.getClassNameId(User.class));
		microblogsEntry.setCreatorClassPK(user.getUserId());
		microblogsEntry.setContent(content);
		microblogsEntry.setType(type);
		microblogsEntry.setParentMicroblogsEntryId(parentMicroblogsEntryId);
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
			"parentMicroblogsEntryId", parentMicroblogsEntryId);

		SocialActivityLocalServiceUtil.addActivity(
			userId, 0, MicroblogsEntry.class.getName(), microblogsEntryId,
			activityKey, extraDataJSONObject.toString(),
			microblogsEntry.getParentMicroblogsEntryUserId());

		// Notification

		subscribeUsers(microblogsEntry, serviceContext);

		sendNotificationEvent(microblogsEntry, serviceContext);

		return microblogsEntry;
	}

	public void deleteMicroblogsEntries(
			long creatorClassNameId, long creatorClassPK)
		throws PortalException {

		microblogsEntryPersistence.removeByCCNI_CCPK(
			creatorClassNameId, creatorClassPK);
	}

	@Override
	public MicroblogsEntry deleteMicroblogsEntry(long microblogsEntryId)
		throws PortalException {

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);

		return deleteMicroblogsEntry(microblogsEntry);
	}

	@Override
	public MicroblogsEntry deleteMicroblogsEntry(
			MicroblogsEntry microblogsEntry)
		throws PortalException {

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
		throws PortalException {

		List<MicroblogsEntry> microblogsEntries =
			microblogsEntryPersistence.findByUserId(userId);

		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			deleteMicroblogsEntry(microblogsEntry);
		}
	}

	public List<MicroblogsEntry> getCompanyMicroblogsEntries(
		long companyId, int start, int end) {

		return microblogsEntryPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getCompanyMicroblogsEntriesCount(long companyId) {
		return microblogsEntryPersistence.countByCompanyId(companyId);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, int type, int start, int end,
		OrderByComparator obc) {

		return microblogsEntryPersistence.findByCCNI_T(
			creatorClassNameId, type, start, end, obc);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int start, int end) {

		return microblogsEntryPersistence.findByCCNI_CCPK(
			creatorClassNameId, creatorClassPK, start, end);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end) {

		return microblogsEntryPersistence.findByCCNI_CCPK_T(
			creatorClassNameId, creatorClassPK, type, start, end);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, String assetTagName,
		boolean andOperator, int start, int end) {

		return microblogsEntryFinder.findByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, andOperator,
			start, end);
	}

	public List<MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, String assetTagName, int start, int end) {

		return microblogsEntryFinder.findByCCNI_ATN(
			creatorClassNameId, assetTagName, start, end);
	}

	public int getMicroblogsEntriesCount(
		long creatorClassNameId, long creatorClassPK) {

		return microblogsEntryPersistence.countByCCNI_CCPK(
			creatorClassNameId, creatorClassPK);
	}

	public int getMicroblogsEntriesCount(
		long creatorClassNameId, long creatorClassPK, int type) {

		return microblogsEntryPersistence.countByCCNI_CCPK_T(
			creatorClassNameId, creatorClassPK, type);
	}

	public int getMicroblogsEntriesCount(
		long creatorClassNameId, long creatorClassPK, String assetTagName,
		boolean andOperator) {

		return microblogsEntryFinder.countByCCNI_CCPK_ATN(
			creatorClassNameId, creatorClassPK, assetTagName, andOperator);
	}

	public int getMicroblogsEntriesCount(
		long creatorClassNameId, String assetTagName) {

		return microblogsEntryFinder.countByCCNI_ATN(
			creatorClassNameId, assetTagName);
	}

	@Override
	public MicroblogsEntry getMicroblogsEntry(long microblogsEntryId)
		throws PortalException {

		return microblogsEntryPersistence.findByPrimaryKey(microblogsEntryId);
	}

	public List<MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end) {

		return microblogsEntryPersistence.findByT_P(
			type, parentMicroblogsEntryId, start, end,
			new EntryCreateDateComparator(true));
	}

	public List<MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end,
		OrderByComparator<MicroblogsEntry> orderByComparator) {

		return microblogsEntryPersistence.findByT_P(
			type, parentMicroblogsEntryId, start, end, orderByComparator);
	}

	public int getParentMicroblogsEntryMicroblogsEntriesCount(
		int type, long parentMicroblogsEntryId) {

		return microblogsEntryPersistence.countByT_P(
			type, parentMicroblogsEntryId);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int start, int end) {

		return microblogsEntryPersistence.findByUserId(userId, start, end);
	}

	public List<MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int type, int start, int end) {

		return microblogsEntryPersistence.findByU_T(userId, type, start, end);
	}

	public int getUserMicroblogsEntriesCount(long userId) {
		return microblogsEntryPersistence.countByUserId(userId);
	}

	public int getUserMicroblogsEntriesCount(long userId, int type) {
		return microblogsEntryPersistence.countByU_T(userId, type);
	}

	public void updateAsset(
			MicroblogsEntry microblogsEntry, long[] assetCategoryIds,
			String[] assetTagNames)
		throws PortalException {

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
		throws PortalException {

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

	protected static long getSubscriptionId(
		long userId, MicroblogsEntry microblogsEntry) {

		try {
			Subscription subscription =
				SubscriptionLocalServiceUtil.getSubscription(
					microblogsEntry.getCompanyId(), userId,
					MicroblogsEntry.class.getName(),
					microblogsEntry.getParentMicroblogsEntryId());

			return subscription.getSubscriptionId();
		}
		catch (PortalException pe) {
		}

		return 0;
	}

	protected void sendNotificationEvent(
			final MicroblogsEntry microblogsEntry,
			ServiceContext serviceContext)
		throws PortalException {

		final JSONObject notificationEventJSONObject =
			JSONFactoryUtil.createJSONObject();

		notificationEventJSONObject.put(
			"className", MicroblogsEntry.class.getName());
		notificationEventJSONObject.put(
			"classPK", microblogsEntry.getMicroblogsEntryId());
		notificationEventJSONObject.put(
			"entryTitle",
			MicroblogsUtil.getProcessedContent(
				StringUtil.shorten(microblogsEntry.getContent(), 50),
				serviceContext));

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				MicroblogsEntry.class.getName());

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			microblogsEntry.getMicroblogsEntryId());

		String entryURL = StringPool.BLANK;

		try {
			entryURL = assetRenderer.getURLViewInContext(
				serviceContext.getLiferayPortletRequest(),
				serviceContext.getLiferayPortletResponse(), null);
		}
		catch (Exception e) {
		}

		notificationEventJSONObject.put("entryURL", entryURL);
		notificationEventJSONObject.put("userId", microblogsEntry.getUserId());

		final List<Long> receiverUserIds = MicroblogsUtil.getSubscriberUserIds(
			microblogsEntry);

		Callable<Void> callable = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				MessageBusUtil.sendMessage(
					DestinationNames.ASYNC_SERVICE,
					new NotificationProcessCallable(
						receiverUserIds, microblogsEntry,
						notificationEventJSONObject));

				return null;
			}

		};

		TransactionCommitCallbackUtil.registerCallback(callable);
	}

	protected void subscribeUsers(
			MicroblogsEntry microblogsEntry, ServiceContext serviceContext)
		throws PortalException {

		long rootMicroblogsEntryId = MicroblogsUtil.getRootMicroblogsEntryId(
			microblogsEntry);

		SubscriptionLocalServiceUtil.addSubscription(
			microblogsEntry.getUserId(), serviceContext.getScopeGroupId(),
			MicroblogsEntry.class.getName(), rootMicroblogsEntryId);

		List<String> screenNames = MicroblogsUtil.getScreenNames(
			microblogsEntry.getContent());

		for (String screenName : screenNames) {
			long userId = UserLocalServiceUtil.getUserIdByScreenName(
				serviceContext.getCompanyId(), screenName);

			SubscriptionLocalServiceUtil.addSubscription(
				userId, serviceContext.getScopeGroupId(),
				MicroblogsEntry.class.getName(), rootMicroblogsEntryId);
		}
	}

	protected void validate(int type, long parentMicroblogsEntryId)
		throws PortalException {

		if (parentMicroblogsEntryId == 0) {
			return;
		}

		MicroblogsEntry microblogsEntry =
			microblogsEntryPersistence.findByPrimaryKey(
				parentMicroblogsEntryId);

		if (microblogsEntry.getSocialRelationType() ==
				MicroblogsEntryConstants.TYPE_EVERYONE) {

			return;
		}

		if (type == MicroblogsEntryConstants.TYPE_REPOST) {
			throw new UnsupportedMicroblogsEntryException();
		}
	}

	private static class NotificationProcessCallable
		implements ProcessCallable<Serializable> {

		public NotificationProcessCallable(
			List<Long> receiverUserIds, MicroblogsEntry microblogsEntry,
			JSONObject notificationEventJSONObject) {

			_receiverUserIds = receiverUserIds;
			_microblogsEntry = microblogsEntry;
			_notificationEventJSONObject = notificationEventJSONObject;
		}

		@Override
		public Serializable call() throws ProcessException {
			try {
				sendUserNotifications(
					_receiverUserIds, _microblogsEntry,
					_notificationEventJSONObject);
			}
			catch (Exception e) {
				throw new ProcessException(e);
			}

			return null;
		}

		protected void sendUserNotifications(
				List<Long> receiverUserIds, MicroblogsEntry microblogsEntry,
				JSONObject notificationEventJSONObject)
			throws PortalException {

			int count = receiverUserIds.size();

			int pages = count / Indexer.DEFAULT_INTERVAL;

			for (int i = 0; i <= pages; i++) {
				int start = (i * Indexer.DEFAULT_INTERVAL);
				int end = start + Indexer.DEFAULT_INTERVAL;

				if (count < end) {
					end = count;
				}

				for (int j = start; j < end; j++) {
					long subscriptionId = getSubscriptionId(
						receiverUserIds.get(j), microblogsEntry);

					notificationEventJSONObject.put(
						"subscriptionId", subscriptionId);

					int notificationType = MicroblogsUtil.getNotificationType(
						microblogsEntry, receiverUserIds.get(j),
						UserNotificationDeliveryConstants.TYPE_PUSH);

					if (notificationType !=
							MicroblogsEntryConstants.
								NOTIFICATION_TYPE_UNKNOWN) {

						notificationEventJSONObject.put(
							"notificationType", notificationType);

						UserNotificationEventLocalServiceUtil.
							sendUserNotificationEvents(
								receiverUserIds.get(j), PortletKeys.MICROBLOGS,
								UserNotificationDeliveryConstants.TYPE_PUSH,
								notificationEventJSONObject);
					}

					notificationType = MicroblogsUtil.getNotificationType(
						microblogsEntry, receiverUserIds.get(j),
						UserNotificationDeliveryConstants.TYPE_WEBSITE);

					if (notificationType !=
							MicroblogsEntryConstants.
								NOTIFICATION_TYPE_UNKNOWN) {

						notificationEventJSONObject.put(
							"notificationType", notificationType);

						UserNotificationEventLocalServiceUtil.
							sendUserNotificationEvents(
								receiverUserIds.get(j), PortletKeys.MICROBLOGS,
								UserNotificationDeliveryConstants.TYPE_WEBSITE,
								notificationEventJSONObject);
					}
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private MicroblogsEntry _microblogsEntry;
		private JSONObject _notificationEventJSONObject;
		private List<Long> _receiverUserIds;

	}

}