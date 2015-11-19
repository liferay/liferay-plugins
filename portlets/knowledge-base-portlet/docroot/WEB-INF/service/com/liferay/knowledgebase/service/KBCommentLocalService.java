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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for KBComment. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentLocalServiceUtil
 * @see com.liferay.knowledgebase.service.base.KBCommentLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface KBCommentLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBCommentLocalServiceUtil} to access the k b comment local service. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the k b comment to the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was added
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.knowledgebase.model.KBComment addKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment);

	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	public com.liferay.knowledgebase.model.KBComment addKBComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		int userRating, com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	/**
	* Creates a new k b comment with the primary key. Does not add the k b comment to the database.
	*
	* @param kbCommentId the primary key for the new k b comment
	* @return the new k b comment
	*/
	public com.liferay.knowledgebase.model.KBComment createKBComment(
		long kbCommentId);

	/**
	* Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was removed
	* @throws PortalException
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	@com.liferay.portal.kernel.systemevent.SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws PortalException;

	/**
	* Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment that was removed
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.DELETE)
	public com.liferay.knowledgebase.model.KBComment deleteKBComment(
		long kbCommentId) throws PortalException;

	public void deleteKBComments(java.lang.String className, long classPK)
		throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws PortalException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

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
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBComment fetchKBComment(
		long kbCommentId);

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBComment fetchKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the k b comment with the primary key.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBComment getKBComment(long userId,
		java.lang.String className, long classPK) throws PortalException;

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment
	* @throws PortalException if a matching k b comment could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.knowledgebase.model.KBComment getKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int[] status, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int status, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, int status, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long userId, java.lang.String className, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator);

	/**
	* Returns all the k b comments matching the UUID and company.
	*
	* @param uuid the UUID of the k b comments
	* @param companyId the primary key of the company
	* @return the matching k b comments, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator);

	/**
	* Returns the number of k b comments.
	*
	* @return the number of k b comments
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount(java.lang.String className, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount(java.lang.String className, long classPK,
		int[] status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount(long groupId, int status);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getKBCommentsCount(long userId, java.lang.String className,
		long classPK);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was updated
	*/
	@com.liferay.portal.kernel.search.Indexable(type = IndexableType.REINDEX)
	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment);

	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	public com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int userRating, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;

	public com.liferay.knowledgebase.model.KBComment updateStatus(long userId,
		long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws PortalException;
}