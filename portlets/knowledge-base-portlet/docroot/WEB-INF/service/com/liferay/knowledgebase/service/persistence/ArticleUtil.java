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

import com.liferay.knowledgebase.model.Article;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the article service. This utility wraps {@link ArticlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticlePersistence
 * @see ArticlePersistenceImpl
 * @generated
 */
public class ArticleUtil {
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
	public static void clearCache(Article article) {
		getPersistence().clearCache(article);
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
	public static List<Article> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Article> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Article> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Article remove(Article article) throws SystemException {
		return getPersistence().remove(article);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Article update(Article article, boolean merge)
		throws SystemException {
		return getPersistence().update(article, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Article update(Article article, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(article, merge, serviceContext);
	}

	/**
	* Caches the article in the entity cache if it is enabled.
	*
	* @param article the article to cache
	*/
	public static void cacheResult(
		com.liferay.knowledgebase.model.Article article) {
		getPersistence().cacheResult(article);
	}

	/**
	* Caches the articles in the entity cache if it is enabled.
	*
	* @param articles the articles to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Article> articles) {
		getPersistence().cacheResult(articles);
	}

	/**
	* Creates a new article with the primary key. Does not add the article to the database.
	*
	* @param articleId the primary key for the new article
	* @return the new article
	*/
	public static com.liferay.knowledgebase.model.Article create(long articleId) {
		return getPersistence().create(articleId);
	}

	/**
	* Removes the article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param articleId the primary key of the article to remove
	* @return the article that was removed
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article remove(long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(articleId);
	}

	public static com.liferay.knowledgebase.model.Article updateImpl(
		com.liferay.knowledgebase.model.Article article, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(article, merge);
	}

	/**
	* Finds the article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param articleId the primary key of the article to find
	* @return the article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByPrimaryKey(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(articleId);
	}

	/**
	* Finds the article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param articleId the primary key of the article to find
	* @return the article, or <code>null</code> if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article fetchByPrimaryKey(
		long articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(articleId);
	}

	/**
	* Finds all the articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Finds a range of all the articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Finds an ordered range of all the articles where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(articleId, uuid, orderByComparator);
	}

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Finds all the articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(articleId,
			resourcePrimKey, orderByComparator);
	}

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_V(resourcePrimKey, version);
	}

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByR_V(resourcePrimKey, version);
	}

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_V(resourcePrimKey, version, retrieveFromCache);
	}

	/**
	* Finds all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKey, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_L_First(
		long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_First(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_L_Last(
		long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_Last(resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByR_L_PrevAndNext(
		long articleId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_PrevAndNext(articleId, resourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latests);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L(resourcePrimKeies, latests, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L(resourcePrimKeies, latests, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_S(resourcePrimKey, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S(resourcePrimKey, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_S_First(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_First(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_S_Last(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_Last(resourcePrimKey, status, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByR_S_PrevAndNext(
		long articleId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_S_PrevAndNext(articleId, resourcePrimKey, status,
			orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L(groupId, latest, start, end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_L_First(
		long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_First(groupId, latest, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_L_Last(
		long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_Last(groupId, latest, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_L_PrevAndNext(
		long articleId, long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_PrevAndNext(articleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latests);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L(groupId, latests, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L(groupId, latests, start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latest, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L(groupId, latest, start, end,
			orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_L_PrevAndNext(
		long articleId, long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_PrevAndNext(articleId, groupId, latest,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latests);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L(groupId, latests, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L(groupId, latests, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest);
	}

	/**
	* Finds a range of all the articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L(companyId, latest, start, end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByC_L_First(
		long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_First(companyId, latest, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByC_L_Last(
		long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_Last(companyId, latest, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByC_L_PrevAndNext(
		long articleId, long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_PrevAndNext(articleId, companyId, latest,
			orderByComparator);
	}

	/**
	* Finds all the articles where companyId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latests);
	}

	/**
	* Finds a range of all the articles where companyId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L(companyId, latests, start, end);
	}

	/**
	* Finds an ordered range of all the articles where companyId = &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L(companyId, latests, start, end, orderByComparator);
	}

	/**
	* Finds all the articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Finds a range of all the articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long parentResourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long parentResourcePrimKey, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByP_L_First(
		long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_First(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByP_L_Last(
		long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_Last(parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByP_L_PrevAndNext(
		long articleId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_PrevAndNext(articleId, parentResourcePrimKey,
			latest, orderByComparator);
	}

	/**
	* Finds all the articles where parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByP_L(parentResourcePrimKeies, latests);
	}

	/**
	* Finds a range of all the articles where parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long[] parentResourcePrimKeies, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latests, start, end);
	}

	/**
	* Finds an ordered range of all the articles where parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L(
		long[] parentResourcePrimKeies, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L(parentResourcePrimKeies, latests, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L_S(resourcePrimKey, latest, status);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S(resourcePrimKey, latest, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S(resourcePrimKey, latest, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_L_S_First(
		long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S_First(resourcePrimKey, latest, status,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByR_L_S_Last(
		long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S_Last(resourcePrimKey, latest, status,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByR_L_S_PrevAndNext(
		long articleId, long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S_PrevAndNext(articleId, resourcePrimKey, latest,
			status, orderByComparator);
	}

	/**
	* Finds all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_L_S(resourcePrimKeies, latests, status);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S(resourcePrimKeies, latests, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByR_L_S(resourcePrimKeies, latests, status, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R_L(groupId, resourcePrimKey, latest);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long resourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L(groupId, resourcePrimKey, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long resourcePrimKey, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L(groupId, resourcePrimKey, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_R_L_First(
		long groupId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_First(groupId, resourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_R_L_Last(
		long groupId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_Last(groupId, resourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_R_L_PrevAndNext(
		long articleId, long groupId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_PrevAndNext(articleId, groupId,
			resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_R_L(groupId, resourcePrimKeies, latests);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L(groupId, resourcePrimKeies, latests, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L(groupId, resourcePrimKeies, latests, start,
			end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long resourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKey, latest, start,
			end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long resourcePrimKey, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKey, latest, start,
			end, orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_R_L_PrevAndNext(
		long articleId, long groupId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_PrevAndNext(articleId, groupId,
			resourcePrimKey, latest, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKeies, latests);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKeies, latests,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L(
		long groupId, long[] resourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L(groupId, resourcePrimKeies, latests,
			start, end, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKey, latest, start,
			end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_P_L_First(
		long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_First(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_P_L_Last(
		long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_Last(groupId, parentResourcePrimKey, latest,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_P_L_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_PrevAndNext(articleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latests);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latests,
			start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L(groupId, parentResourcePrimKeies, latests,
			start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKey, latest,
			start, end, orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_P_L_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_PrevAndNext(articleId, groupId,
			parentResourcePrimKey, latest, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies, latests);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies,
			latests, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L(groupId, parentResourcePrimKeies,
			latests, start, end, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L_S(groupId, latest, status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L_S(groupId, latest, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_S(groupId, latest, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_L_S_First(
		long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_S_First(groupId, latest, status, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_L_S_Last(
		long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_S_Last(groupId, latest, status, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_L_S_PrevAndNext(
		long articleId, long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_S_PrevAndNext(articleId, groupId, latest, status,
			orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L_S(groupId, latests, status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_L_S(groupId, latests, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_L_S(groupId, latests, status, start, end,
			orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L_S(groupId, latest, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_S(groupId, latest, status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_S(groupId, latest, status, start, end,
			orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_L_S_PrevAndNext(
		long articleId, long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_S_PrevAndNext(articleId, groupId, latest,
			status, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByG_L_S(groupId, latests, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_S(groupId, latests, status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_L_S(groupId, latests, status, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L_S(companyId, latest, status);
	}

	/**
	* Finds a range of all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S(companyId, latest, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S(companyId, latest, status, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByC_L_S_First(
		long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S_First(companyId, latest, status,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByC_L_S_Last(
		long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S_Last(companyId, latest, status,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByC_L_S_PrevAndNext(
		long articleId, long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S_PrevAndNext(articleId, companyId, latest,
			status, orderByComparator);
	}

	/**
	* Finds all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_L_S(companyId, latests, status);
	}

	/**
	* Finds a range of all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S(companyId, latests, status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_L_S(companyId, latests, status, start, end,
			orderByComparator);
	}

	/**
	* Finds all the articles where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKey, latest, status);
	}

	/**
	* Finds a range of all the articles where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long parentResourcePrimKey, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKey, latest, status, start,
			end);
	}

	/**
	* Finds an ordered range of all the articles where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long parentResourcePrimKey, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKey, latest, status, start,
			end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByP_L_S_First(
		long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S_First(parentResourcePrimKey, latest, status,
			orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByP_L_S_Last(
		long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S_Last(parentResourcePrimKey, latest, status,
			orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByP_L_S_PrevAndNext(
		long articleId, long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S_PrevAndNext(articleId, parentResourcePrimKey,
			latest, status, orderByComparator);
	}

	/**
	* Finds all the articles where parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKeies, latests, status);
	}

	/**
	* Finds a range of all the articles where parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKeies, latests, status,
			start, end);
	}

	/**
	* Finds an ordered range of all the articles where parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByP_L_S(
		long[] parentResourcePrimKeies, int[] latests, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByP_L_S(parentResourcePrimKeies, latests, status,
			start, end, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKey, latest, status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKey, latest, status,
			start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKey, latest, status,
			start, end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_R_L_S_First(
		long groupId, long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S_First(groupId, resourcePrimKey, latest,
			status, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_R_L_S_Last(
		long groupId, long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S_Last(groupId, resourcePrimKey, latest,
			status, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_R_L_S_PrevAndNext(
		long articleId, long groupId, long resourcePrimKey, int latest,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S_PrevAndNext(articleId, groupId,
			resourcePrimKey, latest, status, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKeies, latests, status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKeies, latests, status,
			start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_R_L_S(groupId, resourcePrimKeies, latests, status,
			start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKey, latest, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKey, latest,
			status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long resourcePrimKey, int latest, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKey, latest,
			status, start, end, orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_R_L_S_PrevAndNext(
		long articleId, long groupId, long resourcePrimKey, int latest,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S_PrevAndNext(articleId, groupId,
			resourcePrimKey, latest, status, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKeies, latests,
			status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKeies, latests,
			status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_R_L_S(
		long groupId, long[] resourcePrimKeies, int[] latests, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_R_L_S(groupId, resourcePrimKeies, latests,
			status, start, end, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKey, latest, status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, start, end, orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_P_L_S_First(
		long groupId, long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S_First(groupId, parentResourcePrimKey, latest,
			status, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByG_P_L_S_Last(
		long groupId, long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S_Last(groupId, parentResourcePrimKey, latest,
			status, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByG_P_L_S_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S_PrevAndNext(articleId, groupId,
			parentResourcePrimKey, latest, status, orderByComparator);
	}

	/**
	* Finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status);
	}

	/**
	* Finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status, start, end);
	}

	/**
	* Finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status, start, end, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status, start, end, orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByG_P_L_S_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S_PrevAndNext(articleId, groupId,
			parentResourcePrimKey, latest, status, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKeies,
			latests, status);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKeies,
			latests, status, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByG_P_L_S(groupId, parentResourcePrimKeies,
			latests, status, start, end, orderByComparator);
	}

	/**
	* Finds all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest);
	}

	/**
	* Finds a range of all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, start, end);
	}

	/**
	* Finds an ordered range of all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, start, end,
			orderByComparator);
	}

	/**
	* Finds the first article in the ordered set where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByNotR_G_P_P_L_First(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L_First(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, orderByComparator);
	}

	/**
	* Finds the last article in the ordered set where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article findByNotR_G_P_P_L_Last(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L_Last(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, orderByComparator);
	}

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] findByNotR_G_P_P_L_PrevAndNext(
		long articleId, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, long priority, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNotR_G_P_P_L_PrevAndNext(articleId, resourcePrimKey,
			groupId, parentResourcePrimKey, priority, latest, orderByComparator);
	}

	/**
	* Filters by the user's permissions and finds all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest);
	}

	/**
	* Filters by the user's permissions and finds a range of all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, start, end);
	}

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> filterFindByNotR_G_P_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		long priority, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest, start, end,
			orderByComparator);
	}

	/**
	* Filters the articles before and after the current article in the ordered set where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Article[] filterFindByNotR_G_P_P_L_PrevAndNext(
		long articleId, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, long priority, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByNotR_G_P_P_L_PrevAndNext(articleId,
			resourcePrimKey, groupId, parentResourcePrimKey, priority, latest,
			orderByComparator);
	}

	/**
	* Finds all the articles.
	*
	* @return the articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of articles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the articles where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes the article where uuid = &#63; and groupId = &#63; from the database.
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
	* Removes all the articles where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Removes the article where resourcePrimKey = &#63; and version = &#63; from the database.
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
	* Removes all the articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_L(long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_L(resourcePrimKey, latest);
	}

	/**
	* Removes all the articles where resourcePrimKey = &#63; and status = &#63; from the database.
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
	* Removes all the articles where groupId = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_L(groupId, latest);
	}

	/**
	* Removes all the articles where companyId = &#63; and latest = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_L(long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_L(companyId, latest);
	}

	/**
	* Removes all the articles where parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_L(long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Removes all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByR_L_S(long resourcePrimKey, int latest,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_L_S(resourcePrimKey, latest, status);
	}

	/**
	* Removes all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_R_L(long groupId, long resourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_R_L(groupId, resourcePrimKey, latest);
	}

	/**
	* Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Removes all the articles where groupId = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_L_S(groupId, latest, status);
	}

	/**
	* Removes all the articles where companyId = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_L_S(long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_L_S(companyId, latest, status);
	}

	/**
	* Removes all the articles where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByP_L_S(long parentResourcePrimKey, int latest,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByP_L_S(parentResourcePrimKey, latest, status);
	}

	/**
	* Removes all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_R_L_S(long groupId, long resourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_R_L_S(groupId, resourcePrimKey, latest, status);
	}

	/**
	* Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByG_P_L_S(groupId, parentResourcePrimKey, latest, status);
	}

	/**
	* Removes all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByNotR_G_P_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, long priority, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest);
	}

	/**
	* Removes all the articles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Counts all the articles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Counts all the articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	/**
	* Counts all the articles where resourcePrimKey = &#63; and version = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_V(resourcePrimKey, version);
	}

	/**
	* Counts all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKey, latest);
	}

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L(long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L(resourcePrimKeies, latests);
	}

	/**
	* Counts all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_S(resourcePrimKey, status);
	}

	/**
	* Counts all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L(groupId, latest);
	}

	/**
	* Counts all the articles where groupId = &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L(long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L(groupId, latests);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L(groupId, latest);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L(long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L(groupId, latests);
	}

	/**
	* Counts all the articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L(long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L(companyId, latest);
	}

	/**
	* Counts all the articles where companyId = &#63; and latest = any &#63;.
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L(long companyId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L(companyId, latests);
	}

	/**
	* Counts all the articles where parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKey, latest);
	}

	/**
	* Counts all the articles where parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L(long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByP_L(parentResourcePrimKeies, latests);
	}

	/**
	* Counts all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L_S(long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L_S(resourcePrimKey, latest, status);
	}

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByR_L_S(long[] resourcePrimKeies, int[] latests,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_L_S(resourcePrimKeies, latests, status);
	}

	/**
	* Counts all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R_L(long groupId, long resourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R_L(groupId, resourcePrimKey, latest);
	}

	/**
	* Counts all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R_L(long groupId, long[] resourcePrimKeies,
		int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_R_L(groupId, resourcePrimKeies, latests);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R_L(long groupId, long resourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_R_L(groupId, resourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R_L(long groupId,
		long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_R_L(groupId, resourcePrimKeies, latests);
	}

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L(groupId, parentResourcePrimKeies, latests);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKey, latest);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L(long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L(groupId, parentResourcePrimKeies, latests);
	}

	/**
	* Counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L_S(groupId, latest, status);
	}

	/**
	* Counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_L_S(long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_L_S(groupId, latests, status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L_S(groupId, latest, status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_L_S(long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByG_L_S(groupId, latests, status);
	}

	/**
	* Counts all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L_S(long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L_S(companyId, latest, status);
	}

	/**
	* Counts all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_L_S(long companyId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_L_S(companyId, latests, status);
	}

	/**
	* Counts all the articles where parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L_S(long parentResourcePrimKey, int latest,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_L_S(parentResourcePrimKey, latest, status);
	}

	/**
	* Counts all the articles where parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByP_L_S(long[] parentResourcePrimKeies,
		int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByP_L_S(parentResourcePrimKeies, latests, status);
	}

	/**
	* Counts all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R_L_S(long groupId, long resourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_R_L_S(groupId, resourcePrimKey, latest, status);
	}

	/**
	* Counts all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_R_L_S(long groupId, long[] resourcePrimKeies,
		int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_R_L_S(groupId, resourcePrimKeies, latests, status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R_L_S(long groupId, long resourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_R_L_S(groupId, resourcePrimKey, latest,
			status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_R_L_S(long groupId,
		long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_R_L_S(groupId, resourcePrimKeies, latests,
			status);
	}

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L_S(groupId, parentResourcePrimKey, latest,
			status);
	}

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByG_P_L_S(groupId, parentResourcePrimKeies, latests,
			status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L_S(long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L_S(groupId, parentResourcePrimKey,
			latest, status);
	}

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByG_P_L_S(groupId, parentResourcePrimKeies,
			latests, status);
	}

	/**
	* Counts all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countByNotR_G_P_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, long priority, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest);
	}

	/**
	* Filters by the user's permissions and counts all the articles where resourcePrimKey &ne; &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and priority = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param priority the priority to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByNotR_G_P_P_L(long resourcePrimKey,
		long groupId, long parentResourcePrimKey, long priority, int latest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByNotR_G_P_P_L(resourcePrimKey, groupId,
			parentResourcePrimKey, priority, latest);
	}

	/**
	* Counts all the articles.
	*
	* @return the number of articles
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ArticlePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ArticlePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.getServletContextName(),
					ArticlePersistence.class.getName());

			ReferenceRegistry.registerReference(ArticleUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(ArticlePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(ArticleUtil.class, "_persistence");
	}

	private static ArticlePersistence _persistence;
}