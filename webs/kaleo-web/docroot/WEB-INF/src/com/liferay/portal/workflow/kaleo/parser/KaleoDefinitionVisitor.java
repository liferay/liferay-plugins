/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.BaseVisitor;
import com.liferay.portal.kernel.xml.CDATA;
import com.liferay.portal.kernel.xml.Comment;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Entity;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.Text;
import com.liferay.portal.workflow.kaleo.definition.DefinitionNode;

import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class KaleoDefinitionVisitor extends BaseVisitor<DefinitionNode> {

	public void setDefinitionNodeFactory(
		DefinitionNodeFactory definitionNodeFactory) {

		_definitionNodeFactory = definitionNodeFactory;
	}

	protected DefinitionNode handleAttribute(Attribute attribute) {
		return new com.liferay.portal.workflow.kaleo.definition.Attribute(
			attribute.getName(), attribute.getValue());
	}

	protected DefinitionNode handleCDATA(CDATA cdata) {
		String value = cdata.getText();

		if (value != null && Validator.isNotNull(value.trim())) {
			return new com.liferay.portal.workflow.kaleo.definition.Text(value);
		}

		return null;
	}

	protected DefinitionNode handleComment(Comment comment) {
		return null;
	}

	protected DefinitionNode handleDocument(Document document,
			List<DefinitionNode> nodeResults) {

		if (!nodeResults.isEmpty()) {
			return nodeResults.get(0);
		}

		return null;
	}

	protected DefinitionNode handleElement(
		Element element, List<DefinitionNode> attributeResults,
		List<DefinitionNode> nodeResults) {

		String name = element.getName();

		DefinitionNode node = _definitionNodeFactory.getDefinitionNode(name);

		for (DefinitionNode nodeResult : nodeResults) {
			nodeResult.configureParent(node);
		}

		return node;
	}

	protected DefinitionNode handleEntity(Entity entity) {
		return null;
	}

	protected DefinitionNode handleNamespace(Namespace namespace) {
		return null;
	}

	protected DefinitionNode handleNode(Node node) {
		return null;
	}

	protected DefinitionNode handleProcessInstruction(
		ProcessingInstruction processingInstruction) {

		return null;
	}

	protected DefinitionNode handleText(Text text) {
		String value = text.getText();

		if (value != null && Validator.isNotNull(value.trim())) {
			return new com.liferay.portal.workflow.kaleo.definition.Text(value);
		}

		return null;
	}

	private DefinitionNodeFactory _definitionNodeFactory;

}