/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
 * The persistence interface for the attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AttachmentPersistenceImpl
 * @see AttachmentUtil
 * @generated
 */
public interface AttachmentPersistence extends BasePersistence<Attachment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AttachmentUtil} to access the attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the attachment in the entity cache if it is enabled.
	*
	* @param attachment the attachment
	*/
	public void cacheResult(com.liferay.mail.model.Attachment attachment);

	/**
	* Caches the attachments in the entity cache if it is enabled.
	*
	* @param attachments the attachments
	*/
	public void cacheResult(
		java.util.List<com.liferay.mail.model.Attachment> attachments);

	/**
	* Creates a new attachment with the primary key. Does not add the attachment to the database.
	*
	* @param attachmentId the primary key for the new attachment
	* @return the new attachment
	*/
	public com.liferay.mail.model.Attachment create(long attachmentId);

	/**
	* Removes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment that was removed
	* @throws com.liferay.mail.NoSuchAttachmentException if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment remove(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Attachment updateImpl(
		com.liferay.mail.model.Attachment attachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the attachment with the primary key or throws a {@link com.liferay.mail.NoSuchAttachmentException} if it could not be found.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment
	* @throws com.liferay.mail.NoSuchAttachmentException if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment findByPrimaryKey(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment, or <code>null</code> if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment fetchByPrimaryKey(
		long attachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the attachments where messageId = &#63;.
	*
	* @param messageId the message ID
	* @return the matching attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the attachments where messageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the message ID
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @return the range of matching attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the attachments where messageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param messageId the message ID
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Attachment> findByMessageId(
		long messageId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching attachment
	* @throws com.liferay.mail.NoSuchAttachmentException if a matching attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment findByMessageId_First(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching attachment, or <code>null</code> if a matching attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment fetchByMessageId_First(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching attachment
	* @throws com.liferay.mail.NoSuchAttachmentException if a matching attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment findByMessageId_Last(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching attachment, or <code>null</code> if a matching attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment fetchByMessageId_Last(
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the attachments before and after the current attachment in the ordered set where messageId = &#63;.
	*
	* @param attachmentId the primary key of the current attachment
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next attachment
	* @throws com.liferay.mail.NoSuchAttachmentException if a attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.mail.model.Attachment[] findByMessageId_PrevAndNext(
		long attachmentId, long messageId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the attachments.
	*
	* @return the attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Attachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.mail.model.Attachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the attachments where messageId = &#63; from the database.
	*
	* @param messageId the message ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the attachments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of attachments where messageId = &#63;.
	*
	* @param messageId the message ID
	* @return the number of matching attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByMessageId(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of attachments.
	*
	* @return the number of attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}