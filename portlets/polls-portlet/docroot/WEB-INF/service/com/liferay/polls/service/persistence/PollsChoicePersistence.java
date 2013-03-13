/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.polls.model.PollsChoice;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the polls choice service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsChoicePersistenceImpl
 * @see PollsChoiceUtil
 * @generated
 */
public interface PollsChoicePersistence extends BasePersistence<PollsChoice> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PollsChoiceUtil} to access the polls choice persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the polls choices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls choices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @return the range of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls choices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choices before and after the current polls choice in the ordered set where uuid = &#63;.
	*
	* @param pollsChoiceId the primary key of the current polls choice
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice[] findByUuid_PrevAndNext(
		long pollsChoiceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls choices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls choices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls choices where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls choices where pollsQuestionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsQuestionId the polls question ID
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @return the range of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls choices where pollsQuestionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsQuestionId the polls question ID
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choices before and after the current polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsChoiceId the primary key of the current polls choice
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice[] findByPollsQuestionId_PrevAndNext(
		long pollsChoiceId, long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls choices where pollsQuestionId = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls choices where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public int countByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByPQI_N(
		long pollsQuestionId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the polls choice where pollsQuestionId = &#63; and name = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the polls choice that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice removeByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls choices where pollsQuestionId = &#63; and name = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public int countByPQI_N(long pollsQuestionId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the polls choice in the entity cache if it is enabled.
	*
	* @param pollsChoice the polls choice
	*/
	public void cacheResult(com.liferay.polls.model.PollsChoice pollsChoice);

	/**
	* Caches the polls choices in the entity cache if it is enabled.
	*
	* @param pollsChoices the polls choices
	*/
	public void cacheResult(
		java.util.List<com.liferay.polls.model.PollsChoice> pollsChoices);

	/**
	* Creates a new polls choice with the primary key. Does not add the polls choice to the database.
	*
	* @param pollsChoiceId the primary key for the new polls choice
	* @return the new polls choice
	*/
	public com.liferay.polls.model.PollsChoice create(long pollsChoiceId);

	/**
	* Removes the polls choice with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice that was removed
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice remove(long pollsChoiceId)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.polls.model.PollsChoice updateImpl(
		com.liferay.polls.model.PollsChoice pollsChoice)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choice with the primary key or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice findByPrimaryKey(
		long pollsChoiceId)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls choice with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice, or <code>null</code> if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsChoice fetchByPrimaryKey(
		long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls choices.
	*
	* @return the polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls choices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @return the range of polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls choices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsChoiceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls choices
	* @param end the upper bound of the range of polls choices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of polls choices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsChoice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls choices from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls choices.
	*
	* @return the number of polls choices
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}