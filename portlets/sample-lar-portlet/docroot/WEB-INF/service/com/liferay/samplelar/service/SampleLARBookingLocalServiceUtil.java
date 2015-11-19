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

package com.liferay.samplelar.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SampleLARBooking. This utility wraps
 * {@link com.liferay.samplelar.service.impl.SampleLARBookingLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Mate Thurzo
 * @see SampleLARBookingLocalService
 * @see com.liferay.samplelar.service.base.SampleLARBookingLocalServiceBaseImpl
 * @see com.liferay.samplelar.service.impl.SampleLARBookingLocalServiceImpl
 * @generated
 */
@ProviderType
public class SampleLARBookingLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.samplelar.service.impl.SampleLARBookingLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the sample l a r booking to the database. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBooking the sample l a r booking
	* @return the sample l a r booking that was added
	*/
	public static com.liferay.samplelar.model.SampleLARBooking addSampleLARBooking(
		com.liferay.samplelar.model.SampleLARBooking sampleLARBooking) {
		return getService().addSampleLARBooking(sampleLARBooking);
	}

	public static com.liferay.samplelar.model.SampleLARBooking addSampleLARBooking(
		long userId, long groupId, java.lang.String bookingNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSampleLARBooking(userId, groupId, bookingNumber,
			serviceContext);
	}

	/**
	* Creates a new sample l a r booking with the primary key. Does not add the sample l a r booking to the database.
	*
	* @param sampleLARBookingId the primary key for the new sample l a r booking
	* @return the new sample l a r booking
	*/
	public static com.liferay.samplelar.model.SampleLARBooking createSampleLARBooking(
		long sampleLARBookingId) {
		return getService().createSampleLARBooking(sampleLARBookingId);
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
	* Deletes the sample l a r booking from the database. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBooking the sample l a r booking
	* @return the sample l a r booking that was removed
	*/
	public static com.liferay.samplelar.model.SampleLARBooking deleteSampleLARBooking(
		com.liferay.samplelar.model.SampleLARBooking sampleLARBooking) {
		return getService().deleteSampleLARBooking(sampleLARBooking);
	}

	/**
	* Deletes the sample l a r booking with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking that was removed
	* @throws PortalException if a sample l a r booking with the primary key could not be found
	*/
	public static com.liferay.samplelar.model.SampleLARBooking deleteSampleLARBooking(
		long sampleLARBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSampleLARBooking(sampleLARBookingId);
	}

	public static void deleteSampleLARBookings(long groupId) {
		getService().deleteSampleLARBookings(groupId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samplelar.model.impl.SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samplelar.model.impl.SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.samplelar.model.SampleLARBooking fetchSampleLARBooking(
		long sampleLARBookingId) {
		return getService().fetchSampleLARBooking(sampleLARBookingId);
	}

	/**
	* Returns the sample l a r booking matching the UUID and group.
	*
	* @param uuid the sample l a r booking's UUID
	* @param groupId the primary key of the group
	* @return the matching sample l a r booking, or <code>null</code> if a matching sample l a r booking could not be found
	*/
	public static com.liferay.samplelar.model.SampleLARBooking fetchSampleLARBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchSampleLARBookingByUuidAndGroupId(uuid, groupId);
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
	* Returns the sample l a r booking with the primary key.
	*
	* @param sampleLARBookingId the primary key of the sample l a r booking
	* @return the sample l a r booking
	* @throws PortalException if a sample l a r booking with the primary key could not be found
	*/
	public static com.liferay.samplelar.model.SampleLARBooking getSampleLARBooking(
		long sampleLARBookingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSampleLARBooking(sampleLARBookingId);
	}

	/**
	* Returns the sample l a r booking matching the UUID and group.
	*
	* @param uuid the sample l a r booking's UUID
	* @param groupId the primary key of the group
	* @return the matching sample l a r booking
	* @throws PortalException if a matching sample l a r booking could not be found
	*/
	public static com.liferay.samplelar.model.SampleLARBooking getSampleLARBookingByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSampleLARBookingByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<com.liferay.samplelar.model.SampleLARBooking> getSampleLARBookings(
		long groupId, int start, int end) {
		return getService().getSampleLARBookings(groupId, start, end);
	}

	/**
	* Returns a range of all the sample l a r bookings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.samplelar.model.impl.SampleLARBookingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @return the range of sample l a r bookings
	*/
	public static java.util.List<com.liferay.samplelar.model.SampleLARBooking> getSampleLARBookings(
		int start, int end) {
		return getService().getSampleLARBookings(start, end);
	}

	/**
	* Returns all the sample l a r bookings matching the UUID and company.
	*
	* @param uuid the UUID of the sample l a r bookings
	* @param companyId the primary key of the company
	* @return the matching sample l a r bookings, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.samplelar.model.SampleLARBooking> getSampleLARBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService()
				   .getSampleLARBookingsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of sample l a r bookings matching the UUID and company.
	*
	* @param uuid the UUID of the sample l a r bookings
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of sample l a r bookings
	* @param end the upper bound of the range of sample l a r bookings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching sample l a r bookings, or an empty list if no matches were found
	*/
	public static java.util.List<com.liferay.samplelar.model.SampleLARBooking> getSampleLARBookingsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.samplelar.model.SampleLARBooking> orderByComparator) {
		return getService()
				   .getSampleLARBookingsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of sample l a r bookings.
	*
	* @return the number of sample l a r bookings
	*/
	public static int getSampleLARBookingsCount() {
		return getService().getSampleLARBookingsCount();
	}

	public static int getSampleLARBookingsCount(long groupId) {
		return getService().getSampleLARBookingsCount(groupId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Updates the sample l a r booking in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param sampleLARBooking the sample l a r booking
	* @return the sample l a r booking that was updated
	*/
	public static com.liferay.samplelar.model.SampleLARBooking updateSampleLARBooking(
		com.liferay.samplelar.model.SampleLARBooking sampleLARBooking) {
		return getService().updateSampleLARBooking(sampleLARBooking);
	}

	public static com.liferay.samplelar.model.SampleLARBooking updateSampleLARBooking(
		long userId, long sampleLARBookingId, java.lang.String bookingNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSampleLARBooking(userId, sampleLARBookingId,
			bookingNumber, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SampleLARBookingLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SampleLARBookingLocalService.class.getName());

			if (invokableLocalService instanceof SampleLARBookingLocalService) {
				_service = (SampleLARBookingLocalService)invokableLocalService;
			}
			else {
				_service = new SampleLARBookingLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SampleLARBookingLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(SampleLARBookingLocalService service) {
	}

	private static SampleLARBookingLocalService _service;
}