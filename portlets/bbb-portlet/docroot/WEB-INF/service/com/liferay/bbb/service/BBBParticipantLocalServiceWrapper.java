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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BBBParticipantLocalService}.
 *
 * @author Shinn Lok
 * @see BBBParticipantLocalService
 * @generated
 */
public class BBBParticipantLocalServiceWrapper
	implements BBBParticipantLocalService,
		ServiceWrapper<BBBParticipantLocalService> {
	public BBBParticipantLocalServiceWrapper(
		BBBParticipantLocalService bbbParticipantLocalService) {
		_bbbParticipantLocalService = bbbParticipantLocalService;
	}

	/**
	* Adds the b b b participant to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was added
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant addBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		return _bbbParticipantLocalService.addBBBParticipant(bbbParticipant);
	}

	/**
	* Creates a new b b b participant with the primary key. Does not add the b b b participant to the database.
	*
	* @param bbbParticipantId the primary key for the new b b b participant
	* @return the new b b b participant
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant createBBBParticipant(
		long bbbParticipantId) {
		return _bbbParticipantLocalService.createBBBParticipant(bbbParticipantId);
	}

	/**
	* Deletes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant that was removed
	* @throws PortalException if a b b b participant with the primary key could not be found
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbParticipantLocalService.deleteBBBParticipant(bbbParticipantId);
	}

	/**
	* Deletes the b b b participant from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was removed
	* @throws SystemException
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant deleteBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.deleteBBBParticipant(bbbParticipant);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bbbParticipantLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _bbbParticipantLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _bbbParticipantLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _bbbParticipantLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _bbbParticipantLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _bbbParticipantLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant fetchBBBParticipant(
		long bbbParticipantId) {
		return _bbbParticipantLocalService.fetchBBBParticipant(bbbParticipantId);
	}

	/**
	* Returns the b b b participant with the primary key.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant
	* @throws PortalException if a b b b participant with the primary key could not be found
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant getBBBParticipant(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbParticipantLocalService.getBBBParticipant(bbbParticipantId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bbbParticipantLocalService.getActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbParticipantLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbParticipantLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		int start, int end) {
		return _bbbParticipantLocalService.getBBBParticipants(start, end);
	}

	/**
	* Returns the number of b b b participants.
	*
	* @return the number of b b b participants
	*/
	@Override
	public int getBBBParticipantsCount() {
		return _bbbParticipantLocalService.getBBBParticipantsCount();
	}

	/**
	* Updates the b b b participant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipant the b b b participant
	* @return the b b b participant that was updated
	*/
	@Override
	public com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		return _bbbParticipantLocalService.updateBBBParticipant(bbbParticipant);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbParticipantLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbParticipantLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbParticipantLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant addBBBParticipant(long userId,
		long groupId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.addBBBParticipant(userId, groupId,
			bbbMeetingId, name, emailAddress, type, status, serviceContext);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant fetchBBBParticipant(
		long bbbMeetingId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.fetchBBBParticipant(bbbMeetingId,
			emailAddress);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBParticipant> getBBBParticipants(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.getBBBParticipants(bbbMeetingId);
	}

	@Override
	public int getBBBParticipantsCount(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.getBBBParticipantsCount(bbbMeetingId);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant updateBBBParticipant(
		long bbbParticipantId, long bbbMeetingId, java.lang.String name,
		java.lang.String emailAddress, int type,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.updateBBBParticipant(bbbParticipantId,
			bbbMeetingId, name, emailAddress, type, serviceContext);
	}

	@Override
	public com.liferay.bbb.model.BBBParticipant updateStatus(
		long bbbParticipantId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbParticipantLocalService.updateStatus(bbbParticipantId, status);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BBBParticipantLocalService getWrappedBBBParticipantLocalService() {
		return _bbbParticipantLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBBBParticipantLocalService(
		BBBParticipantLocalService bbbParticipantLocalService) {
		_bbbParticipantLocalService = bbbParticipantLocalService;
	}

	@Override
	public BBBParticipantLocalService getWrappedService() {
		return _bbbParticipantLocalService;
	}

	@Override
	public void setWrappedService(
		BBBParticipantLocalService bbbParticipantLocalService) {
		_bbbParticipantLocalService = bbbParticipantLocalService;
	}

	private BBBParticipantLocalService _bbbParticipantLocalService;
}