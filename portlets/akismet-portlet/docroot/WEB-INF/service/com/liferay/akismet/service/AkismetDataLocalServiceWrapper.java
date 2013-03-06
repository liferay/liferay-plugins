/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.akismet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AkismetDataLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AkismetDataLocalService
 * @generated
 */
public class AkismetDataLocalServiceWrapper implements AkismetDataLocalService,
	ServiceWrapper<AkismetDataLocalService> {
	public AkismetDataLocalServiceWrapper(
		AkismetDataLocalService akismetDataLocalService) {
		_akismetDataLocalService = akismetDataLocalService;
	}

	/**
	* Adds the akismet data to the database. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.akismet.model.AkismetData addAkismetData(
		com.liferay.akismet.model.AkismetData akismetData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.addAkismetData(akismetData);
	}

	/**
	* Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	*
	* @param akismetDataId the primary key for the new akismet data
	* @return the new akismet data
	*/
	public com.liferay.akismet.model.AkismetData createAkismetData(
		long akismetDataId) {
		return _akismetDataLocalService.createAkismetData(akismetDataId);
	}

	/**
	* Deletes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data that was removed
	* @throws PortalException if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.akismet.model.AkismetData deleteAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.deleteAkismetData(akismetDataId);
	}

	/**
	* Deletes the akismet data from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.akismet.model.AkismetData deleteAkismetData(
		com.liferay.akismet.model.AkismetData akismetData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.deleteAkismetData(akismetData);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _akismetDataLocalService.dynamicQuery();
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
		return _akismetDataLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _akismetDataLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _akismetDataLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _akismetDataLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.akismet.model.AkismetData fetchAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.fetchAkismetData(akismetDataId);
	}

	/**
	* Returns the akismet data with the primary key.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data
	* @throws PortalException if a akismet data with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.akismet.model.AkismetData getAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.getAkismetData(akismetDataId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.akismet.model.AkismetData> getAkismetDatas(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.getAkismetDatas(start, end);
	}

	/**
	* Returns the number of akismet datas.
	*
	* @return the number of akismet datas
	* @throws SystemException if a system exception occurred
	*/
	public int getAkismetDatasCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.getAkismetDatasCount();
	}

	/**
	* Updates the akismet data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.akismet.model.AkismetData updateAkismetData(
		com.liferay.akismet.model.AkismetData akismetData)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.updateAkismetData(akismetData);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _akismetDataLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_akismetDataLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _akismetDataLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void deleteAkismetData(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		_akismetDataLocalService.deleteAkismetData(modifiedDate);
	}

	public void deleteAkismetData(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_akismetDataLocalService.deleteAkismetData(className, classPK);
	}

	public com.liferay.akismet.model.AkismetData fetchAkismetData(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.fetchAkismetData(className, classPK);
	}

	public com.liferay.akismet.model.AkismetData updateAkismetData(
		java.lang.String className, long classPK, java.lang.String type,
		java.lang.String permalink, java.lang.String referrer,
		java.lang.String userAgent, java.lang.String userIP,
		java.lang.String userURL)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _akismetDataLocalService.updateAkismetData(className, classPK,
			type, permalink, referrer, userAgent, userIP, userURL);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AkismetDataLocalService getWrappedAkismetDataLocalService() {
		return _akismetDataLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAkismetDataLocalService(
		AkismetDataLocalService akismetDataLocalService) {
		_akismetDataLocalService = akismetDataLocalService;
	}

	public AkismetDataLocalService getWrappedService() {
		return _akismetDataLocalService;
	}

	public void setWrappedService(
		AkismetDataLocalService akismetDataLocalService) {
		_akismetDataLocalService = akismetDataLocalService;
	}

	private AkismetDataLocalService _akismetDataLocalService;
}