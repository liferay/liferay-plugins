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
 * <a href="KBFeedbackEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryUtil {
	public static com.liferay.knowledgebase.model.KBFeedbackEntry create(
		long kbFeedbackEntryId) {
		return getPersistence().create(kbFeedbackEntryId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry remove(
		long kbFeedbackEntryId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(kbFeedbackEntryId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry remove(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(kbFeedbackEntry);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackEntry);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackEntry, merge);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateImpl(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(kbFeedbackEntry, merge);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByPrimaryKey(
		long kbFeedbackEntryId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(kbFeedbackEntryId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry fetchByPrimaryKey(
		long kbFeedbackEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(kbFeedbackEntryId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleId(
		long articleId) throws com.liferay.portal.SystemException {
		return getPersistence().findByArticleId(articleId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleId(
		long articleId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByArticleId(articleId, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleId(
		long articleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByArticleId(articleId, start, end, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByArticleId_First(
		long articleId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByArticleId_First(articleId, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByArticleId_Last(
		long articleId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByArticleId_Last(articleId, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry[] findByArticleId_PrevAndNext(
		long kbFeedbackEntryId, long articleId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleId_PrevAndNext(kbFeedbackEntryId, articleId,
			obc);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry[] findByUserId_PrevAndNext(
		long kbFeedbackEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(kbFeedbackEntryId, userId, obc);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleId, int score) throws com.liferay.portal.SystemException {
		return getPersistence().findByA_S(articleId, score);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleId, int score, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_S(articleId, score, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleId, int score, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_S(articleId, score, start, end, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByA_S_First(
		long articleId, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_S_First(articleId, score, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByA_S_Last(
		long articleId, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_S_Last(articleId, score, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry[] findByA_S_PrevAndNext(
		long kbFeedbackEntryId, long articleId, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S_PrevAndNext(kbFeedbackEntryId, articleId, score,
			obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByA_U(
		long articleId, long userId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_U(articleId, userId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry fetchByA_U(
		long articleId, long userId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByA_U(articleId, userId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleId, int vote) throws com.liferay.portal.SystemException {
		return getPersistence().findByA_V(articleId, vote);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleId, int vote, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_V(articleId, vote, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleId, int vote, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_V(articleId, vote, start, end, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByA_V_First(
		long articleId, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_V_First(articleId, vote, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry findByA_V_Last(
		long articleId, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_V_Last(articleId, vote, obc);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry[] findByA_V_PrevAndNext(
		long kbFeedbackEntryId, long articleId, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_V_PrevAndNext(kbFeedbackEntryId, articleId, vote,
			obc);
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

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByArticleId(long articleId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByArticleId(articleId);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByA_S(long articleId, int score)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByA_S(articleId, score);
	}

	public static void removeByA_U(long articleId, long userId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		getPersistence().removeByA_U(articleId, userId);
	}

	public static void removeByA_V(long articleId, int vote)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByA_V(articleId, vote);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByArticleId(long articleId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByArticleId(articleId);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByA_S(long articleId, int score)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_S(articleId, score);
	}

	public static int countByA_U(long articleId, long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_U(articleId, userId);
	}

	public static int countByA_V(long articleId, int vote)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_V(articleId, vote);
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

	public static KBFeedbackEntryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(KBFeedbackEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static KBFeedbackEntryPersistence _persistence;
}