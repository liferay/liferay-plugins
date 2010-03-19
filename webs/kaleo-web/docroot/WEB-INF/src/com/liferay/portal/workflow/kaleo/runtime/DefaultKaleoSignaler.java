/*
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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.DefaultSingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceTokenLocalService;

/**
 * <a href="DefaultKaleoSignaler.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class DefaultKaleoSignaler implements KaleoSignaler {

	public void signalEntry(
		String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();
		KaleoInstance kaleoInstance = kaleoInstanceToken.getKaleoInstance();
		KaleoDefinition kaleoDefinition = kaleoInstance.getKaleoDefinition();

		KaleoNode kaleoStartNode = kaleoDefinition.getKaleoStartNode();

		kaleoInstanceToken.setCurrentNode(kaleoStartNode);

		executionContext.setTransitionName(transitionName);

		PathElement startPathElement = new PathElement(
			null, kaleoStartNode, executionContext);

		_singleDestinationMessageSender.send(startPathElement);
	}

	public void signalExit(
		String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		KaleoNode currentKaleoNode = kaleoInstanceToken.getCurrentKaleoNode();

		PathElement pathElement = new PathElement(
			currentKaleoNode, null, executionContext);

		_singleDestinationMessageSender.send(pathElement);
	}

	public void setDestinationName(String destinationName) {
		DefaultSingleDestinationMessageSender singleDestinationMessageSender =
			new DefaultSingleDestinationMessageSender();

		singleDestinationMessageSender.setDestinationName(destinationName);
		singleDestinationMessageSender.setMessageSender(
			MessageBusUtil.getMessageSender());

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	@BeanReference(type = KaleoInstanceTokenLocalService.class)
	protected KaleoInstanceTokenLocalService kaleoInstanceTokenLocalService;

	private SingleDestinationMessageSender _singleDestinationMessageSender;

}
