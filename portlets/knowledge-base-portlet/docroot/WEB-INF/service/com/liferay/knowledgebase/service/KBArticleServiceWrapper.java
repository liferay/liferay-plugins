/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link KBArticleService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBArticleService
 * @generated
 */
public class KBArticleServiceWrapper implements KBArticleService {
	public KBArticleServiceWrapper(KBArticleService kbArticleService) {
		_kbArticleService = kbArticleService;
	}

	public void addAttachment(java.lang.String portletId, long resourcePrimKey,
		java.lang.String dirName, java.lang.String shortFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.addAttachment(portletId, resourcePrimKey, dirName,
			shortFileName, inputStream, serviceContext);
	}

	public com.liferay.knowledgebase.model.KBArticle addKBArticle(
		java.lang.String portletId, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.addKBArticle(portletId, parentResourcePrimKey,
			title, content, description, sections, dirName, serviceContext);
	}

	public void deleteAttachment(long companyId, long groupId,
		java.lang.String portletId, long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.deleteAttachment(companyId, groupId, portletId,
			resourcePrimKey, fileName);
	}

	public void deleteKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.deleteKBArticle(resourcePrimKey);
	}

	public void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.deleteKBArticles(groupId, resourcePrimKeys);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getGroupKBArticles(groupId, status, start,
			end, orderByComparator);
	}

	public int getGroupKBArticlesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getGroupKBArticlesCount(groupId, status);
	}

	public java.lang.String getGroupKBArticlesRSS(int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getGroupKBArticlesRSS(status, rssDelta,
			rssDisplayStyle, rssFormat, themeDisplay);
	}

	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticle(resourcePrimKey, version);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticleAndAllDescendants(groupId,
			resourcePrimKey, status, orderByComparator);
	}

	public java.lang.String getKBArticleRSS(long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticleRSS(resourcePrimKey, status,
			rssDelta, rssDisplayStyle, rssFormat, themeDisplay);
	}

	public com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticleSearchDisplay(groupId, title,
			content, status, startDate, endDate, andOperator, curStartValues,
			cur, delta, orderByComparator);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long groupId, long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticleVersions(groupId, resourcePrimKey,
			status, start, end, orderByComparator);
	}

	public int getKBArticleVersionsCount(long groupId, long resourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticleVersionsCount(groupId,
			resourcePrimKey, status);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getKBArticles(groupId, resourcePrimKeys,
			status, orderByComparator);
	}

	public com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getLatestKBArticle(resourcePrimKey, status);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getSectionsKBArticles(groupId, sections,
			status, start, end, orderByComparator);
	}

	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getSectionsKBArticlesCount(groupId, sections,
			status);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getSiblingKBArticles(groupId,
			parentResourcePrimKey, status, start, end, orderByComparator);
	}

	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.getSiblingKBArticlesCount(groupId,
			parentResourcePrimKey, status);
	}

	public void moveKBArticle(long resourcePrimKey, long parentResourcePrimKey,
		double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.moveKBArticle(resourcePrimKey, parentResourcePrimKey,
			priority);
	}

	public void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.subscribeGroupKBArticles(groupId, portletId);
	}

	public void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.subscribeKBArticle(groupId, resourcePrimKey);
	}

	public void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.unsubscribeGroupKBArticles(groupId, portletId);
	}

	public void unsubscribeKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.unsubscribeKBArticle(resourcePrimKey);
	}

	public java.lang.String updateAttachments(java.lang.String portletId,
		long resourcePrimKey, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.updateAttachments(portletId, resourcePrimKey,
			dirName, serviceContext);
	}

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long resourcePrimKey, java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticleService.updateKBArticle(resourcePrimKey, title,
			content, description, sections, dirName, serviceContext);
	}

	public void updateKBArticlesPriorities(long groupId,
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbArticleService.updateKBArticlesPriorities(groupId,
			resourcePrimKeyToPriorityMap);
	}

	public KBArticleService getWrappedKBArticleService() {
		return _kbArticleService;
	}

	public void setWrappedKBArticleService(KBArticleService kbArticleService) {
		_kbArticleService = kbArticleService;
	}

	private KBArticleService _kbArticleService;
}