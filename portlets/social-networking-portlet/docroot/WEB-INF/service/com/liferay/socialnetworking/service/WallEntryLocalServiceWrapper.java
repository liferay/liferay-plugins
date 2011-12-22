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

package com.liferay.socialnetworking.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link WallEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WallEntryLocalService
 * @generated
 */
public class WallEntryLocalServiceWrapper implements WallEntryLocalService,
	ServiceWrapper<WallEntryLocalService> {
	public WallEntryLocalServiceWrapper(
		WallEntryLocalService wallEntryLocalService) {
		_wallEntryLocalService = wallEntryLocalService;
	}

	/**
	* Adds the wall entry to the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @return the wall entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry addWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.addWallEntry(wallEntry);
	}

	/**
	* Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	*
	* @param wallEntryId the primary key for the new wall entry
	* @return the new wall entry
	*/
	public com.liferay.socialnetworking.model.WallEntry createWallEntry(
		long wallEntryId) {
		return _wallEntryLocalService.createWallEntry(wallEntryId);
	}

	/**
	* Deletes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntryId the primary key of the wall entry
	* @throws PortalException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWallEntry(long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wallEntryLocalService.deleteWallEntry(wallEntryId);
	}

	/**
	* Deletes the wall entry from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @throws SystemException if a system exception occurred
	*/
	public void deleteWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_wallEntryLocalService.deleteWallEntry(wallEntry);
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
		return _wallEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _wallEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _wallEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _wallEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialnetworking.model.WallEntry fetchWallEntry(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.fetchWallEntry(wallEntryId);
	}

	/**
	* Returns the wall entry with the primary key.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry
	* @throws PortalException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry getWallEntry(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallEntry(wallEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallEntries(start, end);
	}

	/**
	* Returns the number of wall entries.
	*
	* @return the number of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public int getWallEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallEntriesCount();
	}

	/**
	* Updates the wall entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @return the wall entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntry);
	}

	/**
	* Updates the wall entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wallEntry the wall entry
	* @param merge whether to merge the wall entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the wall entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _wallEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wallEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.socialnetworking.model.WallEntry addWallEntry(
		long groupId, long userId, java.lang.String comments,
		com.liferay.portal.theme.ThemeDisplay themeDisplay)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.addWallEntry(groupId, userId, comments,
			themeDisplay);
	}

	public void deleteWallEntries(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_wallEntryLocalService.deleteWallEntries(groupId);
	}

	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallEntries(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallEntries(groupId, start, end);
	}

	public int getWallEntriesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallEntriesCount(groupId);
	}

	public java.util.List<com.liferay.socialnetworking.model.WallEntry> getWallToWallEntries(
		long groupId1, long groupId2, long userId1, long userId2, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallToWallEntries(groupId1, groupId2,
			userId1, userId2, start, end);
	}

	public int getWallToWallEntriesCount(long groupId1, long groupId2,
		long userId1, long userId2)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.getWallToWallEntriesCount(groupId1,
			groupId2, userId1, userId2);
	}

	public com.liferay.socialnetworking.model.WallEntry updateWallEntry(
		long wallEntryId, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _wallEntryLocalService.updateWallEntry(wallEntryId, comments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WallEntryLocalService getWrappedWallEntryLocalService() {
		return _wallEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWallEntryLocalService(
		WallEntryLocalService wallEntryLocalService) {
		_wallEntryLocalService = wallEntryLocalService;
	}

	public WallEntryLocalService getWrappedService() {
		return _wallEntryLocalService;
	}

	public void setWrappedService(WallEntryLocalService wallEntryLocalService) {
		_wallEntryLocalService = wallEntryLocalService;
	}

	private WallEntryLocalService _wallEntryLocalService;
}