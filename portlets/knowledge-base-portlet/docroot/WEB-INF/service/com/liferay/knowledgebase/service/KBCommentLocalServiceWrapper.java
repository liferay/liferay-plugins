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

package com.liferay.knowledgebase.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KBCommentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBCommentLocalService
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
	public com.liferay.knowledgebase.model.KBComment createKBComment(
		long kbCommentId) {
		return _kbCommentLocalService.createKBComment(kbCommentId);
	}

	/**
	* Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBComment(long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbCommentLocalService.deleteKBComment(kbCommentId);
	}

	/**
	* Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @throws SystemException if a system exception occurred
	*/
	public void deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kbCommentLocalService.deleteKBComment(kbComment);
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
		return _kbCommentLocalService.dynamicQuery(dynamicQuery);
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
		return _kbCommentLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.knowledgebase.model.KBComment fetchKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.fetchKBComment(kbCommentId);
	}

	/**
	* Returns the k b comment with the primary key.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComment(kbCommentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the k b comment with the UUID in the group.
	*
	* @param uuid the UUID of k b comment
	* @param groupId the group id of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of k b comments
	* @throws SystemException if a system exception occurred
	*/
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
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbComment);
	}

	/**
	* Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @param merge whether to merge the k b comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the k b comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbComment, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kbCommentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kbCommentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.addKBComment(userId, classNameId,
			classPK, content, helpful, serviceContext);
	}

	public void deleteKBComments(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kbCommentLocalService.deleteKBComments(className, classPK);
	}

	public com.liferay.knowledgebase.model.KBComment getKBComment(long userId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComment(userId, className, classPK);
	}

	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBComments(className, classPK, start,
			end, orderByComparator);
	}

	public int getKBCommentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.getKBCommentsCount(className, classPK);
	}

	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbCommentLocalService.updateKBComment(kbCommentId, classNameId,
			classPK, content, helpful, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KBCommentLocalService getWrappedKBCommentLocalService() {
		return _kbCommentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKBCommentLocalService(
		KBCommentLocalService kbCommentLocalService) {
		_kbCommentLocalService = kbCommentLocalService;
	}

	public KBCommentLocalService getWrappedService() {
		return _kbCommentLocalService;
	}

	public void setWrappedService(KBCommentLocalService kbCommentLocalService) {
		_kbCommentLocalService = kbCommentLocalService;
	}

	private KBCommentLocalService _kbCommentLocalService;
}