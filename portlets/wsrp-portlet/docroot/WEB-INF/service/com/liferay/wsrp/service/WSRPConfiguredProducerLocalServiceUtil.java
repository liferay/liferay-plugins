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
 * <a href="WSRPConfiguredProducerLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerLocalServiceUtil {
	public static com.liferay.wsrp.model.WSRPConfiguredProducer addWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		return getService().addWSRPConfiguredProducer(wsrpConfiguredProducer);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer createWSRPConfiguredProducer(
		long configuredProducerId) {
		return getService().createWSRPConfiguredProducer(configuredProducerId);
	}

	public static void deleteWSRPConfiguredProducer(long configuredProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteWSRPConfiguredProducer(configuredProducerId);
	}

	public static void deleteWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		getService().deleteWSRPConfiguredProducer(wsrpConfiguredProducer);
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

	public static com.liferay.wsrp.model.WSRPConfiguredProducer getWSRPConfiguredProducer(
		long configuredProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getWSRPConfiguredProducer(configuredProducerId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getWSRPConfiguredProducers(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getWSRPConfiguredProducers(start, end);
	}

	public static int getWSRPConfiguredProducersCount()
		throws com.liferay.portal.SystemException {
		return getService().getWSRPConfiguredProducersCount();
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		return getService().updateWSRPConfiguredProducer(wsrpConfiguredProducer);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException {
		return getService()
				   .updateWSRPConfiguredProducer(wsrpConfiguredProducer, merge);
	}

	public static void addConfiguredProducer(java.lang.String name,
		java.lang.String portalId, java.lang.String namespace,
		java.lang.String producerURL, java.lang.String producerVersion,
		java.lang.String producerMarkupURL, int status,
		java.lang.String registrationData,
		java.lang.String registrationContext,
		java.lang.String serviceDescription,
		java.lang.String userCategoryMapping,
		java.lang.String customUserProfile,
		java.lang.String identityPropagationType,
		java.lang.String lifetimeTerminationTime, long sdLastModified,
		int entityVersion) throws com.liferay.portal.SystemException {
		getService()
			.addConfiguredProducer(name, portalId, namespace, producerURL,
			producerVersion, producerMarkupURL, status, registrationData,
			registrationContext, serviceDescription, userCategoryMapping,
			customUserProfile, identityPropagationType,
			lifetimeTerminationTime, sdLastModified, entityVersion);
	}

	public static com.liferay.wsrp.model.WSRPConfiguredProducer getConfiguredProducer(
		long configuredProducer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getConfiguredProducer(configuredProducer);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getConfiguredProducers(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException {
		return getService().getConfiguredProducers(portalId, namespace);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConfiguredProducerLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					WSRPConfiguredProducerLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new WSRPConfiguredProducerLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(WSRPConfiguredProducerLocalService service) {
		_service = service;
	}

	private static WSRPConfiguredProducerLocalService _service;
}