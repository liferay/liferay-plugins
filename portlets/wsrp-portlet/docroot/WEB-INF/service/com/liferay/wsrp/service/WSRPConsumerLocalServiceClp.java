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
 * <a href="WSRPConsumerLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerLocalServiceClp implements WSRPConsumerLocalService {
	public WSRPConsumerLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConsumer);

		if (wsrpConsumer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPConsumer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWSRPConsumer",
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		Object paramObj0 = new LongWrapper(wsrpConsumerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createWSRPConsumer",
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteWSRPConsumer(long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpConsumerId);

		try {
			_classLoaderProxy.invoke("deleteWSRPConsumer",
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

	public void deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConsumer);

		if (wsrpConsumer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPConsumer");
		}

		try {
			_classLoaderProxy.invoke("deleteWSRPConsumer",
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

	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpConsumerId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConsumer",
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConsumers",
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

		return (java.util.List<com.liferay.wsrp.model.WSRPConsumer>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPConsumersCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConsumersCount",
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

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConsumer);

		if (wsrpConsumer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPConsumer");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPConsumer",
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer, boolean merge)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(wsrpConsumer);

		if (wsrpConsumer == null) {
			paramObj0 = new NullWrapper("com.liferay.wsrp.model.WSRPConsumer");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPConsumer",
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(long companyId,
		java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = ClpSerializer.translateInput(adminPortletId);

		if (adminPortletId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(url);

		if (url == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWSRPConsumer",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConsumers",
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

		return (java.util.List<com.liferay.wsrp.model.WSRPConsumer>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPConsumersCount(long companyId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getWSRPConsumersCount",
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

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpConsumerId);

		Object paramObj1 = ClpSerializer.translateInput(adminPortletId);

		if (adminPortletId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(registrationProperties);

		if (registrationProperties == null) {
			paramObj2 = new NullWrapper(
					"com.liferay.portal.kernel.util.UnicodeProperties");
		}

		Object paramObj3 = ClpSerializer.translateInput(registrationHandle);

		if (registrationHandle == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("registerWSRPConsumer",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(wsrpConsumerId);

		Object paramObj1 = ClpSerializer.translateInput(adminPortletId);

		if (adminPortletId == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(name);

		if (name == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(url);

		if (url == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateWSRPConsumer",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.wsrp.model.WSRPConsumer)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}