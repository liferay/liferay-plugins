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
 * <a href="KBFeedbackEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBFeedbackEntryPersistence {
	public com.liferay.knowledgebase.model.KBFeedbackEntry create(
		long feedbackEntryId);

	public com.liferay.knowledgebase.model.KBFeedbackEntry remove(
		long feedbackEntryId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry remove(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry update(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry updateImpl(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByPrimaryKey(
		long feedbackEntryId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry fetchByPrimaryKey(
		long feedbackEntryId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByArticleResourcePrimKey(
		long articleResourcePrimKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByArticleResourcePrimKey_First(
		long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByArticleResourcePrimKey_Last(
		long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry[] findByArticleResourcePrimKey_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByUserId_First(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByUserId_Last(
		long userId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry[] findByUserId_PrevAndNext(
		long feedbackEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_S(
		long articleResourcePrimKey, int score, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByA_S_First(
		long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByA_S_Last(
		long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry[] findByA_S_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey, int score,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByA_U(
		long articleResourcePrimKey, long userId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry fetchByA_U(
		long articleResourcePrimKey, long userId)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findByA_V(
		long articleResourcePrimKey, int vote, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByA_V_First(
		long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry findByA_V_Last(
		long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackEntry[] findByA_V_PrevAndNext(
		long feedbackEntryId, long articleResourcePrimKey, int vote,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByArticleResourcePrimKey(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public void removeByA_S(long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException;

	public void removeByA_U(long articleResourcePrimKey, long userId)
		throws com.liferay.knowledgebase.NoSuchFeedbackEntryException,
			com.liferay.portal.SystemException;

	public void removeByA_V(long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByArticleResourcePrimKey(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.SystemException;

	public int countByA_S(long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException;

	public int countByA_U(long articleResourcePrimKey, long userId)
		throws com.liferay.portal.SystemException;

	public int countByA_V(long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}