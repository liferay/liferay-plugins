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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTransitionImpl extends KaleoTransitionBaseImpl {

	public KaleoTransitionImpl() {
	}

	@Override
	public KaleoNode getSourceKaleoNode() throws PortalException {
		return KaleoNodeLocalServiceUtil.getKaleoNode(getSourceKaleoNodeId());
	}

	@Override
	public KaleoNode getTargetKaleoNode() throws PortalException {
		return KaleoNodeLocalServiceUtil.getKaleoNode(getTargetKaleoNodeId());
	}

}