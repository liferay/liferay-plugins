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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import com.liferay.wsrp.model.WSRPConfiguredProducerClp;
import com.liferay.wsrp.model.WSRPConsumerRegistrationClp;
import com.liferay.wsrp.model.WSRPPortletClp;
import com.liferay.wsrp.model.WSRPProducerClp;

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

		if (oldModelClassName.equals(WSRPConfiguredProducerClp.class.getName())) {
			WSRPConfiguredProducerClp oldCplModel = (WSRPConfiguredProducerClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConfiguredProducerImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setConfiguredProducerId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getConfiguredProducerId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value1 = oldCplModel.getName();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setPortalId",
							new Class[] { String.class });

					String value2 = oldCplModel.getPortalId();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setNamespace",
							new Class[] { String.class });

					String value3 = oldCplModel.getNamespace();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setProducerURL",
							new Class[] { String.class });

					String value4 = oldCplModel.getProducerURL();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setProducerVersion",
							new Class[] { String.class });

					String value5 = oldCplModel.getProducerVersion();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setProducerMarkupURL",
							new Class[] { String.class });

					String value6 = oldCplModel.getProducerMarkupURL();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getStatus());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setRegistrationData",
							new Class[] { String.class });

					String value8 = oldCplModel.getRegistrationData();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setRegistrationContext",
							new Class[] { String.class });

					String value9 = oldCplModel.getRegistrationContext();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setServiceDescription",
							new Class[] { String.class });

					String value10 = oldCplModel.getServiceDescription();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setUserCategoryMapping",
							new Class[] { String.class });

					String value11 = oldCplModel.getUserCategoryMapping();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setCustomUserProfile",
							new Class[] { String.class });

					String value12 = oldCplModel.getCustomUserProfile();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setIdentityPropagationType",
							new Class[] { String.class });

					String value13 = oldCplModel.getIdentityPropagationType();

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setLifetimeTerminationTime",
							new Class[] { String.class });

					String value14 = oldCplModel.getLifetimeTerminationTime();

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setSdLastModified",
							new Class[] { Long.TYPE });

					Long value15 = new Long(oldCplModel.getSdLastModified());

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setEntityVersion",
							new Class[] { Integer.TYPE });

					Integer value16 = new Integer(oldCplModel.getEntityVersion());

					method16.invoke(newModel, value16);

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
					WSRPConsumerRegistrationClp.class.getName())) {
			WSRPConsumerRegistrationClp oldCplModel = (WSRPConsumerRegistrationClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPConsumerRegistrationImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setConsumerRegistrationId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getConsumerRegistrationId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setConsumerName",
							new Class[] { String.class });

					String value1 = oldCplModel.getConsumerName();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setStatus",
							new Class[] { Boolean.TYPE });

					Boolean value2 = new Boolean(oldCplModel.getStatus());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setRegistrationHandle",
							new Class[] { String.class });

					String value3 = oldCplModel.getRegistrationHandle();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setRegistrationData",
							new Class[] { String.class });

					String value4 = oldCplModel.getRegistrationData();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setLifetimeTerminationTime",
							new Class[] { String.class });

					String value5 = oldCplModel.getLifetimeTerminationTime();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setProducerKey",
							new Class[] { String.class });

					String value6 = oldCplModel.getProducerKey();

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

		if (oldModelClassName.equals(WSRPPortletClp.class.getName())) {
			WSRPPortletClp oldCplModel = (WSRPPortletClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPPortletImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setPortletId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getPortletId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value1 = oldCplModel.getName();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setChannelName",
							new Class[] { String.class });

					String value2 = oldCplModel.getChannelName();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value3 = oldCplModel.getTitle();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setShortTitle",
							new Class[] { String.class });

					String value4 = oldCplModel.getShortTitle();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setDisplayName",
							new Class[] { String.class });

					String value5 = oldCplModel.getDisplayName();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setKeywords",
							new Class[] { String.class });

					String value6 = oldCplModel.getKeywords();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getStatus());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setProducerEntityId",
							new Class[] { String.class });

					String value8 = oldCplModel.getProducerEntityId();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setConsumerId",
							new Class[] { String.class });

					String value9 = oldCplModel.getConsumerId();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setPortletHandle",
							new Class[] { String.class });

					String value10 = oldCplModel.getPortletHandle();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setMimeTypes",
							new Class[] { String.class });

					String value11 = oldCplModel.getMimeTypes();

					method11.invoke(newModel, value11);

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

		if (oldModelClassName.equals(WSRPProducerClp.class.getName())) {
			WSRPProducerClp oldCplModel = (WSRPProducerClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wsrp.model.impl.WSRPProducerImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setProducerId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getProducerId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setPortalId",
							new Class[] { String.class });

					String value1 = oldCplModel.getPortalId();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setStatus",
							new Class[] { Boolean.TYPE });

					Boolean value2 = new Boolean(oldCplModel.getStatus());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setNamespace",
							new Class[] { String.class });

					String value3 = oldCplModel.getNamespace();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setInstanceName",
							new Class[] { String.class });

					String value4 = oldCplModel.getInstanceName();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setRequiresRegistration",
							new Class[] { Boolean.TYPE });

					Boolean value5 = new Boolean(oldCplModel.getRequiresRegistration());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setSupportsInbandRegistration",
							new Class[] { Boolean.TYPE });

					Boolean value6 = new Boolean(oldCplModel.getSupportsInbandRegistration());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setVersion",
							new Class[] { String.class });

					String value7 = oldCplModel.getVersion();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setOfferedPortlets",
							new Class[] { String.class });

					String value8 = oldCplModel.getOfferedPortlets();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setProducerProfileMap",
							new Class[] { String.class });

					String value9 = oldCplModel.getProducerProfileMap();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setRegistrationProperties",
							new Class[] { String.class });

					String value10 = oldCplModel.getRegistrationProperties();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setRegistrationValidatorClass",
							new Class[] { String.class });

					String value11 = oldCplModel.getRegistrationValidatorClass();

					method11.invoke(newModel, value11);

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
					"com.liferay.wsrp.model.impl.WSRPConfiguredProducerImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPConfiguredProducerClp newModel = new WSRPConfiguredProducerClp();

					Method method0 = oldModelClass.getMethod(
							"getConfiguredProducerId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setConfiguredProducerId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getName");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setName(value1);

					Method method2 = oldModelClass.getMethod("getPortalId");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setPortalId(value2);

					Method method3 = oldModelClass.getMethod("getNamespace");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setNamespace(value3);

					Method method4 = oldModelClass.getMethod("getProducerURL");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setProducerURL(value4);

					Method method5 = oldModelClass.getMethod(
							"getProducerVersion");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setProducerVersion(value5);

					Method method6 = oldModelClass.getMethod(
							"getProducerMarkupURL");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setProducerMarkupURL(value6);

					Method method7 = oldModelClass.getMethod("getStatus");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value7.intValue());

					Method method8 = oldModelClass.getMethod(
							"getRegistrationData");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationData(value8);

					Method method9 = oldModelClass.getMethod(
							"getRegistrationContext");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationContext(value9);

					Method method10 = oldModelClass.getMethod(
							"getServiceDescription");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setServiceDescription(value10);

					Method method11 = oldModelClass.getMethod(
							"getUserCategoryMapping");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setUserCategoryMapping(value11);

					Method method12 = oldModelClass.getMethod(
							"getCustomUserProfile");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setCustomUserProfile(value12);

					Method method13 = oldModelClass.getMethod(
							"getIdentityPropagationType");

					String value13 = (String)method13.invoke(oldModel,
							(Object[])null);

					newModel.setIdentityPropagationType(value13);

					Method method14 = oldModelClass.getMethod(
							"getLifetimeTerminationTime");

					String value14 = (String)method14.invoke(oldModel,
							(Object[])null);

					newModel.setLifetimeTerminationTime(value14);

					Method method15 = oldModelClass.getMethod(
							"getSdLastModified");

					Long value15 = (Long)method15.invoke(oldModel,
							(Object[])null);

					newModel.setSdLastModified(value15.longValue());

					Method method16 = oldModelClass.getMethod(
							"getEntityVersion");

					Integer value16 = (Integer)method16.invoke(oldModel,
							(Object[])null);

					newModel.setEntityVersion(value16.intValue());

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
					"com.liferay.wsrp.model.impl.WSRPConsumerRegistrationImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPConsumerRegistrationClp newModel = new WSRPConsumerRegistrationClp();

					Method method0 = oldModelClass.getMethod(
							"getConsumerRegistrationId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setConsumerRegistrationId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getConsumerName");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setConsumerName(value1);

					Method method2 = oldModelClass.getMethod("getStatus");

					Boolean value2 = (Boolean)method2.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value2.booleanValue());

					Method method3 = oldModelClass.getMethod(
							"getRegistrationHandle");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationHandle(value3);

					Method method4 = oldModelClass.getMethod(
							"getRegistrationData");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationData(value4);

					Method method5 = oldModelClass.getMethod(
							"getLifetimeTerminationTime");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setLifetimeTerminationTime(value5);

					Method method6 = oldModelClass.getMethod("getProducerKey");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setProducerKey(value6);

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
					"com.liferay.wsrp.model.impl.WSRPPortletImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPPortletClp newModel = new WSRPPortletClp();

					Method method0 = oldModelClass.getMethod("getPortletId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setPortletId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getName");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setName(value1);

					Method method2 = oldModelClass.getMethod("getChannelName");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setChannelName(value2);

					Method method3 = oldModelClass.getMethod("getTitle");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value3);

					Method method4 = oldModelClass.getMethod("getShortTitle");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setShortTitle(value4);

					Method method5 = oldModelClass.getMethod("getDisplayName");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setDisplayName(value5);

					Method method6 = oldModelClass.getMethod("getKeywords");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setKeywords(value6);

					Method method7 = oldModelClass.getMethod("getStatus");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value7.intValue());

					Method method8 = oldModelClass.getMethod(
							"getProducerEntityId");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setProducerEntityId(value8);

					Method method9 = oldModelClass.getMethod("getConsumerId");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setConsumerId(value9);

					Method method10 = oldModelClass.getMethod(
							"getPortletHandle");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setPortletHandle(value10);

					Method method11 = oldModelClass.getMethod("getMimeTypes");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setMimeTypes(value11);

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
					"com.liferay.wsrp.model.impl.WSRPProducerImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WSRPProducerClp newModel = new WSRPProducerClp();

					Method method0 = oldModelClass.getMethod("getProducerId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setProducerId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getPortalId");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setPortalId(value1);

					Method method2 = oldModelClass.getMethod("getStatus");

					Boolean value2 = (Boolean)method2.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value2.booleanValue());

					Method method3 = oldModelClass.getMethod("getNamespace");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setNamespace(value3);

					Method method4 = oldModelClass.getMethod("getInstanceName");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setInstanceName(value4);

					Method method5 = oldModelClass.getMethod(
							"getRequiresRegistration");

					Boolean value5 = (Boolean)method5.invoke(oldModel,
							(Object[])null);

					newModel.setRequiresRegistration(value5.booleanValue());

					Method method6 = oldModelClass.getMethod(
							"getSupportsInbandRegistration");

					Boolean value6 = (Boolean)method6.invoke(oldModel,
							(Object[])null);

					newModel.setSupportsInbandRegistration(value6.booleanValue());

					Method method7 = oldModelClass.getMethod("getVersion");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value7);

					Method method8 = oldModelClass.getMethod(
							"getOfferedPortlets");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setOfferedPortlets(value8);

					Method method9 = oldModelClass.getMethod(
							"getProducerProfileMap");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setProducerProfileMap(value9);

					Method method10 = oldModelClass.getMethod(
							"getRegistrationProperties");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationProperties(value10);

					Method method11 = oldModelClass.getMethod(
							"getRegistrationValidatorClass");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setRegistrationValidatorClass(value11);

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