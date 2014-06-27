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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Node;
import com.liferay.portal.workflow.kaleo.export.builder.DefinitionBuilder;

import java.io.IOException;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public class XMLDefinitionExporter implements DefinitionExporter {

	public void afterPropertiesSet() {
		_namespace = "urn:liferay.com:liferay-workflow_" + _version;
		_schemaVersion = StringUtil.replace(
			_version, StringPool.PERIOD, StringPool.UNDERLINE);
	}

	@Override
	public String export(long kaleoDefinitionId) throws PortalException {
		Definition definition = _definitionBuilder.buildDefinition(
			kaleoDefinitionId);

		return doExport(definition);
	}

	@Override
	public String export(long companyId, String name, int version)
		throws PortalException {

		Definition definition = _definitionBuilder.buildDefinition(
			companyId, name, version);

		return doExport(definition);
	}

	public void setDefinitionBuilder(DefinitionBuilder definitionBuilder) {
		_definitionBuilder = definitionBuilder;
	}

	public void setVersion(String version) {
		_version = version;
	}

	protected String doExport(Definition definition) {
		try {
			Document document = SAXReaderUtil.createDocument();

			Element workflowDefinitionElement = document.addElement(
				"workflow-definition");

			workflowDefinitionElement.addAttribute(
				"xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
			workflowDefinitionElement.addAttribute(
				"xsi:schemaLocation",
				"urn:liferay.com:liferay-workflow_" + _version +
					" http://www.liferay.com/dtd/liferay-workflow-definition_" +
					_schemaVersion + ".xsd");
			workflowDefinitionElement.addNamespace(
				"", "urn:liferay.com:liferay-workflow_" + _version);

			Element nameElement = workflowDefinitionElement.addElement(
				"name", _namespace);

			nameElement.addText(definition.getName());

			if (Validator.isNotNull(definition.getDescription())) {
				Element descriptionElement =
					workflowDefinitionElement.addElement(
						"description", _namespace);

				descriptionElement.addText(definition.getDescription());
			}

			Element versionElement = workflowDefinitionElement.addElement(
				"version", _namespace);

			versionElement.addText(String.valueOf(definition.getVersion()));

			Collection<Node> nodes = definition.getNodes();

			for (Node node : nodes) {
				NodeExporter nodeExporter =
					NodeExporterRegistry.getNodeExporter(node.getNodeType());

				nodeExporter.exportNode(
					node, workflowDefinitionElement, _namespace);
			}

			return document.formattedString();
		}
		catch (IOException ioe) {
			throw new SystemException("Unable to export definition", ioe);
		}
	}

	private DefinitionBuilder _definitionBuilder;
	private String _namespace;
	private String _schemaVersion;
	private String _version = ReleaseInfo.getVersion();

}