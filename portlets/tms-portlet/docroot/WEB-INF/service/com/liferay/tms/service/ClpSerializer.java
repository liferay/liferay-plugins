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

package com.liferay.tms.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import com.liferay.tms.model.TasksEntryClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ClpSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ClpSerializer {
	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(TasksEntryClp.class.getName())) {
			TasksEntryClp oldCplModel = (TasksEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.tms.model.impl.TasksEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setTasksEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getTasksEntryId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getGroupId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getCompanyId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getUserId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value4 = oldCplModel.getUserName();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getCreateDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value6 = oldCplModel.getModifiedDate();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value7 = oldCplModel.getTitle();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setPriority",
							new Class[] { Integer.TYPE });

					Integer value8 = new Integer(oldCplModel.getPriority());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setAssigneeUserId",
							new Class[] { Long.TYPE });

					Long value9 = new Long(oldCplModel.getAssigneeUserId());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setResolverUserId",
							new Class[] { Long.TYPE });

					Long value10 = new Long(oldCplModel.getResolverUserId());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value11 = oldCplModel.getDueDate();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setFinishDate",
							new Class[] { Date.class });

					Date value12 = oldCplModel.getFinishDate();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value13 = new Integer(oldCplModel.getStatus());

					method13.invoke(newModel, value13);

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

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.tms.model.impl.TasksEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					TasksEntryClp newModel = new TasksEntryClp();

					Method method0 = oldModelClass.getMethod("getTasksEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setTasksEntryId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCompanyId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setUserId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getUserName");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value4);

					Method method5 = oldModelClass.getMethod("getCreateDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value5);

					Method method6 = oldModelClass.getMethod("getModifiedDate");

					Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value6);

					Method method7 = oldModelClass.getMethod("getTitle");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value7);

					Method method8 = oldModelClass.getMethod("getPriority");

					Integer value8 = (Integer)method8.invoke(oldModel,
							(Object[])null);

					newModel.setPriority(value8.intValue());

					Method method9 = oldModelClass.getMethod(
							"getAssigneeUserId");

					Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

					newModel.setAssigneeUserId(value9.longValue());

					Method method10 = oldModelClass.getMethod(
							"getResolverUserId");

					Long value10 = (Long)method10.invoke(oldModel,
							(Object[])null);

					newModel.setResolverUserId(value10.longValue());

					Method method11 = oldModelClass.getMethod("getDueDate");

					Date value11 = (Date)method11.invoke(oldModel,
							(Object[])null);

					newModel.setDueDate(value11);

					Method method12 = oldModelClass.getMethod("getFinishDate");

					Date value12 = (Date)method12.invoke(oldModel,
							(Object[])null);

					newModel.setFinishDate(value12);

					Method method13 = oldModelClass.getMethod("getStatus");

					Integer value13 = (Integer)method13.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value13.intValue());

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

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}