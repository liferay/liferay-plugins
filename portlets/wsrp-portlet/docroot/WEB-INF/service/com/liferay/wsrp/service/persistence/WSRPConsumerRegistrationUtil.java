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
 * <a href="WSRPConsumerRegistrationUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationUtil {
	public static com.liferay.wsrp.model.WSRPConsumerRegistration create(
		long consumerRegistrationId) {
		return getPersistence().create(consumerRegistrationId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration remove(
		long consumerRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence().remove(consumerRegistrationId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration remove(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(wsrpConsumerRegistration);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration update(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpConsumerRegistration);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration update(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(wsrpConsumerRegistration, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration updateImpl(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(wsrpConsumerRegistration, merge);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration findByPrimaryKey(
		long consumerRegistrationId)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence().findByPrimaryKey(consumerRegistrationId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration fetchByPrimaryKey(
		long consumerRegistrationId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(consumerRegistrationId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findByProducerKey(
		java.lang.String producerKey) throws com.liferay.portal.SystemException {
		return getPersistence().findByProducerKey(producerKey);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findByProducerKey(
		java.lang.String producerKey, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByProducerKey(producerKey, start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findByProducerKey(
		java.lang.String producerKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByProducerKey(producerKey, start, end, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration findByProducerKey_First(
		java.lang.String producerKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence().findByProducerKey_First(producerKey, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration findByProducerKey_Last(
		java.lang.String producerKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence().findByProducerKey_Last(producerKey, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration[] findByProducerKey_PrevAndNext(
		long consumerRegistrationId, java.lang.String producerKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence()
				   .findByProducerKey_PrevAndNext(consumerRegistrationId,
			producerKey, obc);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration findByR_P(
		java.lang.String registrationHandle, java.lang.String producerKey)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		return getPersistence().findByR_P(registrationHandle, producerKey);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration fetchByR_P(
		java.lang.String registrationHandle, java.lang.String producerKey)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByR_P(registrationHandle, producerKey);
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

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByProducerKey(java.lang.String producerKey)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByProducerKey(producerKey);
	}

	public static void removeByR_P(java.lang.String registrationHandle,
		java.lang.String producerKey)
		throws com.liferay.portal.SystemException,
			com.liferay.wsrp.NoSuchConsumerRegistrationException {
		getPersistence().removeByR_P(registrationHandle, producerKey);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByProducerKey(java.lang.String producerKey)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByProducerKey(producerKey);
	}

	public static int countByR_P(java.lang.String registrationHandle,
		java.lang.String producerKey) throws com.liferay.portal.SystemException {
		return getPersistence().countByR_P(registrationHandle, producerKey);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static WSRPConsumerRegistrationPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(WSRPConsumerRegistrationPersistence persistence) {
		_persistence = persistence;
	}

	private static WSRPConsumerRegistrationPersistence _persistence;
}