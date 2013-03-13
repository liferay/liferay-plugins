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

package com.liferay.so.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the member request local service. This utility wraps {@link com.liferay.so.service.impl.MemberRequestLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestLocalService
 * @see com.liferay.so.service.base.MemberRequestLocalServiceBaseImpl
 * @see com.liferay.so.service.impl.MemberRequestLocalServiceImpl
 * @generated
 */
public class MemberRequestLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.service.impl.MemberRequestLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the member request to the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addMemberRequest(memberRequest);
	}

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public static com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return getService().createMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws PortalException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest deleteMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteMemberRequest(memberRequest);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.so.model.MemberRequest fetchMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchMemberRequest(memberRequestId);
	}

	/**
	* Returns the member request with the primary key.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws PortalException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMemberRequest(memberRequestId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.MemberRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMemberRequests(start, end);
	}

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	* @throws SystemException if a system exception occurred
	*/
	public static int getMemberRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getMemberRequestsCount();
	}

	/**
	* Updates the member request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMemberRequest(memberRequest);
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

	public static com.liferay.so.model.MemberRequest addMemberRequest(
		long userId, long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addMemberRequest(userId, groupId, receiverUserId,
			receiverEmailAddress, invitedRoleId, invitedTeamId, serviceContext);
	}

	public static void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds, long invitedRoleId, long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addMemberRequests(userId, groupId, receiverUserIds, invitedRoleId,
			invitedTeamId, serviceContext);
	}

	public static void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addMemberRequests(userId, groupId, emailAddresses, invitedRoleId,
			invitedTeamId, serviceContext);
	}

	public static com.liferay.so.model.MemberRequest getMemberRequest(
		long groupId, long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMemberRequest(groupId, receiverUserId, status);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getReceiverMemberRequest(receiverUserId, start, end);
	}

	public static int getReceiverMemberRequestCount(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getReceiverMemberRequestCount(receiverUserId);
	}

	public static java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReceiverStatusMemberRequest(receiverUserId, status,
			start, end);
	}

	public static int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getReceiverStatusMemberRequestCount(receiverUserId, status);
	}

	public static boolean hasPendingMemberRequest(long groupId,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasPendingMemberRequest(groupId, receiverUserId);
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		long userId, long memberRequestId, int status)
		throws java.lang.Exception {
		return getService().updateMemberRequest(userId, memberRequestId, status);
	}

	public static com.liferay.so.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateMemberRequest(key, receiverUserId);
	}

	public static void clearService() {
		_service = null;
	}

	public static MemberRequestLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MemberRequestLocalService.class.getName());

			if (invokableLocalService instanceof MemberRequestLocalService) {
				_service = (MemberRequestLocalService)invokableLocalService;
			}
			else {
				_service = new MemberRequestLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(MemberRequestLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(MemberRequestLocalService service) {
	}

	private static MemberRequestLocalService _service;
}