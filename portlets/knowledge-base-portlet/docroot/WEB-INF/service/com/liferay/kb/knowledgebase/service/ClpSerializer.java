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

package com.liferay.kb.knowledgebase.service;

import com.liferay.kb.knowledgebase.model.KBArticleClp;
import com.liferay.kb.knowledgebase.model.KBArticleResourceClp;
import com.liferay.kb.knowledgebase.model.KBFeedbackEntryClp;
import com.liferay.kb.knowledgebase.model.KBFeedbackStatsClp;

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

		if (oldModelClassName.equals(KBArticleClp.class.getName())) {
			KBArticleClp oldCplModel = (KBArticleClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBArticleImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setUuid",
							new Class[] { String.class });

					String value0 = oldCplModel.getUuid();

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleId",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleId());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setGroupId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getGroupId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getResourcePrimKey());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getCompanyId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getUserId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value6 = oldCplModel.getUserName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value7 = oldCplModel.getModifiedDate();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value8 = oldCplModel.getTitle();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setVersion",
							new Class[] { Double.TYPE });

					Double value9 = new Double(oldCplModel.getVersion());

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setMinorEdit",
							new Class[] { Boolean.TYPE });

					Boolean value10 = new Boolean(oldCplModel.getMinorEdit());

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setContent",
							new Class[] { String.class });

					String value11 = oldCplModel.getContent();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setDescription",
							new Class[] { String.class });

					String value12 = oldCplModel.getDescription();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setHead",
							new Class[] { Boolean.TYPE });

					Boolean value13 = new Boolean(oldCplModel.getHead());

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setTemplate",
							new Class[] { Boolean.TYPE });

					Boolean value14 = new Boolean(oldCplModel.getTemplate());

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setDraft",
							new Class[] { Boolean.TYPE });

					Boolean value15 = new Boolean(oldCplModel.getDraft());

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setParentResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value16 = new Long(oldCplModel.getParentResourcePrimKey());

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

		if (oldModelClassName.equals(KBArticleResourceClp.class.getName())) {
			KBArticleResourceClp oldCplModel = (KBArticleResourceClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBArticleResourceImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getResourcePrimKey());

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

					Method method6 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value6 = oldCplModel.getTitle();

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

		if (oldModelClassName.equals(KBFeedbackEntryClp.class.getName())) {
			KBFeedbackEntryClp oldCplModel = (KBFeedbackEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBFeedbackEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFeedbackEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFeedbackEntryId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleResourcePrimKey());

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

					Method method6 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value6 = oldCplModel.getComments();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setScore",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getScore());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setVote",
							new Class[] { Integer.TYPE });

					Integer value8 = new Integer(oldCplModel.getVote());

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

		if (oldModelClassName.equals(KBFeedbackStatsClp.class.getName())) {
			KBFeedbackStatsClp oldCplModel = (KBFeedbackStatsClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBFeedbackStatsImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFeedbackStatsId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFeedbackStatsId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleResourcePrimKey());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setAverageScore",
							new Class[] { Double.TYPE });

					Double value2 = new Double(oldCplModel.getAverageScore());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setTotalScoreEntries",
							new Class[] { Integer.TYPE });

					Integer value3 = new Integer(oldCplModel.getTotalScoreEntries());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setTotalVotes",
							new Class[] { Integer.TYPE });

					Integer value4 = new Integer(oldCplModel.getTotalVotes());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setYesVotes",
							new Class[] { Integer.TYPE });

					Integer value5 = new Integer(oldCplModel.getYesVotes());

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
					"com.liferay.kb.knowledgebase.model.impl.KBArticleImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBArticleClp newModel = new KBArticleClp();

					Method method0 = oldModelClass.getMethod("getUuid");

					String value0 = (String)method0.invoke(oldModel,
							(Object[])null);

					newModel.setUuid(value0);

					Method method1 = oldModelClass.getMethod("getArticleId");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleId(value1.longValue());

					Method method2 = oldModelClass.getMethod("getGroupId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setGroupId(value2.longValue());

					Method method3 = oldModelClass.getMethod(
							"getResourcePrimKey");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setResourcePrimKey(value3.longValue());

					Method method4 = oldModelClass.getMethod("getCompanyId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getUserId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setUserId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getUserName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value6);

					Method method7 = oldModelClass.getMethod("getModifiedDate");

					Date value7 = (Date)method7.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value7);

					Method method8 = oldModelClass.getMethod("getTitle");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value8);

					Method method9 = oldModelClass.getMethod("getVersion");

					Double value9 = (Double)method9.invoke(oldModel,
							(Object[])null);

					newModel.setVersion(value9.doubleValue());

					Method method10 = oldModelClass.getMethod("getMinorEdit");

					Boolean value10 = (Boolean)method10.invoke(oldModel,
							(Object[])null);

					newModel.setMinorEdit(value10.booleanValue());

					Method method11 = oldModelClass.getMethod("getContent");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setContent(value11);

					Method method12 = oldModelClass.getMethod("getDescription");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setDescription(value12);

					Method method13 = oldModelClass.getMethod("getHead");

					Boolean value13 = (Boolean)method13.invoke(oldModel,
							(Object[])null);

					newModel.setHead(value13.booleanValue());

					Method method14 = oldModelClass.getMethod("getTemplate");

					Boolean value14 = (Boolean)method14.invoke(oldModel,
							(Object[])null);

					newModel.setTemplate(value14.booleanValue());

					Method method15 = oldModelClass.getMethod("getDraft");

					Boolean value15 = (Boolean)method15.invoke(oldModel,
							(Object[])null);

					newModel.setDraft(value15.booleanValue());

					Method method16 = oldModelClass.getMethod(
							"getParentResourcePrimKey");

					Long value16 = (Long)method16.invoke(oldModel,
							(Object[])null);

					newModel.setParentResourcePrimKey(value16.longValue());

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
					"com.liferay.kb.knowledgebase.model.impl.KBArticleResourceImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBArticleResourceClp newModel = new KBArticleResourceClp();

					Method method0 = oldModelClass.getMethod(
							"getResourcePrimKey");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setResourcePrimKey(value0.longValue());

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

					Method method6 = oldModelClass.getMethod("getTitle");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value6);

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
					"com.liferay.kb.knowledgebase.model.impl.KBFeedbackEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBFeedbackEntryClp newModel = new KBFeedbackEntryClp();

					Method method0 = oldModelClass.getMethod(
							"getFeedbackEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFeedbackEntryId(value0.longValue());

					Method method1 = oldModelClass.getMethod(
							"getArticleResourcePrimKey");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleResourcePrimKey(value1.longValue());

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

					Method method6 = oldModelClass.getMethod("getComments");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value6);

					Method method7 = oldModelClass.getMethod("getScore");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setScore(value7.intValue());

					Method method8 = oldModelClass.getMethod("getVote");

					Integer value8 = (Integer)method8.invoke(oldModel,
							(Object[])null);

					newModel.setVote(value8.intValue());

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
					"com.liferay.kb.knowledgebase.model.impl.KBFeedbackStatsImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBFeedbackStatsClp newModel = new KBFeedbackStatsClp();

					Method method0 = oldModelClass.getMethod(
							"getFeedbackStatsId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFeedbackStatsId(value0.longValue());

					Method method1 = oldModelClass.getMethod(
							"getArticleResourcePrimKey");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleResourcePrimKey(value1.longValue());

					Method method2 = oldModelClass.getMethod("getAverageScore");

					Double value2 = (Double)method2.invoke(oldModel,
							(Object[])null);

					newModel.setAverageScore(value2.doubleValue());

					Method method3 = oldModelClass.getMethod(
							"getTotalScoreEntries");

					Integer value3 = (Integer)method3.invoke(oldModel,
							(Object[])null);

					newModel.setTotalScoreEntries(value3.intValue());

					Method method4 = oldModelClass.getMethod("getTotalVotes");

					Integer value4 = (Integer)method4.invoke(oldModel,
							(Object[])null);

					newModel.setTotalVotes(value4.intValue());

					Method method5 = oldModelClass.getMethod("getYesVotes");

					Integer value5 = (Integer)method5.invoke(oldModel,
							(Object[])null);

					newModel.setYesVotes(value5.intValue());

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