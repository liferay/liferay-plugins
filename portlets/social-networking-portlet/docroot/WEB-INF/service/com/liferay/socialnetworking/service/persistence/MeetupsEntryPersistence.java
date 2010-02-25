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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialnetworking.model.MeetupsEntry;

/**
 * <a href="MeetupsEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface MeetupsEntryPersistence extends BasePersistence<MeetupsEntry> {
	public void cacheResult(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry);

	public void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> meetupsEntries);

	public com.liferay.socialnetworking.model.MeetupsEntry create(
		long meetupsEntryId);

	public com.liferay.socialnetworking.model.MeetupsEntry remove(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry updateImpl(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsEntry findByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry fetchByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry[] findByCompanyId_PrevAndNext(
		long meetupsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.socialnetworking.model.MeetupsEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry[] findByUserId_PrevAndNext(
		long meetupsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}