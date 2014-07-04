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
 * Provides the local service utility for BBBMeeting. This utility wraps
 * {@link com.liferay.bbb.service.impl.BBBMeetingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Shinn Lok
 * @see BBBMeetingLocalService
 * @see com.liferay.bbb.service.base.BBBMeetingLocalServiceBaseImpl
 * @see com.liferay.bbb.service.impl.BBBMeetingLocalServiceImpl
 * @generated
 */
public class BBBMeetingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.bbb.service.impl.BBBMeetingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the b b b meeting to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was added
	*/
	public static com.liferay.bbb.model.BBBMeeting addBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		return getService().addBBBMeeting(bbbMeeting);
	}

	public static com.liferay.bbb.model.BBBMeeting addBBBMeeting(long userId,
		long groupId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword, int status,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addBBBMeeting(userId, groupId, bbbServerId, name,
			description, attendeePassword, moderatorPassword, status,
			bbbParticipants, serviceContext);
	}

	/**
	* Creates a new b b b meeting with the primary key. Does not add the b b b meeting to the database.
	*
	* @param bbbMeetingId the primary key for the new b b b meeting
	* @return the new b b b meeting
	*/
	public static com.liferay.bbb.model.BBBMeeting createBBBMeeting(
		long bbbMeetingId) {
		return getService().createBBBMeeting(bbbMeetingId);
	}

	/**
	* Deletes the b b b meeting from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was removed
	* @throws PortalException
	*/
	public static com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBBBMeeting(bbbMeeting);
	}

	/**
	* Deletes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting that was removed
	* @throws PortalException if a b b b meeting with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteBBBMeeting(bbbMeetingId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.bbb.model.BBBMeeting fetchBBBMeeting(
		long bbbMeetingId) {
		return getService().fetchBBBMeeting(bbbMeetingId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the b b b meeting with the primary key.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting
	* @throws PortalException if a b b b meeting with the primary key could not be found
	*/
	public static com.liferay.bbb.model.BBBMeeting getBBBMeeting(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getBBBMeeting(bbbMeetingId);
	}

	public static java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getBBBMeetings(groupId, start, end, obc);
	}

	public static java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, long userId, java.lang.String name,
		java.lang.String description, int status, boolean andSearch, int start,
		int end, java.lang.String orderByField, java.lang.String orderByType) {
		return getService()
				   .getBBBMeetings(groupId, userId, name, description, status,
			andSearch, start, end, orderByField, orderByType);
	}

	/**
	* Returns a range of all the b b b meetings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of b b b meetings
	*/
	public static java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		int start, int end) {
		return getService().getBBBMeetings(start, end);
	}

	public static java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		int status) {
		return getService().getBBBMeetings(status);
	}

	/**
	* Returns the number of b b b meetings.
	*
	* @return the number of b b b meetings
	*/
	public static int getBBBMeetingsCount() {
		return getService().getBBBMeetingsCount();
	}

	public static int getBBBMeetingsCount(long bbbServerId, int status) {
		return getService().getBBBMeetingsCount(bbbServerId, status);
	}

	public static int getBBBMeetingsCount(long groupId) {
		return getService().getBBBMeetingsCount(groupId);
	}

	public static int getBBBMeetingsCount(long groupId, long userId,
		java.lang.String name, java.lang.String description, int status,
		boolean andSearch) {
		return getService()
				   .getBBBMeetingsCount(groupId, userId, name, description,
			status, andSearch);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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
	* Updates the b b b meeting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was updated
	*/
	public static com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		return getService().updateBBBMeeting(bbbMeeting);
	}

	public static com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		long bbbMeetingId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateBBBMeeting(bbbMeetingId, bbbServerId, name,
			description, attendeePassword, moderatorPassword, bbbParticipants,
			serviceContext);
	}

	public static com.liferay.bbb.model.BBBMeeting updateStatus(
		long bbbMeetingId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(bbbMeetingId, status);
	}

	public static void clearService() {
		_service = null;
	}

	public static BBBMeetingLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					BBBMeetingLocalService.class.getName());

			if (invokableLocalService instanceof BBBMeetingLocalService) {
				_service = (BBBMeetingLocalService)invokableLocalService;
			}
			else {
				_service = new BBBMeetingLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(BBBMeetingLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(BBBMeetingLocalService service) {
	}

	private static BBBMeetingLocalService _service;
}