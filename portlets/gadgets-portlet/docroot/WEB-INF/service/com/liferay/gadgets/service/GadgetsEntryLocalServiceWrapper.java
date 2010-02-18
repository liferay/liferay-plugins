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

package com.liferay.gadgets.service;

/**
 * <a href="GadgetsEntryLocalServiceWrapper.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryLocalServiceWrapper implements GadgetsEntryLocalService {
	public GadgetsEntryLocalServiceWrapper(
		GadgetsEntryLocalService gadgetsEntryLocalService) {
		_gadgetsEntryLocalService = gadgetsEntryLocalService;
	}

	public com.liferay.gadgets.model.GadgetsEntry addGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.addGadgetsEntry(gadgetsEntry);
	}

	public com.liferay.gadgets.model.GadgetsEntry createGadgetsEntry(
		long gadgetsEntryId) {
		return _gadgetsEntryLocalService.createGadgetsEntry(gadgetsEntryId);
	}

	public void deleteGadgetsEntry(long gadgetsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetsEntryLocalService.deleteGadgetsEntry(gadgetsEntryId);
	}

	public void deleteGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_gadgetsEntryLocalService.deleteGadgetsEntry(gadgetsEntry);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.gadgets.model.GadgetsEntry getGadgetsEntry(
		long gadgetsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.getGadgetsEntry(gadgetsEntryId);
	}

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> getGadgetsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.getGadgetsEntries(start, end);
	}

	public int getGadgetsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.getGadgetsEntriesCount();
	}

	public com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.updateGadgetsEntry(gadgetsEntry);
	}

	public com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.updateGadgetsEntry(gadgetsEntry, merge);
	}

	public com.liferay.gadgets.model.GadgetsEntry addGadgetsEntry(
		long companyId, java.lang.String name, java.lang.String url,
		java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.addGadgetsEntry(companyId, name, url,
			xml);
	}

	public void destroyGadgetsEntries()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetsEntryLocalService.destroyGadgetsEntries();
	}

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> getGadgetsEntries(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.getGadgetsEntries(companyId, start, end);
	}

	public int getGadgetsEntriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.getGadgetsEntriesCount(companyId);
	}

	public void initGadgetsEntries()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_gadgetsEntryLocalService.initGadgetsEntries();
	}

	public com.liferay.gadgets.model.GadgetsEntry updateGadgetsEntry(
		long gadgetsEntryId, java.lang.String name, java.lang.String xml)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _gadgetsEntryLocalService.updateGadgetsEntry(gadgetsEntryId,
			name, xml);
	}

	public GadgetsEntryLocalService getWrappedGadgetsEntryLocalService() {
		return _gadgetsEntryLocalService;
	}

	private GadgetsEntryLocalService _gadgetsEntryLocalService;
}