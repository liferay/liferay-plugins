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

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.kernel.xml.Visitor;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.DefinitionNode;

import java.io.InputStream;

/**
 * @author Michael C. Han
 * @author Marcellus Tavares
 */
public class XMLWorkflowModelParser implements WorkflowModelParser {

	public Definition parse(InputStream inputStream) throws WorkflowException {
		try {
			return doParse(inputStream);
		}
		catch (Exception e) {
			throw new WorkflowException("Unable to parse definition", e);
		}
	}

	public void setValidate(boolean validate) {
		_validate = validate;
	}

	public void setVisitor(Visitor<DefinitionNode> visitor) {
		_visitor = visitor;
	}

	protected Definition doParse(InputStream inputStream) throws Exception {
		Document document = SAXReaderUtil.read(inputStream, _validate);

		return (Definition)document.accept(_visitor);
	}

	private boolean _validate;
	private Visitor<DefinitionNode> _visitor;

}