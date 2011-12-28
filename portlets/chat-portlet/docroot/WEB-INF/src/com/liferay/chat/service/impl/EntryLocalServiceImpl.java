/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.chat.service.impl;

import com.liferay.chat.jabber.JabberUtil;
import com.liferay.chat.model.Entry;
import com.liferay.chat.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

	public Entry addEntry(long fromUserId, long toUserId, String content)
		throws SystemException {

		long createDate = System.currentTimeMillis();

		return addEntry(createDate, fromUserId, toUserId, content);
	}

	public Entry addEntry(
			long createDate, long fromUserId, long toUserId, String content)
		throws SystemException {

		if (Validator.isNull(content)) {
			content = StringPool.BLANK;

			List<Entry> entries = entryFinder.findByEmptyContent(
				fromUserId, toUserId, 0, 5);

			for (Entry entry : entries) {
				entryPersistence.remove(entry);
			}
		}
		else {
			if (content.length() > 500) {
				content = content.substring(0, 500);
			}
		}

		long entryId = counterLocalService.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setCreateDate(createDate);
		entry.setFromUserId(fromUserId);
		entry.setToUserId(toUserId);
		entry.setContent(content);

		entryPersistence.update(entry, false);

		JabberUtil.sendMessage(fromUserId, toUserId, content);

		return entry;
	}

	public void deleteEntries(long userId) throws SystemException {
		entryPersistence.removeByFromUserId(userId);
		entryPersistence.removeByToUserId(userId);
	}

	public List<Entry> getNewEntries(
			long userId, long createDate, int start, int end)
		throws SystemException {

		return entryFinder.findByNew(userId, createDate, start, end);
	}

	public List<Entry> getOldEntries(long createDate, int start, int end)
		throws SystemException {

		return entryFinder.findByOld(createDate, start, end);
	}

}