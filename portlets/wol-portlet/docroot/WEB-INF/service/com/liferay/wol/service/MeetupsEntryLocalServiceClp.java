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
import com.liferay.portal.kernel.util.DoubleWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import com.liferay.wol.model.MeetupsEntryClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="MeetupsEntryLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsEntryLocalServiceClp implements MeetupsEntryLocalService {
	public MeetupsEntryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.wol.model.MeetupsEntry addMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.MeetupsEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMeetupsEntry",
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsEntry createMeetupsEntry(
		long meetupsEntryId) {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createMeetupsEntry",
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	public void deleteMeetupsEntry(long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		try {
			_classLoaderProxy.invoke("deleteMeetupsEntry",
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

	public void deleteMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.MeetupsEntry");
		}

		try {
			_classLoaderProxy.invoke("deleteMeetupsEntry",
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

	public com.liferay.wol.model.MeetupsEntry getMeetupsEntry(
		long meetupsEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(meetupsEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntry",
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.MeetupsEntry> getMeetupsEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntries",
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

		return (java.util.List<com.liferay.wol.model.MeetupsEntry>)translateOutput(returnObj);
	}

	public int getMeetupsEntriesCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntriesCount",
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

	public com.liferay.wol.model.MeetupsEntry updateMeetupsEntry(
		com.liferay.wol.model.MeetupsEntry meetupsEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(meetupsEntry);

		if (meetupsEntry == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.MeetupsEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsEntry",
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsEntry addMeetupsEntry(long userId,
		java.lang.String title, java.lang.String description,
		int startDateMonth, int startDateDay, int startDateYear,
		int startDateHour, int startDateMinute, int endDateMonth,
		int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
		int totalAttendees, int maxAttendees, double price, byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = translateInput(description);

		if (description == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new IntegerWrapper(startDateMonth);

		Object paramObj4 = new IntegerWrapper(startDateDay);

		Object paramObj5 = new IntegerWrapper(startDateYear);

		Object paramObj6 = new IntegerWrapper(startDateHour);

		Object paramObj7 = new IntegerWrapper(startDateMinute);

		Object paramObj8 = new IntegerWrapper(endDateMonth);

		Object paramObj9 = new IntegerWrapper(endDateDay);

		Object paramObj10 = new IntegerWrapper(endDateYear);

		Object paramObj11 = new IntegerWrapper(endDateHour);

		Object paramObj12 = new IntegerWrapper(endDateMinute);

		Object paramObj13 = new IntegerWrapper(totalAttendees);

		Object paramObj14 = new IntegerWrapper(maxAttendees);

		Object paramObj15 = new DoubleWrapper(price);

		Object paramObj16 = translateInput(thumbnail);

		if (thumbnail == null) {
			paramObj16 = new NullWrapper("[B");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addMeetupsEntry",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13,
						paramObj14, paramObj15, paramObj16
					});
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.MeetupsEntry> getMeetupsEntries(
		long companyId) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getMeetupsEntries",
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

		return (java.util.List<com.liferay.wol.model.MeetupsEntry>)translateOutput(returnObj);
	}

	public com.liferay.wol.model.MeetupsEntry updateMeetupsEntry(long userId,
		long meetupsEntryId, java.lang.String title,
		java.lang.String description, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, int totalAttendees, int maxAttendees, double price,
		byte[] thumbnail)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new LongWrapper(meetupsEntryId);

		Object paramObj2 = translateInput(title);

		if (title == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new IntegerWrapper(startDateMonth);

		Object paramObj5 = new IntegerWrapper(startDateDay);

		Object paramObj6 = new IntegerWrapper(startDateYear);

		Object paramObj7 = new IntegerWrapper(startDateHour);

		Object paramObj8 = new IntegerWrapper(startDateMinute);

		Object paramObj9 = new IntegerWrapper(endDateMonth);

		Object paramObj10 = new IntegerWrapper(endDateDay);

		Object paramObj11 = new IntegerWrapper(endDateYear);

		Object paramObj12 = new IntegerWrapper(endDateHour);

		Object paramObj13 = new IntegerWrapper(endDateMinute);

		Object paramObj14 = new IntegerWrapper(totalAttendees);

		Object paramObj15 = new IntegerWrapper(maxAttendees);

		Object paramObj16 = new DoubleWrapper(price);

		Object paramObj17 = translateInput(thumbnail);

		if (thumbnail == null) {
			paramObj17 = new NullWrapper("[B");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateMeetupsEntry",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7, paramObj8, paramObj9,
						paramObj10, paramObj11, paramObj12, paramObj13,
						paramObj14, paramObj15, paramObj16, paramObj17
					});
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

		return (com.liferay.wol.model.MeetupsEntry)translateOutput(returnObj);
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(MeetupsEntryClp.class.getName())) {
			MeetupsEntryClp oldCplModel = (MeetupsEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.wol.model.impl.MeetupsEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setMeetupsEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getMeetupsEntryId());

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

					Method method8 = newModelClass.getMethod("setStartDate",
							new Class[] { Date.class });

					Date value8 = oldCplModel.getStartDate();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setEndDate",
							new Class[] { Date.class });

					Date value9 = oldCplModel.getEndDate();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setTotalAttendees",
							new Class[] { Integer.TYPE });

					Integer value10 = new Integer(oldCplModel.getTotalAttendees());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setMaxAttendees",
							new Class[] { Integer.TYPE });

					Integer value11 = new Integer(oldCplModel.getMaxAttendees());

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setPrice",
							new Class[] { Double.TYPE });

					Double value12 = new Double(oldCplModel.getPrice());

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setThumbnailId",
							new Class[] { Long.TYPE });

					Long value13 = new Long(oldCplModel.getThumbnailId());

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
					"com.liferay.wol.model.impl.MeetupsEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					MeetupsEntryClp newModel = new MeetupsEntryClp();

					Method method0 = oldModelClass.getMethod(
							"getMeetupsEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setMeetupsEntryId(value0.longValue());

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

					Method method8 = oldModelClass.getMethod("getStartDate");

					Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

					newModel.setStartDate(value8);

					Method method9 = oldModelClass.getMethod("getEndDate");

					Date value9 = (Date)method9.invoke(oldModel, (Object[])null);

					newModel.setEndDate(value9);

					Method method10 = oldModelClass.getMethod(
							"getTotalAttendees");

					Integer value10 = (Integer)method10.invoke(oldModel,
							(Object[])null);

					newModel.setTotalAttendees(value10.intValue());

					Method method11 = oldModelClass.getMethod("getMaxAttendees");

					Integer value11 = (Integer)method11.invoke(oldModel,
							(Object[])null);

					newModel.setMaxAttendees(value11.intValue());

					Method method12 = oldModelClass.getMethod("getPrice");

					Double value12 = (Double)method12.invoke(oldModel,
							(Object[])null);

					newModel.setPrice(value12.doubleValue());

					Method method13 = oldModelClass.getMethod("getThumbnailId");

					Long value13 = (Long)method13.invoke(oldModel,
							(Object[])null);

					newModel.setThumbnailId(value13.longValue());

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

	private static Log _log = LogFactoryUtil.getLog(MeetupsEntryLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}