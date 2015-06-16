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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for WSRPConsumerPortlet. This utility wraps
 * {@link com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletLocalService
 * @see com.liferay.wsrp.service.base.WSRPConsumerPortletLocalServiceBaseImpl
 * @see com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl
 * @generated
 */
@ProviderType
public class WSRPConsumerPortletLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addWSRPConsumerPortlet(wsrpConsumerId, name, portletHandle,
			serviceContext);
	}

	/**
	* Adds the w s r p consumer portlet to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was added
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return getService().addWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		java.lang.String wsrpConsumerUuid, java.lang.String name,
		java.lang.String portletHandle,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addWSRPConsumerPortlet(wsrpConsumerUuid, name,
			portletHandle, serviceContext);
	}

	/**
	* Creates a new w s r p consumer portlet with the primary key. Does not add the w s r p consumer portlet to the database.
	*
	* @param wsrpConsumerPortletId the primary key for the new w s r p consumer portlet
	* @return the new w s r p consumer portlet
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return getService().createWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the w s r p consumer portlet from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws PortalException
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	/**
	* Deletes the w s r p consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet that was removed
	* @throws PortalException if a w s r p consumer portlet with the primary key could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet deleteWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static void deleteWSRPConsumerPortlet(
		java.lang.String wsrpConsumerPortletUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	public static void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteWSRPConsumerPortlets(wsrpConsumerId);
	}

	public static void destroyWSRPConsumerPortlet(long wsrpConsumerPortletId,
		java.lang.String wsrpConsumerPortletUuid, java.lang.String url) {
		getService()
			.destroyWSRPConsumerPortlet(wsrpConsumerPortletId,
			wsrpConsumerPortletUuid, url);
	}

	public static void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().destroyWSRPConsumerPortlets();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		return getService().fetchWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	/**
	* Returns the w s r p consumer portlet with the matching UUID and company.
	*
	* @param uuid the w s r p consumer portlet's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer portlet, or <code>null</code> if a matching w s r p consumer portlet could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet fetchWSRPConsumerPortletByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .fetchWSRPConsumerPortletByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerId, portletHandle);
	}

	/**
	* Returns the w s r p consumer portlet with the primary key.
	*
	* @param wsrpConsumerPortletId the primary key of the w s r p consumer portlet
	* @return the w s r p consumer portlet
	* @throws PortalException if a w s r p consumer portlet with the primary key could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletId);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		java.lang.String wsrpConsumerPortletUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumerPortlet(wsrpConsumerPortletUuid);
	}

	/**
	* Returns the w s r p consumer portlet with the matching UUID and company.
	*
	* @param uuid the w s r p consumer portlet's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer portlet
	* @throws PortalException if a matching w s r p consumer portlet could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortletByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getWSRPConsumerPortletByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end) {
		return getService().getWSRPConsumerPortlets(start, end);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end) {
		return getService().getWSRPConsumerPortlets(wsrpConsumerId, start, end);
	}

	/**
	* Returns the number of w s r p consumer portlets.
	*
	* @return the number of w s r p consumer portlets
	*/
	public static int getWSRPConsumerPortletsCount() {
		return getService().getWSRPConsumerPortletsCount();
	}

	public static int getWSRPConsumerPortletsCount(long wsrpConsumerId) {
		return getService().getWSRPConsumerPortletsCount(wsrpConsumerId);
	}

	public static void initFailedWSRPConsumerPortlets() {
		getService().initFailedWSRPConsumerPortlets();
	}

	public static void initWSRPConsumerPortlet(long companyId,
		long wsrpConsumerId, long wsrpConsumerPortletId,
		java.lang.String wsrpConsumerPortletUuid, java.lang.String name,
		java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.initWSRPConsumerPortlet(companyId, wsrpConsumerId,
			wsrpConsumerPortletId, wsrpConsumerPortletUuid, name, portletHandle);
	}

	public static void initWSRPConsumerPortlets() {
		getService().initWSRPConsumerPortlets();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	/**
	* Updates the w s r p consumer portlet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerPortlet the w s r p consumer portlet
	* @return the w s r p consumer portlet that was updated
	*/
	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet) {
		return getService().updateWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public static com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateWSRPConsumerPortlet(wsrpConsumerPortletId, name);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConsumerPortletLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WSRPConsumerPortletLocalService.class.getName());

			if (invokableLocalService instanceof WSRPConsumerPortletLocalService) {
				_service = (WSRPConsumerPortletLocalService)invokableLocalService;
			}
			else {
				_service = new WSRPConsumerPortletLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WSRPConsumerPortletLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(WSRPConsumerPortletLocalService service) {
	}

	private static WSRPConsumerPortletLocalService _service;
}