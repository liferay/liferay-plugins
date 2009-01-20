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
 * <a href="WSRPProducerLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return getService().addWSRPProducer(wsrpProducer);
	}

	public static com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long producerId) {
		return getService().createWSRPProducer(producerId);
	}

	public static void deleteWSRPProducer(long producerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPProducer(producerId);
	}

	public static void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		getService().deleteWSRPProducer(wsrpProducer);
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

	public static com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long producerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWSRPProducer(producerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWSRPProducers(start, end);
	}

	public static int getWSRPProducersCount()
		throws com.liferay.portal.SystemException {
		return getService().getWSRPProducersCount();
	}

	public static com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		return getService().updateWSRPProducer(wsrpProducer);
	}

	public static void addProducer(java.lang.String portalId, boolean status,
		java.lang.String namespace, java.lang.String instanceName,
		boolean requiresRegistration, boolean supportsInbandRegistration,
		java.lang.String version, java.lang.String offeredPortlets,
		java.lang.String producerProfileMap,
		java.lang.String registrationProperties,
		java.lang.String registrationValidatorClass)
		throws com.liferay.portal.SystemException {
		getService()
			.addProducer(portalId, status, namespace, instanceName,
			requiresRegistration, supportsInbandRegistration, version,
			offeredPortlets, producerProfileMap, registrationProperties,
			registrationValidatorClass);
	}

	public static void deleteProducer(
		com.liferay.wsrp.model.WSRPProducer producer)
		throws com.liferay.portal.SystemException {
		getService().deleteProducer(producer);
	}

	public static com.liferay.wsrp.model.WSRPProducer getProducer(
		java.lang.String instanceName)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getProducer(instanceName);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPProducer> getProducers(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException {
		return getService().getProducers(portalId, namespace);
	}

	public static WSRPProducerLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("wsrp-portlet",
					WSRPProducerLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("wsrp-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WSRPProducerLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPProducerLocalService service) {
		_service = service;
	}

	private static WSRPProducerLocalService _service;
}