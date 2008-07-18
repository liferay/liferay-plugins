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
 * <a href="CommunityUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class CommunityUtil {
	public static com.liferay.portal.iweb.model.Community create(long cid) {
		return getPersistence().create(cid);
	}

	public static com.liferay.portal.iweb.model.Community remove(long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchCommunityException {
		return getPersistence().remove(cid);
	}

	public static com.liferay.portal.iweb.model.Community remove(
		com.liferay.portal.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(community);
	}

	public static com.liferay.portal.iweb.model.Community update(
		com.liferay.portal.iweb.model.Community community)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(community);
	}

	public static com.liferay.portal.iweb.model.Community update(
		com.liferay.portal.iweb.model.Community community, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(community, merge);
	}

	public static com.liferay.portal.iweb.model.Community updateImpl(
		com.liferay.portal.iweb.model.Community community, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(community, merge);
	}

	public static com.liferay.portal.iweb.model.Community findByPrimaryKey(
		long cid)
		throws com.liferay.portal.SystemException,
			com.liferay.portal.iweb.NoSuchCommunityException {
		return getPersistence().findByPrimaryKey(cid);
	}

	public static com.liferay.portal.iweb.model.Community fetchByPrimaryKey(
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

	public static java.util.List<com.liferay.portal.iweb.model.Community> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.iweb.model.Community> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.iweb.model.Community> findAll(
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

	public static java.util.List<com.liferay.portal.iweb.model.Semantics> getSemanticss(
		long pk) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticss(pk);
	}

	public static java.util.List<com.liferay.portal.iweb.model.Semantics> getSemanticss(
		long pk, int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticss(pk, start, end);
	}

	public static java.util.List<com.liferay.portal.iweb.model.Semantics> getSemanticss(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticss(pk, start, end, obc);
	}

	public static int getSemanticssSize(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().getSemanticssSize(pk);
	}

	public static boolean containsSemantics(long pk,
		java.lang.String semanticsPK) throws com.liferay.portal.SystemException {
		return getPersistence().containsSemantics(pk, semanticsPK);
	}

	public static boolean containsSemanticss(long pk)
		throws com.liferay.portal.SystemException {
		return getPersistence().containsSemanticss(pk);
	}

	public static void addSemantics(long pk, java.lang.String semanticsPK)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemantics(pk, semanticsPK);
	}

	public static void addSemantics(long pk,
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemantics(pk, semantics);
	}

	public static void addSemanticss(long pk, java.lang.String[] semanticsPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticss(pk, semanticsPKs);
	}

	public static void addSemanticss(long pk,
		java.util.List<com.liferay.portal.iweb.model.Semantics> semanticss)
		throws com.liferay.portal.SystemException {
		getPersistence().addSemanticss(pk, semanticss);
	}

	public static void clearSemanticss(long pk)
		throws com.liferay.portal.SystemException {
		getPersistence().clearSemanticss(pk);
	}

	public static void removeSemantics(long pk, java.lang.String semanticsPK)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemantics(pk, semanticsPK);
	}

	public static void removeSemantics(long pk,
		com.liferay.portal.iweb.model.Semantics semantics)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemantics(pk, semantics);
	}

	public static void removeSemanticss(long pk, java.lang.String[] semanticsPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticss(pk, semanticsPKs);
	}

	public static void removeSemanticss(long pk,
		java.util.List<com.liferay.portal.iweb.model.Semantics> semanticss)
		throws com.liferay.portal.SystemException {
		getPersistence().removeSemanticss(pk, semanticss);
	}

	public static void setSemanticss(long pk, java.lang.String[] semanticsPKs)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticss(pk, semanticsPKs);
	}

	public static void setSemanticss(long pk,
		java.util.List<com.liferay.portal.iweb.model.Semantics> semanticss)
		throws com.liferay.portal.SystemException {
		getPersistence().setSemanticss(pk, semanticss);
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static CommunityPersistence getPersistence() {
		return _getUtil()._persistence;
	}

	public void setPersistence(CommunityPersistence persistence) {
		_persistence = persistence;
	}

	private static CommunityUtil _getUtil() {
		if (_util == null) {
			_util = (CommunityUtil)com.liferay.util.bean.PortletBeanLocatorUtil.locate(_UTIL);
		}

		return _util;
	}

	private static final String _UTIL = CommunityUtil.class.getName();
	private static CommunityUtil _util;
	private CommunityPersistence _persistence;
}