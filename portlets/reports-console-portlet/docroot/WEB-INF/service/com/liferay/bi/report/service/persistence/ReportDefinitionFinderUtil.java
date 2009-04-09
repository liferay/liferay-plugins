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

package com.liferay.bi.report.service.persistence;

/**
 * <a href="ReportDefinitionFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ReportDefinitionFinderUtil {
	public static int countByC_G_N_D(long companyId, long groupId,
		java.lang.String[] definitionNames, java.lang.String[] descriptions,
		boolean andOperator) throws com.liferay.portal.SystemException {
		return getFinder()
				   .countByC_G_N_D(companyId, groupId, definitionNames,
			descriptions, andOperator);
	}

	public static int countByKeywords(long companyId, long groupId,
		java.lang.String keywords,
		java.util.LinkedHashMap<String, Object> params)
		throws com.liferay.portal.SystemException {
		return getFinder().countByKeywords(companyId, groupId, keywords, params);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByC_G_N_D(
		long companyId, long groupId, java.lang.String[] definitionNames,
		java.lang.String[] descriptions, boolean andOperator, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getFinder()
				   .findByC_G_N_D(companyId, groupId, definitionNames,
			descriptions, andOperator, start, end, obc);
	}

	public static java.util.List<com.liferay.bi.report.model.ReportDefinition> findByKeywords(
		long companyId, long groupId, java.lang.String keywords,
		java.util.LinkedHashMap<String, Object> params, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getFinder()
				   .findByKeywords(companyId, groupId, keywords, params, start,
			end, obc);
	}

	public static ReportDefinitionFinder getFinder() {
		return _finder;
	}

	public void setFinder(ReportDefinitionFinder finder) {
		_finder = finder;
	}

	private static ReportDefinitionFinder _finder;
}