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
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import com.liferay.wol.model.SVNRepositoryClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SVNRepositoryLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SVNRepositoryLocalServiceClp implements SVNRepositoryLocalService {
	public SVNRepositoryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.wol.model.SVNRepository addSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(svnRepository);

		if (svnRepository == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.SVNRepository");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addSVNRepository",
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

		return (com.liferay.wol.model.SVNRepository)translateOutput(returnObj);
	}

	public com.liferay.wol.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		Object paramObj0 = new LongWrapper(svnRepositoryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createSVNRepository",
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

		return (com.liferay.wol.model.SVNRepository)translateOutput(returnObj);
	}

	public void deleteSVNRepository(long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(svnRepositoryId);

		try {
			_classLoaderProxy.invoke("deleteSVNRepository",
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

	public void deleteSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(svnRepository);

		if (svnRepository == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.SVNRepository");
		}

		try {
			_classLoaderProxy.invoke("deleteSVNRepository",
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

	public com.liferay.wol.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(svnRepositoryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSVNRepository",
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

		return (com.liferay.wol.model.SVNRepository)translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wol.model.SVNRepository> getSVNRepositories(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSVNRepositories",
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

		return (java.util.List<com.liferay.wol.model.SVNRepository>)translateOutput(returnObj);
	}

	public int getSVNRepositoriesCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSVNRepositoriesCount",
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

	public com.liferay.wol.model.SVNRepository updateSVNRepository(
		com.liferay.wol.model.SVNRepository svnRepository)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(svnRepository);

		if (svnRepository == null) {
			paramObj0 = new NullWrapper("com.liferay.wol.model.SVNRepository");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateSVNRepository",
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

		return (com.liferay.wol.model.SVNRepository)translateOutput(returnObj);
	}

	public com.liferay.wol.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(url);

		if (url == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getSVNRepository",
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

		return (com.liferay.wol.model.SVNRepository)translateOutput(returnObj);
	}

	public void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(url);

		if (url == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		try {
			_classLoaderProxy.invoke("updateSVNRepository",
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

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(SVNRepositoryClp.class.getName())) {
			SVNRepositoryClp oldCplModel = (SVNRepositoryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.wol.model.impl.SVNRepositoryImpl",
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

	private static Log _log = LogFactoryUtil.getLog(SVNRepositoryLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}