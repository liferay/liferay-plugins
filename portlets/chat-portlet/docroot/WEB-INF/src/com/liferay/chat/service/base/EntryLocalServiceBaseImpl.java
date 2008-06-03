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

package com.liferay.chat.service.base;

import com.liferay.chat.model.Entry;
import com.liferay.chat.service.EntryLocalService;
import com.liferay.chat.service.persistence.EntryFinder;
import com.liferay.chat.service.persistence.EntryFinderUtil;
import com.liferay.chat.service.persistence.EntryPersistence;
import com.liferay.chat.service.persistence.EntryUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="EntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class EntryLocalServiceBaseImpl implements EntryLocalService,
	InitializingBean {
	public Entry addEntry(Entry entry) throws SystemException {
		entry.setNew(true);

		return entryPersistence.update(entry, false);
	}

	public void deleteEntry(long entryId)
		throws PortalException, SystemException {
		entryPersistence.remove(entryId);
	}

	public void deleteEntry(Entry entry)
		throws PortalException, SystemException {
		entryPersistence.remove(entry);
	}

	public List<Entry> dynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
		return entryPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<Entry> dynamicQuery(DynamicQueryInitializer queryInitializer,
		int start, int end) throws SystemException {
		return entryPersistence.findWithDynamicQuery(queryInitializer, start,
			end);
	}

	public Entry getEntry(long entryId) throws PortalException, SystemException {
		return entryPersistence.findByPrimaryKey(entryId);
	}

	public Entry updateEntry(Entry entry) throws SystemException {
		entry.setNew(false);

		return entryPersistence.update(entry, true);
	}

	public EntryPersistence getEntryPersistence() {
		return entryPersistence;
	}

	public void setEntryPersistence(EntryPersistence entryPersistence) {
		this.entryPersistence = entryPersistence;
	}

	public EntryFinder getEntryFinder() {
		return entryFinder;
	}

	public void setEntryFinder(EntryFinder entryFinder) {
		this.entryFinder = entryFinder;
	}

	public void afterPropertiesSet() {
		if (entryPersistence == null) {
			entryPersistence = EntryUtil.getPersistence();
		}

		if (entryFinder == null) {
			entryFinder = EntryFinderUtil.getFinder();
		}
	}

	protected EntryPersistence entryPersistence;
	protected EntryFinder entryFinder;
}