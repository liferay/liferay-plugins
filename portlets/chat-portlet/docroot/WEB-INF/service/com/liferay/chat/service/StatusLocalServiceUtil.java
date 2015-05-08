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

package com.liferay.chat.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Status. This utility wraps
 * {@link com.liferay.chat.service.impl.StatusLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see StatusLocalService
 * @see com.liferay.chat.service.base.StatusLocalServiceBaseImpl
 * @see com.liferay.chat.service.impl.StatusLocalServiceImpl
 * @generated
 */
@ProviderType
public class StatusLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.chat.service.impl.StatusLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the status to the database. Also notifies the appropriate model listeners.
	*
	* @param status the status
	* @return the status that was added
	*/
	public static com.liferay.chat.model.Status addStatus(
		com.liferay.chat.model.Status status) {
		return getService().addStatus(status);
	}

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public static com.liferay.chat.model.Status createStatus(long statusId) {
		return getService().createStatus(statusId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the status from the database. Also notifies the appropriate model listeners.
	*
	* @param status the status
	* @return the status that was removed
	*/
	public static com.liferay.chat.model.Status deleteStatus(
		com.liferay.chat.model.Status status) {
		return getService().deleteStatus(status);
	}

	/**
	* Deletes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status
	* @return the status that was removed
	* @throws PortalException if a status with the primary key could not be found
	*/
	public static com.liferay.chat.model.Status deleteStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStatus(statusId);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.chat.model.Status fetchStatus(long statusId) {
		return getService().fetchStatus(statusId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<java.lang.Object[]> getAllStatuses(
		long companyId, long userId, long modifiedDate, int start, int end) {
		return getService()
				   .getAllStatuses(companyId, userId, modifiedDate, start, end);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static java.util.List<java.lang.Object[]> getGroupStatuses(
		long userId, long modifiedDate, java.lang.String[] groupNames,
		int start, int end) {
		return getService()
				   .getGroupStatuses(userId, modifiedDate, groupNames, start,
			end);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<java.lang.Object[]> getSocialStatuses(
		long userId, int type, long modifiedDate, int start, int end) {
		return getService()
				   .getSocialStatuses(userId, type, modifiedDate, start, end);
	}

	public static java.util.List<java.lang.Object[]> getSocialStatuses(
		long userId, int[] types, long modifiedDate, int start, int end) {
		return getService()
				   .getSocialStatuses(userId, types, modifiedDate, start, end);
	}

	/**
	* Returns the status with the primary key.
	*
	* @param statusId the primary key of the status
	* @return the status
	* @throws PortalException if a status with the primary key could not be found
	*/
	public static com.liferay.chat.model.Status getStatus(long statusId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStatus(statusId);
	}

	/**
	* Returns a range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.chat.model.impl.StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of statuses
	*/
	public static java.util.List<com.liferay.chat.model.Status> getStatuses(
		int start, int end) {
		return getService().getStatuses(start, end);
	}

	/**
	* Returns the number of statuses.
	*
	* @return the number of statuses
	*/
	public static int getStatusesCount() {
		return getService().getStatusesCount();
	}

	public static com.liferay.chat.model.Status getUserStatus(long userId) {
		return getService().getUserStatus(userId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param status the status
	* @return the status that was updated
	*/
	public static com.liferay.chat.model.Status updateStatus(
		com.liferay.chat.model.Status status) {
		return getService().updateStatus(status);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate) {
		return getService().updateStatus(userId, modifiedDate);
	}

	public static com.liferay.chat.model.Status updateStatus(long userId,
		long modifiedDate, int online, int awake,
		java.lang.String activePanelIds, java.lang.String message, int playSound) {
		return getService()
				   .updateStatus(userId, modifiedDate, online, awake,
			activePanelIds, message, playSound);
	}

	public static void clearService() {
		_service = null;
	}

	public static StatusLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					StatusLocalService.class.getName());

			if (invokableLocalService instanceof StatusLocalService) {
				_service = (StatusLocalService)invokableLocalService;
			}
			else {
				_service = new StatusLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(StatusLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(StatusLocalService service) {
	}

	private static StatusLocalService _service;
}