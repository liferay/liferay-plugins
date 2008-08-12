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
 * <a href="KBFeedbackStatsPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public interface KBFeedbackStatsPersistence {
	public com.liferay.knowledgebase.model.KBFeedbackStats create(
		long feedbackStatsId);

	public com.liferay.knowledgebase.model.KBFeedbackStats remove(
		long feedbackStatsId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats remove(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats update(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats update(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats updateImpl(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats,
		boolean merge) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats findByPrimaryKey(
		long feedbackStatsId)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats fetchByPrimaryKey(
		long feedbackStatsId) throws com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats findByArticleResourcePrimKey(
		long articleResourcePrimKey)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException;

	public com.liferay.knowledgebase.model.KBFeedbackStats fetchByArticleResourcePrimKey(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	public java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByArticleResourcePrimKey(long articleResourcePrimKey)
		throws com.liferay.knowledgebase.NoSuchFeedbackStatsException,
			com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByArticleResourcePrimKey(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}