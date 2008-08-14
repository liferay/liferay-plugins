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
 * <a href="KBArticleFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBArticleFinderUtil {
	public static int countByG_U_H_T_D(long groupId, long userId, boolean head,
		boolean template, boolean draft)
		throws com.liferay.portal.SystemException {
		return getFinder()
				   .countByG_U_H_T_D(groupId, userId, head, template, draft);
	}

	public static int countByP_U_H_D(long parentResourcePrimKey, long userId,
		boolean head, boolean draft) throws com.liferay.portal.SystemException {
		return getFinder()
				   .countByP_U_H_D(parentResourcePrimKey, userId, head, draft);
	}

	public static int countByR_U_H_D(long resourcePrimKey, long userId,
		boolean head, boolean draft) throws com.liferay.portal.SystemException {
		return getFinder().countByR_U_H_D(resourcePrimKey, userId, head, draft);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByG_U_H_T_D(
		long groupId, long userId, boolean head, boolean template,
		boolean draft, int start, int end)
		throws com.liferay.portal.SystemException {
		return getFinder()
				   .findByG_U_H_T_D(groupId, userId, head, template, draft,
			start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByP_U_H_D(
		long parentResourcePrimKey, long userId, boolean head, boolean draft)
		throws com.liferay.portal.SystemException {
		return getFinder()
				   .findByP_U_H_D(parentResourcePrimKey, userId, head, draft);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBArticle> findByR_U_H_D(
		long resourcePrimKey, long userId, boolean head, boolean draft,
		int start, int end) throws com.liferay.portal.SystemException {
		return getFinder()
				   .findByR_U_H_D(resourcePrimKey, userId, head, draft, start,
			end);
	}

	public static KBArticleFinder getFinder() {
		return _finder;
	}

	public void setFinder(KBArticleFinder finder) {
		_finder = finder;
	}

	private static KBArticleFinder _finder;
}