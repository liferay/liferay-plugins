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

package com.liferay.portal.iweb.service.persistence;

/**
 * <a href="PostUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class PostUtil {
	public static com.liferay.portal.iweb.model.Post create(long uid) {
		return getPersistence().create(uid);
	}

	public static com.liferay.portal.iweb.model.Post remove(long uid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchPostException {
		return getPersistence().remove(uid);
	}

	public static com.liferay.portal.iweb.model.Post remove(
		com.liferay.portal.iweb.model.Post post)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(post);
	}

	public static com.liferay.portal.iweb.model.Post update(
		com.liferay.portal.iweb.model.Post post)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(post);
	}

	public static com.liferay.portal.iweb.model.Post update(
		com.liferay.portal.iweb.model.Post post, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(post, merge);
	}

	public static com.liferay.portal.iweb.model.Post updateImpl(
		com.liferay.portal.iweb.model.Post post, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(post, merge);
	}

	public static com.liferay.portal.iweb.model.Post findByPrimaryKey(long uid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchPostException {
		return getPersistence().findByPrimaryKey(uid);
	}

	public static com.liferay.portal.iweb.model.Post fetchByPrimaryKey(long uid)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(uid);
	}

	public static com.liferay.portal.iweb.model.Post findById_Type(long pid,
		java.lang.String type)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchPostException {
		return getPersistence().findById_Type(pid, type);
	}

	public static com.liferay.portal.iweb.model.Post fetchById_Type(long pid,
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

	public static java.util.List<com.liferay.portal.iweb.model.Post> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.iweb.model.Post> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.iweb.model.Post> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeById_Type(long pid, java.lang.String type)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchPostException {
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

	public static java.util.List<com.liferay.portal.iweb.model.SemanticsElement> getSemanticsElements(
		long pk) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElements(pk);
	}

	public static java.util.List<com.liferay.portal.iweb.model.SemanticsElement> getSemanticsElements(
		long pk, int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsElements(pk, start, end);
	}

	public static java.util.List<com.liferay.portal.iweb.model.SemanticsElement> getSemanticsElements(
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
		com.liferay.portal.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElement(pk, semanticsElement);
	}

	public static void addSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsElements(pk, semanticsElementPKs);
	}

	public static void addSemanticsElements(long pk,
		java.util.List<com.liferay.portal.iweb.model.SemanticsElement> semanticsElements)
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
		com.liferay.portal.iweb.model.SemanticsElement semanticsElement)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElement(pk, semanticsElement);
	}

	public static void removeSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElements(pk, semanticsElementPKs);
	}

	public static void removeSemanticsElements(long pk,
		java.util.List<com.liferay.portal.iweb.model.SemanticsElement> semanticsElements)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsElements(pk, semanticsElements);
	}

	public static void setSemanticsElements(long pk,
		java.lang.String[] semanticsElementPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticsElements(pk, semanticsElementPKs);
	}

	public static void setSemanticsElements(long pk,
		java.util.List<com.liferay.portal.iweb.model.SemanticsElement> semanticsElements)
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

	public static PostPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(PostPersistence persistence) {
		_persistence = persistence;
	}

	private static PostUtil _getUtil() {
		if (_util == null) {
			_util = (PostUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = PostUtil.class.getName();
	private static PostUtil _util;
	private PostPersistence _persistence;
}