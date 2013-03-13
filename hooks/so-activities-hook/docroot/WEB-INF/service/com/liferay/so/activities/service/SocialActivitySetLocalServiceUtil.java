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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the social activity set local service. This utility wraps {@link com.liferay.so.activities.service.impl.SocialActivitySetLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySetLocalService
 * @see com.liferay.so.activities.service.base.SocialActivitySetLocalServiceBaseImpl
 * @see com.liferay.so.activities.service.impl.SocialActivitySetLocalServiceImpl
 * @generated
 */
public class SocialActivitySetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.activities.service.impl.SocialActivitySetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the social activity set to the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet addSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSocialActivitySet(socialActivitySet);
	}

	/**
	* Creates a new social activity set with the primary key. Does not add the social activity set to the database.
	*
	* @param activitySetId the primary key for the new social activity set
	* @return the new social activity set
	*/
	public static com.liferay.so.activities.model.SocialActivitySet createSocialActivitySet(
		long activitySetId) {
		return getService().createSocialActivitySet(activitySetId);
	}

	/**
	* Deletes the social activity set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set that was removed
	* @throws PortalException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet deleteSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSocialActivitySet(activitySetId);
	}

	/**
	* Deletes the social activity set from the database. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet deleteSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSocialActivitySet(socialActivitySet);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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

	public static com.liferay.so.activities.model.SocialActivitySet fetchSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSocialActivitySet(activitySetId);
	}

	/**
	* Returns the social activity set with the primary key.
	*
	* @param activitySetId the primary key of the social activity set
	* @return the social activity set
	* @throws PortalException if a social activity set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet getSocialActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySet(activitySetId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.so.activities.model.SocialActivitySet> getSocialActivitySets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySets(start, end);
	}

	/**
	* Returns the number of social activity sets.
	*
	* @return the number of social activity sets
	* @throws SystemException if a system exception occurred
	*/
	public static int getSocialActivitySetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSocialActivitySetsCount();
	}

	/**
	* Updates the social activity set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @return the social activity set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet updateSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSocialActivitySet(socialActivitySet);
	}

	/**
	* Updates the social activity set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialActivitySet the social activity set
	* @param merge whether to merge the social activity set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the social activity set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.activities.model.SocialActivitySet updateSocialActivitySet(
		com.liferay.so.activities.model.SocialActivitySet socialActivitySet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSocialActivitySet(socialActivitySet, merge);
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

	public static com.liferay.so.activities.model.SocialActivitySet addActivitySet(
		long userId, long activityId, java.lang.String className, long classPK,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addActivitySet(userId, activityId, className, classPK, type);
	}

	public static com.liferay.so.activities.model.SocialActivitySet decrementActivityCount(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().decrementActivityCount(activitySetId);
	}

	public static com.liferay.so.activities.model.SocialActivitySet deleteActivitySet(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteActivitySet(activitySetId);
	}

	public static com.liferay.so.activities.model.SocialActivitySet deleteActivitySet(
		com.liferay.so.activities.model.SocialActivitySet activitySet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteActivitySet(activitySet);
	}

	public static com.liferay.so.activities.model.SocialActivitySet incrementActivityCount(
		long activitySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().incrementActivityCount(activitySetId);
	}

	public static void clearService() {
		_service = null;
	}

	public static SocialActivitySetLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SocialActivitySetLocalService.class.getName());

			if (invokableLocalService instanceof SocialActivitySetLocalService) {
				_service = (SocialActivitySetLocalService)invokableLocalService;
			}
			else {
				_service = new SocialActivitySetLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SocialActivitySetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SocialActivitySetLocalService service) {
	}

	private static SocialActivitySetLocalService _service;
}