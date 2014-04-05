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

/**
 * @author Michael C. Han
 */
public class DefinitionExporterUtil {

	public static String export(long kaleoDefinitionId)
		throws PortalException, SystemException {

		return getDefinitionExporter().export(kaleoDefinitionId);
	}

	public static String export(long companyId, String name, int version)
		throws PortalException, SystemException {

		return getDefinitionExporter().export(companyId, name, version);
	}

	public static DefinitionExporter getDefinitionExporter() {
		return _definitionExporter;
	}

	public void setDefinitionExporter(DefinitionExporter definitionExporter) {
		_definitionExporter = definitionExporter;
	}

	private static DefinitionExporter _definitionExporter;

}