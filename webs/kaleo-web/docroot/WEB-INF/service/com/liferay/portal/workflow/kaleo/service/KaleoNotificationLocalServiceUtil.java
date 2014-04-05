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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KaleoNotification. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationLocalService
 * @see com.liferay.portal.workflow.kaleo.service.base.KaleoNotificationLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationLocalServiceImpl
 * @generated
 */
public class KaleoNotificationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.service.impl.KaleoNotificationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo notification to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoNotification(kaleoNotification);
	}

	/**
	* Creates a new kaleo notification with the primary key. Does not add the kaleo notification to the database.
	*
	* @param kaleoNotificationId the primary key for the new kaleo notification
	* @return the new kaleo notification
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification createKaleoNotification(
		long kaleoNotificationId) {
		return getService().createKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification that was removed
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification deleteKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoNotification(kaleoNotificationId);
	}

	/**
	* Deletes the kaleo notification from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification deleteKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoNotification(kaleoNotification);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification fetchKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoNotification(kaleoNotificationId);
	}

	/**
	* Returns the kaleo notification with the primary key.
	*
	* @param kaleoNotificationId the primary key of the kaleo notification
	* @return the kaleo notification
	* @throws PortalException if a kaleo notification with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification getKaleoNotification(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotification(kaleoNotificationId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotifications(start, end);
	}

	/**
	* Returns the number of kaleo notifications.
	*
	* @return the number of kaleo notifications
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoNotificationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotificationsCount();
	}

	/**
	* Updates the kaleo notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoNotification the kaleo notification
	* @return the kaleo notification that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification updateKaleoNotification(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoNotification(kaleoNotification);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoNotification addKaleoNotification(
		java.lang.String kaleoClassName, long kaleoClassPK,
		long kaleoDefinitionId, java.lang.String kaleoNodeName,
		com.liferay.portal.workflow.kaleo.definition.Notification notification,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoNotification(kaleoClassName, kaleoClassPK,
			kaleoDefinitionId, kaleoNodeName, notification, serviceContext);
	}

	public static void deleteCompanyKaleoNotifications(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCompanyKaleoNotifications(companyId);
	}

	public static void deleteKaleoDefinitionKaleoNotifications(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoDefinitionKaleoNotifications(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		java.lang.String kaleoClassName, long kaleoClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoNotifications(kaleoClassName, kaleoClassPK);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotification> getKaleoNotifications(
		java.lang.String kaleoClassName, long kaleoClassPK,
		java.lang.String executionType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getKaleoNotifications(kaleoClassName, kaleoClassPK,
			executionType);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoNotificationLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoNotificationLocalService.class.getName());

			if (invokableLocalService instanceof KaleoNotificationLocalService) {
				_service = (KaleoNotificationLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoNotificationLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoNotificationLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoNotificationLocalService service) {
	}

	private static KaleoNotificationLocalService _service;
}