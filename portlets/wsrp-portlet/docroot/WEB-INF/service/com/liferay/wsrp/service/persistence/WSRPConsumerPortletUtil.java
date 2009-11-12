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

package com.liferay.wsrp.service.persistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.util.List;

/**
 * <a href="WSRPConsumerPortletUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletUtil {
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

	public static WSRPConsumerPortlet remove(
		WSRPConsumerPortlet wsrpConsumerPortlet) throws SystemException {
		return getPersistence().remove(wsrpConsumerPortlet);
	}

	public static WSRPConsumerPortlet update(
		WSRPConsumerPortlet wsrpConsumerPortlet, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumerPortlet, merge);
	}

	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		getPersistence().cacheResult(wsrpConsumerPortlet);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> wsrpConsumerPortlets) {
		getPersistence().cacheResult(wsrpConsumerPortlets);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet create(
		long wsrpConsumerPortletId) {
		return getPersistence().create(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet remove(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().remove(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wsrpConsumerPortlet, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByPrimaryKey(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchByPrimaryKey(
		long wsrpConsumerPortletId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerPortletId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId) throws com.liferay.portal.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByWsrpConsumerId(wsrpConsumerId, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByWsrpConsumerId(wsrpConsumerId, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByWsrpConsumerId_First(wsrpConsumerId, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence().findByWsrpConsumerId_Last(wsrpConsumerId, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException {
		return getPersistence()
				   .findByWsrpConsumerId_PrevAndNext(wsrpConsumerPortletId,
			wsrpConsumerId, obc);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByWsrpConsumerId(wsrpConsumerId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByWsrpConsumerId(wsrpConsumerId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerPortletPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPortletPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPortletPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPortletPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerPortletPersistence _persistence;
}