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

package com.liferay.wsrp.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link WSRPConsumerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerLocalService
 * @generated
 */
public class WSRPConsumerLocalServiceWrapper implements WSRPConsumerLocalService,
	ServiceWrapper<WSRPConsumerLocalService> {
	public WSRPConsumerLocalServiceWrapper(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	/**
	* Adds the w s r p consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(wsrpConsumer);
	}

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	public com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return _wsrpConsumerLocalService.createWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Deletes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Deletes the w s r p consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumer);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wsrpConsumerLocalService.dynamicQuery();
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery);
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _wsrpConsumerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.wsrp.model.WSRPConsumer fetchWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.fetchWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Returns the w s r p consumer with the primary key.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(start, end);
	}

	/**
	* Returns the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	* @throws SystemException if a system exception occurred
	*/
	public int getWSRPConsumersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount();
	}

	/**
	* Updates the w s r p consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _wsrpConsumerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wsrpConsumerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wsrpConsumerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(long companyId,
		java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url, java.lang.String forwardCookies,
		java.lang.String forwardHeaders, java.lang.String markupCharacterSets,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.addWSRPConsumer(companyId,
			adminPortletId, name, url, forwardCookies, forwardHeaders,
			markupCharacterSets, serviceContext);
	}

	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		java.lang.String wsrpConsumerUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerUuid);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumers(companyId, start, end);
	}

	public int getWSRPConsumersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.getWSRPConsumersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.registerWSRPConsumer(wsrpConsumerId,
			adminPortletId, registrationProperties, registrationHandle);
	}

	public void restartConsumer(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.restartConsumer(wsrpConsumerId);
	}

	public void updateServiceDescription(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpConsumerLocalService.updateServiceDescription(wsrpConsumerId);
	}

	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url,
		java.lang.String forwardCookies, java.lang.String forwardHeaders,
		java.lang.String markupCharacterSets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumerId,
			adminPortletId, name, url, forwardCookies, forwardHeaders,
			markupCharacterSets);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WSRPConsumerLocalService getWrappedWSRPConsumerLocalService() {
		return _wsrpConsumerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWSRPConsumerLocalService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	public WSRPConsumerLocalService getWrappedService() {
		return _wsrpConsumerLocalService;
	}

	public void setWrappedService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	private WSRPConsumerLocalService _wsrpConsumerLocalService;
}