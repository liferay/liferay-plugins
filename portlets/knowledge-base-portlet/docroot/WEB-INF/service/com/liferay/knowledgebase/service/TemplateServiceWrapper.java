/**
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

package com.liferay.knowledgebase.service;


/**
 * <a href="TemplateServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link TemplateService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TemplateService
 * @generated
 */
public class TemplateServiceWrapper implements TemplateService {
	public TemplateServiceWrapper(TemplateService templateService) {
		_templateService = templateService;
	}

	public TemplateService getWrappedTemplateService() {
		return _templateService;
	}

	private TemplateService _templateService;
}