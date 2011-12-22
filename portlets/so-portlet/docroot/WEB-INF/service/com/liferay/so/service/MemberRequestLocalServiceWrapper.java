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

package com.liferay.so.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MemberRequestLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MemberRequestLocalService
 * @generated
 */
public class MemberRequestLocalServiceWrapper
	implements MemberRequestLocalService,
		ServiceWrapper<MemberRequestLocalService> {
	public MemberRequestLocalServiceWrapper(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	/**
	* Adds the member request to the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.addMemberRequest(memberRequest);
	}

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	public com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return _memberRequestLocalService.createMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @throws PortalException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMemberRequest(long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.deleteMemberRequest(memberRequest);
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
		return _memberRequestLocalService.dynamicQuery(dynamicQuery);
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
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _memberRequestLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.so.model.MemberRequest fetchMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.fetchMemberRequest(memberRequestId);
	}

	/**
	* Returns the member request with the primary key.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws PortalException if a member request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequest(memberRequestId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the member requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of member requests
	* @param end the upper bound of the range of member requests (not inclusive)
	* @return the range of member requests
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequests(start, end);
	}

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	* @throws SystemException if a system exception occurred
	*/
	public int getMemberRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequestsCount();
	}

	/**
	* Updates the member request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest);
	}

	/**
	* Updates the member request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @param merge whether to merge the member request with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the member request that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(memberRequest,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _memberRequestLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_memberRequestLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.so.model.MemberRequest addMemberRequest(long userId,
		long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress, long invitedRoleId,
		long invitedTeamId, com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.addMemberRequest(userId, groupId,
			receiverUserId, receiverEmailAddress, invitedRoleId, invitedTeamId,
			themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds, long invitedRoleId, long invitedTeamId,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			receiverUserIds, invitedRoleId, invitedTeamId, themeDisplay);
	}

	public void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses, long invitedRoleId,
		long invitedTeamId, com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			emailAddresses, invitedRoleId, invitedTeamId, themeDisplay);
	}

	public com.liferay.so.model.MemberRequest getMemberRequest(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getMemberRequest(groupId,
			receiverUserId, status);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequest(receiverUserId,
			start, end);
	}

	public int getReceiverMemberRequestCount(long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverMemberRequestCount(receiverUserId);
	}

	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequest(receiverUserId,
			status, start, end);
	}

	public int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.getReceiverStatusMemberRequestCount(receiverUserId,
			status);
	}

	public boolean hasPendingMemberRequest(long groupId, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.hasPendingMemberRequest(groupId,
			receiverUserId);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(long userId,
		long memberRequestId, int status) throws java.lang.Exception {
		return _memberRequestLocalService.updateMemberRequest(userId,
			memberRequestId, status);
	}

	public com.liferay.so.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _memberRequestLocalService.updateMemberRequest(key,
			receiverUserId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MemberRequestLocalService getWrappedMemberRequestLocalService() {
		return _memberRequestLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMemberRequestLocalService(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	public MemberRequestLocalService getWrappedService() {
		return _memberRequestLocalService;
	}

	public void setWrappedService(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	private MemberRequestLocalService _memberRequestLocalService;
}