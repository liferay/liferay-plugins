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

package com.liferay.microblogs.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MicroblogsEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry createMicroblogsEntry(
		long microblogsEntryId) {
		return _microblogsEntryLocalService.createMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Deletes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Deletes the microblogs entry from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.deleteMicroblogsEntry(microblogsEntry);
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry fetchMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.fetchMicroblogsEntry(microblogsEntryId);
	}

	/**
	* Returns the microblogs entry with the primary key.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws PortalException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntry(microblogsEntryId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getPersistedModel(primaryKeyObj);
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntries(start, end);
	}

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getMicroblogsEntriesCount();
	}

	/**
	* Updates the microblogs entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntry);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _microblogsEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_microblogsEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _microblogsEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.addMicroblogsEntry(userId, content,
			type, receiverUserId, receiverMicroblogsEntryId,
			socialRelationType, serviceContext);
	}

	@Override
	public void deleteUserMicroblogsEntries(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.deleteUserMicroblogsEntries(userId);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getCompanyMicroblogsEntries(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntries(companyId,
			start, end);
	}

	@Override
	public int getCompanyMicroblogsEntriesCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getCompanyMicroblogsEntriesCount(companyId);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
		int type, long receiverMicroblogsEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverMicroblogsEntryMicroblogsEntries(type,
			receiverMicroblogsEntryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverMicroblogsEntryMicroblogsEntries(type,
			receiverMicroblogsEntryId, start, end, orderByComparator);
	}

	@Override
	public int getReceiverMicroblogsEntryMicroblogsEntriesCount(int type,
		long receiverMicroblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverMicroblogsEntryMicroblogsEntriesCount(type,
			receiverMicroblogsEntryId);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverUserMicroblogsEntries(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverUserMicroblogsEntries(type,
			receiverUserId, start, end);
	}

	@Override
	public int getReceiverUserMicroblogsEntriesCount(int type,
		long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getReceiverUserMicroblogsEntriesCount(type,
			receiverUserId);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntries(userId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntries(userId,
			type, start, end);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCount(userId);
	}

	@Override
	public int getUserMicroblogsEntriesCount(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.getUserMicroblogsEntriesCount(userId,
			type);
	}

	@Override
	public void updateAsset(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_microblogsEntryLocalService.updateAsset(microblogsEntry,
			assetCategoryIds, assetTagNames);
	}

	@Override
	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _microblogsEntryLocalService.updateMicroblogsEntry(microblogsEntryId,
			content, socialRelationType, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public MicroblogsEntryLocalService getWrappedMicroblogsEntryLocalService() {
		return _microblogsEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedMicroblogsEntryLocalService(
		MicroblogsEntryLocalService microblogsEntryLocalService) {
		_microblogsEntryLocalService = microblogsEntryLocalService;
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