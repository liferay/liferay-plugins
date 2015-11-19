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
 * Provides the local service utility for WSRPConsumer. This utility wraps
 * {@link com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerLocalService
 * @see com.liferay.wsrp.service.base.WSRPConsumerLocalServiceBaseImpl
 * @see com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl
 * @generated
 */
@ProviderType
public class WSRPConsumerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		long companyId, java.lang.String adminPortletId, java.lang.String name,
		java.lang.String url, java.lang.String forwardCookies,
		java.lang.String forwardHeaders, java.lang.String markupCharacterSets,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addWSRPConsumer(companyId, adminPortletId, name, url,
			forwardCookies, forwardHeaders, markupCharacterSets, serviceContext);
	}

	/**
	* Adds the w s r p consumer to the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was added
	*/
	public static com.liferay.wsrp.model.WSRPConsumer addWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return getService().addWSRPConsumer(wsrpConsumer);
	}

	/**
	* Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	*
	* @param wsrpConsumerId the primary key for the new w s r p consumer
	* @return the new w s r p consumer
	*/
	public static com.liferay.wsrp.model.WSRPConsumer createWSRPConsumer(
		long wsrpConsumerId) {
		return getService().createWSRPConsumer(wsrpConsumerId);
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
	* Deletes the w s r p consumer from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException
	*/
	public static com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWSRPConsumer(wsrpConsumer);
	}

	/**
	* Deletes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer that was removed
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumer deleteWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWSRPConsumer(wsrpConsumerId);
	}

	public static void deleteWSRPConsumers(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteWSRPConsumers(companyId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.wsrp.model.WSRPConsumer fetchWSRPConsumer(
		long wsrpConsumerId) {
		return getService().fetchWSRPConsumer(wsrpConsumerId);
	}

	/**
	* Returns the w s r p consumer with the matching UUID and company.
	*
	* @param uuid the w s r p consumer's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumer fetchWSRPConsumerByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchWSRPConsumerByUuidAndCompanyId(uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.portlet.exportimport.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the w s r p consumer with the primary key.
	*
	* @param wsrpConsumerId the primary key of the w s r p consumer
	* @return the w s r p consumer
	* @throws PortalException if a w s r p consumer with the primary key could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumer(wsrpConsumerId);
	}

	public static com.liferay.wsrp.model.WSRPConsumer getWSRPConsumer(
		java.lang.String wsrpConsumerUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumer(wsrpConsumerUuid);
	}

	/**
	* Returns the w s r p consumer with the matching UUID and company.
	*
	* @param uuid the w s r p consumer's UUID
	* @param companyId the primary key of the company
	* @return the matching w s r p consumer
	* @throws PortalException if a matching w s r p consumer could not be found
	*/
	public static com.liferay.wsrp.model.WSRPConsumer getWSRPConsumerByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWSRPConsumerByUuidAndCompanyId(uuid, companyId);
	}

	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		long companyId, int start, int end) {
		return getService().getWSRPConsumers(companyId, start, end);
	}

	/**
	* Returns a range of all the w s r p consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of w s r p consumers
	* @param end the upper bound of the range of w s r p consumers (not inclusive)
	* @return the range of w s r p consumers
	*/
	public static java.util.List<com.liferay.wsrp.model.WSRPConsumer> getWSRPConsumers(
		int start, int end) {
		return getService().getWSRPConsumers(start, end);
	}

	/**
	* Returns the number of w s r p consumers.
	*
	* @return the number of w s r p consumers
	*/
	public static int getWSRPConsumersCount() {
		return getService().getWSRPConsumersCount();
	}

	public static int getWSRPConsumersCount(long companyId) {
		return getService().getWSRPConsumersCount(companyId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.wsrp.model.WSRPConsumer registerWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties,
		java.lang.String registrationHandle)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .registerWSRPConsumer(wsrpConsumerId, adminPortletId,
			registrationProperties, registrationHandle);
	}

	public static void restartConsumer(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().restartConsumer(wsrpConsumerId);
	}

	public static void updateServiceDescription(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateServiceDescription(wsrpConsumerId);
	}

	/**
	* Updates the w s r p consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param wsrpConsumer the w s r p consumer
	* @return the w s r p consumer that was updated
	*/
	public static com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		com.liferay.wsrp.model.WSRPConsumer wsrpConsumer) {
		return getService().updateWSRPConsumer(wsrpConsumer);
	}

	public static com.liferay.wsrp.model.WSRPConsumer updateWSRPConsumer(
		long wsrpConsumerId, java.lang.String adminPortletId,
		java.lang.String name, java.lang.String url,
		java.lang.String forwardCookies, java.lang.String forwardHeaders,
		java.lang.String markupCharacterSets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateWSRPConsumer(wsrpConsumerId, adminPortletId, name,
			url, forwardCookies, forwardHeaders, markupCharacterSets);
	}

	public static void clearService() {
		_service = null;
	}

	public static WSRPConsumerLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WSRPConsumerLocalService.class.getName());

			if (invokableLocalService instanceof WSRPConsumerLocalService) {
				_service = (WSRPConsumerLocalService)invokableLocalService;
			}
			else {
				_service = new WSRPConsumerLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WSRPConsumerLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(WSRPConsumerLocalService service) {
	}

	private static WSRPConsumerLocalService _service;
}