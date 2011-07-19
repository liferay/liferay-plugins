/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the attachment local service. This utility wraps {@link com.liferay.mail.service.impl.AttachmentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentLocalService
 * @see com.liferay.mail.service.base.AttachmentLocalServiceBaseImpl
 * @see com.liferay.mail.service.impl.AttachmentLocalServiceImpl
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Attachment addAttachment(
		com.liferay.mail.model.Attachment attachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAttachment(attachment);
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
	* Deletes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param attachmentId the primary key of the attachment
	* @throws PortalException if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAttachment(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAttachment(attachmentId);
	}

	/**
	* Deletes the attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAttachment(
		com.liferay.mail.model.Attachment attachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAttachment(attachment);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* Returns the attachment with the primary key.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment
	* @throws PortalException if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Attachment getAttachment(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAttachment(attachmentId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @return the range of attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAttachments(start, end);
	}

	/**
	* Returns the number of attachments.
	*
	* @return the number of attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int getAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAttachmentsCount();
	}

	/**
	* Updates the attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Attachment updateAttachment(
		com.liferay.mail.model.Attachment attachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAttachment(attachment);
	}

	/**
	* Updates the attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @param merge whether to merge the attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.mail.model.Attachment updateAttachment(
		com.liferay.mail.model.Attachment attachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAttachment(attachment, merge);
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

	public static com.liferay.mail.model.Attachment addAttachment(long userId,
		long messageId, java.lang.String contentPath,
		java.lang.String fileName, long size, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAttachment(userId, messageId, contentPath, fileName,
			size, file);
	}

	public static void deleteAttachments(long companyId, long messageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAttachments(companyId, messageId);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAttachments(messageId);
	}

	public static java.io.File getFile(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFile(attachmentId);
	}

	public static java.io.InputStream getInputStream(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getInputStream(attachmentId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AttachmentLocalService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AttachmentLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					AttachmentLocalService.class.getName(), portletClassLoader);

			_service = new AttachmentLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(AttachmentLocalServiceUtil.class,
				"_service");
			MethodCache.remove(AttachmentLocalService.class);
		}

		return _service;
	}

	public void setService(AttachmentLocalService service) {
		MethodCache.remove(AttachmentLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(AttachmentLocalServiceUtil.class,
			"_service");
		MethodCache.remove(AttachmentLocalService.class);
	}

	private static AttachmentLocalService _service;
}