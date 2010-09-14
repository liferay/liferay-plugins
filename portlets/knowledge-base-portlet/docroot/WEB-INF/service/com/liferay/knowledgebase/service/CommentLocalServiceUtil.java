/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * The utility for the comment local service. This utility wraps {@link com.liferay.knowledgebase.service.impl.CommentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * Never modify this class directly. Add custom service methods to {@link com.liferay.knowledgebase.service.impl.CommentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommentLocalService
 * @see com.liferay.knowledgebase.service.base.CommentLocalServiceBaseImpl
 * @see com.liferay.knowledgebase.service.impl.CommentLocalServiceImpl
 * @generated
 */
public class CommentLocalServiceUtil {
	/**
	* Adds the comment to the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to add
	* @return the comment that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Comment addComment(
		com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addComment(comment);
	}

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	public static com.liferay.knowledgebase.model.Comment createComment(
		long commentId) {
		return getService().createComment(commentId);
	}

	/**
	* Deletes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment to delete
	* @throws PortalException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteComment(commentId);
	}

	/**
	* Deletes the comment from the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to delete
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteComment(
		com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteComment(comment);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the comment with the primary key.
	*
	* @param commentId the primary key of the comment to get
	* @return the comment
	* @throws PortalException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Comment getComment(
		long commentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getComment(commentId);
	}

	/**
	* Gets the comment with the UUID and group id.
	*
	* @param uuid the UUID of comment to get
	* @param groupId the group id of the comment to get
	* @return the comment
	* @throws PortalException if a comment with the UUID and group id could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Comment getCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCommentByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Gets a range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @return the range of comments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.knowledgebase.model.Comment> getComments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getComments(start, end);
	}

	/**
	* Gets the number of comments.
	*
	* @return the number of comments
	* @throws SystemException if a system exception occurred
	*/
	public static int getCommentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCommentsCount();
	}

	/**
	* Updates the comment in the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to update
	* @return the comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Comment updateComment(
		com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateComment(comment);
	}

	/**
	* Updates the comment in the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to update
	* @param merge whether to merge the comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.knowledgebase.model.Comment updateComment(
		com.liferay.knowledgebase.model.Comment comment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateComment(comment, merge);
	}

	public static com.liferay.knowledgebase.model.Comment addComment(
		long userId, long classNameId, long classPK, java.lang.String content,
		boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addComment(userId, classNameId, classPK, content, helpful,
			serviceContext);
	}

	public static com.liferay.knowledgebase.model.Comment getComment(
		long userId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getComment(userId, className, classPK);
	}

	public static java.util.List<com.liferay.knowledgebase.model.Comment> getComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getComments(className, classPK, start, end,
			orderByComparator);
	}

	public static int getCommentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCommentsCount(className, classPK);
	}

	public static com.liferay.knowledgebase.model.Comment updateComment(
		long commentId, long classNameId, long classPK,
		java.lang.String content, boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateComment(commentId, classNameId, classPK, content,
			helpful, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static CommentLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					CommentLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					CommentLocalService.class.getName(), portletClassLoader);

			_service = new CommentLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(CommentLocalService service) {
		_service = service;
	}

	private static CommentLocalService _service;
}