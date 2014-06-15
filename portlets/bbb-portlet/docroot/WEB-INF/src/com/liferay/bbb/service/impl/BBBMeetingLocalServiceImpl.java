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

package com.liferay.bbb.service.impl;

import com.liferay.bbb.model.BBBMeeting;
import com.liferay.bbb.model.BBBMeetingConstants;
import com.liferay.bbb.model.BBBParticipant;
import com.liferay.bbb.model.BBBParticipantConstants;
import com.liferay.bbb.service.base.BBBMeetingLocalServiceBaseImpl;
import com.liferay.bbb.util.BBBUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Shinn Lok
 */
public class BBBMeetingLocalServiceImpl extends BBBMeetingLocalServiceBaseImpl {

	@Override
	public BBBMeeting addBBBMeeting(
			long userId, long groupId, long bbbServerId, String name,
			String description, String attendeePassword,
			String moderatorPassword, int status,
			List<BBBParticipant> bbbParticipants, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// BBB meeting

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long bbbMeetingId = counterLocalService.increment();

		BBBMeeting bbbMeeting = bbbMeetingPersistence.create(bbbMeetingId);

		bbbMeeting.setGroupId(groupId);
		bbbMeeting.setCompanyId(user.getCompanyId());
		bbbMeeting.setUserId(user.getUserId());
		bbbMeeting.setUserName(user.getFullName());
		bbbMeeting.setCreateDate(serviceContext.getCreateDate(now));
		bbbMeeting.setModifiedDate(serviceContext.getModifiedDate(now));
		bbbMeeting.setBbbServerId(bbbServerId);
		bbbMeeting.setName(name);
		bbbMeeting.setDescription(description);
		bbbMeeting.setAttendeePassword(attendeePassword);
		bbbMeeting.setModeratorPassword(moderatorPassword);
		bbbMeeting.setStatus(status);

		bbbMeetingPersistence.update(bbbMeeting);

		// Resources

		resourceLocalService.addModelResources(bbbMeeting, serviceContext);

		// BBB participants

		updateBBBParticipants(
			userId, groupId, bbbMeetingId, bbbParticipants, serviceContext);

		return bbbMeeting;
	}

	@Override
	public BBBMeeting deleteBBBMeeting(BBBMeeting bbbMeeting)
		throws PortalException, SystemException {

		// BBB meeting

		bbbMeetingPersistence.remove(bbbMeeting);

		// Resources

		resourceLocalService.deleteResource(
			bbbMeeting, ResourceConstants.SCOPE_INDIVIDUAL);

		// BBB participants

		List<BBBParticipant> bbbParticipants =
			bbbParticipantLocalService.getBBBParticipants(
				bbbMeeting.getBbbMeetingId());

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			bbbParticipantLocalService.deleteBBBParticipant(bbbParticipant);
		}

