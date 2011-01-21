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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.model.Comment;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CommentPersistenceImpl
 * @see CommentUtil
 * @generated
 */
public interface CommentPersistence extends BasePersistence<Comment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommentUtil} to access the comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the comment in the entity cache if it is enabled.
	*
	* @param comment the comment to cache
	*/
	public void cacheResult(com.liferay.knowledgebase.model.Comment comment);

	/**
	* Caches the comments in the entity cache if it is enabled.
	*
	* @param comments the comments to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.Comment> comments);

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	public com.liferay.knowledgebase.model.Comment create(long commentId);

	/**
	* Removes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment to remove
	* @return the comment that was removed
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment remove(long commentId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.Comment updateImpl(
		com.liferay.knowledgebase.model.Comment comment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchCommentException} if it could not be found.
	*
	* @param commentId the primary key of the comment to find
	* @return the comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByPrimaryKey(
		long commentId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commentId the primary key of the comment to find
	* @return the comment, or <code>null</code> if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment fetchByPrimaryKey(
		long commentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the comments where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @return the range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first comment in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last comment in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comments before and after the current comment in the ordered set where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param commentId the primary key of the current comment
	* @param uuid the uuid to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment[] findByUuid_PrevAndNext(
		long commentId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchCommentException} if it could not be found.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the comments where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @return the range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first comment in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last comment in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comments before and after the current comment in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param commentId the primary key of the current comment
	* @param groupId the group ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment[] findByGroupId_PrevAndNext(
		long commentId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the comments where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @return the matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByG_C(
		long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the comments where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @return the range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByG_C(
		long groupId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the comments where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comments before and after the current comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param commentId the primary key of the current comment
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment[] findByG_C_PrevAndNext(
		long commentId, long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the comments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the comments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @return the range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the comments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comments before and after the current comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param commentId the primary key of the current comment
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment[] findByC_C_PrevAndNext(
		long commentId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchCommentException} if it could not be found.
	*
	* @param userId the user ID to search with
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the matching comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment findByU_C_C(long userId,
		long classNameId, long classPK)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID to search with
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment fetchByU_C_C(long userId,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the comment where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID to search with
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.Comment fetchByU_C_C(long userId,
		long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the comments.
	*
	* @return the comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the comments.
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
	public java.util.List<com.liferay.knowledgebase.model.Comment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of comments to return
	* @param end the upper bound of the range of comments to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.Comment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the comments where uuid = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the comment where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the comments where groupId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the comments where groupId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the comments where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the comment where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID to search with
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the comments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where uuid = &#63;.
	*
	* @param uuid the uuid to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid to search with
	* @param groupId the group ID to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where groupId = &#63;.
	*
	* @param groupId the group ID to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID to search with
	* @param classNameId the class name ID to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID to search with
	* @param classNameId the class name ID to search with
	* @param classPK the class p k to search with
	* @return the number of matching comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the comments.
	*
	* @return the number of comments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public Comment remove(Comment comment) throws SystemException;
}