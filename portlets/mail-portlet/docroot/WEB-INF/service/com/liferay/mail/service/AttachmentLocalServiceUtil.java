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

package com.liferay.mail.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for Attachment. This utility wraps
 * {@link com.liferay.mail.service.impl.AttachmentLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentLocalService
 * @see com.liferay.mail.service.base.AttachmentLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.AttachmentLocalServiceImpl
 * @generated
 */
@ProviderType
public class AttachmentLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.mail.service.impl.AttachmentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was added
	*/
	public static com.liferay.mail.model.Attachment addAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return getService().addAttachment(attachment);
	}

	public static com.liferay.mail.model.Attachment addAttachment(long userId,
		long messageId, java.lang.String contentPath,
		java.lang.String fileName, long size, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addAttachment(userId, messageId, contentPath, fileName,
			size, file);
	}

	/**
	* Creates a new attachment with the primary key. Does not add the attachment to the database.
	*
	* @param attachmentId the primary key for the new attachment
	* @return the new attachment
	*/
	public static com.liferay.mail.model.Attachment createAttachment(
		long attachmentId) {
		return getService().createAttachment(attachmentId);
	}

	/**
	* Deletes the attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was removed
	*/
	public static com.liferay.mail.model.Attachment deleteAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return getService().deleteAttachment(attachment);
	}

	/**
	* Deletes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment that was removed
	* @throws PortalException if a attachment with the primary key could not be found
	*/
	public static com.liferay.mail.model.Attachment deleteAttachment(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAttachment(attachmentId);
	}

	public static com.liferay.mail.model.Attachment fetchAttachment(
		long attachmentId) {
		return getService().fetchAttachment(attachmentId);
	}

	/**
	* Returns the attachment with the primary key.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment
	* @throws PortalException if a attachment with the primary key could not be found
	*/
	public static com.liferay.mail.model.Attachment getAttachment(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAttachment(attachmentId);
	}

	/**
	* Updates the attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was updated
	*/
	public static com.liferay.mail.model.Attachment updateAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return getService().updateAttachment(attachment);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of attachments.
	*
	* @return the number of attachments
	*/
	public static int getAttachmentsCount() {
		return getService().getAttachmentsCount();
	}

	public static java.io.File getFile(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFile(attachmentId);
	}

	public static java.io.InputStream getInputStream(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInputStream(attachmentId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mail.model.impl.AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @return the range of attachments
	*/
	public static java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		int start, int end) {
		return getService().getAttachments(start, end);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		long messageId) {
		return getService().getAttachments(messageId);
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

	public static void deleteAttachments(long companyId, long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteAttachments(companyId, messageId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AttachmentLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AttachmentLocalService.class.getName());

			if (invokableLocalService instanceof AttachmentLocalService) {
				_service = (AttachmentLocalService)invokableLocalService;
			}
			else {
				_service = new AttachmentLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AttachmentLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static AttachmentLocalService _service;
}