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

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;

/**
 * <a href="AssetEntryPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssetEntryPersistence {
	public com.liferay.ams.model.AssetEntry create(long assetEntryId);

	public com.liferay.ams.model.AssetEntry remove(long assetEntryId)
		throws com.liferay.ams.NoSuchAssetEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetEntry remove(
		com.liferay.ams.model.AssetEntry assetEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetEntry update(
		com.liferay.ams.model.AssetEntry assetEntry)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetEntry update(
		com.liferay.ams.model.AssetEntry assetEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetEntry updateImpl(
		com.liferay.ams.model.AssetEntry assetEntry, boolean merge)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.ams.model.AssetEntry findByPrimaryKey(long assetEntryId)
		throws com.liferay.ams.NoSuchAssetEntryException,
			com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetEntry fetchByPrimaryKey(long assetEntryId)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetEntry> findAll()
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetEntry> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetEntry> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int countAll() throws com.liferay.portal.SystemException;

	public void registerListener(
		com.liferay.portal.model.ModelListener listener);

	public void unregisterListener(
		com.liferay.portal.model.ModelListener listener);
}