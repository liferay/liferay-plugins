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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KBComment. This utility wraps
 * {@link com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentLocalService
 * @see com.liferay.knowledgebase.service.base.KBCommentLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl
 * @generated
 */
@ProviderType
public class KBCommentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.KBCommentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the k b comment to the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was added
	*/
	public static com.liferay.knowledgebase.model.KBComment addKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment) {
		return getService().addKBComment(kbComment);
	}

	public static com.liferay.knowledgebase.model.KBComment addKBComment(
		long userId, long classNameId, long classPK, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKBComment(userId, classNameId, classPK, content,
			serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBComment addKBComment(
		long userId, long classNameId, long classPK, java.lang.String content,
		int userRating, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addKBComment(userId, classNameId, classPK, content,
			userRating, serviceContext);
	}

	/**
	* Creates a new k b comment with the primary key. Does not add the k b comment to the database.
	*
	* @param kbCommentId the primary key for the new k b comment
	* @return the new k b comment
	*/
	public static com.liferay.knowledgebase.model.KBComment createKBComment(
		long kbCommentId) {
		return getService().createKBComment(kbCommentId);
	}

	/**
	* Deletes the k b comment from the database. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was removed
	* @throws PortalException
	*/
	public static com.liferay.knowledgebase.model.KBComment deleteKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKBComment(kbComment);
	}

	/**
	* Deletes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment that was removed
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	public static com.liferay.knowledgebase.model.KBComment deleteKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteKBComment(kbCommentId);
	}

	public static void deleteKBComments(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteKBComments(className, classPK);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.knowledgebase.model.KBComment fetchKBComment(
		long kbCommentId) {
		return getService().fetchKBComment(kbCommentId);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	*/
	public static com.liferay.knowledgebase.model.KBComment fetchKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchKBCommentByUuidAndGroupId(uuid, groupId);
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

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	* Returns the k b comment with the primary key.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws PortalException if a k b comment with the primary key could not be found
	*/
	public static com.liferay.knowledgebase.model.KBComment getKBComment(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBComment(kbCommentId);
	}

	public static com.liferay.knowledgebase.model.KBComment getKBComment(
		long userId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBComment(userId, className, classPK);
	}

	/**
	* Returns the k b comment matching the UUID and group.
	*
	* @param uuid the k b comment's UUID
	* @param groupId the primary key of the group
	* @return the matching k b comment
	* @throws PortalException if a matching k b comment could not be found
	*/
	public static com.liferay.knowledgebase.model.KBComment getKBCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getKBCommentByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator) {
		return getService()
				   .getKBComments(className, classPK, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int[] status, int start,
		int end) {
		return getService().getKBComments(className, classPK, status, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		java.lang.String className, long classPK, int status, int start, int end) {
		return getService().getKBComments(className, classPK, status, start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long groupId, int status, int start, int end) {
		return getService().getKBComments(groupId, status, start, end);
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
	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		int start, int end) {
		return getService().getKBComments(start, end);
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		long userId, java.lang.String className, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator) {
		return getService()
				   .getKBComments(userId, className, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns all the k b comments matching the UUID and company.
	*
	* @param uuid the UUID of the k b comments
	* @param companyId the primary key of the company
	* @return the matching k b comments, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getKBCommentsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBCommentsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBComment> orderByComparator) {
		return getService()
				   .getKBCommentsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of k b comments.
	*
	* @return the number of k b comments
	*/
	public static int getKBCommentsCount() {
		return getService().getKBCommentsCount();
	}

	public static int getKBCommentsCount(java.lang.String className,
		long classPK) {
		return getService().getKBCommentsCount(className, classPK);
	}

	public static int getKBCommentsCount(java.lang.String className,
		long classPK, int status) {
		return getService().getKBCommentsCount(className, classPK, status);
	}

	public static int getKBCommentsCount(java.lang.String className,
		long classPK, int[] status) {
		return getService().getKBCommentsCount(className, classPK, status);
	}

	public static int getKBCommentsCount(long groupId, int status) {
		return getService().getKBCommentsCount(groupId, status);
	}

	public static int getKBCommentsCount(long userId,
		java.lang.String className, long classPK) {
		return getService().getKBCommentsCount(userId, className, classPK);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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
	* Updates the k b comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kbComment the k b comment
	* @return the k b comment that was updated
	*/
	public static com.liferay.knowledgebase.model.KBComment updateKBComment(
		com.liferay.knowledgebase.model.KBComment kbComment) {
		return getService().updateKBComment(kbComment);
	}

	public static com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateKBComment(kbCommentId, classNameId, classPK, content,
			status, serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBComment updateKBComment(
		long kbCommentId, long classNameId, long classPK,
		java.lang.String content, int userRating, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateKBComment(kbCommentId, classNameId, classPK, content,
			userRating, status, serviceContext);
	}

	public static com.liferay.knowledgebase.model.KBComment updateStatus(
		long userId, long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, kbCommentId, status, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static KBCommentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KBCommentLocalService.class.getName());

			if (invokableLocalService instanceof KBCommentLocalService) {
				_service = (KBCommentLocalService)invokableLocalService;
			}
			else {
				_service = new KBCommentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KBCommentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(KBCommentLocalService service) {
	}

	private static KBCommentLocalService _service;
}