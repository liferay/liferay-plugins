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
 * <a href="WallEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WallEntryPersistence {
	public com.liferay.wol.model.WallEntry create(long wallEntryId);

	public com.liferay.wol.model.WallEntry remove(long wallEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry remove(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry update(
		com.liferay.wol.model.WallEntry wallEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry update(
		com.liferay.wol.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry updateImpl(
		com.liferay.wol.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry findByPrimaryKey(long wallEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry fetchByPrimaryKey(long wallEntryId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry[] findByGroupId_PrevAndNext(
		long wallEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry[] findByUserId_PrevAndNext(
		long wallEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.WallEntry findByG_U_First(long groupId,
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry findByG_U_Last(long groupId,
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public com.liferay.wol.model.WallEntry[] findByG_U_PrevAndNext(
		long wallEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchWallEntryException;

	public java.util.List<com.liferay.wol.model.WallEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.WallEntry> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByG_U(long groupId, long userId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}