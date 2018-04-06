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

import com.liferay.meeting.webex.model.WebExSite;

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
 * Provides the local service interface for WebExSite. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Anant Singh
 * @see WebExSiteLocalServiceUtil
 * @see com.liferay.meeting.webex.service.base.WebExSiteLocalServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExSiteLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WebExSiteLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExSiteLocalServiceUtil} to access the web ex site local service. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExSiteLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the web ex site to the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WebExSite addWebExSite(WebExSite webExSite);

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public WebExSite createWebExSite(long webExSiteId);

	/**
	* Deletes the web ex site from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public WebExSite deleteWebExSite(WebExSite webExSite)
		throws PortalException;

	/**
	* Deletes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws PortalException if a web ex site with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public WebExSite deleteWebExSite(long webExSiteId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite fetchSiteKeyWebExSite(long siteKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite fetchWebExSite(long webExSiteId);

	/**
	* Returns the web ex site matching the UUID and group.
	*
	* @param uuid the web ex site's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite fetchWebExSiteByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite getSiteKeyWebExSite(long siteKey)
		throws PortalException;

	/**
	* Returns the web ex site with the primary key.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws PortalException if a web ex site with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite getWebExSite(long webExSiteId) throws PortalException;

	/**
	* Returns the web ex site matching the UUID and group.
	*
	* @param uuid the web ex site's UUID
	* @param groupId the primary key of the group
	* @return the matching web ex site
	* @throws PortalException if a matching web ex site could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WebExSite getWebExSiteByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Updates the web ex site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public WebExSite updateWebExSite(WebExSite webExSite);

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
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWebExSitesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSites(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSites(long groupId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSites(long groupId, int start, int end,
		OrderByComparator obc);

	/**
	* Returns all the web ex sites matching the UUID and company.
	*
	* @param uuid the UUID of the web ex sites
	* @param companyId the primary key of the company
	* @return the matching web ex sites, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSitesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WebExSite> getWebExSitesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<WebExSite> orderByComparator);

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

	public void addWebExSite(long userId, long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		ServiceContext serviceContext) throws PortalException;

	public void updateWebExSite(long webExSiteId, java.lang.String apiURL,
		java.lang.String login, java.lang.String password,
		ServiceContext serviceContext) throws PortalException;
}