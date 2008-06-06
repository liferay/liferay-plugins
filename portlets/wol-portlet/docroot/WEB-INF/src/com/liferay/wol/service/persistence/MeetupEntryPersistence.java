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
 * <a href="MeetupEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface MeetupEntryPersistence {
	public com.liferay.wol.model.MeetupEntry create(long meetupEntryId);

	public com.liferay.wol.model.MeetupEntry remove(long meetupEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException;

	public com.liferay.wol.model.MeetupEntry remove(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupEntry update(
		com.liferay.wol.model.MeetupEntry meetupEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupEntry update(
		com.liferay.wol.model.MeetupEntry meetupEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupEntry updateImpl(
		com.liferay.wol.model.MeetupEntry meetupEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupEntry findByPrimaryKey(
		long meetupEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException;

	public com.liferay.wol.model.MeetupEntry fetchByPrimaryKey(
		long meetupEntryId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wol.model.MeetupEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException;

	public com.liferay.wol.model.MeetupEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException;

	public com.liferay.wol.model.MeetupEntry[] findByCompanyId_PrevAndNext(
		long meetupEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wol.NoSuchMeetupEntryException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.DynamicQueryInitializer queryInitializer,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wol.model.MeetupEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}