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

import com.liferay.kb.knowledgebase.model.KBFeedbackStatsClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="KBFeedbackStatsLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsLocalServiceClp
	implements KBFeedbackStatsLocalService {
	public KBFeedbackStatsLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats addKBFeedbackStats(
		com.liferay.kb.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbFeedbackStats);

		if (kbFeedbackStats == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackStats");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addKBFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats createKBFeedbackStats(
		long feedbackStatsId) {
		Object paramObj0 = new LongWrapper(feedbackStatsId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createKBFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	public void deleteKBFeedbackStats(long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackStatsId);

		try {
			_classLoaderProxy.invoke("deleteKBFeedbackStats",
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

	public void deleteKBFeedbackStats(
		com.liferay.kb.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbFeedbackStats);

		if (kbFeedbackStats == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackStats");
		}

		try {
			_classLoaderProxy.invoke("deleteKBFeedbackStats",
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

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats getKBFeedbackStats(
		long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackStatsId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackStats> getKBFeedbackStatses(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackStatses",
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackStats>)translateOutput(returnObj);
	}

	public int getKBFeedbackStatsesCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackStatsesCount",
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

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats updateKBFeedbackStats(
		com.liferay.kb.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbFeedbackStats);

		if (kbFeedbackStats == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackStats");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateKBFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	public void deleteFeedbackStats(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteFeedbackStats",
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

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats getFeedbackStats(
		long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackStatsId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackStats getArticleFeedbackStats(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticleFeedbackStats",
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackStats)translateOutput(returnObj);
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(KBFeedbackStatsClp.class.getName())) {
			KBFeedbackStatsClp oldCplModel = (KBFeedbackStatsClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBFeedbackStatsImpl",
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

	private static Log _log = LogFactoryUtil.getLog(KBFeedbackStatsLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}