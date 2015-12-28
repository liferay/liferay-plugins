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

package com.liferay.knowledgebase.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBCommentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentLocalService
 * @generated
 */
@ProviderType
public class KBCommentLocalServiceWrapper implements KBCommentLocalService,
	ServiceWrapper<KBCommentLocalService> {
	public KBCommentLocalServiceWrapper(
		KBCommentLocalService kbCommentLocalService) {
		_kbCommentLocalService = kbCommentLocalService;
	}

	/**
	* Adds the k b comment to the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was added
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment) {
		return _kbCommentLocalService.addKBComment(kbComment);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.addKBComment(userId, classNameId,
			classPK, content, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		int userRating, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.addKBComment(userId, classNameId,
			classPK, content, userRating, serviceContext);
	}

	/**
	* Creates a new k b comment with the primary key. Does not add the k b comment to the database.
	*
	* @param kbCommentId the primary key for the new k b comment
	* @return the new k b comment
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment createKBComment(
		long kbCommentId) {
		return _kbCommentLocalService.createKBComment(kbCommentId);
	}

	/**
	* Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.deleteKBComment(kbComment);
	}

	/**
	* Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment that was removed
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.deleteKBComment(kbCommentId);
	}

	@Override
	public void deleteKBComments(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		_kbCommentLocalService.deleteKBComments(className, classPK);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kbCommentLocalService.dynamicQuery();
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
		return _kbCommentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kbCommentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kbCommentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _kbCommentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _kbCommentLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment fetchKBComment(
		long kbCommentId) {
		return _kbCommentLocalService.fetchKBComment(kbCommentId);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment fetchKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _kbCommentLocalService.fetchKBCommentByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _kbCommentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return _kbCommentLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _kbCommentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the k b comment with the primary key.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.getKBComment(kbCommentId);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment getKBComment(long userId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.getKBComment(userId, className, classPK);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment
	* @throws PortalException if a matching k b comment could not be found
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment getKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.getKBCommentByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return _kbCommentLocalService.getKBComments(className, classPK, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int[] status, int start,
		int end) {
		return _kbCommentLocalService.getKBComments(className, classPK, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int status, int start, int end) {
		return _kbCommentLocalService.getKBComments(className, classPK, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, int status, int start, int end) {
		return _kbCommentLocalService.getKBComments(groupId, status, start, end);
	}

	/**
	* Returns a range of all the k b comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of k b comments
	*/
	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		int start, int end) {
		return _kbCommentLocalService.getKBComments(start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long userId, java.lang.String className, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator) {
		return _kbCommentLocalService.getKBComments(userId, className, classPK,
			start, end, orderByComparator);
	}

	/**
	* Returns all the k b comments matching the UUID and company.
	*
	* @param uuid the UUID of the k b comments
	* @param companyId the primary key of the company
	* @return the matching k b comments, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _kbCommentLocalService.getKBCommentsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of k b comments matching the UUID and company.
	*
	* @param uuid the UUID of the k b comments
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching k b comments, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator) {
		return _kbCommentLocalService.getKBCommentsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of k b comments.
	*
	* @return the number of k b comments
	*/
	@Override
	public int getKBCommentsCount() {
		return _kbCommentLocalService.getKBCommentsCount();
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK) {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK);
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int status) {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK,
			status);
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int[] status) {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK,
			status);
	}

	@Override
	public int getKBCommentsCount(long groupId, int status) {
		return _kbCommentLocalService.getKBCommentsCount(groupId, status);
	}

	@Override
	public int getKBCommentsCount(long userId, java.lang.String className,
		long classPK) {
		return _kbCommentLocalService.getKBCommentsCount(userId, className,
			classPK);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _kbCommentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbCommentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was updated
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment) {
		return _kbCommentLocalService.updateKBComment(kbComment);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.updateKBComment(kbCommentId, classNameId,
			classPK, content, status, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int userRating, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.updateKBComment(kbCommentId, classNameId,
			classPK, content, userRating, status, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateStatus(long userId,
		long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _kbCommentLocalService.updateStatus(userId, kbCommentId, status,
			serviceContext);
	}

	@Override
	public KBCommentLocalService getWrappedService() {
		return _kbCommentLocalService;
	}

	@Override
	public void setWrappedService(KBCommentLocalService kbCommentLocalService) {
		_kbCommentLocalService = kbCommentLocalService;
	}

	private KBCommentLocalService _kbCommentLocalService;
}