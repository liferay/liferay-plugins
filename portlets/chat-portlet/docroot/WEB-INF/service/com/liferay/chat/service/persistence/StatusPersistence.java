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

import com.liferay.chat.model.Status;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="StatusPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface StatusPersistence extends BasePersistence<Status> {
	public void cacheResult(com.liferay.chat.model.Status status);

	public void cacheResult(
		java.util.List<com.liferay.chat.model.Status> statuses);

	public com.liferay.chat.model.Status create(long statusId);

	public com.liferay.chat.model.Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status updateImpl(
		com.liferay.chat.model.Status status, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status fetchByPrimaryKey(long statusId)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status fetchByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status fetchByUserId(long userId,
		boolean retrieveFromCache) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByModifiedDate(
		long modifiedDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByModifiedDate_First(
		long modifiedDate, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByModifiedDate_Last(
		long modifiedDate, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status[] findByModifiedDate_PrevAndNext(
		long statusId, long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByOnline(
		boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByOnline_First(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByOnline_Last(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status[] findByOnline_PrevAndNext(
		long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findByM_O(
		long modifiedDate, boolean online, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByM_O_First(long modifiedDate,
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status findByM_O_Last(long modifiedDate,
		boolean online, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public com.liferay.chat.model.Status[] findByM_O_PrevAndNext(
		long statusId, long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Status> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException,
			com.liferay.portal.SystemException;

	public void removeByModifiedDate(long modifiedDate)
		throws com.liferay.portal.SystemException;

	public void removeByOnline(boolean online)
		throws com.liferay.portal.SystemException;

	public void removeByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByModifiedDate(long modifiedDate)
		throws com.liferay.portal.SystemException;

	public int countByOnline(boolean online)
		throws com.liferay.portal.SystemException;

	public int countByM_O(long modifiedDate, boolean online)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}