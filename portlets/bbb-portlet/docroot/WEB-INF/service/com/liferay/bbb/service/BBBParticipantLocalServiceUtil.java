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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for BBBParticipant. This utility wraps
 * {@link com.liferay.bbb.service.impl.BBBParticipantLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shinn Lok
 * @see BBBParticipantLocalService
 * @see com.liferay.bbb.service.base.BBBParticipantLocalServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBParticipantLocalServiceImpl
 * @generated
 */
public class BBBParticipantLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBParticipantLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the b b b participant to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was added
	*/
	public static com.liferay.bbb.model.BBBParticipant addBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		return getService().addBBBParticipant(bbbParticipant);
	}

	/**
	* Creates a new b b b participant with the primary key. Does not add the b b b participant to the database.
	*
	* @param bbbParticipantId the primary key for the new b b b participant
	* @return the new b b b participant
	*/
	public static com.liferay.bbb.model.BBBParticipant createBBBParticipant(
		long bbbParticipantId) {
		return getService().createBBBParticipant(bbbParticipantId);
	}

	/**
	* Deletes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant that was removed
	* @throws PortalException if a b b b participant with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBBBParticipant(bbbParticipantId);
	}

	/**
	* Deletes the b b b participant from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was removed
	* @throws SystemException
	*/
	public static com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteBBBParticipant(bbbParticipant);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.bbb.model.BBBParticipant fetchBBBParticipant(
		long bbbParticipantId) {
		return getService().fetchBBBParticipant(bbbParticipantId);
	}

	/**
	* Returns the b b b participant with the primary key.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant
	* @throws PortalException if a b b b participant with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBParticipant getBBBParticipant(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBBBParticipant(bbbParticipantId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the b b b participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @return the range of b b b participants
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		int start, int end) {
		return getService().getBBBParticipants(start, end);
	}

	/**
	* Returns the number of b b b participants.
	*
	* @return the number of b b b participants
	*/
	public static int getBBBParticipantsCount() {
		return getService().getBBBParticipantsCount();
	}

	/**
	* Updates the b b b participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was updated
	*/
	public static com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		return getService().updateBBBParticipant(bbbParticipant);
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

	public static com.liferay.bbb.model.BBBParticipant addBBBParticipant(
		long userId, long groupId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addBBBParticipant(userId, groupId, bbbMeetingId, name,
			emailAddress, type, status, serviceContext);
	}

	public static com.liferay.bbb.model.BBBParticipant fetchBBBParticipant(
		long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchBBBParticipant(bbbMeetingId, emailAddress);
	}

	public static java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBBBParticipants(bbbMeetingId);
	}

	public static int getBBBParticipantsCount(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getBBBParticipantsCount(bbbMeetingId);
	}

	public static com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		long bbbParticipantId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateBBBParticipant(bbbParticipantId, bbbMeetingId, name,
			emailAddress, type, serviceContext);
	}

	public static com.liferay.bbb.model.BBBParticipant updateStatus(
		long bbbParticipantId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(bbbParticipantId, status);
	}

	public static void clearService() {
		_service = null;
	}

	public static BBBParticipantLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BBBParticipantLocalService.class.getName());

			if (invokableLocalService instanceof BBBParticipantLocalService) {
				_service = (BBBParticipantLocalService)invokableLocalService;
			}
			else {
				_service = new BBBParticipantLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BBBParticipantLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(BBBParticipantLocalService service) {
	}

	private static BBBParticipantLocalService _service;
}