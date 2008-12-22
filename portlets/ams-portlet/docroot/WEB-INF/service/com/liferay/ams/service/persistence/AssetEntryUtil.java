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

package com.liferay.ams.service.persistence;

/**
 * <a href="AssetEntryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetEntryUtil {
	public static com.liferay.ams.model.AssetEntry create(long assetEntryId) {
		return getPersistence().create(assetEntryId);
	}

	public static com.liferay.ams.model.AssetEntry remove(long assetEntryId)
		throws com.liferay.ams.NoSuchAssetEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().remove(assetEntryId);
	}

	public static com.liferay.ams.model.AssetEntry remove(
		com.liferay.ams.model.AssetEntry assetEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(assetEntry);
	}

	public static com.liferay.ams.model.AssetEntry update(
		com.liferay.ams.model.AssetEntry assetEntry)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(assetEntry);
	}

	public static com.liferay.ams.model.AssetEntry update(
		com.liferay.ams.model.AssetEntry assetEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(assetEntry, merge);
	}

	public static com.liferay.ams.model.AssetEntry updateImpl(
		com.liferay.ams.model.AssetEntry assetEntry, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(assetEntry, merge);
	}

	public static com.liferay.ams.model.AssetEntry findByPrimaryKey(
		long assetEntryId)
		throws com.liferay.ams.NoSuchAssetEntryException,
			com.liferay.portal.SystemException {
		return getPersistence().findByPrimaryKey(assetEntryId);
	}

	public static com.liferay.ams.model.AssetEntry fetchByPrimaryKey(
		long assetEntryId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(assetEntryId);
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

	public static java.util.List<com.liferay.ams.model.AssetEntry> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.AssetEntry> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.AssetEntry> findAll(
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

	public static void registerListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().registerListener(listener);
	}

	public static void unregisterListener(
		com.liferay.portal.model.ModelListener listener) {
		getPersistence().unregisterListener(listener);
	}

	public static AssetEntryPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(AssetEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static AssetEntryPersistence _persistence;
}