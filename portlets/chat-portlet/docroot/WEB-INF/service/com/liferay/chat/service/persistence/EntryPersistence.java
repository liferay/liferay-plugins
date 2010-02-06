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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Entry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="EntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface EntryPersistence extends BasePersistence<Entry> {
	public void cacheResult(com.liferay.chat.model.Entry entry);

	public void cacheResult(
		java.util.List<com.liferay.chat.model.Entry> entries);

	public com.liferay.chat.model.Entry create(long entryId);

	public com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByCreateDate_First(
		long createDate, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByCreateDate_Last(long createDate,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByCreateDate_PrevAndNext(
		long entryId, long createDate,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByFromUserId_First(
		long fromUserId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByFromUserId_Last(long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByFromUserId_PrevAndNext(
		long entryId, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByToUserId_First(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByToUserId_Last(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByToUserId_PrevAndNext(
		long entryId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_F_First(long createDate,
		long fromUserId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_F_Last(long createDate,
		long fromUserId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByC_F_PrevAndNext(long entryId,
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_T_First(long createDate,
		long toUserId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_T_Last(long createDate,
		long toUserId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByC_T_PrevAndNext(long entryId,
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_F_T_First(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByC_F_T_Last(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByC_F_T_PrevAndNext(
		long entryId, long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByF_T_C_First(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByF_T_C_Last(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry[] findByF_T_C_PrevAndNext(
		long entryId, long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCreateDate(long createDate)
		throws com.liferay.portal.SystemException;

	public void removeByFromUserId(long fromUserId)
		throws com.liferay.portal.SystemException;

	public void removeByToUserId(long toUserId)
		throws com.liferay.portal.SystemException;

	public void removeByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.SystemException;

	public void removeByC_T(long createDate, long toUserId)
		throws com.liferay.portal.SystemException;

	public void removeByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.SystemException;

	public void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content) throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCreateDate(long createDate)
		throws com.liferay.portal.SystemException;

	public int countByFromUserId(long fromUserId)
		throws com.liferay.portal.SystemException;

	public int countByToUserId(long toUserId)
		throws com.liferay.portal.SystemException;

	public int countByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.SystemException;

	public int countByC_T(long createDate, long toUserId)
		throws com.liferay.portal.SystemException;

	public int countByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.SystemException;

	public int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content) throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}