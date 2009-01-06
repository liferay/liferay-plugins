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

package com.liferay.kb.knowledgebase.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="KBFeedbackEntryLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackEntryLocalServiceClp
	implements KBFeedbackEntryLocalService {
	public KBFeedbackEntryLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kbFeedbackEntry);

		if (kbFeedbackEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addKBFeedbackEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry createKBFeedbackEntry(
		long feedbackEntryId) {
		Object paramObj0 = new LongWrapper(feedbackEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createKBFeedbackEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKBFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackEntryId);

		try {
			_classLoaderProxy.invoke("deleteKBFeedbackEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kbFeedbackEntry);

		if (kbFeedbackEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackEntry");
		}

		try {
			_classLoaderProxy.invoke("deleteKBFeedbackEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getKBFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getKBFeedbackEntries(
		int start, int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackEntries",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKBFeedbackEntriesCount()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKBFeedbackEntriesCount",
					new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kbFeedbackEntry);

		if (kbFeedbackEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackEntry");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateKBFeedbackEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry addFeedbackEntry(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = new IntegerWrapper(score);

		Object paramObj3 = new IntegerWrapper(vote);

		Object paramObj4 = ClpSerializer.translateInput(comments);

		if (comments == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addFeedbackEntry",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteArticleFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteArticleFeedbackEntries",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteFeedbackEntries(long articleResourcePrimKey)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		try {
			_classLoaderProxy.invoke("deleteFeedbackEntries",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteFeedbackEntry(long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackEntryId);

		try {
			_classLoaderProxy.invoke("deleteFeedbackEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry feedbackEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(feedbackEntry);

		if (feedbackEntry == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.kb.knowledgebase.model.KBFeedbackEntry");
		}

		try {
			_classLoaderProxy.invoke("deleteFeedbackEntry",
				new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getArticleFeedbackEntries(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticleFeedbackEntries",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getArticleFeedbackEntries(
		long articleResourcePrimKey, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticleFeedbackEntries",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getArticleFeedbackEntriesCount(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getArticleFeedbackEntriesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleScore(
		long articleResourcePrimKey, int score, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new IntegerWrapper(score);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntriesByArticleScore",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getFeedbackEntriesByArticleScoreCount(
		long articleResourcePrimKey, int score)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new IntegerWrapper(score);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntriesByArticleScoreCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getFeedbackEntriesByArticleVote(
		long articleResourcePrimKey, int vote, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new IntegerWrapper(vote);

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntriesByArticleVote",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getFeedbackEntriesByArticleVoteCount(
		long articleResourcePrimKey, int vote)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new IntegerWrapper(vote);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntriesByArticleVoteCount",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long feedbackEntryId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(feedbackEntryId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntry",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry getFeedbackEntry(
		long articleResourcePrimKey, long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFeedbackEntry",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry> getUserFeedbackEntries(
		long userId, int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getUserFeedbackEntries",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getUserFeedbackEntriesCount(long userId)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(userId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getUserFeedbackEntriesCount",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateComments(
		long articleResourcePrimKey, long userId, java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = ClpSerializer.translateInput(comments);

		if (comments == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateComments",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateFeedback(
		long articleResourcePrimKey, long userId, int score, int vote,
		java.lang.String comments)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = new IntegerWrapper(score);

		Object paramObj3 = new IntegerWrapper(vote);

		Object paramObj4 = ClpSerializer.translateInput(comments);

		if (comments == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateFeedback",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateScore(
		long articleResourcePrimKey, long userId, int score)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = new IntegerWrapper(score);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateScore",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry updateVote(
		long articleResourcePrimKey, long userId, int vote)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(articleResourcePrimKey);

		Object paramObj1 = new LongWrapper(userId);

		Object paramObj2 = new IntegerWrapper(vote);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateVote",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}