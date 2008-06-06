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
 * <a href="MeetupRegistrationUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupRegistrationUtil {
	public static com.liferay.wol.model.MeetupRegistration create(
		long meetupRegistrationId) {
		return getPersistence().create(meetupRegistrationId);
	}

	public static com.liferay.wol.model.MeetupRegistration remove(
		long meetupRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupRegistrationException {
		return getPersistence().remove(meetupRegistrationId);
	}

	public static com.liferay.wol.model.MeetupRegistration remove(
		com.liferay.wol.model.MeetupRegistration meetupRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(meetupRegistration);
	}

	public static com.liferay.wol.model.MeetupRegistration update(
		com.liferay.wol.model.MeetupRegistration meetupRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupRegistration);
	}

	public static com.liferay.wol.model.MeetupRegistration update(
		com.liferay.wol.model.MeetupRegistration meetupRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupRegistration, merge);
	}

	public static com.liferay.wol.model.MeetupRegistration updateImpl(
		com.liferay.wol.model.MeetupRegistration meetupRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(meetupRegistration, merge);
	}

	public static com.liferay.wol.model.MeetupRegistration findByPrimaryKey(
		long meetupRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupRegistrationException {
		return getPersistence().findByPrimaryKey(meetupRegistrationId);
	}

	public static com.liferay.wol.model.MeetupRegistration fetchByPrimaryKey(
		long meetupRegistrationId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupRegistrationId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findByMeetupEntryId(
		long meetupEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().findByMeetupEntryId(meetupEntryId);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findByMeetupEntryId(
		long meetupEntryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByMeetupEntryId(meetupEntryId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findByMeetupEntryId(
		long meetupEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMeetupEntryId(meetupEntryId, start, end, obc);
	}

	public static com.liferay.wol.model.MeetupRegistration findByMeetupEntryId_First(
		long meetupEntryId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupRegistrationException {
		return getPersistence().findByMeetupEntryId_First(meetupEntryId, obc);
	}

	public static com.liferay.wol.model.MeetupRegistration findByMeetupEntryId_Last(
		long meetupEntryId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupRegistrationException {
		return getPersistence().findByMeetupEntryId_Last(meetupEntryId, obc);
	}

	public static com.liferay.wol.model.MeetupRegistration[] findByMeetupEntryId_PrevAndNext(
		long meetupRegistrationId, long meetupEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupRegistrationException {
		return getPersistence()
				   .findByMeetupEntryId_PrevAndNext(meetupRegistrationId,
			meetupEntryId, obc);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wol.model.MeetupRegistration> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByMeetupEntryId(long meetupEntryId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByMeetupEntryId(meetupEntryId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByMeetupEntryId(long meetupEntryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByMeetupEntryId(meetupEntryId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupRegistrationPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(MeetupRegistrationPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupRegistrationUtil _getUtil() {
		if (_util == null) {
			_util = (MeetupRegistrationUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = MeetupRegistrationUtil.class.getName();
	private static MeetupRegistrationUtil _util;
	private MeetupRegistrationPersistence _persistence;
}