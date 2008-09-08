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

import com.liferay.kb.knowledgebase.model.KBFeedbackEntryClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		_classLoader = classLoaderProxy.getClassLoader();
	}

	public com.liferay.kb.knowledgebase.model.KBFeedbackEntry addKBFeedbackEntry(
		com.liferay.kb.knowledgebase.model.KBFeedbackEntry kbFeedbackEntry)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(kbFeedbackEntry);

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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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
		Object paramObj0 = translateInput(kbFeedbackEntry);

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
		Object paramObj0 = translateInput(dynamicQuery);

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

		return (java.util.List<Object>)translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = translateInput(dynamicQuery);

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

		return (java.util.List<Object>)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)translateOutput(returnObj);
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
		Object paramObj0 = translateInput(kbFeedbackEntry);

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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		Object paramObj4 = translateInput(comments);

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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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
		Object paramObj0 = translateInput(feedbackEntry);

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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)translateOutput(returnObj);
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)translateOutput(returnObj);
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (java.util.List<com.liferay.kb.knowledgebase.model.KBFeedbackEntry>)translateOutput(returnObj);
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

		Object paramObj2 = translateInput(comments);

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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		Object paramObj4 = translateInput(comments);

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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
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

		return (com.liferay.kb.knowledgebase.model.KBFeedbackEntry)translateOutput(returnObj);
	}

	protected Object translateInput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(KBFeedbackEntryClp.class.getName())) {
			KBFeedbackEntryClp oldCplModel = (KBFeedbackEntryClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class newModelClass = Class.forName("com.liferay.kb.knowledgebase.model.impl.KBFeedbackEntryImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFeedbackEntryId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFeedbackEntryId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setArticleResourcePrimKey",
							new Class[] { Long.TYPE });

					Long value1 = new Long(oldCplModel.getArticleResourcePrimKey());

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setUserId",
							new Class[] { Long.TYPE });

					Long value2 = new Long(oldCplModel.getUserId());

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setUserName",
							new Class[] { String.class });

					String value3 = oldCplModel.getUserName();

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value4 = oldCplModel.getCreateDate();

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value5 = oldCplModel.getModifiedDate();

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setComments",
							new Class[] { String.class });

					String value6 = oldCplModel.getComments();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setScore",
							new Class[] { Integer.TYPE });

					Integer value7 = new Integer(oldCplModel.getScore());

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setVote",
							new Class[] { Integer.TYPE });

					Integer value8 = new Integer(oldCplModel.getVote());

					method8.invoke(newModel, value8);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateInput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	protected Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List)obj);
		}
		else {
			return obj;
		}
	}

	protected Object translateOutput(BaseModel oldModel) {
		Class oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.kb.knowledgebase.model.impl.KBFeedbackEntryImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					KBFeedbackEntryClp newModel = new KBFeedbackEntryClp();

					Method method0 = oldModelClass.getMethod(
							"getFeedbackEntryId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFeedbackEntryId(value0.longValue());

					Method method1 = oldModelClass.getMethod(
							"getArticleResourcePrimKey");

					Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

					newModel.setArticleResourcePrimKey(value1.longValue());

					Method method2 = oldModelClass.getMethod("getUserId");

					Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

					newModel.setUserId(value2.longValue());

					Method method3 = oldModelClass.getMethod("getUserName");

					String value3 = (String)method3.invoke(oldModel,
							(Object[])null);

					newModel.setUserName(value3);

					Method method4 = oldModelClass.getMethod("getCreateDate");

					Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value4);

					Method method5 = oldModelClass.getMethod("getModifiedDate");

					Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value5);

					Method method6 = oldModelClass.getMethod("getComments");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setComments(value6);

					Method method7 = oldModelClass.getMethod("getScore");

					Integer value7 = (Integer)method7.invoke(oldModel,
							(Object[])null);

					newModel.setScore(value7.intValue());

					Method method8 = oldModelClass.getMethod("getVote");

					Integer value8 = (Integer)method8.invoke(oldModel,
							(Object[])null);

					newModel.setVote(value8.intValue());

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	protected Object translateOutput(List oldList) {
		List newList = new ArrayList(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	protected Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBFeedbackEntryLocalServiceClp.class);
	private ClassLoaderProxy _classLoaderProxy;
	private ClassLoader _classLoader;
}