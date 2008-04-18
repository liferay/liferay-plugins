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
import com.liferay.wol.model.WallEntry;
import com.liferay.wol.service.base.WallEntryLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * <a href="WallEntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WallEntryLocalServiceImpl extends WallEntryLocalServiceBaseImpl {

	public WallEntry addWallEntry(long groupId, long userId, String comments)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);
		Date now = new Date();

		long wallEntryId = CounterLocalServiceUtil.increment();

		WallEntry wallEntry = wallEntryPersistence.create(wallEntryId);

		wallEntry.setGroupId(groupId);
		wallEntry.setCompanyId(user.getCompanyId());
		wallEntry.setUserId(user.getUserId());
		wallEntry.setUserName(user.getFullName());
		wallEntry.setCreateDate(now);
		wallEntry.setModifiedDate(now);
		wallEntry.setComments(comments);

		wallEntryPersistence.update(wallEntry, false);

		return wallEntry;
	}

	public void deleteWallEntry(long wallEntryId)
		throws PortalException, SystemException {

		wallEntryPersistence.remove(wallEntryId);
	}

	public List<WallEntry> getWallEntries(long groupId, int begin, int end)
		throws SystemException {

		return wallEntryPersistence.findByGroupId(groupId, begin, end);
	}

	public int getWallEntriesCount(long groupId) throws SystemException {
		return wallEntryPersistence.countByGroupId(groupId);
	}

	public WallEntry updateWallEntry(long wallEntryId, String comments)
		throws PortalException, SystemException {

		WallEntry wallEntry = wallEntryPersistence.findByPrimaryKey(
			wallEntryId);

		wallEntry.setModifiedDate(new Date());
		wallEntry.setComments(comments);

		wallEntryPersistence.update(wallEntry, false);

		return wallEntry;
	}

}