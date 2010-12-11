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
public class ArticleLocalServiceClp implements ArticleLocalService {
	public ArticleLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addArticleMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addArticle", com.liferay.knowledgebase.model.Article.class);

		_createArticleMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createArticle", long.class);

		_deleteArticleMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteArticle", long.class);

		_deleteArticleMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteArticle", com.liferay.knowledgebase.model.Article.class);

		_dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class);

		_dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQueryCount",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_getArticleMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticle", long.class);

		_getArticleByUuidAndGroupIdMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticleByUuidAndGroupId", java.lang.String.class, long.class);

		_getArticlesMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticles", int.class, int.class);

		_getArticlesCountMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticlesCount");

		_updateArticleMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateArticle", com.liferay.knowledgebase.model.Article.class);

		_updateArticleMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateArticle", com.liferay.knowledgebase.model.Article.class,
				boolean.class);

		_addArticleMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"addArticle", long.class, long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, int.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_addArticleResourcesMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"addArticleResources",
				com.liferay.knowledgebase.model.Article.class, boolean.class,
				boolean.class);

		_addArticleResourcesMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"addArticleResources",
				com.liferay.knowledgebase.model.Article.class,
				java.lang.String[].class, java.lang.String[].class);

		_addAttachmentMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"addAttachment", long.class, java.lang.String.class,
				java.lang.String.class, java.io.InputStream.class);

		_checkAttachmentsMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"checkAttachments");

		_deleteAttachmentMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteAttachment", long.class, java.lang.String.class);

		_deleteGroupArticlesMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteGroupArticles", long.class);

		_getArticleMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticle", long.class, int.class);

		_getArticlesMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticles", long.class, int.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getArticlesMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticles", java.util.Map.class, boolean.class, int.class,
				int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getArticlesCountMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticlesCount", long.class, int.class);

		_getArticlesCountMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"getArticlesCount", java.util.Map.class, boolean.class);

		_getCompanyArticlesMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyArticles", long.class, int.class, boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getCompanyArticlesCountMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyArticlesCount", long.class, int.class, boolean.class);

		_getGroupArticlesMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupArticles", long.class, int.class, boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getGroupArticlesCountMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupArticlesCount", long.class, int.class, boolean.class);

		_getLatestArticleMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
				"getLatestArticle", long.class, int.class);

		_subscribeMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
				"subscribe", long.class, long.class, long.class,
				java.lang.String.class, long.class);

		_unsubscribeMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribe", long.class, long.class, java.lang.String.class,
				long.class);

		_unsubscribeAllPortletsMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
				"unsubscribeAllPortlets", long.class, long.class);

		_updateArticleMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateArticle", long.class, long.class, long.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, int.class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateArticleResourcesMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateArticleResources",
				com.liferay.knowledgebase.model.Article.class,
				java.lang.String[].class, java.lang.String[].class);

		_updateAssetMethodKey36 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateAsset", long.class,
				com.liferay.knowledgebase.model.Article.class, long[].class,
				java.lang.String[].class);

		_updateAttachmentsMethodKey37 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateAttachments", long.class, long.class,
				java.lang.String.class);

		_updateDisplayOrderMethodKey38 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateDisplayOrder",
				com.liferay.knowledgebase.model.Article.class, long.class,
				int.class);

		_updateStatusMethodKey39 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateStatus", long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.knowledgebase.model.Article addArticle(
		com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addArticleMethodKey0,
				ClpSerializer.translateInput(article));

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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.Article createArticle(long articleId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createArticleMethodKey1,
				articleId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteArticle(long articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteArticleMethodKey2,
				articleId);

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

	public void deleteArticle(com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteArticleMethodKey3,
				ClpSerializer.translateInput(article));

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

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				ClpSerializer.translateInput(dynamicQuery));

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				ClpSerializer.translateInput(dynamicQuery), start, end);

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				ClpSerializer.translateInput(dynamicQuery), start, end,
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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				ClpSerializer.translateInput(dynamicQuery));

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

		return ((Long)returnObj).longValue();
	}

	public com.liferay.knowledgebase.model.Article getArticle(long articleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticleMethodKey8,
				articleId);

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

	public com.liferay.knowledgebase.model.Article getArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticleByUuidAndGroupIdMethodKey9,
				ClpSerializer.translateInput(uuid), groupId);

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
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesMethodKey10,
				start, end);

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

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getArticlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesCountMethodKey11);

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

	public com.liferay.knowledgebase.model.Article updateArticle(
		com.liferay.knowledgebase.model.Article article)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateArticleMethodKey12,
				ClpSerializer.translateInput(article));

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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.Article updateArticle(
		com.liferay.knowledgebase.model.Article article, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateArticleMethodKey13,
				ClpSerializer.translateInput(article), merge);

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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.knowledgebase.model.Article addArticle(long userId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addArticleMethodKey14,
				userId, parentResourcePrimKey,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description), priority,
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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public void addArticleResources(
		com.liferay.knowledgebase.model.Article article,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_addArticleResourcesMethodKey15,
				ClpSerializer.translateInput(article), addCommunityPermissions,
				addGuestPermissions);

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

	public void addArticleResources(
		com.liferay.knowledgebase.model.Article article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_addArticleResourcesMethodKey16,
				ClpSerializer.translateInput(article),
				ClpSerializer.translateInput(communityPermissions),
				ClpSerializer.translateInput(guestPermissions));

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

	public void addAttachment(long companyId, java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_addAttachmentMethodKey17,
				companyId, ClpSerializer.translateInput(dirName),
				ClpSerializer.translateInput(shortFileName),
				ClpSerializer.translateInput(inputStream));

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

	public void checkAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_checkAttachmentsMethodKey18);

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

	public void deleteAttachment(long companyId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteAttachmentMethodKey19,
				companyId, ClpSerializer.translateInput(fileName));

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

	public void deleteGroupArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteGroupArticlesMethodKey20,
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

	public com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticleMethodKey21,
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
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesMethodKey22,
				resourcePrimKey, status, start, end,
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

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesMethodKey23,
				ClpSerializer.translateInput(params), allVersions, start, end,
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

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getArticlesCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getArticlesCountMethodKey24,
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

		MethodHandler methodHandler = new MethodHandler(_getArticlesCountMethodKey25,
				ClpSerializer.translateInput(params), allVersions);

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

	public java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, int status, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyArticlesMethodKey26,
				companyId, status, allVersions, start, end,
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

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getCompanyArticlesCount(long companyId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyArticlesCountMethodKey27,
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
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupArticlesMethodKey28,
				groupId, status, allVersions, start, end,
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

		return (java.util.List<com.liferay.knowledgebase.model.Article>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupArticlesCount(long groupId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupArticlesCountMethodKey29,
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

	public com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getLatestArticleMethodKey30,
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

	public void subscribe(long companyId, long groupId, long userId,
		java.lang.String portletId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_subscribeMethodKey31,
				companyId, groupId, userId,
				ClpSerializer.translateInput(portletId), classPK);

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

	public void unsubscribe(long companyId, long userId,
		java.lang.String portletId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeMethodKey32,
				companyId, userId, ClpSerializer.translateInput(portletId),
				classPK);

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

	public void unsubscribeAllPortlets(long companyId, long subscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_unsubscribeAllPortletsMethodKey33,
				companyId, subscriptionId);

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

	public com.liferay.knowledgebase.model.Article updateArticle(long userId,
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateArticleMethodKey34,
				userId, resourcePrimKey, parentResourcePrimKey,
				ClpSerializer.translateInput(title),
				ClpSerializer.translateInput(content),
				ClpSerializer.translateInput(description), priority,
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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public void updateArticleResources(
		com.liferay.knowledgebase.model.Article article,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateArticleResourcesMethodKey35,
				ClpSerializer.translateInput(article),
				ClpSerializer.translateInput(communityPermissions),
				ClpSerializer.translateInput(guestPermissions));

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

	public void updateAsset(long userId,
		com.liferay.knowledgebase.model.Article article,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_updateAssetMethodKey36,
				userId, ClpSerializer.translateInput(article),
				ClpSerializer.translateInput(assetCategoryIds),
				ClpSerializer.translateInput(assetTagNames));

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

	public java.lang.String updateAttachments(long companyId,
		long resourcePrimKey, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateAttachmentsMethodKey37,
				companyId, resourcePrimKey,
				ClpSerializer.translateInput(dirName));

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

	public com.liferay.knowledgebase.model.Article updateDisplayOrder(
		com.liferay.knowledgebase.model.Article article,
		long parentResourcePrimKey, int priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateDisplayOrderMethodKey38,
				ClpSerializer.translateInput(article), parentResourcePrimKey,
				priority);

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

	public com.liferay.knowledgebase.model.Article updateStatus(long userId,
		long resourcePrimKey, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateStatusMethodKey39,
				userId, resourcePrimKey, status,
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

		return (com.liferay.knowledgebase.model.Article)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addArticleMethodKey0;
	private MethodKey _createArticleMethodKey1;
	private MethodKey _deleteArticleMethodKey2;
	private MethodKey _deleteArticleMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _getArticleMethodKey8;
	private MethodKey _getArticleByUuidAndGroupIdMethodKey9;
	private MethodKey _getArticlesMethodKey10;
	private MethodKey _getArticlesCountMethodKey11;
	private MethodKey _updateArticleMethodKey12;
	private MethodKey _updateArticleMethodKey13;
	private MethodKey _addArticleMethodKey14;
	private MethodKey _addArticleResourcesMethodKey15;
	private MethodKey _addArticleResourcesMethodKey16;
	private MethodKey _addAttachmentMethodKey17;
	private MethodKey _checkAttachmentsMethodKey18;
	private MethodKey _deleteAttachmentMethodKey19;
	private MethodKey _deleteGroupArticlesMethodKey20;
	private MethodKey _getArticleMethodKey21;
	private MethodKey _getArticlesMethodKey22;
	private MethodKey _getArticlesMethodKey23;
	private MethodKey _getArticlesCountMethodKey24;
	private MethodKey _getArticlesCountMethodKey25;
	private MethodKey _getCompanyArticlesMethodKey26;
	private MethodKey _getCompanyArticlesCountMethodKey27;
	private MethodKey _getGroupArticlesMethodKey28;
	private MethodKey _getGroupArticlesCountMethodKey29;
	private MethodKey _getLatestArticleMethodKey30;
	private MethodKey _subscribeMethodKey31;
	private MethodKey _unsubscribeMethodKey32;
	private MethodKey _unsubscribeAllPortletsMethodKey33;
	private MethodKey _updateArticleMethodKey34;
	private MethodKey _updateArticleResourcesMethodKey35;
	private MethodKey _updateAssetMethodKey36;
	private MethodKey _updateAttachmentsMethodKey37;
	private MethodKey _updateDisplayOrderMethodKey38;
	private MethodKey _updateStatusMethodKey39;
}