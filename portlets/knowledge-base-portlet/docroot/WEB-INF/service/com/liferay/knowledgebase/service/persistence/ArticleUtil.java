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
 * @author    Brian Wing Shun Chan
 * @see       ArticlePersistence
 * @see       ArticlePersistenceImpl
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

	public static void cacheResult(
		com.liferay.knowledgebase.model.Article article) {
		getPersistence().cacheResult(article);
	}

	public static void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Article> articles) {
		getPersistence().cacheResult(articles);
	}

	public static com.liferay.knowledgebase.model.Article create(long articleId) {
		return getPersistence().create(articleId);
	}

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

	public static com.liferay.knowledgebase.model.Article findByPrimaryKey(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(articleId);
	}

	public static com.liferay.knowledgebase.model.Article fetchByPrimaryKey(
		long articleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(articleId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(articleId, uuid, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	public static com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	public static com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end,
			orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_First(resourcePrimKey,
			orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_Last(resourcePrimKey,
			orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(articleId,
			resourcePrimKey, orderByComparator);
	}

	public static com.liferay.knowledgebase.model.Article findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByR_V(resourcePrimKey, version);
	}

	public static com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByR_V(resourcePrimKey, version);
	}

	public static com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByR_V(resourcePrimKey, version, retrieveFromCache);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	public static void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	public static void removeByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByR_V(resourcePrimKey, version);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	public static int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	public static int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByR_V(resourcePrimKey, version);
	}

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