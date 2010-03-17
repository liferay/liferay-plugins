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

package com.liferay.portal.workflow.kaleo.model;


/**
 * <a href="KaleoTaskInstanceAssignment.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoTaskInstanceAssignment table in the
 * database.
 * </p>
 *
 * <p>
 * Customize {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentImpl} and rerun the
 * ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentModel
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceAssignmentModelImpl
 * @generated
 */
public interface KaleoTaskInstanceAssignment
	extends KaleoTaskInstanceAssignmentModel {
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isCompleted();
}