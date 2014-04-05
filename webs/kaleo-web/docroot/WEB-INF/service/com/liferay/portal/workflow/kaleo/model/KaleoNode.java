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

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the KaleoNode service. Represents a row in the &quot;KaleoNode&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNodeModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeModelImpl
 * @generated
 */
public interface KaleoNode extends KaleoNodeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoNodeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getDefaultKaleoTransition()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions()
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean hasKaleoTransition()
		throws com.liferay.portal.kernel.exception.SystemException;
}