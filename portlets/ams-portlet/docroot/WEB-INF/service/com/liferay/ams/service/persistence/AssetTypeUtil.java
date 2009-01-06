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

package com.liferay.ams.service.persistence;

/**
 * <a href="AssetTypeUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetTypeUtil {
	public static com.liferay.ams.model.AssetType create(long assetTypeId) {
		return getPersistence().create(assetTypeId);
	}

	public static com.liferay.ams.model.AssetType remove(long assetTypeId)
		throws com.liferay.ams.NoSuchAssetTypeException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(assetTypeId);
	}

	public static com.liferay.ams.model.AssetType remove(
		com.liferay.ams.model.AssetType assetType)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(assetType);
	}

	public static com.liferay.ams.model.AssetType update(
		com.liferay.ams.model.AssetType assetType)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(assetType);
	}

	public static com.liferay.ams.model.AssetType update(
		com.liferay.ams.model.AssetType assetType, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(assetType, merge);
	}

	public static com.liferay.ams.model.AssetType updateImpl(
		com.liferay.ams.model.AssetType assetType, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(assetType, merge);
	}

	public static com.liferay.ams.model.AssetType findByPrimaryKey(
		long assetTypeId)
		throws com.liferay.ams.NoSuchAssetTypeException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(assetTypeId);
	}

	public static com.liferay.ams.model.AssetType fetchByPrimaryKey(
		long assetTypeId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(assetTypeId);
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

	public static java.util.List<com.liferay.ams.model.AssetType> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.AssetType> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.AssetType> findAll(
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

	public static AssetTypePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(AssetTypePersistence persistence) {
		_persistence = persistence;
	}

	private static AssetTypePersistence _persistence;
}