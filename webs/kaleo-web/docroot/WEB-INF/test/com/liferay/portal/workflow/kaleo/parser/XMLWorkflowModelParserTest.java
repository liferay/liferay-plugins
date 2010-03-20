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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.test.TestCase;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.xml.SAXReaderImpl;

import java.io.ByteArrayInputStream;

/**
 * <a href="XMLWorkflowModelParserTest.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class XMLWorkflowModelParserTest extends TestCase {

	public void testParseDefinition() throws Exception{
		String workflowXML =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
			"<workflow-definition xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:liferay.com:liferay-workflow_6.0.0 D:\\projects\\liferay\\trunk\\portal\\definitions\\liferay-workflow-definition_6_0_0.xsd\" xmlns=\"urn:liferay.com:liferay-workflow_6.0.0\">\n" +
			"\t<name>SampleWorkflow</name>\n" +
			"\t<description>Sample workflow definition</description>\n" +
			"\t<version>1</version>\n" +
			"\t<state name=\"Created\" initial=\"true\">\n" +
			"\t\t<description>some description</description>\n" +
			"\t\t<transitions>\n" +
			"\t\t\t<transition name=\"review_fork\" target=\"Begin Reviews\" default=\"true\"/>\n" +
			"\t\t</transitions>\n" +
			"\t</state>\n" +
			"\t<fork name=\"Begin Reviews\">\n" +
			"\t\t<description>some description</description>\n" +
			"\t\t<transitions>\n" +
			"\t\t\t<transition name=\"contentReview\" target=\"Content Review\"/>\n" +
			"\t\t\t<transition name=\"publisherReview\" target=\"Publisher Review\"/>\n" +
			"\t\t</transitions>\n" +
			"\t</fork>\n" +
			"\t<task name=\"Content Review\">\n" +
			"\t\t<description>Performing content reviews</description>\n" +
			"\t\t<transitions>\n" +
			"\t\t\t<transition name=\"Approve Content\" target=\"Pending Reviews\" default=\"true\"/>\n" +
			"\t\t</transitions>\n" +
			"\t\t<assignments>\n" +
			"\t\t\t<role name=\"Content_Reviewers\" default=\"true\"/>\n" +
			"\t\t</assignments>\n" +
			"\t</task>\n" +
			"\t<task name=\"Publisher Review\">\n" +
			"\t\t<description>Performing publisher reviews</description>\n" +
			"\t\t<transitions>\n" +
			"\t\t\t<transition name=\"Approve Publish\" target=\"Pending Reviews\" default=\"true\"/>\n" +
			"\t\t</transitions>\n" +
			"\t\t<assignments>\n" +
			"\t\t\t<role name=\"Publishers\" default=\"true\"/>\n" +
			"\t\t</assignments>\n" +
			"\t</task>\n" +
			"\t<join name=\"Pending Reviews\">\n" +
			"\t\t<description>some description</description>\n" +
			"\t\t<transitions>\n" +
			"\t\t\t<transition name=\"Final Approval\" target=\"Approved\" default=\"true\"/>\n" +
			"\t\t</transitions>\n" +
			"\t</join>\n" +
			"\t<state name=\"Approved\">\n" +
			"\t\t<description>some description</description>\n" +
			"\t\t<actions>\n" +
			"\t\t\t<action execution-order=\"0\" name=\"myName\" language=\"javascript\" type=\"onEntry\">\n" +
			"\t\t\t\t<script><![CDATA[\n" +
			"\t\t\t\t\tPackages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(\n" +
			"\t\t\t\t\t\tcompanyId, groupId, userId, entryClassName, entryClassPK,\n" +
			"\t\t\t\t\t\tPackages.com.liferay.portal.kernel.workflow.StatusConstants.fromLabel(\"approved\"));\n" +
			"\t\t\t\t\t\t]]>\n" +
			"\t\t\t\t</script>\n" +
			"\t\t\t</action>\n" +
			"\t\t\t<notification description=\"someDescription\" name=\"myName\" language=\"text\" type=\"onEntry\">\n" +
			"\t\t\t\t<recipients>\n" +
			"\t\t\t\t\t<role name=\"SomeName\" />\n" +
			"\t\t\t\t\t<user screen-name=\"test\"/>\n" +
			"\t\t\t\t</recipients>\n" +
			"\t\t\t\t<notification-type value=\"email\" />\n" +
			"\t\t\t\t<template><![CDATA[\n" +
			"\t\t\t\t\tPackages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(\n" +
			"\t\t\t\t\t\tcompanyId, groupId, userId, entryClassName, entryClassPK,\n" +
			"\t\t\t\t\t\tPackages.com.liferay.portal.kernel.workflow.StatusConstants.fromLabel(\"approved\"));\n" +
			"\t\t\t\t\t\t]]>\n" +
			"\t\t\t\t</template>\n" +
			"\t\t\t</notification>\n" +
			"\t\t</actions>\n" +
			"\t</state>\n" +
			"</workflow-definition>";

		Definition definition =_parser.parse(
			new ByteArrayInputStream(workflowXML.getBytes()));

		assertEquals(6, definition.getNodes().size());
		assertEquals(1, definition.getNode("Approved").getActions().size());
		assertEquals(1, definition.getNode("Approved").getNotifications().size());

	}

	protected void setUp() throws Exception {
		super.setUp();

		_parser = new XMLWorkflowModelParser();
		_parser.setValidate(false);

		SAXReaderUtil readerUtil = new SAXReaderUtil();
		readerUtil.setSAXReader(new SAXReaderImpl());
	}

	private XMLWorkflowModelParser _parser;
}