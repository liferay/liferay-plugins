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

package com.liferay.hr.service;

/**
 * <p>
 * This class is a wrapper for {@link HRExpenseCurrencyConversionLocalService}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRExpenseCurrencyConversionLocalService
 * @generated
 */
public class HRExpenseCurrencyConversionLocalServiceWrapper
	implements HRExpenseCurrencyConversionLocalService {
	public HRExpenseCurrencyConversionLocalServiceWrapper(
		HRExpenseCurrencyConversionLocalService hrExpenseCurrencyConversionLocalService) {
		_hrExpenseCurrencyConversionLocalService = hrExpenseCurrencyConversionLocalService;
	}

	/**
	* Adds the h r expense currency conversion to the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	* @return the h r expense currency conversion that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion addHRExpenseCurrencyConversion(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.addHRExpenseCurrencyConversion(hrExpenseCurrencyConversion);
	}

	/**
	* Creates a new h r expense currency conversion with the primary key. Does not add the h r expense currency conversion to the database.
	*
	* @param hrExpenseCurrencyConversionId the primary key for the new h r expense currency conversion
	* @return the new h r expense currency conversion
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion createHRExpenseCurrencyConversion(
		long hrExpenseCurrencyConversionId) {
		return _hrExpenseCurrencyConversionLocalService.createHRExpenseCurrencyConversion(hrExpenseCurrencyConversionId);
	}

	/**
	* Deletes the h r expense currency conversion with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @throws PortalException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteHRExpenseCurrencyConversion(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_hrExpenseCurrencyConversionLocalService.deleteHRExpenseCurrencyConversion(hrExpenseCurrencyConversionId);
	}

	/**
	* Deletes the h r expense currency conversion from the database. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	* @throws SystemException if a system exception occurred
	*/
	public void deleteHRExpenseCurrencyConversion(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws com.liferay.portal.kernel.exception.SystemException {
		_hrExpenseCurrencyConversionLocalService.deleteHRExpenseCurrencyConversion(hrExpenseCurrencyConversion);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.dynamicQuery(dynamicQuery,
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the h r expense currency conversion with the primary key.
	*
	* @param hrExpenseCurrencyConversionId the primary key of the h r expense currency conversion
	* @return the h r expense currency conversion
	* @throws PortalException if a h r expense currency conversion with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion getHRExpenseCurrencyConversion(
		long hrExpenseCurrencyConversionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.getHRExpenseCurrencyConversion(hrExpenseCurrencyConversionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the h r expense currency conversions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of h r expense currency conversions
	* @param end the upper bound of the range of h r expense currency conversions (not inclusive)
	* @return the range of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hr.model.HRExpenseCurrencyConversion> getHRExpenseCurrencyConversions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.getHRExpenseCurrencyConversions(start,
			end);
	}

	/**
	* Returns the number of h r expense currency conversions.
	*
	* @return the number of h r expense currency conversions
	* @throws SystemException if a system exception occurred
	*/
	public int getHRExpenseCurrencyConversionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.getHRExpenseCurrencyConversionsCount();
	}

	/**
	* Updates the h r expense currency conversion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	* @return the h r expense currency conversion that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion updateHRExpenseCurrencyConversion(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.updateHRExpenseCurrencyConversion(hrExpenseCurrencyConversion);
	}

	/**
	* Updates the h r expense currency conversion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param hrExpenseCurrencyConversion the h r expense currency conversion
	* @param merge whether to merge the h r expense currency conversion with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the h r expense currency conversion that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hr.model.HRExpenseCurrencyConversion updateHRExpenseCurrencyConversion(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversionLocalService.updateHRExpenseCurrencyConversion(hrExpenseCurrencyConversion,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _hrExpenseCurrencyConversionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_hrExpenseCurrencyConversionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public HRExpenseCurrencyConversionLocalService getWrappedHRExpenseCurrencyConversionLocalService() {
		return _hrExpenseCurrencyConversionLocalService;
	}

	public void setWrappedHRExpenseCurrencyConversionLocalService(
		HRExpenseCurrencyConversionLocalService hrExpenseCurrencyConversionLocalService) {
		_hrExpenseCurrencyConversionLocalService = hrExpenseCurrencyConversionLocalService;
	}

	private HRExpenseCurrencyConversionLocalService _hrExpenseCurrencyConversionLocalService;
}