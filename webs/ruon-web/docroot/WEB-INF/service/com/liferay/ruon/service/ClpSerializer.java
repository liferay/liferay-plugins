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

package com.liferay.ruon.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import com.liferay.ruon.model.NetworkClp;
import com.liferay.ruon.model.PresenceClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
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

		if (oldModelClassName.equals(NetworkClp.class.getName())) {
			NetworkClp oldCplModel = (NetworkClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ruon.model.impl.NetworkImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setNetworkId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getNetworkId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value1 = oldCplModel.getName();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setTtl",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getTtl());

					method2.invoke(newModel, value2);

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

		if (oldModelClassName.equals(PresenceClp.class.getName())) {
			PresenceClp oldCplModel = (PresenceClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ruon.model.impl.PresenceImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setPresenceId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getPresenceId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getUserId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getModifiedDate());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setNetworkId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getNetworkId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setOnline",
							new Class[] { Boolean.TYPE });

					Boolean value4 = new Boolean(oldCplModel.getOnline());

					method4.invoke(newModel, value4);

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

		if (oldModelClassName.equals("com.liferay.ruon.model.impl.NetworkImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					NetworkClp newModel = new NetworkClp();

					Method method0 = oldModelClass.getMethod("getNetworkId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setNetworkId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getName");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setName(value1);

					Method method2 = oldModelClass.getMethod("getTtl");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setTtl(value2.longValue());

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

		if (oldModelClassName.equals("com.liferay.ruon.model.impl.PresenceImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					PresenceClp newModel = new PresenceClp();

					Method method0 = oldModelClass.getMethod("getPresenceId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setPresenceId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getUserId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setUserId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2.longValue());

					Method method3 = oldModelClass.getMethod("getNetworkId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setNetworkId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getOnline");

					Boolean value4 = (Boolean)method4.invoke(oldModel,
							(Object[])null);

					newModel.setOnline(value4.booleanValue());

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