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
 * Provides a wrapper for {@link WSRPProducerLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducerLocalService
 * @generated
 */
@ProviderType
public class WSRPProducerLocalServiceWrapper implements WSRPProducerLocalService,
	ServiceWrapper<WSRPProducerLocalService> {
	public WSRPProducerLocalServiceWrapper(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		long groupId, java.lang.String name, java.lang.String version,
		java.lang.String portletIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, groupId, name,
			version, portletIds, serviceContext);
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		java.lang.String name, java.lang.String version,
		java.lang.String portletIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, name, version,
			portletIds, serviceContext);
	}

	/**
	* Adds the w s r p producer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @return the w s r p producer that was added
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		return _wsrpProducerLocalService.addWSRPProducer(wsrpProducer);
	}

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		return _wsrpProducerLocalService.createWSRPProducer(wsrpProducerId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the w s r p producer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @return the w s r p producer that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.deleteWSRPProducer(wsrpProducer);
	}

	/**
	* Deletes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer that was removed
	* @throws PortalException if a w s r p producer with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer deleteWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.deleteWSRPProducer(wsrpProducerId);
	}

	@Override
	public void deleteWSRPProducers(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpProducerLocalService.deleteWSRPProducers(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wsrpProducerLocalService.dynamicQuery();
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _wsrpProducerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _wsrpProducerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer fetchWSRPProducer(
		long wsrpProducerId) {
		return _wsrpProducerLocalService.fetchWSRPProducer(wsrpProducerId);
	}

	/**
	* Returns the w s r p producer matching the UUID and group.
	*
	* @param uuid the w s r p producer's UUID
	* @param groupId the primary key of the group
	* @return the matching w s r p producer, or <code>null</code> if a matching w s r p producer could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer fetchWSRPProducerByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _wsrpProducerLocalService.fetchWSRPProducerByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _wsrpProducerLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wsrpProducerLocalService.getBeanIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _wsrpProducerLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the w s r p producer with the primary key.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws PortalException if a w s r p producer with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerId);
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		java.lang.String wsrpProducerUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerUuid);
	}

	/**
	* Returns the w s r p producer matching the UUID and group.
	*
	* @param uuid the w s r p producer's UUID
	* @param groupId the primary key of the group
	* @return the matching w s r p producer
	* @throws PortalException if a matching w s r p producer could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducerByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.getWSRPProducerByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end) {
		return _wsrpProducerLocalService.getWSRPProducers(companyId, start, end);
	}

	/**
	* Returns a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPProducerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of w s r p producers
	*/
	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end) {
		return _wsrpProducerLocalService.getWSRPProducers(start, end);
	}

	/**
	* Returns all the w s r p producers matching the UUID and company.
	*
	* @param uuid the UUID of the w s r p producers
	* @param companyId the primary key of the company
	* @return the matching w s r p producers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _wsrpProducerLocalService.getWSRPProducersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of w s r p producers matching the UUID and company.
	*
	* @param uuid the UUID of the w s r p producers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching w s r p producers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.wsrp.model.WSRPProducer> orderByComparator) {
		return _wsrpProducerLocalService.getWSRPProducersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	*/
	@Override
	public int getWSRPProducersCount() {
		return _wsrpProducerLocalService.getWSRPProducersCount();
	}

	@Override
	public int getWSRPProducersCount(long companyId) {
		return _wsrpProducerLocalService.getWSRPProducersCount(companyId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wsrpProducerLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wsrpProducerLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the w s r p producer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @return the w s r p producer that was updated
	*/
	@Override
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer) {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer);
	}

	@Override
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String version,
		java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducerId,
			name, version, portletIds);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public WSRPProducerLocalService getWrappedWSRPProducerLocalService() {
		return _wsrpProducerLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedWSRPProducerLocalService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	@Override
	public WSRPProducerLocalService getWrappedService() {
		return _wsrpProducerLocalService;
	}

	@Override
	public void setWrappedService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	private WSRPProducerLocalService _wsrpProducerLocalService;
}