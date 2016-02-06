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

package com.liferay.reports.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.reports.service.EntryServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link EntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryServiceSoap
 * @see HttpPrincipal
 * @see EntryServiceUtil
 * @generated
 */
@ProviderType
public class EntryServiceHttp {
	public static com.liferay.reports.model.Entry addEntry(
		HttpPrincipal httpPrincipal, long groupId, long definitionId,
		java.lang.String format, boolean schedulerRequest,
		java.util.Date startDate, java.util.Date endDate, boolean repeating,
		java.lang.String recurrence, java.lang.String emailNotifications,
		java.lang.String emailDelivery, java.lang.String portletId,
		java.lang.String pageURL, java.lang.String reportName,
		java.lang.String reportParameters,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"addEntry", _addEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					definitionId, format, schedulerRequest, startDate, endDate,
					repeating, recurrence, emailNotifications, emailDelivery,
					portletId, pageURL, reportName, reportParameters,
					serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.reports.model.Entry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteAttachment(HttpPrincipal httpPrincipal,
		long companyId, long entryId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"deleteAttachment", _deleteAttachmentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, entryId, fileName);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.reports.model.Entry deleteEntry(
		HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"deleteEntry", _deleteEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.reports.model.Entry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.reports.model.Entry> getEntries(
		HttpPrincipal httpPrincipal, long groupId,
		java.lang.String definitionName, java.lang.String userName,
		java.util.Date createDateGT, java.util.Date createDateLT,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"getEntries", _getEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					definitionName, userName, createDateGT, createDateLT,
					andSearch, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.reports.model.Entry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getEntriesCount(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String definitionName,
		java.lang.String userName, java.util.Date createDateGT,
		java.util.Date createDateLT, boolean andSearch) {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"getEntriesCount", _getEntriesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					definitionName, userName, createDateGT, createDateLT,
					andSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendEmails(HttpPrincipal httpPrincipal, long entryId,
		java.lang.String fileName, java.lang.String[] emailAddresses,
		boolean notification)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"sendEmails", _sendEmailsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId,
					fileName, emailAddresses, notification);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void unscheduleEntry(HttpPrincipal httpPrincipal, long entryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(EntryServiceUtil.class,
					"unscheduleEntry", _unscheduleEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, entryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(EntryServiceHttp.class);
	private static final Class<?>[] _addEntryParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, boolean.class,
			java.util.Date.class, java.util.Date.class, boolean.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteAttachmentParameterTypes1 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _deleteEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getEntriesParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.util.Date.class, java.util.Date.class, boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getEntriesCountParameterTypes4 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.util.Date.class, java.util.Date.class, boolean.class
		};
	private static final Class<?>[] _sendEmailsParameterTypes5 = new Class[] {
			long.class, java.lang.String.class, java.lang.String[].class,
			boolean.class
		};
	private static final Class<?>[] _unscheduleEntryParameterTypes6 = new Class[] {
			long.class
		};
}