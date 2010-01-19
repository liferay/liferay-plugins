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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="GadgetsEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryUtil {
	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static GadgetsEntry remove(GadgetsEntry gadgetsEntry)
		throws SystemException {
		return getPersistence().remove(gadgetsEntry);
	}

	public static GadgetsEntry update(GadgetsEntry gadgetsEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(gadgetsEntry, merge);
	}

	public static void cacheResult(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry) {
		getPersistence().cacheResult(gadgetsEntry);
	}

	public static void cacheResult(
		java.util.List<com.liferay.gadgets.model.GadgetsEntry> gadgetsEntries) {
		getPersistence().cacheResult(gadgetsEntries);
	}

	public static com.liferay.gadgets.model.GadgetsEntry create(
		long gadgetEntryId) {
		return getPersistence().create(gadgetEntryId);
	}

	public static com.liferay.gadgets.model.GadgetsEntry remove(
		long gadgetEntryId)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(gadgetEntryId);
	}

	public static com.liferay.gadgets.model.GadgetsEntry updateImpl(
		com.liferay.gadgets.model.GadgetsEntry gadgetsEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(gadgetsEntry, merge);
	}

	public static com.liferay.gadgets.model.GadgetsEntry findByPrimaryKey(
		long gadgetEntryId)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(gadgetEntryId);
	}

	public static com.liferay.gadgets.model.GadgetsEntry fetchByPrimaryKey(
		long gadgetEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(gadgetEntryId);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.gadgets.model.GadgetsEntry findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.gadgets.model.GadgetsEntry findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.gadgets.model.GadgetsEntry[] findByCompanyId_PrevAndNext(
		long gadgetEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.gadgets.NoSuchEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(gadgetEntryId, companyId, obc);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.gadgets.model.GadgetsEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static GadgetsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (GadgetsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.gadgets.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					GadgetsEntryPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(GadgetsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static GadgetsEntryPersistence _persistence;
}