/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service;

import com.liferay.hr.model.HRAssetCheckoutClp;
import com.liferay.hr.model.HRAssetClp;
import com.liferay.hr.model.HRAssetDefinitionClp;
import com.liferay.hr.model.HRAssetProductClp;
import com.liferay.hr.model.HRAssetTypeClp;
import com.liferay.hr.model.HRAssetVendorClp;
import com.liferay.hr.model.HRBillabilityClp;
import com.liferay.hr.model.HRBranchClp;
import com.liferay.hr.model.HRClientClp;
import com.liferay.hr.model.HREmploymentTypeClp;
import com.liferay.hr.model.HRExpenseAccountClp;
import com.liferay.hr.model.HRExpenseClp;
import com.liferay.hr.model.HRExpenseCurrencyClp;
import com.liferay.hr.model.HRExpenseCurrencyConversionClp;
import com.liferay.hr.model.HRExpenseTypeClp;
import com.liferay.hr.model.HRHolidayClp;
import com.liferay.hr.model.HRJobTitleClp;
import com.liferay.hr.model.HROfficeClp;
import com.liferay.hr.model.HRProjectBillingRateClp;
import com.liferay.hr.model.HRProjectClp;
import com.liferay.hr.model.HRProjectRoleClp;
import com.liferay.hr.model.HRProjectStatusClp;
import com.liferay.hr.model.HRTaskClp;
import com.liferay.hr.model.HRTaskStatusClp;
import com.liferay.hr.model.HRTerminationTypeClp;
import com.liferay.hr.model.HRTimeOffClp;
import com.liferay.hr.model.HRTimeOffFrequencyTypeClp;
import com.liferay.hr.model.HRTimeOffPolicyClp;
import com.liferay.hr.model.HRTimeOffTypeClp;
import com.liferay.hr.model.HRTimeSheetClp;
import com.liferay.hr.model.HRTimeSheetDayClp;
import com.liferay.hr.model.HRTimeSheetHoursPerDayClp;
import com.liferay.hr.model.HRUserClp;
import com.liferay.hr.model.HRUserHistoryClp;
import com.liferay.hr.model.HRUserProjectClp;
import com.liferay.hr.model.HRUserTaskClp;
import com.liferay.hr.model.HRUserTimeOffClp;
import com.liferay.hr.model.HRWageTypeClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"hr-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"hr-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "hr-portlet";
			}

			return _servletContextName;
		}
	}

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(HRAssetClp.class.getName())) {
			return translateInputHRAsset(oldModel);
		}

		if (oldModelClassName.equals(HRAssetCheckoutClp.class.getName())) {
			return translateInputHRAssetCheckout(oldModel);
		}

		if (oldModelClassName.equals(HRAssetDefinitionClp.class.getName())) {
			return translateInputHRAssetDefinition(oldModel);
		}

		if (oldModelClassName.equals(HRAssetProductClp.class.getName())) {
			return translateInputHRAssetProduct(oldModel);
		}

		if (oldModelClassName.equals(HRAssetTypeClp.class.getName())) {
			return translateInputHRAssetType(oldModel);
		}

		if (oldModelClassName.equals(HRAssetVendorClp.class.getName())) {
			return translateInputHRAssetVendor(oldModel);
		}

		if (oldModelClassName.equals(HRBillabilityClp.class.getName())) {
			return translateInputHRBillability(oldModel);
		}

		if (oldModelClassName.equals(HRBranchClp.class.getName())) {
			return translateInputHRBranch(oldModel);
		}

		if (oldModelClassName.equals(HRClientClp.class.getName())) {
			return translateInputHRClient(oldModel);
		}

		if (oldModelClassName.equals(HREmploymentTypeClp.class.getName())) {
			return translateInputHREmploymentType(oldModel);
		}

		if (oldModelClassName.equals(HRExpenseClp.class.getName())) {
			return translateInputHRExpense(oldModel);
		}

		if (oldModelClassName.equals(HRExpenseAccountClp.class.getName())) {
			return translateInputHRExpenseAccount(oldModel);
		}

		if (oldModelClassName.equals(HRExpenseCurrencyClp.class.getName())) {
			return translateInputHRExpenseCurrency(oldModel);
		}

		if (oldModelClassName.equals(
					HRExpenseCurrencyConversionClp.class.getName())) {
			return translateInputHRExpenseCurrencyConversion(oldModel);
		}

		if (oldModelClassName.equals(HRExpenseTypeClp.class.getName())) {
			return translateInputHRExpenseType(oldModel);
		}

		if (oldModelClassName.equals(HRHolidayClp.class.getName())) {
			return translateInputHRHoliday(oldModel);
		}

		if (oldModelClassName.equals(HRJobTitleClp.class.getName())) {
			return translateInputHRJobTitle(oldModel);
		}

		if (oldModelClassName.equals(HROfficeClp.class.getName())) {
			return translateInputHROffice(oldModel);
		}

		if (oldModelClassName.equals(HRProjectClp.class.getName())) {
			return translateInputHRProject(oldModel);
		}

		if (oldModelClassName.equals(HRProjectBillingRateClp.class.getName())) {
			return translateInputHRProjectBillingRate(oldModel);
		}

		if (oldModelClassName.equals(HRProjectRoleClp.class.getName())) {
			return translateInputHRProjectRole(oldModel);
		}

		if (oldModelClassName.equals(HRProjectStatusClp.class.getName())) {
			return translateInputHRProjectStatus(oldModel);
		}

		if (oldModelClassName.equals(HRTaskClp.class.getName())) {
			return translateInputHRTask(oldModel);
		}

		if (oldModelClassName.equals(HRTaskStatusClp.class.getName())) {
			return translateInputHRTaskStatus(oldModel);
		}

		if (oldModelClassName.equals(HRTerminationTypeClp.class.getName())) {
			return translateInputHRTerminationType(oldModel);
		}

		if (oldModelClassName.equals(HRTimeOffClp.class.getName())) {
			return translateInputHRTimeOff(oldModel);
		}

		if (oldModelClassName.equals(HRTimeOffFrequencyTypeClp.class.getName())) {
			return translateInputHRTimeOffFrequencyType(oldModel);
		}

		if (oldModelClassName.equals(HRTimeOffPolicyClp.class.getName())) {
			return translateInputHRTimeOffPolicy(oldModel);
		}

		if (oldModelClassName.equals(HRTimeOffTypeClp.class.getName())) {
			return translateInputHRTimeOffType(oldModel);
		}

		if (oldModelClassName.equals(HRTimeSheetClp.class.getName())) {
			return translateInputHRTimeSheet(oldModel);
		}

		if (oldModelClassName.equals(HRTimeSheetDayClp.class.getName())) {
			return translateInputHRTimeSheetDay(oldModel);
		}

		if (oldModelClassName.equals(HRTimeSheetHoursPerDayClp.class.getName())) {
			return translateInputHRTimeSheetHoursPerDay(oldModel);
		}

		if (oldModelClassName.equals(HRUserClp.class.getName())) {
			return translateInputHRUser(oldModel);
		}

		if (oldModelClassName.equals(HRUserHistoryClp.class.getName())) {
			return translateInputHRUserHistory(oldModel);
		}

		if (oldModelClassName.equals(HRUserProjectClp.class.getName())) {
			return translateInputHRUserProject(oldModel);
		}

		if (oldModelClassName.equals(HRUserTaskClp.class.getName())) {
			return translateInputHRUserTask(oldModel);
		}

		if (oldModelClassName.equals(HRUserTimeOffClp.class.getName())) {
			return translateInputHRUserTimeOff(oldModel);
		}

		if (oldModelClassName.equals(HRWageTypeClp.class.getName())) {
			return translateInputHRWageType(oldModel);
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

	public static Object translateInputHRAsset(BaseModel<?> oldModel) {
		HRAssetClp oldCplModel = (HRAssetClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetId());

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

				Method method7 = newModelClass.getMethod("setHrAssetDefinitionId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrAssetDefinitionId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrAssetTypeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrAssetTypeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setSerialNumber",
						new Class[] { String.class });

				String value9 = oldCplModel.getSerialNumber();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setInactiveDate",
						new Class[] { Date.class });

				Date value10 = oldCplModel.getInactiveDate();

				method10.invoke(newModel, value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRAssetCheckout(BaseModel<?> oldModel) {
		HRAssetCheckoutClp oldCplModel = (HRAssetCheckoutClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetCheckoutImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetCheckoutId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetCheckoutId());

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

				Method method7 = newModelClass.getMethod("setHrAssetId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrAssetId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrUserId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setCheckoutDate",
						new Class[] { Date.class });

				Date value9 = oldCplModel.getCheckoutDate();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setExpectedCheckInDate",
						new Class[] { Date.class });

				Date value10 = oldCplModel.getExpectedCheckInDate();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setActualCheckInDate",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getActualCheckInDate();

				method11.invoke(newModel, value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRAssetDefinition(BaseModel<?> oldModel) {
		HRAssetDefinitionClp oldCplModel = (HRAssetDefinitionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetDefinitionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetDefinitionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetDefinitionId());

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

				Method method7 = newModelClass.getMethod("setHrAssetProductId",
						new Class[] { String.class });

				String value7 = oldCplModel.getHrAssetProductId();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrAssetTypeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrAssetTypeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrAssetVendorId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrAssetVendorId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setDefinitionNumber",
						new Class[] { String.class });

				String value10 = oldCplModel.getDefinitionNumber();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setOrderId",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getOrderId();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setOrderDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getOrderDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setQuantity",
						new Class[] { Integer.TYPE });

				Integer value13 = new Integer(oldCplModel.getQuantity());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setIndividualPrice",
						new Class[] { Double.TYPE });

				Double value14 = new Double(oldCplModel.getIndividualPrice());

				method14.invoke(newModel, value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRAssetProduct(BaseModel<?> oldModel) {
		HRAssetProductClp oldCplModel = (HRAssetProductClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetProductImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetProductId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetProductId());

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

				Method method7 = newModelClass.getMethod("setHrAssetVendorId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrAssetVendorId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRAssetType(BaseModel<?> oldModel) {
		HRAssetTypeClp oldCplModel = (HRAssetTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetTypeId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRAssetVendor(BaseModel<?> oldModel) {
		HRAssetVendorClp oldCplModel = (HRAssetVendorClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRAssetVendorImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrAssetVendorId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrAssetVendorId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRBillability(BaseModel<?> oldModel) {
		HRBillabilityClp oldCplModel = (HRBillabilityClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRBillabilityImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrBillabilityId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrBillabilityId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRBranch(BaseModel<?> oldModel) {
		HRBranchClp oldCplModel = (HRBranchClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRBranchImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrBranchId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrBranchId());

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

				Method method7 = newModelClass.getMethod("setOrganizationId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getOrganizationId());

				method7.invoke(newModel, value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRClient(BaseModel<?> oldModel) {
		HRClientClp oldCplModel = (HRClientClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRClientImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrClientId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrClientId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHREmploymentType(BaseModel<?> oldModel) {
		HREmploymentTypeClp oldCplModel = (HREmploymentTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HREmploymentTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrEmploymentTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrEmploymentTypeId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRExpense(BaseModel<?> oldModel) {
		HRExpenseClp oldCplModel = (HRExpenseClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRExpenseImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrExpenseId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrExpenseId());

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

				Method method7 = newModelClass.getMethod("setHrExpenseAccountId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrExpenseAccountId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrExpenseTypeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrExpenseTypeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrUserId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setExpenseDate",
						new Class[] { Date.class });

				Date value10 = oldCplModel.getExpenseDate();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setExpenseAmount",
						new Class[] { Double.TYPE });

				Double value11 = new Double(oldCplModel.getExpenseAmount());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setExpenseHRExpenseCurrencyId",
						new Class[] { Long.TYPE });

				Long value12 = new Long(oldCplModel.getExpenseHRExpenseCurrencyId());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setReimbursementAmount",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getReimbursementAmount());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setReimbursementHRExpenseCurrencyId",
						new Class[] { Long.TYPE });

				Long value14 = new Long(oldCplModel.getReimbursementHRExpenseCurrencyId());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value15 = new Integer(oldCplModel.getStatus());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value16 = new Long(oldCplModel.getStatusByUserId());

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value17 = oldCplModel.getStatusByUserName();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value18 = oldCplModel.getStatusDate();

				method18.invoke(newModel, value18);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRExpenseAccount(BaseModel<?> oldModel) {
		HRExpenseAccountClp oldCplModel = (HRExpenseAccountClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRExpenseAccountImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrExpenseAccountId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrExpenseAccountId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRExpenseCurrency(BaseModel<?> oldModel) {
		HRExpenseCurrencyClp oldCplModel = (HRExpenseCurrencyClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRExpenseCurrencyImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrExpenseCurrencyId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrExpenseCurrencyId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRExpenseCurrencyConversion(
		BaseModel<?> oldModel) {
		HRExpenseCurrencyConversionClp oldCplModel = (HRExpenseCurrencyConversionClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRExpenseCurrencyConversionImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrExpenseCurrencyConversionId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrExpenseCurrencyConversionId());

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

				Method method7 = newModelClass.getMethod("setFromHRExpenseCurrencyId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getFromHRExpenseCurrencyId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setToHRExpenseCurrencyId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getToHRExpenseCurrencyId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setConversionDate",
						new Class[] { Date.class });

				Date value9 = oldCplModel.getConversionDate();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setConversionValue",
						new Class[] { Double.TYPE });

				Double value10 = new Double(oldCplModel.getConversionValue());

				method10.invoke(newModel, value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRExpenseType(BaseModel<?> oldModel) {
		HRExpenseTypeClp oldCplModel = (HRExpenseTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRExpenseTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrExpenseTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrExpenseTypeId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRHoliday(BaseModel<?> oldModel) {
		HRHolidayClp oldCplModel = (HRHolidayClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRHolidayImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrHolidayId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrHolidayId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getDayOfYear());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setYear",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getYear());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setPaid",
						new Class[] { Boolean.TYPE });

				Boolean value11 = new Boolean(oldCplModel.getPaid());

				method11.invoke(newModel, value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRJobTitle(BaseModel<?> oldModel) {
		HRJobTitleClp oldCplModel = (HRJobTitleClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRJobTitleImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrJobTitleId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrJobTitleId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHROffice(BaseModel<?> oldModel) {
		HROfficeClp oldCplModel = (HROfficeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HROfficeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrOfficeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrOfficeId());

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

				Method method7 = newModelClass.getMethod("setOrganizationId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getOrganizationId());

				method7.invoke(newModel, value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRProject(BaseModel<?> oldModel) {
		HRProjectClp oldCplModel = (HRProjectClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRProjectImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrProjectId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrProjectId());

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

				Method method7 = newModelClass.getMethod("setHrClientId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrClientId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrProjectStatusId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrProjectStatusId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value9 = oldCplModel.getName();

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value10 = oldCplModel.getDescription();

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setEstimatedStartDate",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getEstimatedStartDate();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setEstimatedEndDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getEstimatedEndDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setEstimatedHours",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getEstimatedHours());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setEstimatedHoursCost",
						new Class[] { Double.TYPE });

				Double value14 = new Double(oldCplModel.getEstimatedHoursCost());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setEstimatedHoursCostCurrencyCode",
						new Class[] { String.class });

				String value15 = oldCplModel.getEstimatedHoursCostCurrencyCode();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setEstimatedExpenses",
						new Class[] { Double.TYPE });

				Double value16 = new Double(oldCplModel.getEstimatedExpenses());

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setEstimatedExpensesCurrencyCode",
						new Class[] { String.class });

				String value17 = oldCplModel.getEstimatedExpensesCurrencyCode();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setActualStartDate",
						new Class[] { Date.class });

				Date value18 = oldCplModel.getActualStartDate();

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setActualEndDate",
						new Class[] { Date.class });

				Date value19 = oldCplModel.getActualEndDate();

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setActualHours",
						new Class[] { Double.TYPE });

				Double value20 = new Double(oldCplModel.getActualHours());

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setActualHoursCost",
						new Class[] { Double.TYPE });

				Double value21 = new Double(oldCplModel.getActualHoursCost());

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setActualHoursCostCurrencyCode",
						new Class[] { String.class });

				String value22 = oldCplModel.getActualHoursCostCurrencyCode();

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setActualExpenses",
						new Class[] { Double.TYPE });

				Double value23 = new Double(oldCplModel.getActualExpenses());

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setActualExpensesCurrencyCode",
						new Class[] { Double.TYPE });

				Double value24 = new Double(oldCplModel.getActualExpensesCurrencyCode());

				method24.invoke(newModel, value24);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRProjectBillingRate(
		BaseModel<?> oldModel) {
		HRProjectBillingRateClp oldCplModel = (HRProjectBillingRateClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRProjectBillingRateImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrProjectBillingRateId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrProjectBillingRateId());

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

				Method method7 = newModelClass.getMethod("setHrProjectId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrProjectId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrProjectRoleId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrProjectRoleId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setRate",
						new Class[] { Double.TYPE });

				Double value9 = new Double(oldCplModel.getRate());

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRProjectRole(BaseModel<?> oldModel) {
		HRProjectRoleClp oldCplModel = (HRProjectRoleClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRProjectRoleImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrProjectRoleId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrProjectRoleId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRProjectStatus(BaseModel<?> oldModel) {
		HRProjectStatusClp oldCplModel = (HRProjectStatusClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRProjectStatusImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrProjectStatusId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrProjectStatusId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTask(BaseModel<?> oldModel) {
		HRTaskClp oldCplModel = (HRTaskClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTaskImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTaskId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTaskId());

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

				Method method7 = newModelClass.getMethod("setHrBillabilityId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrBillabilityId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrProjectId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrProjectId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrTaskStatusId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrTaskStatusId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setParentHRTaskId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getParentHRTaskId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value11 = oldCplModel.getName();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value12 = oldCplModel.getDescription();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setEstimatedStartDate",
						new Class[] { Date.class });

				Date value13 = oldCplModel.getEstimatedStartDate();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setEstimatedEndDate",
						new Class[] { Date.class });

				Date value14 = oldCplModel.getEstimatedEndDate();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setEstimatedHours",
						new Class[] { Double.TYPE });

				Double value15 = new Double(oldCplModel.getEstimatedHours());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setEstimatedHoursCost",
						new Class[] { Double.TYPE });

				Double value16 = new Double(oldCplModel.getEstimatedHoursCost());

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setEstimatedHoursCostCurrencyCode",
						new Class[] { String.class });

				String value17 = oldCplModel.getEstimatedHoursCostCurrencyCode();

				method17.invoke(newModel, value17);

				Method method18 = newModelClass.getMethod("setEstimatedExpenses",
						new Class[] { Double.TYPE });

				Double value18 = new Double(oldCplModel.getEstimatedExpenses());

				method18.invoke(newModel, value18);

				Method method19 = newModelClass.getMethod("setEstimatedExpensesCurrencyCode",
						new Class[] { String.class });

				String value19 = oldCplModel.getEstimatedExpensesCurrencyCode();

				method19.invoke(newModel, value19);

				Method method20 = newModelClass.getMethod("setActualStartDate",
						new Class[] { Date.class });

				Date value20 = oldCplModel.getActualStartDate();

				method20.invoke(newModel, value20);

				Method method21 = newModelClass.getMethod("setActualEndDate",
						new Class[] { Date.class });

				Date value21 = oldCplModel.getActualEndDate();

				method21.invoke(newModel, value21);

				Method method22 = newModelClass.getMethod("setActualHours",
						new Class[] { Double.TYPE });

				Double value22 = new Double(oldCplModel.getActualHours());

				method22.invoke(newModel, value22);

				Method method23 = newModelClass.getMethod("setActualHoursCost",
						new Class[] { Double.TYPE });

				Double value23 = new Double(oldCplModel.getActualHoursCost());

				method23.invoke(newModel, value23);

				Method method24 = newModelClass.getMethod("setActualHoursCostCurrencyCode",
						new Class[] { String.class });

				String value24 = oldCplModel.getActualHoursCostCurrencyCode();

				method24.invoke(newModel, value24);

				Method method25 = newModelClass.getMethod("setActualExpenses",
						new Class[] { Double.TYPE });

				Double value25 = new Double(oldCplModel.getActualExpenses());

				method25.invoke(newModel, value25);

				Method method26 = newModelClass.getMethod("setActualExpensesCurrencyCode",
						new Class[] { String.class });

				String value26 = oldCplModel.getActualExpensesCurrencyCode();

				method26.invoke(newModel, value26);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTaskStatus(BaseModel<?> oldModel) {
		HRTaskStatusClp oldCplModel = (HRTaskStatusClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTaskStatusImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTaskStatusId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTaskStatusId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTerminationType(BaseModel<?> oldModel) {
		HRTerminationTypeClp oldCplModel = (HRTerminationTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTerminationTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTerminationTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTerminationTypeId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeOff(BaseModel<?> oldModel) {
		HRTimeOffClp oldCplModel = (HRTimeOffClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeOffImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeOffId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeOffId());

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

				Method method7 = newModelClass.getMethod("setHrTimeOffTypeId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrTimeOffTypeId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrTimeSheetId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrTimeSheetId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrUserId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setStartDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getStartDayOfYear());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setEndDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getEndDayOfYear());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setYear",
						new Class[] { Integer.TYPE });

				Integer value12 = new Integer(oldCplModel.getYear());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setTotalHours",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getTotalHours());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value14 = new Integer(oldCplModel.getStatus());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value15 = new Long(oldCplModel.getStatusByUserId());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value16 = oldCplModel.getStatusByUserName();

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value17 = oldCplModel.getStatusDate();

				method17.invoke(newModel, value17);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeOffFrequencyType(
		BaseModel<?> oldModel) {
		HRTimeOffFrequencyTypeClp oldCplModel = (HRTimeOffFrequencyTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeOffFrequencyTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeOffFrequencyTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeOffFrequencyTypeId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeOffPolicy(BaseModel<?> oldModel) {
		HRTimeOffPolicyClp oldCplModel = (HRTimeOffPolicyClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeOffPolicyImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeOffPolicyId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeOffPolicyId());

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

				Method method7 = newModelClass.getMethod("setHrTimeOffTypeId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrTimeOffTypeId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrUserId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setAccrueHRTimeOffFrequencyTypeId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getAccrueHRTimeOffFrequencyTypeId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setResetHRTimeOffFrequencyTypeId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getResetHRTimeOffFrequencyTypeId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setEffectiveDate",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getEffectiveDate();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setInactive",
						new Class[] { Boolean.TYPE });

				Boolean value12 = new Boolean(oldCplModel.getInactive());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setHoursAllowed",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getHoursAllowed());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setHoursBaseAmount",
						new Class[] { Double.TYPE });

				Double value14 = new Double(oldCplModel.getHoursBaseAmount());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setHoursToAccrue",
						new Class[] { Double.TYPE });

				Double value15 = new Double(oldCplModel.getHoursToAccrue());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setCarryOverHoursAllowed",
						new Class[] { Double.TYPE });

				Double value16 = new Double(oldCplModel.getCarryOverHoursAllowed());

				method16.invoke(newModel, value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeOffType(BaseModel<?> oldModel) {
		HRTimeOffTypeClp oldCplModel = (HRTimeOffTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeOffTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeOffTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeOffTypeId());

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

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value8 = oldCplModel.getDescription();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeSheet(BaseModel<?> oldModel) {
		HRTimeSheetClp oldCplModel = (HRTimeSheetClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeSheetImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeSheetId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeSheetId());

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

				Method method7 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrUserId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setStartDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value8 = new Integer(oldCplModel.getStartDayOfYear());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setEndDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getEndDayOfYear());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setYear",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getYear());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setStatus",
						new Class[] { Integer.TYPE });

				Integer value11 = new Integer(oldCplModel.getStatus());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setStatusByUserId",
						new Class[] { Long.TYPE });

				Long value12 = new Long(oldCplModel.getStatusByUserId());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setStatusByUserName",
						new Class[] { String.class });

				String value13 = oldCplModel.getStatusByUserName();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setStatusDate",
						new Class[] { Date.class });

				Date value14 = oldCplModel.getStatusDate();

				method14.invoke(newModel, value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeSheetDay(BaseModel<?> oldModel) {
		HRTimeSheetDayClp oldCplModel = (HRTimeSheetDayClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeSheetDayImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeSheetDayId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeSheetDayId());

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

				Method method7 = newModelClass.getMethod("setHrTimeSheetId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrTimeSheetId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrUserId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDayOfYear",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getDayOfYear());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setYear",
						new Class[] { Integer.TYPE });

				Integer value10 = new Integer(oldCplModel.getYear());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setHours",
						new Class[] { Double.TYPE });

				Double value11 = new Double(oldCplModel.getHours());

				method11.invoke(newModel, value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRTimeSheetHoursPerDay(
		BaseModel<?> oldModel) {
		HRTimeSheetHoursPerDayClp oldCplModel = (HRTimeSheetHoursPerDayClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRTimeSheetHoursPerDayImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrTimeSheetHoursPerDayId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrTimeSheetHoursPerDayId());

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

				Method method7 = newModelClass.getMethod("setHrOfficeId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrOfficeId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHoursPerDay",
						new Class[] { Double.TYPE });

				Double value8 = new Double(oldCplModel.getHoursPerDay());

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRUser(BaseModel<?> oldModel) {
		HRUserClp oldCplModel = (HRUserClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRUserImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrUserId());

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

				Method method7 = newModelClass.getMethod("setHrEmploymentTypeId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrEmploymentTypeId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrJobTitleId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrJobTitleId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrOfficeId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrOfficeId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setHrTerminationTypeId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getHrTerminationTypeId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setHrWageTypeId",
						new Class[] { Long.TYPE });

				Long value11 = new Long(oldCplModel.getHrWageTypeId());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setHireDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getHireDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setTerminationDate",
						new Class[] { Date.class });

				Date value13 = oldCplModel.getTerminationDate();

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setWageAmount",
						new Class[] { Double.TYPE });

				Double value14 = new Double(oldCplModel.getWageAmount());

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setWageCurrencyCode",
						new Class[] { String.class });

				String value15 = oldCplModel.getWageCurrencyCode();

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setBenefitsExempt",
						new Class[] { Boolean.TYPE });

				Boolean value16 = new Boolean(oldCplModel.getBenefitsExempt());

				method16.invoke(newModel, value16);

				Method method17 = newModelClass.getMethod("setOvertimeExempt",
						new Class[] { Boolean.TYPE });

				Boolean value17 = new Boolean(oldCplModel.getOvertimeExempt());

				method17.invoke(newModel, value17);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRUserHistory(BaseModel<?> oldModel) {
		HRUserHistoryClp oldCplModel = (HRUserHistoryClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRUserHistoryImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrUserHistoryId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrUserHistoryId());

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

				Method method6 = newModelClass.getMethod("setHrEmploymentTypeId",
						new Class[] { Long.TYPE });

				Long value6 = new Long(oldCplModel.getHrEmploymentTypeId());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setHrJobTitleId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrJobTitleId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrOfficeId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrOfficeId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrTerminationTypeId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrTerminationTypeId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setHrWageTypeId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getHrWageTypeId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setHireDate",
						new Class[] { Date.class });

				Date value11 = oldCplModel.getHireDate();

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setTerminationDate",
						new Class[] { Date.class });

				Date value12 = oldCplModel.getTerminationDate();

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setWageAmount",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getWageAmount());

				method13.invoke(newModel, value13);

				Method method14 = newModelClass.getMethod("setWageCurrencyCode",
						new Class[] { String.class });

				String value14 = oldCplModel.getWageCurrencyCode();

				method14.invoke(newModel, value14);

				Method method15 = newModelClass.getMethod("setBenefitsExempt",
						new Class[] { Boolean.TYPE });

				Boolean value15 = new Boolean(oldCplModel.getBenefitsExempt());

				method15.invoke(newModel, value15);

				Method method16 = newModelClass.getMethod("setOvertimeExempt",
						new Class[] { Boolean.TYPE });

				Boolean value16 = new Boolean(oldCplModel.getOvertimeExempt());

				method16.invoke(newModel, value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRUserProject(BaseModel<?> oldModel) {
		HRUserProjectClp oldCplModel = (HRUserProjectClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRUserProjectImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrUserProjectId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrUserProjectId());

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

				Method method7 = newModelClass.getMethod("setHrProjectBillingRateId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrProjectBillingRateId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrProjectId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrProjectId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrProjectRoleId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrProjectRoleId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getHrUserId());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setActualRate",
						new Class[] { Double.TYPE });

				Double value11 = new Double(oldCplModel.getActualRate());

				method11.invoke(newModel, value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRUserTask(BaseModel<?> oldModel) {
		HRUserTaskClp oldCplModel = (HRUserTaskClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRUserTaskImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrUserTaskId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrUserTaskId());

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

				Method method7 = newModelClass.getMethod("setHrBillabilityId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrBillabilityId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrTaskId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrTaskId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setHrTimesheetId",
						new Class[] { Long.TYPE });

				Long value9 = new Long(oldCplModel.getHrTimesheetId());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value10 = new Long(oldCplModel.getHrUserId());

				method10.invoke(newModel, value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRUserTimeOff(BaseModel<?> oldModel) {
		HRUserTimeOffClp oldCplModel = (HRUserTimeOffClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRUserTimeOffImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrUserTimeOffId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrUserTimeOffId());

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

				Method method7 = newModelClass.getMethod("setHrTimeOffTypeId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getHrTimeOffTypeId());

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setHrUserId",
						new Class[] { Long.TYPE });

				Long value8 = new Long(oldCplModel.getHrUserId());

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setYear",
						new Class[] { Integer.TYPE });

				Integer value9 = new Integer(oldCplModel.getYear());

				method9.invoke(newModel, value9);

				Method method10 = newModelClass.getMethod("setHoursAllowed",
						new Class[] { Double.TYPE });

				Double value10 = new Double(oldCplModel.getHoursAllowed());

				method10.invoke(newModel, value10);

				Method method11 = newModelClass.getMethod("setHoursAccrued",
						new Class[] { Double.TYPE });

				Double value11 = new Double(oldCplModel.getHoursAccrued());

				method11.invoke(newModel, value11);

				Method method12 = newModelClass.getMethod("setHoursCarriedOver",
						new Class[] { Double.TYPE });

				Double value12 = new Double(oldCplModel.getHoursCarriedOver());

				method12.invoke(newModel, value12);

				Method method13 = newModelClass.getMethod("setHoursUsed",
						new Class[] { Double.TYPE });

				Double value13 = new Double(oldCplModel.getHoursUsed());

				method13.invoke(newModel, value13);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputHRWageType(BaseModel<?> oldModel) {
		HRWageTypeClp oldCplModel = (HRWageTypeClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.hr.model.impl.HRWageTypeImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setHrWageTypeId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getHrWageTypeId());

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

				Method method7 = newModelClass.getMethod("setCode",
						new Class[] { String.class });

				String value7 = oldCplModel.getCode();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value8 = oldCplModel.getName();

				method8.invoke(newModel, value8);

				Method method9 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value9 = oldCplModel.getDescription();

				method9.invoke(newModel, value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
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

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRAssetImpl")) {
			return translateOutputHRAsset(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRAssetCheckoutImpl")) {
			return translateOutputHRAssetCheckout(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRAssetDefinitionImpl")) {
			return translateOutputHRAssetDefinition(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRAssetProductImpl")) {
			return translateOutputHRAssetProduct(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRAssetTypeImpl")) {
			return translateOutputHRAssetType(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRAssetVendorImpl")) {
			return translateOutputHRAssetVendor(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRBillabilityImpl")) {
			return translateOutputHRBillability(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRBranchImpl")) {
			return translateOutputHRBranch(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRClientImpl")) {
			return translateOutputHRClient(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HREmploymentTypeImpl")) {
			return translateOutputHREmploymentType(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRExpenseImpl")) {
			return translateOutputHRExpense(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRExpenseAccountImpl")) {
			return translateOutputHRExpenseAccount(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRExpenseCurrencyImpl")) {
			return translateOutputHRExpenseCurrency(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRExpenseCurrencyConversionImpl")) {
			return translateOutputHRExpenseCurrencyConversion(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRExpenseTypeImpl")) {
			return translateOutputHRExpenseType(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRHolidayImpl")) {
			return translateOutputHRHoliday(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRJobTitleImpl")) {
			return translateOutputHRJobTitle(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HROfficeImpl")) {
			return translateOutputHROffice(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRProjectImpl")) {
			return translateOutputHRProject(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRProjectBillingRateImpl")) {
			return translateOutputHRProjectBillingRate(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRProjectRoleImpl")) {
			return translateOutputHRProjectRole(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRProjectStatusImpl")) {
			return translateOutputHRProjectStatus(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRTaskImpl")) {
			return translateOutputHRTask(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTaskStatusImpl")) {
			return translateOutputHRTaskStatus(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTerminationTypeImpl")) {
			return translateOutputHRTerminationType(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRTimeOffImpl")) {
			return translateOutputHRTimeOff(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeOffFrequencyTypeImpl")) {
			return translateOutputHRTimeOffFrequencyType(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeOffPolicyImpl")) {
			return translateOutputHRTimeOffPolicy(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeOffTypeImpl")) {
			return translateOutputHRTimeOffType(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeSheetImpl")) {
			return translateOutputHRTimeSheet(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeSheetDayImpl")) {
			return translateOutputHRTimeSheetDay(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRTimeSheetHoursPerDayImpl")) {
			return translateOutputHRTimeSheetHoursPerDay(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRUserImpl")) {
			return translateOutputHRUser(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRUserHistoryImpl")) {
			return translateOutputHRUserHistory(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRUserProjectImpl")) {
			return translateOutputHRUserProject(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRUserTaskImpl")) {
			return translateOutputHRUserTask(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.hr.model.impl.HRUserTimeOffImpl")) {
			return translateOutputHRUserTimeOff(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.hr.model.impl.HRWageTypeImpl")) {
			return translateOutputHRWageType(oldModel);
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

	public static Object translateOutputHRAsset(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetClp newModel = new HRAssetClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrAssetId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod(
						"getHrAssetDefinitionId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrAssetDefinitionId(value7);

				Method method8 = oldModelClass.getMethod("getHrAssetTypeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrAssetTypeId(value8);

				Method method9 = oldModelClass.getMethod("getSerialNumber");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setSerialNumber(value9);

				Method method10 = oldModelClass.getMethod("getInactiveDate");

				Date value10 = (Date)method10.invoke(oldModel, (Object[])null);

				newModel.setInactiveDate(value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRAssetCheckout(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetCheckoutClp newModel = new HRAssetCheckoutClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrAssetCheckoutId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetCheckoutId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrAssetId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrAssetId(value7);

				Method method8 = oldModelClass.getMethod("getHrUserId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value8);

				Method method9 = oldModelClass.getMethod("getCheckoutDate");

				Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

				newModel.setCheckoutDate(value9);

				Method method10 = oldModelClass.getMethod(
						"getExpectedCheckInDate");

				Date value10 = (Date)method10.invoke(oldModel, (Object[])null);

				newModel.setExpectedCheckInDate(value10);

				Method method11 = oldModelClass.getMethod(
						"getActualCheckInDate");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setActualCheckInDate(value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRAssetDefinition(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetDefinitionClp newModel = new HRAssetDefinitionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrAssetDefinitionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetDefinitionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrAssetProductId");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setHrAssetProductId(value7);

				Method method8 = oldModelClass.getMethod("getHrAssetTypeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrAssetTypeId(value8);

				Method method9 = oldModelClass.getMethod("getHrAssetVendorId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrAssetVendorId(value9);

				Method method10 = oldModelClass.getMethod("getDefinitionNumber");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setDefinitionNumber(value10);

				Method method11 = oldModelClass.getMethod("getOrderId");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setOrderId(value11);

				Method method12 = oldModelClass.getMethod("getOrderDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setOrderDate(value12);

				Method method13 = oldModelClass.getMethod("getQuantity");

				Integer value13 = (Integer)method13.invoke(oldModel,
						(Object[])null);

				newModel.setQuantity(value13);

				Method method14 = oldModelClass.getMethod("getIndividualPrice");

				Double value14 = (Double)method14.invoke(oldModel,
						(Object[])null);

				newModel.setIndividualPrice(value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRAssetProduct(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetProductClp newModel = new HRAssetProductClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrAssetProductId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetProductId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrAssetVendorId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrAssetVendorId(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRAssetType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetTypeClp newModel = new HRAssetTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrAssetTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRAssetVendor(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRAssetVendorClp newModel = new HRAssetVendorClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrAssetVendorId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrAssetVendorId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRBillability(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRBillabilityClp newModel = new HRBillabilityClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrBillabilityId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrBillabilityId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRBranch(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRBranchClp newModel = new HRBranchClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrBranchId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrBranchId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getOrganizationId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setOrganizationId(value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRClient(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRClientClp newModel = new HRClientClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrClientId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrClientId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHREmploymentType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HREmploymentTypeClp newModel = new HREmploymentTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrEmploymentTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrEmploymentTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRExpense(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRExpenseClp newModel = new HRExpenseClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrExpenseId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod(
						"getHrExpenseAccountId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseAccountId(value7);

				Method method8 = oldModelClass.getMethod("getHrExpenseTypeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseTypeId(value8);

				Method method9 = oldModelClass.getMethod("getHrUserId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value9);

				Method method10 = oldModelClass.getMethod("getExpenseDate");

				Date value10 = (Date)method10.invoke(oldModel, (Object[])null);

				newModel.setExpenseDate(value10);

				Method method11 = oldModelClass.getMethod("getExpenseAmount");

				Double value11 = (Double)method11.invoke(oldModel,
						(Object[])null);

				newModel.setExpenseAmount(value11);

				Method method12 = oldModelClass.getMethod(
						"getExpenseHRExpenseCurrencyId");

				Long value12 = (Long)method12.invoke(oldModel, (Object[])null);

				newModel.setExpenseHRExpenseCurrencyId(value12);

				Method method13 = oldModelClass.getMethod(
						"getReimbursementAmount");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setReimbursementAmount(value13);

				Method method14 = oldModelClass.getMethod(
						"getReimbursementHRExpenseCurrencyId");

				Long value14 = (Long)method14.invoke(oldModel, (Object[])null);

				newModel.setReimbursementHRExpenseCurrencyId(value14);

				Method method15 = oldModelClass.getMethod("getStatus");

				Integer value15 = (Integer)method15.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value15);

				Method method16 = oldModelClass.getMethod("getStatusByUserId");

				Long value16 = (Long)method16.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value16);

				Method method17 = oldModelClass.getMethod("getStatusByUserName");

				String value17 = (String)method17.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value17);

				Method method18 = oldModelClass.getMethod("getStatusDate");

				Date value18 = (Date)method18.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value18);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRExpenseAccount(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRExpenseAccountClp newModel = new HRExpenseAccountClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrExpenseAccountId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseAccountId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRExpenseCurrency(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRExpenseCurrencyClp newModel = new HRExpenseCurrencyClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrExpenseCurrencyId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseCurrencyId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRExpenseCurrencyConversion(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRExpenseCurrencyConversionClp newModel = new HRExpenseCurrencyConversionClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrExpenseCurrencyConversionId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseCurrencyConversionId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod(
						"getFromHRExpenseCurrencyId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setFromHRExpenseCurrencyId(value7);

				Method method8 = oldModelClass.getMethod(
						"getToHRExpenseCurrencyId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setToHRExpenseCurrencyId(value8);

				Method method9 = oldModelClass.getMethod("getConversionDate");

				Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

				newModel.setConversionDate(value9);

				Method method10 = oldModelClass.getMethod("getConversionValue");

				Double value10 = (Double)method10.invoke(oldModel,
						(Object[])null);

				newModel.setConversionValue(value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRExpenseType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRExpenseTypeClp newModel = new HRExpenseTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrExpenseTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrExpenseTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRHoliday(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRHolidayClp newModel = new HRHolidayClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrHolidayId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrHolidayId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				Method method9 = oldModelClass.getMethod("getDayOfYear");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setDayOfYear(value9);

				Method method10 = oldModelClass.getMethod("getYear");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setYear(value10);

				Method method11 = oldModelClass.getMethod("getPaid");

				Boolean value11 = (Boolean)method11.invoke(oldModel,
						(Object[])null);

				newModel.setPaid(value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRJobTitle(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRJobTitleClp newModel = new HRJobTitleClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrJobTitleId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrJobTitleId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHROffice(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HROfficeClp newModel = new HROfficeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrOfficeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrOfficeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getOrganizationId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setOrganizationId(value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRProject(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRProjectClp newModel = new HRProjectClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrProjectId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrProjectId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrClientId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrClientId(value7);

				Method method8 = oldModelClass.getMethod("getHrProjectStatusId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrProjectStatusId(value8);

				Method method9 = oldModelClass.getMethod("getName");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setName(value9);

				Method method10 = oldModelClass.getMethod("getDescription");

				String value10 = (String)method10.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value10);

				Method method11 = oldModelClass.getMethod(
						"getEstimatedStartDate");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setEstimatedStartDate(value11);

				Method method12 = oldModelClass.getMethod("getEstimatedEndDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setEstimatedEndDate(value12);

				Method method13 = oldModelClass.getMethod("getEstimatedHours");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHours(value13);

				Method method14 = oldModelClass.getMethod(
						"getEstimatedHoursCost");

				Double value14 = (Double)method14.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHoursCost(value14);

				Method method15 = oldModelClass.getMethod(
						"getEstimatedHoursCostCurrencyCode");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHoursCostCurrencyCode(value15);

				Method method16 = oldModelClass.getMethod(
						"getEstimatedExpenses");

				Double value16 = (Double)method16.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedExpenses(value16);

				Method method17 = oldModelClass.getMethod(
						"getEstimatedExpensesCurrencyCode");

				String value17 = (String)method17.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedExpensesCurrencyCode(value17);

				Method method18 = oldModelClass.getMethod("getActualStartDate");

				Date value18 = (Date)method18.invoke(oldModel, (Object[])null);

				newModel.setActualStartDate(value18);

				Method method19 = oldModelClass.getMethod("getActualEndDate");

				Date value19 = (Date)method19.invoke(oldModel, (Object[])null);

				newModel.setActualEndDate(value19);

				Method method20 = oldModelClass.getMethod("getActualHours");

				Double value20 = (Double)method20.invoke(oldModel,
						(Object[])null);

				newModel.setActualHours(value20);

				Method method21 = oldModelClass.getMethod("getActualHoursCost");

				Double value21 = (Double)method21.invoke(oldModel,
						(Object[])null);

				newModel.setActualHoursCost(value21);

				Method method22 = oldModelClass.getMethod(
						"getActualHoursCostCurrencyCode");

				String value22 = (String)method22.invoke(oldModel,
						(Object[])null);

				newModel.setActualHoursCostCurrencyCode(value22);

				Method method23 = oldModelClass.getMethod("getActualExpenses");

				Double value23 = (Double)method23.invoke(oldModel,
						(Object[])null);

				newModel.setActualExpenses(value23);

				Method method24 = oldModelClass.getMethod(
						"getActualExpensesCurrencyCode");

				Double value24 = (Double)method24.invoke(oldModel,
						(Object[])null);

				newModel.setActualExpensesCurrencyCode(value24);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRProjectBillingRate(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRProjectBillingRateClp newModel = new HRProjectBillingRateClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrProjectBillingRateId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrProjectBillingRateId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrProjectId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrProjectId(value7);

				Method method8 = oldModelClass.getMethod("getHrProjectRoleId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrProjectRoleId(value8);

				Method method9 = oldModelClass.getMethod("getRate");

				Double value9 = (Double)method9.invoke(oldModel, (Object[])null);

				newModel.setRate(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRProjectRole(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRProjectRoleClp newModel = new HRProjectRoleClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrProjectRoleId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrProjectRoleId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRProjectStatus(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRProjectStatusClp newModel = new HRProjectStatusClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrProjectStatusId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrProjectStatusId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTask(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTaskClp newModel = new HRTaskClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTaskId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTaskId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrBillabilityId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrBillabilityId(value7);

				Method method8 = oldModelClass.getMethod("getHrProjectId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrProjectId(value8);

				Method method9 = oldModelClass.getMethod("getHrTaskStatusId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrTaskStatusId(value9);

				Method method10 = oldModelClass.getMethod("getParentHRTaskId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setParentHRTaskId(value10);

				Method method11 = oldModelClass.getMethod("getName");

				String value11 = (String)method11.invoke(oldModel,
						(Object[])null);

				newModel.setName(value11);

				Method method12 = oldModelClass.getMethod("getDescription");

				String value12 = (String)method12.invoke(oldModel,
						(Object[])null);

				newModel.setDescription(value12);

				Method method13 = oldModelClass.getMethod(
						"getEstimatedStartDate");

				Date value13 = (Date)method13.invoke(oldModel, (Object[])null);

				newModel.setEstimatedStartDate(value13);

				Method method14 = oldModelClass.getMethod("getEstimatedEndDate");

				Date value14 = (Date)method14.invoke(oldModel, (Object[])null);

				newModel.setEstimatedEndDate(value14);

				Method method15 = oldModelClass.getMethod("getEstimatedHours");

				Double value15 = (Double)method15.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHours(value15);

				Method method16 = oldModelClass.getMethod(
						"getEstimatedHoursCost");

				Double value16 = (Double)method16.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHoursCost(value16);

				Method method17 = oldModelClass.getMethod(
						"getEstimatedHoursCostCurrencyCode");

				String value17 = (String)method17.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedHoursCostCurrencyCode(value17);

				Method method18 = oldModelClass.getMethod(
						"getEstimatedExpenses");

				Double value18 = (Double)method18.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedExpenses(value18);

				Method method19 = oldModelClass.getMethod(
						"getEstimatedExpensesCurrencyCode");

				String value19 = (String)method19.invoke(oldModel,
						(Object[])null);

				newModel.setEstimatedExpensesCurrencyCode(value19);

				Method method20 = oldModelClass.getMethod("getActualStartDate");

				Date value20 = (Date)method20.invoke(oldModel, (Object[])null);

				newModel.setActualStartDate(value20);

				Method method21 = oldModelClass.getMethod("getActualEndDate");

				Date value21 = (Date)method21.invoke(oldModel, (Object[])null);

				newModel.setActualEndDate(value21);

				Method method22 = oldModelClass.getMethod("getActualHours");

				Double value22 = (Double)method22.invoke(oldModel,
						(Object[])null);

				newModel.setActualHours(value22);

				Method method23 = oldModelClass.getMethod("getActualHoursCost");

				Double value23 = (Double)method23.invoke(oldModel,
						(Object[])null);

				newModel.setActualHoursCost(value23);

				Method method24 = oldModelClass.getMethod(
						"getActualHoursCostCurrencyCode");

				String value24 = (String)method24.invoke(oldModel,
						(Object[])null);

				newModel.setActualHoursCostCurrencyCode(value24);

				Method method25 = oldModelClass.getMethod("getActualExpenses");

				Double value25 = (Double)method25.invoke(oldModel,
						(Object[])null);

				newModel.setActualExpenses(value25);

				Method method26 = oldModelClass.getMethod(
						"getActualExpensesCurrencyCode");

				String value26 = (String)method26.invoke(oldModel,
						(Object[])null);

				newModel.setActualExpensesCurrencyCode(value26);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTaskStatus(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTaskStatusClp newModel = new HRTaskStatusClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTaskStatusId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTaskStatusId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTerminationType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTerminationTypeClp newModel = new HRTerminationTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrTerminationTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTerminationTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeOff(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeOffClp newModel = new HRTimeOffClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTimeOffId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrTimeOffTypeId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffTypeId(value7);

				Method method8 = oldModelClass.getMethod("getHrTimeSheetId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrTimeSheetId(value8);

				Method method9 = oldModelClass.getMethod("getHrUserId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value9);

				Method method10 = oldModelClass.getMethod("getStartDayOfYear");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setStartDayOfYear(value10);

				Method method11 = oldModelClass.getMethod("getEndDayOfYear");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setEndDayOfYear(value11);

				Method method12 = oldModelClass.getMethod("getYear");

				Integer value12 = (Integer)method12.invoke(oldModel,
						(Object[])null);

				newModel.setYear(value12);

				Method method13 = oldModelClass.getMethod("getTotalHours");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setTotalHours(value13);

				Method method14 = oldModelClass.getMethod("getStatus");

				Integer value14 = (Integer)method14.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value14);

				Method method15 = oldModelClass.getMethod("getStatusByUserId");

				Long value15 = (Long)method15.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value15);

				Method method16 = oldModelClass.getMethod("getStatusByUserName");

				String value16 = (String)method16.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value16);

				Method method17 = oldModelClass.getMethod("getStatusDate");

				Date value17 = (Date)method17.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value17);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeOffFrequencyType(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeOffFrequencyTypeClp newModel = new HRTimeOffFrequencyTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrTimeOffFrequencyTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffFrequencyTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeOffPolicy(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeOffPolicyClp newModel = new HRTimeOffPolicyClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTimeOffPolicyId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffPolicyId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrTimeOffTypeId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffTypeId(value7);

				Method method8 = oldModelClass.getMethod("getHrUserId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value8);

				Method method9 = oldModelClass.getMethod(
						"getAccrueHRTimeOffFrequencyTypeId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setAccrueHRTimeOffFrequencyTypeId(value9);

				Method method10 = oldModelClass.getMethod(
						"getResetHRTimeOffFrequencyTypeId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setResetHRTimeOffFrequencyTypeId(value10);

				Method method11 = oldModelClass.getMethod("getEffectiveDate");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setEffectiveDate(value11);

				Method method12 = oldModelClass.getMethod("getInactive");

				Boolean value12 = (Boolean)method12.invoke(oldModel,
						(Object[])null);

				newModel.setInactive(value12);

				Method method13 = oldModelClass.getMethod("getHoursAllowed");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setHoursAllowed(value13);

				Method method14 = oldModelClass.getMethod("getHoursBaseAmount");

				Double value14 = (Double)method14.invoke(oldModel,
						(Object[])null);

				newModel.setHoursBaseAmount(value14);

				Method method15 = oldModelClass.getMethod("getHoursToAccrue");

				Double value15 = (Double)method15.invoke(oldModel,
						(Object[])null);

				newModel.setHoursToAccrue(value15);

				Method method16 = oldModelClass.getMethod(
						"getCarryOverHoursAllowed");

				Double value16 = (Double)method16.invoke(oldModel,
						(Object[])null);

				newModel.setCarryOverHoursAllowed(value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeOffType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeOffTypeClp newModel = new HRTimeOffTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTimeOffTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getDescription");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setDescription(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeSheet(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeSheetClp newModel = new HRTimeSheetClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTimeSheetId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeSheetId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrUserId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value7);

				Method method8 = oldModelClass.getMethod("getStartDayOfYear");

				Integer value8 = (Integer)method8.invoke(oldModel,
						(Object[])null);

				newModel.setStartDayOfYear(value8);

				Method method9 = oldModelClass.getMethod("getEndDayOfYear");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setEndDayOfYear(value9);

				Method method10 = oldModelClass.getMethod("getYear");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setYear(value10);

				Method method11 = oldModelClass.getMethod("getStatus");

				Integer value11 = (Integer)method11.invoke(oldModel,
						(Object[])null);

				newModel.setStatus(value11);

				Method method12 = oldModelClass.getMethod("getStatusByUserId");

				Long value12 = (Long)method12.invoke(oldModel, (Object[])null);

				newModel.setStatusByUserId(value12);

				Method method13 = oldModelClass.getMethod("getStatusByUserName");

				String value13 = (String)method13.invoke(oldModel,
						(Object[])null);

				newModel.setStatusByUserName(value13);

				Method method14 = oldModelClass.getMethod("getStatusDate");

				Date value14 = (Date)method14.invoke(oldModel, (Object[])null);

				newModel.setStatusDate(value14);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeSheetDay(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeSheetDayClp newModel = new HRTimeSheetDayClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrTimeSheetDayId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeSheetDayId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrTimeSheetId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrTimeSheetId(value7);

				Method method8 = oldModelClass.getMethod("getHrUserId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value8);

				Method method9 = oldModelClass.getMethod("getDayOfYear");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setDayOfYear(value9);

				Method method10 = oldModelClass.getMethod("getYear");

				Integer value10 = (Integer)method10.invoke(oldModel,
						(Object[])null);

				newModel.setYear(value10);

				Method method11 = oldModelClass.getMethod("getHours");

				Double value11 = (Double)method11.invoke(oldModel,
						(Object[])null);

				newModel.setHours(value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRTimeSheetHoursPerDay(
		BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRTimeSheetHoursPerDayClp newModel = new HRTimeSheetHoursPerDayClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod(
						"getHrTimeSheetHoursPerDayId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrTimeSheetHoursPerDayId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrOfficeId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrOfficeId(value7);

				Method method8 = oldModelClass.getMethod("getHoursPerDay");

				Double value8 = (Double)method8.invoke(oldModel, (Object[])null);

				newModel.setHoursPerDay(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRUser(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRUserClp newModel = new HRUserClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrUserId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod(
						"getHrEmploymentTypeId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrEmploymentTypeId(value7);

				Method method8 = oldModelClass.getMethod("getHrJobTitleId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrJobTitleId(value8);

				Method method9 = oldModelClass.getMethod("getHrOfficeId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrOfficeId(value9);

				Method method10 = oldModelClass.getMethod(
						"getHrTerminationTypeId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setHrTerminationTypeId(value10);

				Method method11 = oldModelClass.getMethod("getHrWageTypeId");

				Long value11 = (Long)method11.invoke(oldModel, (Object[])null);

				newModel.setHrWageTypeId(value11);

				Method method12 = oldModelClass.getMethod("getHireDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setHireDate(value12);

				Method method13 = oldModelClass.getMethod("getTerminationDate");

				Date value13 = (Date)method13.invoke(oldModel, (Object[])null);

				newModel.setTerminationDate(value13);

				Method method14 = oldModelClass.getMethod("getWageAmount");

				Double value14 = (Double)method14.invoke(oldModel,
						(Object[])null);

				newModel.setWageAmount(value14);

				Method method15 = oldModelClass.getMethod("getWageCurrencyCode");

				String value15 = (String)method15.invoke(oldModel,
						(Object[])null);

				newModel.setWageCurrencyCode(value15);

				Method method16 = oldModelClass.getMethod("getBenefitsExempt");

				Boolean value16 = (Boolean)method16.invoke(oldModel,
						(Object[])null);

				newModel.setBenefitsExempt(value16);

				Method method17 = oldModelClass.getMethod("getOvertimeExempt");

				Boolean value17 = (Boolean)method17.invoke(oldModel,
						(Object[])null);

				newModel.setOvertimeExempt(value17);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRUserHistory(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRUserHistoryClp newModel = new HRUserHistoryClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrUserHistoryId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrUserHistoryId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod(
						"getHrEmploymentTypeId");

				Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

				newModel.setHrEmploymentTypeId(value6);

				Method method7 = oldModelClass.getMethod("getHrJobTitleId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrJobTitleId(value7);

				Method method8 = oldModelClass.getMethod("getHrOfficeId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrOfficeId(value8);

				Method method9 = oldModelClass.getMethod(
						"getHrTerminationTypeId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrTerminationTypeId(value9);

				Method method10 = oldModelClass.getMethod("getHrWageTypeId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setHrWageTypeId(value10);

				Method method11 = oldModelClass.getMethod("getHireDate");

				Date value11 = (Date)method11.invoke(oldModel, (Object[])null);

				newModel.setHireDate(value11);

				Method method12 = oldModelClass.getMethod("getTerminationDate");

				Date value12 = (Date)method12.invoke(oldModel, (Object[])null);

				newModel.setTerminationDate(value12);

				Method method13 = oldModelClass.getMethod("getWageAmount");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setWageAmount(value13);

				Method method14 = oldModelClass.getMethod("getWageCurrencyCode");

				String value14 = (String)method14.invoke(oldModel,
						(Object[])null);

				newModel.setWageCurrencyCode(value14);

				Method method15 = oldModelClass.getMethod("getBenefitsExempt");

				Boolean value15 = (Boolean)method15.invoke(oldModel,
						(Object[])null);

				newModel.setBenefitsExempt(value15);

				Method method16 = oldModelClass.getMethod("getOvertimeExempt");

				Boolean value16 = (Boolean)method16.invoke(oldModel,
						(Object[])null);

				newModel.setOvertimeExempt(value16);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRUserProject(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRUserProjectClp newModel = new HRUserProjectClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrUserProjectId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrUserProjectId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod(
						"getHrProjectBillingRateId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrProjectBillingRateId(value7);

				Method method8 = oldModelClass.getMethod("getHrProjectId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrProjectId(value8);

				Method method9 = oldModelClass.getMethod("getHrProjectRoleId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrProjectRoleId(value9);

				Method method10 = oldModelClass.getMethod("getHrUserId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value10);

				Method method11 = oldModelClass.getMethod("getActualRate");

				Double value11 = (Double)method11.invoke(oldModel,
						(Object[])null);

				newModel.setActualRate(value11);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRUserTask(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRUserTaskClp newModel = new HRUserTaskClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrUserTaskId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrUserTaskId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrBillabilityId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrBillabilityId(value7);

				Method method8 = oldModelClass.getMethod("getHrTaskId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrTaskId(value8);

				Method method9 = oldModelClass.getMethod("getHrTimesheetId");

				Long value9 = (Long)method9.invoke(oldModel, (Object[])null);

				newModel.setHrTimesheetId(value9);

				Method method10 = oldModelClass.getMethod("getHrUserId");

				Long value10 = (Long)method10.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value10);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRUserTimeOff(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRUserTimeOffClp newModel = new HRUserTimeOffClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrUserTimeOffId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrUserTimeOffId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getHrTimeOffTypeId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setHrTimeOffTypeId(value7);

				Method method8 = oldModelClass.getMethod("getHrUserId");

				Long value8 = (Long)method8.invoke(oldModel, (Object[])null);

				newModel.setHrUserId(value8);

				Method method9 = oldModelClass.getMethod("getYear");

				Integer value9 = (Integer)method9.invoke(oldModel,
						(Object[])null);

				newModel.setYear(value9);

				Method method10 = oldModelClass.getMethod("getHoursAllowed");

				Double value10 = (Double)method10.invoke(oldModel,
						(Object[])null);

				newModel.setHoursAllowed(value10);

				Method method11 = oldModelClass.getMethod("getHoursAccrued");

				Double value11 = (Double)method11.invoke(oldModel,
						(Object[])null);

				newModel.setHoursAccrued(value11);

				Method method12 = oldModelClass.getMethod("getHoursCarriedOver");

				Double value12 = (Double)method12.invoke(oldModel,
						(Object[])null);

				newModel.setHoursCarriedOver(value12);

				Method method13 = oldModelClass.getMethod("getHoursUsed");

				Double value13 = (Double)method13.invoke(oldModel,
						(Object[])null);

				newModel.setHoursUsed(value13);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputHRWageType(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				HRWageTypeClp newModel = new HRWageTypeClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getHrWageTypeId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setHrWageTypeId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getUserName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setUserName(value4);

				Method method5 = oldModelClass.getMethod("getCreateDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setCreateDate(value5);

				Method method6 = oldModelClass.getMethod("getModifiedDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setModifiedDate(value6);

				Method method7 = oldModelClass.getMethod("getCode");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setCode(value7);

				Method method8 = oldModelClass.getMethod("getName");

				String value8 = (String)method8.invoke(oldModel, (Object[])null);

				newModel.setName(value8);

				Method method9 = oldModelClass.getMethod("getDescription");

				String value9 = (String)method9.invoke(oldModel, (Object[])null);

				newModel.setDescription(value9);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}