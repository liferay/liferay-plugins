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
import com.liferay.wol.model.MeetupsEntry;
import com.liferay.wol.service.base.MeetupsEntryLocalServiceBaseImpl;

import java.util.Date;

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
			long userId, String title, String description, Date startDate,
			Date endDate, long addressId, int totalAttendees, int maxAttendees,
			double price)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);
		Date now = new Date();

		long meetupsEntryId = CounterLocalServiceUtil.increment();

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
		meetupsEntry.setAddressId(addressId);
		meetupsEntry.setTotalAttendees(totalAttendees);
		meetupsEntry.setMaxAttendees(maxAttendees);
		meetupsEntry.setPrice(price);

		meetupsEntryPersistence.update(meetupsEntry, false);

		return meetupsEntry;
	}

}