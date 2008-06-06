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

package com.liferay.wol.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.wol.model.MeetupEntry;
import com.liferay.wol.service.base.MeetupEntryLocalServiceBaseImpl;

import java.util.Date;

/**
 * <a href="MeetupEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupEntryLocalServiceImpl
	extends MeetupEntryLocalServiceBaseImpl {

	public MeetupEntry addMeetupEntry(
			long userId, String title, String description, Date startDate,
			Date endDate, long addressId, int totalAttendees, int maxAttendees,
			double price)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);
		Date now = new Date();

		long meetupEntryId = CounterLocalServiceUtil.increment();

		MeetupEntry meetupEntry = meetupEntryPersistence.create(meetupEntryId);

		meetupEntry.setCompanyId(user.getCompanyId());
		meetupEntry.setUserId(user.getUserId());
		meetupEntry.setUserName(user.getFullName());
		meetupEntry.setCreateDate(now);
		meetupEntry.setModifiedDate(now);
		meetupEntry.setTitle(title);
		meetupEntry.setDescription(description);
		meetupEntry.setStartDate(startDate);
		meetupEntry.setEndDate(endDate);
		meetupEntry.setAddressId(addressId);
		meetupEntry.setTotalAttendees(totalAttendees);
		meetupEntry.setMaxAttendees(maxAttendees);
		meetupEntry.setPrice(price);

		meetupEntryPersistence.update(meetupEntry, false);

		return meetupEntry;
	}

}