/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AkismetDataLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.akismet.model.AkismetData addAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return _akismetDataLocalService.addAkismetData(akismetData);
	}

	/**
	* Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	*
	* @param akismetDataId the primary key for the new akismet data
	* @return the new akismet data
	*/
	@Override
	public com.liferay.akismet.model.AkismetData createAkismetData(
		long akismetDataId) {
		return _akismetDataLocalService.createAkismetData(akismetDataId);
	}

	/**
	* Deletes the akismet data from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was removed
	*/
	@Override
	public com.liferay.akismet.model.AkismetData deleteAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return _akismetDataLocalService.deleteAkismetData(akismetData);
	}

	/**
	* Deletes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data that was removed
	* @throws PortalException if a akismet data with the primary key could not be found
	*/
	@Override
	public com.liferay.akismet.model.AkismetData deleteAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetDataLocalService.deleteAkismetData(akismetDataId);
	}

	@Override
	public void deleteAkismetData(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_akismetDataLocalService.deleteAkismetData(className, classPK);
	}

	@Override
	public void deleteAkismetData(java.util.Date modifiedDate) {
		_akismetDataLocalService.deleteAkismetData(modifiedDate);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetDataLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _akismetDataLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _akismetDataLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _akismetDataLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _akismetDataLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.akismet.model.AkismetData fetchAkismetData(
		long akismetDataId) {
		return _akismetDataLocalService.fetchAkismetData(akismetDataId);
	}

	@Override
	public com.liferay.akismet.model.AkismetData fetchAkismetData(
		java.lang.String className, long classPK) {
		return _akismetDataLocalService.fetchAkismetData(className, classPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _akismetDataLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the akismet data with the primary key.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data
	* @throws PortalException if a akismet data with the primary key could not be found
	*/
	@Override
	public com.liferay.akismet.model.AkismetData getAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetDataLocalService.getAkismetData(akismetDataId);
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
	*/
	@Override
	public java.util.List<com.liferay.akismet.model.AkismetData> getAkismetDatas(
		int start, int end) {
		return _akismetDataLocalService.getAkismetDatas(start, end);
	}

	/**
	* Returns the number of akismet datas.
	*
	* @return the number of akismet datas
	*/
	@Override
	public int getAkismetDatasCount() {
		return _akismetDataLocalService.getAkismetDatasCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _akismetDataLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _akismetDataLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _akismetDataLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _akismetDataLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the akismet data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was updated
	*/
	@Override
	public com.liferay.akismet.model.AkismetData updateAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return _akismetDataLocalService.updateAkismetData(akismetData);
	}

	@Override
	public com.liferay.akismet.model.AkismetData updateAkismetData(
		java.lang.String className, long classPK, java.lang.String type,
		java.lang.String permalink, java.lang.String referrer,
		java.lang.String userAgent, java.lang.String userIP,
		java.lang.String userURL) {
		return _akismetDataLocalService.updateAkismetData(className, classPK,
			type, permalink, referrer, userAgent, userIP, userURL);
	}

	@Override
	public AkismetDataLocalService getWrappedService() {
		return _akismetDataLocalService;
	}

	@Override
	public void setWrappedService(
		AkismetDataLocalService akismetDataLocalService) {
		_akismetDataLocalService = akismetDataLocalService;
	}

	private AkismetDataLocalService _akismetDataLocalService;
}