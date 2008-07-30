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

package com.liferay.chat.service;

/**
 * <a href="EntryLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface EntryLocalService {
	public com.liferay.chat.model.Entry addEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException;

	public void deleteEntry(long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteEntry(com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry getEntry(long entryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public java.util.List<com.liferay.chat.model.Entry> getEntries(int start,
		int end) throws com.liferay.portal.SystemException;

	public int getEntriesCount() throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException;

	public com.liferay.chat.model.Entry addEntry(long fromUserId,
		long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException;

	public void deleteEntries(long userId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> getNewEntries(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.chat.model.Entry> getOldEntries(
		long createDate, int start, int end)
		throws com.liferay.portal.SystemException;
}