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
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;

/**
 * @author Michael C. Han
 */
public class ConditionNodeBuilder
	extends BaseNodeBuilder implements NodeBuilder {

	@Override
	protected Node createNode(KaleoNode kaleoNode) throws PortalException {
		KaleoCondition kaleoCondition =
			kaleoConditionLocalService.getKaleoNodeKaleoCondition(
				kaleoNode.getKaleoNodeId());

		return new Condition(
			kaleoNode.getName(), kaleoNode.getDescription(),
			kaleoCondition.getScript(), kaleoCondition.getScriptLanguage(),
			kaleoCondition.getScriptRequiredContexts());
	}

}