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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentLocalService
 * @generated
 */
@ProviderType
public class AttachmentLocalServiceWrapper implements AttachmentLocalService,
	ServiceWrapper<AttachmentLocalService> {
	public AttachmentLocalServiceWrapper(
		AttachmentLocalService attachmentLocalService) {
		_attachmentLocalService = attachmentLocalService;
	}

	/**
	* Adds the attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was added
	*/
	@Override
	public com.liferay.mail.model.Attachment addAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return _attachmentLocalService.addAttachment(attachment);
	}

	@Override
	public com.liferay.mail.model.Attachment addAttachment(long userId,
		long messageId, java.lang.String contentPath,
		java.lang.String fileName, long size, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.addAttachment(userId, messageId,
			contentPath, fileName, size, file);
	}

	/**
	* Creates a new attachment with the primary key. Does not add the attachment to the database.
	*
	* @param attachmentId the primary key for the new attachment
	* @return the new attachment
	*/
	@Override
	public com.liferay.mail.model.Attachment createAttachment(long attachmentId) {
		return _attachmentLocalService.createAttachment(attachmentId);
	}

	/**
	* Deletes the attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was removed
	*/
	@Override
	public com.liferay.mail.model.Attachment deleteAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return _attachmentLocalService.deleteAttachment(attachment);
	}

	/**
	* Deletes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment that was removed
	* @throws PortalException if a attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Attachment deleteAttachment(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.deleteAttachment(attachmentId);
	}

	@Override
	public void deleteAttachments(long companyId, long messageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_attachmentLocalService.deleteAttachments(companyId, messageId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _attachmentLocalService.dynamicQuery();
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
		return _attachmentLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _attachmentLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _attachmentLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _attachmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _attachmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.mail.model.Attachment fetchAttachment(long attachmentId) {
		return _attachmentLocalService.fetchAttachment(attachmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _attachmentLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the attachment with the primary key.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment
	* @throws PortalException if a attachment with the primary key could not be found
	*/
	@Override
	public com.liferay.mail.model.Attachment getAttachment(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.getAttachment(attachmentId);
	}

	@Override
	public java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		long messageId) {
		return _attachmentLocalService.getAttachments(messageId);
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
	@Override
	public java.util.List<com.liferay.mail.model.Attachment> getAttachments(
		int start, int end) {
		return _attachmentLocalService.getAttachments(start, end);
	}

	/**
	* Returns the number of attachments.
	*
	* @return the number of attachments
	*/
	@Override
	public int getAttachmentsCount() {
		return _attachmentLocalService.getAttachmentsCount();
	}

	@Override
	public java.io.File getFile(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.getFile(attachmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _attachmentLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.io.InputStream getInputStream(long attachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.getInputStream(attachmentId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _attachmentLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _attachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _attachmentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Updates the attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param attachment the attachment
	* @return the attachment that was updated
	*/
	@Override
	public com.liferay.mail.model.Attachment updateAttachment(
		com.liferay.mail.model.Attachment attachment) {
		return _attachmentLocalService.updateAttachment(attachment);
	}

	@Override
	public AttachmentLocalService getWrappedService() {
		return _attachmentLocalService;
	}

	@Override
	public void setWrappedService(AttachmentLocalService attachmentLocalService) {
		_attachmentLocalService = attachmentLocalService;
	}

	private AttachmentLocalService _attachmentLocalService;
}