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

package com.liferay.knowledgebase;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateKBArticleUrlTitleException extends PortalException {

	public DuplicateKBArticleUrlTitleException() {
		super();
	}

	public DuplicateKBArticleUrlTitleException(String msg) {
		super(msg);
	}

	public DuplicateKBArticleUrlTitleException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateKBArticleUrlTitleException(Throwable cause) {
		super(cause);
	}

}