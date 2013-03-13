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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the polls choice service. This utility wraps {@link PollsChoicePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Juan Fern√°ndez
 * @see PollsChoicePersistence
 * @see PollsChoicePersistenceImpl
 * @generated
 */
public class PollsChoiceUtil {
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
	public static void clearCache(PollsChoice pollsChoice) {
		getPersistence().clearCache(pollsChoice);
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
	public static List<PollsChoice> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PollsChoice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PollsChoice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static PollsChoice update(PollsChoice pollsChoice)
		throws SystemException {
		return getPersistence().update(pollsChoice);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static PollsChoice update(PollsChoice pollsChoice,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(pollsChoice, serviceContext);
	}

	/**
	* Returns all the polls choices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last polls choice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

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
	public static com.liferay.polls.model.PollsChoice[] findByUuid_PrevAndNext(
		long pollsChoiceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(pollsChoiceId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the polls choices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of polls choices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the polls choices where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPollsQuestionId(pollsQuestionId);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId(pollsQuestionId, start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findByPollsQuestionId(
		long pollsQuestionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId(pollsQuestionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_First(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the first polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByPollsQuestionId_First(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsQuestionId_First(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_Last(pollsQuestionId,
			orderByComparator);
	}

	/**
	* Returns the last polls choice in the ordered set where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByPollsQuestionId_Last(
		long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPollsQuestionId_Last(pollsQuestionId,
			orderByComparator);
	}

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
	public static com.liferay.polls.model.PollsChoice[] findByPollsQuestionId_PrevAndNext(
		long pollsChoiceId, long pollsQuestionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPollsQuestionId_PrevAndNext(pollsChoiceId,
			pollsQuestionId, orderByComparator);
	}

	/**
	* Removes all the polls choices where pollsQuestionId = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns the number of polls choices where pollsQuestionId = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPollsQuestionId(long pollsQuestionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPollsQuestionId(pollsQuestionId);
	}

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the matching polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPQI_N(pollsQuestionId, name);
	}

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPQI_N(pollsQuestionId, name);
	}

	/**
	* Returns the polls choice where pollsQuestionId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching polls choice, or <code>null</code> if a matching polls choice could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByPQI_N(
		long pollsQuestionId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPQI_N(pollsQuestionId, name, retrieveFromCache);
	}

	/**
	* Removes the polls choice where pollsQuestionId = &#63; and name = &#63; from the database.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the polls choice that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice removeByPQI_N(
		long pollsQuestionId, java.lang.String name)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByPQI_N(pollsQuestionId, name);
	}

	/**
	* Returns the number of polls choices where pollsQuestionId = &#63; and name = &#63;.
	*
	* @param pollsQuestionId the polls question ID
	* @param name the name
	* @return the number of matching polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPQI_N(long pollsQuestionId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPQI_N(pollsQuestionId, name);
	}

	/**
	* Caches the polls choice in the entity cache if it is enabled.
	*
	* @param pollsChoice the polls choice
	*/
	public static void cacheResult(
		com.liferay.polls.model.PollsChoice pollsChoice) {
		getPersistence().cacheResult(pollsChoice);
	}

	/**
	* Caches the polls choices in the entity cache if it is enabled.
	*
	* @param pollsChoices the polls choices
	*/
	public static void cacheResult(
		java.util.List<com.liferay.polls.model.PollsChoice> pollsChoices) {
		getPersistence().cacheResult(pollsChoices);
	}

	/**
	* Creates a new polls choice with the primary key. Does not add the polls choice to the database.
	*
	* @param pollsChoiceId the primary key for the new polls choice
	* @return the new polls choice
	*/
	public static com.liferay.polls.model.PollsChoice create(long pollsChoiceId) {
		return getPersistence().create(pollsChoiceId);
	}

	/**
	* Removes the polls choice with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice that was removed
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice remove(long pollsChoiceId)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(pollsChoiceId);
	}

	public static com.liferay.polls.model.PollsChoice updateImpl(
		com.liferay.polls.model.PollsChoice pollsChoice)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(pollsChoice);
	}

	/**
	* Returns the polls choice with the primary key or throws a {@link com.liferay.polls.NoSuchChoiceException} if it could not be found.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice
	* @throws com.liferay.polls.NoSuchChoiceException if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice findByPrimaryKey(
		long pollsChoiceId)
		throws com.liferay.polls.NoSuchChoiceException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(pollsChoiceId);
	}

	/**
	* Returns the polls choice with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pollsChoiceId the primary key of the polls choice
	* @return the polls choice, or <code>null</code> if a polls choice with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.polls.model.PollsChoice fetchByPrimaryKey(
		long pollsChoiceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(pollsChoiceId);
	}

	/**
	* Returns all the polls choices.
	*
	* @return the polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.polls.model.PollsChoice> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.polls.model.PollsChoice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the polls choices from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of polls choices.
	*
	* @return the number of polls choices
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PollsChoicePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PollsChoicePersistence)PortletBeanLocatorUtil.locate(com.liferay.polls.service.ClpSerializer.getServletContextName(),
					PollsChoicePersistence.class.getName());

			ReferenceRegistry.registerReference(PollsChoiceUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(PollsChoicePersistence persistence) {
	}

	private static PollsChoicePersistence _persistence;
}