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

package com.liferay.opensocial.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for Gadget. This utility wraps
 * {@link com.liferay.opensocial.service.impl.GadgetLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see GadgetLocalService
 * @see com.liferay.opensocial.service.base.GadgetLocalServiceBaseImpl
 * @see com.liferay.opensocial.service.impl.GadgetLocalServiceImpl
 * @generated
 */
public class GadgetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.opensocial.service.impl.GadgetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the gadget to the database. Also notifies the appropriate model listeners.
	*
	* @param gadget the gadget
	* @return the gadget that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget addGadget(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addGadget(gadget);
	}

	/**
	* Creates a new gadget with the primary key. Does not add the gadget to the database.
	*
	* @param gadgetId the primary key for the new gadget
	* @return the new gadget
	*/
	public static com.liferay.opensocial.model.Gadget createGadget(
		long gadgetId) {
		return getService().createGadget(gadgetId);
	}

	/**
	* Deletes the gadget with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget that was removed
	* @throws PortalException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget deleteGadget(
		long gadgetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteGadget(gadgetId);
	}

	/**
	* Deletes the gadget from the database. Also notifies the appropriate model listeners.
	*
	* @param gadget the gadget
	* @return the gadget that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget deleteGadget(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteGadget(gadget);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.opensocial.model.Gadget fetchGadget(long gadgetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGadget(gadgetId);
	}

	/**
	* Returns the gadget with the matching UUID and company.
	*
	* @param uuid the gadget's UUID
	* @param companyId the primary key of the company
	* @return the matching gadget, or <code>null</code> if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget fetchGadgetByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGadgetByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns the gadget with the primary key.
	*
	* @param gadgetId the primary key of the gadget
	* @return the gadget
	* @throws PortalException if a gadget with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget getGadget(long gadgetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadget(gadgetId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the gadget with the matching UUID and company.
	*
	* @param uuid the gadget's UUID
	* @param companyId the primary key of the company
	* @return the matching gadget
	* @throws PortalException if a matching gadget could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget getGadgetByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of all the gadgets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.opensocial.model.impl.GadgetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of gadgets
	* @param end the upper bound of the range of gadgets (not inclusive)
	* @return the range of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.opensocial.model.Gadget> getGadgets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgets(start, end);
	}

	/**
	* Returns the number of gadgets.
	*
	* @return the number of gadgets
	* @throws SystemException if a system exception occurred
	*/
	public static int getGadgetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsCount();
	}

	/**
	* Updates the gadget in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param gadget the gadget
	* @return the gadget that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.opensocial.model.Gadget updateGadget(
		com.liferay.opensocial.model.Gadget gadget)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGadget(gadget);
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

	public static com.liferay.opensocial.model.Gadget addGadget(
		long companyId, java.lang.String url,
		java.lang.String portletCategoryNames,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addGadget(companyId, url, portletCategoryNames,
			serviceContext);
	}

	public static void deleteGadgets(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteGadgets(companyId);
	}

	public static void destroyGadget(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().destroyGadget(uuid, companyId);
	}

	public static void destroyGadgets()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().destroyGadgets();
	}

	public static com.liferay.opensocial.model.Gadget fetchGadget(
		long companyId, java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchGadget(companyId, url);
	}

	public static com.liferay.opensocial.model.Gadget getGadget(
		long companyId, java.lang.String url)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadget(companyId, url);
	}

	public static com.liferay.opensocial.model.Gadget getGadget(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadget(uuid, companyId);
	}

	public static java.util.List<com.liferay.opensocial.model.Gadget> getGadgets(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgets(companyId, start, end);
	}

	public static int getGadgetsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getGadgetsCount(companyId);
	}

	public static void initGadget(java.lang.String uuid, long companyId,
		long gadgetId, java.lang.String name,
		java.lang.String portletCategoryNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.initGadget(uuid, companyId, gadgetId, name, portletCategoryNames);
	}

	public static void initGadgets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().initGadgets();
	}

	public static com.liferay.opensocial.model.Gadget updateGadget(
		long gadgetId, java.lang.String portletCategoryNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateGadget(gadgetId, portletCategoryNames);
	}

	public static void clearService() {
		_service = null;
	}

	public static GadgetLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					GadgetLocalService.class.getName());

			if (invokableLocalService instanceof GadgetLocalService) {
				_service = (GadgetLocalService)invokableLocalService;
			}
			else {
				_service = new GadgetLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(GadgetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(GadgetLocalService service) {
	}

	private static GadgetLocalService _service;
}