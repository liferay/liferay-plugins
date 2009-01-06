/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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
 * <a href="KBArticleFinder.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBArticleFinder {
	public int countByGroupIds(long[] groupIds)
		throws com.liferay.portal.SystemException;

	public int countByS_U_G(long userId, long groupId)
		throws com.liferay.portal.SystemException;

	public int countByG_P_T_Or_G_P_T_U(long groupId, boolean template,
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByGroupIds(
		long[] groupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByS_U_G(
		long userId, long groupId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.kb.knowledgebase.model.KBArticle> findByG_P_T_Or_G_P_T_U(
		long groupId, long parentResourcePrimKey, boolean template,
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;
}