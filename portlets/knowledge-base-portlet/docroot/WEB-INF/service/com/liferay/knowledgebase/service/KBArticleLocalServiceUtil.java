/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KBArticle. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBArticleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBArticleLocalService
 * @see com.liferay.knowledgebase.service.base.KBArticleLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBArticleLocalServiceImpl
 * @generated
 */
public class KBArticleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBArticleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b article to the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle addKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKBArticle(kbArticle);
	}

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public static com.liferay.knowledgebase.model.KBArticle createKBArticle(
		long kbArticleId) {
		return getService().createKBArticle(kbArticleId);
	}

	/**
	* Deletes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article that was removed
	* @throws PortalException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle deleteKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBArticle(kbArticleId);
	}

	/**
	* Deletes the k b article from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle deleteKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKBArticle(kbArticle);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.knowledgebase.model.KBArticle fetchKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBArticle(kbArticleId);
	}

	/**
	* Returns the k b article with the matching UUID and company.
	*
	* @param uuid the k b article's UUID
	* @param companyId the primary key of the company
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchKBArticleByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBArticleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchKBArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKBArticleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the k b article with the primary key.
	*
	* @param kbArticleId the primary key of the k b article
	* @return the k b article
	* @throws PortalException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticle(kbArticleId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the k b article with the matching UUID and company.
	*
	* @param uuid the k b article's UUID
	* @param companyId the primary key of the company
	* @return the matching k b article
	* @throws PortalException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle getKBArticleByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticleByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the k b article matching the UUID and group.
	*
	* @param uuid the k b article's UUID
	* @param groupId the primary key of the group
	* @return the matching k b article
	* @throws PortalException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle getKBArticleByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b articles
	* @param end the upper bound of the range of k b articles (not inclusive)
	* @return the range of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticles(start, end);
	}

	/**
	* Returns the number of k b articles.
	*
	* @return the number of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int getKBArticlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticlesCount();
	}

	/**
	* Updates the k b article in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbArticle the k b article
	* @return the k b article that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKBArticle(kbArticle);
	}

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

	public static void addAttachment(java.lang.String dirName,
		java.lang.String shortFileName, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAttachment(dirName, shortFileName, inputStream, serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBArticle addKBArticle(
		long userId, long parentResourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String[] sections, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKBArticle(userId, parentResourcePrimKey, title, content,
			description, sections, dirName, serviceContext);
	}

	public static void addKBArticleResources(
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBArticleResources(kbArticle, addGroupPermissions,
			addGuestPermissions);
	}

	public static void addKBArticleResources(
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBArticleResources(kbArticle, groupPermissions, guestPermissions);
	}

	public static void addKBArticleResources(long kbArticleId,
		boolean addGroupPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBArticleResources(kbArticleId, addGroupPermissions,
			addGuestPermissions);
	}

	public static void addKBArticleResources(long kbArticleId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addKBArticleResources(kbArticleId, groupPermissions,
			guestPermissions);
	}

	public static void checkAttachments()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().checkAttachments();
	}

	public static void deleteAttachment(long companyId,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAttachment(companyId, fileName);
	}

	public static void deleteGroupKBArticles(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGroupKBArticles(groupId);
	}

	public static void deleteKBArticles(long[] resourcePrimKeys)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKBArticles(resourcePrimKeys);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getCompanyKBArticles(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCompanyKBArticles(companyId, status, start, end,
			orderByComparator);
	}

	public static int getCompanyKBArticlesCount(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCompanyKBArticlesCount(companyId, status);
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

	public static com.liferay.knowledgebase.model.KBArticle getKBArticle(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticle(resourcePrimKey, version);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleAndAllDescendants(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleAndAllDescendants(resourcePrimKey, status,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticles(
		long[] resourcePrimKeys, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticles(resourcePrimKeys, status, orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getKBArticleVersions(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKBArticleVersions(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	public static int getKBArticleVersionsCount(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKBArticleVersionsCount(resourcePrimKey, status);
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

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> getSiblingKBArticles(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSiblingKBArticles(groupId, parentResourcePrimKey,
			status, start, end, orderByComparator);
	}

	public static int getSiblingKBArticlesCount(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSiblingKBArticlesCount(groupId, parentResourcePrimKey,
			status);
	}

	public static void moveKBArticle(long userId, long resourcePrimKey,
		long parentResourcePrimKey, double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.moveKBArticle(userId, resourcePrimKey, parentResourcePrimKey,
			priority);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> search(
		long groupId, java.lang.String title, java.lang.String content,
		int status, java.util.Date startDate, java.util.Date endDate,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(groupId, title, content, status, startDate, endDate,
			andOperator, start, end, orderByComparator);
	}

	public static void subscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribeGroupKBArticles(userId, groupId);
	}

	public static void subscribeKBArticle(long userId, long groupId,
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().subscribeKBArticle(userId, groupId, resourcePrimKey);
	}

	public static void unsubscribeGroupKBArticles(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeGroupKBArticles(userId, groupId);
	}

	public static void unsubscribeKBArticle(long userId, long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsubscribeKBArticle(userId, resourcePrimKey);
	}

	public static java.lang.String updateAttachments(long resourcePrimKey,
		java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAttachments(resourcePrimKey, dirName, serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateKBArticle(
		long userId, long resourcePrimKey, java.lang.String title,
		java.lang.String content, java.lang.String description,
		java.lang.String[] sections, java.lang.String dirName,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKBArticle(userId, resourcePrimKey, title, content,
			description, sections, dirName, serviceContext);
	}

	public static void updateKBArticleAsset(long userId,
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateKBArticleAsset(userId, kbArticle, assetCategoryIds,
			assetTagNames);
	}

	public static void updateKBArticleResources(
		com.liferay.knowledgebase.model.KBArticle kbArticle,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateKBArticleResources(kbArticle, groupPermissions,
			guestPermissions);
	}

	public static void updateKBArticlesPriorities(
		java.util.Map<java.lang.Long, java.lang.Double> resourcePrimKeyToPriorityMap)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateKBArticlesPriorities(resourcePrimKeyToPriorityMap);
	}

	public static void updatePriority(long resourcePrimKey, double priority)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updatePriority(resourcePrimKey, priority);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateStatus(
		long userId, long resourcePrimKey, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, resourcePrimKey, status, serviceContext);
	}

	public static void updateViewCount(long userId, long resourcePrimKey,
		int viewCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateViewCount(userId, resourcePrimKey, viewCount);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBArticleLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBArticleLocalService.class.getName());

			if (invokableLocalService instanceof KBArticleLocalService) {
				_service = (KBArticleLocalService)invokableLocalService;
			}
			else {
				_service = new KBArticleLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KBArticleLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KBArticleLocalService service) {
	}

	private static KBArticleLocalService _service;
}