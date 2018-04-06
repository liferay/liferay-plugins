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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WebExAccountLocalService}.
 *
 * @author Anant Singh
 * @see WebExAccountLocalService
 * @generated
 */
@ProviderType
public class WebExAccountLocalServiceWrapper implements WebExAccountLocalService,
	ServiceWrapper<WebExAccountLocalService> {
	public WebExAccountLocalServiceWrapper(
		WebExAccountLocalService webExAccountLocalService) {
		_webExAccountLocalService = webExAccountLocalService;
	}

	/**
	* Adds the web ex account to the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was added
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount addWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return _webExAccountLocalService.addWebExAccount(webExAccount);
	}

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount createWebExAccount(
		long webExAccountId) {
		return _webExAccountLocalService.createWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.deleteWebExAccount(webExAccount);
	}

	/**
	* Deletes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.deleteWebExAccount(webExAccountId);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExAccount fetchWebExAccount(
		long webExAccountId) {
		return _webExAccountLocalService.fetchWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount fetchWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _webExAccountLocalService.fetchWebExAccountByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the web ex account with the primary key.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.getWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account
	* @throws PortalException if a matching web ex account could not be found
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount getWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.getWebExAccountByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was updated
	*/
	@Override
	public com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount) {
		return _webExAccountLocalService.updateWebExAccount(webExAccount);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _webExAccountLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _webExAccountLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _webExAccountLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _webExAccountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _webExAccountLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	*/
	@Override
	public int getWebExAccountsCount() {
		return _webExAccountLocalService.getWebExAccountsCount();
	}

	@Override
	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId) {
		return _webExAccountLocalService.getWebExSiteWebExAccountsCount(groupId,
			webExSiteId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExAccountLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _webExAccountLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _webExAccountLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _webExAccountLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _webExAccountLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts(
		int start, int end) {
		return _webExAccountLocalService.getWebExAccounts(start, end);
	}

	/**
	* Returns all the web ex accounts matching the UUID and company.
	*
	* @param uuid the UUID of the web ex accounts
	* @param companyId the primary key of the company
	* @return the matching web ex accounts, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _webExAccountLocalService.getWebExAccountsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.meeting.webex.model.WebExAccount> orderByComparator) {
		return _webExAccountLocalService.getWebExAccountsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId) {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId);
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end) {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId, start, end);
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _webExAccountLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _webExAccountLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void addWebExAccount(long userId, long groupId, long webExSiteId,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_webExAccountLocalService.addWebExAccount(userId, groupId, webExSiteId,
			login, password, serviceContext);
	}

	@Override
	public void deleteWebExSiteWebExAccounts(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_webExAccountLocalService.deleteWebExSiteWebExAccounts(groupId,
			webExSiteId);
	}

	@Override
	public void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_webExAccountLocalService.updateWebExAccount(webExAccountId, password,
			serviceContext);
	}

	@Override
	public WebExAccountLocalService getWrappedService() {
		return _webExAccountLocalService;
	}

	@Override
	public void setWrappedService(
		WebExAccountLocalService webExAccountLocalService) {
		_webExAccountLocalService = webExAccountLocalService;
	}

	private WebExAccountLocalService _webExAccountLocalService;
}