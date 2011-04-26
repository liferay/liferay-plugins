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

package com.liferay.knowledgebase.model;

/**
 * @author Peter Shin
 */
public class KBTemplateConstants {

	public static final int DEFAULT_ENGINE_TYPE =
		KBTemplateConstants.ENGINE_TYPE_VELOCITY;

	public static final int ENGINE_TYPE_FREEMARKER = 1;

	public static final String ENGINE_TYPE_FREEMARKER_LABEL = "freemarker";

	public static final int ENGINE_TYPE_VELOCITY = 0;

	public static final String ENGINE_TYPE_VELOCITY_LABEL = "velocity";

	public static final int[] ENGINE_TYPES = {
		ENGINE_TYPE_FREEMARKER, ENGINE_TYPE_VELOCITY
	};

	public static String getEngineTypeLabel(int engineType) {
		if (engineType == ENGINE_TYPE_FREEMARKER) {
			return ENGINE_TYPE_FREEMARKER_LABEL;
		}
		else {
			return ENGINE_TYPE_VELOCITY_LABEL;
		}
	}

}