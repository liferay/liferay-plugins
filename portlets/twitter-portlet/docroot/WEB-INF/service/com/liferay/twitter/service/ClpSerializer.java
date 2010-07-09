/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.twitter.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import com.liferay.twitter.model.FeedClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static final String SERVLET_CONTEXT_NAME = "twitter-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(FeedClp.class.getName())) {
			FeedClp oldCplModel = (FeedClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.twitter.model.impl.FeedImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFeedId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFeedId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setTwitterUserId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getTwitterUserId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setTwitterScreenName",
							new Class[] { String.class });

					String value2 = oldCplModel.getTwitterScreenName();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value3 = oldCplModel.getCreateDate();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getModifiedDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setLastStatusId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getLastStatusId());

					method5.invoke(newModel, value5);

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
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals("com.liferay.twitter.model.impl.FeedImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FeedClp newModel = new FeedClp();

					Method method0 = oldModelClass.getMethod("getFeedId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFeedId(value0);

					Method method1 = oldModelClass.getMethod("getTwitterUserId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setTwitterUserId(value1);

					Method method2 = oldModelClass.getMethod(
							"getTwitterScreenName");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setTwitterScreenName(value2);

					Method method3 = oldModelClass.getMethod("getCreateDate");

					Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value3);

					Method method4 = oldModelClass.getMethod("getModifiedDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value4);

					Method method5 = oldModelClass.getMethod("getLastStatusId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setLastStatusId(value5);

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
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}