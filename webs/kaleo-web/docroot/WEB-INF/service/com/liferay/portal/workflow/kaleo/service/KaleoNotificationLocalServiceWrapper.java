/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.service;

/**
 * <p>
 * This class is a wrapper for {@link KaleoNotificationLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotificationLocalService
 * @generated
 */
public class KaleoNotificationLocalServiceWrapper
	implements KaleoNotificationLocalService {
	public KaleoNotificationLocalServiceWrapper(
		KaleoNotificationLocalService kaleoNotificationLocalService) {
		_kaleoNotificationLocalService = kaleoNotificationLocalService;
	}

	/**
	* Adds the kaleo notification to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification to add
	* @return the kaleo notification that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.addKaleoNotification(kaleoNotification);
	}

	/**
	* Creates a new kaleo notification with the primary key. Does not add the kaleo notification to the database.
	*
	* @param kaleoNotificationId the primary key for the new kaleo notification
	* @return the new kaleo notification
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification createKaleoNotification(
		long kaleoNotificationId) {
		return _kaleoNotificationLocalService.createKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification to delete
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoNotification(long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotification);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the kaleo notification with the primary key.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification to get
	* @return the kaleo notification
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification getKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotification(kaleoNotificationId);
	}

	/**
	* Gets a range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications to return
	* @param end the upper bound of the range of kaleo notifications to return (not inclusive)
	* @return the range of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(start, end);
	}

	/**
	* Gets the number of kaleo notifications.
	*
	* @return the number of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoNotificationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotificationsCount();
	}

	/**
	* Updates the kaleo notification in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification to update
	* @return the kaleo notification that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.updateKaleoNotification(kaleoNotification);
	}

	/**
	* Updates the kaleo notification in the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification to update
	* @param merge whether to merge the kaleo notification with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the kaleo notification that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.updateKaleoNotification(kaleoNotification,
			merge);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		long kaleoDefinitionId, long kaleoNodeId,
		java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Notification notification,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.addKaleoNotification(kaleoDefinitionId,
			kaleoNodeId, kaleoNodeName, notification, serviceContext);
	}

	public void deleteCompanyKaleoNotifications(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteCompanyKaleoNotifications(companyId);
	}

	public void deleteKaleoDefinitionKaleoNotifications(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoDefinitionKaleoNotifications(kaleoDefinitionId);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		long kaleoNodeId, java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(kaleoNodeId,
			executionType);
	}

	public KaleoNotificationLocalService getWrappedKaleoNotificationLocalService() {
		return _kaleoNotificationLocalService;
	}

	private KaleoNotificationLocalService _kaleoNotificationLocalService;
}