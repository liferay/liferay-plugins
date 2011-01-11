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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.workflow.kaleo.definition.DefinitionNode;
import com.liferay.portal.workflow.kaleo.service.ClpSerializer;

/**
 * @author Marcellus Tavares
 */
public class DefinitionNodeFactory {

	public DefinitionNode getDefinitionNode(String name) {
		DefinitionNode definitionNode =
			(DefinitionNode)PortletBeanLocatorUtil.locate(
				ClpSerializer.SERVLET_CONTEXT_NAME, name);

		return definitionNode;
	}

}