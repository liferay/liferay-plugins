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

package com.liferay.tasks.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

import com.liferay.tasks.service.TasksEntryServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link TasksEntryServiceUtil} service utility. The
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
 * @author Ryan Park
 * @see TasksEntryServiceSoap
 * @see HttpPrincipal
 * @see TasksEntryServiceUtil
 * @generated
 */
@ProviderType
public class TasksEntryServiceHttp {
	public static com.liferay.tasks.model.TasksEntry addTasksEntry(
		HttpPrincipal httpPrincipal, java.lang.String title, int priority,
		long assigneeUserId, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean neverDue,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TasksEntryServiceUtil.class,
					"addTasksEntry", _addTasksEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, neverDue,
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

			return (com.liferay.tasks.model.TasksEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.tasks.model.TasksEntry deleteTasksEntry(
		HttpPrincipal httpPrincipal, long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TasksEntryServiceUtil.class,
					"deleteTasksEntry", _deleteTasksEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					tasksEntryId);

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

			return (com.liferay.tasks.model.TasksEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.tasks.model.TasksEntry getTasksEntry(
		HttpPrincipal httpPrincipal, long tasksEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TasksEntryServiceUtil.class,
					"getTasksEntry", _getTasksEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					tasksEntryId);

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

			return (com.liferay.tasks.model.TasksEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntry(
		HttpPrincipal httpPrincipal, long tasksEntryId, java.lang.String title,
		int priority, long assigneeUserId, long resolverUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean neverDue, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TasksEntryServiceUtil.class,
					"updateTasksEntry", _updateTasksEntryParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					tasksEntryId, title, priority, assigneeUserId,
					resolverUserId, dueDateMonth, dueDateDay, dueDateYear,
					dueDateHour, dueDateMinute, neverDue, status, serviceContext);

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

			return (com.liferay.tasks.model.TasksEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.tasks.model.TasksEntry updateTasksEntryStatus(
		HttpPrincipal httpPrincipal, long tasksEntryId, long resolverUserId,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TasksEntryServiceUtil.class,
					"updateTasksEntryStatus",
					_updateTasksEntryStatusParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					tasksEntryId, resolverUserId, status, serviceContext);

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

			return (com.liferay.tasks.model.TasksEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TasksEntryServiceHttp.class);
	private static final Class<?>[] _addTasksEntryParameterTypes0 = new Class[] {
			java.lang.String.class, int.class, long.class, int.class, int.class,
			int.class, int.class, int.class, boolean.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteTasksEntryParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getTasksEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateTasksEntryParameterTypes3 = new Class[] {
			long.class, java.lang.String.class, int.class, long.class,
			long.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateTasksEntryStatusParameterTypes4 = new Class[] {
			long.class, long.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}