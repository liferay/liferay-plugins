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

package com.liferay.ruon.service.persistence;

/**
 * <a href="PresenceStatusUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PresenceStatusUtil {
	public static com.liferay.ruon.model.PresenceStatus create(
		long presenceStatusId) {
		return getPersistence().create(presenceStatusId);
	}

	public static com.liferay.ruon.model.PresenceStatus remove(
		long presenceStatusId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceStatusException {
		return getPersistence().remove(presenceStatusId);
	}

	public static com.liferay.ruon.model.PresenceStatus remove(
		com.liferay.ruon.model.PresenceStatus presenceStatus)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(presenceStatus);
	}

	public static com.liferay.ruon.model.PresenceStatus update(
		com.liferay.ruon.model.PresenceStatus presenceStatus)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(presenceStatus);
	}

	public static com.liferay.ruon.model.PresenceStatus update(
		com.liferay.ruon.model.PresenceStatus presenceStatus, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(presenceStatus, merge);
	}

	public static com.liferay.ruon.model.PresenceStatus updateImpl(
		com.liferay.ruon.model.PresenceStatus presenceStatus, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(presenceStatus, merge);
	}

	public static com.liferay.ruon.model.PresenceStatus findByPrimaryKey(
		long presenceStatusId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchPresenceStatusException {
		return getPersistence().findByPrimaryKey(presenceStatusId);
	}

	public static com.liferay.ruon.model.PresenceStatus fetchByPrimaryKey(
		long presenceStatusId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(presenceStatusId);
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

	public static java.util.List<com.liferay.ruon.model.PresenceStatus> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ruon.model.PresenceStatus> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ruon.model.PresenceStatus> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
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

	public static PresenceStatusPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(PresenceStatusPersistence persistence) {
		_persistence = persistence;
	}

	private static PresenceStatusUtil _getUtil() {
		if (_util == null) {
			_util = (PresenceStatusUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = PresenceStatusUtil.class.getName();
	private static PresenceStatusUtil _util;
	private PresenceStatusPersistence _persistence;
}