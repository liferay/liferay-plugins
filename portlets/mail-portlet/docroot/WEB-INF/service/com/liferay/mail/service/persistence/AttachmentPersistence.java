/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Attachment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AttachmentPersistenceImpl
 * @see       AttachmentUtil
 * @generated
 */
public interface AttachmentPersistence extends BasePersistence<Attachment> {
	public void cacheResult(com.liferay.mail.model.Attachment attachment);

	public void cacheResult(
		java.util.List<com.liferay.mail.model.Attachment> attachments);

	public com.liferay.mail.model.Attachment create(long attachmentId);

	public com.liferay.mail.model.Attachment remove(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment updateImpl(
		com.liferay.mail.model.Attachment attachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment findByPrimaryKey(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment fetchByPrimaryKey(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment findByMessageId_First(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment findByMessageId_Last(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment[] findByMessageId_PrevAndNext(
		long attachmentId, long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}