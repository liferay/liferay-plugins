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

package com.liferay.bi.report.service;

import com.liferay.bi.report.model.ReportDefinitionClp;
import com.liferay.bi.report.model.RequestedReportClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

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

		if (oldModelClassName.equals(ReportDefinitionClp.class.getName())) {
			ReportDefinitionClp oldCplModel = (ReportDefinitionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.bi.report.model.impl.ReportDefinitionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setDefinitionId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getDefinitionId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getCompanyId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getGroupId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getCreateDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setModifiedBy",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getModifiedBy());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value7 = oldCplModel.getModifiedDate();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setDefinitionName",
							new Class[] { String.class });

					String value8 = oldCplModel.getDefinitionName();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value9 = oldCplModel.getDescription();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setReportName",
							new Class[] { String.class });

					String value10 = oldCplModel.getReportName();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setReportParameters",
							new Class[] { String.class });

					String value11 = oldCplModel.getReportParameters();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setReportFormat",
							new Class[] { String.class });

					String value12 = oldCplModel.getReportFormat();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setDataSourceName",
							new Class[] { String.class });

					String value13 = oldCplModel.getDataSourceName();

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

		if (oldModelClassName.equals(RequestedReportClp.class.getName())) {
			RequestedReportClp oldCplModel = (RequestedReportClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.bi.report.model.impl.RequestedReportImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setRequestId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getRequestId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getCompanyId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getGroupId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getCreateDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value6 = oldCplModel.getModifiedDate();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setDefinitionId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getDefinitionId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setRequestStatus",
							new Class[] { String.class });

					String value8 = oldCplModel.getRequestStatus();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setScheduleRequest",
							new Class[] { Boolean.TYPE });

					Boolean value9 = new Boolean(oldCplModel.getScheduleRequest());

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
					"com.liferay.bi.report.model.impl.ReportDefinitionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					ReportDefinitionClp newModel = new ReportDefinitionClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getDefinitionId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setDefinitionId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCompanyId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getGroupId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getCreateDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value5);

					Method method6 = oldModelClass.getMethod("getModifiedBy");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setModifiedBy(value6.longValue());

					Method method7 = oldModelClass.getMethod("getModifiedDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value7);

					Method method8 = oldModelClass.getMethod(
							"getDefinitionName");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setDefinitionName(value8);

					Method method9 = oldModelClass.getMethod("getDescription");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value9);

					Method method10 = oldModelClass.getMethod("getReportName");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setReportName(value10);

					Method method11 = oldModelClass.getMethod(
							"getReportParameters");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setReportParameters(value11);

					Method method12 = oldModelClass.getMethod("getReportFormat");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setReportFormat(value12);

					Method method13 = oldModelClass.getMethod(
							"getDataSourceName");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setDataSourceName(value13);

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
					"com.liferay.bi.report.model.impl.RequestedReportImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					RequestedReportClp newModel = new RequestedReportClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getRequestId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setRequestId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getCompanyId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getGroupId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getCreateDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value5);

					Method method6 = oldModelClass.getMethod("getModifiedDate");

					Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value6);

					Method method7 = oldModelClass.getMethod("getDefinitionId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setDefinitionId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getRequestStatus");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setRequestStatus(value8);

					Method method9 = oldModelClass.getMethod(
							"getScheduleRequest");

					Boolean value9 = (Boolean)method9.invoke(oldModel,
							(Object[])null);

					newModel.setScheduleRequest(value9.booleanValue());

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