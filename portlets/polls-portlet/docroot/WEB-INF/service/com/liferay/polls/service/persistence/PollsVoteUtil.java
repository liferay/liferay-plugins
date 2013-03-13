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

import com.liferay.polls.model.PollsVote;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the polls vote service. This utility wraps {@link PollsVotePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsVotePersistence
 * @see PollsVotePersistenceImpl
 * @generated
 */
public class PollsVoteUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(PollsVote pollsVote) {
		getPersistence().clearCache(pollsVote);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PollsVote> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PollsVote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PollsVote> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static PollsVote update(PollsVote pollsVote)
		throws SystemException {
		return getPersistence().update(pollsVote);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static PollsVote update(PollsVote pollsVote,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(pollsVote, serviceContext);
	}

	/**
	* Returns all the polls votes where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPollsQuestionId(pollsQuestionId);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId(pollsQuestionId, start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsQuestionId(
		long pollsQuestionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId(pollsQuestionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_First(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the first polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsQuestionId_First(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_Last(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the last polls vote in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsQuestionId_Last(pollsQuestionId,
			orderByComparator);
	}

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
	public static com.liferay.polls.model.PollsVote[] findByPollsQuestionId_PrevAndNext(
		long pollsVoteId, long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_PrevAndNext(pollsVoteId,
			pollsQuestionId, orderByComparator);
	}

	/**
	* Removes all the polls votes where pollsQuestionId = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns the number of polls votes where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns all the polls votes where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @return the matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPollsChoiceId(pollsChoiceId);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPollsChoiceId(pollsChoiceId, start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findByPollsChoiceId(
		long pollsChoiceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsChoiceId(pollsChoiceId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByPollsChoiceId_First(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsChoiceId_First(pollsChoiceId, orderByComparator);
	}

	/**
	* Returns the first polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByPollsChoiceId_First(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsChoiceId_First(pollsChoiceId, orderByComparator);
	}

	/**
	* Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByPollsChoiceId_Last(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsChoiceId_Last(pollsChoiceId, orderByComparator);
	}

	/**
	* Returns the last polls vote in the ordered set where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByPollsChoiceId_Last(
		long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsChoiceId_Last(pollsChoiceId, orderByComparator);
	}

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
	public static com.liferay.polls.model.PollsVote[] findByPollsChoiceId_PrevAndNext(
		long pollsVoteId, long pollsChoiceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsChoiceId_PrevAndNext(pollsVoteId, pollsChoiceId,
			orderByComparator);
	}

	/**
	* Removes all the polls votes where pollsChoiceId = &#63; from the database.
	*
	* @param pollsChoiceId the polls choice ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPollsChoiceId(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPollsChoiceId(pollsChoiceId);
	}

	/**
	* Returns the number of polls votes where pollsChoiceId = &#63;.
	*
	* @param pollsChoiceId the polls choice ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPollsChoiceId(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPollsChoiceId(pollsChoiceId);
	}

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the matching polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_PQI(userId, pollsQuestionId);
	}

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_PQI(userId, pollsQuestionId);
	}

	/**
	* Returns the polls vote where userId = &#63; and pollsQuestionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching polls vote, or <code>null</code> if a matching polls vote could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByU_PQI(long userId,
		long pollsQuestionId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_PQI(userId, pollsQuestionId, retrieveFromCache);
	}

	/**
	* Removes the polls vote where userId = &#63; and pollsQuestionId = &#63; from the database.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the polls vote that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote removeByU_PQI(long userId,
		long pollsQuestionId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_PQI(userId, pollsQuestionId);
	}

	/**
	* Returns the number of polls votes where userId = &#63; and pollsQuestionId = &#63;.
	*
	* @param userId the user ID
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_PQI(long userId, long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_PQI(userId, pollsQuestionId);
	}

	/**
	* Caches the polls vote in the entity cache if it is enabled.
	*
	* @param pollsVote the polls vote
	*/
	public static void cacheResult(com.liferay.polls.model.PollsVote pollsVote) {
		getPersistence().cacheResult(pollsVote);
	}

	/**
	* Caches the polls votes in the entity cache if it is enabled.
	*
	* @param pollsVotes the polls votes
	*/
	public static void cacheResult(
		java.util.List<com.liferay.polls.model.PollsVote> pollsVotes) {
		getPersistence().cacheResult(pollsVotes);
	}

	/**
	* Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	*
	* @param pollsVoteId the primary key for the new polls vote
	* @return the new polls vote
	*/
	public static com.liferay.polls.model.PollsVote create(long pollsVoteId) {
		return getPersistence().create(pollsVoteId);
	}

	/**
	* Removes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote that was removed
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote remove(long pollsVoteId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(pollsVoteId);
	}

	public static com.liferay.polls.model.PollsVote updateImpl(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(pollsVote);
	}

	/**
	* Returns the polls vote with the primary key or throws a {@link com.liferay.polls.NoSuchVoteException} if it could not be found.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote
	* @throws com.liferay.polls.NoSuchVoteException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote findByPrimaryKey(
		long pollsVoteId)
		throws com.liferay.polls.NoSuchVoteException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(pollsVoteId);
	}

	/**
	* Returns the polls vote with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote, or <code>null</code> if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote fetchByPrimaryKey(
		long pollsVoteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(pollsVoteId);
	}

	/**
	* Returns all the polls votes.
	*
	* @return the polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsVote> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsVote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the polls votes from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of polls votes.
	*
	* @return the number of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PollsVotePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PollsVotePersistence)PortletBeanLocatorUtil.locate(com.liferay.polls.service.ClpSerializer.getServletContextName(),
					PollsVotePersistence.class.getName());

			ReferenceRegistry.registerReference(PollsVoteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(PollsVotePersistence persistence) {
	}

	private static PollsVotePersistence _persistence;
}