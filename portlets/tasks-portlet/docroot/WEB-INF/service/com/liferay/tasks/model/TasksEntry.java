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

package com.liferay.tasks.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the TasksEntry service. Represents a row in the &quot;TMS_TasksEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see TasksEntryModel
 * @see com.liferay.tasks.model.impl.TasksEntryImpl
 * @see com.liferay.tasks.model.impl.TasksEntryModelImpl
 * @generated
 */
public interface TasksEntry extends TasksEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.tasks.model.impl.TasksEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.lang.String getAssigneeFullName();

	public java.lang.String getPriorityLabel();

	public java.lang.String getReporterFullName();

	public java.lang.String getStatusLabel();
}