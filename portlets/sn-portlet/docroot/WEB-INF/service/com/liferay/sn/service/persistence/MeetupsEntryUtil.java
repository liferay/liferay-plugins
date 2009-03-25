/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.sn.service.persistence;

/**
 * <a href="MeetupsEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsEntryUtil {
	public static com.liferay.sn.model.MeetupsEntry create(long meetupsEntryId) {
		return getPersistence().create(meetupsEntryId);
	}

	public static com.liferay.sn.model.MeetupsEntry remove(long meetupsEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsEntryException {
		return getPersistence().remove(meetupsEntryId);
	}

	public static com.liferay.sn.model.MeetupsEntry remove(
		com.liferay.sn.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(meetupsEntry);
	}

	public static com.liferay.sn.model.MeetupsEntry update(
		com.liferay.sn.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupsEntry);
	}

	public static com.liferay.sn.model.MeetupsEntry update(
		com.liferay.sn.model.MeetupsEntry meetupsEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupsEntry, merge);
	}

	public static com.liferay.sn.model.MeetupsEntry updateImpl(
		com.liferay.sn.model.MeetupsEntry meetupsEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(meetupsEntry, merge);
	}

	public static com.liferay.sn.model.MeetupsEntry findByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsEntryException {
		return getPersistence().findByPrimaryKey(meetupsEntryId);
	}

	public static com.liferay.sn.model.MeetupsEntry fetchByPrimaryKey(
		long meetupsEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupsEntryId);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.sn.model.MeetupsEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsEntryException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.sn.model.MeetupsEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsEntryException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.sn.model.MeetupsEntry[] findByCompanyId_PrevAndNext(
		long meetupsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(meetupsEntryId, companyId, obc);
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

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupsEntryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(MeetupsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupsEntryPersistence _persistence;
}