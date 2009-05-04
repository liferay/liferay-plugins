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

package com.liferay.wol.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import com.liferay.wol.model.JIRAActionClp;
import com.liferay.wol.model.JIRAChangeGroupClp;
import com.liferay.wol.model.JIRAChangeItemClp;
import com.liferay.wol.model.JIRAIssueClp;
import com.liferay.wol.model.MeetupsEntryClp;
import com.liferay.wol.model.MeetupsRegistrationClp;
import com.liferay.wol.model.SVNRepositoryClp;
import com.liferay.wol.model.SVNRevisionClp;
import com.liferay.wol.model.WallEntryClp;

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
	public static final String SERVLET_CONTEXT_NAME = "wol-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(JIRAActionClp.class.getName())) {
			JIRAActionClp oldCplModel = (JIRAActionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAActionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraActionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraActionId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setJiraUserId",
							new Class[] { String.class });

					String value1 = oldCplModel.getJiraUserId();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value3 = oldCplModel.getModifiedDate();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setJiraIssueId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getJiraIssueId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setType",
							new Class[] { String.class });

					String value5 = oldCplModel.getType();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setBody",
							new Class[] { String.class });

					String value6 = oldCplModel.getBody();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setJiraGroupName",
							new Class[] { String.class });

					String value7 = oldCplModel.getJiraGroupName();

					method7.invoke(newModel, value7);

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

		if (oldModelClassName.equals(JIRAChangeGroupClp.class.getName())) {
			JIRAChangeGroupClp oldCplModel = (JIRAChangeGroupClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAChangeGroupImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraChangeGroupId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraChangeGroupId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setJiraUserId",
							new Class[] { String.class });

					String value1 = oldCplModel.getJiraUserId();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setJiraIssueId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getJiraIssueId());

					method3.invoke(newModel, value3);

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

		if (oldModelClassName.equals(JIRAChangeItemClp.class.getName())) {
			JIRAChangeItemClp oldCplModel = (JIRAChangeItemClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAChangeItemImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraChangeItemId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraChangeItemId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setJiraChangeGroupId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getJiraChangeGroupId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setField",
							new Class[] { String.class });

					String value2 = oldCplModel.getField();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setOldValue",
							new Class[] { String.class });

					String value3 = oldCplModel.getOldValue();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setOldString",
							new Class[] { String.class });

					String value4 = oldCplModel.getOldString();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setNewValue",
							new Class[] { String.class });

					String value5 = oldCplModel.getNewValue();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setNewString",
							new Class[] { String.class });

					String value6 = oldCplModel.getNewString();

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

		if (oldModelClassName.equals(JIRAIssueClp.class.getName())) {
			JIRAIssueClp oldCplModel = (JIRAIssueClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.JIRAIssueImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setJiraIssueId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getJiraIssueId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setProjectId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getProjectId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setKey",
							new Class[] { String.class });

					String value4 = oldCplModel.getKey();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setSummary",
							new Class[] { String.class });

					String value5 = oldCplModel.getSummary();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value6 = oldCplModel.getDescription();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setReporterJiraUserId",
							new Class[] { String.class });

					String value7 = oldCplModel.getReporterJiraUserId();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setAssigneeJiraUserId",
							new Class[] { String.class });

					String value8 = oldCplModel.getAssigneeJiraUserId();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setResolution",
							new Class[] { String.class });

					String value9 = oldCplModel.getResolution();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setStatus",
							new Class[] { String.class });

					String value10 = oldCplModel.getStatus();

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

		if (oldModelClassName.equals(MeetupsEntryClp.class.getName())) {
			MeetupsEntryClp oldCplModel = (MeetupsEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.MeetupsEntryImpl",
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

		if (oldModelClassName.equals(MeetupsRegistrationClp.class.getName())) {
			MeetupsRegistrationClp oldCplModel = (MeetupsRegistrationClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.MeetupsRegistrationImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setMeetupsRegistrationId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getMeetupsRegistrationId());

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

					Method method6 = newModelClass.getMethod("setMeetupsEntryId",
							new Class[] { Long.TYPE });

					Long value6 = new Long(oldCplModel.getMeetupsEntryId());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setStatus",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getStatus());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value8 = oldCplModel.getComments();

					method8.invoke(newModel, value8);

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

		if (oldModelClassName.equals(SVNRepositoryClp.class.getName())) {
			SVNRepositoryClp oldCplModel = (SVNRepositoryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.SVNRepositoryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setSvnRepositoryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getSvnRepositoryId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setUrl",
							new Class[] { String.class });

					String value1 = oldCplModel.getUrl();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setRevisionNumber",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getRevisionNumber());

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

		if (oldModelClassName.equals(SVNRevisionClp.class.getName())) {
			SVNRevisionClp oldCplModel = (SVNRevisionClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.SVNRevisionImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setSvnRevisionId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getSvnRevisionId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setSvnUserId",
							new Class[] { String.class });

					String value1 = oldCplModel.getSvnUserId();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getCreateDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setSvnRepositoryId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getSvnRepositoryId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setRevisionNumber",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getRevisionNumber());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value5 = oldCplModel.getComments();

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

		if (oldModelClassName.equals(WallEntryClp.class.getName())) {
			WallEntryClp oldCplModel = (WallEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.wol.model.impl.WallEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setWallEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getWallEntryId());

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

					Method method7 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value7 = oldCplModel.getComments();

					method7.invoke(newModel, value7);

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
					"com.liferay.wol.model.impl.JIRAActionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAActionClp newModel = new JIRAActionClp();

					Method method0 = oldModelClass.getMethod("getJiraActionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraActionId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getJiraUserId");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setJiraUserId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getModifiedDate");

					Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value3);

					Method method4 = oldModelClass.getMethod("getJiraIssueId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setJiraIssueId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getType");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setType(value5);

					Method method6 = oldModelClass.getMethod("getBody");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setBody(value6);

					Method method7 = oldModelClass.getMethod("getJiraGroupName");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setJiraGroupName(value7);

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
					"com.liferay.wol.model.impl.JIRAChangeGroupImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAChangeGroupClp newModel = new JIRAChangeGroupClp();

					Method method0 = oldModelClass.getMethod(
							"getJiraChangeGroupId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraChangeGroupId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getJiraUserId");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setJiraUserId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod("getJiraIssueId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setJiraIssueId(value3.longValue());

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
					"com.liferay.wol.model.impl.JIRAChangeItemImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAChangeItemClp newModel = new JIRAChangeItemClp();

					Method method0 = oldModelClass.getMethod(
							"getJiraChangeItemId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraChangeItemId(value0.longValue());

					Method method1 = oldModelClass.getMethod(
							"getJiraChangeGroupId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setJiraChangeGroupId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getField");

					String value2 = (String)method2.invoke(oldModel,
							(Object[])null);

					newModel.setField(value2);

					Method method3 = oldModelClass.getMethod("getOldValue");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setOldValue(value3);

					Method method4 = oldModelClass.getMethod("getOldString");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setOldString(value4);

					Method method5 = oldModelClass.getMethod("getNewValue");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setNewValue(value5);

					Method method6 = oldModelClass.getMethod("getNewString");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setNewString(value6);

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

		if (oldModelClassName.equals("com.liferay.wol.model.impl.JIRAIssueImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					JIRAIssueClp newModel = new JIRAIssueClp();

					Method method0 = oldModelClass.getMethod("getJiraIssueId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setJiraIssueId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getProjectId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setProjectId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getKey");

					String value4 = (String)method4.invoke(oldModel,
							(Object[])null);

					newModel.setKey(value4);

					Method method5 = oldModelClass.getMethod("getSummary");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setSummary(value5);

					Method method6 = oldModelClass.getMethod("getDescription");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value6);

					Method method7 = oldModelClass.getMethod(
							"getReporterJiraUserId");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setReporterJiraUserId(value7);

					Method method8 = oldModelClass.getMethod(
							"getAssigneeJiraUserId");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setAssigneeJiraUserId(value8);

					Method method9 = oldModelClass.getMethod("getResolution");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setResolution(value9);

					Method method10 = oldModelClass.getMethod("getStatus");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value10);

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

		if (oldModelClassName.equals(
					"com.liferay.wol.model.impl.MeetupsRegistrationImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					MeetupsRegistrationClp newModel = new MeetupsRegistrationClp();

					Method method0 = oldModelClass.getMethod(
							"getMeetupsRegistrationId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setMeetupsRegistrationId(value0.longValue());

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
							"getMeetupsEntryId");

					Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

					newModel.setMeetupsEntryId(value6.longValue());

					Method method7 = oldModelClass.getMethod("getStatus");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setStatus(value7.intValue());

					Method method8 = oldModelClass.getMethod("getComments");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value8);

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
					"com.liferay.wol.model.impl.SVNRepositoryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					SVNRepositoryClp newModel = new SVNRepositoryClp();

					Method method0 = oldModelClass.getMethod(
							"getSvnRepositoryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setSvnRepositoryId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getUrl");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setUrl(value1);

					Method method2 = oldModelClass.getMethod(
							"getRevisionNumber");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setRevisionNumber(value2.longValue());

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
					"com.liferay.wol.model.impl.SVNRevisionImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					SVNRevisionClp newModel = new SVNRevisionClp();

					Method method0 = oldModelClass.getMethod("getSvnRevisionId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setSvnRevisionId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getSvnUserId");

					String value1 = (String)method1.invoke(oldModel,
							(Object[])null);

					newModel.setSvnUserId(value1);

					Method method2 = oldModelClass.getMethod("getCreateDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value2);

					Method method3 = oldModelClass.getMethod(
							"getSvnRepositoryId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setSvnRepositoryId(value3.longValue());

					Method method4 = oldModelClass.getMethod(
							"getRevisionNumber");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setRevisionNumber(value4.longValue());

					Method method5 = oldModelClass.getMethod("getComments");

					String value5 = (String)method5.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value5);

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

		if (oldModelClassName.equals("com.liferay.wol.model.impl.WallEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					WallEntryClp newModel = new WallEntryClp();

					Method method0 = oldModelClass.getMethod("getWallEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setWallEntryId(value0.longValue());

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

					Method method7 = oldModelClass.getMethod("getComments");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value7);

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