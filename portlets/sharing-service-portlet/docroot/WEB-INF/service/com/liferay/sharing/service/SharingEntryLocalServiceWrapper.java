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

package com.liferay.sharing.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SharingEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntryLocalService
 * @generated
 */
@ProviderType
public class SharingEntryLocalServiceWrapper implements SharingEntryLocalService,
	ServiceWrapper<SharingEntryLocalService> {
	public SharingEntryLocalServiceWrapper(
		SharingEntryLocalService sharingEntryLocalService) {
		_sharingEntryLocalService = sharingEntryLocalService;
	}

	@Override
	public void addSharingEntries(long classNameId, long classPK,
		java.util.Map<java.lang.Long, long[]> scopes) {
		_sharingEntryLocalService.addSharingEntries(classNameId, classPK, scopes);
	}

	@Override
	public void addSharingEntry(long classNameId, long classPK,
		long sharingClassNameId, long sharingClassPK) {
		_sharingEntryLocalService.addSharingEntry(classNameId, classPK,
			sharingClassNameId, sharingClassPK);
	}

	/**
	* Adds the sharing entry to the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was added
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry addSharingEntry(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		return _sharingEntryLocalService.addSharingEntry(sharingEntry);
	}

	@Override
	public int countEntriesByUserId(long userId, long[] classNameIds,
		java.util.Map<java.lang.Long, long[]> scopes) {
		return _sharingEntryLocalService.countEntriesByUserId(userId,
			classNameIds, scopes);
	}

	@Override
	public int countSharingEntriesByScope(long classNameId,
		long sharingClassNameId, long sharingClassPK) {
		return _sharingEntryLocalService.countSharingEntriesByScope(classNameId,
			sharingClassNameId, sharingClassPK);
	}

	@Override
	public int countSharingEntriesByScope(long sharingClassNameId,
		long sharingClassPK) {
		return _sharingEntryLocalService.countSharingEntriesByScope(sharingClassNameId,
			sharingClassPK);
	}

	/**
	* Creates a new sharing entry with the primary key. Does not add the sharing entry to the database.
	*
	* @param sharingEntryPK the primary key for the new sharing entry
	* @return the new sharing entry
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry createSharingEntry(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK) {
		return _sharingEntryLocalService.createSharingEntry(sharingEntryPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sharingEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteSharingEntries(long classNameId, long classPK) {
		_sharingEntryLocalService.deleteSharingEntries(classNameId, classPK);
	}

	/**
	* Deletes the sharing entry from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was removed
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry deleteSharingEntry(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		return _sharingEntryLocalService.deleteSharingEntry(sharingEntry);
	}

	/**
	* Deletes the sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry that was removed
	* @throws PortalException if a sharing entry with the primary key could not be found
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry deleteSharingEntry(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sharingEntryLocalService.deleteSharingEntry(sharingEntryPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _sharingEntryLocalService.dynamicQuery();
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
		return _sharingEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sharingEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _sharingEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _sharingEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _sharingEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.sharing.model.SharingEntry fetchSharingEntry(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK) {
		return _sharingEntryLocalService.fetchSharingEntry(sharingEntryPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _sharingEntryLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _sharingEntryLocalService.getBeanIdentifier();
	}

	@Override
	public java.util.List<java.lang.Object[]> getEntriesByUserId(long userId,
		long[] classNameIds, java.util.Map<java.lang.Long, long[]> scopes,
		int start, int end) {
		return _sharingEntryLocalService.getEntriesByUserId(userId,
			classNameIds, scopes, start, end);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sharingEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<com.liferay.sharing.model.SharingEntry> getSharingEntries(
		long classNameId, long classPK) {
		return _sharingEntryLocalService.getSharingEntries(classNameId, classPK);
	}

	@Override
	public java.util.List<com.liferay.sharing.model.SharingEntry> getSharingEntries(
		long classNameId, long classPK, long sharingClassNameId) {
		return _sharingEntryLocalService.getSharingEntries(classNameId,
			classPK, sharingClassNameId);
	}

	/**
	* Returns a range of all the sharing entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sharing.model.impl.SharingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sharing entries
	* @param end the upper bound of the range of sharing entries (not inclusive)
	* @return the range of sharing entries
	*/
	@Override
	public java.util.List<com.liferay.sharing.model.SharingEntry> getSharingEntries(
		int start, int end) {
		return _sharingEntryLocalService.getSharingEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.sharing.model.SharingEntry> getSharingEntriesByScope(
		long classNameId, long sharingClassNameId, long sharingClassPK,
		int start, int end) {
		return _sharingEntryLocalService.getSharingEntriesByScope(classNameId,
			sharingClassNameId, sharingClassPK, start, end);
	}

	@Override
	public java.util.List<com.liferay.sharing.model.SharingEntry> getSharingEntriesByScope(
		long sharingClassNameId, long sharingClassPK, int start, int end) {
		return _sharingEntryLocalService.getSharingEntriesByScope(sharingClassNameId,
			sharingClassPK, start, end);
	}

	/**
	* Returns the number of sharing entries.
	*
	* @return the number of sharing entries
	*/
	@Override
	public int getSharingEntriesCount() {
		return _sharingEntryLocalService.getSharingEntriesCount();
	}

	/**
	* Returns the sharing entry with the primary key.
	*
	* @param sharingEntryPK the primary key of the sharing entry
	* @return the sharing entry
	* @throws PortalException if a sharing entry with the primary key could not be found
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry getSharingEntry(
		com.liferay.sharing.service.persistence.SharingEntryPK sharingEntryPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _sharingEntryLocalService.getSharingEntry(sharingEntryPK);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sharingEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sharingEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the sharing entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param sharingEntry the sharing entry
	* @return the sharing entry that was updated
	*/
	@Override
	public com.liferay.sharing.model.SharingEntry updateSharingEntry(
		com.liferay.sharing.model.SharingEntry sharingEntry) {
		return _sharingEntryLocalService.updateSharingEntry(sharingEntry);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public SharingEntryLocalService getWrappedSharingEntryLocalService() {
		return _sharingEntryLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedSharingEntryLocalService(
		SharingEntryLocalService sharingEntryLocalService) {
		_sharingEntryLocalService = sharingEntryLocalService;
	}

	@Override
	public SharingEntryLocalService getWrappedService() {
		return _sharingEntryLocalService;
	}

	@Override
	public void setWrappedService(
		SharingEntryLocalService sharingEntryLocalService) {
		_sharingEntryLocalService = sharingEntryLocalService;
	}

	private SharingEntryLocalService _sharingEntryLocalService;
}