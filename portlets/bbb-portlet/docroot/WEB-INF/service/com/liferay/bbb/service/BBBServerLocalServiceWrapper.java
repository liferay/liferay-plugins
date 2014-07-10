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

package com.liferay.bbb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BBBServerLocalService}.
 *
 * @author Shinn Lok
 * @see BBBServerLocalService
 * @generated
 */
public class BBBServerLocalServiceWrapper implements BBBServerLocalService,
	ServiceWrapper<BBBServerLocalService> {
	public BBBServerLocalServiceWrapper(
		BBBServerLocalService bbbServerLocalService) {
		_bbbServerLocalService = bbbServerLocalService;
	}

	/**
	* Adds the b b b server to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was added
	*/
	@Override
	public com.liferay.bbb.model.BBBServer addBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return _bbbServerLocalService.addBBBServer(bbbServer);
	}

	@Override
	public com.liferay.bbb.model.BBBServer addBBBServer(long userId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.addBBBServer(userId, name, url, secret,
			serviceContext);
	}

	@Override
	public void checkBBBServers()
		throws com.liferay.portal.kernel.exception.PortalException {
		_bbbServerLocalService.checkBBBServers();
	}

	/**
	* Creates a new b b b server with the primary key. Does not add the b b b server to the database.
	*
	* @param bbbServerId the primary key for the new b b b server
	* @return the new b b b server
	*/
	@Override
	public com.liferay.bbb.model.BBBServer createBBBServer(long bbbServerId) {
		return _bbbServerLocalService.createBBBServer(bbbServerId);
	}

	/**
	* Deletes the b b b server from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was removed
	*/
	@Override
	public com.liferay.bbb.model.BBBServer deleteBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return _bbbServerLocalService.deleteBBBServer(bbbServer);
	}

	/**
	* Deletes the b b b server with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server that was removed
	* @throws PortalException if a b b b server with the primary key could not be found
	*/
	@Override
	public com.liferay.bbb.model.BBBServer deleteBBBServer(long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.deleteBBBServer(bbbServerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bbbServerLocalService.dynamicQuery();
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
		return _bbbServerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bbbServerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _bbbServerLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _bbbServerLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _bbbServerLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.bbb.model.BBBServer fetchBBBServer(long bbbServerId) {
		return _bbbServerLocalService.fetchBBBServer(bbbServerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bbbServerLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the b b b server with the primary key.
	*
	* @param bbbServerId the primary key of the b b b server
	* @return the b b b server
	* @throws PortalException if a b b b server with the primary key could not be found
	*/
	@Override
	public com.liferay.bbb.model.BBBServer getBBBServer(long bbbServerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.getBBBServer(bbbServerId);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		boolean active) {
		return _bbbServerLocalService.getBBBServers(active);
	}

	/**
	* Returns a range of all the b b b servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b servers
	* @param end the upper bound of the range of b b b servers (not inclusive)
	* @return the range of b b b servers
	*/
	@Override
	public java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		int start, int end) {
		return _bbbServerLocalService.getBBBServers(start, end);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBServer> getBBBServers(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bbb.model.BBBServer> obc) {
		return _bbbServerLocalService.getBBBServers(start, end, obc);
	}

	/**
	* Returns the number of b b b servers.
	*
	* @return the number of b b b servers
	*/
	@Override
	public int getBBBServersCount() {
		return _bbbServerLocalService.getBBBServersCount();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbServerLocalService.getBeanIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbServerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbServerLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the b b b server in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbServer the b b b server
	* @return the b b b server that was updated
	*/
	@Override
	public com.liferay.bbb.model.BBBServer updateBBBServer(
		com.liferay.bbb.model.BBBServer bbbServer) {
		return _bbbServerLocalService.updateBBBServer(bbbServer);
	}

	@Override
	public com.liferay.bbb.model.BBBServer updateBBBServer(long bbbServerId,
		java.lang.String name, java.lang.String url, java.lang.String secret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbServerLocalService.updateBBBServer(bbbServerId, name, url,
			secret, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BBBServerLocalService getWrappedBBBServerLocalService() {
		return _bbbServerLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBBBServerLocalService(
		BBBServerLocalService bbbServerLocalService) {
		_bbbServerLocalService = bbbServerLocalService;
	}

	@Override
	public BBBServerLocalService getWrappedService() {
		return _bbbServerLocalService;
	}

	@Override
	public void setWrappedService(BBBServerLocalService bbbServerLocalService) {
		_bbbServerLocalService = bbbServerLocalService;
	}

	private BBBServerLocalService _bbbServerLocalService;
}