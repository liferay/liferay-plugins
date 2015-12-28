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

package com.liferay.wsrp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WSRPConsumerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerLocalService
 * @generated
 */
@ProviderType
public class WSRPConsumerLocalServiceWrapper implements WSRPConsumerLocalService,
	ServiceWrapper<WSRPConsumerLocalService> {
	public WSRPConsumerLocalServiceWrapper(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(long companyId,
		java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url, java.lang.String forwardCookies,
		java.lang.String forwardHeaders, java.lang.String markupCharacterSets,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.addWSRPConsumer(companyId,
			adminPortletId, name, url, forwardCookies, forwardHeaders,
			markupCharacterSets, serviceContext);
	}

	/**
	* Adds the w s r p consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was added
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return _wsrpConsumerLocalService.addWSRPConsumer(wsrpConsumer);
	}

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return _wsrpConsumerLocalService.createWSRPConsumer(wsrpConsumerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the w s r p consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumer);
	}

	/**
	* Deletes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.deleteWSRPConsumer(wsrpConsumerId);
	}

	@Override
	public void deleteWSRPConsumers(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerLocalService.deleteWSRPConsumers(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wsrpConsumerLocalService.dynamicQuery();
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpConsumerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _wsrpConsumerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _wsrpConsumerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer fetchWSRPConsumer(
		long wsrpConsumerId) {
		return _wsrpConsumerLocalService.fetchWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Returns the w s r p consumer with the matching UUID and company.
	*
	* @param uuid the w s r p consumer's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer fetchWSRPConsumerByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _wsrpConsumerLocalService.fetchWSRPConsumerByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _wsrpConsumerLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return _wsrpConsumerLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _wsrpConsumerLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _wsrpConsumerLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the w s r p consumer with the primary key.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerId);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		java.lang.String wsrpConsumerUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.getWSRPConsumer(wsrpConsumerUuid);
	}

	/**
	* Returns the w s r p consumer with the matching UUID and company.
	*
	* @param uuid the w s r p consumer's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer
	* @throws PortalException if a matching w s r p consumer could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer getWSRPConsumerByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.getWSRPConsumerByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end) {
		return _wsrpConsumerLocalService.getWSRPConsumers(companyId, start, end);
	}

	/**
	* Returns a range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of w s r p consumers
	*/
	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end) {
		return _wsrpConsumerLocalService.getWSRPConsumers(start, end);
	}

	/**
	* Returns the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	*/
	@Override
	public int getWSRPConsumersCount() {
		return _wsrpConsumerLocalService.getWSRPConsumersCount();
	}

	@Override
	public int getWSRPConsumersCount(long companyId) {
		return _wsrpConsumerLocalService.getWSRPConsumersCount(companyId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wsrpConsumerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.registerWSRPConsumer(wsrpConsumerId,
			adminPortletId, registrationProperties, registrationHandle);
	}

	@Override
	public void restartConsumer(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerLocalService.restartConsumer(wsrpConsumerId);
	}

	@Override
	public void updateServiceDescription(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerLocalService.updateServiceDescription(wsrpConsumerId);
	}

	/**
	* Updates the w s r p consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was updated
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumer);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url,
		java.lang.String forwardCookies, java.lang.String forwardHeaders,
		java.lang.String markupCharacterSets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerLocalService.updateWSRPConsumer(wsrpConsumerId,
			adminPortletId, name, url, forwardCookies, forwardHeaders,
			markupCharacterSets);
	}

	@Override
	public WSRPConsumerLocalService getWrappedService() {
		return _wsrpConsumerLocalService;
	}

	@Override
	public void setWrappedService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		_wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	private WSRPConsumerLocalService _wsrpConsumerLocalService;
}