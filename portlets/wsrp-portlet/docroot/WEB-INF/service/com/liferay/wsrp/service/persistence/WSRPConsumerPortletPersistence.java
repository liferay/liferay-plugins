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

import com.liferay.wsrp.model.WSRPConsumerPortlet;

/**
 * <a href="WSRPConsumerPortletPersistence.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WSRPConsumerPortletPersistence extends BasePersistence<WSRPConsumerPortlet> {
	public void cacheResult(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet);

	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> wsrpConsumerPortlets);

	public com.liferay.wsrp.model.WSRPConsumerPortlet create(
		long wsrpConsumerPortletId);

	public com.liferay.wsrp.model.WSRPConsumerPortlet remove(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateImpl(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet findByPrimaryKey(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchByPrimaryKey(
		long wsrpConsumerPortletId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findByWsrpConsumerId(
		long wsrpConsumerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_First(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet findByWsrpConsumerId_Last(
		long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public com.liferay.wsrp.model.WSRPConsumerPortlet[] findByWsrpConsumerId_PrevAndNext(
		long wsrpConsumerPortletId, long wsrpConsumerId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerPortletException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByWsrpConsumerId(long wsrpConsumerId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}