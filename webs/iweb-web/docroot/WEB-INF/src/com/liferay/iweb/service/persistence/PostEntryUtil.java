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

package com.liferay.iweb.service.persistence;

/**
 * <a href="PostEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostEntryUtil {
	public static com.liferay.iweb.model.PostEntry create(long uid) {
		return getPersistence().create(uid);
	}

	public static com.liferay.iweb.model.PostEntry remove(long uid)
		throws com.liferay.iweb.NoSuchPostEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(uid);
	}

	public static com.liferay.iweb.model.PostEntry remove(
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(postEntry);
	}

	public static com.liferay.iweb.model.PostEntry update(
		com.liferay.iweb.model.PostEntry postEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(postEntry);
	}

	public static com.liferay.iweb.model.PostEntry update(
		com.liferay.iweb.model.PostEntry postEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(postEntry, merge);
	}

	public static com.liferay.iweb.model.PostEntry updateImpl(
		com.liferay.iweb.model.PostEntry postEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(postEntry, merge);
	}

	public static com.liferay.iweb.model.PostEntry findByPrimaryKey(long uid)
		throws com.liferay.iweb.NoSuchPostEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(uid);
	}

	public static com.liferay.iweb.model.PostEntry fetchByPrimaryKey(long uid)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(uid);
	}

	public static com.liferay.iweb.model.PostEntry findById_Type(long pid,
		java.lang.String type)
		throws com.liferay.iweb.NoSuchPostEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findById_Type(pid, type);
	}

	public static com.liferay.iweb.model.PostEntry fetchById_Type(long pid,
		java.lang.String type) throws com.liferay.portal.SystemException {
		return getPersistence().fetchById_Type(pid, type);
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

	public static java.util.List<com.liferay.iweb.model.PostEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.iweb.model.PostEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.iweb.model.PostEntry> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeById_Type(long pid, java.lang.String type)
		throws com.liferay.iweb.NoSuchPostEntryException,
			com.liferay.portal.SystemException {
		getPersistence().removeById_Type(pid, type);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countById_Type(long pid, java.lang.String type)
		throws com.liferay.portal.SystemException {
		return getPersistence().countById_Type(pid, type);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElements(pk);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk, int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElements(pk, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsElement> getSemanticsElements(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElements(pk, start, end, obc);
	}

	public static int getSemanticsElementsSize(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElementsSize(pk);
	}

	public static boolean containsSemanticsElement(long pk,
		java.lang.String semanticsElementPK)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsSemanticsElement(pk, semanticsElementPK);
	}

	public static boolean containsSemanticsElements(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsSemanticsElements(pk);
	}

	public static void addSemanticsElement(long pk,
		java.lang.String semanticsElementPK)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElement(pk, semanticsElementPK);
	}

	public static void addSemanticsElement(long pk,
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElement(pk, semanticsElement);
	}

	public static void addSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElements(pk, semanticsElementPKs);
	}

	public static void addSemanticsElements(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElements(pk, semanticsElements);
	}

	public static void clearSemanticsElements(long pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearSemanticsElements(pk);
	}

	public static void removeSemanticsElement(long pk,
		java.lang.String semanticsElementPK)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElement(pk, semanticsElementPK);
	}

	public static void removeSemanticsElement(long pk,
		com.liferay.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElement(pk, semanticsElement);
	}

	public static void removeSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElements(pk, semanticsElementPKs);
	}

	public static void removeSemanticsElements(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElements(pk, semanticsElements);
	}

	public static void setSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticsElements(pk, semanticsElementPKs);
	}

	public static void setSemanticsElements(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsElement> semanticsElements)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticsElements(pk, semanticsElements);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static PostEntryPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(PostEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static PostEntryUtil _getUtil() {
		if (_util == null) {
			_util = (PostEntryUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = PostEntryUtil.class.getName();
	private static PostEntryUtil _util;
	private PostEntryPersistence _persistence;
}