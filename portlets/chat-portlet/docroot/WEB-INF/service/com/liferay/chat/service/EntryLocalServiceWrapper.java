/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.chat.service;


/**
 * <p>
 * This class is a wrapper for {@link EntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EntryLocalService
 * @generated
 */
public class EntryLocalServiceWrapper implements EntryLocalService {
	public EntryLocalServiceWrapper(EntryLocalService entryLocalService) {
		_entryLocalService = entryLocalService;
	}

	public com.liferay.chat.model.Entry addEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.addEntry(entry);
	}

	public com.liferay.chat.model.Entry createEntry(long entryId) {
		return _entryLocalService.createEntry(entryId);
	}

	public void deleteEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_entryLocalService.deleteEntry(entryId);
	}

	public void deleteEntry(com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_entryLocalService.deleteEntry(entry);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.chat.model.Entry getEntry(long entryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getEntry(entryId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getEntries(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getEntries(start, end);
	}

	public int getEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getEntriesCount();
	}

	public com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.updateEntry(entry);
	}

	public com.liferay.chat.model.Entry updateEntry(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.updateEntry(entry, merge);
	}

	public com.liferay.chat.model.Entry addEntry(long fromUserId,
		long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.addEntry(fromUserId, toUserId, content);
	}

	public com.liferay.chat.model.Entry addEntry(long createDate,
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.addEntry(createDate, fromUserId, toUserId,
			content);
	}

	public void deleteEntries(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_entryLocalService.deleteEntries(userId);
	}

	public java.util.List<com.liferay.chat.model.Entry> getNewEntries(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getNewEntries(userId, createDate, start, end);
	}

	public java.util.List<com.liferay.chat.model.Entry> getOldEntries(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entryLocalService.getOldEntries(createDate, start, end);
	}

	public EntryLocalService getWrappedEntryLocalService() {
		return _entryLocalService;
	}

	private EntryLocalService _entryLocalService;
}