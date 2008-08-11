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

package com.liferay.knowledgebase.service;

/**
 * <a href="KBFeedbackEntryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryLocalServiceUtil {
	public static com.liferay.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return _service.addKBFeedbackEntry(kbFeedbackEntry);
	}

	public static void deleteKBFeedbackEntry(long kbFeedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteKBFeedbackEntry(kbFeedbackEntryId);
	}

	public static void deleteKBFeedbackEntry(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
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

	public static com.liferay.knowledgebase.model.KBFeedbackEntry getKBFeedbackEntry(
		long kbFeedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntry(kbFeedbackEntryId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntries(start, end);
	}

	public static int getKBFeedbackEntriesCount()
		throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesCount();
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateKBFeedbackEntry(
		com.liferay.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		return _service.updateKBFeedbackEntry(kbFeedbackEntry);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		long articleId, long userId, int vote, int score,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.addKBFeedbackEntry(articleId, userId, vote, score,
			comments);
	}

	public static void deleteKBFeedbackEntries(long articleId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteKBFeedbackEntries(articleId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> getArticleKBFeedbackEntries(
		long articleId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getArticleKBFeedbackEntries(articleId, start, end);
	}

	public static int getArticleKBFeedbackEntriesCount(long articleId)
		throws com.liferay.portal.SystemException {
		return _service.getArticleKBFeedbackEntriesCount(articleId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntriesByArticleScore(
		long articleId, int score, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesByArticleScore(articleId, score,
			start, end);
	}

	public static int getKBFeedbackEntriesByArticleScoreCount(long articleId,
		int score) throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesByArticleScoreCount(articleId, score);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntriesByArticleVote(
		long articleId, int vote, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesByArticleVote(articleId, vote,
			start, end);
	}

	public static int getKBFeedbackEntriesByArticleVoteCount(long articleId,
		int vote) throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntriesByArticleVoteCount(articleId, vote);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry getKBFeedbackEntry(
		long articleId, long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getKBFeedbackEntry(articleId, userId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackEntry> getUserKBFeedbackEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		return _service.getUserKBFeedbackEntries(userId, start, end);
	}

	public static int getUserKBFeedbackEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		return _service.getUserKBFeedbackEntriesCount(userId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateComments(
		long articleId, long userId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateComments(articleId, userId, comments);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateKBFeedback(
		long articleId, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateKBFeedback(articleId, userId, score, vote,
			comments);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateScore(
		long articleId, long userId, int score)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateScore(articleId, userId, score);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackEntry updateVote(
		long articleId, long userId, int vote)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.updateVote(articleId, userId, vote);
	}

	public static KBFeedbackEntryLocalService getService() {
		return _service;
	}

	public void setService(KBFeedbackEntryLocalService service) {
		_service = service;
	}

	private static KBFeedbackEntryLocalService _service;
}