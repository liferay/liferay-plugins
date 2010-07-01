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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * <a href="AttachmentUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AttachmentPersistence
 * @see       AttachmentPersistenceImpl
 * @generated
 */
public class AttachmentUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Attachment attachment) {
		getPersistence().clearCache(attachment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Attachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Attachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Attachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Attachment remove(Attachment attachment)
		throws SystemException {
		return getPersistence().remove(attachment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Attachment update(Attachment attachment, boolean merge)
		throws SystemException {
		return getPersistence().update(attachment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Attachment update(Attachment attachment, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(attachment, merge, serviceContext);
	}

	public static void cacheResult(com.liferay.mail.model.Attachment attachment) {
		getPersistence().cacheResult(attachment);
	}

	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Attachment> attachments) {
		getPersistence().cacheResult(attachments);
	}

	public static com.liferay.mail.model.Attachment create(long attachmentId) {
		return getPersistence().create(attachmentId);
	}

	public static com.liferay.mail.model.Attachment remove(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(attachmentId);
	}

	public static com.liferay.mail.model.Attachment updateImpl(
		com.liferay.mail.model.Attachment attachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(attachment, merge);
	}

	public static com.liferay.mail.model.Attachment findByPrimaryKey(
		long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(attachmentId);
	}

	public static com.liferay.mail.model.Attachment fetchByPrimaryKey(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(attachmentId);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMessageId(messageId);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMessageId(messageId, start, end);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMessageId(messageId, start, end, orderByComparator);
	}

	public static com.liferay.mail.model.Attachment findByMessageId_First(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMessageId_First(messageId, orderByComparator);
	}

	public static com.liferay.mail.model.Attachment findByMessageId_Last(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMessageId_Last(messageId, orderByComparator);
	}

	public static com.liferay.mail.model.Attachment[] findByMessageId_PrevAndNext(
		long attachmentId, long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMessageId_PrevAndNext(attachmentId, messageId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMessageId(messageId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMessageId(messageId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AttachmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AttachmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AttachmentPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AttachmentPersistence persistence) {
		_persistence = persistence;
	}

	private static AttachmentPersistence _persistence;
}