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
 * <a href="WSRPProducerLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPProducerLocalServiceClp implements WSRPProducerLocalService {
	public WSRPProducerLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpProducer);

		if (wsrpProducer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPProducer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWSRPProducer",
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		Object paramObj0 = new LongWrapper(wsrpProducerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createWSRPProducer",
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpProducerId);

		try {
			_classLoaderProxy.invoke("deleteWSRPProducer",
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

	public void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpProducer);

		if (wsrpProducer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPProducer");
		}

		try {
			_classLoaderProxy.invoke("deleteWSRPProducer",
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

	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpProducerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPProducer",
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPProducers",
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

		return (java.util.List<com.liferay.wsrp.model.WSRPProducer>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPProducersCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPProducersCount",
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

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpProducer);

		if (wsrpProducer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPProducer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPProducer",
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpProducer);

		if (wsrpProducer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPProducer");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPProducer",
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long companyId,
		java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(portletIds);

		if (portletIds == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWSRPProducer",
					new Object[] { paramObj0, paramObj1, paramObj2 });
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String portletIds)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpProducerId);

		Object paramObj1 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(portletIds);

		if (portletIds == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPProducer",
					new Object[] { paramObj0, paramObj1, paramObj2 });
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

		return (com.liferay.wsrp.model.WSRPProducer)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}