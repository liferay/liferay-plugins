/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.gadgets.service.base;

import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.service.GadgetsEntryLocalService;
import com.liferay.gadgets.service.persistence.GadgetsEntryPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="GadgetsEntryLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class GadgetsEntryLocalServiceBaseImpl
	implements GadgetsEntryLocalService {
	public GadgetsEntry addGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws SystemException {
		gadgetsEntry.setNew(true);

		return gadgetsEntryPersistence.update(gadgetsEntry, false);
	}

	public GadgetsEntry createGadgetsEntry(long gadgetsEntryId) {
		return gadgetsEntryPersistence.create(gadgetsEntryId);
	}

	public void deleteGadgetsEntry(long gadgetsEntryId)
		throws PortalException, SystemException {
		gadgetsEntryPersistence.remove(gadgetsEntryId);
	}

	public void deleteGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws PortalException, SystemException {
		gadgetsEntryPersistence.remove(gadgetsEntry);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return gadgetsEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return gadgetsEntryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public GadgetsEntry getGadgetsEntry(long gadgetsEntryId)
		throws PortalException, SystemException {
		return gadgetsEntryPersistence.findByPrimaryKey(gadgetsEntryId);
	}

	public List<GadgetsEntry> getGadgetsEntries(int start, int end)
		throws SystemException {
		return gadgetsEntryPersistence.findAll(start, end);
	}

	public int getGadgetsEntriesCount() throws SystemException {
		return gadgetsEntryPersistence.countAll();
	}

	public GadgetsEntry updateGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws SystemException {
		gadgetsEntry.setNew(false);

		return gadgetsEntryPersistence.update(gadgetsEntry, true);
	}

	public GadgetsEntry updateGadgetsEntry(GadgetsEntry gadgetsEntry,
		boolean merge) throws SystemException {
		gadgetsEntry.setNew(false);

		return gadgetsEntryPersistence.update(gadgetsEntry, merge);
	}

	public GadgetsEntryLocalService getGadgetsEntryLocalService() {
		return gadgetsEntryLocalService;
	}

	public void setGadgetsEntryLocalService(
		GadgetsEntryLocalService gadgetsEntryLocalService) {
		this.gadgetsEntryLocalService = gadgetsEntryLocalService;
	}

	public GadgetsEntryPersistence getGadgetsEntryPersistence() {
		return gadgetsEntryPersistence;
	}

	public void setGadgetsEntryPersistence(
		GadgetsEntryPersistence gadgetsEntryPersistence) {
		this.gadgetsEntryPersistence = gadgetsEntryPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.gadgets.service.GadgetsEntryLocalService")
	protected GadgetsEntryLocalService gadgetsEntryLocalService;
	@BeanReference(name = "com.liferay.gadgets.service.persistence.GadgetsEntryPersistence")
	protected GadgetsEntryPersistence gadgetsEntryPersistence;
}