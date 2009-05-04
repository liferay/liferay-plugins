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
 * <a href="WSRPConfiguredProducerUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerUtil {
	public static void cacheResult(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer) {
		getPersistence().cacheResult(wsrpConfiguredProducer);
	}

	public static void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> wsrpConfiguredProducers) {
		getPersistence().cacheResult(wsrpConfiguredProducers);
	}

	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer create(
		long configuredProducerId) {
		return getPersistence().create(configuredProducerId);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer remove(
		long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException {
		return getPersistence().remove(configuredProducerId);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer remove(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(wsrpConfiguredProducer);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer update(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpConfiguredProducer);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer update(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpConfiguredProducer, merge);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer updateImpl(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wsrpConfiguredProducer, merge);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer findByPrimaryKey(
		long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException {
		return getPersistence().findByPrimaryKey(configuredProducerId);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer fetchByPrimaryKey(
		long configuredProducerId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(configuredProducerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByP_N(portalId, namespace, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer findByP_N_First(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException {
		return getPersistence().findByP_N_First(portalId, namespace, obc);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer findByP_N_Last(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException {
		return getPersistence().findByP_N_Last(portalId, namespace, obc);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer[] findByP_N_PrevAndNext(
		long configuredProducerId, java.lang.String portalId,
		java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException {
		return getPersistence()
				   .findByP_N_PrevAndNext(configuredProducerId, portalId,
			namespace, obc);
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

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByP_N(java.lang.String portalId,
		java.lang.String namespace) throws com.liferay.portal.SystemException {
		getPersistence().removeByP_N(portalId, namespace);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByP_N(java.lang.String portalId,
		java.lang.String namespace) throws com.liferay.portal.SystemException {
		return getPersistence().countByP_N(portalId, namespace);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConfiguredProducerPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WSRPConfiguredProducerPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConfiguredProducerPersistence _persistence;
}