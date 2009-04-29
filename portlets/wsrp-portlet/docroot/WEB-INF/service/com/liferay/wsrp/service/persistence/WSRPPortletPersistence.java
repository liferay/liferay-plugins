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
 * <a href="WSRPPortletPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface WSRPPortletPersistence extends BasePersistence {
	public void cacheResult(com.liferay.wsrp.model.WSRPPortlet wsrpPortlet);

	public void cacheResult(
		java.util.List<com.liferay.wsrp.model.WSRPPortlet> wsrpPortlets);

	public com.liferay.wsrp.model.WSRPPortlet create(long portletId);

	public com.liferay.wsrp.model.WSRPPortlet remove(long portletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet remove(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet update(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet update(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet updateImpl(
		com.liferay.wsrp.model.WSRPPortlet wsrpPortlet, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet findByPrimaryKey(long portletId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet fetchByPrimaryKey(long portletId)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet findByPortletName(
		java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet fetchByPortletName(
		java.lang.String name) throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet fetchByPortletName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByProducerEntityId(
		java.lang.String producerEntityId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByProducerEntityId(
		java.lang.String producerEntityId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByProducerEntityId(
		java.lang.String producerEntityId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet findByProducerEntityId_First(
		java.lang.String producerEntityId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet findByProducerEntityId_Last(
		java.lang.String producerEntityId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet[] findByProducerEntityId_PrevAndNext(
		long portletId, java.lang.String producerEntityId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByP_P(
		java.lang.String producerEntityId, java.lang.String portletHandle)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByP_P(
		java.lang.String producerEntityId, java.lang.String portletHandle,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findByP_P(
		java.lang.String producerEntityId, java.lang.String portletHandle,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.wsrp.model.WSRPPortlet findByP_P_First(
		java.lang.String producerEntityId, java.lang.String portletHandle,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet findByP_P_Last(
		java.lang.String producerEntityId, java.lang.String portletHandle,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public com.liferay.wsrp.model.WSRPPortlet[] findByP_P_PrevAndNext(
		long portletId, java.lang.String producerEntityId,
		java.lang.String portletHandle,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.wsrp.model.WSRPPortlet> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByPortletName(java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchPortletException;

	public void removeByProducerEntityId(java.lang.String producerEntityId)
		throws com.liferay.portal.SystemException;

	public void removeByP_P(java.lang.String producerEntityId,
		java.lang.String portletHandle)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByPortletName(java.lang.String name)
		throws com.liferay.portal.SystemException;

	public int countByProducerEntityId(java.lang.String producerEntityId)
		throws com.liferay.portal.SystemException;

	public int countByP_P(java.lang.String producerEntityId,
		java.lang.String portletHandle)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}