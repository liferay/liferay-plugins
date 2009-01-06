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

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="AssetDefinitionPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
@Transactional(rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssetDefinitionPersistence extends BasePersistence {
	public com.liferay.ams.model.AssetDefinition create(long assetDefinitionId);

	public com.liferay.ams.model.AssetDefinition remove(long assetDefinitionId)
		throws com.liferay.ams.NoSuchAssetDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetDefinition remove(
		com.liferay.ams.model.AssetDefinition assetDefinition)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetDefinition update(
		com.liferay.ams.model.AssetDefinition assetDefinition)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetDefinition update(
		com.liferay.ams.model.AssetDefinition assetDefinition, boolean merge)
		throws com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetDefinition updateImpl(
		com.liferay.ams.model.AssetDefinition assetDefinition, boolean merge)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.ams.model.AssetDefinition findByPrimaryKey(
		long assetDefinitionId)
		throws com.liferay.ams.NoSuchAssetDefinitionException,
			com.liferay.portal.SystemException;

	public com.liferay.ams.model.AssetDefinition fetchByPrimaryKey(
		long assetDefinitionId) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetDefinition> findAll()
		throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetDefinition> findAll(
		int start, int end) throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.ams.model.AssetDefinition> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int countAll() throws com.liferay.portal.SystemException;
}