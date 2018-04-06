/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.meeting.webex.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for WebExSite. This utility wraps
 * {@link com.liferay.meeting.webex.service.impl.WebExSiteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Anant Singh
 * @see WebExSiteLocalService
 * @see com.liferay.meeting.webex.service.base.WebExSiteLocalServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExSiteLocalServiceImpl
 * @generated
 */
@ProviderType
public class WebExSiteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExSiteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the web ex site to the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was added
	*/
	public static com.liferay.meeting.webex.model.WebExSite addWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite) {
		return getService().addWebExSite(webExSite);
	}

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public static com.liferay.meeting.webex.model.WebExSite createWebExSite(
		long webExSiteId) {
		return getService().createWebExSite(webExSiteId);
	}

	/**
	* Deletes the web ex site from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was removed
	* @throws PortalException
	*/
	public static com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWebExSite(webExSite);
	}

	/**
	* Deletes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws PortalException if a web ex site with the primary key could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWebExSite(webExSiteId);
	}

	public static com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey) {
		return getService().fetchSiteKeyWebExSite(siteKey);
	}

	public static com.liferay.meeting.webex.model.WebExSite fetchWebExSite(
		long webExSiteId) {
		return getService().fetchWebExSite(webExSiteId);
	}

	/**
	* Returns the web ex site matching the UUID and group.
	*
	* @param uuid the web ex site's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExSite fetchWebExSiteByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWebExSiteByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.meeting.webex.model.WebExSite getSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSiteKeyWebExSite(siteKey);
	}

	/**
	* Returns the web ex site with the primary key.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws PortalException if a web ex site with the primary key could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExSite(webExSiteId);
	}

	/**
	* Returns the web ex site matching the UUID and group.
	*
	* @param uuid the web ex site's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex site
	* @throws PortalException if a matching web ex site could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExSite getWebExSiteByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExSiteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the web ex site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was updated
	*/
	public static com.liferay.meeting.webex.model.WebExSite updateWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite) {
		return getService().updateWebExSite(webExSite);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	*/
	public static int getWebExSitesCount() {
		return getService().getWebExSitesCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of web ex sites
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		int start, int end) {
		return getService().getWebExSites(start, end);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end) {
		return getService().getWebExSites(groupId, start, end);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getWebExSites(groupId, start, end, obc);
	}

	/**
	* Returns all the web ex sites matching the UUID and company.
	*
	* @param uuid the UUID of the web ex sites
	* @param companyId the primary key of the company
	* @return the matching web ex sites, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSitesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getWebExSitesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of web ex sites matching the UUID and company.
	*
	* @param uuid the UUID of the web ex sites
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching web ex sites, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSitesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.meeting.webex.model.WebExSite> orderByComparator) {
		return getService()
				   .getWebExSitesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void addWebExSite(long userId, long groupId,
		java.lang.String name, java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addWebExSite(userId, groupId, name, apiURL, login, password,
			partnerKey, siteKey, serviceContext);
	}

	public static void updateWebExSite(long webExSiteId,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateWebExSite(webExSiteId, apiURL, login, password,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExSiteLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExSiteLocalService.class.getName());

			if (invokableLocalService instanceof WebExSiteLocalService) {
				_service = (WebExSiteLocalService)invokableLocalService;
			}
			else {
				_service = new WebExSiteLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WebExSiteLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WebExSiteLocalService _service;
}