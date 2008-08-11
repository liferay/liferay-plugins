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
 * <a href="KBFeedbackStatsUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsUtil {
	public static com.liferay.knowledgebase.model.KBFeedbackStats create(
		long kbFeedbackStatsId) {
		return getPersistence().create(kbFeedbackStatsId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats remove(
		long kbFeedbackStatsId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(kbFeedbackStatsId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats remove(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(kbFeedbackStats);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats update(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackStats);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats update(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackStats, merge);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats updateImpl(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(kbFeedbackStats, merge);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats findByPrimaryKey(
		long kbFeedbackStatsId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(kbFeedbackStatsId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats fetchByPrimaryKey(
		long kbFeedbackStatsId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(kbFeedbackStatsId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats findByArticleId(
		long articleId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException {
		return getPersistence().findByArticleId(articleId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats fetchByArticleId(
		long articleId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByArticleId(articleId);
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

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByArticleId(long articleId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException {
		getPersistence().removeByArticleId(articleId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByArticleId(long articleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByArticleId(articleId);
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

	public static KBFeedbackStatsPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(KBFeedbackStatsPersistence persistence) {
		_persistence = persistence;
	}

	private static KBFeedbackStatsPersistence _persistence;
}