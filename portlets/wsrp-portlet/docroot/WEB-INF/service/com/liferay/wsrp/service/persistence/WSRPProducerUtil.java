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

/**
 * <a href="WSRPProducerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPProducerUtil {
	public static void cacheResult(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		getPersistence().cacheResult(wsrpProducer);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPProducer> wsrpProducers) {
		getPersistence().cacheResult(wsrpProducers);
	}

	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static com.liferay.wsrp.model.WSRPProducer create(
		long wsrpProducerId) {
		return getPersistence().create(wsrpProducerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer remove(
		long wsrpProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().remove(wsrpProducerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer remove(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(wsrpProducer);
	}

	public static com.liferay.wsrp.model.WSRPProducer update(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpProducer);
	}

	public static com.liferay.wsrp.model.WSRPProducer update(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpProducer, merge);
	}

	public static com.liferay.wsrp.model.WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wsrpProducer, merge);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByPrimaryKey(wsrpProducerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long wsrpProducerId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(wsrpProducerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(wsrpProducerId, companyId, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
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

	public static WSRPProducerPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WSRPProducerPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPProducerPersistence _persistence;
}