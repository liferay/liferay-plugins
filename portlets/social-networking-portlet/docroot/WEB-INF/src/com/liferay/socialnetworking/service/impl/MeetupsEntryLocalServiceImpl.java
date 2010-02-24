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

package com.liferay.socialnetworking.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.socialnetworking.MeetupsEntryEndDateException;
import com.liferay.socialnetworking.MeetupsEntryStartDateException;
import com.liferay.socialnetworking.model.MeetupsEntry;
import com.liferay.socialnetworking.service.base.MeetupsEntryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupsEntryLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsEntryLocalServiceImpl
	extends MeetupsEntryLocalServiceBaseImpl {

	public MeetupsEntry addMeetupsEntry(
			long userId, String title, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, user.getTimeZone(),
			new MeetupsEntryStartDateException());

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour,
			endDateMinute, user.getTimeZone(),
			new MeetupsEntryEndDateException());

		Date now = new Date();

		long meetupsEntryId = counterLocalService.increment();

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.create(
			meetupsEntryId);

		meetupsEntry.setCompanyId(user.getCompanyId());
		meetupsEntry.setUserId(user.getUserId());
		meetupsEntry.setUserName(user.getFullName());
		meetupsEntry.setCreateDate(now);
		meetupsEntry.setModifiedDate(now);
		meetupsEntry.setTitle(title);
		meetupsEntry.setDescription(description);
		meetupsEntry.setStartDate(startDate);
		meetupsEntry.setEndDate(endDate);
		meetupsEntry.setTotalAttendees(totalAttendees);
		meetupsEntry.setMaxAttendees(maxAttendees);
		meetupsEntry.setPrice(price);
		meetupsEntry.setThumbnailId(counterLocalService.increment());

		meetupsEntryPersistence.update(meetupsEntry, false);

		if ((thumbnail != null) && (thumbnail.length > 0)) {
			ImageLocalServiceUtil.updateImage(
				meetupsEntry.getThumbnailId(), thumbnail);
		}

		return meetupsEntry;
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws PortalException, SystemException {

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.findByPrimaryKey(
			meetupsEntryId);

		meetupsRegistrationPersistence.removeByMeetupsEntryId(meetupsEntryId);

		ImageLocalServiceUtil.deleteImage(meetupsEntry.getThumbnailId());

		meetupsEntryPersistence.remove(meetupsEntry);
	}

	public List<MeetupsEntry> getMeetupsEntries(long companyId)
		throws SystemException {

		return meetupsEntryPersistence.findByCompanyId(companyId);
	}

	public MeetupsEntry updateMeetupsEntry(
			long userId, long meetupsEntryId, String title, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			int totalAttendees, int maxAttendees, double price,
			byte[] thumbnail)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, user.getTimeZone(),
			new MeetupsEntryStartDateException());

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour,
			endDateMinute, user.getTimeZone(),
			new MeetupsEntryEndDateException());

		MeetupsEntry meetupsEntry = meetupsEntryPersistence.findByPrimaryKey(
			meetupsEntryId);

		meetupsEntry.setModifiedDate(new Date());
		meetupsEntry.setTitle(title);
		meetupsEntry.setDescription(description);
		meetupsEntry.setStartDate(startDate);
		meetupsEntry.setEndDate(endDate);
		meetupsEntry.setTotalAttendees(totalAttendees);
		meetupsEntry.setMaxAttendees(maxAttendees);
		meetupsEntry.setPrice(price);

		meetupsEntryPersistence.update(meetupsEntry, false);

		if ((thumbnail != null) && (thumbnail.length > 0)) {
			ImageLocalServiceUtil.updateImage(
				meetupsEntry.getThumbnailId(), thumbnail);
		}

		return meetupsEntry;
	}

}