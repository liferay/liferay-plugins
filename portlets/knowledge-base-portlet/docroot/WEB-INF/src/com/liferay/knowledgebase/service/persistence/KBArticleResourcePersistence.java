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
 * <a href="KBArticleResourcePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticleResourcePersistence {
	public com.liferay.knowledgebase.model.KBArticleResource create(
		long resourcePrimKey);

	public com.liferay.knowledgebase.model.KBArticleResource remove(
		long resourcePrimKey)
		throws com.liferay.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource remove(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource update(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource update(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource updateImpl(
		com.liferay.knowledgebase.model.KBArticleResource kbArticleResource,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource findByPrimaryKey(
		long resourcePrimKey)
		throws com.liferay.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource fetchByPrimaryKey(
		long resourcePrimKey) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource findByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBArticleResource fetchByG_T(
		long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticleResource> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticleResource> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBArticleResource> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByG_T(long groupId, java.lang.String title)
		throws com.liferay.knowledgebase.NoSuchArticleResourceException,
			com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByG_T(long groupId, java.lang.String title)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}