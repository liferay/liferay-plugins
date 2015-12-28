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

package com.liferay.microblogs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MicroblogsEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryLocalService
 * @generated
 */
@ProviderType
public class MicroblogsEntryLocalServiceWrapper
	implements MicroblogsEntryLocalService,
		ServiceWrapper<MicroblogsEntryLocalService> {
	public MicroblogsEntryLocalServiceWrapper(
		MicroblogsEntryLocalService microblogsEntryLocalService) {
		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	/**
	* Adds the microblogs entry to the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was added
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return _microblogsEntryLocalService.addMicroblogsEntry(microblogsEntry);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type,
		long parentMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.addMicroblogsEntry(userId, content,
			type, parentMicroblogsEntryId, socialRelationType, serviceContext);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, long creatorClassNameId, long creatorClassPK,
		java.lang.String content, int type, long parentMicroblogsEntryId,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.addMicroblogsEntry(userId,
			creatorClassNameId, creatorClassPK, content, type,
			parentMicroblogsEntryId, socialRelationType, serviceContext);
	}

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry createMicroblogsEntry(
		long microblogsEntryId) {
		return _microblogsEntryLocalService.createMicroblogsEntry(microblogsEntryId);
	}

	@Override
	public void deleteMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_microblogsEntryLocalService.deleteMicroblogsEntries(creatorClassNameId,
			creatorClassPK);
	}

	/**
	* Deletes the microblogs entry from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntry);
	}

	/**
	* Deletes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntryId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteUserMicroblogsEntries(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_microblogsEntryLocalService.deleteUserMicroblogsEntries(userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _microblogsEntryLocalService.dynamicQuery();
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
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _microblogsEntryLocalService.dynamicQuery(dynamicQuery, start,
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
		return _microblogsEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _microblogsEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry fetchMicroblogsEntry(
		long microblogsEntryId) {
		return _microblogsEntryLocalService.fetchMicroblogsEntry(microblogsEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _microblogsEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getCompanyMicroblogsEntries(
		long companyId, int start, int end) {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntries(companyId,
			start, end);
	}

	@Override
	public int getCompanyMicroblogsEntriesCount(long companyId) {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntriesCount(companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _microblogsEntryLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, java.lang.String assetTagName, int start,
		int end) {
		return _microblogsEntryLocalService.getMicroblogsEntries(creatorClassNameId,
			assetTagName, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK,
		java.lang.String assetTagName, boolean andOperator, int start, int end) {
		return _microblogsEntryLocalService.getMicroblogsEntries(creatorClassNameId,
			creatorClassPK, assetTagName, andOperator, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int start, int end) {
		return _microblogsEntryLocalService.getMicroblogsEntries(creatorClassNameId,
			creatorClassPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end) {
		return _microblogsEntryLocalService.getMicroblogsEntries(creatorClassNameId,
			creatorClassPK, type, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _microblogsEntryLocalService.getMicroblogsEntries(creatorClassNameId,
			type, start, end, obc);
	}

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	*/
	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end) {
		return _microblogsEntryLocalService.getMicroblogsEntries(start, end);
	}

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	*/
	@Override
	public int getMicroblogsEntriesCount() {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount();
	}

	@Override
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		java.lang.String assetTagName) {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount(creatorClassNameId,
			assetTagName);
	}

	@Override
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK) {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount(creatorClassNameId,
			creatorClassPK);
	}

	@Override
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK, java.lang.String assetTagName, boolean andOperator) {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount(creatorClassNameId,
			creatorClassPK, assetTagName, andOperator);
	}

	@Override
	public int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK, int type) {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount(creatorClassNameId,
			creatorClassPK, type);
	}

	/**
	* Returns the microblogs entry with the primary key.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.getMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _microblogsEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end) {
		return _microblogsEntryLocalService.getParentMicroblogsEntryMicroblogsEntries(type,
			parentMicroblogsEntryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getParentMicroblogsEntryMicroblogsEntries(
		int type, long parentMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator) {
		return _microblogsEntryLocalService.getParentMicroblogsEntryMicroblogsEntries(type,
			parentMicroblogsEntryId, start, end, orderByComparator);
	}

	@Override
	public int getParentMicroblogsEntryMicroblogsEntriesCount(int type,
		long parentMicroblogsEntryId) {
		return _microblogsEntryLocalService.getParentMicroblogsEntryMicroblogsEntriesCount(type,
			parentMicroblogsEntryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int start, int end) {
		return _microblogsEntryLocalService.getUserMicroblogsEntries(userId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int type, int start, int end) {
		return _microblogsEntryLocalService.getUserMicroblogsEntries(userId,
			type, start, end);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long userId) {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCount(userId);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long userId, int type) {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCount(userId,
			type);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _microblogsEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public void updateAsset(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		_microblogsEntryLocalService.updateAsset(microblogsEntry,
			assetCategoryIds, assetTagNames);
	}

	/**
	* Updates the microblogs entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was updated
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntry);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
	}

	@Override
	public MicroblogsEntryLocalService getWrappedService() {
		return _microblogsEntryLocalService;
	}

	@Override
	public void setWrappedService(
		MicroblogsEntryLocalService microblogsEntryLocalService) {
		_microblogsEntryLocalService = microblogsEntryLocalService;
	}

	private MicroblogsEntryLocalService _microblogsEntryLocalService;
}