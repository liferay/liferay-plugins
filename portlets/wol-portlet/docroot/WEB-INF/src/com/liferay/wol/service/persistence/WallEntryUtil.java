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
 * <a href="WallEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WallEntryUtil {
	public static com.liferay.wol.model.WallEntry create(long wallEntryId) {
		return getPersistence().create(wallEntryId);
	}

	public static com.liferay.wol.model.WallEntry remove(long wallEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().remove(wallEntryId);
	}

	public static com.liferay.wol.model.WallEntry remove(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(wallEntry);
	}

	public static com.liferay.wol.model.WallEntry update(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wallEntry);
	}

	public static com.liferay.wol.model.WallEntry update(
		com.liferay.wol.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wallEntry, merge);
	}

	public static com.liferay.wol.model.WallEntry updateImpl(
		com.liferay.wol.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wallEntry, merge);
	}

	public static com.liferay.wol.model.WallEntry findByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByPrimaryKey(wallEntryId);
	}

	public static com.liferay.wol.model.WallEntry fetchByPrimaryKey(
		long wallEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(wallEntryId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.wol.model.WallEntry findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.wol.model.WallEntry findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.wol.model.WallEntry[] findByGroupId_PrevAndNext(
		long wallEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(wallEntryId, groupId, obc);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.wol.model.WallEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.wol.model.WallEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.wol.model.WallEntry[] findByUserId_PrevAndNext(
		long wallEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(wallEntryId, userId, obc);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end, obc);
	}

	public static com.liferay.wol.model.WallEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByG_U_First(groupId, userId, obc);
	}

	public static com.liferay.wol.model.WallEntry findByG_U_Last(long groupId,
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence().findByG_U_Last(groupId, userId, obc);
	}

	public static com.liferay.wol.model.WallEntry[] findByG_U_PrevAndNext(
		long wallEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(wallEntryId, groupId, userId, obc);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(queryInitializer);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findWithDynamicQuery(queryInitializer, start, end);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wol.model.WallEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_U(groupId, userId);
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

	public static WallEntryPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(WallEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static WallEntryUtil _getUtil() {
		if (_util == null) {
			_util = (WallEntryUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = WallEntryUtil.class.getName();
	private static WallEntryUtil _util;
	private WallEntryPersistence _persistence;
}