		return bbbMeeting;
	}

	@Override
	public BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws PortalException, SystemException {

		BBBMeeting bbbMeeting = bbbMeetingPersistence.findByPrimaryKey(
			bbbMeetingId);

		return deleteBBBMeeting(bbbMeeting);
	}

	@Override
	public BBBMeeting getBBBMeeting(long bbbMeetingId)
		throws PortalException, SystemException {

		return bbbMeetingPersistence.findByPrimaryKey(bbbMeetingId);
	}

	@Override
	public List<BBBMeeting> getBBBMeetings(int status) throws SystemException {
		return bbbMeetingPersistence.findByStatus(status);
	}

	@Override
	public List<BBBMeeting> getBBBMeetings(
			long groupId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return bbbMeetingPersistence.findByGroupId(groupId, start, end, obc);
	}

	@Override
	public List<BBBMeeting> getBBBMeetings(
			long groupId, long userId, String name, String description,
			int status, boolean andSearch, int start, int end,
			String orderByField, String orderByType)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, userId, name, description, status, andSearch);

		if (orderByType.equals("desc")) {
			dynamicQuery.addOrder(OrderFactoryUtil.desc(orderByField));
		}
		else {
			dynamicQuery.addOrder(OrderFactoryUtil.asc(orderByField));
		}

		return dynamicQuery(dynamicQuery, start, end);
	}

	@Override
	public int getBBBMeetingsCount(long groupId) throws SystemException {
		return bbbMeetingPersistence.countByGroupId(groupId);
	}

	@Override
	public int getBBBMeetingsCount(long bbbServerId, int status)
		throws SystemException {

		return bbbMeetingPersistence.countByBSI_S(bbbServerId, status);
	}

	@Override
	public int getBBBMeetingsCount(
			long groupId, long userId, String name, String description,
			int status, boolean andSearch)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, userId, name, description, status, andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	@Override
	public BBBMeeting updateBBBMeeting(
			long bbbMeetingId, long bbbServerId, String name,
			String description, String attendeePassword,
			String moderatorPassword, List<BBBParticipant> bbbParticipants,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// BBB meeting

		BBBMeeting bbbMeeting = bbbMeetingPersistence.findByPrimaryKey(
			bbbMeetingId);

		bbbMeeting.setModifiedDate(serviceContext.getModifiedDate(null));

		if (bbbServerId > 0) {
			bbbMeeting.setBbbServerId(bbbServerId);
		}

		bbbMeeting.setName(name);
		bbbMeeting.setDescription(description);

		if (Validator.isNotNull(attendeePassword)) {
			bbbMeeting.setAttendeePassword(attendeePassword);
		}

		if (Validator.isNotNull(moderatorPassword)) {
			bbbMeeting.setModeratorPassword(moderatorPassword);
		}

		// BBB participants

		updateBBBParticipants(
			bbbMeeting.getUserId(), bbbMeeting.getGroupId(), bbbMeetingId,
			bbbParticipants, serviceContext);

		return bbbMeetingPersistence.update(bbbMeeting);
	}

	@Override
	public BBBMeeting updateStatus(long bbbMeetingId, int status)
		throws PortalException, SystemException {

		BBBMeeting bbbMeeting = bbbMeetingPersistence.findByPrimaryKey(
			bbbMeetingId);

		bbbMeeting.setStatus(status);

		bbbMeetingPersistence.update(bbbMeeting);

		return bbbMeeting;
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, long userId, String name, String description, int status,
		boolean andSearch) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			BBBMeeting.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		if (userId > 0) {
			Property property = PropertyFactoryUtil.forName("userId");

			dynamicQuery.add(property.eq(userId));
		}

		if (status != BBBMeetingConstants.STATUS_ANY) {
			Property property = PropertyFactoryUtil.forName("status");

			dynamicQuery.add(property.eq(status));
		}

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(name)) {
			Property property = PropertyFactoryUtil.forName("name");

			String value = StringUtil.quote(name, StringPool.PERCENT);

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(description)) {
			Property property = PropertyFactoryUtil.forName("description");

			String value = StringUtil.quote(description, StringPool.PERCENT);

			junction.add(property.like(value));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected void updateBBBParticipants(
			long userId, long groupId, long bbbMeetingId,
			List<BBBParticipant> bbbParticipants, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Set<Long> bbbParticipantIds = new HashSet<Long>();

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			long bbbParticipantId = bbbParticipant.getBbbParticipantId();

			if (bbbParticipantId <= 0) {
				bbbParticipant = bbbParticipantLocalService.addBBBParticipant(
					userId, groupId, bbbMeetingId, bbbParticipant.getName(),
					bbbParticipant.getEmailAddress(), bbbParticipant.getType(),
					BBBParticipantConstants.STATUS_DEFAULT,
					new ServiceContext());

				bbbParticipantId = bbbParticipant.getBbbParticipantId();
			}
			else {
				bbbParticipantLocalService.updateBBBParticipant(
					bbbParticipantId, bbbMeetingId, bbbParticipant.getName(),
					bbbParticipant.getEmailAddress(), bbbParticipant.getType(),
					new ServiceContext());
			}

			bbbParticipantIds.add(bbbParticipantId);
		}

		bbbParticipants = bbbParticipantLocalService.getBBBParticipants(
			bbbMeetingId);

		for (BBBParticipant bbbParticipant : bbbParticipants) {
			if (!bbbParticipantIds.contains(
					bbbParticipant.getBbbParticipantId())) {

				bbbParticipantLocalService.deleteBBBParticipant(bbbParticipant);
			}
		}

		try {
			BBBUtil.sendEmail(bbbMeetingId, serviceContext);
		}
		catch (Exception e) {
			throw new SystemException();
		}
	}

}