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
 * <a href="EntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface EntryPersistence {
	public com.liferay.chat.model.Entry create(long entryId);

	public com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry remove(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry update(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry update(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry[] findByUserId_PrevAndNext(
		long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public java.util.List<com.liferay.chat.model.Entry> findByReceiverUserId(
		long receiverUserId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByReceiverUserId(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findByReceiverUserId(
		long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry findByReceiverUserId_First(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry findByReceiverUserId_Last(
		long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public com.liferay.chat.model.Entry[] findByReceiverUserId_PrevAndNext(
		long entryId, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.chat.NoSuchEntryException;

	public java.util.List<com.liferay.chat.model.Entry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByReceiverUserId(long receiverUserId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}