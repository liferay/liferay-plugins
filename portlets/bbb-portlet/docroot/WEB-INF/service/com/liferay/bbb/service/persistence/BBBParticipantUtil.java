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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.BBBParticipant;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the b b b participant service. This utility wraps {@link BBBParticipantPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see BBBParticipantPersistence
 * @see BBBParticipantPersistenceImpl
 * @generated
 */
public class BBBParticipantUtil {
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
	public static void clearCache(BBBParticipant bbbParticipant) {
		getPersistence().clearCache(bbbParticipant);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BBBParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BBBParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BBBParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static BBBParticipant update(BBBParticipant bbbParticipant)
		throws SystemException {
		return getPersistence().update(bbbParticipant);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static BBBParticipant update(BBBParticipant bbbParticipant,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(bbbParticipant, serviceContext);
	}

	/**
	* Returns all the b b b participants where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @return the matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBbbMeetingId(bbbMeetingId);
	}

	/**
	* Returns a range of all the b b b participants where bbbMeetingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @return the range of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBbbMeetingId(bbbMeetingId, start, end);
	}

	/**
	* Returns an ordered range of all the b b b participants where bbbMeetingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findByBbbMeetingId(
		long bbbMeetingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBbbMeetingId(bbbMeetingId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant findByBbbMeetingId_First(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBbbMeetingId_First(bbbMeetingId, orderByComparator);
	}

	/**
	* Returns the first b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant fetchByBbbMeetingId_First(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBbbMeetingId_First(bbbMeetingId, orderByComparator);
	}

	/**
	* Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant findByBbbMeetingId_Last(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBbbMeetingId_Last(bbbMeetingId, orderByComparator);
	}

	/**
	* Returns the last b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching b b b participant, or <code>null</code> if a matching b b b participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant fetchByBbbMeetingId_Last(
		long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBbbMeetingId_Last(bbbMeetingId, orderByComparator);
	}

	/**
	* Returns the b b b participants before and after the current b b b participant in the ordered set where bbbMeetingId = &#63;.
	*
	* @param bbbParticipantId the primary key of the current b b b participant
	* @param bbbMeetingId the bbb meeting ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant[] findByBbbMeetingId_PrevAndNext(
		long bbbParticipantId, long bbbMeetingId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByBbbMeetingId_PrevAndNext(bbbParticipantId,
			bbbMeetingId, orderByComparator);
	}

	/**
	* Removes all the b b b participants where bbbMeetingId = &#63; from the database.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByBbbMeetingId(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByBbbMeetingId(bbbMeetingId);
	}

	/**
	* Returns the number of b b b participants where bbbMeetingId = &#63;.
	*
	* @param bbbMeetingId the bbb meeting ID
	* @return the number of matching b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBbbMeetingId(long bbbMeetingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBbbMeetingId(bbbMeetingId);
	}

	/**
	* Caches the b b b participant in the entity cache if it is enabled.
	*
	* @param bbbParticipant the b b b participant
	*/
	public static void cacheResult(
		com.liferay.bbb.model.BBBParticipant bbbParticipant) {
		getPersistence().cacheResult(bbbParticipant);
	}

	/**
	* Caches the b b b participants in the entity cache if it is enabled.
	*
	* @param bbbParticipants the b b b participants
	*/
	public static void cacheResult(
		java.util.List<com.liferay.bbb.model.BBBParticipant> bbbParticipants) {
		getPersistence().cacheResult(bbbParticipants);
	}

	/**
	* Creates a new b b b participant with the primary key. Does not add the b b b participant to the database.
	*
	* @param bbbParticipantId the primary key for the new b b b participant
	* @return the new b b b participant
	*/
	public static com.liferay.bbb.model.BBBParticipant create(
		long bbbParticipantId) {
		return getPersistence().create(bbbParticipantId);
	}

	/**
	* Removes the b b b participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant that was removed
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant remove(
		long bbbParticipantId)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(bbbParticipantId);
	}

	public static com.liferay.bbb.model.BBBParticipant updateImpl(
		com.liferay.bbb.model.BBBParticipant bbbParticipant)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(bbbParticipant);
	}

	/**
	* Returns the b b b participant with the primary key or throws a {@link com.liferay.bbb.NoSuchParticipantException} if it could not be found.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant
	* @throws com.liferay.bbb.NoSuchParticipantException if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant findByPrimaryKey(
		long bbbParticipantId)
		throws com.liferay.bbb.NoSuchParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(bbbParticipantId);
	}

	/**
	* Returns the b b b participant with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param bbbParticipantId the primary key of the b b b participant
	* @return the b b b participant, or <code>null</code> if a b b b participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.BBBParticipant fetchByPrimaryKey(
		long bbbParticipantId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(bbbParticipantId);
	}

	/**
	* Returns all the b b b participants.
	*
	* @return the b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the b b b participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @return the range of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the b b b participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.BBBParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of b b b participants
	* @param end the upper bound of the range of b b b participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.BBBParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the b b b participants from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of b b b participants.
	*
	* @return the number of b b b participants
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static BBBParticipantPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (BBBParticipantPersistence)PortletBeanLocatorUtil.locate(com.liferay.bbb.service.ClpSerializer.getServletContextName(),
					BBBParticipantPersistence.class.getName());

			ReferenceRegistry.registerReference(BBBParticipantUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(BBBParticipantPersistence persistence) {
	}

	private static BBBParticipantPersistence _persistence;
}