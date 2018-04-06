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
 * Provides the local service utility for WebExAccount. This utility wraps
 * {@link com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Anant Singh
 * @see WebExAccountLocalService
 * @see com.liferay.meeting.webex.service.base.WebExAccountLocalServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl
 * @generated
 */
@ProviderType
public class WebExAccountLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the web ex account to the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was added
	*/
	public static com.liferay.meeting.webex.model.WebExAccount addWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return getService().addWebExAccount(webExAccount);
	}

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public static com.liferay.meeting.webex.model.WebExAccount createWebExAccount(
		long webExAccountId) {
		return getService().createWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was removed
	* @throws PortalException
	*/
	public static com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWebExAccount(webExAccount);
	}

	/**
	* Deletes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWebExAccount(webExAccountId);
	}

	public static com.liferay.meeting.webex.model.WebExAccount fetchWebExAccount(
		long webExAccountId) {
		return getService().fetchWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExAccount fetchWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWebExAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the web ex account with the primary key.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account
	* @throws PortalException if a matching web ex account could not be found
	*/
	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWebExAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was updated
	*/
	public static com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return getService().updateWebExAccount(webExAccount);
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
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	*/
	public static int getWebExAccountsCount() {
		return getService().getWebExAccountsCount();
	}

	public static int getWebExSiteWebExAccountsCount(long groupId,
		long webExSiteId) {
		return getService().getWebExSiteWebExAccountsCount(groupId, webExSiteId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of web ex accounts
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts(
		int start, int end) {
		return getService().getWebExAccounts(start, end);
	}

	/**
	* Returns all the web ex accounts matching the UUID and company.
	*
	* @param uuid the UUID of the web ex accounts
	* @param companyId the primary key of the company
	* @return the matching web ex accounts, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getWebExAccountsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of web ex accounts matching the UUID and company.
	*
	* @param uuid the UUID of the web ex accounts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching web ex accounts, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.meeting.webex.model.WebExAccount> orderByComparator) {
		return getService()
				   .getWebExAccountsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId) {
		return getService().getWebExSiteWebExAccounts(groupId, webExSiteId);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end) {
		return getService()
				   .getWebExSiteWebExAccounts(groupId, webExSiteId, start, end);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getWebExSiteWebExAccounts(groupId, webExSiteId, start, end,
			obc);
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

	public static void addWebExAccount(long userId, long groupId,
		long webExSiteId, java.lang.String login, java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addWebExAccount(userId, groupId, webExSiteId, login, password,
			serviceContext);
	}

	public static void deleteWebExSiteWebExAccounts(long groupId,
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteWebExSiteWebExAccounts(groupId, webExSiteId);
	}

	public static void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateWebExAccount(webExAccountId, password, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExAccountLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExAccountLocalService.class.getName());

			if (invokableLocalService instanceof WebExAccountLocalService) {
				_service = (WebExAccountLocalService)invokableLocalService;
			}
			else {
				_service = new WebExAccountLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WebExAccountLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static WebExAccountLocalService _service;
}