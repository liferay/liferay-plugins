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
 * Provides a wrapper for {@link PollsQuestionLocalService}.
 *
 * @author    Juan Fern√°ndez
 * @see       PollsQuestionLocalService
 * @generated
 */
public class PollsQuestionLocalServiceWrapper
	implements PollsQuestionLocalService,
		ServiceWrapper<PollsQuestionLocalService> {
	public PollsQuestionLocalServiceWrapper(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	/**
	* Adds the polls question to the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @return the polls question that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion addPollsQuestion(
		com.liferay.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.addPollsQuestion(pollsQuestion);
	}

	/**
	* Creates a new polls question with the primary key. Does not add the polls question to the database.
	*
	* @param pollsQuestionId the primary key for the new polls question
	* @return the new polls question
	*/
	public com.liferay.polls.model.PollsQuestion createPollsQuestion(
		long pollsQuestionId) {
		return _pollsQuestionLocalService.createPollsQuestion(pollsQuestionId);
	}

	/**
	* Deletes the polls question with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestionId the primary key of the polls question
	* @return the polls question that was removed
	* @throws PortalException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion deletePollsQuestion(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.deletePollsQuestion(pollsQuestionId);
	}

	/**
	* Deletes the polls question from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @return the polls question that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion deletePollsQuestion(
		com.liferay.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.deletePollsQuestion(pollsQuestion);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pollsQuestionLocalService.dynamicQuery();
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.polls.model.impl.PollsQuestionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pollsQuestionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _pollsQuestionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.polls.model.PollsQuestion fetchPollsQuestion(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.fetchPollsQuestion(pollsQuestionId);
	}

	/**
	* Returns the polls question with the primary key.
	*
	* @param pollsQuestionId the primary key of the polls question
	* @return the polls question
	* @throws PortalException if a polls question with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion getPollsQuestion(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestion(pollsQuestionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the polls question matching the UUID and group.
	*
	* @param uuid the polls question's UUID
	* @param groupId the primary key of the group
	* @return the matching polls question
	* @throws PortalException if a matching polls question could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion getPollsQuestionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestionByUuidAndGroupId(uuid,
			groupId);
	}

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
	public java.util.List<com.liferay.polls.model.PollsQuestion> getPollsQuestions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestions(start, end);
	}

	/**
	* Returns the number of polls questions.
	*
	* @return the number of polls questions
	* @throws SystemException if a system exception occurred
	*/
	public int getPollsQuestionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestionsCount();
	}

	/**
	* Updates the polls question in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pollsQuestion the polls question
	* @return the polls question that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.polls.model.PollsQuestion updatePollsQuestion(
		com.liferay.polls.model.PollsQuestion pollsQuestion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.updatePollsQuestion(pollsQuestion);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _pollsQuestionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_pollsQuestionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _pollsQuestionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.polls.model.PollsQuestion addPollsQuestion(long userId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.polls.model.PollsChoice> pollsChoices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.addPollsQuestion(userId, titleMap,
			descriptionMap, expirationDateMonth, expirationDateDay,
			expirationDateYear, expirationDateHour, expirationDateMinute,
			neverExpire, pollsChoices, serviceContext);
	}

	public void deletePollsQuestions(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_pollsQuestionLocalService.deletePollsQuestions(groupId);
	}

	public java.util.List<com.liferay.polls.model.PollsQuestion> getPollsQuestions(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestions(groupId);
	}

	public java.util.List<com.liferay.polls.model.PollsQuestion> getPollsQuestions(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestions(groupId, start, end);
	}

	public int getPollsQuestionsCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.getPollsQuestionsCount(groupId);
	}

	public com.liferay.polls.model.PollsQuestion updatePollsQuestion(
		long userId, long pollsQuestionId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		int expirationDateMonth, int expirationDateDay, int expirationDateYear,
		int expirationDateHour, int expirationDateMinute, boolean neverExpire,
		java.util.List<com.liferay.polls.model.PollsChoice> pollsChoices,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pollsQuestionLocalService.updatePollsQuestion(userId,
			pollsQuestionId, titleMap, descriptionMap, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, pollsChoices, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public PollsQuestionLocalService getWrappedPollsQuestionLocalService() {
		return _pollsQuestionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedPollsQuestionLocalService(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	public PollsQuestionLocalService getWrappedService() {
		return _pollsQuestionLocalService;
	}

	public void setWrappedService(
		PollsQuestionLocalService pollsQuestionLocalService) {
		_pollsQuestionLocalService = pollsQuestionLocalService;
	}

	private PollsQuestionLocalService _pollsQuestionLocalService;
}