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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.model.Article;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the article service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticlePersistence
 * @see ArticlePersistenceImpl
 * @generated
 */
public class ArticleUtil {
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
	* Creates a new article with the primary key.
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
	* @param orderByComparator the comparator to order the results by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param groupId the group id to search with
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
	* @param groupId the group id to search with
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
	* @param groupId the group id to search with
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
	* @param orderByComparator the comparator to order the results by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param orderByComparator the comparator to order the set by
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
	* @param orderByComparator the comparator to order the results by
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
	* @param groupId the group id to search with
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
	* @param groupId the group id to search with
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
			_persistence = (ArticlePersistence)PortletBeanLocatorUtil.locate(com.liferay.knowledgebase.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					ArticlePersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(ArticlePersistence persistence) {
		_persistence = persistence;
	}

	private static ArticlePersistence _persistence;
}