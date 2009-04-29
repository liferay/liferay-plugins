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
 *
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

	public static com.liferay.wsrp.model.WSRPProducer create(long producerId) {
		return getPersistence().create(producerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer remove(long producerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().remove(producerId);
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
		long producerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByPrimaryKey(producerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long producerId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(producerId);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByInstanceName(
		java.lang.String instanceName)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByInstanceName(instanceName);
	}

	public static com.liferay.wsrp.model.WSRPProducer fetchByInstanceName(
		java.lang.String instanceName)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByInstanceName(instanceName);
	}

	public static com.liferay.wsrp.model.WSRPProducer fetchByInstanceName(
		java.lang.String instanceName, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .fetchByInstanceName(instanceName, retrieveFromCache);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByP_N_First(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByP_N_First(portalId, namespace, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer findByP_N_Last(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence().findByP_N_Last(portalId, namespace, obc);
	}

	public static com.liferay.wsrp.model.WSRPProducer[] findByP_N_PrevAndNext(
		long producerId, java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		return getPersistence()
				   .findByP_N_PrevAndNext(producerId, portalId, namespace, obc);
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

	public static void removeByInstanceName(java.lang.String instanceName)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException {
		getPersistence().removeByInstanceName(instanceName);
	}

	public static void removeByP_N(java.lang.String portalId,
		java.lang.String namespace) throws com.liferay.portal.SystemException {
		getPersistence().removeByP_N(portalId, namespace);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByInstanceName(java.lang.String instanceName)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByInstanceName(instanceName);
	}

	public static int countByP_N(java.lang.String portalId,
		java.lang.String namespace) throws com.liferay.portal.SystemException {
		return getPersistence().countByP_N(portalId, namespace);
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