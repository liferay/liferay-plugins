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

package com.liferay.chat.service;

/**
 * <a href="EntryLocalServiceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class EntryLocalServiceWrapper implements EntryLocalService {
	public EntryLocalServiceWrapper(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	public com.liferay.chat.model.Entry addEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.addEntry(entry);
	}

	public com.liferay.chat.model.Entry createEntry(long entryId) {
		return _entryLocalService.createEntry(entryId);
	}

	public void deleteEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_entryLocalService.deleteEntry(entryId);
	}

	public void deleteEntry(com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		_entryLocalService.deleteEntry(entry);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.chat.model.Entry getEntry(long entryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _entryLocalService.getEntry(entryId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getEntries(int start,
		int end) throws com.liferay.portal.SystemException {
		return _entryLocalService.getEntries(start, end);
	}

	public int getEntriesCount() throws com.liferay.portal.SystemException {
		return _entryLocalService.getEntriesCount();
	}

	public com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.updateEntry(entry);
	}

	public com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.updateEntry(entry, merge);
	}

	public com.liferay.chat.model.Entry addEntry(long fromUserId,
		long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.addEntry(fromUserId, toUserId, content);
	}

	public com.liferay.chat.model.Entry addEntry(long createDate,
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.addEntry(createDate, fromUserId, toUserId,
			content);
	}

	public void deleteEntries(long userId)
		throws com.liferay.portal.SystemException {
		_entryLocalService.deleteEntries(userId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getNewEntries(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.getNewEntries(userId, createDate, start, end);
	}

	public java.util.List<com.liferay.chat.model.Entry> getOldEntries(
		long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return _entryLocalService.getOldEntries(createDate, start, end);
	}

	public EntryLocalService getWrappedEntryLocalService() {
		return _entryLocalService;
	}

	private EntryLocalService _entryLocalService;
}