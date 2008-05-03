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

package com.liferay.wol.service.persistence;

/**
 * <a href="JIRAChangeGroupFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
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
		java.util.Date createDate, long projectId, int begin, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByCD_P(createDate, projectId, begin, end);
	}

	public static JIRAChangeGroupFinder getFinder() {
		return _getUtil()._finder;
	}

	public void setFinder(JIRAChangeGroupFinder finder) {
		_finder = finder;
	}

	private static JIRAChangeGroupFinderUtil _getUtil() {
		if (_util == null) {
			_util = (JIRAChangeGroupFinderUtil)com.liferay.portlet.service.BeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = JIRAChangeGroupFinderUtil.class.getName();
	private static JIRAChangeGroupFinderUtil _util;
	private JIRAChangeGroupFinder _finder;
}