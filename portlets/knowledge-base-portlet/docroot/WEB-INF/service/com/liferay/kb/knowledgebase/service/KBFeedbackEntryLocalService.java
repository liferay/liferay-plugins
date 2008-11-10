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

package com.liferay.kb.knowledgebase.service;

import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="KBFeedbackEntryLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
@Transactional
public interface KBFeedbackEntryLocalService {
	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry createKBFeedbackEntry(
		long feedbackEntryId);

	public void deleteKBFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	public void deleteKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getKBFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntries(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBFeedbackEntriesCount()
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry addFeedbackEntry(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteArticleFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public void deleteFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry feedbackEntry)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getArticleFeedbackEntries(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getArticleFeedbackEntries(
		long articleResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getArticleFeedbackEntriesCount(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleScore(
		long articleResourcePrimKey, int score, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFeedbackEntriesByArticleScoreCount(
		long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleVote(
		long articleResourcePrimKey, int vote, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFeedbackEntriesByArticleVoteCount(
		long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long articleResourcePrimKey, long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getUserFeedbackEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserFeedbackEntriesCount(long userId)
		throws com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateComments(
		long articleResourcePrimKey, long userId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateFeedback(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateScore(
		long articleResourcePrimKey, long userId, int score)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateVote(
		long articleResourcePrimKey, long userId, int vote)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException;
}