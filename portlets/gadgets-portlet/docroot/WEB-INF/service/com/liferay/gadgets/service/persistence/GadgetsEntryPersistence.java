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

package com.liferay.gadgets.service.persistence;

import com.liferay.gadgets.model.GadgetsEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="GadgetsEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface GadgetsEntryPersistence extends BasePersistence<GadgetsEntry> {
	public void cacheResult(com.liferay.gadgets.model.GadgetsEntry gadgetsEntry);

	public void cacheResult(
		java.util.List<com.liferay.gadgets.model.GadgetsEntry> gadgetsEntries);

	public com.liferay.gadgets.model.GadgetsEntry create(long gadgetEntryId);

	public com.liferay.gadgets.model.GadgetsEntry remove(long gadgetEntryId)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry updateImpl(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry findByPrimaryKey(
		long gadgetEntryId)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry fetchByPrimaryKey(
		long gadgetEntryId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.gadgets.model.GadgetsEntry[] findByCompanyId_PrevAndNext(
		long gadgetEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}