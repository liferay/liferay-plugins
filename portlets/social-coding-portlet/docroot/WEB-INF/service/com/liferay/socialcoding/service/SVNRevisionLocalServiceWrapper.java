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

package com.liferay.socialcoding.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SVNRevisionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevisionLocalService
 * @generated
 */
public class SVNRevisionLocalServiceWrapper implements SVNRevisionLocalService,
	ServiceWrapper<SVNRevisionLocalService> {
	public SVNRevisionLocalServiceWrapper(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	/**
	* Adds the s v n revision to the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnRevision);
	}

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	public com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return _svnRevisionLocalService.createSVNRevision(svnRevisionId);
	}

	/**
	* Deletes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @throws PortalException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSVNRevision(long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevisionId);
	}

	/**
	* Deletes the s v n revision from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		_svnRevisionLocalService.deleteSVNRevision(svnRevision);
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery);
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _svnRevisionLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _svnRevisionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.socialcoding.model.SVNRevision fetchSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.fetchSVNRevision(svnRevisionId);
	}

	/**
	* Returns the s v n revision with the primary key.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws PortalException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevision(svnRevisionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(start, end);
	}

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public int getSVNRevisionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount();
	}

	/**
	* Updates the s v n revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision);
	}

	/**
	* Updates the s v n revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @param merge whether to merge the s v n revision with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the s v n revision that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.updateSVNRevision(svnRevision, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _svnRevisionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_svnRevisionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.addSVNRevision(svnUserId, createDate,
			svnRepositoryId, revisionNumber, comments);
	}

	public com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getFirstSVNRevision(svnUserId);
	}

	public com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getLastSVNRevision(svnUserId);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId, start, end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnRepositoryId, start,
			end);
	}

	public java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisions(svnUserId,
			svnRepositoryId, start, end);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId);
	}

	public int getSVNRevisionsCount(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnRepositoryId);
	}

	public int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _svnRevisionLocalService.getSVNRevisionsCount(svnUserId,
			svnRepositoryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SVNRevisionLocalService getWrappedSVNRevisionLocalService() {
		return _svnRevisionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSVNRevisionLocalService(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	public SVNRevisionLocalService getWrappedService() {
		return _svnRevisionLocalService;
	}

	public void setWrappedService(
		SVNRevisionLocalService svnRevisionLocalService) {
		_svnRevisionLocalService = svnRevisionLocalService;
	}

	private SVNRevisionLocalService _svnRevisionLocalService;
}