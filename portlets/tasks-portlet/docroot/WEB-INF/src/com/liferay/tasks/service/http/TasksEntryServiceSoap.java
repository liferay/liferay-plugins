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

import com.liferay.tasks.service.TasksEntryServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link TasksEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.tasks.model.TasksEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.tasks.model.TasksEntry}, that is translated to a
 * {@link com.liferay.tasks.model.TasksEntrySoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Ryan Park
 * @see TasksEntryServiceHttp
 * @see com.liferay.tasks.model.TasksEntrySoap
 * @see TasksEntryServiceUtil
 * @generated
 */
@ProviderType
public class TasksEntryServiceSoap {
	public static com.liferay.tasks.model.TasksEntrySoap addTasksEntry(
		java.lang.String title, int priority, long assigneeUserId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute, boolean neverDue,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.tasks.model.TasksEntry returnValue = TasksEntryServiceUtil.addTasksEntry(title,
					priority, assigneeUserId, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, neverDue,
					serviceContext);

			return com.liferay.tasks.model.TasksEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.tasks.model.TasksEntrySoap getTasksEntry(
		long tasksEntryId) throws RemoteException {
		try {
			com.liferay.tasks.model.TasksEntry returnValue = TasksEntryServiceUtil.getTasksEntry(tasksEntryId);

			return com.liferay.tasks.model.TasksEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.tasks.model.TasksEntrySoap updateTasksEntry(
		long tasksEntryId, java.lang.String title, int priority,
		long assigneeUserId, long resolverUserId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean neverDue, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.tasks.model.TasksEntry returnValue = TasksEntryServiceUtil.updateTasksEntry(tasksEntryId,
					title, priority, assigneeUserId, resolverUserId,
					dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
					dueDateMinute, neverDue, status, serviceContext);

			return com.liferay.tasks.model.TasksEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TasksEntryServiceSoap.class);
}