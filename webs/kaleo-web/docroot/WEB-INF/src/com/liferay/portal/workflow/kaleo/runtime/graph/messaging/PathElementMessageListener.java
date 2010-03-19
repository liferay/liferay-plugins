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

package com.liferay.portal.workflow.kaleo.runtime.graph.messaging;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.sender.DefaultSingleDestinationMessageSender;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.workflow.kaleo.runtime.graph.GraphWalker;
import com.liferay.portal.workflow.kaleo.runtime.graph.PathElement;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="PathElementMessageListener.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class PathElementMessageListener implements MessageListener {

	public void receive(Message message) {

		PathElement pathElement = (PathElement) message.getPayload();
		List<PathElement> remainingPathElements = new ArrayList<PathElement>();

		try {
			_graphWalker.follow(
				pathElement.getStartNode(), pathElement.getTargetNode(),
				remainingPathElements, pathElement.getExecutionContext());
		}
		catch (Exception e) {
			//TBD Need to write a log message
		}

		for (PathElement remainingPathElement : remainingPathElements) {
			_singleDestinationMessageSender.send(remainingPathElement);
		}
	}

	public void setGraphWalker(GraphWalker graphWalker) {
		_graphWalker = graphWalker;
	}

	public void setDestinationName(String destinationName) {
		DefaultSingleDestinationMessageSender singleDestinationMessageSender =
			new DefaultSingleDestinationMessageSender();

		singleDestinationMessageSender.setDestinationName(destinationName);
		singleDestinationMessageSender.setMessageSender(
			MessageBusUtil.getMessageSender());

		_singleDestinationMessageSender  = singleDestinationMessageSender ;
	}


	private GraphWalker _graphWalker;
	private SingleDestinationMessageSender _singleDestinationMessageSender;

}
