/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
public class KBArticleServiceClp implements KBArticleService {
	public KBArticleServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addAttachmentMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addAttachment", java.lang.String.class, long.class,
				java.lang.String.class, java.lang.String.class,
				java.io.InputStream.class,
				com.liferay.portal.service.ServiceContext.class);

		_addKBArticleMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"addKBArticle", java.lang.String.class, long.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String[].class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteAttachmentMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteAttachment", long.class, long.class,
				java.lang.String.class, long.class, java.lang.String.class);

		_deleteKBArticleMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKBArticle", long.class);

		_deleteKBArticlesMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKBArticles", long.class, long[].class);

		_getGroupKBArticlesMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupKBArticles", long.class, int.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getGroupKBArticlesCountMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupKBArticlesCount", long.class, int.class);

		_getGroupKBArticlesRSSMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupKBArticlesRSS", int.class, int.class,
				java.lang.String.class, java.lang.String.class,
				com.liferay.portal.theme.ThemeDisplay.class);

		_getKBArticleMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticle", long.class, int.class);

		_getKBArticleAndAllDescendantsMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleAndAllDescendants", long.class, long.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticleRSSMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleRSS", long.class, int.class, int.class,
				java.lang.String.class, java.lang.String.class,
				com.liferay.portal.theme.ThemeDisplay.class);

		_getKBArticleSearchDisplayMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleSearchDisplay", long.class,
				java.lang.String.class, java.lang.String.class, int.class,
				java.util.Date.class, java.util.Date.class, boolean.class,
				int[].class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticleVersionsMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleVersions", long.class, long.class, int.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getKBArticleVersionsCountMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticleVersionsCount", long.class, long.class, int.class);

		_getKBArticlesMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKBArticles", long.class, long[].class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getLatestKBArticleMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"getLatestKBArticle", long.class, int.class);

		_getSectionsKBArticlesMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSectionsKBArticles", long.class, java.lang.String[].class,
				int.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getSectionsKBArticlesCountMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSectionsKBArticlesCount", long.class,
				java.lang.String[].class, int.class);

		_getSiblingKBArticlesMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSiblingKBArticles", long.class, long.class, int.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getSiblingKBArticlesCountMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSiblingKBArticlesCount", long.class, long.class, int.class);

		_moveKBArticleMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"moveKBArticle", long.class, long.class, double.class);

		_subscribeGroupKBArticlesMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"subscribeGroupKBArticles", long.class, java.lang.String.class);

		_subscribeKBArticleMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"subscribeKBArticle", long.class, long.class);

		_unsubscribeGroupKBArticlesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribeGroupKBArticles", long.class, java.lang.String.class);

		_unsubscribeKBArticleMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribeKBArticle", long.class);

		_updateAttachmentsMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateAttachments", java.lang.String.class, long.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateKBArticleMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticle", long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String[].class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateKBArticlesPrioritiesMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKBArticlesPriorities", long.class, java.util.Map.class);
	}

	public void addAttachment(java.lang.String portletId, long resourcePrimKey,
		java.lang.String dirName, java.lang.String shortFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_addAttachmentMethodKey0,
				ClpSerializer.translateInput(portletId), resourcePrimKey,
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(shortFileName),
				ClpSerializer.translateInput(inputStream),
				ClpSerializer.translateInput(serviceContext));

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

	public com.liferay.knowledgebase.model.KBArticle addKBArticle(
		java.lang.String portletId, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKBArticleMethodKey1,
				ClpSerializer.translateInput(portletId), parentResourcePrimKey,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(sections),
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

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

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteAttachment(long companyId, long groupId,
		java.lang.String portletId, long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteAttachmentMethodKey2,
				companyId, groupId, ClpSerializer.translateInput(portletId),
				resourcePrimKey, ClpSerializer.translateInput(fileName));

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

	public void deleteKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKBArticleMethodKey3,
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

	public void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKBArticlesMethodKey4,
				groupId, ClpSerializer.translateInput(resourcePrimKeys));

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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupKBArticlesMethodKey5,
				groupId, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupKBArticlesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupKBArticlesCountMethodKey6,
				groupId, status);

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

	public java.lang.String getGroupKBArticlesRSS(int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupKBArticlesRSSMethodKey7,
				status, rssDelta,
				ClpSerializer.translateInput(rssDisplayStyle),
				ClpSerializer.translateInput(rssFormat),
				ClpSerializer.translateInput(themeDisplay));

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

	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleMethodKey8,
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

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleAndAllDescendantsMethodKey9,
				groupId, resourcePrimKey, status,
				ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getKBArticleRSS(long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleRSSMethodKey10,
				resourcePrimKey, status, rssDelta,
				ClpSerializer.translateInput(rssDisplayStyle),
				ClpSerializer.translateInput(rssFormat),
				ClpSerializer.translateInput(themeDisplay));

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

	public com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleSearchDisplayMethodKey11,
				groupId, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content), status,
				ClpSerializer.translateInput(startDate),
				ClpSerializer.translateInput(endDate), andOperator,
				ClpSerializer.translateInput(curStartValues), cur, delta,
				ClpSerializer.translateInput(orderByComparator));

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

		return (com.liferay.knowledgebase.model.KBArticleSearchDisplay)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long groupId, long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleVersionsMethodKey12,
				groupId, resourcePrimKey, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKBArticleVersionsCount(long groupId, long resourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticleVersionsCountMethodKey13,
				groupId, resourcePrimKey, status);

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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKBArticlesMethodKey14,
				groupId, ClpSerializer.translateInput(resourcePrimKeys),
				status, ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLatestKBArticleMethodKey15,
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

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSectionsKBArticlesMethodKey16,
				groupId, ClpSerializer.translateInput(sections), status, start,
				end, ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSectionsKBArticlesCountMethodKey17,
				groupId, ClpSerializer.translateInput(sections), status);

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

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSiblingKBArticlesMethodKey18,
				groupId, parentResourcePrimKey, status, start, end,
				ClpSerializer.translateInput(orderByComparator));

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

		return (java.util.List<com.liferay.knowledgebase.model.KBArticle>)ClpSerializer.translateOutput(returnObj);
	}

	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSiblingKBArticlesCountMethodKey19,
				groupId, parentResourcePrimKey, status);

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

	public void moveKBArticle(long resourcePrimKey, long parentResourcePrimKey,
		double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_moveKBArticleMethodKey20,
				resourcePrimKey, parentResourcePrimKey, priority);

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

	public void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_subscribeGroupKBArticlesMethodKey21,
				groupId, ClpSerializer.translateInput(portletId));

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

	public void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_subscribeKBArticleMethodKey22,
				groupId, resourcePrimKey);

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

	public void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeGroupKBArticlesMethodKey23,
				groupId, ClpSerializer.translateInput(portletId));

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

	public void unsubscribeKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeKBArticleMethodKey24,
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

	public java.lang.String updateAttachments(java.lang.String portletId,
		long resourcePrimKey, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateAttachmentsMethodKey25,
				ClpSerializer.translateInput(portletId), resourcePrimKey,
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

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

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long resourcePrimKey, java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKBArticleMethodKey26,
				resourcePrimKey, ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(sections),
				ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(serviceContext));

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

		return (com.liferay.knowledgebase.model.KBArticle)ClpSerializer.translateOutput(returnObj);
	}

	public void updateKBArticlesPriorities(long groupId,
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateKBArticlesPrioritiesMethodKey27,
				groupId,
				ClpSerializer.translateInput(resourcePrimKeyToPriorityMap));

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

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addAttachmentMethodKey0;
	private MethodKey _addKBArticleMethodKey1;
	private MethodKey _deleteAttachmentMethodKey2;
	private MethodKey _deleteKBArticleMethodKey3;
	private MethodKey _deleteKBArticlesMethodKey4;
	private MethodKey _getGroupKBArticlesMethodKey5;
	private MethodKey _getGroupKBArticlesCountMethodKey6;
	private MethodKey _getGroupKBArticlesRSSMethodKey7;
	private MethodKey _getKBArticleMethodKey8;
	private MethodKey _getKBArticleAndAllDescendantsMethodKey9;
	private MethodKey _getKBArticleRSSMethodKey10;
	private MethodKey _getKBArticleSearchDisplayMethodKey11;
	private MethodKey _getKBArticleVersionsMethodKey12;
	private MethodKey _getKBArticleVersionsCountMethodKey13;
	private MethodKey _getKBArticlesMethodKey14;
	private MethodKey _getLatestKBArticleMethodKey15;
	private MethodKey _getSectionsKBArticlesMethodKey16;
	private MethodKey _getSectionsKBArticlesCountMethodKey17;
	private MethodKey _getSiblingKBArticlesMethodKey18;
	private MethodKey _getSiblingKBArticlesCountMethodKey19;
	private MethodKey _moveKBArticleMethodKey20;
	private MethodKey _subscribeGroupKBArticlesMethodKey21;
	private MethodKey _subscribeKBArticleMethodKey22;
	private MethodKey _unsubscribeGroupKBArticlesMethodKey23;
	private MethodKey _unsubscribeKBArticleMethodKey24;
	private MethodKey _updateAttachmentsMethodKey25;
	private MethodKey _updateKBArticleMethodKey26;
	private MethodKey _updateKBArticlesPrioritiesMethodKey27;
}