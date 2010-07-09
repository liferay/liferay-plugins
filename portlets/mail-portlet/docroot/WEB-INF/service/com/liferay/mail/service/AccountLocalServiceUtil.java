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

package com.liferay.mail.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * {@link AccountLocalService} bean. The static methods of
 * this class calls the same methods of the bean instance. It's convenient to be
 * able to just write one line to call a method on a bean instead of writing a
 * lookup call and a method call.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountLocalService
 * @generated
 */
public class AccountLocalServiceUtil {
	public static com.liferay.mail.model.Account addAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAccount(account);
	}

	public static com.liferay.mail.model.Account createAccount(long accountId) {
		return getService().createAccount(accountId);
	}

	public static void deleteAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccount(accountId);
	}

	public static void deleteAccount(com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccount(account);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.mail.model.Account getAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccount(accountId);
	}

	public static java.util.List<com.liferay.mail.model.Account> getAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccounts(start, end);
	}

	public static int getAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountsCount();
	}

	public static com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccount(account);
	}

	public static com.liferay.mail.model.Account updateAccount(
		com.liferay.mail.model.Account account, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccount(account, merge);
	}

	public static com.liferay.mail.model.Account addAccount(long userId,
		java.lang.String address, java.lang.String personalName,
		java.lang.String protocol, java.lang.String incomingHostName,
		int incomingPort, boolean incomingSecure,
		java.lang.String outgoingHostName, int outgoingPort,
		boolean outgoingSecure, java.lang.String login,
		java.lang.String password, boolean savePassword,
		java.lang.String signature, boolean useSignature,
		java.lang.String folderPrefix, long inboxFolderId, long draftFolderId,
		long sentFolderId, long trashFolderId, boolean defaultSender)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAccount(userId, address, personalName, protocol,
			incomingHostName, incomingPort, incomingSecure, outgoingHostName,
			outgoingPort, outgoingSecure, login, password, savePassword,
			signature, useSignature, folderPrefix, inboxFolderId,
			draftFolderId, sentFolderId, trashFolderId, defaultSender);
	}

	public static void deleteAccounts(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccounts(userId);
	}

	public static com.liferay.mail.model.Account getAccount(long userId,
		java.lang.String address)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccount(userId, address);
	}

	public static java.util.List<com.liferay.mail.model.Account> getAccounts(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccounts(userId);
	}

	public static com.liferay.mail.model.Account updateAccount(long accountId,
		java.lang.String personalName, java.lang.String password,
		boolean savePassword, java.lang.String signature, boolean useSignature,
		java.lang.String folderPrefix, boolean defaultSender)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAccount(accountId, personalName, password,
			savePassword, signature, useSignature, folderPrefix, defaultSender);
	}

	public static com.liferay.mail.model.Account updateFolders(long accountId,
		long inboxFolderId, long draftFolderId, long sentFolderId,
		long trashFolderId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFolders(accountId, inboxFolderId, draftFolderId,
			sentFolderId, trashFolderId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					AccountLocalService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					AccountLocalService.class.getName(), portletClassLoader);

			_service = new AccountLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(AccountLocalService service) {
		_service = service;
	}

	private static AccountLocalService _service;
}