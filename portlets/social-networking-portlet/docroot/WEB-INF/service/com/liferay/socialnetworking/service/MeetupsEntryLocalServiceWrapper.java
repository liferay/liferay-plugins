/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.service;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link MeetupsEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsEntryLocalService
 * @generated
 */
public class MeetupsEntryLocalServiceWrapper implements MeetupsEntryLocalService {
	public MeetupsEntryLocalServiceWrapper(
		MeetupsEntryLocalService meetupsEntryLocalService) {
		_meetupsEntryLocalService = meetupsEntryLocalService;
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(meetupsEntry);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		return _meetupsEntryLocalService.createMeetupsEntry(meetupsEntryId);
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntryId);
	}

	public void deleteMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntry);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntry(meetupsEntryId);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntries(start, end);
	}

	public int getMeetupsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesCount();
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry, merge);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByCompany(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesByCompany(companyId);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntriesByUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesByUser(userId);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		long userId, long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(userId,
			meetupsEntryId, title, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute,
			totalAttendees, maxAttendees, price, thumbnail);
	}

	public MeetupsEntryLocalService getWrappedMeetupsEntryLocalService() {
		return _meetupsEntryLocalService;
	}

	private MeetupsEntryLocalService _meetupsEntryLocalService;
}