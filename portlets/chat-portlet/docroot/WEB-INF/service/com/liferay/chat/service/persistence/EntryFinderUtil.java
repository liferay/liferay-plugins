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

package com.liferay.chat.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * <a href="EntryFinderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class EntryFinderUtil {
	public static java.util.List<com.liferay.chat.model.Entry> findByEmptyContent(
		long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByEmptyContent(fromUserId, toUserId, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByNew(
		long userId, long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByNew(userId, createDate, start, end);
	}

	public static java.util.List<com.liferay.chat.model.Entry> findByOld(
		long createDate, int start, int end)
		throws com.liferay.portal.SystemException {
		return getFinder().findByOld(createDate, start, end);
	}

	public static EntryFinder getFinder() {
		if (_finder == null) {
			_finder = (EntryFinder)PortletBeanLocatorUtil.locate(com.liferay.chat.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					EntryFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(EntryFinder finder) {
		_finder = finder;
	}

	private static EntryFinder _finder;
}