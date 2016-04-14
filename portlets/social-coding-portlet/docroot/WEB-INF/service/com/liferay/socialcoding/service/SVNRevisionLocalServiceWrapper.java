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
 * Provides a wrapper for {@link SVNRevisionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionLocalService
 * @generated
 */
@ProviderType
public class SVNRevisionLocalServiceWrapper implements SVNRevisionLocalService,
	ServiceWrapper<SVNRevisionLocalService> {
	public SVNRevisionLocalServiceWrapper(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _svnRevisionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _svnRevisionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _svnRevisionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the s v n revision to the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was added
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevisionLocalService.addSVNRevision(svnRevision);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.addSVNRevision(svnUserId, createDate,
			svnRepositoryId, revisionNumber, comments);
	}

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return _svnRevisionLocalService.createSVNRevision(svnRevisionId);
	}

	/**
	* Deletes the s v n revision from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was removed
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevisionLocalService.deleteSVNRevision(svnRevision);
	}

	/**
	* Deletes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision that was removed
	* @throws PortalException if a s v n revision with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision deleteSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.deleteSVNRevision(svnRevisionId);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision fetchSVNRevision(
		long svnRevisionId) {
		return _svnRevisionLocalService.fetchSVNRevision(svnRevisionId);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.getFirstSVNRevision(svnUserId);
	}

	@Override
	public com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.getLastSVNRevision(svnUserId);
	}

	/**
	* Returns the s v n revision with the primary key.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws PortalException if a s v n revision with the primary key could not be found
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _svnRevisionLocalService.getSVNRevision(svnRevisionId);
	}

	/**
	* Updates the s v n revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was updated
	*/
	@Override
	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision);
	}

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	*/
	@Override
	public int getSVNRevisionsCount() {
		return _svnRevisionLocalService.getSVNRevisionsCount();
	}

	@Override
	public int getSVNRevisionsCount(java.lang.String svnUserId) {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId);
	}

	@Override
	public int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId) {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId,
			svnRepositoryId);
	}

	@Override
	public int getSVNRevisionsCount(long svnRepositoryId) {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnRepositoryId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _svnRevisionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _svnRevisionLocalService.getOSGiServiceIdentifier();
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.socialcoding.model.impl.SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of s v n revisions
	*/
	@Override
	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end) {
		return _svnRevisionLocalService.getSVNRevisions(start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end) {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end) {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId,
			svnRepositoryId, start, end);
	}

	@Override
	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end) {
		return _svnRevisionLocalService.getSVNRevisions(svnRepositoryId, start,
			end);
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
		return _svnRevisionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _svnRevisionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public SVNRevisionLocalService getWrappedService() {
		return _svnRevisionLocalService;
	}

	@Override
	public void setWrappedService(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	private SVNRevisionLocalService _svnRevisionLocalService;
}