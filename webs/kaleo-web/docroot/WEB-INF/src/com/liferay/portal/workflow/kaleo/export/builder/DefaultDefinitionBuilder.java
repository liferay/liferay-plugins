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

package com.liferay.portal.workflow.kaleo.export.builder;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.BaseKaleoBean;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.definition.Transition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class DefaultDefinitionBuilder
	extends BaseKaleoBean implements DefinitionBuilder {

	@Override
	public Definition buildDefinition(long kaleoDefinitionId)
		throws PortalException {

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionLocalService.getKaleoDefinition(kaleoDefinitionId);

		return doBuildDefinition(kaleoDefinition);
	}

	@Override
	public Definition buildDefinition(long companyId, String name, int version)
		throws PortalException {

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);

		KaleoDefinition kaleoDefinition =
			kaleoDefinitionLocalService.getKaleoDefinition(
				name, version, serviceContext);

		return doBuildDefinition(kaleoDefinition);
	}

	protected Definition doBuildDefinition(KaleoDefinition kaleoDefinition)
		throws PortalException {

		Definition definition = new Definition(
			kaleoDefinition.getName(), kaleoDefinition.getDescription(),
			kaleoDefinition.getContent(), kaleoDefinition.getVersion());

		List<KaleoNode> kaleoNodes =
			kaleoNodeLocalService.getKaleoDefinitionKaleoNodes(
				kaleoDefinition.getKaleoDefinitionId());

		for (KaleoNode kaleoNode : kaleoNodes) {
			NodeBuilder nodeBuilder = NodeBuilderRegistry.getNodeBuilder(
				kaleoNode.getType());

			Node node = nodeBuilder.buildNode(kaleoNode);

			definition.addNode(node);
		}

		List<KaleoTransition> kaleoTransitions =
			kaleoTransitionLocalService.getKaleoDefinitionKaleoTransitions(
				kaleoDefinition.getKaleoDefinitionId());

		for (KaleoTransition kaleoTransition : kaleoTransitions) {
			String sourceNodeName = kaleoTransition.getSourceKaleoNodeName();

			Node sourceNode = definition.getNode(sourceNodeName);

			String targetNodeName = kaleoTransition.getTargetKaleoNodeName();

			Node targetNode = definition.getNode(targetNodeName);

			Transition transition = new Transition(
				kaleoTransition.getName(), sourceNode, targetNode,
				kaleoTransition.isDefaultTransition());

			sourceNode.addOutgoingTransition(transition);

			targetNode.addIncomingTransition(transition);
		}

		return definition;
	}

}