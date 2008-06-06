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

package com.liferay.wol.service.persistence;

/**
 * <a href="MeetupEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupEntryUtil {
	public static com.liferay.wol.model.MeetupEntry create(long meetupEntryId) {
		return getPersistence().create(meetupEntryId);
	}

	public static com.liferay.wol.model.MeetupEntry remove(long meetupEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException {
		return getPersistence().remove(meetupEntryId);
	}

	public static com.liferay.wol.model.MeetupEntry remove(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(meetupEntry);
	}

	public static com.liferay.wol.model.MeetupEntry update(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupEntry);
	}

	public static com.liferay.wol.model.MeetupEntry update(
		com.liferay.wol.model.MeetupEntry meetupEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupEntry, merge);
	}

	public static com.liferay.wol.model.MeetupEntry updateImpl(
		com.liferay.wol.model.MeetupEntry meetupEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(meetupEntry, merge);
	}

	public static com.liferay.wol.model.MeetupEntry findByPrimaryKey(
		long meetupEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException {
		return getPersistence().findByPrimaryKey(meetupEntryId);
	}

	public static com.liferay.wol.model.MeetupEntry fetchByPrimaryKey(
		long meetupEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupEntryId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.wol.model.MeetupEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.wol.model.MeetupEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.wol.model.MeetupEntry[] findByCompanyId_PrevAndNext(
		long meetupEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(meetupEntryId, companyId, obc);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupEntry> findAll(
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

	public static MeetupEntryPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(MeetupEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupEntryUtil _getUtil() {
		if (_util == null) {
			_util = (MeetupEntryUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = MeetupEntryUtil.class.getName();
	private static MeetupEntryUtil _util;
	private MeetupEntryPersistence _persistence;
}