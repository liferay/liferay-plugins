/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.socialnetworking.service;

/**
 * <a href="MeetupsEntryLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryLocalServiceWrapper implements MeetupsEntryLocalService {
	public MeetupsEntryLocalServiceWrapper(
		MeetupsEntryLocalService meetupsEntryLocalService) {
		_meetupsEntryLocalService = meetupsEntryLocalService;
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(meetupsEntry);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		return _meetupsEntryLocalService.createMeetupsEntry(meetupsEntryId);
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntryId);
	}

	public void deleteMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		_meetupsEntryLocalService.deleteMeetupsEntry(meetupsEntry);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntry(meetupsEntryId);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntries(start, end);
	}

	public int getMeetupsEntriesCount()
		throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntriesCount();
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge) throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.updateMeetupsEntry(meetupsEntry, merge);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry addMeetupsEntry(
		long userId, java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.addMeetupsEntry(userId, title,
			description, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, totalAttendees,
			maxAttendees, price, thumbnail);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> getMeetupsEntries(
		long companyId) throws com.liferay.portal.SystemException {
		return _meetupsEntryLocalService.getMeetupsEntries(companyId);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry updateMeetupsEntry(
		long userId, long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
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