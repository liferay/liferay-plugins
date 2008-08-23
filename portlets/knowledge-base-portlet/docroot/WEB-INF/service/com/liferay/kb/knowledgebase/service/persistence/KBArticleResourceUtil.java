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
 * <a href="KBArticleResourceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleResourceUtil {
	public static com.liferay.kb.knowledgebase.model.KBArticleResource create(
		long resourcePrimKey) {
		return getPersistence().create(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource remove(
		long resourcePrimKey)
		throws com.liferay.kb.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource remove(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(kbArticleResource);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource update(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbArticleResource);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource update(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(kbArticleResource, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource updateImpl(
		com.liferay.kb.knowledgebase.model.KBArticleResource kbArticleResource,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(kbArticleResource, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource findByPrimaryKey(
		long resourcePrimKey)
		throws com.liferay.kb.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource fetchByPrimaryKey(
		long resourcePrimKey) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(resourcePrimKey);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource findByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.kb.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException {
		return getPersistence().findByG_T(groupId, title);
	}

	public static com.liferay.kb.knowledgebase.model.KBArticleResource fetchByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByG_T(groupId, title);
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

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticleResource> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticleResource> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBArticleResource> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByG_T(long groupId, java.lang.String title)
		throws com.liferay.kb.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException {
		getPersistence().removeByG_T(groupId, title);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByG_T(groupId, title);
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

	public static KBArticleResourcePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(KBArticleResourcePersistence persistence) {
		_persistence = persistence;
	}

	private static KBArticleResourcePersistence _persistence;
}