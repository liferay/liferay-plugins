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
 * <a href="MeetupsRegistrationUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsRegistrationUtil {
	public static com.liferay.sn.model.MeetupsRegistration create(
		long meetupsRegistrationId) {
		return getPersistence().create(meetupsRegistrationId);
	}

	public static com.liferay.sn.model.MeetupsRegistration remove(
		long meetupsRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().remove(meetupsRegistrationId);
	}

	public static com.liferay.sn.model.MeetupsRegistration remove(
		com.liferay.sn.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(meetupsRegistration);
	}

	public static com.liferay.sn.model.MeetupsRegistration update(
		com.liferay.sn.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupsRegistration);
	}

	public static com.liferay.sn.model.MeetupsRegistration update(
		com.liferay.sn.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(meetupsRegistration, merge);
	}

	public static com.liferay.sn.model.MeetupsRegistration updateImpl(
		com.liferay.sn.model.MeetupsRegistration meetupsRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(meetupsRegistration, merge);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByPrimaryKey(
		long meetupsRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByPrimaryKey(meetupsRegistrationId);
	}

	public static com.liferay.sn.model.MeetupsRegistration fetchByPrimaryKey(
		long meetupsRegistrationId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(meetupsRegistrationId);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByMeetupsEntryId(meetupsEntryId, start, end);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByMeetupsEntryId(
		long meetupsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByMeetupsEntryId(meetupsEntryId, start, end, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByMeetupsEntryId_First(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByMeetupsEntryId_First(meetupsEntryId, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByMeetupsEntryId_Last(
		long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByMeetupsEntryId_Last(meetupsEntryId, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration[] findByMeetupsEntryId_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByMeetupsEntryId_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByU_ME(userId, meetupsEntryId);
	}

	public static com.liferay.sn.model.MeetupsRegistration fetchByU_ME(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByU_ME(userId, meetupsEntryId);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByME_S(meetupsEntryId, status, start, end);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findByME_S(
		long meetupsEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByME_S(meetupsEntryId, status, start, end, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByME_S_First(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByME_S_First(meetupsEntryId, status, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration findByME_S_Last(
		long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence().findByME_S_Last(meetupsEntryId, status, obc);
	}

	public static com.liferay.sn.model.MeetupsRegistration[] findByME_S_PrevAndNext(
		long meetupsRegistrationId, long meetupsEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		return getPersistence()
				   .findByME_S_PrevAndNext(meetupsRegistrationId,
			meetupsEntryId, status, obc);
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

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.sn.model.MeetupsRegistration> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByMeetupsEntryId(meetupsEntryId);
	}

	public static void removeByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.sn.NoSuchMeetupsRegistrationException {
		getPersistence().removeByU_ME(userId, meetupsEntryId);
	}

	public static void removeByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByME_S(meetupsEntryId, status);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByMeetupsEntryId(long meetupsEntryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByMeetupsEntryId(meetupsEntryId);
	}

	public static int countByU_ME(long userId, long meetupsEntryId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByU_ME(userId, meetupsEntryId);
	}

	public static int countByME_S(long meetupsEntryId, int status)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByME_S(meetupsEntryId, status);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static MeetupsRegistrationPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(MeetupsRegistrationPersistence persistence) {
		_persistence = persistence;
	}

	private static MeetupsRegistrationPersistence _persistence;
}