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

package com.liferay.polls.service.persistence;

import com.liferay.polls.model.PollsQuestion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the polls question service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsQuestionPersistenceImpl
 * @see PollsQuestionUtil
 * @generated
 */
public interface PollsQuestionPersistence extends BasePersistence<PollsQuestion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PollsQuestionUtil} to access the polls question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the polls questions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls questions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls questions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls questions before and after the current polls question in the ordered set where uuid = &#63;.
	*
	* @param pollsQuestionId the primary key of the current polls question
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion[] findByUuid_PrevAndNext(
		long pollsQuestionId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls questions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls question where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.polls.NoSuchQuestionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls question where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls question where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the polls question where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the polls question that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls questions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls questions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls questions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls questions before and after the current polls question in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pollsQuestionId the primary key of the current polls question
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion[] findByUuid_C_PrevAndNext(
		long pollsQuestionId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls questions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls questions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls questions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls questions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls question in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls question in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls question, or <code>null</code> if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls questions before and after the current polls question in the ordered set where groupId = &#63;.
	*
	* @param pollsQuestionId the primary key of the current polls question
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion[] findByGroupId_PrevAndNext(
		long pollsQuestionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls questions that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching polls questions that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls questions that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of matching polls questions that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls questions that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls questions that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls questions before and after the current polls question in the ordered set of polls questions that the user has permission to view where groupId = &#63;.
	*
	* @param pollsQuestionId the primary key of the current polls question
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion[] filterFindByGroupId_PrevAndNext(
		long pollsQuestionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls questions where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching polls questions that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the polls question in the entity cache if it is enabled.
	*
	* @param pollsQuestion the polls question
	*/
	public void cacheResult(com.liferay.polls.model.PollsQuestion pollsQuestion);

	/**
	* Caches the polls questions in the entity cache if it is enabled.
	*
	* @param pollsQuestions the polls questions
	*/
	public void cacheResult(
		java.util.List<com.liferay.polls.model.PollsQuestion> pollsQuestions);

	/**
	* Creates a new polls question with the primary key. Does not add the polls question to the database.
	*
	* @param pollsQuestionId the primary key for the new polls question
	* @return the new polls question
	*/
	public com.liferay.polls.model.PollsQuestion create(long pollsQuestionId);

	/**
	* Removes the polls question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestionId the primary key of the polls question
	* @return the polls question that was removed
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion remove(long pollsQuestionId)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.polls.model.PollsQuestion updateImpl(
		com.liferay.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls question with the primary key or throws a {@link com.liferay.polls.NoSuchQuestionException} if it could not be found.
	*
	* @param pollsQuestionId the primary key of the polls question
	* @return the polls question
	* @throws com.liferay.polls.NoSuchQuestionException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion findByPrimaryKey(
		long pollsQuestionId)
		throws com.liferay.polls.NoSuchQuestionException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls question with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pollsQuestionId the primary key of the polls question
	* @return the polls question, or <code>null</code> if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion fetchByPrimaryKey(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls questions.
	*
	* @return the polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls questions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @return the range of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls questions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls questions
	* @param end the upper bound of the range of polls questions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsQuestion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls questions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls questions.
	*
	* @return the number of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}