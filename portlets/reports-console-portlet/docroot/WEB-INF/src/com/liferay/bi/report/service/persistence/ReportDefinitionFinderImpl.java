/*
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

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * <a href="ReportDefinitionFinderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class ReportDefinitionFinderImpl
	extends BasePersistenceImpl implements ReportDefinitionFinder {
	public List<ReportDefinition> findByKeywords(
		long companyId, long groupId, String keywords,
		LinkedHashMap<String, Object> params, int start, int end,
		OrderByComparator obc) {
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	public int countByKeywords(
		long companyId, long groupId, java.lang.String keywords,
		LinkedHashMap<String, Object> params)
		throws com.liferay.portal.SystemException {
		return 1;
	}

}
