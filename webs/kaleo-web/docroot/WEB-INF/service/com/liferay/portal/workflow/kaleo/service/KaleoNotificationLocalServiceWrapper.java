/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoNotificationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationLocalService
 * @generated
 */
public class KaleoNotificationLocalServiceWrapper
	implements KaleoNotificationLocalService,
		ServiceWrapper<KaleoNotificationLocalService> {
	public KaleoNotificationLocalServiceWrapper(
		KaleoNotificationLocalService kaleoNotificationLocalService) {
		_kaleoNotificationLocalService = kaleoNotificationLocalService;
	}

	/**
	* Adds the kaleo notification to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification createKaleoNotification(
		long kaleoNotificationId) {
		return _kaleoNotificationLocalService.createKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification that was removed
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification deleteKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification deleteKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.deleteKaleoNotification(kaleoNotification);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoNotificationLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification fetchKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.fetchKaleoNotification(kaleoNotificationId);
	}

	/**
	* Returns the kaleo notification with the primary key.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification getKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotification(kaleoNotificationId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo notifications
	* @param end the upper bound of the range of kaleo notifications (not inclusive)
	* @return the range of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(start, end);
	}

	/**
	* Returns the number of kaleo notifications.
	*
	* @return the number of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKaleoNotificationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotificationsCount();
	}

	/**
	* Updates the kaleo notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.updateKaleoNotification(kaleoNotification);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoNotificationLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoNotificationLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoNotificationLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId, java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Notification notification,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.addKaleoNotification(kaleoClassName,
			kaleoClassPK, kaleoDefinitionId, kaleoNodeName, notification,
			serviceContext);
	}

	@Override
	public void deleteCompanyKaleoNotifications(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteCompanyKaleoNotifications(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoNotifications(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoNotificationLocalService.deleteKaleoDefinitionKaleoNotifications(kaleoDefinitionId);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(kaleoClassName,
			kaleoClassPK);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotificationLocalService.getKaleoNotifications(kaleoClassName,
			kaleoClassPK, executionType);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public KaleoNotificationLocalService getWrappedKaleoNotificationLocalService() {
		return _kaleoNotificationLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedKaleoNotificationLocalService(
		KaleoNotificationLocalService kaleoNotificationLocalService) {
		_kaleoNotificationLocalService = kaleoNotificationLocalService;
	}

	@Override
	public KaleoNotificationLocalService getWrappedService() {
		return _kaleoNotificationLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoNotificationLocalService kaleoNotificationLocalService) {
		_kaleoNotificationLocalService = kaleoNotificationLocalService;
	}

	private KaleoNotificationLocalService _kaleoNotificationLocalService;
}