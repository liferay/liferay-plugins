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

package com.liferay.kb.knowledgebase.service.persistence;

/**
 * <a href="KBArticleUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleUtil {
	public static com.liferay.kb.knowledgebase.model.KBArticle create(
		long articleId) {
		return getPersistence().create(articleId);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle remove(
		long articleId)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(articleId);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle remove(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(kbArticle);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle update(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbArticle);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle update(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbArticle, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle updateImpl(
		com.liferay.kb.knowledgebase.model.KBArticle kbArticle, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(kbArticle, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByPrimaryKey(
		long articleId)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(articleId);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle fetchByPrimaryKey(
		long articleId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(articleId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid) throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUuid(uuid, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_First(uuid, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_Last(uuid, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByUuid_PrevAndNext(
		long articleId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUuid_PrevAndNext(articleId, uuid, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByCompanyId_PrevAndNext(
		long articleId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(articleId, companyId, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByGroupId(
		long groupId) throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByGroupId(groupId, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByGroupId_First(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_First(groupId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByGroupId_Last(
		long groupId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByGroupId_Last(groupId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByGroupId_PrevAndNext(
		long articleId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(articleId, groupId, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey) throws com.liferay.portal.SystemException {
		return getPersistence().findByResourcePrimKey(resourcePrimKey);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByResourcePrimKey(
		long resourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByResourcePrimKey(resourcePrimKey, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByResourcePrimKey_First(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByResourcePrimKey_First(resourcePrimKey, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByResourcePrimKey_Last(
		long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByResourcePrimKey_Last(resourcePrimKey, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByResourcePrimKey_PrevAndNext(
		long articleId, long resourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByResourcePrimKey_PrevAndNext(articleId,
			resourcePrimKey, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_D(companyId, draft);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_D(companyId, draft, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_D(
		long companyId, boolean draft, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_D(companyId, draft, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByC_D_First(
		long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByC_D_First(companyId, draft, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByC_D_Last(
		long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByC_D_Last(companyId, draft, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByC_D_PrevAndNext(
		long articleId, long companyId, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_D_PrevAndNext(articleId, companyId, draft, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_T(groupId, title);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_T(groupId, title, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T(
		long groupId, java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_T(groupId, title, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_T_First(
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T_First(groupId, title, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_T_Last(
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T_Last(groupId, title, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByG_T_PrevAndNext(
		long articleId, long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_T_PrevAndNext(articleId, groupId, title, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_H(resourcePrimKey, head);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_H(resourcePrimKey, head, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByR_H(
		long resourcePrimKey, boolean head, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByR_H(resourcePrimKey, head, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByR_H_First(
		long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByR_H_First(resourcePrimKey, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByR_H_Last(
		long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByR_H_Last(resourcePrimKey, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByR_H_PrevAndNext(
		long articleId, long resourcePrimKey, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByR_H_PrevAndNext(articleId, resourcePrimKey, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByR_V(
		long resourcePrimKey, double version)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByR_V(resourcePrimKey, version);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle fetchByR_V(
		long resourcePrimKey, double version)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByR_V(resourcePrimKey, version);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_H_T(groupId, head, template);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_H_T(groupId, head, template, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_H_T(
		long groupId, boolean head, boolean template, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_H_T(groupId, head, template, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_H_T_First(
		long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_H_T_First(groupId, head, template, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_H_T_Last(
		long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_H_T_Last(groupId, head, template, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByG_H_T_PrevAndNext(
		long articleId, long groupId, boolean head, boolean template,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_H_T_PrevAndNext(articleId, groupId, head, template,
			obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_T_H(groupId, title, head);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByG_T_H(groupId, title, head, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_T_H(
		long groupId, java.lang.String title, boolean head, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_T_H(groupId, title, head, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_T_H_First(
		long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T_H_First(groupId, title, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_T_H_Last(
		long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T_H_Last(groupId, title, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByG_T_H_PrevAndNext(
		long articleId, long groupId, java.lang.String title, boolean head,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByG_T_H_PrevAndNext(articleId, groupId, title, head, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByG_T_V(
		long groupId, java.lang.String title, double version)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T_V(groupId, title, version);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle fetchByG_T_V(
		long groupId, java.lang.String title, double version)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByG_T_V(groupId, title, version);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_H_T_D(
		long companyId, boolean head, boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByC_H_T_D(companyId, head, template, draft);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_H_T_D(
		long companyId, boolean head, boolean template, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_H_T_D(companyId, head, template, draft, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByC_H_T_D(
		long companyId, boolean head, boolean template, boolean draft,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_H_T_D(companyId, head, template, draft, start, end,
			obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByC_H_T_D_First(
		long companyId, boolean head, boolean template, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_H_T_D_First(companyId, head, template, draft, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle findByC_H_T_D_Last(
		long companyId, boolean head, boolean template, boolean draft,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_H_T_D_Last(companyId, head, template, draft, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticle[] findByC_H_T_D_PrevAndNext(
		long articleId, long companyId, boolean head, boolean template,
		boolean draft, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByC_H_T_D_PrevAndNext(articleId, companyId, head,
			template, draft, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	public static void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		getPersistence().removeByUUID_G(uuid, groupId);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	public static void removeByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByResourcePrimKey(resourcePrimKey);
	}

	public static void removeByC_D(long companyId, boolean draft)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_D(companyId, draft);
	}

	public static void removeByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByG_T(groupId, title);
	}

	public static void removeByR_H(long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByR_H(resourcePrimKey, head);
	}

	public static void removeByR_V(long resourcePrimKey, double version)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		getPersistence().removeByR_V(resourcePrimKey, version);
	}

	public static void removeByG_H_T(long groupId, boolean head,
		boolean template) throws com.liferay.portal.SystemException {
		getPersistence().removeByG_H_T(groupId, head, template);
	}

	public static void removeByG_T_H(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException {
		getPersistence().removeByG_T_H(groupId, title, head);
	}

	public static void removeByG_T_V(long groupId, java.lang.String title,
		double version)
		throws com.liferay.kb.knowledgebase.NoSuchArticleException,
			com.liferay.portal.SystemException {
		getPersistence().removeByG_T_V(groupId, title, version);
	}

	public static void removeByC_H_T_D(long companyId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByC_H_T_D(companyId, head, template, draft);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	public static int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByGroupId(long groupId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	public static int countByResourcePrimKey(long resourcePrimKey)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByResourcePrimKey(resourcePrimKey);
	}

	public static int countByC_D(long companyId, boolean draft)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_D(companyId, draft);
	}

	public static int countByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_T(groupId, title);
	}

	public static int countByR_H(long resourcePrimKey, boolean head)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByR_H(resourcePrimKey, head);
	}

	public static int countByR_V(long resourcePrimKey, double version)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByR_V(resourcePrimKey, version);
	}

	public static int countByG_H_T(long groupId, boolean head, boolean template)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_H_T(groupId, head, template);
	}

	public static int countByG_T_H(long groupId, java.lang.String title,
		boolean head) throws com.liferay.portal.SystemException {
		return getPersistence().countByG_T_H(groupId, title, head);
	}

	public static int countByG_T_V(long groupId, java.lang.String title,
		double version) throws com.liferay.portal.SystemException {
		return getPersistence().countByG_T_V(groupId, title, version);
	}

	public static int countByC_H_T_D(long companyId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByC_H_T_D(companyId, head, template, draft);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static KBArticlePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(KBArticlePersistence persistence) {
		_persistence = persistence;
	}

	private static KBArticlePersistence _persistence;
}