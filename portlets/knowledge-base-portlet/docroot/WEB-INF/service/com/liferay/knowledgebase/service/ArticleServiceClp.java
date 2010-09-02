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

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addArticleMethodKey0,
				parentResourcePrimKey, title, content, description, priority,
				dirName, serviceContext);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_addAttachmentMethodKey1,
				companyId, groupId, resourcePrimKey, dirName, shortFileName,
				inputStream);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_deleteArticleMethodKey2,
				resourcePrimKey);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_deleteAttachmentMethodKey3,
				companyId, groupId, resourcePrimKey, fileName);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticleMethodKey4,
				resourcePrimKey, version);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesMethodKey5,
				resourcePrimKey, status, start, end, orderByComparator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesMethodKey6,
				params, allVersions, start, end, orderByComparator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

	public int getArticlesCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesCountMethodKey7,
				resourcePrimKey, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesCountMethodKey8,
				params, allVersions);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		long resourcePrimKey, int status, int max, java.lang.String type,
		double version, java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesRSSMethodKey9,
				portletId, resourcePrimKey, status, max, type, version,
				displayStyle, maximized, themeDisplay);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		long companyId, int status, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyArticlesMethodKey10,
				companyId, status, allVersions, start, end, orderByComparator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

	public int getCompanyArticlesCount(long companyId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyArticlesCountMethodKey11,
				companyId, status, allVersions);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		long groupId, int status, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupArticlesMethodKey12,
				groupId, status, allVersions, start, end, orderByComparator);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

	public int getGroupArticlesCount(long groupId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupArticlesCountMethodKey13,
				groupId, status, allVersions);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		int status, int max, java.lang.String type, double version,
		java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupArticlesRSSMethodKey14,
				portletId, status, max, type, version, displayStyle, maximized,
				themeDisplay);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLatestArticleMethodKey15,
				resourcePrimKey, status);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_subscribeMethodKey16,
				groupId);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_subscribeArticleMethodKey17,
				portletId, resourcePrimKey);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_unsubscribeMethodKey18,
				groupId);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		MethodHandler methodHandler = new MethodHandler(_unsubscribeArticleMethodKey19,
				companyId, resourcePrimKey);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateArticleMethodKey20,
				resourcePrimKey, parentResourcePrimKey, title, content,
				description, priority, dirName, serviceContext);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateAttachmentsMethodKey21,
				companyId, groupId, resourcePrimKey, dirName);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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
	private MethodKey _addArticleMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
			"addArticle", long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class,
			java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class);
	private MethodKey _addAttachmentMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
			"addAttachment", long.class, long.class, long.class,
			java.lang.String.class, java.lang.String.class,
			java.io.InputStream.class);
	private MethodKey _deleteArticleMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
			"deleteArticle", long.class);
	private MethodKey _deleteAttachmentMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
			"deleteAttachment", long.class, long.class, long.class,
			java.lang.String.class);
	private MethodKey _getArticleMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticle", long.class, int.class);
	private MethodKey _getArticlesMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticles", long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class);
	private MethodKey _getArticlesMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticles", java.util.Map.class, boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class);
	private MethodKey _getArticlesCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticlesCount", long.class, int.class);
	private MethodKey _getArticlesCountMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticlesCount", java.util.Map.class, boolean.class);
	private MethodKey _getArticlesRSSMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
			"getArticlesRSS", java.lang.String.class, long.class, int.class,
			int.class, java.lang.String.class, double.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.theme.ThemeDisplay.class);
	private MethodKey _getCompanyArticlesMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
			"getCompanyArticles", long.class, int.class, boolean.class,
			int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class);
	private MethodKey _getCompanyArticlesCountMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
			"getCompanyArticlesCount", long.class, int.class, boolean.class);
	private MethodKey _getGroupArticlesMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
			"getGroupArticles", long.class, int.class, boolean.class,
			int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class);
	private MethodKey _getGroupArticlesCountMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
			"getGroupArticlesCount", long.class, int.class, boolean.class);
	private MethodKey _getGroupArticlesRSSMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
			"getGroupArticlesRSS", java.lang.String.class, int.class,
			int.class, java.lang.String.class, double.class,
			java.lang.String.class, boolean.class,
			com.liferay.portal.theme.ThemeDisplay.class);
	private MethodKey _getLatestArticleMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
			"getLatestArticle", long.class, int.class);
	private MethodKey _subscribeMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
			"subscribe", long.class);
	private MethodKey _subscribeArticleMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
			"subscribeArticle", java.lang.String.class, long.class);
	private MethodKey _unsubscribeMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
			"unsubscribe", long.class);
	private MethodKey _unsubscribeArticleMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
			"unsubscribeArticle", long.class, long.class);
	private MethodKey _updateArticleMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
			"updateArticle", long.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class,
			java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class);
	private MethodKey _updateAttachmentsMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
			"updateAttachments", long.class, long.class, long.class,
			java.lang.String.class);
}