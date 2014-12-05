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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KBCommentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.addKBComment(kbComment);
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
	* Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment that was removed
	* @throws PortalException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.deleteKBComment(kbCommentId);
	}

	/**
	* Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.deleteKBComment(kbComment);
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _kbCommentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _kbCommentLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment fetchKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.fetchKBComment(kbCommentId);
	}

	/**
	* Returns the k b comment with the matching UUID and company.
	*
	* @param uuid the k b comment's UUID
	* @param companyId the primary key of the company
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment fetchKBCommentByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.fetchKBCommentByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment fetchKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.fetchKBCommentByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the k b comment with the primary key.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComment(kbCommentId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the k b comment with the matching UUID and company.
	*
	* @param uuid the k b comment's UUID
	* @param companyId the primary key of the company
	* @return the matching k b comment
	* @throws PortalException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment getKBCommentByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment
	* @throws PortalException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment getKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentByUuidAndGroupId(uuid, groupId);
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
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(start, end);
	}

	/**
	* Returns the number of k b comments.
	*
	* @return the number of k b comments
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKBCommentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount();
	}

	/**
	* Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbComment);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kbCommentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbCommentLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kbCommentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		int userRating, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.addKBComment(userId, classNameId,
			classPK, content, userRating, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.addKBComment(userId, classNameId,
			classPK, content, serviceContext);
	}

	@Override
	public void deleteKBComments(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbCommentLocalService.deleteKBComments(className, classPK);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment getKBComment(long userId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComment(userId, className, classPK);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(groupId, status, start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long userId, java.lang.String className, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(userId, className, classPK,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(className, classPK, status,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(className, classPK, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int[] status, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(className, classPK, status,
			start, end);
	}

	@Override
	public int getKBCommentsCount(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(groupId, status);
	}

	@Override
	public int getKBCommentsCount(long userId, java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(userId, className,
			classPK);
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK);
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK,
			status);
	}

	@Override
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int[] status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK,
			status);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int userRating, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbCommentId, classNameId,
			classPK, content, userRating, status, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbCommentId, classNameId,
			classPK, content, status, serviceContext);
	}

	@Override
	public com.liferay.knowledgebase.model.KBComment updateStatus(
		long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateStatus(kbCommentId, status,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KBCommentLocalService getWrappedKBCommentLocalService() {
		return _kbCommentLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKBCommentLocalService(
		KBCommentLocalService kbCommentLocalService) {
		_kbCommentLocalService = kbCommentLocalService;
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