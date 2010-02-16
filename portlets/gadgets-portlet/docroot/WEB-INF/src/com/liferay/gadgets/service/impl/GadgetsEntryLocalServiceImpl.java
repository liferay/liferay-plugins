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

package com.liferay.gadgets.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.gadgets.GadgetsEntryNameException;
import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.service.base.GadgetsEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * <a href="GadgetsEntryLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryLocalServiceImpl
	extends GadgetsEntryLocalServiceBaseImpl {

	public GadgetsEntry addGadgetsEntry(
			long companyId, String name, String url, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		long gadgetsEntryId = CounterLocalServiceUtil.increment();

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.create(
			gadgetsEntryId);

		gadgetsEntry.setCompanyId(companyId);
		gadgetsEntry.setCreateDate(now);
		gadgetsEntry.setModifiedDate(now);
		gadgetsEntry.setName(name);
		gadgetsEntry.setUrl(url);
		gadgetsEntry.setXml(xml);

		gadgetsEntryPersistence.update(gadgetsEntry, false);

		return gadgetsEntry;
	}

	public void deleteGadgetsEntry(long gadgetsEntryId)
		throws PortalException, SystemException {

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.findByPrimaryKey(
			gadgetsEntryId);

		deleteGadgetsEntry(gadgetsEntry);
	}

	public void deleteGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws PortalException, SystemException {

		gadgetsEntryPersistence.remove(gadgetsEntry);
	}

	public List<GadgetsEntry> getGadgetsEntries(
			long companyId, int start, int end)
		throws PortalException, SystemException {

		return gadgetsEntryPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getGadgetsEntriesCount(long companyId)
		throws PortalException, SystemException {

		return gadgetsEntryPersistence.countByCompanyId(companyId);
	}

	public GadgetsEntry updateGadgetsEntry(
			long gadgetsEntryId, String name, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.create(
			gadgetsEntryId);

		gadgetsEntry.setModifiedDate(now);
		gadgetsEntry.setName(name);
		gadgetsEntry.setXml(xml);

		gadgetsEntryPersistence.update(gadgetsEntry, false);

		return gadgetsEntry;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GadgetsEntryNameException();
		}
	}

}