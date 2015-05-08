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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SVNRevision. This utility wraps
 * {@link com.liferay.socialcoding.service.impl.SVNRevisionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionLocalService
 * @see com.liferay.socialcoding.service.base.SVNRevisionLocalServiceBaseImpl
 * @see com.liferay.socialcoding.service.impl.SVNRevisionLocalServiceImpl
 * @generated
 */
@ProviderType
public class SVNRevisionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.socialcoding.service.impl.SVNRevisionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the s v n revision to the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was added
	*/
	public static com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return getService().addSVNRevision(svnRevision);
	}

	public static com.liferay.socialcoding.model.SVNRevision addSVNRevision(
		java.lang.String svnUserId, java.util.Date createDate,
		long svnRepositoryId, long revisionNumber, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSVNRevision(svnUserId, createDate, svnRepositoryId,
			revisionNumber, comments);
	}

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	public static com.liferay.socialcoding.model.SVNRevision createSVNRevision(
		long svnRevisionId) {
		return getService().createSVNRevision(svnRevisionId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the s v n revision from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was removed
	*/
	public static com.liferay.socialcoding.model.SVNRevision deleteSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return getService().deleteSVNRevision(svnRevision);
	}

	/**
	* Deletes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision that was removed
	* @throws PortalException if a s v n revision with the primary key could not be found
	*/
	public static com.liferay.socialcoding.model.SVNRevision deleteSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSVNRevision(svnRevisionId);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.socialcoding.model.SVNRevision fetchSVNRevision(
		long svnRevisionId) {
		return getService().fetchSVNRevision(svnRevisionId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.socialcoding.model.SVNRevision getFirstSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFirstSVNRevision(svnUserId);
	}

	public static com.liferay.socialcoding.model.SVNRevision getLastSVNRevision(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLastSVNRevision(svnUserId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the s v n revision with the primary key.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws PortalException if a s v n revision with the primary key could not be found
	*/
	public static com.liferay.socialcoding.model.SVNRevision getSVNRevision(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSVNRevision(svnRevisionId);
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
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		int start, int end) {
		return getService().getSVNRevisions(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		long svnRepositoryId, int start, int end) {
		return getService().getSVNRevisions(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, int start, int end) {
		return getService().getSVNRevisions(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> getSVNRevisions(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end) {
		return getService()
				   .getSVNRevisions(svnUserId, svnRepositoryId, start, end);
	}

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	*/
	public static int getSVNRevisionsCount() {
		return getService().getSVNRevisionsCount();
	}

	public static int getSVNRevisionsCount(long svnRepositoryId) {
		return getService().getSVNRevisionsCount(svnRepositoryId);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId) {
		return getService().getSVNRevisionsCount(svnUserId);
	}

	public static int getSVNRevisionsCount(java.lang.String svnUserId,
		long svnRepositoryId) {
		return getService().getSVNRevisionsCount(svnUserId, svnRepositoryId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the s v n revision in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param svnRevision the s v n revision
	* @return the s v n revision that was updated
	*/
	public static com.liferay.socialcoding.model.SVNRevision updateSVNRevision(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		return getService().updateSVNRevision(svnRevision);
	}

	public static void clearService() {
		_service = null;
	}

	public static SVNRevisionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SVNRevisionLocalService.class.getName());

			if (invokableLocalService instanceof SVNRevisionLocalService) {
				_service = (SVNRevisionLocalService)invokableLocalService;
			}
			else {
				_service = new SVNRevisionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SVNRevisionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(SVNRevisionLocalService service) {
	}

	private static SVNRevisionLocalService _service;
}