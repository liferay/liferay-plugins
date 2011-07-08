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

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBTemplateConstants;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KBTemplateImpl extends KBTemplateBaseImpl {

	public KBTemplateImpl() {
	}

	public boolean isFreeMarker() {
		if (getEngineType() == KBTemplateConstants.ENGINE_TYPE_FREEMARKER) {
			return true;
		}

		return false;
	}

	public boolean isVelocity() {
		if (getEngineType() == KBTemplateConstants.ENGINE_TYPE_VELOCITY) {
			return true;
		}

		return false;
	}

}