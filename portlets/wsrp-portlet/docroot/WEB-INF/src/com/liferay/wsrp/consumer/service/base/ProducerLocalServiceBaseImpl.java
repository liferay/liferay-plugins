/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.consumer.service.base;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.CounterLocalServiceFactory;
import com.liferay.counter.service.CounterService;
import com.liferay.counter.service.CounterServiceFactory;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.dao.DynamicQueryInitializer;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceFactory;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.UserServiceFactory;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.UserUtil;

import com.liferay.wsrp.consumer.model.Producer;
import com.liferay.wsrp.consumer.service.ProducerLocalService;
import com.liferay.wsrp.consumer.service.persistence.ProducerPersistence;
import com.liferay.wsrp.consumer.service.persistence.ProducerUtil;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * <a href="ProducerLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class ProducerLocalServiceBaseImpl
	implements ProducerLocalService, InitializingBean {
	public Producer addProducer(Producer producer) throws SystemException {
		producer.setNew(true);

		return producerPersistence.update(producer, false);
	}

	public void deleteProducer(long producerId)
		throws PortalException, SystemException {
		producerPersistence.remove(producerId);
	}

	public void deleteProducer(Producer producer) throws SystemException {
		producerPersistence.remove(producer);
	}

	public List<Producer> dynamicQuery(DynamicQueryInitializer queryInitializer)
		throws SystemException {
		return producerPersistence.findWithDynamicQuery(queryInitializer);
	}

	public List<Producer> dynamicQuery(
		DynamicQueryInitializer queryInitializer, int start, int end)
		throws SystemException {
		return producerPersistence.findWithDynamicQuery(queryInitializer,
			start, end);
	}

	public Producer getProducer(long producerId)
		throws PortalException, SystemException {
		return producerPersistence.findByPrimaryKey(producerId);
	}

	public Producer updateProducer(Producer producer) throws SystemException {
		producer.setNew(false);

		return producerPersistence.update(producer, true);
	}

	public ProducerPersistence getProducerPersistence() {
		return producerPersistence;
	}

	public void setProducerPersistence(ProducerPersistence producerPersistence) {
		this.producerPersistence = producerPersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public CounterService getCounterService() {
		return counterService;
	}

	public void setCounterService(CounterService counterService) {
		this.counterService = counterService;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		if (producerPersistence == null) {
			producerPersistence = ProducerUtil.getPersistence();
		}

		if (counterLocalService == null) {
			counterLocalService = CounterLocalServiceFactory.getImpl();
		}

		if (counterService == null) {
			counterService = CounterServiceFactory.getImpl();
		}

		if (userLocalService == null) {
			userLocalService = UserLocalServiceFactory.getImpl();
		}

		if (userService == null) {
			userService = UserServiceFactory.getImpl();
		}

		if (userPersistence == null) {
			userPersistence = UserUtil.getPersistence();
		}
	}

	protected ProducerPersistence producerPersistence;
	protected CounterLocalService counterLocalService;
	protected CounterService counterService;
	protected UserLocalService userLocalService;
	protected UserService userService;
	protected UserPersistence userPersistence;
}