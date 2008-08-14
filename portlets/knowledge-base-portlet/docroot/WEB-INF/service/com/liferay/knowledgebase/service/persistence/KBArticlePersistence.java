/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.knowledgebase.service.persistence;

/**
 * <a href="KBArticlePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticlePersistence {
	public com.liferay.knowledgebase.model.KBArticle create(long articleId);

	public com.liferay.knowledgebase.model.KBArticle remove(long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle remove(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle update(
		com.liferay.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle update(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle updateImpl(
		com.liferay.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByPrimaryKey(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByPrimaryKey(
		long articleId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByCompanyId_PrevAndNext(
		long articleId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByGroupId_PrevAndNext(
		long articleId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByParentResourcePrimKey(
		long parentResourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByParentResourcePrimKey_First(
		long parentResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByParentResourcePrimKey_Last(
		long parentResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByParentResourcePrimKey_PrevAndNext(
		long articleId, long parentResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByC_D_First(
		long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByC_D_Last(
		long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByC_D_PrevAndNext(
		long articleId, long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_First(
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_Last(
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByG_T_PrevAndNext(
		long articleId, long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_D(
		long parentResourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_D(
		long parentResourcePrimKey, boolean draft, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_D(
		long parentResourcePrimKey, boolean draft, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_D_First(
		long parentResourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_D_Last(
		long parentResourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByP_D_PrevAndNext(
		long articleId, long parentResourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H(
		long parentResourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H(
		long parentResourcePrimKey, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H(
		long parentResourcePrimKey, boolean head, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_H_First(
		long parentResourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_H_Last(
		long parentResourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByP_H_PrevAndNext(
		long articleId, long parentResourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_D(
		long resourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_D(
		long resourcePrimKey, boolean draft, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_D(
		long resourcePrimKey, boolean draft, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_D_First(
		long resourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_D_Last(
		long resourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByR_D_PrevAndNext(
		long articleId, long resourcePrimKey, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_H_First(
		long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_H_Last(
		long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByR_H_PrevAndNext(
		long articleId, long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_V(
		long resourcePrimKey, double version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, double version)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_H_T_First(
		long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_H_T_Last(
		long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByG_H_T_PrevAndNext(
		long articleId, long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_H_First(
		long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_H_Last(
		long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByG_T_H_PrevAndNext(
		long articleId, long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_V(long groupId,
		java.lang.String title, double version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByG_T_V(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H_D(
		long parentResourcePrimKey, boolean head, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H_D(
		long parentResourcePrimKey, boolean head, boolean draft, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_H_D(
		long parentResourcePrimKey, boolean head, boolean draft, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_H_D_First(
		long parentResourcePrimKey, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByP_H_D_Last(
		long parentResourcePrimKey, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByP_H_D_PrevAndNext(
		long articleId, long parentResourcePrimKey, boolean head,
		boolean draft, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H_D(
		long resourcePrimKey, boolean head, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H_D(
		long resourcePrimKey, boolean head, boolean draft, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_H_D(
		long resourcePrimKey, boolean head, boolean draft, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_H_D_First(
		long resourcePrimKey, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_H_D_Last(
		long resourcePrimKey, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByR_H_D_PrevAndNext(
		long articleId, long resourcePrimKey, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByR_V_D(
		long resourcePrimKey, double version, boolean draft)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByR_V_D(
		long resourcePrimKey, double version, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T_D(
		long groupId, boolean head, boolean template, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T_D(
		long groupId, boolean head, boolean template, boolean draft, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_H_T_D(
		long groupId, boolean head, boolean template, boolean draft, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_H_T_D_First(
		long groupId, boolean head, boolean template, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_H_T_D_Last(
		long groupId, boolean head, boolean template, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByG_H_T_D_PrevAndNext(
		long articleId, long groupId, boolean head, boolean template,
		boolean draft, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H_D(
		long groupId, java.lang.String title, boolean head, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H_D(
		long groupId, java.lang.String title, boolean head, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_T_H_D(
		long groupId, java.lang.String title, boolean head, boolean draft,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_H_D_First(
		long groupId, java.lang.String title, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_H_D_Last(
		long groupId, java.lang.String title, boolean head, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle[] findByG_T_H_D_PrevAndNext(
		long articleId, long groupId, java.lang.String title, boolean head,
		boolean draft, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle findByG_T_V_D(
		long groupId, java.lang.String title, double version, boolean draft)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticle fetchByG_T_V_D(
		long groupId, java.lang.String title, double version, boolean draft)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticle> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public void removeByParentResourcePrimKey(long parentResourcePrimKey)
		throws com.liferay.portal.SystemException;

	public void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.SystemException;

	public void removeByC_D(long companyId, boolean draft)
		throws com.liferay.portal.SystemException;

	public void removeByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public void removeByP_D(long parentResourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public void removeByP_H(long parentResourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public void removeByR_D(long resourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public void removeByR_H(long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public void removeByR_V(long resourcePrimKey, double version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public void removeByG_H_T(long groupId, boolean head, boolean template)
		throws com.liferay.portal.SystemException;

	public void removeByG_T_H(long groupId, java.lang.String title, boolean head)
		throws com.liferay.portal.SystemException;

	public void removeByG_T_V(long groupId, java.lang.String title,
		double version)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public void removeByP_H_D(long parentResourcePrimKey, boolean head,
		boolean draft) throws com.liferay.portal.SystemException;

	public void removeByR_H_D(long resourcePrimKey, boolean head, boolean draft)
		throws com.liferay.portal.SystemException;

	public void removeByR_V_D(long resourcePrimKey, double version,
		boolean draft)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public void removeByG_H_T_D(long groupId, boolean head, boolean template,
		boolean draft) throws com.liferay.portal.SystemException;

	public void removeByG_T_H_D(long groupId, java.lang.String title,
		boolean head, boolean draft) throws com.liferay.portal.SystemException;

	public void removeByG_T_V_D(long groupId, java.lang.String title,
		double version, boolean draft)
		throws com.liferay.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException;

	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException;

	public int countByParentResourcePrimKey(long parentResourcePrimKey)
		throws com.liferay.portal.SystemException;

	public int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.SystemException;

	public int countByC_D(long companyId, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public int countByP_D(long parentResourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countByP_H(long parentResourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public int countByR_D(long resourcePrimKey, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countByR_H(long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException;

	public int countByR_V(long resourcePrimKey, double version)
		throws com.liferay.portal.SystemException;

	public int countByG_H_T(long groupId, boolean head, boolean template)
		throws com.liferay.portal.SystemException;

	public int countByG_T_H(long groupId, java.lang.String title, boolean head)
		throws com.liferay.portal.SystemException;

	public int countByG_T_V(long groupId, java.lang.String title, double version)
		throws com.liferay.portal.SystemException;

	public int countByP_H_D(long parentResourcePrimKey, boolean head,
		boolean draft) throws com.liferay.portal.SystemException;

	public int countByR_H_D(long resourcePrimKey, boolean head, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countByR_V_D(long resourcePrimKey, double version, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countByG_H_T_D(long groupId, boolean head, boolean template,
		boolean draft) throws com.liferay.portal.SystemException;

	public int countByG_T_H_D(long groupId, java.lang.String title,
		boolean head, boolean draft) throws com.liferay.portal.SystemException;

	public int countByG_T_V_D(long groupId, java.lang.String title,
		double version, boolean draft)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}