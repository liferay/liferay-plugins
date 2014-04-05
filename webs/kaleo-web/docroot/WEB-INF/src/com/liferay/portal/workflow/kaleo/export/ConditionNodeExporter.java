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

package com.liferay.portal.workflow.kaleo.export;

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.workflow.kaleo.definition.Condition;
import com.liferay.portal.workflow.kaleo.definition.Node;

/**
 * @author Michael C. Han
 */
public class ConditionNodeExporter
	extends BaseNodeExporter implements NodeExporter {

	@Override
	protected Element createNodeElement(Element element, String namespace) {
		return element.addElement("condition", namespace);
	}

	@Override
	protected void exportAdditionalNodeElements(
		Node node, Element nodeElement) {

		exportTimersElement(node, nodeElement, "timers", "timer");

		Condition condition = (Condition)node;

		populateScriptingElement(
			nodeElement, condition.getScript(),
			condition.getScriptLanguage().getValue(),
			condition.getScriptRequiredContexts());
	}

}