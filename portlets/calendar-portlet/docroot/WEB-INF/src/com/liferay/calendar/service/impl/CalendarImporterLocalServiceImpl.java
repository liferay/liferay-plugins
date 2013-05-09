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

package com.liferay.calendar.service.impl;

import com.liferay.calendar.model.CalendarBooking;
import com.liferay.calendar.model.CalendarResource;
import com.liferay.calendar.notification.NotificationType;
import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.RecurrenceSerializer;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.service.base.CalendarImporterLocalServiceBaseImpl;
import com.liferay.calendar.util.CalendarResourceUtil;
import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.model.ResourceBlockConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetLink;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.persistence.CalEventActionableDynamicQuery;
import com.liferay.portlet.messageboards.model.MBDiscussion;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.social.model.SocialActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class CalendarImporterLocalServiceImpl
	extends CalendarImporterLocalServiceBaseImpl {

	public void importCalEvent(CalEvent calEvent)
		throws PortalException, SystemException {

		// Calendar event

		if (isImported(calEvent)) {
			return;
		}

		long calendarBookingId = counterLocalService.increment();

		CalendarResource calendarResource = getCalendarResource(
			calEvent.getCompanyId(), calEvent.getGroupId());

		Date startDate = calEvent.getStartDate();

		long startTime = startDate.getTime();

		long endTime =
			startTime + calEvent.getDurationHour() * Time.HOUR +
			calEvent.getDurationMinute() * Time.MINUTE;

		if (calEvent.isAllDay()) {
			endTime = endTime - 1;
		}

		String recurrence = getRecurrence(calEvent.getRecurrenceObj());

		addCalendarBooking(
			calEvent.getUuid(), calendarBookingId, calEvent.getCompanyId(),
			calendarResource.getGroupId(), calEvent.getUserId(),
			calEvent.getUserName(), calEvent.getCreateDate(),
			calEvent.getModifiedDate(), calendarResource.getDefaultCalendarId(),
			calendarResource.getCalendarResourceId(), calEvent.getTitle(),
			calEvent.getDescription(), calEvent.getLocation(), startTime,
			endTime, calEvent.getAllDay(), recurrence,
			calEvent.getFirstReminder(), NotificationType.EMAIL,
			calEvent.getSecondReminder(), NotificationType.EMAIL);

		// Resources

		importCalendarBookingResourcePermissions(calEvent, calendarBookingId);

		// Subscriptions

		importSubscriptions(calEvent, calendarBookingId);

		// Asset

		importAssets(calEvent, calendarBookingId);

		// Message boards

		importMBDiscussion(calEvent, calendarBookingId);

		// Social

		importSocialActivities(calEvent, calendarBookingId);

		// Ratings

		importRatings(
			PortalUtil.getClassNameId(CalEvent.class.getName()),
			calEvent.getEventId(),
			PortalUtil.getClassNameId(CalendarBooking.class.getName()),
			calendarBookingId);
	}

	public void importCalEvents() throws PortalException, SystemException {
		ActionableDynamicQuery actionableDynamicQuery =
			new CalEventActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				CalEvent calEvent = (CalEvent)object;

				importCalEvent(calEvent);
			}

		};

		actionableDynamicQuery.performActions();
	}

	protected void addAssetEntry(
			long entryId, long groupId, long companyId, long userId,
			String userName, Date createDate, Date modifiedDate,
			long classNameId, long classPK, String classUuid, boolean visible,
			Date startDate, Date endDate, Date publishDate, Date expirationDate,
			String mimeType, String title, String description, String summary,
			String url, String layoutUuid, int height, int width,
			double priority, int viewCount)
		throws SystemException {

		AssetEntry assetEntry = assetEntryPersistence.create(entryId);

		assetEntry.setGroupId(groupId);
		assetEntry.setCompanyId(companyId);
		assetEntry.setUserId(userId);
		assetEntry.setUserName(userName);
		assetEntry.setCreateDate(createDate);
		assetEntry.setModifiedDate(modifiedDate);
		assetEntry.setClassNameId(classNameId);
		assetEntry.setClassPK(classPK);
		assetEntry.setClassUuid(classUuid);
		assetEntry.setVisible(visible);
		assetEntry.setStartDate(startDate);
		assetEntry.setEndDate(endDate);
		assetEntry.setPublishDate(publishDate);
		assetEntry.setExpirationDate(expirationDate);
		assetEntry.setMimeType(mimeType);
		assetEntry.setTitle(title);
		assetEntry.setDescription(description);
		assetEntry.setSummary(summary);
		assetEntry.setUrl(url);
		assetEntry.setLayoutUuid(layoutUuid);
		assetEntry.setHeight(height);
		assetEntry.setWidth(width);
		assetEntry.setPriority(priority);
		assetEntry.setViewCount(viewCount);

		assetEntryPersistence.update(assetEntry);
	}

	protected void addAssetLink(
			long linkId, long companyId, long userId, String userName,
			Date createDate, long entryId1, long entryId2, int type, int weight)
		throws SystemException {

		AssetLink assetLink = assetLinkPersistence.create(linkId);

		assetLink.setCompanyId(companyId);
		assetLink.setUserId(userId);
		assetLink.setUserName(userName);
		assetLink.setCreateDate(createDate);
		assetLink.setEntryId1(entryId1);
		assetLink.setEntryId2(entryId2);
		assetLink.setType(type);
		assetLink.setWeight(weight);

		assetLinkPersistence.update(assetLink);
	}

	protected void addCalendarBooking(
			String uuid, long calendarBookingId, long companyId, long groupId,
			long userId, String userName, Date createDate, Date modifiedDate,
			long calendarId, long calendarResourceId, String title,
			String description, String location, long startTime, long endTime,
			boolean allDay, String recurrence, int firstReminder,
			NotificationType firstReminderType, int secondReminder,
			NotificationType secondReminderType)
		throws SystemException {

		CalendarBooking calendarBooking = calendarBookingPersistence.create(
			calendarBookingId);

		calendarBooking.setUuid(uuid);
		calendarBooking.setCompanyId(companyId);
		calendarBooking.setGroupId(groupId);
		calendarBooking.setUserId(userId);
		calendarBooking.setUserName(userName);
		calendarBooking.setCreateDate(createDate);
		calendarBooking.setModifiedDate(modifiedDate);
		calendarBooking.setCalendarId(calendarId);
		calendarBooking.setCalendarResourceId(calendarResourceId);
		calendarBooking.setParentCalendarBookingId(calendarBookingId);
		calendarBooking.setTitle(title);
		calendarBooking.setDescription(description);
		calendarBooking.setLocation(location);
		calendarBooking.setStartTime(startTime);
		calendarBooking.setEndTime(endTime);
		calendarBooking.setAllDay(allDay);
		calendarBooking.setRecurrence(recurrence);
		calendarBooking.setFirstReminder(firstReminder);
		calendarBooking.setFirstReminderType(firstReminderType.toString());
		calendarBooking.setSecondReminder(secondReminder);
		calendarBooking.setSecondReminderType(secondReminderType.toString());
		calendarBooking.setStatus(WorkflowConstants.STATUS_APPROVED);
		calendarBooking.setStatusByUserId(userId);
		calendarBooking.setStatusByUserName(userName);
		calendarBooking.setStatusDate(createDate);

		calendarBookingPersistence.update(calendarBooking);
	}

	protected void addMBDiscussion(
			String uuid, long discussionId, long groupId, long companyId,
			long userId, String userName, Date createDate, Date modifiedDate,
			long classNameId, long classPK, long threadId)
		throws SystemException {

		MBDiscussion mbDiscussion = mbDiscussionPersistence.create(
			discussionId);

		mbDiscussion.setUuid(uuid);
		mbDiscussion.setGroupId(groupId);
		mbDiscussion.setCompanyId(companyId);
		mbDiscussion.setUserId(userId);
		mbDiscussion.setUserName(userName);
		mbDiscussion.setCreateDate(createDate);
		mbDiscussion.setModifiedDate(modifiedDate);
		mbDiscussion.setClassNameId(classNameId);
		mbDiscussion.setClassPK(classPK);
		mbDiscussion.setThreadId(threadId);

		mbDiscussionPersistence.update(mbDiscussion);
	}

	protected void addMBMessage(
			String uuid, long messageId, long groupId, long companyId,
			long userId, String userName, Date createDate, Date modifiedDate,
			long classNameId, long classPK, long categoryId, long threadId,
			long rootMessageId, long parentMessageId, String subject,
			String body, String format, boolean anonymous, double priority,
			boolean allowPingbacks, boolean answer, int status,
			long statusByUserId, String statusByUserName, Date statusDate,
			Map<Long, Long> mbMessageIds)
		throws PortalException, SystemException {

		if (parentMessageId == MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID) {
			rootMessageId = messageId;
		}
		else {
			rootMessageId = importMBMessage(
				rootMessageId, threadId, classPK, mbMessageIds);

			parentMessageId = importMBMessage(
				parentMessageId, threadId, classPK, mbMessageIds);
		}

		MBMessage mbMessage = mbMessagePersistence.create(messageId);

		mbMessage.setUuid(uuid);
		mbMessage.setGroupId(groupId);
		mbMessage.setCompanyId(companyId);
		mbMessage.setUserId(userId);
		mbMessage.setUserName(userName);
		mbMessage.setCreateDate(createDate);
		mbMessage.setModifiedDate(modifiedDate);
		mbMessage.setClassNameId(classNameId);
		mbMessage.setClassPK(classPK);
		mbMessage.setCategoryId(categoryId);
		mbMessage.setThreadId(threadId);
		mbMessage.setRootMessageId(rootMessageId);
		mbMessage.setParentMessageId(parentMessageId);
		mbMessage.setSubject(subject);
		mbMessage.setBody(body);
		mbMessage.setFormat(format);
		mbMessage.setAnonymous(anonymous);
		mbMessage.setPriority(priority);
		mbMessage.setAllowPingbacks(allowPingbacks);
		mbMessage.setAnswer(answer);
		mbMessage.setStatus(status);
		mbMessage.setStatusByUserId(statusByUserId);
		mbMessage.setStatusByUserName(statusByUserName);
		mbMessage.setStatusDate(statusDate);

		mbMessagePersistence.update(mbMessage);
	}

	protected void addMBThread(
			String uuid, long threadId, long groupId, long companyId,
			long userId, String userName, Date createDate, Date modifiedDate,
			long categoryId, long rootMessageId, long rootMessageUserId,
			int messageCount, int viewCount, long lastPostByUserId,
			Date lastPostDate, double priority, boolean question, int status,
			long statusByUserId, String statusByUserName, Date statusDate)
		throws SystemException {

		MBThread mbThread = mbThreadPersistence.create(threadId);

		mbThread.setUuid(uuid);
		mbThread.setGroupId(groupId);
		mbThread.setCompanyId(companyId);
		mbThread.setUserId(userId);
		mbThread.setUserName(userName);
		mbThread.setCreateDate(createDate);
		mbThread.setModifiedDate(modifiedDate);
		mbThread.setCategoryId(categoryId);
		mbThread.setRootMessageId(rootMessageId);
		mbThread.setRootMessageUserId(rootMessageUserId);
		mbThread.setMessageCount(messageCount);
		mbThread.setViewCount(viewCount);
		mbThread.setLastPostByUserId(lastPostByUserId);
		mbThread.setLastPostDate(lastPostDate);
		mbThread.setPriority(priority);
		mbThread.setQuestion(question);
		mbThread.setStatus(status);
		mbThread.setStatusByUserId(statusByUserId);
		mbThread.setStatusByUserName(statusByUserName);
		mbThread.setStatusDate(statusDate);

		mbThreadPersistence.update(mbThread);
	}

	protected RatingsEntry addRatingsEntry(
			long entryId, long companyId, long userId, String userName,
			Date createDate, Date modifiedDate, long classNameId, long classPK,
			double score)
		throws SystemException {

		RatingsEntry ratingsEntry = ratingsEntryPersistence.create(entryId);

		ratingsEntry.setCompanyId(companyId);
		ratingsEntry.setUserId(userId);
		ratingsEntry.setUserName(userName);
		ratingsEntry.setCreateDate(createDate);
		ratingsEntry.setModifiedDate(modifiedDate);
		ratingsEntry.setClassNameId(classNameId);
		ratingsEntry.setClassPK(classPK);
		ratingsEntry.setScore(score);

		return ratingsEntryPersistence.update(ratingsEntry);
	}

	protected RatingsStats addRatingsStats(
			long statsId, long classNameId, long classPK, int totalEntries,
			double totalScore, double averageScore)
		throws SystemException {

		RatingsStats ratingsStats = ratingsStatsPersistence.create(statsId);

		ratingsStats.setClassNameId(classNameId);
		ratingsStats.setClassPK(classPK);
		ratingsStats.setTotalEntries(totalEntries);
		ratingsStats.setTotalScore(totalScore);
		ratingsStats.setAverageScore(averageScore);

		return ratingsStatsPersistence.update(ratingsStats);
	}

	protected void addSocialActivity(
			long activityId, long groupId, long companyId, long userId,
			long createDate, long mirrorActivityId, long classNameId,
			long classPK, int type, String extraData, long receiverUserId)
		throws SystemException {

		SocialActivity socialActivity = socialActivityPersistence.create(
			activityId);

		socialActivity.setGroupId(groupId);
		socialActivity.setCompanyId(companyId);
		socialActivity.setUserId(userId);
		socialActivity.setCreateDate(createDate);
		socialActivity.setMirrorActivityId(mirrorActivityId);
		socialActivity.setClassNameId(classNameId);
		socialActivity.setClassPK(classPK);
		socialActivity.setType(type);
		socialActivity.setExtraData(extraData);
		socialActivity.setReceiverUserId(receiverUserId);

		socialActivityPersistence.update(socialActivity);
	}

	protected void addSubscription(
			long subscriptionId, long companyId, long userId, String userName,
			Date createDate, Date modifiedDate, long classNameId, long classPK,
			String frequency)
		throws SystemException {

		Subscription subscription = subscriptionPersistence.create(
			subscriptionId);

		subscription.setCompanyId(companyId);
		subscription.setUserId(userId);
		subscription.setUserName(userName);
		subscription.setCreateDate(createDate);
		subscription.setModifiedDate(modifiedDate);
		subscription.setClassNameId(classNameId);
		subscription.setClassPK(classPK);
		subscription.setFrequency(frequency);

		subscriptionPersistence.update(subscription);
	}

	protected long getActionId(
			ResourceAction oldResourceAction, String newClassName)
		throws SystemException {

		ResourceAction newResourceAction = resourceActionPersistence.fetchByN_A(
			newClassName, oldResourceAction.getActionId());

		if (newResourceAction == null) {
			return 0;
		}

		return newResourceAction.getBitwiseValue();
	}

	protected long getActionIds(
			ResourcePermission resourcePermission, String oldClassName,
			String newClassName)
		throws SystemException {

		long actionIds = 0;

		List<ResourceAction> oldResourceActions =
			resourceActionPersistence.findByName(oldClassName);

		for (ResourceAction oldResourceAction : oldResourceActions) {
			boolean hasActionId = resourcePermissionLocalService.hasActionId(
				resourcePermission, oldResourceAction);

			if (!hasActionId) {
				continue;
			}

			actionIds = actionIds | getActionId(
				oldResourceAction, newClassName);
		}

		return actionIds;
	}

	protected AssetCategory getAssetCategory(
			long userId, long groupId, String name)
		throws PortalException, SystemException {

		String assetVocabularyDefault = PropsUtil.get(
			PropsKeys.ASSET_VOCABULARY_DEFAULT);

		AssetVocabulary assetVocabulary = assetVocabularyPersistence.findByG_N(
			groupId, assetVocabularyDefault);

		AssetCategory assetCategory = assetCategoryPersistence.fetchByP_N_V(
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, name,
			assetVocabulary.getVocabularyId());

		if (assetCategory != null) {
			return assetCategory;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setUserId(userId);

		return assetCategoryLocalService.addCategory(
			userId, name, assetVocabulary.getVocabularyId(), serviceContext);
	}

	protected CalendarResource getCalendarResource(long companyId, long groupId)
		throws PortalException, SystemException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		long userId = UserLocalServiceUtil.getDefaultUserId(companyId);

		serviceContext.setUserId(userId);

		CalendarResource calendarResource =
			CalendarResourceUtil.getGroupCalendarResource(
				groupId, serviceContext);

		return calendarResource;
	}

	protected String getRecurrence(TZSRecurrence tzsRecurrence) {
		if (tzsRecurrence == null) {
			return null;
		}

		Recurrence recurrence = new Recurrence();

		Frequency frequency = _frequencyMap.get(tzsRecurrence.getFrequency());

		int interval = tzsRecurrence.getInterval();

		List<Weekday> weekdays = new ArrayList<Weekday>();

		if ((frequency == Frequency.DAILY) && (interval == 0)) {
			frequency = Frequency.WEEKLY;

			interval = 1;

			weekdays.add(Weekday.MONDAY);
			weekdays.add(Weekday.TUESDAY);
			weekdays.add(Weekday.WEDNESDAY);
			weekdays.add(Weekday.THURSDAY);
			weekdays.add(Weekday.FRIDAY);
		}
		else if (frequency == Frequency.WEEKLY) {
			DayAndPosition[] dayAndPositions = tzsRecurrence.getByDay();

			for (DayAndPosition dayAndPosition : dayAndPositions) {
				Weekday weekday = _weekdayMap.get(
					dayAndPosition.getDayOfWeek());

				weekdays.add(weekday);
			}
		}

		recurrence.setInterval(interval);
		recurrence.setFrequency(frequency);
		recurrence.setWeekdays(weekdays);

		Calendar untilJCalendar = tzsRecurrence.getUntil();

		int ocurrence = tzsRecurrence.getOccurrence();

		if (untilJCalendar != null) {
			recurrence.setUntilJCalendar(untilJCalendar);
		}
		else if (ocurrence > 0) {
			recurrence.setCount(ocurrence);
		}

		return RecurrenceSerializer.serialize(recurrence);
	}

	protected void importAssetLink(AssetLink assetLink, long entryId1)
		throws PortalException, SystemException {

		long entryId2 = assetLink.getEntryId2();

		AssetEntry assetEntry2 = assetEntryPersistence.findByPrimaryKey(
			entryId2);

		if (assetEntry2.getClassNameId() ==
				PortalUtil.getClassNameId(CalEvent.class)) {

			CalEvent calEvent = calEventPersistence.findByPrimaryKey(
				assetEntry2.getClassPK());

			importCalEvent(calEvent);

			assetEntry2 = assetEntryPersistence.findByG_CU(
				calEvent.getGroupId(), calEvent.getUuid());

			entryId2 = assetEntry2.getEntryId();
		}

		long linkId = counterLocalService.increment();

		addAssetLink(
			linkId, assetLink.getCompanyId(), assetLink.getUserId(),
			assetLink.getUserName(), assetLink.getCreateDate(), entryId1,
			entryId2, assetLink.getType(), assetLink.getWeight());
	}

	protected void importAssets(CalEvent calEvent, long calendarBookingId)
		throws PortalException, SystemException {

		// Asset entry

		AssetEntry assetEntry = assetEntryPersistence.fetchByC_C(
			PortalUtil.getClassNameId(CalEvent.class.getName()),
			calEvent.getEventId());

		if (assetEntry == null) {
			return;
		}

		long entryId = counterLocalService.increment();

		addAssetEntry(
			entryId, assetEntry.getGroupId(), assetEntry.getCompanyId(),
			assetEntry.getUserId(), assetEntry.getUserName(),
			assetEntry.getCreateDate(), assetEntry.getModifiedDate(),
			PortalUtil.getClassNameId(CalendarBooking.class.getName()),
			calendarBookingId, calEvent.getUuid(), assetEntry.isVisible(),
			assetEntry.getStartDate(), assetEntry.getEndDate(),
			assetEntry.getPublishDate(), assetEntry.getExpirationDate(),
			assetEntry.getMimeType(), assetEntry.getTitle(),
			assetEntry.getDescription(), assetEntry.getSummary(),
			assetEntry.getUrl(), assetEntry.getLayoutUuid(),
			assetEntry.getHeight(), assetEntry.getWidth(),
			assetEntry.getPriority(), assetEntry.getViewCount());

		// Asset categories

		List<AssetCategory> assetCategories = new ArrayList<AssetCategory>();

		assetCategories.addAll(assetEntry.getCategories());

		assetCategories.add(
			getAssetCategory(
				calEvent.getUserId(), calEvent.getGroupId(),
				calEvent.getType()));

		for (AssetCategory assetCategory : assetCategories) {
			assetEntryLocalService.addAssetCategoryAssetEntry(
				assetCategory.getCategoryId(), entryId);
		}

		// Asset links

		List<AssetLink> assetLinks = assetLinkLocalService.getDirectLinks(
			assetEntry.getEntryId());

		for (AssetLink assetLink : assetLinks) {
			importAssetLink(assetLink, entryId);
		}

		// Asset tags

		List<AssetTag> assetTags = assetEntry.getTags();

		for (AssetTag assetTag : assetTags) {
			assetEntryLocalService.addAssetTagAssetEntry(
				assetTag.getTagId(), entryId);
		}
	}

	protected void importCalendarBookingResourcePermission(
			ResourcePermission resourcePermission, long calendarBookingId)
		throws PortalException, SystemException {

		CalendarBooking calendarBooking =
			calendarBookingPersistence.findByPrimaryKey(calendarBookingId);

		long actionIds = getActionIds(
			resourcePermission, CalEvent.class.getName(),
			CalendarBooking.class.getName());

		resourceBlockLocalService.updateIndividualScopePermissions(
			calendarBooking.getCompanyId(),
			calendarBooking.getResourceGroupId(),
			CalendarBooking.class.getName(), calendarBooking,
			resourcePermission.getRoleId(), actionIds,
			ResourceBlockConstants.OPERATOR_SET);
	}

	protected void importCalendarBookingResourcePermissions(
			CalEvent calEvent, long calendarBookingId)
		throws PortalException, SystemException {

		List<ResourcePermission> resourcePermissions =
			resourcePermissionPersistence.findByC_N_S_P(
				calEvent.getCompanyId(), CalEvent.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL,
				String.valueOf(calEvent.getEventId()));

		for (ResourcePermission resourcePermission : resourcePermissions) {
			importCalendarBookingResourcePermission(
				resourcePermission, calendarBookingId);
		}
	}

	protected void importMBDiscussion(CalEvent calEvent, long calendarBookingId)
		throws PortalException, SystemException {

		MBDiscussion mbDiscussion = mbDiscussionPersistence.fetchByC_C(
			PortalUtil.getClassNameId(CalEvent.class), calEvent.getEventId());

		if (mbDiscussion == null) {
			return;
		}

		long threadId = importMBThread(
			mbDiscussion.getThreadId(), calendarBookingId);

		addMBDiscussion(
			PortalUUIDUtil.generate(), counterLocalService.increment(),
			mbDiscussion.getGroupId(), mbDiscussion.getCompanyId(),
			mbDiscussion.getUserId(), mbDiscussion.getUserName(),
			mbDiscussion.getCreateDate(), mbDiscussion.getModifiedDate(),
			PortalUtil.getClassNameId(CalendarBooking.class.getName()),
			calendarBookingId, threadId);
	}

	protected long importMBMessage(
			long messageId, long threadId, long calendarBookingId,
			Map<Long, Long> mbMessageIds)
		throws PortalException, SystemException {

		MBMessage mbMessage = mbMessagePersistence.findByPrimaryKey(messageId);

		return importMBMessage(
			mbMessage, threadId, calendarBookingId, mbMessageIds);
	}

	protected long importMBMessage(
			MBMessage mbMessage, long threadId, long calendarBookingId,
			Map<Long, Long> mbMessageIds)
		throws PortalException, SystemException {

		Long messageId = mbMessageIds.get(mbMessage.getMessageId());

		if (messageId != null) {
			return messageId;
		}

		messageId = counterLocalService.increment();

		addMBMessage(
			PortalUUIDUtil.generate(), messageId, mbMessage.getGroupId(),
			mbMessage.getCompanyId(), mbMessage.getUserId(),
			mbMessage.getUserName(), mbMessage.getCreateDate(),
			mbMessage.getModifiedDate(),
			PortalUtil.getClassNameId(CalendarBooking.class.getName()),
			calendarBookingId, mbMessage.getCategoryId(), threadId,
			mbMessage.getRootMessageId(), mbMessage.getParentMessageId(),
			mbMessage.getSubject(), mbMessage.getBody(), mbMessage.getFormat(),
			mbMessage.isAnonymous(), mbMessage.getPriority(),
			mbMessage.getAllowPingbacks(), mbMessage.isAnswer(),
			mbMessage.getStatus(), mbMessage.getStatusByUserId(),
			mbMessage.getStatusByUserName(), mbMessage.getStatusDate(),
			mbMessageIds);

		long mbDiscussionClassNameId = PortalUtil.getClassNameId(
			MBDiscussion.class.getName());

		importRatings(
			mbDiscussionClassNameId, mbMessage.getMessageId(),
			mbDiscussionClassNameId, messageId);

		mbMessageIds.put(mbMessage.getMessageId(), messageId);

		return messageId;
	}

	protected long importMBThread(long threadId, long calendarBookingId)
		throws PortalException, SystemException {

		MBThread mbThread = mbThreadPersistence.findByPrimaryKey(threadId);

		return importMBThread(mbThread, calendarBookingId);
	}

	protected long importMBThread(MBThread mbThread, long calendarBookingId)
		throws PortalException, SystemException {

		long threadId = counterLocalService.increment();

		addMBThread(
			PortalUUIDUtil.generate(), threadId, mbThread.getGroupId(),
			mbThread.getCompanyId(), mbThread.getUserId(),
			mbThread.getUserName(), mbThread.getCreateDate(),
			mbThread.getModifiedDate(), mbThread.getCategoryId(), 0,
			mbThread.getRootMessageUserId(), mbThread.getMessageCount(),
			mbThread.getViewCount(), mbThread.getLastPostByUserId(),
			mbThread.getLastPostDate(), mbThread.getPriority(),
			mbThread.isQuestion(), mbThread.getStatus(),
			mbThread.getStatusByUserId(), mbThread.getStatusByUserName(),
			mbThread.getStatusDate());

		Map<Long, Long> mbMessageIds = new HashMap<Long, Long>();

		List<MBMessage> mbMessages = mbMessagePersistence.findByThreadId(
			mbThread.getThreadId());

		for (MBMessage mbMessage : mbMessages) {
			importMBMessage(
				mbMessage, threadId, calendarBookingId, mbMessageIds);
		}

		updateMBThreadRootMessageId(
			threadId, mbMessageIds.get(mbThread.getRootMessageId()));

		return threadId;
	}

	protected void importRatings(
			long oldClassNameId, long oldClassPK, long classNameId,
			long classPK)
		throws SystemException {

		List<RatingsEntry> ratingsEntries = ratingsEntryPersistence.findByC_C(
			oldClassNameId, oldClassPK);

		for (RatingsEntry ratingsEntry : ratingsEntries) {
			addRatingsEntry(
				counterLocalService.increment(), ratingsEntry.getCompanyId(),
				ratingsEntry.getUserId(), ratingsEntry.getUserName(),
				ratingsEntry.getCreateDate(), ratingsEntry.getModifiedDate(),
				classNameId, classPK, ratingsEntry.getScore());
		}

		RatingsStats ratingsStats = ratingsStatsPersistence.fetchByC_C(
			oldClassNameId, oldClassPK);

		if (ratingsStats == null) {
			return;
		}

		addRatingsStats(
			counterLocalService.increment(), classNameId, classPK,
			ratingsStats.getTotalEntries(), ratingsStats.getTotalScore(),
			ratingsStats.getAverageScore());
	}

	protected void importSocialActivities(
			CalEvent calEvent, long calendarBookingId)
		throws SystemException {

		List<SocialActivity> socialActivities =
			socialActivityPersistence.findByC_C(
				PortalUtil.getClassNameId(CalEvent.class),
				calEvent.getEventId());

		for (SocialActivity socialActivity : socialActivities) {
			importSocialActivity(socialActivity, calendarBookingId);
		}
	}

	protected void importSocialActivity(
			SocialActivity socialActivity, long calendarBookingId)
		throws SystemException {

		addSocialActivity(
			counterLocalService.increment(SocialActivity.class.getName()),
			socialActivity.getGroupId(), socialActivity.getCompanyId(),
			socialActivity.getUserId(), socialActivity.getCreateDate(),
			socialActivity.getMirrorActivityId(),
			PortalUtil.getClassNameId(CalendarBooking.class), calendarBookingId,
			socialActivity.getType(), socialActivity.getExtraData(),
			socialActivity.getReceiverUserId());
	}

	protected void importSubscription(
			Subscription subscription, long calendarBookingId)
		throws SystemException {

		addSubscription(
			counterLocalService.increment(), subscription.getCompanyId(),
			subscription.getUserId(), subscription.getUserName(),
			subscription.getCreateDate(), subscription.getModifiedDate(),
			PortalUtil.getClassNameId(CalendarBooking.class), calendarBookingId,
			subscription.getFrequency());
	}

	protected void importSubscriptions(
			CalEvent calEvent, long calendarBookingId)
		throws SystemException {

		List<Subscription> subscriptions = subscriptionPersistence.findByC_C_C(
			calEvent.getCompanyId(), PortalUtil.getClassNameId(CalEvent.class),
			calEvent.getEventId());

		for (Subscription subscription : subscriptions) {
			importSubscription(subscription, calendarBookingId);
		}
	}

	protected boolean isImported(CalEvent calEvent)
		throws PortalException, SystemException {

		Group group = groupLocalService.getCompanyGroup(
			calEvent.getCompanyId());

		CalendarBooking calendarBooking =
			calendarBookingPersistence.fetchByUUID_G(
				calEvent.getUuid(), group.getGroupId());

		if (calendarBooking != null) {
			return true;
		}

		return false;
	}

	protected void updateMBThreadRootMessageId(
			long threadId, long rootMessageId)
		throws PortalException, SystemException {

		MBThread mbThread = mbThreadPersistence.findByPrimaryKey(threadId);

		mbThread.setRootMessageId(rootMessageId);

		mbThreadPersistence.update(mbThread);
	}

	private static Map<Integer, Frequency> _frequencyMap =
		new HashMap<Integer, Frequency>();
	private static Map<Integer, Weekday> _weekdayMap =
		new HashMap<Integer, Weekday>();

	static {
		_frequencyMap.put(TZSRecurrence.DAILY, Frequency.DAILY);
		_frequencyMap.put(TZSRecurrence.WEEKLY, Frequency.WEEKLY);
		_frequencyMap.put(TZSRecurrence.MONTHLY, Frequency.MONTHLY);
		_frequencyMap.put(TZSRecurrence.YEARLY, Frequency.YEARLY);

		_weekdayMap.put(Calendar.SUNDAY, Weekday.SUNDAY);
		_weekdayMap.put(Calendar.MONDAY, Weekday.MONDAY);
		_weekdayMap.put(Calendar.TUESDAY, Weekday.TUESDAY);
		_weekdayMap.put(Calendar.WEDNESDAY, Weekday.WEDNESDAY);
		_weekdayMap.put(Calendar.THURSDAY, Weekday.THURSDAY);
		_weekdayMap.put(Calendar.FRIDAY, Weekday.FRIDAY);
		_weekdayMap.put(Calendar.SATURDAY, Weekday.SATURDAY);
	}

}