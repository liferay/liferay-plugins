/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link CommentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CommentLocalService
 * @generated
 */
public class CommentLocalServiceWrapper implements CommentLocalService {
	public CommentLocalServiceWrapper(CommentLocalService commentLocalService) {
		_commentLocalService = commentLocalService;
	}

	/**
	* Adds the comment to the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to add
	* @return the comment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment addComment(
		com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.addComment(comment);
	}

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	public com.liferay.knowledgebase.model.Comment createComment(long commentId) {
		return _commentLocalService.createComment(commentId);
	}

	/**
	* Deletes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment to delete
	* @throws PortalException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_commentLocalService.deleteComment(commentId);
	}

	/**
	* Deletes the comment from the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to delete
	* @throws SystemException if a system exception occurred
	*/
	public void deleteComment(com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		_commentLocalService.deleteComment(comment);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the comment with the primary key.
	*
	* @param commentId the primary key of the comment to get
	* @return the comment
	* @throws PortalException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment getComment(long commentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getComment(commentId);
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
	public com.liferay.knowledgebase.model.Comment getCommentByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getCommentByUuidAndGroupId(uuid, groupId);
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
	public java.util.List<com.liferay.knowledgebase.model.Comment> getComments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getComments(start, end);
	}

	/**
	* Gets the number of comments.
	*
	* @return the number of comments
	* @throws SystemException if a system exception occurred
	*/
	public int getCommentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getCommentsCount();
	}

	/**
	* Updates the comment in the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to update
	* @return the comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment updateComment(
		com.liferay.knowledgebase.model.Comment comment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.updateComment(comment);
	}

	/**
	* Updates the comment in the database. Also notifies the appropriate model listeners.
	*
	* @param comment the comment to update
	* @param merge whether to merge the comment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the comment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment updateComment(
		com.liferay.knowledgebase.model.Comment comment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.updateComment(comment, merge);
	}

	public com.liferay.knowledgebase.model.Comment addComment(long userId,
		long classNameId, long classPK, java.lang.String content,
		boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.addComment(userId, classNameId, classPK,
			content, helpful, serviceContext);
	}

	public com.liferay.knowledgebase.model.Comment getComment(long userId,
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getComment(userId, className, classPK);
	}

	public java.util.List<com.liferay.knowledgebase.model.Comment> getComments(
		java.lang.String className, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getComments(className, classPK, start, end,
			orderByComparator);
	}

	public int getCommentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.getCommentsCount(className, classPK);
	}

	public com.liferay.knowledgebase.model.Comment updateComment(
		long commentId, long classNameId, long classPK,
		java.lang.String content, boolean helpful,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _commentLocalService.updateComment(commentId, classNameId,
			classPK, content, helpful, serviceContext);
	}

	public CommentLocalService getWrappedCommentLocalService() {
		return _commentLocalService;
	}

	public void setWrappedCommentLocalService(
		CommentLocalService commentLocalService) {
		_commentLocalService = commentLocalService;
	}

	private CommentLocalService _commentLocalService;
}