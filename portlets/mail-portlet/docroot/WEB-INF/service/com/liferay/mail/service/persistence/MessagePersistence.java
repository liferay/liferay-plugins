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

import com.liferay.mail.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="MessagePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagePersistenceImpl
 * @see       MessageUtil
 * @generated
 */
public interface MessagePersistence extends BasePersistence<Message> {
	public void cacheResult(com.liferay.mail.model.Message message);

	public void cacheResult(
		java.util.List<com.liferay.mail.model.Message> messages);

	public com.liferay.mail.model.Message create(long messageId);

	public com.liferay.mail.model.Message remove(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message updateImpl(
		com.liferay.mail.model.Message message, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message findByPrimaryKey(long messageId)
		throws com.liferay.mail.NoSuchMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Message fetchByPrimaryKey(long messageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Message> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}