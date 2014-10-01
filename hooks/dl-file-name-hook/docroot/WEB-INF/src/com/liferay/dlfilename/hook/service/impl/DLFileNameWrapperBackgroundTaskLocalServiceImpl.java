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

package com.liferay.dlfilename.hook.service.impl;

import com.liferay.dlfilename.hook.model.impl.DLFileNameWrapperBackgroundTaskImpl;
import com.liferay.dlfilename.hook.util.DLFileNameThreadLocal;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.service.BackgroundTaskLocalService;
import com.liferay.portal.service.BackgroundTaskLocalServiceWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Preston Crary
 */
public class DLFileNameWrapperBackgroundTaskLocalServiceImpl
	extends BackgroundTaskLocalServiceWrapper {

	public DLFileNameWrapperBackgroundTaskLocalServiceImpl(
		BackgroundTaskLocalService backgroundTaskLocalService) {

		super(backgroundTaskLocalService);
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String taskExecutorClassName, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<BackgroundTask> backgroundTasks = super.getBackgroundTasks(
			groupId, taskExecutorClassName, start, end, orderByComparator);

		if (DLFileNameThreadLocal.isEnabled()) {
			return _wrapBackgroundTasks(backgroundTasks);
		}

		return backgroundTasks;
	}

	@Override
	public List<BackgroundTask> getBackgroundTasks(
			long groupId, String name, String taskExecutorClassName, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		List<BackgroundTask> backgroundTasks = super.getBackgroundTasks(
			groupId, name, taskExecutorClassName, start, end,
			orderByComparator);

		if (DLFileNameThreadLocal.isEnabled()) {
			return _wrapBackgroundTasks(backgroundTasks);
		}

		return backgroundTasks;
	}

	private List<BackgroundTask> _wrapBackgroundTasks(
		List<BackgroundTask> backgroundTasks) {

		List<BackgroundTask> dlFileNameBackgroundTasks =
			new ArrayList<BackgroundTask>(backgroundTasks.size());

		for (BackgroundTask backgroundTask : backgroundTasks) {
			dlFileNameBackgroundTasks.add(
				new DLFileNameWrapperBackgroundTaskImpl(backgroundTask));
		}

		return dlFileNameBackgroundTasks;
	}

}