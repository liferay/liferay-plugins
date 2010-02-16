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

package com.liferay.chat.service.impl;

import com.liferay.chat.model.Entry;
import com.liferay.chat.service.base.EntryLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * <a href="EntryLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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

		long entryId = CounterLocalServiceUtil.increment();

		Entry entry = entryPersistence.create(entryId);

		entry.setCreateDate(createDate);
		entry.setFromUserId(fromUserId);
		entry.setToUserId(toUserId);
		entry.setContent(content);

		entryPersistence.update(entry, false);

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