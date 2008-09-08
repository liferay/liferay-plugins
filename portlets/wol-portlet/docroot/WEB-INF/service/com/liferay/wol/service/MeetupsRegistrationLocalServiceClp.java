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

package com.liferay.wol.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import com.liferay.wol.model.MeetupsRegistrationClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupsRegistrationLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsRegistrationLocalServiceClp
	implements MeetupsRegistrationLocalService {
	public MeetupsRegistrationLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.wol.model.MeetupsRegistration addMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsRegistration);

		if (meetupsRegistration == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wol.model.MeetupsRegistration");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMeetupsRegistration",
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		Object paramObj0 = new LongWrapper(meetupsRegistrationId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createMeetupsRegistration",
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	public void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsRegistrationId);

		try {
			_classLoaderProxy.invoke("deleteMeetupsRegistration",
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

	public void deleteMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsRegistration);

		if (meetupsRegistration == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wol.model.MeetupsRegistration");
		}

		try {
			_classLoaderProxy.invoke("deleteMeetupsRegistration",
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
		Object paramObj0 = translateInput(dynamicQuery);

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

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

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

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsRegistrationId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistration",
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.MeetupsRegistration> getMeetupsRegistrations(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistrations",
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

		return (java.util.List<com.liferay.wol.model.MeetupsRegistration>)translateOutput(returnObj);
	}

	public int getMeetupsRegistrationsCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistrationsCount",
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

	public com.liferay.wol.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.wol.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsRegistration);

		if (meetupsRegistration == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.wol.model.MeetupsRegistration");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsRegistration",
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object paramObj1 = new IntegerWrapper(status);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistrations",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (java.util.List<com.liferay.wol.model.MeetupsRegistration>)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsRegistration getMeetupsRegistration(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(meetupsEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistration",
					new Object[] { paramObj0, paramObj1 });
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object paramObj1 = new IntegerWrapper(status);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsRegistrationsCount",
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

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.wol.model.MeetupsRegistration updateMeetupsRegistration(
		long userId, long meetupsEntryId, int status, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(meetupsEntryId);

		Object paramObj2 = new IntegerWrapper(status);

		Object paramObj3 = translateInput(comments);

		if (comments == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsRegistration",
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

		return (com.liferay.wol.model.MeetupsRegistration)translateOutput(returnObj);
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(MeetupsRegistrationClp.class.getName())) {
			MeetupsRegistrationClp oldCplModel = (MeetupsRegistrationClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.wol.model.impl.MeetupsRegistrationImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setMeetupsRegistrationId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getMeetupsRegistrationId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getCompanyId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getUserId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserName();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getCreateDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getModifiedDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setMeetupsEntryId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getMeetupsEntryId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getStatus());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value8 = oldCplModel.getComments();

					method8.invoke(newModel, value8);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateInput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	protected Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List)obj);
		}
		else {
			return obj;
		}
	}

	protected Object translateOutput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.wol.model.impl.MeetupsRegistrationImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					MeetupsRegistrationClp newModel = new MeetupsRegistrationClp();

					Method method0 = oldModelClass.getMethod(
							"getMeetupsRegistrationId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setMeetupsRegistrationId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCompanyId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getUserId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setUserId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserName");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value3);

					Method method4 = oldModelClass.getMethod("getCreateDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value4);

					Method method5 = oldModelClass.getMethod("getModifiedDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value5);

					Method method6 = oldModelClass.getMethod(
							"getMeetupsEntryId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setMeetupsEntryId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getStatus");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value7.intValue());

					Method method8 = oldModelClass.getMethod("getComments");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value8);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateOutput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	protected Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MeetupsRegistrationLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}