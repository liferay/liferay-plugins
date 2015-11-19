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

package com.liferay.so.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MemberRequestLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MemberRequestLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.so.model.MemberRequest addMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest) {
		return _memberRequestLocalService.addMemberRequest(memberRequest);
	}

	@Override
	public com.liferay.so.model.MemberRequest addMemberRequest(long userId,
		long groupId, long receiverUserId,
		java.lang.String receiverEmailAddress, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.addMemberRequest(userId, groupId,
			receiverUserId, receiverEmailAddress, invitedRoleId, invitedTeamId,
			serviceContext);
	}

	@Override
	public void addMemberRequests(long userId, long groupId,
		java.lang.String[] emailAddresses, long invitedRoleId,
		long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			emailAddresses, invitedRoleId, invitedTeamId, serviceContext);
	}

	@Override
	public void addMemberRequests(long userId, long groupId,
		long[] receiverUserIds, long invitedRoleId, long invitedTeamId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_memberRequestLocalService.addMemberRequests(userId, groupId,
			receiverUserIds, invitedRoleId, invitedTeamId, serviceContext);
	}

	/**
	* Creates a new member request with the primary key. Does not add the member request to the database.
	*
	* @param memberRequestId the primary key for the new member request
	* @return the new member request
	*/
	@Override
	public com.liferay.so.model.MemberRequest createMemberRequest(
		long memberRequestId) {
		return _memberRequestLocalService.createMemberRequest(memberRequestId);
	}

	/**
	* Deletes the member request from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was removed
	*/
	@Override
	public com.liferay.so.model.MemberRequest deleteMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest) {
		return _memberRequestLocalService.deleteMemberRequest(memberRequest);
	}

	/**
	* Deletes the member request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request that was removed
	* @throws PortalException if a member request with the primary key could not be found
	*/
	@Override
	public com.liferay.so.model.MemberRequest deleteMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.deleteMemberRequest(memberRequestId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _memberRequestLocalService.dynamicQuery();
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
		return _memberRequestLocalService.dynamicQuery(dynamicQuery);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start, end);
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
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _memberRequestLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _memberRequestLocalService.dynamicQueryCount(dynamicQuery);
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
		return _memberRequestLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.so.model.MemberRequest fetchMemberRequest(
		long memberRequestId) {
		return _memberRequestLocalService.fetchMemberRequest(memberRequestId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _memberRequestLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _memberRequestLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.so.model.MemberRequest getMemberRequest(long groupId,
		long receiverUserId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.getMemberRequest(groupId,
			receiverUserId, status);
	}

	/**
	* Returns the member request with the primary key.
	*
	* @param memberRequestId the primary key of the member request
	* @return the member request
	* @throws PortalException if a member request with the primary key could not be found
	*/
	@Override
	public com.liferay.so.model.MemberRequest getMemberRequest(
		long memberRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.getMemberRequest(memberRequestId);
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
	*/
	@Override
	public java.util.List<com.liferay.so.model.MemberRequest> getMemberRequests(
		int start, int end) {
		return _memberRequestLocalService.getMemberRequests(start, end);
	}

	/**
	* Returns the number of member requests.
	*
	* @return the number of member requests
	*/
	@Override
	public int getMemberRequestsCount() {
		return _memberRequestLocalService.getMemberRequestsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _memberRequestLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverMemberRequest(
		long receiverUserId, int start, int end) {
		return _memberRequestLocalService.getReceiverMemberRequest(receiverUserId,
			start, end);
	}

	@Override
	public int getReceiverMemberRequestCount(long receiverUserId) {
		return _memberRequestLocalService.getReceiverMemberRequestCount(receiverUserId);
	}

	@Override
	public java.util.List<com.liferay.so.model.MemberRequest> getReceiverStatusMemberRequest(
		long receiverUserId, int status, int start, int end) {
		return _memberRequestLocalService.getReceiverStatusMemberRequest(receiverUserId,
			status, start, end);
	}

	@Override
	public int getReceiverStatusMemberRequestCount(long receiverUserId,
		int status) {
		return _memberRequestLocalService.getReceiverStatusMemberRequestCount(receiverUserId,
			status);
	}

	@Override
	public boolean hasPendingMemberRequest(long groupId, long receiverUserId) {
		return _memberRequestLocalService.hasPendingMemberRequest(groupId,
			receiverUserId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _memberRequestLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.so.model.MemberRequest updateMemberRequest(
		java.lang.String key, long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _memberRequestLocalService.updateMemberRequest(key,
			receiverUserId);
	}

	/**
	* Updates the member request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberRequest the member request
	* @return the member request that was updated
	*/
	@Override
	public com.liferay.so.model.MemberRequest updateMemberRequest(
		com.liferay.so.model.MemberRequest memberRequest) {
		return _memberRequestLocalService.updateMemberRequest(memberRequest);
	}

	@Override
	public com.liferay.so.model.MemberRequest updateMemberRequest(long userId,
		long memberRequestId, int status) throws java.lang.Exception {
		return _memberRequestLocalService.updateMemberRequest(userId,
			memberRequestId, status);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public MemberRequestLocalService getWrappedMemberRequestLocalService() {
		return _memberRequestLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedMemberRequestLocalService(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	@Override
	public MemberRequestLocalService getWrappedService() {
		return _memberRequestLocalService;
	}

	@Override
	public void setWrappedService(
		MemberRequestLocalService memberRequestLocalService) {
		_memberRequestLocalService = memberRequestLocalService;
	}

	private MemberRequestLocalService _memberRequestLocalService;
}