/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.hr.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.hr.model.HRExpenseType;
import com.liferay.hr.service.HRExpenseAccountLocalService;
import com.liferay.hr.service.HRExpenseCurrencyConversionLocalService;
import com.liferay.hr.service.HRExpenseCurrencyLocalService;
import com.liferay.hr.service.HRExpenseLocalService;
import com.liferay.hr.service.HRExpenseTypeLocalService;
import com.liferay.hr.service.persistence.HRAssetCheckoutPersistence;
import com.liferay.hr.service.persistence.HRAssetDefinitionPersistence;
import com.liferay.hr.service.persistence.HRAssetPersistence;
import com.liferay.hr.service.persistence.HRAssetProductPersistence;
import com.liferay.hr.service.persistence.HRAssetTypePersistence;
import com.liferay.hr.service.persistence.HRAssetVendorPersistence;
import com.liferay.hr.service.persistence.HRBillabilityPersistence;
import com.liferay.hr.service.persistence.HRBranchPersistence;
import com.liferay.hr.service.persistence.HRClientPersistence;
import com.liferay.hr.service.persistence.HREmploymentTypePersistence;
import com.liferay.hr.service.persistence.HRExpenseAccountPersistence;
import com.liferay.hr.service.persistence.HRExpenseCurrencyConversionPersistence;
import com.liferay.hr.service.persistence.HRExpenseCurrencyPersistence;
import com.liferay.hr.service.persistence.HRExpensePersistence;
import com.liferay.hr.service.persistence.HRExpenseTypePersistence;
import com.liferay.hr.service.persistence.HRHolidayPersistence;
import com.liferay.hr.service.persistence.HRJobTitlePersistence;
import com.liferay.hr.service.persistence.HROfficePersistence;
import com.liferay.hr.service.persistence.HRProjectBillingRatePersistence;
import com.liferay.hr.service.persistence.HRProjectPersistence;
import com.liferay.hr.service.persistence.HRProjectRolePersistence;
import com.liferay.hr.service.persistence.HRProjectStatusPersistence;
import com.liferay.hr.service.persistence.HRTaskPersistence;
import com.liferay.hr.service.persistence.HRTaskStatusPersistence;
import com.liferay.hr.service.persistence.HRTerminationTypePersistence;
import com.liferay.hr.service.persistence.HRTimeOffFrequencyTypePersistence;
import com.liferay.hr.service.persistence.HRTimeOffPersistence;
import com.liferay.hr.service.persistence.HRTimeOffPolicyPersistence;
import com.liferay.hr.service.persistence.HRTimeOffTypePersistence;
import com.liferay.hr.service.persistence.HRTimeSheetDayPersistence;
import com.liferay.hr.service.persistence.HRTimeSheetHoursPerDayPersistence;
import com.liferay.hr.service.persistence.HRTimeSheetPersistence;
import com.liferay.hr.service.persistence.HRUserHistoryPersistence;
import com.liferay.hr.service.persistence.HRUserPersistence;
import com.liferay.hr.service.persistence.HRUserProjectPersistence;
import com.liferay.hr.service.persistence.HRUserTaskPersistence;
import com.liferay.hr.service.persistence.HRUserTimeOffPersistence;
import com.liferay.hr.service.persistence.HRWageTypePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the h r expense type local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.hr.service.impl.HRExpenseTypeLocalServiceImpl}.
 * </p>
 *
 * @author Wesley Gong
 * @see com.liferay.hr.service.impl.HRExpenseTypeLocalServiceImpl
 * @see com.liferay.hr.service.HRExpenseTypeLocalServiceUtil
 * @generated
 */
