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

import com.liferay.tms.model.ProjectEntryClp;
import com.liferay.tms.model.ProjectMilestoneClp;
import com.liferay.tms.model.TaskEntryClp;
import com.liferay.tms.model.TaskViewClp;

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

		if (oldModelClassName.equals(ProjectEntryClp.class.getName())) {
			ProjectEntryClp oldCplModel = (ProjectEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.tms.model.impl.ProjectEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setProjectEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getProjectEntryId());

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

					Method method8 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value8 = oldCplModel.getDescription();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setFinishDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getFinishDate();

					method9.invoke(newModel, value9);

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

		if (oldModelClassName.equals(ProjectMilestoneClp.class.getName())) {
			ProjectMilestoneClp oldCplModel = (ProjectMilestoneClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.tms.model.impl.ProjectMilestoneImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setProjectMilestoneId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getProjectMilestoneId());

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

					Method method6 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value6 = oldCplModel.getTitle();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value7 = oldCplModel.getDescription();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setProjectEntryId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getProjectEntryId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getDueDate();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setFinishDate",
							new Class[] { Date.class });

					Date value10 = oldCplModel.getFinishDate();

					method10.invoke(newModel, value10);

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

		if (oldModelClassName.equals(TaskEntryClp.class.getName())) {
			TaskEntryClp oldCplModel = (TaskEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.tms.model.impl.TaskEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setTaskEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getTaskEntryId());

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

					Method method7 = newModelClass.getMethod("setProjectEntryId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getProjectEntryId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setProjectMilestoneId",
							new Class[] { Long.TYPE });

					Long value8 = new Long(oldCplModel.getProjectMilestoneId());

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value9 = oldCplModel.getTitle();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value10 = oldCplModel.getDescription();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setAssigneeUserId",
							new Class[] { Long.TYPE });

					Long value11 = new Long(oldCplModel.getAssigneeUserId());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setResolverUserId",
							new Class[] { Long.TYPE });

					Long value12 = new Long(oldCplModel.getResolverUserId());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setDueDate",
							new Class[] { Date.class });

					Date value13 = oldCplModel.getDueDate();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setFinishDate",
							new Class[] { Date.class });

					Date value14 = oldCplModel.getFinishDate();

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value15 = new Integer(oldCplModel.getStatus());

					method15.invoke(newModel, value15);

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

		if (oldModelClassName.equals(TaskViewClp.class.getName())) {
			TaskViewClp oldCplModel = (TaskViewClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.tms.model.impl.TaskViewImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setTaskViewId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getTaskViewId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getGroupId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCompanyId",
							new Class[] { String.class });

					String value2 = oldCplModel.getCompanyId();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserId",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserId();

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

					Method method8 = newModelClass.getMethod("setTags",
							new Class[] { String.class });

					String value8 = oldCplModel.getTags();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setNotTags",
							new Class[] { String.class });

					String value9 = oldCplModel.getNotTags();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setAssigneeUserId",
							new Class[] { Long.TYPE });

					Long value10 = new Long(oldCplModel.getAssigneeUserId());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setReporterUserId",
							new Class[] { Long.TYPE });

					Long value11 = new Long(oldCplModel.getReporterUserId());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setIncludeProjectTasks",
							new Class[] { Integer.TYPE });

					Integer value12 = new Integer(oldCplModel.getIncludeProjectTasks());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setIsPrivate",
							new Class[] { Boolean.TYPE });

					Boolean value13 = new Boolean(oldCplModel.getIsPrivate());

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
					"com.liferay.tms.model.impl.ProjectEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					ProjectEntryClp newModel = new ProjectEntryClp();

					Method method0 = oldModelClass.getMethod(
							"getProjectEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setProjectEntryId(value0.longValue());

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

					Method method8 = oldModelClass.getMethod("getDescription");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value8);

					Method method9 = oldModelClass.getMethod("getFinishDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setFinishDate(value9);

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

		if (oldModelClassName.equals(
					"com.liferay.tms.model.impl.ProjectMilestoneImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					ProjectMilestoneClp newModel = new ProjectMilestoneClp();

					Method method0 = oldModelClass.getMethod(
							"getProjectMilestoneId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setProjectMilestoneId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod("getTitle");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value6);

					Method method7 = oldModelClass.getMethod("getDescription");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value7);

					Method method8 = oldModelClass.getMethod(
							"getProjectEntryId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setProjectEntryId(value8.longValue());

					Method method9 = oldModelClass.getMethod("getDueDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setDueDate(value9);

					Method method10 = oldModelClass.getMethod("getFinishDate");

					Date value10 = (Date)method10.invoke(oldModel,
							(Object[])null);

					newModel.setFinishDate(value10);

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

		if (oldModelClassName.equals("com.liferay.tms.model.impl.TaskEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					TaskEntryClp newModel = new TaskEntryClp();

					Method method0 = oldModelClass.getMethod("getTaskEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setTaskEntryId(value0.longValue());

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

					Method method7 = oldModelClass.getMethod(
							"getProjectEntryId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setProjectEntryId(value7.longValue());

					Method method8 = oldModelClass.getMethod(
							"getProjectMilestoneId");

					Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

					newModel.setProjectMilestoneId(value8.longValue());

					Method method9 = oldModelClass.getMethod("getTitle");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value9);

					Method method10 = oldModelClass.getMethod("getDescription");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value10);

					Method method11 = oldModelClass.getMethod(
							"getAssigneeUserId");

					Long value11 = (Long)method11.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeUserId(value11.longValue());

					Method method12 = oldModelClass.getMethod(
							"getResolverUserId");

					Long value12 = (Long)method12.invoke(oldModel,
							(Object[])null);

					newModel.setResolverUserId(value12.longValue());

					Method method13 = oldModelClass.getMethod("getDueDate");

					Date value13 = (Date)method13.invoke(oldModel,
							(Object[])null);

					newModel.setDueDate(value13);

					Method method14 = oldModelClass.getMethod("getFinishDate");

					Date value14 = (Date)method14.invoke(oldModel,
							(Object[])null);

					newModel.setFinishDate(value14);

					Method method15 = oldModelClass.getMethod("getStatus");

					Integer value15 = (Integer)method15.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value15.intValue());

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

		if (oldModelClassName.equals("com.liferay.tms.model.impl.TaskViewImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					TaskViewClp newModel = new TaskViewClp();

					Method method0 = oldModelClass.getMethod("getTaskViewId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setTaskViewId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCompanyId");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setCompanyId(value2);

					Method method3 = oldModelClass.getMethod("getUserId");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserId(value3);

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

					Method method8 = oldModelClass.getMethod("getTags");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setTags(value8);

					Method method9 = oldModelClass.getMethod("getNotTags");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setNotTags(value9);

					Method method10 = oldModelClass.getMethod(
							"getAssigneeUserId");

					Long value10 = (Long)method10.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeUserId(value10.longValue());

					Method method11 = oldModelClass.getMethod(
							"getReporterUserId");

					Long value11 = (Long)method11.invoke(oldModel,
							(Object[])null);

					newModel.setReporterUserId(value11.longValue());

					Method method12 = oldModelClass.getMethod(
							"getIncludeProjectTasks");

					Integer value12 = (Integer)method12.invoke(oldModel,
							(Object[])null);

					newModel.setIncludeProjectTasks(value12.intValue());

					Method method13 = oldModelClass.getMethod("getIsPrivate");

					Boolean value13 = (Boolean)method13.invoke(oldModel,
							(Object[])null);

					newModel.setIsPrivate(value13.booleanValue());

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