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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoInstanceTokenImpl extends KaleoInstanceTokenBaseImpl {

	public static final long DEFAULT_PARENT_KALEO_INSTANCE_TOKEN_ID = 0;

	public KaleoInstanceTokenImpl() {
	}

	@Override
	public List<KaleoInstanceToken> getChildrenKaleoInstanceTokens() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(getCompanyId());

		return KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceTokens(
			getKaleoInstanceTokenId(), serviceContext);
	}

	@Override
	public KaleoNode getCurrentKaleoNode() throws PortalException {
		return KaleoNodeLocalServiceUtil.getKaleoNode(getCurrentKaleoNodeId());
	}

	@Override
	public List<KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(getCompanyId());

		return KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceTokens(
			getKaleoInstanceTokenId(), null, serviceContext);
	}

	@Override
	public KaleoInstance getKaleoInstance() throws PortalException {
		return KaleoInstanceLocalServiceUtil.getKaleoInstance(
			getKaleoInstanceId());
	}

	@Override
	public KaleoInstanceToken getParentKaleoInstanceToken()
		throws PortalException {

		return KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceToken(
			getParentKaleoInstanceTokenId());
	}

	@Override
	public boolean hasIncompleteChildrenKaleoInstanceToken() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(getCompanyId());

		int count =
			KaleoInstanceTokenLocalServiceUtil.getKaleoInstanceTokensCount(
				getKaleoInstanceTokenId(), null, serviceContext);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void setCurrentKaleoNode(KaleoNode kaleoNode)
		throws PortalException {

		setCurrentKaleoNodeId(kaleoNode.getKaleoNodeId());

		KaleoInstanceTokenLocalServiceUtil.updateKaleoInstanceToken(
			getKaleoInstanceTokenId(), kaleoNode.getKaleoNodeId());
	}

}