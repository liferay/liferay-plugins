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

import com.liferay.polls.model.PollsVote;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the polls vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsVotePersistenceImpl
 * @see PollsVoteUtil
 * @generated
 */
public interface PollsVotePersistence extends BasePersistence<PollsVote> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PollsVoteUtil} to access the polls vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the polls votes where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls votes where pollsQuestionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsQuestionId the polls question ID
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @return the range of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls votes where pollsQuestionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsQuestionId the polls question ID
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls votes before and after the current polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsVoteId the primary key of the current polls vote
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote[] findByPollsQuestionId_PrevAndNext(
		long pollsVoteId, long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls votes where pollsQuestionId = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls votes where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public int countByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls votes where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @return the matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls votes where pollsChoiceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsChoiceId the polls choice ID
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @return the range of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls votes where pollsChoiceId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pollsChoiceId the polls choice ID
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByPollsChoiceId_First(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByPollsChoiceId_First(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByPollsChoiceId_Last(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByPollsChoiceId_Last(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls votes before and after the current polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsVoteId the primary key of the current polls vote
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote[] findByPollsChoiceId_PrevAndNext(
		long pollsVoteId, long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls votes where pollsChoiceId = &#63; from the database.
	*
	* @param pollsChoiceId the polls choice ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPollsChoiceId(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls votes where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public int countByPollsChoiceId(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByU_PQI(long userId,
		long pollsQuestionId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the polls vote where userId = &#63; and pollsQuestionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the polls vote that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote removeByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls votes where userId = &#63; and pollsQuestionId = &#63;.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_PQI(long userId, long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the polls vote in the entity cache if it is enabled.
	*
	* @param pollsVote the polls vote
	*/
	public void cacheResult(com.liferay.polls.model.PollsVote pollsVote);

	/**
	* Caches the polls votes in the entity cache if it is enabled.
	*
	* @param pollsVotes the polls votes
	*/
	public void cacheResult(
		java.util.List<com.liferay.polls.model.PollsVote> pollsVotes);

	/**
	* Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	*
	* @param pollsVoteId the primary key for the new polls vote
	* @return the new polls vote
	*/
	public com.liferay.polls.model.PollsVote create(long pollsVoteId);

	/**
	* Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote that was removed
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote remove(long pollsVoteId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.polls.model.PollsVote updateImpl(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls vote with the primary key or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote findByPrimaryKey(long pollsVoteId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote fetchByPrimaryKey(long pollsVoteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the polls votes.
	*
	* @return the polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the polls votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @return the range of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the polls votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of polls votes
	* @param end the upper bound of the range of polls votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.polls.model.PollsVote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the polls votes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of polls votes.
	*
	* @return the number of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}