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
 * The extended model interface for the KaleoInstanceToken service. Represents a row in the &quot;KaleoInstanceToken&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoInstanceTokenModel
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl
 * @see com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenModelImpl
 * @generated
 */
public interface KaleoInstanceToken extends KaleoInstanceTokenModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceTokenImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getCurrentKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getParentKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean hasIncompleteChildrenKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.SystemException;

	public void setCurrentKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}