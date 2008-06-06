/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wol.service;

/**
 * <a href="MeetupEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupEntryLocalServiceUtil {
	public static com.liferay.wol.model.MeetupEntry addMeetupEntry(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.addMeetupEntry(meetupEntry);
	}

	public static void deleteMeetupEntry(long meetupEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		meetupEntryLocalService.deleteMeetupEntry(meetupEntryId);
	}

	public static void deleteMeetupEntry(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		meetupEntryLocalService.deleteMeetupEntry(meetupEntry);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.dynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> dynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.dynamicQuery(queryInitializer, start, end);
	}

	public static com.liferay.wol.model.MeetupEntry getMeetupEntry(
		long meetupEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.getMeetupEntry(meetupEntryId);
	}

	public static com.liferay.wol.model.MeetupEntry updateMeetupEntry(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.updateMeetupEntry(meetupEntry);
	}

	public static com.liferay.wol.model.MeetupEntry addMeetupEntry(
		long userId, java.lang.String title, java.lang.String description,
		java.util.Date startDate, java.util.Date endDate, long addressId,
		int totalAttendees, int maxAttendees, double price)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		MeetupEntryLocalService meetupEntryLocalService = MeetupEntryLocalServiceFactory.getService();

		return meetupEntryLocalService.addMeetupEntry(userId, title,
			description, startDate, endDate, addressId, totalAttendees,
			maxAttendees, price);
	}
}