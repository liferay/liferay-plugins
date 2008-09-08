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

import com.liferay.wol.model.JIRAChangeItemClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="JIRAChangeItemLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class JIRAChangeItemLocalServiceClp implements JIRAChangeItemLocalService {
	public JIRAChangeItemLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.wol.model.JIRAChangeItem addJIRAChangeItem(
		com.liferay.wol.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraChangeItem);

		if (jiraChangeItem == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAChangeItem");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addJIRAChangeItem",
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

		return (com.liferay.wol.model.JIRAChangeItem)translateOutput(returnObj);
	}

	public com.liferay.wol.model.JIRAChangeItem createJIRAChangeItem(
		long jiraChangeItemId) {
		Object paramObj0 = new LongWrapper(jiraChangeItemId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createJIRAChangeItem",
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

		return (com.liferay.wol.model.JIRAChangeItem)translateOutput(returnObj);
	}

	public void deleteJIRAChangeItem(long jiraChangeItemId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(jiraChangeItemId);

		try {
			_classLoaderProxy.invoke("deleteJIRAChangeItem",
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

	public void deleteJIRAChangeItem(
		com.liferay.wol.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraChangeItem);

		if (jiraChangeItem == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAChangeItem");
		}

		try {
			_classLoaderProxy.invoke("deleteJIRAChangeItem",
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

	public com.liferay.wol.model.JIRAChangeItem getJIRAChangeItem(
		long jiraChangeItemId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(jiraChangeItemId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAChangeItem",
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

		return (com.liferay.wol.model.JIRAChangeItem)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAChangeItem> getJIRAChangeItems(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAChangeItems",
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

		return (java.util.List<com.liferay.wol.model.JIRAChangeItem>)translateOutput(returnObj);
	}

	public int getJIRAChangeItemsCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAChangeItemsCount",
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

	public com.liferay.wol.model.JIRAChangeItem updateJIRAChangeItem(
		com.liferay.wol.model.JIRAChangeItem jiraChangeItem)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(jiraChangeItem);

		if (jiraChangeItem == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.JIRAChangeItem");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateJIRAChangeItem",
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

		return (com.liferay.wol.model.JIRAChangeItem)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.JIRAChangeItem> getJIRAChangeItems(
		long jiraChangeGroupId) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(jiraChangeGroupId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getJIRAChangeItems",
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

		return (java.util.List<com.liferay.wol.model.JIRAChangeItem>)translateOutput(returnObj);
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(JIRAChangeItemClp.class.getName())) {
			JIRAChangeItemClp oldCplModel = (JIRAChangeItemClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAChangeItemImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraChangeItemId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraChangeItemId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setJiraChangeGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getJiraChangeGroupId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setField",
							new Class[] { String.class });

					String value2 = oldCplModel.getField();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setOldValue",
							new Class[] { String.class });

					String value3 = oldCplModel.getOldValue();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setOldString",
							new Class[] { String.class });

					String value4 = oldCplModel.getOldString();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setNewValue",
							new Class[] { String.class });

					String value5 = oldCplModel.getNewValue();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setNewString",
							new Class[] { String.class });

					String value6 = oldCplModel.getNewString();

					method6.invoke(newModel, value6);

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
					"com.liferay.wol.model.impl.JIRAChangeItemImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAChangeItemClp newModel = new JIRAChangeItemClp();

					Method method0 = oldModelClass.getMethod(
							"getJiraChangeItemId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraChangeItemId(value0.longValue());

					Method method1 = oldModelClass.getMethod(
							"getJiraChangeGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setJiraChangeGroupId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getField");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setField(value2);

					Method method3 = oldModelClass.getMethod("getOldValue");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setOldValue(value3);

					Method method4 = oldModelClass.getMethod("getOldString");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setOldString(value4);

					Method method5 = oldModelClass.getMethod("getNewValue");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setNewValue(value5);

					Method method6 = oldModelClass.getMethod("getNewString");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setNewString(value6);

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

	private static Log _log = LogFactoryUtil.getLog(JIRAChangeItemLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}