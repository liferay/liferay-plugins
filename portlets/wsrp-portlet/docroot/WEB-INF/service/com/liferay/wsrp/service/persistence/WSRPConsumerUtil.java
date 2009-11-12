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

import com.liferay.wsrp.model.WSRPConsumer;

import java.util.List;

/**
 * <a href="WSRPConsumerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerUtil {
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

	public static WSRPConsumer remove(WSRPConsumer wsrpConsumer)
		throws SystemException {
		return getPersistence().remove(wsrpConsumer);
	}

	public static WSRPConsumer update(WSRPConsumer wsrpConsumer, boolean merge)
		throws SystemException {
		return getPersistence().update(wsrpConsumer, merge);
	}

	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		getPersistence().cacheResult(wsrpConsumer);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumer> wsrpConsumers) {
		getPersistence().cacheResult(wsrpConsumers);
	}

	public static com.liferay.wsrp.model.WSRPConsumer create(
		long wsrpConsumerId) {
		return getPersistence().create(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer remove(
		long wsrpConsumerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().remove(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer updateImpl(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wsrpConsumer, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByPrimaryKey(
		long wsrpConsumerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByPrimaryKey(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer fetchByPrimaryKey(
		long wsrpConsumerId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpConsumerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumer findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumer[] findByCompanyId_PrevAndNext(
		long wsrpConsumerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpConsumerId, companyId, obc);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> findAll(
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

	public static WSRPConsumerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WSRPConsumerPersistence)PortletBeanLocatorUtil.locate(com.liferay.wsrp.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConsumerPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WSRPConsumerPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerPersistence _persistence;
}