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

import com.liferay.wsrp.model.WSRPProducer;

/**
 * <a href="WSRPProducerPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WSRPProducerPersistence extends BasePersistence<WSRPProducer> {
	public void cacheResult(com.liferay.wsrp.model.WSRPProducer wsrpProducer);

	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPProducer> wsrpProducers);

	public com.liferay.wsrp.model.WSRPProducer create(long wsrpProducerId);

	public com.liferay.wsrp.model.WSRPProducer remove(long wsrpProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer updateImpl(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPProducer findByPrimaryKey(
		long wsrpProducerId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer fetchByPrimaryKey(
		long wsrpProducerId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public com.liferay.wsrp.model.WSRPProducer[] findByCompanyId_PrevAndNext(
		long wsrpProducerId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchProducerException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}