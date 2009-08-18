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

package com.liferay.wol.service.persistence;

/**
 * <a href="JIRAChangeGroupFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class JIRAChangeGroupFinderUtil {
	public static int countByCD_P(java.util.Date createDate, long projectId)
		throws com.liferay.portal.SystemException {
		return getFinder().countByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAChangeGroup> findByCD_P(
		java.util.Date createDate, long projectId)
		throws com.liferay.portal.SystemException {
		return getFinder().findByCD_P(createDate, projectId);
	}

	public static java.util.List<com.liferay.wol.model.JIRAChangeGroup> findByCD_P(
		java.util.Date createDate, long projectId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByCD_P(createDate, projectId, start, end);
	}

	public static JIRAChangeGroupFinder getFinder() {
		return _finder;
	}

	public void setFinder(JIRAChangeGroupFinder finder) {
		_finder = finder;
	}

	private static JIRAChangeGroupFinder _finder;
}