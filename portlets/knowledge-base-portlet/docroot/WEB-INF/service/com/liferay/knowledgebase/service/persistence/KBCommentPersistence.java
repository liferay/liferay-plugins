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

package com.liferay.knowledgebase.service.persistence;

import com.liferay.knowledgebase.model.KBComment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the k b comment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentPersistenceImpl
 * @see KBCommentUtil
 * @generated
 */
public interface KBCommentPersistence extends BasePersistence<KBComment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link KBCommentUtil} to access the k b comment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the k b comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where uuid = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByUuid_PrevAndNext(
		long kbCommentId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comment where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.knowledgebase.NoSuchCommentException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the k b comment where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the k b comment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByUuid_C_PrevAndNext(
		long kbCommentId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where groupId = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByGroupId_PrevAndNext(
		long kbCommentId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_C(
		long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_C(
		long groupId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where groupId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_C(
		long groupId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByG_C_First(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByG_C_Last(
		long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where groupId = &#63; and classNameId = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByG_C_PrevAndNext(
		long kbCommentId, long groupId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where groupId = &#63; and classNameId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where groupId = &#63; and classNameId = &#63;.
	*
	* @param groupId the group ID
	* @param classNameId the class name ID
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_S(
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_S(
		long groupId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByG_S(
		long groupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByG_S_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByG_S_First(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByG_S_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByG_S_Last(
		long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByG_S_PrevAndNext(
		long kbCommentId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_S(long groupId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByC_C_PrevAndNext(
		long kbCommentId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByU_C_C(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByU_C_C(
		long userId, long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByU_C_C(
		long userId, long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByU_C_C_First(
		long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByU_C_C_First(
		long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByU_C_C_Last(
		long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByU_C_C_Last(
		long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByU_C_C_PrevAndNext(
		long kbCommentId, long userId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first k b comment in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last k b comment in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching k b comment, or <code>null</code> if a matching k b comment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comments before and after the current k b comment in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param kbCommentId the primary key of the current k b comment
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment[] findByC_C_S_PrevAndNext(
		long kbCommentId, long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @return the matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the k b comments where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @return the range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findByC_C_S(
		long classNameId, long classPK, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @return the number of matching k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_S(long classNameId, long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the k b comment in the entity cache if it is enabled.
	*
	* @param kbComment the k b comment
	*/
	public void cacheResult(com.liferay.knowledgebase.model.KBComment kbComment);

	/**
	* Caches the k b comments in the entity cache if it is enabled.
	*
	* @param kbComments the k b comments
	*/
	public void cacheResult(
		java.util.List<com.liferay.knowledgebase.model.KBComment> kbComments);

	/**
	* Creates a new k b comment with the primary key. Does not add the k b comment to the database.
	*
	* @param kbCommentId the primary key for the new k b comment
	* @return the new k b comment
	*/
	public com.liferay.knowledgebase.model.KBComment create(long kbCommentId);

	/**
	* Removes the k b comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment that was removed
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment remove(long kbCommentId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.knowledgebase.model.KBComment updateImpl(
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comment with the primary key or throws a {@link com.liferay.knowledgebase.NoSuchCommentException} if it could not be found.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment
	* @throws com.liferay.knowledgebase.NoSuchCommentException if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment findByPrimaryKey(
		long kbCommentId)
		throws com.liferay.knowledgebase.NoSuchCommentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the k b comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kbCommentId the primary key of the k b comment
	* @return the k b comment, or <code>null</code> if a k b comment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.knowledgebase.model.KBComment fetchByPrimaryKey(
		long kbCommentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the k b comments.
	*
	* @return the k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the k b comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.knowledgebase.model.impl.KBCommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of k b comments
	* @param end the upper bound of the range of k b comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of k b comments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.knowledgebase.model.KBComment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the k b comments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of k b comments.
	*
	* @return the number of k b comments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}