public abstract class HRExpenseTypeLocalServiceBaseImpl
	implements HRExpenseTypeLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.hr.service.HRExpenseTypeLocalServiceUtil} to access the h r expense type local service.
	 */

	/**
	 * Adds the h r expense type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseType the h r expense type
	 * @return the h r expense type that was added
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType addHRExpenseType(HRExpenseType hrExpenseType)
		throws SystemException {
		hrExpenseType.setNew(true);

		hrExpenseType = hrExpenseTypePersistence.update(hrExpenseType, false);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(hrExpenseType);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return hrExpenseType;
	}

	/**
	 * Creates a new h r expense type with the primary key. Does not add the h r expense type to the database.
	 *
	 * @param hrExpenseTypeId the primary key for the new h r expense type
	 * @return the new h r expense type
	 */
	public HRExpenseType createHRExpenseType(long hrExpenseTypeId) {
		return hrExpenseTypePersistence.create(hrExpenseTypeId);
	}

	/**
	 * Deletes the h r expense type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseTypeId the primary key of the h r expense type
	 * @throws PortalException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHRExpenseType(long hrExpenseTypeId)
		throws PortalException, SystemException {
		HRExpenseType hrExpenseType = hrExpenseTypePersistence.remove(hrExpenseTypeId);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(hrExpenseType);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Deletes the h r expense type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseType the h r expense type
	 * @throws SystemException if a system exception occurred
	 */
	public void deleteHRExpenseType(HRExpenseType hrExpenseType)
		throws SystemException {
		hrExpenseTypePersistence.remove(hrExpenseType);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.delete(hrExpenseType);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return hrExpenseTypePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return hrExpenseTypePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return hrExpenseTypePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return hrExpenseTypePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the h r expense type with the primary key.
	 *
	 * @param hrExpenseTypeId the primary key of the h r expense type
	 * @return the h r expense type
	 * @throws PortalException if a h r expense type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType getHRExpenseType(long hrExpenseTypeId)
		throws PortalException, SystemException {
		return hrExpenseTypePersistence.findByPrimaryKey(hrExpenseTypeId);
	}

	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return hrExpenseTypePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the h r expense types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of h r expense types
	 * @param end the upper bound of the range of h r expense types (not inclusive)
	 * @return the range of h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public List<HRExpenseType> getHRExpenseTypes(int start, int end)
		throws SystemException {
		return hrExpenseTypePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of h r expense types.
	 *
	 * @return the number of h r expense types
	 * @throws SystemException if a system exception occurred
	 */
	public int getHRExpenseTypesCount() throws SystemException {
		return hrExpenseTypePersistence.countAll();
	}

	/**
	 * Updates the h r expense type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseType the h r expense type
	 * @return the h r expense type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType updateHRExpenseType(HRExpenseType hrExpenseType)
		throws SystemException {
		return updateHRExpenseType(hrExpenseType, true);
	}

	/**
	 * Updates the h r expense type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param hrExpenseType the h r expense type
	 * @param merge whether to merge the h r expense type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	 * @return the h r expense type that was updated
	 * @throws SystemException if a system exception occurred
	 */
	public HRExpenseType updateHRExpenseType(HRExpenseType hrExpenseType,
		boolean merge) throws SystemException {
		hrExpenseType.setNew(false);

		hrExpenseType = hrExpenseTypePersistence.update(hrExpenseType, merge);

		Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

		if (indexer != null) {
			try {
				indexer.reindex(hrExpenseType);
			}
			catch (SearchException se) {
				if (_log.isWarnEnabled()) {
					_log.warn(se, se);
				}
			}
		}

		return hrExpenseType;
	}

	/**
	 * Returns the h r asset persistence.
	 *
	 * @return the h r asset persistence
	 */
	public HRAssetPersistence getHRAssetPersistence() {
		return hrAssetPersistence;
	}

	/**
	 * Sets the h r asset persistence.
	 *
	 * @param hrAssetPersistence the h r asset persistence
	 */
	public void setHRAssetPersistence(HRAssetPersistence hrAssetPersistence) {
		this.hrAssetPersistence = hrAssetPersistence;
	}

	/**
	 * Returns the h r asset checkout persistence.
	 *
	 * @return the h r asset checkout persistence
	 */
	public HRAssetCheckoutPersistence getHRAssetCheckoutPersistence() {
		return hrAssetCheckoutPersistence;
	}

	/**
	 * Sets the h r asset checkout persistence.
	 *
	 * @param hrAssetCheckoutPersistence the h r asset checkout persistence
	 */
	public void setHRAssetCheckoutPersistence(
		HRAssetCheckoutPersistence hrAssetCheckoutPersistence) {
		this.hrAssetCheckoutPersistence = hrAssetCheckoutPersistence;
	}

	/**
	 * Returns the h r asset definition persistence.
	 *
	 * @return the h r asset definition persistence
	 */
	public HRAssetDefinitionPersistence getHRAssetDefinitionPersistence() {
		return hrAssetDefinitionPersistence;
	}

	/**
	 * Sets the h r asset definition persistence.
	 *
	 * @param hrAssetDefinitionPersistence the h r asset definition persistence
	 */
	public void setHRAssetDefinitionPersistence(
		HRAssetDefinitionPersistence hrAssetDefinitionPersistence) {
		this.hrAssetDefinitionPersistence = hrAssetDefinitionPersistence;
	}

	/**
	 * Returns the h r asset product persistence.
	 *
	 * @return the h r asset product persistence
	 */
	public HRAssetProductPersistence getHRAssetProductPersistence() {
		return hrAssetProductPersistence;
	}

	/**
	 * Sets the h r asset product persistence.
	 *
	 * @param hrAssetProductPersistence the h r asset product persistence
	 */
	public void setHRAssetProductPersistence(
		HRAssetProductPersistence hrAssetProductPersistence) {
		this.hrAssetProductPersistence = hrAssetProductPersistence;
	}

	/**
	 * Returns the h r asset type persistence.
	 *
	 * @return the h r asset type persistence
	 */
	public HRAssetTypePersistence getHRAssetTypePersistence() {
		return hrAssetTypePersistence;
	}

	/**
	 * Sets the h r asset type persistence.
	 *
	 * @param hrAssetTypePersistence the h r asset type persistence
	 */
	public void setHRAssetTypePersistence(
		HRAssetTypePersistence hrAssetTypePersistence) {
		this.hrAssetTypePersistence = hrAssetTypePersistence;
	}

	/**
	 * Returns the h r asset vendor persistence.
	 *
	 * @return the h r asset vendor persistence
	 */
	public HRAssetVendorPersistence getHRAssetVendorPersistence() {
		return hrAssetVendorPersistence;
	}

	/**
	 * Sets the h r asset vendor persistence.
	 *
	 * @param hrAssetVendorPersistence the h r asset vendor persistence
	 */
	public void setHRAssetVendorPersistence(
		HRAssetVendorPersistence hrAssetVendorPersistence) {
		this.hrAssetVendorPersistence = hrAssetVendorPersistence;
	}

	/**
	 * Returns the h r billability persistence.
	 *
	 * @return the h r billability persistence
	 */
	public HRBillabilityPersistence getHRBillabilityPersistence() {
		return hrBillabilityPersistence;
	}

	/**
	 * Sets the h r billability persistence.
	 *
	 * @param hrBillabilityPersistence the h r billability persistence
	 */
	public void setHRBillabilityPersistence(
		HRBillabilityPersistence hrBillabilityPersistence) {
		this.hrBillabilityPersistence = hrBillabilityPersistence;
	}

	/**
	 * Returns the h r branch persistence.
	 *
	 * @return the h r branch persistence
	 */
	public HRBranchPersistence getHRBranchPersistence() {
		return hrBranchPersistence;
	}

	/**
	 * Sets the h r branch persistence.
	 *
	 * @param hrBranchPersistence the h r branch persistence
	 */
	public void setHRBranchPersistence(HRBranchPersistence hrBranchPersistence) {
		this.hrBranchPersistence = hrBranchPersistence;
	}

	/**
	 * Returns the h r client persistence.
	 *
	 * @return the h r client persistence
	 */
	public HRClientPersistence getHRClientPersistence() {
		return hrClientPersistence;
	}

	/**
	 * Sets the h r client persistence.
	 *
	 * @param hrClientPersistence the h r client persistence
	 */
	public void setHRClientPersistence(HRClientPersistence hrClientPersistence) {
		this.hrClientPersistence = hrClientPersistence;
	}

	/**
	 * Returns the h r employment type persistence.
	 *
	 * @return the h r employment type persistence
	 */
	public HREmploymentTypePersistence getHREmploymentTypePersistence() {
		return hrEmploymentTypePersistence;
	}

	/**
	 * Sets the h r employment type persistence.
	 *
	 * @param hrEmploymentTypePersistence the h r employment type persistence
	 */
	public void setHREmploymentTypePersistence(
		HREmploymentTypePersistence hrEmploymentTypePersistence) {
		this.hrEmploymentTypePersistence = hrEmploymentTypePersistence;
	}

	/**
	 * Returns the h r expense local service.
	 *
	 * @return the h r expense local service
	 */
	public HRExpenseLocalService getHRExpenseLocalService() {
		return hrExpenseLocalService;
	}

	/**
	 * Sets the h r expense local service.
	 *
	 * @param hrExpenseLocalService the h r expense local service
	 */
	public void setHRExpenseLocalService(
		HRExpenseLocalService hrExpenseLocalService) {
		this.hrExpenseLocalService = hrExpenseLocalService;
	}

	/**
	 * Returns the h r expense persistence.
	 *
	 * @return the h r expense persistence
	 */
	public HRExpensePersistence getHRExpensePersistence() {
		return hrExpensePersistence;
	}

	/**
	 * Sets the h r expense persistence.
	 *
	 * @param hrExpensePersistence the h r expense persistence
	 */
	public void setHRExpensePersistence(
		HRExpensePersistence hrExpensePersistence) {
		this.hrExpensePersistence = hrExpensePersistence;
	}

	/**
	 * Returns the h r expense account local service.
	 *
	 * @return the h r expense account local service
	 */
	public HRExpenseAccountLocalService getHRExpenseAccountLocalService() {
		return hrExpenseAccountLocalService;
	}

	/**
	 * Sets the h r expense account local service.
	 *
	 * @param hrExpenseAccountLocalService the h r expense account local service
	 */
	public void setHRExpenseAccountLocalService(
		HRExpenseAccountLocalService hrExpenseAccountLocalService) {
		this.hrExpenseAccountLocalService = hrExpenseAccountLocalService;
	}

	/**
	 * Returns the h r expense account persistence.
	 *
	 * @return the h r expense account persistence
	 */
	public HRExpenseAccountPersistence getHRExpenseAccountPersistence() {
		return hrExpenseAccountPersistence;
	}

	/**
	 * Sets the h r expense account persistence.
	 *
	 * @param hrExpenseAccountPersistence the h r expense account persistence
	 */
	public void setHRExpenseAccountPersistence(
		HRExpenseAccountPersistence hrExpenseAccountPersistence) {
		this.hrExpenseAccountPersistence = hrExpenseAccountPersistence;
	}

	/**
	 * Returns the h r expense currency local service.
	 *
	 * @return the h r expense currency local service
	 */
	public HRExpenseCurrencyLocalService getHRExpenseCurrencyLocalService() {
		return hrExpenseCurrencyLocalService;
	}

	/**
	 * Sets the h r expense currency local service.
	 *
	 * @param hrExpenseCurrencyLocalService the h r expense currency local service
	 */
	public void setHRExpenseCurrencyLocalService(
		HRExpenseCurrencyLocalService hrExpenseCurrencyLocalService) {
		this.hrExpenseCurrencyLocalService = hrExpenseCurrencyLocalService;
	}

	/**
	 * Returns the h r expense currency persistence.
	 *
	 * @return the h r expense currency persistence
	 */
	public HRExpenseCurrencyPersistence getHRExpenseCurrencyPersistence() {
		return hrExpenseCurrencyPersistence;
	}

	/**
	 * Sets the h r expense currency persistence.
	 *
	 * @param hrExpenseCurrencyPersistence the h r expense currency persistence
	 */
	public void setHRExpenseCurrencyPersistence(
		HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence) {
		this.hrExpenseCurrencyPersistence = hrExpenseCurrencyPersistence;
	}

	/**
	 * Returns the h r expense currency conversion local service.
	 *
	 * @return the h r expense currency conversion local service
	 */
	public HRExpenseCurrencyConversionLocalService getHRExpenseCurrencyConversionLocalService() {
		return hrExpenseCurrencyConversionLocalService;
	}

	/**
	 * Sets the h r expense currency conversion local service.
	 *
	 * @param hrExpenseCurrencyConversionLocalService the h r expense currency conversion local service
	 */
	public void setHRExpenseCurrencyConversionLocalService(
		HRExpenseCurrencyConversionLocalService hrExpenseCurrencyConversionLocalService) {
		this.hrExpenseCurrencyConversionLocalService = hrExpenseCurrencyConversionLocalService;
	}

	/**
	 * Returns the h r expense currency conversion persistence.
	 *
	 * @return the h r expense currency conversion persistence
	 */
	public HRExpenseCurrencyConversionPersistence getHRExpenseCurrencyConversionPersistence() {
		return hrExpenseCurrencyConversionPersistence;
	}

	/**
	 * Sets the h r expense currency conversion persistence.
	 *
	 * @param hrExpenseCurrencyConversionPersistence the h r expense currency conversion persistence
	 */
	public void setHRExpenseCurrencyConversionPersistence(
		HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence) {
		this.hrExpenseCurrencyConversionPersistence = hrExpenseCurrencyConversionPersistence;
	}

	/**
	 * Returns the h r expense type local service.
	 *
	 * @return the h r expense type local service
	 */
	public HRExpenseTypeLocalService getHRExpenseTypeLocalService() {
		return hrExpenseTypeLocalService;
	}

	/**
	 * Sets the h r expense type local service.
	 *
	 * @param hrExpenseTypeLocalService the h r expense type local service
	 */
	public void setHRExpenseTypeLocalService(
		HRExpenseTypeLocalService hrExpenseTypeLocalService) {
		this.hrExpenseTypeLocalService = hrExpenseTypeLocalService;
	}

	/**
	 * Returns the h r expense type persistence.
	 *
	 * @return the h r expense type persistence
	 */
	public HRExpenseTypePersistence getHRExpenseTypePersistence() {
		return hrExpenseTypePersistence;
	}

	/**
	 * Sets the h r expense type persistence.
	 *
	 * @param hrExpenseTypePersistence the h r expense type persistence
	 */
	public void setHRExpenseTypePersistence(
		HRExpenseTypePersistence hrExpenseTypePersistence) {
		this.hrExpenseTypePersistence = hrExpenseTypePersistence;
	}

	/**
	 * Returns the h r holiday persistence.
	 *
	 * @return the h r holiday persistence
	 */
	public HRHolidayPersistence getHRHolidayPersistence() {
		return hrHolidayPersistence;
	}

	/**
	 * Sets the h r holiday persistence.
	 *
	 * @param hrHolidayPersistence the h r holiday persistence
	 */
	public void setHRHolidayPersistence(
		HRHolidayPersistence hrHolidayPersistence) {
		this.hrHolidayPersistence = hrHolidayPersistence;
	}

	/**
	 * Returns the h r job title persistence.
	 *
	 * @return the h r job title persistence
	 */
	public HRJobTitlePersistence getHRJobTitlePersistence() {
		return hrJobTitlePersistence;
	}

	/**
	 * Sets the h r job title persistence.
	 *
	 * @param hrJobTitlePersistence the h r job title persistence
	 */
	public void setHRJobTitlePersistence(
		HRJobTitlePersistence hrJobTitlePersistence) {
		this.hrJobTitlePersistence = hrJobTitlePersistence;
	}

	/**
	 * Returns the h r office persistence.
	 *
	 * @return the h r office persistence
	 */
	public HROfficePersistence getHROfficePersistence() {
		return hrOfficePersistence;
	}

	/**
	 * Sets the h r office persistence.
	 *
	 * @param hrOfficePersistence the h r office persistence
	 */
	public void setHROfficePersistence(HROfficePersistence hrOfficePersistence) {
		this.hrOfficePersistence = hrOfficePersistence;
	}

	/**
	 * Returns the h r project persistence.
	 *
	 * @return the h r project persistence
	 */
	public HRProjectPersistence getHRProjectPersistence() {
		return hrProjectPersistence;
	}

	/**
	 * Sets the h r project persistence.
	 *
	 * @param hrProjectPersistence the h r project persistence
	 */
	public void setHRProjectPersistence(
		HRProjectPersistence hrProjectPersistence) {
		this.hrProjectPersistence = hrProjectPersistence;
	}

	/**
	 * Returns the h r project billing rate persistence.
	 *
	 * @return the h r project billing rate persistence
	 */
	public HRProjectBillingRatePersistence getHRProjectBillingRatePersistence() {
		return hrProjectBillingRatePersistence;
	}

	/**
	 * Sets the h r project billing rate persistence.
	 *
	 * @param hrProjectBillingRatePersistence the h r project billing rate persistence
	 */
	public void setHRProjectBillingRatePersistence(
		HRProjectBillingRatePersistence hrProjectBillingRatePersistence) {
		this.hrProjectBillingRatePersistence = hrProjectBillingRatePersistence;
	}

	/**
	 * Returns the h r project role persistence.
	 *
	 * @return the h r project role persistence
	 */
	public HRProjectRolePersistence getHRProjectRolePersistence() {
		return hrProjectRolePersistence;
	}

	/**
	 * Sets the h r project role persistence.
	 *
	 * @param hrProjectRolePersistence the h r project role persistence
	 */
	public void setHRProjectRolePersistence(
		HRProjectRolePersistence hrProjectRolePersistence) {
		this.hrProjectRolePersistence = hrProjectRolePersistence;
	}

	/**
	 * Returns the h r project status persistence.
	 *
	 * @return the h r project status persistence
	 */
	public HRProjectStatusPersistence getHRProjectStatusPersistence() {
		return hrProjectStatusPersistence;
	}

	/**
	 * Sets the h r project status persistence.
	 *
	 * @param hrProjectStatusPersistence the h r project status persistence
	 */
	public void setHRProjectStatusPersistence(
		HRProjectStatusPersistence hrProjectStatusPersistence) {
		this.hrProjectStatusPersistence = hrProjectStatusPersistence;
	}

	/**
	 * Returns the h r task persistence.
	 *
	 * @return the h r task persistence
	 */
	public HRTaskPersistence getHRTaskPersistence() {
		return hrTaskPersistence;
	}

	/**
	 * Sets the h r task persistence.
	 *
	 * @param hrTaskPersistence the h r task persistence
	 */
	public void setHRTaskPersistence(HRTaskPersistence hrTaskPersistence) {
		this.hrTaskPersistence = hrTaskPersistence;
	}

	/**
	 * Returns the h r task status persistence.
	 *
	 * @return the h r task status persistence
	 */
	public HRTaskStatusPersistence getHRTaskStatusPersistence() {
		return hrTaskStatusPersistence;
	}

	/**
	 * Sets the h r task status persistence.
	 *
	 * @param hrTaskStatusPersistence the h r task status persistence
	 */
	public void setHRTaskStatusPersistence(
		HRTaskStatusPersistence hrTaskStatusPersistence) {
		this.hrTaskStatusPersistence = hrTaskStatusPersistence;
	}

	/**
	 * Returns the h r termination type persistence.
	 *
	 * @return the h r termination type persistence
	 */
	public HRTerminationTypePersistence getHRTerminationTypePersistence() {
		return hrTerminationTypePersistence;
	}

	/**
	 * Sets the h r termination type persistence.
	 *
	 * @param hrTerminationTypePersistence the h r termination type persistence
	 */
	public void setHRTerminationTypePersistence(
		HRTerminationTypePersistence hrTerminationTypePersistence) {
		this.hrTerminationTypePersistence = hrTerminationTypePersistence;
	}

	/**
	 * Returns the h r time off persistence.
	 *
	 * @return the h r time off persistence
	 */
	public HRTimeOffPersistence getHRTimeOffPersistence() {
		return hrTimeOffPersistence;
	}

	/**
	 * Sets the h r time off persistence.
	 *
	 * @param hrTimeOffPersistence the h r time off persistence
	 */
	public void setHRTimeOffPersistence(
		HRTimeOffPersistence hrTimeOffPersistence) {
		this.hrTimeOffPersistence = hrTimeOffPersistence;
	}

	/**
	 * Returns the h r time off frequency type persistence.
	 *
	 * @return the h r time off frequency type persistence
	 */
	public HRTimeOffFrequencyTypePersistence getHRTimeOffFrequencyTypePersistence() {
		return hrTimeOffFrequencyTypePersistence;
	}

	/**
	 * Sets the h r time off frequency type persistence.
	 *
	 * @param hrTimeOffFrequencyTypePersistence the h r time off frequency type persistence
	 */
	public void setHRTimeOffFrequencyTypePersistence(
		HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence) {
		this.hrTimeOffFrequencyTypePersistence = hrTimeOffFrequencyTypePersistence;
	}

	/**
	 * Returns the h r time off policy persistence.
	 *
	 * @return the h r time off policy persistence
	 */
	public HRTimeOffPolicyPersistence getHRTimeOffPolicyPersistence() {
		return hrTimeOffPolicyPersistence;
	}

	/**
	 * Sets the h r time off policy persistence.
	 *
	 * @param hrTimeOffPolicyPersistence the h r time off policy persistence
	 */
	public void setHRTimeOffPolicyPersistence(
		HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence) {
		this.hrTimeOffPolicyPersistence = hrTimeOffPolicyPersistence;
	}

	/**
	 * Returns the h r time off type persistence.
	 *
	 * @return the h r time off type persistence
	 */
	public HRTimeOffTypePersistence getHRTimeOffTypePersistence() {
		return hrTimeOffTypePersistence;
	}

	/**
	 * Sets the h r time off type persistence.
	 *
	 * @param hrTimeOffTypePersistence the h r time off type persistence
	 */
	public void setHRTimeOffTypePersistence(
		HRTimeOffTypePersistence hrTimeOffTypePersistence) {
		this.hrTimeOffTypePersistence = hrTimeOffTypePersistence;
	}

	/**
	 * Returns the h r time sheet persistence.
	 *
	 * @return the h r time sheet persistence
	 */
	public HRTimeSheetPersistence getHRTimeSheetPersistence() {
		return hrTimeSheetPersistence;
	}

	/**
	 * Sets the h r time sheet persistence.
	 *
	 * @param hrTimeSheetPersistence the h r time sheet persistence
	 */
	public void setHRTimeSheetPersistence(
		HRTimeSheetPersistence hrTimeSheetPersistence) {
		this.hrTimeSheetPersistence = hrTimeSheetPersistence;
	}

	/**
	 * Returns the h r time sheet day persistence.
	 *
	 * @return the h r time sheet day persistence
	 */
	public HRTimeSheetDayPersistence getHRTimeSheetDayPersistence() {
		return hrTimeSheetDayPersistence;
	}

	/**
	 * Sets the h r time sheet day persistence.
	 *
	 * @param hrTimeSheetDayPersistence the h r time sheet day persistence
	 */
	public void setHRTimeSheetDayPersistence(
		HRTimeSheetDayPersistence hrTimeSheetDayPersistence) {
		this.hrTimeSheetDayPersistence = hrTimeSheetDayPersistence;
	}

	/**
	 * Returns the h r time sheet hours per day persistence.
	 *
	 * @return the h r time sheet hours per day persistence
	 */
	public HRTimeSheetHoursPerDayPersistence getHRTimeSheetHoursPerDayPersistence() {
		return hrTimeSheetHoursPerDayPersistence;
	}

	/**
	 * Sets the h r time sheet hours per day persistence.
	 *
	 * @param hrTimeSheetHoursPerDayPersistence the h r time sheet hours per day persistence
	 */
	public void setHRTimeSheetHoursPerDayPersistence(
		HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence) {
		this.hrTimeSheetHoursPerDayPersistence = hrTimeSheetHoursPerDayPersistence;
	}

	/**
	 * Returns the h r user persistence.
	 *
	 * @return the h r user persistence
	 */
	public HRUserPersistence getHRUserPersistence() {
		return hrUserPersistence;
	}

	/**
	 * Sets the h r user persistence.
	 *
	 * @param hrUserPersistence the h r user persistence
	 */
	public void setHRUserPersistence(HRUserPersistence hrUserPersistence) {
		this.hrUserPersistence = hrUserPersistence;
	}

	/**
	 * Returns the h r user history persistence.
	 *
	 * @return the h r user history persistence
	 */
	public HRUserHistoryPersistence getHRUserHistoryPersistence() {
		return hrUserHistoryPersistence;
	}

	/**
	 * Sets the h r user history persistence.
	 *
	 * @param hrUserHistoryPersistence the h r user history persistence
	 */
	public void setHRUserHistoryPersistence(
		HRUserHistoryPersistence hrUserHistoryPersistence) {
		this.hrUserHistoryPersistence = hrUserHistoryPersistence;
	}

	/**
	 * Returns the h r user project persistence.
	 *
	 * @return the h r user project persistence
	 */
	public HRUserProjectPersistence getHRUserProjectPersistence() {
		return hrUserProjectPersistence;
	}

	/**
	 * Sets the h r user project persistence.
	 *
	 * @param hrUserProjectPersistence the h r user project persistence
	 */
	public void setHRUserProjectPersistence(
		HRUserProjectPersistence hrUserProjectPersistence) {
		this.hrUserProjectPersistence = hrUserProjectPersistence;
	}

	/**
	 * Returns the h r user task persistence.
	 *
	 * @return the h r user task persistence
	 */
	public HRUserTaskPersistence getHRUserTaskPersistence() {
		return hrUserTaskPersistence;
	}

	/**
	 * Sets the h r user task persistence.
	 *
	 * @param hrUserTaskPersistence the h r user task persistence
	 */
	public void setHRUserTaskPersistence(
		HRUserTaskPersistence hrUserTaskPersistence) {
		this.hrUserTaskPersistence = hrUserTaskPersistence;
	}

	/**
	 * Returns the h r user time off persistence.
	 *
	 * @return the h r user time off persistence
	 */
	public HRUserTimeOffPersistence getHRUserTimeOffPersistence() {
		return hrUserTimeOffPersistence;
	}

	/**
	 * Sets the h r user time off persistence.
	 *
	 * @param hrUserTimeOffPersistence the h r user time off persistence
	 */
	public void setHRUserTimeOffPersistence(
		HRUserTimeOffPersistence hrUserTimeOffPersistence) {
		this.hrUserTimeOffPersistence = hrUserTimeOffPersistence;
	}

	/**
	 * Returns the h r wage type persistence.
	 *
	 * @return the h r wage type persistence
	 */
	public HRWageTypePersistence getHRWageTypePersistence() {
		return hrWageTypePersistence;
	}

	/**
	 * Sets the h r wage type persistence.
	 *
	 * @param hrWageTypePersistence the h r wage type persistence
	 */
	public void setHRWageTypePersistence(
		HRWageTypePersistence hrWageTypePersistence) {
		this.hrWageTypePersistence = hrWageTypePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		PersistedModelLocalServiceRegistryUtil.register("com.liferay.hr.model.HRExpenseType",
			hrExpenseTypeLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.hr.model.HRExpenseType");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return HRExpenseType.class;
	}

	protected String getModelClassName() {
		return HRExpenseType.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = hrExpenseTypePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = HRAssetPersistence.class)
	protected HRAssetPersistence hrAssetPersistence;
	@BeanReference(type = HRAssetCheckoutPersistence.class)
	protected HRAssetCheckoutPersistence hrAssetCheckoutPersistence;
	@BeanReference(type = HRAssetDefinitionPersistence.class)
	protected HRAssetDefinitionPersistence hrAssetDefinitionPersistence;
	@BeanReference(type = HRAssetProductPersistence.class)
	protected HRAssetProductPersistence hrAssetProductPersistence;
	@BeanReference(type = HRAssetTypePersistence.class)
	protected HRAssetTypePersistence hrAssetTypePersistence;
	@BeanReference(type = HRAssetVendorPersistence.class)
	protected HRAssetVendorPersistence hrAssetVendorPersistence;
	@BeanReference(type = HRBillabilityPersistence.class)
	protected HRBillabilityPersistence hrBillabilityPersistence;
	@BeanReference(type = HRBranchPersistence.class)
	protected HRBranchPersistence hrBranchPersistence;
	@BeanReference(type = HRClientPersistence.class)
	protected HRClientPersistence hrClientPersistence;
	@BeanReference(type = HREmploymentTypePersistence.class)
	protected HREmploymentTypePersistence hrEmploymentTypePersistence;
	@BeanReference(type = HRExpenseLocalService.class)
	protected HRExpenseLocalService hrExpenseLocalService;
	@BeanReference(type = HRExpensePersistence.class)
	protected HRExpensePersistence hrExpensePersistence;
	@BeanReference(type = HRExpenseAccountLocalService.class)
	protected HRExpenseAccountLocalService hrExpenseAccountLocalService;
	@BeanReference(type = HRExpenseAccountPersistence.class)
	protected HRExpenseAccountPersistence hrExpenseAccountPersistence;
	@BeanReference(type = HRExpenseCurrencyLocalService.class)
	protected HRExpenseCurrencyLocalService hrExpenseCurrencyLocalService;
	@BeanReference(type = HRExpenseCurrencyPersistence.class)
	protected HRExpenseCurrencyPersistence hrExpenseCurrencyPersistence;
	@BeanReference(type = HRExpenseCurrencyConversionLocalService.class)
	protected HRExpenseCurrencyConversionLocalService hrExpenseCurrencyConversionLocalService;
	@BeanReference(type = HRExpenseCurrencyConversionPersistence.class)
	protected HRExpenseCurrencyConversionPersistence hrExpenseCurrencyConversionPersistence;
	@BeanReference(type = HRExpenseTypeLocalService.class)
	protected HRExpenseTypeLocalService hrExpenseTypeLocalService;
	@BeanReference(type = HRExpenseTypePersistence.class)
	protected HRExpenseTypePersistence hrExpenseTypePersistence;
	@BeanReference(type = HRHolidayPersistence.class)
	protected HRHolidayPersistence hrHolidayPersistence;
	@BeanReference(type = HRJobTitlePersistence.class)
	protected HRJobTitlePersistence hrJobTitlePersistence;
	@BeanReference(type = HROfficePersistence.class)
	protected HROfficePersistence hrOfficePersistence;
	@BeanReference(type = HRProjectPersistence.class)
	protected HRProjectPersistence hrProjectPersistence;
	@BeanReference(type = HRProjectBillingRatePersistence.class)
	protected HRProjectBillingRatePersistence hrProjectBillingRatePersistence;
	@BeanReference(type = HRProjectRolePersistence.class)
	protected HRProjectRolePersistence hrProjectRolePersistence;
	@BeanReference(type = HRProjectStatusPersistence.class)
	protected HRProjectStatusPersistence hrProjectStatusPersistence;
	@BeanReference(type = HRTaskPersistence.class)
	protected HRTaskPersistence hrTaskPersistence;
	@BeanReference(type = HRTaskStatusPersistence.class)
	protected HRTaskStatusPersistence hrTaskStatusPersistence;
	@BeanReference(type = HRTerminationTypePersistence.class)
	protected HRTerminationTypePersistence hrTerminationTypePersistence;
	@BeanReference(type = HRTimeOffPersistence.class)
	protected HRTimeOffPersistence hrTimeOffPersistence;
	@BeanReference(type = HRTimeOffFrequencyTypePersistence.class)
	protected HRTimeOffFrequencyTypePersistence hrTimeOffFrequencyTypePersistence;
	@BeanReference(type = HRTimeOffPolicyPersistence.class)
	protected HRTimeOffPolicyPersistence hrTimeOffPolicyPersistence;
	@BeanReference(type = HRTimeOffTypePersistence.class)
	protected HRTimeOffTypePersistence hrTimeOffTypePersistence;
	@BeanReference(type = HRTimeSheetPersistence.class)
	protected HRTimeSheetPersistence hrTimeSheetPersistence;
	@BeanReference(type = HRTimeSheetDayPersistence.class)
	protected HRTimeSheetDayPersistence hrTimeSheetDayPersistence;
	@BeanReference(type = HRTimeSheetHoursPerDayPersistence.class)
	protected HRTimeSheetHoursPerDayPersistence hrTimeSheetHoursPerDayPersistence;
	@BeanReference(type = HRUserPersistence.class)
	protected HRUserPersistence hrUserPersistence;
	@BeanReference(type = HRUserHistoryPersistence.class)
	protected HRUserHistoryPersistence hrUserHistoryPersistence;
	@BeanReference(type = HRUserProjectPersistence.class)
	protected HRUserProjectPersistence hrUserProjectPersistence;
	@BeanReference(type = HRUserTaskPersistence.class)
	protected HRUserTaskPersistence hrUserTaskPersistence;
	@BeanReference(type = HRUserTimeOffPersistence.class)
	protected HRUserTimeOffPersistence hrUserTimeOffPersistence;
	@BeanReference(type = HRWageTypePersistence.class)
	protected HRWageTypePersistence hrWageTypePersistence;
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
	private static Log _log = LogFactoryUtil.getLog(HRExpenseTypeLocalServiceBaseImpl.class);
	private String _beanIdentifier;
}