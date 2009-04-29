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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="WSRPConsumerRegistrationLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerRegistrationLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPConsumerRegistration addWSRPConsumerRegistration(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration)
		throws com.liferay.portal.SystemException {
		return getService().addWSRPConsumerRegistration(wsrpConsumerRegistration);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration createWSRPConsumerRegistration(
		long consumerRegistrationId) {
		return getService()
				   .createWSRPConsumerRegistration(consumerRegistrationId);
	}

	public static void deleteWSRPConsumerRegistration(
		long consumerRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConsumerRegistration(consumerRegistrationId);
	}

	public static void deleteWSRPConsumerRegistration(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration)
		throws com.liferay.portal.SystemException {
		getService().deleteWSRPConsumerRegistration(wsrpConsumerRegistration);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration getWSRPConsumerRegistration(
		long consumerRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWSRPConsumerRegistration(consumerRegistrationId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> getWSRPConsumerRegistrations(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerRegistrations(start, end);
	}

	public static int getWSRPConsumerRegistrationsCount()
		throws com.liferay.portal.SystemException {
		return getService().getWSRPConsumerRegistrationsCount();
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration updateWSRPConsumerRegistration(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration)
		throws com.liferay.portal.SystemException {
		return getService()
				   .updateWSRPConsumerRegistration(wsrpConsumerRegistration);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration updateWSRPConsumerRegistration(
		com.liferay.wsrp.model.WSRPConsumerRegistration wsrpConsumerRegistration,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService()
				   .updateWSRPConsumerRegistration(wsrpConsumerRegistration,
			merge);
	}

	public static void addConsumerRegistration(java.lang.String consumerName,
		boolean status, java.lang.String registrationHandle,
		java.lang.String registrationData,
		java.lang.String lifetimeTerminationTime, java.lang.String producerKey)
		throws com.liferay.portal.SystemException {
		getService()
			.addConsumerRegistration(consumerName, status, registrationHandle,
			registrationData, lifetimeTerminationTime, producerKey);
	}

	public static com.liferay.wsrp.model.WSRPConsumerRegistration getConsumerRegistration(
		java.lang.String registrationHandle, java.lang.String producerKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService()
				   .getConsumerRegistration(registrationHandle, producerKey);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerRegistration> getConsumerRegistrations(
		java.lang.String producerKey) throws com.liferay.portal.SystemException {
		return getService().getConsumerRegistrations(producerKey);
	}

	public static WSRPConsumerRegistrationLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wsrp-portlet",
					WSRPConsumerRegistrationLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wsrp-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WSRPConsumerRegistrationLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPConsumerRegistrationLocalService service) {
		_service = service;
	}

	private static WSRPConsumerRegistrationLocalService _service;
}