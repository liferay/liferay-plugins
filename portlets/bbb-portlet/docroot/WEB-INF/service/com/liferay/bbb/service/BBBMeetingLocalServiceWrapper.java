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
 * Provides a wrapper for {@link BBBMeetingLocalService}.
 *
 * @author Shinn Lok
 * @see BBBMeetingLocalService
 * @generated
 */
public class BBBMeetingLocalServiceWrapper implements BBBMeetingLocalService,
	ServiceWrapper<BBBMeetingLocalService> {
	public BBBMeetingLocalServiceWrapper(
		BBBMeetingLocalService bbbMeetingLocalService) {
		_bbbMeetingLocalService = bbbMeetingLocalService;
	}

	/**
	* Adds the b b b meeting to the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was added
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting addBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		return _bbbMeetingLocalService.addBBBMeeting(bbbMeeting);
	}

	/**
	* Creates a new b b b meeting with the primary key. Does not add the b b b meeting to the database.
	*
	* @param bbbMeetingId the primary key for the new b b b meeting
	* @return the new b b b meeting
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting createBBBMeeting(long bbbMeetingId) {
		return _bbbMeetingLocalService.createBBBMeeting(bbbMeetingId);
	}

	/**
	* Deletes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting that was removed
	* @throws PortalException if a b b b meeting with the primary key could not be found
	* @throws SystemException
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.deleteBBBMeeting(bbbMeetingId);
	}

	/**
	* Deletes the b b b meeting from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was removed
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting deleteBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.deleteBBBMeeting(bbbMeeting);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bbbMeetingLocalService.dynamicQuery();
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
		return _bbbMeetingLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _bbbMeetingLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _bbbMeetingLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _bbbMeetingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _bbbMeetingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting fetchBBBMeeting(long bbbMeetingId) {
		return _bbbMeetingLocalService.fetchBBBMeeting(bbbMeetingId);
	}

	/**
	* Returns the b b b meeting with the primary key.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting
	* @throws PortalException if a b b b meeting with the primary key could not be found
	* @throws SystemException
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting getBBBMeeting(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeeting(bbbMeetingId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _bbbMeetingLocalService.getActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _bbbMeetingLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		int start, int end) {
		return _bbbMeetingLocalService.getBBBMeetings(start, end);
	}

	/**
	* Returns the number of b b b meetings.
	*
	* @return the number of b b b meetings
	*/
	@Override
	public int getBBBMeetingsCount() {
		return _bbbMeetingLocalService.getBBBMeetingsCount();
	}

	/**
	* Updates the b b b meeting in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param bbbMeeting the b b b meeting
	* @return the b b b meeting that was updated
	*/
	@Override
	public com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		return _bbbMeetingLocalService.updateBBBMeeting(bbbMeeting);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _bbbMeetingLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_bbbMeetingLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _bbbMeetingLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting addBBBMeeting(long userId,
		long groupId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword, int status,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.addBBBMeeting(userId, groupId,
			bbbServerId, name, description, attendeePassword,
			moderatorPassword, status, bbbParticipants, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetings(status);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetings(groupId, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.bbb.model.BBBMeeting> getBBBMeetings(
		long groupId, long userId, java.lang.String name,
		java.lang.String description, int status, boolean andSearch, int start,
		int end, java.lang.String orderByField, java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetings(groupId, userId, name,
			description, status, andSearch, start, end, orderByField,
			orderByType);
	}

	@Override
	public int getBBBMeetingsCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetingsCount(groupId);
	}

	@Override
	public int getBBBMeetingsCount(long bbbServerId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetingsCount(bbbServerId, status);
	}

	@Override
	public int getBBBMeetingsCount(long groupId, long userId,
		java.lang.String name, java.lang.String description, int status,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.getBBBMeetingsCount(groupId, userId,
			name, description, status, andSearch);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting updateBBBMeeting(
		long bbbMeetingId, long bbbServerId, java.lang.String name,
		java.lang.String description, java.lang.String attendeePassword,
		java.lang.String moderatorPassword,
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.updateBBBMeeting(bbbMeetingId,
			bbbServerId, name, description, attendeePassword,
			moderatorPassword, bbbParticipants, serviceContext);
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting updateStatus(long bbbMeetingId,
		int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _bbbMeetingLocalService.updateStatus(bbbMeetingId, status);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public BBBMeetingLocalService getWrappedBBBMeetingLocalService() {
		return _bbbMeetingLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedBBBMeetingLocalService(
		BBBMeetingLocalService bbbMeetingLocalService) {
		_bbbMeetingLocalService = bbbMeetingLocalService;
	}

	@Override
	public BBBMeetingLocalService getWrappedService() {
		return _bbbMeetingLocalService;
	}

	@Override
	public void setWrappedService(BBBMeetingLocalService bbbMeetingLocalService) {
		_bbbMeetingLocalService = bbbMeetingLocalService;
	}

	private BBBMeetingLocalService _bbbMeetingLocalService;
}