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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the article remote service. This utility wraps {@link com.liferay.knowledgebase.service.impl.ArticleServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticleService
 * @see com.liferay.knowledgebase.service.base.ArticleServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.ArticleServiceImpl
 * @generated
 */
public class ArticleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.ArticleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.knowledgebase.model.Article addArticle(
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description, int priority,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addArticle(parentResourcePrimKey, title, content,
			description, priority, dirName, serviceContext);
	}

	public static void addAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAttachment(companyId, groupId, resourcePrimKey, dirName,
			shortFileName, inputStream);
	}

	public static void deleteArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteArticle(resourcePrimKey);
	}

	public static void deleteAttachment(long companyId, long groupId,
		long resourcePrimKey, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAttachment(companyId, groupId, resourcePrimKey, fileName);
	}

	public static com.liferay.knowledgebase.model.Article getArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getArticle(resourcePrimKey, version);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArticles(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getArticles(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArticles(params, allVersions, start, end,
			orderByComparator);
	}

	public static int getArticlesCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getArticlesCount(resourcePrimKey, status);
	}

	public static int getArticlesCount(
		java.util.Map<java.lang.String, java.lang.Object> params,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getArticlesCount(params, allVersions);
	}

	public static java.lang.String getArticlesRSS(java.lang.String portletId,
		long resourcePrimKey, int status, int max, java.lang.String type,
		double version, java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getArticlesRSS(portletId, resourcePrimKey, status, max,
			type, version, displayStyle, maximized, themeDisplay);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getCompanyArticles(
		long companyId, int status, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanyArticles(companyId, status, allVersions, start,
			end, orderByComparator);
	}

	public static int getCompanyArticlesCount(long companyId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanyArticlesCount(companyId, status, allVersions);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> getGroupArticles(
		long groupId, int status, boolean allVersions, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupArticles(groupId, status, allVersions, start, end,
			orderByComparator);
	}

	public static int getGroupArticlesCount(long groupId, int status,
		boolean allVersions)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupArticlesCount(groupId, status, allVersions);
	}

	public static java.lang.String getGroupArticlesRSS(
		java.lang.String portletId, int status, int max, java.lang.String type,
		double version, java.lang.String displayStyle, boolean maximized,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupArticlesRSS(portletId, status, max, type, version,
			displayStyle, maximized, themeDisplay);
	}

	public static com.liferay.knowledgebase.model.Article getLatestArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestArticle(resourcePrimKey, status);
	}

	public static void subscribe(long companyId, long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribe(companyId, groupId, portletId);
	}

	public static void subscribeArticle(long companyId, long groupId,
		java.lang.String portletId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.subscribeArticle(companyId, groupId, portletId, resourcePrimKey);
	}

	public static void unsubscribe(long companyId, long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribe(companyId, groupId, portletId);
	}

	public static void unsubscribeArticle(long companyId,
		java.lang.String portletId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeArticle(companyId, portletId, resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.Article updateArticle(
		long resourcePrimKey, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, int priority, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateArticle(resourcePrimKey, parentResourcePrimKey,
			title, content, description, priority, dirName, serviceContext);
	}

	public static java.lang.String updateAttachments(long companyId,
		long groupId, long resourcePrimKey, java.lang.String dirName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAttachments(companyId, groupId, resourcePrimKey,
			dirName);
	}

	public static void clearService() {
		_service = null;
	}

	public static ArticleService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					ArticleService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					ArticleService.class.getName(), portletClassLoader);

			_service = new ArticleServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(ArticleServiceUtil.class,
				"_service");
			MethodCache.remove(ArticleService.class);
		}

		return _service;
	}

	public void setService(ArticleService service) {
		MethodCache.remove(ArticleService.class);

		_service = service;

		ReferenceRegistry.registerReference(ArticleServiceUtil.class, "_service");
		MethodCache.remove(ArticleService.class);
	}

	private static ArticleService _service;
}