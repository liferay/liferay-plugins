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

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="WSRPConfiguredProducerLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConfiguredProducerLocalServiceClp
	implements WSRPConfiguredProducerLocalService {
	public WSRPConfiguredProducerLocalServiceClp(
		ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer addWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConfiguredProducer);

		if (wsrpConfiguredProducer == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wsrp.model.WSRPConfiguredProducer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWSRPConfiguredProducer",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer createWSRPConfiguredProducer(
		long configuredProducerId) {
		Object paramObj0 = new LongWrapper(configuredProducerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createWSRPConfiguredProducer",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteWSRPConfiguredProducer(long configuredProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(configuredProducerId);

		try {
			_classLoaderProxy.invoke("deleteWSRPConfiguredProducer",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConfiguredProducer);

		if (wsrpConfiguredProducer == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wsrp.model.WSRPConfiguredProducer");
		}

		try {
			_classLoaderProxy.invoke("deleteWSRPConfiguredProducer",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer getWSRPConfiguredProducer(
		long configuredProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(configuredProducerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConfiguredProducer",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getWSRPConfiguredProducers(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConfiguredProducers",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPConfiguredProducersCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConfiguredProducersCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConfiguredProducer);

		if (wsrpConfiguredProducer == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wsrp.model.WSRPConfiguredProducer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPConfiguredProducer",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer updateWSRPConfiguredProducer(
		com.liferay.wsrp.model.WSRPConfiguredProducer wsrpConfiguredProducer,
		boolean merge) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConfiguredProducer);

		if (wsrpConfiguredProducer == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wsrp.model.WSRPConfiguredProducer");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPConfiguredProducer",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public void addConfiguredProducer(java.lang.String name,
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
		Object paramObj0 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = ClpSerializer.translateInput(portalId);

		if (portalId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(namespace);

		if (namespace == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(producerURL);

		if (producerURL == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(producerVersion);

		if (producerVersion == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(producerMarkupURL);

		if (producerMarkupURL == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = new IntegerWrapper(status);

		Object paramObj7 = ClpSerializer.translateInput(registrationData);

		if (registrationData == null) {
			paramObj7 = new NullWrapper("java.lang.String");
		}

		Object paramObj8 = ClpSerializer.translateInput(registrationContext);

		if (registrationContext == null) {
			paramObj8 = new NullWrapper("java.lang.String");
		}

		Object paramObj9 = ClpSerializer.translateInput(serviceDescription);

		if (serviceDescription == null) {
			paramObj9 = new NullWrapper("java.lang.String");
		}

		Object paramObj10 = ClpSerializer.translateInput(userCategoryMapping);

		if (userCategoryMapping == null) {
			paramObj10 = new NullWrapper("java.lang.String");
		}

		Object paramObj11 = ClpSerializer.translateInput(customUserProfile);

		if (customUserProfile == null) {
			paramObj11 = new NullWrapper("java.lang.String");
		}

		Object paramObj12 = ClpSerializer.translateInput(identityPropagationType);

		if (identityPropagationType == null) {
			paramObj12 = new NullWrapper("java.lang.String");
		}

		Object paramObj13 = ClpSerializer.translateInput(lifetimeTerminationTime);

		if (lifetimeTerminationTime == null) {
			paramObj13 = new NullWrapper("java.lang.String");
		}

		Object paramObj14 = new LongWrapper(sdLastModified);

		Object paramObj15 = new IntegerWrapper(entityVersion);

		try {
			_classLoaderProxy.invoke("addConfiguredProducer",
				new Object[] {
					paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
					paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
					paramObj10, paramObj11, paramObj12, paramObj13, paramObj14,
					paramObj15
				});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.wsrp.model.WSRPConfiguredProducer getConfiguredProducer(
		long configuredProducer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(configuredProducer);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getConfiguredProducer",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.wsrp.model.WSRPConfiguredProducer)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer> getConfiguredProducers(
		java.lang.String portalId, java.lang.String namespace)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(portalId);

		if (portalId == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = ClpSerializer.translateInput(namespace);

		if (namespace == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getConfiguredProducers",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.wsrp.model.WSRPConfiguredProducer>)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}