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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="WSRPConfiguredProducerPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WSRPConfiguredProducerPersistence extends BasePersistence {
	public void cacheResult(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer);

	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> wsrpConfiguredProducers);

	public com.liferay.wsrp.model.WSRPConfiguredProducer create(
		long configuredProducerId);

	public com.liferay.wsrp.model.WSRPConfiguredProducer remove(
		long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer remove(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer update(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer update(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer updateImpl(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer findByPrimaryKey(
		long configuredProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer fetchByPrimaryKey(
		long configuredProducerId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findByP_N(
		java.lang.String portalId, java.lang.String namespace, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer findByP_N_First(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer findByP_N_Last(
		java.lang.String portalId, java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException;

	public com.liferay.wsrp.model.WSRPConfiguredProducer[] findByP_N_PrevAndNext(
		long configuredProducerId, java.lang.String portalId,
		java.lang.String namespace,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConfiguredProducerException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByP_N(java.lang.String portalId,
		java.lang.String namespace) throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByP_N(java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}