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

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.OpenSocialGadget;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="OpenSocialGadgetUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class OpenSocialGadgetUtil {
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

	public static OpenSocialGadget remove(OpenSocialGadget openSocialGadget)
		throws SystemException {
		return getPersistence().remove(openSocialGadget);
	}

	public static OpenSocialGadget update(OpenSocialGadget openSocialGadget,
		boolean merge) throws SystemException {
		return getPersistence().update(openSocialGadget, merge);
	}

	public static void cacheResult(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget) {
		getPersistence().cacheResult(openSocialGadget);
	}

	public static void cacheResult(
		java.util.List<com.liferay.opensocial.model.OpenSocialGadget> openSocialGadgets) {
		getPersistence().cacheResult(openSocialGadgets);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget create(
		long openSocialGadgetId) {
		return getPersistence().create(openSocialGadgetId);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget remove(
		long openSocialGadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(openSocialGadgetId);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget updateImpl(
		com.liferay.opensocial.model.OpenSocialGadget openSocialGadget,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(openSocialGadget, merge);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget findByPrimaryKey(
		long openSocialGadgetId)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(openSocialGadgetId);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget fetchByPrimaryKey(
		long openSocialGadgetId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(openSocialGadgetId);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.opensocial.model.OpenSocialGadget[] findByCompanyId_PrevAndNext(
		long openSocialGadgetId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.opensocial.NoSuchGadgetException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(openSocialGadgetId, companyId,
			obc);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.opensocial.model.OpenSocialGadget> findAll(
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

	public static OpenSocialGadgetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OpenSocialGadgetPersistence)PortletBeanLocatorUtil.locate(com.liferay.opensocial.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					OpenSocialGadgetPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(OpenSocialGadgetPersistence persistence) {
		_persistence = persistence;
	}

	private static OpenSocialGadgetPersistence _persistence;
}