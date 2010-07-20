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
 * <p>
 * This interface is a model that represents the Kaleo_KaleoDefinition table in the
 * database.
 * </p>
 *
 * <p>
 * Customize {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl} and rerun the
 * ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinitionModel
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoDefinitionModelImpl
 * @generated
 */
public interface KaleoDefinition extends KaleoDefinitionModel {
	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoStartNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasIncompleteKaleoInstances()
		throws com.liferay.portal.kernel.exception.SystemException;
}