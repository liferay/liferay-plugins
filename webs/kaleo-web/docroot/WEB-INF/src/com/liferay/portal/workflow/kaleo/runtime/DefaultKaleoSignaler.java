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

package com.liferay.portal.workflow.kaleo.runtime;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.sender.DefaultSingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.NodeType;
import com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutor;
import com.liferay.portal.workflow.kaleo.runtime.node.NodeExecutorFactory;
import com.liferay.portal.workflow.kaleo.runtime.util.ExecutionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
@Transactional(
	isolation = Isolation.PORTAL, propagation = Propagation.SUPPORTS,
	rollbackFor = {Exception.class})
public class DefaultKaleoSignaler
	extends BaseKaleoBean implements KaleoSignaler {

	public void signalEntry(
			String transitionName, ExecutionContext executionContext)
		throws PortalException, SystemException {

		KaleoInstanceToken kaleoInstanceToken =
			executionContext.getKaleoInstanceToken();

		executionContext.setTransitionName(transitionName);

		PathElement startPathElement = new PathElement(
			null, kaleoInstanceToken.getCurrentKaleoNode(), executionContext);

		_singleDestinationMessageSender.send(startPathElement);
	}

	@Transactional(
		isolation = Isolation.PORTAL, propagation = Propagation.REQUIRED,
		rollbackFor = {Exception.class})
	public void signalExecute(
			KaleoNode currentKaleoNode, ExecutionContext executionContext)
		throws PortalException, SystemException {

		NodeExecutor nodeExecutor = NodeExecutorFactory.getNodeExecutor(
			NodeType.valueOf(currentKaleoNode.getType()));

		List<PathElement> remainingPathElements = new ArrayList<PathElement>();

		nodeExecutor.execute(
			currentKaleoNode, executionContext, remainingPathElements);

		ExecutionUtil.checkKaleoInstanceComplete(executionContext);

		for (PathElement remainingPathElement : remainingPathElements) {
			_singleDestinationMessageSender.send(remainingPathElement);
		}

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

	private SingleDestinationMessageSender _singleDestinationMessageSender;

}