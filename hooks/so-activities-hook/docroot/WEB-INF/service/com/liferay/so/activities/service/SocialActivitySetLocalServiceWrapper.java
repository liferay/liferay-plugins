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

package com.liferay.so.activities.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SocialActivitySetLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SocialActivitySetLocalService
 * @generated
 */
public class SocialActivitySetLocalServiceWrapper
	implements SocialActivitySetLocalService,
		ServiceWrapper<SocialActivitySetLocalService> {
	public SocialActivitySetLocalServiceWrapper(
		SocialActivitySetLocalService socialActivitySetLocalService) {
		_socialActivitySetLocalService = socialActivitySetLocalService;
	}

	/**
	* Adds the social activity set to the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet addSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.addSocialActivitySet(socialActivitySet);
	}

	/**
	* Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	*
	* @param activitySetId the primary key for the new social activity set
	* @return the new social activity set
	*/
	public com.liferay.so.activities.model.SocialActivitySet createSocialActivitySet(
		long activitySetId) {
		return _socialActivitySetLocalService.createSocialActivitySet(activitySetId);
	}

	/**
	* Deletes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set that was removed
	* @throws PortalException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet deleteSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.deleteSocialActivitySet(activitySetId);
	}

	/**
	* Deletes the social activity set from the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet deleteSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.deleteSocialActivitySet(socialActivitySet);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _socialActivitySetLocalService.dynamicQuery();
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
		return _socialActivitySetLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
		return _socialActivitySetLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
		return _socialActivitySetLocalService.dynamicQuery(dynamicQuery, start,
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
		return _socialActivitySetLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.so.activities.model.SocialActivitySet fetchSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.fetchSocialActivitySet(activitySetId);
	}

	/**
	* Returns the social activity set with the primary key.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set
	* @throws PortalException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet getSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.getSocialActivitySet(activitySetId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the social activity sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of social activity sets
	* @param end the upper bound of the range of social activity sets (not inclusive)
	* @return the range of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.activities.model.SocialActivitySet> getSocialActivitySets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.getSocialActivitySets(start, end);
	}

	/**
	* Returns the number of social activity sets.
	*
	* @return the number of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public int getSocialActivitySetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.getSocialActivitySetsCount();
	}

	/**
	* Updates the social activity set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet updateSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.updateSocialActivitySet(socialActivitySet);
	}

	/**
	* Updates the social activity set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @param merge whether to merge the social activity set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the social activity set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.activities.model.SocialActivitySet updateSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.updateSocialActivitySet(socialActivitySet,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _socialActivitySetLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialActivitySetLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _socialActivitySetLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.so.activities.model.SocialActivitySet addActivitySet(
		long userId, long activityId, java.lang.String className, long classPK,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.addActivitySet(userId,
			activityId, className, classPK, type);
	}

	public com.liferay.so.activities.model.SocialActivitySet decrementActivityCount(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.decrementActivityCount(activitySetId);
	}

	public com.liferay.so.activities.model.SocialActivitySet deleteActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.deleteActivitySet(activitySetId);
	}

	public com.liferay.so.activities.model.SocialActivitySet deleteActivitySet(
		com.liferay.so.activities.model.SocialActivitySet activitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.deleteActivitySet(activitySet);
	}

	public com.liferay.so.activities.model.SocialActivitySet incrementActivityCount(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialActivitySetLocalService.incrementActivityCount(activitySetId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SocialActivitySetLocalService getWrappedSocialActivitySetLocalService() {
		return _socialActivitySetLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSocialActivitySetLocalService(
		SocialActivitySetLocalService socialActivitySetLocalService) {
		_socialActivitySetLocalService = socialActivitySetLocalService;
	}

	public SocialActivitySetLocalService getWrappedService() {
		return _socialActivitySetLocalService;
	}

	public void setWrappedService(
		SocialActivitySetLocalService socialActivitySetLocalService) {
		_socialActivitySetLocalService = socialActivitySetLocalService;
	}

	private SocialActivitySetLocalService _socialActivitySetLocalService;
}