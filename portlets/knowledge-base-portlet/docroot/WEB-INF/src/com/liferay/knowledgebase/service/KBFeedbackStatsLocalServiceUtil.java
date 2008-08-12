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
 * <a href="KBFeedbackStatsLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jorge Ferrer
 *
 */
public class KBFeedbackStatsLocalServiceUtil {
	public static com.liferay.knowledgebase.model.KBFeedbackStats addKBFeedbackStats(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		return _service.addKBFeedbackStats(kbFeedbackStats);
	}

	public static void deleteKBFeedbackStats(long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_service.deleteKBFeedbackStats(feedbackStatsId);
	}

	public static void deleteKBFeedbackStats(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		_service.deleteKBFeedbackStats(kbFeedbackStats);
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

	public static com.liferay.knowledgebase.model.KBFeedbackStats getKBFeedbackStats(
		long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getKBFeedbackStats(feedbackStatsId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFeedbackStats> getKBFeedbackStatses(
		int start, int end) throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackStatses(start, end);
	}

	public static int getKBFeedbackStatsesCount()
		throws com.liferay.portal.SystemException {
		return _service.getKBFeedbackStatsesCount();
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats updateKBFeedbackStats(
		com.liferay.knowledgebase.model.KBFeedbackStats kbFeedbackStats)
		throws com.liferay.portal.SystemException {
		return _service.updateKBFeedbackStats(kbFeedbackStats);
	}

	public static void deleteFeedbackStats(long articleResourcePrimKey)
		throws com.liferay.portal.SystemException {
		_service.deleteFeedbackStats(articleResourcePrimKey);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats getFeedbackStats(
		long feedbackStatsId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _service.getFeedbackStats(feedbackStatsId);
	}

	public static com.liferay.knowledgebase.model.KBFeedbackStats getArticleFeedbackStats(
		long articleResourcePrimKey) throws com.liferay.portal.SystemException {
		return _service.getArticleFeedbackStats(articleResourcePrimKey);
	}

	public static KBFeedbackStatsLocalService getService() {
		return _service;
	}

	public void setService(KBFeedbackStatsLocalService service) {
		_service = service;
	}

	private static KBFeedbackStatsLocalService _service;
}