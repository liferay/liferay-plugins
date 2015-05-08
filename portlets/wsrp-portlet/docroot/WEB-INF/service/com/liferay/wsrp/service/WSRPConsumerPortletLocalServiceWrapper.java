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

package com.liferay.wsrp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WSRPConsumerPortletLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletLocalService
 * @generated
 */
@ProviderType
public class WSRPConsumerPortletLocalServiceWrapper
	implements WSRPConsumerPortletLocalService,
		ServiceWrapper<WSRPConsumerPortletLocalService> {
	public WSRPConsumerPortletLocalServiceWrapper(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		_wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerId,
			name, portletHandle, serviceContext);
	}

	/**
	* Adds the w s r p consumer portlet to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was added
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		java.lang.String wsrpConsumerUuid, java.lang.String name,
		java.lang.String portletHandle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.addWSRPConsumerPortlet(wsrpConsumerUuid,
			name, portletHandle, serviceContext);
	}

	/**
	* Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	*
	* @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	* @return the new w s r p consumer portlet
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return _wsrpConsumerPortletLocalService.createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the w s r p consumer portlet from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	/**
	* Deletes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws PortalException if a w s r p consumer portlet with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet deleteWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	@Override
	public void deleteWSRPConsumerPortlet(
		java.lang.String wsrpConsumerPortletUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	@Override
	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerPortletLocalService.deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	@Override
	public void destroyWSRPConsumerPortlet(long wsrpConsumerPortletId,
		java.lang.String wsrpConsumerPortletUuid, java.lang.String url) {
		_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlet(wsrpConsumerPortletId,
			wsrpConsumerPortletUuid, url);
	}

	@Override
	public void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerPortletLocalService.destroyWSRPConsumerPortlets();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _wsrpConsumerPortletLocalService.dynamicQuery();
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
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _wsrpConsumerPortletLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _wsrpConsumerPortletLocalService.dynamicQueryCount(dynamicQuery);
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
		return _wsrpConsumerPortletLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return _wsrpConsumerPortletLocalService.fetchWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	* Returns the w s r p consumer portlet with the matching UUID and company.
	*
	* @param uuid the w s r p consumer portlet's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet fetchWSRPConsumerPortletByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _wsrpConsumerPortletLocalService.fetchWSRPConsumerPortletByUuidAndCompanyId(uuid,
			companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _wsrpConsumerPortletLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _wsrpConsumerPortletLocalService.getBeanIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portal.kernel.lar.PortletDataContext portletDataContext) {
		return _wsrpConsumerPortletLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlet(wsrpConsumerId,
			portletHandle);
	}

	/**
	* Returns the w s r p consumer portlet with the primary key.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet
	* @throws PortalException if a w s r p consumer portlet with the primary key could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		java.lang.String wsrpConsumerPortletUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	/**
	* Returns the w s r p consumer portlet with the matching UUID and company.
	*
	* @param uuid the w s r p consumer portlet's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer portlet
	* @throws PortalException if a matching w s r p consumer portlet could not be found
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortletByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of all the w s r p consumer portlets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumer portlets
	* @param end the upper bound of the range of w s r p consumer portlets (not inclusive)
	* @return the range of w s r p consumer portlets
	*/
	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end) {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end) {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortlets(wsrpConsumerId,
			start, end);
	}

	/**
	* Returns the number of w s r p consumer portlets.
	*
	* @return the number of w s r p consumer portlets
	*/
	@Override
	public int getWSRPConsumerPortletsCount() {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount();
	}

	@Override
	public int getWSRPConsumerPortletsCount(long wsrpConsumerId) {
		return _wsrpConsumerPortletLocalService.getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	@Override
	public void initFailedWSRPConsumerPortlets() {
		_wsrpConsumerPortletLocalService.initFailedWSRPConsumerPortlets();
	}

	@Override
	public void initWSRPConsumerPortlet(long companyId, long wsrpConsumerId,
		long wsrpConsumerPortletId, java.lang.String wsrpConsumerPortletUuid,
		java.lang.String name, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		_wsrpConsumerPortletLocalService.initWSRPConsumerPortlet(companyId,
			wsrpConsumerId, wsrpConsumerPortletId, wsrpConsumerPortletUuid,
			name, portletHandle);
	}

	@Override
	public void initWSRPConsumerPortlets() {
		_wsrpConsumerPortletLocalService.initWSRPConsumerPortlets();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _wsrpConsumerPortletLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_wsrpConsumerPortletLocalService.setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the w s r p consumer portlet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was updated
	*/
	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	@Override
	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _wsrpConsumerPortletLocalService.updateWSRPConsumerPortlet(wsrpConsumerPortletId,
			name);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public WSRPConsumerPortletLocalService getWrappedWSRPConsumerPortletLocalService() {
		return _wsrpConsumerPortletLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedWSRPConsumerPortletLocalService(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		_wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	@Override
	public WSRPConsumerPortletLocalService getWrappedService() {
		return _wsrpConsumerPortletLocalService;
	}

	@Override
	public void setWrappedService(
		WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		_wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	private WSRPConsumerPortletLocalService _wsrpConsumerPortletLocalService;
}