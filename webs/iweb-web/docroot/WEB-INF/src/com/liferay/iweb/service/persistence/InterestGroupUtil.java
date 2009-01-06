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
 * <a href="InterestGroupUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class InterestGroupUtil {
	public static com.liferay.iweb.model.InterestGroup create(long cid) {
		return getPersistence().create(cid);
	}

	public static com.liferay.iweb.model.InterestGroup remove(long cid)
		throws com.liferay.iweb.NoSuchInterestGroupException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(cid);
	}

	public static com.liferay.iweb.model.InterestGroup remove(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(interestGroup);
	}

	public static com.liferay.iweb.model.InterestGroup update(
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(interestGroup);
	}

	public static com.liferay.iweb.model.InterestGroup update(
		com.liferay.iweb.model.InterestGroup interestGroup, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(interestGroup, merge);
	}

	public static com.liferay.iweb.model.InterestGroup updateImpl(
		com.liferay.iweb.model.InterestGroup interestGroup, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(interestGroup, merge);
	}

	public static com.liferay.iweb.model.InterestGroup findByPrimaryKey(
		long cid)
		throws com.liferay.iweb.NoSuchInterestGroupException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(cid);
	}

	public static com.liferay.iweb.model.InterestGroup fetchByPrimaryKey(
		long cid) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(cid);
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

	public static java.util.List<com.liferay.iweb.model.InterestGroup> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.iweb.model.InterestGroup> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.iweb.model.InterestGroup> findAll(
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

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsFiles(pk);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsFiles(pk, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> getSemanticsFiles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsFiles(pk, start, end, obc);
	}

	public static int getSemanticsFilesSize(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticsFilesSize(pk);
	}

	public static boolean containsSemanticsFile(long pk,
		java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsSemanticsFile(pk, semanticsFilePK);
	}

	public static boolean containsSemanticsFiles(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsSemanticsFiles(pk);
	}

	public static void addSemanticsFile(long pk,
		java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsFile(pk, semanticsFilePK);
	}

	public static void addSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsFile(pk, semanticsFile);
	}

	public static void addSemanticsFiles(long pk,
		java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsFiles(pk, semanticsFilePKs);
	}

	public static void addSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticsFiles(pk, semanticsFiles);
	}

	public static void clearSemanticsFiles(long pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearSemanticsFiles(pk);
	}

	public static void removeSemanticsFile(long pk,
		java.lang.String semanticsFilePK)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsFile(pk, semanticsFilePK);
	}

	public static void removeSemanticsFile(long pk,
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsFile(pk, semanticsFile);
	}

	public static void removeSemanticsFiles(long pk,
		java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsFiles(pk, semanticsFilePKs);
	}

	public static void removeSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticsFiles(pk, semanticsFiles);
	}

	public static void setSemanticsFiles(long pk,
		java.lang.String[] semanticsFilePKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticsFiles(pk, semanticsFilePKs);
	}

	public static void setSemanticsFiles(long pk,
		java.util.List<com.liferay.iweb.model.SemanticsFile> semanticsFiles)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticsFiles(pk, semanticsFiles);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static InterestGroupPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(InterestGroupPersistence persistence) {
		_persistence = persistence;
	}

	private static InterestGroupUtil _getUtil() {
		if (_util == null) {
			_util = (InterestGroupUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = InterestGroupUtil.class.getName();
	private static InterestGroupUtil _util;
	private InterestGroupPersistence _persistence;
}