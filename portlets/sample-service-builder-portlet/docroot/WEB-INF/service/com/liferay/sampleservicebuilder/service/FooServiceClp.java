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

package com.liferay.sampleservicebuilder.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.model.BaseModel;

import com.liferay.sampleservicebuilder.model.FooClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FooServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FooServiceClp implements FooService {
	public FooServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(FooClp.class.getName())) {
			FooClp oldCplModel = (FooClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.sampleservicebuilder.model.impl.FooImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFooId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFooId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setField1",
							new Class[] { String.class });

					String value1 = oldCplModel.getField1();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setField2",
							new Class[] { Boolean.TYPE });

					Boolean value2 = new Boolean(oldCplModel.getField2());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setField3",
							new Class[] { Integer.TYPE });

					Integer value3 = new Integer(oldCplModel.getField3());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setField4",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getField4();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setField5",
							new Class[] { String.class });

					String value5 = oldCplModel.getField5();

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

		if (obj instanceof List) {
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
					"com.liferay.sampleservicebuilder.model.impl.FooImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FooClp newModel = new FooClp();

					Method method0 = oldModelClass.getMethod("getFooId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFooId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getField1");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setField1(value1);

					Method method2 = oldModelClass.getMethod("getField2");

					Boolean value2 = (Boolean)method2.invoke(oldModel,
							(Object[])null);

					newModel.setField2(value2.booleanValue());

					Method method3 = oldModelClass.getMethod("getField3");

					Integer value3 = (Integer)method3.invoke(oldModel,
							(Object[])null);

					newModel.setField3(value3.intValue());

					Method method4 = oldModelClass.getMethod("getField4");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setField4(value4);

					Method method5 = oldModelClass.getMethod("getField5");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setField5(value5);

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

		if (obj instanceof List) {
			return translateOutput((List)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FooServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}