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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the polls vote local service. This utility wraps {@link com.liferay.polls.service.impl.PollsVoteLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsVoteLocalService
 * @see com.liferay.polls.service.base.PollsVoteLocalServiceBaseImpl
 * @see com.liferay.polls.service.impl.PollsVoteLocalServiceImpl
 * @generated
 */
public class PollsVoteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.polls.service.impl.PollsVoteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the polls vote to the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote addPollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPollsVote(pollsVote);
	}

	/**
	* Creates a new polls vote with the primary key. Does not add the polls vote to the database.
	*
	* @param pollsVoteId the primary key for the new polls vote
	* @return the new polls vote
	*/
	public static com.liferay.polls.model.PollsVote createPollsVote(
		long pollsVoteId) {
		return getService().createPollsVote(pollsVoteId);
	}

	/**
	* Deletes the polls vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote that was removed
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote deletePollsVote(
		long pollsVoteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePollsVote(pollsVoteId);
	}

	/**
	* Deletes the polls vote from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote deletePollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePollsVote(pollsVote);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.polls.model.PollsVote fetchPollsVote(
		long pollsVoteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPollsVote(pollsVoteId);
	}

	/**
	* Returns the polls vote with the primary key.
	*
	* @param pollsVoteId the primary key of the polls vote
	* @return the polls vote
	* @throws PortalException if a polls vote with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote getPollsVote(
		long pollsVoteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsVote(pollsVoteId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.polls.model.PollsVote> getPollsVotes(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsVotes(start, end);
	}

	/**
	* Returns the number of polls votes.
	*
	* @return the number of polls votes
	* @throws SystemException if a system exception occurred
	*/
	public static int getPollsVotesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsVotesCount();
	}

	/**
	* Updates the polls vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsVote the polls vote
	* @return the polls vote that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsVote updatePollsVote(
		com.liferay.polls.model.PollsVote pollsVote)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePollsVote(pollsVote);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.polls.model.PollsVote addPollsVote(long userId,
		long pollsQuestionId, long pollsChoiceId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPollsVote(userId, pollsQuestionId, pollsChoiceId,
			serviceContext);
	}

	public static java.util.List<com.liferay.polls.model.PollsVote> getPollsChoicePollsVotes(
		long pollsChoiceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsChoicePollsVotes(pollsChoiceId, start, end);
	}

	public static int getPollsChoicePollsVotesCount(long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsChoicePollsVotesCount(pollsChoiceId);
	}

	public static java.util.List<com.liferay.polls.model.PollsVote> getPollsQuestionPollsVotes(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPollsQuestionPollsVotes(pollsQuestionId, start, end);
	}

	public static int getPollsQuestionPollsVotesCount(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsQuestionPollsVotesCount(pollsQuestionId);
	}

	public static com.liferay.polls.model.PollsVote getPollsVote(
		long pollsQuestionId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPollsVote(pollsQuestionId, userId);
	}

	public static void clearService() {
		_service = null;
	}

	public static PollsVoteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PollsVoteLocalService.class.getName());

			if (invokableLocalService instanceof PollsVoteLocalService) {
				_service = (PollsVoteLocalService)invokableLocalService;
			}
			else {
				_service = new PollsVoteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PollsVoteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(PollsVoteLocalService service) {
	}

	private static PollsVoteLocalService _service;
}