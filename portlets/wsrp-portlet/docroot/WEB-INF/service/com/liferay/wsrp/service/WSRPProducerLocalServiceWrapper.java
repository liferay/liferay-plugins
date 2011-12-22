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
 * This class is a wrapper for {@link WSRPProducerLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPProducerLocalService
 * @generated
 */
public class WSRPProducerLocalServiceWrapper implements WSRPProducerLocalService,
	ServiceWrapper<WSRPProducerLocalService> {
	public WSRPProducerLocalServiceWrapper(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	/**
	* Adds the w s r p producer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @return the w s r p producer that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(wsrpProducer);
	}

	/**
	* Creates a new w s r p producer with the primary key. Does not add the w s r p producer to the database.
	*
	* @param wsrpProducerId the primary key for the new w s r p producer
	* @return the new w s r p producer
	*/
	public com.liferay.wsrp.model.WSRPProducer createWSRPProducer(
		long wsrpProducerId) {
		return _wsrpProducerLocalService.createWSRPProducer(wsrpProducerId);
	}

	/**
	* Deletes the w s r p producer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @throws PortalException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWSRPProducer(long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducerId);
	}

	/**
	* Deletes the w s r p producer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wsrpProducerLocalService.deleteWSRPProducer(wsrpProducer);
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery);
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _wsrpProducerLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _wsrpProducerLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.wsrp.model.WSRPProducer fetchWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.fetchWSRPProducer(wsrpProducerId);
	}

	/**
	* Returns the w s r p producer with the primary key.
	*
	* @param wsrpProducerId the primary key of the w s r p producer
	* @return the w s r p producer
	* @throws PortalException if a w s r p producer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		long wsrpProducerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the w s r p producer with the UUID in the group.
	*
	* @param uuid the UUID of w s r p producer
	* @param groupId the group id of the w s r p producer
	* @return the w s r p producer
	* @throws PortalException if a w s r p producer with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer getWSRPProducerByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducerByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the w s r p producers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of w s r p producers
	* @param end the upper bound of the range of w s r p producers (not inclusive)
	* @return the range of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(start, end);
	}

	/**
	* Returns the number of w s r p producers.
	*
	* @return the number of w s r p producers
	* @throws SystemException if a system exception occurred
	*/
	public int getWSRPProducersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount();
	}

	/**
	* Updates the w s r p producer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @return the w s r p producer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer);
	}

	/**
	* Updates the w s r p producer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpProducer the w s r p producer
	* @param merge whether to merge the w s r p producer with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the w s r p producer that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		com.liferay.wsrp.model.WSRPProducer wsrpProducer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducer, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _wsrpProducerLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wsrpProducerLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		java.lang.String name, java.lang.String version,
		java.lang.String portletIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, name, version,
			portletIds, serviceContext);
	}

	public com.liferay.wsrp.model.WSRPProducer addWSRPProducer(long userId,
		long groupId, java.lang.String name, java.lang.String version,
		java.lang.String portletIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.addWSRPProducer(userId, groupId, name,
			version, portletIds, serviceContext);
	}

	public com.liferay.wsrp.model.WSRPProducer getWSRPProducer(
		java.lang.String wsrpProducerUuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducer(wsrpProducerUuid);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPProducer> getWSRPProducers(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducers(companyId, start, end);
	}

	public int getWSRPProducersCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.getWSRPProducersCount(companyId);
	}

	public com.liferay.wsrp.model.WSRPProducer updateWSRPProducer(
		long wsrpProducerId, java.lang.String name, java.lang.String version,
		java.lang.String portletIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wsrpProducerLocalService.updateWSRPProducer(wsrpProducerId,
			name, version, portletIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WSRPProducerLocalService getWrappedWSRPProducerLocalService() {
		return _wsrpProducerLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWSRPProducerLocalService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	public WSRPProducerLocalService getWrappedService() {
		return _wsrpProducerLocalService;
	}

	public void setWrappedService(
		WSRPProducerLocalService wsrpProducerLocalService) {
		_wsrpProducerLocalService = wsrpProducerLocalService;
	}

	private WSRPProducerLocalService _wsrpProducerLocalService;
}