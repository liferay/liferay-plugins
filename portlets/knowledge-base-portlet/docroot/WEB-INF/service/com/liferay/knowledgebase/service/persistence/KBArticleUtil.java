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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.model.KBArticle;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the k b article service. This utility wraps {@link KBArticlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBArticlePersistence
 * @see KBArticlePersistenceImpl
 * @generated
 */
public class KBArticleUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KBArticle kbArticle) {
		getPersistence().clearCache(kbArticle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KBArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KBArticle remove(KBArticle kbArticle)
		throws SystemException {
		return getPersistence().remove(kbArticle);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KBArticle update(KBArticle kbArticle, boolean merge)
		throws SystemException {
		return getPersistence().update(kbArticle, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KBArticle update(KBArticle kbArticle, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kbArticle, merge, serviceContext);
	}

	/**
	* Caches the k b article in the entity cache if it is enabled.
	*
	* @param kbArticle the k b article to cache
	*/
	public static void cacheResult(
		com.liferay.knowledgebase.model.KBArticle kbArticle) {
		getPersistence().cacheResult(kbArticle);
	}

	/**
	* Caches the k b articles in the entity cache if it is enabled.
	*
	* @param kbArticles the k b articles to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.KBArticle> kbArticles) {
		getPersistence().cacheResult(kbArticles);
	}

	/**
	* Creates a new k b article with the primary key. Does not add the k b article to the database.
	*
	* @param kbArticleId the primary key for the new k b article
	* @return the new k b article
	*/
	public static com.liferay.knowledgebase.model.KBArticle create(
		long kbArticleId) {
		return getPersistence().create(kbArticleId);
	}

	/**
	* Removes the k b article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbArticleId the primary key of the k b article to remove
	* @return the k b article that was removed
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle remove(
		long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(kbArticleId);
	}

	public static com.liferay.knowledgebase.model.KBArticle updateImpl(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kbArticle, merge);
	}

	/**
	* Finds the k b article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article to find
	* @return the k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByPrimaryKey(
		long kbArticleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(kbArticleId);
	}

	/**
	* Finds the k b article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbArticleId the primary key of the k b article to find
	* @return the k b article, or <code>null</code> if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByPrimaryKey(
		long kbArticleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kbArticleId);
	}

	/**
	* Finds all the k b articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Finds a range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByUuid_PrevAndNext(
		long kbArticleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(kbArticleId, uuid, orderByComparator);
	}

	/**
	* Finds the k b article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Finds the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Finds the k b article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByResourcePrimKey_PrevAndNext(
		long kbArticleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(kbArticleId,
			resourcePrimKey, orderByComparator);
	}

	/**
	* Finds all the k b articles where kbTemplateId = &#63;.
	*
	* @param kbTemplateId the kb template ID to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByKBTemplateId(
		long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKBTemplateId(kbTemplateId);
	}

	/**
	* Finds a range of all the k b articles where kbTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbTemplateId the kb template ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByKBTemplateId(
		long kbTemplateId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKBTemplateId(kbTemplateId, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where kbTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbTemplateId the kb template ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByKBTemplateId(
		long kbTemplateId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKBTemplateId(kbTemplateId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where kbTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbTemplateId the kb template ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByKBTemplateId_First(
		long kbTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKBTemplateId_First(kbTemplateId, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where kbTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbTemplateId the kb template ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByKBTemplateId_Last(
		long kbTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKBTemplateId_Last(kbTemplateId, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where kbTemplateId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param kbTemplateId the kb template ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByKBTemplateId_PrevAndNext(
		long kbArticleId, long kbTemplateId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKBTemplateId_PrevAndNext(kbArticleId, kbTemplateId,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G(resourcePrimKey, groupId);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G(resourcePrimKey, groupId, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G(
		long resourcePrimKey, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G(resourcePrimKey, groupId, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_First(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_First(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_Last(
		long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_Last(resourcePrimKey, groupId, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByR_G(resourcePrimKey, groupId);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G(resourcePrimKey, groupId, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G(
		long resourcePrimKey, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G(resourcePrimKey, groupId, start, end,
			orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, orderByComparator);
	}

	/**
	* Finds the k b article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_V(resourcePrimKey, version);
	}

	/**
	* Finds the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByR_V(resourcePrimKey, version);
	}

	/**
	* Finds the k b article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching k b article, or <code>null</code> if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_V(resourcePrimKey, version, retrieveFromCache);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long resourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_L_First(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_First(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_L_Last(
		long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_Last(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_PrevAndNext(kbArticleId, resourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latest);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_L(
		long[] resourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKeies, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKey, main);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKey, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long resourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M(resourcePrimKey, main, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_M_First(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_First(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_M_Last(
		long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_Last(resourcePrimKey, main, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M_PrevAndNext(kbArticleId, resourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKeies, main);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_M(resourcePrimKeies, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_M(
		long[] resourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_M(resourcePrimKeies, main, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_S_First(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_S_Last(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_PrevAndNext(kbArticleId, resourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKeies, status);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKeies, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_S(
		long[] resourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(resourcePrimKeies, status, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_L(
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L(groupId, latest, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_L_First(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_First(groupId, latest, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_L_Last(
		long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_Last(groupId, latest, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_L_PrevAndNext(
		long kbArticleId, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_PrevAndNext(kbArticleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_L(
		long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L(groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_L_PrevAndNext(
		long kbArticleId, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_PrevAndNext(kbArticleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M(groupId, main);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M(groupId, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_M(
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M(groupId, main, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_M_First(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M_First(groupId, main, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_M_Last(
		long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_M_Last(groupId, main, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_M_PrevAndNext(
		long kbArticleId, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_M_PrevAndNext(kbArticleId, groupId, main,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_M(groupId, main);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_M(groupId, main, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_M(
		long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M(groupId, main, start, end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_M_PrevAndNext(
		long kbArticleId, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_M_PrevAndNext(kbArticleId, groupId, main,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(groupId, status);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_S(groupId, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S(groupId, status, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_S_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_First(groupId, status, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_S_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_Last(groupId, status, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_S_PrevAndNext(
		long kbArticleId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_S_PrevAndNext(kbArticleId, groupId, status,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_S(groupId, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_S(groupId, status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_S(groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_S_PrevAndNext(
		long kbArticleId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_S_PrevAndNext(kbArticleId, groupId, status,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest);
	}

	/**
	* Finds a range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_L(
		long companyId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L(companyId, latest, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_L_First(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_First(companyId, latest, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_L_Last(
		long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_Last(companyId, latest, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_L_PrevAndNext(
		long kbArticleId, long companyId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_PrevAndNext(kbArticleId, companyId, latest,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_M(companyId, main);
	}

	/**
	* Finds a range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_M(companyId, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_M(
		long companyId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M(companyId, main, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_M_First(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_First(companyId, main, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_M_Last(
		long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_Last(companyId, main, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where companyId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_M_PrevAndNext(
		long kbArticleId, long companyId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_M_PrevAndNext(kbArticleId, companyId, main,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status);
	}

	/**
	* Finds a range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_S(companyId, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_S(
		long companyId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S(companyId, status, start, end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_S_First(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_First(companyId, status, orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByC_S_Last(
		long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_Last(companyId, status, orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where companyId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByC_S_PrevAndNext(
		long kbArticleId, long companyId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_S_PrevAndNext(kbArticleId, companyId, status,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long parentResourcePrimKey, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_L_First(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_First(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_L_Last(
		long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_Last(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_L_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_PrevAndNext(kbArticleId, parentResourcePrimKey,
			latest, orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKeies, latest);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_L(
		long[] parentResourcePrimKeies, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_M(parentResourcePrimKey, main);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKey, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long parentResourcePrimKey, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKey, main, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_M_First(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_First(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_M_Last(
		long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_Last(parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_M_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M_PrevAndNext(kbArticleId, parentResourcePrimKey,
			main, orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_M(parentResourcePrimKeies, main);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKeies, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_M(
		long[] parentResourcePrimKeies, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_M(parentResourcePrimKeies, main, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_S(parentResourcePrimKey, status);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKey, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long parentResourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_S_First(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_First(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByP_S_Last(
		long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_Last(parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByP_S_PrevAndNext(
		long kbArticleId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S_PrevAndNext(kbArticleId, parentResourcePrimKey,
			status, orderByComparator);
	}

	/**
	* Finds all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_S(parentResourcePrimKeies, status);
	}

	/**
	* Finds a range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKeies, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_S(
		long[] parentResourcePrimKeies, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_S(parentResourcePrimKeies, status, start, end,
			orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKey, groupId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKey, groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_L_First(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_First(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_L_Last(
		long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_Last(resourcePrimKey, groupId, latest,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, latest, orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKeies, groupId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_L(resourcePrimKeies, groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest, start,
			end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long resourcePrimKey, long groupId, boolean latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKey, groupId, latest, start,
			end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_L_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, latest, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_L(
		long[] resourcePrimKeies, long groupId, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_L(resourcePrimKeies, groupId, latest,
			start, end, orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKey, groupId, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKey, groupId, main, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_M_First(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_First(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_M_Last(
		long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_Last(resourcePrimKey, groupId, main,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, main, orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKeies, groupId, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_M(resourcePrimKeies, groupId, main, start, end,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKey, groupId, main, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long resourcePrimKey, long groupId, boolean main, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKey, groupId, main, start,
			end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_M_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, main, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main, start,
			end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_M(
		long[] resourcePrimKeies, long groupId, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_M(resourcePrimKeies, groupId, main, start,
			end, orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKey, groupId, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKey, groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_S_First(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_First(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByR_G_S_Last(
		long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_Last(resourcePrimKey, groupId, status,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByR_G_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, status, orderByComparator);
	}

	/**
	* Finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKeies, groupId, status, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_G_S(resourcePrimKeies, groupId, status, start, end,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status, start,
			end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long resourcePrimKey, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKey, groupId, status, start,
			end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByR_G_S_PrevAndNext(
		long kbArticleId, long resourcePrimKey, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S_PrevAndNext(kbArticleId, resourcePrimKey,
			groupId, status, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByR_G_S(
		long[] resourcePrimKeies, long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByR_G_S(resourcePrimKeies, groupId, status,
			start, end, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_L_First(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_First(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_L_Last(
		long groupId, long parentResourcePrimKey, boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_Last(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, boolean latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_L_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, boolean latest,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latest,
			start, end, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKey, main, start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKey, main, start,
			end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_M_First(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_First(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_M_Last(
		long groupId, long parentResourcePrimKey, boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_Last(groupId, parentResourcePrimKey, main,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, main, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_M(groupId, parentResourcePrimKeies, main, start,
			end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long parentResourcePrimKey, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKey, main,
			start, end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_M_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey,
		boolean main,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, main, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_M(
		long groupId, long[] parentResourcePrimKeies, boolean main, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_M(groupId, parentResourcePrimKeies, main,
			start, end, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status, start,
			end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKey, status, start,
			end, orderByComparator);
	}

	/**
	* Finds the first k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_First(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_First(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds the last k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching k b article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle findByG_P_S_Last(
		long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_Last(groupId, parentResourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] findByG_P_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, status, orderByComparator);
	}

	/**
	* Finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end);
	}

	/**
	* Finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long parentResourcePrimKey, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKey, status,
			start, end, orderByComparator);
	}

	/**
	* Filters the k b articles before and after the current k b article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param kbArticleId the primary key of the current k b article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a k b article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.KBArticle[] filterFindByG_P_S_PrevAndNext(
		long kbArticleId, long groupId, long parentResourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S_PrevAndNext(kbArticleId, groupId,
			parentResourcePrimKey, status, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> filterFindByG_P_S(
		long groupId, long[] parentResourcePrimKeies, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_S(groupId, parentResourcePrimKeies, status,
			start, end, orderByComparator);
	}

	/**
	* Finds all the k b articles.
	*
	* @return the k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @return the range of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the k b articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of k b articles to return
	* @param end the upper bound of the range of k b articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the k b articles where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the k b article where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Removes all the k b articles where kbTemplateId = &#63; from the database.
	*
	* @param kbTemplateId the kb template ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKBTemplateId(long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKBTemplateId(kbTemplateId);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G(resourcePrimKey, groupId);
	}

	/**
	* Removes the k b article where resourcePrimKey = &#63; and version = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_V(resourcePrimKey, version);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_L(long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_L(resourcePrimKey, latest);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_M(long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_M(resourcePrimKey, main);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_S(resourcePrimKey, status);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_L(groupId, latest);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_M(groupId, main);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_S(groupId, status);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and latest = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_L(long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_L(companyId, latest);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and main = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_M(long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_M(companyId, main);
	}

	/**
	* Removes all the k b articles where companyId = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_S(companyId, status);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_L(long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_M(long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_M(parentResourcePrimKey, main);
	}

	/**
	* Removes all the k b articles where parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_S(long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_S(parentResourcePrimKey, status);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Removes all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Removes all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Removes all the k b articles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the k b articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Counts all the k b articles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Counts all the k b articles where kbTemplateId = &#63;.
	*
	* @param kbTemplateId the kb template ID to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKBTemplateId(long kbTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKBTemplateId(kbTemplateId);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G(resourcePrimKey, groupId);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G(long resourcePrimKey, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByR_G(resourcePrimKey, groupId);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and version = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_V(resourcePrimKey, version);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long resourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKey, latest);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long[] resourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKeies, latest);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_M(long resourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_M(resourcePrimKey, main);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_M(long[] resourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_M(resourcePrimKeies, main);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(resourcePrimKey, status);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long[] resourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(resourcePrimKeies, status);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L(groupId, latest);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L(long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L(groupId, latest);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_M(groupId, main);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_M(long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_M(groupId, main);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_S(groupId, status);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_S(groupId, status);
	}

	/**
	* Counts all the k b articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L(long companyId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L(companyId, latest);
	}

	/**
	* Counts all the k b articles where companyId = &#63; and main = &#63;.
	*
	* @param companyId the company ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_M(long companyId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_M(companyId, main);
	}

	/**
	* Counts all the k b articles where companyId = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_S(long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_S(companyId, status);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKeies, latest);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_M(long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_M(parentResourcePrimKey, main);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_M(long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_M(parentResourcePrimKeies, main);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_S(long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_S(parentResourcePrimKey, status);
	}

	/**
	* Counts all the k b articles where parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_S(long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_S(parentResourcePrimKeies, status);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_L(long[] resourcePrimKeies, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_L(long resourcePrimKey, long groupId,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_L(resourcePrimKey, groupId, latest);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and latest = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_L(long[] resourcePrimKeies,
		long groupId, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_L(resourcePrimKeies, groupId, latest);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_M(long[] resourcePrimKeies, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_M(long resourcePrimKey, long groupId,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_M(resourcePrimKey, groupId, main);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and main = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param main the main to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_M(long[] resourcePrimKeies,
		long groupId, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_M(resourcePrimKeies, groupId, main);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_G_S(long[] resourcePrimKeies, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_S(long resourcePrimKey, long groupId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_S(resourcePrimKey, groupId, status);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where resourcePrimKey = any &#63; and groupId = &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param status the status to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByR_G_S(long[] resourcePrimKeies,
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByR_G_S(resourcePrimKeies, groupId, status);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId, long parentResourcePrimKey,
		boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long parentResourcePrimKey, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latest the latest to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long[] parentResourcePrimKeies, boolean latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKeies, latest);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_M(long groupId, long parentResourcePrimKey,
		boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param main the main to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_M(long groupId,
		long parentResourcePrimKey, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_M(groupId, parentResourcePrimKey, main);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and main = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param main the main to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_M(long groupId,
		long[] parentResourcePrimKeies, boolean main)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_M(groupId, parentResourcePrimKeies, main);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S(long groupId, long parentResourcePrimKey,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the number of matching k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param status the status to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S(long groupId,
		long parentResourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S(groupId, parentResourcePrimKey, status);
	}

	/**
	* Filters by the user's permissions and counts all the k b articles where groupId = &#63; and parentResourcePrimKey = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param status the status to search with
	* @return the number of matching k b articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_S(long groupId,
		long[] parentResourcePrimKeies, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_S(groupId, parentResourcePrimKeies, status);
	}

	/**
	* Counts all the k b articles.
	*
	* @return the number of k b articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KBArticlePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KBArticlePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					KBArticlePersistence.class.getName());

			ReferenceRegistry.registerReference(KBArticleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(KBArticlePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(KBArticleUtil.class, "_persistence");
	}

	private static KBArticlePersistence _persistence;
}