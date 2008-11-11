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

package com.liferay.chat.service.persistence;

/**
 * <a href="StatusUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class StatusUtil {
	public static com.liferay.chat.model.Status create(long statusId) {
		return getPersistence().create(statusId);
	}

	public static com.liferay.chat.model.Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(statusId);
	}

	public static com.liferay.chat.model.Status remove(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(status);
	}

	public static com.liferay.chat.model.Status update(
		com.liferay.chat.model.Status status)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(status);
	}

	public static com.liferay.chat.model.Status update(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(status, merge);
	}

	public static com.liferay.chat.model.Status updateImpl(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(status, merge);
	}

	public static com.liferay.chat.model.Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(statusId);
	}

	public static com.liferay.chat.model.Status fetchByPrimaryKey(long statusId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(statusId);
	}

	public static com.liferay.chat.model.Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static com.liferay.chat.model.Status fetchByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUserId(userId);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate) throws com.liferay.portal.SystemException {
		return getPersistence().findByModifiedDate(modifiedDate);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByModifiedDate(modifiedDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByModifiedDate(modifiedDate, start, end, obc);
	}

	public static com.liferay.chat.model.Status findByModifiedDate_First(
		long modifiedDate, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByModifiedDate_First(modifiedDate, obc);
	}

	public static com.liferay.chat.model.Status findByModifiedDate_Last(
		long modifiedDate, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByModifiedDate_Last(modifiedDate, obc);
	}

	public static com.liferay.chat.model.Status[] findByModifiedDate_PrevAndNext(
		long statusId, long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByModifiedDate_PrevAndNext(statusId, modifiedDate, obc);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online) throws com.liferay.portal.SystemException {
		return getPersistence().findByOnline(online);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByOnline(online, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByOnline(online, start, end, obc);
	}

	public static com.liferay.chat.model.Status findByOnline_First(
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByOnline_First(online, obc);
	}

	public static com.liferay.chat.model.Status findByOnline_Last(
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByOnline_Last(online, obc);
	}

	public static com.liferay.chat.model.Status[] findByOnline_PrevAndNext(
		long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByOnline_PrevAndNext(statusId, online, obc);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByM_O(modifiedDate, online);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByM_O(modifiedDate, online, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByM_O(modifiedDate, online, start, end, obc);
	}

	public static com.liferay.chat.model.Status findByM_O_First(
		long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByM_O_First(modifiedDate, online, obc);
	}

	public static com.liferay.chat.model.Status findByM_O_Last(
		long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence().findByM_O_Last(modifiedDate, online, obc);
	}

	public static com.liferay.chat.model.Status[] findByM_O_PrevAndNext(
		long statusId, long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByM_O_PrevAndNext(statusId, modifiedDate, online, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.chat.model.Status> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByModifiedDate(long modifiedDate)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByModifiedDate(modifiedDate);
	}

	public static void removeByOnline(boolean online)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByOnline(online);
	}

	public static void removeByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByM_O(modifiedDate, online);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByModifiedDate(long modifiedDate)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByModifiedDate(modifiedDate);
	}

	public static int countByOnline(boolean online)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByOnline(online);
	}

	public static int countByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByM_O(modifiedDate, online);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static StatusPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(StatusPersistence persistence) {
		_persistence = persistence;
	}

	private static StatusPersistence _persistence;
}