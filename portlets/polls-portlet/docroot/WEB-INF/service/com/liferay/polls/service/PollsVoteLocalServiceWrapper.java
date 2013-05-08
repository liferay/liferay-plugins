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

package com.liferay.polls.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PollsVoteLocalService}.
 *
 * @author    Juan Fern√°ndez
 * @see       PollsVoteLocalService
 * @generated
 */
public class PollsVoteLocalServiceWrapper implements PollsVoteLocalService,
	ServiceWrapper<PollsVoteLocalService> {
	public PollsVoteLocalServiceWrapper(
		PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	/**
	* Adds the polls vote to the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote addPollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.addPollsVote(pollsVote);
	}

	/**
	* Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	*
	* @param pollsVoteId the primary key for the new polls vote
	* @return the new polls vote
	*/
	public com.liferay.polls.model.PollsVote createPollsVote(long pollsVoteId) {
		return _pollsVoteLocalService.createPollsVote(pollsVoteId);
	}

	/**
	* Deletes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote that was removed
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote deletePollsVote(long pollsVoteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.deletePollsVote(pollsVoteId);
	}

	/**
	* Deletes the polls vote from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote deletePollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.deletePollsVote(pollsVote);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pollsVoteLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.polls.model.PollsVote fetchPollsVote(long pollsVoteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.fetchPollsVote(pollsVoteId);
	}

	/**
	* Returns the polls vote with the primary key.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote getPollsVote(long pollsVoteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVote(pollsVoteId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.polls.model.PollsVote> getPollsVotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVotes(start, end);
	}

	/**
	* Returns the number of polls votes.
	*
	* @return the number of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public int getPollsVotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVotesCount();
	}

	/**
	* Updates the polls vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsVote updatePollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.updatePollsVote(pollsVote);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsVoteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsVoteLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pollsVoteLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.polls.model.PollsVote addPollsVote(long userId,
		long pollsQuestionId, long pollsChoiceId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.addPollsVote(userId, pollsQuestionId,
			pollsChoiceId, serviceContext);
	}

	public java.util.List<com.liferay.polls.model.PollsVote> getPollsChoicePollsVotes(
		long pollsChoiceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsChoicePollsVotes(pollsChoiceId,
			start, end);
	}

	public int getPollsChoicePollsVotesCount(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsChoicePollsVotesCount(pollsChoiceId);
	}

	public java.util.List<com.liferay.polls.model.PollsVote> getPollsQuestionPollsVotes(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsQuestionPollsVotes(pollsQuestionId,
			start, end);
	}

	public int getPollsQuestionPollsVotesCount(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsQuestionPollsVotesCount(pollsQuestionId);
	}

	public com.liferay.polls.model.PollsVote getPollsVote(
		long pollsQuestionId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsVoteLocalService.getPollsVote(pollsQuestionId, userId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PollsVoteLocalService getWrappedPollsVoteLocalService() {
		return _pollsVoteLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPollsVoteLocalService(
		PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	public PollsVoteLocalService getWrappedService() {
		return _pollsVoteLocalService;
	}

	public void setWrappedService(PollsVoteLocalService pollsVoteLocalService) {
		_pollsVoteLocalService = pollsVoteLocalService;
	}

	private PollsVoteLocalService _pollsVoteLocalService;
}