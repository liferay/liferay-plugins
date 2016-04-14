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

package com.liferay.socialcoding.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SVNRepositoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepositoryLocalService
 * @generated
 */
@ProviderType
public class SVNRepositoryLocalServiceWrapper
	implements SVNRepositoryLocalService,
		ServiceWrapper<SVNRepositoryLocalService> {
	public SVNRepositoryLocalServiceWrapper(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		_svnRepositoryLocalService = svnRepositoryLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _svnRepositoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _svnRepositoryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _svnRepositoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRepositoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRepositoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the s v n repository to the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepository the s v n repository
	* @return the s v n repository that was added
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository addSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepositoryLocalService.addSVNRepository(svnRepository);
	}

	/**
	* Creates a new s v n repository with the primary key. Does not add the s v n repository to the database.
	*
	* @param svnRepositoryId the primary key for the new s v n repository
	* @return the new s v n repository
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository createSVNRepository(
		long svnRepositoryId) {
		return _svnRepositoryLocalService.createSVNRepository(svnRepositoryId);
	}

	/**
	* Deletes the s v n repository from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepository the s v n repository
	* @return the s v n repository that was removed
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository deleteSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepositoryLocalService.deleteSVNRepository(svnRepository);
	}

	/**
	* Deletes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository that was removed
	* @throws PortalException if a s v n repository with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository deleteSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRepositoryLocalService.deleteSVNRepository(svnRepositoryId);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRepository fetchSVNRepository(
		long svnRepositoryId) {
		return _svnRepositoryLocalService.fetchSVNRepository(svnRepositoryId);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRepositoryLocalService.getSVNRepository(url);
	}

	/**
	* Returns the s v n repository with the primary key.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository
	* @throws PortalException if a s v n repository with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository getSVNRepository(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRepositoryLocalService.getSVNRepository(svnRepositoryId);
	}

	/**
	* Updates the s v n repository in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param svnRepository the s v n repository
	* @return the s v n repository that was updated
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRepository updateSVNRepository(
		com.liferay.socialcoding.model.SVNRepository svnRepository) {
		return _svnRepositoryLocalService.updateSVNRepository(svnRepository);
	}

	/**
	* Returns the number of s v n repositories.
	*
	* @return the number of s v n repositories
	*/
	@Override
	public int getSVNRepositoriesCount() {
		return _svnRepositoryLocalService.getSVNRepositoriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _svnRepositoryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _svnRepositoryLocalService.getOSGiServiceIdentifier();
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
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _svnRepositoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @return the range of s v n repositories
	*/
	@Override
	public java.util.List<com.liferay.socialcoding.model.SVNRepository> getSVNRepositories(
		int start, int end) {
		return _svnRepositoryLocalService.getSVNRepositories(start, end);
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
		return _svnRepositoryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _svnRepositoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void updateSVNRepository(java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException {
		_svnRepositoryLocalService.updateSVNRepository(url);
	}

	@Override
	public SVNRepositoryLocalService getWrappedService() {
		return _svnRepositoryLocalService;
	}

	@Override
	public void setWrappedService(
		SVNRepositoryLocalService svnRepositoryLocalService) {
		_svnRepositoryLocalService = svnRepositoryLocalService;
	}

	private SVNRepositoryLocalService _svnRepositoryLocalService;
}