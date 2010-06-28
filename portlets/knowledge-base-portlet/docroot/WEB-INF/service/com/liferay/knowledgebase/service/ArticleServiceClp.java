/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.service;

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.DoubleWrapper;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="ArticleServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ArticleServiceClp implements ArticleService {
	public ArticleServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.knowledgebase.model.Article addArticle(
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(parentResourcePrimKey);

		Object paramObj1 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(content);

		if (content == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new IntegerWrapper(priority);

		Object paramObj5 = ClpSerializer.translateInput(dirName);

		if (dirName == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj6 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public void addAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new LongWrapper(resourcePrimKey);

		Object paramObj3 = ClpSerializer.translateInput(dirName);

		if (dirName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(shortFileName);

		if (shortFileName == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(inputStream);

		if (inputStream == null) {
			paramObj5 = new NullWrapper("java.io.InputStream");
		}

		try {
			_classLoaderProxy.invoke("addAttachment",
				new Object[] {
					paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
					paramObj5
				});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteArticle", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void deleteAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new LongWrapper(resourcePrimKey);

		Object paramObj3 = ClpSerializer.translateInput(fileName);

		if (fileName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		try {
			_classLoaderProxy.invoke("deleteAttachment",
				new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(version);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticle",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object paramObj3 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticles",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj0 = new NullWrapper("java.util.Map");
		}

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object paramObj4 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getArticlesCount(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticlesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public int getArticlesCount(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(params);

		if (params == null) {
			paramObj0 = new NullWrapper("java.util.Map");
		}

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticlesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.lang.String getArticlesRSS(java.lang.String portletId,
		long resourcePrimKey, int max, java.lang.String type, double version,
		java.lang.String displayStyle, boolean isMaximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(portletId);

		if (portletId == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		Object paramObj2 = new IntegerWrapper(max);

		Object paramObj3 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = new DoubleWrapper(version);

		Object paramObj5 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj5 = new NullWrapper("java.lang.String");
		}

		Object paramObj6 = new BooleanWrapper(isMaximized);

		Object paramObj7 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj7 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticlesRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object paramObj4 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getCompanyArticlesCount(long companyId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getCompanyArticlesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object paramObj4 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticles",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupArticlesCount(long groupId, boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		Object paramObj1 = new BooleanWrapper(allVersions);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public java.lang.String getGroupArticlesRSS(java.lang.String portletId,
		int max, java.lang.String type, double version,
		java.lang.String displayStyle, boolean isMaximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(portletId);

		if (portletId == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = new IntegerWrapper(max);

		Object paramObj2 = ClpSerializer.translateInput(type);

		if (type == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = new DoubleWrapper(version);

		Object paramObj4 = ClpSerializer.translateInput(displayStyle);

		if (displayStyle == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new BooleanWrapper(isMaximized);

		Object paramObj6 = ClpSerializer.translateInput(themeDisplay);

		if (themeDisplay == null) {
			paramObj6 = new NullWrapper("com.liferay.portal.theme.ThemeDisplay");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getGroupArticlesRSS",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getLatestArticle",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public void subscribe(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		try {
			_classLoaderProxy.invoke("subscribe", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void subscribeArticle(java.lang.String portletId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(portletId);

		if (portletId == null) {
			paramObj0 = new NullWrapper("java.lang.String");
		}

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("subscribeArticle",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void unsubscribe(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(groupId);

		try {
			_classLoaderProxy.invoke("unsubscribe", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public void unsubscribeArticle(long companyId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(resourcePrimKey);

		try {
			_classLoaderProxy.invoke("unsubscribeArticle",
				new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
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

	public com.liferay.knowledgebase.model.Article updateArticle(
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(resourcePrimKey);

		Object paramObj1 = new LongWrapper(parentResourcePrimKey);

		Object paramObj2 = ClpSerializer.translateInput(title);

		if (title == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(content);

		if (content == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object paramObj4 = ClpSerializer.translateInput(description);

		if (description == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = new IntegerWrapper(priority);

		Object paramObj6 = ClpSerializer.translateInput(dirName);

		if (dirName == null) {
			paramObj6 = new NullWrapper("java.lang.String");
		}

		Object paramObj7 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj7 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateArticle",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5, paramObj6, paramObj7
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String updateAttachments(long companyId, long groupId,
		long resourcePrimKey, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(companyId);

		Object paramObj1 = new LongWrapper(groupId);

		Object paramObj2 = new LongWrapper(resourcePrimKey);

		Object paramObj3 = ClpSerializer.translateInput(dirName);

		if (dirName == null) {
			paramObj3 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateAttachments",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
}