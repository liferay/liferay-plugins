/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service;

/**
 * <p>
 * This class is a wrapper for {@link MicroblogsEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MicroblogsEntryLocalService
 * @generated
 */
public class MicroblogsEntryLocalServiceWrapper
	implements MicroblogsEntryLocalService {
	public MicroblogsEntryLocalServiceWrapper(
		MicroblogsEntryLocalService microblogsEntryLocalService) {
		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	/**
	* Adds the microblogs entry to the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry to add
	* @return the microblogs entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.addMicroblogsEntry(microblogsEntry);
	}

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public com.liferay.microblogs.model.MicroblogsEntry createMicroblogsEntry(
		long microblogsEntryId) {
		return _microblogsEntryLocalService.createMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Deletes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry to delete
	* @throws PortalException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Deletes the microblogs entry from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public void deleteMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntry);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the microblogs entry with the primary key.
	*
	* @param microblogsEntryId the primary key of the microblogs entry to get
	* @return the microblogs entry
	* @throws PortalException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Gets a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntries(start, end);
	}

	/**
	* Gets the number of microblogs entries.
	*
	* @return the number of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount();
	}

	/**
	* Updates the microblogs entry in the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry to update
	* @return the microblogs entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntry);
	}

	/**
	* Updates the microblogs entry in the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry to update
	* @param merge whether to merge the microblogs entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the microblogs entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntry,
			merge);
	}

	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.addMicroblogsEntry(userId, content,
			type, receiverUserId, receiverEntryId, socialRelationType,
			serviceContext);
	}

	public void addMicroblogsEntryResources(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.addMicroblogsEntryResources(microblogsEntry);
	}

	public void deleteUserMicroblogsEntries(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.deleteUserMicroblogsEntries(userId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getCompanyMicroblogsEntries(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntries(companyId,
			start, end);
	}

	public int getCompanyMicroblogsEntriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntriesCount(companyId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getEntryMicroblogsEntriesByType(
		int type, long receiverEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getEntryMicroblogsEntriesByType(type,
			receiverEntryId, start, end);
	}

	public int getEntryMicroblogsEntriesCountByType(int type,
		long receiverEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getEntryMicroblogsEntriesCountByType(type,
			receiverEntryId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverMicroblogsEntriesByType(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverMicroblogsEntriesByType(type,
			receiverUserId, start, end);
	}

	public int getRecieverMicroblogsEntriesCountByType(int type,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getRecieverMicroblogsEntriesCountByType(type,
			receiverUserId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntries(userId,
			start, end);
	}

	public int getUserMicroblogsEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCount(userId);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntriesByType(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesByType(userId,
			type, start, end);
	}

	public int getUserMicroblogsEntriesCountByType(long userId, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCountByType(userId,
			type);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUsersMicroblogsEntries(
		long[] userIds, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUsersMicroblogsEntries(userIds,
			viewerUserId, start, end);
	}

	public int getUsersMicroblogsEntriesCount(long[] userIds, long viewerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUsersMicroblogsEntriesCount(userIds,
			viewerUserId);
	}

	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
	}

	public MicroblogsEntryLocalService getWrappedMicroblogsEntryLocalService() {
		return _microblogsEntryLocalService;
	}

	public void setWrappedMicroblogsEntryLocalService(
		MicroblogsEntryLocalService microblogsEntryLocalService) {
		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	private MicroblogsEntryLocalService _microblogsEntryLocalService;
}