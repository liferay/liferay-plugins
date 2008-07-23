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
 * <a href="SemanticsFileUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFileUtil {
	public static com.liferay.iweb.model.SemanticsFile create(
		java.lang.String semanticsURI) {
		return getPersistence().create(semanticsURI);
	}

	public static com.liferay.iweb.model.SemanticsFile remove(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(semanticsURI);
	}

	public static com.liferay.iweb.model.SemanticsFile remove(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(semanticsFile);
	}

	public static com.liferay.iweb.model.SemanticsFile update(
		com.liferay.iweb.model.SemanticsFile semanticsFile)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semanticsFile);
	}

	public static com.liferay.iweb.model.SemanticsFile update(
		com.liferay.iweb.model.SemanticsFile semanticsFile, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semanticsFile, merge);
	}

	public static com.liferay.iweb.model.SemanticsFile updateImpl(
		com.liferay.iweb.model.SemanticsFile semanticsFile, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(semanticsFile, merge);
	}

	public static com.liferay.iweb.model.SemanticsFile findByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(semanticsURI);
	}

	public static com.liferay.iweb.model.SemanticsFile fetchByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(semanticsURI);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup) throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup(createdInInterestGroup);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup(createdInInterestGroup, start,
			end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findByCreatedInInterestGroup(
		long createdInInterestGroup, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup(createdInInterestGroup, start,
			end, obc);
	}

	public static com.liferay.iweb.model.SemanticsFile findByCreatedInInterestGroup_First(
		long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup_First(createdInInterestGroup,
			obc);
	}

	public static com.liferay.iweb.model.SemanticsFile findByCreatedInInterestGroup_Last(
		long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup_Last(createdInInterestGroup,
			obc);
	}

	public static com.liferay.iweb.model.SemanticsFile[] findByCreatedInInterestGroup_PrevAndNext(
		java.lang.String semanticsURI, long createdInInterestGroup,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsFileException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInInterestGroup_PrevAndNext(semanticsURI,
			createdInInterestGroup, obc);
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

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.iweb.model.SemanticsFile> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCreatedInInterestGroup(
		long createdInInterestGroup) throws com.liferay.portal.SystemException {
		getPersistence().removeByCreatedInInterestGroup(createdInInterestGroup);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCreatedInInterestGroup(long createdInInterestGroup)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .countByCreatedInInterestGroup(createdInInterestGroup);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk) throws com.liferay.portal.SystemException {
		return getPersistence().getInterestGroups(pk);
	}

	public static java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().getInterestGroups(pk, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.InterestGroup> getInterestGroups(
		java.lang.String pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getInterestGroups(pk, start, end, obc);
	}

	public static int getInterestGroupsSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getInterestGroupsSize(pk);
	}

	public static boolean containsInterestGroup(java.lang.String pk,
		long interestGroupPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsInterestGroup(pk, interestGroupPK);
	}

	public static boolean containsInterestGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsInterestGroups(pk);
	}

	public static void addInterestGroup(java.lang.String pk,
		long interestGroupPK) throws com.liferay.portal.SystemException {
		getPersistence().addInterestGroup(pk, interestGroupPK);
	}

	public static void addInterestGroup(java.lang.String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException {
		getPersistence().addInterestGroup(pk, interestGroup);
	}

	public static void addInterestGroups(java.lang.String pk,
		long[] interestGroupPKs) throws com.liferay.portal.SystemException {
		getPersistence().addInterestGroups(pk, interestGroupPKs);
	}

	public static void addInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException {
		getPersistence().addInterestGroups(pk, interestGroups);
	}

	public static void clearInterestGroups(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearInterestGroups(pk);
	}

	public static void removeInterestGroup(java.lang.String pk,
		long interestGroupPK) throws com.liferay.portal.SystemException {
		getPersistence().removeInterestGroup(pk, interestGroupPK);
	}

	public static void removeInterestGroup(java.lang.String pk,
		com.liferay.iweb.model.InterestGroup interestGroup)
		throws com.liferay.portal.SystemException {
		getPersistence().removeInterestGroup(pk, interestGroup);
	}

	public static void removeInterestGroups(java.lang.String pk,
		long[] interestGroupPKs) throws com.liferay.portal.SystemException {
		getPersistence().removeInterestGroups(pk, interestGroupPKs);
	}

	public static void removeInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException {
		getPersistence().removeInterestGroups(pk, interestGroups);
	}

	public static void setInterestGroups(java.lang.String pk,
		long[] interestGroupPKs) throws com.liferay.portal.SystemException {
		getPersistence().setInterestGroups(pk, interestGroupPKs);
	}

	public static void setInterestGroups(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.InterestGroup> interestGroups)
		throws com.liferay.portal.SystemException {
		getPersistence().setInterestGroups(pk, interestGroups);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static SemanticsFilePersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(SemanticsFilePersistence persistence) {
		_persistence = persistence;
	}

	private static SemanticsFileUtil _getUtil() {
		if (_util == null) {
			_util = (SemanticsFileUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = SemanticsFileUtil.class.getName();
	private static SemanticsFileUtil _util;
	private SemanticsFilePersistence _persistence;
}