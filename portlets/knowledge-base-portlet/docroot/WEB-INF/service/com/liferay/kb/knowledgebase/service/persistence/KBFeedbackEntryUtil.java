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
 * <a href="KBFeedbackEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryUtil {
	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry create(
		long feedbackEntryId) {
		return getPersistence().create(feedbackEntryId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry remove(
		long feedbackEntryId)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(feedbackEntryId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry remove(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(kbFeedbackEntry);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackEntry);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().update(kbFeedbackEntry, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateImpl(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(kbFeedbackEntry, merge);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByPrimaryKey(
		long feedbackEntryId)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(feedbackEntryId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry fetchByPrimaryKey(
		long feedbackEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(feedbackEntryId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey(articleResourcePrimKey);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey(articleResourcePrimKey, start,
			end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey(articleResourcePrimKey, start,
			end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByArticleResourcePrimKey_First(
		long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey_First(articleResourcePrimKey,
			obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByArticleResourcePrimKey_Last(
		long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey_Last(articleResourcePrimKey,
			obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry[] findByArticleResourcePrimKey_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByArticleResourcePrimKey_PrevAndNext(feedbackEntryId,
			articleResourcePrimKey, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByUserId(userId, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_First(userId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByUserId_Last(userId, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry[] findByUserId_PrevAndNext(
		long feedbackEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(feedbackEntryId, userId, obc);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_S(articleResourcePrimKey, score);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S(articleResourcePrimKey, score, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S(articleResourcePrimKey, score, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByA_S_First(
		long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S_First(articleResourcePrimKey, score, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByA_S_Last(
		long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S_Last(articleResourcePrimKey, score, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry[] findByA_S_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_S_PrevAndNext(feedbackEntryId,
			articleResourcePrimKey, score, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByA_U(
		long articleResourcePrimKey, long userId)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_U(articleResourcePrimKey, userId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry fetchByA_U(
		long articleResourcePrimKey, long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByA_U(articleResourcePrimKey, userId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByA_V(articleResourcePrimKey, vote);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_V(articleResourcePrimKey, vote, start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_V(articleResourcePrimKey, vote, start, end, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByA_V_First(
		long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_V_First(articleResourcePrimKey, vote, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry findByA_V_Last(
		long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByA_V_Last(articleResourcePrimKey, vote, obc);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry[] findByA_V_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByA_V_PrevAndNext(feedbackEntryId,
			articleResourcePrimKey, vote, obc);
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

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByArticleResourcePrimKey(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		getPersistence().removeByArticleResourcePrimKey(articleResourcePrimKey);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeByA_S(long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByA_S(articleResourcePrimKey, score);
	}

	public static void removeByA_U(long articleResourcePrimKey, long userId)
		throws com.liferay.kb.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException {
		getPersistence().removeByA_U(articleResourcePrimKey, userId);
	}

	public static void removeByA_V(long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByA_V(articleResourcePrimKey, vote);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByArticleResourcePrimKey(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByArticleResourcePrimKey(articleResourcePrimKey);
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countByA_S(long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_S(articleResourcePrimKey, score);
	}

	public static int countByA_U(long articleResourcePrimKey, long userId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_U(articleResourcePrimKey, userId);
	}

	public static int countByA_V(long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByA_V(articleResourcePrimKey, vote);
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