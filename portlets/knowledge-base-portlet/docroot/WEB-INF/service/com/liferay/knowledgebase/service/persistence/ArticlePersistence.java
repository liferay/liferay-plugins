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
 * @author    Brian Wing Shun Chan
 * @see       ArticlePersistenceImpl
 * @see       ArticleUtil
 * @generated
 */
public interface ArticlePersistence extends BasePersistence<Article> {
	public void cacheResult(com.liferay.knowledgebase.model.Article article);

	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Article> articles);

	public com.liferay.knowledgebase.model.Article create(long articleId);

	public com.liferay.knowledgebase.model.Article remove(long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article updateImpl(
		com.liferay.knowledgebase.model.Article article, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByPrimaryKey(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article fetchByPrimaryKey(
		long articleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article findByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Article fetchByR_V(
		long resourcePrimKey, int version, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.Article> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByR_V(long resourcePrimKey, int version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByR_V(long resourcePrimKey, int version)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}