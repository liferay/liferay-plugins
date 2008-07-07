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

	public static java.util.List<com.liferay.chat.model.Status> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.search.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.chat.model.Status> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.search.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, start, end);
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

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
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
		return _getUtil()._persistence;
	}

	public void setPersistence(StatusPersistence persistence) {
		_persistence = persistence;
	}

	private static StatusUtil _getUtil() {
		if (_util == null) {
			_util = (StatusUtil)com.liferay.util.bean.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = StatusUtil.class.getName();
	private static StatusUtil _util;
	private StatusPersistence _persistence;
}