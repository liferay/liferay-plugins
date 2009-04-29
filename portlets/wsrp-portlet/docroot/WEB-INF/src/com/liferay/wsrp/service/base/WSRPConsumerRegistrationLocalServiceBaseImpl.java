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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import com.liferay.wsrp.model.WSRPConsumerRegistration;
import com.liferay.wsrp.service.WSRPConfiguredProducerLocalService;
import com.liferay.wsrp.service.WSRPConsumerRegistrationLocalService;
import com.liferay.wsrp.service.WSRPPortletLocalService;
import com.liferay.wsrp.service.WSRPProducerLocalService;
import com.liferay.wsrp.service.persistence.WSRPConfiguredProducerPersistence;
import com.liferay.wsrp.service.persistence.WSRPConsumerRegistrationPersistence;
import com.liferay.wsrp.service.persistence.WSRPPortletPersistence;
import com.liferay.wsrp.service.persistence.WSRPProducerPersistence;

import java.util.List;

/**
 * <a href="WSRPConsumerRegistrationLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class WSRPConsumerRegistrationLocalServiceBaseImpl
	implements WSRPConsumerRegistrationLocalService {
	public WSRPConsumerRegistration addWSRPConsumerRegistration(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		wsrpConsumerRegistration.setNew(true);

		return wsrpConsumerRegistrationPersistence.update(wsrpConsumerRegistration,
			false);
	}

	public WSRPConsumerRegistration createWSRPConsumerRegistration(
		long consumerRegistrationId) {
		return wsrpConsumerRegistrationPersistence.create(consumerRegistrationId);
	}

	public void deleteWSRPConsumerRegistration(long consumerRegistrationId)
		throws PortalException, SystemException {
		wsrpConsumerRegistrationPersistence.remove(consumerRegistrationId);
	}

	public void deleteWSRPConsumerRegistration(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		wsrpConsumerRegistrationPersistence.remove(wsrpConsumerRegistration);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return wsrpConsumerRegistrationPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return wsrpConsumerRegistrationPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public WSRPConsumerRegistration getWSRPConsumerRegistration(
		long consumerRegistrationId) throws PortalException, SystemException {
		return wsrpConsumerRegistrationPersistence.findByPrimaryKey(consumerRegistrationId);
	}

	public List<WSRPConsumerRegistration> getWSRPConsumerRegistrations(
		int start, int end) throws SystemException {
		return wsrpConsumerRegistrationPersistence.findAll(start, end);
	}

	public int getWSRPConsumerRegistrationsCount() throws SystemException {
		return wsrpConsumerRegistrationPersistence.countAll();
	}

	public WSRPConsumerRegistration updateWSRPConsumerRegistration(
		WSRPConsumerRegistration wsrpConsumerRegistration)
		throws SystemException {
		wsrpConsumerRegistration.setNew(false);

		return wsrpConsumerRegistrationPersistence.update(wsrpConsumerRegistration,
			true);
	}

	public WSRPConsumerRegistration updateWSRPConsumerRegistration(
		WSRPConsumerRegistration wsrpConsumerRegistration, boolean merge)
		throws SystemException {
		wsrpConsumerRegistration.setNew(false);

		return wsrpConsumerRegistrationPersistence.update(wsrpConsumerRegistration,
			merge);
	}

	public WSRPConfiguredProducerLocalService getWSRPConfiguredProducerLocalService() {
		return wsrpConfiguredProducerLocalService;
	}

	public void setWSRPConfiguredProducerLocalService(
		WSRPConfiguredProducerLocalService wsrpConfiguredProducerLocalService) {
		this.wsrpConfiguredProducerLocalService = wsrpConfiguredProducerLocalService;
	}

	public WSRPConfiguredProducerPersistence getWSRPConfiguredProducerPersistence() {
		return wsrpConfiguredProducerPersistence;
	}

	public void setWSRPConfiguredProducerPersistence(
		WSRPConfiguredProducerPersistence wsrpConfiguredProducerPersistence) {
		this.wsrpConfiguredProducerPersistence = wsrpConfiguredProducerPersistence;
	}

	public WSRPConsumerRegistrationLocalService getWSRPConsumerRegistrationLocalService() {
		return wsrpConsumerRegistrationLocalService;
	}

	public void setWSRPConsumerRegistrationLocalService(
		WSRPConsumerRegistrationLocalService wsrpConsumerRegistrationLocalService) {
		this.wsrpConsumerRegistrationLocalService = wsrpConsumerRegistrationLocalService;
	}

	public WSRPConsumerRegistrationPersistence getWSRPConsumerRegistrationPersistence() {
		return wsrpConsumerRegistrationPersistence;
	}

	public void setWSRPConsumerRegistrationPersistence(
		WSRPConsumerRegistrationPersistence wsrpConsumerRegistrationPersistence) {
		this.wsrpConsumerRegistrationPersistence = wsrpConsumerRegistrationPersistence;
	}

	public WSRPPortletLocalService getWSRPPortletLocalService() {
		return wsrpPortletLocalService;
	}

	public void setWSRPPortletLocalService(
		WSRPPortletLocalService wsrpPortletLocalService) {
		this.wsrpPortletLocalService = wsrpPortletLocalService;
	}

	public WSRPPortletPersistence getWSRPPortletPersistence() {
		return wsrpPortletPersistence;
	}

	public void setWSRPPortletPersistence(
		WSRPPortletPersistence wsrpPortletPersistence) {
		this.wsrpPortletPersistence = wsrpPortletPersistence;
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

	@BeanReference(name = "com.liferay.wsrp.service.WSRPConfiguredProducerLocalService.impl")
	protected WSRPConfiguredProducerLocalService wsrpConfiguredProducerLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConfiguredProducerPersistence.impl")
	protected WSRPConfiguredProducerPersistence wsrpConfiguredProducerPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.WSRPConsumerRegistrationLocalService.impl")
	protected WSRPConsumerRegistrationLocalService wsrpConsumerRegistrationLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPConsumerRegistrationPersistence.impl")
	protected WSRPConsumerRegistrationPersistence wsrpConsumerRegistrationPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.WSRPPortletLocalService.impl")
	protected WSRPPortletLocalService wsrpPortletLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPPortletPersistence.impl")
	protected WSRPPortletPersistence wsrpPortletPersistence;
	@BeanReference(name = "com.liferay.wsrp.service.WSRPProducerLocalService.impl")
	protected WSRPProducerLocalService wsrpProducerLocalService;
	@BeanReference(name = "com.liferay.wsrp.service.persistence.WSRPProducerPersistence.impl")
	protected WSRPProducerPersistence wsrpProducerPersistence;
}