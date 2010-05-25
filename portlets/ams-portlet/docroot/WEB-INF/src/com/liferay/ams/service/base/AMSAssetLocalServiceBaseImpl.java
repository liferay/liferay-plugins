/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ams.service.base;

import com.liferay.ams.model.AMSAsset;
import com.liferay.ams.service.AMSAssetLocalService;
import com.liferay.ams.service.AMSCheckoutLocalService;
import com.liferay.ams.service.AMSDefinitionLocalService;
import com.liferay.ams.service.AMSTypeLocalService;
import com.liferay.ams.service.persistence.AMSAssetPersistence;
import com.liferay.ams.service.persistence.AMSCheckoutPersistence;
import com.liferay.ams.service.persistence.AMSDefinitionPersistence;
import com.liferay.ams.service.persistence.AMSTypePersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.util.List;

import javax.sql.DataSource;

/**
 * <a href="AMSAssetLocalServiceBaseImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Brian Wing Shun Chan
 */
public abstract class AMSAssetLocalServiceBaseImpl
	implements AMSAssetLocalService {
	public AMSAsset addAMSAsset(AMSAsset amsAsset) throws SystemException {
		amsAsset.setNew(true);

		return amsAssetPersistence.update(amsAsset, false);
	}

	public AMSAsset createAMSAsset(long amsAssetId) {
		return amsAssetPersistence.create(amsAssetId);
	}

	public void deleteAMSAsset(long amsAssetId)
		throws PortalException, SystemException {
		amsAssetPersistence.remove(amsAssetId);
	}

	public void deleteAMSAsset(AMSAsset amsAsset) throws SystemException {
		amsAssetPersistence.remove(amsAsset);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return amsAssetPersistence.findWithDynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return amsAssetPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return amsAssetPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return amsAssetPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public AMSAsset getAMSAsset(long amsAssetId)
		throws PortalException, SystemException {
		return amsAssetPersistence.findByPrimaryKey(amsAssetId);
	}

	public List<AMSAsset> getAMSAssets(int start, int end)
		throws SystemException {
		return amsAssetPersistence.findAll(start, end);
	}

	public int getAMSAssetsCount() throws SystemException {
		return amsAssetPersistence.countAll();
	}

	public AMSAsset updateAMSAsset(AMSAsset amsAsset) throws SystemException {
		amsAsset.setNew(false);

		return amsAssetPersistence.update(amsAsset, true);
	}

	public AMSAsset updateAMSAsset(AMSAsset amsAsset, boolean merge)
		throws SystemException {
		amsAsset.setNew(false);

		return amsAssetPersistence.update(amsAsset, merge);
	}

	public AMSAssetLocalService getAMSAssetLocalService() {
		return amsAssetLocalService;
	}

	public void setAMSAssetLocalService(
		AMSAssetLocalService amsAssetLocalService) {
		this.amsAssetLocalService = amsAssetLocalService;
	}

	public AMSAssetPersistence getAMSAssetPersistence() {
		return amsAssetPersistence;
	}

	public void setAMSAssetPersistence(AMSAssetPersistence amsAssetPersistence) {
		this.amsAssetPersistence = amsAssetPersistence;
	}

	public AMSCheckoutLocalService getAMSCheckoutLocalService() {
		return amsCheckoutLocalService;
	}

	public void setAMSCheckoutLocalService(
		AMSCheckoutLocalService amsCheckoutLocalService) {
		this.amsCheckoutLocalService = amsCheckoutLocalService;
	}

	public AMSCheckoutPersistence getAMSCheckoutPersistence() {
		return amsCheckoutPersistence;
	}

	public void setAMSCheckoutPersistence(
		AMSCheckoutPersistence amsCheckoutPersistence) {
		this.amsCheckoutPersistence = amsCheckoutPersistence;
	}

	public AMSDefinitionLocalService getAMSDefinitionLocalService() {
		return amsDefinitionLocalService;
	}

	public void setAMSDefinitionLocalService(
		AMSDefinitionLocalService amsDefinitionLocalService) {
		this.amsDefinitionLocalService = amsDefinitionLocalService;
	}

	public AMSDefinitionPersistence getAMSDefinitionPersistence() {
		return amsDefinitionPersistence;
	}

	public void setAMSDefinitionPersistence(
		AMSDefinitionPersistence amsDefinitionPersistence) {
		this.amsDefinitionPersistence = amsDefinitionPersistence;
	}

	public AMSTypeLocalService getAMSTypeLocalService() {
		return amsTypeLocalService;
	}

	public void setAMSTypeLocalService(AMSTypeLocalService amsTypeLocalService) {
		this.amsTypeLocalService = amsTypeLocalService;
	}

	public AMSTypePersistence getAMSTypePersistence() {
		return amsTypePersistence;
	}

	public void setAMSTypePersistence(AMSTypePersistence amsTypePersistence) {
		this.amsTypePersistence = amsTypePersistence;
	}

	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = amsAssetPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AMSAssetLocalService.class)
	protected AMSAssetLocalService amsAssetLocalService;
	@BeanReference(type = AMSAssetPersistence.class)
	protected AMSAssetPersistence amsAssetPersistence;
	@BeanReference(type = AMSCheckoutLocalService.class)
	protected AMSCheckoutLocalService amsCheckoutLocalService;
	@BeanReference(type = AMSCheckoutPersistence.class)
	protected AMSCheckoutPersistence amsCheckoutPersistence;
	@BeanReference(type = AMSDefinitionLocalService.class)
	protected AMSDefinitionLocalService amsDefinitionLocalService;
	@BeanReference(type = AMSDefinitionPersistence.class)
	protected AMSDefinitionPersistence amsDefinitionPersistence;
	@BeanReference(type = AMSTypeLocalService.class)
	protected AMSTypeLocalService amsTypeLocalService;
	@BeanReference(type = AMSTypePersistence.class)
	protected AMSTypePersistence amsTypePersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
}