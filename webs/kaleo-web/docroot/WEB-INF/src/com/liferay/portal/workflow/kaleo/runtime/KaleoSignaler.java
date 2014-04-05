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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

/**
 * @author Michael C. Han
 */
public interface KaleoSignaler {

	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException;

	public void signalExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException;

	public void signalExit(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException;

}