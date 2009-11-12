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

package com.liferay.wsrp.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.WSRPConsumerLocalService;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalService;
import com.liferay.wsrp.service.WSRPProducerLocalService;
import com.liferay.wsrp.service.persistence.WSRPConsumerPersistence;
import com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence;
import com.liferay.wsrp.service.persistence.WSRPProducerPersistence;

import java.util.List;

/**
 * <a href="WSRPConsumerLocalServiceBaseImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class WSRPConsumerLocalServiceBaseImpl
	implements WSRPConsumerLocalService {
	public WSRPConsumer addWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws SystemException {
		wsrpConsumer.setNew(true);

		return wsrpConsumerPersistence.update(wsrpConsumer, false);
	}

	public WSRPConsumer createWSRPConsumer(long wsrpConsumerId) {
		return wsrpConsumerPersistence.create(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException, SystemException {
		wsrpConsumerPersistence.remove(wsrpConsumerId);
	}

	public void deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException, SystemException {
		wsrpConsumerPersistence.remove(wsrpConsumer);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return wsrpConsumerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return wsrpConsumerPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public WSRPConsumer getWSRPConsumer(long wsrpConsumerId)
		throws PortalException, SystemException {
		return wsrpConsumerPersistence.findByPrimaryKey(wsrpConsumerId);
	}

	public List<WSRPConsumer> getWSRPConsumers(int start, int end)
		throws SystemException {
		return wsrpConsumerPersistence.findAll(start, end);
	}

	public int getWSRPConsumersCount() throws SystemException {
		return wsrpConsumerPersistence.countAll();
	}

	public WSRPConsumer updateWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws SystemException {
		wsrpConsumer.setNew(false);

		return wsrpConsumerPersistence.update(wsrpConsumer, true);
	}

	public WSRPConsumer updateWSRPConsumer(WSRPConsumer wsrpConsumer,
		boolean merge) throws SystemException {
		wsrpConsumer.setNew(false);

		return wsrpConsumerPersistence.update(wsrpConsumer, merge);
	}

	public WSRPConsumerLocalService getWSRPConsumerLocalService() {
		return wsrpConsumerLocalService;
	}

	public void setWSRPConsumerLocalService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		this.wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	public WSRPConsumerPersistence getWSRPConsumerPersistence() {
		return wsrpConsumerPersistence;
	}

	public void setWSRPConsumerPersistence(
		WSRPConsumerPersistence wsrpConsumerPersistence) {
		this.wsrpConsumerPersistence = wsrpConsumerPersistence;
	}

	public WSRPConsumerPortletLocalService getWSRPConsumerPortletLocalService() {
		return wsrpConsumerPortletLocalService;
	}

	public void setWSRPConsumerPortletLocalService(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		this.wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	public WSRPConsumerPortletPersistence getWSRPConsumerPortletPersistence() {
		return wsrpConsumerPortletPersistence;
	}

	public void setWSRPConsumerPortletPersistence(
		WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence) {
		this.wsrpConsumerPortletPersistence = wsrpConsumerPortletPersistence;
	}

	public WSRPProducerLocalService getWSRPProducerLocalService() {
		return wsrpProducerLocalService;
	}

	public void setWSRPProducerLocalService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		this.wsrpProducerLocalService = wsrpProducerLocalService;
	}

	public WSRPProducerPersistence getWSRPProducerPersistence() {
		return wsrpProducerPersistence;
	}

	public void setWSRPProducerPersistence(
		WSRPProducerPersistence wsrpProducerPersistence) {
		this.wsrpProducerPersistence = wsrpProducerPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DB db = DBFactoryUtil.getDB();

			db.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "com.liferay.wsrp.service.WSRPConsumerLocalService")
	protected WSRPConsumerLocalService wsrpConsumerLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPersistence")
	protected WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.WSRPConsumerPortletLocalService")
	protected WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence")
	protected WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.WSRPProducerLocalService")
	protected WSRPProducerLocalService wsrpProducerLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence")
	protected WSRPProducerPersistence wsrpProducerPersistence;
}