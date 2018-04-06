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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WebExAccount. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Anant Singh
 * @see WebExAccountLocalServiceUtil
 * @see com.liferay.meeting.webex.service.base.WebExAccountLocalServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WebExAccountLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExAccountLocalServiceUtil} to access the web ex account local service. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the web ex account to the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WebExAccount addWebExAccount(WebExAccount webExAccount);

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public WebExAccount createWebExAccount(long webExAccountId);

	/**
	* Deletes the web ex account from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public WebExAccount deleteWebExAccount(WebExAccount webExAccount)
		throws PortalException;

	/**
	* Deletes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WebExAccount deleteWebExAccount(long webExAccountId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExAccount fetchWebExAccount(long webExAccountId);

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExAccount fetchWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the web ex account with the primary key.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExAccount getWebExAccount(long webExAccountId)
		throws PortalException;

	/**
	* Returns the web ex account matching the UUID and group.
	*
	* @param uuid the web ex account's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex account
	* @throws PortalException if a matching web ex account could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExAccount getWebExAccountByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WebExAccount updateWebExAccount(WebExAccount webExAccount);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWebExAccountsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExAccounts(int start, int end);

	/**
	* Returns all the web ex accounts matching the UUID and company.
	*
	* @param uuid the UUID of the web ex accounts
	* @param companyId the primary key of the company
	* @return the matching web ex accounts, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExAccountsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<WebExAccount> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExSiteWebExAccounts(long groupId,
		long webExSiteId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExSiteWebExAccounts(long groupId,
		long webExSiteId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExAccount> getWebExSiteWebExAccounts(long groupId,
		long webExSiteId, int start, int end, OrderByComparator obc);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void addWebExAccount(long userId, long groupId, long webExSiteId,
		java.lang.String login, java.lang.String password,
		ServiceContext serviceContext) throws PortalException;

	public void deleteWebExSiteWebExAccounts(long groupId, long webExSiteId)
		throws PortalException;

	public void updateWebExAccount(long webExAccountId,
		java.lang.String password, ServiceContext serviceContext)
		throws PortalException;
}