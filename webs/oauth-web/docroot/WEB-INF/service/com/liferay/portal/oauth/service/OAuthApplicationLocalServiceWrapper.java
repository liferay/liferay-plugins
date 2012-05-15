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

package com.liferay.portal.oauth.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OAuthApplicationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OAuthApplicationLocalService
 * @generated
 */
public class OAuthApplicationLocalServiceWrapper
	implements OAuthApplicationLocalService,
		ServiceWrapper<OAuthApplicationLocalService> {
	public OAuthApplicationLocalServiceWrapper(
		OAuthApplicationLocalService oAuthApplicationLocalService) {
		_oAuthApplicationLocalService = oAuthApplicationLocalService;
	}

	/**
	* Adds the o auth application to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthApplication the o auth application
	* @return the o auth application that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication addOAuthApplication(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.addOAuthApplication(oAuthApplication);
	}

	/**
	* Creates a new o auth application with the primary key. Does not add the o auth application to the database.
	*
	* @param applicationId the primary key for the new o auth application
	* @return the new o auth application
	*/
	public com.liferay.portal.oauth.model.OAuthApplication createOAuthApplication(
		long applicationId) {
		return _oAuthApplicationLocalService.createOAuthApplication(applicationId);
	}

	/**
	* Deletes the o auth application with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicationId the primary key of the o auth application
	* @return the o auth application that was removed
	* @throws PortalException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication deleteOAuthApplication(
		long applicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.deleteOAuthApplication(applicationId);
	}

	/**
	* Deletes the o auth application from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthApplication the o auth application
	* @return the o auth application that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication deleteOAuthApplication(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.deleteOAuthApplication(oAuthApplication);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _oAuthApplicationLocalService.dynamicQuery();
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
		return _oAuthApplicationLocalService.dynamicQuery(dynamicQuery);
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
		return _oAuthApplicationLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _oAuthApplicationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _oAuthApplicationLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.oauth.model.OAuthApplication fetchOAuthApplication(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.fetchOAuthApplication(applicationId);
	}

	/**
	* Returns the o auth application with the primary key.
	*
	* @param applicationId the primary key of the o auth application
	* @return the o auth application
	* @throws PortalException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication getOAuthApplication(
		long applicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.getOAuthApplication(applicationId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the o auth applications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> getOAuthApplications(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.getOAuthApplications(start, end);
	}

	/**
	* Returns the number of o auth applications.
	*
	* @return the number of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int getOAuthApplicationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.getOAuthApplicationsCount();
	}

	/**
	* Updates the o auth application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthApplication the o auth application
	* @return the o auth application that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication updateOAuthApplication(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.updateOAuthApplication(oAuthApplication);
	}

	/**
	* Updates the o auth application in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthApplication the o auth application
	* @param merge whether to merge the o auth application with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth application that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.oauth.model.OAuthApplication updateOAuthApplication(
		com.liferay.portal.oauth.model.OAuthApplication oAuthApplication,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.updateOAuthApplication(oAuthApplication,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _oAuthApplicationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_oAuthApplicationLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _oAuthApplicationLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Add info about new application that should use OAuth feature. Method will generate new
	* consumer key and secret that will be used by this application to do authorized access
	* to portal resources.
	*/
	public com.liferay.portal.oauth.model.OAuthApplication addOAuthApplication(
		int accessLevel, java.lang.String callbackURL,
		java.lang.String description, java.lang.String name, long ownerId,
		java.lang.String website)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.addOAuthApplication(accessLevel,
			callbackURL, description, name, ownerId, website);
	}

	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.countByName(name);
	}

	public int countByNameAndOwner(java.lang.String name, long ownerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.countByNameAndOwner(name, ownerId);
	}

	public com.liferay.portal.oauth.model.OAuthApplication getOAuthApplicationByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.getOAuthApplicationByConsumerKey(consumerKey);
	}

	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.findByName(name, start, end,
			orderByComparator);
	}

	public java.util.List<com.liferay.portal.oauth.model.OAuthApplication> findByNameAndOwner(
		java.lang.String name, long ownerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationLocalService.findByNameAndOwner(name, ownerId,
			start, end, orderByComparator);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OAuthApplicationLocalService getWrappedOAuthApplicationLocalService() {
		return _oAuthApplicationLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOAuthApplicationLocalService(
		OAuthApplicationLocalService oAuthApplicationLocalService) {
		_oAuthApplicationLocalService = oAuthApplicationLocalService;
	}

	public OAuthApplicationLocalService getWrappedService() {
		return _oAuthApplicationLocalService;
	}

	public void setWrappedService(
		OAuthApplicationLocalService oAuthApplicationLocalService) {
		_oAuthApplicationLocalService = oAuthApplicationLocalService;
	}

	private OAuthApplicationLocalService _oAuthApplicationLocalService;
}