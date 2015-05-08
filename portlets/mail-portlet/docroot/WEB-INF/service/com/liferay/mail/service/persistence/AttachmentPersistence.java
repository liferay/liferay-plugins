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

package com.liferay.mail.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.mail.service.persistence.impl.AttachmentPersistenceImpl
 * @see AttachmentUtil
 * @generated
 */
@ProviderType
public interface AttachmentPersistence extends BasePersistence<Attachment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AttachmentUtil} to access the attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the attachments where messageId = &#63;.
	*
	* @param messageId the message ID
	* @return the matching attachments
	*/
	public java.util.List<Attachment> findByMessageId(long messageId);

	/**
	* Returns a range of all the attachments where messageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param messageId the message ID
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @return the range of matching attachments
	*/
	public java.util.List<Attachment> findByMessageId(long messageId,
		int start, int end);

	/**
	* Returns an ordered range of all the attachments where messageId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param messageId the message ID
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching attachments
	*/
	public java.util.List<Attachment> findByMessageId(long messageId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator);

	/**
	* Returns the first attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching attachment
	* @throws NoSuchAttachmentException if a matching attachment could not be found
	*/
	public Attachment findByMessageId_First(long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException;

	/**
	* Returns the first attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching attachment, or <code>null</code> if a matching attachment could not be found
	*/
	public Attachment fetchByMessageId_First(long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator);

	/**
	* Returns the last attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching attachment
	* @throws NoSuchAttachmentException if a matching attachment could not be found
	*/
	public Attachment findByMessageId_Last(long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException;

	/**
	* Returns the last attachment in the ordered set where messageId = &#63;.
	*
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching attachment, or <code>null</code> if a matching attachment could not be found
	*/
	public Attachment fetchByMessageId_Last(long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator);

	/**
	* Returns the attachments before and after the current attachment in the ordered set where messageId = &#63;.
	*
	* @param attachmentId the primary key of the current attachment
	* @param messageId the message ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next attachment
	* @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	*/
	public Attachment[] findByMessageId_PrevAndNext(long attachmentId,
		long messageId,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator)
		throws com.liferay.mail.NoSuchAttachmentException;

	/**
	* Removes all the attachments where messageId = &#63; from the database.
	*
	* @param messageId the message ID
	*/
	public void removeByMessageId(long messageId);

	/**
	* Returns the number of attachments where messageId = &#63;.
	*
	* @param messageId the message ID
	* @return the number of matching attachments
	*/
	public int countByMessageId(long messageId);

	/**
	* Caches the attachment in the entity cache if it is enabled.
	*
	* @param attachment the attachment
	*/
	public void cacheResult(Attachment attachment);

	/**
	* Caches the attachments in the entity cache if it is enabled.
	*
	* @param attachments the attachments
	*/
	public void cacheResult(java.util.List<Attachment> attachments);

	/**
	* Creates a new attachment with the primary key. Does not add the attachment to the database.
	*
	* @param attachmentId the primary key for the new attachment
	* @return the new attachment
	*/
	public Attachment create(long attachmentId);

	/**
	* Removes the attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment that was removed
	* @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	*/
	public Attachment remove(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException;

	public Attachment updateImpl(Attachment attachment);

	/**
	* Returns the attachment with the primary key or throws a {@link NoSuchAttachmentException} if it could not be found.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment
	* @throws NoSuchAttachmentException if a attachment with the primary key could not be found
	*/
	public Attachment findByPrimaryKey(long attachmentId)
		throws com.liferay.mail.NoSuchAttachmentException;

	/**
	* Returns the attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param attachmentId the primary key of the attachment
	* @return the attachment, or <code>null</code> if a attachment with the primary key could not be found
	*/
	public Attachment fetchByPrimaryKey(long attachmentId);

	@Override
	public java.util.Map<java.io.Serializable, Attachment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the attachments.
	*
	* @return the attachments
	*/
	public java.util.List<Attachment> findAll();

	/**
	* Returns a range of all the attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @return the range of attachments
	*/
	public java.util.List<Attachment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of attachments
	* @param end the upper bound of the range of attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of attachments
	*/
	public java.util.List<Attachment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Attachment> orderByComparator);

	/**
	* Removes all the attachments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of attachments.
	*
	* @return the number of attachments
	*/
	public int countAll();
}