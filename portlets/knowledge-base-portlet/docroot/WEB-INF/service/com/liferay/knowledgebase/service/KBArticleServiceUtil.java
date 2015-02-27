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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for KBArticle. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBArticleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleService
 * @see com.liferay.knowledgebase.service.base.KBArticleServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBArticleServiceImpl
 * @generated
 */
public class KBArticleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBArticleServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.knowledgebase.model.KBArticle addKBArticle(
		java.lang.String portletId, long parentResourceClassNameId,
		long parentResourcePrimKey, java.lang.String title,
		java.lang.String urlTitle, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBArticle(portletId, parentResourceClassNameId,
			parentResourcePrimKey, title, urlTitle, content, description,
			sourceURL, sections, selectedFileNames, serviceContext);
	}

	public static int addKBArticlesMarkdown(long groupId,
		long parentKBFolderId, java.lang.String fileName,
		boolean prioritizeByNumericalPrefix, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBArticlesMarkdown(groupId, parentKBFolderId, fileName,
			prioritizeByNumericalPrefix, inputStream, serviceContext);
	}

	public static void addTempAttachment(long groupId, long resourcePrimKey,
		java.lang.String fileName, java.lang.String tempFolderName,
		java.io.InputStream inputStream, java.lang.String mimeType)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addTempAttachment(groupId, resourcePrimKey, fileName,
			tempFolderName, inputStream, mimeType);
	}

	public static com.liferay.knowledgebase.model.KBArticle deleteKBArticle(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBArticle(resourcePrimKey);
	}

	public static void deleteKBArticles(long groupId, long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBArticles(groupId, resourcePrimKeys);
	}

	public static void deleteTempAttachment(long groupId, long resourcePrimKey,
		java.lang.String fileName, java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteTempAttachment(groupId, resourcePrimKey, fileName,
			tempFolderName);
	}

	public static com.liferay.knowledgebase.model.KBArticle fetchLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchLatestKBArticle(resourcePrimKey, status);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getGroupKBArticles(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBArticles(groupId, status, start, end,
			orderByComparator);
	}

	public static int getGroupKBArticlesCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGroupKBArticlesCount(groupId, status);
	}

	public static java.lang.String getGroupKBArticlesRSS(int status,
		int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getGroupKBArticlesRSS(status, rssDelta, rssDisplayStyle,
			rssFormat, themeDisplay);
	}

	public static com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticle(resourcePrimKey, version);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendantKBArticles(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleAndAllDescendantKBArticles(groupId,
			resourcePrimKey, status, orderByComparator);
	}

	/**
	* @deprecated As of 7.0.0, replaced by
	{@link #getKBArticleAndAllDescendantKBArticles(long, long,
	int, com.liferay.portal.kernel.util.OrderByComparator)}
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long groupId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleAndAllDescendants(groupId, resourcePrimKey,
			status, orderByComparator);
	}

	public static java.lang.String getKBArticleRSS(long resourcePrimKey,
		int status, int rssDelta, java.lang.String rssDisplayStyle,
		java.lang.String rssFormat,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleRSS(resourcePrimKey, status, rssDelta,
			rssDisplayStyle, rssFormat, themeDisplay);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticles(groupId, parentResourcePrimKey, status,
			start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long[] resourcePrimKeys, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticles(groupId, resourcePrimKeys, status, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long groupId, long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticles(groupId, resourcePrimKeys, status,
			orderByComparator);
	}

	public static int getKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticlesCount(groupId, parentResourcePrimKey, status);
	}

	public static int getKBArticlesCount(long groupId, long[] resourcePrimKeys,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticlesCount(groupId, resourcePrimKeys, status);
	}

	public static com.liferay.knowledgebase.model.KBArticleSearchDisplay getKBArticleSearchDisplay(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int[] curStartValues, int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleSearchDisplay(groupId, title, content, status,
			startDate, endDate, andOperator, curStartValues, cur, delta,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long groupId, long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleVersions(groupId, resourcePrimKey, status,
			start, end, orderByComparator);
	}

	public static int getKBArticleVersionsCount(long groupId,
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleVersionsCount(groupId, resourcePrimKey, status);
	}

	public static com.liferay.knowledgebase.model.KBArticle getLatestKBArticle(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLatestKBArticle(resourcePrimKey, status);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getSectionsKBArticles(
		long groupId, java.lang.String[] sections, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSectionsKBArticles(groupId, sections, status, start,
			end, orderByComparator);
	}

	public static int getSectionsKBArticlesCount(long groupId,
		java.lang.String[] sections, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSectionsKBArticlesCount(groupId, sections, status);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticles(long, long,
	int, int, int,
	com.liferay.portal.kernel.util.OrderByComparator)}
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSiblingKBArticles(groupId, parentResourcePrimKey,
			status, start, end, orderByComparator);
	}

	/**
	* @deprecated As of 7.0.0, replaced by {@link #getKBArticlesCount(long,
	long, int)}
	*/
	public static int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSiblingKBArticlesCount(groupId, parentResourcePrimKey,
			status);
	}

	public static java.lang.String[] getTempAttachmentNames(long groupId,
		java.lang.String tempFolderName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTempAttachmentNames(groupId, tempFolderName);
	}

	public static void moveKBArticle(long resourcePrimKey,
		long parentResourceClassNameId, long parentResourcePrimKey,
		double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.moveKBArticle(resourcePrimKey, parentResourceClassNameId,
			parentResourcePrimKey, priority);
	}

	public static void subscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribeGroupKBArticles(groupId, portletId);
	}

	public static void subscribeKBArticle(long groupId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribeKBArticle(groupId, resourcePrimKey);
	}

	public static void unsubscribeGroupKBArticles(long groupId,
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeGroupKBArticles(groupId, portletId);
	}

	public static void unsubscribeKBArticle(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeKBArticle(resourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long resourcePrimKey, java.lang.String title, java.lang.String content,
		java.lang.String description, java.lang.String sourceURL,
		java.lang.String[] sections, java.lang.String[] selectedFileNames,
		long[] removeFileEntryIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBArticle(resourcePrimKey, title, content,
			description, sourceURL, sections, selectedFileNames,
			removeFileEntryIds, serviceContext);
	}

	public static void updateKBArticlesPriorities(long groupId,
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateKBArticlesPriorities(groupId, resourcePrimKeyToPriorityMap);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBArticleService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBArticleService.class.getName());

			if (invokableService instanceof KBArticleService) {
				_service = (KBArticleService)invokableService;
			}
			else {
				_service = new KBArticleServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(KBArticleServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KBArticleService service) {
	}

	private static KBArticleService _service;
}