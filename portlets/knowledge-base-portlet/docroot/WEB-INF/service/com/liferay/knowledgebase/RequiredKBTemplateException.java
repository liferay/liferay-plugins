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

package com.liferay.knowledgebase;

import com.liferay.knowledgebase.model.KBTemplate;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class RequiredKBTemplateException extends PortalException {

	public RequiredKBTemplateException() {
		super();
	}

	public RequiredKBTemplateException(String msg) {
		super(msg);
	}

	public RequiredKBTemplateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RequiredKBTemplateException(Throwable cause) {
		super(cause);
	}

	public RequiredKBTemplateException(KBTemplate kbTemplate) {
		_kbTemplate = kbTemplate;
	}

	public KBTemplate getKBTemplate() {
		return _kbTemplate;
	}

	public void setKBTemplate(KBTemplate kbTemplate) {
		_kbTemplate = kbTemplate;
	}

	private KBTemplate _kbTemplate;

}