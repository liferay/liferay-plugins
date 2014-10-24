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

package com.liferay.microblogs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for MicroblogsEntry. This utility wraps
 * {@link com.liferay.microblogs.service.impl.MicroblogsEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryLocalService
 * @see com.liferay.microblogs.service.base.MicroblogsEntryLocalServiceBaseImpl
 * @see com.liferay.microblogs.service.impl.MicroblogsEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class MicroblogsEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.microblogs.service.impl.MicroblogsEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the microblogs entry to the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was added
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return getService().addMicroblogsEntry(microblogsEntry);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMicroblogsEntry(userId, content, type, receiverUserId,
			receiverMicroblogsEntryId, socialRelationType, serviceContext);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, long creatorClassNameId, long creatorClassPK,
		java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addMicroblogsEntry(userId, creatorClassNameId,
			creatorClassPK, content, type, receiverUserId,
			receiverMicroblogsEntryId, socialRelationType, serviceContext);
	}

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry createMicroblogsEntry(
		long microblogsEntryId) {
		return getService().createMicroblogsEntry(microblogsEntryId);
	}

	public static void deleteMicroblogsEntries(long creatorClassNameId,
		long creatorClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteMicroblogsEntries(creatorClassNameId, creatorClassPK);
	}

	/**
	* Deletes the microblogs entry from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMicroblogsEntry(microblogsEntry);
	}

	/**
	* Deletes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry that was removed
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry deleteMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMicroblogsEntry(microblogsEntryId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteUserMicroblogsEntries(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteUserMicroblogsEntries(userId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry fetchMicroblogsEntry(
		long microblogsEntryId) {
		return getService().fetchMicroblogsEntry(microblogsEntryId);
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

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getCompanyMicroblogsEntries(
		long companyId, int start, int end) {
		return getService().getCompanyMicroblogsEntries(companyId, start, end);
	}

	public static int getCompanyMicroblogsEntriesCount(long companyId) {
		return getService().getCompanyMicroblogsEntriesCount(companyId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int start, int end) {
		return getService()
				   .getMicroblogsEntries(creatorClassNameId, creatorClassPK,
			start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, long creatorClassPK, int type, int start,
		int end) {
		return getService()
				   .getMicroblogsEntries(creatorClassNameId, creatorClassPK,
			type, start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long creatorClassNameId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getMicroblogsEntries(creatorClassNameId, type, start, end,
			obc);
	}

	/**
	* Returns a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.microblogs.model.impl.MicroblogsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries
	* @param end the upper bound of the range of microblogs entries (not inclusive)
	* @return the range of microblogs entries
	*/
	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end) {
		return getService().getMicroblogsEntries(start, end);
	}

	/**
	* Returns the number of microblogs entries.
	*
	* @return the number of microblogs entries
	*/
	public static int getMicroblogsEntriesCount() {
		return getService().getMicroblogsEntriesCount();
	}

	public static int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK) {
		return getService()
				   .getMicroblogsEntriesCount(creatorClassNameId, creatorClassPK);
	}

	public static int getMicroblogsEntriesCount(long creatorClassNameId,
		long creatorClassPK, int type) {
		return getService()
				   .getMicroblogsEntriesCount(creatorClassNameId,
			creatorClassPK, type);
	}

	/**
	* Returns the microblogs entry with the primary key.
	*
	* @param microblogsEntryId the primary key of the microblogs entry
	* @return the microblogs entry
	* @throws PortalException if a microblogs entry with the primary key could not be found
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMicroblogsEntry(microblogsEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
		int type, long receiverMicroblogsEntryId, int start, int end) {
		return getService()
				   .getReceiverMicroblogsEntryMicroblogsEntries(type,
			receiverMicroblogsEntryId, start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverMicroblogsEntryMicroblogsEntries(
		int type, long receiverMicroblogsEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.microblogs.model.MicroblogsEntry> orderByComparator) {
		return getService()
				   .getReceiverMicroblogsEntryMicroblogsEntries(type,
			receiverMicroblogsEntryId, start, end, orderByComparator);
	}

	public static int getReceiverMicroblogsEntryMicroblogsEntriesCount(
		int type, long receiverMicroblogsEntryId) {
		return getService()
				   .getReceiverMicroblogsEntryMicroblogsEntriesCount(type,
			receiverMicroblogsEntryId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getReceiverUserMicroblogsEntries(
		int type, long receiverUserId, int start, int end) {
		return getService()
				   .getReceiverUserMicroblogsEntries(type, receiverUserId,
			start, end);
	}

	public static int getReceiverUserMicroblogsEntriesCount(int type,
		long receiverUserId) {
		return getService()
				   .getReceiverUserMicroblogsEntriesCount(type, receiverUserId);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int start, int end) {
		return getService().getUserMicroblogsEntries(userId, start, end);
	}

	public static java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long userId, int type, int start, int end) {
		return getService().getUserMicroblogsEntries(userId, type, start, end);
	}

	public static int getUserMicroblogsEntriesCount(long userId) {
		return getService().getUserMicroblogsEntriesCount(userId);
	}

	public static int getUserMicroblogsEntriesCount(long userId, int type) {
		return getService().getUserMicroblogsEntriesCount(userId, type);
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

	public static void updateAsset(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		long[] assetCategoryIds, java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateAsset(microblogsEntry, assetCategoryIds, assetTagNames);
	}

	/**
	* Updates the microblogs entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntry the microblogs entry
	* @return the microblogs entry that was updated
	*/
	public static com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry) {
		return getService().updateMicroblogsEntry(microblogsEntry);
	}

	public static com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMicroblogsEntry(microblogsEntryId, content,
			socialRelationType, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static MicroblogsEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					MicroblogsEntryLocalService.class.getName());

			if (invokableLocalService instanceof MicroblogsEntryLocalService) {
				_service = (MicroblogsEntryLocalService)invokableLocalService;
			}
			else {
				_service = new MicroblogsEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(MicroblogsEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setService(MicroblogsEntryLocalService service) {
	}

	private static MicroblogsEntryLocalService _service;
}