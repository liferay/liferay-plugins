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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * The interface for the k b article remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleServiceUtil
 * @see com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBArticleServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBArticleService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBArticleServiceUtil} to access the k b article remote service. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBArticleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addAttachment(java.lang.String portletId, long resourcePrimKey,
		java.lang.String dirName, java.lang.String shortFileName,
		java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.KBArticle addKBArticle(
		java.lang.String portletId, long parentResourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteAttachment(long companyId, long groupId,
		java.lang.String portletId, long resourcePrimKey,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBArticlesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getGroupKBArticlesRSS(int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getKBArticleRSS(long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long groupId, long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticleVersionsCount(long groupId, long resourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void moveKBArticle(long resourcePrimKey, long parentResourcePrimKey,
		double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void unsubscribeKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String updateAttachments(java.lang.String portletId,
		long resourcePrimKey, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long resourcePrimKey, java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String[] sections,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void updateKBArticlesPriorities(long groupId,
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}