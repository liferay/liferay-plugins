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

package com.liferay.ams.service.base;

import com.liferay.ams.model.AssetDefinition;
import com.liferay.ams.service.AssetCheckoutLocalService;
import com.liferay.ams.service.AssetDefinitionLocalService;
import com.liferay.ams.service.AssetEntryLocalService;
import com.liferay.ams.service.AssetTypeLocalService;
import com.liferay.ams.service.persistence.AssetCheckoutPersistence;
import com.liferay.ams.service.persistence.AssetDefinitionPersistence;
import com.liferay.ams.service.persistence.AssetEntryPersistence;
import com.liferay.ams.service.persistence.AssetTypePersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

import java.util.List;

/**
 * <a href="AssetDefinitionLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class AssetDefinitionLocalServiceBaseImpl
	implements AssetDefinitionLocalService {
	public AssetDefinition addAssetDefinition(AssetDefinition assetDefinition)
		throws SystemException {
		assetDefinition.setNew(true);

		return assetDefinitionPersistence.update(assetDefinition, false);
	}

	public AssetDefinition createAssetDefinition(long assetDefinitionId) {
		return assetDefinitionPersistence.create(assetDefinitionId);
	}

	public void deleteAssetDefinition(long assetDefinitionId)
		throws PortalException, SystemException {
		assetDefinitionPersistence.remove(assetDefinitionId);
	}

	public void deleteAssetDefinition(AssetDefinition assetDefinition)
		throws SystemException {
		assetDefinitionPersistence.remove(assetDefinition);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return assetDefinitionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return assetDefinitionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	public AssetDefinition getAssetDefinition(long assetDefinitionId)
		throws PortalException, SystemException {
		return assetDefinitionPersistence.findByPrimaryKey(assetDefinitionId);
	}

	public List<AssetDefinition> getAssetDefinitions(int start, int end)
		throws SystemException {
		return assetDefinitionPersistence.findAll(start, end);
	}

	public int getAssetDefinitionsCount() throws SystemException {
		return assetDefinitionPersistence.countAll();
	}

	public AssetDefinition updateAssetDefinition(
		AssetDefinition assetDefinition) throws SystemException {
		assetDefinition.setNew(false);

		return assetDefinitionPersistence.update(assetDefinition, true);
	}

	public AssetCheckoutLocalService getAssetCheckoutLocalService() {
		return assetCheckoutLocalService;
	}

	public void setAssetCheckoutLocalService(
		AssetCheckoutLocalService assetCheckoutLocalService) {
		this.assetCheckoutLocalService = assetCheckoutLocalService;
	}

	public AssetCheckoutPersistence getAssetCheckoutPersistence() {
		return assetCheckoutPersistence;
	}

	public void setAssetCheckoutPersistence(
		AssetCheckoutPersistence assetCheckoutPersistence) {
		this.assetCheckoutPersistence = assetCheckoutPersistence;
	}

	public AssetDefinitionLocalService getAssetDefinitionLocalService() {
		return assetDefinitionLocalService;
	}

	public void setAssetDefinitionLocalService(
		AssetDefinitionLocalService assetDefinitionLocalService) {
		this.assetDefinitionLocalService = assetDefinitionLocalService;
	}

	public AssetDefinitionPersistence getAssetDefinitionPersistence() {
		return assetDefinitionPersistence;
	}

	public void setAssetDefinitionPersistence(
		AssetDefinitionPersistence assetDefinitionPersistence) {
		this.assetDefinitionPersistence = assetDefinitionPersistence;
	}

	public AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	public AssetTypeLocalService getAssetTypeLocalService() {
		return assetTypeLocalService;
	}

	public void setAssetTypeLocalService(
		AssetTypeLocalService assetTypeLocalService) {
		this.assetTypeLocalService = assetTypeLocalService;
	}

	public AssetTypePersistence getAssetTypePersistence() {
		return assetTypePersistence;
	}

	public void setAssetTypePersistence(
		AssetTypePersistence assetTypePersistence) {
		this.assetTypePersistence = assetTypePersistence;
	}

	@BeanReference(name = "com.liferay.ams.service.AssetCheckoutLocalService.impl")
	protected AssetCheckoutLocalService assetCheckoutLocalService;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetCheckoutPersistence.impl")
	protected AssetCheckoutPersistence assetCheckoutPersistence;
	@BeanReference(name = "com.liferay.ams.service.AssetDefinitionLocalService.impl")
	protected AssetDefinitionLocalService assetDefinitionLocalService;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetDefinitionPersistence.impl")
	protected AssetDefinitionPersistence assetDefinitionPersistence;
	@BeanReference(name = "com.liferay.ams.service.AssetEntryLocalService.impl")
	protected AssetEntryLocalService assetEntryLocalService;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetEntryPersistence.impl")
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(name = "com.liferay.ams.service.AssetTypeLocalService.impl")
	protected AssetTypeLocalService assetTypeLocalService;
	@BeanReference(name = "com.liferay.ams.service.persistence.AssetTypePersistence.impl")
	protected AssetTypePersistence assetTypePersistence;
}