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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.BBBMeeting;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the b b b meeting service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBMeetingPersistenceImpl
 * @see BBBMeetingUtil
 * @generated
 */
public interface BBBMeetingPersistence extends BasePersistence<BBBMeeting> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BBBMeetingUtil} to access the b b b meeting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the b b b meetings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByGroupId(
		long groupId);

	/**
	* Returns a range of all the b b b meetings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the b b b meetings where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the first b b b meeting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the first b b b meeting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the last b b b meeting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the last b b b meeting in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b meetings before and after the current b b b meeting in the ordered set where groupId = &#63;.
	*
	* @param bbbMeetingId the primary key of the current b b b meeting
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting[] findByGroupId_PrevAndNext(
		long bbbMeetingId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns all the b b b meetings that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching b b b meetings that the user has permission to view
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> filterFindByGroupId(
		long groupId);

	/**
	* Returns a range of all the b b b meetings that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of matching b b b meetings that the user has permission to view
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> filterFindByGroupId(
		long groupId, int start, int end);

	/**
	* Returns an ordered range of all the b b b meetings that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b meetings that the user has permission to view
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b meetings before and after the current b b b meeting in the ordered set of b b b meetings that the user has permission to view where groupId = &#63;.
	*
	* @param bbbMeetingId the primary key of the current b b b meeting
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting[] filterFindByGroupId_PrevAndNext(
		long bbbMeetingId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Removes all the b b b meetings where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of b b b meetings where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching b b b meetings
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns the number of b b b meetings that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching b b b meetings that the user has permission to view
	*/
	public int filterCountByGroupId(long groupId);

	/**
	* Returns all the b b b meetings where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @return the matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBbbServerId(
		long bbbServerId);

	/**
	* Returns a range of all the b b b meetings where bbbServerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbServerId the bbb server ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBbbServerId(
		long bbbServerId, int start, int end);

	/**
	* Returns an ordered range of all the b b b meetings where bbbServerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbServerId the bbb server ID
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBbbServerId(
		long bbbServerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the first b b b meeting in the ordered set where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByBbbServerId_First(
		long bbbServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the first b b b meeting in the ordered set where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByBbbServerId_First(
		long bbbServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the last b b b meeting in the ordered set where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByBbbServerId_Last(
		long bbbServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the last b b b meeting in the ordered set where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByBbbServerId_Last(
		long bbbServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b meetings before and after the current b b b meeting in the ordered set where bbbServerId = &#63;.
	*
	* @param bbbMeetingId the primary key of the current b b b meeting
	* @param bbbServerId the bbb server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting[] findByBbbServerId_PrevAndNext(
		long bbbMeetingId, long bbbServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Removes all the b b b meetings where bbbServerId = &#63; from the database.
	*
	* @param bbbServerId the bbb server ID
	*/
	public void removeByBbbServerId(long bbbServerId);

	/**
	* Returns the number of b b b meetings where bbbServerId = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @return the number of matching b b b meetings
	*/
	public int countByBbbServerId(long bbbServerId);

	/**
	* Returns all the b b b meetings where status = &#63;.
	*
	* @param status the status
	* @return the matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByStatus(
		int status);

	/**
	* Returns a range of all the b b b meetings where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByStatus(
		int status, int start, int end);

	/**
	* Returns an ordered range of all the b b b meetings where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the first b b b meeting in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the first b b b meeting in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the last b b b meeting in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the last b b b meeting in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b meetings before and after the current b b b meeting in the ordered set where status = &#63;.
	*
	* @param bbbMeetingId the primary key of the current b b b meeting
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting[] findByStatus_PrevAndNext(
		long bbbMeetingId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Removes all the b b b meetings where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(int status);

	/**
	* Returns the number of b b b meetings where status = &#63;.
	*
	* @param status the status
	* @return the number of matching b b b meetings
	*/
	public int countByStatus(int status);

	/**
	* Returns all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @return the matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBSI_S(
		long bbbServerId, int status);

	/**
	* Returns a range of all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBSI_S(
		long bbbServerId, int status, int start, int end);

	/**
	* Returns an ordered range of all the b b b meetings where bbbServerId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findByBSI_S(
		long bbbServerId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the first b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByBSI_S_First(
		long bbbServerId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the first b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByBSI_S_First(
		long bbbServerId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the last b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByBSI_S_Last(long bbbServerId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the last b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b meeting, or <code>null</code> if a matching b b b meeting could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByBSI_S_Last(
		long bbbServerId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Returns the b b b meetings before and after the current b b b meeting in the ordered set where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbMeetingId the primary key of the current b b b meeting
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting[] findByBSI_S_PrevAndNext(
		long bbbMeetingId, long bbbServerId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Removes all the b b b meetings where bbbServerId = &#63; and status = &#63; from the database.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	*/
	public void removeByBSI_S(long bbbServerId, int status);

	/**
	* Returns the number of b b b meetings where bbbServerId = &#63; and status = &#63;.
	*
	* @param bbbServerId the bbb server ID
	* @param status the status
	* @return the number of matching b b b meetings
	*/
	public int countByBSI_S(long bbbServerId, int status);

	/**
	* Caches the b b b meeting in the entity cache if it is enabled.
	*
	* @param bbbMeeting the b b b meeting
	*/
	public void cacheResult(com.liferay.bbb.model.BBBMeeting bbbMeeting);

	/**
	* Caches the b b b meetings in the entity cache if it is enabled.
	*
	* @param bbbMeetings the b b b meetings
	*/
	public void cacheResult(
		java.util.List<com.liferay.bbb.model.BBBMeeting> bbbMeetings);

	/**
	* Creates a new b b b meeting with the primary key. Does not add the b b b meeting to the database.
	*
	* @param bbbMeetingId the primary key for the new b b b meeting
	* @return the new b b b meeting
	*/
	public com.liferay.bbb.model.BBBMeeting create(long bbbMeetingId);

	/**
	* Removes the b b b meeting with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting that was removed
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting remove(long bbbMeetingId)
		throws com.liferay.bbb.NoSuchMeetingException;

	public com.liferay.bbb.model.BBBMeeting updateImpl(
		com.liferay.bbb.model.BBBMeeting bbbMeeting);

	/**
	* Returns the b b b meeting with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingException} if it could not be found.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting
	* @throws com.liferay.bbb.NoSuchMeetingException if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting findByPrimaryKey(long bbbMeetingId)
		throws com.liferay.bbb.NoSuchMeetingException;

	/**
	* Returns the b b b meeting with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bbbMeetingId the primary key of the b b b meeting
	* @return the b b b meeting, or <code>null</code> if a b b b meeting with the primary key could not be found
	*/
	public com.liferay.bbb.model.BBBMeeting fetchByPrimaryKey(long bbbMeetingId);

	@Override
	public java.util.Map<java.io.Serializable, com.liferay.bbb.model.BBBMeeting> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the b b b meetings.
	*
	* @return the b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findAll();

	/**
	* Returns a range of all the b b b meetings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @return the range of b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the b b b meetings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBMeetingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b meetings
	* @param end the upper bound of the range of b b b meetings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of b b b meetings
	*/
	public java.util.List<com.liferay.bbb.model.BBBMeeting> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

	/**
	* Removes all the b b b meetings from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of b b b meetings.
	*
	* @return the number of b b b meetings
	*/
	public int countAll();
}