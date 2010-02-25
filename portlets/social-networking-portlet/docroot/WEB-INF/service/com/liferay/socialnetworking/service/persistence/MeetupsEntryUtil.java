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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.socialnetworking.model.MeetupsEntry;

import java.util.List;

/**
 * <a href="MeetupsEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryUtil {
	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static MeetupsEntry remove(MeetupsEntry meetupsEntry)
		throws SystemException {
		return getPersistence().remove(meetupsEntry);
	}

	public static MeetupsEntry update(MeetupsEntry meetupsEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(meetupsEntry, merge);
	}

	public static void cacheResult(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry) {
		getPersistence().cacheResult(meetupsEntry);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> meetupsEntries) {
		getPersistence().cacheResult(meetupsEntries);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry create(
		long meetupsEntryId) {
		return getPersistence().create(meetupsEntryId);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry remove(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().remove(meetupsEntryId);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry updateImpl(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(meetupsEntry, merge);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry findByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().findByPrimaryKey(meetupsEntryId);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry fetchByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupsEntryId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry[] findByCompanyId_PrevAndNext(
		long meetupsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(meetupsEntryId, companyId, obc);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.socialnetworking.model.MeetupsEntry[] findByUserId_PrevAndNext(
		long meetupsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(meetupsEntryId, userId, obc);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MeetupsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					MeetupsEntryPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(MeetupsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupsEntryPersistence _persistence;
}