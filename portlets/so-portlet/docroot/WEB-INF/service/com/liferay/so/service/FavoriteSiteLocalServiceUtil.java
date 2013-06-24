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

package com.liferay.so.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the favorite site local service. This utility wraps {@link com.liferay.so.service.impl.FavoriteSiteLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSiteLocalService
 * @see com.liferay.so.service.base.FavoriteSiteLocalServiceBaseImpl
 * @see com.liferay.so.service.impl.FavoriteSiteLocalServiceImpl
 * @generated
 */
public class FavoriteSiteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.so.service.impl.FavoriteSiteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the favorite site to the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite addFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addFavoriteSite(favoriteSite);
	}

	/**
	* Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	*
	* @param favoriteSiteId the primary key for the new favorite site
	* @return the new favorite site
	*/
	public static com.liferay.so.model.FavoriteSite createFavoriteSite(
		long favoriteSiteId) {
		return getService().createFavoriteSite(favoriteSiteId);
	}

	/**
	* Deletes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site that was removed
	* @throws PortalException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite deleteFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFavoriteSite(favoriteSiteId);
	}

	/**
	* Deletes the favorite site from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite deleteFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteFavoriteSite(favoriteSite);
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

	public static com.liferay.so.model.FavoriteSite fetchFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchFavoriteSite(favoriteSiteId);
	}

	/**
	* Returns the favorite site with the primary key.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site
	* @throws PortalException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite getFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSite(favoriteSiteId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.so.model.FavoriteSite> getFavoriteSites(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSites(start, end);
	}

	/**
	* Returns the number of favorite sites.
	*
	* @return the number of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public static int getFavoriteSitesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSitesCount();
	}

	/**
	* Updates the favorite site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite updateFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFavoriteSite(favoriteSite);
	}

	/**
	* Updates the favorite site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @param merge whether to merge the favorite site with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the favorite site that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.so.model.FavoriteSite updateFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateFavoriteSite(favoriteSite, merge);
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

	public static com.liferay.so.model.FavoriteSite addFavoriteSite(
		long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addFavoriteSite(userId, groupId);
	}

	public static void deleteFavoriteSites(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFavoriteSites(userId, groupId);
	}

	public static java.util.List<com.liferay.so.model.FavoriteSite> getFavoriteSites(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSites(userId, start, end);
	}

	public static java.util.List<java.lang.Object[]> getFavoriteSites(
		long userId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSites(userId, name, start, end);
	}

	public static int getFavoriteSitesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSitesCount(userId);
	}

	public static int getFavoriteSitesCount(long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getFavoriteSitesCount(userId, name);
	}

	public static boolean isFavoriteSite(long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().isFavoriteSite(favoriteSiteId);
	}

	public static boolean isFavoriteSite(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().isFavoriteSite(userId, groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static FavoriteSiteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					FavoriteSiteLocalService.class.getName());

			if (invokableLocalService instanceof FavoriteSiteLocalService) {
				_service = (FavoriteSiteLocalService)invokableLocalService;
			}
			else {
				_service = new FavoriteSiteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(FavoriteSiteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(FavoriteSiteLocalService service) {
	}

	private static FavoriteSiteLocalService _service;
}