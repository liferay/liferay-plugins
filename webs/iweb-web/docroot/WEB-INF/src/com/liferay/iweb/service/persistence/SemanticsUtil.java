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
 * <a href="SemanticsUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsUtil {
	public static com.liferay.iweb.model.Semantics create(
		java.lang.String semanticsURI) {
		return getPersistence().create(semanticsURI);
	}

	public static com.liferay.iweb.model.Semantics remove(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(semanticsURI);
	}

	public static com.liferay.iweb.model.Semantics remove(
		com.liferay.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(semantics);
	}

	public static com.liferay.iweb.model.Semantics update(
		com.liferay.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semantics);
	}

	public static com.liferay.iweb.model.Semantics update(
		com.liferay.iweb.model.Semantics semantics, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(semantics, merge);
	}

	public static com.liferay.iweb.model.Semantics updateImpl(
		com.liferay.iweb.model.Semantics semantics, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(semantics, merge);
	}

	public static com.liferay.iweb.model.Semantics findByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(semanticsURI);
	}

	public static com.liferay.iweb.model.Semantics fetchByPrimaryKey(
		java.lang.String semanticsURI)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(semanticsURI);
	}

	public static java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity) throws com.liferay.portal.SystemException {
		return getPersistence().findByCreatedInCommunity(createdInCommunity);
	}

	public static java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInCommunity(createdInCommunity, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.Semantics> findByCreatedInCommunity(
		long createdInCommunity, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInCommunity(createdInCommunity, start, end, obc);
	}

	public static com.liferay.iweb.model.Semantics findByCreatedInCommunity_First(
		long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInCommunity_First(createdInCommunity, obc);
	}

	public static com.liferay.iweb.model.Semantics findByCreatedInCommunity_Last(
		long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInCommunity_Last(createdInCommunity, obc);
	}

	public static com.liferay.iweb.model.Semantics[] findByCreatedInCommunity_PrevAndNext(
		java.lang.String semanticsURI, long createdInCommunity,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.iweb.NoSuchSemanticsException,
			com.liferay.portal.SystemException {
		return getPersistence()
				   .findByCreatedInCommunity_PrevAndNext(semanticsURI,
			createdInCommunity, obc);
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

	public static java.util.List<com.liferay.iweb.model.Semantics> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.iweb.model.Semantics> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.iweb.model.Semantics> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCreatedInCommunity(long createdInCommunity)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCreatedInCommunity(createdInCommunity);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCreatedInCommunity(long createdInCommunity)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCreatedInCommunity(createdInCommunity);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk) throws com.liferay.portal.SystemException {
		return getPersistence().getCommunities(pk);
	}

	public static java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().getCommunities(pk, start, end);
	}

	public static java.util.List<com.liferay.iweb.model.Community> getCommunities(
		java.lang.String pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getCommunities(pk, start, end, obc);
	}

	public static int getCommunitiesSize(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getCommunitiesSize(pk);
	}

	public static boolean containsCommunity(java.lang.String pk,
		long communityPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsCommunity(pk, communityPK);
	}

	public static boolean containsCommunities(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsCommunities(pk);
	}

	public static void addCommunity(java.lang.String pk, long communityPK)
		throws com.liferay.portal.SystemException {
		getPersistence().addCommunity(pk, communityPK);
	}

	public static void addCommunity(java.lang.String pk,
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		getPersistence().addCommunity(pk, community);
	}

	public static void addCommunities(java.lang.String pk, long[] communityPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addCommunities(pk, communityPKs);
	}

	public static void addCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException {
		getPersistence().addCommunities(pk, communities);
	}

	public static void clearCommunities(java.lang.String pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearCommunities(pk);
	}

	public static void removeCommunity(java.lang.String pk, long communityPK)
		throws com.liferay.portal.SystemException {
		getPersistence().removeCommunity(pk, communityPK);
	}

	public static void removeCommunity(java.lang.String pk,
		com.liferay.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		getPersistence().removeCommunity(pk, community);
	}

	public static void removeCommunities(java.lang.String pk,
		long[] communityPKs) throws com.liferay.portal.SystemException {
		getPersistence().removeCommunities(pk, communityPKs);
	}

	public static void removeCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException {
		getPersistence().removeCommunities(pk, communities);
	}

	public static void setCommunities(java.lang.String pk, long[] communityPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setCommunities(pk, communityPKs);
	}

	public static void setCommunities(java.lang.String pk,
		java.util.List<com.liferay.iweb.model.Community> communities)
		throws com.liferay.portal.SystemException {
		getPersistence().setCommunities(pk, communities);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static SemanticsPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(SemanticsPersistence persistence) {
		_persistence = persistence;
	}

	private static SemanticsUtil _getUtil() {
		if (_util == null) {
			_util = (SemanticsUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = SemanticsUtil.class.getName();
	private static SemanticsUtil _util;
	private SemanticsPersistence _persistence;
}