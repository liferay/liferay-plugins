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

package com.liferay.iweb.service.persistence;

/**
 * <a href="SemanticsElementUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementUtil {
	public static com.liferay.iweb.model.SemanticsElement create(
		java.lang.String elementURI) {
		return getPersistence().create(elementURI);
	}

	public static com.liferay.iweb.model.SemanticsElement remove(
		java.lang.String elementURI)
		throws com.liferay.iweb.NoSuchSemanticsElementException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(elementURI);
	}

	public static com.liferay.iweb.model.SemanticsElement remove(
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(semanticsElement);
	}

	public static com.liferay.iweb.model.SemanticsElement update(
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semanticsElement);
	}

	public static com.liferay.iweb.model.SemanticsElement update(
		com.liferay.iweb.model.SemanticsElement semanticsElement, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semanticsElement, merge);
	}

	public static com.liferay.iweb.model.SemanticsElement updateImpl(
		com.liferay.iweb.model.SemanticsElement semanticsElement, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(semanticsElement, merge);
	}

	public static com.liferay.iweb.model.SemanticsElement findByPrimaryKey(
		java.lang.String elementURI)
		throws com.liferay.iweb.NoSuchSemanticsElementException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(elementURI);
	}

	public static com.liferay.iweb.model.SemanticsElement fetchByPrimaryKey(
		java.lang.String elementURI) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(elementURI);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List<com.liferay.iweb.model.PostEntry> getPostEntries(
		java.lang.String pk) throws com.liferay.portal.SystemException {
		return getPersistence().getPostEntries(pk);
	}

	public static java.util.List<com.liferay.iweb.model.PostEntry> getPostEntries(
		java.lang.String pk, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().getPostEntries(pk, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.PostEntry> getPostEntries(
		java.lang.String pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getPostEntries(pk, start, end, obc);
	}

	public static int getPostEntriesSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getPostEntriesSize(pk);
	}

	public static boolean containsPostEntry(java.lang.String pk,
		long postEntryPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsPostEntry(pk, postEntryPK);
	}

	public static boolean containsPostEntries(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsPostEntries(pk);
	}

	public static void addPostEntry(java.lang.String pk, long postEntryPK)
		throws com.liferay.portal.SystemException {
		getPersistence().addPostEntry(pk, postEntryPK);
	}

	public static void addPostEntry(java.lang.String pk,
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException {
		getPersistence().addPostEntry(pk, postEntry);
	}

	public static void addPostEntries(java.lang.String pk, long[] postEntryPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addPostEntries(pk, postEntryPKs);
	}

	public static void addPostEntries(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.PostEntry> postEntries)
		throws com.liferay.portal.SystemException {
		getPersistence().addPostEntries(pk, postEntries);
	}

	public static void clearPostEntries(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearPostEntries(pk);
	}

	public static void removePostEntry(java.lang.String pk, long postEntryPK)
		throws com.liferay.portal.SystemException {
		getPersistence().removePostEntry(pk, postEntryPK);
	}

	public static void removePostEntry(java.lang.String pk,
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException {
		getPersistence().removePostEntry(pk, postEntry);
	}

	public static void removePostEntries(java.lang.String pk,
		long[] postEntryPKs) throws com.liferay.portal.SystemException {
		getPersistence().removePostEntries(pk, postEntryPKs);
	}

	public static void removePostEntries(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.PostEntry> postEntries)
		throws com.liferay.portal.SystemException {
		getPersistence().removePostEntries(pk, postEntries);
	}

	public static void setPostEntries(java.lang.String pk, long[] postEntryPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setPostEntries(pk, postEntryPKs);
	}

	public static void setPostEntries(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.PostEntry> postEntries)
		throws com.liferay.portal.SystemException {
		getPersistence().setPostEntries(pk, postEntries);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static SemanticsElementPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(SemanticsElementPersistence persistence) {
		_persistence = persistence;
	}

	private static SemanticsElementUtil _getUtil() {
		if (_util == null) {
			_util = (SemanticsElementUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = SemanticsElementUtil.class.getName();
	private static SemanticsElementUtil _util;
	private SemanticsElementPersistence _persistence;
}