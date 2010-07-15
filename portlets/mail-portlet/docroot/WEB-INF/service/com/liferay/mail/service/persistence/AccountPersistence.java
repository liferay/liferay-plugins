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

import com.liferay.mail.model.Account;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       AccountPersistenceImpl
 * @see       AccountUtil
 * @generated
 */
public interface AccountPersistence extends BasePersistence<Account> {
	public void cacheResult(com.liferay.mail.model.Account account);

	public void cacheResult(
		java.util.List<com.liferay.mail.model.Account> accounts);

	public com.liferay.mail.model.Account create(long accountId);

	public com.liferay.mail.model.Account remove(long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account updateImpl(
		com.liferay.mail.model.Account account, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account findByPrimaryKey(long accountId)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account fetchByPrimaryKey(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account[] findByUserId_PrevAndNext(
		long accountId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account findByU_A(long userId,
		java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Account fetchByU_A(long userId,
		java.lang.String address, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Account> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByU_A(long userId, java.lang.String address)
		throws com.liferay.mail.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByU_A(long userId, java.lang.String address)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}