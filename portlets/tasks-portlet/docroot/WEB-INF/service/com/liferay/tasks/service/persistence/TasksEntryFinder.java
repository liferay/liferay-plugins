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

package com.liferay.tasks.service.persistence;

/**
 * @author Ryan Park
 */
public interface TasksEntryFinder {
	public int countByG_P_A_R_S_T_N(long groupId, int priority,
		long assigneeUserId, long reporterUserId, int status,
		long[] assetTagIds, long[] notAssetTagIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.tasks.model.TasksEntry> findByG_P_A_R_S_T_N(
		long groupId, int priority, long assigneeUserId, long reporterUserId,
		int status, long[] assetTagIds, long[] notAssetTagIds, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;
}