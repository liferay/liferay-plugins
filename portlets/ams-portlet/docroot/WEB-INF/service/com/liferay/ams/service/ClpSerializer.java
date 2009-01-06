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

package com.liferay.ams.service;

import com.liferay.ams.model.AssetCheckoutClp;
import com.liferay.ams.model.AssetDefinitionClp;
import com.liferay.ams.model.AssetEntryClp;
import com.liferay.ams.model.AssetTypeClp;

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

		if (oldModelClassName.equals(AssetCheckoutClp.class.getName())) {
			AssetCheckoutClp oldCplModel = (AssetCheckoutClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ams.model.impl.AssetCheckoutImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setAssetCheckoutId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getAssetCheckoutId());

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

					Method method6 = newModelClass.getMethod("setAssetEntryId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getAssetEntryId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setCheckOutDate",
							new Class[] { Date.class });

					Date value7 = oldCplModel.getCheckOutDate();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setExpectedCheckInDate",
							new Class[] { Date.class });

					Date value8 = oldCplModel.getExpectedCheckInDate();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setActualCheckInDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getActualCheckInDate();

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

		if (oldModelClassName.equals(AssetDefinitionClp.class.getName())) {
			AssetDefinitionClp oldCplModel = (AssetDefinitionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ams.model.impl.AssetDefinitionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setAssetDefinitionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getAssetDefinitionId());

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

					Method method7 = newModelClass.getMethod("setAssetTypeId",
							new Class[] { Long.TYPE });

					Long value7 = new Long(oldCplModel.getAssetTypeId());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setManufacturer",
							new Class[] { String.class });

					String value8 = oldCplModel.getManufacturer();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setModel",
							new Class[] { String.class });

					String value9 = oldCplModel.getModel();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setOrderDate",
							new Class[] { Date.class });

					Date value10 = oldCplModel.getOrderDate();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setQuantity",
							new Class[] { Integer.TYPE });

					Integer value11 = new Integer(oldCplModel.getQuantity());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setPrice",
							new Class[] { Double.TYPE });

					Double value12 = new Double(oldCplModel.getPrice());

					method12.invoke(newModel, value12);

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

		if (oldModelClassName.equals(AssetEntryClp.class.getName())) {
			AssetEntryClp oldCplModel = (AssetEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ams.model.impl.AssetEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setAssetEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getAssetEntryId());

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

					Method method6 = newModelClass.getMethod("setAssetDefinitionId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getAssetDefinitionId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setSerialNumber",
							new Class[] { String.class });

					String value7 = oldCplModel.getSerialNumber();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setInactiveDate",
							new Class[] { Date.class });

					Date value8 = oldCplModel.getInactiveDate();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setActive",
							new Class[] { Boolean.TYPE });

					Boolean value9 = new Boolean(oldCplModel.getActive());

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

		if (oldModelClassName.equals(AssetTypeClp.class.getName())) {
			AssetTypeClp oldCplModel = (AssetTypeClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.ams.model.impl.AssetTypeImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setAssetTypeId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getAssetTypeId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getGroupId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value2 = oldCplModel.getName();

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
					"com.liferay.ams.model.impl.AssetCheckoutImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					AssetCheckoutClp newModel = new AssetCheckoutClp();

					Method method0 = oldModelClass.getMethod(
							"getAssetCheckoutId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setAssetCheckoutId(value0.longValue());

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

					Method method6 = oldModelClass.getMethod("getAssetEntryId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setAssetEntryId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getCheckOutDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setCheckOutDate(value7);

					Method method8 = oldModelClass.getMethod(
							"getExpectedCheckInDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setExpectedCheckInDate(value8);

					Method method9 = oldModelClass.getMethod(
							"getActualCheckInDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setActualCheckInDate(value9);

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
					"com.liferay.ams.model.impl.AssetDefinitionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					AssetDefinitionClp newModel = new AssetDefinitionClp();

					Method method0 = oldModelClass.getMethod(
							"getAssetDefinitionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setAssetDefinitionId(value0.longValue());

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

					Method method7 = oldModelClass.getMethod("getAssetTypeId");

					Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

					newModel.setAssetTypeId(value7.longValue());

					Method method8 = oldModelClass.getMethod("getManufacturer");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setManufacturer(value8);

					Method method9 = oldModelClass.getMethod("getModel");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setModel(value9);

					Method method10 = oldModelClass.getMethod("getOrderDate");

					Date value10 = (Date)method10.invoke(oldModel,
							(Object[])null);

					newModel.setOrderDate(value10);

					Method method11 = oldModelClass.getMethod("getQuantity");

					Integer value11 = (Integer)method11.invoke(oldModel,
							(Object[])null);

					newModel.setQuantity(value11.intValue());

					Method method12 = oldModelClass.getMethod("getPrice");

					Double value12 = (Double)method12.invoke(oldModel,
							(Object[])null);

					newModel.setPrice(value12.doubleValue());

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
					"com.liferay.ams.model.impl.AssetEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					AssetEntryClp newModel = new AssetEntryClp();

					Method method0 = oldModelClass.getMethod("getAssetEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setAssetEntryId(value0.longValue());

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
							"getAssetDefinitionId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setAssetDefinitionId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getSerialNumber");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setSerialNumber(value7);

					Method method8 = oldModelClass.getMethod("getInactiveDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setInactiveDate(value8);

					Method method9 = oldModelClass.getMethod("getActive");

					Boolean value9 = (Boolean)method9.invoke(oldModel,
							(Object[])null);

					newModel.setActive(value9.booleanValue());

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

		if (oldModelClassName.equals("com.liferay.ams.model.impl.AssetTypeImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					AssetTypeClp newModel = new AssetTypeClp();

					Method method0 = oldModelClass.getMethod("getAssetTypeId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setAssetTypeId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getName");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setName(value2);

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