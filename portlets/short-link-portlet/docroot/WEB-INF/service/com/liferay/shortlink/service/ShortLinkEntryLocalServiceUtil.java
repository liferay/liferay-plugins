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

package com.liferay.shortlink.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ShortLinkEntry. This utility wraps
 * {@link com.liferay.shortlink.service.impl.ShortLinkEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ShortLinkEntryLocalService
 * @see com.liferay.shortlink.service.base.ShortLinkEntryLocalServiceBaseImpl
 * @see com.liferay.shortlink.service.impl.ShortLinkEntryLocalServiceImpl
 * @generated
 */
public class ShortLinkEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.shortlink.service.impl.ShortLinkEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the short link entry to the database. Also notifies the appropriate model listeners.
	*
	* @param shortLinkEntry the short link entry
	* @return the short link entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry addShortLinkEntry(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addShortLinkEntry(shortLinkEntry);
	}

	/**
	* Creates a new short link entry with the primary key. Does not add the short link entry to the database.
	*
	* @param shortLinkEntryId the primary key for the new short link entry
	* @return the new short link entry
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry createShortLinkEntry(
		long shortLinkEntryId) {
		return getService().createShortLinkEntry(shortLinkEntryId);
	}

	/**
	* Deletes the short link entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param shortLinkEntryId the primary key of the short link entry
	* @return the short link entry that was removed
	* @throws PortalException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry deleteShortLinkEntry(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteShortLinkEntry(shortLinkEntryId);
	}

	/**
	* Deletes the short link entry from the database. Also notifies the appropriate model listeners.
	*
	* @param shortLinkEntry the short link entry
	* @return the short link entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry deleteShortLinkEntry(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteShortLinkEntry(shortLinkEntry);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.shortlink.model.ShortLinkEntry fetchShortLinkEntry(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchShortLinkEntry(shortLinkEntryId);
	}

	/**
	* Returns the short link entry with the primary key.
	*
	* @param shortLinkEntryId the primary key of the short link entry
	* @return the short link entry
	* @throws PortalException if a short link entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry getShortLinkEntry(
		long shortLinkEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getShortLinkEntry(shortLinkEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the short link entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.shortlink.model.impl.ShortLinkEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of short link entries
	* @param end the upper bound of the range of short link entries (not inclusive)
	* @return the range of short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> getShortLinkEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getShortLinkEntries(start, end);
	}

	/**
	* Returns the number of short link entries.
	*
	* @return the number of short link entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getShortLinkEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getShortLinkEntriesCount();
	}

	/**
	* Updates the short link entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param shortLinkEntry the short link entry
	* @return the short link entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.shortlink.model.ShortLinkEntry updateShortLinkEntry(
		com.liferay.shortlink.model.ShortLinkEntry shortLinkEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateShortLinkEntry(shortLinkEntry);
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

	public static com.liferay.shortlink.model.ShortLinkEntry addShortLinkEntry(
		java.lang.String originalURL, java.lang.String shortURL,
		boolean autogenerated)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addShortLinkEntry(originalURL, shortURL, autogenerated);
	}

	public static void deleteShortLinkEntries(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteShortLinkEntries(modifiedDate);
	}

	public static java.util.List<com.liferay.shortlink.model.ShortLinkEntry> getShortLinkEntries(
		boolean autogenerated, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getShortLinkEntries(autogenerated, start, end);
	}

	public static com.liferay.shortlink.model.ShortLinkEntry getShortLinkEntry(
		java.lang.String shortURL, boolean autogenerated)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getShortLinkEntry(shortURL, autogenerated);
	}

	public static com.liferay.shortlink.model.ShortLinkEntry updateShortLinkEntry(
		long shortLinkEntryId, java.lang.String originalURL,
		java.lang.String shortURL, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateShortLinkEntry(shortLinkEntryId, originalURL,
			shortURL, active);
	}

	public static void clearService() {
		_service = null;
	}

	public static ShortLinkEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ShortLinkEntryLocalService.class.getName());

			if (invokableLocalService instanceof ShortLinkEntryLocalService) {
				_service = (ShortLinkEntryLocalService)invokableLocalService;
			}
			else {
				_service = new ShortLinkEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ShortLinkEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(ShortLinkEntryLocalService service) {
	}

	private static ShortLinkEntryLocalService _service;
}