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

/**
 * <a href="KBFeedbackEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryLocalServiceUtil {
	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return _service.addKBFeedbackEntry(kbFeedbackEntry);
	}

	public static void deleteKBFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteKBFeedbackEntry(feedbackEntryId);
	}

	public static void deleteKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		_service.deleteKBFeedbackEntry(kbFeedbackEntry);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _service.dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry getKBFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntry(feedbackEntryId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntries(start, end);
	}

	public static int getKBFeedbackEntriesCount()
		throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesCount();
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return _service.updateKBFeedbackEntry(kbFeedbackEntry);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry addFeedbackEntry(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.addFeedbackEntry(articleResourcePrimKey, userId, score,
			vote, comments);
	}

	public static void deleteArticleFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteArticleFeedbackEntries(articleResourcePrimKey);
	}

	public static void deleteFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteFeedbackEntries(articleResourcePrimKey);
	}

	public static void deleteFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteFeedbackEntry(feedbackEntryId);
	}

	public static void deleteFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry feedbackEntry)
		throws com.liferay.portal.SystemException {
		_service.deleteFeedbackEntry(feedbackEntry);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getArticleFeedbackEntries(
		long articleResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getArticleFeedbackEntries(articleResourcePrimKey,
			start, end);
	}

	public static int getArticleFeedbackEntriesCount(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		return _service.getArticleFeedbackEntriesCount(articleResourcePrimKey);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleScore(
		long articleResourcePrimKey, int score, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getFeedbackEntriesByArticleScore(articleResourcePrimKey,
			score, start, end);
	}

	public static int getFeedbackEntriesByArticleScoreCount(
		long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException {
		return _service.getFeedbackEntriesByArticleScoreCount(articleResourcePrimKey,
			score);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleVote(
		long articleResourcePrimKey, int vote, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getFeedbackEntriesByArticleVote(articleResourcePrimKey,
			vote, start, end);
	}

	public static int getFeedbackEntriesByArticleVoteCount(
		long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException {
		return _service.getFeedbackEntriesByArticleVoteCount(articleResourcePrimKey,
			vote);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getFeedbackEntry(feedbackEntryId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long articleResourcePrimKey, long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getFeedbackEntry(articleResourcePrimKey, userId);
	}

	public static java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getUserFeedbackEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getUserFeedbackEntries(userId, start, end);
	}

	public static int getUserFeedbackEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return _service.getUserFeedbackEntriesCount(userId);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateComments(
		long articleResourcePrimKey, long userId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateComments(articleResourcePrimKey, userId, comments);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateFeedback(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateFeedback(articleResourcePrimKey, userId, score,
			vote, comments);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateScore(
		long articleResourcePrimKey, long userId, int score)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateScore(articleResourcePrimKey, userId, score);
	}

	public static com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateVote(
		long articleResourcePrimKey, long userId, int vote)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateVote(articleResourcePrimKey, userId, vote);
	}

	public static KBFeedbackEntryLocalService getService() {
		return _service;
	}

	public void setService(KBFeedbackEntryLocalService service) {
		_service = service;
	}

	private static KBFeedbackEntryLocalService _service;
}