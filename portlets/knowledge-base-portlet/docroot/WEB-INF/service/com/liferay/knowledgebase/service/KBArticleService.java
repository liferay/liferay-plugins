/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.model.KBArticle;
import com.liferay.knowledgebase.model.KBArticleSearchDisplay;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Provides the remote service interface for KBArticle. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleServiceUtil
 * @see com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBArticleServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBArticleService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBArticleServiceUtil} to access the k b article remote service. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBArticleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public KBArticle addKBArticle(java.lang.String portletId,
		long parentResourceClassNameId, long parentResourcePrimKey,
		java.lang.String title, java.lang.String urlTitle,
		java.lang.String content, java.lang.String description,
		java.lang.String sourceURL, java.lang.String[] sections,
		java.lang.String[] selectedFileNames, ServiceContext serviceContext)
		throws PortalException;

	public int addKBArticlesMarkdown(long groupId, long parentKBFolderId,
		java.lang.String fileName, boolean prioritizeByNumericalPrefix,
		InputStream inputStream, ServiceContext serviceContext)
		throws PortalException;

	public void addTempAttachment(long groupId, long resourcePrimKey,
		java.lang.String fileName, java.lang.String tempFolderName,
		InputStream inputStream, java.lang.String mimeType)
		throws PortalException;

	public KBArticle deleteKBArticle(long resourcePrimKey)
		throws PortalException;

	public void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws PortalException;

	public void deleteTempAttachment(long groupId, long resourcePrimKey,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle fetchLatestKBArticle(long resourcePrimKey, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getGroupKBArticles(long groupId, int status,
		int start, int end, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getGroupKBArticlesCount(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getGroupKBArticlesRSS(int status, int rssDelta,
		java.lang.String rssDisplayStyle, java.lang.String rssFormat,
		ThemeDisplay themeDisplay) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getKBArticle(long resourcePrimKey, int version)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleAndAllDescendantKBArticles(
		long groupId, long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* @deprecated As of 7.0.0, replaced by {@link
	#getKBArticleAndAllDescendantKBArticles(long, long, int,
	OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleAndAllDescendants(long groupId,
		long resourcePrimKey, int status,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getKBArticleRSS(long resourcePrimKey, int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat, ThemeDisplay themeDisplay)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticleSearchDisplay getKBArticleSearchDisplay(long groupId,
		java.lang.String title, java.lang.String content, int status,
		Date startDate, Date endDate, boolean andOperator,
		int[] curStartValues, int cur, int delta,
		OrderByComparator<KBArticle> orderByComparator)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticleVersions(long groupId,
		long resourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticleVersionsCount(long groupId, long resourcePrimKey,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(long groupId, long[] resourcePrimKeys,
		int status, OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getKBArticles(long groupId, long[] resourcePrimKeys,
		int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticlesCount(long groupId, long parentResourcePrimKey,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBArticlesCount(long groupId, long[] resourcePrimKeys,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public KBArticle getLatestKBArticle(long resourcePrimKey, int status)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getSectionsKBArticles(long groupId,
		java.lang.String[] sections, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	int, int, int,
	OrderByComparator)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<KBArticle> getSiblingKBArticles(long groupId,
		long parentResourcePrimKey, int status, int start, int end,
		OrderByComparator<KBArticle> orderByComparator);

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	long, int)}
	*/
	@java.lang.Deprecated
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String[] getTempAttachmentNames(long groupId,
		java.lang.String tempFolderName) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public void moveKBArticle(long resourcePrimKey,
		long parentResourceClassNameId, long parentResourcePrimKey,
		double priority) throws PortalException;

	public KBArticle revertKBArticle(long resourcePrimKey, int version,
		ServiceContext serviceContext) throws PortalException;

	public void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId) throws PortalException;

	public void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws PortalException;

	public void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId) throws PortalException;

	public void unsubscribeKBArticle(long resourcePrimKey)
		throws PortalException;

	public KBArticle updateKBArticle(long resourcePrimKey,
		java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		long[] removeFileEntryIds, ServiceContext serviceContext)
		throws PortalException;

	public void updateKBArticlesPriorities(long groupId,
		Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws PortalException;
}