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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ArticlePersistenceImpl
 * @see ArticleUtil
 * @generated
 */
public interface ArticlePersistence extends BasePersistence<Article> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArticleUtil} to access the article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the article in the entity cache if it is enabled.
	*
	* @param article the article to cache
	*/
	public void cacheResult(com.liferay.knowledgebase.model.Article article);

	/**
	* Caches the articles in the entity cache if it is enabled.
	*
	* @param articles the articles to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Article> articles);

	/**
	* Creates a new article with the primary key. Does not add the article to the database.
	*
	* @param articleId the primary key for the new article
	* @return the new article
	*/
	public com.liferay.knowledgebase.model.Article create(long articleId);

	/**
	* Removes the article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param articleId the primary key of the article to remove
	* @return the article that was removed
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article remove(long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article updateImpl(
		com.liferay.knowledgebase.model.Article article, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param articleId the primary key of the article to find
	* @return the article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByPrimaryKey(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param articleId the primary key of the article to find
	* @return the article, or <code>null</code> if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article fetchByPrimaryKey(
		long articleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.knowledgebase.model.Article[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchArticleException} if it could not be found.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the article where resourcePrimKey = &#63; and version = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the matching article, or <code>null</code> if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long resourcePrimKey, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_L_First(
		long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_L_Last(
		long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByR_L_PrevAndNext(
		long articleId, long resourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L(
		long[] resourcePrimKeies, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_S(
		long resourcePrimKey, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_S_First(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_S_Last(
		long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByR_S_PrevAndNext(
		long articleId, long resourcePrimKey, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_L_First(
		long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where groupId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_L_Last(
		long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByG_L_PrevAndNext(
		long articleId, long groupId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L(
		long groupId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L(
		long groupId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByC_L_First(
		long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where companyId = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByC_L_Last(
		long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByC_L_PrevAndNext(
		long articleId, long companyId, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L(
		long companyId, int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long resourcePrimKey, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_L_S_First(
		long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_L_S_Last(
		long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByR_L_S_PrevAndNext(
		long articleId, long resourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_L_S(
		long[] resourcePrimKeies, int[] latests, int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_P_L_First(
		long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_P_L_Last(
		long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByG_P_L_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long parentResourcePrimKey, int latest, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_L_S_First(
		long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_L_S_Last(
		long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByG_L_S_PrevAndNext(
		long articleId, long groupId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_L_S(
		long groupId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_L_S(
		long groupId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByC_L_S_First(
		long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByC_L_S_Last(
		long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByC_L_S_PrevAndNext(
		long articleId, long companyId, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByC_L_S(
		long companyId, int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_G_P_L_First(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_G_P_L_Last(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByR_G_P_L_PrevAndNext(
		long articleId, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_P_L_S_First(
		long groupId, long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByG_P_L_S_Last(
		long groupId, long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByG_P_L_S_PrevAndNext(
		long articleId, long groupId, long parentResourcePrimKey, int latest,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long parentResourcePrimKey, int latest, int status,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByG_P_L_S(
		long groupId, long[] parentResourcePrimKeies, int[] latests,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_G_P_L_S_First(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a matching article could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article findByR_G_P_L_S_Last(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the articles before and after the current article in the ordered set where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param articleId the primary key of the current article
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next article
	* @throws com.liferay.knowledgebase.NoSuchArticleException if a article with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Article[] findByR_G_P_L_S_PrevAndNext(
		long articleId, long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long resourcePrimKey, long groupId, long parentResourcePrimKey,
		int latest, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @return the range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @param start the lower bound of the range of articles to return
	* @param end the upper bound of the range of articles to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> filterFindByR_G_P_L_S(
		long[] resourcePrimKeies, long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the articles.
	*
	* @return the articles
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Article> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the article where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the article where resourcePrimKey = &#63; and version = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_L(long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where groupId = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where companyId = &#63; and latest = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_L(long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_L_S(long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where groupId = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where companyId = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_L_S(long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63; from the database.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the articles from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and version = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param version the version to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_L(long resourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_L(long[] resourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_S(long resourcePrimKey, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_L(long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_L(long groupId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_L(long groupId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where companyId = &#63; and latest = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_L(long companyId, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where companyId = &#63; and latest = any &#63;.
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_L(long companyId, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_L_S(long resourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_L_S(long[] resourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_P_L(long groupId, long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_P_L(long groupId, long[] parentResourcePrimKeies,
		int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_P_L(long groupId, long parentResourcePrimKey,
		int latest) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_P_L(long groupId, long[] parentResourcePrimKeies,
		int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_L_S(long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_L_S(long groupId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where groupId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param groupId the group ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_L_S(long groupId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where companyId = &#63; and latest = &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_L_S(long companyId, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where companyId = &#63; and latest = any &#63; and status = &#63;.
	*
	* @param companyId the company ID to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_L_S(long companyId, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByR_G_P_L(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByR_G_P_L(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int countByG_P_L_S(long groupId, long[] parentResourcePrimKeies,
		int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int filterCountByG_P_L_S(long groupId, long parentResourcePrimKey,
		int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public int filterCountByG_P_L_S(long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles
	* @throws SystemException if a system exception occurred
	*/
	public int countByR_G_P_L_S(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where resourcePrimKey = &#63; and groupId = &#63; and parentResourcePrimKey = &#63; and latest = &#63; and status = &#63;.
	*
	* @param resourcePrimKey the resource prim key to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKey the parent resource prim key to search with
	* @param latest the latest to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByR_G_P_L_S(long resourcePrimKey, long groupId,
		long parentResourcePrimKey, int latest, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the articles where resourcePrimKey = any &#63; and groupId = &#63; and parentResourcePrimKey = any &#63; and latest = any &#63; and status = &#63;.
	*
	* @param resourcePrimKeies the resource prim keies to search with
	* @param groupId the group ID to search with
	* @param parentResourcePrimKeies the parent resource prim keies to search with
	* @param latests the latests to search with
	* @param status the status to search with
	* @return the number of matching articles that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByR_G_P_L_S(long[] resourcePrimKeies, long groupId,
		long[] parentResourcePrimKeies, int[] latests, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the articles.
	*
	* @return the number of articles
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}