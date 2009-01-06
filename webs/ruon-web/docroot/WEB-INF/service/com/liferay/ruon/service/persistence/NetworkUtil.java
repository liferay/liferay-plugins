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

package com.liferay.ruon.service.persistence;

/**
 * <a href="NetworkUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class NetworkUtil {
	public static com.liferay.ruon.model.Network create(long networkId) {
		return getPersistence().create(networkId);
	}

	public static com.liferay.ruon.model.Network remove(long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchNetworkException {
		return getPersistence().remove(networkId);
	}

	public static com.liferay.ruon.model.Network remove(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(network);
	}

	public static com.liferay.ruon.model.Network update(
		com.liferay.ruon.model.Network network)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(network);
	}

	public static com.liferay.ruon.model.Network update(
		com.liferay.ruon.model.Network network, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(network, merge);
	}

	public static com.liferay.ruon.model.Network updateImpl(
		com.liferay.ruon.model.Network network, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(network, merge);
	}

	public static com.liferay.ruon.model.Network findByPrimaryKey(
		long networkId)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchNetworkException {
		return getPersistence().findByPrimaryKey(networkId);
	}

	public static com.liferay.ruon.model.Network fetchByPrimaryKey(
		long networkId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(networkId);
	}

	public static com.liferay.ruon.model.Network findByName(
		java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchNetworkException {
		return getPersistence().findByName(name);
	}

	public static com.liferay.ruon.model.Network fetchByName(
		java.lang.String name) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByName(name);
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

	public static java.util.List<com.liferay.ruon.model.Network> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ruon.model.Network> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ruon.model.Network> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.SystemException,
			com.liferay.ruon.NoSuchNetworkException {
		getPersistence().removeByName(name);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByName(java.lang.String name)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByName(name);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static NetworkPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(NetworkPersistence persistence) {
		_persistence = persistence;
	}

	private static NetworkPersistence _persistence;
}