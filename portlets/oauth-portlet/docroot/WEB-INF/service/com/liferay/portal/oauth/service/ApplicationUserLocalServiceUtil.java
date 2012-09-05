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

package com.liferay.portal.oauth.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the application user local service. This utility wraps {@link com.liferay.portal.oauth.service.impl.ApplicationUserLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ApplicationUserLocalService
 * @see com.liferay.portal.oauth.service.base.ApplicationUserLocalServiceBaseImpl
 * @see com.liferay.portal.oauth.service.impl.ApplicationUserLocalServiceImpl
 * @generated
 */
public class ApplicationUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.oauth.service.impl.ApplicationUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the application user to the database. Also notifies the appropriate model listeners.
	*
	* @param applicationUser the application user
	* @return the application user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser addApplicationUser(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addApplicationUser(applicationUser);
	}

	/**
	* Creates a new application user with the primary key. Does not add the application user to the database.
	*
	* @param oaauId the primary key for the new application user
	* @return the new application user
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser createApplicationUser(
		long oaauId) {
		return getService().createApplicationUser(oaauId);
	}

	/**
	* Deletes the application user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oaauId the primary key of the application user
	* @return the application user that was removed
	* @throws PortalException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser deleteApplicationUser(
		long oaauId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteApplicationUser(oaauId);
	}

	/**
	* Deletes the application user from the database. Also notifies the appropriate model listeners.
	*
	* @param applicationUser the application user
	* @return the application user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser deleteApplicationUser(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteApplicationUser(applicationUser);
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

	public static com.liferay.portal.oauth.model.ApplicationUser fetchApplicationUser(
		long oaauId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchApplicationUser(oaauId);
	}

	/**
	* Returns the application user with the primary key.
	*
	* @param oaauId the primary key of the application user
	* @return the application user
	* @throws PortalException if a application user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser getApplicationUser(
		long oaauId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUser(oaauId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the application users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of application users
	* @param end the upper bound of the range of application users (not inclusive)
	* @return the range of application users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getApplicationUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsers(start, end);
	}

	/**
	* Returns the number of application users.
	*
	* @return the number of application users
	* @throws SystemException if a system exception occurred
	*/
	public static int getApplicationUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsersCount();
	}

	/**
	* Updates the application user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicationUser the application user
	* @return the application user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser updateApplicationUser(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApplicationUser(applicationUser);
	}

	/**
	* Updates the application user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicationUser the application user
	* @param merge whether to merge the application user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the application user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser updateApplicationUser(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateApplicationUser(applicationUser, merge);
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

	/**
	* Add new user's authorization for an existing application that is
	* registered to use OAuth feature. All optional fields will be set to null
	* or initial value (depending on data type). Method creates necessary
	* resources used later by permissions algorithm.
	*
	* @param authorized
	* @param applicationId
	* @param userId
	* @param accessSecret
	* @param accessToken
	* @param serviceContext
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser addApplicationUser(
		long userId, long applicationId, java.lang.String accessToken,
		java.lang.String accessSecret, boolean authorized,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addApplicationUser(userId, applicationId, accessToken,
			accessSecret, authorized, serviceContext);
	}

	public static com.liferay.portal.oauth.model.ApplicationUser deleteApplicationUser(
		long userId, long applicationId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteApplicationUser(userId, applicationId, serviceContext);
	}

	public static com.liferay.portal.oauth.model.ApplicationUser deleteApplicationUser(
		com.liferay.portal.oauth.model.ApplicationUser applicationUser,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteApplicationUser(applicationUser, serviceContext);
	}

	/**
	* Return ApplicationUser with accessToken given by parameter.
	*
	* @param accessToken
	* @return
	* @throws SystemException
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser getApplicationUserByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUserByAccessToken(accessToken);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getApplicationUsers(
		long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsers(applicationId);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getApplicationUsers(
		long applicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationUsers(applicationId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getApplicationUsersByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsersByUserId(userId);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getApplicationUsersByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getApplicationUsersByUserId(userId, start, end,
			orderByComparator);
	}

	public static int getApplicationUsersByUserIdCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsersByUserIdCount(userId);
	}

	public static int getApplicationUsersCount(long applicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getApplicationUsersCount(applicationId);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getAuthorizedApplicationUsersByOwnerId(
		long ownerId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAuthorizedApplicationUsersByOwnerId(ownerId, authorized,
			start, end, orderByComparator);
	}

	public static int getAuthorizedApplicationUsersByOwnerIdCount(
		long ownerId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAuthorizedApplicationUsersByOwnerIdCount(ownerId,
			authorized);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getAuthorizedApplicationUsersByUserId(
		long userId, boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAuthorizedApplicationUsersByUserId(userId, authorized);
	}

	public static java.util.List<com.liferay.portal.oauth.model.ApplicationUser> getAuthorizedApplicationUsersByUserId(
		long userId, boolean authorized, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAuthorizedApplicationUsersByUserId(userId, authorized,
			start, end, orderByComparator);
	}

	public static int getAuthorizedApplicationUsersByUserIdCount(long userId,
		boolean authorized)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAuthorizedApplicationUsersByUserIdCount(userId,
			authorized);
	}

	/**
	* Update user's authorization for an existing application that is
	* registered to use OAuth feature. If entity doesn't exist new one (with
	* resources for later permissions check) will be created.
	*
	* @param authorized
	if set to false application access rights are revoked
	* @param oAuthApplicationId
	* @param userId
	* @param accessSecret
	* @param accessToken
	* @param serviceContext
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	public static com.liferay.portal.oauth.model.ApplicationUser updateApplicationUser(
		long userId, long applicationId, java.lang.String accessToken,
		java.lang.String accessSecret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateApplicationUser(userId, applicationId, accessToken,
			accessSecret, serviceContext);
	}

	public static com.liferay.portal.oauth.model.ApplicationUser updateAuthorized(
		long userId, long applicationId, boolean authorized,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAuthorized(userId, applicationId, authorized,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ApplicationUserLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ApplicationUserLocalService.class.getName());

			if (invokableLocalService instanceof ApplicationUserLocalService) {
				_service = (ApplicationUserLocalService)invokableLocalService;
			}
			else {
				_service = new ApplicationUserLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ApplicationUserLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(ApplicationUserLocalService service) {
	}

	private static ApplicationUserLocalService _service;
}