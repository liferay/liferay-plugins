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
import com.liferay.chat.service.StatusLocalService;
import com.liferay.chat.service.StatusLocalServiceFactory;
import com.liferay.chat.service.persistence.EntryFinder;
import com.liferay.chat.service.persistence.EntryFinderUtil;
import com.liferay.chat.service.persistence.EntryPersistence;
import com.liferay.chat.service.persistence.EntryUtil;
import com.liferay.chat.service.persistence.StatusFinder;
import com.liferay.chat.service.persistence.StatusFinderUtil;
import com.liferay.chat.service.persistence.StatusPersistence;
import com.liferay.chat.service.persistence.StatusUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="EntryLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class EntryLocalServiceBaseImpl implements EntryLocalService {
	public Entry addEntry(Entry entry) throws SystemException {
		entry.setNew(true);

		return entryPersistence.update(entry, false);
	}

	public void deleteEntry(long entryId)
		throws PortalException, SystemException {
		entryPersistence.remove(entryId);
	}

	public void deleteEntry(Entry entry) throws SystemException {
		entryPersistence.remove(entry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return entryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return entryPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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

	public StatusLocalService getStatusLocalService() {
		return statusLocalService;
	}

	public void setStatusLocalService(StatusLocalService statusLocalService) {
		this.statusLocalService = statusLocalService;
	}

	public StatusPersistence getStatusPersistence() {
		return statusPersistence;
	}

	public void setStatusPersistence(StatusPersistence statusPersistence) {
		this.statusPersistence = statusPersistence;
	}

	public StatusFinder getStatusFinder() {
		return statusFinder;
	}

	public void setStatusFinder(StatusFinder statusFinder) {
		this.statusFinder = statusFinder;
	}

	protected void init() {
		if (entryPersistence == null) {
			entryPersistence = EntryUtil.getPersistence();
		}

		if (entryFinder == null) {
			entryFinder = EntryFinderUtil.getFinder();
		}

		if (statusLocalService == null) {
			statusLocalService = StatusLocalServiceFactory.getImpl();
		}

		if (statusPersistence == null) {
			statusPersistence = StatusUtil.getPersistence();
		}

		if (statusFinder == null) {
			statusFinder = StatusFinderUtil.getFinder();
		}
	}

	protected EntryPersistence entryPersistence;
	protected EntryFinder entryFinder;
	protected StatusLocalService statusLocalService;
	protected StatusPersistence statusPersistence;
	protected StatusFinder statusFinder;
}