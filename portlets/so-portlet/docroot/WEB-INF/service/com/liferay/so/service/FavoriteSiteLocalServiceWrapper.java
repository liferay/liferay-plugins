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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FavoriteSiteLocalService}.
 *
 * @author    Brian Wing Shun Chan
 * @see       FavoriteSiteLocalService
 * @generated
 */
public class FavoriteSiteLocalServiceWrapper implements FavoriteSiteLocalService,
	ServiceWrapper<FavoriteSiteLocalService> {
	public FavoriteSiteLocalServiceWrapper(
		FavoriteSiteLocalService favoriteSiteLocalService) {
		_favoriteSiteLocalService = favoriteSiteLocalService;
	}

	/**
	* Adds the favorite site to the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite addFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.addFavoriteSite(favoriteSite);
	}

	/**
	* Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	*
	* @param favoriteSiteId the primary key for the new favorite site
	* @return the new favorite site
	*/
	public com.liferay.so.model.FavoriteSite createFavoriteSite(
		long favoriteSiteId) {
		return _favoriteSiteLocalService.createFavoriteSite(favoriteSiteId);
	}

	/**
	* Deletes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site that was removed
	* @throws PortalException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite deleteFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.deleteFavoriteSite(favoriteSiteId);
	}

	/**
	* Deletes the favorite site from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite deleteFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.deleteFavoriteSite(favoriteSite);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _favoriteSiteLocalService.dynamicQuery();
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
		return _favoriteSiteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _favoriteSiteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _favoriteSiteLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _favoriteSiteLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.so.model.FavoriteSite fetchFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.fetchFavoriteSite(favoriteSiteId);
	}

	/**
	* Returns the favorite site with the primary key.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site
	* @throws PortalException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite getFavoriteSite(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSite(favoriteSiteId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> getFavoriteSites(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSites(start, end);
	}

	/**
	* Returns the number of favorite sites.
	*
	* @return the number of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public int getFavoriteSitesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSitesCount();
	}

	/**
	* Updates the favorite site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param favoriteSite the favorite site
	* @return the favorite site that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite updateFavoriteSite(
		com.liferay.so.model.FavoriteSite favoriteSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.updateFavoriteSite(favoriteSite);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _favoriteSiteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_favoriteSiteLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _favoriteSiteLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.so.model.FavoriteSite addFavoriteSite(long userId,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.addFavoriteSite(userId, groupId);
	}

	public void deleteFavoriteSites(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_favoriteSiteLocalService.deleteFavoriteSites(userId, groupId);
	}

	public java.util.List<com.liferay.so.model.FavoriteSite> getFavoriteSites(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSites(userId, start, end);
	}

	public java.util.List<java.lang.Object[]> getFavoriteSites(long userId,
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSites(userId, name, start,
			end);
	}

	public int getFavoriteSitesCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSitesCount(userId);
	}

	public int getFavoriteSitesCount(long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.getFavoriteSitesCount(userId, name);
	}

	public boolean isFavoriteSite(long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.isFavoriteSite(favoriteSiteId);
	}

	public boolean isFavoriteSite(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSiteLocalService.isFavoriteSite(userId, groupId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public FavoriteSiteLocalService getWrappedFavoriteSiteLocalService() {
		return _favoriteSiteLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedFavoriteSiteLocalService(
		FavoriteSiteLocalService favoriteSiteLocalService) {
		_favoriteSiteLocalService = favoriteSiteLocalService;
	}

	public FavoriteSiteLocalService getWrappedService() {
		return _favoriteSiteLocalService;
	}

	public void setWrappedService(
		FavoriteSiteLocalService favoriteSiteLocalService) {
		_favoriteSiteLocalService = favoriteSiteLocalService;
	}

	private FavoriteSiteLocalService _favoriteSiteLocalService;
}