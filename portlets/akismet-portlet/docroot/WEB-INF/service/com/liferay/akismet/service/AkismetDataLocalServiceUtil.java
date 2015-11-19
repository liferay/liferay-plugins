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

package com.liferay.akismet.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for AkismetData. This utility wraps
 * {@link com.liferay.akismet.service.impl.AkismetDataLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataLocalService
 * @see com.liferay.akismet.service.base.AkismetDataLocalServiceBaseImpl
 * @see com.liferay.akismet.service.impl.AkismetDataLocalServiceImpl
 * @generated
 */
@ProviderType
public class AkismetDataLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.akismet.service.impl.AkismetDataLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the akismet data to the database. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was added
	*/
	public static com.liferay.akismet.model.AkismetData addAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return getService().addAkismetData(akismetData);
	}

	/**
	* Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	*
	* @param akismetDataId the primary key for the new akismet data
	* @return the new akismet data
	*/
	public static com.liferay.akismet.model.AkismetData createAkismetData(
		long akismetDataId) {
		return getService().createAkismetData(akismetDataId);
	}

	/**
	* Deletes the akismet data from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was removed
	*/
	public static com.liferay.akismet.model.AkismetData deleteAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return getService().deleteAkismetData(akismetData);
	}

	/**
	* Deletes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data that was removed
	* @throws PortalException if a akismet data with the primary key could not be found
	*/
	public static com.liferay.akismet.model.AkismetData deleteAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAkismetData(akismetDataId);
	}

	public static void deleteAkismetData(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAkismetData(className, classPK);
	}

	public static void deleteAkismetData(java.util.Date modifiedDate) {
		getService().deleteAkismetData(modifiedDate);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.akismet.model.AkismetData fetchAkismetData(
		long akismetDataId) {
		return getService().fetchAkismetData(akismetDataId);
	}

	public static com.liferay.akismet.model.AkismetData fetchAkismetData(
		java.lang.String className, long classPK) {
		return getService().fetchAkismetData(className, classPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the akismet data with the primary key.
	*
	* @param akismetDataId the primary key of the akismet data
	* @return the akismet data
	* @throws PortalException if a akismet data with the primary key could not be found
	*/
	public static com.liferay.akismet.model.AkismetData getAkismetData(
		long akismetDataId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAkismetData(akismetDataId);
	}

	/**
	* Returns a range of all the akismet datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.akismet.model.impl.AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of akismet datas
	* @param end the upper bound of the range of akismet datas (not inclusive)
	* @return the range of akismet datas
	*/
	public static java.util.List<com.liferay.akismet.model.AkismetData> getAkismetDatas(
		int start, int end) {
		return getService().getAkismetDatas(start, end);
	}

	/**
	* Returns the number of akismet datas.
	*
	* @return the number of akismet datas
	*/
	public static int getAkismetDatasCount() {
		return getService().getAkismetDatasCount();
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

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Updates the akismet data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param akismetData the akismet data
	* @return the akismet data that was updated
	*/
	public static com.liferay.akismet.model.AkismetData updateAkismetData(
		com.liferay.akismet.model.AkismetData akismetData) {
		return getService().updateAkismetData(akismetData);
	}

	public static com.liferay.akismet.model.AkismetData updateAkismetData(
		java.lang.String className, long classPK, java.lang.String type,
		java.lang.String permalink, java.lang.String referrer,
		java.lang.String userAgent, java.lang.String userIP,
		java.lang.String userURL) {
		return getService()
				   .updateAkismetData(className, classPK, type, permalink,
			referrer, userAgent, userIP, userURL);
	}

	public static void clearService() {
		_service = null;
	}

	public static AkismetDataLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AkismetDataLocalService.class.getName());

			if (invokableLocalService instanceof AkismetDataLocalService) {
				_service = (AkismetDataLocalService)invokableLocalService;
			}
			else {
				_service = new AkismetDataLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AkismetDataLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(AkismetDataLocalService service) {
	}

	private static AkismetDataLocalService _service;
}